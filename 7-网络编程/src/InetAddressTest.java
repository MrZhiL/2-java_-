import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: InetAddressTest
 * @Description: Java - 网络编程测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/19 17:31
 * @node:
 *          1. 在Java中使用InetAddress类表示IP
 *          2. 可以通过InetAddress.getByName("192.168.0.1"); 来获取IP地址
 *          3. 使用域名：www.baidu.com  www.sina.com
 *          4. 本级IP：loaclhost / 127.0.0.1
 *          5. 如何实例化InetAddress:
 *              5.1 getByName(String host)
 *              5.2 getLocalHost();
*           6. 两个常用方法：getHostName() / getHostAddress() : 获取域名和Ip地址
 */
public class InetAddressTest {
    @Test
    public void InetAddressTest01() {

        try {
            // 网络IP地址获取测试1
            InetAddress inet1 = InetAddress.getByName("192.168.0.1");
            System.out.println(inet1);

            InetAddress[] inet2 = InetAddress.getAllByName("www.baidu.com");
            for (int i = 0; i < inet2.length; ++i) {
                System.out.println(inet2[i]);
            }

            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);
            
            // 可通过 直接获取本地的IP地址
            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);

            // getHostName() : 获取Ip地址的域名
            System.out.println(inet2[0].getHostName());

            // getHostAddress() ： 获取域名的IP地址
            System.out.println(inet4.getHostName());
            System.out.println(inet4.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
