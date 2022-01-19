/**
 * @author : zhilx(zhilx1997@sina.com)
 * @Description: 案例Demo
 * @version: v1.0
 * @data: 2022/1/19 15:54
 * @node: 创建三个窗口售票，总票数为10张
 *        此时存在线程安全问题，带解决
 */
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
public class ThreadDemo {
    public static void main(String[] args) {
        windows w1 = new windows("window_1");
        windows w2 = new windows("window_2");
        windows w3 = new windows("window_3");
        w1.start();
        w2.start();
        w3.start();
    }
}
