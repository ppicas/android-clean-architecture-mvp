package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.City;

public class GetCityTask extends CancellableTask<City> {

    private String mId;

    public GetCityTask(String id) {
        mId = id;
    }

    @Override
    protected City doExecute() {
        return null;
    }
}
