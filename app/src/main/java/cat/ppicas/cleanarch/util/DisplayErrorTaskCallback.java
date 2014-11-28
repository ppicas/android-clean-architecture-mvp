package cat.ppicas.cleanarch.util;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.ui.display.TaskResultDisplay;
import cat.ppicas.cleanarch.ui.presenter.Presenter;

public abstract class DisplayErrorTaskCallback<T> implements TaskCallback<T> {

    private final Presenter<? extends TaskResultDisplay> mPresenter;

    public DisplayErrorTaskCallback(Presenter<? extends TaskResultDisplay> presenter) {
        mPresenter = presenter;
    }

    public void onError(Exception error) {
        TaskResultDisplay display = mPresenter.getDisplay();
        if (display == null) {
            return;
        }

        display.displayLoading(false);
        if (!(error instanceof TaskCancelledException)) {
            error.printStackTrace();
            display.displayError(R.string.error__connection);
        }
    }
}
