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
