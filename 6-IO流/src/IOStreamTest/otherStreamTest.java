package IOStreamTest;

import org.junit.Test;

import java.io.*;
import java.util.Locale;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 其他流的使用
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/12 18:39
 * @node: 其他流的使用
 *          1. 标准的输入、输出流： System.in, System.out
 *              - 默认输入设备是：键盘；输出设备是：显示器
 *              - System.in的类型为InputStream
 *              - System.out的类型是PrintStream，其是OutputStream的子类
 *                FilterOutputStream的子类
 *              - 重定向：通过System类的setIn, setOut方法对默认设备进行改变。
 *                - `public static void setIn(InputStream in)`
 *                - `public static void setOut(PrintStream out)`
 *
 *              1.1 练习：从键盘输入字符串，要求将读取到的整行字符串转化成大写输出，然后继续进行输入操作。
 *                  直到输入"e"或“exit"时，退出程序。
 *          2. 打印流
 *              - 实现将**基本数据类型**的数据格式转化为**字符串**输出。
 *              - 打印流：`PrintStream`, `PrintWriter`:
 *              - 提供了一系列重载的print()和println()方法，用于多种数据类型的输出
 *              - PrintStream和PrintWriter的输出不会抛出IOException异常
 *              - PrintStream和PrintWriter有自动flush功能
 *              - PrintStream打印的所有字符都使用平台的默认字符编码转换为字节。
 *                在需要写入字符而不是字节的情况下，应该使用PrintWriter类。
 *              - System.out返回的是PrintStream的实例。
 *          3. 数据流
 */
public class otherStreamTest {
    public static void main(String[] args) {
        test01();
    }

    // 练习：从键盘输入字符串，要求将读取到的整行字符串转化成大写输出，然后继续进行输入操作。直到输入"e"或“exit"时，退出程序.
    public static void test01() {
        // 方法一：使用Scanner实现，调用next()返回一个字符串
        // 方法二：使用System.in实现；因为System.in为字节流，所以需要首先转换为字符流-->再使用缓冲流进行读取（BufferedReader.readLine()）

        InputStreamReader isr = null;
        try {
            // 1. 创建输入字节流
            // fis = new InputStreamReader(System.in);

            // 1. 创建转换流
            isr = new InputStreamReader(System.in);

            // 2. 创建缓冲流
            BufferedReader br = new BufferedReader(isr);

            String data;
            while (true) {
                System.out.println("请输入数据：");
                data = br.readLine();
                // note: 为了避免空指针异常，建议使用"e".equals(data)方法，而不是data.equals("e");
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序运行结束");
                    break;
                }

                System.out.println(data.toUpperCase(Locale.ROOT));
                System.out.println(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /* 打印流：`PrintStream`, `PrintWriter`: 提供了一系列重载的print()和println()方法，用于多种数据类型的输出 */
    @Test
    public void test02() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("io_text.txt"));

            // 创建打印流，设置为自动刷新模式（写入换行符或字节'\n'时都会刷新输出缓冲区）
            ps = new PrintStream(fos, true);
            if (ps != null) {
                System.setOut(ps);
            }

            // 输出ASCII码
            for (int i = 0; i < 255; ++i) {
                System.out.print((char)i);
                if (i % 50 == 0) {
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
