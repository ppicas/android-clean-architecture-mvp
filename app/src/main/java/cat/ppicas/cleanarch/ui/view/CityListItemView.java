package cat.ppicas.cleanarch.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityListItemDisplay;

/**
 * A {@link LinearLayout} extension that implements {@link CityListItemDisplay}.
 */
public class CityListItemView extends LinearLayout implements CityListItemDisplay {

    private String mName;
    private String mCountry;

    private TextView mNameView;
    private TextView mTempView;
    private TextView mElevationView;

    public CityListItemView(Context context) {
        super(context);
    }

    public CityListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        mNameView = (TextView) findViewById(R.id.city_list_item__name);
        mTempView = (TextView) findViewById(R.id.city_list_item__temp);
        mElevationView = (TextView) findViewById(R.id.city_list_item__elevation);
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
    public void setCurrentTemp(String temp) {
        mTempView.setText(temp);
    }

    @Override
    public void setLoadingElevation(boolean loading) {
        String elevation = getContext().getString(R.string.city_list_item__elevation);
        if (loading) {
            mElevationView.setText(elevation);
        } else {
            if (mElevationView.getText().equals(elevation)) {
                mElevationView.setText("");
            }
        }
    }

    @Override
    public void setElevation(int elevation) {
        mElevationView.setText(NumberFormat.formatElevation(elevation));
    }

    private void updateNameView() {
        String text = mName + ", " + mCountry;
        mNameView.setText(text);
    }
}
