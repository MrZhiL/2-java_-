package src.SetTest.TreeSetTest2;

/**
 * @ClassName: src.SetTest.TreeSetTest2.MyDate类包含
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/1 10:35
 * @node: MyDate类包含：
 *  *     private成员变量 year, month, day；并为每一个属性定义getter, setter方法
 */
public class MyDate implements Comparable{
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        // 按照年月日的自然顺序进行排序
        if (o instanceof MyDate) {
            MyDate date = (MyDate) o;

            int minYear = this.year - date.year;
            if (minYear != 0) {
                return minYear;
            }

            int minMonth = this.month - date.month;
            if (minMonth != 0) {
                return minMonth;
            }

            int minDay = this.day - date.day;
            if (minDay != 0) {
                return minDay;
            }
        }
        return 0;
//        throw new RuntimeException("传输类型参数不一致!");
    }
}
