package src.SetTest;

import org.junit.Test;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName: src
 * @Description: Java - 去除list中的重复元素
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/1 14:41
 * @node: 利用Set集合的特性，来去除list中的重复元素
 */
public class test {
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

    @Test
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
}
