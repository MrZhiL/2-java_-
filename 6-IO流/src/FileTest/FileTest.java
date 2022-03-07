package src.FileTest;

import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * @ClassName: src.FileTest
 * @Description: Java - I/O流 - File测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/6 21:07
 * @node: 常用构造器：
 *          1. public File(String pathname)
 *            以pathname为路径创建File对象，可以是**绝对路径或者相对路径**，如果pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。
 *            - 绝对路径：是一个固定的路径，从盘符开始
 *            - 相对路径：是相对某个位置开始
 *
 *          2. public File(String parent, String chile)
 *            以parent为父路径，child为子路径创建File对象
 *
 *          3. public File(File paren, String child)
 *            根据一个父File对象和子文件路径创建File对象
 *
 *          4. 路径中的每级目录之间用一个**路径分隔符**隔开
 *              4.1 路径分隔符和系统有关：
 *                  - Windoes和DOS系统默认使用"\\"来表示
 *                  - UNIX和URL中使用"/"来表示
 *              4.2 Java程序支持跨平台运行，因此路径分隔符要慎用。为了解决这个隐患，File类提供了一个常量：**public static final Striing separator**。根据操作系统，动态的提供分隔符
 *
 *          5. File的常用方法
 */
public class FileTest {
    @Test
    public void test01() {
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\src\\FileTest\\hello.txt");
        System.out.println(file1);
        System.out.println(file2);
        System.out.println(file1.exists());
        System.out.println(file2.exists());

        File file3 = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分", "\\6-IO流\\src");
        System.out.println(file3);

        File file4 = new File(file3, "hello.txt");
        System.out.println(file4);

        System.out.println(System.getProperty("user.dir")); // 获取当前dir的路径
    }

    @Test
    /** File的常用方法
     * public String getAbsolutePath(); // 获取绝对路径
     * public String getPath(); // 获取路径
     * public String getName(); // 获取名称
     * public String getParent(); // 获取上层次文件目录路径，若无，返回null
     * public long length(); // 获取文件长度（即，字节数）。不能获取目录的长度
     * public long lastModified(); // 获取最后一次的修改时间，毫秒值
     *
     * public String[] list(); // 获取指定目录下的所有文件或者文件目录的名称数组 ： 获取文件名
     * public File[] listFiles(); // 获取指定目录下的所有文件或者文件目录的File数组 : 获取绝对路径
     */
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

    @Test
    public void test04() {
        // public boolean renameTo(File dest); // 把文件重命名为指定的文件路径
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\src\\FileTest\\hello.txt");

        boolean b = file1.renameTo(file2);
        System.out.println(b);
    }
}
