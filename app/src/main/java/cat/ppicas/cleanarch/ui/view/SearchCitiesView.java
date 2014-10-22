package cat.ppicas.cleanarch.ui.view;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.ui.presenter.SearchCitiesPresenter;

public interface SearchCitiesView extends View<SearchCitiesPresenter> {

    public void showCities(List<City> cities);

    public void showCitiesNotFound();
}
