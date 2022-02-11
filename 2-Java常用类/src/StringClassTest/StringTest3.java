package StringClassTest;

/**
 * @ClassName: StringClassTest3
 * @Description: Java - 一道简单的面试题
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/11 17:25
 * @node:
 */
public class StringTest3 {
    String str = new String("hello");
    char[] ch = {'t', 'e', 's', 't'};


    // note: 因为String具有不可变性，因此此时change中的str只是一个临时变量，当调用结束后就会销毁，str的值不会发生改变
    // 而char数组中传递的是ch的地址，具有可变性，因此会发生改变
    public void change(String str, char[] ch) {
        str = "good";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest3 st3 = new StringTest3();
        st3.change(st3.str, st3.ch);
        System.out.println(st3.str); // hello
        System.out.println(st3.ch);  // best
    }
}
