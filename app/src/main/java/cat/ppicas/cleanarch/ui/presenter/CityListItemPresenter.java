package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityListItemDisplay;

public class CityListItemPresenter extends Presenter<CityListItemDisplay> {

    private City mCity;

    public CityListItemPresenter(City city) {
        mCity = city;
    }

    @Override
    public void bindDisplay(CityListItemDisplay display) {
        super.bindDisplay(display);
        updateDisplay();
    }

    public String getCityId() {
        return (mCity != null) ? mCity.getId() : null;
    }

    public void setCity(City city) {
        mCity = city;
        updateDisplay();
    }

    private void updateDisplay() {
        CityListItemDisplay display = getDisplay();
        if (display == null) {
            return;
        }

        display.setCityName(mCity.getName());
        display.setCountry(mCity.getCountry());
        double temp = mCity.getCurrentWeatherPreview().getCurrentTemp();
        display.setCurrentTemp(NumberFormat.formatTemperature(temp));
    }
}
