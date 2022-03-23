package ReflectionTest;

/**
 * @ClassName: ReflectionTest/Person
 * @Description: Java - 构建Person类
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/23 11:32
 * @node:
 */
public class Person {
    private String name;
    protected int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
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

    public void show() {
        System.out.println("pubic void show() : " + this.toString());
    }

    private String showNation(String nation) {
        System.out.println("public void show(nation) : " + nation);
        return nation;
    }
}
