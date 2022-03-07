package FIleTest2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: FIleTest2
 * @Description: Java - file的练习
 * @author: zhilx (zhilx1997@sina.com)
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
