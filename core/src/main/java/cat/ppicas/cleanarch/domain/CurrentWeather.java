package cat.ppicas.cleanarch.domain;

public class CurrentWeather {

    private String mCityId;
    private int mCurrentTemp;
    private Integer mMaxTemp;
    private Integer mMinTemp;

    public CurrentWeather(String cityId, int currentTemp) {
        mCityId = cityId;
        mCurrentTemp = currentTemp;
    }

    public CurrentWeather(String cityId, int currentTemp, int maxTemp, int minTemp) {
        mCityId = cityId;
        mCurrentTemp = currentTemp;
        mMaxTemp = maxTemp;
        mMinTemp = minTemp;
    }

    public String getCityId() {
        return mCityId;
    }

    public int getCurrentTemp() {
        return mCurrentTemp;
    }

    public Integer getMaxTemp() {
        return mMaxTemp;
    }

    public Integer getMinTemp() {
        return mMinTemp;
    }
}
