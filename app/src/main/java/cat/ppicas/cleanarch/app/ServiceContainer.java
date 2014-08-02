package cat.ppicas.cleanarch.app;

import cat.ppicas.cleanarch.repository.FruitRepository;
import cat.ppicas.cleanarch.util.TaskExecutor;

public interface ServiceContainer {

    public FruitRepository getFruitRepository();

    public TaskExecutor getTaskExecutor();
}
