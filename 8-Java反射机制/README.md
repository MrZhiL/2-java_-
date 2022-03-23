## 13. Java反射机制

1. Java反射机制概述
2. 理解Class类并获取Class实例
3. 类的加载与ClassLoader的理解
4. 创建运行时类的对象
5. 获取运行时类的完整结构
6. 调用运行时类的指定结构
7. 反射的应用：动态代理

### 1. Java反射机制概述
- Reflection(反射) 是被视为动态语言的关键，反射机制允许程序在执行期借助于 Reflection API 取得任何类的内部信息，
并能直接操作任意对象的内部属性及方法。
- 加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类
的结构信息。我们可以通过这个对象看到类的结构。**这个对象就像一面镜子，透过这个镜子看到类的结构，所以，我们形象的称之为：反射**

- ![img.png](README.assert/img.png)

#### 1.1 Java反射机制研究及应用

- Java反射机制提供的功能
  - 在运行时判断任意一个对象所属的类
  - 在运行时构造任意一个类的对象
  - 在运行时判断任意一个类所具有的成员变量和方法
  - 在运行时获取泛型信息
  - 在运行时调用任意一个对象的成员变量和方法
  - 在运行时处理注解
  - 生产动态代理

#### 1.2 反射相关的主要API
- `java.lang.Class`: 代表一个类, 用来描述类的类
- `java.lang.reflect.Method`: 代表类的方法
- `java.lang.reflect.Field`: 代表类的成员变量
- `java.lang.reflect.Constructor`: 代表类的构造器
- ...
 
### 补充：动态语言 和 静态语言
1. 动态语言：
    - 是一类在运行时可以改变其结构的语言：例如新的函数、对象、甚至代码可以被引进，
   已有的函数可以被删除或是其他结构上的变化。通俗点就是在运行时可以根据某些条件改变自身结构
    - 主要动态语言：Object-C, C#, JavaScript, PHP, Python, Erlang
2. 静态语言：
    - 与动态语言相对应的，运行时结构不可变的语言就是静态语言。如Java, C, C++

3. Java不是动态语言，但Java可以称之为“准动态语言”。即Java有一定的动态性，我们可以利用反射机制、字节码操作获得
类似动态语言的特性。

    Java的动态性让编程的时候更加灵活。

### 2. 理解Class类并获取Class实例

#### 2.1 代码测试
```java
//  1. 反射之前，对于Person的操作
@Test
public void test01() {
    // 创建Person类的对象
    Person p1 = new Person("Tom", 21);

    // 通过对象，调用其内部的属性、方法
    p1.setAge(22);
    System.out.println(p1.toString());

    p1.show();

    // note: 在Person类外部，不可以通过Person类的对象调用其内部私有结构。
    // 比如：name、showNation()以及私有的构造器
}

// 2. 使用反射之后，对于Person的操作
@Test
public void test02() throws Exception{
    // 2.1 通过反射来创建Person类对象
    Class classPerson = Person.class;
    Constructor cons = classPerson.getConstructor(String.class, int.class);
    Object obj = cons.newInstance("Tom", 21);
    Person p = (Person) obj;
    p.setAge(22);
    System.out.println(p.toString());


    // 2.2 通过反射，调用对象指定的属性、方法
    // 调用属性getDeclaredField, 只能调用非私有化的属性和方法
    Field age = classPerson.getDeclaredField("age");
    age.set(p, 20);     // 调用Field的set()方法来设置指定属性
    System.out.println(p.toString());

    // 调用方法getDeclaredMethod：只能调用非私有化的方法
    Method method = classPerson.getDeclaredMethod("show");
    method.invoke(p);   // 通过调用Method的invoke()方法来调用类的方法
}
```


### 3. 类的加载与ClassLoader的理解


### 4. 创建运行时类的对象


### 5. 获取运行时类的完整结构


### 6. 调用运行时类的指定结构


### 7. 反射的应用：动态代理


