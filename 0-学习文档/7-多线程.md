# 七、IDEA的使用和多线程

## 1. 程序、进程、线程的概念

- 程序（program）是为完成特定任务、用某种语言编写的一组指令的集合。即指**一段静态的代码**，静态对象。
- 进程（process）是程序的一次执行过程，或是正在运行的一个程序。是一个动态的进程：有它自身的产生、存在和消亡的过程。——生命周期
  - 如：运行中的QQ，运行中的MP3
  - 程序是静态的，进程是动态的
  - **进程作为资源分配的单位**，系统在运行时为每个进程分配不同的内存区域。
- 线程（thread），进程可进一步细化为线程，是一个程序内部的一条执行路径。
  - 若一个进程同一时间并行执行多个线程，就是支持多线程的
  - **线程作为调用和执行的单位，每个线程拥有独立的运行栈和程序计数器（pc）**，线路切开的开销小
  - 一个进程中的多个线程共享相同的内存单元/内存地址空间 -> 它们从同一堆中分配对象，可以访问相同的变量和对象。这就使得线程间通信更简便、高效。单多个线程操作共享的系统资源可能带来**安全的隐患**。
- 单核CPU和多核CPU的理解
  - **一个java程序至少有三个线程**：main()主线程，gc()垃圾回收线程，异常处理线程。当然如果发生异常，会影响主线程。
- 并行与并发：
  - 并行：多个CPU同时执行多个任务。比如，多个人同时做不同的事
  - 并发：一个CPU（采用时间片）同时执行多个任务。比如：秒杀、多个人做同一件事

### 2. 多线程的优点

1. 提高应用程序的响应。对图形化界面更有意义，可增强用户体验。
2. 提高计算机系统CPU的利用率
3. 改善程序结构。将既长又复杂的进程分为多个线程，独立运行，利于理解和修改

### 3. 什么时候需要多线程

- 程序需要同时执行两个或多个任务
- 程序需要实现一些需要等待的任务时，如用户输入、文件读写操作、网络操作、搜索等。
- 需要一些后台运行的程序时。



## 2. 线程的创建和使用

### 2.1 多线程创建方式一：通过继承Thread类来实现

One is to declare a class to be a subclass of `Thread`. This subclass should override the `run` method of class `Thread`. An instance of the subclass can then be allocated and started. For example, a thread that computes primes larger than a stated value could be written as follows:

------

> ```java
>      class PrimeThread extends Thread {
>          long minPrime;
>          PrimeThread(long minPrime) {
>              this.minPrime = minPrime;
>          }
> 
>          public void run() {
>              // compute primes larger than minPrime
>               . . .
>          }
>      }
>  
> ```

------

The following code would then create a thread and start it running:

> ```java
>      PrimeThread p = new PrimeThread(143);
>      p.start();
>  
> ```



Java语言的JVM允许程序运行多个线程，它通过java.lang.Thread类来体现。

1. 步骤：
   1. 创建一个继承于Thread类的子类
   2. 重写Thread类中的run()方法 --> 该run()方法体中实现该子类要实现的功能
   3. 实例化一个子类对象
   4. 该实例化的对象调用start() 方法: 1)启动当前线程；2)调用当前线程的run();

2. start() 方法的作用：
   1. 启动当前线程；
   2. 调用当前线程的run()；

3. 需要注意的问题：
   1. 不可以直接通过调用run()方法来启动线程；
   2. 不可以让已经start()的线程去执行另一个start()方法，会报`IllegalThreadStateException`的异常。



### 2.2 多线程创建方式二：通过实现Runnable接口来实现

- 创建线程的另一种方法是声明一个实现`Runnable`接口的类。  该类然后实现`run`方法。  然后可以分配类的实例，在创建`Thread`时作为参数传递，然后启动。

- The other way to create a thread is to declare a class that implements the `Runnable` interface. That class then implements the `run` method. An instance of the class can then be allocated, passed as an argument when creating `Thread`, and started. The same example in this other style looks like the following:

  ------

  > ```java
  >      class PrimeRun implements Runnable {
  >          long minPrime;
  >          PrimeRun(long minPrime) {
  >              this.minPrime = minPrime;
  >          }
  > 
  >          public void run() {
  >              // compute primes larger than minPrime
  >               . . .
  >          }
  >      }
  >  
  > ```

  ------

  The following code would then create a thread and start it running:

  > ```java
  >      PrimeRun p = new PrimeRun(143);
  >      new Thread(p).start();
  >  
  > ```

  Every thread has a name for identification purposes. More than one thread may have the same name. If a name is not specified when a thread is created, a new name is generated for it.

  Unless otherwise noted, passing a `null` argument to a constructor or method in this class will cause a `NullPointerException`to be thrown.



步骤：

1. 创建一个实现了Runnable接口的类
2. 实现类去实现Runnable接口中的抽象方法：run()
3. 创建实现类的实例化对象 ： `MyThread p = new MyThread();`
4. 将此实例化对象作为参数传递到Thread类的构造器中，并创建Thread类的对象: `new Thread(p)`
5. 通过Thread类的对象调用start() ： `new Thread(p). start()`
6. note: 如果创建多个线程，可以共用同一个实现Runnable接口的类的实例化对象



### 2.3 Thread类的有关方法()

- `void start()` 启动线程，并执行对象的run()方法

- `run()` 线程在被调度时执行的操作

- `String getName()` 返回线程的名称

- `void setName(String name)` 设置线程名称

- `static Thread currentThread()` 静态方法，返回当前线程。在Thread子类中就是this，通常用于主线程和Runable实现类

- `static void yield` 线程让步
  - 暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程
  - 若队列中没有同优先级的线程，忽略此方法
  
- `join()` 当某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，直到join()方法加入的join线程执行完为止

  如，在线程a中调用线程b的join()，此时线程a就会进入阻塞状态，直到线程b完全执行以后，线程a才结束阻塞状态。

- `static void sleep(long millis)` (指定时间：毫秒)
  - 令当前活动线程在指定时间段内放弃对CPU的控制，使其他线程有机会被执行，时间到后，重新排队。
  - 抛出InterruptedException异常
  - 在指定的millitime毫秒时间内，当前线程时阻塞状态。
  
- `stop()` 强制线程声明期结束，不推荐使用（已过时）

- `boolean isAlive()` 返回boolean，判断线程是否还活着



### 2.4 两种创建多线程方式的对比

开发中：优先选择实现Runnable接口的方式

原因：

1. 出现的方式没有类的单继承性的局限性
2. 实现的方式更适合来处理多个线程有共享数据的情况

联系：Thread类本身也实现了Runnable接口

相同点：两种方式都需要重写run()方法，将线程要执行的逻辑声明在run() 中。



## 3. 线程的调用

- 调度策略
  - 时间片
  - 抢占式：高优先级的线程抢先占用CPU
- Java的调度方法
  - 同优先级线程组成先进先出（FIFO），使用时间片策略
  - 对高优先级，使用优先调度的抢占式策略

- 线程的优先级等级
  - MAX_PRIORITY : 10
  - MIIN_PRIORITY: 1
  - NORM_PRIORITY: 5
- 涉及的方法
  - getPriority(): 返回线程优先值
  - setPriority(int newPriority) : 改变线程的优先级
- 说明
  - 线程创建时继承父线程的优先级
  - 低优先级只是获得调度的概率低，并非一定是在高优先级之后才被调用。

## 4. 线程的分类

Java中线程分为两类：一类是守护线程，一类是用户线程。

- 它们在几乎每个方面都是相同的，唯一的区别是判断`JVM`何事离开

- 守护线程时用来服务用户线程的，通过在start() 方法签调用`thread.setDaemon(true)`可以把一个用户线程变成一个守护线程

- Java垃圾回收就是一个典型的守护线程。

- 若`JVM`中都是守护线程，当前`JVM`将退出

- 形象理解：兔死狗烹，鸟尽弓藏

  

## 5. 线程的声明周期

- `JDK`中用`Thread.State`类定义了线程的几种状态：`NEW`, `TIMED_WAITING`. `BOLCKED`, `WITIING`, `TIMED_WAITING`,  `TERMMINATED`, 

  要想实现多线程，必须了主线程中创建新的线程对象。Java语言使用Thread类及其子类的对象来表示线程，在它的一个完整的声明周期中通常要经历**如下的五种状态：**

  1. 新建(`NEW`)：当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态
  2. 就绪()：处于新建状态的线程被`start()`后，将进入线程队列等待CPU时间片，此时它已经具备了运行的条件，只是没分配到CPU资源。
  3. 运行(`RUNNABLE`)：当就绪状态的线程被调度并获得CPU资源时，便进入运行状态，run()方法定义了线程的操作和功能。
  4. 阻塞()：在某种特殊情况下，被人为挂起或执行输入输出操作时，让出CPU并临时中止自己的执行，进入阻塞状态
  5. 死亡：线程完成了它的全部工作或线程被提前强制性地中止或出现异常导致结束。

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\1-多线程\README.assets\image-20220120165318314-16426689565601.png" alt="image-20220120165318314" style="zoom:67%;" />



## 6. 线程的同步

### 6.1 线程同步方法

- 问题的提出

  - 多个线程执行的不确定性引起执行结果的不稳定

  - 多个线程对账本的共享，会造成操作的不完整性，会破坏数据

    <img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\1-多线程\README.assets\image-20220121091650637-16427278254601.png" alt="image-20220121091650637" style="zoom:50%;" />

- 关于同步方法的踪迹：

  - 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明
  - 非静态的同步方法，同步监视器是：this
  - 静态的同步方法，同步监视器是：当前类本身

  ```java
  package ThreadSynchronizationTest;
  
  import static java.lang.Thread.sleep;
  
  /**
   * @author : zhilx(zhilx1997@sina.com)
   * @Description: Java
   * @version: v1.2
   * @data: 2022/1/21 9:25
   * @node:
   *         v1.0, v1.1 创建三个窗口售票，总票数为10张(通过继承Thread类来实现)
   *         1. 问题：买票过程中，出现了重票和错票问题，出现线程安全问题。
   *         2. 原因：当某个线程操作车票的过程中，在尚未完成操作时，其他线程也参与进来，从而导致出现问问题
   *         3. 如何解决：当一个线程a在操作ticket的时候，其他线程不能参与进来，直到线程a操作完成ticket操作时
   *                    其他线程才可以进行操作。这种情况，即使线程a出现了阻塞，也不能被改变。
   *         4. 在Java中，我们通过同步机制，来解决线程的安全问题
   *         -4.1 方式一：同步代码块
   *              synchronized(同步监视器) {
   *                  // 需要被同步的代码
   *
   *              }
   *              说明：1. 操作共享数据的代码，即为需要被同步的代码。 --> 不能包代码过多或过少，必须值包含需要被同步的代码
   *                   2. 共享数据：多个线程共同操作的变量。比如：ticket
   *                   3. 同步监视器，俗称：锁。**任何一个类的对象**，都可以充当锁
   *                      要求：多个线程必须要共用同一把锁。
   *                   4. (补充)在通过实现接口来实现多线程的方式中，可以考虑通过使用this来充当同步监视器
   *                   5. (补充)在通过继承来实现多线程的方式中，要慎用this来充当监视器，可以考虑当前类充当同步监视器
   *         -4.2 方式而：同步方法
   *
   *          5. 优点：同步的方式，解决了线程的安全问题
   *             局限性：操作同步代码时，只能有一个线程参与，其他线程等待。相等于是一个单线程的过程，效率较低。
   */
  
  // 方式一: 通过实现Runnable接口来实现多线程，并使用同步代码块方式来解决线程安全问题
  class windows implements Runnable {
      // note: 此时可以不必声明为static的，因为在method2()中多个线程共有同一个windows2的实例化对象
      private int ticket = 10;
  
      // note: 使用同步代码块进行线程安全操作时，需要多个线程共用同一把锁，因此需要共用同一个变量
      Object obj = new Object();
  
      @Override
      public void run() {
          // Object obj = new Object(); // error, 所有的线程必须共用同一把锁
          // 4. (补充)在通过实现接口来实现多线程的方式中，可以考虑通过使用this来充当同步监视器
          // synchronized(obj) {
          synchronized(this) {    // right, 此时this表示windows w = new windows()对象本身
              while (true) {
                  try {
                      sleep(100);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
  
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
  }
  
  // 方式一_1 使用代码块的方式解决继承方式实现多线程的 线程同步问题
  class windows2 extends Thread {
      private static int ticket = 10;
  
      // 在继承实现多线程中，需要创建静态变量来解决同步问题
      private static Object obj = new Object();
  
      @Override
      public void run() {
          // 5. (补充)在通过继承来实现多线程的方式中，要慎用this来充当监视器，可以考虑当前类充当同步监视器
          // synchronized(obj) {
          // synchronized(this) { // error, 此时this表示不同的实例化对象w2_1, w2_2, w2_3
          synchronized(windows2.class) { // right, windows2.class为window2的唯一对象
              while (true) {
                  try {
                      sleep(100);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
  
                  if (ticket > 0) {
                      System.out.println(getName() + "售出票号为: " + ticket + "的票");
                      ticket--;
                  } else {
                      break;
                  }
              }
          }
      }
  }
  
  public class ThreadWindowsDemo {
      public static void main(String[] args) {
          ThreadWindowsDemo demo = new ThreadWindowsDemo();
          demo.method();
          System.out.println("--------------------------");
          // demo.method2();
      }
  
      public void method() {
          windows w = new windows();
          Thread t1 = new Thread(w, "window_1");
          Thread t2 = new Thread(w, "window_2");
          Thread t3 = new Thread(w, "window_3");
  
          t1.start();
          t2.start();
          t3.start();
      }
  
      public void method2() {
          windows2 w2_1 = new windows2();
          windows2 w2_2 = new windows2();
          windows2 w2_3 = new windows2();
  
          w2_1.setName("window2_1");
          w2_2.setName("window2_2");
          w2_3.setName("window2_3");
  
          w2_1.start();
          w2_2.start();
          w2_3.start();
      }
  }
  
  ```

  ```java
  package ThreadSynchronizationTest;
  
  import static java.lang.Thread.sleep;
  
  /**
   * @ClassName: ThreadWindowsDemo2
   * @Description: Java的同步方法：使用同步代方法
   * @author: zhilx(zhilx1997@sina.com)
   * @version: v1.0
   * @data: 2022/1/21 11:16
   * @node:
   *        4.2 方式二：同步方法
   *        如果操作共享数据的代码完成的声明在一个方法中，我们不妨将此方法声明为同步的。
   */
  
  // 方式二：使用同步方法将多线程声明为同步的
  class windows3 implements Runnable {
      private int ticket = 100;
  
      @Override
      public void run() {
          while (ticket > 0) {
              show();
          }
      }
  
      // note: 此时使用的是同步方法，若没有声明synchronized(obj)，则默认为使用了this对象
          private synchronized void show() {
          try {
              sleep(10);
          } catch (Exception e) {
              e.printStackTrace();
          }
  
          if (ticket > 0) {
              // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
              System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
              ticket--;
          }
      }
  }
  
  // 使用同步方法解决继承的多线程安全问题
  class windows4 extends Thread {
      private static int ticket = 100;
  
      @Override
      public void run() {
          while (ticket > 0) {
              show();
          }
      }
  
      // note: 此时使用的是同步方法，若没有声明synchronized(obj)，则默认为使用了this对象
          // private synchronized void show() { // 此时的同步监视器为w4_1, w4_2, w4_3，所以这个方式是错误的，需要将其声明为static的
          private static synchronized void show() { // 此时的同步监视器为window4.class
          try {
              sleep(10);
          } catch (Exception e) {
              e.printStackTrace();
          }
  
          if (ticket > 0) {
              // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
              System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
              ticket--;
          }
      }
  }
  
  public class ThreadWindowsDemo2 {
      public static void main(String[] args) {
          ThreadWindowsDemo2 demo = new ThreadWindowsDemo2();
          // demo.method3();
          System.out.println("--------------------------");
          demo.method4();
      }
  
      public void method3() {
          windows3 w = new windows3();
          Thread t1 = new Thread(w, "window3_1");
          Thread t2 = new Thread(w, "window3_2");
          Thread t3 = new Thread(w, "window3_3");
  
          t1.start();
          t2.start();
          t3.start();
      }
  
      public void method4() {
          windows4 w4_1 = new windows4();
          windows4 w4_2 = new windows4();
          windows4 w4_3 = new windows4();
  
          w4_1.setName("window4_1");
          w4_2.setName("window4_2");
          w4_3.setName("window4_3");
  
          w4_1.start();
          w4_2.start();
          w4_3.start();
      }
  }
  
  ```

  

### 6.2 线程安全的单例模式之懒汉式

- 通过同步方法/同步代码块 将单例模式设置为线程安全的
- 可以通过外加判断来提高 线程安全懒汉式单例模式的效率

```java
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

                return instance;
            }
        }
    }
}

```



## 7. 线程的死锁问题

- 死锁：
  - 不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
  - 出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
- 解决方法：
  - 专门的算法、原则
  - 尽量减少同步资源的定义
  - 尽量避免嵌套同步

```java
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

```



## 8. Lock(锁)

- 从JDK5.0 开始，Java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。

- `java.util.concurrent.locks.Lock` **接口是控制多个线程对共享资源进行访问的工具**。锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。

- `ReentrantLock`类实现了Lock，它拥有与`synchronized`相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是`ReentrantLock`，可以显式加锁、释放锁。

- ```
  4. Lock锁的使用步骤：
      4.1 首先通过 ReentrantLock类 来创建一个锁（需要声明为run()方法之外，因为所有的线程要共用同一个lock锁）
        ReentrantLock lock_t = new ReentrantLock(); // new ReentrantLock(true)表示为公平的，即按照FIFO的顺序进行资源的分配
      4.2 进行lock()方法，进行加锁
      4.3 进行相应代码的调用
  	4.4 进行unlick()解锁
  ```

  

### 8.1 面试题：synchronized 与 lock的异同

- 相同点：两者都可以解决线程安全问题
- 不同点：
  - `synchronized`机制在执行完相应的同步代码以后，自动的释放同步监视器
  - `Lock` 需要手动的启动同步（`lock()`），同时结束同步也需要手动释放（`unlock()`）

### 8.2 面试题：如何解决线程安全问题？有几种方式？

1. 可以通过lock、同步方法和同步代码块来解决问题。

2. 其中：

   1. Lock 是显式锁（需要手动开启和关闭锁，别忘记关闭锁），synchronized是隐式锁，出了作用域自动释放。
   2. Lock 只有代码块锁，synchronized 有代码块锁和方法锁
   3. 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提过更多的子类）。

3. 优先使用顺序：

   Lock -> 同步代码块（已经进入方法体，分配了相应资源）-> 同步方法（在方法体之外）

```java
package DeadThreadTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest.java
 * @Description: Java - 解决死锁的方法三：通过Lock锁机制
 * @author: zhilx(zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/22 9:47
 * @node:
 *      1. Lock锁介绍：
 *      - 从JDK5.0 开始，Java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。
 *      - `java.util.concurrent.locks.Lock` **接口是控制多个线程对共享资源进行访问的工具**。锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。
 *      - `ReentrantLock`类实现了Lock，它拥有与`synchronized`相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是`ReentrantLock`，可以显式加锁、释放锁。
 *
 *      2. synchronized 与 Lock 锁的区别
 *      2.1. Lock 是显式锁（需要手动开启和关闭锁，别忘记关闭锁），synchronized是隐式锁，出了作用域自动释放。
 *      2.2. Lock 只有代码块锁，synchronized 有代码块锁和方法锁
 *      2.3. 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提过更多的子类）。
 *
 *      3. 优先使用顺序：
 *      Lock -> 同步代码块（已经进入方法体，分配了相应资源）-> 同步方法（在方法体之外）
 *
 *      4. Lock锁的使用步骤：
 *      4.1 首先通过 ReentrantLock类 来创建一个锁（需要声明为run()方法之外，因为所有的线程要共用同一个lock锁）
 *          ReentrantLock lock_t = new ReentrantLock(); // new ReentrantLock(true)表示为公平的，即按照FIFO的顺序进行资源的分配
 *      4.2 进行lock()方法，进行加锁
 *      4.3 进行相应代码的调用
 *      4.4 进行unlick()解锁
 */
class window implements Runnable {
    private int ticket = 100;

    // 方法三：通过Lock锁来解决线程安全问题
    // 1. 首先通过 ReentrantLock类 来创建一个锁
    ReentrantLock lock_t = new ReentrantLock(); // new ReentrantLock(true)表示为公平的，即按照FIFO的顺序进行资源的分配

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 进行lock()方法，进行加锁
                lock_t.lock();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    // 因为实现的是接口，所有无法直接调用getName()方法，需要调用Thread.currentThread()来获取当前线程
                    System.out.println(Thread.currentThread().getName() + "售出票号为: " + ticket + "的票");
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 4. 调用unlock()方法，进行解锁
                // note: 将unlock()放到finally块中，可以避免因为异常而无法导致unlock()方法的调用
                lock_t.unlock();
            }

        }

    }
}
public class LockTest {
    public static void main(String[] args) {
        window w = new window();

        Thread t1 = new Thread(w, "Thread1");
        Thread t2 = new Thread(w, "Thread2");
        Thread t3 = new Thread(w, "Thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}

```



### 8.3 练习

```java
银行有一个账户：有两个储户分别向同一个账户存3000元，每次存1000，存3次，每次存完后打印余额。
分析：
	1. 是否为多线程问题？是，因为有两个储户线程
	2. 是否有共享数据？  是，账户（或账户余额）
	3. 是否有线程安全问题？ 是
	4. 需要考虑如何解决线程安全问题？同步机制：有三种方式（这里使用继承方式实现多线程，然后用同步方法和lock来解决线程安全问题）
```

```java
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

```



## 9. 线程的通信

### 9.1 线程通信用到的三个方法：

1. `wait()` : 一旦执行此方法，当前线程就会进入阻塞状态，并释放同步监视器

2. `notify()` : 一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个。

3. `notifyAll()` : 一旦执行此方法，就会唤醒所有被wait()的线程。

   

### 9.2 说明：

1. `wait()\notify()\norifyAll()`这三个方法必须使用在同步代码块或同步方法中。
2. `wait()\notify()\norifyAll()`这三个方法的调用者必须是同步代码块或同步方法中**同步监视器**，否则会出现`java.lang.IllegalMonitorStateException`异常。
3. `wait()\notify()\norifyAll()`这三个方法定义在`java.lang.Object`类中

### 9.3 面试题：sleep() 和 wait()的异同？

- 相同点：一旦执行这两个方法，都可以使得当前线程进入阻塞状态
- 不同点：
  	1. 两个方法声明的位置不同，Thread类中声明sleep()，Object类中声明wait()；
   	2. 调用的要求不同：sleep()可以在任何需要的场景下调用。**wait()必须在同步代码块或同步方法中使用**。
   	3. 关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep()方法不会释放同步监视器，wait()方法会释放同步监视器（锁）。

```java
package ThreadCommunicationTest;

import java.util.concurrent.locks.ReentrantLock;
/**
 * @ClassName: ThreadCommun1Test
 * @Description: Java - 线程间通信方式
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/1/22 11:32
 * @node: 线程通信的例子：两个线程交替打印1-100之间的数字
 * 		  释放同步监视器，wait()方法会释放同步监视器（锁）。
 */

// 一：使用同步代码块进行线程安全同步
class PrintNumber implements Runnable {
    private int number = 1;
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                // 2. 进行notifyAll()唤醒
                obj.notifyAll();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (number < 100) {
                    System.out.println(Thread.currentThread().getName() + " : " + number);
                    ++number;

                    // 1. 使用wait()进行阻塞
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}

// 二、使用lock锁进行线程安全同步(不可以使用lock锁中调用wait()/notify()，因为lock锁住线程之后，无法调用notify()解开)
class PrintNumber2 implements Runnable {
    private int number = 1;

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            if (number < 100) {

                try {
                    lock.lock();
                    // 2. 进行notifyAll()唤醒
                    notifyAll();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 加锁 : " + number);
                    ++number;

                    // 1. 使用wait()进行阻塞
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 解锁");
                    lock.unlock();
                }
            } else {
                break;
            }
        }
    }
}
public class ThreadCommun1Test {
    public static void main(String[] args) {
        ThreadCommun1Test tc = new ThreadCommun1Test();
        tc.method1();
//        tc.method2(); // error
    }

    public void method1() {
        PrintNumber pn = new PrintNumber();

        Thread t1 = new Thread(pn, "Thread_1");
        Thread t2 = new Thread(pn, "Thread_2");

        t1.start();
        t2.start();
    }

    // error
    public void method2() {
        PrintNumber2 pn2 = new PrintNumber2();

        Thread t1 = new Thread(pn2, "Thread_1");
        Thread t2 = new Thread(pn2, "Thread_2");

        t1.start();
        t2.start();
    }
}

```



### 9.4 线程通信的应用：经典例题：生产者/消费者问题

生产者（producer）将产品交给店员（clerk），而消费者（Customer）从店员处取走产品，店员一次只能持有固定数量的产品（比如，20），如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。



分析：

1. 是否为多线程问题？是，生产者线程，消费者线程
2. 是否有共享数据？是，产品（或店员）
3. 如何解决线程的安全问题？同步机制，有三种方法
4. 是否涉及线程的通信？是



## 10. JDK5.0 新增线程创建方式

- ### 新增方式一：实现`Callable`接口

  - 与使用`Runnable`相比，`Callable`功能更强大些：
    - 相比run()方法，可以有返回值；
    - 方法可以抛出异常
    - 支持泛型的返回值
    - 需要借助`Future Task`类，比如获取返回结果
  - `Futher`接口
    - 可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
    - **FutherTask是Futher接口的唯一的实现类**
    - FutherTask同时实现了Runnable，Futher接口。它既可以作为Runnable被线程执行，又可以作为Futher得到Callable的返回值。

- 步骤：
  1. 创建一个实现`Callable`的实现类
  2. 实现`call()`方法，将此线程需要执行的操作声明在`call()`中
  3. 创建`Callable`接口实现类的对象
  4. 将此`Callable`接口实现类的对象作为传递到`FutherTask`构造器中，并创建`FutherTask`的对象的
  5. 将`FutherTask`的对象作为单数传递到`Thread`类的构造器中，创建`Thread`对象。
  6. 可以通过`get()`方法获取`Callable`中的`call()`方法的返回值
- 如何理解实现`Callable`接口的方式创建多线程比实现`Runnable`接口创建多线程方式强大？
  - `call()`方法可以有返回值
  - `call()`方法可以抛出异常，从而被外面操作，获取异常的信息
  - `Callable`是支持泛型的

```java
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

```



- ### 新增方式二：使用线程池

  - 背景：经常创建或销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
  - 思路：提前创建好多个线程，放入线程池中，使用时直接获取，使用完返回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。
  - 好处：
    - 提高响应速度（减少了创建新线程的时间）
    - 降低资源销毁（重复利用线程池中的线程，不需要每次都创建）
    - 便于线程管理：
      - `corePoolSize` : 核心池的大小
      - `maximumPoolSize` : 最大线程数
      - `keepAliveTime` : 线程没有任务时最多保持多长时间后会终止。
      - `...`
  - 线程池相关API
    - JDK 5.0 起提供了线程池相关API : `ExecutorService` 和 `Executors`
    - `ExecutorService` : 真正的线程池接口。常见子类ThreadPoolExecutor
      - `void execute(Runnable command)` : 执行任务/命令，无返回值，一般用来执行`Runnable`
      - `<T>Future<T> submit(Callable<T> task)`: 执行任务，，有返回值，一般用来执行Callable
      - `void shutdown()` : 关闭连接池
    - `Executors` : 工具类、线程池的工厂类，用于创建并返回不同类型的线程池
      - `Executors.newCachedThreadPool()` : 创建一个可根据需要创建新线程的线程池
      - `Executors.newFixedThreadPool(n)` : 创建一个可重用固定线程数的线程池
      - `Executors.newSingleThreadExecutor()` : 创建一个只有一个线程的线程池
      - `Executors.newScheduledThreadPool(n)` : 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。

  
