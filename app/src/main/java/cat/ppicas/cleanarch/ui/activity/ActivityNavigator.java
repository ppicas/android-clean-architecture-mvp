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

package cat.ppicas.cleanarch.ui.activity;

import cat.ppicas.cleanarch.model.City;

/**
 * Interface that can be used to open (launch) different activities of the app without
 * relying on a concrete implementation. This interface helps to decouple the presenters
 * from the activities.
 */
public interface ActivityNavigator {

    /**
     * Opens the details activity for the specified {@link City}.
     *
     * @param cityId an ID of the desired {@code City} to open
     */
    void openCityDetails(String cityId);

}
