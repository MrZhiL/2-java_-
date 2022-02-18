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
