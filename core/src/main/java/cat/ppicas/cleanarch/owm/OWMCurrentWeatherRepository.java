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

import cat.ppicas.cleanarch.model.CurrentWeather;
import cat.ppicas.cleanarch.owm.model.OWMCurrentWeather;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;

public class OWMCurrentWeatherRepository implements CurrentWeatherRepository {

    private OWMService mService;

    public OWMCurrentWeatherRepository(OWMService service) {
        mService = service;
    }

    @Override
    public CurrentWeather getCityCurrentWeather(String cityId) {
        OWMCurrentWeather cw = mService.getCurrentWeatherByCityId(cityId);
        return new CurrentWeather(cw.getCityId(), cw.getMain().getTemp(),
                cw.getMain().getHumidity(), cw.getWind().getSpeed());
    }
}
