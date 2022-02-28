package src.SetTest;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName: LinkedHashSetTest的测试
 * @Description: Java - LinkedHashSet
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/28 19:48
 * @node:
 *          |----Set接口：存储无序的、不可重复的数据：线程不安全的，可以存储null值
 *              |----LinkedHashSet: 作为HashSet的子类：遍历其内部数据时，可以按照添加的顺序来
 */
public class LinkedHashSetTest {
    @Test
    public void test01() {
        Set set = new LinkedHashSet();    // 默认容量为16
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
