package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.view.CityWeatherView;

public class CityWeatherPresenter extends Presenter<CityWeatherView> {

    private int mCityId;

    public CityWeatherPresenter(int cityId) {
        mCityId = cityId;
    }

    @Override
    public void onViewStart() {
        // new GetCityWeatherTask();
    }

    @Override
    public void saveState(Bundle state) {
    }

    @Override
    public void restoreState(Bundle state) {
    }

    @Override
    protected Class<CityWeatherView> getViewClassToken() {
        return CityWeatherView.class;
    }
}
