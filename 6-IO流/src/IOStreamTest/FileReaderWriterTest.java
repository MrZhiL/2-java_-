package IOStreamTest;

import org.junit.Test;

import java.io.*;
import java.nio.CharBuffer;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - IO流的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/8 17:04
 * @node:
 *          一、流的分类
 *              1。 操作数据单位：字节流、字符流
 *              2. 数据的流向：输入流、输出流
 *              3. 流的角色： 节点流、处理流
 *
 *          二、流的体系结构
 *              抽象基类            节点流（或文件流）           缓冲流（处理流）
 *              InputStream        FileInputStream          BufferedInputStream
 *              OutputStream       FileOutputStream         BufferedOutputStream
 *              Reader             FileReader               BufferedReader
 *              Writer             FileWriter               BufferedWriter
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt"); // 此时的相对路径为当前整个工程下
        System.out.println(file.getAbsolutePath()); // D:\Program Files (x86)\JavaProject\2-Java高级部分\hello

        file = new File("6-IO流\\hello.txt"); // 此时的相对路径为当前的Module下
        System.out.println(file.getAbsolutePath()); // D:\Program Files (x86)\JavaProject\2-Java高级部分\6-IO流\hello

    }

    @Test
    // 将6-IO流中的hello。txt的文件内容读取并输出到控制台中
    // note: 在处理IO流的时候，不建议使用throws抛出异常，因为此时如果抛出异常，会导致文件流无法关闭
    /**
     * 说明点: 1. read()的理解：返回读入的一个字符，如果达到文件末尾，则返回-1
     *        2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally进行处理
     *        3. 读入的文件一定要存在，否则会报FileNotFoundException
     */
    public void test01() {
        // 1. 实例化File类的对许多，指明要操作的文件
        File file = new File("hello.txt"); // 此时的相对路径为当前的Module下
        System.out.println(file.getAbsolutePath()); // D:\Program Files (x86)\JavaProject\2-Java高级部分\6-IO流\hello

        // 2. 提供具体的流
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char[] buff = new char[500];

            // 3. 数据的读入：
            // read() : 返回读入的一个字符。如果达到文件末尾，则返回-1
            // 方式一：使用read()读取
            //int read = fileReader.read();
            //while (read != -1) {
            //    System.out.print((char)read);
            //    read = fileReader.read();
            //}

            // 方式二： 语法上针对于read的修改
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流的关闭操作
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
