# pieces
饮片B2B

##工具类
------
###1.JSON解析工具类
com.pieces.tools.utils.GsonUtil

```java
//把User对象转换成json字符串,并过滤password属性
GsonUtil.toJsonExclude(user,"password");

//把User对象转换成json字符串,只解析username和email属性
GsonUtil.toJsonInclude(user,"username","email")

//把json字符串转换成User对象
User user = GsonUtil.jsonToEntity("{username:'zhangsan',email:'zhangsan@gmail.com'}",User.class)

```

###2.发送response工具类
com.pieces.tools.utils.WebUtil
```java
//把user对象转换成json,通过response输出
WebUtil.print(response,user)

//把json字符串通过response输出
WebUtil.printJson(response,"{username:'wangb'}")
```

###3.随机字符工具
com.pieces.tools.utils.SeqNoUtil
```java
//生成六位随机数
SeqNoUtil.getRandomNum(6)

```



###4.上传文件
com.pieces.tools.upload.DefaultUploadFile
```java
//上传文件工具类,需要配置basePath来设置上传更目录,配置customImageName来自定义上传子目录和上传文件名
defaultUploadFile.uploadFile("abc.txt",inputStream);

```
biz项目**GeneralController**类里**fileUpload**方法有实例。

###5.http请求工具类
com.pieces.tools.utils.httpclient.HttpClientUtil
```java
//发送POST请求，访问taobao，并传入name参数
Map<String, Object> param = new HashMap<>();
param.put("name", "zhangsan");
HttpClientUtil.post(HttpConfig.custom().url("http://www.taobao.com").map(param));

```

###6.发送短信工具类
com.pieces.service.impl.SmsService
```java
//向手机号发送验证码
smsService.sendSmsCaptcha("18802345678")

```

###7.系统邮箱
system@sghaoyao.com / Sghy20161219