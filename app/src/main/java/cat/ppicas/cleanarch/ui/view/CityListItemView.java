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

package cat.ppicas.cleanarch.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityListItemDisplay;

/**
 * A {@link LinearLayout} extension that implements {@link CityListItemDisplay}.
 */
public class CityListItemView extends LinearLayout implements CityListItemDisplay {

    private String mName;
    private String mCountry;

    private TextView mNameView;
    private TextView mTempView;
    private TextView mElevationView;

    public CityListItemView(Context context) {
        super(context);
    }

    public CityListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        mNameView = (TextView) findViewById(R.id.city_list_item__name);
        mTempView = (TextView) findViewById(R.id.city_list_item__temp);
        mElevationView = (TextView) findViewById(R.id.city_list_item__elevation);
    }

    @Override
    public void setCityName(String name) {
        mName = name;
        updateNameView();
    }

    @Override
    public void setCountry(String country) {
        mCountry = country;
        updateNameView();
    }

    @Override
    public void setCurrentTemp(String temp) {
        mTempView.setText(temp);
    }

    @Override
    public void setLoadingElevation(boolean loading) {
        if (loading) {
            mElevationView.setText(getContext().getString(R.string.city_list_item__elevation));
        }
    }

    @Override
    public void setElevation(int elevation) {
        mElevationView.setText(NumberFormat.formatElevation(elevation));
    }

    private void updateNameView() {
        String text = mName + ", " + mCountry;
        mNameView.setText(text);
    }
}
