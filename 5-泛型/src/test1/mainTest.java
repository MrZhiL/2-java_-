package src.test1;

/**
 * @ClassName: src.test1
 * @Description: Java - 自定义泛型类测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/4 16:11
 * @node:
 */
public class mainTest {
    public static void main(String[] args) {
        // 此时自定义泛型类Order可以直接实现
        Order<String> ord = new Order<>("lisi", 21, "hello");
        System.out.println(ord);
        ord.setOrderT("AA::hello");
        System.out.println(ord);

        // 子类继承泛型父类时，如果不指定泛型类型，则默认为为Object类型
        SubOrder1 subOrder1 = new SubOrder1();
        subOrder1.setOrderT("AAA");
        System.out.println(subOrder1.getOrderT());
        subOrder1.setOrderT(123);
        System.out.println(subOrder1.getOrderT());

        // 子类继承泛型父类时，如果指定泛型类型，则默认为指定的类型
        SubOrder2 subOrder2 = new SubOrder2();
        // SubOrder2<String> subOrder2 = new SubOrder2<>(); // error, 因为继承的时候指定了泛型T，因此不可以继续指定

        // 子类继承泛型父类时，可以直接继承父类的泛型类型
        SubOrder3<Integer> subOrder3 = new SubOrder3<>();
    }
}
