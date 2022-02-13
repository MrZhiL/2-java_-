import org.junit.Test;

import java.util.Date;

/**
 * @ClassName: DateTest
 * @Description: Java - Date时间测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/13 16:31
 * @node: java.util.Date类：表示特定的瞬间，精确到毫秒
 *          - Date(): 使用无参构造器创建的对象可以获取本地当前时间
 *          - Date(long date)
 *          toString(): 显示当前的年、月、日、时、分、秒
 *          getTime(): 获取当前Date对象应用的毫秒数（时间戳）
 *
 *          |----java.sql.Date类：表示数据库下的时间
 *          1. 如何实例化 java.sql.Date date = new java.sql.Date();
 *          2. 如何将java.sql.Date对象与java.util.Date对象进行转换
 */
public class DateTest {
    @Test
    public void test() {
        // 构造器一：创建一个对应当前时间的Date对象
        Date date1 = new Date();
        System.out.println(date1.toString()); // Sun Feb 13 16:34:02 CST 2022
        System.out.println(date1.getTime());  // 1644741242446

        // 构造器二：创建指定毫秒数的Date对应时间
        Date date2 = new Date(1644741242446L);
        System.out.println(date2.toString());

        System.out.println("****************************");

        // 3. 实例化java.sql.Date对象, 没有空参构造器
        java.sql.Date date3 = new java.sql.Date(1644741242446L);
        System.out.println(date3.toString());

        // 3.1 如何将java.sql.Date对象与java.util.Date对象进行转换
        Date date4 = new java.sql.Date(1644741242446L);

        // 方式一：直接强制转换，不推荐
        java.sql.Date date5 = (java.sql.Date) date4;
        System.out.println(date4.toString());
        System.out.println(date5.toString());

        // 方式二：
        Date date6 = new Date();
        java.sql.Date date7 = new java.sql.Date(date6.getTime());
        System.out.println(date6);
        System.out.println(date6.toString());
    }
}
