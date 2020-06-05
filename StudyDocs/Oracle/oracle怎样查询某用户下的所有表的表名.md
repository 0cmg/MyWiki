## oracle怎样查询某用户下的所有表的表名
####问题描述：查询一个oracle用户下有哪些表
> 解决方法：
>+ （1）dba_tables(在dba权限下可查)
```
select count(*) from dba_tables where owner='TEST';
```
注：表名以及各详细内容可以通过desc dba_tables查看相应字段，在查询相应内容
>+ （2）all_tables(在dba权限下可查)
```
select count(*) from all_tables where owner='TEST';
```
注：表名以及各详细内容可以通过desc all_tables查看相应字段，在查询相应内容
>+ （3）user_tables(当前用户下可查)
```
select count(*) from user_tables;
```
注：表名以及各详细内容可以通过desc user_tables查看相应字段，在查询相应内容