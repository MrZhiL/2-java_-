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
