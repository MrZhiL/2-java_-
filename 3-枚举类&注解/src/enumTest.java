/**
 * @ClassName: enumTest
 * @Description: Java - 枚举类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/20 11:34
 * @node:
 *        1. 枚举类的性质
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
 *       2. 枚举类的定义
 *          - 枚举类的实现：
 *            - JDK 1.5此前需要自定义枚举类
 *            - JDK 1.5新增enum关键字用于定义枚举类 (enum类继承与java.lang.Enum类)
 *          - 若枚举类只有一个对象，则可以作为一种单例模式的实现方式
 *          - **枚举类的属性**：
 *            - 枚举类对象的属性不应允许被改动，所以应该使用 private final 修饰
 *            - 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
 *            - 若枚举类显式的定义类带参数的构造器，则在列出枚举值时也必须对应的传入参数。
 *
 *        3. Enum类的常用方法
 *          | 方法名              | 详细描述                                                     |
 *          | ------------------- | ------------------------------------------------------------ |
 *          | valueOf(String str) | 传递枚举类型的Class对象和枚举常量名称给静态方法valueOf，会得到与参数匹配的枚举常量，**如果找不到则抛出异常**：
 *                                  IllegalArgumentException。 |
 *          | toString()          | 得到当前枚举常量的名称。可以通过重写这个方法来使得结果更加容易读取 |
 *          | values()            | 返回枚举类型的对象数组。该方法可以很方便的遍历所有的枚举值。 |
*
 *      4. 使用enum关键字定义的枚举类实现接口
 *          - 情况一：实现接口，在enum类中实现抽象方法，此时所有枚举类的返回值相同
 *          - 情况二：让枚举类的对象分别实现接口中的抽象方法
 */
public class enumTest {
    public static void main(String[] args) {
        // 1. 自定义枚举类的测试
        System.out.println("*********自定义枚举类测试*************");
        Season season1 = Season.SPRING;
        Season season2 = Season.WINTER;
        System.out.println(season1);
        System.out.println(season2);

        // 2. enum类测试
        System.out.println("*********使用enum的枚举类测试*************");
        Season2 summer = Season2.SUMMER;
        Season2 autumn = Season2.AUTUMN;
        System.out.println(summer); // 如果没有重写toString(), 此时会输出SUMMER
        System.out.println(autumn); // 此时会输出AUTUMN
        System.out.println(Season2.class.getSuperclass()); // class java.lang.Enum

        // 3. Enum类的常用方法
        System.out.println("*********Enum类的常用方法测试*************");
        System.out.println("**********1. values() 方法***************");
        Season2[] values = Season2.values();
        for (int i = 0; i < values.length; ++i) {
            System.out.println(values[i]);
        }

        System.out.println("------------Thread.values----------------");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; ++i) {
            System.out.println(values1[i]);
        }

        System.out.println("***********2. toString() 方法**************");
        System.out.println(summer.toString());
        System.out.println(autumn.toString());

        System.out.println("***********3. valueOf(str) 方法************");
        // valueOf(str)方法：直接返回str的枚举类型，如果不存在则报错java.lang.IllegalArgumentException
        Season2 spring = Season2.valueOf("SPRING");
        System.out.println(spring);

        System.out.println("\n*************** enum类实现接口************");
        summer.show(); // 夏天是万物生长的季节
        autumn.show(); // 秋天是万物成熟的季节

        switch (summer) {
            // 使用switch时，不需要指明 enum类名
            case SUMMER :
                System.out.println("this is a summer");
                break;
            case AUTUMN:
                System.out.println("this is a autumn");
                break;
            default:
                break;
        }
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

// enum类实现接口
interface info {
    void show();
}

// 二、使用enum关键字来实现枚举类
// 接口1：首先需要实现接口
enum Season2 implements info {
    // 1. 使用enum关键字创建枚举类时，首先需要提供枚举类的对象，多个对象之间用逗号”，“隔开，最后使用“;"结尾
    // 方法二: 在每个枚举类对象中实现接口
    SPRING("春天", "春暖花开") {
        @Override
        public void show() {
            System.out.println("春天是万物复苏的季节");
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        @Override
        public void show() {
            System.out.println("夏天是万物生长的季节");
        }
    },
    AUTUMN("秋天", "秋高气爽") {
        @Override
        public void show() {
            System.out.println("秋天是万物成熟的季节");
        }
    },
    WINTER("冬天", "梅花盛开") {
        @Override
        public void show() {
            System.out.println("冬天是冰雪的世界");
        }
    };

    // 2. 创建枚举类的属性
    private final String seasonName;
    private final String seasonDesc;

    // 3. 将构造器声明为private的
    private Season2(String seasonDesc, String seasonName) {
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    // 4. 其他诉求：获取属性信息
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }



    // 4.2 此时可以不必重写toString()方法，因为enum继承于Enum类

    // 5. 方法一：重写接口中的抽象方法，此时所有枚举类的返回值相同
    @Override
    public void show() {
        System.out.println("This is a season!");
    }

}