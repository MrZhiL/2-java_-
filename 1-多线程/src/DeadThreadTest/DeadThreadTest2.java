package DeadThreadTest;

import java.sql.SQLOutput;

import static java.lang.Thread.sleep;

/**
 * @ClassName: DeadThreadTest2
 * @Description: Java - 死锁案例测试2
 * @author: zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/22 9:11
 * @node:
 */

class A {
    public synchronized void foo(B b) {
        System.out.println("当前线程名: " + Thread.currentThread().getName()
                + " 进入了A实例的foo方法");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程名: " + Thread.currentThread().getName()
        + " 企图调用B实例的last方法");

        b.last();
    }

    public synchronized void last() {
        System.out.println("进入了A类内部的last()方法");
    }
}

class B {
    public synchronized void bar(A a) {
        System.out.println("当前线程名: " + Thread.currentThread().getName()
                + " 进入了B实例的bar方法");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程名: " + Thread.currentThread().getName()
        + " 企图调用A实例的last方法");

        a.last();
    }

    public synchronized void last() {
        System.out.println("进入了B类内部的last()方法");
    }
}

public class DeadThreadTest2 implements Runnable{
    A a = new A();
    B b = new B();

    public void init() {
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入了主线程之后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        b.bar(a);
        System.out.println("进入了副线程之后");
    }

    public static void main(String[] args) {
        DeadThreadTest2 dl = new DeadThreadTest2();
        Thread t = new Thread(dl);

        t.start(); // 此时调用的是副线程

        dl.init(); // 此时通过主线程调用
    }
}
