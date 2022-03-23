package ReflectionTest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: ClassLoaderTeset
 * @Description: Java - 了解类的加载器
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/23 21:23
 * @node:
 */
public class ClassLoaderTest {

    @Test
    public void test01() {
        // 1. 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader1); // jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc

        // 2. 调用系统类加载的getParent() 可以扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2); // jdk.internal.loader.ClassLoaders$PlatformClassLoader@7e0e6aa2

        // 3. 调用扩展类加载器的getParent()：无法获取引导类加载器
        // note: 引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);

        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4); // null; 可以说明String为扩展类
    }

    /** Properties: 用来读取配置文件
     * 使用两种方式来读取配置文件
     */
    @Test
    public void test02() {
        // 1. 创建配置文件类
        Properties pros = null;
        FileInputStream fis = null;
        try {
            pros = new Properties();

            // 2. 读取配置文件方式一：
            // 此时FileInputStream在Test下的默认路径为当前Module下
            fis = new FileInputStream("jdbc.properties");
            pros.load(fis);

            String name = pros.getProperty("name");
            String age = pros.getProperty("age");
            System.out.println("name = " + name + ", age = " + age); // name = 孙悟空, age = 999

            // 3. 读取配置文件方式二：使用反射
            // note: 此时使用反射机制时，默认读取文件的路径为当前Module/src目录下
            ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc2.properties");
            pros.load(resourceAsStream);
            name = pros.getProperty("name");
            age = pros.getProperty("age");
            System.out.println("name = " + name + ", age = " + age); // name = 猪八戒, age = 888

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
}
