import org.junit.Test;

import java.util.*;

/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/17 11:12
 * @node:
 */
public class Java9Test2 {
    // java9新特性八：集合工厂方法：创建只读集合。
    @Test
    public void test04() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        // integers.add(6); // error, integers为只读集合，不可以修改
        System.out.println(integers);

        Set<Integer> integers1 = Set.of(12, 432, 342, 9, 1, 52, 42, 28); // 此时需要主要的是Set集合中不可以添加重复元素，否则会报错
        // integers1.add(5); // error, 不可以添加元素
        System.out.println(integers1);

        Map<String, Integer> map = Map.of("Tom", 21, "jack", 19, "Jerry", 21);
        // map.put("Hrei", 23); // java.lang.UnsupportedOperationException
        System.out.println(map);

        Map<String, Integer> map2 = Map.ofEntries(Map.entry("tom", 21), Map.entry("jack", 19), Map.entry("jerry", 23));
        // map2.put("hrri", 21);
        System.out.println(map2);
    }

    @Test
    public void test03() {
        // 此时得到的集合也是一个只读集合
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // list.add(6); // error, list不可以添加元素 （java.lang.UnsupportedOperationException）
    }

    @Test
    public void test02() {
        List<String> list = Collections.unmodifiableList(Arrays.asList("a", "b", "c", "d"));
        Set<String> set = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "c", "D")));

        // 如下操作不适用于jdk 8及以前的版本，适用于jdk9之后的版本
        Map<String, Integer> map = Collections.unmodifiableMap(new HashMap<String, Integer>() {
            {
                put("a", 1);
                put("b", 2);
                put("c", 3);
                put("d", 4);
            }
        });
        map.forEach((k, v) -> System.out.println(k + "; " + v));
    }

    // java8中的写法：
    @Test
    public void test01() {
        // 缺点：需要写入多行，不可以表达成单个表达式
        List<String> nameList = new ArrayList<>();
        nameList.add("joe");
        nameList.add("Bob");
        nameList.add("Bill");
        nameList.add("jack");

        // unmodifiableList返回一个只读的集合
        nameList = Collections.unmodifiableList(nameList);
        // nameList.add("tom"); // 此时已经不可以再修改了
        System.out.println(nameList);

    }
}
