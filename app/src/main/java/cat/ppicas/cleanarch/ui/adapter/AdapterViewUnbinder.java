package cat.ppicas.cleanarch.ui.adapter;

import android.view.View;

import cat.ppicas.cleanarch.ui.presenter.Presenter;

public class AdapterViewUnbinder implements View.OnAttachStateChangeListener {

    private Presenter<?> mPresenter;

    public AdapterViewUnbinder(Presenter<?> presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onViewAttachedToWindow(View v) {
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        mPresenter.unbindDisplay();
        mPresenter.onDestroy();
    }
}
