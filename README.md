# elasticsearch索引局部更新



## 批量执行elasticsearch的reindex功能实现索引的局部更新

### 实现功能

1.局部更新elasticsearch索引

1.批量实现elasticsearch的reindex功能

2.防止reindex功能的不科学重复执行

### 运行环境

1.jdk1.8

2.elasticsearch6.4.3

### 使用方法

1.配置工具中的application.properties配置文件

```
# elasticsearch路径，非空
url=127.0.0.1
# elasticsearch端口，非空
port=9200
# 索引名称不同但相似的处理方式，在之前索引后加该后缀，非空
change=_19700601
# 仅包含该关键字的index会被处理，可为空
keyword=poi_
# 存储"记录已处理过的索引"文件的文件夹的路径，可为空
file_url=D:\\
# 存储"需要排除在外的索引名称"的文件的名称,可为空
index_file_name=1970-06-01-13-49-14.log
```

2.执行工具——普通jar包执行方法执行

3.当发生错误时

​	1）及时修改发生错误索引	

​	2）修改该工具配置文件中的index_file_name为记录排除索引的文件名称，以防止索引的重复reindex

​	3）"2)"中支持将记录排除索引的文件合并成一个文件后，输入该文件名称 

4.再次执行工具

### 注意事项

1.满足elasticsearch的reindex条件

2.source索引与dest索引名称基本相同，但必须不同(有规律的不同)

3.执行工具前保证两index中数据的id必须不同
