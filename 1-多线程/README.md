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

  
