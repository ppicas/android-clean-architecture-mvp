package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.view.View;

public interface Presenter<T extends View<?>> {

    void bindView(T view);

    void unbindView();

    void saveState(Bundle state);

    void restoreState(Bundle state);

    void onDestroy();

}
