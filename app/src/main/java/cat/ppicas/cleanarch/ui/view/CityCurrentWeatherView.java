package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;

public interface CityCurrentWeatherView extends View<CityCurrentWeatherPresenter> {

    public void setCurrentTemp(double currentWeather);

    public void setMaxTemp(double currentWeather);

    public void setMinTemp(double currentWeather);

}
