package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.ui.view.CityListItemView;

public class CityListItemPresenter extends Presenter<CityListItemView> {

    private City mCity;

    public CityListItemPresenter(City city) {
        mCity = city;
    }

    @Override
    public void bindView(CityListItemView view) {
        super.bindView(view);
        updateView();
    }

    public String getCityId() {
        return (mCity != null) ? mCity.getId() : null;
    }

    public void setCity(City city) {
        mCity = city;
        updateView();
    }

    private void updateView() {
        CityListItemView view = getView();
        if (view == null) {
            return;
        }

        view.setCityName(mCity.getName());
        view.setCountry(mCity.getCountry());
        view.setCurrentTemp(mCity.getCurrentWeatherPreview().getCurrentTemp());
    }
}
