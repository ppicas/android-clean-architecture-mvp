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
import android.os.Bundle;

import cat.ppicas.cleanarch.ui.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.ui.fragment.SearchCitiesFragment;
import cat.ppicas.framework.ui.Presenter;
import cat.ppicas.framework.ui.PresenterFactory;
import cat.ppicas.framework.ui.PresenterHolder;

public class SearchCitiesActivity extends Activity implements PresenterHolder {

    private PresenterHolder mPresenterHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(new PresenterHolderFragment(), null)
                    .add(android.R.id.content, new SearchCitiesFragment())
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
}
