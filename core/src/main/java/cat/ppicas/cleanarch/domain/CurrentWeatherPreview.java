package cat.ppicas.cleanarch.domain;

public class CurrentWeatherPreview {

    private String mCityId;
    private double mCurrentTemp;

    public CurrentWeatherPreview(String cityId, double currentTemp) {
        mCityId = cityId;
        mCurrentTemp = currentTemp;
    }

    public String getCityId() {
        return mCityId;
    }

    public double getCurrentTemp() {
        return mCurrentTemp;
    }
}
