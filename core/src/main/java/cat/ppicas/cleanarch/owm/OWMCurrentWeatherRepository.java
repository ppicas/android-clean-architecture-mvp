package cat.ppicas.cleanarch.owm;

import cat.ppicas.cleanarch.domain.CurrentWeather;
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
