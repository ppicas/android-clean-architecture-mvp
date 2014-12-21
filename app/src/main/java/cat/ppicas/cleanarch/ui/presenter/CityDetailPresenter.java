package cat.ppicas.cleanarch.ui.presenter;

import android.content.res.Resources;
import android.text.format.DateFormat;

import java.util.Calendar;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.GetCityTask;
import cat.ppicas.cleanarch.ui.display.CityDetailDisplay;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityDetailPresenter extends Presenter<CityDetailDisplay> {

    private static final String DAY_OF_WEEK_DATE_FORMAT_PATTERN = "cccc";

    private TaskExecutor mTaskExecutor;
    private CityRepository mCityRepository;
    private Resources mResources;
    private String mCityId;

    private GetCityTask mGetCityTask;
    private City mCity;

    public CityDetailPresenter(TaskExecutor taskExecutor, CityRepository cityRepository,
            Resources resources, String cityId) {
        mTaskExecutor = taskExecutor;
        mCityRepository = cityRepository;
        mResources = resources;
        mCityId = cityId;
    }

    @Override
    public void bindDisplay(CityDetailDisplay display) {
        super.bindDisplay(display);

        display.setTitle(R.string.city_details__title_loading);

        if (mCity != null) {
            display.setTitle(R.string.city_details__title, mCity.getName());
            return;
        }

        display.displayLoading(true);

        if (mTaskExecutor.isRunning(mGetCityTask)) {
            return;
        }
        mGetCityTask = new GetCityTask(mCityRepository, mCityId);
        mTaskExecutor.execute(mGetCityTask, new DisplayErrorTaskCallback<City>(this) {
            @Override
            public void onSuccess(City city) {
                mCity = city;
                CityDetailDisplay display = getDisplay();
                if (display != null) {
                    display.displayLoading(false);
                    display.setTitle(R.string.city_details__title, city.getName());
                }
            }
        });
    }

    public String getForecastPageTitle(int daysFromToday) {
        if (daysFromToday == 0) {
            return mResources.getString(R.string.city_details__tab_forecast,
                    mResources.getString(R.string.global__today));
        } else if (daysFromToday == 1) {
            return mResources.getString(R.string.city_details__tab_forecast,
                    mResources.getString(R.string.global__tomorrow));
        } else {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, daysFromToday);
            return mResources.getString(R.string.city_details__tab_forecast,
                    DateFormat.format(DAY_OF_WEEK_DATE_FORMAT_PATTERN, cal));
        }
    }
}
