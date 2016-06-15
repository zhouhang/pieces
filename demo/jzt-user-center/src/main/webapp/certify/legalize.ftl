<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-认证首页</title>
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
    <div class="hy-right fr">
        <div class="border-1 box-tip bg-cof5">
            <p><i class="tip-icon"></i>请您填写真实信息进行认证，珍药材将对您的信息进行严格审核。<br/>
                <span>身份认证：是由珍药材提供的一项身份识别服务，只有完成实名认证才可进行交易，珍药材倡导诚实交易。</span><br/>
                <span>个人会员必须进行个人身份认证，企业需做企业认证，每位会员必须以一个身份参与珍药材的各种行为活动。</span>
            </p>
        </div>
        <div class="hr-red mt10"></div>
        <!--认证首页模块-->
        <div class="fl">
            <ul class="legalize-list">
            	<#if (!userStatus??&&companyStatus!=0&&companyStatus!=1)||userStatus==0||(userStatus==1&&companyStatus!=0&&companyStatus!=1)||(userStatus==2&&companyStatus!=0&&companyStatus!=1)>
	                <li>
	                    <i class="icon-person"></i>
	                    <span class="status"><strong>个人身份认证</strong><br/>
	                        <#if !userStatus??>
		                        <i class="legalize normal">实</i><b class="col_555"> 未认证</b>
		                    </#if>
	                        <#if userStatus==0>
		                        <i class="legalize cur">实</i><b class="col_blue"> 待审核</b>
		                    </#if>
		                    <#if userStatus==1>
		                        <i class="legalize legalizing">实</i><b class="col_green"> 审核通过</b>
		                    </#if>
		                    <#if userStatus==2>
		                        <i class="legalize miss">实</i><b class="col_red"> 已驳回</b>
		                    </#if>
	                    </span>
	                    <#if userStatus==0||userStatus==1>
		                    <a class="btn-red1 f14" href="/checkPersonCertify?certifyId=${ucPersonCertify.certifyId }">查看详情</a>
	                    </#if>
	                    <#if !userStatus??>
		                    <a class="btn-red1 f14" href="/getLegalizePerson">认 证</a>
	                    </#if>
	                    <#if userStatus==2>
		                    <a class="btn-red1 f14" href="/getLegalizePerson?certifyId=${ucPersonCertify.certifyId }">重新提交</a>
	                    </#if>
	                    <#if userStatus==1&&!companyStatus??>
	                    	<a class="shengji" href="/getLegalizeCompany">去升级企业认证</a>
	                    </#if>
	                    <#if userStatus==1&&companyStatus==2>
	                    	<a class="shengji" href="/getLegalizeCompany?certifyId=${companyCertify.certifyId }">重新提交资料！</a>
	                    </#if>
		                <#if userStatus==2>
			            	<p class="ml28 mt20">您提交的个人资料审核未通过:<b class="col_red">${personCheckRemark["${ucPersonCertify.rejectMemo}"]}</b></p>
		                </#if>
	                    <#if userStatus==1&&companyStatus==2>
		            		<p class="ml28 mt20">您提交的企业资料审核未通过:<b class="col_red">${companyCheckRemark["${companyCertify.rejectmemo}"]}</b></p>
	                	</#if>
	                </li>
            	</#if>
            	<#if (!companyStatus??&&userStatus!=0&&userStatus!=1)||companyStatus==0||companyStatus==1||(companyStatus==2&&userStatus!=0&&userStatus!=1)>
	               	<li>
	                    <i class="icon-company"></i>
	                    <span class="status"><strong>企业认证</strong><br/>
		                    <#if !companyStatus??>
		                        <i class="legalize normal">实</i><b class="col_555"> 未认证</b>
		                    </#if>
	                        <#if companyStatus==0>
		                        <i class="legalize cur">实</i><b class="col_blue"> 待审核</b>
		                    </#if>
		                    <#if companyStatus==1>
		                        <i class="legalize legalizing">实</i><b class="col_green"> 审核通过</b>
		                    </#if>
		                    <#if companyStatus==2>
		                        <i class="legalize miss">实</i><b class="col_red"> 已驳回</b>
		                    </#if>
	                    </span>
	                    <#if companyStatus==0||companyStatus==1>
		                    <a class="btn-red1 f14" href="/checkCompanyCertify?certifyId=${companyCertify.certifyId }">查看详情</a>
	                    </#if>
	                    <#if !companyStatus??>
		                    <a class="btn-red1 f14" href="/getLegalizeCompany">认 证</a>
	                    </#if>
	                    <#if companyStatus==2>
		                    <a class="btn-red1 f14" href="/getLegalizeCompany?certifyId=${companyCertify.certifyId }">重新提交</a>
	                    </#if>
		                <#if companyStatus==2>
			            	<p class="ml28 mt20">您提交的企业资料审核未通过:<b class="col_red">${companyCheckRemark["${companyCertify.rejectmemo}"]}</b></p>
		                </#if>
	                </li>
            	</#if>
            </ul>
        </div>

        <div class="kf-box fl">
            <h3><strong>您还可联系</strong></h3>
            <p><i class="phone dis-in-bk"></i> 客服热线：<br/>
            <strong>400-10-54315</strong></p>
            <p><i class="qq dis-in-bk"></i> 客服QQ：<br/>
            <strong>4001054315</strong></p>
        </div>

        <!--认证首页模块 over-->
    </div>

</div>
<!-- 底部  -->
<#include "common/footer.ftl" /> 
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
</body>
</html>