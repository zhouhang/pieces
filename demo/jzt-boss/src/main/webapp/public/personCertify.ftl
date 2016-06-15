<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main relative">
            <h1 class="title1">资料审核</h1>
            <form action="" id="personCertifyForm" method="POST">
                <ul class="form-2">
                    <li>
                    	<!-- 查询条件 开始-->
                    	<input type="hidden" name="submitStartDate" value="${submitStartDate }"/>
                    	<input type="hidden" name="submitEndDate" value="${submitEndDate }"/>
                    	<input type="hidden" name="userName" value="${userName }"/>
                    	<input type="hidden" name="mobileNo" value="${mobileNo }"/>
                    	<input type="hidden" name="realName" value="${realName }"/>
                    	<input type="hidden" name="certifyType" value="${certifyType }"/>
                    	<input type="hidden" name="userType" value="${userType}"/>
                    	<!-- 查询条件 结束-->
                    	<input type="hidden" name="certifyId" id="certifyId" value="${ucPCertify.certifyId }"/>
                    	<input type="hidden" name="userId" id="userId" value="${ucPCertify.userId }"/>
                       <label><i class="red">*</i>真实姓名：</label>
                        <input type="text" name="name" value="${ucPCertify.name }" class="text text-2 mr10" /><br/>
                    </li>
                    <li>
                   		 <label><i class="red">*</i>身份证号码：</label>
                   		 <input type="text" name="idCard" value="${ucPCertify.idCard}"  class="text text-2 mr10"/>
                    </li>
                    <li>
                   		<label><i class="red">*</i>上传身份证：</label>
                    	<span class="col_999">照片支持jpg、gif、bmp、png格式，图片最大不要超过4MB</span>
                    </li>
                    
                    <li class="coption">
	                    <p class="fl ml10">身份证正面照<br/>
	                        <span class="col_999"> 需要能看清姓名、身份证号码等信息</span>
	                        <span class="img relative">
	                        	<#if (picPath??)>
                        			<img id="img" style="width:250px;height:150px;" src="${picPath }" />
	                        	<#else>
	                        		<img id="img" style="width:250px;height:150px;" src="${RESOURCE_CSS}/images/jzt-user-center/shenfz_normal.jpg" />
	                        	</#if>
	                        	<i class="loading" id="loading">
			                    	<img alt="" src="${RESOURCE_IMG}/images/loading.gif"/>
			                    </i>
	                        </span>
	                        <span class="ml30 btn-hui1 relative mr10" >
	                            <input type="file" id="file" name="file" class="btn-none" onchange="javascirpt:fileUpload();" title="上传新图片" value=""  /><i>上传新图片</i>
	                        </span>
	                        <a href="#" name="see" id="seePicPath" onclick="showPic()">点击查看大图</a>
	                        <input type="hidden" id="imgPic" value="${picPath }">
	                        <input type="hidden" id="picName" name="picName" value="" />
			                <input type="hidden" id="picPath" name="picPath" value="" />
			                <input type="hidden" id="picType" name="picType" value="" />
			                <input type="hidden" id="picNameSmall" name="picNameSmall" value="" />
			                <input type="hidden" id="picSmallPath" name="picSmallPath" value="" />
			                <input type="hidden" id="picSmallType" name="picSmallType" value="" />
			                <input type="hidden" id="picNameBig" name="picNameBig" value="" />
			                <input type="hidden" id="picBigPath" name="picBigPath" value="" />
			                <input type="hidden" id="picBigType" name="picBigType" value="" />
	                    </p>
	                    <p class="fl ml50">身份证反面照<br/>
	                        <span class="col_999"> 需要能看清签发机关、有效期限等信息</span>
	                        <span class="img relative">
	                            <#if (picPath1??)>
                        			<img id="img1" style="width:250px;height:150px;" src="${picPath1 }" />
	                        	<#else>
	                        		<img id="img1" style="width:250px;height:150px;" src="${RESOURCE_CSS}/images/jzt-user-center/shenfz_normal.jpg" />
	                        	</#if>
	                        	<i class="loading" id="loading1">
			                    	<img alt="" src="${RESOURCE_IMG}/images/loading.gif"/>
			                    </i>
	                        </span>
	                        <span class="ml30 btn-hui1 relative mr10">
	                            <input type="file" id="file1" name="file" class="btn-none" onchange="javascript:fileUpload1();" title="上传新图片" value=""  /><i>上传新图片</i>
	                        </span>
	                        <a href="#" name="see" id="seePicPath1" onclick="showPic1()" >点击查看大图</a>
	                        <input type="hidden" id="imgPic1" value="${picPath1 }">
	                        <input type="hidden" id="picName1" name="picName1" value="" />
		                    <input type="hidden" id="picPath1" name="picPath1" value="" />
		                    <input type="hidden" id="picType1" name="picType1" value="" />
		                    <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="" />
		                    <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="" />
		                    <input type="hidden" id="picSmallType1" name="picSmallType1" value="" />
		                    <input type="hidden" id="picNameBig1" name="picNameBig1" value="" />
		                    <input type="hidden" id="picBigPath1" name="picBigPath1" value="" />
		                    <input type="hidden" id="picBigType1" name="picBigType1" value="" />
	                    </p>
               		</li>
               		<#if (ucPCertify.status!=1)>
	               		<li class="clearfix" style="padding-top:20px;">
	               			<label>审核备注：</label>
	               			<select id="rejectId" name="rejectContent" class="text">
	               				<option value="0">请选择</option>
	               				<#if (checkRemark??)>
	               					<#list checkRemark?keys as crkey>
	               						<option value="${crkey}" <#if (ucPCertify.rejectMemo == crkey)>selected</#if> >${checkRemark[crkey]}</option>
	               					</#list>
	               				</#if>
	               			</select>
	               		</li>
               		</#if>
                    <li class="hr clearfix"></li>
                    <li style="padding-left:350px;">
                    	<#if (ucPCertify.status==1)>
                    		<input type="button" class="btn-blue" id="saveInfo" value="保存"/>
                    	<#else>
                    		<input type="button" class="btn-blue" id="certPass" value="通过"/>
                    		<input type="button" class="btn-hui ml10" id="certNoPass" value="不通过"/>
                    	</#if>
                    	<input type="button" class="btn-hui ml10" value="返回" onClick="javascript:history.go(-1);"/>
                    </li>
                </ul>    
            </form>
            <!-- 注意事项 -->
            <div class="potation">
            	
            		<h2>审核人员需注意:</h2>
            		<p>1、身份证号码与真实姓名必须匹配<br/>
            		2、真实姓名、身份证号码与上传正反照片信息一致<br/>
            		3、审核人员需对审核结果负责！</p>
            	
            </div>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 弹层 -->
<div class="popup-box" id="picBox">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
</div>
<!-- 弹层  over -->
<script type="text/javascript" charset="utf-8" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
<script>
$(function(){
	    $('#Close').click (function(){
	        $(this).parents('#picBox').hide();
	        $('.bghui').remove();
	    });
	});
    //图片点击查看大图拖动效果
    /* $(function() {
        $('#picbbox').imageView({width:600, height:400});
    }); */
    
    /* $("#personCertifyForm").validForm({
    	tiptype:3,
    	ajaxPost:true,
	    callback:function(data){
	    	if(data.status){
	    		bghui();
	    		Alert({
	    			str:'操作成功!',
	    			buttons:[{
	    				name:'确定',
	    				id:'1',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	    				}}
	    			}]
	    		});
	    	}
	    }
    }); */
    
  //图片上传
	function fileUpload(){
    	$("#loading").show();
		 //$(this).parents('li').children('span:last').addClass('waiting');
		 //ajax请求后台
		 var pic = $("#file").val();
		 var _userId = $("#userId").val();
		 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
			//alert("请选择图片!");
			$("#loading").hide();
			bghui();
			Alert({str:'请选择图片!'});
			return false;
		 }
		 $.ajaxFileUpload({
			url:'/getCertificationManage/uploadImg?type=0&userId=' + _userId, 
			secureuri:false,
			fileElementId:'file',
			dataType: 'json',
			success: function (data, status)
			{
				if(data.status.code==0){
					//alert("上传成功");
					$("#loading").hide();
					bghui();
					Alert({
			            str:'上传成功!',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                classname:'btn-style',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
			                    }
			                }
			            }]
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
					//$("#bigImg").attr("src",data.con[2].path);
					$("#imgPic").val(data.con[2].path);
				}else if(data.status.code==1){
					//alert("图片超过规定大小！");
					$("#loading").hide();
					bghui();
					Alert({str:'图片超过规定大小！'});
					return false;
				}else{
					//alert("上传失败！");
					$("#loading").hide();
					bghui();
					Alert({str:'上传失败！'});
					return false;
				}
			},
			error: function (data, status, e)
			{
				//alert(e);
				$("#loading").hide();
				bghui();
				Alert({str:e});
				
			}
	     })
	 }
	
	function fileUpload1(){
		$("#loading1").show();
		 //$(this).parents('li').children('span:last').addClass('waiting');
		 //ajax请求后台
		 var pic = $("#file1").val();
		 var _userId = $("#userId").val();
		 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
			//alert("请选择图片!");
			$("#loading1").hide();
			bghui();
			Alert({str:'请选择图片!'});
			return false;
		 }
		 $.ajaxFileUpload({
			url:'/getCertificationManage/uploadImg?type=3&userId=' + _userId, 
			secureuri:false,
			fileElementId:'file1',
			dataType: 'json',
			success: function (data, status)
			{
				if(data.status.code==0){
					//alert("上传成功");
					$("#loading1").hide();
					bghui();
					Alert({
			            str:'上传成功!',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                classname:'btn-style',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
			                    }
			                }
			            }]
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
					//$("#bigImg").attr("src",data.con[2].path);
					$("#imgPic1").val(data.con[2].path);
				}else if(data.status.code==1){
					//alert("图片超过规定大小！");
					$("#loading1").hide();
					bghui();
					Alert({str:'图片超过规定大小！'});
					return false;
				}else{
					//alert("上传失败！");
					$("#loading1").hide();
					bghui();
					Alert({str:'上传失败！'});
					return false;
				}
			},
			error: function (data, status, e)
			{
				//alert(e);
				$("#loading1").hide();
				bghui();
				Alert({str:e});
			}
	      })
	  }
    
    
    //通过审核操作
    $("#certPass").click(function(){
    	isPass(1);//通过状态
    });
    
    //不通过审核操作
	$("#certNoPass").click(function(){
    	isPass(2);
    });
    
    function isPass(status){
    	var _name = $("input[name=name]").val();
    	var _idCard = $("input[name=idCard]").val();
    	var _certId = $("#certifyId").val();
    	var _userId = $("#userId").val();
    	var _checkRemark = $("#rejectId").val();
    	
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
    	
    	if(_name == null || _name == '' ){
    		bghui();
    		Alert({str:"真实姓名不能为空!"});
    		return;
    	}
    	
    	if(_idCard == null || _idCard == ''){
    		bghui();
    		Alert({str:"身份证号不能为空!"});
    		return;
    	}
    	
    	var regx = /^(\d{14}|\d{17})(\d|[xX])$/
    	if(!regx.test(_idCard)){
    		bghui();
    		Alert({str:"身份证格式错误!"});
    		return;
    	}
    	//审核备注校验
    	if(status==1){
        	if(_checkRemark != 0){
        		bghui();
        		Alert({str:'通过审核时,审核备注必须为空!'});
        		return;
        	}
    	}else if(status==2){
        	if(_checkRemark == 0){
        		bghui();
        		Alert({str:'不通过审核时,审核备注不能为空!'});
        		return;
        	}
    	}
    	
    	/* if(!window.confirm("确定进行审核操作吗？")){
    		return;
    	} */
    	
    	bghui();
        Alert({str:'确定进行审核操作吗？',
            buttons:[
                {
                    name:'确定',
                    id:'1',
                    classname:'btn-style',
                    ev:{click:function(data){
                        disappear();
                        $(".bghui").remove();
                        $.post("/getCertificationManage/personCertifyIsPass", {
        					certifyId : _certId,
        					userId : _userId,
        					name : _name,
        					idCard : _idCard,
        					isPass : status,
        					checkRemark:_checkRemark,
        					
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
        						//alert("操作成功!");
        						bghui();
        						Alert({
        	    					str:'操作成功!',
        	    					buttons:[{
        	    						name:'确定',
        	    						id:'1',
        	    						classname:'btn-style',
        	    						ev:{click:function(data){
        	    							disappear();
        	    							$(".bghui").remove;
        	    							getUpdateAfterUcCertifyInfo(_certId + "-0");
        	    							}
        	    						}
        	    					}]
        	    				});
        					}else{
        						//alert(data.errorMessages[0].message);
        						bghui();
        						Alert({str:data.errorMessages[0].message});
        					}
        				});
                    }}
                },
                {
                    name:'取消',
                    id:'2',
                    classname:'btn-hui',
                    ev:{click:function(data){
                        disappear();
                        $(".bghui").remove();
                        return;
                    }}
                }
            ]
        });
        
		}

		//保存资料
		$("#saveInfo").click(function() {
			var _name = $("input[name=name]").val();
	    	var _idCard = $("input[name=idCard]").val();
	    	var _userId = $("#userId").val();
	    	var _certId = $("#certifyId").val();
	    	
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
	    	
	    	if(_name == null || _name == '' ){
	    		bghui();
	    		Alert({str:'真实姓名不能为空!'});
	    		return;
	    	}
	    	
	    	if(_idCard == null || _idCard == ''){
	    		bghui();
	    		Alert({str:"身份证号不能为空!"});
	    		return;
	    	}
	    	
	    	var regx = /^(\d{14}|\d{17})(\d|[xX])$/
	    	if(!regx.test(_idCard)){
	    		bghui();
	    		Alert({str:"身份证格式错误!"});
	    		return;
	    	}
	    	/* if(!window.confirm("确定进行保存操作吗？")){
	    		return;
	    	} */
	    	bghui();
	        Alert({str:'确定进行保存操作吗？',
	            buttons:[
	                {
	                    name:'确定',
	                    id:'1',
	                    classname:'btn-style',
	                    ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                        $.post("/getCertificationManage/personCertifyInfoSave",{
	            	    		userId : _userId,
	            	    		certifyId:_certId,
	            				name : _name,
	            				idCard : _idCard,
	            				
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
	            	    		},function(data){
	            	    			if(data.ok){
	            	    				bghui();
	            	    				Alert({
	            	    					str:'保存成功!',
	            	    					id:'3',
	            	    					buttons:[{
	            	    						name:'确定',
	            	    						id:'1',
	            	    						classname:'btn-hui',
	            	    						ev:{click:function(data){
	            	    							disappear();
	            	    							$(".bghui").remove;
	            	    							getUpdateAfterUcCertifyInfo(_certId + "-0");
	            	    							}
	            	    						}
	            	    					}]
	            	    				});
	            						//alert("保存成功!");
	            					}else{
	            						bghui();
	            						Alert({str:data.errorMessages[0].message});
	            						//alert(data.errorMessages[0].message);
	            					}
	            	    		});
	                    }}
	                },
	                {
	                    name:'取消',
	                    id:'2',
	                    classname:'btn-hui',
	                    ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                        return;
	                    }}
	                }
	            ]
	        });
	    	
		});
		
		//获取最新认证会员信息
		function getUpdateAfterUcCertifyInfo(param){
			//location.href="/getCertifyUcUserInfo?param=" + param;
			//获取查询条件参数
        	var _submitStartDate = $("input[name='submitStartDate']").val();
        	var _submitEndDate = $("input[name='submitEndDate']").val();
        	var _userName = $("input[name='userName']").val();
        	var _mobileNo = $("input[name='mobileNo']").val();
        	var _realName = $("input[name='realName']").val();
        	var _certifyType = $("input[name='certifyType']").val();
        	var _userType = $("input[name='userType']").val();
        	
			location.href="/getCertificationManage/searchUcCertifyCondition?submitStartDate="
																	+ _submitStartDate
																	+ "&submitEndDate="
																	+ _submitEndDate
																	+ "&userName="
																	+ _userName
																	+ "&mobileNo="
																	+ _mobileNo
																	+ "&realName="
																	+ _realName
																	+ "&certifyType="
																	+ _certifyType 
																	+ "&userType=" + _userType;
		}
		//查看大图
		function showPic(){
			var path = $("#imgPic").val();
			  $('#picBox').show();
		      var html = '<div class="bghui"></div>';
		      var height = $(document).height();
		      $('body').append(html);
		      $('.bghui').css('height',height);
			  $('#showImg').attr('src',path);
			  $('#picBox').imageView({width:600, height:400});
		      return false;
		  }
		
		function showPic1(){
			var path = $("#imgPic1").val();
			  $('#picBox').show();
		      var html = '<div class="bghui"></div>';
		      var height = $(document).height();
		      $('body').append(html);
		      $('.bghui').css('height',height);
			  $('#showImg').attr('src',path);
			  $('#picBox').imageView({width:600, height:400});
		      return false;
		  }
	
</script>
</body>
</html>