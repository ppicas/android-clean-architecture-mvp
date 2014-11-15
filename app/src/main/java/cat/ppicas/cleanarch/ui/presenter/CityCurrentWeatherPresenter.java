package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.ui.view.CityCurrentWeatherView;

public class CityCurrentWeatherPresenter extends Presenter<CityCurrentWeatherView> {

    private CityDetailPresenter mMasterPresenter;

    public CityCurrentWeatherPresenter(CityDetailPresenter masterPresenter) {
        mMasterPresenter = masterPresenter;
    }

    @Override
    public void bindView(final CityCurrentWeatherView view) {
        super.bindView(view);

        mMasterPresenter.getCity(new CityDetailPresenter.GetCityCallback() {
            @Override
            public void onGetCityResult(City city) {
                CurrentWeather cw = city.getCurrentWeatherPreview();
                view.setCurrentTemp(cw.getCurrentTemp());
                view.setHumidity(cw.getHumidity());
                view.setWindSpeed(cw.getWindSpeed());
            }
        });
    }
}
