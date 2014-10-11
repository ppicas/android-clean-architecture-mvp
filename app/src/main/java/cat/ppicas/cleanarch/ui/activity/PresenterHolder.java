package cat.ppicas.cleanarch.ui.activity;

import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.View;

public interface PresenterHolder {

    public <T extends Presenter<?>> T getPresenter(View<T> view);
}
