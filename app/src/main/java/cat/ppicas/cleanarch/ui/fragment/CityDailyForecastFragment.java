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

package cat.ppicas.cleanarch.ui.fragment;

import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.app.ServiceContainerProvider;
import cat.ppicas.cleanarch.ui.display.CityDailyForecastDisplay;
import cat.ppicas.cleanarch.ui.presenter.CityDailyForecastPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;

public class CityDailyForecastFragment extends Fragment implements CityDailyForecastDisplay,
        PresenterFactory<CityDailyForecastPresenter> {

    private static final String ARG_CITY_ID = "cityId";
    private static final String ARG_DAYS_FROM_TODAY = "daysFromToday";

    private String mCityId;
    private int mDaysFromToday;
    private CityDailyForecastPresenter mPresenter;

    private TextView mDescription;
    private TextView mDayTemp;
    private TextView mMinTemp;
    private TextView mMaxTemp;
    private TextView mHumidity;
    private TextView mWindSpeed;
    private View mLoading;

    public static CityDailyForecastFragment newInstance(String cityId, int daysFromToday) {
        Bundle args = new Bundle();
        args.putString(ARG_CITY_ID, cityId);
        args.putInt(ARG_DAYS_FROM_TODAY, daysFromToday);
        CityDailyForecastFragment fragment = new CityDailyForecastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mCityId = args.getString(ARG_CITY_ID);
        mDaysFromToday = args.getInt(ARG_DAYS_FROM_TODAY);

        mPresenter = ((PresenterHolder) getActivity()).getOrCreatePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View view = inflater.inflate(R.layout.fragment_city_daily_forecast, container, false);

        mDescription = (TextView) view.findViewById(R.id.daily_forecast__description);
        mDayTemp = (TextView) view.findViewById(R.id.daily_forecast__day_temp);
        mMinTemp = (TextView) view.findViewById(R.id.daily_forecast__min_temp);
        mMaxTemp = (TextView) view.findViewById(R.id.daily_forecast__max_temp);
        mHumidity = (TextView) view.findViewById(R.id.daily_forecast__humidity);
        mWindSpeed = (TextView) view.findViewById(R.id.daily_forecast__wind_speed);
        mLoading = view.findViewById(R.id.daily_forecast__loading);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.bindDisplay(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindDisplay();
    }

    @Override
    public void setForecastDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void setDayTemp(String temp) {
        mDayTemp.setText(temp);
    }

    @Override
    public void setMinTemp(String temp) {
        mMinTemp.setText(temp);
    }

    @Override
    public void setMaxTemp(String temp) {
        mMaxTemp.setText(temp);
    }

    @Override
    public void setHumidity(String humidity) {
        mHumidity.setText(humidity);
    }

    @Override
    public void setWindSpeed(String windSpeed) {
        mWindSpeed.setText(windSpeed);
    }

    @Override
    public void displayLoading(boolean display) {
        mLoading.setVisibility(display ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayError(int stringResId, Object... args) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public CityDailyForecastPresenter createPresenter() {
        Application app = getActivity().getApplication();
        ServiceContainer sc = ((ServiceContainerProvider) app).getServiceContainer();
        return new CityDailyForecastPresenter(sc.getTaskExecutor(), sc.getDailyForecastRepository(),
                mCityId, mDaysFromToday);
    }

    @Override
    public String getPresenterTag() {
        return CityDailyForecastFragment.class.getName() + "." + mDaysFromToday;
    }
}
