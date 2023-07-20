# 设计思路：
- 首先设计所有的状态及各状态下的事件
- 创建对象类
- 配置状态机machine
- 配置listener
- 写impl方法，方法里使用状态机

## 核心
1、config中配置跳转规则（事件前后状态及事件）
2、在具体处理逻辑类中写好sendEvent方法
3、不同触发事件方法中对于Message的使用（参数放在message的header中）