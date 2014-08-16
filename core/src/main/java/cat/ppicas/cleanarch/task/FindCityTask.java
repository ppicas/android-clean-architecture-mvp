package cat.ppicas.cleanarch.task;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;

public class FindCityTask implements Task<List<City>> {

    private String mCityName;
    private CityRepository mRepository;

    public FindCityTask(String cityName, CityRepository repository) {
        mCityName = cityName;
        mRepository = repository;
    }

    @Override
    public List<City> execute() throws Exception {
        return mRepository.findCity(mCityName);
    }
}
