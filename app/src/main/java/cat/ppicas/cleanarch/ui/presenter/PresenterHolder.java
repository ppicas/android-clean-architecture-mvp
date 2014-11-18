package cat.ppicas.cleanarch.ui.presenter;

public interface PresenterHolder {

    public <T extends Presenter<?>> T getOrCreatePresenter(PresenterFactory<T> presenterFactory);

    public void destroyPresenter(PresenterFactory<?> presenterFactory);

}
