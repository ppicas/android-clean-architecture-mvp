package cat.ppicas.cleanarch.ui.presenter.impl;

import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.View;

public abstract class AbstractPresenter<T extends View<?>> implements Presenter<T> {

    private T mView;

    public T getView() {
        return mView;
    }

    @Override
    public void bindView(T view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void onDestroy() {
    }

}
