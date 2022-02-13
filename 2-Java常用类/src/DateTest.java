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
 *          |----java.sql.Date类
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
    }
}
