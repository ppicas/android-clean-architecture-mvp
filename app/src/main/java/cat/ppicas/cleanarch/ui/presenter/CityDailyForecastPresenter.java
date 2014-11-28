package cat.ppicas.cleanarch.ui.presenter;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.cleanarch.task.GetDailyForecastsTask;
import cat.ppicas.cleanarch.ui.display.CityDailyForecastDisplay;
import cat.ppicas.cleanarch.util.DisplayErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityDailyForecastPresenter extends Presenter<CityDailyForecastDisplay> {

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
    public void bindDisplay(CityDailyForecastDisplay display) {
        super.bindDisplay(display);

        if (mLastDailyForecast != null) {
            updateDisplay(display, mLastDailyForecast);
            return;
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
                        if (list.size() >= mDaysFromToday + 1) {
                            dailyForecast = list.get(mDaysFromToday);
                        }

                        mLastDailyForecast = dailyForecast;

                        CityDailyForecastDisplay display = getDisplay();
                        if (display != null) {
                            display.displayLoading(false);
                            if (dailyForecast != null) {
                                updateDisplay(display, dailyForecast);
                            } else {
                                display.displayError(R.string.error__connection);
                            }
                        }
                    }
                });
    }

    private void updateDisplay(CityDailyForecastDisplay display, DailyForecast df) {
        display.setForecastDescription(capitalizeFirstLetter(
                df.getDescription()));
        display.setDayTemp(df.getDayTemp());
        display.setMinTemp(df.getMinTemp());
        display.setMaxTemp(df.getMaxTemp());
        display.setHumidity(df.getHumidity());
        display.setWindSpeed(df.getWindSpeed());
    }

    private String capitalizeFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
