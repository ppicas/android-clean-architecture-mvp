package cat.ppicas.cleanarch.ui.presenter;

import cat.ppicas.cleanarch.ui.view.View;

public interface PresenterHolder {

    public <T extends Presenter<?>> T getOrCreatePresenter(View<T> view);

    public void destroyPresenter(View<?> view);

}
