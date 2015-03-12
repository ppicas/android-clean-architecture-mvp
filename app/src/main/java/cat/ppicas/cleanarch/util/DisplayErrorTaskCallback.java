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

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.task.Task;
import cat.ppicas.cleanarch.task.TaskCancelledException;
import cat.ppicas.cleanarch.ui.vista.TaskResultVista;
import cat.ppicas.cleanarch.ui.presenter.Presenter;

/**
 * A {@link TaskCallback} implementation that handles {@link Task} execution errors automatically.
 * This class will stop the loader and call {@link cat.ppicas.cleanarch.ui.vista.TaskResultVista#displayError} when
 * an error is thrown during {@code Task} execution.
 */
public abstract class DisplayErrorTaskCallback<T> implements TaskCallback<T> {

    private final Presenter<? extends TaskResultVista> mPresenter;

    /**
     * Constructs an instance of {@link DisplayErrorTaskCallback} that will use
     * the specified {@link Presenter} to display the errors. To display the errors
     * this class expects a {@link Presenter} with a parameter extending {@link cat.ppicas.cleanarch.ui.vista.TaskResultVista}.
     *
     * @param presenter a {@link Presenter} with a parameter extending {@link cat.ppicas.cleanarch.ui.vista.TaskResultVista}
     */
    public DisplayErrorTaskCallback(Presenter<? extends TaskResultVista> presenter) {
        mPresenter = presenter;
    }

    public void onError(Exception error) {
        TaskResultVista vista = mPresenter.getVista();
        if (vista == null) {
            return;
        }

        vista.displayLoading(false);
        if (!(error instanceof TaskCancelledException)) {
            error.printStackTrace();
            vista.displayError(R.string.error__connection);
        }
    }
}
