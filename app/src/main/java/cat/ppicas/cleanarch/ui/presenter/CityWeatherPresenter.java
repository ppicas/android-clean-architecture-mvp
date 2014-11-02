package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.ui.view.CityWeatherView;

public class CityWeatherPresenter extends Presenter<CityWeatherView> {

    private int mCityId;

    public CityWeatherPresenter(int cityId) {
        mCityId = cityId;
    }

}
