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

package cat.ppicas.cleanarch.text;

public class NumberFormat {

    private static final java.text.NumberFormat DECIMAL_FORMATTER
            = java.text.NumberFormat.getInstance();

    static {
        DECIMAL_FORMATTER.setMaximumFractionDigits(2);
    }

    public static String formatDecimal(double num) {
        return DECIMAL_FORMATTER.format(num);
    }

    public static String formatTemperature(double temp) {
        return DECIMAL_FORMATTER.format(temp) + "º";
    }

    public static String formatHumidity(double humidity) {
        return Math.round(humidity) + "%";
    }

    public static String formatWindSpeed(double windSpeed) {
        return DECIMAL_FORMATTER.format(windSpeed) + " m/s";
    }

    public static String formatElevation(int elevation) {
        return elevation + "m";
    }

    private NumberFormat() {
        throw new RuntimeException("Instances are not allowed for this class");
    }
}
