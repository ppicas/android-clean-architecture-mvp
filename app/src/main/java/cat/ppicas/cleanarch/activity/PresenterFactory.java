package cat.ppicas.cleanarch.activity;

import cat.ppicas.cleanarch.presenter.Presenter;

public interface PresenterFactory<T extends Presenter<?>> {

    public T createPresenter();
}
