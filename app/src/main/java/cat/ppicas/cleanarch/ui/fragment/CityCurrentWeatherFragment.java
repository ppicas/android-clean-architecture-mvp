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
import cat.ppicas.cleanarch.app.ServiceContainers;
import cat.ppicas.cleanarch.ui.presenter.CityCurrentWeatherPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;
import cat.ppicas.cleanarch.ui.vista.CityCurrentWeatherVista;

public class CityCurrentWeatherFragment extends Fragment implements CityCurrentWeatherVista,
        PresenterFactory<CityCurrentWeatherPresenter> {

    private static final String ARG_CITY_ID = "cityId";

    private String mCityId;
    private CityCurrentWeatherPresenter mPresenter;

    private TextView mCurrent;
    private TextView mHumidity;
    private TextView mWindSpeed;
    private View mLoading;

    public static CityCurrentWeatherFragment newInstance(String cityId) {
        Bundle args = new Bundle();
        args.putString(ARG_CITY_ID, cityId);
        CityCurrentWeatherFragment fragment = new CityCurrentWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args == null || !args.containsKey(ARG_CITY_ID)) {
            throw new IllegalArgumentException("Invalid Fragment arguments");
        }

        mCityId = args.getString(ARG_CITY_ID);

        mPresenter = ((PresenterHolder) getActivity()).getOrCreatePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View view = inflater.inflate(R.layout.fragment_city_current_weather, container, false);

        mCurrent = (TextView) view.findViewById(R.id.city_weather__current);
        mHumidity = (TextView) view.findViewById(R.id.city_weather__humidity);
        mWindSpeed = (TextView) view.findViewById(R.id.city_weather__wind_speed);
        mLoading = view.findViewById(R.id.city_weather__loading);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void setCurrentTemp(String temp) {
        mCurrent.setText(temp);
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
    public CityCurrentWeatherPresenter createPresenter() {
        ServiceContainer sc = ServiceContainers.getFromApp(getActivity());
        return new CityCurrentWeatherPresenter(sc.getTaskExecutor(),
                sc.getCurrentWeatherRepository(), mCityId);
    }

    @Override
    public String getPresenterTag() {
        return CityCurrentWeatherFragment.class.getName();
    }
}
