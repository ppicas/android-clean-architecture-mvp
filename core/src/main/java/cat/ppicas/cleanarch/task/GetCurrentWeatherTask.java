package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.CurrentWeather;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;

public class GetCurrentWeatherTask extends CancellableTask<CurrentWeather> {

    private CurrentWeatherRepository mRepository;

    private String mCityId;

    public GetCurrentWeatherTask(CurrentWeatherRepository repository, String cityId) {
        mRepository = repository;
        mCityId = cityId;
    }

    @Override
    protected CurrentWeather doExecute() throws Exception {
        return mRepository.getCityCurrentWeather(mCityId);
    }
}
