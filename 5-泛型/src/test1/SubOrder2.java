package src.test1;

/**
 * @ClassName: src.test1
 * @Description: Java - 子类继承泛型父类：不指定泛型类型时
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/4 16:10
 * @node:
 *      子类在继承父类时，若指定父类的泛型类型，则默认为该子类为指定父类的泛型类型，即直接调用 SubOrder1 so1 = new SunOrder();即可，不可以加<T>
 */
public class SubOrder2 extends Order<String> {
    // 此时指定SubOrder的类型为String，不再为泛型结构
}

