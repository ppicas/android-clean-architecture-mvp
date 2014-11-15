package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;

public class GetCityTask extends CancellableTask<City> {

    private String mId;

    private CityRepository mRepository;

    public GetCityTask(CityRepository repository, String id) {
        mId = id;
        mRepository = repository;
    }

    @Override
    protected City doExecute() throws Exception {
        return mRepository.getCity(mId);
    }
}
