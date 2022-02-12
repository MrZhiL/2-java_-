package StringMethodTest;

import org.junit.Test;

/**
 * @ClassName: StringMethodTest3
 * @Description: Java - 常用方法的测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/12 17:41
 * @node:
 *      String replace(char oldChar, char newChar): 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
 *      String replace(CharSequence target, CharSequence replacement): 使用指定的字母值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
 *      String replaceAll(String regex, String replacement): 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
 *      String replaceFirst(String regex, String replacement): 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
 *
 *      boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式
 *
 *      String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
 *      String[] split(String regex, int limit): 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
 */
public class StringMethodTest3 {
    @Test
    public void test4() {

        // 1. String replace(char oldChar, char newChar): 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
        String str1 = "helloworld";
        String str2 = str1.replace('o', 'O');
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("*********************************");

        // 2. String replace(CharSequence target, CharSequence replacement): 使用指定的字母值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
        String str3 = "Hello, this is a test, this is String test!";
        String str4 = str3.replace("test", "TEST");
        System.out.println(str3);
        System.out.println(str4);
        System.out.println("*********************************");

        // 3. String replaceAll(String regex, String replacement): 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
        String str5 = "12hello34world5java7891mysql";
        // 把字符串中的数字替换成','，如果开头和结尾中存在','则去掉
        String str6 = str5.replaceAll("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(str6); // hello,world,java,mysql
        System.out.println("*********************************");

        // 4. String replaceFirst(String regex, String replacement): 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
        String str7 = str5.replaceFirst("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(str7); // hello34world5java7891mysql
        System.out.println("*********************************");

        // 5. boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式
        // 判断这是否是一个北京的电话号码
        String tel = "010-2928192";
        String tel2 = "010-292811292";
        boolean res = tel.matches("010-\\d{7,8}"); // true
        boolean res2 = tel2.matches("010-\\d{7,8}"); // false
        System.out.println(res);
        System.out.println(res2);
        System.out.println("*********************************");

        // 6. String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
        String str8 = "Hello,|welcome|to|Java|world!";
        String[] str9 = str8.split("\\|"); // Hello, welcome to Java world!
        for(int i = 0; i < str9.length; ++i) {
            System.out.print(str9[i] + " ");
        }
        System.out.println();

        String str10 = "Hello,.welcome.to.Java.world!";
        String[] str11 = str10.split("\\."); // Hello, welcome to Java world!

        // 7. String[] split(String regex, int limit): 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
        String[] str12 = str10.split("\\.", 3); // Hello, welcome to.Java.world!
        for(int i = 0; i < str11.length; ++i) {
            System.out.print(str11[i] + " ");
        }
        System.out.println();
        System.out.println("*********************************");

        for(int i = 0; i < str12.length; ++i) {
            System.out.print(str12[i] + " ");
        }
        System.out.println();
    }
}
