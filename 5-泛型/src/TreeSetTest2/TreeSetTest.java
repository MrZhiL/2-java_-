package src.TreeSetTest2;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @ClassName: TreeSetTest2
 * @Description: Java - TreeSet的测试案例， 使用泛型
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/1 10:12
 * @node:
 *         1. 定义一个Employee类
 *            该类包括：private 成员变量：name, age, birthday，其中birthday为MyDate类的对象;
 *            并为每一个属性定义 getter, setter方法；
 *            重写toString()方法输出 name,age,birthday
 *
 *         2. MyDate类包含：
 *            private成员变量 year, month, day；并为每一个属性定义getter, setter方法
 *
 *         3. 创建该类的5个对象，并把这些对象放入TreeSet集合中，分别按以下两种方法对的集合中的元素进行排序，并遍历输出：
 *            1). 使Employee实现Comparable接口，并按Name排序
 *            2). 创建TreeSet时传入Comparator对象，按生日日期的先后排序
 *
 */
public class TreeSetTest {
    public static void main(String[] args) {
        TreeSetTest tst = new TreeSetTest();
        System.out.println("----1. 使Employee实现Comparable接口，并按Name排序----");
        tst.test01();

        System.out.println("----2. 创建TreeSet时传入Comparator对象，按生日日期的先后排序----");
        tst.test02();
    }

    // 1. 创建该类的5个对象，并把这些对象放入TreeSet集合中，分别按以下两种方法对的集合中的元素进行排序，并遍历输出：
    public void test01() {

        TreeSet<Employee> treeSet = new TreeSet<Employee>();
        // TreeSet<Employee> treeSet = new TreeSet<>(); // jdk7.0 之后可以在后面省略泛型中的内容，只需要指定一个

        Employee e1 = new Employee("lisi", 24, new MyDate(1999, 9, 21));
        Employee e2 = new Employee("wangwu", 26, new MyDate(1997, 1, 9));
        Employee e3 = new Employee("zhaosi", 19, new MyDate(2002, 5, 15));
        Employee e4 = new Employee("liyou", 31, new MyDate(1980, 7, 8));
        Employee e5 = new Employee("caode", 24, new MyDate(1999, 9, 1));
        Employee e6 = new Employee("caode", 24, new MyDate(1999, 9, 2));

        treeSet.add(e1);
        treeSet.add(e2);
        treeSet.add(e3);
        treeSet.add(e4);
        treeSet.add(e5);
        treeSet.add(e6);

        // 2. 使Employee实现Comparable接口，并按Name排序(此时指定按照MyDate进行二级排序)
        for(Employee e : treeSet) {
            System.out.println(e);
        }
    }

    // 2. 创建TreeSet时传入Comparator对象，按生日日期的先后排序 : 使用泛型
    public void test02(){ 
        /** 不使用泛型的写法 */
//        Comparator comparator = new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                if (o1 instanceof Employee && o2 instanceof Employee) {
//                    Employee e1 = (Employee) o1;
//                    Employee e2 = (Employee) o2;
//
//                    // 指定一级排序方式：生日
//                    MyDate m1 = e1.getBirthday();
//                    MyDate m2 = e2.getBirthday();
//                    int minBir = m1.compareTo(m2);
//                    if (minBir != 0) {
//                        return minBir;
//                    }
//
//                    // 指定二级排序方式：name
//                    return e1.getName().compareTo(e2.getName());
//                }
//                // return 0;
//                 throw new RuntimeException("传输参数类型不匹配!");
//            }
//        };
        /** 使用泛型的写法 */
        Comparator<Employee> comparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                // 指定一级排序方式：生日
                MyDate m1 = o1.getBirthday();
                MyDate m2 = o2.getBirthday();
                int minBir = m1.compareTo(m2);
                if (minBir != 0) {
                    return minBir;
                }

                // 指定二级排序方式：name
                return o1.getName().compareTo(o2.getName());
            }
        };

        TreeSet<Employee> treeSet = new TreeSet<Employee>(comparator);

        Employee e1 = new Employee("lisi", 24, new MyDate(1999, 9, 21));
        Employee e2 = new Employee("wangwu", 26, new MyDate(1997, 1, 9));
        Employee e3 = new Employee("zhaosi", 19, new MyDate(2002, 5, 15));
        Employee e4 = new Employee("liyou", 31, new MyDate(1980, 7, 8));
        Employee e5 = new Employee("caode", 24, new MyDate(1999, 9, 1));
        Employee e6 = new Employee("caode", 24, new MyDate(1999, 9, 2));

        treeSet.add(e1);
        treeSet.add(e2);
        treeSet.add(e3);
        treeSet.add(e4);
        treeSet.add(e5);
        treeSet.add(e6);

        // 2. 按生日日期的先后排序(此时指定按照name进行二级排序)
        for(Employee e : treeSet) {
            System.out.println(e);
        }
    }
}
