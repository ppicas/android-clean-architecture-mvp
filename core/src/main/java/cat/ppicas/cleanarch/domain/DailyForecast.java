package cat.ppicas.cleanarch.domain;

import java.util.Date;

public class DailyForecast {

    private String mCityId;
    private Date mDate;
    private String mDescription;
    private double mDayTemp;
    private double mMinTemp;
    private double mMaxTemp;
    private double mHumidity;
    private double mWindSpeed;

    public DailyForecast(String cityId, Date date, String description, double dayTemp,
            double minTemp, double maxTemp, double humidity, double windSpeed) {
        mCityId = cityId;
        mDate = date;
        mDescription = description;
        mDayTemp = dayTemp;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
        mHumidity = humidity;
        mWindSpeed = windSpeed;
    }

    public String getCityId() {
        return mCityId;
    }

    public Date getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getDayTemp() {
        return mDayTemp;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }
}
