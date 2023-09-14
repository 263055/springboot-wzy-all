# spring cache缓存

## 1 概述

这里只是做一个简单的缓存demo

spring cache整合其它缓存框架，这里整合了

1. redis
2. caffeine
3. ehcache（暂不适配springboot 3.0）

## 2 其它参考

springboot 使用 redis guava caffeine 缓存示例：https://github.com/zheng-zy/spring-boot-redis-guava-caffeine-cache

spring cache注解简单了解：https://blog.csdn.net/zlj1217/article/details/80928122

## 3 两种redis依赖的区别：

spring-boot-starter-data-redis 以及 redisson-spring-boot-starter

这两个Redis相关的依赖项都与在Spring Boot应用程序中使用Redis有关，但它们有不同的作用和特点：

1. `spring-boot-starter-data-redis`:
    - 这是Spring Boot提供的官方依赖，用于集成Redis与Spring应用程序。它基于Spring Data Redis项目，并提供了一种方便的方式来配置和使用Redis。
    - 使用此依赖可以轻松地将Redis与Spring的数据访问层集成，以便进行缓存管理和数据存储等操作。
    - 它提供了`RedisTemplate`等Spring Data Redis的核心组件，使您能够更容易地执行与Redis相关的操作。

2. `redisson-spring-boot-starter`:
    - 这是Redisson项目提供的Spring Boot启动器，用于将Redisson集成到Spring Boot应用程序中。
    - Redisson是一个用于Java的分布式和高级的Redis客户端，它提供了一组强大的特性，如分布式锁、分布式集合、异步操作等。
    - 使用此依赖可以在Spring Boot应用程序中轻松地使用Redisson的功能，从而实现更复杂的分布式应用。

总的来说，`spring-boot-starter-data-redis`主要用于基本的Redis操作和Spring Data Redis的集成，而`redisson-spring-boot-starter`
则更侧重于提供更高级的分布式特性和功能。您应该根据您的具体需求来选择其中一个依赖项。如果您只需要基本的缓存和数据存储功能，那么`spring-boot-starter-data-redis`
可能足够了。如果您需要更多分布式功能，例如分布式锁和队列，那么可以考虑使用`redisson-spring-boot-starter`。