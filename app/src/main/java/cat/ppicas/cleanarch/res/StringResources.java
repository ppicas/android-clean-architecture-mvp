package cat.ppicas.cleanarch.res;

public interface StringResources {

    public String getString(int resId);

    public String getString(int resId, Object... formatArgs);

}
