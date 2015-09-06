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

package cat.ppicas.cleanarch.ui.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cat.ppicas.cleanarch.ui.vista.Vista;

public abstract class Presenter<T extends Vista> {

    private T mVista;

    /**
     * Returns the current bound {@link Vista} if any.
     *
     * @return The bound {@code Vista} or {@code null}.
     */
    @Nullable
    public T getVista() {
        return mVista;
    }

    /**
     * Must be called to bind the {@link Vista} and let the {@link Presenter} know that can start
     * {@code Vista} updates. Normally this method will be called from {@link Activity#onStart()}
     * or from {@link Fragment#onStart()}.
     *
     * @param vista A {@code Vista} instance to bind.
     */
    public final void start(T vista) {
        mVista = vista;
        onStart(vista);
    }

    /**
     * Must be called un unbind the {@link Vista} and let the {@link Presenter} know that must
     * stop updating the {@code Vista}. Normally this method will be called from
     * {@link Activity#onStop()} or from {@link Fragment#onStop()}.
     */
    public final void stop() {
        mVista = null;
        onStop();
    }

    /**
     * Called when the {@link Presenter} can start {@link Vista} updates.
     *
     * @param vista The bound {@code Vista}.
     */
    public abstract void onStart(T vista);

    /**
     * Called when the {@link Presenter} must stop {@link Vista} updates. The extending
     * {@code Presenter} can override this method to provide some custom logic.
     */
    public void onStop() {
    }

    /**
     * Called to ask the {@link Presenter} to save its current dynamic state, so it
     * can later be reconstructed in a new instance of its process is
     * restarted.
     *
     * @param state Bundle in which to place your saved state.
     * @see Activity#onSaveInstanceState(Bundle)
     */
    public void saveState(Bundle state) {
    }

    /**
     * Called to ask the {@link Presenter} to restore the previous saved state.
     *
     * @param state The data most recently supplied in {@link #saveState}.
     * @see Activity#onRestoreInstanceState(Bundle)
     */
    public void restoreState(Bundle state) {
    }

    /**
     * Called when this {@link Presenter} is going to be destroyed, so it has a chance to
     * release resources. The extending {@code Presenter} can override this method to provide some
     * custom logic.
     */
    public void onDestroy() {
    }
}
