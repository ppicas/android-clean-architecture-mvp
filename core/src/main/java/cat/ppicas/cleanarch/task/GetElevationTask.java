package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.City;

public class GetElevationTask extends CancellableTask<Integer> {

    @SuppressWarnings("FieldCanBeLocal")
    private City mCity;

    public GetElevationTask(City city) {
        mCity = city;
    }

    @Override
    protected Integer doExecute() throws Exception {
        try {
            // Here we simulate an external call to some service
            Thread.sleep(1500);
            return (int) (Math.random() * 1500);
        } catch (InterruptedException ignored) {
            return 0;
        }
    }
}
