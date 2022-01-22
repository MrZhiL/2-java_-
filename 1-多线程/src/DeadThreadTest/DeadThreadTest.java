package DeadThreadTest;

import static java.lang.Thread.sleep;

/**
 * @ClassName: DeadThreadTest
 * @Description: Java - 死锁发生情况 (该案例因为分别使用s1和s2两个同步锁，从而变成死锁)
 * @author: zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/21 22:20
 * @node:
 *       - 死锁：
 *          - 不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *          - 出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 *      - 解决方法：
 *          - 专门的算法、原则
 *          - 尽量减少同步资源的定义
 *          - 尽量避免嵌套同步
 */
public class DeadThreadTest {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        new Thread(){
            @Override
            public void run() {
                synchronized(s1) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    s1.append("a");
                    s2.append("1");

                    synchronized(s2) {
                        s2.append("2");
                        s1.append("b");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(s2) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    s1.append("c");
                    s2.append("3");

                    synchronized(s1) {
                        s2.append("4");
                        s1.append("d");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
