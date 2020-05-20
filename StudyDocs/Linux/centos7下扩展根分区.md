#### 查看当前系统磁盘使用状况   
```bash
df -h
``` 
![例图1](../../images/Linux/centos7下扩展根分区(1).png)  

#### 可以看见，我新添加了一块硬盘，大小为10G，新磁盘/dev/sdb
```bash
fdisk -l
``` 
![例图2](../../images/Linux/centos7下扩展根分区(２).png)    

#### 对新的磁盘进行分区 ,在交互模式输入
```bash
fdisk /dev/sdb
``` 
![例图3](../../images/Linux/centos7下扩展根分区(３).png)  

#### 查看卷分组为centos
```bash
vgdisplay -v
```   
![例图4](../../images/Linux/centos7下扩展根分区(４).png)  
  
#### 为之前新增的分区创建物理卷，查看新分区的大小  
```bash
pvcreate /dev/sdb1
pvdisplay
```    
![例图5](../../images/Linux/centos7下扩展根分区(５).png)     
  
#### 扩展卷分组,"centos"是vgdisplay命令查到的卷分组名，查看逻辑卷  
```bash
vgextend centos /dev/sdb1
lvdisplay
``` 
![例图6](../../images/Linux/centos7下扩展根分区(６).png)  

#### 扩展逻辑卷（第一次扩展10G失败了，改成9G成功）
```bash
lvextend -L +10G /dev/centos/root
```   
![例图7](../../images/Linux/centos7下扩展根分区(７).png)  

#### 扩展完成后还要将文件系统扩大或者使用resize2fs，原本只有15G的空间，现在变成了24G
```bash
xfs_growfs /dev/centos/root
df -h
```   
![例图8](../../images/Linux/centos7下扩展根分区(８).png)    