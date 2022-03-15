package IOStreamTest;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - RandomAccessFile类测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/15 13:22
 * @node:
 *          1. RandomAccessFile直接继承与java.lang.Object类，实现了DataInput和DataOutput接口
 *          2. RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 *          3. RandomAccessFile 作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建
 *                              如果写出到的文件存在，则会对原有文件内容进行覆盖。 （默认情况下从头覆盖）
 *             RandomAccessFile 作为输出流时，如果写出的文件不存在，则会报错
 *          4. 可以通过相关操作，实现插入操作
 */
public class RandomAccessFileTest {
    @Test
    public void test01() {
        RandomAccessFile raf = null;
        RandomAccessFile raf2 = null;

        try {
            raf = new RandomAccessFile("photo1.jpg", "r");
            raf2 = new RandomAccessFile("photo4.jpg", "rw");

            byte[] data = new byte[1024];
            int len = -1;

            while ((len = raf.read(data)) != -1) {
                raf2.write(data, 0, len);
            }
            System.out.println(raf.getFD() + " 复制成功 -> " + raf2.getFD());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    // 实现对文本文件的覆盖
    public void test02() {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("randomTest.txt", "rw");

            //System.out.println("原文件内容：" + raf.readUTF());

            // 此时会对文件的内容直接进行覆盖，默认从头开始
            // 可以通过seek函数定位指针
            raf.seek(3); // 将指针调到角标为3的位置
            raf.write("123".getBytes(StandardCharsets.UTF_8));

            //System.out.println("现文件内容：" + raf.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* 使用RandomAccessFile实现数据的插入操作 */
    /* 可以将StringBuilder修改为ByteArrayOutputStream */
    @Test
    public void test03() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("randomTest.txt", "rw");

        raf.seek(3);

        // 方法一：使用StringBuilder,缓冲要插入数据之后的所有元素
        StringBuilder buffer = new StringBuilder((int) new File("randomTest.txt").length());
        byte[] data = new byte[50];
        int len = -1;
        // 保存要插入数据之后的所有元素
        while ((len = raf.read(data)) != -1) {
            buffer.append(new String(data, 0, len));
        }
        // 插入想要得到的数据
        raf.seek(3);
        raf.write("123".getBytes(StandardCharsets.UTF_8));
        // 将插入元素之前的数据追加到后面
        raf.write(buffer.toString().getBytes(StandardCharsets.UTF_8));


        // 方法二：使用StringBuilder,缓冲要插入数据之后的所有元素 （避免出现乱码）
        raf.seek(3);
        ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
        byte[] data2 = new byte[50];
        int len2 = -1;
        // 保存要插入数据之后的所有元素
        while ((len2 = raf.read(data2)) != -1) {
            buffer2.write(data2, 0, len2);
        }
        // 插入想要得到的数据
        raf.seek(3);
        raf.write("123".getBytes(StandardCharsets.UTF_8));

        // 将插入元素之前的数据追加到后面
        raf.write(buffer2.toByteArray());
    }
}
