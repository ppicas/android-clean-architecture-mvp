package cat.ppicas.cleanarch.ui.display;

public interface CityCurrentWeatherDisplay extends TaskResultDisplay {

    public void setCurrentTemp(String temp);

    public void setHumidity(String humidity);

    public void setWindSpeed(String windSpeed);

}
