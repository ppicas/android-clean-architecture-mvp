package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.Presenter;

public interface View<T extends Presenter<?>> {

    T createPresenter();

    void showProgress(boolean show);

    void showError(int stringResId);
}
