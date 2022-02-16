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
