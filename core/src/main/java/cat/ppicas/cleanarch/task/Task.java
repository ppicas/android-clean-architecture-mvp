package cat.ppicas.cleanarch.task;

public interface Task<T> {

    public T execute() throws Exception;
}
