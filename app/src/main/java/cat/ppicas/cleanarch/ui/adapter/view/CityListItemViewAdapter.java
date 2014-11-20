package cat.ppicas.cleanarch.ui.adapter.view;

import android.view.View;
import android.widget.TextView;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.view.CityListItemView;

public class CityListItemViewAdapter implements CityListItemView {

    private String mName;
    private String mCountry;

    private final TextView mNameView;
    private final TextView mTempView;

    /**
     * @param view a {@link View} inflated using layout {@link R.layout#view_city_list_item}
     */
    public CityListItemViewAdapter(View view) {
        mNameView = (TextView) view.findViewById(R.id.city_list_item__name);
        mTempView = (TextView) view.findViewById(R.id.city_list_item__temp);
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
    public void setCurrentTemp(Double temp) {
        mTempView.setText(NumberFormat.formatDecimal(temp) + "º");
    }

    private void updateNameView() {
        String text = mName + ", " + mCountry;
        mNameView.setText(text);
    }
}
