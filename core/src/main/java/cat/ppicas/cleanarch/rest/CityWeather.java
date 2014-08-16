package cat.ppicas.cleanarch.rest;

import com.google.gson.annotations.SerializedName;

public class CityWeather {

    @SerializedName("id")
    private String mCityId;
    @SerializedName("name")
    private String mCityName;
    @SerializedName("main")
    private Main mMain;

    public String getCityId() {
        return mCityId;
    }

    public String getCityName() {
        return mCityName;
    }

    public Main getMain() {
        return mMain;
    }

    public class Main {
        @SerializedName("temp")
        private int mTemp;
        @SerializedName("temp_min")
        private int mMinTemp;
        @SerializedName("temp_max")
        private int mMaxTemp;

        public int getTemp() {
            return mTemp;
        }

        public int getMinTemp() {
            return mMinTemp;
        }

        public int getMaxTemp() {
            return mMaxTemp;
        }
    }
}
