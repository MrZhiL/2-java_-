import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: ThreadCreateTest3
 * @Description: Java 创建多线程方式三：实现Callable接口
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/23 9:41
 * @node: - 新增方式三：实现`Callable`接口
 *        - 与使用`Runnable`相比，`Callable`功能更强大些：
 *          - 相比run()方法，可以有返回值；
 *          - 方法可以抛出异常
 *          - 支持泛型的返回值
 *          - 需要借助`Future Task`类，比如获取返回结果
 *        - `Futher`接口
 *          - 可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
 *          - **FutherTask是Futher接口的唯一的实现类**
 *          - FutherTask同时实现了Runnable，Futher接口。它既可以作为Runnable被线程执行，又可以作为Futher得到Callable的返回值。
 *
 *      - 步骤：
 *        1. 创建一个实现`Callable`的实现类
 *        2. 实现`call()`方法，将此线程需要执行的操作声明在`call()`中
 *        3. 创建`Callable`接口实现类的对象
 *        4. 将此`Callable`接口实现类的对象作为传递到`FutherTask`构造器中，并创建`FutherTask`的对象的
 *        5. 将`FutherTask`的对象作为单数传递到`Thread`类的构造器中，创建`Thread`对象。
 *        6. 可以通过`get()`方法获取`Callable`中的`call()`方法的返回值
 *      - 如何理解实现`Callable`接口的方式创建多线程比实现`Runnable`接口创建多线程方式强大？
 *        - `call()`方法可以有返回值
 *        - `call()`方法可以抛出异常，从而被外面操作，获取异常的信息
 *        - `Callable`是支持泛型的
 */

// 1. 创建一个实现Callable的实现类
// note: Callable支持泛型，这里使用Integer进行介绍
class NewThread implements Callable<Integer> {
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

class NewThread2 implements Callable<Integer> {
    @Override
    // 2. 实现`call()`方法，将此线程需要执行的操作声明在`call()`中
    public Integer call() throws Exception {
        int sum = 0;
        // 找出1-100中所有的偶数并返回偶数和
        for (int i = 1; i <= 100; ++i) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                sum += i;
            }
        }

        return sum;
    }
}

public class ThreadCreateTest3 {
    public static void main(String[] args) {
        // 3. 创建`Callable`接口实现类的对象
        NewThread newThread = new NewThread();
        NewThread2 newThread2 = new NewThread2();

        // 4. 将此`Callable`接口实现类的对象作为传递到`FutherTask`构造器中，并创建`FutherTask`的对象的
        FutureTask<Integer> futureTask = new FutureTask<Integer>(newThread);
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(newThread2);

        // 5. 将`FutherTask`的对象作为单数传递到`Thread`类的构造器中，创建`Thread`对象
        new Thread(futureTask, "Thread_1").start();
        new Thread(futureTask2, "Thread_2").start();

        // 6. 可以通过`get()`方法获取`Callable`中的`call()`方法的返回值
        try {
            Integer sum = futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Integer sum = futureTask2.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
