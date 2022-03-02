package src.MapTest;

import org.junit.Test;

import java.util.*;

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
