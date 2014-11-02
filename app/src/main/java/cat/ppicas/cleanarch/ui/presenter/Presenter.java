package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.view.View;

public abstract class Presenter<T extends View<?>> {

    private T mView;

    public T getView() {
        return mView;
    }

    public void bindView(T view, boolean recreated) {
        mView = view;
    }

    public void unbindView() {
        mView = null;
    }

    public void saveState(Bundle state) {
    }

    public void restoreState(Bundle state) {
    }

    public void onDestroy() {
    }
}
