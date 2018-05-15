package Summer.Ioc;

import java.util.Map;
import java.util.Set;
import Summer.AOP.AOPCenter;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import Summer.AppContext.Config;
import Summer.Broadcast.BroadCenter;
import static Summer.Ioc.ScanUtil.getClasses;


public class IocCenter {
    private static Map<String, Object> singleMap = new LinkedHashMap<>();   //单例对象容器，String是类的绝对包名

    public static void scanTargetBean() {
        Set<Class<?>> clazzs = getClasses(Config.topPackage); //clazzs是类的class元数据.
        clazzs.addAll(getClasses("Summer"));
        if (clazzs == null) return;

        for (Class<?> clazz : clazzs) {
            // 获取类上的注解
            Compontent compontent = clazz.getAnnotation(Compontent.class);
            if (compontent==null)       continue;
            try {
                Object object = AOPCenter.register(clazz.getConstructor().newInstance());
                BroadCenter.register(object);                           //向广播监听器报道
                singleMap.put(clazz.getCanonicalName(), object);        //加入单例容器
            } catch (Exception e) { e.printStackTrace(); }
        }
        for (Object object : singleMap.values())    injecter(object);   //对要管理的单例Map对象注入。
    }

    private static void injecter(Object object) {       //传入object，对其@AutoWired注入
        Class<?> clazz = object.getClass();
        //获取到这个类的所有的成员字段
        Field[] declaredFields = clazz.getDeclaredFields();
        //遍历所有的字段
        for (Field declaredField : declaredFields) {
            //判断这个成员字段中有没有AutoWired这个注解
            AutoWired AutoWired = declaredField.getAnnotation(Summer.Ioc.AutoWired.class);
            //如果没有这个注解，跳过这个成员字段
            if (AutoWired == null) continue;

            String url = declaredField.getType().getName();     //获取属性类型，类型的全名，classLoader新建
            try {
                Object obj = singleMap.get(url);
                if (obj == null)    obj = getDuoLi(Class.forName(url));
                declaredField.setAccessible(true);
                declaredField.set(object, obj);
            } catch (Exception e) {
                System.out.println("Ioc错误：无法新建对应工具类 或 注入赋值失败！！ ：" + url);
                e.printStackTrace();
            }
        }
    }

    public static Object getBean(Class cls){
        Object object = singleMap.get(cls.getCanonicalName());
        return (object!=null) ? object : getDuoLi(cls);
    }

    private static Object getDuoLi(Class cls){
        Object object = null;
        try {
            object = AOPCenter.register(cls.getConstructor().newInstance());
            injecter(object);
            BroadCenter.register(object);
        } catch (Exception e) { e.printStackTrace(); }
        return object;
    }

}
