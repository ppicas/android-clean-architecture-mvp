package cat.ppicas.cleanarch.task;

public abstract class CancellableTask<T> implements Task<T> {

    protected boolean mCancelled;

    @Override
    public T execute() throws Exception {
        T result = doExecute();
        if (mCancelled) {
            throw new TaskCancelledException();
        }
        return result;
    }

    @Override
    public void cancel() {
        mCancelled = true;
    }

    protected abstract T doExecute() throws Exception;
}
