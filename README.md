# miaosha
###1、Nacos注册与配置中心
1.1配置nacos集群，通过Nginx转发到nacos集群。nacos三台，Nginx三台，mysql主从  
配置nacos集群步骤：[点击查看](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)  
###2、Sentinel限流、熔断、降级  
2.1流控规则：直接、关联、链路  
直接：意思是，对QPS(每秒请求数)、线程数进行直接限制  
关联：意思是当一个请求资源达到阈值时，对关联的资源进行限制。例如支付接口请求达到了阈值，那么就对下单接口进行限制  
链路：A、B两个接口都可以请求到资源C，对A、C配置链路模式，那么当请求A达到阈值时，对C会进行限制，而大量请求B，不会对C进行限制