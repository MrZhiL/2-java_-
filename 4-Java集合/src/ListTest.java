package src;

/**
 * @ClassName: ListTest
 * @Description: Java - List接口的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/26 9:40
 * @node: Collection的List接口
 *        |-------Collection接口：单列集合，用来存储一个一个的对象
 *            |-------List接口：存储有序的、可重复的数据。 --> “动态“数组，替换原有的数组
 *              |-------ArrayList ： 作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储
 *              |-------LinkedList ： 底层使用双向链表存储；对于频繁的插入、删除操作，使用此类效率比ArrayList高
 *              |-------Vector ： 作为List接口的古老实现类；为线程安全的，效率低；底层使用Object[] elementData存储
 *
 *
 *
 *
 *        面试题：比较ArrayList、LinkedList和Vector三者的异同：
 *        同： 三个类都实现了List接口；
 *            存储数据的特点相同：可以存储有序的、可重复的数据
 *        不同： 见上
 */
public class ListTest {
}
