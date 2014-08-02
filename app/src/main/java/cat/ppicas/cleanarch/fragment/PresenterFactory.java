package cat.ppicas.cleanarch.fragment;

import cat.ppicas.cleanarch.presenter.Presenter;

public interface PresenterFactory<T extends Presenter<?>> {

    public T create();
}
