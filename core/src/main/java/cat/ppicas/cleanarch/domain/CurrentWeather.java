package cat.ppicas.cleanarch.domain;

public class CurrentWeather {

    private String mCityId;
    private double mCurrentTemp;
    private Double mMaxTemp;
    private Double mMinTemp;

    public CurrentWeather(String cityId, double currentTemp) {
        mCityId = cityId;
        mCurrentTemp = currentTemp;
    }

    public CurrentWeather(String cityId, double currentTemp, double maxTemp, double minTemp) {
        mCityId = cityId;
        mCurrentTemp = currentTemp;
        mMaxTemp = maxTemp;
        mMinTemp = minTemp;
    }

    public String getCityId() {
        return mCityId;
    }

    public double getCurrentTemp() {
        return mCurrentTemp;
    }

    public Double getMaxTemp() {
        return mMaxTemp;
    }

    public Double getMinTemp() {
        return mMinTemp;
    }
}
