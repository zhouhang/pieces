<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="/html/resources/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/html/resources/thirdparty/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="/html/resources/thirdparty/ckeditor/ckeditor.js"></script>
	<title>文章发布</title>
</head>
<body>
	<form id="inputForm" modelAttribute="article" action="/cms/art" method="post" enctype="multipart/form-data">
		<div style="width:auto;">
        	<div style="width:100%;"><label>门户：</label><select id="siteid"><option value="1">九州通中药材总站</option></select></div>
            <div style="width:100%;margin-top:10px;"><label>栏目：</label><select id="category_id" name="category_id"><option value="1">新闻</option></select></div>
            <div style="width:100%;margin-top:10px;"><label>标题：</label><input id="title" name="title" type="text"/></div>
            <div style="width:100%;margin-top:10px;"><label>标签：</label><input id="keywords" name="keywords" type="text"/></div>
            <div style="width:100%;margin-top:10px;"><label>摘要：</label><input id="description" name="description" type="text"/></div>
            <div style="width:100%;margin-top:10px;"><label>来源：</label><input id="copyfrom" name="copyfrom" type="text"/></div>
            <div style="width:100%;margin-top:10px;"><label>发布时间：</label><input type="text" class="Wdate" id="create_date" name="create_date" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2014-03-10 00:00:00'})" />
			</div>
			<div style="width:100%;margin-top:10px;"><label>内容：</label>
			<textarea ws="30" cols="50" id="content" name="content"></textarea><script type="text/javascript">CKEDITOR.replace('content');</script></div>
        </div>
        <input type="submit" value="提交"/>
        <span style="color:red;" id="errorMsg" >
	        <#if mvo??>
	        	#{mvo.message}
			</#if>
        </span>
	</form>
</body>
</html>
