package src;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: ListTest
 * @Description: Java - List接口的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/26 9:40
 * @node:
 *       1. Collection的List接口
 *          |-------Collection接口：单列集合，用来存储一个一个的对象
 *              |-------List接口：存储有序的、可重复的数据。 --> “动态“数组，替换原有的数组
 *                |-------ArrayList ： 作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储
 *                |-------LinkedList ： 底层使用双向链表存储；对于频繁的插入、删除操作，使用此类效率比ArrayList高
 *                |-------Vector ： 作为List接口的古老实现类；为线程安全的，效率低；底层使用Object[] elementData存储
 *
 *
 *
 *
 *          面试题：比较ArrayList、LinkedList和Vector三者的异同：
 *          同： 三个类都实现了List接口；
 *              存储数据的特点相同：可以存储有序的、可重复的数据
 *          不同： 见上
 *
 *        2. ArrayList的源码分析：
 *          2.1 jdk7下：
 *            - ArrayList list = new ArrayList(); // 底层创建了长度是10的Object[]数组的elementData
 *            - list.add(123); // elementData[0] = new Integer(123);
 *              ...
 *              list.add(11); // 如果此次的添加导致底层elementData数组容量不够，则扩容：
 *            - **默认情况下，扩容为原来容量的1.5倍，同时需要将原有数组中的内容赋值到新的数组中**
 *            - 结论：建议开发中使用代餐的构造器：ArrayList list = new ArrayList(int capacity);
 *
 *          2.2 JDK8中的ArrayList变化：
 *            - ArrayList list = new ArrayList(); // 底层Object[] elementData初始化为{}，并没有创建长度
 *            - list.add(123); // 第一次调用add()时，底层才创建了长度为10的数组，并将123添加到elementData中
 *            - ...
 *            - 后续的添加可扩展操作与jdk 7 相同
 *          2.3 小结：jdk7中的ArrayList的创建对象类似于单例的饿汉式；而jdk8中的ArrayList的对象的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
 *
 *
 *          3. LinkedList源码分析
 *              - LinkedList list = new LinkedList(); // 内部声明了Node类型的first和last属性，默认值为null
 *              - list.add(123); // 将123封装到Node中，创建了Node对象。
 *              - ...
 *              - 其中，Node定义为：体现了LinkedList的双向链表的说法
 *                ```java
 *                     private static class Node<E> {
 *                     E item;
 *                     Node<E> next;
 *                     Node<E> prev;
 *                     Node(Node<E> prev, E element, Node<E> next) {
 *                         this.item = element;
 *                         this.next = next;
 *                         this.prev = prev;
 *                     }
 *                 }
 *                ```
 *
 *          4. Vector源码分析
 *              - jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的Object数组
 *              - 在扩容方面，默认扩容为原来数组长度的2倍。
 *
 *          5. List总结：常用方法
 *              - 增： add(Object obj)
 *              - 删： remove(int index) / remove(Object obj)
 *              - 查： get(int index)
 *              - 改： set(int index, Object ele)
 *              - 插： add(int index, Object ele)
 *              - 长度： size()
 *              - 遍历： Iterator迭代器遍历\增强for循环\普通for循环
 */
public class ListTest {
    /**
     * List接口中的常用方法的测试
     */
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(15);
        arrayList.add(123);
        arrayList.add(234);
        for(int i = 0; i < 15; ++i) {
            arrayList.add((int) (Math.random()*100));
        }
        System.out.println("----------------------------");
        for (var k : arrayList) {
            System.out.println(k);
        }
    }

    /**
     * 1. void add(int index, Object ele); // 在index位置插入ele元素
     * 2. boolean addAll(int index, Collection eles); // 从index位置开始将eles中的所有元素添加进来
     * 3. Object get(int index); // 获取指定index位置的元素
     * 4.int indexOf(Object obj); // 返回obj第一次出现的位置
     * 5. int lastIndexOf(Object obj); // 返回obj在当前集合中末次出现的位置
     * 6. Object remove(int index); // 移除指定index位置的元素，并返回 此元素
     * 7. Object set(int index, Object ele); // 设置指定index位置的元素为ele，并返回old值
     * 8. List subList(int fromIndex, int toIndex); // 返回从fromIndex到toIndex位置的子集合（左闭右开[fromIndex, toIndex)）
     */
    @Test
    public void test01() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add(456);
        arrayList.add("AA");
        arrayList.add(new Person("Tom", 21));
        arrayList.add(new String("CC"));
        System.out.println("原数组：" + arrayList);

        // 1. void add(int index, Object ele); // 在index位置插入ele元素
        System.out.println("---------1. void add(index, ele)------------");
        arrayList.add(0, "First");
        System.out.println("在第一个位置添加First: " + arrayList);

        // 2. boolean addAll(int index, Collection eles); // 从index位置开始将eles中的所有元素添加进来
        System.out.println("---------2. boolean addAll(index, eles)----------");
        List list = Arrays.asList(1, 2, 3);
        arrayList.addAll(0,list);
        System.out.println("在第一个位置添加list: " + arrayList);

        // 3. Object get(int index); // 获取指定index位置的元素
        System.out.println("---------3. Object get(int index)----------");
        System.out.println("get(1) = " + arrayList.get(1));
        // System.out.println(arrayList.get(10)); // error, 不能越界

        // 4.int indexOf(Object obj); // 返回obj第一次出现的位置
        arrayList.add(456);
        arrayList.add("456");
        System.out.println("---------4. int IndexOf(obj)------------");
        System.out.println("arrayList = " + arrayList);
        System.out.println("456第一次出现的位置：" + arrayList.indexOf(456));
        System.out.println("\"456\"第一次出现的位置：" + arrayList.indexOf("456"));

        // 5. int lastIndexOf(Object obj); // 返回obj在当前集合中末次出现的位置
        System.out.println("---------5. int lastIndexOf(obj)------------");
        System.out.println("456最后一次出现的位置：" + arrayList.lastIndexOf(456));
        System.out.println("\"456\"最后一次出现的位置：" + arrayList.lastIndexOf("456"));

        // 6. Object remove(int index); // 移除指定index位置的元素，并返回此元素
        System.out.println("---------6. Object remove(int index)------------");
        System.out.println("remove第一个元素：" + arrayList.remove(0)); // ArrayList中的remove()方法
        System.out.println("remove第二个元素：" + arrayList.remove(0));
        System.out.println("remove第三个元素：" + arrayList.remove(0));
        arrayList.remove("456"); // Collection中的remove(Object obj)方法
        System.out.println("arrayList = " + arrayList);

        // 7. Object set(int index, Object ele); // 设置指定index位置的元素为ele，并返回old值
        System.out.println("------------7. Object set(int index, Object ele)------------");
        System.out.println("oldValue = " + arrayList.set(0,"Hello"));
        System.out.println("arrayList = " + arrayList);

        // 8. List subList(int fromIndex, int toIndex); // 返回从fromIndex到toIndex位置的子集合（左闭右开[fromIndex, toIndex)）
        System.out.println("-----------8 subList(fromIndex, toIndex)------------");
        List list1 = arrayList.subList(0, 4);
        System.out.println("arrayList.subList(0, 4) = " + list1);
        System.out.println("arrayList = " + arrayList);
    }

    /* 遍历方法 */
    @Test
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
}
