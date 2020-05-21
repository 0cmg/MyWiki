较旧的 Docker 版本称为 docker 或 docker-engine 。如果已安装这些程序，请卸载它们以及相关的依赖项  
`sudo yum remove docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-engine`
### 安装 Docker Engine-Community
+ 使用 Docker 仓库进行安装
在新主机上首次安装 Docker Engine-Community 之前，需要设置 Docker 仓库。之后，您可以从仓库安装和更新 Docker。  
- 设置仓库
安装所需的软件包。yum-utils 提供了 yum-config-manager ，并且 device mapper 存储驱动程序需要 device-mapper-persistent-data 和 lvm2。  
`sudo yum install -y yum-utils device-mapper-persistent-data lvm2`  
使用以下命令来设置稳定的仓库。  
`sudo yum-config-manager --add-repo  https://download.docker.com/linux/centos/docker-ce.repo`  
安装 Docker Engine-Community  
安装最新版本的 Docker Engine-Community 和 containerd，或者转到下一步安装特定版本：  
`sudo yum install docker-ce docker-ce-cli containerd.io`  
### 启动 Docker。
`sudo systemctl start docker`
### 停止Docker
`sudo systemctl stop docker`
### 查看docker容器日志
`docker logs amazing_cori`
### 容器使用
+ 启动容器
以下命令使用 ubuntu 镜像启动一个容器，参数为以命令行模式进入该容器：    
`docker run -it ubuntu /bin/bash`  
>参数说明：  
>>• -i: 交互式操作。  
>>• -t: 终端。  
>>• ubuntu: ubuntu 镜像。  
>>• /bin/bash：放在镜像名后的是命令，这里我们希望有个交互式 Shell，因此用的是 /bin/bash。  
要退出终端，直接输入 exit:
### 启动已停止运行的容器
查看所有的容器命令如下：  
`docker ps -a`  
使用 docker start 启动一个已停止的容器：  
`docker start b750bbbcfd88`  

### 后台运行
在大部分的场景下，我们希望 docker 的服务是在后台运行的，我们可以过 -d 指定容器的运行模式。  
`docker run -itd --name ubuntu-test ubuntu /bin/bash`  
>注：加了 -d 参数默认不会进入容器，想要进入容器需要使用指令 docker exec（下面会介绍到）。    

+ 停止一个容器
>停止容器的命令如下：  
`$ docker stop <容器 ID>`  
停止的容器可以通过 docker restart 重启：  
`docker restart <容器 ID>`
- 进入容器
>在使用 -d 参数时，容器启动后会进入后台。此时想要进入容器，可以通过以下指令进入：  
• `docker attach`  
• `docker exec`：推荐大家使用 docker exec 命令，因为此退出容器终端，不会导致容器的停止。  
attach 命令  
下面演示了使用 docker attach 命令。  
`docker attach 1e560fca3906`  
注意： 如果从这个容器退出，会导致容器的停止。  
exec 命令  
下面演示了使用 docker exec 命令。  
`docker exec -it 243c32535da7 /bin/bash`  
注意： 如果从这个容器退出，不会导致容器的停止，这就是为什么推荐大家使用 docker exec 的原因。
更多参数说明请使用 docker exec --help 命令查看。

### 导出和导入容器
+ 导出容器  
如果要导出本地某个容器，可以使用 docker export 命令。  
`docker export 1e560fca3906 > ubuntu.tar`  
导出容器 1e560fca3906 快照到本地文件 ubuntu.tar。  
这样将导出容器快照到本地文件。
+ 导入容器快照  
可以使用 docker import 从容器快照文件中再导入为镜像，以下实例将快照文件 ubuntu.tar 导入到镜像 test/ubuntu:v1:  
`cat docker/ubuntu.tar | docker import - test/ubuntu:v1`  
此外，也可以通过指定 URL 或者某个目录来导入，例如：  
`docker import http://example.com/exampleimage.tgz example/imagerepo`  
* 删除容器  
删除容器使用 docker rm 命令：     
`docker rm -f 1e560fca3906`  
下面的命令可以清理掉所有处于终止状态的容器  
`docker container prune`  