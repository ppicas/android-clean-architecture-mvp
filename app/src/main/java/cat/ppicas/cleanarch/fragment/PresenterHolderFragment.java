package cat.ppicas.cleanarch.fragment;

import android.app.Fragment;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import cat.ppicas.cleanarch.activity.PresenterFactory;
import cat.ppicas.cleanarch.presenter.Presenter;

public class PresenterHolderFragment extends Fragment {

    private final Map<String, Presenter<?>> mPresenterMap = new HashMap<String, Presenter<?>>();

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setRetainInstance(true);

        if (state != null) {
            for (Map.Entry<String, Presenter<?>> entry : mPresenterMap.entrySet()) {
                if (state.containsKey(entry.getKey())) {
                    entry.getValue().restoreState(state.getBundle(entry.getKey()));
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        for (Map.Entry<String, Presenter<?>> entry : mPresenterMap.entrySet()) {
            Bundle bundle = new Bundle();
            entry.getValue().saveState(bundle);
            state.putBundle(entry.getKey(), bundle);
        }
    }

    public <T extends Presenter<?>> T getPresenter(String tag, PresenterFactory<T> presenterFactory) {
        @SuppressWarnings("unchecked")
        T presenter = (T) mPresenterMap.get(tag);

        if (presenter == null) {
            presenter = presenterFactory.createPresenter();
            mPresenterMap.put(tag, presenter);
        }

        return presenter;
    }

    public void removePresenter(String tag) {
        mPresenterMap.remove(tag);
    }

    public String createTag(PresenterFactory<?> presenterFactory) {
        return presenterFactory.getClass().getName();
    }

    public String createTag(PresenterFactory<?> presenterFactory, int index) {
        return presenterFactory.getClass().getName() + "." + index;
    }
}
