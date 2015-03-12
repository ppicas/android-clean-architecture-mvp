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

package cat.ppicas.cleanarch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainers;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.ui.vista.CityListItemVista;
import cat.ppicas.cleanarch.ui.presenter.CityListItemPresenter;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityAdapter extends ArrayAdapter<City> {

    private final LayoutInflater mInflater;
    private final TaskExecutor mTaskExecutor;

    public CityAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        mInflater = LayoutInflater.from(context);

        mTaskExecutor = ServiceContainers.getFromApp(context).getTaskExecutor();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = mInflater.inflate(R.layout.view_city_list_item, parent, false);
        }
        CityListItemVista vista = (CityListItemVista) view;

        City city = getItem(position);

        // Initialize or reconfigures a Presenter for CityListItemView
        CityListItemPresenter presenter = (CityListItemPresenter) view.getTag();
        if (presenter == null) {
            presenter = new CityListItemPresenter(mTaskExecutor, city);
            presenter.bindVista(vista);
            view.setTag(presenter);
            view.addOnAttachStateChangeListener(new AdapterViewUnbinder(presenter));
        } else {
            presenter.setCity(city);
        }

        return view;
    }
}
