package src.test2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: GenericTest
 * @Description: Java - 泛型
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/6 15:55
 * @node:
 *      1. 泛型在继承方面的体现
 *          1.1. 若类A是类B的父类，则G<A> 和 G<B>两者不具备子父类关系，二者是并列关系
 *          1.2. 若类A是类B的父类，则A<G> B<G>两者具备子父类关系
 *
 *      2. 通配符的使用
 *          2.1 通配符：？
 *          2.2 如类A是类B的父类，G<A> 和 G<B> 是没有关系的，两者共同的父类是: G<?>
 *          2.3 获取（读取）：允许读取数据，读取的数据类型为Object
 *          2.4 对于List<?>就不能向其内部添加数据，除了null之外：
 *              list.add("DD"); // error
 *              list .add(null); // right
 */
public class GenericTest {

    /** 通配符的使用 */
    @Test
    public void test03() {
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;
        list = list1; // right，编译通过
        list = list2; // right
        
        // print(list1); // right，编译通过
        // print(list2); // right

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("DD");
        list3.add("CC");

        list = list3;
        // 1. 对于List<?>就不能向其内部添加数据，除了null之外：
        // list.add("EE"); // error
        list.add(null); // right

        Object o = list.get(0);
        System.out.println(o);
        System.out.println("----------------------------");
        print(list); // right
    }
    
    public void print(List<?> list) {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /** 泛型在继承方面的体现 */
    @Test
    public void test01(){
        Object obj = null;
        String str = null;
        obj = str; // right
        // str = obj; // error

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2; // right

        List<Object> list1 = null;
        List<String> list2 = null;
        // list1 = list2; // error, 此时的list1和list2的类型不具有子父类关系
        show(list1);
        show1(list2); // right
        // show(list2); // error
        // show1(list1); // error
    }

    @Test
    public void test02() {
        List<String> list1 = null;
        ArrayList<String> list2 = null;
        list1 = list2; // right --> 相当于 List<String> list = new ArrayList<String>();
    }


    public void show(List<Object> list) {

    }

    public void show1(List<String> list) {

    }
}
