# springCloud 的服务注册相关

## 项目结构

```
spring-cloud-service
  ├── cloud-service-demo                     # 通过这个demo实例，引出微服务下的问题
  │   ├── order-service-t                    # 简单的 order 服务
  │   └── user-service-t                     # 简单的 user  服务
  ├── cloud-eureka                           
  │   ├── eureka-client-order                # Eureka order 服务调用端
  │   └── eureka-client-user                 # Eureka user 服务提供端
  │   └── eureka-service                     # Eureka 注册中心
  ├── cloud-feign                            
  │   ├── feign-client                       # 提供 公共类以及公共的接口方法
  │   ├── feign-order                        # 调用 client 端的方法
  ├── cloud-gateway                          # gateway 的简单使用--服务的入口
  ├── cloud-nacos                            
  │   ├── 1order-register                    # 在 nacos 中注册 order 服务
  │   ├── 1user-register                     # 在 nacos 中注册 user 服务
  │   ├── 2user-properties                   # 在 nacos 中操作配置文件
```