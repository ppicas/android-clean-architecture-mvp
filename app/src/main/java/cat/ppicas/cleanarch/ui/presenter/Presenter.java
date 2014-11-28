package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.display.Display;

public abstract class Presenter<T extends Display> {

    private T mDisplay;

    public T getDisplay() {
        return mDisplay;
    }

    public void bindDisplay(T display) {
        mDisplay = display;
    }

    public void unbindDisplay() {
        mDisplay = null;
    }

    public void saveState(Bundle state) {
    }

    public void restoreState(Bundle state) {
    }

    public void onDestroy() {
    }
}
