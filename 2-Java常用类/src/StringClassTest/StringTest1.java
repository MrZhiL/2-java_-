package StringClassTest;

import org.junit.Test;

/**
 * @ClassName: StringTest1.java
 * @Description: Java - String类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/25 21:12
 * @node: `String`：字符串，使用一对“”引起来表示：
 *          1. `String` 声明为final的，不可被继承
 *          2. `String`实现了`Serializable`接口：表示字符串是支持序列化的
 *                     实现了`Comparable`接口：表示String 可以比较大小
 *
 *          3. String内部定义了final char[] value用于存储字符串数据
 *          4. String：代表不可变的字符序列。简称：不可变性
 *             体现：
 *               1. 当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
 *    			 2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能指定原有的value。
 *    			 3. 当调用String的replace()方法修改字符或字符串时，也必须重新指定内存区域赋值，不能使用原有的value。
 *          5. 通过自变量的方式（String s = "abc"; 区别于new）给一个字符串赋值，此时的字符串声明在字符串常量池中。
 *          6. 字符串常量池中是不会存储相同内容的字符串的。
 */
public class StringTest1 {
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
    }

}
