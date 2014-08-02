package cat.ppicas.cleanarch.presenter.view;

public interface View {

    void showProgress(boolean show);

    void showError(int stringResId);
}
