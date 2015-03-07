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
