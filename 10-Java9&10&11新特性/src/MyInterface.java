/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/17 8:56
 * @node:
 */
public interface MyInterface {
    // 接口中如果不声明，则默认为public权限
    // 如下的三个方法的权限修饰符都是public
    void methodAbstract();

    static void methodStatic() {
        System.out.println("接口中的静态方法");
    }

    default void methodDefault() {
        System.out.println("接口中的默认方法");
    }

    // jdk9 及以后允许接口中定义私有方法
    private void methodPrivate() {
        System.out.println("接口中的私有方法");
    }
}
