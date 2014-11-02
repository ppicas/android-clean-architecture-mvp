package cat.ppicas.cleanarch.util;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.View;

public abstract class ShowErrorTaskCallback implements TaskCallback<List<City>> {

    private final Presenter<?> mPresenter;

    public ShowErrorTaskCallback(Presenter<?> presenter) {
        mPresenter = presenter;
    }

    public void onError(Exception error) {
        View<?> view = mPresenter.getView();
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
