import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Target;
import java.util.*;

/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/17 9:10
 * @node:
 */
public class Java9Test {
    // java9特性五：钻石操作符的升级
    // 钻石操作符与匿名内部类在Java 8中不能共存，在java 9中可以
    @Test
    public void test2() {
        // 在java 8中如果使用匿名内部类，则必须指明操作的类型：Comparator<Object> com = new Comparator<Object> () {...}
        // 否则会：编译报错信息：Cannot use "<>" with anonymous inner classes
        // 在java 9中可以不用指明操作的类型，因为java 9中对其进行了升级
        Comparator<Object> com = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        // jdk7 中的新特性：类型推断
        ArrayList<String> list = new ArrayList<>();

        System.out.println('\'');
    }

    // java9特性六：try操作的升级
    // java8 之前资源关闭的操作，在finally中进行关闭
    public static void tryTest() {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(System.in);
            char[] cbuf = new char[20];
            int len;
            while ((len = reader.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len - 1);
                if ("e".equalsIgnoreCase(str) || "exit".equalsIgnoreCase(str)) break;
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // java 8中关闭资源的操作，此时会自动的关闭资源
    // java 8中，可以实现资源的自动关闭，但是要求执行后必须关闭的所有资源必须在try子句中初始化，否则编译不通过
    public static void tryTest2() {
        try (InputStreamReader reader = new InputStreamReader(System.in)) {
            char[] cbuf = new char[20];
            int len;
            while ((len = reader.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len - 1);
                if ("e".equalsIgnoreCase(str) || "exit".equalsIgnoreCase(str)) {
                    break;
                }
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // java 9中关闭资源的操作
    // java 9中，用资源语句编写try将更容易，我们可以在try子句中使用已经初始化过的资源，此时的资源是final的
    // 此时的资源属性是一个final常量，因此不可以修改
    public static void tryTest3() {
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try (reader; writer) {
            char[] cbuf = new char[20];
            int len;
            // read()方法在读取的时候会把回车符当成一个字符
            while ((len = reader.read(cbuf)) != -1) {
                System.out.println(len);
                String str = new String(cbuf, 0, len - 1);
                if ("e".equalsIgnoreCase(str) || "exit".equalsIgnoreCase(str)) {
                    break;
                }
                System.out.println(str + ", " + str.length() + ", " + str.equals("exit"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        Java9Test.tryTest();
//        Java9Test.tryTest2();
        Java9Test.tryTest3();
    }
}