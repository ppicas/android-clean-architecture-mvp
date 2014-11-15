package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.Presenter;

public interface TaskResultView<T extends Presenter<?>> extends View<T> {

    public void displayLoading(boolean display);

    public void displayError(int stringResId, Object... args);

}
