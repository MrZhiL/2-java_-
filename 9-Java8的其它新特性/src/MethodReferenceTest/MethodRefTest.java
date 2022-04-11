package MethodReferenceTest;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName: MethodReferenceTest
 * @Description: Java - 方法引用测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/11 10:28
 * @node:
 *          方法引用的使用：
 *          1. 使用情景：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 *          2. 方法引用：本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以方法引用，也就是函数式接口的实例
 *          3. 使用格式： 类(或对象) :: 方法名
 *          4. 具体分为如下三种情况：
 *              情况1： 对象::非静态方法
 *              情况2： 类::静态方法
 *              情况3： 类::非静态方法
 *          5. 方法引用使用的要求：要求接口中的抽象方法的参数列表和返回值类型与方法引用的方法的形参列表和返回值类型相同 （针对前两种情况）
 */
public class MethodRefTest {

    @Test
    // Consumer中的void accept(T t)
    // PrintStream中的void println(T t)
    public void test01() {
        // 1. 非Lambda表达式
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("北京1");

        // 2. Lambda表达式
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("北京2");

        // 3. 方法引用
        PrintStream ps = System.out;
        Consumer<String > con3 = ps::println;
        con3.accept("beijing");
    }

    // 情况一：对象::非静态方法
    // Supplier中的 T get();
    // Employee中的 String getName()
    @Test
    public void test2() {
        Employee employee = new Employee(001, "jack", 34, 2200.123);

        // 1. 不使用Lambda表达式
        Supplier<String> supplier1 = new Supplier<String>() {
            @Override
            public String get() {
                return employee.getName();
            }
        };
        System.out.println(supplier1.get()); // jack

        // 2. Lambda表达式方法
        Supplier<String> supplier2 = () -> employee.getName();
        System.out.println(supplier2.get()); // jack

        // 3. 方法引用
        Supplier<String> supplier3 = employee::getName;
        System.out.println(supplier3.get()); // jack
    }

    // 情况二：类::静态方法
    // Comparator中int compare(T t1, T t2)
    // Integer 中int compare(T t1, T t2)
    @Test
    public void test03() {
        // 1. 非Lambda表达式
        Comparator<Integer> comparator1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator1.compare(10, 11)); // -1

        // 2. Lambda表达式1
        Comparator<Integer> comparator2 = (o1, o2) -> {
            return Integer.compare(o1, o2);
        };
        System.out.println(comparator1.compare(11, 11)); // 0

        // 3. Lambda表达式2
        Comparator<Integer> comparator3 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator1.compare(12, 11)); // 1

        // 4. 方法引用
        Comparator<Integer> comparator4 = Integer::compare;
        System.out.println(comparator4.compare(12, 11)); // 1
    }

    // Function中的R apply(T t)
    // Math中的Long round(Double d);
    @Test
    public void test04() {
        // 1. 非Lambda表达式
        Function<Double, Long> function1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double d) {
                return Math.round(d);
            }
        };
        System.out.println(function1.apply(123.321)); // 123

        // 2. Lambda表达式
        Function<Double, Long> function2 = d -> Math.round(d);
        System.out.println(function1.apply(321.123)); // 321

        // 3. 方法引用
        Function<Double, Long> function3 = Math::round;
        System.out.println(function3.apply(231.132)); // 231
    }

    /** 情况三：类::实例方法 (有难度) */
    // Comparator中int compare(T t1, T t2)
    // String 中的 int t1.compareTo(t2)
    @Test
    public void test05() {
        // 1. 非Lambda表达式
        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator1.compare("a", "e")); // a - e =  -4

        // 2. Lambda表达式
        Comparator<String> comparator2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator1.compare("cde", "zbd")); // cde - abd = c - z = -23

        // 3. 方法引用
        Comparator<String> comparator3 = String::compareTo;
        System.out.println(comparator1.compare("22", "22")); // 0
    }

    // BiPredicate中boolean test(T t1, T t2);
    // String 中的boolean t1.equals(t2)
    @Test
    public void test06() {
        // 1. Lambda表达式
        BiPredicate<String, String> biPredicate1 = (s1, s2) -> s1.equals(s2);
        System.out.println(biPredicate1.test("abc", "abc")); // true

        // 2. 方法引用
        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("abc", "abd")); // false
        System.out.println(biPredicate2.test("abc", "abc")); // true
    }

    // Functions的 R apply(T t)
    // Employee中的String getName();
    @Test
    public void test07() {
        Employee employee = new Employee(1002, "Merry", 24, 8000);

        // 1. 使用Lambda
        Function<Employee, String> function1 = e -> e.getName();
        System.out.println(function1.apply(employee)); // Merry

        // 2. 使用方法引用
        Function<Employee, String> function2 = Employee::getName;
        System.out.println(function2.apply(employee)); // Merry
    }
}
