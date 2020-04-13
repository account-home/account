package  com.home.account.aop;

import java.lang.annotation.*;

/**
 * 定义日志注解，用于注解接口访问，方便记载访问信息
 * author 吴应平
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     * @return
     */
    String description() default "";

}
