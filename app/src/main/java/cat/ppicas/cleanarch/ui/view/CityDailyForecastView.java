package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.CityDailyForecastPresenter;

public interface CityDailyForecastView extends TaskResultView<CityDailyForecastPresenter> {

    public void setForecastDescription(String description);

}
