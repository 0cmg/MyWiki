### 1、查看可用的 RabbitMQ版本

> Docker系列之RabbitMQ安装部署教程
>
> 因为学习RabbitMQ需要，需要安装RabbitMQ，网上找资料，RabbitMQ官方提供了window版、Linux版、Docker版的管理页面，为了方便，选择了docker版本进行学习
>
> 主要介绍一下Docker版本，常用的docker镜像操作： 

![2021-05-17-1](C:\workspace\Coding\MyWiki\images\Docker\rabbitmq\2021-05-17-1.png)

> 查询rabbitMQ镜像：
>
> management版本，不指定默认为最新版本latest

```bash
sudo docker search rabbitmq:management
```

![2021-05-17-2](C:\workspace\Coding\MyWiki\images\Docker\rabbitmq\2021-05-17-2.png)

 拉取镜像：

```bash
sudo docker pull rabbitmq:management
```

查看docker镜像列表：

```bash
sudo docker images
```

Docker容器操作： ok，上面命令执行后，镜像就已经拉取到本地仓库了，然后可以进行容器操作，启动rabbitMQ

简单版

```bash
sudo docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
```

- -d 后台运行
- -p 隐射端口
- –name 指定rabbitMQ名称

复杂版（设置账户密码，hostname）

```bash
sudo docker run -d -p 15672:15672  -p  5672:5672  -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin --name rabbitmq --hostname=rabbitmqhostone  rabbitmq:management
```

- -d 后台运行
- -p 隐射端口
- –name 指定rabbitMQ名称
- RABBITMQ_DEFAULT_USER 指定用户账号
- RABBITMQ_DEFAULT_PASS 指定账号密码

执行如上命令后访问：http://ip:15672/

默认账号密码：guest/guest

![2021-05-17-3](C:\workspace\Coding\MyWiki\images\Docker\rabbitmq\2021-05-17-3.png)

![2021-05-17-4](C:\workspace\Coding\MyWiki\images\Docker\rabbitmq\2021-05-17-4.png)

 其它常用容器命令：

查看运行中的容器

```bash
# 查看所有的容器用命令docker ps -a
sudo docker ps
```

启动容器

```bash
# eg: docker start 9781cb2e64bd
sudo docker start CONTAINERID[容器ID]
```

stop容器

```bash
sudo docker stop CONTAINERID[容器ID]
```

删除一个容器

```bash
 sudo docker rm CONTAINERID[容器ID]
```

查看Docker容器日志

```bash
# eg：docker logs 9781cb2e64bd
sudo docker logs container‐name[容器名]/container‐id[容器ID]
```



### 安装完成后15672端口访问不通
如果15672端口访问不进去，则需要打开cmd窗口，进入RabbitMq的安装路径下的sbin文件夹

```bash
运行: ./rabbitmq-plugins enable rabbitmq_management
```

再次访问就可以进入RabbitMq的Web界面了


