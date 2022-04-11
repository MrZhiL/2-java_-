package MethodReferenceTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName: ConstructorRefTest
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/11 11:37
 * @node: 构造器引用和数组引用
 *
 *        1. 构造器引用：
 *           和方法引用类似，函数式接口的抽象方法的形参列表要和构造器的形参列表一致。
 *           抽象方法的返回值类型即为构造器所属的类的类型
 *
 *        2. 数组引用
 *           如果把数组看做是一个特殊的类，则写法与构造器引用一致。
 */
public class ConstructorRefTest {
    // 构造器引用
    // Supplier中的T get()
    // Employee的空参构造器 Employee()
    @Test
    public void test01() {
        // 1. 原始方法
        Supplier<Employee> supplier1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(supplier1.get());
        System.out.println("**************************");

        // 2. Lambda表达式
        // Supplier<Employee> supplier2 = () -> new Employee(1001, "jack", 23, 1231.123); // 这个也可以实现
        Supplier<Employee> supplier2 = () -> new Employee();
        System.out.println(supplier2.get()); // 相当于 44,45行：Employee employee = supplier3.get(); System.out.println(employee);
        System.out.println("**************************");

        // 3. 构造器引用
        Supplier<Employee> supplier3 = Employee::new;
        Employee employee = supplier3.get();
        System.out.println(employee);
    }

    // Function中的R apply(T t)
    @Test
    public void test02() {

        // 1. Lambda表达式格式
        Function<Integer, Employee> function1 = (id) -> new Employee(id);
        Employee employee = function1.apply(1001);
        System.out.println(employee.getId());

        // 2. 方法调用
        Function<Integer, Employee> function2 = Employee::new;
        Employee employee2 = function2.apply(1002);
        System.out.println(employee2.getId());

    }

    // BiFunction中的 R apply(T t, U u)
    @Test
    public void test03() {
        // 1. 普通方法
        System.out.println("普通方法调用抽象接口来实现Employee类的初始化");
        BiFunction<Integer, String, Employee> biFunction = new BiFunction<Integer, String, Employee>() {
            @Override
            public Employee apply(Integer id, String name) {
                return new Employee(id, name);
            }
        };
        Employee employee1 = biFunction.apply(89, "王五");
        System.out.println(employee1);
        System.out.println();

        // 2. Lambda表达式
        System.out.println("Lambda方法调用抽象接口来实现Employee类的初始化");
        BiFunction<Integer, String, Employee> biFunction2 = (id, name) -> new Employee(id, name);
        Employee employee2 = biFunction2.apply(12, "李四");
        System.out.println();

        // 3. 构造器方法调用
        System.out.println("构造器方法：Lambda方法调用抽象接口来实现Employee类的初始化");
        BiFunction<Integer, String, Employee> biFunction3 = Employee::new;
        Employee employee3 = biFunction3.apply(11, "吉姆");
        System.out.println(employee3);
    }

    // 数组引用
    // Function中的 R apply(T t)
    @Test
    public void test04() {
        // 1. Lambda表达式引用
        Function<Integer, String[]> function1 = (length) -> new String[length];
        String[] str1 = function1.apply(10);
        System.out.println(Arrays.toString(str1));
        System.out.println("********************");

        // 2. 数组引用
        Function<Integer, String[]> function2 = String[]::new;
        String[] str2 = function2.apply(5);
        System.out.println(Arrays.toString(str2));
    }
}
