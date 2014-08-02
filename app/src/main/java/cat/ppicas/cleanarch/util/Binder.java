package cat.ppicas.cleanarch.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Binder<T> {

    private final T mProxy;

    private T mObject;

    @SuppressWarnings("unchecked")
    public Binder(Class<T> interfaceToken) {
        mProxy = (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{interfaceToken}, new BinderInvocationHandler());
    }

    public void bind(T object) {
        mObject = object;
    }

    public void unbind() {
        mObject = null;
    }

    public boolean isBound() {
        return mObject != null;
    }

    public T getProxy() {
        return mProxy;
    }

    private class BinderInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (mObject != null) {
                return method.invoke(mObject, args);
            } else {
                return null;
            }
        }
    }
}
