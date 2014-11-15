package cat.ppicas.cleanarch.ui.presenter;

import android.text.format.DateFormat;

import java.util.Calendar;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.res.StringResources;
import cat.ppicas.cleanarch.task.GetCityTask;
import cat.ppicas.cleanarch.ui.view.CityDetailView;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityDetailPresenter extends Presenter<CityDetailView> {

    private static final String DAY_OF_WEEK_DATE_FORMAT_PATTERN = "cccc";

    private TaskExecutor mTaskExecutor;
    private CityRepository mCityRepository;
    private StringResources mStringResources;
    private String mCityId;

    private GetCityTask mGetCityTask;
    private City mCity;

    public CityDetailPresenter(TaskExecutor taskExecutor, CityRepository cityRepository,
            StringResources stringResources, String cityId) {
        mTaskExecutor = taskExecutor;
        mCityRepository = cityRepository;
        mStringResources = stringResources;
        mCityId = cityId;
    }

    @Override
    public void bindView(CityDetailView view) {
        super.bindView(view);

        view.setTitle(R.string.city_details__title_loading);

        if (mCity != null) {
            view.setTitle(R.string.city_details__title, mCity.getName());
        }

        if (mTaskExecutor.isRunning(mGetCityTask)) {
            return;
        }

        view.displayLoading(true);
        mGetCityTask = new GetCityTask(mCityRepository, mCityId);
        mTaskExecutor.execute(mGetCityTask, new DisplayErrorTaskCallback<City>(this) {
            @Override
            public void onSuccess(City city) {
                mCity = city;
                CityDetailView view = getView();
                if (view != null) {
                    view.displayLoading(false);
                    view.setTitle(R.string.city_details__title, city.getName());
                }
            }
        });
    }

    public String getForecastPageTitle(int daysFromToday) {
        if (daysFromToday == 0) {
            return mStringResources.getString(R.string.city_details__tab_forecast,
                    mStringResources.getString(R.string.global__today));
        } else if (daysFromToday == 1) {
            return mStringResources.getString(R.string.city_details__tab_forecast,
                    mStringResources.getString(R.string.global__tomorrow));
        } else {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, daysFromToday);
            return mStringResources.getString(R.string.city_details__tab_forecast,
                    DateFormat.format(DAY_OF_WEEK_DATE_FORMAT_PATTERN, cal));
        }
    }
}
