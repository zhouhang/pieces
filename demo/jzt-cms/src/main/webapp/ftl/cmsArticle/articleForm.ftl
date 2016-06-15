<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>发布文章</title>
	<#include "/home/common.ftl"> 
	<script type="text/javascript" charset="utf-8" src="/html/resources/js/ajaxfileupload.js"></script>
	<script type="text/javascript" charset="utf-8" src="/html/resources/thirdparty/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="main">
   <div class="page-main">
   <h1 class="title1">发布文章</h1>
	<form id="article" name="article" action="/addArticle" method="post">
		<ul class="form-1">
           <li>	
           	<label><i class="red">*</i> 站点名称：</label>
           	<p><select id="siteid" id="siteid" name="category_id" class="text text-1" style="width:226px;"><option value="1">九州通中药材总站</option></select></p>
           </li>
           <li>	
           	<label><i class="red">*</i> 栏目：</label>
           	<p><select id="category_id" name="category_id" class="text text-1" style="width:226px;"><option value="1">新闻</option></select></p>
           </li>
           <li>
               <label><i class="red">*</i> 标题：</label>
               <p><input class="text text-1" type="text" id="title" name="title" /></p>
              </li>
               
              <li>
               <label><i class="red">*</i> 标签：</label>
               <p><input class="text text-1" type="text" id="keywords" name="keywords"/></p>
              </li>
              
              <li>
               <label><i class="red">*</i> 摘要：</label>
               <p><input class="text text-1" type="text" id="description" name="description"/></p>
              </li>
             
               <li>
               <label> 来源：</label>
               <p><input class="text text-1" type="text" id="copyfrom" name="copyfrom"/></p>
              	</li>
              	
              	<li>
               <label><i class="red">*</i> 正文：</label>
               	<p>
               		<textarea id="content" name="content" class="text textarea-1" draggable="false" style="width:700px;height:200px;visibility:hidden;"></textarea>
               		<script type="text/javascript">CKEDITOR.replace('content', {
               			height : 400,
               			width : 980});</script>
               	</p>
              	</li>
              	
               <li>
               <label> 图片：</label>
               <p>
               <input id="upload"  name="upload" type="file"  runat="server"/>   
				<input id="file_id" class="text text-1" name="file_id" type='hidden' value="" />
				<input id="but" name="but" value="图片上传" class="btn-blue" type="button"/>
				<span id="imgPre"></span>
			</p>
             </li>
           <li class="mt25">
           		<label></label>
           		<p><input id="add" type="submit" class="btn-blue" value="添加" /> </p>
       		</li>
        </ul>
    </form>
    <span style="color:red;" id="errorMsg" >
        <#if mvo??>
        	${mvo.errorMessages[0].message}
		</#if>
    </span>
    <script type="text/javascript">
	    $(function() {
			$("#but").click(function(){
				var pic = $("#upload").val();
				if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(pic)){
					 alert("请选择图片!");
					return false;
				}
				$.ajaxFileUpload(
					{
						url:'/uploadpic', 
						secureuri:false,
						fileElementId:'upload',
						dataType: 'json',
						success: function (data, status)
						{
							if(data.status.code==0){
								$("#imgPre").append("<span style='margin-right:10px;'>"
								  +"<img width='180' height='180' src='"+data.con.path+data.con.dateDir+"/"+data.con.filename+"'></img></span>");
								$("#file_id").val(data.con.dateDir+"/"+data.con.filename);
							}else{
								alert("上传失败！");
								return false;
							}
						},
						error: function (data, status, e)
						{
							alert(e);
						}
				  })
			})	
	   })	
    </script>
    </div>
    </div>
</body>
</html>