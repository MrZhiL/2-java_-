package StringBufferBuilder;

import org.junit.Test;

/**
 * @ClassName: StringBufferBuilder
 * @Description: Java - StringBuffer、StringBuilder的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/13 11:31
 * @node:  String、StringBuilder、StringBuffer三者的异同：
 *      - String: 不可变的字符序列，底层使用char[]存储
 *      - StringBuffer:可变的字符序列；线程安全的，效率低；底层使用char[]存储
 *      - StringBuilder: 可变的字符序列；jdk5.0新增的，为线程不安全的，效率高；底层使用char[]存储
 */
public class StringBufferBuilderTest {
    @Test
    public void test01() {
        StringBuffer strBuff1 = new StringBuffer("abc");
        System.out.println(strBuff1.length()); // 3
        System.out.println(strBuff1.capacity()); // 19

        strBuff1.setCharAt(0,'m');
        System.out.println(strBuff1); // mbc

        StringBuffer bf = strBuff1.append("hello");
        System.out.println(bf);
        System.out.println(strBuff1);
        System.out.println(strBuff1.length()); // 8
        System.out.println(strBuff1.capacity()); // 19

    }
}
