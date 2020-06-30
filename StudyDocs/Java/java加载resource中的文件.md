### java 加载resource中的文件
用getResourceAsStream可以在jar包中加载，如果用LstmSeqModel.class.getResource("lstmSeq.pb").getPath()加载会报错。

```
InputStream is = LstmSeqModel.class.getResourceAsStream("/lstmSeq.pb");
int count = is.available();  
System.out.println(count);
graphDef = new byte[count];
```

值得一提的是getResourceAsStream("")是得到当前类所在的package目录，
而getResourceAsStream("/")得到的是classpath的位置。

