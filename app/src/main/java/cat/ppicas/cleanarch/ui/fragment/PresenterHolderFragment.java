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

import java.util.HashMap;
import java.util.Map;

import cat.ppicas.framework.ui.Presenter;
import cat.ppicas.framework.ui.PresenterFactory;
import cat.ppicas.framework.ui.PresenterHolder;

public class PresenterHolderFragment extends Fragment implements PresenterHolder {

    private static final String STATE_PRESENTERS = "presenters";

    private final Map<String, Presenter<?>> mPresenterMap = new HashMap<String, Presenter<?>>();

    private Bundle mPresentersStates;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setRetainInstance(true);

        if (state != null) {
            mPresentersStates = state.getBundle(STATE_PRESENTERS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        Bundle presentersStates = new Bundle();
        for (Map.Entry<String, Presenter<?>> entry : mPresenterMap.entrySet()) {
            Bundle presenterState = new Bundle();
            entry.getValue().saveState(presenterState);
            presentersStates.putBundle(entry.getKey(), presenterState);
        }
        state.putBundle(STATE_PRESENTERS, presentersStates);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Presenter<?> presenter : mPresenterMap.values()) {
            presenter.onDestroy();
        }
    }

    @Override
    public <T extends Presenter<?>> T getOrCreatePresenter(PresenterFactory<T> presenterFactory) {
        String tag = presenterFactory.getPresenterTag();

        @SuppressWarnings("unchecked")
        T presenter = (T) mPresenterMap.get(tag);

        if (presenter == null) {
            presenter = presenterFactory.createPresenter();
            if (mPresentersStates != null && mPresentersStates.containsKey(tag)) {
                presenter.restoreState(mPresentersStates.getBundle(tag));
            }
            mPresenterMap.put(tag, presenter);
        }

        return presenter;
    }

    @Override
    public void destroyPresenter(PresenterFactory<?> presenterFactory) {
        String tag = presenterFactory.getPresenterTag();
        Presenter<?> presenter = mPresenterMap.get(tag);
        if (presenter != null) {
            presenter.onDestroy();
        }
        mPresenterMap.remove(tag);
    }
}
