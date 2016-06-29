<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>珍药材-聚好药商，卖珍药材-个人认证</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
 	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
 	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/list.css"  />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<style>
		.loading{
			position:absolute;
			top:50%;
			left:50%;
			width:60px;
			height:60px;
			margin:-30px 0 0 -30px;
		}
</style>
	
</head>
<body>
<!-- 头部  -->
    <#include "common/header.ftl" /> 
<!-- 头部 end  -->
<div class="area-1200 clearfix">
    <!-- 会员左侧 -->
   	<#include "common/left.ftl" /> 
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="border-1 box-tip">
            <p><i class="tip-icon"></i>只有实名认证才可进行交易<br/>
                <span>身份认证：是由珍药材提供的一项身份识别服务，只有完成实名认证才可进行交易，珍药材倡导诚实交易。</span><br/>
                <span>个人会员必须进行个人身份认证，企业需做企业认证，每位会员必须以一个身份参与珍药材的各种行为活动。</span>
            </p>
        </div>
        <div class="hr-red mt10"></div>
        <form id="legalizePerson" action="/submitPersonCertify" method="post">
            <ul class="form-2 mt15">
                <li>
                	<input type="hidden" name="isPass" value="0"/>
                    <label><i class="red">*</i>真实姓名：</label>
                    <input type="text" class="text-2 text-Valid" name="name" value="" ajaxurl="" nullmsg="请输入您的真实姓名。" datatype="zh2-10" errormsg="请正确输入您正确的真实姓名（仅支持2-10个中文）。">
                    <span class="Validform_checktip col_999"></span>
                    <p class="capt">请填写身份证上的姓名，它也是您在本站提现时使用的银行卡开户姓名，请谨慎！</p>
                </li>
                <li>
                    <label><i class="red">*</i>身份证号：</label>
                    <input type="text" class="text-2 text-Valid" name="idCard" value=""  datatype="sfz" nullmsg="请输入您的身份证号码（由15位、18位数字或字母组成）。" errormsg="请输入15位、18位数字或字母组成的身份证号码。">
                </li>
                <li>
                    <label><i class="red">*</i>上传身份证：</label>
                    <span class="col_999">照片支持jpg、gif、bmp、png格式，图片最大不要超过4MB <a href="http://help.54315.com/view-1209124341-1715159533.html"  class="col_red2" target="_blank">图片太大如何处理？</a></span>
                </li>
                <li class="coption">
                    <p class="fl ml10">身份证正面照<br/>
                        <span class="col_999"> 需要能看清姓名、身份证号码等信息</span>
                        <span class="img relative">
                            <img id="img" style="width:250px;height:150px;" src="${RESOURCE_CSS}/images/jzt-user-center/shenfz_normal.jpg" />
                        	<i class="loading" id="loading">
			                    	<img alt="" src="${RESOURCE_IMG}/images/loading.gif"/>
			                </i>
                        </span>
                        <span class="btn-hui1 relative middle"  style="*display: block;">
                            <input type="file" id="file" name="file" class="btn-none" onChange="javascript:fileUpload();" title="上传新图片" value=""  /><i>上传新图片</i>
                        </span>
                        <input type="hidden" id="picName" name="picName" value="" />
		                <input type="hidden" id="picPath" name="picPath" value="" />
		                <input type="hidden" id="picType" name="picType" value="" />
		                <input type="hidden" id="picNameSmall" name="picNameSmall" value="" />
		                <input type="hidden" id="picSmallPath" name="picSmallPath" value="" />
		                <input type="hidden" id="picSmallType" name="picSmallType" value="" />
		                <input type="hidden" id="picNameBig" name="picNameBig" value="" />
		                <input type="hidden" id="picBigPath" name="picBigPath"  datatype="ppath" nullmsg=" " errormsg=" " value="" />
		                <input type="hidden" id="picBigType" name="picBigType" value="" />
                    </p>
                    <p class="fl ml50">身份证反面照<br/>
                        <span class="col_999"> 需要能看清签发机关、有效期限等信息</span>
                        <span class="img relative">
                            <img id="img1" style="width:250px;height:150px;" src="${RESOURCE_CSS}/images/jzt-user-center/shenfz_normal.jpg" />
                        	<i class="loading" id="loading1">
			                    <img alt="" src="${RESOURCE_IMG}/images/loading.gif"/>
			                </i>
                        </span>
                        <span class="btn-hui1 relative middle" style="*display: block;">
                            <input type="file" id="file1" name="file" class="btn-none" onChange="javascript:fileUpload1();" title="上传新图片" value=""  /><i>上传新图片</i>
                        </span>
                        <input type="hidden" id="picName1" name="picName1" value="" />
	                    <input type="hidden" id="picPath1" name="picPath1" value="" />
	                    <input type="hidden" id="picType1" name="picType1" value="" />
	                    <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="" />
	                    <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="" />
	                    <input type="hidden" id="picSmallType1" name="picSmallType1" value="" />
	                    <input type="hidden" id="picNameBig1" name="picNameBig1" value="" />
	                    <input type="hidden" id="picBigPath1" name="picBigPath1" datatype="ppath" nullmsg=" " errormsg=" " value="" />
	                    <input type="hidden" id="picBigType1" name="picBigType1" value="" />
                    </p>
                </li>
                <li class="hr clearfix"></li>
                <li class="mid tj-btn"><input type="submit" id="subbtn" class="yellow-btn3" value="确认，提交" /></li>
                <li class="paption mid">您提供的资料珍药材将予以保护，不会挪作他用</li>
            </ul>
        </form>
    </div>

</div>
<!-- 底部  -->
<#include "common/footer.ftl" /> 
<!-- 底部 end  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" charset="utf-8" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_CSS}/js/jzt-user-center/legalize_person.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript">
	var formObj = $("#legalizePerson");
	$("#legalizePerson").Validform({
	    tiptype:4,
	    ajaxPost:true,
	    dragonfly:true,
	    datatype:{//传入自定义datatype类型，可以是正则，也可以是函数（函数内会传入一个参数）;
			"ppath":function(gets,obj,curform,regxp){
				var picPath = $("#picBigPath").val();
 				var picPath1 = $("#picBigPath1").val();
 				if(picPath == ""){
 					bghui();
 					Alert({str:'请上传身份证正面照!'});
 					return false;
 				}
 				if(picPath1 == ""){
 					bghui();
 					Alert({str:'请上传身份证反面照!'});
 					return false;
 				}
 				return true;
			}
		},
	    beforeSubmit:function(formObj){
	    	$("#subbtn").val("提交中...");
			$("#subbtn").attr("disabled","disabled");
			return true;
		},
	    callback:function(data){
	    	if(data.status=='yes'){
	    		bghui();
	    		$("#subbtn").val("提交成功");
	    		Alert({
	    			str:'提交资料成功！我们会在1-3个工作日内为您审核，请耐心等待！',
	    			buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/getLegalize";
	    				}
	    			}
	    			}]
	    		});
	    	}else if(data.status == 'code001'){
	    		bghui();
		        Alert({
		            str:'请上传身份证正面图片！'
		        });
	    	}else if(data.status == 'code002'){
	    		bghui();
		        Alert({
		            str:'请上传身份证反面图片！'
		        });
		        
			//added by biran 20150715 提交前增加对会员认证的校验
	    	}else if(data.status == 'code003'){
	    		bghui();
		        Alert({
		            str:'您的个人认证已提交，请勿再次提交！',
		            buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/getLegalize";
	    					}
	    				}
	    			}]
		        });
	    	}else if(data.status == 'code004'){
	    		bghui();
		        Alert({
		            str:'您的企业认证已完成，不能提交个人认证！',
		            buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/getLegalize";
	    					}
	    				}
	    			}]
		        });
	    	}else if(data.status == 'code005'){
	    		bghui();
		        Alert({
		            str:'您的企业认证已完成，请勿重复提交！',
		            buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/getLegalize";
	    					}
	    				}
	    			}]
		        });
	    	}else if(data.status == 'code006'){
	    		bghui();
		        Alert({
		            str:'您的认证资料正在审核中，请勿再提交！',
		            	buttons:[{
		    				name:'确定',
		    				id:'1',
		    				classname:'btn-style',
		    				ev:{click:function(data){
		    					disappear();
		    					$(".bghui").remove();
		                        location.href = "/getLegalize";
		    					}
		    				}
		    			}]
		            	
		        });
		        
		      //added end
	    	}else if(data.status=='double'){
	    		bghui();
		        Alert({
		            str:'提交失败！不可重复提交！'
		        });
	    	}else{
	    		bghui();
		        Alert({
		            str:'提交资料失败！请仔细核对资料正确性！'
		        });
	    	}
	    }
	});
	//图片上传
	function fileUpload(){
		$("#loading").show();
		 //$(this).parents('li').children('span:last').addClass('waiting');
		 //ajax请求后台
		 var pic = $("#file").val();
		 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
			 $("#loading").hide();
			 bghui();
			Alert({str:"请选择图片!"});
			return false;
		 }
		 $.ajaxFileUpload({
			url:'/uploadImg?type=0', 
			secureuri:false,
			fileElementId:'file',
			dataType: 'json',
			success: function (data, status)
			{
				if(data.status.code==0){
					$("#loading").hide();
					bghui();
					Alert({
			            str:'上传成功!'
			            
			        });
					$("#picName").val(data.con[0].filename);
					$("#picPath").val(data.con[0].path);
					$("#picType").val(data.con[0].type);
					$("#picNameSmall").val(data.con[1].filename);
					$("#picSmallPath").val(data.con[1].path);
					$("#picSmallType").val(data.con[1].type);
					$("#picNameBig").val(data.con[2].filename);
					$("#picBigPath").val(data.con[2].path);
					$("#picBigType").val(data.con[2].type);
					$("#img").attr("src",data.con[2].path);
				}else if(data.status.code==1){
					$("#loading").hide();
					bghui();
			        Alert({
			            str:'图片超过规定大小!'
			        });
					return false;
				}else{
					$("#loading").hide();
					bghui();
			        Alert({
			            str:'上传失败!'
			        });
					return false;
				}
			},
			error: function (data, status, e)
			{
				$("#loading").hide();
				bghui();
		        Alert({
		            str:e
		        });
			}
	     })
	 }
	
	function fileUpload1(){
		$("#loading1").show();
		 //$(this).parents('li').children('span:last').addClass('waiting');
		 //ajax请求后台
		 var pic = $("#file1").val();
		 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
			 $("#loading1").hide();
			 bghui();
		     Alert({
		         str:'请选择图片!'
		     });
			return false;
		 }
		 $.ajaxFileUpload({
			url:'/uploadImg?type=3', 
			secureuri:false,
			fileElementId:'file1',
			dataType: 'json',
			success: function (data, status)
			{
				if(data.status.code==0){
					$("#loading1").hide();
					bghui();
					Alert({
			            str:'上传成功!'
			           
			        });
					$("#picName1").val(data.con[0].filename);
					$("#picPath1").val(data.con[0].path);
					$("#picType1").val(data.con[0].type);
					$("#picNameSmall1").val(data.con[1].filename);
					$("#picSmallPath1").val(data.con[1].path);
					$("#picSmallType1").val(data.con[1].type);
					$("#picNameBig1").val(data.con[2].filename);
					$("#picBigPath1").val(data.con[2].path);
					$("#picBigType1").val(data.con[2].type);
					$("#img1").attr("src",data.con[2].path);
				}else if(data.status.code==1){
					$("#loading1").hide();
					bghui();
			        Alert({
			            str:'图片超过规定大小!'
			        });
					return false;
				}else{
					$("#loading1").hide();
					bghui();
			        Alert({
			            str:'上传失败!'
			        });
					return false;
				}
			},
			error: function (data, status, e)
			{
				$("#loading1").hide();
				bghui();
		        Alert({
		            str:e
		        });
			}
	      })
	  }
	
	//提交个人实名认证审核资料
	 /* $("#subbtn").click(function(){
		var _name = $("input[name=name]").val();
		var _idCard = $("input[name=idCard]").val();
		//$("").val();
		
		var _picName = $("#picName").val();
		var _picPath = $("#picPath").val();
		var _picType = $("#picType").val();
		var _picNameSmall = $("#picNameSmall").val();
		var _picSmallPath = $("#picSmallPath").val();
		var _picSmallType = $("#picSmallType").val();
		var _picNameBig = $("#picNameBig").val();
		var _picBigPath = $("#picBigPath").val();
		var _picBigType = $("#picBigType").val();
		
		var _picName1 = $("#picName1").val();
		var _picPath1 = $("#picPath1").val();
		var _picType1 = $("#picType1").val();
		var _picNameSmall1 = $("#picNameSmall1").val();
		var _picSmallPath1 = $("#picSmallPath1").val();
		var _picSmallType1 = $("#picSmallType1").val();
		var _picNameBig1 = $("#picNameBig1").val();
		var _picBigPath1 = $("#picBigPath1").val();
		var _picBigType1 = $("#picBigType1").val();
		
	$.post("/submitPersonCertify", {
			name : _name,
			idCard : _idCard,
			isPass:0,
			picName:_picName,
			picPath:_picPath,
			picType:_picType,
			picNameSmall:_picNameSmall,
			picSmallPath:_picSmallPath,
			picSmallType:_picSmallType,
			picNameBig:_picNameBig,
			picBigPath:_picBigPath,
			picBigType:_picBigType,
			picName1:_picName1,
			picPath1:_picPath1,
			picType1:_picType1,
			picNameSmall1:_picNameSmall1,
			picSmallPath1:_picSmallPath1,
			picSmallType1:_picSmallType1,
			picNameBig1:_picNameBig1,
			picBigPath1:_picBigPath1,
			picBigType1:_picBigType1
			
		}, function(data) {
			if(data.ok){
				alert("提交成功!");
				location.href="/getLegalize";
			}else{
				alert("提交失败!");
			}
		});

		//$("#legalizePerson").submit();
	}); */
</script>
</body>
</html>