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

import cat.ppicas.cleanarch.model.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.framework.task.Task;
import cat.ppicas.framework.task.TaskResult;
import retrofit.RetrofitError;

public class FindCityTask implements Task<List<City>, IOException> {

    private String mCityName;
    private CityRepository mRepository;

    private boolean mCanceled;

    public FindCityTask(String cityName, CityRepository repository) {
        mCityName = cityName;
        mRepository = repository;
    }

    @Override
    public TaskResult<List<City>, IOException> execute() {
        try {
            List<City> city = mRepository.findCity(mCityName);
            if (mCanceled) {
                return TaskResult.newCanceledResult();
            } else {
                return TaskResult.newSuccessResult(city);
            }
        } catch (RetrofitError e) {
            return TaskResult.newErrorResult(new IOException(e));
        }
    }

    public void cancel() {
        mCanceled = true;
    }
}
