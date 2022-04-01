# Java8中的新特性
1. Lambda表达式
2. 函数式（Functional）接口
3. 方法引用与构造器引用
4. 强大的Stream API
5. Optional类

## 1. Java8 的新特性
- 速度更快
- 代码更少（增加了新的语法：Lambda表达式）
- 强大的Stream API
- 便于并行
  - 并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。
    相比于串行的流，并行的流可以很大程度上提高程序的执行效率。
  - Java 8中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream API可以声明性地
    通过parallel()与sequential()在并行流与顺序流之间进行切换。
- 最大化减少空指针异常：Optional
- Nashorn引擎，运行JVM上运行JS应用

## 2. Lambda表达式
- Lambda 是一个匿名函数，我们可以把Lambda表达式理解为时一段可以传递的代码（将代码像数据一样传递）。
使用它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。

- Lambda表达式：在Java8语言中引入的一种新的语法元素和操作符。这个操作符为“->”，
该操作符被称为 **Lambda 操作符**或**箭头操作符**。它将Lambda分为两个部分：
  - 左侧：指定了Lambda表达式需要的参数列表
  - 右侧：指定了Lambda体，是抽象方法的逻辑实现，也即Lambda表达式要执行的功能

### 2.1 Lambda语法：
1. 语法格式一：无参，无返回值

    `Runnable r1 = () -> {System.out.println("Hello Lambda");}`

2. 语法格式二：Lambda需要一个参数，无返回值

   `Consumer<String> con = (String str) -> {System.out.println(str);}`

3. 语法格式三：数据类型可以省略，因为可由编译器推断得出。称为“类型推断”

   `Consumer<String> con = (str) -> {System.out.println(str);}`

4. 语法格式四：Lambda若只需要一个参数时，**参数的小括号可以省略**

   `Consumer<String> con = str -> {System.out.println(str);}`

5. 语法格式五：Lambda若需要二个或以上的参数，多条执行语句，并且可以有返回值

   `Comparator<Integer> com = (x,y) -> { 
        System.out.println("实现函数式接口方法！");
        return Integer.compare(x,y);
    }`

6. 语法格式六：当Lambda体只有一条语句时，return 与大括号若有，都可以省略。

   `Comparator<Integer> com = (x,y) -> Integer.compare(x,y);`

7. 总结：

    Lambda左边：Lambda形参列表的参数类型可以省略（类型推断）；如果Lambda形参列表只有一个参数，则这一对()也可以省略。 
    
    Lambda右边：Lambda方法体应该使用一对{}包裹；如果Lambda体只有一条执行语句（可能是return语句），则可以省略这一对{}和return

### 2.2 Lambda代码测试1：
```java
package LambdaTest;

import org.junit.Test;

import java.util.Comparator;

/**
 * @ClassName: LambdaTest
 * @Description: Java - Lambda表达式举例
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/31 10:49
 * @node:
 */
public class LambdaTest {
    @Test
    public void test01() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 常用方法调用");
            }
        };
        r1.run();

        System.out.println("***********************");

        Runnable r2 = () -> System.out.println("Runnable->lambda表达式调用");
        r2.run();
    }


    @Test
    public void test02() {
        // 1. 常用方法调用
        Comparator<Integer> c1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = c1.compare(12, 21);
        System.out.println(compare);

        System.out.println("*****************************");

        // 2. 使用Lambda表达式进行调用
        Comparator<Integer> c2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(c2.compare(22, 11));

        // 3. 使用方法引用
        Comparator<Integer> c3 = Integer::compare;
        System.out.println(c3.compare(11, 11));
    }
}
```

### 2.3 Lambda表达式语法代码测试：
```java
    @Test
    // 语法格式一：无参，无返回值
    public void test01() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 常用方法调用");
            }
        };
        r1.run();

        System.out.println("***********************");

        Runnable r2 = () -> System.out.println("Runnable->lambda表达式调用");
        r2.run();
    }

    @Test
    // 语法格式二：Lambda需要一个参数，无返回值
    public void test02() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        con.accept("小不忍则乱大谋");
        System.out.println("******************");

        // Lambda格式：
        Consumer<String> con2 = (String s) -> { System.out.println(s); };
        con2.accept("小不忍则乱大谋！");
    }

    @Test
    // 语法格式三：数据类型可以省略，因为可由编译器推断得出。称为“类型推断”
    public void test03() {
        // Lambda格式：
        Consumer<String> con1 = (String s) -> { System.out.println(s); };
        con1.accept("小不忍则乱大谋！");
        System.out.println("**************************");

        // 类型推断
        Consumer<String> con2 = (s) -> { System.out.println(s); };
        con2.accept("hhhhhhhhhh");
    }

    @Test
    // 语法格式四：Lambda若只需要一个参数时，**参数的小括号可以省略**
    public void test04() {
        Consumer<String> con1 = (String s) -> { System.out.println(s); };
        con1.accept("小不忍则乱大谋！");
        System.out.println("**************************");

        // Lambda若只需要一个参数时，参数的小括号可以省略
        Consumer<String> con2 = s -> { System.out.println(s); };
        con2.accept("hhhhhhhhhh");
    }

    @Test
    // 语法格式五：Lambda若需要二个或以上的参数，多条执行语句，并且可以有返回值
    public void test05() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(11, 22));

        System.out.println("**********************");

        // Lambda表达式
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        System.out.println(com2.compare(111, 22));
    }

    @Test
    // 语法格式六：当Lambda体只有一条语句时，return 与大括号若有，都可以省略。
    public void test06() {
        Comparator<Integer> com1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        System.out.println(com1.compare(111, 22));
        System.out.println("*****************");

        // 当Lambda体只有一条语句时，return 与大括号若有，都可以省略。
        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(111,222));
    }
```

