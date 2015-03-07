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

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.cleanarch.task.GetDailyForecastsTask;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityDailyForecastDisplay;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityDailyForecastPresenter extends Presenter<CityDailyForecastDisplay> {

    private TaskExecutor mTaskExecutor;
    private DailyForecastRepository mRepository;
    private final String mCityId;
    private final int mDaysFromToday;

    private GetDailyForecastsTask mGetDailyForecastsTask;
    private DailyForecast mLastDailyForecast;

    public CityDailyForecastPresenter(TaskExecutor taskExecutor, DailyForecastRepository repository,
            String cityId, int daysFromToday) {
        mTaskExecutor = taskExecutor;
        mRepository = repository;
        mCityId = cityId;
        mDaysFromToday = daysFromToday;
    }

    @Override
    public void bindDisplay(CityDailyForecastDisplay display) {
        super.bindDisplay(display);

        if (mLastDailyForecast != null) {
            updateDisplay(display, mLastDailyForecast);
            return;
        }

        display.displayLoading(true);

        if (mTaskExecutor.isRunning(mGetDailyForecastsTask)) {
            return;
        }
        mGetDailyForecastsTask = new GetDailyForecastsTask(mRepository, mCityId);
        mTaskExecutor.execute(mGetDailyForecastsTask,
                new DisplayErrorTaskCallback<List<DailyForecast>>(this) {
                    @Override
                    public void onSuccess(List<DailyForecast> list) {
                        DailyForecast dailyForecast = null;
                        if (list.size() >= mDaysFromToday + 1) {
                            dailyForecast = list.get(mDaysFromToday);
                        }

                        mLastDailyForecast = dailyForecast;

                        CityDailyForecastDisplay display = getDisplay();
                        if (display != null) {
                            display.displayLoading(false);
                            if (dailyForecast != null) {
                                updateDisplay(display, dailyForecast);
                            } else {
                                display.displayError(R.string.error__connection);
                            }
                        }
                    }
                });
    }

    private void updateDisplay(CityDailyForecastDisplay display, DailyForecast df) {
        display.setForecastDescription(capitalizeFirstLetter(
                df.getDescription()));
        display.setDayTemp(NumberFormat.formatTemperature(df.getDayTemp()));
        display.setMinTemp(NumberFormat.formatTemperature(df.getMinTemp()));
        display.setMaxTemp(NumberFormat.formatTemperature(df.getMaxTemp()));
        display.setHumidity(NumberFormat.formatHumidity(df.getHumidity()));
        display.setWindSpeed(NumberFormat.formatWindSpeed(df.getWindSpeed()));
    }

    private String capitalizeFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
