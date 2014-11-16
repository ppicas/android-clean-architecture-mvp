package cat.ppicas.cleanarch.ui.presenter;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.cleanarch.task.GetDailyForecastsTask;
import cat.ppicas.cleanarch.ui.view.CityDailyForecastView;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityDailyForecastPresenter extends Presenter<CityDailyForecastView> {

    private TaskExecutor mTaskExecutor;
    private DailyForecastRepository mRepository;
    private final String mCityId;
    private final int mDaysFromToday;

    private GetDailyForecastsTask mGetDailyForecastsTask;
    private DailyForecast mLastDailyForecast;

    public CityDailyForecastPresenter(TaskExecutor taskExecutor, DailyForecastRepository repository,
            String cityId, int daysFromToday) {
        mTaskExecutor = taskExecutor;
        mRepository = repository;
        mCityId = cityId;
        mDaysFromToday = daysFromToday;
    }

    @Override
    public void bindView(CityDailyForecastView view) {
        super.bindView(view);

        if (mLastDailyForecast != null) {
            view.setForecastDescription(capitalizeFirstLetter(mLastDailyForecast.getDescription()));
        }

        if (mTaskExecutor.isRunning(mGetDailyForecastsTask)) {
            return;
        }

        mGetDailyForecastsTask = new GetDailyForecastsTask(mRepository, mCityId);
        mTaskExecutor.execute(mGetDailyForecastsTask,
                new DisplayErrorTaskCallback<List<DailyForecast>>(this) {
                    @Override
                    public void onSuccess(List<DailyForecast> list) {
                        DailyForecast dailyForecast = null;
                        if (list.size() >= mDaysFromToday - 1) {
                            dailyForecast = list.get(mDaysFromToday);
                        }

                        mLastDailyForecast = dailyForecast;

                        CityDailyForecastView view = getView();
                        if (view != null) {
                            view.displayLoading(false);
                            if (dailyForecast != null) {
                                view.setForecastDescription(capitalizeFirstLetter(
                                        dailyForecast.getDescription()));
                            } else {
                                view.displayError(R.string.error__connection);
                            }
                        }
                    }
                });
    }

    private String capitalizeFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
