package cat.ppicas.cleanarch.ui.fragment;

import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.app.ServiceContainerProvider;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityCurrentWeatherDisplay;
import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;

public class CityCurrentWeatherFragment extends Fragment implements CityCurrentWeatherDisplay,
        PresenterFactory<CityCurrentWeatherPresenter> {

    private static final String ARG_CITY_ID = "cityId";

    private String mCityId;
    private CityCurrentWeatherPresenter mPresenter;

    private TextView mCurrent;
    private TextView mHumidity;
    private TextView mWindSpeed;
    private View mLoading;

    public static CityCurrentWeatherFragment newInstance(String cityId) {
        Bundle args = new Bundle();
        args.putString(ARG_CITY_ID, cityId);
        CityCurrentWeatherFragment fragment = new CityCurrentWeatherFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View view = inflater.inflate(R.layout.fragment_city_current_weather, container, false);

        mCurrent = (TextView) view.findViewById(R.id.city_weather__current);
        mHumidity = (TextView) view.findViewById(R.id.city_weather__humidity);
        mWindSpeed = (TextView) view.findViewById(R.id.city_weather__wind_speed);
        mLoading = view.findViewById(R.id.city_weather__loading);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.bindDisplay(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindDisplay();
    }

    @Override
    public void setCurrentTemp(double temp) {
        mCurrent.setText(NumberFormat.formatDecimal(temp) + "º");
    }

    @Override
    public void setHumidity(int humidity) {
        mHumidity.setText(humidity + "%");
    }

    @Override
    public void setWindSpeed(double windSpeed) {
        mWindSpeed.setText(NumberFormat.formatDecimal(windSpeed) + " m/s");
    }

    @Override
    public void displayLoading(boolean display) {
        mLoading.setVisibility(display ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayError(int stringResId, Object... args) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public CityCurrentWeatherPresenter createPresenter() {
        Application app = getActivity().getApplication();
        ServiceContainer sc = ((ServiceContainerProvider) app).getServiceContainer();
        return new CityCurrentWeatherPresenter(sc.getTaskExecutor(),
                sc.getCurrentWeatherRepository(), mCityId);
    }

    @Override
    public String getPresenterTag() {
        return CityCurrentWeatherFragment.class.getName();
    }
}
