package cat.ppicas.cleanarch.task;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;

public class FindCityTask extends CancellableTask<List<City>> {

    private String mCityName;
    private CityRepository mRepository;

    public FindCityTask(String cityName, CityRepository repository) {
        mCityName = cityName;
        mRepository = repository;
    }

    @Override
    protected List<City> doExecute() {
        return mRepository.findCity(mCityName);
    }
}
