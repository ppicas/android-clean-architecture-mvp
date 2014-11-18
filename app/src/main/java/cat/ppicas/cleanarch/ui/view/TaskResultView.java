package cat.ppicas.cleanarch.ui.view;

public interface TaskResultView extends View {

    public void displayLoading(boolean display);

    public void displayError(int stringResId, Object... args);

}
