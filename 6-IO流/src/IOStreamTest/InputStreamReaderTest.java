package IOStreamTest;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 转换流：实现字节流和字符流的转换
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/12 16:42
 * @node:
 *          1. 转换流提供了在字节流和字符流之间的转换
 *          2. Java API提供了两个转换流：（属于字符流）
 *             - **InputStreamReader : 将inputStream转化为Reader**（将一个字节的输入流转换为字符的输入流）
 *               ```java
 *               InputStreamReader isr = new InputStreamReader(new FileInputStream("hello.txt")); // 此时使用系统默认的字符集
 *               // InputStreamReader()方法的参数2可以指定字符集，但是具体使用那个字符集，取决于要读取的文件在保存的时候使用的字符集
 *              InputStreamReader isr=new InputStreamReader(new FileInputStream("hello.txt"),"UFT-8"); // 此时使用指定的字符集：UTF-8
 *                ```
 *          -**OutputStreamWriter:将Writer转换为OutputStream**（将一个字符的输出流转换为字节的输出流）
 *
 *          3.字节流中的数据都是字符时，转化成字符流操作更高效
 *          4.很多时候我们使用转换率来处理文件乱码的问题。实现编码和解码的功能。
 *              -解码：字节、字节数组--->字符数组、字符串
 *              -解码：字符数组、字符串--->字节、字节数组
 */
public class InputStreamReaderTest {
    @Test
    // 为了方便展示核心代码，我们这里进行了异常抛出，而没有处理。
    // 在实际编程中，一定要使用try-catch-finally进行处理
    // note: InputStreamReader可以实现字节的输入流到字符的输入流的转换
    public void testInputStreamReader() throws IOException {
        FileInputStream fis = new FileInputStream("read.md");

        // InputStreamReader inputStreamReader = new InputStreamReader(fis); // 此时使用系统（IDE）默认的编码集: 这里IDE的编码集设置为UTF-8
        InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8"); // 此时使用指定的字符集：UTF-8

        char[] cbuf = new char[10];
        int len = 0;
        while ((len = inputStreamReader.read(cbuf)) != -1) {
            System.out.print(new String(cbuf, 0, len));
        }

        if (inputStreamReader != null) {
            inputStreamReader.close();
        }

        if (fis != null) {
            fis.close();
        }
    }

    @Test
    // 为了方便展示核心代码，我们这里进行了异常抛出，而没有处理。
    // 在实际编程中，一定要使用try-catch-finally进行处理
    // 综合使用InputStreamReader 和 OutputStreamWriter() 方法
    // 使用InputStreamReader读取UTF-8编码的文件，并转化为字符流后；使用OutputStreamWriter()将读取的字符流转换为字节流，并存入GDK编码的文件中
    public void testOutputStreamWriter() throws IOException {
        FileInputStream fis = new FileInputStream("read.md");
        FileOutputStream fos = new FileOutputStream("read_gbk.md");

        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        OutputStreamWriter osr = new OutputStreamWriter(fos,"gbk");

        char[] cbuf = new char[50];
        int len = -1;

        while ((len = isr.read(cbuf)) != -1) {
            osr.write(cbuf, 0, len);
        }
        System.out.println("编码格式转换成功，并成功写入到");

        if (osr != null) {
            osr.close();
        }

        if (isr != null) {
            osr.close();
        }

        if (fos != null) {
            osr.close();
        }

        if (fis != null) {
            osr.close();
        }
    }
}
