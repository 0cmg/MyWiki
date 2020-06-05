## **Docker下redis的主从、持久化配置**

redis是k-v型nosql数据库，支持字符串（string）、列表（list）、集合（set）、散列（hash）、有序集合（zset：形如member：score的散列集合，其中member为成员，score为成员得分，必须为float型数据）。

综合使用redis的以上5种数据类型，可以将redis应用于各种场景，比如点赞、投票网站、消息队列、分布式锁（使用setnx指令，该指令只有在key不存在的时候，才会执行写入操作）、文件分发（没研究过）、日志记录等等。

redis支持主从配置（拓展读性能，主机负责读取、写入，从机只负责读取，主机自动向从机以rdb快照形式同步数据）、持久化配置（支持rdb快照持久化、aof持久化，旧版redis不支持同时配置两种持久化方式，新版的貌似可以）、发布订阅（pub、sub）、事务、流水线pipeline（减少客户端和服务端往返连接次数从而提高性能）、客户端分片（拓展写性能）等。

本文主要记录下redis中的主从配置及持久化操作

#### **1.下载redis镜像**

镜像中心

推荐使用国内daocloud镜像
 镜像地址：daocloud.io/library/

拉取redis镜像

```
docker pull daocloud.io/library/redis:latest
```

查看拉取的镜像

```
docker images
```

#### **2.搭建redis集群**

#####  **2.1运行redis镜像**

首先使用docker启动3个redis容器服务，分别使用到6379、6380、6381端口

```
docker run --name redis-6379 -p 6379:6379 -d daocloud.io/library/redis
docker run --name redis-6380 -p 6380:6379 -d daocloud.io/library/redis
docker run --name redis-6381 -p 6381:6379 -d daocloud.io/library/redis

```

查看运行

```
docker ps
```

<!--![例图1](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(1).png)-->

![例图1](https://s1.ax1x.com/2020/06/05/tr9Hot.png)

##### 2.2配置redis集群

使用如下命令查看容器内网的ip地址等信息

```
docker inspect containerid
```

<!--![例图2](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(2).png)-->

![例图2](https://s1.ax1x.com/2020/06/05/tr9ILd.png)

3个redis的内网ip地址为：

redis-6379：172.17.0.2:6379
 redis-6380：172.17.0.3:6379
 redis-6381：172.17.0.4:6379

进入docker容器内部，查看当前redis角色（主还是从）

<!--![例图3](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(3).png)-->

![例图3](https://s1.ax1x.com/2020/06/05/tr9TeA.png)

可以看到当前3台redis都是master角色，使用redis-cli命令修改redis-6380、redis-6381的主机为172.17.0.2:6379

SLAVEOF host port // SLAVEOF 172.17.0.2 6379

查看redis-6379是否已经拥有2个从机：

<!--![例图4](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(4).png)-->

![例图4](https://s1.ax1x.com/2020/06/05/tr97dI.png)

至此，redis下的主从配置就ok了。

#### **3.配置Sentinel哨兵**

Redis 的 Sentinel 系统用于管理多个 Redis 服务器（instance）， 该系统执行以下三个任务：

监控（Monitoring）： Sentinel 会不断地检查你的主服务器和从服务器是否运作正常。
 提醒（Notification）： 当被监控的某个 Redis 服务器出现问题时， Sentinel 可以通过 API 向管理员或者其他应用程序发送通知。
 自动故障迁移（Automatic failover）： 当一个主服务器不能正常工作时， Sentinel 会开始一次自动故障迁移操作， 它会将失效主服务器的其中一个从服务器升级为新的主服务器， 并让失效主服务器的其他从服务器改为复制新的主服务器； 当客户端试图连接失效的主服务器时， 集群也会向客户端返回新主服务器的地址， 使得集群可以使用新主服务器代替失效服务器。

具体介绍可参考：http://redisdoc.com/topic/sentinel.html

接下来直接进入3台redis容器内部进行配置

docker exec -ti 容器id /bin/bash

进入根目录创建sentinel.conf文件

cd / && touch sentinel.conf

修改文件内容为：

sentinel monitor mymaster 172.17.0.2 6379 1

最后，启动Redis哨兵：

使用 redis-sentinel /sentinel.conf 启动Redis哨兵监控
 使用 ps –ef |grep redis 命令，可以看到redis-server和redis-sentinel正在运行

至此，Sentinel哨兵配置完毕。

##### **3.1测试验证**

首先查看哨兵监控情况

<!--![例图5](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(5).png)-->

![例图5](https://s1.ax1x.com/2020/06/05/tr95sH.png)

然后尝试关闭主机

<!--![例图6](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(6).png)-->

![例图6](https://s1.ax1x.com/2020/06/05/tr9qFP.png)

再查看剩余2个从机，这里会自动选举产生新的主机

<!--![例图7](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(7).png)-->

![例图7](https://s1.ax1x.com/2020/06/05/tr9LJf.png)

然后，我们再次将刚才停止的主机启动起来，发现启动后其自动成为从机

<!--![例图8](C:\workspace\Coding\MyWiki\images\Redis\redis-docker(8).png)-->

![例图8](https://s1.ax1x.com/2020/06/05/tr9OW8.png)

至此，redis的主从高可用模式已经全部配置完毕，持久化下次研究了再过来记录吧，over...