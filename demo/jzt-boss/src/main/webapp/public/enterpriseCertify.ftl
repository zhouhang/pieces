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
                <ul class="form-2 m-blank">
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
                    	<input type="hidden" id="certifyId" value="${ucComCertify.certifyId }"/>
                    	<input type="hidden" id="userId" value="${ucComCertify.userId }">
 						<label><i style="color:red;">*</i>企业名称：</label>
                        <input type="text" name="companyName" value="${ucComCertify.companyName }" class="text text-2 mr10"/>
                    <li>
                   		<label><i style="color:red;">*</i>法人名称：</label>
                   		<input type="text" name="legalPeople" value="${ucComCertify.presidentName }"  class="text text-2 mr10"/>
                    </li>
                    <li>
                   		<label><i style="color:red;">*</i>营业执照注册号：</label>
                   		<input type="text" name="licenseCode" value="${ucComCertify.licenceCode }"  class="text text-2 mr10"/>
                    </li>
                    <li>
                   		<label><i style="color:red;">*</i>营业执照有效期：</label>
                   		<input type="text" name="licenseStartDate" id="datetimepicker1" value="${(ucComCertify.licenceStartdate?string("yyyy/MM/dd"))!'' }"  class="text text-3 mr10" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datetimepicker2\')}',dateFmt:'yyyy/MM/dd'})" />至
                   		<input type="text" name="licenseEndDate" id="datetimepicker2" value="${(ucComCertify.licenceEnddate?string("yyyy/MM/dd"))!'' }"  class="text text-3 ml10" onclick="WdatePicker({minDate:'#F{$dp.$D(\'datetimepicker1\')}',dateFmt:'yyyy/MM/dd'})"/>
                    </li>
                    <li>
	                    <label><i class="red">*</i>上传企业证件：</label>
	                    <span class="btn-hui1 relative mr10">
	                    	<input type="file" id="file" name="file" onchange="javascript:fileUpload();" class="btn-none" value="上传营业执照"/><i>上传营业执照</i>
	                    	<i class="loading" id="loading">
                    			<img alt="" width="30" src="${RESOURCE_IMG}/images/loading.gif"/>
                    		</i>
	                    </span>
	                    <span class="ml10 right"></span>
						<a href="#" name="see" id="seePicPath" onclick="showPic()">点击查看大图</a>
						<input type="hidden" id="imgPic" value="${picPath }"/>
						<input type="hidden" id="picName" name="picName" value="" />
			            <input type="hidden" id="picPath" name="picPath" value="" />
			            <input type="hidden" id="picType" name="picType" value="" />
			            <input type="hidden" id="picNameSmall" name="picNameSmall" value="" />
			            <input type="hidden" id="picSmallPath" name="picSmallPath" value="" />
			            <input type="hidden" id="picSmallType" name="picSmallType" value="" />
			            <input type="hidden" id="picNameBig" name="picNameBig" value="" />
			            <input type="hidden" id="picBigPath" name="picBigPath" value="" />
			            <input type="hidden" id="picBigType" name="picBigType" value="" />						
               		</li>
                    <li>
                   		<label><i class="red"></i>组织机构代码：</label>
                   		<input type="text" name="orgCode" value="${ucComCertify.orgCode }"  class="text text-2 mr10"/>
                    </li>
                    <li>
	                    <label><i class="red"></i>上传企业证件：</label>
	                    <span class="btn-hui1 relative mr10">
	                    	<input type="file" id="file1" name="file" onchange="javascript:fileUpload1();" class="btn-none" value="上传组织机构代码证"/><i>上传组织机构代码证</i>
	                    	<i class="loading" id="loading1">
                    			<img alt="" width="30" src="${RESOURCE_IMG}/images/loading.gif"/>
                    		</i>
	                    </span>
	                    <span id="bigPicFlag" class="ml10 <#if (picPath1??)>right</#if>"></span>
	                    <a href="#" name="see" id="seePicPath1" onclick="showPic1()"><#if (picPath1??)>点击查看大图</#if></a>
	                    <input type="hidden" id="imgPic1" value="${picPath1 }"/>
						<input type="hidden" id="picName1" name="picName1" value="" />
		                <input type="hidden" id="picPath1" name="picPath1" value="" />
		                <input type="hidden" id="picType1" name="picType1" value="" />
		                <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="" />
		                <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="" />
		                <input type="hidden" id="picSmallType1" name="picSmallType1" value="" />
		                <input type="hidden" id="picNameBig1" name="picNameBig1" value="" />
		                <input type="hidden" id="picBigPath1" name="picBigPath1" value="" />
		                <input type="hidden" id="picBigType1" name="picBigType1" value="" />  
               		</li>
                    <li>
                   		<label><i class="red">*</i>会员性质：</label>
                   		<select name="property" class="text">
                   			<option value="1" <#if (ucComCertify.property==0)>selected</#if> >公司</option>
                   			<option value="2" <#if (ucComCertify.property==1)>selected</#if> >个体工商户</option>
                   		</select>
                    </li>
                    <li>
                   		<label>注册资金：</label>
                   		<input type="text" name="regAmount" value="<#if (ucComCertify.registeredCapital??)>${ucComCertify.registeredCapital?c }</#if>"  class="text text-2 mr10"/>(单位：万元)
                    </li>
                    <li>
                   		<label>营业执照成立日期：</label>
                   		<input type="text" name="regDate" id="datetimepicker3" value="${(ucComCertify.licenceDate?string("yyyy/MM/dd"))!'' }"  class="text text-3 mr10" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
                    </li>
                    <#if (ucComCertify.status!=1)>
	                    <li>
	                    	<label>审核备注：</label>
	                   		<select id="rejectId" name="rejectMemo" class="text">
	                   			<option value="0">请选择</option>
	                   			<#if (checkRemark??)>
	               					<#list checkRemark?keys as crkey>
	               						<option value="${crkey}" <#if (ucComCertify.rejectmemo==crkey)>selected</#if> >${checkRemark[crkey]}</option>
	               					</#list>
	              					</#if>
	                   		</select>
	                    </li>
                    </#if>
                    <li class="mt10">
                        <label></label>
                        <#if (ucComCertify.status==1)>
                        	<input type="button" class="btn-blue" id="saveInfo" value="保存"/>
                        <#else>
                        	<input type="button" class="btn-blue" id="certPass" value="通过"/>
                    		<input type="button" class="btn-hui ml10" id="certNoPass" value="不通过"/>
                        </#if>
                    	<input type="button" class="btn-hui ml10" onclick="javascript:history.go(-1);" value="返回"/>
                    </li>
                </ul>    
            </form>
            <div class="potation">
            		<h2>审核人员需注意:</h2>
            		<p>1、营业执照三个月内到期的不予通过<br/>
            		2、营业执照与法人信息在当地工商局官网（红盾网）核实信息<br/>
            		3、组织机构代码信息在全国组织机构代码管理中心核实信息<br/>
            		4、确保所有信息都真实一致无误后才可通过审核<br/>
            		5、审核人员需对审核结果负责！</p>
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
	//日期控件
	/* var datetimepicker1 = $('#datetimepicker1');
    var datetimepicker2 = $('#datetimepicker2');
    var datetimepicker3 = $('#datetimepicker3');
    datetimepicker1.datetimepicker({
    	lang:'ch',
  		timepicker:false,
  		format:'Y/m/d',
		formatDate:'Y/m/d'
    });
    datetimepicker2.datetimepicker({
    	lang:'ch',
  		timepicker:false,
  		format:'Y/m/d',
		formatDate:'Y/m/d'
    });
    datetimepicker3.datetimepicker({
    	lang:'ch',
  		timepicker:false,
  		format:'Y/m/d',
		formatDate:'Y/m/d'
    }); */
	
    $('#Close').click (function(){
        $(this).parents('#picBox').hide();
        $('.bghui').remove();
    });
});

	//图片上传
	function fileUpload(){
	$("#loading").show();
	 //$(this).parents('li').children('span:last').addClass('waiting');
	 //ajax请求后台
	 var pic = $("#file").val();
	 var _userId = $("#userId").val();
	 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
		$("#loading").hide();
		bghui();
		Alert({str:'请选择图片!'});
		return false;
	 }
	 $.ajaxFileUpload({
		url:'/getCertificationManage/uploadImg?type=6&userId=' + _userId, 
		secureuri:false,
		fileElementId:'file',
		dataType: 'json',
		success: function (data, status)
		{
			if(data.status.code==0){
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
	 if(!/.(gif|jpeg|bmp|jpg|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
		$("#loading1").hide();
		bghui();
		Alert({str:'请选择图片!'});
	 }
	 $.ajaxFileUpload({
		url:'/getCertificationManage/uploadImg?type=9&userId=' + _userId, 
		secureuri:false,
		fileElementId:'file1',
		dataType: 'json',
		success: function (data, status)
		{
			if(data.status.code==0){
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
				$("#imgPic1").val(data.con[2].path);
				$("#bigPicFlag").attr("class","ml10 right");
				$("#seePicPath1").html("点击查看大图");
			}else if(data.status.code==1){
				//alert("图片超过规定大小！");
				$("#loading1").hide();
				bghui();
				Alert({str:'图片超过规定大小！'});
				return false;
			}else{
				$("#loading1").hide();
				bghui();
				Alert({str:'上传失败！'});
				return false;
			}
		},
		error: function (data, status, e)
		{
			$("#loading1").hide();
			bghui();
			Alert({str:e});
		}
      })
  }

    //通过审核操作
    $("#certPass").click(function(){
    	isPass(1);
    });
    
    //不通过审核操作
	$("#certNoPass").click(function(){
    	isPass(2);
    });
    
    function isPass(status){
    	var _certId = $("#certifyId").val();
    	var _userId = $("#userId").val();
    	var _comName = $("input[name=companyName]").val();
    	var _presidentName = $("input[name=legalPeople]").val();
    	var _licenseCode = $("input[name=licenseCode]").val();
    	var _licenseStartDate = $("input[name=licenseStartDate]").val();
    	var _licenseEndDate = $("input[name=licenseEndDate]").val();
    	var _orgCode = $("input[name=orgCode]").val();
    	var _property = $("select[name=property]").val();
    	var _registeredCapital = $("input[name=regAmount]").val();
    	var _licenseDate = $("input[name=regDate]").val();
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
    	
    	if(_comName == null || _comName == ''){
    		bghui();
    		Alert({str:"企业名称不能为空!"});
    		return;
    	}
    	if(_presidentName == null || _presidentName == ''){
    		Alert({str:"企业法人不能为空!"});
    		
    		return;
    	}
    	if(_licenseCode == null || _licenseCode == ''){
    		Alert({str:"营业执照编码不能为空!"});
    		return;
    	}
    	if(_licenseStartDate == null || _licenseStartDate == ''){
    		Alert({str:"营业执照开始时间不能为空!"});
    		return;
    	}
    	if(_licenseEndDate == null || _licenseEndDate == ''){
    		bghui();
    		Alert({str:"营业执照结束时间不能为空!"});
    		return;
    	}
        var start=new Date(_licenseStartDate.replace("-", "/").replace("-", "/"));
        var end=new Date(_licenseEndDate.replace("-", "/").replace("-", "/"));
        if(end<start){
        	bghui();
            Alert({str:'结束时间不可小于开始时间！'});
            return;
        }
    	var regx = /^\+?[1-9][0-9]*$/;
    	if(_registeredCapital != null && _registeredCapital != '' && !regx.test(_registeredCapital)){
    		bghui();
    		Alert({str:"注册资金格式不合法!"});
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
                        $.post("/getCertificationManage/companyCertfyIsPass",{
                    		certifyId:_certId,
                    		userId:_userId,
                    		companyName:_comName,
                    		presidentName:_presidentName,
                    		licenceCode:_licenseCode,
                    		licenceStartdate:_licenseStartDate,
                    		licenceEnddate:_licenseEndDate,
                    		orgCode:_orgCode,
                    		property:_property,
                    		registeredCapital:_registeredCapital,
                    		licenceDate:_licenseDate,
                    		status:status,
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
                    	},function(data){
                    		if(data.ok){
                    			//alert("操作成功！");
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
                							getUpdateAfterUcCertifyInfo(_certId + "-1");
                							}
                						}
                					}]
                				});		
                    			//getUpdateAfterUcCertifyInfo(_certId + "-1");
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
	$("#saveInfo").click(function(){
		var _certId = $("#certifyId").val();
    	var _userId = $("#userId").val();
    	var _comName = $("input[name=companyName]").val();
    	var _presidentName = $("input[name=legalPeople]").val();
    	var _licenseCode = $("input[name=licenseCode]").val();
    	var _licenseStartDate = $("input[name=licenseStartDate]").val();
    	var _licenseEndDate = $("input[name=licenseEndDate]").val();
    	var _orgCode = $("input[name=orgCode]").val();
    	var _property = $("select[name=property]").val();
    	var _registeredCapital = $("input[name=regAmount]").val();
    	var _licenseDate = $("input[name=regDate]").val();
    	
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
    	
    	if(_comName == null || _comName == ''){
    		bghui();
    		Alert({str:"企业名称不能为空!"});
    		return;
    	}
    	if(_presidentName == null || _presidentName == ''){
    		bghui();
    		Alert({str:"企业法人不能为空!"});
    		return;
    	}
    	if(_licenseCode == null || _licenseCode == ''){
    		bghui();
    		Alert({str:"营业执照编码不能为空!"});
    		return;
    	}
    	if(_licenseStartDate == null || _licenseStartDate == ''){
    		bghui();
    		Alert({str:"营业执照开始时间不能为空!"});
    		return;
    	}
    	if(_licenseEndDate == null || _licenseEndDate == ''){
    		bghui();
    		Alert({str:"营业执照结束时间不能为空!"});
    		return;
    	}
    	var start=new Date(_licenseStartDate.replace("-", "/").replace("-", "/"));
        var end=new Date(_licenseEndDate.replace("-", "/").replace("-", "/"));
        if(end<start){
        	bghui();
            Alert({str:'结束时间不可小于开始时间！'});
            return;
        }
    	var regx = /^\+?[1-9][0-9]*$/;
    	if(_registeredCapital != null && _registeredCapital != '' && !regx.test(_registeredCapital)){
    		bghui();
    		Alert({str:"注册资金格式不合法!"});
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
                        $.post("/getCertificationManage/comCertifyInfoSave",{
                    		certifyId:_certId,
                    		userId:_userId,
                    		companyName:_comName,
                    		presidentName:_presidentName,
                    		licenceCode:_licenseCode,
                    		licenceStartdate:_licenseStartDate,
                    		licenceEnddate:_licenseEndDate,
                    		orgCode:_orgCode,
                    		property:_property,
                    		registeredCapital:_registeredCapital,
                    		licenceDate:_licenseDate,
                    		
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
                    			//alert("操作成功！");
                    			bghui();
                    			Alert({
                					str:'保存成功!',
                					buttons:[{
                						name:'确定',
                						id:'1',
                						classname:'btn-style',
                						ev:{click:function(data){
                							disappear();
                							$(".bghui").remove;
                							getUpdateAfterUcCertifyInfo(_certId + "-1");
                							}
                						}
                					}]
                				});		
                    			//getUpdateAfterUcCertifyInfo(_certId + "-1");
                    		}else{
                    			alert(data.errorMessages[0].message);
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
	//显示图片
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