package cat.ppicas.cleanarch.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import cat.ppicas.cleanarch.ui.fragment.CityDetailFragment;
import cat.ppicas.cleanarch.ui.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.view.View;

public class CityDetailsActivity extends Activity implements PresenterHolder {

    private PresenterHolder mPresenterHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(new PresenterHolderFragment(), null)
                    .add(android.R.id.content, CityDetailFragment.newInstance(cityId))
                    .commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PresenterHolder) {
            mPresenterHolder = (PresenterHolder) fragment;
        }
    }

    @Override
    public <T extends Presenter<?>> T getOrCreatePresenter(View<T> view) {
        return mPresenterHolder.getOrCreatePresenter(view);
    }

    @Override
    public void destroyPresenter(View<?> view) {
        mPresenterHolder.destroyPresenter(view);
    }
}
