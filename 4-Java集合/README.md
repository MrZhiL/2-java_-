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

  <img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220224125143484.png" alt="image-20220224125143484" style="zoom: 67%;" />

- Map接口继承树

  ​	<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220224125230891.png" alt="image-20220224125230891" style="zoom:67%;" />



## 10.3 Collection接口中的API

### Q1:使用Collection集合存储对象时，要求对象属性的类满足：

向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()方法

### Q2: Collenction集合与数组间的转换

- 集合->数组： toArray();

  `Object[] arr = coll.toArray();`

- 数组->集合：调用Arrays类的静态方法asList();

  `List<String> list = Arrays.asList("AA", "BB", "CC");`

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
    // 如果存储自定义类，则需要重写equals()方法，从而可以进行remove/contains()/等等方法的调用
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



### 6. List接口方法

- List除了从Collection集合继承的方法外，List集合里添加了一些根据索引来操作集合元素的方法。

    - ```java
    void add(int index, Object ele); // 在index位置插入ele元素
    boolean addAll(int index, Collection eles); // 从index位置开始将eles中的所有元素添加进来
    Object get(int index); // 获取指定index位置的元素
    int indexof(Object obj); // 返回第一次出现的位置，如果找不到则返回-1
    int lastIndexOf(Object obj); // 返回obj在当前集合中末次出现的位置
    Object remove(int index); // 移除指定index位置的元素，并返回 此元素
    Object set(int index, Object ele); // 设置指定index位置的元素为ele，并返回old值
    List subList(int fromIndex, int toIndex); // 返回从fromIndex到toIndex位置的子集合（左闭右开[fromIndex, toIndex)）
    ```

- 总结：常用方法

    - 增： add(Object obj)
    - 删： remove(int index) / remove(Object obj)
    - 查： get(int index)
    - 改： set(int index, Object ele)
    - 插： add(int index, Object ele)
    - 长度： size()
    - 遍历： Iterator迭代器遍历\增强for循环\普通for循环

  ```java
  public void test02() {
          ArrayList arrayList = new ArrayList();
          arrayList.add(123);
          arrayList.add("ANND");
          arrayList.add("hello");
          arrayList.add(new Person("Job", 18));
  
          // 1. 使用Iterator进行遍历
          System.out.println("1. 使用Iterator进行遍历");
          Iterator iterator = arrayList.iterator();
          while (iterator.hasNext()) {
              System.out.println(iterator.next());
          }
  
          // 2. 使用增强for循环
          System.out.println("2. 使用增强for循环");
          for (Object obj : arrayList) {
              System.out.println(obj);
          }
  
          // 3. 使用普通for循环
          System.out.println("3. 使用普通for循环");
          for (int i = 0; i < arrayList.size(); ++i) {
              System.out.println(arrayList.get(i));
          }
      }
  ```



### 7. List 面试题：

```java
package src;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @ClassName: ListTestQuestion
 * @Description: Java - remove()方法的调用问题
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/28 10:16
 * @node: 区分list中的remove(int index) 和 remove(Object obj)方法
 */
public class ListTestQuestion {
    @Test
    public void test01() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        System.out.println(arrayList);
        updateArrayList(arrayList);
        System.out.println("调用updateArrayList : " + arrayList);
        updateArrayList2(arrayList);
        System.out.println("调用updateArrayList2 : " + arrayList);
    }

    public void updateArrayList(ArrayList arrayList) {
        arrayList.remove(2); // 此时调用的是List中重载的remove(int index)方法
    }

    public void updateArrayList2(ArrayList arrayList) {
        arrayList.remove(new Integer(2)); // 此时调用的为Collection中的remove(Object obj)方法
    }

}

```



## 10.7 Collection子接口之二：Set接口

### 1. Set接口概述

- Set接口是Collection的子接口，set接口没有提供额外的方法
- Set集合不允许包含相同的元素，如果试着把两个相同的元素加入同一个Set集合中，则添加操作失败
- Set判断两个对象是否相同不是使用 == 运算符，而是根据 equal()方法

### 2. Set实现类之一：HashSet

- HashSet是Set接口的典型实现，大多数时候使用Set集合时都使用这个实现类。

- HashSet按Hash算法来存储集合中的元素，因此具有很好的存取、查找、删除性能。

- HashSet具有以下特点：

    - 不能保证元素的排列顺序
    - HashSet不是线程安全的
    - 集合元素可以是**null**

- **HashSet集合判断两个元素相等的标准：**两个对象通过hashCode()方法比较相等，并且两个对象的equals()方法返回值也相等。

- 对于存放Set容器的对象，**对应的类一定要重写equals()和hashCode(Object obj)方法，以实现对象相等规则。即：“相等的对象必须具有相等的散列码”。**

- ```
  Set接口的框架
   *            |----Collection接口：单列集合，用来存储一个一个的对象
   *                |----Set接口：存储无序的、不可重复的数据：线程不安全的，可以存储null值
   *                    |----LinkedHashSet: 作为HashSet的子类：遍历其内部数据时，可以按照添加的顺序来
   *                |----TreeSet: 可以按照添加对象的指定属性，进行排序
   *         2. Set：存储无序的、不可重复的数据（以HashSet为例说明：）
   *            1. 无序性：不等于随机性。存储的数据在底层数组中并非按照数组所有的顺序添加的，而是根据数据的哈希值进行添加的
   *            2. 不可重复性：保证添加的元素按照equals()判断时，不能返回true：即，相同的元素只能添加一个
  ```



```java
package src.SetTest;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * @ClassName: src.SetTest
 * @Description: Java - Set接口的框架
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/28 12:43
 * @node:
 *         1. Set接口的框架
 *            |----Collection接口：单列集合，用来存储一个一个的对象
 *                |----Set接口：存储无序的、不可重复的数据：线程不安全的，可以存储null值
 *                    |----LinkedHashSet: 作为HashSet的子类：遍历其内部数据时，可以按照添加的顺序来
 *                |----TreeSet: 可以按照添加对象的指定属性，进行排序
 *         2. Set：存储无序的、不可重复的数据（以HashSet为例说明：）
 *            1. 无序性：不等于随机性。存储的数据在底层数组中并非按照数组所有的顺序添加的，而是根据数据的哈希值进行添加的
 *            2. 不可重复性：保证添加的元素按照equals()判断时，不能返回true：即，相同的元素只能添加一个
 *
 *		4. 要求：
 *              1) Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法
 *              2) 向Set中添加的数据，其所在的类一定要重写hashCode()和equals()方法
 *                 要求重写的hashCode()和equals()尽可能保持一致性。
 */
public class SetTest1 {
    @Test
    public void test01() {
        Set set = new HashSet();    // 默认容量为16
        set.add(123);
        set.add(879);
        set.add(627);
        set.add("AA");
        set.add("AA");
        set.add(new String("BB"));
        set.add(new String("BB"));
        set.add(new Person("Tom", 12));
        set.add(new Person("Tom", 12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

```



### 3. 添加元素的过程：以HashSet为例

*            我们向HashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
*            此哈希值接着通过某种散列算法（散列表+数组）计算出差在HashSet底层数组中的存放位置（即为，索引位置），
* 判断数组此位置上是否已经有元素：
    * 1). 如果此位置上没有元素，则元素a添加成功；

    * 2). 如果存在其他元素b（或以链表形式存在多个元素），则比较元素a与元素b（或链表中的多个元素）的哈希值：

    * 如果hash值不相同，则元素a添加成功；

      如果hash值相同，进而调用元素a所在类的equals()方法进行比较，如果返回true，则添加失败，如果返回false，则添加成功。
        *

    *              对于添加成功的情况2)和3)而言：元素a与已经存在指定索引位置上的数据以链表的形式存储：
* jdk7中：元素a放到数组中，指向原来的元素；jdk8中：原来的元素在数组中指向元素a。
* 总结：七上八下。HashSet底层：数组+链表的结构

- 其中调用的散列函数会与底层数组的长度相计算得到在数组中的下标，并且这种散列函数计算还尽可能保证均匀存储元素，越是散列分布，该散列函数设计的越好。



### 4. LinkedHashSet的使用：

- LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，分别用来计算上一个元素和后一个元素(的索引)。
- 优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220228195623867.png" alt="image-20220228195623867" style="zoom:67%;" />





### 5. TreeSet的使用：

- TreeSet和TreeMap采用红黑数的存储结构；

- 特点：有序，查询速度比List快

- ```java
  package src.SetTest;
  
  import org.junit.Test;
  
  import java.util.Comparator;
  import java.util.Iterator;
  import java.util.TreeSet;
  
  /**
   * @ClassName: TreeSetTest
   * @Description: Java - Set集合中的TreeSet测试
   * @author: zhilx (zhilx1997@sina.com)
   * @version: v1.0
   * @data: 2022/2/28 20:12
   * @node: TreeSet(可以按照添加对象的指定属性，进行排序):
   *          1. 向TreeSet中添加的数据，要求是相同类的对象。
   *          2. 两种自然排序方式： 自然排序(实现Comparabl接口) 和 定制排序（通过Comparator来实现）
   *
   *          3. 自然排序中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals()
   *          4. 定制排序中，比较两个对象是否相同的标准为：compare()返回0，不再是equals()
   */
  public class TreeSetTest {
      @Test
      public void test01() {
          // 自然排序
          // 1. TreeSet可以按照添加对象的指定属性，进行排序（默认为从小到大进行排序）
          TreeSet set = new TreeSet();
          set.add(123);
          set.add(43);
          set.add(64);
          // set.add("AA"); // error, TreeSet中必须添加相同元素的对象
          set.add(-129);
          set.add(98);
  
          Iterator iterator = set.iterator();
          while (iterator.hasNext()) {
              System.out.println(iterator.next()); // -129, 43, 64, 98, 123
          }
  
          // 2. 指定排序方式:自然排序
          set.clear();
          set.add(new Person("Tom", 21));
          set.add(new Person("Jack", 17));
          set.add(new Person("Jerry", 34));
          set.add(new Person("Smith", 19));
          set.add(new Person("Meiko", 41));
          set.add(new Person("Kity", 12));
          set.add(new Person("Jack", 37));
  
          iterator = set.iterator();
          while (iterator.hasNext()) {
              System.out.println(iterator.next());
          }
      }
  
      @Test
      public void test02() {
          // 2. 指定排序方式:定制排序
          Comparator comparator = new Comparator() {
              @Override
              public int compare(Object o1, Object o2) {
                  if (o1 instanceof Person && o2 instanceof Person) {
                      // 按照年龄从小到大进行排序
                      Person p1 = (Person) o1;
                      Person p2 = (Person) o2;
                      return (Integer.compare(p1.getAge(), p2.getAge()));
                  } else {
                      throw new RuntimeException("传输参数类型不一致!");
                  }
              }
          };
  
          TreeSet set = new TreeSet(comparator);
  
          set.add(new Person("Tom", 21));
          set.add(new Person("Hose", 21)); // 因为只指定了按照年龄大小排序，此时已经存在21，因此Hose元素无法插入到其中，除非指定二级排序方式
          set.add(new Person("Jack", 17));
          set.add(new Person("Jerry", 34));
          set.add(new Person("Smith", 19));
          set.add(new Person("Meiko", 41));
          set.add(new Person("Kity", 12));
          set.add(new Person("Jack", 37));
  
          Iterator iterator = set.iterator();
          while (iterator.hasNext()) {
              System.out.println(iterator.next());
          }
      }
  }
  
  ```



## 10.8 Eclipse/IDEA工具里hashCode()的重写

以Eclipse/IDEA为例，在自定义类中可以调用工具自动重写equals()和hashCode()。**问题：为什么用Eclipse/IDEA复写hashCode()方法，有31这个数字？**

- 选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，所谓的”冲突“就越少，查找起来效率也会提高。（减少冲突）

- 并且31只占用5bits，相乘造成数据溢出的概率较小。

- 31可以有 i*31 == (1 << 5) - 1来表示，现在很多虚拟机里面都有做相关优化（提高算法效率）。

- 31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结果只能被素数本身和被乘数1来整除！（减少冲突）



重写hashCode()方法的基本原则：

- 在程序运行时，同一个对象多次调用hashCode()方法应该返回相同的值。
- 当两个对象的equals()方法比较返回true时，这两个对象的hashCode()方法返回值也应相等。
- 对象中用作equals()方法比较的Field，都应该用来计算hashCode值。



## 10.9 练习：Set接口的两个小问题

### 1. 在List内去除重复数字值，要求尽量简单

```java
public static List duplicateList(List list) {
	HashSet set = new HashSet();
	set.addAll(list);
	return new ArrayList(set);
}

public static void main(String[] args) {
	List list = new ArrayList();
	list.add(new Integer(1));
	list.add(new Integer(2));
	list.add(new Integer(2));
	list.add(new Integer(4));
	list.add(new Integer(4));
	List list2 = duplicateList(list);
	for (Object integer : list2) {
		System.out.println(integer);
	}
}
```



### 2. 去除List内的重复自定义类：

```java
public void test02() {
        HashSet set = new HashSet();
        Person p1 = new Person("jack", 21);
        Person p2 = new Person("jerry", 22);

        set.add(p1);
        set.add(p2);
        System.out.println("set : \n" + set); // [Person{name='jack', age=21}, Person{name='jerry', age=22}]

        System.out.println("-----set.remove(p1)--------");
        p1.setName("Marry");
        // 此时set中存储p1元素为Person{name='jack', age=21}的哈希值，此时p1为{Marry, 21}，可以发现两者的hash值不同，因此remove失败
        boolean remove = set.remove(p1);
        System.out.println(remove); // false
        System.out.println(set);    // [Person{name='Marry', age=21}, Person{name='jerry', age=22}]

        System.out.println("------set.add(p1)----------");
        // 因为set中之前没有存储{Marry, 21}所对应的hash值的元素，因此此时会添加成功，此时set元素为3个
        set.add(new Person("Marry", 21)); // [Person{name='Marry', age=21}, Person{name='Marry', age=21}, Person{name='jerry', age=22}]
        System.out.println(set);
        // 因为set中之前的p1{Jack, 21}已经被更改为了{Marry， 21}，当再次添加该元素时：hash值相同，但是equals()比较返回false，因此可以添加 成功。此时set中的元素数量为4
        set.add(new Person("Jack", 21)); // [Person{name='Marry', age=21}, Person{name='Marry', age=21}, Person{name='Jack', age=21}, Person{name='jerry', age=22}]
        System.out.println(set);
    }
```



## 10.10 Map接口：

### 1. Map实现类的结构

- Map: 双列数据，存储key-value键值对

  ​    |----HashMap: 作为Map的主要实现类 (jdk 1.2)；线程不安全的，效率高；可以存储null的key和value

  ​			|----LinkedHashMap (jdk 1.4): 包装在遍历map元素时，可以按照添加的顺序实现遍历。

  ​					原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。

  ​					对于频繁的遍历操作，此类执行效率高于HashMap。

  ​	 |----TreeMap : 包装按照添加的key-value键值对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序 （底层使用红黑树）

  ​	 |----Hashtable: 作为Map的古老实现类（jdk1.0)；线程安全的，效率高；不可以存储null的key和value

  ​			|----Properties: 常用来处理配置文件。key和value都是String类型的5

- HaspMap的底层：（jdk7.0及之前）数组+链表； （jdk8之后）数组+链表+红黑树，为了提高效率

- 面试题：

    1. HashMap的底层实现原理？

    2. HashMap和Hashtable的异同？
    3. CurrentHaspMap 与 Hashtable的异同？

- ```java
  Map map = new HashMap();
  map.put(null, 123); // right, 可以存储key为null的元素
  map.put(null, numm);// right, 可以存储value为null的元素
  
  Map map2 = new Hashtable();
  map2.put(null, 123); // error, Hashtable不可以存储key或value为null的元素
  ```



<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220301150739510-16461207409962.png" alt="image-20220301150739510" style="zoom:67%;" />



### 2. Map接口中key-value的特点

- Map中的key：无序的、不可重复的，使用Set存储是所有的key --> **要求key所在的类要重写eauqls()和hashCode()方法（以HashMap为例）**

- Map中的value：无序的、可重复的，使用Collection存储所有的value --> value所在的类需要重写equals()方法

- 一个键值对：key-value构成了一个Entry对象

- Map中的Entry：无序的、不可重复的，使用set存储所有的Entry对象。

  map中存储key-value可以看做是存储在Set集合中的entry实体对象，每个entry中包含key和value两个属性。

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220301154532904.png" alt="image-20220301154532904" style="zoom:67%;" />

### 3. HashMap的底层实现原理：

HashMap中的内部类：Node

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;
	....        
}
```



#### 3.1 在JDK7中：HashMap map = new HashMap();

1. 在实例化以后，底层创建了一个长度为16的一维数组Entry[] table.
2. ...可能执行过多次put...
    1. `map.put(key1, value1);`
    2. 首先，调用key1所在类的hashCode()计算key1的哈希值，此哈希值经过某种散列函数计算以后，得到在Entry数组中的存放位置。
        1. (**情况一**) 如果此位置上的数据为空，此时的key1-value1添加成功；
        2. 如果此位置上的数据不为空（意味着此位置上存在一个或多个数据（以链表形式存在）），然后比较key1和已经存在的一个或多个数据的哈希值：
            - 如果key1 的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功
            - 如果key1的哈希值和已经存在的某一个元素(key2-value2)的哈希值相同，继续比较, 调用key1所在类的equals(key2) :
                - (**情况二**) 如果equals()返回false，此时key1-value1添加成功
                - (**情况三**) 如果equals()返回true，此时使用value1来替换value2
3. 补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。
4. 在不断添加元素的过程中，会设计到扩容问题，默认的扩容方式：当插入元素key值不为null且数量超出threshold值时，扩容为原来的2倍，并将之前的数据拷贝进去。

#### 3.2 JDK8与JDK7中在底层实现方式的不同：

1. new HashMap()； 此时底层没有创建一个长度为16的数组
2. jdk8底层的数组是：Node[]类型，而非Entry[]
3. 首次调用put()方法时，底层会创建长度为16的数组
4. jdk7底层结构只有**数组+链表**；jdk8中底层为 **数组+链表+红黑树**
    - 当数组的某一个所有位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度超过64，此时此索引位置上的所有数据改为红黑树存储。



#### 3.3 HashMap源码中的重要常量

- `DEFAULT_INITIAL_CAPACITY` : HashMap的默认容量，16
- `MAXIMUM_CAPACITY` : HashMap的最大支持容量，2^30
- `DEFAULT_LOAD_FACTOR` : HashMap的默认加载因子
- `TREEIFY_THRESHOLD` : Bucket中链表长度大于该默认值，转化为红黑树
- `UNTREEIFY_THRESHOLD` : Bucket中红黑树存储的Node小于该默认值，转化为链表
- `MIN_TREEIFY_CAPACITY` （64）: 桶中的Node被树化时最小的hash表容量。（当桶中Node的数量大到需要变为红黑树时，若hash表容量小于MIN_TREEIFY_CAPACITY时，此时应执行resize()扩容操作这个MIN_TREEIFY_CAPACITY的值至少是TREEIFY_THRESHOLD的4倍）。
- `table` : 存储元素的数组，总是2的n次幂
- `entrySet` : 存储具体元素的集
- `size` : HashMap中存储键值对的数量
- `modCount` : HashMap扩容和结构改变的次数
- `threshold` : 扩容的临界值， = 容量*填充因子
- `loadFactor` ：填充因子



### 4. LinkedHashMap的底层实现原理

LinkedHashMap中的内部类：Entry

```java
/**
 * HashMap.Node subclass for normal LinkedHashMap entries.
 */
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after; // 此时可以记录添加元素的上一个和后一个元素的顺序
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```



## 10.11 Map接口中的常用方法

- 添加、删除、修改操作
    - Object put(Object key, Object value) : 将指定key-value添加到（或修改）当前map对象中
    - void putAll(Map m) : 将m中的所有key-value对存放到当前map中
    - Object remove(Object key) : 移除指定key的key-value对，并返回value
    - void clear() : 清空当前map中的所有数据
- 元素查询的操作：
    - Object get(Object key) : 获取指定key对应的value
    - boolean containsKey(Object key) : 是否包含指定的key
    - boolean containsValue(Objecct value) : 是否包含指定的value
    - int size() : 返回map中key-value对的个数
    - boolean isEmpty()：判断当前map是否为空
    - boolean equals(Object obj) : 判断当前map和参数对象obj是否相等
- 元视图操作的方法：
    - Set keySet() : 返回所有key构成的Set集合
    - Collection values()：返回所有value构成的Collection集合
    - Set entrySet(): 返回所有key-value对构成的Set集合

```java
@Test
    public void test01() {
        /** 添加、删除、修改操作
            1. - Object put(Object key, Object value) : 将指定key-value添加到（或修改）当前map对象中
            2. - void putAll(Map m) : 将m中的所有key-value对存放到当前map中
            3. - Object remove(Object key) : 移除指定key的key-value对，并返回value
            4. - void clear() : 清空当前map中的所有数据
        */
        Map map = new HashMap();
        // 1. 使用put方法进行元素的添加
        map.put("AA", 123);
        map.put("BB", 234);
        map.put("CC", 456);
        map.put("DD", 123);
        map.put(999, 888);
        System.out.println("使用put()方法进行添加：" + map);

        // 2. 使用put方法进行元素的修改
        map.put("AA", 111);
        System.out.println("使用put()方法进行修改：" + map);

        // 3. 使用remove方法移除指定key值的元素
        map.remove(999);
        System.out.println("调用remove()方法移除指定key的元素：" + map);

        // 4. 调用clear()方法清空map中的数据
        System.out.println("清空前 : " + map.size());
        map.clear();
        System.out.println("清空后 : " + map.size());
    }

    @Test
    public void test02() {
        /** 元素查询的操作：
            1. Object get(Object key) : 获取指定key对应的value
            2. boolean containsKey(Object key) : 是否包含指定的key
            3. boolean containsValue(Objecct value) : 是否包含指定的value
            4. int size() : 返回map中key-value对的个数
            5. boolean isEmpty()：判断当前map是否为空
            6. boolean equals(Object obj) : 判断当前map和参数对象obj是否相等
         */
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 234);
        map.put("CC", 456);
        map.put("DD", 123);
        map.put(999, 888);
        System.out.println("原数据 : " + map);

        // 1. Object get(Object key) : 获取指定key对应的value
        Object o = map.get(999);
        System.out.println("map中是否包含key为999的元素：" + o);  // 888
        System.out.println("map中是否包含key为888的元素：" + map.get(888));  // null

        // 2. boolean containsKey(Object key) : 是否包含指定的key
        boolean cc = map.containsKey("CC");
        boolean ccc = map.containsKey("CCC");
        System.out.println("map.containsKey(\"CC\") = " + cc); // true
        System.out.println("map.containsKey(\"CCC\") = " + ccc); // false

        // 3. boolean containsValue(Objecct value) : 是否包含指定的value
        boolean b1 = map.containsValue(123);
        boolean b2 = map.containsValue(888);
        System.out.println("map.containsValue(123) = " + b1); // true
        System.out.println("map.containsValue(888) = " + b2); // false

        // 4. 判断是否为空
        System.out.println("map.isEmpty() = " + map.isEmpty());
        map.clear();
        System.out.println("-----调用map.clear()----");
        System.out.println("map.isEmpty() = " + map.isEmpty());

        // 5. equals(Object obj) : 判断当前map和参数对象obj是否相等
    }

    @Test
    public void test03() {
        /**（3） 元视图操作的方法：
            1. Set keySet() : 返回所有key构成的Set集合
            2. Collection values()：返回所有value构成的Collection集合
            3. Set entrySet(): 返回所有key-value对构成的Set集合
         */
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 234);
        map.put(999, 888);
        map.put("CC", 456);
        map.put("DD", 123);
        System.out.println("原数据 : " + map); // {AA=123, BB=234, CC=456, DD=123, 999=888}

        // 1.Set keySet() : 返回所有key构成的Set集合
        Set set = map.keySet();
        System.out.println("map中的所有key构成的Set集合: " + set); // [AA, BB, CC, DD, 999]
        System.out.println("使用Iterator查看Set中的元素: ");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        // 2. Collection values()：返回所有value构成的Collection集合（此时可以包含重复的value）
        Collection values = map.values();
        System.out.println("map中所有value构成的集合: " + values); // [123, 234, 456, 123, 888]

        Set value2 = new HashSet(); // 通过set集合可以去除重复元素
        value2.addAll(values);
        System.out.println("map中所有value构成的集合: " + value2); // [456, 888, 234, 123]

        // 3. Set entrySet(): 返回所有key-value对构成的Set集合(此时得到的都是Entry对象)
        Set set2 = map.entrySet();
        System.out.println("map中所有key-value构成的Set集合 : " + set2); // [AA=123, BB=234, CC=456, DD=123, 999=888]
        System.out.println("使用增强for循环遍历set2中的元素: ");
        for (Object o : set2) {
            Map.Entry entry = (Map.Entry) o;
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }
```



## 10.12 TreeMap

```java
/**
 * @ClassName: TreeMapTest
 * @Description: Java - TreeMap测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/2 16:14
 * @node:
 *        1. 向TreeMap中添加key-value时，要求key必须是由同一个类创建的对象
 *           因为要按照key值进行排序：自然排序和定制排序两种
 */
public class TreeMapTest {
    @Test
    public void test01() {
        // 使用自然排序
        System.out.println("使用自然排序：Collection中的重写compareTo()方法：指定按照姓名从大到小，年龄从小到大");
        TreeMap map = new TreeMap();

        Person p1 = new Person("Tom", 21);
        Person p2 = new Person("Jack", 17);
        Person p3 = new Person("Jerry", 34);
        Person p4 = new Person("Smith", 19);
        Person p5 = new Person("Meiko", 41);
        Person p6 = new Person("Kity", 12);
        Person p7 = new Person("Jack", 37);

        map.put(p1, 98);
        map.put(p2, 120);
        map.put(p3, 87);
        map.put(p4, 142);
        map.put(p5, 99);
        map.put(p6, 110);
        map.put(p7, 99);

        // 输出：
        Set set = map.entrySet();
        for (Object o : set) {
            Map.Entry entry = (Map.Entry)o;
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }

    @Test
    public void test02() {
        System.out.println("使用定制排序：指定按照年龄从大到小，姓名从小到大");
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person) {
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;

                    int age = Integer.compare(p1.getAge(), p2.getAge());
                    if (age != 0) {
                        return -age;
                    }

                    return p1.getName().compareTo(p2.getName());
                }
                throw new RuntimeException("传输类型参数不匹配!");
            }
        };


        TreeMap map = new TreeMap(comparator);

        Person p1 = new Person("Tom", 21);
        Person p2 = new Person("Jack", 17);
        Person p3 = new Person("Jerry", 34);
        Person p4 = new Person("Smith", 19);
        Person p5 = new Person("Meiko", 41);
        Person p6 = new Person("Kity", 12);
        Person p7 = new Person("Jack", 37);

        map.put(p1, 98);
        map.put(p2, 120);
        map.put(p3, 87);
        map.put(p4, 142);
        map.put(p5, 99);
        map.put(p6, 110);
        map.put(p7, 99);

        // 输出：
        Set set = map.entrySet();
        for (Object o : set) {
            Map.Entry entry = (Map.Entry)o;
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }
}
```



## 10.13 Map实现类之五：Properties

- Properties类是Hashtable的子类，该对象用于处理属性文件

- 由于属性文件里的key、value都是字符串类型，**所以Properties里的key和value都是字符串类型**

- 存取数据时，建议使用setProperty(String key, String value)方法和getProperty(String key)方法

- ```java
  Properties pros = new Properties();
  pros.load(new FileInputStream("jdbc.properties"));
  String user = pros.getProperty("user");
  System.out.println(user);
  ```



## 10.14 Collections工具类（操作数组的工具类Arrays）

- Collections是一个操作Set、List和Map等集合的工具类

- Collections中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法。

- 排序操作（均为static方法）：
    - reverse(List) : 反转List中元素的顺序
    - shuffle(List) : 对List集合元素进行随机排序
    - sort(List) : 根据元素的自然顺序对指定List集合元素按升序排序
    - sort(List,  Comparator) : 根据指定的Comparator产生的顺序对List集合元素进行排序
    - swap(List, int, int) :  将指定list集合中的第i个元素和第j个元素进行交换
    
- 查找、替换：

    - Object max(Collection) : 根据元素的自然顺序，返回给定集合中的最大元素
    - Object max(Collection, Comparator) : 根据comparator指定的顺序，返回给定集合中的最大元素
    - Object min(Collection) 
    - Object min(Collection, Comparator) 
    - int frequency(Collection, Object) ：返回指定集合中指定元素的出现次数
    - void copy(List dest, List src) ： 将src中的内容赋值到dest中（需要dest中的长度大于src中的长度，即dest.size() >= src.size()）
    - boolean replaceAll(List list, Object oldVal, Object newVal) : 使用新值替换List对象的所有的oldVa旧值。

- ```java
    public void test01() {
            List list = new ArrayList();
            for (int i = 0; i < 10; ++i) {
                list.add((int)(Math.random()*100));
            }
    
            System.out.println("原list : " + list);
    
            // 1. reverse(list)，反转list
            Collections.reverse(list);
            System.out.println("反转list : " + list);
    
            // 2. shuffle(List) : 对List集合元素进行随机排序
            Collections.shuffle(list);
            System.out.println("shuffle : " + list);
    
            // 3. sort(List) : 根据元素的自然顺序对指定List集合元素按升序排序
            Collections.sort(list);
            System.out.println("排序后: " + list);
    
            // 4. swap(List, int, int) :  将指定list集合中的第i个元素和第j个元素进行交换
            Collections.swap(list, 0, 1);
            System.out.println("swap(list, 0, 1) = " + list);
    
            // 5. max(Collection) : 根据元素的自然顺序，返回给定集合中的最大元素
            list.add(133);
            list.add(133);
            list.add(133);
            System.out.println("\nlist = " + list);
            System.out.println("max(list) = " + Collections.max(list));
            System.out.println("min(list) = " + Collections.min(list));
    
            // 6. int frequency(Collection, Object) ：返回指定集合中指定元素的出现次数
            System.out.println("frequency(list, 133) = " + Collections.frequency(list, 133));
            System.out.println("frequency(list, 125) = " + Collections.frequency(list, 125));
    
            // 7. note: void copy(List dest, List src) ： 将src中的内容赋值到dest中（需要dest中的长度大于src中的长度，即dest.size() >= src.size()）
    
            // 此时会报错（java.lang.IndexOutOfBoundsException: Source does not fit in dest），因为dest的长度小于list长度
            // List dest = new ArrayList();
            // Collections.copy(dest, list);
    
            // 正确的方法：
            List dest = Arrays.asList(new Object[list.size()]); // 此时通过Arrays数组来指定dest的大小
            System.out.println("\ndest = " + dest);
            Collections.copy(dest, list);
            System.out.println("dest = " + dest);
    
            // 8. 使用新值替换List对象的所有旧值。
            Collections.replaceAll(dest, 133, -99);
            System.out.println("replaceAll(dest, 133, -99) = " + dest);
    
            // 9. Collections常用方法：同步控制**
            // *              Collections类中提供了多个synchronizedXxx()方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。
            // *              synchronizedCollection(Collection<T> c)、synchronizedList(List<T> list)、
            /**此时得到的list1即为线程安全的List*/
            List list1 = Collections.synchronizedList(list);
    
        }
    ```

    

- **Collections常用方法：同步控制**

    Collections类中提供了多个synchronizedXxx()方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。

    - synchronizedCollection(Collection<T> c)、synchronizedList(List<T> list)、synchronizedMap()、synchronizedSet(Set<T> s)、synchronizedSortedMap(SortedMap<K, V> m)、 synchronizedSortedSet(SortedSet<T> s);

### Q1:Collection和Collections的区别？

