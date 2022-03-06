package src.test3;

import java.util.List;

/**
 * @ClassName: src.test3
 * @Description: Java - 自定义泛型类练习
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/6 17:24
 * @node:
 *          1. 定义个泛型类 DAO<T>，在其中定义一个Map成员变量，Map的键为String类型，值为T类型。
 *             分别创建以下方法：
 *             - public void save(String id, T entity); 保存T类型的对象到Map成员变量中
 *             - public T get(String id) : 从map中获取id对应的对象
 *             - public void update(String id, T entity) : 替换map中key为id的内容，改为entity对象
 *             - public List<T> list() : 返回map中存放的所有T对象
 *             - public void delete(String id) : 删除指定id对象
 *
 *          2. 定义一个User类：
 *             该类包含： private 成员变量(int 类型) id, age; (String 类型)name
 *
 *          3. 定义一个测试类：
 *             1. 创建DAO类的对象，分别调用save/get/update/list/delete方法来操作User对象
 *             2. 使用Junit单元测试类进行测试
 */
public class main {
    public static void main(String[] args) {
        /** 创建DAO类的对象，分别调用save/get/update/list/delete方法来操作User对象 */
        DAO<User> dao = new DAO<User>();

        // 1. save()方法的调用
        dao.save("1001", new User(1001, 35, "lisi"));
        dao.save("1002", new User(1002, 15, "wangwu"));
        dao.save("1003", new User(1003, 20, "zhaoliu"));

        // 2. list方法的调用
        List<User> list = dao.list();
        list.forEach(System.out::println);

        // 3. update方法的调用
        dao.update("1003", new User(1003, 25, "jack"));

        // 4. get方法的调用
        System.out.println("----------------------");
        User user = dao.get("1003");
        System.out.println(user);

        // 5. delete方法的调用
        dao.delete("1003");
        user = dao.get("1003");
        System.out.println(user);

    }
}
