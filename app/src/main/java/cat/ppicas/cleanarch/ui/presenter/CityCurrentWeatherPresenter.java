/*
 * Copyright (C) 2015 Pau Picas Sans <pau.picas@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;
import cat.ppicas.cleanarch.task.GetCurrentWeatherTask;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.vista.CityCurrentWeatherVista;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.framework.task.TaskExecutor;

public class CityCurrentWeatherPresenter extends Presenter<CityCurrentWeatherVista> {

    private final TaskExecutor mTaskExecutor;
    private final CurrentWeatherRepository mRepository;
    private final String mCityId;

    private GetCurrentWeatherTask mGetCurrentWeatherTask;
    private CurrentWeather mLastCurrentWeather;

    public CityCurrentWeatherPresenter(TaskExecutor taskExecutor,
            CurrentWeatherRepository repository, String cityId) {
        mTaskExecutor = taskExecutor;
        mRepository = repository;
        mCityId = cityId;
    }

    @Override
    public void onStart(CityCurrentWeatherVista vista) {
        if (mLastCurrentWeather != null) {
            updateVista(vista, mLastCurrentWeather);
        } else {
            vista.displayLoading(true);
            if (!mTaskExecutor.isRunning(mGetCurrentWeatherTask)) {
                mGetCurrentWeatherTask = new GetCurrentWeatherTask(mRepository, mCityId);
                mTaskExecutor.execute(mGetCurrentWeatherTask, new GetCurrentWeatherTaskCallback());
            }
        }
    }

    private void updateVista(CityCurrentWeatherVista vista, CurrentWeather cw) {
        vista.setCurrentTemp(NumberFormat.formatTemperature(cw.getCurrentTemp()));
        vista.setHumidity(NumberFormat.formatHumidity(cw.getHumidity()));
        vista.setWindSpeed(NumberFormat.formatWindSpeed(cw.getWindSpeed()));
    }

    private class GetCurrentWeatherTaskCallback extends DisplayErrorTaskCallback<CurrentWeather> {
        public GetCurrentWeatherTaskCallback() {
            super(CityCurrentWeatherPresenter.this);
        }

        @Override
        public void onSuccess(CurrentWeather cw) {
            mLastCurrentWeather = cw;
            CityCurrentWeatherVista vista = getVista();
            if (vista != null) {
                vista.displayLoading(false);
                updateVista(vista, cw);
            }
        }
    }
}
