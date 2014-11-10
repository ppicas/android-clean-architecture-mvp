package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;

public interface CityCurrentWeatherView extends View<CityCurrentWeatherPresenter> {

    public void setCurrentTemp(double temp);

    public void setMaxTemp(double temp);

    public void setMinTemp(double temp);

}
