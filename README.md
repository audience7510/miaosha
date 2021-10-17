# miaosha
### 1、Nacos注册与配置中心
#### 1.1配置nacos集群，通过Nginx转发到nacos集群。nacos三台，Nginx三台，mysql主从  
配置nacos集群步骤：[点击查看](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)  

### 2、Sentinel限流、熔断降级  
#### 2.1流控规则：直接、关联、链路  
Sentinel使用文档[点击查看](https://sentinelguard.io/zh-cn/docs/basic-api-resource-rule.html)  

直接：意思是，对QPS(每秒请求数)、线程数进行直接限制  

关联：意思是当一个请求资源达到阈值时，对关联的资源进行限制。例如支付接口请求达到了阈值，那么就对下单接口进行限制  

链路：A、B两个接口都可以请求到资源C，对A、C配置链路模式，那么当请求A达到阈值时，对C会进行限制，而大量请求B，不会对C进行限制  
#### 2.2流控效果：快速失败、Warm Up(预热)、排队等待  
快速失败：直接返回失败  

Warm Up：假设阈值设置为30，官方的加载因子是3，我们设置预热时长为5。一开始请求为30/3=10个，5s后上升为30  

排队等待：排队处理请求，阈值类型只能选择QPS，可以设置超时时间(应用场景：突发性流量，例如消息队列，突然一瞬间很多请求需要处理，又突然没有请求)  
#### 2.3熔断降级：当资源不稳定(超时或者异常比例升高)，对资源进行限制，让请求快速失败。当资源被降级之后，在接下来的时间窗口内，对该资源的调用都会自动熔断
降级规则：RT(平均响应时间ms)、异常比例、异常数
  
RT：单位统计时长（statIntervalMs）内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，若大于设置的慢调用 RT 则会再次被熔断  

异常比例：当单位统计时长（statIntervalMs）内请求数目大于设置的最小请求数目，并且异常的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%  

异常数：当单位统计时长内的异常数目超过阈值之后会自动进行熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），
若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断  

热点参数限流：队请求的资源的参数进行限流，即热点key限流，并且支持参数例外项(参数等于特定值的，可以增加阈值或减小阈值)  

系统自适应保护：系统整体的访问量进行限制，有如下五种方式  
1、Load（仅对 Linux/Unix-like 机器生效）：当系统 load1 超过阈值，且系统当前的并发线程数超过系统容量时才会触发系统保护。系统容量由系统的 maxQps * minRt 计算得出。设定参考值一般是 CPU cores * 2.5。  
2、CPU usage（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0）。  
3、RT：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。  
4、线程数：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。  
5、入口 QPS：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。  
