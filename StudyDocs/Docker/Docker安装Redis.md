### 1、查看可用的 Redis 版本
访问 Redis 镜像库地址： https://hub.docker.com/_/redis?tab=tags。
可以通过 Sort by 查看其他版本的 Redis，默认是最新版本 redis:latest。

### 2、取最新版的 Redis 镜像
这里我们拉取官方的最新版本的镜像：

```bash
docker pull redis:latest
```

### 3、查看本地镜像
使用以下命令来查看是否已安装了 redis：

```bash
docker images
```

### 4、运行容器
安装完成后，我们可以使用以下命令来运行 redis 容器：

```bash
docker run -itd --name redis-test -p 6379:6379 redis
```

参数说明：

+ -p 6379:6379：映射容器服务的 6379 端口到宿主机的 6379 端口。外部可以直接通过宿主机ip:6379 访问到 Redis 的服务。

### 5、安装成功
最后我们可以通过 docker ps 命令查看容器的运行信息：  

接着我们通过 redis-cli 连接测试使用 redis 服务。  

```bash
docker exec -it redis-test /bin/bash
```

