# 概述
## 安装
官方安装文档地址  
https://docs.docker.com/engine/installation/linux/centos/  
### 安装必须的软件包  
&emsp;&emsp;安装yum-utils，它提供一个yum-config-manager单元，同时安装的device-mapper-persistent-data和lvm2用于储存设备映射（devicemapper）必须的两个软件包。  
```bash
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```  
紧接着配置一个稳定（stable）的仓库  
仓库配置会保存到/etc/yum.repos.d/docker-ce.repo文件中。    
```bash
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo    
```  
更新Yum安装的相关Docke软件包     
```bash
sudo yum makecache fast    
```  
### 安装Docker CE
安装Docker CE命令为：  
```bash  
sudo yum install docker-ce  
```  
安装Docker EE的命令为：  
```bash
sudo yum install docker-ee  
```
### 设置随系统启动  
```bash
systemctl enable docker.service  
```
### 启动Docker  
```bash
sudo systemctl start docker  
```
### 查看Docker版本  
正确显示客户端（client）和服务器（server）表示Docker安装成功。  
```bash
docker version
```
Client:
 Version:      17.03.1-ce
 API version:  1.27
 Go version:   go1.7.5
 Git commit:   c6d412e
 Built:        Mon Mar 27 17:05:44 2017
 OS/Arch:      linux/amd64

Server:
 Version:      17.03.1-ce
 API version:  1.27 (minimum version 1.12)
 Go version:   go1.7.5
 Git commit:   c6d412e
 Built:        Mon Mar 27 17:05:44 2017
 OS/Arch:      linux/amd64
 Experimental: false

### 卸载Docker
查询已经安装的Docker包  
```bash
yum list installed | grep docker  
```   
docker-ce.x86_64                     17.12.0.ce-1.el7.centos        @docker-ce-stable  
删除Yum中的Docker软件包    
```bash
sudo yum remove docker-ce.x86_64    
```  
删除Docker相关的所有镜像、容器、自定义配置等一系列文件    
```bash
sudo rm -rf /var/lib/docker  
```