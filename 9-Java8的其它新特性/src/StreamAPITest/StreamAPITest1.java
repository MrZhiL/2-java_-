package StreamAPITest;

import MethodReferenceTest.Employee;
import MethodReferenceTest.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName: StreamAPITest1
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/11 15:50
 * @node: Stream API的测试
 *
 *          1. Stream关注的是对数据的运算，与CPU打交道
 *             集合关注的是数据的存储，与内存大交道
 *          2. Stream流的特点
 *             2.1 Stream 自己不会存储元素
 *             2.2 Stream 不会改变源对象。相反，他们会返回一个持有结果的新的Stream
 *             2.3 Stream 操作时延迟执行的。这意味着他们会等到需要结果的时候才执行
 *          3. Stream 执行流程：
 *             3.1 Stream 实例化
 *             3.2 一系列的中间操作（filter, map, ...）
 *             3.3 终止操作
 *          4. 说明：
 *             4.1 一个中间操作链，对数据源的数据进行处理
 *             4.2 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用。
 *
 */
public class StreamAPITest1 {
    
    // 创建Stream方式一：通过集合
    @Test
    public void test01(){
        List<Employee> employees = EmployeeData.getEmployees();

        // default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();
        Object[] employeeps = stream.toArray(); // 将stream流转换为Object数组

        long t1 = System.currentTimeMillis();
        for (Object o : employeeps) {
            System.out.println(o);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1); // 17 ms
        System.out.println("**************************\n");

        // default Stream<E> parallelStream() : 返回一个并行流
        Stream<Employee> employeeStream = employees.parallelStream();
        Object[] objects = employeeStream.toArray();
        t1 = System.currentTimeMillis();
        for (Object o : objects) {
            System.out.println(o);
        }
        System.out.println(System.currentTimeMillis() - t1); // 0 ms
    }

    // 创建Stream方式二：通过数组
    @Test
    public void test02() {
        // 调用Arrays类的 static <T> stream<T> stream(int[] array)：返回一个流

        int[] arr = new int[] {1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);
        System.out.println(stream.sum()); // 21

        Employee e1 = new Employee(1, "jack");
        Employee e2 = new Employee(2, "lisi");
        Employee[] arr2 = new Employee[]{e1, e2};
        Stream<Employee> stream2 = Arrays.stream(arr2);
        System.out.println(stream2.toString());
    }

    // 创建Stream方式三：通过Stream的of()
    @Test
    public void test03() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    // 创建Stream方式四：创建无限流
    // 可以使用静态方法 Stream.iterate() 和 Stream.generate()创建无限流
    // 迭代：`public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)`
    // 生成：`public static<T> Stream<T> generate(Supplier<T> s)`
    @Test
    public void test04() {
        // 1. 迭代Stream.iterate()，遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        // 2. 使用生产函数来产生10个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
