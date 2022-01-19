/**
 * @author : zhilx(zhilx1997@sina.com)
 * @Description: Java-多线程优先级的设置
 * @version: v1.0
 * @data: 2022/1/19 15:34
 * @node:  多线程优先级
 *   - Java的调度方法
 *     - 同优先级线程组成先进先出（FIFO），使用时间片策略
 *     - 对高优先级，使用优先调度的抢占式策略
 *
 *   - 线程的优先级等级
 *     - MAX_PRIORITY : 10
 *     - MIIN_PRIORITY: 1
 *     - NORM_PRIORITY: 5
 *   - 涉及的方法
 *     - getPriority(): 返回线程优先值
 *     - setPriority(int newPriority) : 改变线程的优先级
 *   - 说明
 *     - 线程创建时继承父线程的优先级
 *     - 低优先级只是获得调度的概率低，并非一定是在高优先级之后才被调用。
 */
public class ThreadPriorityTest {
    public static void main(String[] args) {
        MyThread my1 = new MyThread();
        MyThread2 my2 = new MyThread2();

        System.out.println("---------------------------------");
        System.out.println(my1.getName() + " priority is " + my1.getPriority());
        System.out.println(my2.getName() + " priority is " + my2.getPriority());

//        my1.start();
//        my2.start();

        System.out.println("---------------------------------");
        my1.setPriority(Thread.MAX_PRIORITY);
        my2.setPriority(Thread.MIN_PRIORITY);
        my1.start();
        my2.start();

        try {
            my2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------");
        System.out.println(my1.getName() + " priority is " + my1.getPriority());
        System.out.println(my2.getName() + " priority is " + my2.getPriority());
        System.out.println("---------------------------------");
    }
}
