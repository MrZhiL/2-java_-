## 12. 网络编程

1. 网络编程概述
2. 网络通信要素概述
3. 通信要素1：IP和端口号
4. 通信要素2：网络协议
5. TCP网络编程
6. UDP网络编程
7. URL编程

### 1. 概述
- java是Internet上的语言，它从语言级上提供了对网络应用程序的支持，程序员能够很容易开发常见的网络应用程序
- Java提供的网络类库，可以实现无痛的网络连接，联网的底层细节被隐藏在Java的本级安装系统里，由JVM进行控制。
并且Java实现了一个跨平台的网卡库，**程序员面对的是一个统一的网络编程环境。**

### 2. 网络通信要素要素1：IP地址和端口号

```java
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
 *
 *          7. 端口号：要求不同的进程有不同的端口号：0~65535
 *
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

```

### 3. 通信要素2：网络协议

#### 3.1 TCP 
- 使用TCP协议前，须先建立TCP连接，形成传输数据通道
- 传输前，采用“三次握手”方式，点对点通信，是可靠的
- TCP协议进行通信的两个应用进程：客户端、服务器
- 在连接中可进行大数据量的传输
- 传输完毕后，需释放已建立的连接，效率低

#### 3.2 UDP
- 将数据、源、目的封装成数据包，不需要建立连接
- 每个数据报的大小限制在64K内
- 发送不管对方会是否准备好，接收方收到也不确认，故为不可靠的
- 可以广播发送
- 发送数据结束时无序释放资源，开销小，速度快

### 3.3 TCP代码测试
- Server端
```java
public void server() {
    ServerSocket ssc = null;
    Socket sc = null;
    InputStream is = null;
    ByteArrayOutputStream bios = null;
    try {
        // 1. 创建服务端Socket，必须指定端口号，否则无法创建Socket套接字
        ssc = new ServerSocket(7859);

        // 2. 接收客户端的socket
        sc = ssc.accept();

        // 3. 获取输入流
        is = sc.getInputStream();
        bios = new ByteArrayOutputStream();
        byte[] buffer = new byte[100];
        int len = -1;

        while ((len = is.read(buffer)) != -1) {
            bios.write(buffer, 0, len);
        }

        // 4. 打印数据
        if (bios.size() != 0) {
            System.out.print("服务器接收到：" + sc.getInetAddress().getHostAddress() + " 发送过来的数据: ");
            System.out.println(bios);
            bios.reset();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 5. 关闭流和套接字
        if (bios != null) {
            try {
                bios.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sc != null) {
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (ssc != null) {
            try {
                ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

- client端
```java
public void client() {
    Socket sc = null; // 指明IP地址和端口号
    OutputStream os = null;
    try {
        // 1. 创建Socket对象，指明服务器端的IP地址和端口号
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        sc = new Socket(addr, 7859);
    
        // 2. 获取一个输出流，用于输出数据
        os = sc.getOutputStream();
    
        // 3. 写出数据
        // 循环写出数据，直到输入exit
        StringBuilder stringBuilder = null;
        os.write(new String("栋梁栋梁！这里是TCP的Client客户端，收到请回复！").getBytes(StandardCharsets.UTF_8));

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 4. 关闭Socket对象
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        if (sc != null) {
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```


