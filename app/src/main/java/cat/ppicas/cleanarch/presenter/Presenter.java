package cat.ppicas.cleanarch.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.presenter.view.View;
import cat.ppicas.cleanarch.util.Binder;

public abstract class Presenter<T extends View> {

    private Binder<T> mView = new Binder<T>(getViewClassToken());

    public void bindView(T view) {
        mView.bind(view);
    }

    public void unbindView() {
        mView.unbind();
    }

    public void saveState(Bundle state) {
    }

    public void restoreState(Bundle state) {
    }

    protected T getView() {
        return mView.getProxy();
    }

    protected abstract Class<T> getViewClassToken();
}
