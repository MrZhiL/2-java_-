## 10. System类

- System类代表系统，系统级的很多属性和控制方法都放置在该类的内部。该类位于`java.lang`包
- 由于该类的构造器是private的，所以无法创建该类的对象，也就是无法实例化该类。其内部的成员变量和成员方法都是static的，所以也可以很方便的进行调用。
- 成员变量：
  - System类内部包含in、out和err三个成员变量，分别代表标准输入流（键盘输入），标准输出流（显示器）和标准错误输出流（显示器）
- 成员方法：
  - `native long currentTimeMillis()`
     该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间（格林威治时间）1970年1月1日0时0分0秒所差的毫秒数。
  - `void exit(int status)`
     该方法的作用是退出程序。其中status的值为0代表正常退出，非零代表异常退出。**使用该方法可以在图形界面编程中实现程序的退出功能**等。
  - `void gc()`
     该方法的作用是请求系统进行垃圾回收。至于系统是否立刻回收，则取决于系统中垃圾回收算法的实现以及系统执行时的情况。
  - `String getProperty(String key)`
     该方法的作用是获得系统中属性名为key的属性对应的值。系统中常见的属性名以及作用如下表所示：

    | 属性名       | 属性说明           |
    | ------------ | ------------------ |
    | java.version | java运行时环境版本 |
    | java.home    | java安装目录       |
    | os.name      | 操作系统的名称     |
    | os.version   | 操作系统的版本     |
    | user.name    | 用户的账号名称     |
    | user.home    | 用户的主目录       |
    | user.dir     | 用户的当前工作目录 |


## 11. Math类
`java.lang.Math`提供了一系列静态方法用于科学计算。其方法的参数和返回值类型一般为double型
- abs  绝对值
- acos,asin,atan,cos,sin,tan 三角函数
- sqrt、pow(double a, double b)、log、exp（e为底质数）
- max(double a, double b)、min(double a, double b)
- random() : 返回0.0到1.0的随机数
- long round(double a) double型数据a转换为long型（四舍五入）
- toDegrees(double angrad) : 弧度 -> 角度
- toRadians（double angdeg) : 角度 -> 弧度



## 12. BigInteger与BigDecinmal

### 1. BigInteger类：

- Integer类作为int的包装类，能存储的最大整形值为2^31 - 1，Long类也是有限的，最大为2^63-1.如果要表示再大的数，不管是基本数据类型还是他们的包装类都无能为力，更不用说进行运算了。
- **java.math包的BigInteger可以表示不可变的任意静定的整数**。BigInteger提供所有Java的基本整数操作符的对应物，并提供`java.lang.Math`的所有相关方法。另外，BigInteger还提供以下运算：模算术、GCD计算、质数测试、素数生产、位操作以及一些其他操作。
- 构造器：
  - BigInteger(String val): 根据字符串构建BigInteger对象
- 常用方法
  - public BigInteger abs(): 返回此BigInteger的绝对值的BigInteger
  - public BigInteger add(BigInteger val): 返回其值为this+val的BigInteger
  - public BigInteger subtract(BigInteger val)): 返回其值为this-val的BigInteger
  - public BigInteger multiply(BigInteger val)): 返回其值为this*val的BigInteger
  - public BigInteger divide(BigInteger val)): 返回其值为this/val的BigInteger。整数相除只保留整数部分
  - public BigInteger remainder(BigInteger val)): 返回其值为this%val的BigInteger
  - public BigInteger[] divideAndRemainder(BigInteger val)): 返回包含（this/val）后跟（this%val）的两个BigInteger的数组
  - public BigInteger pow(int val): 返回其值为this^val的BigInteger

### 2. BigDecimal类

- 一般Float和Double类可以用来做科学计算或工厂计算，但在商业计算中，要求数字精度比较高，故用到java.math.BigDecimal类。
- BigDecimal类支持不可变的、任意精度的有符号十进制定点数
- 构造器：public BigDecimal(double val); public BigDecimal(String val);
- 常用方法：
  - public BigDecimal add(BigDecimal augend)
  - public BigDecimal subtract(BigDecimal augend)
  - public BigDecimal mulitply(BigDecimal augend)
  - public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)