package cat.ppicas.cleanarch.ui.display;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;

public interface SearchCitiesDisplay extends TaskResultDisplay {

    public void setTitle(int stringResId, Object... args);

    public void setCities(List<City> cities);

    public void displayCitiesNotFound();

}
