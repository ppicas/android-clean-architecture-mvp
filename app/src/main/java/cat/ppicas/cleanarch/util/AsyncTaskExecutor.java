package cat.ppicas.cleanarch.util;

import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cat.ppicas.cleanarch.task.Task;

public class AsyncTaskExecutor implements TaskExecutor {

    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    private final Handler mHandler = new Handler();

    private final List<WeakReference<Task<?>>> mRunningTasks
            = new ArrayList<WeakReference<Task<?>>>();

    @Override
    public <T> void execute(final Task<T> task, final TaskCallback<T> callback) {
        addRunningTask(task);
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    T result = task.execute();
                    onResult(callback, result);
                } catch (Exception e) {
                    onError(callback, e);
                }
                removeRunningTask(task);
            }
        });
    }

    @Override
    public synchronized boolean isRunning(Task<?> task) {
        for (WeakReference<Task<?>> rt : mRunningTasks) {
            if (rt.get() == task) {
                return true;
            }
        }

        return false;
    }

    private synchronized  <T> void addRunningTask(Task<T> task) {
        mRunningTasks.add(new WeakReference<Task<?>>(task));
    }

    private synchronized <T> void removeRunningTask(Task<T> task) {
        Iterator<WeakReference<Task<?>>> iterator = mRunningTasks.iterator();
        while (iterator.hasNext()) {
            WeakReference<Task<?>> rt = iterator.next();
            if (rt.get() == task || rt.get() == null) {
                iterator.remove();
            }
        }
    }

    private <T> void onResult(final TaskCallback<T> callback, final T result) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(result);
            }
        });
    }

    private <T> void onError(final TaskCallback<T> callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(e);
            }
        });
    }
}
