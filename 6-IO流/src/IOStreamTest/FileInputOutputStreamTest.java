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
            File des = new File("photo2.jpg");

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
