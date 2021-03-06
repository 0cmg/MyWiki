Nginx 是一个高性能的 HTTP 和反向代理 web 服务器，同时也提供了 IMAP/POP3/SMTP 服务 。

### 1、查看可用的 Nginx版本

访问 Nginx 镜像库地址： https://hub.docker.com/_/nginx?tab=tags。

可以通过 Sort by 查看其他版本的 Nginx，默认是最新版本 **nginx:latest**。

此外，我们还可以用 **docker search nginx** 命令来查看可用版本：

### 2、取最新版的 Nginx镜像
这里我们拉取官方的最新版本的镜像：

```bash
sudo docker pull nginx:latest
```

### 3、查看本地镜像
使用以下命令来查看是否已安装了 nginx：

```bash
sudo docker images
```

### 4、运行容器
安装完成后，我们可以使用以下命令来运行 nginx 容器：

```bash
docker run -itd --name nginx-lastest -p 8088:80 nginx
```

参数说明：

+ **--name nginx-lastest**：容器名称。
+ **-p 8088:80**： 端口进行映射，将本地 8080 端口映射到容器内部的 80 端口。
+ **-d nginx**： 设置容器在在后台一直运行。

### 5、安装成功
最后我们可以通过 docker ps 命令查看容器的运行信息：  

最后我们可以通过浏览器可以直接访问 8088 端口的 nginx 服务：

