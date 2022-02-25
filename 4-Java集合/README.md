# 十、Java集合

1. Java集合框架概述
2. Collection接口方法
3. Iterator迭代器接口
4. Collection子接口一：List
5. Collection子接口二：Set
6. Map接口
7. Collections工具类

## 10.1 Java集合框架概述

- 一方面，面向对象语言对事物的体现都是以对象的形式，为了方便对多个对象的操作，就要对对象进行存储。另一方面，使用Array存储对象方面具有一些**弊端**，而Java集合就像一种容器，可以**动态地**把多个对象的引用放入容器中。

    - 数组在内存存储方面的特点：
        - 数组初始化以后，其长度就确定了
        - 数组声明的类型，就决定了进行元素初始化时的类型
    - 数组在存储数据方面的弊端：
        - 数组初始化以后，长度就不可变了，不便于扩展
        - 数组中提供的属性和方法少，不便于进行添加、删除、插入等操作，且效率不高。同时无法直接获取存储元素的个数
        - 数组存储的数据时有序的、可以重复的。-->存储数据的特点单一

- Java集合类可以用于存储数量不等的多个**对象**，还可用于保存具有映射关系的关联数组。



## 10.2 Java集合体系：Collection和Map

Java集合可分为Collection和Map两种体系

- Collection接口：单列数据，定义了存取一组对象的方法的集合

    - List：元素有序、可重复的集合
    - Set：元素无序、不可重复的集合

- Map接口：双列数据，用来存储一对（key - value）的数据。

- Collection接口继承树

  ![image-20220224125143484](D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220224125143484.png)

- Map接口继承树

  ​	![image-20220224125230891](D:\Program Files (x86)\JavaProject\2-Java高级部分\4-Java集合\README.assets\image-20220224125230891.png)



## 10.3 Collection接口中的API

