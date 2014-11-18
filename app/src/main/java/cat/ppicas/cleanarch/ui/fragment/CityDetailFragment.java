package cat.ppicas.cleanarch.ui.fragment;

import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.app.ServiceContainerProvider;
import cat.ppicas.cleanarch.res.AppStringResources;
import cat.ppicas.cleanarch.ui.presenter.CityDetailPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.view.CityDetailView;

public class CityDetailFragment extends Fragment implements CityDetailView,
        PresenterFactory<CityDetailPresenter> {

    private static final String ARG_CITY_ID = "cityId";

    private String mCityId;

    private CityDetailPresenter mPresenter;

    public static CityDetailFragment newInstance(String cityId) {
        Bundle args = new Bundle();
        args.putString(ARG_CITY_ID, cityId);
        CityDetailFragment fragment = new CityDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args == null || !args.containsKey(ARG_CITY_ID)) {
            throw new IllegalArgumentException("Invalid Fragment arguments");
        }

        mCityId = args.getString(ARG_CITY_ID);

        mPresenter = ((PresenterHolder) getActivity()).getOrCreatePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_detail, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.city_detail__view_pager);
        viewPager.setAdapter(new LocalPageAdapter(getFragmentManager()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.bindView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @Override
    public void setTitle(int stringResId, Object... args) {
        getActivity().setTitle(getString(stringResId, args));
    }

    @Override
    public void displayLoading(boolean display) {
        getActivity().setProgressBarIndeterminate(true);
        getActivity().setProgressBarIndeterminateVisibility(display);
    }

    @Override
    public void displayError(int stringResId, Object... args) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public CityDetailPresenter createPresenter() {
        Application app = getActivity().getApplication();
        ServiceContainer sc = ((ServiceContainerProvider) app).getServiceContainer();
        return new CityDetailPresenter(sc.getTaskExecutor(), sc.getCityRepository(),
                new AppStringResources(getResources()), mCityId);
    }

    @Override
    public String getPresenterTag() {
        return CityDetailFragment.class.getName();
    }

    private class LocalPageAdapter extends FragmentStatePagerAdapter {

        public LocalPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return CityCurrentWeatherFragment.newInstance(mCityId);
            } else {
                return CityDailyForecastFragment.newInstance(mCityId, position - 1);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.city_details__tab_current);
            } else {
                return mPresenter.getForecastPageTitle(position - 1);
            }
        }

        @Override
        public int getCount() {
            return 1 + 3;
        }
    }
}
