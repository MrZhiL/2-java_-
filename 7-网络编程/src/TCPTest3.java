import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: TCPTest3
 * @Description: Java - TCP测试3
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/19 21:59
 * @node:
 *      从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的流
 */
public class TCPTest3 {
    @Test
    public void client() throws IOException {
        Socket sc = new Socket( InetAddress.getByName("127.0.0.1"), 8001);

        OutputStream outputStream = sc.getOutputStream();

        outputStream.write("栋梁栋梁！这里是Clinet客户端，收到请回复!".getBytes(StandardCharsets.UTF_8));
        // 当客户端想要接收数据时，需要告诉服务端什么时候发送完成
        // 因为read()为阻塞式的
        sc.shutdownOutput();

        InputStream inputStream = sc.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        System.out.println(baos.toString());

        baos.close();
        inputStream.close();
        outputStream.close();
        sc.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocketc = new ServerSocket(8001);

        Socket socket = serverSocketc.accept();

        InputStream inputStream = socket.getInputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        System.out.println(baos);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("收到回复!".getBytes(StandardCharsets.UTF_8));
        System.out.println("已回复");

        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocketc.close();

    }
}
