package src.MapTest;

import java.util.Objects;

/**
 * @ClassName: src.SetTest
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/28 13:12
 * @node:
 */
class Person implements Comparable {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Person equals() ...");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
        // 一个简单的方法(不是特别准确)：
        // return name.hashCode() + age;
    }

    @Override
    // 指定排序方式为：姓名从大到小，年龄从小到大
    public int compareTo(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            int i = this.name.compareTo(p.name);
            if (i != 0) {
                return -i;
            } else {
                return Integer.compare(this.age, p.age);
            }
        } else {
            throw new RuntimeException("输入类型错误！");
        }
    }
}

