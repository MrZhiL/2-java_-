package otherClassTest;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @ClassName: otherClassTest
 * @Description: Java - 其他常用类的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/19 11:38
 * @node: 其他常用类的使用
 *      1. System
 *      2. Math
 *      3. BigInteger 和 BigDecimal
 */
public class OtherClassTest {
    @Test
    // 1. System类的测试
    public void test01() {
        String str1 = System.getProperty("java.version"); // 获取当前java的版本
        String str2 = System.getProperty("java.home"); // 获取java的安装目录
        String str3 = System.getProperty("os.name"); // 获取操作系统名称
        String str4 = System.getProperty("os.version"); // 获取操作系统的版本
        String str5 = System.getProperty("user.name"); // 获取用户的账号名称
        String str6 = System.getProperty("user.home"); // 获取用户的主目录
        String str7 = System.getProperty("user.dir"); // 获取用户的当前工作目录

        System.out.println("java版本：" + str1);
        System.out.println("java的安装目录：" + str2);
        System.out.println("操作系统名称：" + str3);
        System.out.println("操作系统的版本：" + str4);
        System.out.println("用户的账号名称：" + str5);
        System.out.println("用户的主目录：" + str6);
        System.out.println("用户的当前工作目录：" + str7);
    }

    // 2. BigInteger / BigDecimal
    @Test
    public void test02() {
        BigInteger bi1 = new BigInteger("1238818892381729873979123");
        BigInteger bi2 = new BigInteger("1238818");

        BigInteger bi3 = bi1.divide(bi2);
        System.out.println("bi1 = " + bi1);
        System.out.println("bi2 = " + bi2);
        System.out.println("bi1 / bi2 = " + bi3);

        BigDecimal bd1 = new BigDecimal("123999890892123.901");
        BigDecimal bd2 = new BigDecimal("12331232.21");
        BigDecimal bd3 = bd1.divide(bd2, 15, RoundingMode.HALF_UP); // 保留精度15位，并四舍五入
        // BigDecimal bd4 = bd1.divide(bd2); // 如果不指定精度会报错
        System.out.println("bd1 = " + bd1);
        System.out.println("bd2 = " + bd2);
        System.out.println("bd1 / bd2 = " + bd3);

    }
}
