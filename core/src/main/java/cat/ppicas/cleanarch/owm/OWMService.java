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

package cat.ppicas.cleanarch.owm;

import cat.ppicas.cleanarch.owm.model.OWMCurrentWeather;
import cat.ppicas.cleanarch.owm.model.OWMCurrentWeatherList;
import cat.ppicas.cleanarch.owm.model.OWMDailyForecastList;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface OWMService {

    @GET("/data/2.5/find?units=metric")
    @Headers("Cache-Control: max-age=300, max-stale=900")
    public OWMCurrentWeatherList getCurrentWeatherByCityName(@Query("q") String query);

    @GET("/data/2.5/weather?units=metric")
    @Headers("Cache-Control: max-age=300, max-stale=900")
    public OWMCurrentWeather getCurrentWeatherByCityId(@Query("id") String id);

    @GET("/data/2.5/forecast/daily?units=metric")
    @Headers("Cache-Control: max-age=300, max-stale=900")
    public OWMDailyForecastList getDailyForecastByCityId(@Query("id") String id,
            @Query("cnt") int days);
}
