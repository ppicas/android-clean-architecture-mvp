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
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.app.ServiceContainers;
import cat.ppicas.cleanarch.ui.vista.CityDetailVista;
import cat.ppicas.cleanarch.ui.presenter.CityDetailPresenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;

public class CityDetailFragment extends Fragment implements CityDetailVista,
        PresenterFactory<CityDetailPresenter> {

    private static final String ARG_CITY_ID = "cityId";

    private String mCityId;

    private CityDetailPresenter mPresenter;

    public static CityDetailFragment newInstance(String cityId) {
        Bundle args = new Bundle();
        args.putString(ARG_CITY_ID, cityId);
        CityDetailFragment fragment = new CityDetailFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_detail, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.city_detail__view_pager);
        viewPager.setAdapter(new LocalPageAdapter(getFragmentManager()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.bindVista(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindVista();
    }

    @Override
    public void setTitle(int stringResId, Object... args) {
        getActivity().setTitle(getString(stringResId, args));
    }

    @Override
    public void displayLoading(boolean display) {
        getActivity().setProgressBarIndeterminate(true);
        getActivity().setProgressBarIndeterminateVisibility(display);
    }

    @Override
    public void displayError(int stringResId, Object... args) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public CityDetailPresenter createPresenter() {
        ServiceContainer sc = ServiceContainers.getFromApp(getActivity());
        return new CityDetailPresenter(sc.getTaskExecutor(), sc.getCityRepository(),
                getResources(), mCityId);
    }

    @Override
    public String getPresenterTag() {
        return CityDetailFragment.class.getName();
    }

    private class LocalPageAdapter extends FragmentStatePagerAdapter {

        public LocalPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return CityCurrentWeatherFragment.newInstance(mCityId);
            } else {
                return CityDailyForecastFragment.newInstance(mCityId, position - 1);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.city_details__tab_current);
            } else {
                return mPresenter.getForecastPageTitle(position - 1);
            }
        }

        @Override
        public int getCount() {
            return 1 + 3;
        }
    }
}
