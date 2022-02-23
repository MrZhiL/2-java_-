import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/22 12:12
 * @node:
 */
// 使用元注解Retention来指定声明周期
@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
    String value() default "hello"; // 可以通知default来指定直接的默认值
}
