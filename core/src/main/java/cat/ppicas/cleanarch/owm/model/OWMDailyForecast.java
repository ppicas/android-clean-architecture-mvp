/*
 * Copyright (C) 2015 Pau Picas Sans <pau.picas@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

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
