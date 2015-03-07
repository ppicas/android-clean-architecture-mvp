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
