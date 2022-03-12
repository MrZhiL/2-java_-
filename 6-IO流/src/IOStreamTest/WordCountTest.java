package IOStreamTest;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 获取文本文件中每个字符出现的次数，并把数据写入到文件中
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/11 22:40
 * @node: 思路：
 *          1. 遍历文本中的每一个字符
 *          2. 统计字符出现的此时，将其存在Map中进行统计
 */
public class WordCountTest {
    public static void main(String[] args) {
        WordCountTest wordCountTest = new WordCountTest();
        String srcStr = "D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\read.md";
        String destStr = "D:\\Program Files (x86)\\JavaProject\\2-Java高级部分\\6-IO流\\readWordCount.txt";
        wordCountTest.fileWordCountTest(srcStr, destStr);
//        System.out.println(new File("read.md").getAbsolutePath()); // D:\Program Files (x86)\JavaProject\2-Java高级部分\read.md
    }

    public void fileWordCountTest(String srcfile, String destfile) {
        File srcFile = new File(srcfile);
        File destFile = new File(destfile);

        System.out.println(srcFile.getName() + " exists ? " + srcFile.exists());
        System.out.println(destFile.getName() + " exists ? " + destFile.exists());

        FileReader fr = null;
        BufferedWriter bw = null;

        Map<Character, Integer> wordMap = new HashMap<>();

        try {

            fr = new FileReader(srcFile);
            bw = new BufferedWriter(new FileWriter(destFile));

            int data = 0;
            Character ch;

            // 1. 读取文件中的字符，并写入到Map中
            while ((data = fr.read()) != -1) {
                ch = (char)data;
                if (!wordMap.containsKey(ch)) {
                    wordMap.put(ch, 0);
                } else {
                    wordMap.put(ch, wordMap.get(ch) + 1);
                }
            }

            // 2. 将Map中的字符写入的destFile中
            wordWritetoFile(wordMap, bw);

            System.out.println("字符统计成功!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean wordWritetoFile(Map<Character, Integer> wordMap, BufferedWriter bw) throws IOException {
        if (wordMap.isEmpty()) {
            System.out.println("map为空!");
            return true;
        }

        Set<Map.Entry<Character, Integer>> entries = wordMap.entrySet();
        for (var val : entries) {
            Character ch = val.getKey();
            switch (ch) {
                case '\n' :
                    bw.write("换行符 : " + val.getValue());
                    break;
                case ' ' :
                    bw.write("空格 : " + val.getValue());
                    break;
                case '\t' :
                    bw.write("制表符 : " + val.getValue());
                    break;
                case '\r' :
                    bw.write("回车符 : " + val.getValue());
                    break;
                default:
                    bw.write(val.getKey()  + " : " + val.getValue());
                    break;
            }
            bw.newLine();
        }

        if (bw != null) {
            bw.close();
        }
        return true;
    }
}
