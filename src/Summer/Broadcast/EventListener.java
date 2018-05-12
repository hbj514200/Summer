package Summer.Broadcast;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于注解在监听器的接收方法上面，该方法入参是接收一个Event类的事件，eventType是该方法要监听的事件类型，默认default接受全部。
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {
    String eventType() default "default";
}
