package ThreadCommunicationTest;

import java.util.concurrent.locks.ReentrantLock;
/**
 * @ClassName: ThreadCommun1Test
 * @Description: Java - 线程间通信方式
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/22 11:32
 * @node: 线程通信的例子：两个线程交替打印1-100之间的数字
 *
 *        线程通信用到的三个方法：
 *        1. `wait()` : 一旦执行此方法，当前线程就会进入阻塞状态，并释放同步监视器
 *        2. `notify()` : 一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个。
 *        3. `notifyAll()` : 一旦执行此方法，就会唤醒所有被wait()的线程。
 *
 *        说明:
 *        1. `wait()\notify()\norifyAll()`这三个方法必须使用在同步代码块或同步方法中。
 *        2. `wait()\notify()\norifyAll()`这三个方法的调用者必须是同步代码块或同步方法中**同步监视器**，否则会出现`java.lang.IllegalMonitorStateException`异常。
 *        3. `wait()\notify()\norifyAll()`这三个方法定义在`java.lang.Object`类中
 *
 *        ### 面试题：sleep() 和 wait()的异同？
 *        - 相同点：一旦执行这两个方法，都可以使得当前线程进入阻塞状态
 *        - 不同点：
 *    	        1. 两个方法声明的位置不同，Thread类中声明sleep()，Object类中声明wait()；
 *    	        2. 调用的要求不同：sleep()可以在任何需要的场景下调用。**wait()必须在同步代码块或同步方法中使用**。
 *    	        3. 关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep()方法不会释放同步监视器，wait()方法会释放同步监视器（锁）。
 */

// 一：使用同步代码块进行线程安全同步
class PrintNumber implements Runnable {
    private int number = 1;
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                // 2. 进行notifyAll()唤醒
                obj.notifyAll();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (number < 100) {
                    System.out.println(Thread.currentThread().getName() + " : " + number);
                    ++number;

                    // 1. 使用wait()进行阻塞
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}

// 二、使用lock锁进行线程安全同步(不可以使用lock锁中调用wait()/notify()，因为lock锁住线程之后，无法调用notify()解开)
class PrintNumber2 implements Runnable {
    private int number = 1;

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            if (number < 100) {

                try {
                    lock.lock();
                    // 2. 进行notifyAll()唤醒
                    notifyAll();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 加锁 : " + number);
                    ++number;

                    // 1. 使用wait()进行阻塞
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 解锁");
                    lock.unlock();
                }
            } else {
                break;
            }
        }
    }
}
public class ThreadCommun1Test {
    public static void main(String[] args) {
        ThreadCommun1Test tc = new ThreadCommun1Test();
        tc.method1();
//        tc.method2(); // error
    }

    public void method1() {
        PrintNumber pn = new PrintNumber();

        Thread t1 = new Thread(pn, "Thread_1");
        Thread t2 = new Thread(pn, "Thread_2");

        t1.start();
        t2.start();
    }

    // error
    public void method2() {
        PrintNumber2 pn2 = new PrintNumber2();

        Thread t1 = new Thread(pn2, "Thread_1");
        Thread t2 = new Thread(pn2, "Thread_2");

        t1.start();
        t2.start();
    }
}
