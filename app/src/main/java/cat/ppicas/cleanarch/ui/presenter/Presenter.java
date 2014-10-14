package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.view.View;
import cat.ppicas.cleanarch.util.Binder;

public abstract class Presenter<T extends View<?>> {

    private Binder<T> mView = new Binder<T>(getViewClassToken());

    public void bindView(T view) {
        mView.bind(view);
    }

    public void unbindView() {
        mView.unbind();
    }

    public T getView() {
        return mView.getProxy();
    }

    public void onViewStart() {
    }

    public void onViewStop() {
    }

    public abstract void saveState(Bundle state);

    public abstract void restoreState(Bundle state);

    protected abstract Class<T> getViewClassToken();
}
