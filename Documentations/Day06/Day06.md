### Thymeleaf基本用法
* Thymeleaf 使用了 XML DOM 解析器，因此它并不适合于处理大规模的 XML 文件。
* 作用域在 HTML 标签内
* K-V 传值
* ModelMap
* 条件判断if/Unless
* for 循环
    * 状态变量
* URL
* 三目运算
* switch
### Thymeleaf 高级用法
* 内联 - 比较简洁
* Thymeleaf 基本对象，以#开头
* 内嵌变量 - 内置于Context中的一系列Utility对象
* 表达式
    * 变量表达式 ${...}
    * 选择或者星标表达式 *{...}
    * 文字国际化表达式 #{...}
    * URL表达式 @{...}
    * 片段表达式 ~{...}
* 支持表达式的各种语法
    * 字面
    * 文本操作
    * 算术运算
    * 布尔操作
    * 比较和等价
    * 条件运算符
* th标签常用关键字 - 对应几乎所有html的标签
* application.properties中Thymeleaf的配置
### Thymeleaf 页面布局
* 引入依赖thymeleaf-layour-dialect
* th:insert
* 片段表达式
* 页面布局 - 头部，左侧菜单栏，尾部，中间展示区