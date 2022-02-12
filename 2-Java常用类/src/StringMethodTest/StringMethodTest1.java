package StringMethodTest;

import org.junit.Test;

import java.util.Locale;

/**
 * @ClassName: StringMethodTest1.java
 * @Description: Java - String常用类的介绍
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/12 10:34
 * @node:
 *      ing length(): 返回字符串的长度： return value.length
 *      char charAt(int index): 返回某索引处的字符return value[index]
 *      boolean isEmpty(): 判断是否为空字符串： return value.length == 0
 *      String toLowerCase(): 使用默认语言环境，将String中的所有字符转换为小写(之前的字符串不会改变，而是返回新的字符串)
 *      String toUpperCase(): 使用默认语言环境，将String中的所有字符转换为大写
 *      String trim(): 返回字符串的副本，忽略前导空白和尾部空白
 *      boolean equals(Object obj): 比较字符串的内容是否相同
 *      boolean equalsIgnoreCase(String anotherString): 与equals方法类似，忽略大小写
 *      String concat(String str):将指定字符串连接到此字符串的结尾。等价于用“+”
 *      int compareTo(String anotherString):比较两个字符串的大小
 *      String substring(int beginIndex): 返回一个新的字符串，子字符串以指定索引处的字符开头，并延伸到此字符串的末尾。
 *      String substring(int beginIndex, int endIndex): 返回一个新的字符串,该字符串是此字符串的子字符串。 子字符串从指定的beginIndex开始，并扩展到索引endIndex - 1处的字符。 因此子串的长度是endIndex-beginIndex
 */
public class StringMethodTest1 {

    @Test
    public void test1() {
        // 1. ing length(): 返回字符串的长度： return value.length
        String s1 = "HelloWorld";
        System.out.println(s1 + "的长度为: " + s1.length());
        System.out.println("----------------------------------------");

        // 2. char charAt(int index): 返回某索引处的字符return value[index]
        System.out.println("s1[" + 3 + "] = " + s1.charAt(3));
        System.out.println("s1[" + 4 + "] = " + s1.charAt(4));
        System.out.println("s1[" + 5 + "] = " + s1.charAt(5));
        System.out.println("----------------------------------------");

        // 3. boolean isEmpty(): 判断是否为空字符串： return value.length == 0
        String s2 = "";
        System.out.println("s1.empty ? " + s1.isEmpty());
        System.out.println("s2.empty ? " + s2.isEmpty());
        System.out.println("----------------------------------------");

        // 4. String toLowerCase(): 使用默认语言环境，将String中的所有字符转换为小写(之前的字符串不会改变，而是返回新的字符串)
        String s3 = s1.toUpperCase();
        String s4 = s3.toLowerCase(Locale.ROOT);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println("----------------------------------------");

        // 5. String trim(): 返回字符串的副本，忽略前导空白和尾部空白--常用在用户名输入中
        String s5 = "   hello   world   this   ";
        String s6 = s5.trim();
        System.out.println("-------" + s5 + "------------");
        System.out.println("-------" + s6 + "------------");
        System.out.println("----------------------------------------");

        // 6. boolean equalsIgnoreCase(String anotherString): 与equals方法类似，忽略大小写
        System.out.println(s3 + " == " + s4 + " ? " + s3.equalsIgnoreCase(s4));
        System.out.println("----------------------------------------");
    }

    @Test
    public void test2() {
        // 7. String concat(String str):将指定字符串连接到此字符串的结尾。等价于用“+”
        String s1 = "abc";
        String s2 = s1.concat("def");
        System.out.println(s1);
        System.out.println("----------------------------------------");

        // 8. int compareTo(String anotherString):比较两个字符串的大小(CompareTo本质上是两个字符串的每个字符依次比较并相减)
        String s3 = "abe";
        System.out.println(s1 + " - " + s3 + " = ? " + s1.compareTo(s3));
        System.out.println("----------------------------------------");

        // 9. String substring(int beginIndex): 返回一个新的字符串，子字符串以指定索引处的字符开头，并延伸到此字符串的末尾。
        String s4 = "MyNameisLiHua";
        String s5 = s4.substring(2);

        // 10. String substring(int beginIndex, int endIndex): 返回一个新的字符串,该字符串是此字符串的子字符串。 子字符串从指定的beginIndex开始，并扩展到索引endIndex - 1处的字符。 因此子串的长度是endIndex-beginIndex
        String s6 = s4.substring(7,10); // 左闭右开

        System.out.println(s5);
        System.out.println(s6);
    }
}
