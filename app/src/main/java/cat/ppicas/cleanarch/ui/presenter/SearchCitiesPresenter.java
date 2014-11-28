package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.FindCityTask;
import cat.ppicas.cleanarch.ui.activity.ActivityNavigator;
import cat.ppicas.cleanarch.ui.display.SearchCitiesDisplay;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class SearchCitiesPresenter extends Presenter<SearchCitiesDisplay> {

    private static final String STATE_LAST_SEARCH = "lastSearch";

    private TaskExecutor mTaskExecutor;
    private ActivityNavigator mActivityNavigator;
    private CityRepository mCityRepository;

    private FindCityTask mFindCityTask;
    private String mLastSearch;
    private List<City> mLastResults;

    public SearchCitiesPresenter(TaskExecutor taskExecutor, ActivityNavigator activityNavigator,
            CityRepository cityRepository) {
        mTaskExecutor = taskExecutor;
        mActivityNavigator = activityNavigator;
        mCityRepository = cityRepository;
    }

    @Override
    public void bindDisplay(SearchCitiesDisplay display) {
        super.bindDisplay(display);

        display.setTitle(R.string.search_cities__title);

        if (mLastResults != null) {
            display.setCities(mLastResults);
        } else if (!TextUtils.isEmpty(mLastSearch) && !mTaskExecutor.isRunning(mFindCityTask)) {
            onCitySearch(mLastSearch);
        }
    }

    public void onCitySearch(String cityName) {
        getDisplay().displayLoading(true);
        mLastSearch = cityName;

        if (mFindCityTask != null) {
            mFindCityTask.cancel();
        }
        mFindCityTask = new FindCityTask(cityName, mCityRepository);
        mTaskExecutor.execute(mFindCityTask, new DisplayErrorTaskCallback<List<City>>(this) {
            @Override
            public void onSuccess(List<City> result) {
                mLastResults = result;
                SearchCitiesDisplay display = getDisplay();
                if (display != null) {
                    display.displayLoading(false);
                    if (result.isEmpty()) {
                        display.displayCitiesNotFound();
                    } else {
                        display.setCities(result);
                    }
                }
            }

            @Override
            public void onError(Exception error) {
                super.onError(error);
                mLastSearch = null;
            }
        });
    }

    public void onCitySelected(String cityId) {
        mActivityNavigator.openCityDetails(cityId);
    }

    @Override
    public void saveState(Bundle state) {
        state.putString(STATE_LAST_SEARCH, mLastSearch);
    }

    @Override
    public void restoreState(Bundle state) {
        mLastSearch = state.getString(STATE_LAST_SEARCH);
    }

    @Override
    public void onDestroy() {
        if (mFindCityTask != null) {
            mFindCityTask.cancel();
        }
    }
}
