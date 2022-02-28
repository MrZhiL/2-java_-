package src.SetTest;

import java.util.Objects;

/**
 * @ClassName: src.SetTest
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/28 13:12
 * @node:
 */
class Person {
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
}

