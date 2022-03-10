package IOStreamTest;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: IOStreamTest
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/9 21:12
 * @node:
 */
public class dateReadtoWrite {
    public static void main(String[] args) {
        dateReadtoWrite dr = new dateReadtoWrite();
        dr.testFileReadertoWriter();
    }


    public void testFileReadertoWriter() {
        // 1. File类的实例化
        // 2. IO流的实例化
        // 3. 读入的操作
        // 4. 资源的关闭

        // 1. File类的实例化
        File srcFile = new File("C:\\Users\\Mr.ZHI\\Desktop\\data");
        File destFile = new File("C:\\Users\\Mr.ZHI\\Desktop\\data\\newdata");

        if (destFile.exists() == false) {
            destFile.mkdir();
        }
//        destFile.delete();

        FileReader fr = null; // 创建FileReader的变量
        FileWriter fw = null;

        String[] list = srcFile.list();
        for (String str : list) {
            try {
                if (str.contains(".txt")) {

                    System.out.println(srcFile + "\\" + str);
                    fr = new FileReader(srcFile + "\\" + str);

                    fw = new FileWriter(new File(destFile + "\\" + str.substring(0, str.length() - 4) + "modify.txt"));

                    BufferedReader srcBuff = new BufferedReader(fr);

                    // 3. 读入的操作，使用read(char[] cbuf) : 返回这每次读入cbuf数组中的字符的个数。果到达文件末尾，则返回-1
                    String line = "";

                    for (int i = 0; i < 1024; ++i) {
                        if ((line = srcBuff.readLine()) != null) {
                            fw.write(line + "\n");
                        }
                    }
                    System.out.println("已成功将" + srcFile.getName() + " 复制到 " + destFile.getName());

                    if (srcBuff != null) {
                        srcBuff.close();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 4. 资源的关闭
                // note：此时可以并行写多个try-catch语句，且都会执行，因此try-catch本身相当于已经把异常处理了（抛出处理），因此不会影响下面的语句的执行
                try {
                    if (fr != null) {
                        fr.close();
                        System.out.println("fr已关闭");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (fw != null) {
                        fw.close();
                        System.out.println("fw已关闭");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
