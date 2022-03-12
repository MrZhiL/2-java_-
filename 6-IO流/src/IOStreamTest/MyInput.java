package IOStreamTest;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName: IOStreamTest
 * @Description: Java - 标准输入输出测试
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/12 19:08
 * @node:
 *          MyInput.java: Contain the methods for reading int, double, float, boolean,
 *                        short, byte and string values from the keyboard
 */
public class MyInput {
    // Read a String from the keyboard
    public static String readString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Declare and initialize the String
        String str = "";

        // Get the string from the keyboard
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the String obtained from the keyboard
        return str;
    }

    // Read a int from the keyboard
    public static int readInt() {return Integer.parseInt(readString()); }

    // Read a double from the keyboard
    public static double readDouble() {return Double.parseDouble(readString()); }

    // Read a float from the keyboard
    public static float readFloat() {return Float.parseFloat(readString()); }

    // Read a short from the keyboard
    public static short readShort() {return Short.parseShort(readString()); }

    // Read a byte from the keyboard
    public static byte readByte() {return  Byte.parseByte(readString()); }

    // Read a boolean from the keyboard
    public static boolean readBoolean() {return Boolean.getBoolean(readString()); }
}
