package LambdaTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @ClassName: LambdaTest2
 * @Description: Java - Java内置的四大核心函数式接口
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/6 20:49
 * @node:
 *          java内置的四大核心函数式接口
 *
 *          消费性接口： Consumer<T>     void accept(T t)
 *          供给型接口： Supplier<T>     T get()
 *          函数型接口： Function<T, R>  R apply()
 *          断定型接口： Predicate<T>    boolean test(T t)
 */
public class LambdaTest2 {

    @Test
    public void test1() {
        // 1. 非Lambda表达式写法
        System.out.println("***************非Lambda表达式写法****************");
        happyTime(500, new Consumer<Double>() {

            @Override
            public void accept(Double aDouble) {
                System.out.println("消费 " + aDouble + " 元来提示学习能力");
            }
        });

        // 2. Lambda表达式写法
        System.out.println("***************Lambda表达式写法****************");
        happyTime(500, (money) -> System.out.println("消费 " + money + " 元来提示学习能力"));
    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("北京", "南京", "天津", "重庆", "成都", "东京");

        // 1. 非Lambda表达式写法，输出包含“京”的字符串
        System.out.println("***************非Lambda表达式写法****************");
        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.contains("京")) return true;

                return false;
            }
        });
        System.out.println(filterStrs);


        // 2. Lambda表达式写法，输出包含“京”的字符串
        System.out.println("*************** Lambda表达式写法****************");
        List<String> filterStrs2 = filterString(list, (String str) -> {return str.contains("京");});
        System.out.println(filterStrs2);

    }

    // 根据给定的规则，过滤集合中的字符串。此规则由Predicate方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre) {
        ArrayList<String> filterList = new ArrayList<>();

        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }

        return filterList;
    }
}
