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
import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.domain.CurrentWeatherPreview;
import cat.ppicas.cleanarch.owm.model.OWMCurrentWeather;
import cat.ppicas.cleanarch.owm.model.OWMCurrentWeatherList;
import cat.ppicas.cleanarch.repository.CityRepository;

public class OWMCityRepository implements CityRepository {

    private final OWMService mService;

    public OWMCityRepository(OWMService service) {
        mService = service;
    }

    @Override
    public City getCity(String cityId) {
        OWMCurrentWeather cw = mService.getCurrentWeatherByCityId(cityId);
        return createCityFromCityWeather(cw);
    }

    @Override
    public List<City> findCity(String name) {
        List<City> cities = new ArrayList<City>();
        OWMCurrentWeatherList citiesWeather = mService.getCurrentWeatherByCityName(name);
        for (OWMCurrentWeather cw : citiesWeather.getList()) {
            cities.add(createCityFromCityWeather(cw));
        }
        return cities;
    }

    private City createCityFromCityWeather(OWMCurrentWeather cw) {
        CurrentWeatherPreview weatherPreview = new CurrentWeatherPreview(
                cw.getCityId(), cw.getMain().getTemp());
        City city = new City(cw.getCityId(), cw.getCityName(),
                cw.getSystem().getCountry(), weatherPreview);
        return city;
    }
}
