package StringMethodTest;

import org.junit.Test;

/**
 * @ClassName: StringMethodTest2
 * @Description: Java - String常用方法2
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/12 11:03
 * @node:
 *      boolean endsWith(String suffic): 测试此字符串是否可以指定的后缀结束
 *      boolean startsWith(String prefix): 测试此字符串是否可以以指定的前缀开始
 *      boolean startsWith(String prefix, int toffset): 测试此字符串从指定索引开始的子字符串是否以指定前缀开始
 *
 *      boolean contains(CharSequence s): 当且仅此字符串包含指定的char值序列时，返回true
 *      int indexOf(String str): 返回指定子字符串在此字符串中第一次出现处的索引
 *      int indexOf(String str, int fromIndex): 返回指定子字符串在此字符中第一此出现处的索引，从指定的索引开始
 *      int lastIndexOf(String str): 返回指定子字符串在此字符串最后边出现处的索引
 *      int lastIndexOf(String str, int fromIndex): 返回指定子字符串在此字符串中最后一次出现处的索引，**从指定的索引开始返向搜索**。
 *
 *      注：indexOf和lastIndexOf方法如果未找到都是返回-1
 *
 *      note: 什么情况下，indexOf(String str)和lastIndexOf(String str)返回相同：1. 只存在唯一一个str；2.不存在str
 */
public class StringMethodTest2 {
    @Test
    public void test3() {
        String s1 = "hellowworld";

        // 1. boolean endsWith(String suffic): 测试此字符串是否可以指定的后缀结束
        boolean b1 = s1.endsWith("rld");
        boolean b2 = s1.endsWith("rl");
        System.out.println(b1);
        System.out.println(b2);
        System.out.println("--------------------------------");

        // 2. boolean startsWith(String prefix): 测试此字符串是否可以以指定的前缀开始
        boolean b3 = s1.startsWith("he");
        boolean b4 = s1.startsWith("el");
        System.out.println(b3);
        System.out.println(b4);
        System.out.println("--------------------------------");

        // 3. boolean startsWith(String prefix, int toffset): 测试此字符串从指定索引开始的子字符串是否以指定前缀开始
        boolean b5 = s1.startsWith("ww", 5);
        System.out.println(b5);
        System.out.println("--------------------------------");

        // 4. boolean contains(CharSequence s): 当且仅此字符串包含指定的char值序列时，返回true
        System.out.println(s1.contains("ww"));  // true
        System.out.println(s1.contains("worll")); // false
        System.out.println("--------------------------------");


        String str = "helloworld";
        // 5. int indexOf(String str): 返回指定子字符串在此字符串中第一次出现处的索引
        int i1 = str.indexOf("llo");
        int i2 = str.indexOf("lloo"); // 如果找不到，则返回-1
        System.out.println(i1);
        System.out.println(i2);
        System.out.println("--------------------------------");

        // 6. int indexOf(String str, int fromIndex): 返回指定子字符串在此字符中第一此出现处的索引，从指定的索引开始
        int i3 = str.indexOf("llo", 2);
        int i4 = str.indexOf("llo", 4); // 如果找不到，则返回-1
        System.out.println(i3);
        System.out.println(i4);
        System.out.println("--------------------------------");

        // 7. int lastIndexOf(String str): 返回指定子字符串在此字符串最后边出现处的索引
        int i5 = str.lastIndexOf("llo");
        System.out.println(i5);
        System.out.println("--------------------------------");

        // 8. int lastIndexOf(String str, int fromIndex): 返回指定子字符串在此字符串中最后一次出现处的索引，**从指定的索引开始返向(左)搜索**。
        String str2 = "hellorworld";
        int i6 = str2.lastIndexOf("or", 6);
        int i7 = str2.lastIndexOf("or", 3);
        System.out.println("str = " + str2 + ", str.sunstr(0, 4) = " + str2.substring(0, 4));
        int i8 = str2.substring(0, 4).lastIndexOf("llo", 3);
        System.out.println(i6);
        System.out.println(i7);
        System.out.println(i8);
        System.out.println("--------------------------------");

        System.out.println(new String("helloworld").lastIndexOf("llo", 0)); // -1
        System.out.println(new String("helloworld").lastIndexOf("llo", 1)); // -1
        // note: 下面会输出2,可能是因为此时我们从索引2开始，helloworld的索引2为l，与"llo"的第一个元素相同，此时会继续往后（右边）寻找，知道匹配完成
        System.out.println(new String("helloworld").lastIndexOf("llo", 2)); // 2
        System.out.println(new String("hellworllod").lastIndexOf("llo", 3)); // -1
        System.out.println(new String("helloworllod").lastIndexOf("llo", 3)); // 2

    }
}
