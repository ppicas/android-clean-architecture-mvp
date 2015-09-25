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

package cat.ppicas.framework.task;

public class TaskResult<R, E extends Exception> {

    private final R mResult;

    private final E mError;

    private final boolean mCanceled;

    public static <R, E extends Exception> TaskResult<R, E> newSuccessResult(R result) {
        return new TaskResult<>(result, null, false);
    }

    public static <R, E extends Exception> TaskResult<R, E> newErrorResult(E error) {
        return new TaskResult<>(null, error, false);
    }

    public static <R, E extends Exception> TaskResult<R, E> newCanceledResult() {
        return new TaskResult<>(null, null, true);
    }

    TaskResult(R result, E error, boolean canceled) {
        mResult = result;
        mError = error;
        mCanceled = canceled;
    }

    public R getResult() {
        return mResult;
    }

    public E getError() {
        return mError;
    }

    public boolean isSuccess() {
        return !mCanceled && mError == null;
    }

    public boolean isCanceled() {
        return mCanceled;
    }
}
