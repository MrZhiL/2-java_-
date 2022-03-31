package LambdaTest;

import org.junit.Test;

import java.util.Comparator;

/**
 * @ClassName: LambdaTest
 * @Description: Java - Lambda表达式举例
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/31 10:49
 * @node:
 */
public class LambdaTest {
    @Test
    public void test01() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 常用方法调用");
            }
        };
        r1.run();

        System.out.println("***********************");

        Runnable r2 = () -> System.out.println("Runnable->lambda表达式调用");
        r2.run();
    }


    @Test
    public void test02() {
        // 1. 常用方法调用
        Comparator<Integer> c1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = c1.compare(12, 21);
        System.out.println(compare);

        System.out.println("*****************************");

        // 2. 使用Lambda表达式进行调用
        Comparator<Integer> c2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(c2.compare(22, 11));

        // 3. 使用方法引用
        Comparator<Integer> c3 = Integer::compare;
        System.out.println(c3.compare(11, 11));
    }
}
