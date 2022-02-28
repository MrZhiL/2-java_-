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
