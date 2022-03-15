package IOStreamTest;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 对象流的使用
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/15 12:19
 * @node: 对象流的使用
 *          1. ObjectInputStream 和 ObjectOutputStream
 *          2. 作用：用于存储和读取**基本数据类型**或**对象**的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源还原出来。
 *          3.
 */
public class ObjectInputOutputStream {
    /* 序列化： */
    @Test
    public void ObjectOutputStreamTest() {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            oos.writeObject(new String("这是一个序列化的流，将其转换为二进制流"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** 反序列化： 将磁盘文件中的对象还原为内存中的一个java对象
    *   需要使用ObjectInputStream来实现
    */
    @Test
    public void ObjectInputStreamTest() {
        ObjectInputStream ois = null;
        try {
            // 1. 创建对象
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            // 2. 进行读取
            Object read = ois.readObject();

            // 打印读取的的对象流
            System.out.println(read);
            System.out.println((String) read); // 因为我们知道对象流的类型，因此可以直接进行强制转换

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3. 关闭输入流
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
