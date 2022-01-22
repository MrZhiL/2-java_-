package DeadThreadTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest.java
 * @Description: Java - 解决死锁的方法三：通过Lock锁机制
 * @author: zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/22 9:47
 * @node:
 *      1. Lock锁介绍：
 *      - 从JDK5.0 开始，Java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。
 *      - `java.util.concurrent.locks.Lock` **接口是控制多个线程对共享资源进行访问的工具**。锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。
 *      - `ReentrantLock`类实现了Lock，它拥有与`synchronized`相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是`ReentrantLock`，可以显式加锁、释放锁。
 *
 *      2. synchronized 与 Lock 锁的区别
 *      2.1. Lock 是显式锁（需要手动开启和关闭锁，别忘记关闭锁），synchronized是隐式锁，出了作用域自动释放。
 *      2.2. Lock 只有代码块锁，synchronized 有代码块锁和方法锁
 *      2.3. 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提过更多的子类）。
 *
 *      3. 优先使用顺序：
 *      Lock -> 同步代码块（已经进入方法体，分配了相应资源）-> 同步方法（在方法体之外）
 *
 *      4. Lock锁的使用步骤：
 *      4.1 首先通过 ReentrantLock类 来创建一个锁（需要声明为run()方法之外，因为所有的线程要共用同一个lock锁）
 *          ReentrantLock lock_t = new ReentrantLock(); // new ReentrantLock(true)表示为公平的，即按照FIFO的顺序进行资源的分配
 *      4.2 进行lock()方法，进行加锁
 *      4.3 进行相应代码的调用
 *      4.4 进行unlick()解锁
 */
class window implements Runnable {
    private int ticket = 100;

    // 方法三：通过Lock锁来解决线程安全问题
    // 1. 首先通过 ReentrantLock类 来创建一个锁
    ReentrantLock lock_t = new ReentrantLock(); // new ReentrantLock(true)表示为公平的，即按照FIFO的顺序进行资源的分配

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 进行lock()方法，进行加锁
                lock_t.lock();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
                    System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 4. 调用unlock()方法，进行解锁
                lock_t.unlock();
            }

        }

    }
}
public class LockTest {
    public static void main(String[] args) {
        window w = new window();

        Thread t1 = new Thread(w, "Thread1");
        Thread t2 = new Thread(w, "Thread2");
        Thread t3 = new Thread(w, "Thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}
