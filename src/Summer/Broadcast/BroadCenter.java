package Summer.Broadcast;

import java.util.Map;
import java.util.LinkedHashMap;
import java.lang.reflect.Method;

public class BroadCenter {
    private static Map<Object, Data> listenerMap = new LinkedHashMap<>();   //监听队列，目标对象、（方法、监听事件类型）

    public static void register(Object object){
        //获取到这个类的所有的方法
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            //判断这个方法中有没有EventListener这个注解
            EventListener eventListener = method.getAnnotation(EventListener.class);
            if (eventListener == null)  continue;
            //把方法对象加入Map中，作为监听队列。顺便保存该object, 因为method执行需要object
            listenerMap.put(object, new Data(method, eventListener.eventType()));
        }
    }

    //别人向广播中心申请广播自己的消息
    public static void publishEvent(Event event){
        String eventType = event.getType();
        //利用反射回调监听队列中的目标监听方法
        for (Object object : listenerMap.keySet()){
            Data data = listenerMap.get(object);
            if (data.type.equals(eventType) || "default".equals(data.type))
                try { data.method.invoke(object, event); } catch (Exception e) { e.printStackTrace(); }
        }

    }

    //在监听队列中 移除监听对象
    public static void remove(Object object){
        listenerMap.remove(object);
    }

}

//数据类，（方法、监听事件类型）
class Data {
    Method method;
    String type;
    Data(Method method, String type) {
        this.method = method;
        this.type = type;
    }
}
