package cat.ppicas.cleanarch.repository;

import cat.ppicas.cleanarch.domain.CurrentWeather;

public interface CurrentWeatherRepository {

    public CurrentWeather getCityCurrentWeather(String cityId);
}
