## Spring Boot应用JDBC
* IDEA上查看Diagram依赖关系
* 数据使用UTC - 使用世界时间标准的原因是因为要国际化吗？
* 约定大于配置，实体类中attributes和DB中的fields对应关系：
    * Long 对应 bigint
    * String 对应 varchar
    * int 对应 int
* JdbcTemplate的用法，依赖注入后，直接把sql作为返回函数的参数
## 多数据源
* 熟悉从多数据源根据配置，对不同数据源增删改查的各种操作
