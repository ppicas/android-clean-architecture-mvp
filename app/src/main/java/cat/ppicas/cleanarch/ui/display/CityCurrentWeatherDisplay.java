package cat.ppicas.cleanarch.ui.display;

public interface CityCurrentWeatherDisplay extends TaskResultDisplay {

    public void setCurrentTemp(double temp);

    public void setHumidity(int humidity);

    public void setWindSpeed(double windSpeed);

}
