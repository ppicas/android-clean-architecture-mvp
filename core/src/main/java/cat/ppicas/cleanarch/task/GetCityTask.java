package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;

public class GetCityTask extends CancellableTask<City> {

    private String mId;

    private CityRepository mCityRepository;

    public GetCityTask(CityRepository cityRepository, String id) {
        mId = id;
        mCityRepository = cityRepository;
    }

    @Override
    protected City doExecute() {
        return mCityRepository.getCity(mId);
    }
}
