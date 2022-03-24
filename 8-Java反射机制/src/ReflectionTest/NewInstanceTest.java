package ReflectionTest;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @ClassName: NewInstanceTest
 * @Description: Java - 创建运行时类的对象
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/24 19:59
 * @node: 通过反射创建对应的运行时类的对象
 */
public class NewInstanceTest {

    // newInstance() : 调用此方法，创建对应的运行时类的对象。 该方法内部调用了运行时类的空参构造器
    // 因此，如果运行时类中没有空参构造器，则会抛出异常；且不可以为private权限，如果为private则会报错
    // 要想此方法可以正常的创建运行时类的对象，要求：
    // 1. 运行时类必须提供空参的构造器；
    // 2. 空参的构造器的访问权限得够。通常设置为public
    //
    // note: 在javabean中要求提供一个public的空参构造器，原因：
    //       1. 便于通过反射，创建运行时类的对象
    //       2. 便于子类继承此运行时类，默认调用super()时，保证父类有此构造器
    @Test
    public void test01() {
        Class<Person> clazz = Person.class;
        Person person = null;
        try {
            person = clazz.getDeclaredConstructor().newInstance();
            System.out.println(person);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // 体会反射的动态性
    @Test
    public void test02() {
        int num;
        String str = "";
        for (int i = 0; i < 10; ++i) {
            num = new Random().nextInt(3); // 生产 [0,3)以内的随机数
            switch (num) {
                case 0:
                    // 生成java.util.Date的运行时类的对象
                    str = "java.util.Date";
                    break;
                case 1:
                    // 生成java.Object的运行时类的对象
                    str = "java.lang.Object";
                    break;
                case 2:
                    // 生成Person类
                    str = "ReflectionTest.Person";
                    break;
                default:
                    break;
            }
            Object obj = null;
            try {
                obj = getInstanct(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(obj);
        }
    }

    // 创建一个指定类的对象。classPath: 指定类的全类名
    public Object getInstanct(String classPath) throws ClassNotFoundException, NoSuchMethodException, Exception {
        Class clazz = Class.forName(classPath);

        Object obj = clazz.getDeclaredConstructor().newInstance();
        return obj;
    }
}
