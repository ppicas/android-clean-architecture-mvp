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

package cat.ppicas.cleanarch.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import cat.ppicas.cleanarch.ui.fragment.CityDetailFragment;
import cat.ppicas.cleanarch.ui.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.presenter.PresenterFactory;
import cat.ppicas.cleanarch.ui.presenter.PresenterHolder;

public class CityDetailsActivity extends Activity implements PresenterHolder {

    private PresenterHolder mPresenterHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        if (savedInstanceState == null) {
            Intent intent = new Intent(getIntent());
            getFragmentManager().beginTransaction()
                    .add(new PresenterHolderFragment(), null)
                    .add(android.R.id.content, CityDetailFragment.newInstance(intent.getCityId()))
                    .commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PresenterHolder) {
            mPresenterHolder = (PresenterHolder) fragment;
        }
    }

    @Override
    public <T extends Presenter<?>> T getOrCreatePresenter(PresenterFactory<T> presenterFactory) {
        return mPresenterHolder.getOrCreatePresenter(presenterFactory);
    }

    @Override
    public void destroyPresenter(PresenterFactory<?> presenterFactory) {
        mPresenterHolder.destroyPresenter(presenterFactory);
    }

    public static class Intent extends android.content.Intent {

        private static final String EXTRA_CITY_ID = "cityId";

        public Intent(Context packageContext, String cityId) {
            super(packageContext, CityDetailsActivity.class);
            putExtra(EXTRA_CITY_ID, cityId);
        }

        public Intent(android.content.Intent intent) {
            super(intent);
        }

        public String getCityId() {
            return getStringExtra(EXTRA_CITY_ID);
        }
    }
}
