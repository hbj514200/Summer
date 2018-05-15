package Summer.BlackBorad;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 黑板体系结构 的实现，  用于在各模块程序之间搭建一个共享的数据池。不同模块通过对数据池中各自感兴趣的数据做出操作。
 * 有效解耦，有点类似订阅-发布模式，即本框架的Broadcast。
 * 也顺便 提供了一个共享数据池。
 * 还有， 管道过滤器模式也可以用这个东西进行数据传输
 */

public class BlackCenter {
    private static Map<String, Object> map = new HashMap<>();

    public static void put(String name, Object object){
        map.put(name, object);
    }

    public static Object get(String name){
        return map.get(name);
    }

    public static Set<String> getKeySet(){
        return map.keySet();
    }

}
