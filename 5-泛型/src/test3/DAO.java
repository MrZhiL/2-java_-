package src.test3;

import java.util.*;

/**
 * @ClassName: src.test3
 * @Description: Java - 自定义泛型类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/6 17:24
 * @node:
 *          定义个泛型类 DAO<T>，在其中定义一个Map成员变量，Map的键为String类型，值为T类型。
 *          分别创建以下方法：
 *              1 public void save(String id, T entity); 保存T类型的对象到Map成员变量中
 *              2 public T get(String id) : 从map中获取id对应的对象
 *              3 public void update(String id, T entity) : 替换map中key为id的内容，改为entity对象
 *              4 public List<T> list() : 返回map中存放的所有T对象
 *              5 public void delete(String id) : 删除指定id对象
 */
public class DAO<T> {
    private Map<String, T> map;

    // 1. 保存T类型的对象到Map成员变量中
    public void save(String id, T entity) {
        if (map == null) {
            map = new HashMap<String, T>();
        }
        map.put(id, entity);
    }

    // 2. 从map中获取id对应的对象
    public T get(String id) {
        return map.get(id);
    }

    // 3. 替换map中key为id的内容，改为entity对象
    public void update(String id, T entity) {
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }
    
    // 4. 返回map中存放的所有T对象
    public List<T> list() {
        Collection<T> values = map.values(); // 首先需要获取出所有的value对象
        // return (List<T>) values; // error, 不可以直接将其转换为List
        List<T> list = new ArrayList<>();
        for (T t : values) {
            list.add(t);
        }

        return list;
    }

    // 5. 删除指定id对象
    public void delete(String id) {
        map.remove(id);
    }
}
