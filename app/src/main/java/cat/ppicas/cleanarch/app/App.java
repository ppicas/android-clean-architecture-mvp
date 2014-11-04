package cat.ppicas.cleanarch.app;

import android.app.Application;

public class App extends Application implements ServiceContainerProvider {

    private ServiceContainer mServiceContainer;

    @Override
    public void onCreate() {
        super.onCreate();

        mServiceContainer = new DefaultServiceContainer(this);
    }

    @Override
    public ServiceContainer getServiceContainer() {
        return mServiceContainer;
    }
}
