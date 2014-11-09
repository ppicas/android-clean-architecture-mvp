package cat.ppicas.cleanarch.ui.fragment;

import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.app.ServiceContainerProvider;
import cat.ppicas.cleanarch.ui.presenter.CityDetailPresenter;
import cat.ppicas.cleanarch.ui.view.CityDetailView;

public class CityDetailFragment extends Fragment implements CityDetailView {

    private static final String ARG_CITY_ID = "cityId";

    private String mCityId;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_detail, container, false);

        FragmentTabHost tabHost = (FragmentTabHost) view.findViewById(R.id.city_detail__tab_host);
        tabHost.setup(getActivity(), getFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("weather").setIndicator("Weather"),
                CityCurrentWeatherFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("forecast").setIndicator("Forecast"),
                Fragment.class, null);

        return view;
    }

    @Override
    public void setTitle(int stringResId, Object... args) {
        getActivity().setTitle(getString(stringResId, args));
    }

    @Override
    public void showProgress(boolean show) {
        getActivity().setProgressBarIndeterminate(true);
        getActivity().setProgressBarIndeterminateVisibility(show);
    }

    @Override
    public void showError(int stringResId, Object... args) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public CityDetailPresenter createPresenter() {
        Application app = getActivity().getApplication();
        ServiceContainer sc = ((ServiceContainerProvider) app).getServiceContainer();
        return new CityDetailPresenter(sc.getTaskExecutor(), mCityId);
    }

    @Override
    public String getPresenterTag() {
        return CityDetailFragment.class.getName();
    }
}
