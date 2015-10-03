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

import android.content.res.Resources;
import android.text.format.DateFormat;

import java.util.Calendar;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.model.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.GetCityTask;
import cat.ppicas.cleanarch.ui.vista.CityDetailVista;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.framework.task.TaskExecutor;
import cat.ppicas.framework.ui.Presenter;

public class CityDetailPresenter extends Presenter<CityDetailVista> {

    private static final String DAY_OF_WEEK_DATE_FORMAT_PATTERN = "cccc";

    private final TaskExecutor mTaskExecutor;
    private final CityRepository mCityRepository;
    private final Resources mResources;
    private final String mCityId;

    private GetCityTask mGetCityTask;
    private City mCity;

    public CityDetailPresenter(TaskExecutor taskExecutor, CityRepository cityRepository,
            Resources resources, String cityId) {
        mTaskExecutor = taskExecutor;
        mCityRepository = cityRepository;
        mResources = resources;
        mCityId = cityId;
    }

    @Override
    public void onStart(CityDetailVista vista) {
        vista.setTitle(R.string.city_details__title_loading);

        if (mCity != null) {
            vista.setTitle(R.string.city_details__title, mCity.getName());
        } else {
            vista.displayLoading(true);
            if (!mTaskExecutor.isRunning(mGetCityTask)) {
                mGetCityTask = new GetCityTask(mCityRepository, mCityId);
                mTaskExecutor.execute(mGetCityTask, new GetCityTaskCallback());
            }
        }
    }

    public String getForecastPageTitle(int daysFromToday) {
        if (daysFromToday == 0) {
            return mResources.getString(R.string.city_details__tab_forecast,
                    mResources.getString(R.string.global__today));
        } else if (daysFromToday == 1) {
            return mResources.getString(R.string.city_details__tab_forecast,
                    mResources.getString(R.string.global__tomorrow));
        } else {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, daysFromToday);
            return mResources.getString(R.string.city_details__tab_forecast,
                    DateFormat.format(DAY_OF_WEEK_DATE_FORMAT_PATTERN, cal));
        }
    }

    private class GetCityTaskCallback extends DisplayErrorTaskCallback<City> {

        public GetCityTaskCallback() {
            super(CityDetailPresenter.this);
        }

        @Override
        public void onSuccess(City city) {
            mCity = city;
            CityDetailVista vista = getVista();
            if (vista != null) {
                vista.displayLoading(false);
                vista.setTitle(R.string.city_details__title, city.getName());
            }
        }
    }
}
