package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.FindCityTask;
import cat.ppicas.cleanarch.ui.view.SearchCitiesView;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class SearchCitiesPresenter extends Presenter<SearchCitiesView> {

    private TaskExecutor mTaskExecutor;
    private CityRepository mCityRepository;
    private FindCityTask mFindCityTask;

    private List<City> mRecentResults;

    public SearchCitiesPresenter(TaskExecutor taskExecutor, CityRepository cityRepository) {
        mTaskExecutor = taskExecutor;
        mCityRepository = cityRepository;
    }

    @Override
    public void onViewStart() {
        if (mFindCityTask != null && mTaskExecutor.isRunning(mFindCityTask)) {
            getView().showProgress(true);
        } else if (mRecentResults != null) {
            getView().showCities(mRecentResults);
        }
    }

    public void onCitySearch(String cityName) {
        if (mFindCityTask != null && mTaskExecutor.isRunning(mFindCityTask)) {
            mFindCityTask.cancel();
        }
        getView().showProgress(true);
        mFindCityTask = new FindCityTask(cityName, mCityRepository);
        mTaskExecutor.execute(mFindCityTask, new ShowErrorTaskCallback(this) {
            @Override
            public void onSuccess(List<City> result) {
                getView().showProgress(false);
                getView().showCities(result);
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

    @Override
    protected Class<SearchCitiesView> getViewClassToken() {
        return SearchCitiesView.class;
    }
}
