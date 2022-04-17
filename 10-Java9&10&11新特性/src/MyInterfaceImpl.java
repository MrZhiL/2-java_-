/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/17 9:00
 * @node:
 */
public class MyInterfaceImpl implements MyInterface{

    @Override
    public void methodAbstract() {
        System.out.println("实现类实现抽象接口");
    }

    // 默认方法可以不用重写，但是抽象方法必须重写
    @Override
    public void methodDefault() {
        System.out.println("实现类重写默认方法");
    }

    public static void main(String[] args) {
        // 1. 接口中的静态方法只能由接口自己调用, 接口的实现类不能调用接口的静态方法
        MyInterface.methodStatic();
        // MyInterfaceImpl.methodStatic();

        // 2. 实现类可以调用非静态方法
        MyInterfaceImpl myInterface = new MyInterfaceImpl();
        myInterface.methodAbstract();
        myInterface.methodDefault();
        // myInterface.methodStatic(); //error, 不可以调用静态方法
        // myInterface.methodPrivate(); // error，不可以调用私有方法
    }
}
