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

  

1. Java的IO流共设计40多个类，实际上非常规则，都是从如下4个抽象基类派生的。
2. 由这四个类派生出来的子类名称都是以其父类名作为子类名的后缀。



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



