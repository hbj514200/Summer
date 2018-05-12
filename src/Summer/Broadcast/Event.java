package Summer.Broadcast;

/**
 * 监听模型中传输的消息模型， 基本是set、get方法存储传输信息。 注意：该消息可以携带一个额外的OBject对象。
 */

public class Event {
    private String type;                         //消息类型
    private String msg;                          //消息 主体正文
    private Object object;                       //可能携带的对象

    public String getType()                      { return type; }
    public String getMsg()                       { return msg;  }
    public Object getExtraObject()               { return object;  }
    public Event(String type, String msg)        { this.type = type;       this.msg = msg; }

    public Event(String type, String msg, Object object) {
        this.type = type;
        this.msg = msg;
        this.object = object;
    }

}
