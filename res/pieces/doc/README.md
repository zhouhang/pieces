##开发环境
1. mysql-5.6.7,elasticsearch-2.2.1,redis-2.4.5(需用指定版本的软件否则会有兼容性问题)
2. 项目数据库在/doc/database/pieces20170505.sql(数据的备份文件)

##启动项目前的配置
1. pieces-biz/pom.xml里面profiles标签里面配置自己的属性
2. pieces-boss/pom.xml
3. pieces-dao/main/resources/config/ 里面根据配置的profile建立对应的文件夹配置数据库链接属性
4. pieces-service/main/resources/config/ 里面根据配置的profile建立对应的文件夹
   配置 redis,es,mail,微信公众号,短信平台,kdniao快递鸟(用来获取物流跟踪信息)
5. 项目启动后通过在浏览器里面直接访问pieces-boss后台的demo/create/index/all 链接来重建商品索引
6. 备注: 相关第三方服务的配置信息和地址在另一个文档中.

##项目大概介绍
1. 数据库用户和相关字段含义见pieces-dao/src/main/java/com/pieces/dao/model 里面的实体类介绍注释.


