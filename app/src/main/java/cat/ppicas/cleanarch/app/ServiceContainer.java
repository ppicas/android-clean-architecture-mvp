package cat.ppicas.cleanarch.app;

import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.util.TaskExecutor;

public interface ServiceContainer {

    public CityRepository getCityRepository();

    public TaskExecutor getTaskExecutor();
}
