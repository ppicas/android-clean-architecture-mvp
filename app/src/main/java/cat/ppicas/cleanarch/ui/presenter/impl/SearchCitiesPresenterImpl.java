package cat.ppicas.cleanarch.ui.presenter.impl;

import android.os.Bundle;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.FindCityTask;
import cat.ppicas.cleanarch.ui.presenter.SearchCitiesPresenter;
import cat.ppicas.cleanarch.ui.view.SearchCitiesView;
import cat.ppicas.cleanarch.util.ShowErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class SearchCitiesPresenterImpl extends AbstractPresenter<SearchCitiesView>
        implements SearchCitiesPresenter {

    private TaskExecutor mTaskExecutor;
    private CityRepository mCityRepository;
    private FindCityTask mFindCityTask;

    private List<City> mRecentResults;

    public SearchCitiesPresenterImpl(TaskExecutor taskExecutor, CityRepository cityRepository) {
        mTaskExecutor = taskExecutor;
        mCityRepository = cityRepository;
    }

    @Override
    public void bindView(SearchCitiesView view) {
        super.bindView(view);

        if (mFindCityTask != null && mTaskExecutor.isRunning(mFindCityTask)) {
            view.showProgress(true);
        } else if (mRecentResults != null) {
            view.showCities(mRecentResults);
        }
    }

    @Override
    public void onCitySearch(String cityName) {
        if (mFindCityTask != null && mTaskExecutor.isRunning(mFindCityTask)) {
            mFindCityTask.cancel();
        }
        getView().showProgress(true);
        mFindCityTask = new FindCityTask(cityName, mCityRepository);
        mTaskExecutor.execute(mFindCityTask, new ShowErrorTaskCallback(this) {
            @Override
            public void onSuccess(List<City> result) {
                if (getView() != null) {
                    getView().showProgress(false);
                    getView().showCities(result);
                }
                mRecentResults = result;
            }
        });
    }

    @Override
    public void saveState(Bundle state) {
    }

    @Override
    public void restoreState(Bundle state) {
    }
}
