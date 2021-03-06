# CodeReview

一、排版

1. 关键词(或变量)和操作符之间加一个空格。 例如:int sum= 1;（操作符和值之间有一个空格）
2. 较长的语句、表达式等要分成多行书写。
3. 相对独立的代码块与块之间加空行。 
4. 较复杂的算数表达式要加括号分清其逻辑运算顺序。
5. 代码的新行与上一行根据逻辑关系要进行适应的缩进(一个 table 键)，使排版整齐，语句易读。
6. 不允许把多个短语句写在一行中，即一行只写一条语句。
7. 函数或过程的开始、结构的定义及循环、判断等语句中的代码都要采用缩进风格。采用 TABLE 键缩进。
8. 循环、判断等语句中若有较长的表达式或语句，则要进行适应的划分。
9. 若函数或过程中的参数较长，则要进行适当的划分。
10. 避免重复使用同样的字符串。
11. 在函数体的开始、类的定义、结构的定义、 枚举的定义以及 if、for、do、while、switch、case 语句中的程序要采用大括号‘{'和‘}'界定一段程序块时，空格加‘{'， ‘}'应各独占一行 并且位于同一列，同时与引用它的语句左对齐。如：

```java
if () {
    
}
```

二、命名规范

1. 变量名、方法名首字母小写，如果名称由多个单词组成，每个单词的首字母都要大写 ，使用驼峰法。
2. 常量名全部大写，不同单词用下划线分隔。
3. 不允许出现中文命名。
4. 包名全小写。
5. 类名首字母大写，使用驼峰法。
6. 测试类以Test结尾。

三、注释规范

1. 不采用行尾注释。

2. 较复杂逻辑代码块前加必要注释。

3. 类，方法声明前加注释。

4. 属性前要加注释。

   