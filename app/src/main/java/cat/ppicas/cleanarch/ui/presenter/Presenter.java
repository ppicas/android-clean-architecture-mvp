package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.view.View;
import cat.ppicas.cleanarch.util.Binder;

public abstract class Presenter<T extends View<?>> {

    private Binder<T> mView = new Binder<T>(getViewClassToken());

    public void bindView(T view) {
        mView.bind(view);
        onViewBound();
    }

    public void unbindView() {
        mView.unbind();
        onViewUnbound();
    }

    public abstract void saveState(Bundle state);

    public abstract void restoreState(Bundle state);

    protected void onViewBound() {
    }

    protected void onViewUnbound() {
    }

    protected T getView() {
        return mView.getProxy();
    }

    protected abstract Class<T> getViewClassToken();
}
