package src.SetTest.TreeSetTest2;

import java.util.Objects;

/**
 * @ClassName: src.SetTest.TreeSetTest2.Employee
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/1 10:36
 * @node: 定义一个Employee类
 *  *            该类包括：private 成员变量：name, age, birthday，其中birthday为MyDate类的对象;
 *  *            并为每一个属性定义 getter, setter方法；
 *  *            重写toString()方法输出 name,age,birthday
 */
public class Employee implements Comparable{
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }


    @Override
    // 使Employee实现Comparable接口，并按Name排序, 二级排序使用MyDate进行排序
    public int compareTo(Object o) {
        if (o instanceof Employee) {
            Employee e = (Employee) o;

            int n = this.name.compareTo(e.name);
            if (n != 0) {
                return n;
            }

            // 如果姓名相同，则按照生日进行排序
            return this.birthday.compareTo(e.birthday);
        }
        throw new RuntimeException("传输类型参数不匹配!");
    }
}
