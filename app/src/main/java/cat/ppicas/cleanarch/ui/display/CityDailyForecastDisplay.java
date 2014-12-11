package cat.ppicas.cleanarch.ui.display;

public interface CityDailyForecastDisplay extends TaskResultDisplay {

    public void setForecastDescription(String description);

    public void setDayTemp(String temp);

    public void setMinTemp(String temp);

    public void setMaxTemp(String temp);

    public void setHumidity(String humidity);

    public void setWindSpeed(String windSpeed);

}
