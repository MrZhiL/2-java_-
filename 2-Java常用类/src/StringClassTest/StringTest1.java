package StringClassTest;

import org.junit.Test;

/**
 * @ClassName: StringTest1.java
 * @Description: Java - String类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/25 21:12
 * @node: `String`：字符串，使用一对“”引起来表示：
 *          test1(): 字符串的说明
 *          test2(): 字符串的创建方式
 *
 *          ### 面试题：String s = new String("abc") ; 方式创建对象，在内存中创建了几个对象？
 *          答：如果是第一次创建则是两个：一个是堆空间中new结构，另一个是char[]对应的常量池中的数据。
 *          如果之前已经声明过“abc"变量，则此时仍然会在堆空间new一个地址，然后该new出来的地址调用之前声明的”abc"变量，因为字符串常量池中只有一份数据（不允许有相同的数据）
 */
public class StringTest1 {
    /** 一：
     *  1. `String` 声明为final的，不可被继承
     *  2. `String`实现了`Serializable`接口：表示字符串是支持序列化的
     *             实现了`Comparable`接口：表示String 可以比较大小
     *
     *   3. String内部定义了final char[] value用于存储字符串数据
     *   4. String：代表不可变的字符序列。简称：不可变性
     *       体现：
     *       1. 当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
     *    	 2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能指定原有的value。
     *    	 3. 当调用String的replace()方法修改字符或字符串时，也必须重新指定内存区域赋值，不能使用原有的value。
     *    5. 通过自变量的方式（String s = "abc"; 区别于new）给一个字符串赋值，此时的字符串声明在字符串常量池中。
     *    6. 字符串常量池中是不会存储相同内容的字符串的。
     */
    @Test
    public void test1() {
        String s1 = "abc";  // 字面量的定义方式
        String s2 = "abc";
        String s3 = s2;

        System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3); // abc, abc, abc
        System.out.println("s1 == s2 ? " + (s1 == s2)); // true
        System.out.println("s2 == s3 ? " + (s3 == s2)); // true
        System.out.println("s1 == s3 ? " + (s3 == s1)); // true
        System.out.println("*************************************");

        // 1. 当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
        s2 = "hello"; // 对s2重新赋值
        System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3); // abc, hello, abc
        System.out.println("s1 == s2 ? " + (s1 == s2)); // false
        System.out.println("s2 == s3 ? " + (s3 == s2)); // false
        System.out.println("s1 == s3 ? " + (s3 == s1)); // true
        System.out.println("*************************************");

        // 2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能指定原有的value。
        s3 += "abc";
        System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3); // abc, hello, abcabc
        System.out.println("s1 == s2 ? " + (s1 == s2)); // false
        System.out.println("s2 == s3 ? " + (s3 == s2)); // false
        System.out.println("s1 == s3 ? " + (s3 == s1)); // false
        System.out.println("*************************************");

        // 3. 当调用String的replace()方法修改字符或字符串时，也必须重新指定内存区域赋值，不能使用原有的value
        String s4 = s3.replace('a', 'h');
        System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3 + ", s4 = " + s4); // abc, hello, abcabc, hbchbc
        System.out.println("s1 == s2 ? " + (s1 == s2)); // false
        System.out.println("s2 == s3 ? " + (s3 == s2)); // false
        System.out.println("s3 == s4 ? " + (s3 == s4)); // false
        System.out.println("*************************************");

        // note: 字符串常量存储在字符串常量池中，目的是共享；字符串非常量对象存储在堆中（通过new创建的对象）
        String s5 = new String("hello");
        String s6 = new String("hello");
        String s7 = "hello";
        System.out.println("s5 == s6 ? " + (s5 == s6)); // false
        System.out.println("s7 == s6 ? " + (s7 == s6)); // false
    }


    /** 二： String的实例化创建方式
     * 方式一：通过字面量定义的方式
     * 方式二：通过new + 构造器的方式
     */
    @Test
    public void test2() {
        // 1. 通过字母量的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";

        // 2. 通过new+构造器的方式：此时s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println("s1 == s2 ? " + (s1 == s2)); // true
        System.out.println("s1 == s3 ? " + (s1 == s3)); // false
        System.out.println("s1 == s4 ? " + (s1 == s4)); // false
        System.out.println("s3 == s4 ? " + (s3 == s4)); // false

        // 3. 对自定义类进行创建
        Person p1 = new Person("Bob", 13);
        Person p2 = new Person("Bob", 13);
        System.out.println("p1.name = " + p1.name + ", p2.name = " + p2.name);
        System.out.println("p1.name.equals(p2.name) ? " + p1.name.equals(p2.name)); // true
        System.out.println("p1.name == p2.name ? " + (p1.name == p2.name)); // true
        System.out.println("*************************");

        p2.name = "Jone";
        System.out.println("p1.name = " + p1.name + ", p2.name = " + p2.name);
        System.out.println("p1.name.equals(p2.name) ? " + p1.name.equals(p2.name)); // false
        System.out.println("p1.name == p2.name ? " + (p1.name == p2.name)); // false
    }
}

class Person {
    String name;
    int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}