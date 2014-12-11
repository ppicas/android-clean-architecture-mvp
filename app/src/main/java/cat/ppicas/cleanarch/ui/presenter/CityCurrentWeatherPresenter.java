package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;
import cat.ppicas.cleanarch.task.GetCurrentWeatherTask;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityCurrentWeatherDisplay;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityCurrentWeatherPresenter extends Presenter<CityCurrentWeatherDisplay> {

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
    public void bindDisplay(CityCurrentWeatherDisplay display) {
        super.bindDisplay(display);

        if (mLastCurrentWeather != null) {
            updateDisplay(mLastCurrentWeather);
            return;
        }

        if (mTaskExecutor.isRunning(mGetCurrentWeatherTask)) {
            return;
        }

        display.displayLoading(true);
        mGetCurrentWeatherTask = new GetCurrentWeatherTask(mRepository, mCityId);
        mTaskExecutor.execute(mGetCurrentWeatherTask,
                new DisplayErrorTaskCallback<CurrentWeather>(this) {
                    @Override
                    public void onSuccess(CurrentWeather cw) {
                        mLastCurrentWeather = cw;
                        updateDisplay(cw);
                    }
                });
    }

    private void updateDisplay(CurrentWeather cw) {
        CityCurrentWeatherDisplay display = getDisplay();
        if (display != null) {
            display.displayLoading(false);
            display.setCurrentTemp(NumberFormat.formatTemperature(cw.getCurrentTemp()));
            display.setHumidity(NumberFormat.formatHumidity(cw.getHumidity()));
            display.setWindSpeed(NumberFormat.formatWindSpeed(cw.getWindSpeed()));
        }
    }
}
