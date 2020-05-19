在sqlplus命令行输入!ls 可查看linux 当前路径下的文件列表


从sqlplus 中导入sql脚本时，需要先设置编码
export NLS_LANG=AMERICAN_AMERICA.ZHS16GBK


select userenv('language') from dual; 
