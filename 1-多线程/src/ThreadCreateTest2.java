package src;

/**
 * @Description: ava-多线程的创建方式二：通过实现Runnable接口来实现
 * @author : zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/19 16:07
 * @node: 步骤：
 *          1. 创建一个实现了Runnable接口的类
 *          2. 实现类去实现Runnable接口中的抽象方法：run()
 *          3. 创建实现类的实例化对象 ： `MyThread p = new MyThread();`
 *          4. 将此实例化对象作为参数传递到Thread类的构造器中，并创建Thread类的对象: `new Thread(p)`
 *          5. 通过Thread类的对象调用start() ： `new Thread(p). start()`
 *
 *          6. note: 如果创建多个线程，可以共用同一个实现Runnable接口的类的实例化对象
 */

// 1. 创建一个实现了Runnable接口的类
class MThread implements Runnable {
    @Override
    // 2. 实现类去实现Runnable接口中的抽象方法：run()
    public void run() {
        for (int i = 0; i < 100; ++i) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}
public class ThreadCreateTest2 {
    public static void main(String[] args) {
        // 3. 创建实现类的实例化对象
        MThread mt = new MThread();

        // 4. 将此实例化对象作为参数传递到Thread类的构造器中，并创建Thread类的对象
        Thread t = new Thread(mt);
        t.setName("Thread1");

        // 5. 通过Thread类的对象调用start()
        t.start();

        // note: 如果创建多个线程，可以共用同一个实现Runnable接口的类的实例化对象
        Thread t2 = new Thread(mt);
        t2.setName("Thread2");
        t2.start();
    }
}
