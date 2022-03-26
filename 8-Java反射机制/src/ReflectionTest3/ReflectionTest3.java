package ReflectionTest3;

import ReflectionTest2.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName: ReflectionTest3
 * @Description: Java - 调用运行时类的指定结构
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/26 17:31
 * @node: 调用运行时类中指定的结构：属性、方法、构造器
 */
public class ReflectionTest3 {
    @Test
    // 1. 获取运行时类中指定的属性
    public void testField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 创建Class类
        Class clazz = Person.class;

        // 创建运行时类的对象
        Person p = (Person) clazz.newInstance();
        Person p1 = (Person) clazz.newInstance();

        // 获取指定运行时类的属性，通常不采用此方法
        Field id = clazz.getField("id");

        // 获取指定运行时类的属性，通常采用此方法
        // getDeclaredField(String fieldName) : 获取运行时类中指定变量名的属性
        Field age = clazz.getDeclaredField("age");

        // 设置当前属性的值
        // set(): 参数1： 指明设置那个对象的属性； 参数2：将此属性值设置为多少
        id.set(p, 1001);

        // 如果想要操作非public的属性，需要使用setAccessible(true)设置为可访问的
        age.setAccessible(true);
        age.set(p1, 19);
        System.out.println(p.id);

        // 获取当前属性的值
        // get() : 参数1： 获取那个对象的当前属性值
        int pID = (int) id.get(p);
        int p1age = (int) age.get(p1);
        System.out.println(pID);
        System.out.println(p1age);
    }

    // 2. 获取运行时类中指定的方法
    @Test
    public void testMethod() throws Exception {
        Class<Person> clazz = Person.class;

        // 创建运行时类的对象
        Person p = clazz.getDeclaredConstructor().newInstance();

        // 1. 获取指定的某个方法
        Method info = clazz.getMethod("info"); // 获取public的方法

        // getDeclaredMethod(): 参数1：指明获取方法的名称; 参数2：指明获取的方法的形参列表
        Method info1 = clazz.getDeclaredMethod("show", String.class); //

        // 调用方法前需要保证是可访问的
        info1.setAccessible(true);

        // invoke(): 参数1： 方法的调用者； 参数2：给方法形参赋值的实参
        // invoke() 方法的返回值即为对应类装调用的方法的返回值。
        String str = (String) info1.invoke(p, "China");
        System.out.println(str);


        // 2. 调用静态方法
        Method showDec = clazz.getDeclaredMethod("showDesc");
        showDec.setAccessible(true);

        // 如果调用的运行时类中的方法没有返回值，则次invoke()返回null
        Object invoke = showDec.invoke(Person.class); // Person.class 可以换成null；因为前面showDec是根据Person来确定的
        System.out.println(invoke); // null
    }

    // 3. 获取运行时类中指定的构造器
    @Test
    public void testConstructor() throws Exception{
        Class<Person> clazz = Person.class;

        // 1. 获取指定的构造器
        // getConstructor() : 参数：指明构造器的参数列表
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);

        // 2. 保证此构造器是可访问的
        constructor.setAccessible(true);

        // 3. 调用此构造器创建运行时类的对象
        Person p = (Person) constructor.newInstance("Tom", 21);
        System.out.println(p);
    }
}