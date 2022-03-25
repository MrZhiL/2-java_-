package ReflectionTest2;

import java.io.Serializable;

/**
 * @ClassName: ReflectionTest2
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/25 9:49
 * @node:
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath() {
        System.out.println("所有生物需要进行呼吸");
    }

    public void eat() {
        System.out.println("生物可以吃东西");
    }
}
