package Summer.PipeLine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 管道过滤器模式的框架，使用方法如下：    按列表顺序执行。管道类实现PipeInter接口。数据传输使用黑板模式模块BlackBroad.
 * PipeCenter center = new PipeCenter();
 center.addPipe(Pipe1.class, new Pipe1());
 center.addPipe(Pipe2.class, new Pipe2());
 center.addPipe(Pipe3.class, new Pipe3());
 center.addPipe(Pipe4.class, new Pipe4());
 center.addPipe(Pipe5.class, new Pipe5());
 center.work();
 */

public class PipeCenter {
    private static int xvhao = 0;
    private static List<PipeInter>     list = new ArrayList<>();
    private static Map<String,Integer> map  = new LinkedHashMap<>();

    public static void addPipe(Class cls, PipeInter inter){
        map.put(cls.getSimpleName(), xvhao++);
        list.add(inter);
    }
    public static void clear(){
        list.clear();
        map.clear();
    }

    public static void work(){
        for (int index=0; index<list.size(); index++){
            PipeInter inter = list.get(index);
            inter.input();
            inter.work();
            inter.output();
            if (inter.getNext()!=null)
                index = map.get(inter.getNext().getSimpleName())-1;
        }
    }

}

