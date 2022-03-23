package ReflectionTest;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName: ReflectionTest
 * @Description: Java - Reflect 反射测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/23 11:32
 * @node:
 */
public class ReflectionTest {

    //  1. 反射之前，对于Person的操作
    @Test
    public void test01() {
        // 创建Person类的对象
        Person p1 = new Person("Tom", 21);

        // 通过对象，调用其内部的属性、方法
        p1.setAge(22);
        System.out.println(p1.toString());

        p1.show();

        // note: 在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        // 比如：name、showNation()以及私有的构造器
    }

    // 2. 使用反射之后，对于Person的操作
    @Test
    public void test02() throws Exception{
        System.out.println("---------通过反射调用非私有结构------------");
        // 2.1 通过反射来创建Person类对象
        Class classPerson = Person.class;
        Constructor cons = classPerson.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 21);
        Person p = (Person) obj;
        p.setAge(22);
        System.out.println(p.toString());


        // 2.2 通过反射，调用对象指定的属性、方法
        // 调用属性getDeclaredField, 只能调用非私有化的属性和方法
        Field age = classPerson.getDeclaredField("age");
        age.set(p, 20);     // 调用Field的set()方法来设置指定属性
        System.out.println(p.toString());

        // 调用方法getDeclaredMethod：只能调用非私有化的方法
        Method method = classPerson.getDeclaredMethod("show");
        method.invoke(p);   // 通过调用Method的invoke()方法来调用类的方法

        System.out.println("---------通过反射调用私有结构------------");

        // 2.3 通过反射，可以调用Person类的私有结构：如私有的构造器、方法和属性
        // 调用私有的构造器
        Constructor const1 = classPerson.getDeclaredConstructor(String.class);
        const1.setAccessible(true);    // 需要设置为可访问的, 否则不可访问私有结构
        Person person2 = (Person) const1.newInstance("Jack");
        System.out.println(person2);

        // 调用私有的属性
        Field name = classPerson.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person2, "Tom");   // 通过调用set()方法来修改私有属性
        System.out.println(person2);

        // 调用私有的方法
        Method showNation = classPerson.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(person2,"中国"); // 相当于 String nation = person.showNation("中国");
        System.out.println(nation);

    }

    /** java.lang.Class类的理解
     * 1. 类的加载过程：程序在经过javac.exe命令以后，会生成一个或多个字节码文件（.class结尾），接着我们使用java.exe命令对某个字节码文件进行解释运行。
     * 相当于将某个字节码文件加载到内存中，此过程就称为类的加载。
     *
     * 2. 加载到内存中的类，我们就称为运行时类，此运行时类就作为Class的一个实例。
     *
     * 3. 换句话说，Class的实例就对应这一个运行时类。
     *
     * 4. 加载到内准中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类
     */
    @Test
    /* 获取CLass的实例的方式 */
    public void test03() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性：.class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1); // class ReflectionTest.Person

        // 方式二：通过运行时类的对象
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2); // class ReflectionTest.Person

        // 方式三：调用Class的静态方法：forName(String classPath)，此时需要指明类的详细路径
        Class clazz3 = Class.forName("ReflectionTest.Person");
        System.out.println(clazz3); // class ReflectionTest.Person
        System.out.println(Class.forName("java.lang.String")); // class java.lang.String

        // 加载到内准中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类
        System.out.println(clazz1 == clazz2); // true
        System.out.println(clazz1 == clazz3); // true
        System.out.println(clazz2 == clazz3); // true

        // 方式四：使用类的加载器： ClassLoader （了解）
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("ReflectionTest.Person");
        System.out.println(clazz4); // class ReflectionTest.Person
        System.out.println(clazz4 == clazz1); // true
    }

    // Class实例可以是哪些结构的说明：
    @Test
    public void test04() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        System.out.println(c10);

        // 只要数组元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11); // true
    }
}
