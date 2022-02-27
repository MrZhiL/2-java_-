# 十、Java集合

1. Java集合框架概述
2. Collection接口方法
3. Iterator迭代器接口
4. Collection子接口一：List
5. Collection子接口二：Set
6. Map接口
7. Collections工具类

## 10.1 Java集合框架概述

- 一方面，面向对象语言对事物的体现都是以对象的形式，为了方便对多个对象的操作，就要对对象进行存储。另一方面，使用Array存储对象方面具有一些**弊端**，而Java集合就像一种容器，可以**动态地**把多个对象的引用放入容器中。

    - 数组在内存存储方面的特点：
        - 数组初始化以后，其长度就确定了
        - 数组声明的类型，就决定了进行元素初始化时的类型
    - 数组在存储数据方面的弊端：
        - 数组初始化以后，长度就不可变了，不便于扩展
        - 数组中提供的属性和方法少，不便于进行添加、删除、插入等操作，且效率不高。同时无法直接获取存储元素的个数
        - 数组存储的数据时有序的、可以重复的。-->存储数据的特点单一

- Java集合类可以用于存储数量不等的多个**对象**，还可用于保存具有映射关系的关联数组。



## 10.2 Java集合体系：Collection和Map

Java集合可分为Collection和Map两种体系

- Collection接口：单列数据，定义了存取一组对象的方法的集合

    - List：元素有序、可重复的集合
    - Set：元素无序、不可重复的集合

- Map接口：双列数据，用来存储一对（key - value）的数据。

- Collection接口继承树

  ![image-20220224125143484](D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220224125143484.png)

- Map接口继承树

  ​	![image-20220224125230891](D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220224125230891.png)



## 10.3 Collection接口中的API

```java
package src;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName: CollectionTest.java
 * @Description: Java - 集合框架的概述
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/24 11:05
 * @node: 集合框架的概述
 *          1. 集合、数组都是对多个数据进行存储操作的结构，简称Java容器
 *             说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt, .avi, 数据库中的存储等）
 *          2.1 数组在春初多个数据方面的特点：
 *              - 一旦初始化以后，其长度就固定了
 *              - 数组一旦定义好，其元素的类型也就确定了。我们也就只能操作指定类型的数据了，
 *                比如：String[] arr, int[] arr1, Object[] arr2
 *          2.2 数组在存储多个数据方面的缺点：
 *              - 一旦初始化以后，其长度就不可修改
 *              - 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便
 *              - 获取数组中实际元素的灌输的需求，数组没有线程的属性或方法可用
 *              - 数组存储数据的特点：有序、可重复。对于无序、不可重复的需求，不能满足
 *
 *          3. Java集合可分为Collection和Map两种体系
 *              - Collection接口：单列数据，定义了存取一组对象的方法的集合
 *                  - List：元素有序、可重复的集合  --> "动态"数组
 *                    -- ArrayList、LinkedList、Vector
 *                  - Set：元素无序、不可重复的集合  --> "集合"
 *                    -- HashSet、LinkedHashset、TreeSet
*               - Map接口：双列数据，用来存储一对（key - value）的数据。
 *                    -- HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 *          4. Collection接口中的方法的使用:
 *              向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()方法
 */
public class CollectionTest {
    @Test
    public void test01() {
        // 1. 创建Collection接口的变量，需要指定具体的类型，这里使用ArrayList
        Collection coll = new ArrayList();

        // 2. 调用empty()和size()方法
        System.out.println("coll.size = " + coll.size());
        System.out.println("coll.isEmpty() = " + coll.isEmpty());

        // 3. 调用add(Object e) 方法
        System.out.println("---------------------------");
        coll.add("AA");
        coll.add("BB");
        coll.add(123); // 自动装箱
        coll.add(new Date());
        System.out.println("coll.size = " + coll.size());
        System.out.println("coll.isEmpty() = " + coll.isEmpty());

        // 4. 调用addAll(Collection c)方法
        Collection coll1 = new ArrayList();
        coll1.add("CC");
        coll1.add(879);
        coll.addAll(coll1);
        System.out.println("coll.size = " + coll.size() + ", coll = " + coll);

        // 5. clear()方法
        System.out.println("---------------clear()方法------------------");
        coll.clear();
        System.out.println("coll.size = " + coll.size());
        System.out.println("coll.isEmpty() = " + coll.isEmpty());
    }

    @Test
    public void test02() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("jerry", 21));
        coll.add(new Person("Tom", 19));
        coll.add(new String("hello"));
        coll.add(false);

        // 6. 调用contains(Obj e)方法，判断coll中是否存在e对象: 若存在则返回true，不存在则返回false
        // String()类中自动重写了equals()方法
        System.out.println("---------------contains()-----------------");
        System.out.println(coll.contains(new String("hello"))); // true
        // 调用contains()方法时，编译器会自动对coll的对象依次进行比较（按照add的顺序进行比较）
        System.out.println(coll.contains(new Person("jerry", 21))); // 如果不重写实现类中的equals()方法则返回false，如果重写则返回true

        // 7. containsAll(Collection c): 判断c中的对象是否被包含, 如果都被包含则返回true，否则返回false
        System.out.println("-------------containsAll(Collection c)------------------");
        Collection coll1 = Arrays.asList(123, 456);
        Collection coll2 = Arrays.asList(123, 4567);
        System.out.println(coll.containsAll(coll1)); // true
        System.out.println(coll.containsAll(coll2)); // false

    }

    @Test
    public void test03() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("jerry", 21));
        coll.add(new Person("Tom", 19));
        coll.add(new String("hello"));
        coll.add(false);
        System.out.println("coll = " + coll);

        // 8. remove(obj) : 从当前集合中移除指定的obj元素，如果移除成功则返回true；失败则返回false
        System.out.println("---------remove(obj)-----------");
        coll.remove("hello"); // true
        coll.remove("123");   // false
        System.out.println("coll = " + coll);

        // 9. removeAll(Collection c) : 从当前集合中移除Collection c中存在的所有元素：即取交集
        System.out.println("----------removeAll(Collection c)----------------");
        Collection coll1 = Arrays.asList(123, new Person("jerry", 21), "false");
        coll.removeAll(coll1);
        System.out.println("coll = " + coll);
    }

    @Test
    public void test04() {
        // 10. retainAll()方法：
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("jerry", 21));
        coll.add(new Person("Tom", 19));
        coll.add(new String("hello"));
        coll.add(false);
        System.out.println("coll = " + coll);

        // 11. equals(obj): 判断当前对象是否与obj元素是否相同（ArrayList为有序的，因此需要顺序也一样；HashSet可以无序），需要所有元素都相同
        System.out.println("----------------equals(obj)---------------------");
        Collection coll2 = new ArrayList();
        coll2.add(123);
        coll2.add(456);
        coll2.add(new Person("jerry", 21));
        coll2.add(new Person("Tom", 19));
        coll2.add(new String("hello"));
        coll2.add(false);
        System.out.println("coll == coll1 ? " + coll.equals(coll2)); // true

        Collection coll3 = new ArrayList();
        coll3.add(456);
        coll3.add(123);
        coll3.add(new Person("jerry", 21));
        coll3.add(new Person("Tom", 19));
        coll3.add(new String("hello"));
        coll3.add(false);
        System.out.println("-----------------------------------");
        System.out.println("coll == coll1 ? " + coll.equals(coll3)); //false, 因为ArrayList为有序的，而123 和 456元素位置不一样

        // retainAll(Collection coll): 返回当前集合与coll集合的交集，并返回给当前集合
        Collection coll1 = Arrays.asList(123, "hello", false);
        coll.retainAll(coll1);
        System.out.println("--------------retainAll(coll)---------------");
        System.out.println("coll = " + coll);
    }

    @Test
    public void test05() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("jerry", 21));
        coll.add(new Person("Tom", 19));
        coll.add(new String("hello"));
        coll.add(false);
        System.out.println("coll = " + coll);

        // 12. hashCode()
        System.out.println("coll.hashCode = " + coll.hashCode());

        // 13. toArray() : 将集合转化为数组，返回值为Object类型
        System.out.println("-------------Collection to Array()----------------");
        Object[] objects = coll.toArray();
        for (int i = 0; i < objects.length; ++i) {
            System.out.println(objects[i]);
        }

        // 14. 扩展：数组->集合的转换（Arrays.asList()）,需要注意的是如果直接使用int[] {1, 2}则会识别为一个元素，Integer[] {1, 2}则被识别为两个元素
        List<String> strings = Arrays.asList(new String[]{"abc", "def", "AAA"});
        System.out.println(strings);

        List arr1 = Arrays.asList(new int[]{111, 222});
        System.out.println("arr1 = " + arr1 + ", arr1.length = " + arr1.size()); // arr1 = [[I@5427c60c], arr1.length = 1

        List arr2 = Arrays.asList(new Integer[] {111, 222});
        List arr3 = Arrays.asList(111, 222);

        System.out.println("arr2 = " + arr2 + ", arr2.length = " + arr2.size()); // arr2 = [111, 222], arr2.length = 2
        System.out.println("arr3 = " + arr3 + ", arr3.length = " + arr3.size()); // arr3 = [111, 222], arr3.length = 2
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Person equals() ...");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

```



## 10.4 Iterator迭代器接口

- Iterator对象称为迭代器（设计模式的一种），主要用于遍历Collection集合中的元素。

- **GOF给迭代器模式的定义为：提供一种方法访问一个容器（container）对象中的各个元素，而又不需暴露该对象的内部描述**。**迭代器模式，就是为容器而生。**

- Collection接口继承了java.lang.Iterator接口，该接口有一个iterator()方法，那么所有实现了Collection接口的集合类都有一个iterator()方法，用以返回一个实现了Iterator接口的对象。

- **Iterator仅用于遍历集合**，Iterator本身并不提供承载对象的能力。如果需要创建Iterator对象，则必须有一个被迭代的集合。

- **集合对象每次调用iterator()方法都得到一个全新的迭代器对象**，默认游标都在集合的第一个元素之前。

- 迭代器内部定义了remove() ：可以在遍历的时候，删除集合中的元素。此方法不同于集合直接调用remove()方法

  注意：

    - Iterator可以删除集合中的元素，但是遍历过程中通过迭代器对象的remove方法，不是集合对象的remove方法。
    - 如果还未调用next()或在上一次调用next方法之后已经调用了remove方法，再调用remove都会报IllegalStateException.

- ```java
  package src;
  
  import org.junit.Test;
  
  import java.util.ArrayList;
  import java.util.Collection;
  import java.util.Iterator;
  
  /**
   * @ClassName: IteratorTest
   * @Description: Java - Iterator迭代器
   * @author: zhilx (zhilx1997@sina.com)
   * @version: v1.0
   * @data: 2022/2/25 20:24
   * @node:
   *          - Iterator对象称为迭代器（设计模式的一种），主要用于遍历Collection集合中的元素。
   *          - **GOF给迭代器模式的定义为：提供一种方法访问一个容器（container）对象中的各个元素，而又不需暴露该对象的内部描述**。**迭代器模式，就是为容器而生。**
   *          - Collection接口继承了java.lang.Iterator接口，该接口有一个iterator()方法，那么所有实现了Collection接口的集合类都有一个iterator()方法，用以返回一个实现了Iterator接口的对象。
   *          - **Iterator仅用于遍历集合**，Iterator本身并不提供承载对象的能力。如果需要创建Iterator对象，则必须有一个被迭代的集合。
   *          - **集合对象每次调用iterator()方法都得到一个全新的迭代器对象**，默认游标都在集合的第一个元素之前。
   */
  public class IteratorTest {
      @Test
      public void test06() {
          // 15. Iterator迭代器：返回Iterator接口的实例，用于遍历集合元素。放在Iterator.java中
          Collection coll = new ArrayList();
          coll.add(123);
          coll.add(456);
          coll.add(new Person("jerry", 21));
          coll.add(new Person("Tom", 19));
          coll.add(new String("hello"));
          coll.add(false);
          System.out.println("coll = " + coll);
  
          // 使用Iterator迭代器对集合进行遍历
          Iterator iterator = coll.iterator();
  
          // 方式一：直接使用next()方法进行输出
          System.out.println("--------直接输出--------------");
          System.out.println(iterator.next()); // 123
          System.out.println(iterator.next()); // 456
          System.out.println(iterator.next()); // Jerry, 21
          System.out.println(iterator.next()); // Tom, 19
          System.out.println(iterator.next()); // hello
          System.out.println(iterator.next()); // false
  
          // System.out.println(iterator.next()); // 抛出异常java.util.NoSuchElementException，因为coll集合已经被遍历完成
  
          // 方式二：使用for循环(不推荐)
          System.out.println("-------------使用for循环（不推荐）-------------");
          Iterator iterator1 = coll.iterator();
          for (int i = 0; i < coll.size(); ++i) {
              System.out.println(iterator1.next());
          }
  
          // 方式三：使用hasNext()方法与while()调用（开发中优先使用该方法）
          System.out.println("---------使用hasNext()（优先使用）---------");
          Iterator iterator2 = coll.iterator();
          while(iterator2.hasNext()) {
              System.out.println(iterator2.next());
          }
  
      }
  
      @Test
      public void test07() {
          Collection coll = new ArrayList();
          coll.add(123);
          coll.add(456);
          coll.add(new Person("Tom", 19));
          coll.add(new String("hello"));
          coll.add(false);
  
          // 测试Iterator中的remove()方法：删除集合中的指定元素，和集合中的remove()方法不同
          // note: - Iterator可以删除集合中的元素，但是遍历过程中通过迭代器对象的remove方法，不是集合对象的remove方法。
          //       - 如果还未调用next()或在上一次调用next方法之后已经调用了remove方法，再调用remove都会报IllegalStateException.
          Iterator iterator = coll.iterator();
          while (iterator.hasNext()) {
              // iterator.remove(); // error, 不可以在next()方法之前调用remove()方法，因为此时iterator的指针可能为空
              Object obj = iterator.next();
              if (obj.equals("hello")) {
                  iterator.remove();
                  // iterator.remove(); // error, 此时当上一次调用之后，指针已经为空，再次调用将会报错
              }
          }
  
          iterator = coll.iterator();
          while (iterator.hasNext()) {
              System.out.println(iterator.next());
          }
          
          // JDK5.0之后新增了foreach来对集合和数组进行遍历，其底层也是Iterator
          System.out.println("--------foreach进行遍历--------------");
          for(Object c : coll) {
              System.out.println(c);
          }
  
          // note: 如果使用增强for循环，则此时会转换为Iterator，如果对数据进行修改不会发生变化
          int[] arr = new int[]{1,2,3,4,5};
          for (int i : arr) {
              i = 10; // 此时i为临时变量，当调用完成后就会进行释放，因此不会对原数组中的内容进行修改
          }
          for (int i : arr) {
              System.out.println(i); // 1 2 3 4 5
          }
      }
  }
  
  ```

- hasNext() : 判断是否还有下一个元素

  ```java
  while (iterator.hasNext()) {
  	// next()原理:指针下移；将下移以后集合位置上的元素返回
  	System.out.println(iterator.next());
  }
  ```



## 10.5 使用foreach循环遍历集合元素(增强for循环)

- Java 5.0提供了foreach循环迭代方法Collection和数组。

- 遍历操作不需要获取Collection或数组的长度，无序使用索引访问元素。

- **遍历集合的底层调用Iterator完成操作。**

- foreach还可以用来遍历数组

- ```
  // for(要遍历的元素类型 遍历后自定义元素名称 : 要遍历的结构名称)
  for (Person person: persons) {
  	System.out.println(person.getName());
  }
  ```




## 10.6 Collection子接口之一：List接口

### 1. List接口概述：

- 鉴于Java中数组用来存储数据的局限性，我们通常使用List替代数组
- List集合类中**元素有序、且可重复**，集合中的每个元素都有其对应的顺序索引。
- List容器中的元素都一一对应一个整数型的序号记载其在容器中的位置，可以根据序号存取容器中的元素。
- JDK API中List接口的实现类常用的有：ArrayList、LinkedList和Vector

### 2. List实现类之二：LinkedList

- LinkedList：双向链表，内部没有声明数组，而是定义了Node类型的first和last，用于记录首末元素。同时，定义内部类Node，作为LinkedList中保存数据的基本结构。Node除了保存数据，还定义了两个变量：

  - prev变量记录前一个元素的位置

  - next变量记录下一个元素的位置

  - ```java
    private static class Node<E> {
    	E item;
    	Node<E> next;
    	Node<E> prev;
    	
    	Node(Node<E> prev, E element, Node<E> next) {
    		this.item = element;
    		this.next = next;
    		this.prev = prev;
    	}
    }
    ```

  - ![image-20220226100821318](D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220226100821318-16458413188931.png)

### 3. ArrayList的源码分析：

- jdk7下：

  - ArrayList list = new ArrayList(); // 底层创建了长度是10的Object[]数组的elementData

  - list.add(123); // elementData[0] = new Integer(123);

    ...

    list.add(11); // 如果此次的添加导致底层elementData数组容量不够，则扩容：

  - **默认情况下，扩容为原来容量的1.5倍，同时需要将原有数组中的内容赋值到新的数组中**

  - 结论：建议开发中使用代餐的构造器：ArrayList list = new ArrayList(int capacity);

- JDK8中的ArrayList变化：
  - ArrayList list = new ArrayList(); // 底层Object[] elementData初始化为{}，并没有创建长度
  - list.add(123); // 第一次调用add()时，底层才创建了长度为10的数组，并将123添加到elementData中
  - ...
  - 后续的添加可扩展操作与jdk 7 相同
- 小结：jdk7中的ArrayList的创建对象类似于单例的饿汉式；而jdk8中的ArrayList的对象的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。

### 4. LinkedList源码分析

- LinkedList list = new LinkedList(); // 内部声明了Node类型的first和last属性，默认值为null

- list.add(123); // 将123封装到Node中，创建了Node对象。

- ...

- 其中，Node定义为：体现了LinkedList的双向链表的说法

  ```java
   private static class Node<E> {    
       E item;    
       Node<E> next;    
       Node<E> prev;        
       Node(Node<E> prev, E element, Node<E> next) {        
           this.item = element;        
           this.next = next;        
           this.prev = prev;    
       }
   }
  ```



### 5. Vector源码分析

- jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的Object数组
- 在扩容方面，默认扩容为原来数组长度的2倍。



