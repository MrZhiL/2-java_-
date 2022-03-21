import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName: URLTest
 * @Description: Java - URL编程测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/21 12:37
 * @node:
 *          - URL(Uniform Resource Locator) : 统一资源定位符，它表示Internet上某一资源的地址
 *          - 它是一种具体的URI，即URL可以用来标识一个资源，而且还指明了如何loacte这个资源。
 *          - 通过URL我们可以访问Internet上的各种网络资源，比如最常见的www, ftp站点。 浏览通过解析给定的URL可以在网络上查找相应的文件或其他资源。
 *          - URL的基本结构由5部分组成：
 *          - <传输协议>://<主机名>:<端口号>/<文件名>#片段名?参数列表
 *          - 例如： `http://192.168.0.100:8080/helloworld/index.jsp#a?username=zhilx&passwd=123456
 *          - #片段名：即锚点，例如看小说，直接定位到章节
 *          - 参数列表格式：参数名=参数值&参数名=参数值...
 */
public class URLTest {
    public static void main(String[] args) {
        URLTest urlTest = new URLTest();
        urlTest.test01();
        urlTest.test02();
    }

    // 1. URL测试
    public void test01() {
        // 1. 创建URL标识
        try {
            URL url = new URL("http://192.168.0.100:8080/helloworld/index.jsp#a?username=zhilx&passwd=123456");

            // 2. URL常用方法测试
            // 2.1 使用 getProtocol获取URL的协议名
            System.out.println(url.getProtocol());

            // 2.2 使用getHost()获取URL的主机名
            System.out.println(url.getHost());

            // 2.3 使用getPost()获取URL的端口号
            System.out.println(url.getProtocol());

            // 2.4 使用getPath()获取文件路径
            System.out.println(url.getPath());

            // 2.5 使用getFile()获取URL的文件名
            System.out.println(url.getFile());

            // 2.6 使用getQuery() 获取URL的查询名
            System.out.println(url.getQuery());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // 2. URL编程实现网络资源的下载
    // TCP、UDP的博客中的图片： https://img-blog.csdnimg.cn/56b8a4e4777c4b4ba632cbf9cbeaac5e.png
    public void test02(){
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            // 1. 指定URL的指定文件位置
            URL url = new URL(" https://img-blog.csdnimg.cn/56b8a4e4777c4b4ba632cbf9cbeaac5e.png");

            // 2. 获取URL连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // 3. 建立连接（一定要有这个步骤）
            urlConnection.connect();

            // 4. 下载数据
            inputStream = urlConnection.getInputStream();
            fos = new FileOutputStream("7-网络编程\\src\\figure1.png");

            // 5. 将数据写入到文件中
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("文件下载完成!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 6. 关闭流和套接字
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
