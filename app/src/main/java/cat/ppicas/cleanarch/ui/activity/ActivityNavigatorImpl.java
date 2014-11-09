package cat.ppicas.cleanarch.ui.activity;

import android.app.Activity;

public class ActivityNavigatorImpl implements ActivityNavigator {

    private Activity mActivity;

    public ActivityNavigatorImpl(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void openCityDetails(String cityId) {
        mActivity.startActivity(new CityDetailsActivity.Intent(mActivity, cityId));
    }

}
