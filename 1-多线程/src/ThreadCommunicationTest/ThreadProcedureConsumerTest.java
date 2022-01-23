package ThreadCommunicationTest;

/**
 * @ClassName: ThreadCommunicationTest
 * @Description: Java - 经典线程通信例题：生产者与消费者问题
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/23 8:51
 * @node:
 *        生产者（producer）将产品交给店员（clerk），而消费者（Customer）从店员处取走产品，店员一次只能持有固定数量的产品（比如，20），
 *        如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，
 *        店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 *        分析：
 *        1. 是否为多线程问题？是，生产者线程，消费者线程
 *        2. 是否有共享数据？是，产品（或店员）
 *        3. 如何解决线程的安全问题？同步机制，有三种方法
 *        4. 是否涉及线程的通信？是
 */

// 用来声明一个全局常量
final class Constant {
    // 私有的构造方法
    private Constant() {}
    public static final int maxNum = 20;
    public static final int minNum = 0;
}

class Clerk {
    private int produceCount = 0;

    // 生产者生产产品
    public synchronized void produceProdect() {
        if (produceCount < Constant.maxNum) {
            produceCount++;
            System.out.println(Thread.currentThread().getName() + "开始生产第" + produceCount + "个产品");
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费者消费产品
    public synchronized void consumeProdect() {
        if (produceCount > Constant.minNum) {
            System.out.println(Thread.currentThread().getName() + "开始消费第" + produceCount + "个产品");
            produceCount--;
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 这是使用实现接口的方式实现多线程
class Producer implements Runnable {
    Clerk clk = new Clerk();

    public Producer(Clerk clk) {
        this.clk = clk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "生产开始生产产品");
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clk.produceProdect();
        }
    }
}

class Customer implements Runnable {
    Clerk clk = new Clerk();

    public Customer(Clerk clk) {
        this.clk = clk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "生产开始消费产产品");
        while(true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clk.consumeProdect();
        }
    }
}

public class ThreadProcedureConsumerTest {

    public static void main(String[] args) {
        Clerk clk = new Clerk();
        Producer prod1 = new Producer(clk);
        Customer cust1 = new Customer(clk);
        Customer cust2 = new Customer(clk);

        Thread p1 = new Thread(prod1, "生产者1");
        Thread c1 = new Thread(cust1, "消费者1");
        Thread c2 = new Thread(cust2, "消费者2");

        p1.start();
        c1.start();
        c2.start();
    }
}
