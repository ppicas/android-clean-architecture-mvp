package cat.ppicas.cleanarch.owm.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("UnusedDeclaration")
public class OWMDailyForecast {

    @SerializedName("dt")
    private int mTimestamp;
    @SerializedName("temp")
    private Temp mTemp;
    @SerializedName("humidity")
    private int mHumidity;
    @SerializedName("speed")
    private double mWindSpeed;
    @SerializedName("weather")
    private Weather[] mWeatherList;

    public int getTimestamp() {
        return mTimestamp;
    }

    public Temp getTemp() {
        return mTemp;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public Weather[] getWeatherList() {
        return mWeatherList;
    }

    public class Temp {
        @SerializedName("day")
        private double mDay;
        @SerializedName("min")
        private double mMin;
        @SerializedName("max")
        private double mMax;

        public double getDay() {
            return mDay;
        }

        public double getMin() {
            return mMin;
        }

        public double getMax() {
            return mMax;
        }
    }

    public class Weather {
        @SerializedName("description")
        private String mDescription;

        public String getDescription() {
            return mDescription;
        }
    }
}
