### 1-1 Spring Boot产生的背景和它的设计理念
* Rob Johnson
* Pivotal
* Spring->Spring Boot，约定大于配置
* Starters
* Spring, Spring Boot, Spring Cloud

### 1-2 Spring Boot 2.0之更新 - 上
* 基础环境升级
    * 最低JDK8， Jetty9.4， Tomcat8.5，Flyway5，Hibernate5.2， Gradle3.4， Thymeleaf 3.0
* 默认组件升级
    * HikariCP - JDBC 连接池
    * Security - 与Shiro对比
    * OAuth2.0 - 并不向后兼容OAuth1.0
    * Micrometer - 一款监控指标的度量类库
    * Redis - 使用Lettuce取代Jedis
    * Actuator - 改进Actuator endpoints
    * 测试 - @WebFluxTest, Converter和GenericConverter beans， @AutoConfigureWebTestClient, ApplicationContextRunner自动测试等
    * 其他一些微小的调整和改进
* Spring Boot2.0引入的新技术
    * HTTP/2
    * 内嵌的Netty服务器
    * 支持Kotlin
    * 支持JOOQ
    * 支持Quartz
    * 响应式编程 - WebFlux

### 1-2 Spring Boot 2.0之更新 - 下
> 彩蛋 - Spring Boot 2.0支持动态Gif启动Logo打印
* 2.0之API变化
    * SpringBootServletInitializer - 部署到Tomcat与1.0有所区别
    * 建议使用slf4j，而不是log4j（默认也不包含）
    * Thymeleaf 3.0默认不包含布局模块，使用Thymeleaf需要单独添加
    * 配置文件Servlet目录变更
    * WebMvcConfigurerAdapter接口替代原先的WebMvcConfigurerAdapter类
    * SpringBoot JPA的变化 - 去掉xxxOne()方法，改为xxxById()；需指定主键自增策略；PageRequest变化；JPA关联查询，如果resultset为空2.0会直接报空指针异常