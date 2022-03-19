package TCPTest1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @ClassName: TCPTest1.TCPTest1
 * @Description: Java - TCP的网络编程例子1
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/19 20:15
 * @node:
 *         案例实现： 客户端发送信息给服务端，服务端将数据显示在控制台上
 *
 *          1. 客户端：
 *              1.1 创建socket对象，指明服务器端的IP地址和端口号。必须指定端口号，否则无法创建Socket套接字
 *              1.2 获取一个输出流，用于输出数据
 *              1.3 写出数据的操作
 *              1.4 关闭Socket对象
 *
 *          2. 服务端：
 *              2.1 创建服务器端的ServerSocket，指明自己的端口号。必须指定端口号，否则无法创建Socket套接字
 *              2.2 调用accept方法，表示接收客户端的Socket
 *              2.3 获取输入流
 *              2.4 读取数据
 *              2.5 关闭相应的流和Socket对象
 */
public class TCPClient {
    public static void main(String[] args) {
        TCPClient t1 = new TCPClient();
        //t1.server();
        t1.client();
    }

    //@Test
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
}
