package cat.ppicas.cleanarch.task;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;

public class FindCityTask implements Task<List<City>> {

    private String mCityName;
    private CityRepository mRepository;
    private boolean mCancelled;

    public FindCityTask(String cityName, CityRepository repository) {
        mCityName = cityName;
        mRepository = repository;
    }

    @Override
    public List<City> execute() throws Exception {
        List<City> cities = mRepository.findCity(mCityName);
        if (mCancelled) {
            throw new TaskCancelledException();
        }
        return cities;
    }

    @Override
    public void cancel() {
        mCancelled = true;
    }
}
