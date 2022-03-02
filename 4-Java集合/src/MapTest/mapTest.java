package src.MapTest;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName: src.MapTest
 * @Description: Java - Map接口的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/2 15:12
 * @node:
 *          1. Map中的常用方法：
 *            （1） 添加、删除、修改操作
 *              - Object put(Object key, Object value) : 将指定key-value添加到（或修改）当前map对象中
 *              - void putAll(Map m) : 将m中的所有key-value对存放到当前map中
 *              - Object remove(Object key) : 移除指定key的key-value对，并返回value
 *              - void clear() : 清空当前map中的所有数据
 * -          （2） 元素查询的操作：
 *              - Object get(Object key) : 获取指定key对应的value
 *              - boolean containsKey(Object key) : 是否包含指定的key
 *              - boolean containsValue(Objecct value) : 是否包含指定的value
 *              - int size() : 返回map中key-value对的个数
 *              - boolean isEmpty()：判断当前map是否为空
 *              - boolean equals(Object obj) : 判断当前map和参数对象obj是否相等
 * -           （3） 元视图操作的方法：
 *              - Set keySet() : 返回所有key构成的Set集合
 *              - Collection values()：返回所有value构成的Collection集合
 *              - Set entrySet(): 返回所有key-value对构成的Set集合
 */
public class mapTest {
    @Test
    public void test01() {
        /** 添加、删除、修改操作
            1. - Object put(Object key, Object value) : 将指定key-value添加到（或修改）当前map对象中
            2. - void putAll(Map m) : 将m中的所有key-value对存放到当前map中
            3. - Object remove(Object key) : 移除指定key的key-value对，并返回value
            4. - void clear() : 清空当前map中的所有数据
        */
        Map map = new HashMap();
        // 1. 使用put方法进行元素的添加
        map.put("AA", 123);
        map.put("BB", 234);
        map.put("CC", 456);
        map.put("DD", 123);
        map.put(999, 888);
        System.out.println("使用put()方法进行添加：" + map);

        // 2. 使用put方法进行元素的修改
        map.put("AA", 111);
        System.out.println("使用put()方法进行修改：" + map);

        // 3. 使用remove方法移除指定key值的元素
        map.remove(999);
        System.out.println("调用remove()方法移除指定key的元素：" + map);

        // 4. 调用clear()方法清空map中的数据
        System.out.println("清空前 : " + map.size());
        map.clear();
        System.out.println("清空后 : " + map.size());
    }

    @Test
    public void test02() {
        /** 元素查询的操作：
            1. Object get(Object key) : 获取指定key对应的value
            2. boolean containsKey(Object key) : 是否包含指定的key
            3. boolean containsValue(Objecct value) : 是否包含指定的value
            4. int size() : 返回map中key-value对的个数
            5. boolean isEmpty()：判断当前map是否为空
            6. boolean equals(Object obj) : 判断当前map和参数对象obj是否相等
         */
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 234);
        map.put("CC", 456);
        map.put("DD", 123);
        map.put(999, 888);
        System.out.println("原数据 : " + map);

        // 1. Object get(Object key) : 获取指定key对应的value
        Object o = map.get(999);
        System.out.println("map中是否包含key为999的元素：" + o);  // 888
        System.out.println("map中是否包含key为888的元素：" + map.get(888));  // null

        // 2. boolean containsKey(Object key) : 是否包含指定的key
        boolean cc = map.containsKey("CC");
        boolean ccc = map.containsKey("CCC");
        System.out.println("map.containsKey(\"CC\") = " + cc); // true
        System.out.println("map.containsKey(\"CCC\") = " + ccc); // false

        // 3. boolean containsValue(Objecct value) : 是否包含指定的value
        boolean b1 = map.containsValue(123);
        boolean b2 = map.containsValue(888);
        System.out.println("map.containsValue(123) = " + b1); // true
        System.out.println("map.containsValue(888) = " + b2); // false

        // 4. 判断是否为空
        System.out.println("map.isEmpty() = " + map.isEmpty());
        map.clear();
        System.out.println("-----调用map.clear()----");
        System.out.println("map.isEmpty() = " + map.isEmpty());

        // 5. equals(Object obj) : 判断当前map和参数对象obj是否相等
    }

    @Test
    public void test03() {
        /**（3） 元视图操作的方法：
            1. Set keySet() : 返回所有key构成的Set集合
            2. Collection values()：返回所有value构成的Collection集合
            3. Set entrySet(): 返回所有key-value对构成的Set集合
         */
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 234);
        map.put(999, 888);
        map.put("CC", 456);
        map.put("DD", 123);
        System.out.println("原数据 : " + map); // {AA=123, BB=234, CC=456, DD=123, 999=888}

        // 1.Set keySet() : 返回所有key构成的Set集合
        Set set = map.keySet();
        System.out.println("map中的所有key构成的Set集合: " + set); // [AA, BB, CC, DD, 999]
        System.out.println("使用Iterator查看Set中的元素: ");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        // 2. Collection values()：返回所有value构成的Collection集合（此时可以包含重复的value）
        Collection values = map.values();
        System.out.println("map中所有value构成的集合: " + values); // [123, 234, 456, 123, 888]

        Set value2 = new HashSet(); // 通过set集合可以去除重复元素
        value2.addAll(values);
        System.out.println("map中所有value构成的集合: " + value2); // [456, 888, 234, 123]

        // 3. Set entrySet(): 返回所有key-value对构成的Set集合(此时得到的都是Entry对象)
        Set set2 = map.entrySet();
        System.out.println("map中所有key-value构成的Set集合 : " + set2); // [AA=123, BB=234, CC=456, DD=123, 999=888]
        System.out.println("使用增强for循环遍历set2中的元素: ");
        for (Object o : set2) {
            Map.Entry entry = (Map.Entry) o;
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }
}
