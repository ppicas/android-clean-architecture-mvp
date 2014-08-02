package cat.ppicas.cleanarch.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import cat.ppicas.cleanarch.presenter.Presenter;
import cat.ppicas.cleanarch.presenter.view.View;

public class PresenterHolderFragment<T extends Presenter<?>> extends Fragment {

    private static final String STATE_PRESENTER = "presenterState";

    private T mPresenter;

    public static <T extends Presenter<S>, S extends View> PresenterHolderFragment<T> findOrCreate(
            FragmentManager manager, String tag, PresenterFactory<T> factory, S view) {

        @SuppressWarnings("unchecked")
        PresenterHolderFragment<T> holder = (PresenterHolderFragment<T>)
                manager.findFragmentByTag(tag);
        if (holder == null) {
            holder = new PresenterHolderFragment<T>();
            manager.beginTransaction().add(android.R.id.content, holder, tag).commit();
        }
        if (holder.mPresenter == null) {
            holder.mPresenter = factory.create();
        }
        holder.mPresenter.bindView(view);

        return holder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState != null) {
            mPresenter.restoreState(savedInstanceState.getBundle(STATE_PRESENTER));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle presenterState = new Bundle();
        mPresenter.saveState(presenterState);
        outState.putBundle(STATE_PRESENTER, presenterState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    public T getPresenter() {
        return mPresenter;
    }

    public void remove() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
