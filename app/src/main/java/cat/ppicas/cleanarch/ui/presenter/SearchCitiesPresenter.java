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

import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.FindCityTask;
import cat.ppicas.cleanarch.ui.activity.ActivityNavigator;
import cat.ppicas.cleanarch.ui.vista.SearchCitiesVista;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class SearchCitiesPresenter extends Presenter<SearchCitiesVista> {

    private static final String STATE_LAST_SEARCH = "lastSearch";

    private TaskExecutor mTaskExecutor;
    private ActivityNavigator mActivityNavigator;
    private CityRepository mCityRepository;

    private FindCityTask mFindCityTask;
    private String mLastSearch;
    private List<City> mLastResults;

    public SearchCitiesPresenter(TaskExecutor taskExecutor, ActivityNavigator activityNavigator,
            CityRepository cityRepository) {
        mTaskExecutor = taskExecutor;
        mActivityNavigator = activityNavigator;
        mCityRepository = cityRepository;
    }

    @Override
    public void bindVista(SearchCitiesVista vista) {
        super.bindVista(vista);

        vista.setTitle(R.string.search_cities__title);

        if (mTaskExecutor.isRunning(mFindCityTask)) {
            vista.displayLoading(true);
        }

        if (mLastResults != null) {
            vista.setCities(mLastResults);
        } else if (!TextUtils.isEmpty(mLastSearch) && !mTaskExecutor.isRunning(mFindCityTask)) {
            onCitySearch(mLastSearch);
        }
    }

    public void onCitySearch(String cityName) {
        cityName = cityName.trim().toLowerCase();
        mLastSearch = cityName;

        getVista().displayLoading(true);

        if (mFindCityTask != null) {
            mFindCityTask.cancel();
        }
        mFindCityTask = new FindCityTask(cityName, mCityRepository);
        mTaskExecutor.execute(mFindCityTask, new DisplayErrorTaskCallback<List<City>>(this) {
            @Override
            public void onSuccess(List<City> result) {
                mLastResults = result;
                SearchCitiesVista vista = getVista();
                if (vista != null) {
                    vista.displayLoading(false);
                    if (result.isEmpty()) {
                        vista.displayCitiesNotFound();
                    } else {
                        vista.setCities(result);
                    }
                }
            }

            @Override
            public void onError(Exception error) {
                super.onError(error);
                mLastSearch = null;
            }
        });
    }

    public void onCitySelected(String cityId) {
        mActivityNavigator.openCityDetails(cityId);
    }

    @Override
    public void saveState(Bundle state) {
        state.putString(STATE_LAST_SEARCH, mLastSearch);
    }

    @Override
    public void restoreState(Bundle state) {
        mLastSearch = state.getString(STATE_LAST_SEARCH);
    }

    @Override
    public void onDestroy() {
        if (mFindCityTask != null) {
            mFindCityTask.cancel();
        }
    }
}
