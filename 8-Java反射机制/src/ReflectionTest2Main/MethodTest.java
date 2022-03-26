package ReflectionTest2Main;

import ReflectionTest2.Person;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @ClassName: ReflectionTest2Main
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/26 10:44
 * @node: 获取当前运行时类中的方法
 */
public class MethodTest {
    @Test
    public void test01() {
        Class clazz = Person.class;

        // 1.getMethods() : 获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] method = clazz.getMethods();
        for (Method m : method) {
            System.out.println(m);
        }

        System.out.println("\n*******************");
        // 2.getDeclaredMethods(): 获取当前运行时类的所有方法，包含private方法，不包含父类中声明的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }
}
