package cat.ppicas.cleanarch.ui.view;

import java.util.List;

import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.ui.presenter.Presenter;

public interface CityDailyForecastView extends View<Presenter<?>> {

    public void setDailyForecasts(List<DailyForecast> dailyForecasts);

}
