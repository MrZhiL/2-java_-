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
 *         3. 添加元素的过程：以HashSet为例
 *            我们向HashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
 *            此哈希值接着通过某种算法（散列表+数组）计算出差在HashSet底层数组中的存放位置（即为，索引位置），
 *            判断数组此位置上是否已经有元素：
 *              1). 如果此位置上没有元素，则元素a添加成功；
 *              2). 如果存在其他元素b（或以链表形式存在多个元素），则比较元素a与元素b（或链表中的多个元素）的哈希值：
 *                  如果hash值不相同，则元素a添加成功；
 *                  3) 如果hash值相同，进而调用元素a所在类的equals()方法进行比较，如果返回true，则添加失败，如果返回false，则添加成功。
 *
 *              对于添加成功的情况2)和3)而言：元素a与已经存在指定索引位置上的数据以链表的形式存储：
 *              jdk7中：元素a放到数组中，指向原来的元素；
 *              jdk8中：原来的元素在数组中指向元素a。
 *              （七上八下）
 *
 *          4. 要求：
 *              1) Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法
 *              2) 向Set中添加的数据，其所在的类一定要重写hashCode()和equals()方法
 *                 要求重写的hashCode()和equals()尽可能保持一致性。
 *              小技巧：对象中用作equals()方法比较的Field，都应该用来计算hashCode值。
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
