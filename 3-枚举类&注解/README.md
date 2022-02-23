# 九、枚举类与注解

## 1. 枚举类的使用

### 1.1 主要内容：

- 如何自定义枚举类
- 如何使用关键字enum定义枚举类
- Enum类的主要方法
- 实现接口的枚举类

### 1.2 性质

- 当类的对象只有有限个，确定的。距离如下：
  - 星期：Monday、... 、Sunday
  - 性别：Man/Woman
  - 季节：Sprint 、... 、Winter
  - 支付方式：Cash(现金)、WeChatPay、Alipay、BankCard、CreditCard(信用卡)、
  - 就职状态：Busy、Free、Vocation、Dimission
  - 线程状态：创建、就绪、运行、阻塞、死亡
  - ....
- 当需要定义一组常量时，强烈建议使用枚举类



### 1.3 枚举类的定义

- 枚举类的实现：
  - JDK 1.5此前需要自定义枚举类
  - JDK 1.5新增enum关键字用于定义枚举类（enum继承与java.lang.Enum类）
- 若枚举类只有一个对象，则可以作为一种单例模式的实现方式
- **枚举类的属性**：
  - 枚举类对象的属性不应允许被改动，所以应该使用 private final 修饰
  - 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
  - 若枚举类显式的定义类带参数的构造器，则在列出枚举值时也必须对应的传入参数。



### 1.4 Enum类的主要方法

| 方法名              | 详细描述                                                     |
| ------------------- | ------------------------------------------------------------ |
| valueOf(String str) | 传递枚举类型的Class对象和枚举常量名称给静态方法valueOf，会得到与参数匹配的枚举常量，**如果找不到则抛出异常**：IllegalArgumentException。 |
| toString()          | 得到当前枚举常量的名称。可以通过重写这个方法来使得结果更加容易读取 |
| values()            | 返回枚举类型的对象数组。该方法可以很方便的遍历所有的枚举值。 |



| 方法名            | 详细描述                                                     |
| ----------------- | ------------------------------------------------------------ |
| valueOf           | 传递枚举类型的Class对象和枚举常量名称给静态方法valueOf，会得到与参数匹配的枚举常量 |
| toString          | 得到当前枚举常量的名称。可以通过重写这个方法来使得结果更加容易读取 |
| equals            | 在枚举类型中可以使用"=="来比较两个枚举常量是否相等。Enum提供的这个equals()方法，也是直接使用“==”实现的。它的存在是为了在Set、List和Map中使用。注意，equals()是不可变的。 |
| hashCode          | Enum实现了hashCode()来和equals()保持一致。它也是不可变的     |
| getDeclaringClass | 得到枚举常量所属枚举类型的Class对象。可以用它来判断两个枚举常量是否属于同一个枚举类型 |
| name              | 得到当前枚举常量的名称。建议优先使用toString()               |
| ordinal           | 得到当前枚举常量的次序                                       |
| compareTo         | 枚举类型实现了Comparable接口，这样可以比较两个枚举常量的大小（按照声明顺序） |
| clone             | 枚举类型必能被Clone。为了防止子类实现克隆方法，Enum实现了一个仅抛出CloneNotSupportedException异常的不变Clone()。 |



### 1.5 使用enum关键字定义的枚举类实现接口

- 情况一：实现接口，在enum类中实现抽象方法，此时所有枚举类的返回值相同

- 情况二：让枚举类的对象分别实现接口中的抽象方法

- ```java
  interface info {
  	void show();
  }
  
  enum Season {
  	// 2. 分别在每个对象中实现
      Season("SPRINIG", "春天") {
          public void show() {
              System.out.println("春天是万物复苏的季节");
          }
      }
      Season("SUMMER", "夏天") {
          public void show() {
              System.out.println("夏天是万物生长的季节");
          }
      }
      Season("AUTUMN", "秋天") {
          public void show() {
              System.out.println("秋天是万物成熟的季节");
          }
      }
      Season("WINTER", "冬天") {
          public void show() {
              System.out.println("冬天是冰雪的世界");
          }
      }
  	
  	@Override
  	// 1. 直接在enum类中实现抽象方法
      public void show() {
  		System.out.println("This is a Season!");
  	}
  }
  ```







```java
/**
 * @ClassName: enumTest
 * @Description: Java - 枚举类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/20 11:34
 * @node:
 *        1. 枚举类的性质
 *          - 当类的对象只有有限个，确定的。距离如下：
 *          - 星期：Monday、... 、Sunday
 *          - 性别：Man/Woman
 *          - 季节：Sprint 、... 、Winter
 *          - 支付方式：Cash(现金)、WeChatPay、Alipay、BankCard、CreditCard(信用卡)、
 *          - 就职状态：Busy、Free、Vocation、Dimission
 *          - 线程状态：创建、就绪、运行、阻塞、死亡
 *          - ....
 *          - 当需要定义一组常量时，强烈建议使用枚举类
 *
 *       2. 枚举类的定义
 *          - 枚举类的实现：
 *            - JDK 1.5此前需要自定义枚举类
 *            - JDK 1.5新增enum关键字用于定义枚举类 (enum类继承与java.lang.Enum类)
 *          - 若枚举类只有一个对象，则可以作为一种单例模式的实现方式
 *          - **枚举类的属性**：
 *            - 枚举类对象的属性不应允许被改动，所以应该使用 private final 修饰
 *            - 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
 *            - 若枚举类显式的定义类带参数的构造器，则在列出枚举值时也必须对应的传入参数。
 *
 *        3. Enum类的常用方法
 *          | 方法名              | 详细描述                                                     |
 *          | ------------------- | ------------------------------------------------------------ |
 *          | valueOf(String str) | 传递枚举类型的Class对象和枚举常量名称给静态方法valueOf，会得到与参数匹配的枚举常量，**如果找不到则抛出异常**：
 *                                  IllegalArgumentException。 |
 *          | toString()          | 得到当前枚举常量的名称。可以通过重写这个方法来使得结果更加容易读取 |
 *          | values()            | 返回枚举类型的对象数组。该方法可以很方便的遍历所有的枚举值。 |
*
 *      4. 使用enum关键字定义的枚举类实现接口
 *          - 情况一：实现接口，在enum类中实现抽象方法，此时所有枚举类的返回值相同
 *          - 情况二：让枚举类的对象分别实现接口中的抽象方法
 */
public class enumTest {
    public static void main(String[] args) {
        // 1. 自定义枚举类的测试
        System.out.println("*********自定义枚举类测试*************");
        Season season1 = Season.SPRING;
        Season season2 = Season.WINTER;
        System.out.println(season1);
        System.out.println(season2);

        // 2. enum类测试
        System.out.println("*********使用enum的枚举类测试*************");
        Season2 summer = Season2.SUMMER;
        Season2 autumn = Season2.AUTUMN;
        System.out.println(summer); // 如果没有重写toString(), 此时会输出SUMMER
        System.out.println(autumn); // 此时会输出AUTUMN
        System.out.println(Season2.class.getSuperclass()); // class java.lang.Enum

        // 3. Enum类的常用方法
        System.out.println("*********Enum类的常用方法测试*************");
        System.out.println("**********1. values() 方法***************");
        Season2[] values = Season2.values();
        for (int i = 0; i < values.length; ++i) {
            System.out.println(values[i]);
        }

        System.out.println("------------Thread.values----------------");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; ++i) {
            System.out.println(values1[i]);
        }

        System.out.println("***********2. toString() 方法**************");
        System.out.println(summer.toString());
        System.out.println(autumn.toString());

        System.out.println("***********3. valueOf(str) 方法************");
        // valueOf(str)方法：直接返回str的枚举类型，如果不存在则报错java.lang.IllegalArgumentException
        Season2 spring = Season2.valueOf("SPRING");
        System.out.println(spring);

        System.out.println("\n*************** enum类实现接口************");
        summer.show(); // 夏天是万物生长的季节
        autumn.show(); // 秋天是万物成熟的季节
    }
}

// 一、自定义枚举类
class Season {
    // 1. 将属性标记为 private final 的
    private final String seasonName;
    private final String seasonDesc;

    // 2. 将构造器声明为private的
    private Season(String seasonDesc, String seasonName) {
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    // 3. 创建枚举类的多个对象，并且需要声明为public static final 常量形式的，因为该对象只能存在一份
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "梅花盛开");

    // 4.1 其他诉求：获取枚举类的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 4.2 其他诉求2：提供toString()方法
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

// enum类实现接口
interface info {
    void show();
}

// 二、使用enum关键字来实现枚举类
// 接口1：首先需要实现接口
enum Season2 implements info {
    // 1. 使用enum关键字创建枚举类时，首先需要提供枚举类的对象，多个对象之间用逗号”，“隔开，最后使用“;"结尾
    // 方法二: 在每个枚举类对象中实现接口
    SPRING("春天", "春暖花开") {
        @Override
        public void show() {
            System.out.println("春天是万物复苏的季节");
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        @Override
        public void show() {
            System.out.println("夏天是万物生长的季节");
        }
    },
    AUTUMN("秋天", "秋高气爽") {
        @Override
        public void show() {
            System.out.println("秋天是万物成熟的季节");
        }
    },
    WINTER("冬天", "梅花盛开") {
        @Override
        public void show() {
            System.out.println("冬天是冰雪的世界");
        }
    };

    // 2. 创建枚举类的属性
    private final String seasonName;
    private final String seasonDesc;

    // 3. 将构造器声明为private的
    private Season2(String seasonDesc, String seasonName) {
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    // 4. 其他诉求：获取属性信息
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }



    // 4.2 此时可以不必重写toString()方法，因为enum继承于Enum类

    // 5. 方法一：重写接口中的抽象方法，此时所有枚举类的返回值相同
    @Override
    public void show() {
        System.out.println("This is a season!");
    }

}
```



## 2. 注解（Annotation）

主要内容：

- 注解(Annotation)概述
- 常见的Annotation示例
- 自定义Annotation
- JDK中的元注解
- 利用反射获取注解信息（在反射部分涉及）
- JDK8中注解的新特性

### 2.1 概述

- 从JDK 5.0开始，Java增加了对元数据（MetaData）的支持，也就是Annotation(注解)

- Annotation其实就是代码里的**特殊标记**，这些标记可以在编译，类加载，运行时被读取，并执行相应的处理。通过使用Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证或者进行部署。

- Annotation可以像修饰符一样被使用，可用于**修饰包，类，构造器，方法，成员变量，参数，局部变量的声明**，这些信息被保存在Annotation的"name = value"中。

- 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，替代JavaEE旧版中所遗留的繁冗代码和XML配置等。

- 未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以上都是基于注解的，Hibernate3.x以后也是基于注解的，现在Struct2有一部分也是基于注解的了，注解是一种趋势，一定程度上，可以说：**框架 = 注解 + 反射 + 设计模式**

- 使用Annotation时要在其前面增加 @ 符号，并把该Annotation当成一个修饰符使用。用于修饰它支持的程序元素。

- 示例一：生成文档相关的注解：

  ```
  @author : 标明开发该类模块的作者，多个作者之间使用,分割
  @version : 标明该类模块的版本
  @see : 参考转向，也就是相关主题
  @since : 从哪个版本开始增加的
  @param : 对方法中某参数的说明，如果没有参数就不能写
  @return : 对方法返回值的说明，如果方法的返回值类型是void则不写
  @exception : 对方法可能抛出的异常进行说明，如果方法没有用throws显示抛出的异常，则不能写
  
  @para @return 和 @exception 这三个标记都是只用于方法的
  @param的格式要求：@param 形参名 形参类型 形参说明
  @return 的格式要求： @return  返回值类型 返回值说明
  @exception 的格式要求： @exception 异常类型 异常说明
  @param 和 @exception 可以并列多个
  ```

- 示例二：在编译时进行格式检查（JDK内置的三个基本注解）

  - @Override : 限定重写父类方法，该注解只能用于方法
  - @Deprecated: 用于表示所修饰的元素（类，方法等）已超时。通常是因为所修饰的结构危险或者存在更好的选择
  - @SuppressWarnings: 抑制编译器警告

- 示例三：跟踪代码依赖性，实现替代配置文件功能：

  - Servlet3.0提供了注解，使得不再需要在web.xml文件中进行Servlet的部署

  - ```java
    @WebServlet("/login")
    public class LoginServlet extends HttpServlet {
    	private static fiinal long serialVersionUID = 1L;
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
    	protected void dooPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);
    	}
    }
    ```

    ```web-idl
    <servlet>
    	<servlet-name>LoginServlet</servlet-name>
    	<servlet-class>com.servlet.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
    	<servlet-name>LoginServlet</servlet-name>
    	<servlet-class>/login</servlet-class>
    </servlet-mapping>
    ```

  - spring框架中关于“事物”的管理



### 2.2 自定义注解

如何自定义注解：参考@SuppressWarnings定义

- 定义新的Annotation类型使用 @interface 关键字
- 自定义注解自动继承了java.lang.annotation.Annotation接口
- Annotation 的成员变量在Annotation定义中以无参数方法的形式来声明。其方法名和返回值定义了该成员的名字和类型。我们称为配置参数。类型只能是八种基本数据类型：String数组、Class类烈、enum类型、Annotation类型**以上所有类型的数组**。
- 可以在定义Annotation的成员变量时为其指定初始值，指定成员变量的初始值可使用**default关键字**
- 如果只有一个参数成员，建议使用**参数名为value**
- 如果定义的注解含有配置参数，那么使用时必须指定参数值，除非它有默认值。格式是“参数名 = 参数值”，如果只有一个参数成员，且名为value，可以省略"value = "
- 没有成员定义的Annotation称为**标记**；包含成员变量的Annotation称为元数据Annotation。

**注意：自定义注解必须配上注解的信息处理流程才有意义。**

**注意：自定义注解通常都会指明两个元注解：Retention、Target**



```java
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

```



### 2.3 JDK中的元注解

- JDK的元Annotation用于修饰其他Annotation定义
- JDK5.0提供了4个标准的meta-annotation类型，分别是：
  - Retention
  - Target
  - Documented
  - Inherited
- 元数据的理解：String name = "tkei"; 其中String 和 name都是对“tkei”的修饰



- @Retention：只能用于修饰一个Annotation定义，用于指定该Annotation的生命周期，@Retention包含一个RetentionPolicy类型的成员变量，使用@Rentention时必须为该value成员变量指定值：
  - `RententionPolicy.SOURCE`：在源文件中有效（即源文件保留），编译器直接丢弃这种策略的注释
  - `RententionPolicy.CLASS`：在class文件中有效（即class保留），当运行java程序时，JVM不会保留注释。这是默认值
  - `RententionPolicy.RUNTIME`：在运行时有效（即运行时保留），当运行Java程序时，JVM会保留注释。程序可以通过反射获取该注释。
  - ![image-20220223170417039](D:\Program Files (x86)\JavaProject\2-Java高级部分\0-学习文档\9-枚举类与注解.assets\image-20220223170417039.png)

- @Target：用于修饰Annotation定义，用于指定被修饰的Annotation能用于修饰哪些程序元素。@Target也包含一个名为value的成员变量。

  | 取值(ElementType) |                  | 取值(ElementType) |                                          |
    | ----------------- | ---------------- | ----------------- | ---------------------------------------- |
  | CONSTRUCTOR       | 用于描述构造器   | PACKAGE           | 用于描述包                               |
  | FIELD             | 用于描述域       | PARAMETER         | 用于描述参数                             |
  | LOCAL_VARIABLE    | 用于描述局部变量 | TYPE              | 用于描述类、接口(包括注解类型)或enum声明 |
  | METHOD            | 用于描述方法     |                   |                                          |

- (出现频率较低)Document：用于指定被该元Annotation修饰的Annotation类将被javadoc工具提取出文档。默认情况下，javadoc是不包括注解的。

  - 定义为Documented的注解必须设置Retention值为RUNTIME。
  - @Deprecated被@Document标识了。

- (出现频率较低)Inherited：被它修饰的Annotation将具有**继承性**。如果某个类使用了被@Inherited修饰的Annotation，则其子类将自动具有该注解。

  - 比如：如果把标有@Inherited注解的自定义的注解标注在类级别上，子类则可以继承父类类级别的注解。
  - 实际应用中，使用较少

### 2.4 通过反射获取注解信息---后面补充

```java
public void testGetAnnotation() {
	Class classzz = Student.class; // 首先需要获取类名
	Annotation[] annotations = classzz.getAnnotations(); // 使用getAnnotations()方法获取注解
	for (int i = 0; i < annotations.length; i++) {
		System.out.println(annotations[i]); // @xxxpath.java1.MyAnnotation(value=hello)
	}
}

@MyAnnotation(value = "hello")
class Person {
	private String name;
	private int age;
	...
}

class Student extends Person {
	...
}
```

