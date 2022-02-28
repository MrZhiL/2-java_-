package src;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @ClassName: ListTestQuestion
 * @Description: Java - remove()方法的调用问题
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/28 10:16
 * @node: 区分list中的remove(int index) 和 remove(Object obj)方法
 */
public class ListTestQuestion {
    @Test
    public void test01() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        System.out.println(arrayList);
        updateArrayList(arrayList);
        System.out.println("调用updateArrayList : " + arrayList);
        updateArrayList2(arrayList);
        System.out.println("调用updateArrayList2 : " + arrayList);
    }

    public void updateArrayList(ArrayList arrayList) {
        arrayList.remove(2); // 此时调用的是List中重载的remove(int index)方法
    }

    public void updateArrayList2(ArrayList arrayList) {
        arrayList.remove(new Integer(2)); // 此时调用的为Collection中的remove(Object obj)方法
    }

}
