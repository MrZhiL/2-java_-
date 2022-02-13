## String 与 基本数据类型、包装类的转换

### 1. 复习：String与基本数据类型、包装类之间的转换

*          字符串 -> 基本数据类型、包装类：
            *          Integer包装类的public static int parseInt(String s): 可以将由“数字”符组成的字符串转换为整型。
            *          类似地，使用java.lang包中的Byte、Short、Long、Float、Double类调用相应的类方法可以将由“数字”字符组成的字符串，转化为相应的基本数据类型。
*          基本数据类型、包装类 --> String: 
            *          调用String重载的valueof() 或者直接使用连接符
            *          相应的valueOf(byte b)、valueOf(long l)、valueOf(float f)、valueOf(double b)、valueOf(boolean b)可右参数的相应类型到字符串的转换。



### 2. String与字符数组char[]转换

- 字符数组char[] -> 字符串
    - String类的构造器：String(char[]) 和 String(char[], int offset, int length) 分别用字符数组中的全部字符和部分字符创建字符串对象。
- 字符串 -> 字符数组
    - public char[] to CharArray(): 将字符串中的全部字符存放在一个字符数组中的方法。
    - public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin): 提供了将指定索引范围内的字符串存放到数组中的方法



### 3. String与字节数组byte[]的转换

- 字节数组 -> 字符串
    - String(byte[] bytes): 通过使用平台默认的字符集解码指定的byte数组，构造一个新的String。
    - String(byte[] bytes, int offset, int length): 用指定的字节数组的一部分，即从数组起始位置offset开始取length个字节构造一个字符串对象。
- 字符串 -> 字节数组
    - public byte[] getBytes() : 使用平台的默认字符集将此String编码为byte序列，并将结果存储到一个新的byte数组中
    - public byte[] getBytes(String charsetName): 使用指定的字符集将此String编码到byte序列，并将结果存储到新的byte数组。

```java
package StringReverseTest;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @ClassName: StringReverseTest1
 * @Description: Java - String数据类型与基本包装类的转换
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/13 10:21
 * @node: 复习：
 *
 *          1. String与基本数据类型、包装类之间的转换
 *              String --> 基本数据类型、包装类：调用包装类的静态方法prasexxx(str);
 *              基本数据类型、包装类 --> String: 调用String重载的valueof() 或者直接使用连接符
 *          2. String 与 char[] 的转换
 *              String --> char[] : 调用String的toCharArray()方法；
 *              char[] --> String : 调用String的构造函数
 *
 *          3. String 与 byte[] 的转换：
 *              编码：String --> byte[] : 调用String的getBytes();
 *              解码：byte[] --> String : 调用String的构造器
 *              note:编码和解码时需要使用相同的字符集，否则会出现乱码
 *              编码：字符串-->字节
 *              解码：编码的逆过程
 */
public class StringTest1 {
    @Test
    // 1. String与基本数据类型、包装类之间的转换
    public void test1() {
        String str1 = "123";

        // int num1 = (int)str1; // error，不可以使用强制转换,强制转换只适用于子父类关系
        int num1 = Integer.parseInt(str1);

        String s2 = num1 + "";   // 1. 使用连接符进行转换，此时s2在堆中
        String s3 = String.valueOf(num1); // 2. 使用valueof进行转换，此时s3在堆中

        System.out.println(str1 == s2); // false
        System.out.println(str1 == s3); // false

    }

    @Test
    // 2. String与char[]的转换
    public void test2() {
        String str1 = "abc123";

        // 2.1 String --> char[] : 调用toCharAyyay()方法
        char[] ch = str1.toCharArray();
        for (int i = 0; i < ch.length; ++i) {
            System.out.println(ch[i]);
        }

        // 2.2 char[] --> String : 调用String的构造函数
        char[] ch2 = {'h', 'e', 'l', 'l', 'o'};
        String str2 = new String(ch2);
        System.out.println(str2);
    }

    @Test
    // 3. String 与 byte[] 的转换：
    public void test3() throws UnsupportedEncodingException {
        String str1 = "China中国";

        // 3.1 String --> byte[]
        byte[] bytes = str1.getBytes(); // 如果不指定编码字符集，则采用编译器默认的字符集
        System.out.println(bytes[0]);
        System.out.println(Arrays.toString(bytes));
        System.out.println("***************************");

        // 采用指定的字符集合进行转换,此时需要抛出异常
        byte[] gbks = str1.getBytes("gbk");
        System.out.println(Arrays.toString(gbks));
        System.out.println("***************************");

        // 3.2 byte[] -> String : 采用String(byte[] bytes)的构造器
        String str2 = new String(bytes); // 此时会采用编译器默认的字符集进行转换
        System.out.println(str2);

        // 指定转换的字符集
        String str3 = new String(gbks);
        System.out.println(str3);

        String str4 = new String(gbks, "gbk");
        System.out.println(str4);

    }
}

```

