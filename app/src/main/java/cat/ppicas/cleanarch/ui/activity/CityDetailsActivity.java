package cat.ppicas.cleanarch.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import cat.ppicas.cleanarch.ui.fragment.CityDetailFragment;
import cat.ppicas.cleanarch.ui.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;

public class CityDetailsActivity extends Activity implements PresenterHolder {

    private PresenterHolder mPresenterHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

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
        }
    }

    @Override
    public <T extends Presenter<?>> T getOrCreatePresenter(PresenterFactory<T> presenterFactory) {
        return mPresenterHolder.getOrCreatePresenter(presenterFactory);
    }

    @Override
    public void destroyPresenter(PresenterFactory<?> presenterFactory) {
        mPresenterHolder.destroyPresenter(presenterFactory);
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
