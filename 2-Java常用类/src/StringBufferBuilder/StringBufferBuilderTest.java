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
 *
 *      - 效率 ：StringBuilder > StringBuffer > String
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

    @Test
    public void test02() {
        // String\StringBuffer\StringBuilder三者效率的对比
        long startTime = 0L;
        long endTime = 0L;

        String test = "";
        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();

        // 开始对比:StringBuffer
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; ++i) {
            // public static String valueOf(int i) : 返回int参数的字符串表示形式。
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Buffer runtime:" + (endTime - startTime));

        // 开始对比:StringBuilder
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; ++i) {
            // public static String valueOf(int i) : 返回int参数的字符串表示形式。
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Builder runtime:" + (endTime - startTime));

        // 开始对比:String
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; ++i) {
            test += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String runtime:" + (endTime - startTime));
    }
}
