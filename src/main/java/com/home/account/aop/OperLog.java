package  com.home.account.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
     String operModul() default ""; // 操作模块
     String operType() default "";  // 操作类型
     String operDesc() default "";  // 操作说明
}
