package cat.ppicas.cleanarch.ui.display;

public interface CityDailyForecastDisplay extends TaskResultDisplay {

    public void setForecastDescription(String description);

    public void setDayTemp(double temp);

    public void setMinTemp(double temp);

    public void setMaxTemp(double temp);

    public void setHumidity(double humidity);

    public void setWindSpeed(double windSpeed);

}
