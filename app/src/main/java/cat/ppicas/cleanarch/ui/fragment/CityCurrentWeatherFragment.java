package cat.ppicas.cleanarch.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;
import cat.ppicas.cleanarch.ui.presenter.CityDetailPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.view.CityCurrentWeatherView;

import static cat.ppicas.cleanarch.ui.fragment.CityDetailFragment.CityDetailFragmentProvider;

public class CityCurrentWeatherFragment extends Fragment implements CityCurrentWeatherView {

    private CityCurrentWeatherPresenter mPresenter;

    private TextView mCurrent;
    private TextView mHumidity;
    private TextView mWindSpeed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ((PresenterHolder) getActivity()).getOrCreatePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View view = inflater.inflate(R.layout.fragment_city_current_weather, container, false);

        mCurrent = (TextView) view.findViewById(R.id.city_weather__current);
        mHumidity = (TextView) view.findViewById(R.id.city_weather__humidity);
        mWindSpeed = (TextView) view.findViewById(R.id.city_weather__wind_speed);

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
        mCurrent.setText(temp + "º");
    }

    @Override
    public void setHumidity(int humidity) {
        mHumidity.setText(humidity + "º");
    }

    @Override
    public void setWindSpeed(double windSpeed) {
        mWindSpeed.setText(windSpeed + "º");
    }

    @Override
    public CityCurrentWeatherPresenter createPresenter() {
        CityDetailFragment fragment = ((CityDetailFragmentProvider) getActivity())
                .getCityDetailFragment();
        CityDetailPresenter parentPresenter = ((PresenterHolder) getActivity())
                .getOrCreatePresenter(fragment);
        return new CityCurrentWeatherPresenter(parentPresenter);
    }

    @Override
    public String getPresenterTag() {
        return CityCurrentWeatherFragment.class.getName();
    }
}
