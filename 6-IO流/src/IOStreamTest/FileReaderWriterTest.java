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
    // 将6-IO流中的hello.txt的文件内容读取并输出到控制台中
    // note: 在处理IO流的时候，不建议使用throws抛出异常，因为此时如果抛出异常，会导致文件流无法关闭
    /**
     * 说明点: 1. read()的理解：返回读入的一个字符，如果达到文件末尾，则返回-1
     *        2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally进行处理
     *        3. 读入的文件一定要存在，否则会报FileNotFoundException
     */
    public void testFileReader01() {
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

    @Test
    // 对test01()中的read()方法的改进，将hello.txt中的内容读取并输出到控制台中
    public void testFileReader02() {
        // 1. File类的实例化
        // 2. FileReader流的实例化
        // 3. 读入的操作
        // 4. 资源的关闭

        FileReader fr = null; // 创建FileReader的变量
        try {
            // 1. File类的实例化
            File file = new File("hello.txt");

            // 2. FileReader流的实例化
            fr = new FileReader(file);

            // 3. 读入的操作，使用read(char[] cbuf) : 返回这每次读入cbuf数组中的字符的个数。果到达文件末尾，则返回-1
            char[] cbuf = new char[50];
            int len = 0;
            while ((len = fr.read(cbuf)) != -1) {
                // System.out.print(new String(cbuf); // error，此时会把cbuf中的内容全部输出，导致结构错误
                System.out.print(new String(cbuf, 0, len));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的关闭
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    // 将内存中写出数据到磁盘的文件中
    /** 输出操作，对应的File可以不存在，并不会报异常**：
     *    - 对应的文件如果不存在，在输出的过程中，会自动创建此文件
     *    - 对应的文件如果存在：
     *         - 如果流使用的构造器是：FileWriter(file, false) / FileWriter(file) : 此时会对原有的文件进行覆盖
     *         - 如果流使用的构造器是：FileWriter(file, true) : 不会对原有的文件进行覆盖，而是在后面追加
     *
     *    步骤： 1. 提供File类的对象，指明写出到的文件
     *          2. 提供FileWriter的对象，用于数据的写出
     *          3. 写出的操作
     *          4. 流资源的关闭
     */
    public void testFileWriter01() {
        FileWriter fw = null;

        try {
            // 1. 提供File类的对象，指明写出到的文件
            File file = new File("hello1.txt");

            // 2. 提供FileWriter的对象，用于数据的写出
            // 将append设置为false，从而为覆盖模式
            // new FileWriter(file) : 此时调用的时候默认为覆盖模式，会从头开始写
            // fw = new FileWriter(file, false); // 此时如果文件不存在，则会自动创建
            fw = new FileWriter(file, true); // 使用追加模式

            // 3. 写出的操作
            fw.write("第一次写入\n");
            fw.write("第二次写入\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流资源的关闭
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Test
    // 将hello.txt中的内容读取出来并存入hello1.txt中
    public void testFileReadertoWriter() {
        // 1. File类的实例化
        // 2. IO流的实例化
        // 3. 读入的操作
        // 4. 资源的关闭

        FileReader fr = null; // 创建FileReader的变量
        FileWriter fw = null;
        try {
            // 1. File类的实例化
            File srcFile = new File("hello.txt");
            File destFile = new File("hello1.txt");

            // 2. IO流的实例化
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3. 读入的操作，使用read(char[] cbuf) : 返回这每次读入cbuf数组中的字符的个数。果到达文件末尾，则返回-1
            char[] cbuf = new char[50];
            int len = 0;
            while ((len = fr.read(cbuf)) != -1) {
                // System.out.print(new String(cbuf); // error，此时会把cbuf中的内容全部输出，导致结构错误
                // System.out.print(new String(cbuf, 0, len));
                fw.write(cbuf, 0, len);
            }
            System.out.println("已成功将" + srcFile.getName() + " 复制到 " + destFile.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的关闭
            // note：此时可以并行写多个try-catch语句，且都会执行，因此try-catch本身相当于已经把异常处理了（抛出处理），因此不会影响下面的语句的执行
            try {
                if (fr != null) {
                    fr.close();
                    System.out.println("fr已关闭");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fw != null) {
                    fw.close();
                    System.out.println("fw已关闭");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
