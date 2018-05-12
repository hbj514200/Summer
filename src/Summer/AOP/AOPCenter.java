package Summer.AOP;

import Summer.Ioc.IocCenter;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static Summer.Ioc.ScanUtil.getClasses;

public class AOPCenter {
    private static Map<Method, Object> contextMap = new LinkedHashMap<>();      //保存切面方法的Object
    private static Map<Method, String> aspectMap  = new LinkedHashMap<>();      //正则，方法

    private static Map<Method, Method> beforeMap  = new LinkedHashMap<>();      //原方法，前置方法
    private static Map<Method, Method> afterMap   = new LinkedHashMap<>();      //原方法，后置方法

    public static void scanTargetBean(){
        Set<Class<?>> clazzs = getClasses("xiaoxiang");
        if (clazzs == null) return;

        for (Class<?> clazz : clazzs) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods){
                Before before = method.getAnnotation(Before.class);
                After after  = method.getAnnotation(After.class);
                if (before!=null){
                    aspectMap.put(method, "B"+before.execution());      //与后置方法切面区分
                    contextMap.put(method, IocCenter.getBean(clazz));
                }
                if (after!=null){
                    aspectMap.put(method, "A"+after.execution());
                    contextMap.put(method, IocCenter.getBean(clazz));
                }
            }
        }
    }

    public static Object register(Object object){
        boolean isTarget = false;
        Class cls = object.getClass();
        for (Method method : cls.getDeclaredMethods()){
            for (Method key : aspectMap.keySet()){
                String value1 = aspectMap.get(key);
                String value = value1.substring(1, value1.length());

                    if ( (cls.getName()+"."+method.getName()).matches(value) ){
                        if (value1.charAt(0)=='B')
                            beforeMap.put(method, key);
                        if (value1.charAt(0)=='A')
                            afterMap.put(method,  key);
                        isTarget = true;
                    }
            }
        }
        return (isTarget) ? new CGLibProxy().createProxyObject(object) : object;
    }

    public static Method getBeforeAspect(Method method){
        return beforeMap.get(method);
    }
    public static Method getAfterAspect(Method method){
        return afterMap.get(method);
    }

    public static Object getContext(Method method){
        if (getBeforeAspect(method)!=null)
            return contextMap.get(getBeforeAspect(method));
        else
            return contextMap.get(getAfterAspect(method));
    }

}
