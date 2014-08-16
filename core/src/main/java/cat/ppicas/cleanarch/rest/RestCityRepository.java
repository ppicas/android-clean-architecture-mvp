package cat.ppicas.cleanarch.rest;

import java.util.ArrayList;
import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.repository.CityRepository;
import retrofit.RestAdapter;

public class RestCityRepository implements CityRepository {

    private final OWMService mService;

    public RestCityRepository(RestAdapter adapter) {
        mService = adapter.create(OWMService.class);
    }

    @Override
    public City getCity(String cityId) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<City> findCity(String name) {
        List<City> cities = new ArrayList<City>();
        CityWeatherList citiesWeather = mService.findCitiesWeather(name);
        for (CityWeather cw : citiesWeather.getCityWeathers()) {
            City city = new City(cw.getCityId(), cw.getCityName());
            CurrentWeather weather = new CurrentWeather(cw.getCityId(), cw.getMain().getTemp(),
                    cw.getMain().getMaxTemp(), cw.getMain().getMinTemp());
            city.setCurrentWeather(weather);
            cities.add(city);
        }
        return cities;
    }
}
