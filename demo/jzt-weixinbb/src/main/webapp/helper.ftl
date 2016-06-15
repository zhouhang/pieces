<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>小珍助手-意见箱</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
		
<!-- 顶部 -->
<div class="sell-box-head">
	<div class="strip"></div>
	<div align="center" class="opinion">意见箱</div>
</div>

<div class="box-layout inStore-box" style="display: block;">   	
	<form id="addOpinionform" action="/addOpinion" method="post">
		
		<!-- 用户名和手机号码 -->
        <ul class="search-report login" id="loginBox">
        	<li class="search-input login-box">
                <input id="userName" name="userName" type="text" placeholder="请输入联系人">
            </li>
            <li class="search-input phone">
            	<!-- 手机号码 -->
                <input id="mobile" name="mobile" type="text" placeholder="请输入您的手机号码">
            </li>
            <li class="yzm relative">
            	<!-- 验证码 -->
                <input id="mobileCode" type="text" />
                
                <!-- 获取验证码 -->
                <input id="getMobileCode" type="button"  value="获取验证码" />
            </li>
        </ul>
   		
   		<!-- 意见描述 -->
    	<div class="op_describe">
    		<!-- 意见描述输入框 -->
	        <textarea id="memo" name="memo" class="op_textarea" size="500" maxlength="500" theme="simple" onblur="check()" onkeyup="checkLength(this)" accesskey="1" tabindex="11" placeholder="意见描述"></textarea>
    		
    		<!-- 意见描述文字限制提示 -->
    		<div class="cl">                                
	        	<span id="subjectchk" class="limit500">可输入<strong id="checklen">500</strong>字</span>
	        	<span id="postNameRule" class="spn_flag_1" style="display:none"></span>
			</div>
    	</div>
    	
    	<!-- 图片 -->
		<div class="pic-upfile">
            <ul>
                <li class="file-bg"><input type="file" name="file" class="file" accept="image/*" multiple="multiple"/></li> 
            </ul> 
        </div>
   		<div class="twopic cl">您可上传2张相关照片或截屏</div>
    	
    	<!-- 提交按钮 -->
		<div class="strip mt20"></div>       
        <div class="box-layout">
	        <ul class="search-report">
	        	<li><span id="msg" style="margin-left: 0.4em;display: none;" text="您输入的用户名密码错误!"></span></li>
	        	<li class="mt20"><input id="feedbackSubmit" type="submit" class="btn-1" value="捎话给小珍"></li>
	    	</ul>
        </div>
        
    </form>
</div>


<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/flipsnap.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>

<script>
    $(function(){
		//击活改变边框色
        $(':input[type=text]').focus(function(){
            $(this).parent().css('borderColor','#f59e73');
        }).blur(function(){
            $(this).parent().css('borderColor','#cfcfcf');
        });

		//击活隐藏默认文本值
        function Focus(els){
            var default_value = els.val();
            els.focus(function(){
                if($(this).val() == default_value){
                    $(this).val('');
                }
            }).blur(function(){
                if($(this).val() == ''){
                    $(this).val(default_value);
                }
            })
        }
        //Focus($('#loginBox .phone input'));
        //Focus($('#loginBox .login-box input'));
        Focus($('#loginBox .de input'));
        Focus($('#loginBox .re input'));

		// 手机号码输入框焦点事件
        $('#mobile').focus(function(){
            $('.yzm').slideDown();
        }).blur(function(){
            if($(this).val() == '请输入您的手机号码'){
            	//alert($(this).val());
                $('.yzm').slideUp();
            }
        });
        
        
        //点击发送验证码信息
		$("#getMobileCode").click(function() {
			//验证手机号码
			var ok = addOpinionform.check(false, '#mobile');
			if(ok){
				var mobile = $('#mobile').val();
				//获取手机验证码 
				$.post("/feedback/getMobileCode", {"mobile" : mobile}, 
						function(data) {
							if (data == 'y') {
								time(60);
							}
						}
				);
			}
		});


		//上传图片
		$('.file').fileupload({
			url: '/feedback/uploadPic',
			autoUpload: true,
			singleFileUploads: false,
	        dataType: 'json',
	        acceptFileTypes: /(\.|\/)(gif|jpe?g|png|bmp)$/i,
	        maxFileSize: 5000000,
	        messages: {
                acceptFileTypes: '不支持的文件类型！',
                maxFileSize: '不支持文件大小超过5M的图片！'
            }
	    }).on('fileuploadsubmit', function (e, data) {
	    
	       	var filesLength = data.files.length;
	       	var picImgsLength = $(this).parent('li').prevAll('li').find('img').length;
			if(filesLength + picImgsLength > 2 || picImgsLength > 2){
				layerMsg("最多添加2张图片！");
				return false;
			}
			$.each(data.files, function (index,file) {
	           $('.file-bg').before('<li class="see"><img class="loading" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"><i rel=""></i></li>');
	        });
	        
	        picImgsLength = $(this).parent('li').prevAll('li').find('img').length;
	       	if(picImgsLength == 2){
				$('.file-bg').hide();
			}
			
	    }).on('fileuploadprocessalways', function (e, data) {
	        if(data.files.error){
	        	layerMsg(data.files[0].error);
			}
	    }).on('fileuploaddone', function (e, data) {
	    	var picImgs = $('.file-bg').prevAll('li').find('img.loading');
        	var ok = data.result.ok;
        	if(ok){
        		// 返回图片路径
        		var picPaths = data.result.obj;
        		$.each(picPaths, function (index,picPath) {
        			picPath = '${RESOURCE_IMG_UPLOAD}/' + picPath;
        			//加载图片
		        	var img = new Image();
			    	img.onload = function(){
			    		$(picImgs[index]).removeClass('loading').attr('src',picPath).after('<input type="hidden" name="picUrl" value="'+picPath+'" />');
			    	};
			    	img.onerror = function(){
			    		layerMsg('图片加载失败！');
			    	};	
			    	img.src = picPath;
		        });
			}else{
				var msg = data.result.msg;
				if(msg != undefined){
					layerMsg(msg);
				}
			}
	    }).on('fileuploadfail', function (e, data) {
	        layerMsg('操作失败！');
	    });


		//删除图片
		$('.pic-upfile ul').on('click','li i',function(){
			var pic = $(this).parent('li');
			$(pic).remove();
			$('.file-bg').show();
        });
        

	    //验证表单
	    var addOpinionform = $("#addOpinionform").Validform({
	    	btnSubmit:"#feedbackSubmit",
		    tiptype:function(msg,o,cssctl){
				if(!o.obj.is("form")){
					var objtip=$('#msg');
					cssctl(objtip,o.type);
					if(o.type == 3){
						objtip.text(msg).show();
					}else{
						objtip.text('').show();
					}
				}	
			},
		    ajaxPost:true,
		    showAllError:false,
		    ajaxurl:{
		        success:function(data,obj){
		            var ok = data.ok;
		            if(ok==undefined){
		            	$('#msg').text('网络繁忙，请稍后再试！');
		            }
		        },
		        error:function(data,obj){
		        	var readyState = data.readyState;
		            if(readyState != 0){
	    	        	$('#msg').text('网络繁忙，请稍后再试！');
		            }
		        }
		    },
		    beforeSubmit:function(curform){
		    	//验证图片上传状态
		    	var loadingNum = $('.pic-upfile').find('img.loading').length;
		    	if(loadingNum > 0){
		    		layerMsg('请等待图片上传完成再提交！');
		    		return false;
		    	}
		    	
	    		$('#feedbackSubmit').val('提 交 中').attr('disabled','disabled');
		    },
		    callback:function(data){
		    	$('#feedbackSubmit').val('捎话给小珍').removeAttr('disabled');
		    	var ok = data.ok;
		    	var msg = data.msg;
		    	if(ok){
		    		layer.open({
					    content : msg,
					    shadeClose: false,
					    time: 3,
					    end: function(){
					 		// 提交完成后关闭窗口
					        window.opener=null;
							window.open('','_self');
							window.close();
							WeixinJSBridge.call('closeWindow');
					    }
					});
		    	}else{
		    		if(msg != undefined){
		    			layerMsg(msg);
		    		}else{
		    			layerMsg('网络繁忙，请稍后再试！');
		    		}
		    	}
		    }
		});
		
		// 验证规则
		addOpinionform.addRule([
		    {
		        ele:"#userName",
		        //ignore:"ignore",
		        datatype:"*1-100",
		        nullmsg:"请填写联系人！",
		        errormsg:"请填写1到100位任意字符！",
		        sucmsg:""
		    },
		    {
		        ele:"#mobile",
		        datatype:"m",
		        nullmsg:"请填写联系电话！",
		        errormsg:"请填写正确的电话号码！",
		        sucmsg:""
		    },
		    {
		        ele:"#mobileCode",
		        ajaxurl:"/checkMobileCode",
		        datatype:"*1-50",
		        nullmsg:"请输入您的验证码！",
		        errormsg:"验证码错误！",
		        sucmsg:""
		    },
		    {
		        ele:"#memo",
		        datatype:"*1-500",
		        nullmsg:"请填写描述！",
		        errormsg:"请填写1到500位任意字符！",
		        sucmsg:""
		    }
		    
		]);

		//点击提交按钮显示错误提示信息
        $('#feedbackSubmit').click(function(){
            $('#msg').removeAttr('hidden');
        })
	
        //提示框
        function layerMsg(msg){
        	layer.open({
			    content: msg,
			    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			    time: 2
			});
        };
	
        //touch事件替换CLICK事件
        $('input[type=button]').touchStart(function () {
            $(this).addClass('hover');
        });
        $('input[type=button]').touchMove(function () {
            $(this).addClass('hover');
        });
        $('input[type=button]').touchEnd(function () {
            $(this).removeClass('hover');
        });
        $('input[type=button]').tapOrClick(function () {
            $(this).removeClass('hover');
        });
    });
	
	
    $(function(){
        $(document).bind("click",function(e){
            var target  = $(e.target);
            if(target.closest(".pop-up2,.choose input[type=button]").length == 0){
                $(this).find('.select').hide();
                $('.choose input[type=button]').removeClass('cur');
                //$('.bghui').remove();
            }
            e.stopPropagation();
        });

        //
        $('.select').each(function(){
           $(this).children('a:last').css('border','none');
        })
		
    })
</script>

<!-- 重获手机验证码 -->
<script type="text/javascript">
	var i = 60;
	function time() {
		i -= 1;
		$("#getMobileCode").val(i + '秒重新获取');
		$("#getMobileCode").addClass('disable');
		$("#getMobileCode").attr("disabled", "disabled");
		if (i == 0) {
			$("#getMobileCode").removeAttr("disabled");
			$("#getMobileCode").val("重获验证码");
			$("#getMobileCode").removeClass('disable');
			i = 60;
			return;
		}
		setTimeout("time()", 1000);
	}
</script>
	
<!-- 限制可输入文字长度 -->
<script type="text/javascript">   
        // 只要键盘一抬起就验证编辑框中的文字长度，最大字符长度可以根据需要设定
        function checkLength(obj)  { 
            var maxChars = 500;//最多字符数     
            var curr = maxChars - obj.value.length; 
            if( curr > 0 ){
                document.getElementById("checklen").innerHTML = curr.toString(); 
            }else{
                document.getElementById("checklen").innerHTML = '0';
            }
        } 
         
</script>

<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>