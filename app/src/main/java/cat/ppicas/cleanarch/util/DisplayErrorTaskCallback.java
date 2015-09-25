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

import java.io.IOException;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.vista.TaskResultVista;
import cat.ppicas.framework.task.TaskCallback;

/**
 * A {@link TaskCallback} implementation that handles {@link IOException} errors automatically. This
 * class will stop the loading animation and call {@link TaskResultVista#displayError} when an error
 * is thrown during {@code Task} execution.
 */
public abstract class DisplayErrorTaskCallback<T> implements TaskCallback<T, IOException> {

    private final Presenter<? extends TaskResultVista> mPresenter;

    /**
     * Constructs an instance of {@link DisplayErrorTaskCallback} that will use
     * the specified {@link Presenter} to display the errors. To display the errors
     * this class expects a {@link Presenter} with a parameter extending {@link
     * TaskResultVista}.
     *
     * @param presenter a {@link Presenter} with a parameter extending {@link
     *                  TaskResultVista}
     */
    public DisplayErrorTaskCallback(Presenter<? extends TaskResultVista> presenter) {
        mPresenter = presenter;
    }

    public void onError(IOException error) {
        TaskResultVista vista = mPresenter.getVista();
        if (vista != null) {
            vista.displayLoading(false);
            error.printStackTrace();
            vista.displayError(R.string.error__connection);
        }
    }
}
