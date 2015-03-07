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

package cat.ppicas.cleanarch.app;

import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.cleanarch.util.TaskExecutor;

/**
 * Interface to be used as a repository of dependency or services required across the app.
 * The services provided by this container can be used to fulfill the dependencies from
 * other classes implementing the inversion of control principle.
 */
public interface ServiceContainer {

    public CityRepository getCityRepository();

    public CurrentWeatherRepository getCurrentWeatherRepository();

    public DailyForecastRepository getDailyForecastRepository();

    public TaskExecutor getTaskExecutor();

}
