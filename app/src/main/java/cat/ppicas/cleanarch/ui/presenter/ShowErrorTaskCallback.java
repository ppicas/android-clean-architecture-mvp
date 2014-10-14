package cat.ppicas.cleanarch.ui.presenter;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.util.TaskCallback;

public abstract class ShowErrorTaskCallback implements TaskCallback<List<City>> {

    private final Presenter<?> mPresenter;

    public ShowErrorTaskCallback(Presenter<?> presenter) {
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
