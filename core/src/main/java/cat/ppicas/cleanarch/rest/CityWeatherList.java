package cat.ppicas.cleanarch.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWeatherList {

    @SerializedName("count")
    private int mCount;
    @SerializedName("list")
    private List<CityWeather> mCityWeathers;

    public int getCount() {
        return mCount;
    }

    public List<CityWeather> getCityWeathers() {
        return mCityWeathers;
    }
}
