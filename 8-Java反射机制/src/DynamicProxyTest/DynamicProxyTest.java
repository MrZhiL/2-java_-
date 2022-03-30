package DynamicProxyTest;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: DynamicProxyTest
 * @Description: Java - 动态代理的举例
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/30 20:30
 * @node:
 *          要想实现动态代理，需要解决的问题：
 *          问题1：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象
 *          问题2：当通过代理类的对象调用方法时，如何动态的去调用被代理类中的同名方法
 */

interface Human {
    String getBelief();
    void eat(String food);
}

// 被代理类
class SuperHuman implements Human {

    @Override
    public String getBelief() {
        return new String("I believe I can fly!");
    }

    @Override
    public void eat(String food) {
        System.out.println("超人喜欢吃" + food);
    }
}

//
class ProxyFactory {

    // 通过传输和返回Object对象来解决问题1，从而动态的创建一个被代理类及其对象
    public static Object getProxyInstance(Object obj) {
        // 此时需要通过创建MyInvocationHandler对象来实现动态绑定
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);

        // 在动态代理类实现中，调用Reflect中的Proxy对象来创建被代理类
        Object returnVal = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
        return returnVal;
    }
}

// Proxy.newProxyInstance()方法中的第三个参数，此时是为了解决问题2；、
// 可以通过MyInvocationHandler来实现调用代理类对象的方法时，自动的调用被代理类中的同名方法
class MyInvocationHandler implements InvocationHandler {

    Object obj;

    public MyInvocationHandler() {

    }

    public void bind(Object obj) {
        this.obj = obj;
    }

    @Override
    // invoke()方法中的method即为被代理类中的方法，args即为被代理类中方法的形参
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(obj, args);
        return invoke;
    }
}

public class DynamicProxyTest {
    public static void main(String[] args) {
        // 创建被代理类
        SuperHuman superHuman = new SuperHuman();

        // 通过将被代理类传入代理类中
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superHuman);

        // 调用被代理类的方法
        // 当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("四川火锅！");

        System.out.println("----------------------");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        System.out.println(ProxyFactory.getProxyInstance(nikeClothFactory));

        // note: 不可以强转为NikeClothFactory，否则会报错
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyInstance1.produceCloth();
    }
}
