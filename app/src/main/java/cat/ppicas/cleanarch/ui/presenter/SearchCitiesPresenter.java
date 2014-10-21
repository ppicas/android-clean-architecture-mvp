package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.ui.view.SearchCitiesView;

public interface SearchCitiesPresenter extends Presenter<SearchCitiesView> {

    void onCitySearch(String cityName);

}
