package ReflectionTest2Main;

import ReflectionTest2.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

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

    @Test
    /* 获取构造器结构 */
    public void otherTest() {
        Class clazz = Person.class;

        // 1. getConstructor(): 获取当前运行时类中声明为public的构造器
        Constructor[] constructor = clazz.getConstructors();
        for (Constructor con : constructor) {
            System.out.println(con);
        }
        System.out.println();
        
        // 2. getDeclaredConstructors() : 获取当前运行时类中声明的所有构造器
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor dec : declaredConstructors) {
            System.out.println(dec);
        }
    }

    @Test
    // 获取运行时 类的父类及其父类的泛型
    public void test04() {
        Class clazz = Person.class;

        // 1. getSuperclass() : 获取运行时类的父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);

        // 2. getGenericSuperclass() : 获取运行时类的带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);

        // 3. 获取运行时类的带泛型的父类的泛型
        // 获取泛型结构
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type type : actualTypeArguments) {
            System.out.println(type);   // 方法一 ：class java.lang.String
            System.out.println(type.getTypeName()); // 方法二 ： java.lang.String
            System.out.println(((Class)type).getName()); // 方法三 ：java.lang.String
        }

        // 4. 获取其父类的方法
        for (Field field : superclass.getFields()) {
            System.out.println(field);
        }
    }

    @Test
    /* 通过反射获取运行时类的接口、所在包、注解等 */
    public void test05() {
        Class clazz = Person.class;

        // 1. 获取运行时类的接口
        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }

        System.out.println();
        // 获取运行时类的所在父类的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class c : interfaces1) {
            System.out.println(c);
        }

        System.out.println();
        // 2. 获取运行时类的所在包
        Package pack = clazz.getPackage();
        System.out.println(pack);

        System.out.println();
        // 3. 获取运行时类的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation anno : annotations) {
            System.out.println(anno);
        }
    }
}
