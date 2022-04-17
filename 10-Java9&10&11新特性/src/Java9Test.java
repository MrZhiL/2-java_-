import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/17 9:10
 * @node:
 */
public class Java9Test {
    // java9特性五：钻石操作符的升级
    // 钻石操作符与匿名内部类在Java 8中不能共存，在java 9中可以
    @Test
    public void test2() {
        // 在java 8中如果使用匿名内部类，则必须指明操作的类型：Comparator<Object> com = new Comparator<Object> () {...}
        // 否则会：编译报错信息：Cannot use "<>" with anonymous inner classes
        // 在java 9中可以不用指明操作的类型，因为java 9中对其进行了升级
        Comparator<Object> com = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        // jdk7 中的新特性：类型推断
        ArrayList<String> list = new ArrayList<>();
    }
}
