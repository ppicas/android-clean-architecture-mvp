package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.City;

public class GetCityTask extends CancellableTask<City> {

    private int mId;

    public GetCityTask(int id) {
        mId = id;
    }

    @Override
    protected City doExecute() {
        return null;
    }
}
