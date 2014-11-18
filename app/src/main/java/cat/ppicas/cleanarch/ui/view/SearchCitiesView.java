package cat.ppicas.cleanarch.ui.view;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;

public interface SearchCitiesView extends TaskResultView {

    public void setTitle(int stringResId, Object... args);

    public void setCities(List<City> cities);

    public void displayCitiesNotFound();

}
