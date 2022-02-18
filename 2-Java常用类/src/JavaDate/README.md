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

```java
package JavaDate;

import org.junit.Test;

import java.time.*;

/**
 * @ClassName: JavaDate
 * @Description: Java - JDK8.0的新日期时间API：java.time
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/16 11:18
 * @node:   新时间日期API
 *          - 第三次引入的API是成功的，并且Java8中引入的java.time API已经纠正了过去的缺陷，将来很长一段时间内它都会为我们服务。
 *          - Java 8吸收了Joda-Time的精华，以一个新的开始为Java创建优秀的API。**新的java.time中包含了所有关于本地日期（LocalDate）、本地时间（LocalTime）、本地日期时间（LocalDateTime）、时区（ZonedDateTime）和持续时间（Duration）的类**。历史悠久的**Date类新增了toInstant()方法**，用于把Date转换成新的表示形式。这些新增的本地化时间日期API大大简化了日期时间和本地化的管理。
 *
 *          API：
 *
 *          1. java.time - 包含值对象的基础包
 *          2. java.time.chrono - 提供不同的日历系统的访问
 *          3. java.time.format - 格式化和解析时间和日期
 *          4. jata.time.temporal - 包括底层框架和扩展特性
 *          5. java.time.zone - 包含时区支持的类
 */
public class JDK8TimeClassTest {
    @Test
    public void test01() {
        // LocalTime\LocalDate\LocalDateTime的测试
        // 1. 实例化对象
        // 1.1 通过now()方法
        System.out.println("******* now() 方法实例化对象 **********");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // 1.2 通过of()方法实例化对象，此时需要指定相应的日期和时间
        System.out.println("******* of() 方法实例化对象 **********");
        LocalDate localDate1 = LocalDate.of(2021, 12, 01);
        LocalTime localTime1 = LocalTime.of(15, 31, 20);
        LocalDateTime localDateTime1 = LocalDateTime.of(2021,12,02, 20,01,43);
        System.out.println(localDate1);
        System.out.println(localTime1);
        System.out.println(localDateTime1);

        // 2. getxxx()方法获取time的对象
        System.out.println("******* getxxx() 方法 **********");
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());

        // 3. withxxx()方法进行设置，可证明time的不可变性
        System.out.println("******* withxxx() 方法 **********");
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(12);
        System.out.println("now time   : " + localDateTime);
        System.out.println("Modify time: " + localDateTime2);

        // 4. plusxxx()，可证明time的不可变性
        System.out.println("******* plusxxx() 方法 **********");
        LocalDateTime localDateTime3 = localDateTime.plusYears(1).plusMonths(1).plusDays(1); // 加一年，一月，一天
        System.out.println("now time   : " + localDateTime);
        System.out.println("Modify time: " + localDateTime3);

        // 5. minusxxx()，可证明time的不可变性
        System.out.println("******* withxxx() 方法 **********");
        LocalDateTime localDateTime4 = localDateTime.minusYears(1).minusMonths(1).minusDays(1); // 减一年，一月，一天
        System.out.println("now time   : " + localDateTime);
        System.out.println("Modify time: " + localDateTime4);
    }
}

```



#### 2.3 瞬时：Instant

- Instant：时间线上的一个瞬时点。这可能被用来计算应用程序中的时间时间戳。
- 在处理时间和日期的时候，我们通常会想到年，月，日，时，分，秒。然而，这只是时间的一个模型，是面向人类的。第二种通用模型是面向机器的，或者说是连续的。在此模型中，时间线中的一个点表示为一个很大的数，这有利于计算机处理。在UNIX中，这个数从1970年开始，以秒为单位；同样的，在Java中，也是从1970年开始，但是以毫秒为单位。
- java.time包括值类型Instant提供机器视图，不提供处理人类意义上的时间单位。Instant表示时间线上的一点，而不需要任何上下文信息，例如，时区。概念上讲，它只是简单的表示自1970你1月1日0时0分0秒（UTC）开始的秒数。因为java.time包是基于纳秒计算的，所以Instant的精度可以达到纳秒级。
- 1ns = 10^(-9) s, 1s = 1000 ms = 1000 000 us = 1000 000 000 ns



| 方法                          | 秒数                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| now()                         | 静态方法，返回默认UTC时区的Instant类的对象                   |
| ofEpochMilli(long epochMilli) | 静态方法，返回在1970-01-01 00:00:00基础上加上指定毫秒数之后的Instant类的对象 |
| atOffset(ZoneOffset offset)   | 结合即时的偏移来创建一个OffsetDateTime                       |
| toEpochMilli()                | 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳        |

时间戳是指格林威治时间1970年1月1日00时00分00秒（北京时间1970年01月01日08时00分00秒）起至现在的总秒数。

```java
package JavaDate;

import org.junit.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @ClassName: JavaDate
 * @Description: Java - Instant瞬时测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/16 14:30
 * @node: Instant的使用 ： 类似于java.util.Date类
 *
 */
public class JDK8InstantTest {
    @Test
    public void test01() {
        // 1. Instant的初始化 : 获取本初子午线的时间（格林威治时间，比北京时间小8个小时）
        Instant instant = Instant.now();
        System.out.println(instant);

        // 2. atOffset(ZoneOffset offset) : 结合即时的偏移来创建一个OffsetDateTime
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));// 设置为北京时间
        System.out.println(offsetDateTime);

        // 3. toEpochMilli() : 返回1970-01-01 00:00:00到当前时间的毫秒数，即为时间戳
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        // 4. Instant的另一种实例化方式：通过指定毫秒数
        Instant instant1 = Instant.ofEpochMilli(1644993408397L); // 1644993408397L : 2022-02-16T06:36:48.397Z
        System.out.println(instant1);
    }
}

```



#### 2.4 格式化与解析日期或时间（java.time.format.Date TimeFormatter）

`java.time.format.Date TimeFormatter`类：该类提供了三种格式化方法：

- 预定义的标准格式。如：ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE; ISO_LOCAL_TIME
- 本地化相关的格式。如：`ofLocalizedDateTime(FormatStyle.LONG)`
- 自定义的格式。如：`ofPattern("yyyy-MM-dd hh:mm:ss E")`、
- 用来格式化和解析时间，类似于SimpleDateFormat

| 方法                       | 描述                                                |
| -------------------------- | --------------------------------------------------- |
| ofPattern(String pattern)  | 静态方法，返回一个指定字符串格式的DateTimeFormatter |
| format(TemporalAccessor t) | 格式化一个日期、时间，返回字符串                    |
| parse(CharSequence text)   | 将指定格式的字符序列解析为一个日期、时间            |

```java
package JavaDate;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

import static java.time.format.FormatStyle.FULL;

/**
 * @ClassName: JavaDate
 * @Description: Java - java.time.DateTimeFormat类
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/18 11:02
 * @node: `java.time.format.Date TimeFormatter`类：该类提供了三种格式化方法：
 *      - 预定义的标准格式。如：ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE; ISO_LOCAL_TIME
 *      - 本地化相关的格式。如：`ofLocalizedDateTime(FormatStyle.LONG)`
 *      - 自定义的格式。如：`ofPattern("yyyy-MM-dd hh:mm:ss E")`、
 *      - 用来格式化和解析时间，类似于SimpleDateFormat
 */
public class JDK8DataTimeFormat {
    @Test
    public void test() {
        // 1. 预定义的标准格式。如：ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE; ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        // 2. 格式化操作： 日期->字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);

        System.out.println("************格式化***************");
        System.out.println(localDateTime);
        System.out.println(str1);

        // 2.2 解析：字符串->日期
        TemporalAccessor parse = formatter.parse(str1);
        System.out.println("************解析***************");
        System.out.println(parse);

        // 3.1 本地化相关的格式一。如：`ofLocalizedDateTime(xxx)`: 适用于LocalDateTime
        // FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT
        System.out.println("***********本地格式化：ofLocalDateTime()**************");
        // 这个函数时jdk8.0中的，因此需要指定ZoneID，否则会报错
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        // 将日期和时间转化为字符串：LONG类型的
        String str2 = formatter1.format(localDateTime);// 将日期和时间转化为字符串
        String str3 = formatter2.format(localDateTime);// 将日期和时间转化为字符串
        String str4 = formatter3.format(localDateTime);// 将日期和时间转化为字符串
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);

        // 3.2 本地化相关格式二： ofLocalizedDate()
        // FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT
        System.out.println("***********本地格式化：ofLocalDate()**************");
        // 这个函数时jdk8.0中的，因此需要指定ZoneID，否则会报错
        DateTimeFormatter formatter10 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter11 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter12 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault());
        DateTimeFormatter formatter13 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        // 将日期和时间转化为字符串：LONG类型的
        String str11 = formatter10.format(LocalDate.now());// 将日期和时间转化为字符串
        String str12 = formatter11.format(LocalDate.now());// 将日期和时间转化为字符串
        String str13 = formatter12.format(LocalDate.now());// 将日期和时间转化为字符串
        String str14 = formatter13.format(LocalDate.now());// 将日期和时间转化为字符串
        System.out.println(str11);
        System.out.println(str12);
        System.out.println(str13);
        System.out.println(str14);

        // 4. 重点：方式三：自定义的格式，如ofPattern("yyyy-MM-dd hh:mm:ss")
        System.out.println("***********ofPattern()***************");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str5 = formatter4.format(LocalDateTime.now());
        System.out.println(str5);

    }
}


***************输出************
************格式化***************
2022-02-18T11:44:36.691812500
2022-02-18T11:44:36.6918125
************解析***************
{},ISO resolved to 2022-02-18T11:44:36.691812500
***********本地格式化：ofLocalDateTime()**************
2022年2月18日 CST 上午11:44:36
2022年2月18日 上午11:44:36
2022/2/18 上午11:44
***********本地格式化：ofLocalDate()**************
2022年2月18日星期五
2022年2月18日
2022年2月18日
2022/2/18
***********ofPattern()***************
2022-02-18 11:44:36

```



#### 2.5 其它API

- Zoneld: 该类中包含了所有的时区信息，一个时区的ID，如Europe/Paris。
- ZonedDateTime: 一个在ISO-8601日历系统时区的日期时间，如2007-12-03T10:15:30+01:00 Europe/Paris
  - 其中每个时区都对应着ID，地区ID都为“{区域}/{城市}”的格式，例如: Asia/Shanghai等。
- Clock：使用时区提供对当前即时、日期和时间的访问的时钟。
- 持续时间：Duration，用于计算两个“时间”间隔
- 日期间隔：Period，用于计算两个“日期”间隔
- TemporalAdjuster: 时间校正器。有时我们可能需要获取例如：将日期调整到“下一个工作日”等操作。
- TemporalAdjusters:  该类通过静态方法（firstDayOfXxx() / lastDayOfXxx() / nextXxx()提供了大量的常用TemporalAdjuster的实现）

```java
public void test2() {
        // ZoneId: 类中包含了所有的时区信息
        // ZoneId的getAvailableZoneIds() : 获取所有的ZoneId
        System.out.println("---------获取所有时区信息---------------");
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String s : zoneIds) {
            System.out.println(s);
        }

        // ZoneId的of()；获取指定时区的时间
        System.out.println("----------获取指定时区信息--------------");
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(localDateTime);

        // ZonedDateTime: 带时区的日期时间
        // ZonedDateTime的now()：获取本时区的ZonedDateTime对象
        System.out.println("------------获取带时区的日期时间: ZonedDateTime.now()-----------");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(); // 2022-02-18T12:12:06.829560800+08:00[Asia/Shanghai]
        System.out.println(zonedDateTime);

        // ZonedDateTime的now(ZoneId id)：
        System.out.println("-----------zonedDateTime.now(ZoneId.of(\"Asia/Tokyo\"));--------------");
        ZonedDateTime zonedDateTime1 = zonedDateTime.now(ZoneId.of("Asia/Tokyo")); // 2022-02-18T13:12:06.829560800+09:00[Asia/Tokyo]
        System.out.println(zonedDateTime1);
    }
```



#### 5. 参考：与传统日期处理的转换

| 类                                                        | To遗留类                              | From遗留类                  |
| --------------------------------------------------------- | ------------------------------------- | --------------------------- |
| java.time.Instant 与 java.util.Date                       | Date.form(instant)                    | date.toInstant()            |
| java.time.Instant 与 java.sql.Timestamp                   | Timestamp.from(instant)               | timestamp.toInstant()       |
| java.time.ZonedDateTime / java.util.GregorianCalendar     | GregorianCalendar.from(zonedDateTime) | cal.toZonedDateTime()       |
| java.time.LocalDate / java.sql.Time                       | Date.valudOf(localDate)               | date.toLocalDate()          |
| java.time.LocalTime / java.sql.Time                       | Date.valueOf(localDate)               | date.toLocalTime()          |
| java.time.LocalDate / java.sql.Timestamp                  | Timestamp.valueOf(localDate Time)     | timestamp.toLocalDateTime() |
| java.time.ZoneId / java.util.TimeZone                     | timeZone.getTimeZone(id)              | timeZone toZoned()          |
| java.time.format.DateTimeFormatter / java.text.DateFormat | formatter.toFormat()                  | 无                          |



## 9. Java比较器

