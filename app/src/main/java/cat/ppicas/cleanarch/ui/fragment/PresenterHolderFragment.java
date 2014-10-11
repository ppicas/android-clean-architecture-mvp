package cat.ppicas.cleanarch.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.View;

public class PresenterHolderFragment extends Fragment {

    private static final String STATE_PRESENTERS = "presenters";

    private final Map<String, Presenter<?>> mPresenterMap = new HashMap<String, Presenter<?>>();

    private Bundle mPresentersStates;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setRetainInstance(true);

        if (state != null) {
            mPresentersStates = state.getBundle(STATE_PRESENTERS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        Bundle presentersStates = new Bundle();
        for (Map.Entry<String, Presenter<?>> entry : mPresenterMap.entrySet()) {
            Bundle presenterState = new Bundle();
            entry.getValue().saveState(presenterState);
            presentersStates.putBundle(entry.getKey(), presenterState);
        }
        state.putBundle(STATE_PRESENTERS, presentersStates);
    }

    public <T extends Presenter<?>> T getPresenter(String tag, View<T> view) {
        @SuppressWarnings("unchecked")
        T presenter = (T) mPresenterMap.get(tag);

        if (presenter == null) {
            presenter = view.createPresenter();
            if (mPresentersStates != null && mPresentersStates.containsKey(tag)) {
                presenter.restoreState(mPresentersStates.getBundle(tag));
            }
            mPresenterMap.put(tag, presenter);
        }

        return presenter;
    }

    public void removePresenter(String tag) {
        mPresenterMap.remove(tag);
    }
}
