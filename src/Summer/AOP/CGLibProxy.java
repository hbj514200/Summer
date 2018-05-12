package Summer.AOP;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 网上的CGlib代理例子。
 */

public class CGLibProxy implements MethodInterceptor {

    private Object targetObject;// CGLib需要代理的目标对象

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();// 返回代理对象
    }

    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        if (AOPCenter.getBeforeAspect(method)!=null)
            AOPCenter.getBeforeAspect(method).invoke(AOPCenter.getContext(method), args);
        obj = method.invoke(targetObject, args);    //原方法
        if (AOPCenter.getAfterAspect(method)!=null)
            AOPCenter.getAfterAspect(method).invoke(AOPCenter.getContext(method), args);
        return obj;
    }


}