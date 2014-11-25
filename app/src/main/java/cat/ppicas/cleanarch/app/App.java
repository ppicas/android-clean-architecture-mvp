package cat.ppicas.cleanarch.app;

import android.app.Application;

public class App extends Application implements ServiceContainerProvider {

    private ServiceContainer mServiceContainer;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the default implementation of the ServiceContainer interface
        mServiceContainer = new DefaultServiceContainer(this);
    }

    @Override
    public ServiceContainer getServiceContainer() {
        return mServiceContainer;
    }
}
