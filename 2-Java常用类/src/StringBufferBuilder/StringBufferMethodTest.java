package StringBufferBuilder;

import org.junit.Test;

import java.util.Locale;

/**
 * @ClassName: StringBufferBuilder
 * @Description: Java - StringBuffer类的常用方法
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/13 15:54
 * @node:
 *          StringBuffer append(xxx) : 提供了很多的append()方法，用于进行字符串拼接操作
 *          StringBuffer delete(int start, int end): 删除指定位置[start, end)位置的内容
 *          StringBuffer replace(int start, int end, String str): 把[start, end)位置替换为str字符串
 *          StringBuffer insert(int offset, xxx): 在指定位置插入xxx
 *          StringBuffer reveres(): 把当前字符序列逆转
 *
 *          public int indexOf(String str) : 返回指定子字符串str第一次出现的字符串中的索引。
 *          public String substring(int start, int end): // 返回一个从start开始到end索引位置的子字符串
 *          public int length()：查找字符序列的长度
 *          public char charAt()：查找第n个索引位置的元素
 *          public void setCharAt(int n, char ch) : 修改第n个索引位置的元素为ch
 */
public class StringBufferMethodTest {
    @Test
    public void test() {
        StringBuffer buffer = new StringBuffer("helloworld");

        // 1. append拼接操作, 可以进行链式操作
        buffer.append(1).append('c').append("hhh");
        System.out.println(buffer);

        // 2. StringBuffer delete(int start, int end): 删除指定位置[start, end)位置的内容
        buffer.delete(10,12); // 删除[10,12)位置的元素
        System.out.println(buffer);
        buffer.delete(10,11).delete(5,10); // delete也可知支持链式操作
        System.out.println(buffer);

        // 3. StringBuffer replace(int start, int end, String str): 把[start, end)位置替换为str字符串
        buffer.replace(5,7, "world");
        System.out.println(buffer);

        // 4. insert(int offset, xxx): 在指定位置插入xxx
        buffer.insert(5, "wlecome");
        System.out.println(buffer);

        // 5. StringBuffer reveres(): 把当前字符序列逆转
        buffer.reverse();
        System.out.println(buffer);
        buffer.reverse();

        // 6. public int length()：查找字符序列的长度
        System.out.println(buffer.length());

        // 7. public char charAt()：查找第n个索引位置的元素
        System.out.println(buffer.charAt(2));

        // 8. public void setCharAt(int n, char ch) : 修改第n个索引位置的元素为ch
        buffer.setCharAt(2, (char) (buffer.charAt(2)+1));
        System.out.println(buffer);

        // 9. public int indexOf(String str) : 返回指定子字符串str第一次出现的字符串中的索引。
        System.out.println(buffer.indexOf("world"));

    }
}
