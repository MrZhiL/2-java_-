package SingletonPatternTest;

/**
 * @ClassName: SingletonBankTest
 * @Description: Java - 通过同步方式解决懒汉式单例模式中的线程安全问题
 * @author: zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/21 20:45
 * @node:
 */
public class SingletonBankTest {
    public static void main(String[] args) {

    }
}

class Bank {
    // 1. 单例设计模式中需要将构造器初始化
    private Bank() {}

    // 2. 私有化Back的一个属性
    private static Bank instance = null;

    // 3. 返回Back属性的instance方法
    /*
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }

        return instance;
    }*/

    // 4. 改为线程安全的getInstance()方法
    // 4.1 直接通过同步方法进行同步
    /*
    public static synchronized Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }

        return instance;
    }*/

    // 4.2 通过同步代码块进行同步
    /*
    public static Bank getInstance() {
        synchronized(Bank.class) {
            if (instance == null) {
                instance = new Bank();
            }

            return instance;
        }
    }*/

    // note: 在4.1和4.2的方法中，此时由于所有线程都需要进行同步等待，因此效率较低
    // 4.3 可以对4.2进行改进，来提高单例模式的效率
    public static Bank getInstance() {
        // note: 此时通过首先对instance进行判断，从而避免所有节点均需要进行同步等待
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }

        return instance;
    }
}
