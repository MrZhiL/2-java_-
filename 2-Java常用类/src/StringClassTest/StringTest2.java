package StringClassTest;

/**
 * @ClassName: StringClassTest2
 * @Description: Java - String连接测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/11 17:03
 * @node:
 *          1. 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
 *          2. 只要其中有一个是变量，结果就在堆中。
 *          3. 如果拼接的结果调用intern()方法，返回值就在常量池中。
 */
public class StringTest2 {
    public static void main(String[] args) {
        String s1 = "JavaEE";
        String s2 = "hadoop";

        String s3 = "JavaEEhadoop";

        // 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
        // 只要其中有一个是变量，结果就在堆中。
        String s4 = "JavaEE" + "hadoop";

        // 如果连接中有变量，则会在堆区中开辟新的空间并保存，和new一样
        String s5 = s1 + "hadoop";
        String s6 = "JavaEE" + s2;
        String s7 = s1 + s2;

        // 如果拼接的结果调用intern()方法，返回值就在常量池中。
        String s8 = (s1 + s2).intern();

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s3 == s8); // true

        System.out.println(s4 == s5); // false
        System.out.println(s4 == s6); // false
        System.out.println(s4 == s7); // false
        System.out.println(s4 == s8); // true

        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s5 == s8); // false

        System.out.println(s7 == s6); // false
        System.out.println(s7 == s8); // false
    }
}
