package cat.ppicas.cleanarch.util;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.ui.presenter.impl.AbstractPresenter;

public abstract class ShowErrorTaskCallback implements TaskCallback<List<City>> {

    private final AbstractPresenter<?> mPresenter;

    public ShowErrorTaskCallback(AbstractPresenter<?> presenter) {
        mPresenter = presenter;
    }

    public void onError(Exception error) {
        mPresenter.getView().showProgress(false);
        if (!(error instanceof TaskCancelledException)) {
            error.printStackTrace();
            mPresenter.getView().showError(R.string.error__connection);
        }
    }
}
