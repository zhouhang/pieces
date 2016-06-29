<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-基本资料</title>
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#include "common/header.ftl" /> 
<div class="area-1200 clearfix">
	<#include "common/left.ftl" /> 

	<div class="hy-right fr">
	        <h2 class="title"><i class="allowup dis-in-bk fr"></i>基本资料</h2>
	        <div class="content" style="display: block;">
	        <ul class="form">
	            <li>
	                <label class="lab">登录名：</label>
	                <p class="xm">${(user.userName)!''}</p>
	                <span class="tip2">（可用于登录，请妥善保管）</span>
	            </li>
	            <li>
	                <label class="lab">登录密码：</label>
	                <p class="xm">已设 </p>
	                <span class="tip2"><a style="color:#0088cc;" href="javascript:void(0);">[修改]</a></span>
	                <div class="re-message" id="rePassword"><!-- 修改密码 -->
	                    <span class="sj"></span>
	                    <form class="rePasswordForm" action="/my_material/reset_password">
		                    <p>
		                        <label><i class="red">*</i>原密码：</label>
		                        <span>
			                        <input type="password" class="text text-1 text-Valid" name="oldPassword" ajaxurl="/my_material/match_password" datatype="todo,pwd" todo="newPassword" nullmsg="请输入原密码！"/>
			                        <span></span>
		                        </span>
		                    </p>
		                    <p>
		                        <label><i class="red">*</i>新密码：</label>
		                        <span>
			                        <input type="password" class="text text-1 text-Valid" name="newPassword" datatype="pwd,noteq" nullmsg="请输入新密码！" noteqto="oldPassword" noteqmsg="新密码与原密码不能一致！"/>
			                        <span></span>
		                        </span>
		                    	
		                    </p>
		                    <p>
		                        <label><i class="red">*</i>确认密码：</label>
		                        <span>
			                        <input type="password" class="text text-1 text-Valid" name="surePassword" datatype="*" recheck="newPassword" nullmsg="请再输入一次新密码！" errormsg="您两次输入的账号密码不一致！"/>
			                        <span></span>	
		                        </span>
		                    	
		                    </p>
		                    <p class="mt5">
		                        <label></label>
		                        <span>
		                            <input type="button" class="yellow-btn2" value="确定"  id="rePasswordBtn"/>
		                            <input type="button" class="btn-hui" value="取消" id="rePasswordResetBtn"/>
		                        </span>
		                    </p>
	                    </form>
	                </div>
	            </li>
	            <li>
	                <label class="lab"><i class="red">*</i> 手机号码：</label>
	                <p class="xm">${(user.mobile)!'' }</p>
	                <a style="color:#0088cc;" href="/authenticateMobile" target="_blank">[更改号码]</a>
	            </li>
	            <li>
	                <label class="lab"><i class="red">*</i> 公司/姓名：</label>
	                <p class="xm">${(user.companyName)!''}</p>
	                <span class="tip2"><a style="color:#0088cc;;" href="javascript:void(0);">[修改]</a></span>
	                <b>个体经营者或者个人经营者可填写姓名</b>
	                <div class="re-message" id="reCompany"><!-- 修改公司名称 -->
	                    <span class="sj"></span>
	                    <form class="reCompanyForm" action="/my_material/reset_company">
		                    <p>
		                        <label><i class="red">*</i>原公司/真实姓名：</label>
		                        <span>${(user.companyName)!''}<input type="hidden" name="oldCompanyName" value="${(user.companyName)!''}"/></span>
		                    </p>
		                    <p>
		                        <label><i class="red">*</i>新公司/真实名称：</label>
		                        <span><input type="text" class="text text-1 text-Valid" name="newCompanyName" datatype="*2-16,noteq" nullmsg="请输入新公司名或姓名！" errormsg="新公司名或姓名在2~16位之间！" noteqto="oldCompanyName" noteqmsg="新公司名或姓名与原来的不能一致！"/><span></span> </span>
		                    </p>
		                    <p class="mt5">
		                        <label></label>
		                        <span>
		                            <input type="button" class="yellow-btn2" value="确定" id="reCompanyBtn" />
		                            <input type="button" class="btn-hui" value="取消" id="reCompanyResetBtn" />
		                        </span>
		                    </p>
	                    </form>
	                </div>
	            </li>
	            <li>
	                <label class="lab">邮箱：</label>
	                <p class="xm">${(user.email)!'暂无'}</p>
	                <#if (user.email)??>
<!-- 	                	<span class="tip2"><a style="color:#0088cc;;" href="javascript:void(0);">[修改]</a></span> -->
	                	<a style="color:#0088cc;" href="/userEmailOpt/optEmail?optType=2" target="_blank">[修改]</a>
	                <#else>
<!-- 	                	<span class="tip2"><a style="color:#0088cc;" href="/userEmailOpt/modEmailWay">[设置]</a></span> -->
	                	<a style="color:#0088cc;" href="/authenticateMobile/authenticateIdentity?optType=1" target="_blank">[设置]</a>
	                	<b class="tips"><i></i>请尽快设置邮箱，方便日后找回密码时使用！</b>
	                </#if>
	                <div class="re-message" id="setEmail"><!-- 设置邮箱 -->
	                    <span class="sj"></span>
	                    <form class="setEmailForm" action="/my_material/set_email">
		                    <p>
		                        <label><i class="red">*</i>邮箱：</label>
		                        <span><input type="text" class="text text-1 text-Valid" name="newEmail" datatype="e" nullmsg="请输入邮箱！" errormsg="输入的邮箱格式不正确！"/><span></span></span>
		                        
		                    </p>
		                    <p class="mt5">
		                        <label></label>
		                        <span>
		                            <input type="button" class="yellow-btn2" value="确定"  id="setEmailBtn"/>
		                            <input type="button" class="btn-hui" value="取消" id="setEmailResetBtn"/>
		                        </span>
		                    </p>
	                    </form>
	                </div>
	                <div class="re-message" id="reEmail"><!-- 修改邮箱 -->
	                    <span class="sj"></span>
	                    <form class="reEmailForm" action="/my_material/reset_email">
	                    	<input type="hidden" name="oldEmail" value="${(user.email)!''}"/>
		                    <p>
		                        <label><i class="red">*</i>登录密码：</label>
		                        <span><input type="password" class="text text-1 text-Valid" name="oldPassword" ajaxurl="/my_material/match_password" datatype="*6-16" nullmsg="请输入登录密码！" errormsg="密码范围在6~16位之间！"/><span></span></span>
		                    	
		                    </p>
		                    <p>
		                        <label><i class="red">*</i>新邮箱：</label>
		                        <span><input type="text" class="text text-1 text-Valid" name="newEmail" datatype="e,noteq" nullmsg="请输入新邮箱！" errormsg="输入的邮箱格式不正确！" noteqto="oldEmail" noteqmsg="新邮箱与原来的不能一致！"/><span></span></span>
		                    </p>
		                    <p class="mt5">
		                        <label></label>
		                        <span>
		                            <input type="button" class="yellow-btn2" value="确定"  id="reEmailBtn"/>
		                            <input type="button" class="btn-hui" value="取消" id="reEmailResetBtn"/>
		                        </span>
		                    </p>
	                    </form>
	                </div>
	            </li>
	        </ul>
	</div>
	
	<div class="mt20"></div>
        <h2 class="title"><i class="allowup dis-in-bk down fr"></i> 经营信息<span class="cap">（完善经营信息，能使您获得更多的中药材贸易机会）</span></h2>
        <div class="content">
        	<form action="/userDealIn/addUserDealInfo" id="scopeForm" method="post">
            <ul class="form ie">
                <li>
                    <label class="lab"><i class="red">*</i> 业务类型：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="1" <#if ((dealInfo.dealType)!0)==1>checked=checked</#if> /> 我买药材</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="2" <#if ((dealInfo.dealType)!0)==2>checked=checked</#if> /> 我卖药材</span>
                    <span class="dis-in-bk"><input type="radio" name="dealType" value="3" <#if ((dealInfo.dealType)!0)==3>checked="checked"</#if> /> 我既买药材，也卖药材</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealType-msg"></span>
                </li>
                <li>
                    <label class="lab"><i class="red">*</i> 经营身份：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="1" <#if ((dealInfo.dealRole)!0)==1 >checked="checked"</#if> /> 产地经营户</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="2" <#if ((dealInfo.dealRole)!0)==2 >checked="checked"</#if> /> 市场经营大户</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="3" <#if ((dealInfo.dealRole)!0)==3 >checked="checked"</#if> /> 中药饮片厂</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="4" <#if ((dealInfo.dealRole)!0)==4 >checked="checked"</#if> /> 中成药厂</span><br/>
                    <label class="lab"></label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="5" <#if ((dealInfo.dealRole)!0)==5 >checked="checked"</#if> /> 种植合作社</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="6" <#if ((dealInfo.dealRole)!0)==6 >checked="checked"</#if> /> 药农</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="7" <#if ((dealInfo.dealRole)!0)==7 >checked="checked"</#if> /> 其他</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealRole-msg"></span>
                </li>
                <li>
                    <label class="lab breed fl"><i class="red">*</i> 主营品种：</label>
                    <p class="sty-4 dis-in-bk fl" id="breed-p">
                    <#if dealBreeds?size=0>
	                   <span name="breedInputlist" class="relative breedAdd">
	                       <span class="dis-in-bk input-text">
	                           <input type="hidden" name="breeds[0].breedId" value=""/>
	                           <input id="breedText" name="breeds[0].breedName" class="text-sty2 col_333" value="">
	                           <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span> </i>
	                       </span>
	                       <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
	                   </span>
	                   <span name="breedInputlist" class="relative breedAdd">
	                       <span class="dis-in-bk input-text">
	                           <input type="hidden" name="breeds[1].breedId" value=""/>
	                           <input id="breedText" name="breeds[1].breedName" class="text-sty2 col_333" value="">
	                           <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
	                       </span>
	                       <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
	                   </span>
	
	                   <span name="breedInputlist" class="relative breedAdd">
	                       <span class="dis-in-bk input-text">
	                           <input type="hidden" name="breeds[2].breedId" value=""/>
	                           <input id="breedText" name="breeds[2].breedName" class="text-sty2 col_333" value="">
	                           <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
	                       </span>
	                       <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
	                   </span>
	
	                   <span name="breedInputlist" class="relative breedAdd">
	                       <span class="dis-in-bk input-text name="breedText"">
	                          <input type="hidden" name="breeds[3].breedId" value=""/>
	                           <input id="breedText" name="breeds[3].breedName" class="text-sty2 col_333" value="">
	                           <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
	                       </span>
	                       <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
	                   </span>
                    <#else>
                    	<#list dealBreeds as dbs>
                    		<span name="breedInputlist" class="relative breedAdd">
		                       <span class="dis-in-bk input-text">
		                           <input type="hidden" name="breeds[${dbs_index }].breedId" value="${dbs.breedId!''}"/>
		                           <input id="breedText" name="breeds[${dbs_index }].breedName" class="text-sty2 col_333" value="${dbs.breedName!''}">
		                           <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
		                       </span>
		                       <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
		                   </span>
                    	</#list>
                    </#if>
                   </p>
                </li>
                <li class="clearfix">
                    <label class="lab"></label>
                    <a class="add dis-in-bk" href="#" name="addBreed"> <strong>+</strong> 继续增加品种</a>
                    <b class="tips ma"><i></i>请至少输入一个中药材品种，输入多个品种时可以获得更多的贸易机会。</b>
                    &nbsp;&nbsp;<span class="Validform_checktip" id="breed-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab"><i class="red">*</i> 经营规模：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="1" <#if ((dealInfo.scale)!0)==1 >checked="checked"</#if> /> 100万以下/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="2" <#if ((dealInfo.scale)!0)==2 >checked="checked"</#if> /> 100万—500万/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="3" <#if ((dealInfo.scale)!0)==3 >checked="checked"</#if> /> 500万—1000万/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="4" <#if ((dealInfo.scale)!0)==4 >checked="checked"</#if> /> 1000万—5000万/年</span><br/>
                    <label class="lab"></label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="5" <#if ((dealInfo.scale)!0)==5 >checked="checked"</#if> /> 5000万以上/年</span>
                    <span class="dis-in-bk">( 年采购金额或销售金额 ）</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="scale-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab"><i class="red">*</i> 所在地址：</label>
                    <div class="select-bg">
	                    <span>
		                    <select name="provinceCode" id="provinceCode" class="col_333">
		                    	<option selected value="">请选择省份</option>
		                    	<#if provinceCode??>
		                    		<#list provinceCode as p>
		                    			<option value="${p.code!'' }" <#if dealInfo??><#if dealInfo.provinceCode==((p.code)!'') >selected=selected</#if> </#if> >${p.name!'' }</option>
		                    		</#list>
		                    	</#if>
		                    </select>
	                    </span>
                    </div>
                    <div class="select-bg">
	                    <span>
		                    <select name="cityCode" id="cityCode" class="col_333">
		                    	<option value="">请选择城市</option>
		                    	<#if cityCodeList??>
		                    		<#list cityCodeList as ccl>
		                    			<option value="${ccl.code!'' }" <#if dealInfo??><#if dealInfo.cityCode==((ccl.code)!'')>selected=selected</#if> </#if> >${ccl.name!'' }</option>
		                    		</#list>
		                    	</#if>
		                    </select>
	                    </span>
                    </div>
                    <span class="dis-in-bk" id="afterProvince">( 公司注册所在地 ）</span><span id="provinceCode-msg"></span><br/>
                    <label class="lab"></label>
                    <input name="address" class="text-sty2 text-wid355 mt10" placeholder="请填写街道地址" type="text" value="<#if dealInfo??>${dealInfo.address!'' }</#if>"/>
                	<span id="address-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab">邮编：</label>
                    <input name="zipCode" id="zipCode" class="text-sty2 text-wid210" type="text" value="<#if dealInfo??>${dealInfo.zipCode!'' }</#if>"/>
                	<span id="zipCode-msg"></span>
                </li>
                <li class="mt5">
                    <label class="lab">传真：</label>
                    <input name="areaCode" class="text-sty2 text-wid45" placeholder="区号" type="text" value="<#if dealInfo??>${dealInfo.areaCode!'' }</#if>"/> - <input name="fax" class="text-sty2" placeholder="传真号码" type="text" value="<#if dealInfo??>${dealInfo.fax!'' }</#if>" />
                </li>
                <li class="mt25"><div align="center" class="relative"><input type="submit" id="dealBtn" class="btn btn-red-grad big" name="save" value="保 存" /></div> </li>
            </ul>
           </form>
        </div>
        
         <div class="mt20"></div>
        <h2 class="title"><i class="allowup down dis-in-bk fr"></i> 联系人信息<span class="cap">（完善联系人信息，能使您获得更多的中药材贸易服务）</span></h2>
        <div class="content" name="len">
        <form action="/userDealIn/saveContacterInfo" method="post" id="completeContacterInfoForm">
        <#if ucContactLst?size=0 >
	            <ul id="dynamic_ul_0" class="form">
	                <li>
	                    <label class="lab"><i class="red">*</i> 姓名：</label>
	                    <input class="text-sty2 col_333 mr10" name="ucUserContact[0].name" value="" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;
	                    <#list sexMap?keys as key> 
	                    	<input id="sex" type="radio" name="ucUserContact[0].sex" value="" <#if key == 1>checked="checked"</#if>/> ${sexMap[key]}&nbsp;&nbsp;&nbsp;
	              	 	</#list>
	                    <span class="Validform_checktip"></span>&nbsp;&nbsp;&nbsp;
	                </li>
	                <li>
	                    <label class="lab">部门：</label>
	                    <input name="ucUserContact[0].department" class="text-sty2 text-wid210 col_333 mr10" value="" />
	                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
	                </li>
	                <li>
	                    <label class="lab">职务：</label>
	                    <input name="ucUserContact[0].job"  class="text-sty2 text-wid210 col_333 mr10" value="" />
	                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
	                </li>
	                <li>
	                    <label class="lab">联系座机：</label>
	                    <input name="ucUserContact[0].phone" class="text-sty2 text-wid210 col_333 mr10" value="" />
	                </li>
	                <li>
	                    <label class="lab"><i class="red">*</i> 联系手机：</label>
	                    <input name="ucUserContact[0].mobile" class="text-sty2 text-wid210 col_333 mr10" value="" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>
	                </li>
	                <li>
	                    <label class="lab">电子邮箱：</label>
	                    <input name="ucUserContact[0].email" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>
	                </li>
	            </ul>
            <#else>
            	<#list ucContactLst as ucl>
		            <ul id="dynamic_ul_${ucl_index }" class="form">
		                <li>
		                    <label class="lab"><i class="red">*</i> 姓名：</label>
		                    <input class="text-sty2 col_333 mr10" name="ucUserContact[${ucl_index }].name" value="${ucl.name!'' }" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;
		                    <#list sexMap?keys as key> 
		                    	<input id="sex" type="radio" name="ucUserContact[${ucl_index }].sex" value="${ucl.sex!1 }" <#if key == (ucl.sex)!1>checked="checked"</#if>/> ${sexMap[key]}&nbsp;&nbsp;&nbsp;
		              	 	</#list>
		                    <span class="Validform_checktip"></span>&nbsp;&nbsp;&nbsp;
		                </li>
		                <li>
		                    <label class="lab">部门：</label>
		                    <input name="ucUserContact[${ucl_index }].department" class="text-sty2 text-wid210 col_333 mr10" value="${ucl.department!'' }" />
		                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
		                </li>
		                <li>
		                    <label class="lab">职务：</label>
		                    <input name="ucUserContact[${ucl_index }].job"  class="text-sty2 text-wid210 col_333 mr10" value="${ucl.job!'' }" />
		                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
		                </li>
		                <li>
		                    <label class="lab">联系座机：</label>
		                    <input name="ucUserContact[${ucl_index }].phone" class="text-sty2 text-wid210 col_333 mr10" value="${ucl.phone!''}" />
		                </li>
		                <li>
		                    <label class="lab"><i class="red">*</i> 联系手机：</label>
		                    <input name="ucUserContact[${ucl_index }].mobile" class="text-sty2 text-wid210 col_333 mr10" value="${ucl.mobile!'' }" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>
		                </li>
		                <li>
		                    <label class="lab">电子邮箱：</label>
		                    <input name="ucUserContact[${ucl_index }].email" class="text-sty2 text-wid210 col_333 mr10" value="${ucl.email!'' }" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>
		                </li>
		                <#if (ucl_index>0)>
		                	<li class="mt20"><div align="center"><span class="blue f14 text-sty-udline hand" id="removeContact">删 除</span></div> </li>
		                </#if>
		            </ul>
	            </#list>
            </#if>
            <div name="append">
                <div class="mt25 save relative">
                    <span class="fr mr60"><a href="#" class="add dis-in-bk" name="addContact"> <strong>+</strong> 继续添加联系人</a></span>
                    <div align="center"><input type="submit" id="save" class="btn btn-red-grad big" value="保 存" /></div>
                </div>
            </div>
       </form>
        </div>
     </div>   
</div>
<#include "common/footer.ftl" /> 
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript">
	//修改前后的内容不能一致的check(Validform的扩展属性)
	$.Datatype.noteq = function(gets,obj,curform,regxp){
		var value = $.trim(gets);
		var name = obj.attr('noteqto');
		var value1 = $.trim($('input[name='+ name +']', curform).val());
		if(value === value1){
			return obj.attr('noteqmsg');
		} else {
			return true;
		}
	}
	
	$.Datatype.todo = function(gets,obj,curform,regxp){
		var name = obj.attr('todo');
		if($('input[name='+ name +']', curform)[0].value != ''){
			$('input[name='+ name +']', curform)[0].validform_lastval = '';
			$('input[name='+ name +']', curform).trigger("blur");
		}
		return true;
	}
	
	$.Datatype.pwd = function(gets,obj,curform,regxp){
		var reg1=/^[a-zA-Z0-9\\pP]{6,16}$/;
		reg2=/^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
		var _newPwd = $.trim(gets)
		if(!reg1.test(_newPwd)){
			return "密码为6-16个字符!";
		}
		/* if(reg2.test(_newPwd)){
			return "需包含数字、字母或符号至少两种！";
		} */
		return true;
	}
	
	function validSubmit(formName, fn){
		var form = "." + formName + "Form";
		var validForm = $(form).Validform({
	    	btnSubmit:"#" + formName + "Btn",
	    	btnReset:"#" + formName + "ResetBtn",
	        tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.next();
					//objtip.css('marginLeft','115px');
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			},
			dragonfly:true,
			ignoreHidden:true,
	        showAllError:true,
	        ajaxPost:true,
	        callback:function(data){
	        	if('y' == data.status){
	        		Alert({
	        			str:data.info,
	        			buttons:[{
	        				name:'确定',
	        				id:'1',
	        				classname:'btn-style',
	        				ev:{click:function(){
		        	        		disappear();
			    					$(".bghui").remove();
			    					validForm.resetForm();
			    	        		$('.Validform_checktip', $(form)).removeAttr('style');
			    	        		$('#'+ formName).hide();
			    	        		$('#'+ formName).siblings('.tip2').children('a').removeClass('disabled').css('color','#0088cc');
			    	        		if(fn){
			    	        			if(data.objval){
			    	            			fn(formName, data.objval);
			    	        			}
			    					}
	        					}
	        				}
	        			}]
	        		});
	        		
	        	} else if('n' == data.status){
	        		//请求失败
	        		bghui();
	        		Alert({str:data.info});
	        	} else {
	        		bghui();
	        		Alert({str:"未知错误，修改失败！"});
	        	}
	        }
	    });
		
		return validForm;
	}

	$(function(){
		//修改弹层
	    $('.form li span a').each(function(){
	        $(this).click(function() {
	        	if($(this).hasClass('disabled')){
	        		return false;
	        	}
	            var reMessage = $(this).parents('li').children('.re-message');
	            if(reMessage.length == 2)
	            {
	                if($(this).text()=='[设置]')
	                {
	                    $(this).parents('li').children('.re-message:eq(0)').show();
	                    $(this).parents('li').children('.re-message:eq(1)').hide();
	                    $(this).parent().siblings('b').hide();
	                }
	                if($(this).text()=='[修改]')
	                {
	                    $(this).parents('li').children('.re-message:eq(1)').show();
	                    $(this).parents('li').children('.re-message:eq(0)').hide();
	                }
	            }
	            else{
	                $(this).parents('li').children('.re-message').show();
	            }
	            $(this).addClass('disabled');
	            $(this).css('color','#bdbdbd');
	        	return false;
	        })
	    });
	    
	    //取消
	    $('.re-message input[class=btn-hui]').click(function(){
	    	$('.Validform_checktip', $(this).parents('.re-message')).empty().removeAttr('style');
	        $(this).parents('.re-message').hide();
	        var a = $(this).parents('.re-message').siblings('.tip2').children('a');
	        a.removeClass('disabled');
	        a.css('color','#0088cc');
	        if(a.text()=='[设置]'){
	        	a.parent().siblings('b').show();
	        } else if(a.text()=='[更改号码]') {
		        clearTimeout(t);
		        $("#getMobileCode").removeAttr("disabled");
				$("#getMobileCode").val("获取验证码");
	        }
	    });
	    
	    //修改密码
	    validSubmit('rePassword');
	    
	    //修改手机号
	    validSubmit('reMobile', function(formName, objval){
	    	clearTimeout(t);
	    	$("#getMobileCode").removeAttr("disabled");
			$("#getMobileCode").val("获取验证码");
	    	$('#'+ formName).siblings('.xm').html(
	    			objval.substring(0, 3) + '****' + objval.substring(objval.length - 4, objval.length));
	    });
	    
	    //修改公司名称
	    validSubmit('reCompany', function(formName, objval){
	    	$('#'+ formName).siblings('.xm').html(objval);
	    	$('p:eq(0) span:eq(0)', '#'+ formName).html(objval);
	    });
	    
	    //设置邮箱
	    validSubmit('setEmail', function(formName, objval){
	    	$('#'+ formName).siblings('.xm').html(objval);
	    	$('input[name=oldEmail]', $('#'+ formName).siblings('#reEmail')).val(objval);
	    	$('#'+ formName).siblings('.tip2').children('a').text('[修改]');
	    });
	    
	    //修改邮箱
	    validSubmit('reEmail', function(formName, objval){
	    	//$('#'+ formName).siblings('.xm').html(objval);
	    	alert("aaa");
	    	location.href="";
	    });
	    
		//获取手机短信校验码
		$("#getMobileCode").click(function(){
			var memberMobile = $(".reMobileForm input[name=newMobile]").val();
			var oldMemberMobile = $(".reMobileForm input[name=oldMobile]").val();
			if(memberMobile==null || memberMobile==""){
				bghui();
				Alert({str:"请输入新手机号码！"});
				return;
			}
			var reg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}|177[0-9]{8}$/;
			if(!reg.test(memberMobile)){
				bghui();
				Alert({str:"请输入11位正确的手机号码！"});
				return;
			}
			if(memberMobile == oldMemberMobile){
				bghui();
				Alert({str:"新手机号码与原手机号码不能一致！"});
				return;
			}
			
			time(60,"getMobileCode");
			$.post("/findBackPwd/getMobileCode",{memberMobile:memberMobile});
		});
		//获取手机短信校验码(支付密码)
		$("#getCode").click(function(){
			time(60,"getCode");
			$.post("/findBackPwd/getCode");
		});
	})
	
	//获取短信验证码后，60s可以重新发送 
	var t;
	function time(i,id){
		i-=1 ;
		$("#"+id).val('(' +i+ ')秒后再获取');
		$("#"+id).attr("disabled","disabled");
		if(i==0){
			$("#"+id).removeAttr("disabled");
			$("#"+id).val("重新获取验证码");
			return;
		}
		t = setTimeout("time("+ i +",'"+id+"')", 1000);
	}
	
	//经营信息(start)----------------------------------------------------------------------
	$(function(){
        //折叠效果
        $('.allowup').on('click',function(){
            var Cont = $(this).parent('h2').next('.content');
            if(Cont.is(':hidden')){
                $(this).removeClass('down');
                Cont.slideDown();
            }
            else{
                $(this).addClass('down');
                Cont.slideUp();
            }
        });
        
        $('input[type=text]').keydown(function(){
            $(this).css('color','#333');
        });
        
        $('input[type=text]').blur(function(){
        	if($(this).val() != ''&& $(this).val()!=null){
        		$(this).css('color','#333');
        	}
        	else{
        		$(this).css('color','#c8c8c8');
        	}
        });
        
        
        function removeBg(selector){
        	var _text = $(selector).val();
        	if(_text !=null &&_text != ''){
        		$(selector).css('color','#333');
        	}
        }
        removeBg('input[name=address]');
        removeBg('input[name=zipCode]');
        removeBg('input[name=areaCode]');
        removeBg('input[name=fax]');

        //主营品种添加删除
        $('body').delegate('[name=breedInputlist]','mouseover',function(){
            var Text = $(this).children().children('input[id=breedText]').val();
            if(!Text == ''){
                $(this).children('.remove-bg').show();
            }
        });
        $('body').delegate('[name=breedInputlist]','mouseout',function(){
            $(this).children('.remove-bg').hide();
        });

        var index = $('#breed-p').children('span').length;
        $('a[name=addBreed]').on('click',function(){
        	var html = 
            	'<span id="span_'+index+'" class="relative breedAdd" name="breedInputlist">'+
            	'<span class="dis-in-bk input-text">'+
            	'<input type="hidden" name="breeds['+index+'].breedId" value=""/>'+
            	'<input id="breedText" name="breeds['+index+'].breedName" class="text-sty2 col_333" value="">'+
            	'&nbsp;<i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>'+
            	'</span><span class="remove-bg" style="display: none;">'+
            	'<a class="remove" name="remove" href="#">删除</a></span></span>';
            $(this).parent().prev().children('p').append(html);
            index ++;
            return false;
        });

        //alert($('a[name=remove]'));
        $('.form li p').delegate('a[name=remove]','click',function(){
            $(this).parents('span[name=breedInputlist]').remove();
            return false;
        });
        $('a[name=remove]').on('click',function(){
            $(this).parents('span[name=breedInputlist]').remove();
            return false;
        });



        //添加联系人
        $('a[name=addContact]').on('click',function(){
        	var dynamic_index = $('a[name=addContact]').parents('div[name=len]').children('form').children('ul').length;
            var contactBox = '<ul id="dynamic_ul_'+dynamic_index+'" class="form">'+
                    '<li>'+
                    '<label class="lab"><i class="red">*</i> 姓名：</label>'+
                    '<input id="userName" type="text" name="ucUserContact['+dynamic_index+'].name" class="text-sty2 col_333 mr10" value="" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;&nbsp;&nbsp;'+
                    '<#list sexMap?keys as key>'+ 
                	'<input type="radio" name="ucUserContact['+dynamic_index+'].sex" value="${key}" <#if key == 1>checked="checked"</#if>/>${sexMap[key]}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
          	 		'</#list>'+
                    '<span class="Validform_checktip" id="name-msg"></span>&nbsp;&nbsp;&nbsp;'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">部门：</label>'+
                    '<input type="text" name="ucUserContact['+dynamic_index+'].department" class="text-sty2 text-wid210 col_333 mr10" value="" />'+
                    '<span class="dis-in-bk">（未做部门分工，可不填写）</span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">职务：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].job" class="text-sty2 text-wid210 col_333 mr10" value="" />'+
                    '<span class="dis-in-bk">（未做部门分工，可不填写）</span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">联系座机：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].phone" class="text-sty2 text-wid210 col_333 mr10" value="" />'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab"><i class="red">*</i> 联系手机：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].mobile" class="text-sty2 text-wid210 col_333 mr10" value="" datatype="mo" errormsg="手机号格式不正确!" />'+
                    ' <span class="Validform_checktip" id="mobile"></span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">电子邮箱：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].email" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>'+
                    '<span class="Validform_checktip"></span>'+
                    '</li>'+
                    '<li class="mt20"><div align="center"><span class="blue f14 text-sty-udline hand" id="removeContact">删 除</span></div> </li>'+
                    '</ul>';
            var len = $(this).parents('div[name=len]').children('form').children('ul').length;
            if(len<=4){
                $(this).parents('div[name=len]').children('form').children('ul').last().after(contactBox);
                len++;
                $(this).css('color','#0088cc');
            }
            else
            {
                $(this).css('color','#666');
            }
            dynamic_index++;
            return false;
        });
        $('body').delegate('span[id=removeContact]','click',function(){
            $('[name=addContact]').css('color','#0088cc');
            if($(this).parents('[name=len]').children('form').children('ul').length>1){
                $(this).parents('.form').remove();
            }
        });

        //保存成功
        function save(){
            var Alert = '<span class="alert"><i></i>保存成功！</span>';
            $('input[name=save]').parent('div').append(Alert);
        }
    });
    function remove(){
        $('.alert').remove();
        setTimeout('remove()', 5000);
    }
    remove();
	//经营信息(end)---------------------------------------------------------------
	
	$(function(){
		//选着省份获取城市
		$("#provinceCode").change(function(){
			var _pCode = $("#provinceCode").val();
			var scity = $("#cityCode");
			if(_pCode == null || _pCode == ''){
				scity.html('');
				scity.html('<option value="">请选择城市</option>');
				$('#afterProvince').after('<span id="provinceCode-msg"></span>');
				return;
			}
			$('#provinceCode-msg').remove();
			$.ajax({
				type:'POST',
				url:'/userDealIn/getCity',
				data:'provinceCode=' + _pCode,
				success:function(msg){
					if(msg.ok){
						var len = msg.obj.length;
						scity.html('');
						for(var i=0;i<len;i++){
							scity.append('<option value=' + msg.obj[i].code +'>' + msg.obj[i].name + '</option>');
						}
					}else{
						scity.html('');
						scity.html('<option value="">请选择城市</option>');
					}
					
				}
			});
		});
		
		//经营信息表单提交
		$("#scopeForm").Validform({
			tiptype:4,
			dragonfly:true,
			showAllError:true,
			ajaxPost:true,
			callback:function(data){
				if(data.status==1){
					 var Alert = '<span class="alert"><i></i>保存成功！</span>';
			         $('input[name=save]').parent('div').append(Alert);
				}else if(data.status=='no'){
					var _msg = $.parseJSON(data.errorMsg);
					var len = _msg.length;
					for(var i = 0;i<len;i++){
						var _id = '#' + _msg[i].code + '-msg';
						if(_msg[i].code == 'cityCode' || _msg[i].code == 'provinceCode'){
							$('#provinceCode-msg').addClass("Validform_wrong");
							$('#provinceCode-msg').text("请选择所在地");
							continue;
						}
						$(_id).addClass("Validform_wrong");
						$(_id).text(_msg[i].message);
					}
				}else{
					bg();
					Alert({str:'操作失败'});
				}
			}
		});
		
		//单选按钮选中时清除
		clear('input[name=dealType]','dealType');
		clear('input[name=dealRole]','dealRole');
		clear("input[name=scale]",'scale');
		clear('#breed-p','breed');
		clear('select[name=provinceCode]','provinceCode');
		clear('input[name=zipCode]','zipCode');
		clear('input[name=address]','address');
		
		function clear(selector,msgSelector){
			$(selector).click(function(){
				$("#" + msgSelector + "-msg").removeClass('Validform_wrong');
				$('#' + msgSelector + '-msg').text('');
			});
		}
		
	});
	
/////////////////////////////////////////////// 品种匹配 START ///////////////////////////////////////////////
    $(function(){
        var value = 'amc';
        var isMouseIn = false;
        var breedMain = '';
        //品种输入keyup       
        $('body').delegate('input[id=breedText]','keyup',function(){
            var This = $(this);
            var inputParam = This.val();//获取当前输入参数
            $.ajax({
          		  type: 'POST',
          		  url: "/completeInfoGuide/getBreeds",
          		  data: {'param':inputParam},
				  dataType : "json",
				  async : false,//同步处理
				  success : function(data) {
				    var ok = data.ok;
				    if(ok){
				    	var breeds = data.obj;
				    	var top = '<div class="breed-list">';
				    	var html = "";
				    	var footer = '<span class="close">关 闭</span></div>';
				    	$.each(breeds,function(i,breed){
				    		html += '<a href="#"  id="'+breed.breedId+'">'+breed.breedName+'</a>';
				    	})
				    	breedMain = top + html + footer;
				    }else{
				    	 $('.breed-list').remove();
				    }
				 }
            });
            
             if(value){
                $('body').append(breedMain);
                var x = $(this).offset().left;
                var y = $(this).offset().top;
                $('.breed-list').css('left',x);
                $('.breed-list').css('top',y+25);
                $('.breed-list a').on('click',function(){
                	//主营品种赋值
                	This.val($(this).text());
                	//主营品种隐藏域赋值
                	This.prev("input").val($(this).attr('id'));
                	checkBreed(This,$(this).text());
                    $('.breed-list').remove();
                    return false;
                });
                $('.breed-list .close').on('click',function(){
                    $('.breed-list').remove();
                })
            }else{
                $('.breed-list').remove();
            } 
        });
        
        //品种弹层关闭
        $('body').delegate('input[id=breedText]','keydown',function(){
        	breedMain = '';
            $('.breed-list').remove();
        });
        
        $('body').delegate('input[id=breedText]','blur',function(){
            if(!isMouseIn){
                $('.breed-list').remove();
            }
        });
		
        $('body').delegate('.breed-list','mouseover',function(){
            isMouseIn=true;
        });
        
        $('body').delegate('.breed-list','mouseout',function(){
            isMouseIn=false;
        });
    });
    /////////////////////////////////////////////// 品种匹配 END///////////////////////////////////////////////
    
    /////////////////////////////////////////////// 品种验证 START ///////////////////////////////////////////////
    $(function(){
    	//进入页面是显示品种标示
		$('input[id=breedText]').each(function(){ 
	      	var This = $(this);
	      	var inputVal = $.trim($(this).val());  
	      	if(inputVal == ''){
		    		This.next('i').hide();
	          }else{
	        	  checkBreed(This,inputVal);
	          }
	      });
    	
    	//1.将输入参数传递到后台
    	//2.后台根据参数模糊查询返回json数组
    	//3.判断选中div中的品种 ,是否包含于返回的品种数组中
    	//var arr = ["三七","景天三七","三七"];  
    	//alert($.inArray("三七", arr));
    	//如果不包含在数组中,则返回 -1;
        //当前品种  是否存在
        $('body').delegate('input[id=breedText]','blur',function(){ 
        	var This = $(this);
        	var inputVal = $.trim($(this).val());  
        	if(inputVal == ''){
	    		This.next('i').hide();
            }else{
            	checkBreed(This,inputVal);
            }
        });
    });
    
    //品种验证
    function checkBreed(This,inputVal){
    	$.ajax({
    		  type: 'POST',
    		  url: "/completeInfoGuide/getBreeds",
    		  data: {'param':inputVal},
			  dataType : "json",
			  async : false,//同步处理
			  success : function(data) {
			    var ok = data.ok;
			    if(ok){
			    	var breeds = data.obj;
			    	var arr =  new Array();
			    	$.each(breeds,function(i,breed){  
			    		//品种id 赋值到 隐藏域
			    		if(inputVal == breed.breedCname){
			    			This.prev("input").val(breed.breedId);
			    		}
			    		
			    		arr.push('"'+breed.breedCname+'"'); 
			    	})
			    	if($.inArray('"'+inputVal+'"', arr) >= 0){
			    		This.next('i').addClass('ok').css('display','inline-block');
			    		This.next('i').removeClass('wrong');
		            }
			    	if($.inArray('"'+inputVal+'"', arr) < 0){
			    		This.next('i').addClass('wrong').css('display','inline-block');
			    		This.next('i').removeClass('ok');
		            }
			    }else{
			    	This.prev("input").val('');//清空隐藏域id
			    	This.next('i').addClass('wrong').css('display','inline-block');
			    	This.next('i').removeClass('ok');
			    }
			 }
  	});
    }
    function remove(){
        $('.alert').remove();
        setTimeout('remove()', 5000);
    }
    remove();
/////////////////////////////////////////////// 品种验证END ///////////////////////////////////////////////
    
   //联系人保存 
   $("#completeContacterInfoForm").Validform({
		tiptype:4,
		showAllError:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status){
				 var Alert = '<span class="alert"><i></i>保存成功！</span>';
		         $('input[id=save]').parent('div').append(Alert);
			}else if(!data.status){
				bg();
				Alert({str:'操作失败'});
			}
		}
	});
	
</script>
</body>
</html>