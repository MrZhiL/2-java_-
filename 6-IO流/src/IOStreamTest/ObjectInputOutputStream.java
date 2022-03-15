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
 *
 *          3. 要想将自定义类实现序列化机制，则需要满足以下要去，否则会报java.io.NotSerializableException异常：
 *              3.1 需要实现接口：Serializeable
 *              3.2 需要在当前类中提供一个全局常量：serialVersionUID（这是为了对不同的类型进行区分，以防止报错）
 *              3.3 除了保证当前类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的  （默认情况，基本数据类型是可序列化的）
 *
 *          4. note: ObjectInputStream和ObjectOutputStream不能序列化static和transient修饰的成员变量 （该变量不会被保存，而是使用null赋值）
 */
public class ObjectInputOutputStream {
    /* 序列化： */
    @Test
    public void ObjectOutputStreamTest() {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            oos.writeObject(new String("这是一个序列化的流，将其转换为二进制流"));
            oos.flush(); // 刷新操作，将内存的数据写入到文件中

            // 将自定义类序列化
            oos.writeObject(new Person("张三", 26));
            oos.flush();

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

            // 读取自定义类的序列化
            Object obj2 = ois.readObject();

            // 打印读取的的对象流
            // System.out.println(read);
            System.out.println((String) read); // 因为我们知道对象流的类型，因此可以直接进行强制转换
            System.out.println((Person) obj2);

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

/** 创建自定义类，如果想要该类实现序列化机制，则需要满足以下要求
 *  1. 需要实现接口：Serializeable
 *  2. 需要在当前类中提供一个全局常量：serialVersionUID（这是为了对不同的类型进行区分，以防止报错）
 *  3. 除了保证当前类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的
 *  4. 如果使用static and transiene修饰，则这些变量不会被序列化，在保存的时候将会使用null值进行赋值，因此读取出来的值也为null
 */
class Person implements Serializable{

    public static final long serialVersionUID = 9213959829L;

    private String name;
    // note: 如果使用static and transiene修饰，则这些变量不会被序列化，在保存的时候将会使用null值进行赋值，因此读取出来的值也为null
    // private static String name;
    // private transient String name;
    private int age;
    private Account acct; // 此时该类必须也是可靠序列化的，否则会报错

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Account implements Serializable{
    private float money;

    public static final long serialVersionUID = 92113959829L;
}
