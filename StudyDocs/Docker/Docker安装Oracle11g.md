## Docker 拉取 oracle 11g镜像配置
开始记录docker拉取阿里的oracle11g 镜像并进行配置，用pl/sql 可以登录为最终结果
navicat连接是在最后一步
参考：https://blog.csdn.net/zwx521515/article/details/77982884
#### 开始：
##### ①、开始拉取镜像-执行命令：
```bash
docker pull registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```
下载的过程少长，等待吧，喝杯咖啡，休息一会！（镜像6.8G）
下载完成后 查看镜像： 
```bash
docker images
```
<!-- ![例图(1)](../../images/Docker/Oracle/Docker安装Oracle11g(1).png) -->  
![例图(1)](https://s1.ax1x.com/2020/05/22/YLYibn.png)    
可以看到已经下载好了

##### ② 、创建容器
```bash
sudo docker run --name oracle11g -p 1521:1521 -d registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```
这里说一下，命令后面的地址一定要是你下载的镜像地址也就是你拉取镜像名字，否则会出现名字已存在等问题！  
如果创建成功能会返回容器id

##### ③、启动容器 
<!-- ![例图(2)](../../images/Docker/Oracle/Docker安装Oracle11g(2).png) -->  
![例图(2)](https://s1.ax1x.com/2020/05/22/YLYPDs.png)     

##### ④、进入镜像进行配置
+ 1、
<!-- ![例图(3)](../../images/Docker/Oracle/Docker安装Oracle11g(3).png) -->
![例图(3)](https://s1.ax1x.com/2020/05/22/YLYCuj.png)      
+ 2、进行软连接
```bash
sqlplus /nolog
```
<!-- ![例图(4)](../../images/Docker/Oracle/Docker安装Oracle11g(4).png) -->  
![例图(4)](https://s1.ax1x.com/2020/05/22/YLYpvQ.png)   
发现没有这个命令，用不了
+ 3、切换到root 用户下
```bash
su root
```
密码：helowin  
<!-- ![例图(5)](../../images/Docker/Oracle/Docker安装Oracle11g(5).png) -->  
![例图(5)](https://s1.ax1x.com/2020/05/22/YLJz8S.png)  
注意这里还是在容器当中。。有朋友退去了。。。。。。。

+ 4、编辑profile文件配置ORACLE环境变量
```bash
export ORACLE_HOME=/home/oracle/app/oracle/product/11.2.0/dbhome_2
export ORACLE_SID=helowin
export PATH=$ORACLE_HOME/bin:$PATH
```
<!-- ![例图(6)](../../images/Docker/Oracle/Docker安装Oracle11g(6).png) -->  
![例图(6)](https://s1.ax1x.com/2020/05/22/YLYSgg.png)  
在最后加上    
<!-- ![例图(7)](../../images/Docker/Oracle/Docker安装Oracle11g(7).png) -->  
![例图(7)](https://s1.ax1x.com/2020/05/22/YLYAU0.png)  
保存并退出 ：wq  

+ 5、创建软连接
```bash
ln -s $ORACLE_HOME/bin/sqlplus /usr/bin
```
+ 6、切换到oracle 用户
这里还要说一下，一定要写中间的内条 -   必须要，否则软连接无效    
<!-- ![例图(8)](../../images/Docker/Oracle/Docker安装Oracle11g(8).png) -->
![例图(8)](https://s1.ax1x.com/2020/05/22/YLYkEq.png)      
##### ⑤ 、登录sqlplus并修改sys、system用户密码
```bash
sqlplus /nolog
conn /as sysdba
```
<!-- ![例图(9)](../../images/Docker/Oracle/Docker安装Oracle11g(9).png) -->  
![例图(9)](https://s1.ax1x.com/2020/05/22/YLYZCT.png)  
接着执行下面命令

```bash
alter user system identified by system;
alter user sys identified by sys;
也可以创建用户:create user test identified by test;
并给用户赋予权限:grant connect,resource,dba to test;
注意了这里的坑开始出现了
当执行修改密码的时候出现 ：database not open
提示数据库没有打开，不急按如下操作
输入：alter database open;
注意了：这里也许还会提示  ：   ORA-01507: database not mounted
不急！继续！
```
<!-- ![例图(10)](../../images/Docker/Oracle/Docker安装Oracle11g(10).png) -->
![例图(10)](https://s1.ax1x.com/2020/05/22/YLYe8U.png)    
========== 解决方法=========== 
```sql
输入：alter database mount;
输入 ：alter database open;
然后就可执行 修改数据库密码的命令了
改完之后输入：ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;
刷新下表 
exit
```
<!-- ![例图(11)](../../images/Docker/Oracle/Docker安装Oracle11g(11).png) -->  
<!-- ![例图(12)](../../images/Docker/Oracle/Docker安装Oracle11g(12).png) -->  
![例图(11)](https://s1.ax1x.com/2020/05/22/YLYE5V.png)  
![例图(12)](https://s1.ax1x.com/2020/05/22/YLYm2F.png)  

```
 alter profile default limit password_life_time unlimited;
```



##### ⑥、使用pl/sql 进行连接 第7步是navicat连接的在最后
&emsp;&emsp;之前我们把端口映射到了1521上，所以我们需要进行配置tnsnames.ora
几个朋友不知道ora文件在哪，所以添加了这一步     
pl/sql 安装包，汉化包，秘钥工具     
https://download.csdn.net/download/qq_38380025/11168289  
plsql安装配置工具包    
https://blog.csdn.net/qq_38380025/article/details/89677588

```ini
docker_oracle11 =(
    DESCRIPTION = (
        ADDRESS_LIST = (
        ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.211.135)(PORT =1521))
    )
    (CONNECT_DATA =
        (SERVICE_NAME = orcl)
    )
)
```
打开pl/sql 进行登录 ：提示监听程序当前无法识别连接描述符中请求的服务  
<!-- ![例图(13)](../../images/Docker/Oracle/Docker安装Oracle11g(13).png) -->  
<!-- ![例图(14)](../../images/Docker/Oracle/Docker安装Oracle11g(14).png) -->  
![例图(13)](https://s1.ax1x.com/2020/05/22/YLYMr9.png)  
![例图(14)](https://s1.ax1x.com/2020/05/22/YLYKKJ.png)  
这时我们需要去看一下oracle 的 lsnrctl 服务  
<!-- ![例图(15)](../../images/Docker/Oracle/Docker安装Oracle11g(15).png) -->   
![例图(15)](https://s1.ax1x.com/2020/05/22/YLYnv4.png)  
看到这两个了么，任选其一，修改 tnsnames.ora的 service_name=helowinXDB

```ini
docker_oracle11 =(
    DESCRIPTION =
    (ADDRESS_LIST =
        (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.211.135)(PORT =1521))
    )
    (CONNECT_DATA =
        (SERVICE_NAME = helowinXDB)
    )
)
```
欧克，登录成功。  
<!-- ![例图(16)](../../images/Docker/Oracle/Docker安装Oracle11g(16).png) -->
![例图(16)](https://s1.ax1x.com/2020/05/22/YLYQbR.png)     
##### ⑦、navicat连接
有几个朋友用的是navicat连的所以故此添加这一步  
打开navicat后（navicat12不用配置oci.dll文件了）  
直接新建连接    
<!-- ![例图(17)](../../images/Docker/Oracle/Docker安装Oracle11g(17).png) -->
<!-- ![例图(18)](../../images/Docker/Oracle/Docker安装Oracle11g(18).png) -->    
![例图(17)](https://s1.ax1x.com/2020/05/22/YLY3Ux.png)  
![例图(18)](https://s1.ax1x.com/2020/05/22/YLY1V1.png)  
