package cat.ppicas.cleanarch.app;

import android.app.Application;

public class App extends Application {

    private static ServiceContainer sServiceContainer;

    public static ServiceContainer getServiceContainer() {
        return sServiceContainer;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sServiceContainer = new DefaultServiceContainer(this);
    }
}
