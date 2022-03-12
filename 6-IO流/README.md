# 十二 IO流

1. File类的使用
2. IO流原理及流的应用
3. 节点流（或文件流）
4. 缓冲流
5. 转换流
6. 标准输入、输出流
7. 打印流
8. 数据流
9. 对象流
10. 随机存取文件流
11. NIO.2中Path、Paths、Files类的使用



## 1. File类的使用

- java.io.File类：**文件和文件目录路径**的抽象表示形式，与平台无关

- File能新建、删除、重命名文件和目录，但File不能访问文件内容本身。

  如果需要访问文件内容本身，**则需要使用输入/输出流（I/O流）**

- **想要在Java程序中表示一个真是存在的文件或目录，那么必须有一个File对象，但是Java程序中的一个File对象，可能没有一个真是存在的文件或目录。**
- File对象可以作为参数传递给流的构造器。

### 1. 常用构造器

- public File(String pathname)

  以pathname为路径创建File对象，可以是**绝对路径或者相对路径**，如果pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。

    - 绝对路径：是一个固定的路径，从盘符开始
    - 相对路径：是相对某个位置开始

- public File(String parent, String chile)

  以parent为父路径，child为子路径创建File对象

- public File(File paren, String child)

  根据一个父File对象和子文件路径创建File对象

- 路径中的每级目录之间用一个**路径分隔符**隔开

- 路径分隔符和系统有关：

    - Windoes和DOS系统默认使用"\\"来表示
    - UNIX和URL中使用"/"来表示

- Java程序支持跨平台运行，因此路径分隔符要慎用。为了解决这个隐患，File类提供了一个常量：**public static final Striing separator**。根据操作系统，动态的提供分隔符，例如：

  ```java
  File file1 = new File("d:\\JavaProject\\hello.txt");
  File file2 = new File("d:" + File.separator + "JavaProject" + File.separator + "hello.txt");
  File file3 = new File("d:/javaProject")
  ```



### 2. File的常用方法

- File类的获取功能

  ```java
  public String getAbsolutePath(); // 获取绝对路径
  public String getPath(); // 获取路径
  public String getName(); // 获取名称
  public String getParent(); // 获取上层次文件目录路径，若无，返回null
  public long length(); // 获取文件长度（即，字节数）。不能获取目录的长度
  public long lastModified(); // 获取最后一次的修改时间，毫秒值
  
  public String[] list(); // 获取指定目录下的所有文件或者文件目录的名称数组  ： 获取文件名，且可以获取到隐藏目录和隐藏文件
  public File[] listFiles(); // 获取指定目录下的所有文件或者文件目录的File数组 ： 获取绝对路径，且可以获取到隐藏目录和隐藏文件
  ```

- File类的重命名功能

  note: 比如file1.renameTo(file2)：**要想保证返回true，需要file1在硬盘中是存在的，且file2不能存在于硬盘中。**

  当调用成功后，会将file1**移动**到file2所指定的位置。

  ```java
  public boolean renameTo(File dest); // 把文件重命名为指定的文件路径
  
  public void test04() {
      File file1 = new File("hello.txt");
      File file2 = new File("D:\\IO\\hi.txt");
      
      boolean remameTo = file1.renameTo(file2);
      System.out.println(renameTo);
  }
  ```



- File类的判断功能

    - public boolean isDirectory() : 判断是否为文件目录
    - public boolean isFile() : 判断是否为文件
    - public boolean exists() : 判断文件是否存在
    - public boolean canRead() : 判断文件是否可读
    - public boolean canWrite() : 判断是否可写
    - public boolean isHidden() :  判断文件是否为隐藏文件



- File类的创建功能：

    - public boolean createNewFile(): 创建文件。若文件存在，则不创建，返回false
    - public boolean mkdir() : 创建文件目录。如果此文件目录存在，则不创建；如果此文件目录的上层目录也不存在，则不创建
    - public boolean mkdirs() : 创建文件目录，如果上层文件目录不存在，一并创建
    - **注意事项**：**如果要创建的文件或者文件目录没有写盘符路径，则默认在项目路径下创建**。

- File类的删除功能

    - public boolean delete() : 删除文件或者文件夹

      **删除注意事项：：**

        - Java中的删除不走**回收站**
        - 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录

  ```java
  @Test
      // File的常用方法
      public void test02() {
          File file1 = new File("hello.txt");
          File file2 = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\src\\FileTest\\hello.txt");
  
          System.out.println(file1.getAbsoluteFile());
          System.out.println(file1.getParent()); // null
          System.out.println(file1.getName());   // hello.txt
          System.out.println(file1.getParent()); // null
          System.out.println(file1.length());
          System.out.println(file1.lastModified());
  
          System.out.println("-----------------------");
          System.out.println(file2.getAbsoluteFile());
          System.out.println(file2.getParent());
          System.out.println(file2.getName());
          System.out.println(file2.getParent());
          System.out.println(file2.length());
          System.out.println(new Date(file2.lastModified()));
      }
  
      @Test
      public void test03() {
          // public String[] list(); // 获取指定目录下的所有文件或者文件目录的名称数组
          // public File[] listFiles(); // 获取指定目录下的所有文件或者文件目录的File数组
          File file = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分");
  
          String[] list = file.list();
          for (String l : list) {
              System.out.println(l);
          }
  
          System.out.println("---------------");
          File[] files = file.listFiles();
          for (File f : files) {
              System.out.println(f);
          }
      }
  
  // test03()输出
  .git
  .gitignore
  .idea
  0-学习文档
  1-多线程
  10-Java9&10&11新特性
  2-Java常用类
  2-Java高级部分.iml
  3-枚举类&注解
  4-Java集合
  5-泛型
  6-IO流
  7-网络编程
  8-Java反射机制
  9-Java8的其它新特性
  IDEA快捷键.md
  jdbc.properties
  LICENSE
  out
  README.md
  ---------------
  D:\Program Files (x86)\JavaProject\2-Java高级部分\.git
  D:\Program Files (x86)\JavaProject\2-Java高级部分\.gitignore
  D:\Program Files (x86)\JavaProject\2-Java高级部分\.idea
  D:\Program Files (x86)\JavaProject\2-Java高级部分\0-学习文档
  D:\Program Files (x86)\JavaProject\2-Java高级部分\1-多线程
  D:\Program Files (x86)\JavaProject\2-Java高级部分\10-Java9&10&11新特性
  D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类
  D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java高级部分.iml
  D:\Program Files (x86)\JavaProject\2-Java高级部分\3-枚举类&注解
  D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合
  D:\Program Files (x86)\JavaProject\2-Java高级部分\5-泛型
  D:\Program Files (x86)\JavaProject\2-Java高级部分\6-IO流
  D:\Program Files (x86)\JavaProject\2-Java高级部分\7-网络编程
  D:\Program Files (x86)\JavaProject\2-Java高级部分\8-Java反射机制
  D:\Program Files (x86)\JavaProject\2-Java高级部分\9-Java8的其它新特性
  D:\Program Files (x86)\JavaProject\2-Java高级部分\IDEA快捷键.md
  D:\Program Files (x86)\JavaProject\2-Java高级部分\jdbc.properties
  D:\Program Files (x86)\JavaProject\2-Java高级部分\LICENSE
  D:\Program Files (x86)\JavaProject\2-Java高级部分\out
  D:\Program Files (x86)\JavaProject\2-Java高级部分\README.md
  
  Process finished with exit code 0
  
  ```



### 3. 练习

1. 利用File构造器，new一个文件目录file
    1. 在其中创建多个文件和目录
    2. 编写方法，实现删除file中指定文件的操作
2. 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
3. 遍历指定目录所有文件名称，包括子文件目录中的文件。
    - 扩展1：并计算指定目录占用空间的大小
    - 扩展2：删除指定文件目录及其下的所有文件

```java
package FIleTest2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: FIleTest2
 * @Description: Java - file的练习
 * @version: v1.0
 * @data: 2022/3/7 11:25
 * @node:
 *          1. 利用File构造器，new一个文件目录file
 *             1.1. 在其中创建多个文件和目录
 *             1.2. 编写方法，实现删除file中指定文件的操作
 *          2. 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
 *          3. 遍历指定目录所有文件名称，包括子文件目录中的文件。
 *             - 扩展1：并计算指定目录占用空间的大小
 *             - 扩展2：删除指定文件目录及其下的所有文件
 */
public class FileTest {
    @Test
    public void test01() throws IOException {
        File file = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\src\\FIleTest2\\test\\hello.txt");

        // 1.1 在指定的目录中创建多个文件和目录
        File destFile = new File(file.getParent(), "hihi.txt");
        boolean newFile = destFile.createNewFile();
        showCreateInfo(newFile, destFile);

        newFile = new File(file.getParent(), "1.jpg").createNewFile();
        showCreateInfo(newFile, new File(file.getParent(), "1.jpg"));

        newFile = new File(file.getParent(), "2.jpg").createNewFile();
        showCreateInfo(newFile, new File(file.getParent(), "2.jpg"));

        newFile = new File(file.getParent(), "3.jpg").createNewFile();
        showCreateInfo(newFile, new File(file.getParent(), "3.jpg"));

        newFile = new File(file.getParent(), "subTest1").mkdir();
        showCreateInfo(newFile, new File(file.getParent(), "subTest1"));
        newFile = new File(file.getParent(), "subTest2").mkdir();
        showCreateInfo(newFile, new File(file.getParent(), "subTest2"));
        newFile = new File(file.getParent(), "subTest3").mkdir();
        showCreateInfo(newFile, new File(file.getParent(), "subTest3"));

        // 1.2 编写方法，实现删除file中指定文件的操作
        File file2 = new File(destFile.getParent(),"hihi.txt");
        boolean delete = file2.delete();
        if (delete) {
            System.out.println(file2.getName() + " 删除成功");
        }

        // 2. 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
        String[] list = new File(destFile.getParent()).list();
        for (String str : list) {
            if (str.contains(".jpg")) {
                System.out.println(str);
            }
        }
    }

    @Test
    public void test02() {
        // 3. 遍历指定目录所有文件名称，包括子文件目录中的文件。
        File destFile = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\src\\FIleTest2\\test");
        System.out.println(showDirectory(destFile));
    }

    public void showCreateInfo(boolean newFile, File destFile) {
        if (newFile) {
            System.out.println(destFile.getName() + " 创建成功");
        }
    }

    public int showDirectory(File file) {
        int DirSize = 0;
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.print("|----");
                DirSize += showDirectory(f); // 计算指定目录占用空间的大小
            }
        } else {
            DirSize += file.length();
        }

        // file.delete(); // 递归删除目录

        return DirSize; // 计算指定目录占用空间的大小
    }
}

```





## 2. IO流原理及流的分类

### 1. Java IO原理

- I/O是Input/Output的缩写，I/O技术使非常实用的技术，**用于处理设备之间的数据传输**。如读/写文件，网络通讯等。

- Java程序中，对于数据的输入/输出操作以“流(stream)” 的方式进行。

- java.io包下提供了各种“流”类和接口，用以获取不同中了的数据，并通过**标准的方法**输入或输出数据。



#### 1.1 流的分类：

- 按操作**数据单位**不同分为：**字节流(8bit)，字符流(16bit)**

- 按数据流的**流向**不同分为：**输入流，输出流**

- 按流的**角色**的不同分为：**节点流，处理流**

- | [抽象基类] | 字节流       | 字符流 |
    | ---------- | ------------ | ------ |
  | 输入流     | InputStream  | Reader |
  | 输出流     | OutputStream | Writer |

**note: IO字符流不能对图片进行处理。**




- 输入流：

    1. Java的IO流共设计40多个类，实际上非常规则，都是从如下4个抽象基类派生的。
    2. 由这四个类派生出来的子类名称都是以其父类名作为子类名的后缀。

  note: 在处理IO流的时候，不建议使用throws抛出异常，因为此时如果抛出异常，会导致文件流无法关闭：

    - read()的理解：返回读入的一个字符，如果达到文件末尾，则返回-1

        - 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally进行处理
          否则会报FileNotFoundException

    - 输出流：从内存中写出数据到硬盘中

        - **输出操作，对应的File可以不存在，并不会报异常**：
            - 对应的文件如果不存在，在输出的过程中，会自动创建此文件
            - 对应的文件如果存在：
                - 如果流使用的构造器是：FileWriter(file, false) / FileWriter(file) : 此时会对原有的文件进行覆盖
                - 如果流使用的构造器是：FileWriter(file, true) : 不会对原有的文件进行覆盖，而是在后面追加

#### 1.2 I/O流体系：

| 分类       | 字节输入流              | 字节输出流             | 字符输入流            | 字符输出流             |
| ---------- | ----------------------- | ---------------------- | --------------------- | ---------------------- |
| 抽象基类   | **InputStream**         | **OutputStream**       | **Reader**            | **Writer**             |
| 访问文件   | **FileInputStream**     | **FileOutputStream**   | **FileReader**        | **FileWriter**         |
| 访问数组   | ByteArrayInputStream    | ByteArrayOutputStream  | CharArrayReader       | CharArrayWriter        |
| 访问管道   | PipedInputStream        | PipedOutputStream      | PipedReader           | PipedWriter            |
| 访问字符串 |                         |                        | StringReader          | StringWriter           |
| 缓冲流     | **BufferedInputStream** | **PipedOutputStream**  | **BufferedReader**    | **BufferedWriter**     |
| 转换流     |                         |                        | **InputStreamReader** | **OutputStreamWriter** |
| 对象流     | **ObjectInutStream**    | **ObjectOutputStream** |                       |                        |
|            | FilterInputStream       | FilterOutputStream     | FilterReader          | Filterwriter           |
| 打印流     |                         | PrintStream            |                       | PrintWriter            |
| 推回输入流 | PUshbackInputStream     |                        | PushbackReader        |                        |
| 特殊流     | DateInputStream         | DateOutputStream       |                       |                        |



##### 1. 使用FileReader读入数据，并输出到控制台上

```java
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

    @Test
    // 对test01()中的read()方法的改进，将hello.txt中的内容读取并输出到控制台中
    public void test02() {
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
}

```



##### 2. 输出流：将内存中的数据写出到文件中

```java
public void testFileWriter01() {
    FileWriter fw = null;

    try {
        // 1. 提供File类的对象，指明写出到的文件
        File file = new File("hello1.txt");

        // 2. 提供FileWriter的对象，用于数据的写出
        // 将append设置为false，从而为覆盖模式
        // new FileWriter(file) : 此时调用的时候默认为覆盖模式，会从头开始写
        fw = new FileWriter(file, false); // 此时如果文件不存在，则会自动创建

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
        // File srcFile = new File("hello.txt");
        // File destFile = new File("hello1.txt");

        // note: 不能使用字符流来处理图片等字节数据
        File srcFile = new File("photo.jpg");
        File destFile = new File("photo1.jpg");

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
```



### 2. 使用FileInputStream不能读取文本文件的测试

1. 对于文本文件(.txt, .java, .c, .cpp, .md(Markdown))，要使用字符流来处理

2. 对于非文本文件(.doc, .jpg, .mp4, .avi, .ppt, ...)，使用字节流处理



```java
package IOStreamTest;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 测试FileInputStream和FileOutputStream的使用
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/10 15:08
 * @node:   测试FileInputStream和FileOutputStream的使用
 *
 *          结论：
 *              1. 对于文本文件(.txt, .java, .c, .cpp, .md(Markdown))，要使用字符流来处理
 *              2. 对于非文本文件(.doc, .jpg, .mp4, .avi, .ppt, ...)，使用字节流处理
 */
public class FileInputOutputStreamTest {
    @Test
    /** 使用字节流FileInputStream处理文本文件可能会出现乱码 */
    public void FileInputStreamTest() {
        FileInputStream fis = null;
        try {
//            File file = new File("hello.txt");
            File file = new File("read.md");
            // File file = new File("photo.jpg");
            fis = new FileInputStream(file);

            // 对于文本文件(.txt, .java, .c, .cpp, .md(Markdown))，要使用字符流来处理
            byte[] bytes = new byte[10];
            int len = 0; // 记录每次读取文件的长度
            while ((len = fis.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    /** 使用字节流FileInputStream处理非文本文件 */
    public void FileInputOutputStreamTest() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File src = new File("photo.jpg");
            File des = new File("photo1.jpg");

            fis = new FileInputStream(src);
            fos = new FileOutputStream(des);

            // 对于文本文件(.txt, .java, .c, .cpp, .md(Markdown))，要使用字符流来处理
            // 对于非文本文件(.doc, .jpg, .mp4, .avi, .ppt, ...)，使用字节流处理
            byte[] bytes = new byte[100];
            int len = 0; // 记录每次读取文件的长度
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            System.out.println(src.getName() + " 成功写入到 " + des.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```





### 3. 缓冲流（处理流之一）

1. 缓冲流：BufferedInputStream、BufferedOutputStream、BufferedReader、BufferedWriter

2. 作用：提高流的读取和写入的速度

   提高读写速度的原因：内部提高了一个缓冲区。

3. 处理流，就是“套接”在已有的流的基础上。

4. ```java
   // 使用缓冲流实现非文本文件的赋值
   public void BufferedTest() {
       // 1. 创建File文件
       File srcFile = new File("photo.jpg");
       File destFile = new File("photo3.jpg");
   
       // 2. 创建流
       // 2.1 创建节点流
       FileInputStream fis = null;
       FileOutputStream fos = null;
   
       // 2.2 创建缓冲流
       BufferedInputStream bis = null;
       BufferedOutputStream bos = null;
   
       // 3. 进行文件的复制
       try {
           fis = new FileInputStream(srcFile);
           fos = new FileOutputStream(destFile);
   
           bis = new BufferedInputStream(fis);
           bos = new BufferedOutputStream(fos);
   
           byte[] buffer = new byte[1024];
           int len = 0;
   
           while ((len = bis.read(buffer)) != -1) {
               bos.write(buffer, 0, len);
           }
           System.out.println(srcFile.getName() + " 成功复制到 " + destFile.getName());
   
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           // 4. 关闭流，要求：先关闭缓冲流，再关闭节点流（因为需要先关闭内层的，再关闭外层的）
           if (bos != null) {
               try {
                   bos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
   
           if (bis != null) {
               try {
                   bos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
   
           if (fos != null) {
               try {
                   bos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
   
           if (bis != null) {
               try {
                   bos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }
   ```



### 4. 缓冲流实现文本的复制

```java
package IOStreamTest;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 缓冲流的使用（处理流之一）
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/11 21:33
 * @node:
 *          一、流的分类
 *              1。 操作数据单位：字节流、字符流
 *              2. 数据的流向：输入流、输出流
 *              3. 流的角色： 节点流、处理流
 *
 *          二、流的体系结构
 *              抽象基类            节点流（或文件流）                                   缓冲流（处理流）
 *              InputStream        FileInputStream  (read(byte[] buffer))           BufferedInputStream  (read(byte[] buffer))
 *              OutputStream       FileOutputStream (write(byte[] buffer, 0, len))  BufferedOutputStream (write(byte[] buffer, 0, len)) / flush()
 *              Reader             FileReader  (read(char[] cbuf))                  BufferedReader       (read(char[] cbuf) / readLine() )
 *              Writer             FileWriter  (write(char[] cbuf, 0, len))         BufferedWriter       (write(char[] cbuf, 0, len)) / flush()
 *
 *          三、 缓冲流的作用： 提高流的读取和写入的速度。
 */
public class BufferedTest {
    @Test
    // 使用缓冲流实现非文本文件的赋值
    public void BufferedTest() {
        // 1. 创建File文件
        File srcFile = new File("photo.jpg");
        File destFile = new File("photo3.jpg");

        // 2. 创建流
        // 2.1 创建节点流
        FileInputStream fis = null;
        FileOutputStream fos = null;

        // 2.2 创建缓冲流
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        // 3. 进行文件的复制
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                // bos.flush(); // flush()方法用于刷新缓冲区
            }
            System.out.println(srcFile.getName() + " 成功复制到 " + destFile.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流，要求：先关闭缓冲流，再关闭节点流（因为需要先关闭内层的，再关闭外层的）
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void copyFiletest() {
        long start = System.currentTimeMillis();

        copyFileBufferedTest("photo.jpg", "photo3.jpg");

        long end = System.currentTimeMillis();
        System.out.println("复制文件花费的时间：" + (end - start) + "ms"); // 36 -> 20ms
    }

    // 使用缓冲流实现非文本文件的赋值
    public void copyFileBufferedTest(String srcfile, String destfile) {
        // 1. 创建File文件
        File srcFile = new File(srcfile);
        File destFile = new File(destfile);

        // 2. 创建流
        // 2.1 创建节点流
        FileInputStream fis = null;
        FileOutputStream fos = null;

        // 2.2 创建缓冲流
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        // 3. 进行文件的复制
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                // bos.flush(); // flush()方法用于刷新缓冲区
            }
            System.out.println(srcFile.getName() + " 成功复制到 " + destFile.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流，要求：先关闭缓冲流，再关闭节点流（因为需要先关闭内层的，再关闭外层的）
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 使用BufferedReader和BufferedWriter实现文本的复制操作
    @Test
    public void BufferedReaderBufferedWriterTest() {
        BufferedReader bis = null;
        BufferedWriter bos = null;

        try {
            bis = new BufferedReader(new FileReader(new File("read.md")));
            bos = new BufferedWriter(new FileWriter(new File("read1.md")));

            // 方式一：使用char[]数组进行赋值
//            char[] cbuf = new char[100];
//            int len = 0;
//            while ((len = bis.read(cbuf)) != -1) {
//                bos.write(cbuf, 0, len);
//            }

            // 方式二：使用readLine()方法进行读取
            String str = "";
            while ((str = bis.readLine()) != null) {
                // 1. 手动在最后加入换行符
                // bos.write(str + "\n");

                // 2. 调用newLine()方法进行换行
                bos.write(str);
                bos.newLine();
            }


            System.out.println("文件复制成功!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```



### 5. IO流测试：使用字符流获取文本中每个字符出现的次数，并把数据写入到文件中

```java
package IOStreamTest;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 获取文本文件中每个字符出现的次数，并把数据写入到文件中
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/11 22:40
 * @node: 思路：
 *          1. 遍历文本中的每一个字符
 *          2. 统计字符出现的此时，将其存在Map中进行统计
 */
public class WordCountTest {
    public static void main(String[] args) {
        WordCountTest wordCountTest = new WordCountTest();
        String srcStr = "D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\read.md";
        String destStr = "D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\readWordCount.txt";
        wordCountTest.fileWordCountTest(srcStr, destStr);
//        System.out.println(new File("read.md").getAbsolutePath()); // D:\Program Files (x86)\JavaProject\2-Java高级部分\read.md
    }

    public void fileWordCountTest(String srcfile, String destfile) {
        File srcFile = new File(srcfile);
        File destFile = new File(destfile);

        System.out.println(srcFile.getName() + " exists ? " + srcFile.exists());
        System.out.println(destFile.getName() + " exists ? " + destFile.exists());

        FileReader fr = null;
        BufferedWriter bw = null;

        Map<Character, Integer> wordMap = new HashMap<>();

        try {

            fr = new FileReader(srcFile);
            bw = new BufferedWriter(new FileWriter(destFile));

            int data = 0;
            Character ch;

            // 1. 读取文件中的字符，并写入到Map中
            while ((data = fr.read()) != -1) {
                ch = (char)data;
                if (!wordMap.containsKey(ch)) {
                    wordMap.put(ch, 0);
                } else {
                    wordMap.put(ch, wordMap.get(ch) + 1);
                }
            }

            // 2. 将Map中的字符写入的destFile中
            wordWritetoFile(wordMap, bw);

            System.out.println("字符统计成功!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean wordWritetoFile(Map<Character, Integer> wordMap, BufferedWriter bw) throws IOException {
        if (wordMap.isEmpty()) {
            System.out.println("map为空!");
            return true;
        }

        Set<Map.Entry<Character, Integer>> entries = wordMap.entrySet();
        for (var val : entries) {
            Character ch = val.getKey();
            switch (ch) {
                case '\n' :
                    bw.write("换行符 : " + val.getValue());
                    break;
                case ' ' :
                    bw.write("空格 : " + val.getValue());
                    break;
                case '\t' :
                    bw.write("制表符 : " + val.getValue());
                    break;
                case '\r' :
                    bw.write("回车符 : " + val.getValue());
                    break;
                default:
                    bw.write(val.getKey()  + " : " + val.getValue());
                    break;
            }
            bw.newLine();
        }

        if (bw != null) {
            bw.close();
        }
        return true;
    }
}
```





### 6. 转换流（处理流之二）

#### 6.1 转换率概述与InputStreamReader的使用

1. 转换流提供了在字节流和字符流之间的转换

2. Java API提供了两个转换流：（属于字符流）

    - **InputStreamReader : 将inputStream转化为Reader**（将一个字节的输入流转换为字符的输入流）

      ```java
      InputStreamReader isr = new InputStreamReader(new FileInputStream("hello.txt")); // 此时使用系统默认的字符集
      
      /* InputStreamReader()方法的参数2可以指定字符集，但是具体使用那个字符集，取决于要读取的文件在保存的时候使用的字符集 */
      InputStreamReader isr = new InputStreamReader(new FileInputStream("hello.txt"), "UFT-8"); // 此时使用指定的字符集：UTF-8
      ```

    - **OutputStreamWriter : 将Writer转换为OutputStream**（将一个字符的输出流转换为字节的输出流）

3. 字节流中的数据都是字符时，转化成字符流操作更高效

4. 很多时候我们使用转换率来处理文件乱码的问题。实现编码和解码的功能。

    - 解码：字节、字节数组 ---> 字符数组、字符串
    - 解码：字符数组、字符串 ---> 字节、字节数组

5. ![image-20220312162823005.png](README.assets/image-20220312162823005.png)


### 7. 字符集

1. ASCII : 美国标准信息交换码，用一个字节的7位就可以表示
2. ISO8859-1 : 拉丁码表，为欧洲码表。用一个字节的8位来表示
3. GB2312 : 中国的中文编码表，最多两个字节，可以编码所有字符
4. GBK : 中国的中文编码表升级版，融合了更多的中文文字符号（包括繁体字），最多使用两个字节进行编码

   (note : 在计算机底层进行内存读取时，如果一个字节的首位为0，则表示存储了一个字节的信息；如果第一个字节首位为1，则表示使用两个字节进行了存储，需要连续读取两个字节。)
5. Unicode : 国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符吗。所有的文字都是用两个字节来表示。
6. UTF-8 : 变长的编码方式，可用1-4个字节来表示一个字符。

- **补充**：
- Unicode不完美，这里就有三个问题：
  - 一个是我们已经知道英文字母只用一个字节就可以表示，
  - 第二个是如何才能区别Unicode和ASCII？计算机怎么知道两个字节表示一个字符，而不是分别表示两个符号呢？
  - 第三个是如果和GBK等双字节编码方式一样，用最高位是1或0表示两个字节和一个字节，就少了很多值无法用于表示字符，导致不能表示所有字符。
  - 因此导致Unicode在很长一段时间内无法推广，直到互联网的出现。
  
- 面向传输的众多UTF(UCS Transfer Format)标准出现了，顾名思义，UTF-8就是每次传输8个位传输数据，而UTF-16就是每次使用16个位传输。
  这是为传输而设计的编码，并使编码无国界，这样就可以显式全世界上所有文化的字符了。

- Unicode只是定义了一个庞大的、全球通用的字符集，并为每个字符规定了唯一确定的编号，具体存储成什么样的字节流，取决于字符编码方案。
  推荐的Unicode编码是UTF-8和UTF-16。

- ![img.png](README.assets/img.png)

- ![img_1.png](README.assets/img_1.png)


### 8. 标准输入、输出流

- System.in 和 System.out 分别代表了系统标准的输入和输出设备
- 默认输入设备是：键盘；输出设备是：显示器
- System.in的类型为InputStream
- System.out的类型是PrintStream，其是OutputStream的子类
  FilterOutputStream的子类
- 重定向：通过System类的setln, setOut方法对默认设备进行改变。
  - `public static void setIn(InputStream in)`
  - `public static void setOut(PrintStream out)`

- 练习：从键盘输入字符串，要求将读取到的整行字符串转化成大写输出，然后继续进行输入操作。直到输入"e"或“exit"时，退出程序.
```java
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
```

- 练习二：Contain the methods for reading int, double, float, boolean, short, byte and string values from the keyboard
```java
package IOStreamTest;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 标准输入输出测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/12 19:08
 * @node:
 *          MyInput.java: Contain the methods for reading int, double, float, boolean,
 *                        short, byte and string values from the keyboard
 */
public class MyInput {
    // Read a String from the keyboard
    public static String readString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Declare and initialize the String
        String str = "";

        // Get the string from the keyboard
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the String obtained from the keyboard
        return str;
    }

    // Read a int from the keyboard
    public static int readInt() {return Integer.parseInt(readString()); }

    // Read a double from the keyboard
    public static double readDouble() {return Double.parseDouble(readString()); }

    // Read a float from the keyboard
    public static float readFloat() {return Float.parseFloat(readString()); }

    // Read a short from the keyboard
    public static short readShort() {return Short.parseShort(readString()); }

    // Read a byte from the keyboard
    public static byte readByte() {return  Byte.parseByte(readString()); }

    // Read a boolean from the keyboard
    public static boolean readBoolean() {return Boolean.getBoolean(readString()); }
}
 
```

