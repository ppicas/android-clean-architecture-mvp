package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.FindCityTask;
import cat.ppicas.cleanarch.ui.activity.ActivityNavigator;
import cat.ppicas.cleanarch.ui.view.SearchCitiesView;
import cat.ppicas.cleanarch.util.ShowErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class SearchCitiesPresenter extends Presenter<SearchCitiesView> {

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
    public void bindView(SearchCitiesView view) {
        super.bindView(view);

        view.setTitle(R.string.search_cities__title);

        if (mLastResults != null) {
            view.showCities(mLastResults);
        } else if (!TextUtils.isEmpty(mLastSearch) && !mTaskExecutor.isRunning(mFindCityTask)) {
            onCitySearch(mLastSearch);
        }
    }

    public void onCitySearch(String cityName) {
        getView().showProgress(true);
        mLastSearch = cityName;

        if (mFindCityTask != null && mTaskExecutor.isRunning(mFindCityTask)) {
            mFindCityTask.cancel();
        }
        mFindCityTask = new FindCityTask(cityName, mCityRepository);
        mTaskExecutor.execute(mFindCityTask, new ShowErrorTaskCallback<List<City>>(this) {
            @Override
            public void onSuccess(List<City> result) {
                if (getView() != null) {
                    getView().showProgress(false);
                    if (result.isEmpty()) {
                        getView().showCitiesNotFound();
                    } else {
                        getView().showCities(result);
                    }
                }
                mLastResults = result;
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
