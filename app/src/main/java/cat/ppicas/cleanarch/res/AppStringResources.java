package cat.ppicas.cleanarch.res;

import android.content.res.Resources;

public class AppStringResources implements StringResources {

    private Resources mResources;

    public AppStringResources(Resources resources) {
        mResources = resources;
    }

    @Override
    public String getString(int resId) {
        return mResources.getString(resId);
    }

    @Override
    public String getString(int resId, Object... formatArgs) {
        return mResources.getString(resId, formatArgs);
    }
}
