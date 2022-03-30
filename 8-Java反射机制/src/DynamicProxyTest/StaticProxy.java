package DynamicProxyTest;

/**
 * @ClassName: StaticProxy
 * @Description: Java - 静态代理举例
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/30 20:22
 * @node: 静态代理举例
 *          特点：代理类和被代理类在编译期间就已经确定
 */

interface ClothFactory {
    void produceCloth();
}

// 代理类
class ProxyClothFactory implements ClothFactory {

    // 用被代理类对象进行实例化
    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("准备中...");
        factory.produceCloth();
        System.out.println("处理中...");
    }
}

// 被代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("NikeFactory 生产运动服饰!");
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        // 创建被代理类对象
        ClothFactory nikeClothFactory = new NikeClothFactory();

        //  创建代理类对象
        ClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);

        proxyClothFactory.produceCloth();
    }
}
