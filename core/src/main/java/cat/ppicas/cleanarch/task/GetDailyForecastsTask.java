package cat.ppicas.cleanarch.task;

import java.util.List;

import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;

public class GetDailyForecastsTask extends CancellableTask<List<DailyForecast>> {

    private DailyForecastRepository mRepository;

    private String mCityId;

    public GetDailyForecastsTask(DailyForecastRepository repository, String cityId) {
        mRepository = repository;
        mCityId = cityId;
    }

    @Override
    protected List<DailyForecast> doExecute() throws Exception {
        return mRepository.getDailyForecasts(mCityId);
    }
}
