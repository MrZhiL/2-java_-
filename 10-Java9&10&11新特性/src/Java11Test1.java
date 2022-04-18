import org.junit.Test;

/**
 * @ClassName: PACKAGE_NAME
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/18 16:01
 * @node: Java 11中增项特性的测试
 */
public class Java11Test1 {
    @Test
    /** Java 11中增项特性一：String中新增的方法
     * 判断字符串是否为空白 : " ".isBlank(); // true
     * 去除首尾空白     : "  Javastack  ".strip(); // "javastack"
     * 去除尾部空格     : "  Javastack  ".stripTrailint(); // "  Javastack"
     * 去除首部格      : "  Javastack  ".stripLeading(); // "Javastack  "
     * 复制字符串      : "Java".repeat(3); // "JavaJavaJava"
     * 行数统计        : "A\nB\C".lines().count(); // 3
     */
    public void test01() {
        String str = "  Javastack  ";
        System.out.println(str);

        // 1. isBlank();
        System.out.println("----- isBlank()测试 -----");
        System.out.println(str.isBlank()); // false
        System.out.println("    ".isBlank()); // true

        // 2. strip()
        System.out.println("----- strip()测试 -----");
        System.out.println(str.strip()); // "Javastack"

        // 3. stripTrailing()
        System.out.println("----- stripTrailing()测试 -----");
        System.out.println(str.stripTrailing()); // "  Javastack"

        // 4. stropLeading()
        System.out.println("----- stropLeading()测试 -----");
        System.out.println(str.stripLeading()); // "Javastack  "

        // 5. repeat()
        System.out.println("----- repeat()测试 -----");
        System.out.println(str.repeat(3)); // "  Javastack    Javastack    Javastack  "

        // 6. lines()
        System.out.println("----- lines()测试 -----");
        System.out.println(str.lines().count()); // 1
        System.out.println("A\nB\nC".lines().count()); // 3
    }
}
