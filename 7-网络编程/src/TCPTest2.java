import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @ClassName: TCPTest2
 * @Description: Java - TCP测试2：
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/19 21:07
 * @node:
 *         实现：客户端发送文件给服务端，服务端将文件保存在本地
 */
public class TCPTest2 {
    @Test
    public void client() throws IOException {
        Socket sc = new Socket( InetAddress.getByName("127.0.0.1"), 8001);

        OutputStream outputStream = sc.getOutputStream();

        FileInputStream fis = new FileInputStream("photo.jpg");

        byte[] data = new byte[1024];
        int len = -1;
        while ((len = fis.read(data)) != -1) {
            outputStream.write(data,0, len);
        }
        System.out.println("文件复制完成");

        fis.close();
        outputStream.close();
        sc.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocketc = new ServerSocket(8001);

        Socket socket = serverSocketc.accept();

        InputStream inputStream = socket.getInputStream();

        FileOutputStream fos = new FileOutputStream("photo2.jpg");

        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }

        System.out.println("文件复制完成");

        fos.close();
        inputStream.close();
        socket.close();
        serverSocketc.close();

    }
}
