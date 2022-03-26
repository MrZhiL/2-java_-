package ReflectionTest2Main;

import ReflectionTest2.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

    @Test
    /**获取方法的参数和注解
     * @xxx
     * 权限修饰符    返回值类型   方法名(参数类型1 形参名1, 参数类型2, ...) throws xxxException{}
     */
    public void test02() {
        Class clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);

            // 1. 获取运行时类的方法的注解
            Annotation[] anno = m.getAnnotations();
            for (Annotation a : anno) {
                System.out.println(a);
            }

            // 2. 获取运行时类的方法的权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + ";\t");

            // 3. 获取运行时类的方法的返回值类型
            System.out.print(m.getReturnType() + "; \t");

            // 4. 获取方法名
            System.out.print(m.getName() + "(");

            // 5. 获取形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
//            if (parameterTypes.length > 0) {
                for (int i = 0; i < parameterTypes.length; ++i) {
                    if (i == parameterTypes.length - 1) {
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + " args_" + i + ", ");
                }
            }
            System.out.print(")");

            // 6. 获取抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.println(" throws ");
                for (int i = 0; i < exceptionTypes.length; ++i) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ", ");
                }
            }

            System.out.println("\n");
        }
    }
}
