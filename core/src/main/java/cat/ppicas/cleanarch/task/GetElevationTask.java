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

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.framework.task.SuccessTask;

public class GetElevationTask extends SuccessTask<Integer> {

    @SuppressWarnings("FieldCanBeLocal")
    private City mCity;

    public GetElevationTask(City city) {
        mCity = city;
    }

    @Override
    protected Integer doExecute() {
        int result = (int) (Math.random() * 1500);
        try {
            // Here we simulate an external call to some service
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        return result;
    }


    public void cancel() {
        // Here we could have some logic to cancel the current network request
    }
}
