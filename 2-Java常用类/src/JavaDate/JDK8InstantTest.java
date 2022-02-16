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
