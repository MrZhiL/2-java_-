package ReflectionTest;

import org.junit.Test;

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
    }
}
