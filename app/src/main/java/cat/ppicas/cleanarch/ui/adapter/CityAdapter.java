package cat.ppicas.cleanarch.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cat.ppicas.cleanarch.domain.City;

public class CityAdapter extends ArrayAdapter<City> {

    public CityAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);
        String text = city.getName();
        if (city.getCurrentWeather() != null) {
            text += "\n" + city.getCurrentWeather().getCurrentTemp();
        }
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setText(text);
        return view;
    }
}
