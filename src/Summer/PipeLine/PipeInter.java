package Summer.PipeLine;

/**
 * 管道接口，如果想要中途不按顺序执行，进行跳跃，请使用getNext（）返回目标类的class
 */

public interface PipeInter {

    public void input();
    public void work();
    public void output();
    public Class<?> getNext();

}
