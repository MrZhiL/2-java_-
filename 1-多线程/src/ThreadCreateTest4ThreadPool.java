import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadCreateTest4ThreadPool
 * @Description: Java - 创建多线程方式四：线程池
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/23 10:08
 * @node:
*          - JDK 5.0 起提供了线程池相关API : `ExecutorService` 和 `Executors`
*          - `ExecutorService` : 真正的线程池接口。常见子类ThreadPoolExecutor
*            - `void execute(Runnable command)` : 执行任务/命令，无返回值，一般用来执行`Runnable`
*            - `<T>Future<T> submit(Callable<T> task)`: 执行任务，，有返回值，一般用来执行Callable
*            - `void shutdown()` : 关闭连接池
*          - `Executors` : 工具类、线程池的工厂类，用于创建并返回不同类型的线程池
*            - `Executors.newCachedThreadPool()` : 创建一个可根据需要创建新线程的线程池
*            - `Executors.newFixedThreadPool(n)` : 创建一个可重用固定线程数的线程池
*            - `Executors.newSingleThreadExecutor()` : 创建一个只有一个线程的线程池
*            - `Executors.newScheduledThreadPool(n)` : 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
 */

// 1. 创建一个实现Callable的实现类
// note: Callable支持泛型，这里使用Integer进行介绍
class NewThread3 implements Callable<Integer> {
    @Override
    // 2. 实现`call()`方法，将此线程需要执行的操作声明在`call()`中
    public Integer call() throws Exception {
        int sum = 0;
        // 找出1-100中所有的偶数并返回偶数和
        for (int i = 1; i <= 100; ++i) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                sum += i;
            }
        }

        return sum;
    }
}

class NewThread4 implements Runnable {
    @Override
    public void run() {
        // 找出1-100中所有的偶数并返回偶数和
        for (int i = 1; i <= 100; ++i) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}


public class ThreadCreateTest4ThreadPool {
    public static void main(String[] args) {
        // 1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // note: 设置线程池属性,需要将service强转为ThreadPoolExecutor
        // servece.setxxx() // error, ExecutorService没有提供相应的属性
        System.out.println(service.getClass()); // class java.util.concurrent.ThreadPoolExecutor
        ThreadPoolExecutor serviceExe = (ThreadPoolExecutor) service;
        // serviceExe.setCorePoolSize(15);
        // serviceExe.setKeepAliveTime(100);

        // 2. 执行指定的线程的操作，需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NewThread4()); // 适用于Runnable
        service.submit(new NewThread3()); // 适用于Callable

        // 3. 关闭连接池
        service.shutdown();
    }
}
