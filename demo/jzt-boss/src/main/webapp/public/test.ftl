<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>九州通中药材电商平台</title>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>

</head>

<body>
		<form id="passwordForm" action="/test/checkPasswor" method="post">
		 <li> 密码：
	         <input type="password" class="register-text" name="password" id="password" nullmsg="请输入密码！"  />
	         <span class="Validform_checktip"></span>
	         <input type="submit" id="subbtn" class="yellow-btn3" value="验证" />
	     </li>
	     </form>	
	     <div  id="update" style="display: none;" >
		     <form id="updateForm" action="/test/update"  method="post">
			 <li><label> 输入sql(执行多条，以分号分割)：</label></li>
			 <li>
				 <textarea id="sql" name="sql"   class="text-store" nullmsg="请填写sql！"  style=" height: 100px; resize: none;width: 72%;"></textarea>
		         <span class="Validform_checktip"></span>
		      </li>
		     <li>
		     	<input type="submit" id="subbtn" class="yellow-btn3" value="执行" />
		     </li>
		     </form>	
		  </div>



<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$("#passwordForm").Validform({
	    tiptype:4,
	    ajaxPost:true,
	    showAllError:true,
	    callback:function(data){
	    	if(data.status=='yes'){
	    		$("#password").parent().hide();
	    		$('#update').attr("style","");;
	    	}else if(data.status=='user_wrong'){	
	    		alert('使用人错误！');
	    	}else if(data.status=='user_null'){	
	    		alert('session过期了，重新登录！');
	    	}else{
	    		alert('密码错误！');
	    	}
	    }
	});
	
	
	var addform= $("#updateForm").Validform({
		tiptype:4,
		ajaxPost:true,
		showAllError:true,
		callback:function(data){
			if(data.status=="yes"){
				alert('执行成功！');
			}else if(data.status=="sql_null"){
				alert('sql不能为空！');
			}else{
				alert(data.status);
			}
		}
	});

</script>
</body>
</html>

