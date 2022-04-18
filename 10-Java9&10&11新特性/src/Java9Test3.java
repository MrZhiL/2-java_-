import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java - Java9新特性
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/18 8:54
 * @node:
 */
public class Java9Test3 {
    @Test
    // java 9的新特性十：增强Stream API
    public void test01() {
        List<Integer> list = Arrays.asList(45, 43, 76, 87, 42, 77, 90, 73, 67, 88);

        // 1. takeWhile(): 用于从Stream中获取一部分数据，接收一个Predicate来进行选择。在有序的Stream中，takeWhile返回从头开始的尽量多的元素。
        // 一旦不满足条件则立即退出，后续的元素不再进行遍历
        list.stream().takeWhile(x -> x < 50).forEach(System.out::println); // 45, 43
        System.out.println();

        // 2. dorpWhile(): 与takeWhile()相反，返回剩余的元素
        list.stream().dropWhile(x -> x < 50).forEach(System.out::println); // 45, 43

        System.out.println();

        list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.stream().takeWhile(x -> x < 5).forEach(System.out::println); // 1 2 3 4
    }

    @Test
    public void test02() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, null);
        integerStream.forEach(System.out::println); // 1 2 3 null


        // Stream<Object> stream2 = Stream.of(null); // 不可以存在只有一个null值，否则会报空指针异常
        // stream2.forEach(System.out::println); // 1 2 3 null

        // 报NullPointerException
        // Stream<Object> stream1 = Stream.of(null);
        // System.out.println(stream1.count());
        Stream<Object> stream11 = Stream.of(null, null); // of()参数不能存储单个null值，但是可以存储多个
        System.out.println(stream11.count()); // 2

        // 不报异常，可以通过
        Stream<String> stringStream = Stream.of("AA", "BB", null);
        System.out.println(stringStream.count()); // 3

        // 不报异常，允许通过
        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add(null);
        System.out.println(list.stream().count()); // 2

        // ofNullable() : 允许值为null
        Stream<Object> stream2 = Stream.ofNullable(null);
        System.out.println(stream2.count()); // 0

        Stream<String> stream3 = Stream.ofNullable("hello world");
        System.out.println(stream3.count()); // 1
    }

    @Test
    // 重载的iterate()的使用:
    // 这个iterate方法的新重载方法，可以让我们提供一个Predicate（判断条件）来指定什么时候结束迭代。
    public void test03() {
        // 原来的控制终止方法
        Stream.iterate(1, i -> i + 1).limit(10).forEach(x -> System.out.print(x + " ")); // 1 2 3 4 5 6 7 8 9 10
        System.out.println();

        // 现在的终止方式：
        Stream.iterate(1, i -> i < 10, i -> i + 1).forEach(x -> System.out.print(x + " ")); // 1 2 3 4 5 6 7 8 9
    }

    @Test
    public void test04() {
        // java9的新特性十一：Optional类提供了新的stream方法
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("jack");
        list.add("merry");

        Optional<List<String>> optional = Optional.ofNullable(list);

        // 将Optional转换为Stream流
        Stream<List<String>> stream = optional.stream();

        // System.out.println(stream.count()); // 1, 当调用完stream.count()后该流会关闭，因此这里进行了注释
        stream.flatMap(x -> x.stream()).forEach(System.out::println); // Tom jack merry
    }

    @Test
    // java10的新增特性二：集合中新增的copyof()，用于创建一个只读的集合
    public void test05() {
        // 1.
        var list1 = List.of("java", "python", "C");
        var copy1 = List.copyOf(list1);
        System.out.println(list1 == copy1); // true

        // 2.
        var list2 = new ArrayList<String>();
        // list2.add("aaa");
        var copy2 = List.copyOf(list2);
        System.out.println(list2 == copy2); // false

    }
}
