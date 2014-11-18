package cat.ppicas.cleanarch.util;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.TaskResultView;

public abstract class DisplayErrorTaskCallback<T> implements TaskCallback<T> {

    private final Presenter<? extends TaskResultView> mPresenter;

    public DisplayErrorTaskCallback(Presenter<? extends TaskResultView> presenter) {
        mPresenter = presenter;
    }

    public void onError(Exception error) {
        TaskResultView view = mPresenter.getView();
        if (view == null) {
            return;
        }

        view.displayLoading(false);
        if (!(error instanceof TaskCancelledException)) {
            error.printStackTrace();
            view.displayError(R.string.error__connection);
        }
    }
}
