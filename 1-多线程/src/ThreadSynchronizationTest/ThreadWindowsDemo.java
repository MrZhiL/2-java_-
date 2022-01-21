package ThreadSynchronizationTest;

import static java.lang.Thread.sleep;

/**
 * @author : zhilx(zhilx1997@sina.com)
 * @Description: Java的同步方法：使用同步代码块
 * @version: v1.2
 * @data: 2022/1/21 9:25
 * @node:
 *         v1.0, v1.1 创建三个窗口售票，总票数为10张(通过继承Thread类来实现)
 *         1. 问题：买票过程中，出现了重票和错票问题，出现线程安全问题。
 *         2. 原因：当某个线程操作车票的过程中，在尚未完成操作时，其他线程也参与进来，从而导致出现问问题
 *         3. 如何解决：当一个线程a在操作ticket的时候，其他线程不能参与进来，直到线程a操作完成ticket操作时
 *                    其他线程才可以进行操作。这种情况，即使线程a出现了阻塞，也不能被改变。
 *         4. 在Java中，我们通过同步机制，来解决线程的安全问题
 *         -4.1 方式一：同步代码块
 *              synchronized(同步监视器) {
 *                  // 需要被同步的代码
 *
 *              }
 *              说明：1. 操作共享数据的代码，即为需要被同步的代码。 --> 不能包代码过多或过少，必须值包含需要被同步的代码
 *                   2. 共享数据：多个线程共同操作的变量。比如：ticket
 *                   3. 同步监视器，俗称：锁。**任何一个类的对象**，都可以充当锁
 *                      要求：多个线程必须要共用同一把锁。
 *                   4. (补充)在通过实现接口来实现多线程的方式中，可以考虑通过使用this来充当同步监视器
 *                   5. (补充)在通过继承来实现多线程的方式中，要慎用this来充当监视器，可以考虑当前类充当同步监视器
 *
 *         -4.2 方式二：同步方法
 *              如果操作共享数据的代码完成的声明在一个方法中，我们不妨将此方法声明为同步的。
 *
 *          5. 优点：同步的方式，解决了线程的安全问题
 *             局限性：操作同步代码时，只能有一个线程参与，其他线程等待。相等于是一个单线程的过程，效率较低。
 */

// 方式一: 通过实现Runnable接口来实现多线程，并使用同步代码块方式来解决线程安全问题
class windows implements Runnable {
    // note: 此时可以不必声明为static的，因为在method2()中多个线程共有同一个windows2的实例化对象
    private int ticket = 100;

    // note: 使用同步代码块进行线程安全操作时，需要多个线程共用同一把锁，因此需要共用同一个变量
    private final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            // Object obj = new Object(); // error, 所有的线程必须共用同一把锁
            // 4. (补充)在通过实现接口来实现多线程的方式中，可以考虑通过使用this来充当同步监视器
            synchronized(obj) {
            // synchronized(this) {    // right, 此时this表示windows w = new windows()对象本身
                try {
                    sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
                    System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

// 方式一_1 使用代码块的方式解决继承方式实现多线程的 线程同步问题
class windows2 extends Thread {
    private static int ticket = 100;

    // 在继承实现多线程中，需要创建静态变量来解决同步问题
    private static final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            // 5. (补充)在通过继承来实现多线程的方式中，要慎用this来充当监视器，可以考虑当前类充当同步监视器
            // synchronized(obj) {
            // synchronized(this) { // error, 此时this表示不同的实例化对象w2_1, w2_2, w2_3
            synchronized(windows2.class) { // right, windows2.class为window2的唯一对象
                // while(true) { // error，如果将while语句放到同步代码块中，则一直会被一个线程占用，使其他线程饿死
                try {
                    sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    System.out.println(getName() + "售出票号为: " + ticket + "的票");
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class ThreadWindowsDemo {
    public static void main(String[] args) {
        ThreadWindowsDemo demo = new ThreadWindowsDemo();
//        demo.method();
        System.out.println("--------------------------");
        demo.method2();
    }

    public void method() {
        windows w = new windows();
        Thread t1 = new Thread(w, "window_1");
        Thread t2 = new Thread(w, "window_2");
        Thread t3 = new Thread(w, "window_3");

        t1.start();
        t2.start();
        t3.start();
    }

    public void method2() {
        windows2 w2_1 = new windows2();
        windows2 w2_2 = new windows2();
        windows2 w2_3 = new windows2();

        w2_1.setName("window2_1");
        w2_2.setName("window2_2");
        w2_3.setName("window2_3");

        w2_1.start();
        w2_2.start();
        w2_3.start();
    }
}
