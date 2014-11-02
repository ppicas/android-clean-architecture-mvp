package cat.ppicas.cleanarch.util;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.MainView;

public abstract class ShowErrorTaskCallback<T> implements TaskCallback<T> {

    private final Presenter<?> mPresenter;

    public ShowErrorTaskCallback(Presenter<?> presenter) {
        mPresenter = presenter;
    }

    public void onError(Exception error) {
        MainView<?> view = mPresenter.getView();
        if (view == null) {
            return;
        }

        view.showProgress(false);
        if (!(error instanceof TaskCancelledException)) {
            error.printStackTrace();
            view.showError(R.string.error__connection);
        }
    }
}
