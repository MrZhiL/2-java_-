## 9. Java比较器

Java对象中，正常情况下，只能进行比较：== 或 !=。不能使用 > 或 < 的。

但是在开发场景中，我们需要对多个对象进行排序，言外之意，就需要比较对象的大小。

如何实现？使用两个接口中的任何一个：Comparable 或 Comparator

### 1. Comparable接口的使用举例：（自然排序）

1. 像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象的实现方式
2. 像String、包装类重写compareTo(obj)方法以后，默认为进行了从小到大的排序
3. 重写compareTo(obj)的规则：
   - 如果当前对象this小于形参对象obj，则返回正整数
   - 如果当前对象this大于形参对象obj，则返回负整数
   - 如果当前对象this等于形参对象obj，则返回零。
4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Compareble类，重写compareTo()方法并指明如何排序

```java
public void test01() {
    // String类的sort排序
    String[] arr = {"KKK", "AAA", "CC", "II", "ZZ", "DD", "GG"};

    Arrays.sort(arr); // [AAA, CC, DD, GG, II, KKK, ZZ]

    System.out.println(Arrays.toString(arr));
}

public void test02() {
    Goods[] goods = new Goods[5];
    goods[0] = new Goods("xiaomi Phone", 1999);
    goods[1] = new Goods("Huawei Phone", 2999);
    goods[2] = new Goods("Lenovo Phone", 1999);
    goods[3] = new Goods("Oppo Phone", 2400);
    goods[4] = new Goods("vivo Phone", 2599);

    for (int i = 0; i < goods.length; ++i) {
        System.out.println(goods[i].getName() + ", " + goods[i].getPrice());
    }
    System.out.println(Arrays.toString(goods));

    Arrays.sort(goods);
    System.out.println("After sort");
    for (int i = 0; i < goods.length; ++i) {
        System.out.println(goods[i].getName() + ", " + goods[i].getPrice());
    }
    System.out.println(Arrays.deepToString(goods));
}
```

