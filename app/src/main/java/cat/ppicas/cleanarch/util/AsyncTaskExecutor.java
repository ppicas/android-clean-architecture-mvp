/*
 * Copyright (C) 2015 Pau Picas Sans <pau.picas@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cat.ppicas.cleanarch.util;

import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cat.ppicas.cleanarch.task.Task;

/**
 * A {@link TaskExecutor} implementation that executes tasks on another thread. This class
 * will not execute various {@link Task} concurrently. Instead the tasks will be added to the
 * queue and execute one by one respecting the execution order.
 */
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
