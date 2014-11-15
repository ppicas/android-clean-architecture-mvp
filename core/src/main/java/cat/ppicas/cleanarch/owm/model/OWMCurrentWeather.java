package cat.ppicas.cleanarch.owm.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("UnusedDeclaration")
public class OWMCurrentWeather {

    @SerializedName("id")
    private String mCityId;
    @SerializedName("name")
    private String mCityName;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("wind")
    private Wind mWind;
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

    public Wind getWind() {
        return mWind;
    }

    public System getSystem() {
        return mSystem;
    }

    public class Main {
        @SerializedName("temp")
        private double mTemp;
        @SerializedName("humidity")
        private int mHumidity;

        public double getTemp() {
            return mTemp;
        }

        public int getHumidity() {
            return mHumidity;
        }
    }

    public class Wind {
        @SerializedName("speed")
        private double mSpeed;

        public double getSpeed() {
            return mSpeed;
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
