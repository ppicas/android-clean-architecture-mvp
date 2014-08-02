package cat.ppicas.cleanarch.util;

import cat.ppicas.cleanarch.task.Task;

public interface TaskExecutor {

    <T> void execute(Task<T> task, TaskCallback<T> callback);

    boolean isRunning(Task<?> task);
}
