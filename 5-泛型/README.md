# 十一 泛型（标签）

1. 为什么要有泛型
2. 在集合中使用泛型
3. 自定义泛型结构
4. 泛型在继承上的体现
5. 通配符的使用
6. 泛型应用举例

note:泛型是一个类型，而不是一个变量，因此不可以使用基本数据类型（int, long, double, float, boolean, char, byte）

## 11.1 为什么要有泛型

集合容器类在设计阶段/声明阶段不能确定这个容器到底实际存的什么类型的对象，**所以在JDK1.5之前只能把元素设计为Object，JDK 1.5以后使用泛型来解决。**因为这个时候除了元素的类型不能确定，其他的部分是确定的，例如关于这个元素如何保存，如何管理等是确定的，因此此时**把元素的类型设计成一个参数，这个类型参数叫做泛型**。Collectioni<E>, List<E>, ArrayList<E>, 这个<E>就是类型参数，即泛型。



### 1. 泛型的概念

- 所谓泛型，就是允许在定义类、接口时通过一个标识标识类中的每个属性的类型或者是某个方法的返回值及参数类型。这个类型参数将在使用时（例如，继承或实现这个接口，用这个类型声明变量、创建对象时）确定（即传入实际的类型参数，也称为类型实参）。
- 从JDK 1.5以后，Java引入了“参数化类型（parameterized type)”的概念，允许我们在创建集合时再指定集合元素的类型，正如:List<String>，这表明该List只能保存字符串类型的对象。
- JDK 1.5改写了集合框架中的全部接口和类，为这些接口、类增加了泛型的支持，从而可以在声明集合变量、创建集合对象时传入类型实参。

### 2. 为什么要有泛型(Generic)

为什么要有泛型呢，直接Object不是也可以存储数据吗？

1. 解决元素存储的安全性问题，好比商品、药品标签，不会弄错。
2. 解决获取数据元素时，需要类型强制转换的问题，好比不用次拿商品、药品都要辨别。



- 在集合中没有泛型时：

<img src="D:\Program Files (x86)\JavaProject\2-Java高级部分\5-泛型\README.assets\image-20220304152605239.png" alt="image-20220304152605239" style="zoom:67%;" />

- 在集合中使用泛型时：
  - 集合接口或集合类在JDK5.0时都修改为带泛型的结构。
  - 在实例化集合类时，可以指明具体的泛型类型。
  - 指明后，在集合类或者接口中凡是定义类或接口时，内部结构使用到类的泛型的位置，都指定为实例化的泛型类型，比如：add(E e) -->  实例化以后 add(Integer e)
  - **注意：泛型的类型必须是类，不能是基本数据类型。需要用到的基本数据类型的位置，拿包装类替换**
  - **如果实例化时没有指定泛型，则默认为java.lang.Object类型的**
  - 如何自定义泛型结构：泛型类、泛型接口；泛型方法

### 3. 注意事项

- 如果定义了泛型类，实例化没有指明类的泛型，则认为泛型类型为Object类型
- 要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
- **如果子类在继承带泛型的父类时，若指明了泛型类型，则实例化对象时，不再需要指明泛型结构**。



## 11.2 自定义泛型结构：泛型类、泛型接口

1. 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：<E1, E2, E3>

2. 泛型类的构造器如下： public GenericClass() {}

   此时是错误的： public GenericClass<E>(){}

3. 实例化后，操作原来泛型位置的结构必须与制定的泛型类型一致。

4. 泛型不同的引用不能相互赋值。

   尽管在编译时ArrayList<String>和ArrayList<Integer>是两种类型，但是，在运行时只有一个ArrayList被加载到JVM中。

5. 泛型如果不指定，将被擦除，泛型对应的类型均按照Object处理，单不等价于Object。**经验：泛型要使用一路都用。要不用，一路都不用**。

6. 如果泛型类是一个接口或者抽象类，则不可创建泛型类的对象。

7. jdk1.7中，泛型的简化操作：ArrayList<Fruit> flist = new ArrayList<>();

8. 泛型的指定中不能使用基本数据类型，但是可以使用包装类。

9. 在类/接口上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、非静态方法的参数类型、非静态方法的返回值类型。**但在静态方法中不能使用类的泛型（因为静态方法的调用（编译时）要早于泛型对象的创建（实例化对象时））。**

10. 异常类不能是泛型的

11. 不能使用new E[]。但是可以使用：E[] elements = (E[])new Object[capacity];

    参考：ArrayList源码中的声明，Object[] elementData，而非泛型参数类型数组。

12. 父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型：

    1. 子类不保留父类的泛型: 按需实现

       - 没有类型 擦除
       - 具体类型

    2. 子类保留父类的泛型：泛型子类

       - 全部保留
       - 部分保留

    3. 结论：子类必须是“富二代”，子类除了指定货保留父类的泛型，还可以增加自己的泛型。

    4. ```java
       class Father<T1, T2> {
       
       }
       
       // 子类不保留父类的泛型
       // 1) 没有类型，擦除
       class Son<A, B> extends Father {} // 等价于class Son extends Father<Object, object> {}
       
       // 2) 具体类型
       class Son2<A, B> extends Father<Integer, String>{}
       
       // 子类保留父类的泛型
       // 1) 全部保留
       class Son3<T1, T2, A, B> extends Father<T1, T2> {}
       
       // 2) 部分保留 
       class Son4<T2, A, B> extends Father<Integer, T2> {}
       ```

       

## 11.3 泛型方法

- 例如：`<T> T[] toArray(T[] a);`

- 泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。

- 换句话说，泛型方法所属的类是不是泛型没有关系

- **note：泛型方法，可以声明为静态的。原因是：泛型参数在调用方法时确定的，并非在实例化类的对象时所确定的。**

  ```java
  public class SubOrder<T> extends Order<T> {
  	
  	// 泛型方法：
  	public <E> List<E> copyFromArrayToList(E[] arr) {
  		ArrayList<E> list = new ArrayList<>();
  		
  		for (E e : arr) {
  			list.add(e);
  		}
  		
  		return list;
  	}
      
      // 静态泛型方法：
  	public static <E> List<E> copyFromArrayToList2(E[] arr) {
  		ArrayList<E> list = new ArrayList<>();
  		
  		for (E e : arr) {
  			list.add(e);
  		}
  		
  		return list;
  	}
      
  }
  
  @Test
  public void test01() {
      Suborder<String> suborder1 = new SubOrder<>();
      Integer[] arr = new Integer[]{1, 2, 3, 4};
      // 泛型方法在调用时，需要指明泛型参数的类型
      List<Integer> list = suborder1.copyFromArrayToList(arr);
      System.out.println(list); // [1, 2, 3, 4]
  }
  ```

  

## 11.4 泛型方法的使用场景

如要对多个类似于的数据进行操作，如不同的数据库之间，数据库之间的不同表格，此时可以通过创建泛型创建一个通用的方法，然后分别让子类实现想要实现的功能。



## 11.5 泛型在继承上的体现

1. 若类A是类B的父类，则G<A> 和 G<B>两者不具备子父类关系，二者是并列关系

2. 若类A是类B的父类，则A<G> B<G>两者具备子父类关系

   ```java
   public void test01() {
   	List<Object> list1 = null;
   	List<String> list2 = null;
   	
   	show(list1); // right
   	show(list2); // error
   	show1(list2); // right
   }
   
   public void show(List<Object> list) {
   	
   }
   
   public void show1(List<String> list1) {
   
   }
   
   public void test02() {
       List<String> list1 = null;
       ArrayList<String> list2 = null;
       
       list1 = list2; // right, 相当于 List<String> list = new ArrayList<String>();
   }
   ```

   

### 1. 如果两个变量属于子父类关系，且这两个变量中null，则可以把子类赋给父类的变量：

```java
public void test01(){
	Object obj = null;
	String str = null;
	obj = str; // right
	str = obj; // error
	
	Object[] arr1 = null;
	String[] arr2 = null;
	arr1 = arr2; // right
	
	List<Object> list1 = null;
	List<String> list2 = null;
	list1 = list2; // error, 此时的list1和list2的类型不具有子父类关系
	/* 若list1 = list2, 则此时list1.add(123)会添加成功，因此导致String中存入非String的数据，因此不可以赋值*/
	
	
}


```



### 2. 通配符的使用

- 通配符：？

- 如类A是类B的父类，G<A> 和 G<B> 是没有关系的，两者共同的父类是: G<?> 

- 获取（读取）：允许读取数据，读取的数据类型为Object

- 对于List<?>就不能向其内部添加数据，除了null之外：

  list.add("DD"); // error

  list .add(null); // right

- ```java
  /** 通配符的使用 */
  @Test
  public void test03() {
      List<Object> list1 = null;
      List<String> list2 = null;
  
      List<?> list = null;
      list = list1; // right
      list = list2; // right
  
      print(list1); // right
      print(list2); // right
      
      List<String> list3 = new ArrayList<>();
          list3.add("AA");
          list3.add("BB");
          list3.add("DD");
          list3.add("CC");
  
          list = list3;
          // 1. 对于List<?>就不能向其内部添加数据，除了null之外：
          // list.add("EE"); // error
          list.add(null); // right
  
          Object o = list.get(0);
          System.out.println(o); // AA
          System.out.println("----------------------------");
          print(list); // right: AA BB DD CC null
  }
  
  public void print(List<?> list) {
      Iterator<?> iterator = list.iterator();
      while (iterator.hasNext()) {
          Object obj = iterator.next();
          System.out.println(obj);
      }
  }
  ```

  

### 1. 使用通配符后数据的读取和写入要求

