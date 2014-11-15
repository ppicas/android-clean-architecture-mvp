package cat.ppicas.cleanarch.owm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class OWMCurrentWeatherList {

    @SerializedName("count")
    private int mCount;
    @SerializedName("list")
    private List<OWMCurrentWeather> mCurrentWeatherList;

    public int getCount() {
        return mCount;
    }

    public List<OWMCurrentWeather> getList() {
        return mCurrentWeatherList;
    }
}
