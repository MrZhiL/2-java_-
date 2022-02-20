/**
 * @ClassName: enumTest
 * @Description: Java - 枚举类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/20 11:34
 * @node:
 *        ### 枚举类的性质
 *          - 当类的对象只有有限个，确定的。距离如下：
 *          - 星期：Monday、... 、Sunday
 *          - 性别：Man/Woman
 *          - 季节：Sprint 、... 、Winter
 *          - 支付方式：Cash(现金)、WeChatPay、Alipay、BankCard、CreditCard(信用卡)、
 *          - 就职状态：Busy、Free、Vocation、Dimission
 *          - 线程状态：创建、就绪、运行、阻塞、死亡
 *          - ....
 *          - 当需要定义一组常量时，强烈建议使用枚举类
 *
 *       ### 枚举类的定义
 *          - 枚举类的实现：
 *            - JDK 1.5此前需要自定义枚举类
 *            - JDK 1.5新增enum关键字用于定义枚举类
 *          - 若枚举类只有一个对象，则可以作为一种单例模式的实现方式
 *          - **枚举类的属性**：
 *            - 枚举类对象的属性不应允许被改动，所以应该使用 private final 修饰
 *            - 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
 *            - 若枚举类显式的定义类带参数的构造器，则在列出枚举值时也必须对应的传入参数。
 */
public class enumTest {
    public static void main(String[] args) {
        // 1. 自定义枚举类的测试
        Season season1 = Season.SPRING;
        Season season2 = Season.WINTER;
        System.out.println(season1);
        System.out.println(season2);

    }
}

// 一、自定义枚举类
class Season {
    // 1. 将属性标记为 private final 的
    private final String seasonName;
    private final String seasonDesc;

    // 2. 将构造器声明为private的
    private Season(String seasonDesc, String seasonName) {
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    // 3. 创建枚举类的多个对象，并且需要声明为public static final 常量形式的，因为该对象只能存在一份
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "梅花盛开");

    // 4.1 其他诉求：获取枚举类的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 4.2 其他诉求2：提供toString()方法
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
