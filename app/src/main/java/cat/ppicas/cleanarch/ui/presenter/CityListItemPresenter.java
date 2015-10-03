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

import cat.ppicas.cleanarch.model.City;
import cat.ppicas.cleanarch.task.GetElevationTask;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.vista.CityListItemVista;
import cat.ppicas.framework.task.SuccessTaskCallback;
import cat.ppicas.framework.task.TaskExecutor;
import cat.ppicas.framework.ui.Presenter;

public class CityListItemPresenter extends Presenter<CityListItemVista> {

    private TaskExecutor mTaskExecutor;
    private City mCity;

    private int mElevation = -1;
    private GetElevationTask mGetElevationTask;

    public CityListItemPresenter(TaskExecutor taskExecutor, City city) {
        mTaskExecutor = taskExecutor;
        mCity = city;
    }

    @Override
    public void onStart(CityListItemVista vista) {
        updateVista();

        if (mElevation > -1) {
            vista.setLoadingElevation(false);
            vista.setElevation(mElevation);
        } else {
            vista.setLoadingElevation(true);
            if (!mTaskExecutor.isRunning(mGetElevationTask)) {
                mGetElevationTask = new GetElevationTask(mCity);
                mTaskExecutor.execute(mGetElevationTask, new GetElevationTaskCallback());
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mTaskExecutor.isRunning(mGetElevationTask)) {
            mGetElevationTask.cancel();
        }
    }

    public void setCity(City city) {
        if (mCity.getId().equals(city.getId()) && mCity.getName().equals(city.getName())) {
            return;
        }

        mCity = city;
        updateVista();

        if (mTaskExecutor.isRunning(mGetElevationTask)) {
            mGetElevationTask.cancel();
        }
        if (getVista() != null) {
            getVista().setLoadingElevation(true);
        }
        mElevation = -1;
        mGetElevationTask = new GetElevationTask(city);
        mTaskExecutor.execute(mGetElevationTask, new GetElevationTaskCallback());
    }

    private void updateVista() {
        CityListItemVista vista = getVista();
        if (vista == null) {
            return;
        }

        vista.setCityName(mCity.getName());
        vista.setCountry(mCity.getCountry());
        double temp = mCity.getCurrentWeatherPreview().getCurrentTemp();
        vista.setCurrentTemp(NumberFormat.formatTemperature(temp));
    }

    private class GetElevationTaskCallback extends SuccessTaskCallback<Integer> {
        @Override
        public void onSuccess(Integer elevation) {
            mElevation = elevation;
            if (getVista() != null) {
                getVista().setElevation(elevation);
                getVista().setLoadingElevation(false);
            }
        }
    }
}
