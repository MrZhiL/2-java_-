package ThreadSynchronizationTest;

import static java.lang.Thread.sleep;

/**
 * @ClassName: ThreadWindowsDemo2
 * @Description: Java的同步方法：使用同步代方法
 * @author: zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/21 11:16
 * @node:
 *        4.2 方式二：同步方法
 *        如果操作共享数据的代码完成的声明在一个方法中，我们不妨将此方法声明为同步的。
 *
 *        关于同步方法的踪迹：
 *          - 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明
 *          - 非静态的同步方法，同步监视器是：this
 *          - 静态的同步方法，同步监视器是：当前类本身
 */

// 方式二：使用同步方法将多线程声明为同步的
class windows3 implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            show();
        }
    }

    // note: 此时使用的是同步方法，若没有声明synchronized(obj)，则默认为使用了this对象
        private synchronized void show() {
        try {
            sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ticket > 0) {
            // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
            System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
            ticket--;
        }
    }
}

// 使用同步方法解决继承的多线程安全问题
class windows4 extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            show();
        }
    }

    // note: 此时使用的是同步方法，若没有声明synchronized(obj)，则默认为使用了this对象
        // private synchronized void show() { // 此时的同步监视器为w4_1, w4_2, w4_3，所以这个方式是错误的，需要将其声明为static的
        private static synchronized void show() { // 此时的同步监视器为window4.class
        try {
            sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ticket > 0) {
            // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
            System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
            ticket--;
        }
    }
}

public class ThreadWindowsDemo2 {
    public static void main(String[] args) {
        ThreadWindowsDemo2 demo = new ThreadWindowsDemo2();
        // demo.method3();
        System.out.println("--------------------------");
        demo.method4();
    }

    public void method3() {
        windows3 w = new windows3();
        Thread t1 = new Thread(w, "window3_1");
        Thread t2 = new Thread(w, "window3_2");
        Thread t3 = new Thread(w, "window3_3");

        t1.start();
        t2.start();
        t3.start();
    }

    public void method4() {
        windows4 w4_1 = new windows4();
        windows4 w4_2 = new windows4();
        windows4 w4_3 = new windows4();

        w4_1.setName("window4_1");
        w4_2.setName("window4_2");
        w4_3.setName("window4_3");

        w4_1.start();
        w4_2.start();
        w4_3.start();
    }
}
