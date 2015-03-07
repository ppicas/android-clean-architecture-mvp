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
import cat.ppicas.cleanarch.ui.display.CityCurrentWeatherDisplay;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityCurrentWeatherPresenter extends Presenter<CityCurrentWeatherDisplay> {

    private TaskExecutor mTaskExecutor;
    private CurrentWeatherRepository mRepository;
    private String mCityId;

    private GetCurrentWeatherTask mGetCurrentWeatherTask;
    private CurrentWeather mLastCurrentWeather;

    public CityCurrentWeatherPresenter(TaskExecutor taskExecutor,
            CurrentWeatherRepository repository, String cityId) {
        mTaskExecutor = taskExecutor;
        mRepository = repository;
        mCityId = cityId;
    }

    @Override
    public void bindDisplay(CityCurrentWeatherDisplay display) {
        super.bindDisplay(display);

        if (mLastCurrentWeather != null) {
            updateDisplay(display, mLastCurrentWeather);
            return;
        }

        display.displayLoading(true);

        if (mTaskExecutor.isRunning(mGetCurrentWeatherTask)) {
            return;
        }
        mGetCurrentWeatherTask = new GetCurrentWeatherTask(mRepository, mCityId);
        mTaskExecutor.execute(mGetCurrentWeatherTask,
                new DisplayErrorTaskCallback<CurrentWeather>(this) {
                    @Override
                    public void onSuccess(CurrentWeather cw) {
                        mLastCurrentWeather = cw;
                        CityCurrentWeatherDisplay display = getDisplay();
                        if (display != null) {
                            display.displayLoading(false);
                            updateDisplay(display, cw);
                        }
                    }
                });
    }

    private void updateDisplay(CityCurrentWeatherDisplay display, CurrentWeather cw) {
        display.setCurrentTemp(NumberFormat.formatTemperature(cw.getCurrentTemp()));
        display.setHumidity(NumberFormat.formatHumidity(cw.getHumidity()));
        display.setWindSpeed(NumberFormat.formatWindSpeed(cw.getWindSpeed()));
    }
}
