package ReflectionTest2;

/**
 * @ClassName: ReflectionTest2
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/3/25 9:51
 * @node:
 */
@MyAnnotation(value = "Person")
public class Person extends Creature<String> implements Comparable, MyInterface{

    private String name;
    int age;
    public int id;

    public Person() {}

    @MyAnnotation(value = "Person(name)")
    private Person(String name) {
        this.name = name;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation(value = "show")
    private String show(String nation) {
        System.out.println("国籍： " + nation);
        return nation;
    }

    public String display(String interests, int age) throws NullPointerException, RuntimeException, ClassCastException {
        System.out.println("interest: " + interests);
        return interests + age;
    }

    @Override
    public void info() {
        System.out.println("I am a person!");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
