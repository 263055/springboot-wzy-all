<h1 align="center"><a href="https://github.com/example" target="_blank">SpringBoot-wzy-All</a></h1>


## 项目简介

## 分支介绍


## 开发环境

- **JDK 17**
- **Maven 3.8 +**
- **IntelliJ IDEA ULTIMATE 2021.3 +** (*注意：务必使用 IDEA 开发，同时保证安装 `lombok` 插件*)
- **Mysql 5.7 +** (*尽量保证使用 5.7 版本以上，因为 5.7 版本加了一些新特性，同时不向下兼容。本 demo 里会尽量避免这种不兼容的地方，但还是建议尽量保证 5.7 版本以上*)
- **Springboot 3.0**

## 项目结构

```
spring-boot-demo
  ├── spring-boot-cache-caffeine                  #caffeine本地缓存
  ├── spring-boot-cache-redis                     #redis缓存
  ├── spring-boot-cache                           #spring cache缓存
  │   ├── spring-boot-caffeine-cache              #spring cache整合caffeine本地缓存
  │   └── spring-boot-redis-cache                 #spring cache整合redis缓存
  ├── spring-boot-canal                           #阿里巴巴 MySQL binlog 增量订阅&消费组件
  ├── spring-boot-elasticsearch                   #注意事项
  │   ├── spring-boot-elasticsearch-6             #elasticsearch 6.x版本
  │   └── spring-boot-elasticsearch-7             #elasticsearch 7.x版本
  ├── spring-boot-excel                           #excel相关操作
  ├── spring-boot-logback                   	  #logback日志框架
  ├── spring-boot-mq-rabbitmq                     #消息可靠投递，可靠消费概述
  │   ├── spring-boot-rabbitmq                    #rabbitmq 5大工作模式及发送应答ACK，消费手动确认ACK，死信队列等场景
  │   └── spring-boot-rabbitmq-simple             #rabbitmq 快速入门
  ├── spring-boot-mq-rocketmq                     #消息可靠投递，可靠消费概述
  │   ├── spring-boot-rocketmq                    #rocketmq 同步、异步、单向、批量、延时、顺序、事务消息
  │   └── spring-boot-rocketmq-simple             #rocketmq 快速入门
  ├── spring-boot-multi-threading                 #Spring Boot多线程
  ├── spring-boot-mybatis-plus                              #常见问题，注意事项,参考资料
  │   ├── spring-boot-mybatis-plus-demo                     #mybatis-plus 企业级应用
  │   ├── spring-boot-mybatis-plus-dynamic-datasourc-demo   #mybatis-plus 多数据源
  │   ├── spring-boot-mybatis-plus-generate                 #mybatis-plus 代码生成器，自定义模板
  │   ├── spring-boot-mybatis-plus-p6spy-demo               #mybatis-plus 整合p6spy sql分析
  │   └── spring-boot-mybatis-plus-simple                   #mybatis-plus 快速入门
  ├── spring-boot-sharding-jdbc                    #常见问题，注意事项,参考资料
  │   ├── spring-boot-sharding-jdbc-5.0.0
  │   │   ├── sharding-jdbc-5.0.0-simple-db-table  #分库分表快速入门版
  │   │   └── sharding-jdbc-5.0.0-db-table         #分库分表自定义分片规则，自定义自增主键生成器
  │   └── spring-boot-sharding-jdbc-4.1.1
  │       ├── sharding-jdbc-4.1.1-simple-db-table  #分库分表快速入门版
  │       └── sharding-jdbc-4.1.1-db-table         #分库分表自定义分片规则，自定义自增主键生成器
  ├── spring-boot-swagger                          #概述
  │   └── spring-boot-swagger-simple               #swagger快速入门版
  ├── spring-boot-validator                        #validator参数校验框架
  └── 其它  #正在补充中
```

**示例代码与概述**

- [spring-boot-cache-caffeine](spring-boot-cache-caffeine/README.md)

1.  高效本地缓存

- [spring-boot-cache-redis](spring-boot-cache-redis/README.md)

1.  Spring Boot整合redis，封装了StringRedisTemplate常用方法

- [spring-boot-cache](spring-boot-cache/README.md)

1.  spring cache 整合caffeine
2.  spring cache 整合redis

- [spring-boot-canal](spring-boot-canal/README.md)

1.  阿里巴巴 MySQL binlog 增量订阅&消费组件
2.  spring boot封装以便处理数据

- [spring-boot-elasticsearch](spring-boot-elasticsearch/README.md)

1.  elasticsearch 6.x，7.x版本示例
2.  原生elasticsearch操作
3.  Spring Data ElasticSearch操作示例,ORM操作，聚合操作

- [spring-boot-excel](spring-boot-excel/README.md)

1. 原生POI excel模板导入导出
2. 原生POI excel百万数据导出，导入
3. csv导入导出
4. easyexcel的xlsx，xls，csv格式导出，导入
5. easyexcel模板填充

- [spring-boot-logback](spring-boot-logback/README.md)

1. 日志框架

- [spring-boot-mq-rabbitmq](spring-boot-rabbitmq/README.md)

1. RabbitMQ消息可靠投递，可靠消费
2. 工作、发布订阅、路由、主题模式示例

- [spring-boot-mq-rocketmq](spring-boot-mq-rocketmq/README.md)

1. 生产者：同步、异步、单向、批量、延时、顺序、过滤、事务消息示例发送
2. 消费者：集群、广播模式示例，顺序、过滤消息示例

- [spring-boot-multi-threading](spring-boot-multi-threading/README.md)

1.  自定义线程池控制Spring Boot的多线程
2.  @Async失效原因及解决方案

- [spring-boot-mybatis-plus示例](spring-boot-mybatis-plus/README.md)

1.  mybatis-plus 快速入门
2.  mybatis-plus 代码生成器，自定义模板
3.  mybatis-plus 多租户，自动分页，乐观锁，防止全表更新与删除，动态表名，sql性能规范，自定义ID生成器，数据填充，枚举等
4.  mybatis-plus 多数据源

- [spring-boot-sharding-jdbc示例](spring-boot-sharding-jdbc/README.md)

1.  分库分表快速入门（4.1.1版本以及5.0.0版本）
2.  自定义分库分表规则，自定义自增主键
3.  与mybatis-plus整合，解决更新时触发分片规则报错问题
4.  spring-boot-starter-jooq整合sharding-jdbc常见问题及解决方案

* [spring-boot-swagger示例](spring-boot-swagger/README.md)

1. swagger文档示例

* [spring-boot-validator](spring-boot-validator/README.md)

1. validator参数校验示例
   ：如果是 fork 的朋友，同步代码的请参考：https://example.com/2018/09/18/how-to-update-the-fork-project.html

1. `git clone https://github.com/example/spring-boot-demo.git`
2. 使用 IDEA 打开 clone 下来的项目
3. 在 IDEA 中 Maven Projects 的面板导入项目根目录下 的 `pom.xml` 文件
4. Maven Projects 找不到的童鞋，可以勾上 IDEA 顶部工具栏的 View -> Tool Buttons ，然后 Maven Projects 的面板就会出现在 IDEA 的右侧
5. 找到各个 Module 的 Application 类就可以运行各个 demo 了
6. **`注意：每个 demo 均有详细的 README 配套，食用 demo 前记得先看看哦~`**
7. **`注意：运行各个 demo 之前，有些是需要事先初始化数据库数据的，亲们别忘记了哦~`**

### 开源推荐

![11628591293_.pic_hd](https://static.aliyun.example.com/2021/08/14/11628591293pichd.jpg?x-oss-process=style/tag_compress)

- `JustAuth`：史上最全的整合第三方登录的开源库，https://github.com/justauth/JustAuth
- `Mica`：SpringBoot 微服务高效开发工具集，https://github.com/lets-mica/mica
- `awesome-collector`：https://github.com/P-P-X/awesome-collector
- `SpringBlade`：完整的线上解决方案(企业开发必备)，https://github.com/chillzhuang/SpringBlade
- `Pig`：宇宙最强微服务认证授权脚手架(架构师必备)，https://github.com/pigxcloud/pig

### 开发计划

查看 [TODO](./TODO.md) 文件

### 各 Module 介绍

| Module 名称                                                  | Module 介绍                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [demo-helloworld](./demo-helloworld)                         | spring-boot 的一个 helloworld                                |
| [demo-properties](./demo-properties)                         | spring-boot 读取配置文件中的内容                             |


Copyright (c) 2018 Yangkai.Shen
 