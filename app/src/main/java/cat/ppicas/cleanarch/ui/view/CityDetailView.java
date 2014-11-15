package cat.ppicas.cleanarch.ui.view;

import cat.ppicas.cleanarch.ui.presenter.CityDetailPresenter;

public interface CityDetailView extends TaskResultView<CityDetailPresenter> {

    public void setTitle(int stringResId, Object... args);

}
