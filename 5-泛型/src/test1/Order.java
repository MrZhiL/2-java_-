package src.test1;

/**
 * @ClassName: src.test1
 * @Description: Java - 自定义泛型类
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/4 16:04
 * @node:
 *          1. 泛型介绍：
 *          - 在集合中使用泛型时：
 *          - 集合接口或集合类在JDK5.0时都修改为带泛型的结构。
 *          - 在实例化集合类时，可以指明具体的泛型类型。
 *          - 指明后，在集合类或者接口中凡是定义类或接口时，内部结构使用到类的泛型的位置，都指定为实例化的泛型类型，比如：add(E e) -->  实例化以后 add(Integer e)
 *          - **注意：泛型的类型必须是类，不能是基本数据类型。需要用到的基本数据类型的位置，拿包装类替换**
 *          - **如果实例化时没有指定泛型，则默认为java.lang.Object类型的**
 *          - 如何自定义泛型结构：泛型类、泛型接口；泛型方法
 *
 *          2. 注意事项
 *          - 如果定义了泛型类，实例化没有指明类的泛型，则认为泛型类型为Object类型
 *          - 要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
 *          - **如果子类在继承带泛型的父类时，若指明了泛型类型，则实例化对象时，不再需要指明泛型结构**。
 */
public class Order<T> {
    private int age;
    private String name;
    private T orderT; // 自定义泛型变量

    public Order() {

    }

    public Order(String name, int age, T t) {
        this.name = name;
        this.age = age;
        this.orderT = t;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", orderT=" + orderT +
                '}';
    }
}
