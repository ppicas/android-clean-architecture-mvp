package cat.ppicas.cleanarch.app;

import android.app.Application;

import cat.ppicas.cleanarch.repository.FruitRepository;
import cat.ppicas.cleanarch.rest.RestFruitRepository;
import cat.ppicas.cleanarch.util.AsyncTaskExecutor;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class App extends Application {

    private static ServiceContainer sServiceContainer;

    public static ServiceContainer getServiceContainer() {
        return sServiceContainer;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sServiceContainer = new ServiceContainer() {

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
        };
    }
}
