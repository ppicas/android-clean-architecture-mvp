package cat.ppicas.cleanarch.ui.view;

public interface CityCurrentWeatherView extends TaskResultView {

    public void setCurrentTemp(double temp);

    public void setHumidity(int humidity);

    public void setWindSpeed(double windSpeed);

}
