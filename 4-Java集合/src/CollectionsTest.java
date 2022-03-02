package src;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: CollectionsTest
 * @Description: Java - Collections类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/2 17:49
 * @node: Collections工具类（操作数组的工具类Arrays）
 *          1。 Collections是一个操作Set、List和Map等集合的工具类
 *          2. Collections中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法。
 *            2.1 排序操作（均为static方法）：
 *            - reverse(List) : 反转List中元素的顺序
 *            - shuffle(List) : 对List集合元素进行随机排序
 *            - sort(List) : 根据元素的自然顺序对指定List集合元素按升序排序
 *            - sort(List,  Comparator) : 根据指定的Comparator产生的顺序对List集合元素进行排序
 *            - swap(List, int, int) :  将指定list集合中的第i个元素和第j个元素进行交换
 *
 *            2.2 查找、替换：
 *            - Object max(Collection) : 根据元素的自然顺序，返回给定集合中的最大元素
 *            - Object max(Collection, Comparator) : 根据comparator指定的顺序，返回给定集合中的最大元素
 *            - Object min(Collection)
 *            - Object min(Collection, Comparator)
 *            - int frequency(Collection, Object) ：返回指定集合中指定元素的出现次数
 *            - void copy(List dest, List src) ： 将src中的内容赋值到dest中（需要dest中的长度大于src中的长度，即dest.size() >= src.size()）
 *            - boolean replaceAll(List list, Object oldVal, Object newVal) : 使用新值替换List对象的所有旧值。
 *
 *          3. **Collections常用方法：同步控制**
 *              Collections类中提供了多个synchronizedXxx()方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。
 *              synchronizedCollection(Collection<T> c)、synchronizedList(List<T> list)、
 *              synchronizedMap()、synchronizedSet(Set<T> s)、synchronizedSortedMap(SortedMap<K, V> m)、
 *              synchronizedSortedSet(SortedSet<T> s);
 */
public class CollectionsTest {
    @Test
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
}
