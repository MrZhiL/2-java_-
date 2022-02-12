## 这里对String类的常用方法进行介绍。

- 常用方法1

  ```
  ing length(): 返回字符串的长度： return value.length
  char charAt(int index): 返回某索引处的字符return value[index]
  boolean isEmpty(): 判断是否为空字符串： return value.length == 0
  String toLowerCase(): 使用默认语言环境，将String中的所有字符转换为小写(之前的字符串不会改变，而是返回新的字符串)
  String toUpperCase(): 使用默认语言环境，将String中的所有字符转换为大写
  String trim(): 返回字符串的副本，忽略前导空白和尾部空白
  boolean equals(Object obj): 比较字符串的内容是否相同
  boolean equalsIgnoreCase(String anotherString): 与equals方法类似，忽略大小写
  String concat(String str):将指定字符串连接到此字符串的结尾。等价于用“+”
  int compareTo(String anotherString):比较两个字符串的大小
  String substring(int beginIndex): 返回一个新的字符串，子字符串以指定索引处的字符开头，并延伸到此字符串的末尾。 
  String substring(int beginIndex, int endIndex): 返回一个新的字符串,该字符串是此字符串的子字符串。 子字符串从指定的beginIndex开始，并扩展到索引endIndex - 1处的字符。 因此子串的长度是endIndex-beginIndex 
  ```



- 常用方法2

  ```
  boolean endsWith(String suffic): 测试此字符串是否可以指定的后缀结束
  boolean startsWith(String prefix): 测试此字符串是否可以以指定的前缀开始
  boolean startsWith(String prefix, int toffset): 测试此字符串从指定索引开始的子字符串是否以指定前缀开始
  
  boolean contains(CharSequence s): 当且仅此字符串包含指定的char值序列时，返回true
  int indexOf(String str): 返回指定子字符串在此字符串中第一次出现处的索引
  int indexOf(String str, int fromIndex): 返回指定子字符串在此字符中第一此出现处的索引，从指定的索引开始
  int lastIndexOf(String str): 返回指定子字符串在此字符串最后边出现处的索引
  int lastIndexOf(String str, int fromIndex): 返回指定子字符串在此字符串中最后一次出现处的索引，**从指定的索引开始返向（左）搜索**。
  
  注：indexOf和lastIndexOf方法如果未找到都是返回-1
  
  note: 什么情况下，indexOf(String str)和lastIndexOf(String str)返回相同：1. 只存在唯一一个str；2.不存在str
  
  ```

  ```java
  System.out.println(new String("helloworld").lastIndexOf("llo", 0)); // -1
          System.out.println(new String("helloworld").lastIndexOf("llo", 1)); // -1
          // note: 下面会输出2,可能是因为此时我们从索引2开始，helloworld的索引2为l，与"llo"的第一个元素相同，此时会继续往后（右边）寻找，知道匹配完成
          System.out.println(new String("helloworld").lastIndexOf("llo", 2)); // 2
  ```



- 常用方法3

  ```
  String replace(char oldChar, char newChar): 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
  String replace(CharSequence target, CharSequence replacement): 使用指定的字母值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
  String replaceAll(String regex, String replacement): 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
  String replaceFirst(String regex, String replacement): 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
  
  boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式
  
  String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
  String[] split(String regex, int limit): 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
  ```

  

