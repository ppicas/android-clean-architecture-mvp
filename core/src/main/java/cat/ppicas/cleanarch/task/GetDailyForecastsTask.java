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

package cat.ppicas.cleanarch.task;

import java.io.IOException;
import java.util.List;

import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.framework.task.Task;
import cat.ppicas.framework.task.TaskResult;
import retrofit.RetrofitError;

public class GetDailyForecastsTask implements Task<List<DailyForecast>, IOException> {

    private DailyForecastRepository mRepository;

    private String mCityId;

    public GetDailyForecastsTask(DailyForecastRepository repository, String cityId) {
        mRepository = repository;
        mCityId = cityId;
    }

    @Override
    public TaskResult<List<DailyForecast>, IOException> execute() {
        try {
            return TaskResult.newSuccessResult(mRepository.getDailyForecasts(mCityId));
        } catch (RetrofitError e) {
            return TaskResult.newErrorResult(new IOException(e));
        }
    }
}
