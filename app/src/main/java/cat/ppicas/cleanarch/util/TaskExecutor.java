package cat.ppicas.cleanarch.util;

import cat.ppicas.cleanarch.task.Task;

public interface TaskExecutor {

    public <T> void execute(Task<T> task, TaskCallback<T> callback);

    public boolean isRunning(Task<?> task);
}
