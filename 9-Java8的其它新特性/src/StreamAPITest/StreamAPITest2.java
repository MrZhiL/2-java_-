package StreamAPITest;

import MethodReferenceTest.Employee;
import MethodReferenceTest.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @ClassName: StreamAPITest2
 * @Description: Java - Stream API的中间操作
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/12 9:15
 * @node: 测试Stream的中间操作：
 *
 */
public class StreamAPITest2 {

    // 1. 筛选与切片
    @Test
    public void test01() {
        List<Employee> list = EmployeeData.getEmployees();
        System.out.println("list中元素的数量：" + list.size());
        /**
         * note：对流进行终止操作后，该流就被关闭，相等于Iterator的调用，关闭后必须创新创建或打开才可以继续使用。
         */

        // filter(Predicate p) : 接口Lambda，从流中排出某些元素
        // 练习：查询员工表中年龄小于45的员工信息
        System.out.println("-----打印表中年龄大于45的CEO的数量-----");
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getAge() > 45).forEach(System.out::println);

        // note: forEach()表示Stream的终止操作，当调用该条件时已经将Stream关闭，因此后面的流无法指向相应的操作
        //       如果想要执行，则必须重新创建Stream流
        System.out.println("\n-----截断表中的前5个元素-----");
        // limit(long maxSize) : 截断流，使其元素不超过给定数量
        list.stream().limit(5).forEach(System.out::println);

        System.out.println("\n-----跳过表中的前5个元素，打印剩下的元素-----");
        // skip(long n)        : 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
        list.stream().skip(5).forEach(System.out::println);

        System.out.println("\n-----使用distince()去除表中重复的元素-----");
        // distinct()          : 筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        list.add(new Employee(1010, "jack", 44, 8000.00));
        list.add(new Employee(1010, "jack", 45, 8000.00)); // age为45，所以该元素和其余的4个元素的的hashCode不相同，因此会重复打印
        list.add(new Employee(1010, "jack", 44, 8000.00));
        list.add(new Employee(1010, "jack", 44, 8000.00));
        list.add(new Employee(1010, "jack", 44, 8000.00));
        list.stream().distinct().forEach(System.out::println);
    }


    /**
     * 2. 映射
     * | 方法                             | 描述                                         |
     * |---------------------------------|--------------------------------------------|
     * | map(Function f)                 | 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的函数，相当于list.add()|
     * | mapToDouble(ToDoubleFunction f) | 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream |
     * | mapToInt(ToIntFunction f)       | 接收一个函数作为参数，该函数会被应所有到每个元素上，产生一个新的IntStream  |
     * | mapToLong(ToLongFunction f)     | 接收一个函数作为参数，该函数会被应所有到每个元素上，产生一个新的LongStream |
     * | flatMap(Function f)             | 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流都连接成一个流，相当于list.addAll()|
     */
    @Test
    public void test02() {
        // map(Function f) : 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的函数。相当于list.add()
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        // 将list中的所有元素转换为大写
        list.stream().map(str -> str.toUpperCase(Locale.ROOT)).forEach(System.out::println);
        System.out.println();

        // 练习1：获取员工姓名长度大于3的员工的姓名
        System.out.println("----- 获取员工姓名长度大于3的员工的姓名 -----");
        List<Employee> employees = EmployeeData.getEmployees();
        // e -> getName() 可以写成: Employee::getName
        // employees.stream().map(e -> e.getName()).filter(name -> name.length() > 3).forEach(System.out::print ln);
        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);

        // 练习2：使用自定义的函数
        System.out.println("----- 使用自定义函数 -----");
        Stream<Stream<Character>> arrayListStream = list.stream().map(StreamAPITest2::fromStringToStream);
        arrayListStream.forEach(s -> {
            s.forEach(System.out::println);
        });;

        // flatMap(Function f) : 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流都连接成一个流。相当于list.addAll()
        System.out.println("-----------");
        Stream<Character> stream = list.stream().flatMap(StreamAPITest2::fromStringToStream);
        stream.forEach(System.out::println);

    }

    // 将字符串中的多个字符构成的集合转换为对应的Stream实例
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }
}
