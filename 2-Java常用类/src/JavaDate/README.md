## 8. Java中的日期时间API

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

3. java.sql.Date类：

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

```java
public void simpleDateFormatTest() throws ParseException {
        // 1. SimpleDateFormat初始化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 2. 格式化： 日期->字符串
        Date date = new Date();
        System.out.println(date);

        String format = simpleDateFormat.format(date);
        System.out.println(format);

        // 3. 解析：格式化的逆过程，字符串 -> 日期
        String str = "2022/2/14 下午5:16";
        Date date2 = simpleDateFormat.parse(str); // parse函数需要抛出异常
        System.out.println(date2);
    }

    public void simpleDateFormateTest2() throws ParseException {
        // 1. 利用指定的方式进行格式化和解析
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 2020-10-09 10:02:29 格式的
        Date date = new Date();

        // 2. 格式化：日期->字符串
        String str = sdf.format(date);
        System.out.println(str);

        // 3. 解析：格式化的逆过程: 字符串->日期
        // note: 解析要求字符串必须是符合SimpleDateFormat识别的格式（通过构造器参数体现），否则会抛出异常
        String str2 = "2020-10-09 10:02:29";
        Date date2 = sdf.parse(str2);
        System.out.println(date2);
    }
```



#### 1.4 java.util.Calendar类

- Calendar是一个抽象基类，主要用于完成日期字段之间相互操作的功能。

- 获取Calendar实例的方法：

  - 使用Calendar.getInstance()方法
  - 调用它的子类GregorianCalendar的构造器

- 一个Calendar的实例是系统时间的抽象表示，通过get(int field)方法来取得想要的时间信息。比如YEAR、MONTH、DAY_OF_MONTH、DAY_OF_WEEK、HOUR_OF_DAY、MINUTE、SECOND

  - public int get(int field);
  - public void set(int field, int value);
  - public final Date getTime()
  - public final void setTime(Date date)

- 注意：

  - 获取月份时：一月是0，二月是1，以此类推，12月是11
  - 获取星期时：周日是1，周二是2，。。。周六是7

  ```java
  package JavaDate;
  
  import org.junit.Test;
  
  import java.util.Calendar;
  import java.util.Date;
  import java.util.GregorianCalendar;
  
  /**
   * @ClassName: JavaDate
   * @Description: Java - JDK8.0之前的Calendar日历类测试（Calendar为抽象基类）
   * @author: zhilx (zhilx1997@sina.com)
   * @version: v1.0
   * @data: 2022/2/16 9:44
   * @node:
   *          . 1. System类中的currentTimeMillis();
   *            2. java.util.Date和子类java.sql.Date; (Date不利用国际化的操作)
   *            3. SimpleDateFormat
   *            4. Calendar
   */
  public class CalendarTest {
      @Test
      /* Calendar是一个**抽象基类**，主要用于完成日期字段之间互相操作的功能。 */
      public void test() {
          // 1. 获取Calendar实例的方法：
          // 1.1 使用Calendar.getInstance()方法
          Calendar cal1 = Calendar.getInstance();
  
          // 1.2 调用它的子类GregorianCalendar的构造器
          Calendar cal2 = new GregorianCalendar();
  
          // 2. 常用方法
          // 2.1 get() : 获取Calendar的指定时间
          System.out.println("***********get() function*************");
          int day = cal1.get(Calendar.DAY_OF_MONTH); // Calendar.DAY_OF_MONTH: 获取cal1为当月的第几天
          System.out.println(day);
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
  
          // 2.2 set(int field, int value):
          System.out.println("***********set() function*************");
          cal1.set(Calendar.DAY_OF_MONTH, 1);
          System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
  
          // 2.3 add()
          System.out.println("***********add() function*************");
          cal1.add(Calendar.DAY_OF_MONTH, 5); //  根据日历的规则，将指定的时间量添加或减去给定的日历字段
          cal1.add(Calendar.DAY_OF_MONTH, -1); //  根据日历的规则，将指定的时间量添加或减去给定的日历字段
          System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
  
          // 2.4 getTime(): Calendar ---> Date类
          System.out.println("***********getTime() function*************");
          Date date1 = cal1.getTime(); // 返回一个Date类型的返回值
          System.out.println(date1);
  
          // 2.5 setTime(): Date类 ---> Calendar
          System.out.println("***********getTime() function*************");
          Date date2 = new Date();
          cal1.setTime(date2);
          System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
          System.out.println(cal1.get(Calendar.DAY_OF_WEEK));  // Calendar.DAY_OF_WEEK: 获取当前为这周的第几天
          System.out.println(cal1.get(Calendar.DAY_OF_YEAR));  // Calendar.DAY_OF_YEAR: 获取当前为今年的第几天
      }
  }
  
  ```



### 2. JDK8中的新日期时间API

#### 2.1 新时间日期API出现的背景

我们希望时间与昼夜和四季有关，使得事情较为复杂。JDK 1.0中包含了一个java.util.Date类，但是它的大多数方法已经在JDK 1.1引入Calendar类之后被弃用了。而Calendar并不比Date好多少。他们面临的问题是：

- 可变性：像日期和时间这样的类应该是不可变的。
- 偏移性：Date中的年份是从1900开始的，而月份都是从0开始
- 格式化：格式化只对Date有用，Calendar则不行
- 此外，它们也不是线程安全的；不能处理闰秒等。

总结：对日期和时间的操作一直是Java程序员最痛苦的地方之一。

#### 2.2 新时间日期API

- 第三次引入的API是成功的，并且Java8中引入的java.time API已经纠正了过去的缺陷，将来很长一段时间内它都会为我们服务。
- Java 8吸收了Joda-Time的精华，以一个新的开始为Java创建优秀的API。**新的java.time中包含了所有关于本地日期（LocalDate）、本地时间（LocalTime）、本地日期时间（LocalDateTime）、时区（ZonedDateTime）和持续时间（Duration）的类**。历史悠久的**Date类新增了toInstant()方法**，用于把Date转换成新的表示形式。这些新增的本地化时间日期API大大简化了日期时间和本地化的管理。

API：

1. java.time - 包含值对象的基础包
2. java.time.chrono - 提供不同的日历系统的访问
3. java.time.format - 格式化和解析时间和日期
4. jata.time.temporal - 包括底层框架和扩展特性
5. java.time.zone - 包含时区支持的类

说明：大多数开发者只能用到基础包和format包，也可能会用到temporal包。因此，尽管有68个新的公开特性，大多数开发者，大概只会用到其中的三分之一。



**常用方法：**

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| now() / * noew (Zoneld zone)                                 | 静态方法，根据当前时间创建对象/指定时区的对象                |
| of()                                                         | 静态方法，根据指定日期/时间创建对象；设置指定的日期和时间，没有偏移量 |
| getDaOfMonth() / getDayOfYear()                              | 获取月份天数（1-31）/ 获取年份天数（1-366）                  |
| getDayOfWeek()                                               | 或得星期几（返回一个DayofWeek枚举值）                        |
| getMonth()                                                   | 获得月份，返回一个Month枚举值                                |
| getMonthValue() / getYear()                                  | 获得月份(1-12)/获得年份                                      |
| getHour() / getMinute() / getSecond()                        | 获得当前对象对应的小时、分钟、秒                             |
| withDayOfMonth() / withDayOfYear() / withMonth() / withYear() | 将月份天数、年份天数、月份、年份修改为指定的值并返回新的对象 |
| plusDays(), plusWeeks(), plusMonths(), plusYears(), plusHours() | 向当前对象添加几天、几周、几个月、几年、几小时               |
| minusDays(),  minusWeeks(), minusMonths(), minusYears(), minusHours() | 从当前对象减去几月、几周、几天、几年、几小时                 |

note: LocalTime、LocalDate、LocalDateTime具有不可变性，且LocalDateTime的使用频率较高

