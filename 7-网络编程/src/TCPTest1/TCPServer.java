package TCPTest1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: TCPTest1
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/19 21:19
 * @node:
 */
public class TCPServer {
    public static void main(String[] args) {
        TCPServer ts = new TCPServer();
        ts.server();
    }
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
}
