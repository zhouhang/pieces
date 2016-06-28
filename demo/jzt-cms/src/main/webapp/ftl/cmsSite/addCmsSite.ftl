<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>站点查询</title>
	<#include "/home/common.ftl">   
	<link rel="stylesheet" href="/html/resources/thirdparty/kindeditor-4.1.10/themes/default/default.css" />
	<script charset="utf-8" src="/html/resources/thirdparty/kindeditor-4.1.10/kindeditor-min.js"></script>
	<script charset="utf-8" src="/html/resources/thirdparty/kindeditor-4.1.10/lang/zh_CN.js"></script>
	
	
	
</head>
	<body>	
	<!-- pageCenter start -->
	    <div class="main">
	        <div class="page-main">
	        <h1 class="title1">添加站点</h1>
		        <form id="cmsSite" modelAttribute="cmsSite" name="cmsSite" action="/ftl/cmsSite/addCmsSite" method="post">
	                <ul class="form-1">
	                   <li>	
	                   	<label><i class="red">*</i> 站点名称：</label>
	                   	<p><input class="text text-1" type="text" id="name" name="name"/></p>
	                   </li>
	                   <li>	
	                   	<label><i class="red">*</i> 站点标题：</label>
	                   	<p><input class="text text-1" type="text" id="title" name="title"/></p>
	                   </li>
	                   <#--<li>	
	                   	<label>站点logo：</label>
	                   	<p><input type="text" id="url" name="url" value=""  class="text text-1"/> </p>
	                   	<p><input type="button" id="insertfile" value="选择文件"  /></p>
	                   		<p><input type="file" id="myfiles" name="myfiles"  accept="image/gif" /></p>
	                   </li>-->
	                
	                   <li>
	                   
                        <label>站点域名</label>
                        <p><input class="text text-1" type="text" id="domain" name="domain"/></p>
                       </li>
                        
                       <li>
                        <label>关键字</label>
                        <p><input class="text text-1" type="text" id="keywords" name="keywords"/></p>
                       </li>
                       
                       <li>
                        <label>主题</label>
                        <p><input class="text text-1" type="text" id="theme" name="theme"/></p>
                       </li>
                      
                        <li>
                        <label>版本信息</label>
                        <p><input class="text text-1" type="text" id="copyright" name="copyright"/></p>
                       	</li>
                       	
                       	<li>
                        <label>创建者</label>
                        <p><input class="text text-1" type="text" id="create_by" name="create_by"/></p>
                       	</li>
                        
                       	<li>
                        <label>创建时间</label>
                        <p>
                        	<input type="text"   name="createDate"  id="createDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2014-03-10 00:00:00'})" />
						</p>
                       	</li>
                       		
                       	<li>
                        <label>描述</label>
                        <p><textarea id="description" name="description" class="text textarea-1" draggable="false" style="width:700px;height:200px;visibility:hidden;"></textarea></p>
                       	</li>
                     
	                   <li class="mt25">
                    		<label></label>
                    		<p><input id="add" type="button" class="btn-blue" value="添加" /> </p>
                		</li>
	                </ul>
	            </form>
	        </div>
	    </div>
	    <script>
	    	$(function(){
			    $("#add").click(function(){	
					if($("#name").val()==null||$("#name").val()==""||$("#title").val()==null||$("#title").val()==""){
						alert("站点名称和站点标题不能为空");
						return false;
					}
					if(confirm("确定要提交数据吗？"))
						$("#cmsSite").submit();
		        	});
		        	
	    	})
	         
	       KindEditor.ready(function(K) {
					var editor = K.editor({
						allowFileManager : true
					});
					
					<#--K('#insertfile').click(function() {
						editor.loadPlugin('insertfile', function() {
							editor.plugin.fileDialog({
								fileUrl : K('#url').val(),
								clickFn : function(url, title) {
									K('#url').val(url);
									editor.hideDialog();
								}
							});
						});
					});-->
					
					
					editor = K.create('textarea[name="description"]', {
						resizeType : 1,
						allowPreviewEmoticons : false,
						allowImageUpload : false,
						items : [
							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
							'insertunorderedlist', '|', 'emoticons', 'link']
					});
				
				}); 
        
        
       <#-- KindEditor.ready(function(K) {
				K.create('textarea[name="content"]', {
					autoHeightMode : true,
					afterCreate : function() {
						this.loadPlugin('autoheight');
					}
				});
			});-->
	    </script>
	    
	</body>
</html>