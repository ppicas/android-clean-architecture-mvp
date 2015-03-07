package cat.ppicas.cleanarch.ui.display;

public interface CityListItemDisplay extends Display {

    public void setCityName(String name);

    public void setCountry(String country);

    public void setCurrentTemp(String temp);

    public void setLoadingElevation(boolean loading);

    public void setElevation(int elevation);

}
