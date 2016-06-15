<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-个人认证查看</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
 	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
 	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/list.css"  />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
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
        <form id="legalizeCompany">
            <ul class="form-2 mt15">
                <li>
                    <label><i class="red">*</i>真实姓名：</label>
                    <span class="f14">${ucPersonCertify.name!'' }</span>
                     <!--<span class="col_999 ml50">请填写身份证上的姓名，它也是您在本站提现时使用的银行卡开户姓名，请谨慎！</span> -->
                </li>
                <li>
                	<label><i class="red">*</i>身份证：</label>
               	 	<#assign status=(ucPersonCertify.status!'')>
                	<#switch status>
                		<#case 0>
                			认证中
                			<#break>
                		<#case 1>
                			已认证
                			<#break>
                		<#case 2>
                			未认证
                			<#break>
                		<#default>
                			其他
                	</#switch>
                </li>
               
                <li>
                    <label><i class="red">*</i>身份证号：</label>
                    <span class="f14">${ucPersonCertify.idCard!'' }</span>
                </li>
               <!--  <li>
                    <label><i class="red">*</i>上传身份证：</label>
                    <span class="col_999">照片支持jpg、gif、bmp、png格式，图片最大不要超过1MB</span>
                </li> -->
                <#if (ucPersonCertify.status==1)>
                	<#else>
                	<li class="coption">
                    <p class="fl ml10">身份证正面照<br/>
                        <span class="col_999"> 需要能看清姓名、身份证号码等信息</span>
                        <span class="img">
                            <#if (ucPersonCertify.certifyId??)>
                        		<img style="width:250px;height:150px;" src="/verifyImg?certifyId=${ucPersonCertify.certifyId }&type=2" />
                        	<#else>
                        		<img style="width:250px;height:150px;" src="${RESOURCE_CSS}/images/jzt-user-center/shenfz_normal.jpg" />
                        	</#if>
                        </span>
                        <a href="#" name="see" onclick="showPic('${ucPersonCertify.certifyId }',2)" class="btn-hui1 middle dis-in-bk">点击查看大图</a>

                    </p>
                    <p class="fl ml50">身份证反面照<br/>
                        <span class="col_999"> 需要能看清签发机关、有效期限等信息</span>
                        <span class="img">
                        	<#if (ucPersonCertify.certifyId??)>
                        		<img style="width:250px;height:150px;" src="/verifyImg?certifyId=${ucPersonCertify.certifyId }&type=5" />
                        	<#else>
                        		<img style="width:250px;height:150px;" src="${RESOURCE_CSS}/images/jzt-user-center/shenfz_normal.jpg" />
                        	</#if>
                        </span>
                        <a href="#" name="see" onclick="showPic('${ucPersonCertify.certifyId }',5)" class="btn-hui1 middle dis-in-bk">点击查看大图</a>
                    </p>
                </li>
                </#if>
            </ul>
        </form>
    </div>

</div>

<!-- 弹层 -->
<div class="popup-box" id="picbbox">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
</div>
<!-- 弹层  over -->

<!-- 底部  -->
<#include "common/footer.ftl" /> 
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_CSS}/js/imgView/jquery.imageView.js"></script>
<script type="text/javascript" src="${RESOURCE_CSS}/js/jzt-user-center/view_person.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript">
function showPic(certifyId,type){
	$('#picPath').show();
    var html = '<div class="bghui"></div>';
    var height = $(document).height();
    $('body').append(html);
    $('.bghui').css('height',height);
	$('#showImg').attr('src','verifyImg?certifyId=' + certifyId + '&type=' + type);
	$('#picbbox').imageView({width:600, height:400});
    return false;
}
</script>
</body>
</html>