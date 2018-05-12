package Summer.Ioc;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述：用于注解成员的注解类
 */
@Target(ElementType.FIELD)//标识这个注解类只能注解字段
@Retention(RetentionPolicy.RUNTIME) //表示这个注解在运行时期起作用
public @interface AutoWired {
    // 注解里面的值，为 int 类型，用value表示是默认的
    // 比如：使用这个注解的时候
    // @ViewInject(R.id.btn1),R.id.btn1是int值，可以不用 @ViewInject(value=R.id.btn1)的形式。
    // 如果不是  int value()  ,而是 int name() 的话，则使用注解时就要用 @ViewInject(name=R.id.btn1)
    // 所以说 value是一个默认的值
}