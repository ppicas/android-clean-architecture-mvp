package cat.ppicas.cleanarch.ui.presenter;

public interface PresenterFactory<T extends Presenter<?>> {

    public T createPresenter();

    public String getPresenterTag();

}
