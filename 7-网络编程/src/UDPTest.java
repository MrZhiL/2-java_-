import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: UDPTest
 * @Description: Java - 网络编程之UDP测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/20 21:50
 * @node:
 *          - 类 DatagramSocket 和 DatagramPacket 实现了基于UDP协议网络程序
 *          - UDP数据报通过套接字 DatagramSocket 发送和接收，系统不保证UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达。
 *          - DatagramPacket 对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口号以及接收端的IP地址和端口号
 *          - UDP协议中每个数据报都给出了完成的地址信息，因此无序建立发送方和接收方的连接。
 */
public class UDPTest {
    @Test
    // UDP的发送端
    public void sender() {
        DatagramSocket datagramSocket = null; // 此时可以不用指定IP地址和端口号
        try {
            // 1. 使用类DatagramSocket 建立UDP的套接字
            datagramSocket = new DatagramSocket();

            // 2. 使用类DatagramPacket 建立UDP的数据报，来存储数据
            // 如果 datagramSocket 没有指定IP和端口号，则需要在这里进行绑定
            String str = new String("Hello, 这是使用UDP发送的数据报！");
            byte[] data = str.getBytes(StandardCharsets.UTF_8);

            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            DatagramPacket datagramPacket = new DatagramPacket(data, 0, data.length, inetAddress, 8081);

            // 3. 调用DatagramSocket的send()方法发送数据
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭套接字
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    // UDP的接收端
    @Test
    public void receiver() {
        DatagramSocket socket = null;
        try {
            // 1. 建立UDP套接字，因为是服务端，所以需要指定端口号
            socket = new DatagramSocket(8081);

            // 2. 建立数据报
            byte[] buffer = new byte[100]; // 因此使用UDP接收数据报时，需要一次性全部接收，如果接收不完则会抛弃，因此可以将该数组设置大一点
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

            // 3. 调用DatagramSocket的receive()方法进行数据接收，并存放如packet中
            socket.receive(packet);

            // 4. 将数据进行打印
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭套接字
            socket.close();
        }


    }
}
