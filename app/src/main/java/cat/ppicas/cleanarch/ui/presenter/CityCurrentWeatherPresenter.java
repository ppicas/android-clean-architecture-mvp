package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;
import cat.ppicas.cleanarch.task.GetCurrentWeatherTask;
import cat.ppicas.cleanarch.ui.view.CityCurrentWeatherView;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityCurrentWeatherPresenter extends Presenter<CityCurrentWeatherView> {

    private TaskExecutor mTaskExecutor;
    private CurrentWeatherRepository mRepository;
    private String mCityId;

    private GetCurrentWeatherTask mGetCurrentWeatherTask;
    private CurrentWeather mLastCurrentWeather;

    public CityCurrentWeatherPresenter(TaskExecutor taskExecutor,
            CurrentWeatherRepository repository, String cityId) {
        mTaskExecutor = taskExecutor;
        mRepository = repository;
        mCityId = cityId;
    }

    @Override
    public void bindView(CityCurrentWeatherView view) {
        super.bindView(view);

        if (mLastCurrentWeather != null) {
            updateView(mLastCurrentWeather);
            return;
        }

        if (mTaskExecutor.isRunning(mGetCurrentWeatherTask)) {
            return;
        }

        view.displayLoading(true);
        mGetCurrentWeatherTask = new GetCurrentWeatherTask(mRepository, mCityId);
        mTaskExecutor.execute(mGetCurrentWeatherTask,
                new DisplayErrorTaskCallback<CurrentWeather>(this) {
                    @Override
                    public void onSuccess(CurrentWeather cw) {
                        mLastCurrentWeather = cw;
                        updateView(cw);
                    }
                });
    }

    private void updateView(CurrentWeather cw) {
        CityCurrentWeatherView view = getView();
        if (view != null) {
            view.displayLoading(false);
            view.setCurrentTemp(cw.getCurrentTemp());
            view.setHumidity(cw.getHumidity());
            view.setWindSpeed(cw.getWindSpeed());
        }
    }
}
