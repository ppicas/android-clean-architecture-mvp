package cat.ppicas.cleanarch.ui.presenter.impl;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.presenter.CityWeatherPresenter;
import cat.ppicas.cleanarch.ui.view.CityWeatherView;

public class CityWeatherPresenterImpl extends AbstractPresenter<CityWeatherView>
        implements CityWeatherPresenter {

    private int mCityId;

    public CityWeatherPresenterImpl(int cityId) {
        mCityId = cityId;
    }

    @Override
    public void saveState(Bundle state) {
    }

    @Override
    public void restoreState(Bundle state) {
    }
}
