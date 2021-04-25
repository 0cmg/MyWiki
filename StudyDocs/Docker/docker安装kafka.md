#### 下载kafka镜像
kafka需要zookeeper管理，所以需要先安装zookeeper。zookeeper安装请看"docker安装zookeeper.md"
```
docker pull wurstmeister/kafka
```
#### 启动kafka镜像生成容器
```
docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=192.168.155.56:2181/kafka -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.155.56:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -v /etc/localtime:/etc/localtime wurstmeister/kafka
```
> 参数说明：
>+ -e KAFKA_BROKER_ID=0  在kafka集群中，每个kafka都有一个BROKER_ID来区分自己
>+ -e KAFKA_ZOOKEEPER_CONNECT=192.168.155.56:2181/kafka 配置zookeeper管理kafka的路径192.168.155.56:2181/kafka
>+ -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.155.56:9092  把kafka的地址端口注册给zookeeper
>+ -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 配置kafka的监听端口
>+ -v /etc/localtime:/etc/localtime 容器时间同步虚拟机的时间
> 注意：参数中的IP必须是zookeeper容器的实际IP，否则kafka启动后会自动停止

#### 验证kafka是否可以使用
+ 进入容器
```
docker exec -it kafka /bin/sh
#进入路径
/opt/kafka_2.11-2.0.0/bin下
```
+ 运行kafka生产者发送消息
```
../../kafka-console-producer.sh --broker-list localhost:9092 --topic sun
```
发送消息
```
{"datas":[{"channel":"","metric":"temperature","producer":"ijinus","sn":"IJA0101-00002245","time":"1543207156000","value":"80"}],"ver":"1.0"}
```
运行kafka消费者接收消息
```
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sun --from-beginning
```
