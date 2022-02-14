package JavaDate;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: JavaDate
 * @Description: Java - JDK8之前的日期时间的API测试 : SimpleDateFormat
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/14 11:26
 * @node:
 *          1. System类中的currentTimeMillis();
 *          2. java.util.Date和子类java.sql.Date; (Date不利用国际化的操作)
 *          3. SimpleDateFormat
 *          4. Calendar
 */
public class DateTest2 {
    public static void main(String[] args) {
        DateTest2 test = new DateTest2();

        try {
            test.simpleDateFormatTest();
            System.out.println("******************");
            test.simpleDateFormateTest2();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

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
}
