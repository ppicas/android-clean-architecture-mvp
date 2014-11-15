package cat.ppicas.cleanarch.domain;

public class CurrentWeather extends CurrentWeatherPreview {

    private int mHumidity;
    private double mWindSpeed;

    public CurrentWeather(String cityId, double currentTemp, int humidity, double windSpeed) {
        super(cityId, currentTemp);
        mHumidity = humidity;
        mWindSpeed = windSpeed;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }
}
