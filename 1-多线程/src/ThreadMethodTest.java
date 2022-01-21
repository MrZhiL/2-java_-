/**
 * @Description: Thread类的有关方法()
 * @author : zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/19 13:53
 * @node: Thread类的有关方法()
 *      1. `void start()` 启动线程，并执行对象的run()方法
 *      2. `run()` 线程在被调度时执行的操作
 *      3. `String getName()` 返回线程的名称
 *      4. `void setName(String name)` 设置线程名称
 *      5. `static Thread currentThread()` 静态方法，返回当前线程。在Thread子类中就是this，通常用于主线程和Runable实现类
 *      6. `static void yield` 线程让步
 *          - 暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程
 *          - 若队列中没有同优先级的线程，忽略此方法
 *      7. `join()` 当某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，直到join()方法加入的join线程执行完为止
 *          - 如，在线程a中调用线程b的join()，此时线程a就会进入阻塞状态，直到线程b完全执行以后，线程a才结束阻塞状态。
 *      8. `static void sleep(long millis)` (指定时间：毫秒)
 *          - 令当前活动线程在指定时间段内放弃对CPU的控制，使其他线程有机会被执行，时间到后，重新排队。
 *          - 抛出InterruptedException异常
 *      9. `stop()` 强制线程声明期结束，不推荐使用
 *      10. `boolean isAlive()` 返回boolean，判断线程是否还活着
 */
class MyThread3 extends Thread {
    public MyThread3() {
        super();
    }

    public MyThread3(String name) {
        super(name);
    }

    @Override
    // 2. `run()` 线程在被调度时执行的操作
    public void run() {
        for (int i = 0; i < 100; ++i) {
            if (i % 2 == 0) {
                // 3. `String getName()` 返回线程的名称
                // 5. `static Thread currentThread()` 静态方法，返回当前线程。在Thread子类中就是this，通常用于主线程和Runable实现类
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }

            if (i % 20 == 0) {
                // 6. `static void yield` 线程让步: 暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程
                 yield();
            }
        }
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {
        MyThread3 myt3 = new MyThread3();

        // 4. `void setName(String name)` 设置线程名称
        myt3.setName("MyThread3");

        // 1. 使用start()方法启动线程
        myt3.start();

        // 更改main线程为mainThread
        Thread.currentThread().setName("mainThread");
        for (int i = 0; i <100; ++i) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }

            // 7. `join()` 当某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，直到join()方法加入的join线程执行完为止
            // public final void join() throws InterruptedException，join方法会抛出异常，因此需要try-catch处理
            if (i == 20) {
                try {
                    myt3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 8. `static void sleep(long millis)` (指定时间：毫秒): 令当前活动线程在指定时间段内放弃对CPU的控制，使其他线程有机会被执行，时间到后，重新排队。
            // public static native void sleep(long millis) throws InterruptedException; sleep抛出了异常，因此需要try-catch处理
            try {
                // sleep中的时间为ms，1000ms = 1s
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 9. `stop()` 强制线程声明期结束，不推荐使用(已经抛出stop函数)
            // 10. `boolean isAlive()` 返回boolean，判断线程是否还活着
            if (i == 90) {
                System.out.println(Thread.currentThread().getName() + "is alive ? " + Thread.currentThread().isAlive());
                System.out.println(myt3.getName() + "is alive ? " + myt3.isAlive());
            }

        }

        System.out.println(myt3.getName() + " priority is : " + myt3.getPriority());
        System.out.println(Thread.currentThread().getName() + " priority is " +Thread.currentThread().getPriority());

    }
}
