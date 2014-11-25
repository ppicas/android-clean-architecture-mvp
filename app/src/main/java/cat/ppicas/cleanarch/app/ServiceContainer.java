package cat.ppicas.cleanarch.app;

import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.cleanarch.util.TaskExecutor;

/**
 * Interface to be used as a repository of dependency or services required across the app.
 * The services provided by this container can be used to fulfill the dependencies from
 * other classes implementing the inversion of control principle.
 */
public interface ServiceContainer {

    public CityRepository getCityRepository();

    public CurrentWeatherRepository getCurrentWeatherRepository();

    public DailyForecastRepository getDailyForecastRepository();

    public TaskExecutor getTaskExecutor();

}
