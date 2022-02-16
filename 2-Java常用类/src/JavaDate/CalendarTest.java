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
