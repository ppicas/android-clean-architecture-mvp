package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.Presenter;

public interface CityWeatherForecastView extends View<Presenter<?>> {

    public void showWeatherForecast(Object weatherForecast);

}
