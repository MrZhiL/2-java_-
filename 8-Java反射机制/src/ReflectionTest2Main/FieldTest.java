package ReflectionTest2Main;

import ReflectionTest2.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @ClassName: FieldTest
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/25 10:00
 * @node: 获取当前运行类的属性结构
 */
public class FieldTest {
    @Test
    public void test01() {
        Class clazz = Person.class;

        // 获取属性结构
        // getFields(): 获取当前运行时类及其父类中声明为public访问权限的属性
        System.out.println("*******getFields()**************");
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        // getDeclaredFields(): 获取当前运行时类中声明的所有属性，包括private属性（不包含父类中声明的属性）
        // 获取权限修饰符  类型  变量名
        System.out.println("\n*******getDeclaredFields()**************");
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f : fields1) {
            System.out.println(f);

            // 1. 获取权限修饰符
            int modifiers = f.getModifiers();
            System.out.print("权限修饰符： " + Modifier.toString(modifiers) + "\t");

            // 2. 获取类型
            Class<?> type = f.getType();
            System.out.print("类型： " + type.getName() + "\t");

            // 3. 获取属性名
            String name = f.getName();
            System.out.print("属性名： " + name);

            System.out.println();
        }
    }
}
