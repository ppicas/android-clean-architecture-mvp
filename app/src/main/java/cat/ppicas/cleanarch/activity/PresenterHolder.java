package cat.ppicas.cleanarch.activity;

import cat.ppicas.cleanarch.presenter.Presenter;

public interface PresenterHolder {

    public <T extends Presenter<?>> T getPresenter(PresenterFactory<T> viewFragment);
}
