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

package cat.ppicas.cleanarch.model;

import java.util.Date;

public class DailyForecast {

    private String mCityId;
    private Date mDate;
    private String mDescription;
    private double mDayTemp;
    private double mMinTemp;
    private double mMaxTemp;
    private double mHumidity;
    private double mWindSpeed;

    public DailyForecast(String cityId, Date date, String description, double dayTemp,
            double minTemp, double maxTemp, double humidity, double windSpeed) {
        mCityId = cityId;
        mDate = date;
        mDescription = description;
        mDayTemp = dayTemp;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
        mHumidity = humidity;
        mWindSpeed = windSpeed;
    }

    public String getCityId() {
        return mCityId;
    }

    public Date getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getDayTemp() {
        return mDayTemp;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }
}
