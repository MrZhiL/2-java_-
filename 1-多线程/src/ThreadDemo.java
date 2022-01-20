/**
 * @author : zhilx(zhilx1997@sina.com)
 * @Description: 案例Demo
 * @version: v1.1
 * @data: 2022/1/19 15:54
 * @node:
 *        v1.0 创建三个窗口售票，总票数为10张(通过继承Thread类来实现) - 此时存在线程安全问题，带解决
 *        v1.1 创建三个窗口售票，总票数为10张，通过实现接口来实现 - 此时存在线程安全问题，带解决
 *
 *        ### 2.4 两种创建多线程方式的对比
 *        开发中：优先选择实现Runnable接口的方式
 *        原因：
 *          -1. 出现的方式没有类的单继承性的局限性
 *          -2. 实现的方式更适合来处理多个线程有共享数据的情况
 *        联系：Thread类本身也实现了Runnable接口
 *        相同点：两种方式都需要重写run()方法，将线程要执行的逻辑声明在run() 中。
 */

// 方法一：通过继承Thread类来实现 - 此时存在线程安全问题，带解决
class windows extends Thread {
    private static int ticket = 100;

    public windows() {
        super();
    }

    public windows(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(getName() + "售出票号为: " + ticket + "的票");
                ticket--;
            } else {
                break;
            }
        }
    }
}

// 方式二：通过实现Runnable接口来实现
class windows2 implements Runnable {
    // note：此时可以不必声明为static的，因为在method2()中多个线程共有同一个windows2的实例化对象
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
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

public class ThreadDemo {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        // td.method1();
        td.method2();
    }

    public void method1() {
        windows w1 = new windows("window_1");
        windows w2 = new windows("window_2");
        windows w3 = new windows("window_3");
        w1.start();
        w2.start();
        w3.start();
    }

    public void method2() {
        windows2 w2 = new windows2();
        Thread t1 = new Thread(w2, "window2_1");
        Thread t2 = new Thread(w2, "window2_2");
        Thread t3 = new Thread(w2, "window2_3");

        t1.start();
        t2.start();
        t3.start();
    }
}

