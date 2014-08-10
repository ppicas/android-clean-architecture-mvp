package cat.ppicas.cleanarch.app;

import cat.ppicas.cleanarch.repository.FruitRepository;
import cat.ppicas.cleanarch.rest.RestFruitRepository;
import cat.ppicas.cleanarch.util.AsyncTaskExecutor;
import cat.ppicas.cleanarch.util.TaskExecutor;

class DefaultServiceContainer implements ServiceContainer {

    private AsyncTaskExecutor mTaskExecutor;

    @Override
    public FruitRepository getFruitRepository() {
        return new RestFruitRepository();
    }

    @Override
    public TaskExecutor getTaskExecutor() {
        if (mTaskExecutor == null) {
            mTaskExecutor = new AsyncTaskExecutor();
        }
        return mTaskExecutor;
    }
}
