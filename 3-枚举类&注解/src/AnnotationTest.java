/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java - 注解的使用
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/22 12:13
 * @node: 注解：
 *          - 从JDK 5.0开始，Java增加了对元数据（MetaData）的支持，也就是Annotation(注解)
 *          - Annotation其实就是代码里的**特殊标记**，这些标记可以在编译，类加载，运行时被读取，并执行相应的处理。
 *              通过使用Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证或者进行部署。
 *          - Annotation可以像修饰符一样被使用，可用于**修饰包，类，构造器，方法，成员变量，参数，局部变量的声明**，这些信息被保存在Annotation的"name = value"中。
 *          - 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
 *              在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，替代JavaEE旧版中所遗留的繁冗代码和XML配置等。
 *          - 未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以上都是基于注解的，Hibernate3.x以后也是基于注解的，
 *              现在Struct2有一部分也是基于注解的了，注解是一种趋势，一定程度上，可以说：**框架 = 注解 + 反射 + 设计模式**
 *          - 使用Annotation时要在其前面增加 @ 符号，并把该Annotation当成一个修饰符使用。用于修饰它支持的程序元素。
 *          3. 如何自定义注解：参考@SuppressWarnings定义
 *             3.1 注解声明为: @interface
 *             3.2 内部定义成员，通常使用value表示
 *             3.3 可以指定成员的默认值，使用default定义
 *             3.4 如果自定义注解没有成员，表明是一个标识作用。
 *
 *             如果注解有成员，在使用注解时，需要指明成员的值
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person p = new Person("lisi", 12);
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }
}

@MyAnnotation("hi")
class Person {
    private String name;
    private int age;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
