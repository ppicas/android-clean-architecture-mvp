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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.ppicas.cleanarch.model.DailyForecast;
import cat.ppicas.cleanarch.owm.model.OWMDailyForecast;
import cat.ppicas.cleanarch.owm.model.OWMDailyForecastList;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;

public class OWMDailyForecastRepository implements DailyForecastRepository {

    private static final int FORECAST_DAYS = 3;

    private OWMService mService;

    public OWMDailyForecastRepository(OWMService service) {
        mService = service;
    }

    @Override
    public List<DailyForecast> getDailyForecasts(String cityId) {
        List<DailyForecast> list = new ArrayList<DailyForecast>();
        OWMDailyForecastList owmDFList = mService.getDailyForecastByCityId(cityId, FORECAST_DAYS);
        for (OWMDailyForecast owmDF : owmDFList.getList()) {
            list.add(createDailyForecast(cityId, owmDF));
        }
        return list;
    }

    private DailyForecast createDailyForecast(String cityId, OWMDailyForecast owmDF) {
        Date data = new Date(owmDF.getTimestamp() * 1000L);
        String description = (owmDF.getWeatherList().length >= 1)
                ? owmDF.getWeatherList()[0].getDescription() : "Not available";
        OWMDailyForecast.Temp temp = owmDF.getTemp();
        return new DailyForecast(cityId, data, description, temp.getDay(), temp.getMin(),
                temp.getMax(), owmDF.getHumidity(), owmDF.getWindSpeed());
    }
}
