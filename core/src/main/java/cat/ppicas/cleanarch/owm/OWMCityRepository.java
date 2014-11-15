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
