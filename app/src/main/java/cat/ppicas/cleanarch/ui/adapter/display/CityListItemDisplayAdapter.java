package cat.ppicas.cleanarch.ui.adapter.display;

import android.view.View;
import android.widget.TextView;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityListItemDisplay;

/**
 * Class to provide an adapter for {@link cat.ppicas.cleanarch.ui.display.CityListItemDisplay} interface from a {@link View}
 * inflated using layout {@link R.layout#view_city_list_item}.
 */
public class CityListItemDisplayAdapter implements CityListItemDisplay {

    private String mName;
    private String mCountry;

    private final TextView mNameView;
    private final TextView mTempView;

    /**
     * @param view a {@link View} inflated using layout {@link R.layout#view_city_list_item}
     */
    public CityListItemDisplayAdapter(View view) {
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
