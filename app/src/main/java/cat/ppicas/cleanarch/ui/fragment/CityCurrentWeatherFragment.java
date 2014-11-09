package cat.ppicas.cleanarch.ui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.view.CityCurrentWeatherView;

public class CityCurrentWeatherFragment extends Fragment implements CityCurrentWeatherView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View view = new View(getActivity());
        view.setBackgroundColor(Color.RED);
        return view;
    }

    @Override
    public void setCurrentTemp(double currentWeather) {

    }

    @Override
    public void setMaxTemp(double currentWeather) {

    }

    @Override
    public void setMinTemp(double currentWeather) {

    }

    @Override
    public CityCurrentWeatherPresenter createPresenter() {
        getFragmentManager().find
        ((PresenterHolder) getActivity()).getOrCreatePresenter()
        return new CityCurrentWeatherPresenter();
    }

    @Override
    public String getPresenterTag() {
        return CityCurrentWeatherFragment.class.getName();
    }
}
