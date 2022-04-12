package StreamAPITest;

import MethodReferenceTest.Employee;
import MethodReferenceTest.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Collectors;

/**
 * @ClassName: StreamAPITest3
 * @Description: Java - Stream API的终止操作
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/12 11:39
 * @node:
 *
 */
public class StreamAPITest3 {
    /** 1. 匹配与查找   */
    @Test
    public void test01() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 1. allMatch(Predicate p) : 检查是否匹配所有元素
        // 练习：判断是否所有员工的年龄都大于18
        System.out.println("----- 判断所有员工的年龄身边大于18 -----");
        System.out.println(employees.stream().allMatch(e -> e.getAge() > 18)); // true

        // 2. anyMatch(Predicate p) : 检查是否至少匹配一个元素
        System.out.println("----- 判断是否存在员工的工作大于12k -----");
        System.out.println(employees.stream().anyMatch(e -> e.getSalary() > 12000)); // false

        // 3. noneMatch(Predicate p) : 检查是否没有匹配所有元素
        System.out.println("----- 判断是否存在员工姓“雷” -----");
        System.out.println(employees.stream().noneMatch(e -> e.getName().contains("雷"))); // false
        System.out.println("----- 判断是否存在员工姓“任” -----");
        System.out.println(employees.stream().noneMatch(e -> e.getName().contains("任"))); // true

        // 4. findFirst() : 返回第一个元素
        System.out.println("----- 返回第一个元素 -----");
        // 输出： Optional[Employee{id=1001, name='马化腾', age=44, salary=4.0E8}]
        System.out.println(employees.stream().findFirst());
        // Optional[Employee{id=1007, name='比尔盖茨', age=58, salary=11112.0}]
        System.out.println(employees.stream().sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).findAny());

        // 5. findAny() : 返回当前流中的任意元素
        System.out.println("----- 返回当前流中的任意元素 -----");
        // Optional[Employee{id=1006, name='扎克伯格', age=40, salary=9983.123}]
        System.out.println(employees.parallelStream().findAny());
        // Optional[Employee{id=1004, name='刘强东', age=48, salary=4521.0}]
        System.out.println(employees.parallelStream().sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).findAny());
    }

    @Test
    public void test02() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 6.count() - 方案当前流中的总个数
        System.out.println("----- 返回当前流中的工资大于8000的个数 -----");
        System.out.println(employees.stream().filter(e -> e.getSalary() > 8000).count()); // 6

        // 7. max(Comparator c) ：返回流中的最大值
        System.out.println("---- 返回最高的工资的员工 -----");
        // Optional[Employee{id=1007, name='比尔盖茨', age=58, salary=11112.0}]
        System.out.println(employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        // 8. min(Comparator c) : 返回流中的最小值
        System.out.println("----- 返回最低工资 -----");
        // Optional[Employee{id=1009, name='李想', age=34, salary=1234.0}]
        System.out.println(employees.stream().max((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(employees.stream().map(e -> e.getSalary()).max(Double::compare)); // Optional[11112.0]

        // 9. forEach(Consumer c) : 内部迭代
        System.out.println("----- forEach进行内部迭代 -----");
        employees.stream().forEach(System.out::println);

        // 使用集合的遍历操作
        System.out.println("----- 使用集合的遍历操作 -----");
        employees.forEach(System.out::println);
    }

    /** 2. 规约
     * reduce(T iden, BinaryOperator b) : 可以将流中元素反复结合起来，得到一个值。返回T
     * reduce(BinaryOperator b)         : 可以将流中元素反复结合起来，得到一个值。返回Optional<T>
     */
    @Test
    public void test03() {
        // 1. reduce(T iden, BinaryOperator b) : 可以将流中元素反复结合起来，得到一个值。返回T
        // iden表示一个初始值，这里设置为0
        System.out.println("----- 练习：计算1-10的自然数的和-----");
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(integers.stream().reduce(0, (i1, i2) -> i1 + i2)); // 55
        System.out.println(integers.stream().reduce(0, Integer::sum)); // 55

        // 2. reduce(BinaryOperator b): 可以将流中元素反复结合起来，得到一个值。返回Optional<T>
        System.out.println("-----计算公司所有员工的工资的总和 -----");
        List<Employee> employees = EmployeeData.getEmployees();
        // employees.stream().reduce((e1, e2) -> e1.getSalary() + e2.getSalary()); // 需要使用map进行映射，取出其中的salary
        Optional<Double> reduce = employees.stream().map(Employee::getSalary).reduce(Double::sum); // 和下面语句的作用一样
        reduce = employees.stream().map(e -> e.getSalary()).reduce((d1, d2) -> d1 + d2); // Optional[71761.246]
        System.out.println(reduce); // Optional[71761.246]
        System.out.println(reduce.get()); // 71761.246

        System.out.println("----- 练习：使用reduce(BinaryOperator p)计算1-10的自然数的和-----");
        System.out.println(integers.stream().reduce( Integer::sum)); // Optional[55]

    }

    /** 3. 收集
     * collect(Collector c) | 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test04() {
        System.out.println("----- 练习：查找工资大于9000的员工，结果返回一个为List或Set的集合 -----");

        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> list = employees.stream().filter(e -> e.getSalary() > 9000).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("----- 遍历Set集合 -----");
        Set<Employee> set = employees.stream().filter(e -> e.getSalary() > 9000).collect(Collectors.toSet());
        // set.forEach(System.out::println);
        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        double total = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(total); // 71761.246

        System.out.println("----- 使用averagingDouble计算流中元素属性的平均值 -----");
        double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg); // 7973.471777777778

        System.out.println("----- 使用summarizingDouble 收集流中元素属性的统计值，如计算平均值 -----");
        // DoubleSummaryStatistics{count=9, sum=71761.246000, min=1234.000000, average=7973.471778, max=11112.000000}
        DoubleSummaryStatistics collect = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect);
    }
}
