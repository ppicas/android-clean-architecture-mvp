package cat.ppicas.cleanarch.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import cat.ppicas.cleanarch.ui.fragment.CityDetailFragment;
import cat.ppicas.cleanarch.ui.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.view.View;

public class CityDetailsActivity extends Activity implements PresenterHolder,
        CityDetailFragment.CityDetailFragmentProvider {

    private PresenterHolder mPresenterHolder;

    private CityDetailFragment mCityDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Intent intent = new Intent(getIntent());
            getFragmentManager().beginTransaction()
                    .add(new PresenterHolderFragment(), null)
                    .add(android.R.id.content, CityDetailFragment.newInstance(intent.getCityId()))
                    .commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PresenterHolder) {
            mPresenterHolder = (PresenterHolder) fragment;
        } else if (fragment instanceof CityDetailFragment) {
            mCityDetailFragment = (CityDetailFragment) fragment;
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

    @Override
    public CityDetailFragment getCityDetailFragment() {
        return mCityDetailFragment;
    }

    public static class Intent extends android.content.Intent {

        private static final String EXTRA_CITY_ID = "cityId";

        public Intent(Context packageContext, String cityId) {
            super(packageContext, CityDetailsActivity.class);
            putExtra(EXTRA_CITY_ID, cityId);
        }

        public Intent(android.content.Intent intent) {
            super(intent);
        }

        public String getCityId() {
            return getStringExtra(EXTRA_CITY_ID);
        }
    }
}
