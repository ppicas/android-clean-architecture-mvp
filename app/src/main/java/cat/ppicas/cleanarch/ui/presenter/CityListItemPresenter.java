package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.task.GetElevationTask;
import cat.ppicas.cleanarch.text.NumberFormat;
import cat.ppicas.cleanarch.ui.display.CityListItemDisplay;
import cat.ppicas.cleanarch.util.TaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityListItemPresenter extends Presenter<CityListItemDisplay> {

    private TaskExecutor mTaskExecutor;
    private City mCity;

    private int mElevation = -1;
    private GetElevationTask mGetElevationTask;

    public CityListItemPresenter(TaskExecutor taskExecutor, City city) {
        mTaskExecutor = taskExecutor;
        mCity = city;
    }

    @Override
    public void bindDisplay(CityListItemDisplay display) {
        super.bindDisplay(display);
        updateDisplay();

        if (mElevation > -1) {
            display.setElevation(mElevation);
            display.setLoadingElevation(false);
        } else {
            display.setLoadingElevation(true);
            if (!mTaskExecutor.isRunning(mGetElevationTask)) {
                mGetElevationTask = new GetElevationTask(mCity);
                mTaskExecutor.execute(mGetElevationTask, new GetElevationTaskCallback());
            }
        }
    }

    public void setCity(City city) {
        mCity = city;
        updateDisplay();

        if (mTaskExecutor.isRunning(mGetElevationTask)) {
            mGetElevationTask.cancel();
        }
        if (getDisplay() != null) {
            getDisplay().setLoadingElevation(true);
        }
        mElevation = -1;
        mGetElevationTask = new GetElevationTask(city);
        mTaskExecutor.execute(mGetElevationTask, new GetElevationTaskCallback());
    }

    private void updateDisplay() {
        CityListItemDisplay display = getDisplay();
        if (display == null) {
            return;
        }

        display.setCityName(mCity.getName());
        display.setCountry(mCity.getCountry());
        double temp = mCity.getCurrentWeatherPreview().getCurrentTemp();
        display.setCurrentTemp(NumberFormat.formatTemperature(temp));
    }

    private class GetElevationTaskCallback implements TaskCallback<Integer> {
        @Override
        public void onSuccess(Integer elevation) {
            if (getDisplay() != null) {
                mElevation = elevation;
                getDisplay().setElevation(elevation);
                getDisplay().setLoadingElevation(false);
            }
        }

        @Override
        public void onError(Exception error) {
            getDisplay().setLoadingElevation(false);
        }
    }
}
