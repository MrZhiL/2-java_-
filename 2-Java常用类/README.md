# 八、Java常用类

## 1.  字符串相关的类：String

- String类：代表字符串。Java程序中的所有字符串字面值（如"abc"）都作为此类的实例实现。

- String是一个final类，代表不可变的字符序列

- 字符串是常量，用双引号引起来表示。它们的值在创建之后不能更改。

- String对象的字符内容是存储在一个字符数组value[]中的。

  ```java
  public final class String
      implements java.io.Serializable, Comparable<String>, CharSequence {
  
      
      @Stable
      private final byte[] value;
     
      private final byte coder;
  
      private int hash; // Default to 0
  	
  	....
      
  }
  ```

  

1. `String`：字符串，使用一对“”引起来表示：

   1. `String` 声明为final的，不可被继承

   2. `String`实现了`Serializable`接口：表示字符串是支持序列化的

      ​				实现了`Comparable`接口：表示String 可以比较大小

   3. String内部定义了final char[] value用于存储字符串数据

   4. String：代表不可变的字符序列。简称：不可变性

      体现： 

      1. 当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。

         2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能指定原有的value。
            3. 当调用String的replace()方法修改字符或字符串时，也必须重新指定内存区域赋值，不能使用原有的value。

   5. 通过自变量的方式（String s = "abc"; 区别于new）给一个字符串赋值，此时的字符串声明在字符串常量池中。

   6. 字符串常量池中是不会存储相同内容的字符串的。

   ```java
   package StringClassTest;
   
   import org.junit.Test;
   
   /**
    * @ClassName: StringTest1.java
    * @Description: Java - String类的测试
    * @author: zhilx (zhilx1997@sina.com)
    * @version: v1.0
    * @data: 2022/1/25 21:12
    * @node: `String`：字符串，使用一对“”引起来表示：
    *          1. `String` 声明为final的，不可被继承
    *          2. `String`实现了`Serializable`接口：表示字符串是支持序列化的
    *                     实现了`Comparable`接口：表示String 可以比较大小
    *
    *          3. String内部定义了final char[] value用于存储字符串数据
    *          4. String：代表不可变的字符序列。简称：不可变性
    *             体现：
    *               1. 当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
    *    			 2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能指定原有的value。
    *    			 3. 当调用String的replace()方法修改字符或字符串时，也必须重新指定内存区域赋值，不能使用原有的value。
    *          5. 通过自变量的方式（String s = "abc"; 区别于new）给一个字符串赋值，此时的字符串声明在字符串常量池中。
    *          6. 字符串常量池中是不会存储相同内容的字符串的。
    */
   public class StringTest1 {
       @Test
       public void test1() {
           String s1 = "abc";  // 字面量的定义方式
           String s2 = "abc";
           String s3 = s2;
   
           System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3); // abc, abc, abc
           System.out.println("s1 == s2 ? " + (s1 == s2)); // true
           System.out.println("s2 == s3 ? " + (s3 == s2)); // true
           System.out.println("s1 == s3 ? " + (s3 == s1)); // true
           System.out.println("*************************************");
   
           // 1. 当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值。
           s2 = "hello"; // 对s2重新赋值
           System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3); // abc, hello, abc
           System.out.println("s1 == s2 ? " + (s1 == s2)); // false
           System.out.println("s2 == s3 ? " + (s3 == s2)); // false
           System.out.println("s1 == s3 ? " + (s3 == s1)); // true
           System.out.println("*************************************");
   
           // 2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能指定原有的value。
           s3 += "abc";
           System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3); // abc, hello, abcabc
           System.out.println("s1 == s2 ? " + (s1 == s2)); // false
           System.out.println("s2 == s3 ? " + (s3 == s2)); // false
           System.out.println("s1 == s3 ? " + (s3 == s1)); // false
           System.out.println("*************************************");
   
           // 3. 当调用String的replace()方法修改字符或字符串时，也必须重新指定内存区域赋值，不能使用原有的value
           String s4 = s3.replace('a', 'h');
           System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3 + ", s4 = " + s4); // abc, hello, abcabc, hbchbc
           System.out.println("s1 == s2 ? " + (s1 == s2)); // false
           System.out.println("s2 == s3 ? " + (s3 == s2)); // false
           System.out.println("s3 == s4 ? " + (s3 == s4)); // false
           System.out.println("*************************************");
       }
   
   }
   
   ```

   <img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220125214718624-16431184402811.png" style="zoom:67%;" />

## 2. 字符串的创建

### 练习1：

```java
String str = "hello"; // 通过字面量的方式创建

// 本质上是this.value = new char[0];
String s1 = new String();

// this.value = original.value;
String s2 = new String(String orinigal);

// this.value = Arrays.copyof(value, value.length);
String s3 = new String(char[] a);

// 指定字符数组中开始位置（下标从0开始计算）
String s4 = new String(char[] a, int startIndex, int count);
```

### 练习2：**字符串常量存储在字符串常量池中，目的是共享；字符串非常量对象存储在堆中（通过new创建的对象）**

```java
@Test
    public void test2() {
        // 1. 通过字母量的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";

        // 2. 通过new+构造器的方式：此时s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println("s1 == s2 ? " + (s1 == s2)); // true
        System.out.println("s1 == s3 ? " + (s1 == s3)); // false
        System.out.println("s1 == s4 ? " + (s1 == s4)); // false
        System.out.println("s3 == s4 ? " + (s3 == s4)); // false
    }
```



<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220125215525084.png" alt="image-20220125215525084" style="zoom: 67%;" />

### 练习3：

```java
class Person {
    String name;
    int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public void test2() {
        // 1. 通过字母量的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";

        // 2. 通过new+构造器的方式：此时s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println("s1 == s2 ? " + (s1 == s2)); // true
        System.out.println("s1 == s3 ? " + (s1 == s3)); // false
        System.out.println("s1 == s4 ? " + (s1 == s4)); // false
        System.out.println("s3 == s4 ? " + (s3 == s4)); // false

        // 3. 对自定义类进行创建
        Person p1 = new Person("Bob", 13);
        Person p2 = new Person("Bob", 13);
        System.out.println("p1.name = " + p1.name + ", p2.name = " + p2.name);
        System.out.println("p1.name.equals(p2.name) ? " + p1.name.equals(p2.name)); // true
        System.out.println("p1.name == p2.name ? " + (p1.name == p2.name)); // true
        System.out.println("*************************");

        p2.name = "Jone";
        System.out.println("p1.name = " + p1.name + ", p2.name = " + p2.name);
        System.out.println("p1.name.equals(p2.name) ? " + p1.name.equals(p2.name)); // false
        System.out.println("p1.name == p2.name ? " + (p1.name == p2.name)); // false
    }
```

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220125215837145.png" alt="image-20220125215837145" style="zoom:67%;" />



### 面试题：String s = new String("abc") ; 方式创建对象，在内存中创建了几个对象？

答：如果是第一次创建则是两个：一个是堆空间中new结构，另一个是char[]对应的常量池中的数据。

​        如果之前已经声明过“abc"变量，则此时仍然会在堆空间new一个地址，然后该new出来的地址调用之前声明的”abc"变量，因为字符串常量池中只有一份数据（不允许有相同的数据）



### 练习4：字符串连接测试

判断：

```java
String s1 = "JavaEE";
        String s2 = "hadoop";

        String s3 = "JavaEEhadoop";

        // 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
        // 只要其中有一个是变量，结果就在堆中。
        String s4 = "JavaEE" + "hadoop";

        // 如果连接中有变量，则会在堆区中开辟新的空间并保存，和new一样
        String s5 = s1 + "hadoop";
        String s6 = "JavaEE" + s2;
        String s7 = s1 + s2;
        String s8 = (s1 + s2).intern();
	
		final String s9 = "JavaEE"; // 此时s9为常量
		String s10 = s9 + "hadoop";	// 常量+常量，此时s10的结果也存在于常量池中
		System.out.println(s3 == s7); // false
		System.out.println(s3 == s10); // true

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s3 == s8); // true

        System.out.println(s4 == s5); // false
        System.out.println(s4 == s6); // false
        System.out.println(s4 == s7); // false
        System.out.println(s4 == s8); // true

        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s5 == s8); // false

        System.out.println(s7 == s6); // false
        System.out.println(s7 == s8); // false
```



**结论：**

1. 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
2. 只要其中有一个是变量，结果就在堆中。
3. 如果拼接的结果调用intern()方法，返回值就在常量池中。



<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211171002092.png" alt="image-20220211171002092" style="zoom:67%;" />



### 练习5：考察String的不可变性

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211172009212.png" alt="image-20220211172009212" style="zoom:67%;" />

```java
package StringClassTest;

/**
 * @ClassName: StringClassTest3
 * @Description: Java - 一道简单的面试题
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/11 17:25
 * @node:
 */
public class StringTest3 {
    String str = new String("hello");
    char[] ch = {'t', 'e', 's', 't'};


    // note: 因为String具有不可变性，因此此时change中的str只是一个临时变量，当调用结束后就会销毁，str的值不会发生改变
    // 而char数组中传递的是ch的地址，具有可变性，因此会发生改变
    public void change(String str, char[] ch) {
        str = "good";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest3 st3 = new StringTest3();
        st3.change(st3.str, st3.ch);
        System.out.println(st3.str); // hello
        System.out.println(st3.ch);  // best
    }
}

```



## 3. JVM中涉及String字符串的内存结构

- Sun公司的HopSpot
- BEA公司的JRockit
- IBM公司的J9 VM

### 3.1 Heap 堆

一个JVM实例只存在一个堆内存，堆内存的大小是可调节的。类加载读取了类文件后，需要把类、方法、常变量放到堆内存中，保存所有引用类型的真是信息，以方便执行器执行，堆内存分为三部分：

1. **Young Generation Space 新生区	Young**
2. **Tenure generation space 养老区    Old**
3. Permanent Space   永久存储区       Perm （但其实永久区并不在堆区中，在方法区中）

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211173947852.png" alt="image-20220211173947852" style="zoom:67%;" />

#### 1 新生区

​	新生区是类诞生、成长、消亡的区域，一个类在这里产生，应用，最后被垃圾回收器收集，结束生命。新生区又分为两部分：伊甸区（Eden space）和幸存者区（Survivor pace），所有的类都是在伊甸区被new出来的。幸存区有两个：0区（Survivor 0 space）和（Survivor 1 space）。当伊甸区的空间用完时，程序又需要创建对象，JVM的垃圾回收器将对伊甸区进行垃圾回收（Minor GC），将伊甸区中的不再被其他对象引用的对象进行销毁。然后将伊甸区中的剩余对象移动到幸存0区。若幸存0区也满了，再对该区进行垃圾回收，然后移动到1区。那如果1区也满了，则移动到养老区。若养老区也满了，此时将产生Major GC(FullGC)，进行养老区的内存清理。如养老区执行了Full GC之后依然无法进行对象的保存，就会产生OOM异常：`OutOfMemoryError`。



如果出现`java.lang.OutOfMemoryError: Java heap space`异常，说明Java虚拟机的堆内存不够，原因有二：

1. java虚拟机的堆内存设置不够，可以通过参数-Xms、-Xmx来调整
2. 代码中创建了大量大对象，并且长时间不能被垃圾收集器收集（存在被有用）。——内存溢出；内存泄漏



#### 2. 永久区

​	永久区储区是一个常驻内存区域，用于存放JDK 自身所携带的Class, Interface的元数据，也就是说它存储的是运行环境必须的类信息，被装在进此区域的数据是不会被垃圾回收器回收掉的，关闭JVM才会释放此区域所占用的内存。

​	如果出现`Java.lang.OutOfMemoryError: PermGen space`，说明是Java虚拟机对永久代Perm内存设置不够。一般出现这种情况，都是程序启动需要加载大量的第三方jar包。例如，在一个Tomcat下部署了太多的应用。或者大量动态反射生产的类不断被加载，最终导致Perm区被占满。

其中：JDK 1.6及之前：常量池分配在永久代，1.6在方法区

​			JDK 1.7 ： 有，但已经逐步”去永久代“， 1.7在堆中

​			JDK 1.8及之后：无，1.8在元空间



​		实际而言，方法区（Method Area）和堆一样，是各个线程共享的内存区域，它用于存储虚拟机加载的：类信息+普通常量+静态变量+编译器编译后的代码等等，虽然JVM规范将方法区描述为堆的一个逻辑部分，但它却还有一个别名叫做Non-Heap（非堆），目的就是要和堆分开。

​		对于Hospot虚拟机，很多开发者习惯将方法区称之为“永久代(Permanenet Gen)”，但严格本质上说两个不同，或者说使用永久代来实现方法区而已，永久代是方法区的一个实现，jdk1.7的版本中，已经将原来放在永久代的字符串常量池移走。

​		常量池（Constant Pool）是方法区的一部分，Class文件除了有类的版本、字段、方法、接口等描述信息外，还有一项信息就是常量池，这部分内容将在类加载后进入方法区的运行时常量池中存放。

![image-20220211180149345](D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211180149345.png)



<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211174934390.png" alt="image-20220211174934390" style="zoom:67%;" />



<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211174948652.png" alt="image-20220211174948652" style="zoom:67%;" />



<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220211175038324.png" alt="image-20220211175038324" style="zoom:67%;" />



## 4. String的常用方法

- 常用方法1

  ```
  ing length(): 返回字符串的长度： return value.length
  char charAt(int index): 返回某索引处的字符return value[index]
  boolean isEmpty(): 判断是否为空字符串： return value.length == 0
  String toLowerCase(): 使用默认语言环境，将String中的所有字符转换为小写(之前的字符串不会改变，而是返回新的字符串)
  String toUpperCase(): 使用默认语言环境，将String中的所有字符转换为大写
  String trim(): 返回字符串的副本，忽略前导空白和尾部空白
  boolean equals(Object obj): 比较字符串的内容是否相同
  boolean equalsIgnoreCase(String anotherString): 与equals方法类似，忽略大小写
  String concat(String str):将指定字符串连接到此字符串的结尾。等价于用“+”
  int compareTo(String anotherString):比较两个字符串的大小
  String substring(int beginIndex): 返回一个新的字符串，子字符串以指定索引处的字符开头，并延伸到此字符串的末尾。 
  String substring(int beginIndex, int endIndex): 返回一个新的字符串,该字符串是此字符串的子字符串。 子字符串从指定的beginIndex开始，并扩展到索引endIndex - 1处的字符。 因此子串的长度是endIndex-beginIndex 
  ```

  

- 常用方法2

  ```
  boolean endsWith(String suffic): 测试此字符串是否可以指定的后缀结束
  boolean startsWith(String prefix): 测试此字符串是否可以以指定的前缀开始
  boolean startsWith(String prefix, int toffset): 测试此字符串从指定索引开始的子字符串是否以指定前缀开始
  
  boolean contains(CharSequence s): 当且仅此字符串包含指定的char值序列时，返回true
  int indexOf(String str): 返回指定子字符串在此字符串中第一次出现处的索引
  int indexOf(String str, int fromIndex): 返回指定子字符串在此字符中第一此出现处的索引，从指定的索引开始
  int lastIndexOf(String str): 返回指定子字符串在此字符串最后边出现处的索引
  int lastIndexOf(String str, int fromIndex): 返回指定子字符串在此字符串中最后一次出现处的索引，**从指定的索引开始返向（左）搜索**。
  
  注：indexOf和lastIndexOf方法如果未找到都是返回-1
  
  note: 什么情况下，indexOf(String str)和lastIndexOf(String str)返回相同：1. 只存在唯一一个str；2.不存在str
  
  ```

  ```java
  System.out.println(new String("helloworld").lastIndexOf("llo", 0)); // -1
          System.out.println(new String("helloworld").lastIndexOf("llo", 1)); // -1
          // note: 下面会输出2,可能是因为此时我们从索引2开始，helloworld的索引2为l，与"llo"的第一个元素相同，此时会继续往后（右边）寻找，知道匹配完成
          System.out.println(new String("helloworld").lastIndexOf("llo", 2)); // 2
  ```

  

- 常用方法3

  ```
  String replace(char oldChar, char newChar): 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
  String replace(CharSequence target, CharSequence replacement): 使用指定的字母值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
  String replaceAll(String regex, String replacement): 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
  String replaceFirst(String regex, String replacement): 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
  
  boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式
  
  String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
  String[] split(String regex, int limit): 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
  ```

  

```java
package StringMethodTest;

import org.junit.Test;

/**
 * @ClassName: StringMethodTest3
 * @Description: Java - 常用方法的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/12 17:41
 * @node:
 *      String replace(char oldChar, char newChar): 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
 *      String replace(CharSequence target, CharSequence replacement): 使用指定的字母值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
 *      String replaceAll(String regex, String replacement): 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
 *      String replaceFirst(String regex, String replacement): 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
 *
 *      boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式
 *
 *      String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
 *      String[] split(String regex, int limit): 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
 */
public class StringMethodTest3 {
    @Test
    public void test4() {

        // 1. String replace(char oldChar, char newChar): 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
        String str1 = "helloworld";
        String str2 = str1.replace('o', 'O');
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("*********************************");

        // 2. String replace(CharSequence target, CharSequence replacement): 使用指定的字母值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
        String str3 = "Hello, this is a test, this is String test!";
        String str4 = str3.replace("test", "TEST");
        System.out.println(str3);
        System.out.println(str4);
        System.out.println("*********************************");

        // 3. String replaceAll(String regex, String replacement): 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
        String str5 = "12hello34world5java7891mysql";
        // 把字符串中的数字替换成','，如果开头和结尾中存在','则去掉
        String str6 = str5.replaceAll("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(str6); // hello,world,java,mysql
        System.out.println("*********************************");

        // 4. String replaceFirst(String regex, String replacement): 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
        String str7 = str5.replaceFirst("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(str7); // hello34world5java7891mysql
        System.out.println("*********************************");

        // 5. boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式
        // 判断这是否是一个北京的电话号码
        String tel = "010-2928192";
        String tel2 = "010-292811292";
        boolean res = tel.matches("010-\\d{7,8}"); // true
        boolean res2 = tel2.matches("010-\\d{7,8}"); // false
        System.out.println(res);
        System.out.println(res2);
        System.out.println("*********************************");

        // 6. String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
        String str8 = "Hello,|welcome|to|Java|world!";
        String[] str9 = str8.split("\\|"); // Hello, welcome to Java world! 
        for(int i = 0; i < str9.length; ++i) {
            System.out.print(str9[i] + " ");
        }
        System.out.println();

        String str10 = "Hello,.welcome.to.Java.world!";
        String[] str11 = str10.split("\\."); // Hello, welcome to Java world!

        // 7. String[] split(String regex, int limit): 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
        String[] str12 = str10.split("\\.", 3); // Hello, welcome to.Java.world!
        for(int i = 0; i < str11.length; ++i) {
            System.out.print(str11[i] + " ");
        }
        System.out.println();
        System.out.println("*********************************");

        for(int i = 0; i < str12.length; ++i) {
            System.out.print(str12[i] + " ");
        }
        System.out.println();
    }
}

```



## 5. String 与 基本数据类型、包装类的转换

### 1. 复习：String与基本数据类型、包装类之间的转换

 *          字符串 -> 基本数据类型、包装类：
             *          Integer包装类的public static int parseInt(String s): 可以将由“数字”符组成的字符串转换为整型。
             *          类似地，使用java.lang包中的Byte、Short、Long、Float、Double类调用相应的类方法可以将由“数字”字符组成的字符串，转化为相应的基本数据类型。
 *          基本数据类型、包装类 --> String: 
             *          调用String重载的valueof() 或者直接使用连接符
             *          相应的valueOf(byte b)、valueOf(long l)、valueOf(float f)、valueOf(double b)、valueOf(boolean b)可右参数的相应类型到字符串的转换。



### 2. String与字符数组char[]转换

- 字符数组char[] -> 字符串
  - String类的构造器：String(char[]) 和 String(char[], int offset, int length) 分别用字符数组中的全部字符和部分字符创建字符串对象。
- 字符串 -> 字符数组
  - public char[] to CharArray(): 将字符串中的全部字符存放在一个字符数组中的方法。
  - public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin): 提供了将指定索引范围内的字符串存放到数组中的方法



### 3. String与字节数组byte[]的转换

- 字节数组 -> 字符串
  - String(byte[] bytes): 通过使用平台默认的字符集解码指定的byte数组，构造一个新的String。
  - String(byte[] bytes, int offset, int length): 用指定的字节数组的一部分，即从数组起始位置offset开始取length个字节构造一个字符串对象。
- 字符串 -> 字节数组
  - public byte[] getBytes() : 使用平台的默认字符集将此String编码为byte序列，并将结果存储到一个新的byte数组中
  - public byte[] getBytes(String charsetName): 使用指定的字符集将此String编码到byte序列，并将结果存储到新的byte数组。

```java
package StringReverseTest;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @ClassName: StringReverseTest1
 * @Description: Java - String数据类型与基本包装类的转换
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/13 10:21
 * @node: 复习：
 *
 *          1. String与基本数据类型、包装类之间的转换
 *              String --> 基本数据类型、包装类：调用包装类的静态方法prasexxx(str);
 *              基本数据类型、包装类 --> String: 调用String重载的valueof() 或者直接使用连接符
 *          2. String 与 char[] 的转换
 *              String --> char[] : 调用String的toCharArray()方法；
 *              char[] --> String : 调用String的构造函数
 *
 *          3. String 与 byte[] 的转换：
 *              编码：String --> byte[] : 调用String的getBytes();
 *              解码：byte[] --> String : 调用String的构造器
 *              note:编码和解码时需要使用相同的字符集，否则会出现乱码
 *              编码：字符串-->字节
 *              解码：编码的逆过程
 */
public class StringTest1 {
    @Test
    // 1. String与基本数据类型、包装类之间的转换
    public void test1() {
        String str1 = "123";

        // int num1 = (int)str1; // error，不可以使用强制转换,强制转换只适用于子父类关系
        int num1 = Integer.parseInt(str1);

        String s2 = num1 + "";   // 1. 使用连接符进行转换，此时s2在堆中
        String s3 = String.valueOf(num1); // 2. 使用valueof进行转换，此时s3在堆中

        System.out.println(str1 == s2); // false
        System.out.println(str1 == s3); // false

    }

    @Test
    // 2. String与char[]的转换
    public void test2() {
        String str1 = "abc123";

        // 2.1 String --> char[] : 调用toCharAyyay()方法
        char[] ch = str1.toCharArray();
        for (int i = 0; i < ch.length; ++i) {
            System.out.println(ch[i]);
        }

        // 2.2 char[] --> String : 调用String的构造函数
        char[] ch2 = {'h', 'e', 'l', 'l', 'o'};
        String str2 = new String(ch2);
        System.out.println(str2);
    }

    @Test
    // 3. String 与 byte[] 的转换：
    public void test3() throws UnsupportedEncodingException {
        String str1 = "China中国";

        // 3.1 String --> byte[]
        byte[] bytes = str1.getBytes(); // 如果不指定编码字符集，则采用编译器默认的字符集
        System.out.println(bytes[0]);
        System.out.println(Arrays.toString(bytes));
        System.out.println("***************************");

        // 采用指定的字符集合进行转换,此时需要抛出异常
        byte[] gbks = str1.getBytes("gbk");
        System.out.println(Arrays.toString(gbks));
        System.out.println("***************************");

        // 3.2 byte[] -> String : 采用String(byte[] bytes)的构造器
        String str2 = new String(bytes); // 此时会采用编译器默认的字符集进行转换
        System.out.println(str2);

        // 指定转换的字符集
        String str3 = new String(gbks);
        System.out.println(str3);

        String str4 = new String(gbks, "gbk");
        System.out.println(str4);

    }
}

```



## 6. String、StringBuffer、StringBuilder的异同：

String->StringBuilder/StringBuffer: 调用StringBuffer、StringBuilder的构造器

StringBuffer/StringBuilder -> String: 调用String的构造器；或者使用toString()方法



String、StringBuilder、StringBuffer三者的异同：

- 效率 ：StringBuilder > StringBuffer > String

- String: 不可变的字符序列，底层使用char[]存储

- StringBuffer:可变的字符序列；线程安全的，效率低；底层使用char[]存储

  - 扩容问题：如果要添加的数据底层数组盛不下了，那就需要扩容底层的数组。
  - 默认情况下，扩容为原来容量的2倍+2，同时将原有数组中的元素复制到新的数组中。

  ```java
  @HotSpotIntrinsicCandidate
  public StringBuffer() {
  	super(16);
  }
  
  /**
  * Constructs a string buffer initialized to the contents of the
  * specified string. The initial capacity of the string buffer is
  * {@code 16} plus the length of the string argument.
  *
  * @param   str   the initial contents of the buffer.
  */
  @HotSpotIntrinsicCandidate
  public StringBuffer(String str) {
  	super(str.length() + 16);
  	append(str);
  }
  ```

  

- StringBuilder: 可变的字符序列；jdk5.0新增的，为线程不安全的，效率高；底层使用char[]存储

- 指导意义：开发中建议大家使用 StringBuffer(int capacity) 或 StringBuilder(int capacity)

```java
public void test01() {
    StringBuffer strBuff1 = new StringBuffer("abc");
    System.out.println(strBuff1.length()); // 3
    System.out.println(strBuff1.capacity()); // 19

    strBuff1.setCharAt(0,'m');
    System.out.println(strBuff1); // mbc

    strBuff1.append("hello");
    System.out.println(strBuff1.length()); // 8
    System.out.println(strBuff1.capacity()); // 19
}
```



- 效率测试代码：

  ```java
  public void test02() {
          // String\StringBuffer\StringBuilder三者效率的对比
          long startTime = 0L;
          long endTime = 0L;
  
          String test = "";
          StringBuffer buffer = new StringBuffer();
          StringBuilder builder = new StringBuilder();
  
          // 开始对比:StringBuffer
          startTime = System.currentTimeMillis();
          for (int i = 0; i < 20000; ++i) {
              // public static String valueOf(int i) : 返回int参数的字符串表示形式。
              buffer.append(String.valueOf(i));
          }
          endTime = System.currentTimeMillis();
          System.out.println("Buffer runtime:" + (endTime - startTime));
  
          // 开始对比:StringBuilder
          startTime = System.currentTimeMillis();
          for (int i = 0; i < 20000; ++i) {
              // public static String valueOf(int i) : 返回int参数的字符串表示形式。
              builder.append(String.valueOf(i));
          }
          endTime = System.currentTimeMillis();
          System.out.println("Builder runtime:" + (endTime - startTime));
  
          // 开始对比:String
          startTime = System.currentTimeMillis();
          for (int i = 0; i < 20000; ++i) {
              test += i;
          }
          endTime = System.currentTimeMillis();
          System.out.println("String runtime:" + (endTime - startTime));
      }
  ```

  



## 7. StringBuffer类

- java.lang.StringBuffer代表可变的字符序列，JDK1.0中声明，可以对字符串内容进行增删，此时不会改变新的对象。

- 很多方法与String相同

- 作为参数传递时，方法内部可以改变值

- ```java
  abstract class AbstractStringBuilder implements Appendable, CharSequence {
      /**
       * The value is used for character storage.
       */
      byte[] value; // value没有声明为final，因此可以不断扩容
  
      /**
       * The id of the encoding used to encode the bytes in {@code value}.
       */
      byte coder; 
      
      /**
       * The count is the number of characters used.
       */
      int count; // count 记录有效字符的个数
  }
  ```

  

### 1. StringBuffer类

StringBuffer类不同于String，其对象必须使用构造器生产。有三个构造器：

- StringBuffer(): 初始容量为16的字符串缓冲区

- StringBuffer(int size): 构造指定容量的字符串缓冲区

- StringBuffer(String str): 将内容初始化为指定字符串内容

- ```java
  String s = new String("我喜欢学习");
  StringBuffer buffer = new StringBuffer("我喜欢学习");
  buffer.append("数学");
  ```

  ![image-20220213154234140](D:\Program Files (x86)\JavaProject\2-Java高级部分\2-Java常用类\README.assets\image-20220213154234140.png)

### 2. StringBuffer的常用方法

```
StringBuffer append(xxx) : 提供了很多的append()方法，用于进行字符串拼接操作
StringBuffer delete(int start, int end): 删除指定位置[start, end)位置的内容
StringBuffer replace(int start, int end, String str): 把[start, end)位置替换为str字符串
StringBuffer insert(int offset, xxx): 在指定位置插入xxx
StringBuffer reveres(): 把当前字符序列逆转
```

- 当append和insert时，如果之前的value数组长度不够，可扩容

- 如上这些方法支持方法链操作（链式操作，即a.append().append()...）

- 方法链原理：

- ```
  public synchronized StringBuffer append(String str) {
      toStringCache = null;
      super.append(str);
      return this; // 因为这里返回了this，所以可以进行链式操作
  }
  ```

```
其他常用方法：
public int indexOf(String str) : 返回指定子字符串str第一次出现的字符串中的索引。
public String substring(int start, int end): // 返回一个从start开始到end索引位置的子字符串
public int length()：查找字符序列的长度
public char charAt()：查找第n个索引位置的元素
public void setCharAt(int n, char ch) : 修改第n个索引位置的元素为ch
```



- 总结：

  增：append(xxx)

  删：delete(int start, int end)

  改：setCharAt(int n, char ch) / replace(int start, int end, String str)

  查：charAt(int n)

  插：insert(int offset, xxx)

  长度：length()

  **遍历**：for() + charAt() / toString()



## 8. Java中的日期时间API

### 1. JDK8之前日期时间API

#### 1.1  java.lang.System类：

System类提供的`public static long currentTimeMilli()`用来返回的当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。

**此方法适用于计算时间差**

- 计算时间时间的主要标准有：
  - UTC(Coordinated Universal Time)
  - GMT(Gerrnwich Mean Time)
  - CST(Central Standard Time)

#### 1.2 java.util.Date类：

表示特定的瞬间，精确到毫秒

- 构造器：
  - Date(): 使用无参构造器创建的对象可以获取本地当前时间
  - Date(long date)
- 常用方法：
  - getTime(): 返回自1970年1月1日0时0分0秒 GMT以来此Date对象表示的毫秒数
  - toString(): 把此Date对象转换为以下形式的String: dow mon dd hh:mm:ss zzz yyy 其中，dow是一周中的某一天（Sun,Mon,Tue,Wed,Thu,Fri,Sat)，zzz是时间标准。
  - 其他很多方法都过时了

3. java.sql.Date类：

   ```
   |----java.sql.Date类：表示数据库下的时间
   *          1. 如何实例化 java.sql.Date date = new java.sql.Date();
   *          2. 如何将java.sql.Date对象与java.util.Date对象进行转换
   ```

   ```
   // 方式一：直接强制转换，不推荐
   java.sql.Date date5 = (java.sql.Date) date4;
   System.out.println(date4.toString());
   System.out.println(date5.toString());
   
   // 方式二：
   Date date6 = new Date();
   java.sql.Date date7 = new java.sql.Date(date6.getTime());
   System.out.println(date6);
   System.out.println(date6.toString());
   ```




#### 1.3 java.text.SimpleDateFormat类

- Date类的API不易于国际化，大部分被废弃了，`java.text.SimpleDateFormat`类是一个不与语言环境有关的方式来格式化和解析日期的具体类。
- 它允许进行格式化：日期 -> 文本、解析：文本 -> 日期
- 格式化：
  - `SimpleDateFormat()`:默认的模式和语言环境创建对象
  - `public SimpleDateFormat(String pattern)` : 该构造方法可以用参数pattern指定的格式创建一个对象，该对象调用：
  - `public String format(Date date)` ： 方法格式化时间对象date
- 解析：
  - `public Date parse(String source)` : 从给定字符串的开始解析文本，以生产一个日期。

```java
public void simpleDateFormatTest() throws ParseException {
        // 1. SimpleDateFormat初始化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 2. 格式化： 日期->字符串
        Date date = new Date();
        System.out.println(date);

        String format = simpleDateFormat.format(date);
        System.out.println(format);

        // 3. 解析：格式化的逆过程，字符串 -> 日期
        String str = "2022/2/14 下午5:16";
        Date date2 = simpleDateFormat.parse(str); // parse函数需要抛出异常
        System.out.println(date2);
    }

    public void simpleDateFormateTest2() throws ParseException {
        // 1. 利用指定的方式进行格式化和解析
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 2020-10-09 10:02:29 格式的
        Date date = new Date();

        // 2. 格式化：日期->字符串
        String str = sdf.format(date);
        System.out.println(str);

        // 3. 解析：格式化的逆过程: 字符串->日期
        // note: 解析要求字符串必须是符合SimpleDateFormat识别的格式（通过构造器参数体现），否则会抛出异常
        String str2 = "2020-10-09 10:02:29";
        Date date2 = sdf.parse(str2);
        System.out.println(date2);
    }
```



#### 1.4 java.util.Calendar类

- Calendar是一个抽象基类，主要用于完成日期字段之间相互操作的功能。

- 获取Calendar实例的方法：

  - 使用Calendar.getInstance()方法
  - 调用它的子类GregorianCalendar的构造器

- 一个Calendar的实例是系统时间的抽象表示，通过get(int field)方法来取得想要的时间信息。比如YEAR、MONTH、DAY_OF_MONTH、DAY_OF_WEEK、HOUR_OF_DAY、MINUTE、SECOND

  - public int get(int field);
  - public void set(int field, int value);
  - public final Date getTime()
  - public final void setTime(Date date)

- 注意：

  - 获取月份时：一月是0，二月是1，以此类推，12月是11
  - 获取星期时：周日是1，周二是2，。。。周六是7

  ```java
  package JavaDate;
  
  import org.junit.Test;
  
  import java.util.Calendar;
  import java.util.Date;
  import java.util.GregorianCalendar;
  
  /**
   * @ClassName: JavaDate
   * @Description: Java - JDK8.0之前的Calendar日历类测试（Calendar为抽象基类）
   * @author: zhilx (zhilx1997@sina.com)
   * @version: v1.0
   * @data: 2022/2/16 9:44
   * @node:
   *          . 1. System类中的currentTimeMillis();
   *            2. java.util.Date和子类java.sql.Date; (Date不利用国际化的操作)
   *            3. SimpleDateFormat
   *            4. Calendar
   */
  public class CalendarTest {
      @Test
      /* Calendar是一个**抽象基类**，主要用于完成日期字段之间互相操作的功能。 */
      public void test() {
          // 1. 获取Calendar实例的方法：
          // 1.1 使用Calendar.getInstance()方法
          Calendar cal1 = Calendar.getInstance();
  
          // 1.2 调用它的子类GregorianCalendar的构造器
          Calendar cal2 = new GregorianCalendar();
  
          // 2. 常用方法
          // 2.1 get() : 获取Calendar的指定时间
          System.out.println("***********get() function*************");
          int day = cal1.get(Calendar.DAY_OF_MONTH); // Calendar.DAY_OF_MONTH: 获取cal1为当月的第几天
          System.out.println(day);
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
  
          // 2.2 set(int field, int value):
          System.out.println("***********set() function*************");
          cal1.set(Calendar.DAY_OF_MONTH, 1);
          System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
  
          // 2.3 add()
          System.out.println("***********add() function*************");
          cal1.add(Calendar.DAY_OF_MONTH, 5); //  根据日历的规则，将指定的时间量添加或减去给定的日历字段
          cal1.add(Calendar.DAY_OF_MONTH, -1); //  根据日历的规则，将指定的时间量添加或减去给定的日历字段
          System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
  
          // 2.4 getTime(): Calendar ---> Date类
          System.out.println("***********getTime() function*************");
          Date date1 = cal1.getTime(); // 返回一个Date类型的返回值
          System.out.println(date1);
  
          // 2.5 setTime(): Date类 ---> Calendar
          System.out.println("***********getTime() function*************");
          Date date2 = new Date();
          cal1.setTime(date2);
          System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
      }
  }
  
  ```

  

### 2. JDK8中的新日期时间API

#### 2.1 新时间日期API出现的背景

我们希望时间与昼夜和四季有关，使得事情较为复杂。JDK 1.0中包含了一个java.util.Date类，但是它的大多数方法已经在JDK 1.1引入Calendar类之后被弃用了。而Calendar并不比Date好多少。他们面临的问题是：

- 可变性：像日期和时间这样的类应该是不可变的。
- 偏移性：Date中的年份是从1900开始的，而月份都是从0开始
- 格式化：格式化只对Date有用，Calendar则不行
- 此外，它们也不是线程安全的；不能处理闰秒等。

总结：对日期和时间的操作一直是Java程序员最痛苦的地方之一。

#### 2.2 新时间日期API

- 第三次引入的API是成功的，并且Java8中引入的java.time API已经纠正了过去的缺陷，将来很长一段时间内它都会为我们服务。
- Java 8吸收了Joda-Time的精华，以一个新的开始为Java创建优秀的API。**新的java.time中包含了所有关于本地日期（LocalDate）、本地时间（LocalTime）、本地日期时间（LocalDateTime）、时区（ZonedDateTime）和持续时间（Duration）的类**。历史悠久的**Date类新增了toInstant()方法**，用于把Date转换成新的表示形式。这些新增的本地化时间日期API大大简化了日期时间和本地化的管理。

API：

1. java.time - 包含值对象的基础包
2. java.time.chrono - 提供不同的日历系统的访问
3. java.time.format - 格式化和解析时间和日期
4. jata.time.temporal - 包括底层框架和扩展特性
5. java.time.zone - 包含时区支持的类

说明：大多数开发者只能用到基础包和format包，也可能会用到temporal包。因此，尽管有68个新的公开特性，大多数开发者，大概只会用到其中的三分之一。



**常用方法：**

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| now() / * noew (Zoneld zone)                                 | 静态方法，根据当前时间创建对象/指定时区的对象                |
| of()                                                         | 静态方法，根据指定日期/时间创建对象；设置指定的日期和时间，没有偏移量 |
| getDaOfMonth() / getDayOfYear()                              | 获取月份天数（1-31）/ 获取年份天数（1-366）                  |
| getDayOfWeek()                                               | 或得星期几（返回一个DayofWeek枚举值）                        |
| getMonth()                                                   | 获得月份，返回一个Month枚举值                                |
| getMonthValue() / getYear()                                  | 获得月份(1-12)/获得年份                                      |
| getHour() / getMinute() / getSecond()                        | 获得当前对象对应的小时、分钟、秒                             |
| withDayOfMonth() / withDayOfYear() / withMonth() / withYear() | 将月份天数、年份天数、月份、年份修改为指定的值并返回新的对象 |
| plusDays(), plusWeeks(), plusMonths(), plusYears(), plusHours() | 向当前对象添加几天、几周、几个月、几年、几小时               |
| minusDays(),  minusWeeks(), minusMonths(), minusYears(), minusHours() | 从当前对象减去几月、几周、几天、几年、几小时                 |

note: LocalTime、LocalDate、LocalDateTime具有不可变性，且LocalDateTime的使用频率较高

```java
package JavaDate;

import org.junit.Test;

import java.time.*;

/**
 * @ClassName: JavaDate
 * @Description: Java - JDK8.0的新日期时间API：java.time
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/16 11:18
 * @node:   新时间日期API
 *          - 第三次引入的API是成功的，并且Java8中引入的java.time API已经纠正了过去的缺陷，将来很长一段时间内它都会为我们服务。
 *          - Java 8吸收了Joda-Time的精华，以一个新的开始为Java创建优秀的API。**新的java.time中包含了所有关于本地日期（LocalDate）、本地时间（LocalTime）、本地日期时间（LocalDateTime）、时区（ZonedDateTime）和持续时间（Duration）的类**。历史悠久的**Date类新增了toInstant()方法**，用于把Date转换成新的表示形式。这些新增的本地化时间日期API大大简化了日期时间和本地化的管理。
 *
 *          API：
 *
 *          1. java.time - 包含值对象的基础包
 *          2. java.time.chrono - 提供不同的日历系统的访问
 *          3. java.time.format - 格式化和解析时间和日期
 *          4. jata.time.temporal - 包括底层框架和扩展特性
 *          5. java.time.zone - 包含时区支持的类
 */
public class JDK8TimeClassTest {
    @Test
    public void test01() {
        // LocalTime\LocalDate\LocalDateTime的测试
        // 1. 实例化对象
        // 1.1 通过now()方法
        System.out.println("******* now() 方法实例化对象 **********");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // 1.2 通过of()方法实例化对象，此时需要指定相应的日期和时间
        System.out.println("******* of() 方法实例化对象 **********");
        LocalDate localDate1 = LocalDate.of(2021, 12, 01);
        LocalTime localTime1 = LocalTime.of(15, 31, 20);
        LocalDateTime localDateTime1 = LocalDateTime.of(2021,12,02, 20,01,43);
        System.out.println(localDate1);
        System.out.println(localTime1);
        System.out.println(localDateTime1);

        // 2. getxxx()方法获取time的对象
        System.out.println("******* getxxx() 方法 **********");
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());

        // 3. withxxx()方法进行设置，可证明time的不可变性
        System.out.println("******* withxxx() 方法 **********");
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(12);
        System.out.println("now time   : " + localDateTime);
        System.out.println("Modify time: " + localDateTime2);

        // 4. plusxxx()，可证明time的不可变性
        System.out.println("******* plusxxx() 方法 **********");
        LocalDateTime localDateTime3 = localDateTime.plusYears(1).plusMonths(1).plusDays(1); // 加一年，一月，一天
        System.out.println("now time   : " + localDateTime);
        System.out.println("Modify time: " + localDateTime3);

        // 5. minusxxx()，可证明time的不可变性
        System.out.println("******* withxxx() 方法 **********");
        LocalDateTime localDateTime4 = localDateTime.minusYears(1).minusMonths(1).minusDays(1); // 减一年，一月，一天
        System.out.println("now time   : " + localDateTime);
        System.out.println("Modify time: " + localDateTime4);
    }
}

```



#### 2.3 瞬时：Instant

- Instant：时间线上的一个瞬时点。这可能被用来计算应用程序中的时间时间戳。
- 在处理时间和日期的时候，我们通常会想到年，月，日，时，分，秒。然而，这只是时间的一个模型，是面向人类的。第二种通用模型是面向机器的，或者说是连续的。在此模型中，时间线中的一个点表示为一个很大的数，这有利于计算机处理。在UNIX中，这个数从1970年开始，以秒为单位；同样的，在Java中，也是从1970年开始，但是以毫秒为单位。
- java.time包括值类型Instant提供机器视图，不提供处理人类意义上的时间单位。Instant表示时间线上的一点，而不需要任何上下文信息，例如，时区。概念上讲，它只是简单的表示自1970你1月1日0时0分0秒（UTC）开始的秒数。因为java.time包是基于纳秒计算的，所以Instant的精度可以达到纳秒级。
- 1ns = 10^(-9) s, 1s = 1000 ms = 1000 000 us = 1000 000 000 ns



| 方法                          | 秒数                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| now()                         | 静态方法，返回默认UTC时区的Instant类的对象                   |
| ofEpochMilli(long epochMilli) | 静态方法，返回在1970-01-01 00:00:00基础上加上指定毫秒数之后的Instant类的对象 |
| atOffset(ZoneOffset offset)   | 结合即时的偏移来创建一个OffsetDateTime                       |
| toEpochMilli()                | 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳        |

时间戳是指格林威治时间1970年1月1日00时00分00秒（北京时间1970年01月01日08时00分00秒）起至现在的总秒数。

```java
package JavaDate;

import org.junit.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @ClassName: JavaDate
 * @Description: Java - Instant瞬时测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/16 14:30
 * @node: Instant的使用 ： 类似于java.util.Date类
 *
 */
public class JDK8InstantTest {
    @Test
    public void test01() {
        // 1. Instant的初始化 : 获取本初子午线的时间（格林威治时间，比北京时间小8个小时）
        Instant instant = Instant.now();
        System.out.println(instant);

        // 2. atOffset(ZoneOffset offset) : 结合即时的偏移来创建一个OffsetDateTime
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));// 设置为北京时间
        System.out.println(offsetDateTime);

        // 3. toEpochMilli() : 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        // 4. Instant的另一种实例化方式：通过指定毫秒数
        Instant instant1 = Instant.ofEpochMilli(1644993408397L); // 1644993408397L : 2022-02-16T06:36:48.397Z
        System.out.println(instant1);
    }
}

```



#### 2.4 格式化与解析日期或时间（java.time.format.Date TimeFormatter）

`java.time.format.Date TimeFormatter`类：该类提供了三种格式化方法：

- 预定义的标准格式。如：ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE; ISO_LOCAL_TIME
- 本地化相关的格式。如：`ofLocalizedDateTime(FormatStyle.LONG)`
- 自定义的格式。如：`ofPattern("yyyy-MM-dd hh:mm:ss E")`、
- 用来格式化和解析时间，类似于SimpleDateFormat

| 方法                       | 描述                                                |
| -------------------------- | --------------------------------------------------- |
| ofPattern(String pattern)  | 静态方法，返回一个指定字符串格式的DateTimeFormatter |
| format(TemporalAccessor t) | 格式化一个日期、时间，返回字符串                    |
| parse(CharSequence text)   | 将指定格式的字符序列解析为一个日期、时间            |

```java
package JavaDate;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

import static java.time.format.FormatStyle.FULL;

/**
 * @ClassName: JavaDate
 * @Description: Java - java.time.DateTimeFormat类
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/18 11:02
 * @node: `java.time.format.Date TimeFormatter`类：该类提供了三种格式化方法：
 *      - 预定义的标准格式。如：ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE; ISO_LOCAL_TIME
 *      - 本地化相关的格式。如：`ofLocalizedDateTime(FormatStyle.LONG)`
 *      - 自定义的格式。如：`ofPattern("yyyy-MM-dd hh:mm:ss E")`、
 *      - 用来格式化和解析时间，类似于SimpleDateFormat
 */
public class JDK8DataTimeFormat {
    @Test
    public void test() {
        // 1. 预定义的标准格式。如：ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE; ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        // 2. 格式化操作： 日期->字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);

        System.out.println("************格式化***************");
        System.out.println(localDateTime);
        System.out.println(str1);

        // 2.2 解析：字符串->日期
        TemporalAccessor parse = formatter.parse(str1);
        System.out.println("************解析***************");
        System.out.println(parse);

        // 3.1 本地化相关的格式一。如：`ofLocalizedDateTime(xxx)`: 适用于LocalDateTime
        // FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT
        System.out.println("***********本地格式化：ofLocalDateTime()**************");
        // 这个函数时jdk8.0中的，因此需要指定ZoneID，否则会报错
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        // 将日期和时间转化为字符串：LONG类型的
        String str2 = formatter1.format(localDateTime);// 将日期和时间转化为字符串
        String str3 = formatter2.format(localDateTime);// 将日期和时间转化为字符串
        String str4 = formatter3.format(localDateTime);// 将日期和时间转化为字符串
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);

        // 3.2 本地化相关格式二： ofLocalizedDate()
        // FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT
        System.out.println("***********本地格式化：ofLocalDate()**************");
        // 这个函数时jdk8.0中的，因此需要指定ZoneID，否则会报错
        DateTimeFormatter formatter10 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter11 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter12 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter13 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        // 将日期和时间转化为字符串：LONG类型的
        String str11 = formatter10.format(LocalDate.now());// 将日期和时间转化为字符串
        String str12 = formatter11.format(LocalDate.now());// 将日期和时间转化为字符串
        String str13 = formatter12.format(LocalDate.now());// 将日期和时间转化为字符串
        String str14 = formatter13.format(LocalDate.now());// 将日期和时间转化为字符串
        System.out.println(str11);
        System.out.println(str12);
        System.out.println(str13);
        System.out.println(str14);

        // 4. 重点：方式三：自定义的格式，如ofPattern("yyyy-MM-dd hh:mm:ss")
        System.out.println("***********ofPattern()***************");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str5 = formatter4.format(LocalDateTime.now());
        System.out.println(str5);

    }
}


***************输出************
************格式化***************
2022-02-18T11:44:36.691812500
2022-02-18T11:44:36.6918125
************解析***************
{},ISO resolved to 2022-02-18T11:44:36.691812500
***********本地格式化：ofLocalDateTime()**************
2022年2月18日 CST 上午11:44:36
2022年2月18日 上午11:44:36
2022/2/18 上午11:44
***********本地格式化：ofLocalDate()**************
2022年2月18日星期五
2022年2月18日
2022年2月18日
2022/2/18
***********ofPattern()***************
2022-02-18 11:44:36

```



#### 2.5 其它API

- Zoneld: 该类中包含了所有的时区信息，一个时区的ID，如Europe/Paris。
- ZonedDateTime: 一个在ISO-8601日历系统时区的日期时间，如2007-12-03T10:15:30+01:00 Europe/Paris
  - 其中每个时区都对应着ID，地区ID都为“{区域}/{城市}”的格式，例如: Asia/Shanghai等。
- Clock：使用时区提供对当前即时、日期和时间的访问的时钟。
- 持续时间：Duration，用于计算两个“时间”间隔
- 日期间隔：Period，用于计算两个“日期”间隔
- TemporalAdjuster: 时间校正器。有时我们可能需要获取例如：将日期调整到“下一个工作日”等操作。
- TemporalAdjusters:  该类通过静态方法（firstDayOfXxx() / lastDayOfXxx() / nextXxx()提供了大量的常用TemporalAdjuster的实现）

```java
public void test2() {
        // ZoneId: 类中包含了所有的时区信息
        // ZoneId的getAvailableZoneIds() : 获取所有的ZoneId
        System.out.println("---------获取所有时区信息---------------");
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String s : zoneIds) {
            System.out.println(s);
        }

        // ZoneId的of()；获取指定时区的时间
        System.out.println("----------获取指定时区信息--------------");
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(localDateTime);

        // ZonedDateTime: 带时区的日期时间
        // ZonedDateTime的now()：获取本时区的ZonedDateTime对象
        System.out.println("------------获取带时区的日期时间: ZonedDateTime.now()-----------");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(); // 2022-02-18T12:12:06.829560800+08:00[Asia/Shanghai]
        System.out.println(zonedDateTime);

        // ZonedDateTime的now(ZoneId id)：
        System.out.println("-----------zonedDateTime.now(ZoneId.of(\"Asia/Tokyo\"));--------------");
        ZonedDateTime zonedDateTime1 = zonedDateTime.now(ZoneId.of("Asia/Tokyo")); // 2022-02-18T13:12:06.829560800+09:00[Asia/Tokyo]
        System.out.println(zonedDateTime1);
    }
```



#### 5. 参考：与传统日期处理的转换

| 类                                                        | To遗留类                              | From遗留类                  |
| --------------------------------------------------------- | ------------------------------------- | --------------------------- |
| java.time.Instant 与 java.util.Date                       | Date.form(instant)                    | date.toInstant()            |
| java.time.Instant 与 java.sql.Timestamp                   | Timestamp.from(instant)               | timestamp.toInstant()       |
| java.time.ZonedDateTime / java.util.GregorianCalendar     | GregorianCalendar.from(zonedDateTime) | cal.toZonedDateTime()       |
| java.time.LocalDate / java.sql.Time                       | Date.valudOf(localDate)               | date.toLocalDate()          |
| java.time.LocalTime / java.sql.Time                       | Date.valueOf(localDate)               | date.toLocalTime()          |
| java.time.LocalDate / java.sql.Timestamp                  | Timestamp.valueOf(localDate Time)     | timestamp.toLocalDateTime() |
| java.time.ZoneId / java.util.TimeZone                     | timeZone.getTimeZone(id)              | timeZone toZoned()          |
| java.time.format.DateTimeFormatter / java.text.DateFormat | formatter.toFormat()                  | 无                          |



## 9. Java比较器

Java对象中，正常情况下，只能进行比较：== 或 !=。不能使用 > 或 < 的。

但是在开发场景中，我们需要对多个对象进行排序，言外之意，就需要比较对象的大小。

如何实现？使用两个接口中的任何一个：Comparable 或 Comparator

### 1. Comparable接口的使用举例：（自然排序）

1. 像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象的实现方式
2. 像String、包装类重写compareTo(obj)方法以后，默认为进行了从小到大的排序
3. 重写compareTo(obj)的规则：
    - 如果当前对象this小于形参对象obj，则返回正整数
    - 如果当前对象this大于形参对象obj，则返回负整数
    - 如果当前对象this等于形参对象obj，则返回零。
4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Compareble类，重写compareTo()方法并指明如何排序

```java
public void test01() {
    // String类的sort排序
    String[] arr = {"KKK", "AAA", "CC", "II", "ZZ", "DD", "GG"};

    Arrays.sort(arr); // [AAA, CC, DD, GG, II, KKK, ZZ]

    System.out.println(Arrays.toString(arr));
}

public void test02() {
    Goods[] goods = new Goods[5];
    goods[0] = new Goods("xiaomi Phone", 1999);
    goods[1] = new Goods("Huawei Phone", 2999);
    goods[2] = new Goods("Lenovo Phone", 1999);
    goods[3] = new Goods("Oppo Phone", 2400);
    goods[4] = new Goods("vivo Phone", 2599);

    for (int i = 0; i < goods.length; ++i) {
        System.out.println(goods[i].getName() + ", " + goods[i].getPrice());
    }
    System.out.println(Arrays.toString(goods));

    Arrays.sort(goods);
    System.out.println("After sort");
    for (int i = 0; i < goods.length; ++i) {
        System.out.println(goods[i].getName() + ", " + goods[i].getPrice());
    }
    System.out.println(Arrays.deepToString(goods));
}
```



### 2. 方式二：定制排序：java.util.Comparator

- 当元素类型没有实现 `java.lang.Comparable`接口而又不方便修改代码，或者实现了 `java.lang.Comparable`接口的排序规则不适合当前的操作，那么可以考虑使用Comparator的对象来排序，强行对多个对象进行整体排序的比较
- 重写compare(Object o1, Object o2)方法，比较o1和o2的大小：如果方法返回正整数，则表示o1大于o2；如果返回0，表示相等；如果返回负整数，表示o1小于o2。
- 可以将Comparator传递给sort方法（如Collections.sort或Arrays.sort），从而允许在排序顺序上实现精度控制。
- 还可以使用Comparator来控制某些数据结构（如有序set或有序映射）的顺序，或者为那些没有自然顺序的对象collection提供排序。

```java
package CompareTo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName: ComparatorTest
 * @Description: Java - Comparator接口比较
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/19 10:58
 * @node: 方式二：定制排序：java.util.Comparator
 *        1  - 当元素类型没有实现 `java.lang.Comparable`接口而又不方便修改代码，
 *          或者实现了 `java.lang.Comparable`接口的排序规则不适合当前的操作，
 *          那么可以考虑使用Comparator的对象来排序，强行对多个对象进行整体排序的比较
 *        2  - 重写compare(Object o1, Object o2)方法，比较o1和o2的大小：
 *          如果方法返回正整数，则表示o1大于o2；
 *          如果返回0，表示相等；
 *          如果返回负整数，表示o1小于o2。
 *        3  - 可以将Comparator传递给sort方法（如Collections.sort或Arrays.sort），从而允许在排序顺序上实现精度控制。
 *        4  - 还可以使用Comparator来控制某些数据结构（如有序set或有序映射）的顺序，或者为那些没有自然顺序的对象collection提供排序。
 *
 *
 */
public class ComparatorTest {
    @Test
    public void test01() {
        // String类的sort排序,指定为从大到小
        String[] arr = {"KKK", "AAA", "CC", "II", "ZZ", "DD", "GG"};

        Arrays.sort(arr, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof  String && o2 instanceof String) {
                    String str1 = (String) o1;
                    String str2 = (String) o2;

                    return str2.compareTo(str1);
                }

                throw new RuntimeException("数据输入类型不匹配！");
            }
        }); // [ZZ, KKK, II, GG, DD, CC, AAA]

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2() {
        Goods[] goods = new Goods[6];
        goods[0] = new Goods("xiaomi Phone", 1999);
        goods[1] = new Goods("Huawei Phone", 2999);
        goods[2] = new Goods("Lenovo Phone", 1999);
        goods[3] = new Goods("Oppo Phone", 2400);
        goods[4] = new Goods("vivo Phone", 2599);
        goods[5] = new Goods("xiaomi Phone", 3299);

        // 指明商品的排序方式：名称从小到大,价格从高到低
        Arrays.sort(goods, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Goods && o2 instanceof Goods) {
                    Goods goods1 = (Goods) o1;
                    Goods goods2 = (Goods) o2;

                    if (goods1.getName().equals(goods2.getName())) {
                        return Double.compare(goods2.getPrice(), goods1.getPrice());
                    } else {
                        return goods1.getName().compareTo(goods2.getName());
                    }
                }
                throw new RuntimeException("数据类型输入不匹配!");
            }
        });

        System.out.println(Arrays.toString(goods));
    }
}

```

### 3. 对比：

- Comparator接口属于临时性的比较，只能使用一次。

- Comparable接口的方式一旦固定，保证Comparable接口实现类的对象在任何位置都可以比较大小。


## 10. System类

- System类代表系统，系统级的很多属性和控制方法都放置在该类的内部。该类位于`java.lang`包

- 由于该类的构造器是private的，所以无法创建该类的对象，也就是无法实例化该类。其内部的成员变量和成员方法都是static的，所以也可以很方便的进行调用。

- 成员变量：

    - System类内部包含in、out和err三个成员变量，分别代表标准输入流（键盘输入），标准输出流（显示器）和标准错误输出流（显示器）

- 成员方法：

    - `native long currentTimeMillis()`
      该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间（格林威治时间）1970年1月1日0时0分0秒所差的毫秒数。

    - `void exit(int status)`
      该方法的作用是退出程序。其中status的值为0代表正常退出，非零代表异常退出。**使用该方法可以在图形界面编程中实现程序的退出功能**等。

    - `void gc()`
      该方法的作用是请求系统进行垃圾回收。至于系统是否立刻回收，则取决于系统中垃圾回收算法的实现以及系统执行时的情况。

    - `String getProperty(String key)`
      该方法的作用是获得系统中属性名为key的属性对应的值。系统中常见的属性名以及作用如下表所示：

      | 属性名       | 属性说明           |
          | ------------ | ------------------ |
      | java.version | java运行时环境版本 |
      | java.home    | java安装目录       |
      | os.name      | 操作系统的名称     |
      | os.version   | 操作系统的版本     |
      | user.name    | 用户的账号名称     |
      | user.home    | 用户的主目录       |
      | user.dir     | 用户的当前工作目录 |


## 11. Math类

`java.lang.Math`提供了一系列静态方法用于科学计算。其方法的参数和返回值类型一般为double型

- abs  绝对值
- acos,asin,atan,cos,sin,tan 三角函数
- sqrt、pow(double a, double b)、log、exp（e为底质数）
- max(double a, double b)、min(double a, double b)
- random() : 返回0.0到1.0的随机数
- long round(double a) double型数据a转换为long型（四舍五入）
- toDegrees(double angrad) : 弧度 -> 角度
- toRadians（double angdeg) : 角度 -> 弧度



## 12. BigInteger与BigDecinmal

### 1. BigInteger类：

- Integer类作为int的包装类，能存储的最大整形值为2^31 - 1，Long类也是有限的，最大为2^63-1.如果要表示再大的数，不管是基本数据类型还是他们的包装类都无能为力，更不用说进行运算了。
- **java.math包的BigInteger可以表示不可变的任意静定的整数**。BigInteger提供所有Java的基本整数操作符的对应物，并提供`java.lang.Math`的所有相关方法。另外，BigInteger还提供以下运算：模算术、GCD计算、质数测试、素数生产、位操作以及一些其他操作。
- 构造器：
    - BigInteger(String val): 根据字符串构建BigInteger对象
- 常用方法
    - public BigInteger abs(): 返回此BigInteger的绝对值的BigInteger
    - public BigInteger add(BigInteger val): 返回其值为this+val的BigInteger
    - public BigInteger subtract(BigInteger val)): 返回其值为this-val的BigInteger
    - public BigInteger multiply(BigInteger val)): 返回其值为this*val的BigInteger
    - public BigInteger divide(BigInteger val)): 返回其值为this/val的BigInteger。整数相除只保留整数部分
    - public BigInteger remainder(BigInteger val)): 返回其值为this%val的BigInteger
    - public BigInteger[] divideAndRemainder(BigInteger val)): 返回包含（this/val）后跟（this%val）的两个BigInteger的数组
    - public BigInteger pow(int val): 返回其值为this^val的BigInteger

### 2. BigDecimal类

- 一般Float和Double类可以用来做科学计算或工厂计算，但在商业计算中，要求数字精度比较高，故用到java.math.BigDecimal类。
- BigDecimal类支持不可变的、任意精度的有符号十进制定点数
- 构造器：public BigDecimal(double val); public BigDecimal(String val);
- 常用方法：
    - public BigDecimal add(BigDecimal augend)
    - public BigDecimal subtract(BigDecimal augend)
    - public BigDecimal mulitply(BigDecimal augend)
    - public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)

```java
package otherClassTest;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
* @ClassName: otherClassTest
* @Description: Java - 其他常用类的测试
* @author: zhilx (zhilx1997@sina.com)
* @version: v1.0
* @data: 2022/2/19 11:38
* @node: 其他常用类的使用
*      1. System
*      2. Math
*      3. BigInteger 和 BigDecimal
*/
public class OtherClassTest {
@Test
// 1. System类的测试
public void test01() {
String str1 = System.getProperty("java.version"); // 获取当前java的版本
String str2 = System.getProperty("java.home"); // 获取java的安装目录
String str3 = System.getProperty("os.name"); // 获取操作系统名称
String str4 = System.getProperty("os.version"); // 获取操作系统的版本
String str5 = System.getProperty("user.name"); // 获取用户的账号名称
String str6 = System.getProperty("user.home"); // 获取用户的主目录
String str7 = System.getProperty("user.dir"); // 获取用户的当前工作目录

        System.out.println("java版本：" + str1);
        System.out.println("java的安装目录：" + str2);
        System.out.println("操作系统名称：" + str3);
        System.out.println("操作系统的版本：" + str4);
        System.out.println("用户的账号名称：" + str5);
        System.out.println("用户的主目录：" + str6);
        System.out.println("用户的当前工作目录：" + str7);
    }

    // 2. BigInteger / BigDecimal
    @Test
    public void test02() {
        BigInteger bi1 = new BigInteger("1238818892381729873979123");
        BigInteger bi2 = new BigInteger("1238818");

        BigInteger bi3 = bi1.divide(bi2);
        System.out.println("bi1 = " + bi1);
        System.out.println("bi2 = " + bi2);
        System.out.println("bi1 / bi2 = " + bi3);

        BigDecimal bd1 = new BigDecimal("123999890892123.901");
        BigDecimal bd2 = new BigDecimal("12331232.21");
        BigDecimal bd3 = bd1.divide(bd2, 15, RoundingMode.HALF_UP); // 保留精度15位，并四舍五入
        // BigDecimal bd4 = bd1.divide(bd2); // 如果不指定精度会报错
        System.out.println("bd1 = " + bd1);
        System.out.println("bd2 = " + bd2);
        System.out.println("bd1 / bd2 = " + bd3);

    }
}
```