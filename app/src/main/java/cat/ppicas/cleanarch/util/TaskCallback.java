package cat.ppicas.cleanarch.util;

public interface TaskCallback<T> {

    public void onSuccess(T result);

    public void onError(Exception error);

}
