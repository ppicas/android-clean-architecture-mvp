package cat.ppicas.cleanarch.ui.display;

public interface TaskResultDisplay extends Display {

    public void displayLoading(boolean display);

    public void displayError(int stringResId, Object... args);

}
