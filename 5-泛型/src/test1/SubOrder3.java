package src.test1;
/**
 * @ClassName: src.test1
 * @Description: Java - 子类继承泛型父类：不指定泛型类型时
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/4 16:10
 * @node:
 *      子类在继承父类时，若指定父类的泛型类型，则默认为该子类为指定父类的泛型类型，即直接调用 SubOrder3<T> so3 = new SunOrder3();即可，可以加<T>
 */
// 此时继承父类的泛型结构，调用的时候需要指定泛型结构 SubOrder3<String> so3 = new SunOrder3<>();
public class SubOrder3<T> extends Order<T> {

}
