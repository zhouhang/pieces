<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>珍药材-聚好药商，卖珍药材-企业认证</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
 	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
 	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/list.css"  />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#include "common/header.ftl" /> 
<div class="area-1200 clearfix">
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
        <form id="legalizeCompany" method="post" action="/addCompanyCertify">
            <ul class="form-2 mt15">
                <li>	
                    <label><i class="red">*</i>企业名称：</label>
                    <input type="text" class="text-2 text-Valid" value="${companyCertify.companyName!'' }" name="companyName" id="companyName" datatype="s4-60" nullmsg="请输入企业名称。" errormsg="请输入正确的企业名称4-60个字。">
                    <span class="Validform_checktip"></span>
                    <p class="capt">请填写真实企业名称，它也是在本站提现时使用的银行卡开户抬头，请谨慎！</p>
                </li>
                <li>
                    <label><i class="red">*</i>法定代表人：</label>
                    <input type="text" class="text-2 text-Valid" value="${companyCertify.presidentName!'' }" name="presidentName" id="presidentName" datatype="zh2-10" nullmsg="请输入法人姓名。"  errormsg="请正确输入您正确的法人姓名（仅支持2-10个中文）。">
                    <span class="Validform_checktip"></span>
                    <p class="capt">法人姓名为2-10个中文！</p>
                </li>
                <li>
                    <label><i class="red">*</i>营业执照注册号：</label>
                    <input type="text" class="text-2 text-Valid" value="${companyCertify.licenceCode!'' }" name="licenceCode" id="licenceCode" datatype="*"  nullmsg="请输入营业执照上的注册号。" errormsg="请输入正确的营业执照注册号。" />
                     <span class="Validform_checktip"></span>
                    <p class="capt">请输入营业执照上的注册号！</p>
                </li>
                <li>
                    <label><i class="red">*</i>营业执照有效期：</label>
                    <input name="licenceStartdate" id="licenceStartdate" value="${companyCertify.licenceStartdate?string('yyyy/MM/dd')!'' }" class="text-2 text-Valid" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'licenceEnddate\')}',dateFmt:'yyyy/MM/dd'})" style="width:174px; width:168px\9; *width:168px;" /> 至 <input name="licenceEnddate" id="licenceEnddate" value="${companyCertify.licenceEnddate?string('yyyy/MM/dd')!'' }" class="text-2 text-Valid" type="text" datatype="vt" nullmsg="请输入有效日期。" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'licenceStartdate\')}',dateFmt:'yyyy/MM/dd'})" style="width:174px; width:168px\9; *width:168px;" />
                    <span class="Validform_checktip"></span>
                    <p class="capt">请按此日期格式输入：年/月/日</p>
                </li>
                <li class="relative">
                    <label><i class="red">*</i>上传企业证件：</label>
                    <span class="btn-hui1 relative"><input type="file" id="file" name="file" class="btn-none" onchange="fileUpload();" value=""  /><i>上传营业执照</i></span>
                    <input type="hidden" id="hidFile" datatype="*"  nullmsg="请上传企业证件图。" value="${companyCertifyDto.picName!'' }"  />
                    <#if !companyCertifyDto.picName??>
	                    <span class="Validform_checktip col_999 ml10">图片支持jpg、gif、bmp、png格式，图片最大不要超过4MB <a href="http://help.54315.com/view-1209124341-1715159533.html"  class="col_red2" target="_blank">图片太大如何处理？</a></span>
                    <#else>
	                    <span class="Validform_checktip right"></span>
                    </#if>
                    <input type="hidden" id="picName" name="picName" value="${companyCertifyDto.picName!'' }" />
                    <input type="hidden" id="picPath" name="picPath" value="${companyCertifyDto.picPath!'' }" />
                    <input type="hidden" id="picType" name="picType" value="${companyCertifyDto.picType!'' }" />
                    <input type="hidden" id="picNameSmall" name="picNameSmall" value="${companyCertifyDto.picNameSmall!'' }" />
                    <input type="hidden" id="picSmallPath" name="picSmallPath" value="${companyCertifyDto.picSmallPath!'' }" />
                    <input type="hidden" id="picSmallType" name="picSmallType" value="${companyCertifyDto.picSmallType!'' }" />
                    <input type="hidden" id="picNameBig" name="picNameBig" value="${companyCertifyDto.picNameBig!'' }" />
                    <input type="hidden" id="picBigPath" name="picBigPath" value="${companyCertifyDto.picBigPath!'' }" />
                    <input type="hidden" id="picBigType" name="picBigType" value="${companyCertifyDto.picBigType!'' }" />
                    <i class="loading" id="loading">
                    	<img alt="" width="30" height="30" src="${RESOURCE_IMG}/images/loading.gif" style="display:block;"/>
                    </i>
                </li>
                <li>
                    <label>组织机构代码：</label>
                    <input type="text" class="text-2 text-Valid" value="${companyCertify.orgCode!'' }" name="orgCode" id="orgCode" />
                </li>
                <li class="relative">
                    <label>上传企业证件：</label>
                    <span class="btn-hui1 relative"><input type="file" id="file1" name="file" class="btn-none" onchange="fileUpload1();" title="上传组织机构代码证" value="" style="width: 160px;" /><i>上传组织机构代码证</i></span>
                    <#if !companyCertifyDto.picName1??>
                    	<span class="col_999 ml10"> 请重新上传图片，图片支持jpg、gif、bmp、png格式，图片最大不要超过4MB</span>
                    <#else>
                    	<span class="right"></span>
                    </#if>
                    <input type="hidden" id="picName1" name="picName1" value="${companyCertifyDto.picName1!'' }" />
                    <input type="hidden" id="picPath1" name="picPath1" value="${companyCertifyDto.picPath1!'' }" />
                    <input type="hidden" id="picType1" name="picType1" value="${companyCertifyDto.picType1!'' }" />
                    <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="${companyCertifyDto.picNameSmall1!'' }" />
                    <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="${companyCertifyDto.picSmallPath1!'' }" />
                    <input type="hidden" id="picSmallType1" name="picSmallType1" value="${companyCertifyDto.picSmallType1!'' }" />
                    <input type="hidden" id="picNameBig1" name="picNameBig1" value="${companyCertifyDto.picNameBig1!'' }" />
                    <input type="hidden" id="picBigPath1" name="picBigPath1" value="${companyCertifyDto.picBigPath1!'' }" />
                    <input type="hidden" id="picBigType1" name="picBigType1" value="${companyCertifyDto.picBigType1!'' }" />
                    <i class="loading" id="loading1">
                    	<img alt="" width="30" src="${RESOURCE_IMG}/images/loading.gif"/>
                    </i>
                </li>
                <li class="coption">
                    备注：若是公司性质，必须填写组织机构代码证和上传证件，否则会影响您的认证情况。<br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个体工商户可根据情况是否上传组织机构代码证。</li>
                <li class="tj-btn"><input type="submit" id="subBtn" class="yellow-btn3" value="重新提交" /></li>
                <li class="paption">您提供的资料珍药材将予以保护，不会挪作他用</li>
                <input type="hidden" id="certifyId" name="certifyId" value="${companyCertify.certifyId!'' }" />
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
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript">
  $(function () {
	  $("#legalizeCompany").Validform({
		    tiptype:4,
		    ajaxPost:true,
		    dragonfly:true,
		    datatype:{
     			"vt":function(gets,obj,curform,regxp){
     				var start = $("#licenceStartdate").val();
     				var end = $("#licenceEnddate").val();
     				if(gets==""){
     					return false;
     				}
     				if(start==""){
     					return "开始时间不能为空!";
     				}
     				return true;
     			}
		    },
		    beforeSubmit:function(formObj){
		    	$("#subBtn").val("提交中...");
				$("#subBtn").attr("disabled","disabled");
				return true;
			},
			callback:function(data){
				if(data.status=="yes"){
			        bghui();
			        Alert({
			            str:'提交资料成功！我们会在1-3个工作日内为您审核，请耐心等待！',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                classname:'btn-style',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
							        window.location.replace("getLegalize");
			                    }
			                }
			            }]
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
			    //add end
				}else if(data.status=='double'){
		    		bghui();
			        Alert({
			            str:'提交失败！不可重复提交！'
			        });
	    		}else{
					bghui();
			        Alert({
			            str:'提交失败！请刷新后重新提交！',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                classname:'btn-style',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
			                        $("#subBtn").val("确认，提交");
			        				$("#subBtn").removeAttr("disabled");
			                    }
			                }
			            }]
			        });
				}
			}
	  });
  });
  function fileUpload(){
	  $("#loading").show();
		 //ajax请求后台
		 var pic = $("#file").val();
		 $("#hidFile").val(pic);
		 if(!/.(gif|jpg|jpeg|png|bmp|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
	        $("#loading").hide();
			bghui();
	        Alert({
	            str:'请选择图片!'
	        });
			return false;
		 }
		 $.ajaxFileUpload({
			url:'/uploadImg?type=6', 
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
					$("#file").parent().next().next().html('');
					$("#file").parent().next().next().addClass('right');
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
	      });
  }
  function fileUpload1(){
	  $("#loading1").show();
		 //ajax请求后台
		 var pic = $("#file1").val();
		 if(!/.(gif|jpg|jpeg|png|bmp|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
	        $("#loading1").hide();
			bghui();
	        Alert({
	            str:'请选择图片!'
	        });
			return false;
		 }
		 $.ajaxFileUpload({
			url:'/uploadImg?type=9', 
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
					$("#file1").parent().next().html('');
					$("#file1").parent().next().addClass('right');
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
	      });
  }
</script>

</body>
</html>