package cat.ppicas.cleanarch.owm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class OWMDailyForecastList {

    @SerializedName("cnt")
    private int mCount;
    @SerializedName("city")
    private City mCity;
    @SerializedName("list")
    private List<OWMDailyForecast> mDailyForecasts;

    public int getCount() {
        return mCount;
    }

    public City getCity() {
        return mCity;
    }

    public List<OWMDailyForecast> getList() {
        return mDailyForecasts;
    }

    public class City {
        @SerializedName("id")
        private String mId;

        public String getId() {
            return mId;
        }
    }
}
