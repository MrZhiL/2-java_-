package OptionalTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * @ClassName: OptionalTest
 * @Description: Java - Optional类的介绍
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/4/12 16:23
 * @node:
 *      Optional类：为了在程序中避免出现空指针异常而创建的。
 */
public class OptionalTest {
    @Test
    /** 创建Optional类对象的方法：
     - Optional.of(T t) : 创建一个Optional实例，t必须非空
     - optional.empty() ：创建一个空的Optional实例
     - Optional.ofNullable(T t) : t可以为null
     - Optional.orElse(T t1): 如果当前的Optional内部封装的t是非空的，则返回内部的t；
                              如果内部为空，则返回orElse()方法中的参数t1
     */
    public void test01() {
        // 1. Optional.of(T t): t必须为非空
        Girl girl = new Girl();
        // girl = null; // error, 此时会报空指针异常
        Optional<Girl> girl1 = Optional.of(girl);
        System.out.println(girl1); // Optional[Girl{name='null'}]

        // 2. Optional.ofNullable(T t) : t可以为null
        girl = null;
        Optional<Girl> girl2 = Optional.ofNullable(girl);
        System.out.println(girl2); // Optional.empty

        // 3. optional.empty() ：创建一个空的Optional实例
        Optional<Object> empty = Optional.empty();
        System.out.println(empty); // Optional.empty

        // 4. Optional.orElse(T t1): 如果当前的Optional内部封装的t是非空的，则返回内部的t；
        // 如果内部为空，则返回orElse()方法中的参数t1
        Girl girl3 = girl2.orElse(new Girl("name is not exist!"));
        System.out.println(girl3); // Girl{name='name is not exist!'}
    }

    // 优化以前的代码，此时如果没有初始化boy和girl容易出现空指针异常
    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    // 优化以后避免空指针的异常
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            if (boy.getGirl() != null) {
                return boy.getGirl().getName();
            }
        }

        return null;
    }

    // 使用Optional类的getGirlName2()
    public String getGirlName2(Boy boy) {

        Optional<Boy> boyOptional = Optional.ofNullable(boy);

        // Optional.orElse(T t1): 如果当前的Optional内部封装的t是非空的，则返回内部的t；
        // 如果内部为空，则返回orElse()方法中的参数t1
        // 此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("gril is not exist!")));

        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        // 此时的girl1一定非空
        Girl girl1 = girlOptional.orElse(new Girl("our name"));

        return girl1.getName();
    }

    @Test
    public void test02() {
        Boy boy = new Boy();
        // String name = getGirlName(boy); // 此时会报空指针异常
        String name = getGirlName1(boy); // name = null
        System.out.println(name);

        String name2 = getGirlName2(boy);
        System.out.println(name2); // our name

        String name3 = getGirlName2(null);
        System.out.println(name3); // gril is not exist!

        String name4 = getGirlName(new Boy(new Girl("jack")));
        System.out.println(name4); // jack
    }

}
