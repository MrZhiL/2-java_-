## Java中的日期时间API

### 1. JDK8之前日期时间API

#### 1.1  java.lang.System类：

System类提供的`public static long currentTimeMilli()`用来返回的当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。

**此方法适用于计算时间差**

- 计算时间时间的主要标准有：
    - UTC(Coordinated Universal Time)
    - GMT(Gerrnwich Mean Time)
    - CST(Central Standard Time)

#### 1.2 java.util.Date类：

表示特定的瞬间，精确到毫秒

- 构造器：
    - Date(): 使用无参构造器创建的对象可以获取本地当前时间
    - Date(long date)
- 常用方法：
    - getTime(): 返回自1970年1月1日0时0分0秒 GMT以来此Date对象表示的毫秒数
    - toString(): 把此Date对象转换为以下形式的String: dow mon dd hh:mm:ss zzz yyy 其中，dow是一周中的某一天（Sun,Mon,Tue,Wed,Thu,Fri,Sat)，zzz是时间标准。
    - 其他很多方法都过时了

1. java.sql.Date类：

   ```
   |----java.sql.Date类：表示数据库下的时间
   *          1. 如何实例化 java.sql.Date date = new java.sql.Date();
   *          2. 如何将java.sql.Date对象与java.util.Date对象进行转换
   ```

   ```
   // 方式一：直接强制转换，不推荐
   java.sql.Date date5 = (java.sql.Date) date4;
   System.out.println(date4.toString());
   System.out.println(date5.toString());
   
   // 方式二：
   Date date6 = new Date();
   java.sql.Date date7 = new java.sql.Date(date6.getTime());
   System.out.println(date6);
   System.out.println(date6.toString());
   ```



#### 1.3 java.text.SimpleDateFormat类

- Date类的API不易于国际化，大部分被废弃了，`java.text.SimpleDateFormat`类是一个不与语言环境有关的方式来格式化和解析日期的具体类。
- 它允许进行格式化：日期 -> 文本、解析：文本 -> 日期
- 格式化：
    - `SimpleDateFormat()`:默认的模式和语言环境创建对象
    - `public SimpleDateFormat(String pattern)` : 该构造方法可以用参数pattern指定的格式创建一个对象，该对象调用：
    - `public String format(Date date)` ： 方法格式化时间对象date
- 解析：
    - `public Date parse(String source)` : 从给定字符串的开始解析文本，以生产一个日期。