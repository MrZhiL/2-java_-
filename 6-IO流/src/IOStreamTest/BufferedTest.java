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
