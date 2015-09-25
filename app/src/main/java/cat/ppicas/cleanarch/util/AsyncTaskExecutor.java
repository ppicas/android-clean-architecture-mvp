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

import cat.ppicas.framework.task.Task;
import cat.ppicas.framework.task.TaskCallback;
import cat.ppicas.framework.task.TaskExecutor;
import cat.ppicas.framework.task.TaskResult;

/**
 * A {@link TaskExecutor} implementation that executes tasks on another thread. This class
 * will not execute various {@link Task} concurrently. Instead the tasks will be added to the
 * queue and execute one by one respecting the execution order.
 */
public class AsyncTaskExecutor implements TaskExecutor {

    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    private final Handler mHandler = new Handler();

    private final List<WeakReference<?>> mRunningTasks = new ArrayList<>();

    @Override
    public <R, E extends Exception> void execute(
            final Task<R, E> task, final TaskCallback<R, E> callback) {

        addRunningTask(task);
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final TaskResult<R, E> result = task.execute();
                removeRunningTask(task);

                if (!result.isCanceled()) {
                    if (result.isSuccess()) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result.getResult());
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError(result.getError());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public synchronized boolean isRunning(Task<?, ?> task) {
        for (WeakReference<?> rt : mRunningTasks) {
            if (rt.get() == task) {
                return true;
            }
        }

        return false;
    }

    private synchronized void addRunningTask(Task<?, ?> task) {
        mRunningTasks.add(new WeakReference<>(task));
    }

    private synchronized void removeRunningTask(Task<?, ?> task) {
        Iterator<WeakReference<?>> iterator = mRunningTasks.iterator();
        while (iterator.hasNext()) {
            WeakReference<?> reference = iterator.next();
            if (reference.get() == task || reference.get() == null) {
                iterator.remove();
            }
        }
    }
}
