package MethodReferenceTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MethodReferenceTest
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/11 10:41
 * @node:
 */
public class EmployeeData {
    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "马化腾", 44, 400000000) );
        list.add(new Employee(1002, "马云", 54, 2318293) );
        list.add(new Employee(1003, "雷军", 52, 12543234) );
        list.add(new Employee(1004, "刘强东", 48, 12312341) );
        list.add(new Employee(1005, "李彦宏", 43, 901281233.31) );
        list.add(new Employee(1006, "扎克伯格", 40, 99899123.123) );
        list.add(new Employee(1007, "比尔盖茨", 58, 841909312) );
        list.add(new Employee(1008, "马斯克", 45, 8907813) );
        list.add(new Employee(1009, "李想", 34, 1231234) );

        return list;
    }

}
