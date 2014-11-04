package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.Presenter;

public interface MainView<T extends Presenter<?>> extends View<T> {

    public void setTitle(int stringResId, Object... args);

    public void showProgress(boolean show);

    public void showError(int stringResId, Object... args);

}
