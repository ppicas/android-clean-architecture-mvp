package cat.ppicas.cleanarch.rest;

import java.util.ArrayList;
import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.repository.CityRepository;

public class RestCityRepository implements CityRepository {

    private final OWMService mService;

    public RestCityRepository(OWMService service) {
        mService = service;
    }

    @Override
    public City getCity(String cityId) {
        CityWeather cw = mService.getCityById(cityId);
        return createCityFromCityWeather(cw);
    }

    @Override
    public List<City> findCity(String name) {
        List<City> cities = new ArrayList<City>();
        CityWeatherList citiesWeather = mService.findCitiesWeather(name);
        for (CityWeather cw : citiesWeather.getCityWeathers()) {
            cities.add(createCityFromCityWeather(cw));
        }
        return cities;
    }

    private City createCityFromCityWeather(CityWeather cw) {
        City city = new City(cw.getCityId(), cw.getCityName());
        CurrentWeather weather = new CurrentWeather(cw.getCityId(), cw.getMain().getTemp(),
                cw.getMain().getMaxTemp(), cw.getMain().getMinTemp());
        city.setCurrentWeather(weather);
        return city;
    }
}
