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
}
