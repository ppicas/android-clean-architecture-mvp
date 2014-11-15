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

import java.text.NumberFormat;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.app.ServiceContainerProvider;
import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.view.CityCurrentWeatherView;

public class CityCurrentWeatherFragment extends Fragment implements CityCurrentWeatherView {

    private static final String ARG_CITY_ID = "cityId";

    public static final NumberFormat DECIMAL_FORMATTER;
    static {
        DECIMAL_FORMATTER = NumberFormat.getInstance();
        DECIMAL_FORMATTER.setMaximumFractionDigits(2);
    }

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
        mPresenter.bindView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @Override
    public void setCurrentTemp(double temp) {
        mCurrent.setText(DECIMAL_FORMATTER.format(temp) + "º");
    }

    @Override
    public void setHumidity(int humidity) {
        mHumidity.setText(humidity + "%");
    }

    @Override
    public void setWindSpeed(double windSpeed) {
        mWindSpeed.setText(DECIMAL_FORMATTER.format(windSpeed) + " m/s");
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
