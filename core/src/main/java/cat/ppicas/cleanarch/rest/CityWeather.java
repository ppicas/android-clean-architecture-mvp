package cat.ppicas.cleanarch.rest;

import com.google.gson.annotations.SerializedName;

public class CityWeather {

    @SerializedName("id")
    private String mCityId;
    @SerializedName("name")
    private String mCityName;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("sys")
    private System mSystem;

    public String getCityId() {
        return mCityId;
    }

    public String getCityName() {
        return mCityName;
    }

    public Main getMain() {
        return mMain;
    }

    public System getSystem() {
        return mSystem;
    }

    public class Main {
        @SerializedName("temp")
        private double mTemp;
        @SerializedName("temp_min")
        private double mMinTemp;
        @SerializedName("temp_max")
        private double mMaxTemp;

        public double getTemp() {
            return mTemp;
        }

        public double getMinTemp() {
            return mMinTemp;
        }

        public double getMaxTemp() {
            return mMaxTemp;
        }
    }

    public class System {
        @SerializedName("country")
        private String mCountry;

        public String getCountry() {
            return mCountry;
        }
    }
}
