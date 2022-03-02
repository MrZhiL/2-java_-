package src.MapTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName: PropertiesTest
 * @Description: Java - Map中的Properties实现类
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/2 16:44
 * @node:
 *      - Properties类是Hashtable的子类，该对象用于处理属性文件
 *      - 由于属性文件里的key、value都是字符串类型，**所以Properties里的key和value都是字符串类型**
 *      - 存取数据时，建议使用setProperty(String key, String value)方法和getProperty(String key)方法
 */
public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");

        pros.load(fis); // 加载对应的文件

        // 获取fis中的信息
        String name = pros.getProperty("name");
        String passwd = pros.getProperty("passwd");
        System.out.println("name = " + name + ", passwd = " + passwd);
        System.out.println("name = " + name + ", passwd = " + pros.getProperty("passwd1")); // 如果找不到则返回null

        fis.close();
    }
}
