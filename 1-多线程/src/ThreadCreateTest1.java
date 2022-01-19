/**
 * @Description: Java-多线程的创建方式一：通过继承Thread来实现
 * @author : zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/18 9:03
 * @node: 步骤 ：
 *        1. 创建一个继承于Thread类的子类
 *        2. 重写Thread类中的run()方法 --> 该run()方法体中实现该子类要实现的功能
 *        3. 实例化一个子类对象
 *        4. 该实例化的对象调用start() 方法: 1)启动当前线程；2)调用当前线程的run();
 */

// 1. 创建一个继承于Thread类的子类，并计算0-99之间的偶数
class MyThread extends Thread {
    // 2. 重写Thread类中的run方法，并在方法体内实现功能
    @Override
    public void run() {
        // 3. 需要在方法体内实现该子类的功能
        for (int i = 0; i < 100; ++i) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

// 1. 创建一个继承于Thread类的子类，并计算0-99之间的奇数
class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <100; ++i) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}

public class ThreadCreateTest1 {
    public static void main(String[] args) {
        // new ThreadTest1().method1();
        new ThreadCreateTest1().method2();
    }

    // 一、 通过实例化对象来创建多线程
    public void method1() {
        // 4. 实例化一个MyThread子类的对象
        MyThread mt = new MyThread();
        // 5. 调用start方法: 1)启动当前线程；2)调用当前线程的run();
        mt.start();

        // note: 1) 不可以通过调用run()方法来启动线程，此时是通过main()方法来调用的
        // mt.run();

        // note: 2) 如果一个实例化对象已经调用过start()方法吗，则不可以再次调用start()方法，否则会报IllegalThreadStateException异常
        // mt.start();

        // test：同时创建两个线程
        MyThread2 mt2 = new MyThread2();
        mt2.start();
    }

    // 二、通过匿名对象来创建多个线程
    public void method2() {
        new Thread(){
            @Override
            public void run() {
                // 寻找0-99之间的偶数
                for (int i = 1; i <100; ++i) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                // 寻找0-99之间的奇数
                for (int i = 1; i <100; ++i) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    }
                }
            }
        }.start();
    }

}
