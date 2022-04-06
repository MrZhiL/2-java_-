package LambdaTest;

/**
 * @ClassName: LambdaTest
 * @Description: Java - 自定义函数式接口
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/5 20:34
 * @node: 自定义函数式接口
 */
@FunctionalInterface
public interface MyInterface {
    void method1();
    // void method2(); // error, 函数式抽象接口中只能定义一个抽象方法
}
