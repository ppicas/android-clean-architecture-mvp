package cat.ppicas.cleanarch.repository;

import java.util.List;

import cat.ppicas.cleanarch.domain.DailyForecast;

public interface DailyForecastRepository {

    public List<DailyForecast> getDailyForecasts(String cityId);

}
