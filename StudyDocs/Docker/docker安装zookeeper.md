#### 下载zookeeper
```
docker pull wurstmeister/zookeeper
```
#### 启动镜像生成容器
```
docker run -d --name zookeeper -p 2181:2181 -v /etc/localtime:/etc/localtime wurstmeister/zookeeper
```
> 参数说明：
> 