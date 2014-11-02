package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.Presenter;

public interface View<T extends Presenter<?>> {

    public T createPresenter();

    public String getPresenterTag();

    public void showProgress(boolean show);

    public void showError(int stringResId);

}
