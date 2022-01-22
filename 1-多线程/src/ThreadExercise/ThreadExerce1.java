package ThreadExercise;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadExerce1
 * @Description: Java - 线程安全练习_1
 * @author: zhilx(zhilx1997 @ sina.com)
 * @version: v1.0
 * @data: 2022/1/22 10:21
 * @node: 银行有一个账户：有两个储户分别向同一个账户存3000元，每次存1000，存3次，每次存完后打印余额。
 *      分析：
 * 	        1. 是否为多线程问题？是，因为有两个储户线程
 * 	        2. 是否有共享数据？  是，账户（或账户余额）
 * 	        3. 是否有线程安全问题？ 是
 * 	        4. 需要考虑如何解决线程安全问题？同步机制：有三种方式（这里使用继承方式实现多线程，然后用同步方法和lock来解决线程安全问题）
 */

// 1. 创建一个银行账户
class Account {
    private double balance; // 创建账户余额

    public Account() {
        balance = 0;
    }

    public Account(double amt) {
        balance = amt;
    }

    // 存钱，通过同步方法来实现
    // note: 因为这时候所有储户共用同一个账户，所以可以不用声明为静态的，使用全局监视器为this（this为共用的）
    public synchronized void deposit(double amt) {
        if (amt >0) {
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "存钱成功！当前账户余额为: " + balance);
        }
    }

    // 存钱，通过Lock锁来实现
    ReentrantLock lock = new ReentrantLock(true); // 设置为公平的
    public void deposit2(double amt) {
        if (amt >0) {
            try {
                lock.lock();

                balance += amt;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "存钱成功！当前账户余额为: " + balance);
            } finally {
                lock.unlock();
            }
        }
    }
}

// 2. 创建储户
class Customer extends Thread {
    Account acct;   // 在储户中创建一个银行账户，此时需要两个储户共用同一个账户

    // 可以通过构造器来实现多个储户共用同一个账户
    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        // 在run方法中实现同步, 每次存入1000元，共存入3此
        for (int i = 0; i < 3; ++i) {
            acct.deposit2(1000);
        }
    }
}

public class ThreadExerce1 {
    public static void main(String[] args) {
        Account acct = new Account(0);

        // 因为c1和c2都是通过构造器初始化的，所以两个储户共用同一个账户
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
