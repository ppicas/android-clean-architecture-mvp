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

package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.model.City;
import cat.ppicas.framework.task.NoException;
import cat.ppicas.framework.task.Task;
import cat.ppicas.framework.task.TaskResult;

public class GetElevationTask implements Task<Integer, NoException> {

    @SuppressWarnings("FieldCanBeLocal")
    private City mCity;

    private volatile boolean mCanceled;

    public GetElevationTask(City city) {
        mCity = city;
    }

    @Override
    public TaskResult<Integer, NoException> execute() {
        try {
            // Here we simulate an external call to some service
            for (int i = 0; i < 15 && !mCanceled; i++) {
                Thread.sleep(100);
            }
        } catch (InterruptedException ignored) {}

        if (!mCanceled) {
            int result = (int) (Math.random() * 1500);
            return TaskResult.newSuccessResult(result);
        } else {
            return TaskResult.newCanceledResult();
        }
    }

    public void cancel() {
        mCanceled = true;
    }
}
