package LambdaTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @ClassName: LambdaTest
 * @Description: Java - Lambda表达式的使用说明
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/31 11:03
 * @node:
 *          1. 举例：(o1, o2) -> Integer.compare(o1,o2);
 *          2. 格式：
 *                  -> : Lambda操作符 或 箭头操作符
 *                  -> 左边： Lambda形参，其实就是接口中抽象方法的形参列表
 *                  -> 右边： Lambda体，其实就是重写的抽象方法的方法体
 *          3. Lambda表达式的使用：（分为6种情况介绍）：
 *          4. Lambda语法
 *              4.1. 语法格式一：无参，无返回值
 *                  `Runnable r1 = () -> {System.out.println("Hello Lambda");}`
 *
 *              4.2. 语法格式二：Lambda需要一个参数，无返回值
 *                 `Consumer<String> con = (String str) -> {System.out.println(str);}`
 *
 *              4.3. 语法格式三：数据类型可以省略，因为可由编译器推断得出。称为“类型推断”
 *                 `Consumer<String> con = (str) -> {System.out.println(str);}`
 *
 *              4.4. 语法格式四：Lambda若只需要一个参数时，**参数的小括号可以省略**
 *                 `Consumer<String> con = str -> {System.out.println(str);}`
 *
 *              4.5. 语法格式五：Lambda若需要二个或以上的参数，多条执行语句，并且可以有返回值
 *                 `Comparator<Integer> com = (x,y) -> {
 *                      System.out.println("实现函数式接口方法！");
 *                      return Integer.compare(x,y);
 *                  }`
 *
 *              4.6. 语法格式六：当Lambda体只有一条语句时，return 与大括号若有，都可以省略。
 *                 `Comparator<Integer> com = (x,y) -> Integer.compare(x,y);`
 *
 *          5. Lambda表达式的本质：作为函数式接口的实例，而不是函数
 *          6. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口
 *          7. 总结：
 *              Lambda左边：Lambda形参列表的参数类型可以省略（类型推断）；如果Lambda形参列表只有一个参数，则这一对()也可以省略。
 *              Lambda右边：Lambda方法体应该使用一对{}包裹；如果Lambda体只有一条执行语句（可能是return语句），则可以省略这一对{}和return
 */
public class LambdaTest1 {
    @Test
    // 语法格式一：无参，无返回值
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
    // 语法格式二：Lambda需要一个参数，无返回值
    public void test02() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        con.accept("小不忍则乱大谋");
        System.out.println("******************");

        // Lambda格式：
        Consumer<String> con2 = (String s) -> { System.out.println(s); };
        con2.accept("小不忍则乱大谋！");
    }

    @Test
    // 语法格式三：数据类型可以省略，因为可由编译器推断得出。称为“类型推断”
    public void test03() {
        // Lambda格式：
        Consumer<String> con1 = (String s) -> { System.out.println(s); };
        con1.accept("小不忍则乱大谋！");
        System.out.println("**************************");

        // 类型推断
        Consumer<String> con2 = (s) -> { System.out.println(s); };
        con2.accept("hhhhhhhhhh");
    }

    @Test
    // 语法格式四：Lambda若只需要一个参数时，**参数的小括号可以省略**
    public void test04() {
        Consumer<String> con1 = (String s) -> { System.out.println(s); };
        con1.accept("小不忍则乱大谋！");
        System.out.println("**************************");

        // Lambda若只需要一个参数时，参数的小括号可以省略
        Consumer<String> con2 = s -> { System.out.println(s); };
        con2.accept("hhhhhhhhhh");
    }

    @Test
    // 语法格式五：Lambda若需要二个或以上的参数，多条执行语句，并且可以有返回值
    public void test05() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(11, 22));

        System.out.println("**********************");

        // Lambda表达式
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        System.out.println(com2.compare(111, 22));
    }

    @Test
    // 语法格式六：当Lambda体只有一条语句时，return 与大括号若有，都可以省略。
    public void test06() {
        Comparator<Integer> com1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        System.out.println(com1.compare(111, 22));
        System.out.println("*****************");

        // 当Lambda体只有一条语句时，return 与大括号若有，都可以省略。
        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(111,222));
    }


}

