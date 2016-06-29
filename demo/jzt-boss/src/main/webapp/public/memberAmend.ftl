<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/form.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />

</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" />
<!-- nav over -->

<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">会员管理<span class="subhead"> >> 修改会员信息</span></h1>
               <div class="listbox">
            	<div class="list_basic">
            	<form id="updateMemberForm" action="/getMemberManage/modifyMember" method="post">
        		<dl>
                	<dt>基本资料</dt>
                    <dd>
                        <label><span class="col_red">*</span> 会员名</label>
                        <p>${(ucUser.userName)}</p>
                        <input type="hidden" name="memberId" id="userId" class='userId' value="${(ucUser.userId)}"/>
                    </dd>
                    <dd>
                    	<label><span class="col_red">*</span> 公司/姓名：</label>
                        <input class="text-sty2 text-2 col_333" name="realName" datatype="s2-50" nullmsg="真实姓名不能为空！" errormsg="至少4个字符,最多50个字符！" type="text" value="${(ucUser.companyName)}">
                        <span class="Validform_checktip"></span>
                    </dd>
                    <dd>
                    	<label><span class="col_red">*</span> 手机：</label>
                        <input class="text-sty2 text-2 col_333" name="mobileNo" datatype="mo" nullmsg="请输入手机号码！" errormsg="请输入正确手机号！" type="text" value="${(ucUser.mobile)}">
                        <span class="Validform_checktip"></span>
                    </dd>
                    <dd>
                    	<label> 邮箱：</label>
                        <input type="text" class="text-sty2 text-2 col_333" name="email" value="${(ucUser.email)}" />
                    </dd>
                    <dd>
                    	<label> 备注：</label>
                        <textarea name="remark" class="text textarea-1" draggable="false" datatype="*0-500" errormsg="最多500个字符！" ignore="ignore">${(ucUser.remark)}</textarea>
                        <span class="Validform_checktip"></span>
                    </dd>
                    <dd>
                    	<label></label>
           
                        <div class="mt25 save relative clearfix">   
                            <div align="left"  style="margin-left:230px;"><input type="button" id="userSave" class="btn btn-blue fl" value="保 存"/></div>
                        </div>
            
                    </dd>
                </dl>
                </form>
                </div>
        

        <dl><dt>经营信息<span class="cap">（完善经营信息，能使您获得更多的中药材贸易机会）</span></dt>
        <dd><div class="">
        	<form action="/getMemberManage/addAnyUserDealInfo" id="updateAnyUserDealInfoForm" method="post">
            <#if (ucUserScope?exists)>
            <ul class="form">
                <li>
                	<input type="hidden" name="userId" id="userId" class='userId' value="${(ucUser.userId)}"/>
                    <label class="lab"><i class="red">*</i> 业务类型：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="1" <#if (ucUserScope.dealType==1)>checked="checked"</#if>/> 我买药材</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="2" <#if (ucUserScope.dealType==2)>checked="checked"</#if>/> 我卖药材</span>
                    <span class="dis-in-bk"><input type="radio" name="dealType" value="3" <#if (ucUserScope.dealType==3)>checked="checked"</#if>/> 我既买药材，也卖药材</span>
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealType-msg"></span>
                </li>
                <li>
                    <label class="lab"><i class="red">*</i> 经营身份：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="1" <#if (ucUserScope.dealRole==1)>checked="checked"</#if>/> 产地经营户</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="2" <#if (ucUserScope.dealRole==2)>checked="checked"</#if>/> 市场经营大户</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="3" <#if (ucUserScope.dealRole==3)>checked="checked"</#if>/> 中药饮片厂</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="4" <#if (ucUserScope.dealRole==4)>checked="checked"</#if>/> 中成药厂</span><br/>
                    <label class="lab"></label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="5" <#if (ucUserScope.dealRole==5)>"checked"</#if>/> 种植合作社</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="6" <#if (ucUserScope.dealRole==6)>checked="checked"</#if>/> 药农</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="7" <#if (ucUserScope.dealRole==7)>checked="checked"</#if>/> 其他</span>
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealRole-msg"></span>
                </li>
                <li>
                    <label class="lab breed fl"><i class="red">*</i> 主营品种：</label>
                    <p class="sty-4 dis-in-bk fl" id="breed_p_tag">
                    <#list breedList as bl>
                    <span id="span_${bl_index}" class="relative breedAdd">
                        <span class="dis-in-bk input-text">
                        	<input type="hidden" name="breeds[${bl_index}].breedId" value="${(bl.breedId)}"/>
                            <input id="breedText" name="breeds[${bl_index}].breedName" class="text-sty2 col_333" value="${(bl.breedName)}"/>
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                        <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
                    </span>
                    
                    
                    </#list>
                    
                   </p>
                </li>
                <li class="clearfix">
                    <label class="lab"></label>
                    <a class="add dis-in-bk" href="#" name="addBreed"> <strong>+</strong> 继续增加采购品种</a>
                    <b class="tips ma"><i></i>请至少输入一个采购的中药材品种，输入多个品种时会增加您的采购机会。</b>
                	&nbsp;&nbsp;<span class="Validform_checktip" id="breed-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab"><i class="red">*</i> 经营规模：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="1" <#if (ucUserScope.scale==1)>checked="checked"</#if>/> 100万以下/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="2" <#if (ucUserScope.scale==2)>checked="checked"</#if>/> 100万—500万/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="3" <#if (ucUserScope.scale==3)>checked="checked"</#if>/> 500万—1000万/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="4" <#if (ucUserScope.scale==4)>checked="checked"</#if>/> 1000万—5000万/年</span><br/>
                    <label class="lab"></label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="5" <#if (ucUserScope.scale==5)>checked="checked"</#if>/> 5000万以上/年</span>
                    <span class="dis-in-bk">( 年采购金额或销售金额 ）</span>
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="scale-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab"><i class="red">*</i> 所在地址：</label>
                    <div class="select-bg">
                    	<span>
                    		<select name="provinceCode" id="provinceCode" class="col_333">
                    			<#if provinceCode??>
		                    		<#list provinceCode as p>
		                    			<option value="${p.code}" <#if (p.code==province.code)>selected</#if>>${p.name }</option>
		                    		</#list>
		                    	</#if>
                    		</select>
                    	</span>
                    </div>
                    <div class="select-bg">
	                    <span>
		                    <select name="cityCode" id="cityCode" class="col_333">
		                    	<option value="${city.code}" selected>${city.name}</option>
		                    </select>
	                    </span>
                    </div>
                    <span class="dis-in-bk">( 公司注册所在地 ）</span><br/>
                    <label class="lab"></label>
                    <input class="text-sty2 text-wid355 mt10 col_333" placeholder="请填写街道地址" name="address" value="${(ucUserScope.address)}"/>
                	<span id="address-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab">邮编：</label>
                    <input class="text-sty2 text-wid210 col_333" name="zipCode" value="${(ucUserScope.zipCode)}"/>
                    <span id="zipCode-msg"></span>
                </li>
                <li class="mt5">
                    <label class="lab">传真：</label>
                    <input class="text-sty2 text-wid45 col_333" placeholder="区号" name="areaCode" value="${(ucUserScope.areaCode)}"/> - <input class="text-sty2 col_333" placeholder="传真号码" name="fax" value="${(ucUserScope.fax)}"/>
                </li>
                <li class="mt25"><div align="left" style="margin-left:165px;" class="relative"><input type="button" class="btn btn-blue" id="dealSave" value="保 存"/></div> </li>
            </ul>
            <#else>
            	<ul class="form">
                <li>
                	<input type="hidden" name="userId" id="userId" class='userId' value="${(ucUser.userId)}"/>
                    <label class="lab"><i class="red">*</i> 业务类型：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="1"/> 我买药材</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="2"/> 我卖药材</span>
                    <span class="dis-in-bk"><input type="radio" name="dealType" value="3"/> 我既买药材，也卖药材</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealType-msg"></span>
                </li>
                <li>
                    <label class="lab"><i class="red">*</i> 经营身份：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="1"/> 产地经营户</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="2"/> 市场经营大户</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="3"/> 中药饮片厂</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="4"/> 中成药厂</span><br/>
                    <label class="lab"></label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="5"/> 种植合作社</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="6"/> 药农</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="7"/> 其他</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealRole-msg"></span>
                </li>
                <li>
                    <label class="lab breed fl"><i class="red">*</i> 主营品种：</label>
                    <p class="sty-4 dis-in-bk fl" id="breed_p_tag">
                    <span id="span_0" class="relative breedAdd">
                        <span class="dis-in-bk input-text">
                        	<input type="hidden" name="breeds[0].breedId" value=""/>
                            <input id="breedText" name="breeds[0].breedName" class="text-sty2 col_333" value=""/>
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span> </i>
                        </span>
                        <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
                    </span>

                    <span id="span_1" class="relative breedAdd">
                        <span class="dis-in-bk input-text">
                        	<input type="hidden" name="breeds[1].breedId" value=""/>
                            <input id="breedText" name="breeds[1].breedName" class="text-sty2 col_333" value=""/>
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                        <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
                    </span>

                    <span id="span_2" class="relative breedAdd">
                        <span class="dis-in-bk input-text">
                        	<input type="hidden" name="breeds[2].breedId" value=""/>
                            <input id="breedText" name="breeds[2].breedName" class="text-sty2 col_333" value=""/>
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                        <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
                    </span>

                    <span id="span_3" class="relative breedAdd">
                        <span class="dis-in-bk input-text">
                            <input type="hidden" name="breeds[3].breedId" value=""/>
                            <input id="breedText" name="breeds[3].breedName" class="text-sty2 col_333" value=""/>
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                        <span class="remove-bg"><a href="#" name="remove" class="remove">删除</a></span>
                    </span>
                   </p>
                </li>
                <li class="clearfix">
                    <label class="lab"></label>
                    <a class="add dis-in-bk" href="#" name="addBreed"> <strong>+</strong> 继续增加采购品种</a>
                    <b class="tips ma"><i></i>请至少输入一个采购的中药材品种，输入多个品种时会增加您的采购机会。</b>
                	&nbsp;&nbsp;<span class="Validform_checktip" id="breed-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab"><i class="red">*</i> 经营规模：</label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="1"/> 100万以下/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="2"/> 100万—500万/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="3"/> 500万—1000万/年</span>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="4"/> 1000万—5000万/年</span><br/>
                    <label class="lab"></label>
                    <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="5"/> 5000万以上/年</span>
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
		                    			<option value="${p.code }">${p.name }</option>
		                    		</#list>
		                    	</#if>
		                    </select>
	                    </span>
                    </div>
                    <div class="select-bg">
	                    <span>
	                    	<select name="cityCode" id="cityCode" class="col_333">
		                    	<option value="">请选择城市</option>
		                    </select>
	                    </span>
                    </div>
                    <span class="dis-in-bk">( 公司注册所在地 ）</span><span class="Validform_checktip" id="provinceCode-msg"></span><br/>
                    <label class="lab"></label>
                    <input name="address" class="text-sty2 text-wid355 mt10 col_333" placeholder="请填写街道地址" />
                	<span id="address-msg"></span>
                </li>
                <li class="mt10">
                    <label class="lab">邮编：</label>
                    <input name="zipCode" class="text-sty2 text-wid210 col_333" />
                    <span id="zipCode-msg"></span>
                </li>
                <li class="mt5">
                    <label class="lab">传真：</label>
                    <input name="areaCode" class="text-sty2 text-wid45 col_333" placeholder="区号" /> - <input name="fax" class="text-sty2 col_333" placeholder="传真号码" />
                </li>
                <li class="mt25"><div align="left" style="margin-left:165px;" class="relative"><input id="dealSave" type="button" class="btn btn-blue" value="保 存"/></div> </li>
            </ul>
            </#if>
            </form>
            </div></dd>
         </dl>
         
        <dl><dt>联系人信息<span class="cap">（完善联系人信息，能使您获得更多的中药材贸易服务）</span></dt>
        <dd>
        <form action="/getMemberManage/saveContacterInfo" method="post" id="updateContacterInfoForm">
        <div class="" id="len">
        	<#if (ucUserContact?exists)>
                   	<#if (ucUserContact?size=0)>
                    	<ul id="dynamic_ul_0" class="form contacterInfoForm">
			                <li>
			                	<input type="hidden" name="ucUserContact[0].userId" id="userId" class='userId' value="${(ucUser.userId)}"/>
			                    <label class="lab"><i class="red">*</i> 姓名：</label>
			                    <input id="name" name="ucUserContact[0].name" class="text-sty2 col_333 mr10" value="" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;&nbsp;&nbsp;
			                    <input type="radio" name="ucUserContact[0].sex" value="0" checked="checked"/> 先生&nbsp;&nbsp;&nbsp;
			                    <input type="radio" name="ucUserContact[0].sex" value="1"/> 女士
			                    <span class="Validform_checktip"></span>
			                </li>
			                <li>
			                    <label class="lab">部门：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].department" value="" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>
			                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
			                    <span class="Validform_checktip"></span>
			                </li>
			                <li>
			                    <label class="lab">职务：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].job" value="" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>
			                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
			                    <span class="Validform_checktip"></span>
			                </li>
			                <li>
			                    <label class="lab">联系座机：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].phone" value="" ignore="ignore" datatype="n0-12"  errormsg="不可超过12位数字"/>
			                </li>
			                <li>
			                    <label class="lab"><i class="red">*</i> 联系手机：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" id="mobile" name="ucUserContact[0].mobile" value="" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>
			                	<span class="Validform_checktip" id="mobile"></span>
			                </li>
			                <li>
			                    <label class="lab">电子邮箱：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].email" value="" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>
			                </li>
			            </ul>
			        <#else>
			        	<#list ucUserContact as contact>
				            <ul id="dynamic_ul_${contact_index}" class="form contacterInfoForm">
				                <li>
				                	<input type="hidden" name="ucUserContact[${contact_index}].userId" id="userId" class='userId' value="${(ucUser.userId)}"/>
				                    <label class="lab"><i class="red">*</i> 姓名：</label>
				                    <input id="name" name="ucUserContact[${contact_index}].name" class="text-sty2 col_333 mr10" value="${(contact.name)}" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;&nbsp;&nbsp;
				                    <input type="radio" name="ucUserContact[${contact_index}].sex" value="0" <#if (contact.sex==0)>checked="checked"</#if>/> 先生&nbsp;&nbsp;&nbsp;
				                    <input type="radio" name="ucUserContact[${contact_index}].sex" value="1" <#if (contact.sex==1)>checked="checked"</#if>/> 女士
				                </li>
				                <li>
				                    <label class="lab">部门：</label>
				                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[${contact_index}].department" value="${(contact.department)}" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>
				                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
				                </li>
				                <li>
				                    <label class="lab">职务：</label>
				                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[${contact_index}].job" value="${(contact.job)}" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>
				                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
				                </li>
				                <li>
				                    <label class="lab">联系座机：</label>
				                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[${contact_index}].phone" value="${(contact.phone)}" ignore="ignore" datatype="n0-12"  errormsg="不可超过12位数字"/>
				                </li>
				                <li>
				                    <label class="lab"><i class="red">*</i> 联系手机：</label>
				                    <input class="text-sty2 text-wid210 col_333 mr10" id="mobile" name="ucUserContact[${contact_index}].mobile" value="${(contact.mobile)}" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>
				                	<span class="Validform_checktip" id="mobile"></span>
				                </li>
				                <li>
				                    <label class="lab">电子邮箱：</label>
				                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[${contact_index}].email" value="${(contact.email)}" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>
				                </li>
				                <#if (contact_index != 0)>
				                <li class="mt20"><div align="center"><span class="blue f14 text-sty-udline hand" id="removeContact">删 除</span></div></li>
				            	</#if>
				            </ul>
				            </#list>
			         
                   	</#if>
            <#else>
        		<ul id="dynamic_ul_0" class="form contacterInfoForm">
			                <li>
			                	<input type="hidden" name="ucUserContact[0].userId" id="userId" class='userId' value="${(ucUser.userId)}"/>
			                    <label class="lab"><i class="red">*</i> 姓名：</label>
			                    <input id="name" name="ucUserContact[0].name" class="text-sty2 col_333 mr10" value="" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;&nbsp;&nbsp;
			                    <input type="radio" name="ucUserContact[0].sex" value="0" checked="checked"/> 先生&nbsp;&nbsp;&nbsp;
			                    <input type="radio" name="ucUserContact[0].sex" value="1" /> 女士
			                </li>
			                <li>
			                    <label class="lab">部门：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].department" value="" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>
			                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
			                </li>
			                <li>
			                    <label class="lab">职务：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].job" value="" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>
			                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
			                </li>
			                <li>
			                    <label class="lab">联系座机：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].phone" value="" ignore="ignore" datatype="n0-12"  errormsg="不可超过12位数字"/>
			                </li>
			                <li>
			                    <label class="lab"><i class="red">*</i> 联系手机：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" id="mobile" name="ucUserContact[0].mobile" value="" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>
			                	<span class="Validform_checktip" id="mobile"></span>
			                </li>
			                <li>
			                    <label class="lab">电子邮箱：</label>
			                    <input class="text-sty2 text-wid210 col_333 mr10" name="ucUserContact[0].email" value="" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>
			                </li>
			            </ul>
            </#if>
            <div id="append">
                <div class="mt25 save relative clearfix"> 
                    <span class="fr mr60"><a href="#" class="dis-in-bk" name="addContact"><input class="btn-hui" value="继续添加联系人" type="button"></a></span>
                    <div align="left" style="margin-left:188px; padding-bottom:5px;"><input type="button" id="contactSave" class="btn btn-blue fl" value="保 存"/></div>
                </div>
            </div>
        </div>
        </form>
        </dd></dl>


    </div>
</div>
            
        </div></div>
    </div>
<!-- pageCenter over -->
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js" type="text/javascript"></script>
<script>
//会员基本信息---------------------------------------------------------------
$(function(){
		//保存会员基本信息
      $("#updateMemberForm").Validform({
      	btnSubmit:"#userSave",
  		tiptype:4,
  		ajaxPost:true,
  		callback:function(data){
  			var mes = data.msg;
  			if(mes == '1'){
  				var Alert = '<span class="min-alert"><i></i>保存成功！</span>';
  	            $("#userSave").parent('div').append(Alert);
      		}else{
      			var Alert = '<span class="min-alert"><i></i>'+mes+'</span>';
  	            $("#userSave").parent('div').append(Alert);
      		}
  		}
  	})
});
//经营信息(end)---------------------------------------------------------------
	
	$(function(){
		//选着省份获取城市
		$("#provinceCode").change(function(){
			var _pCode = $("#provinceCode").val();
			var scity = $("#cityCode");
			if(_pCode == null || _pCode == ''){
				scity.html('');
				scity.html('<option value="">请选择城市</option>');
				return;
			}
			$.ajax({
				type:'POST',
				url:'/getMemberManage/getCity',
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
		
		$("#updateAnyUserDealInfoForm").Validform({
			btnSubmit:"#dealSave",
			tiptype:4,
			dragonfly:true,
			showAllError:true,
			ajaxPost:true,
			callback:function(data){
				if(data.status==1){
					 var Alert = '<span class="min-alert"><i></i>保存成功！</span>';
			         $('#dealSave').parent('div').append(Alert);
				}else if(data.status=='no'){
					var _msg = $.parseJSON(data.errorMsg);
					var len = _msg.length;
					for(var i = 0;i<len;i++){
						var _id = '#' + _msg[i].code + '-msg';
						if(data.feild == 'cityCode'){
							$('#provinceCode-msg').remove();
							return;
						}
						$(_id).addClass("Validform_wrong");
						$(_id).text(_msg[i].message);
					}
				}else if(data.status=='nouserId'){
					var Alert = '<span class="min-alert"><i></i>请先保存用户基本信息！</span>';
			         $('#dealSave').parent('div').append(Alert);
				}
			}
		});
		
		//单选按钮选中时清除
		clear('input[name=dealType]','dealType');
		clear('input[name=dealRole]','dealRole');
		clear("input[name=scale]",'scale');
		clear('#breed_p_tag','breed');
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
	
	//主营品种添加删除
  $('body').delegate('.breedAdd','mouseover',function(){
      var Text = $(this).children().children('input[id=breedText]').val();
      if(!Text == ''){
          $(this).children('.remove-bg').show();
      }
  });
  $('body').delegate('.breedAdd','mouseout',function(){
      $(this).children('.remove-bg').hide();
  });
  
  //动态添加行索引
  var index = $('input[id=breedText]').size();
	//添加按钮 动态新增
  $('a[name=addBreed]').on('click',function(){
      var html = 
      	'<span id="span_'+index+'" class="relative breedAdd">'+
      	'<span class="dis-in-bk input-text">'+
      	'<input type="hidden" name="breeds['+index+'].breedId" value=""/>'+
      	'<input id="breedText" name="breeds['+index+'].breedName" class="text-sty2 col_333" value="">'+
      	' <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>'+
      	'</span><span class="remove-bg" style="display: none;">'+
      	'<a class="remove" name="remove" href="#">删除</a></span></span>';
      $(this).parent().prev().children('p').append(html);
      index ++;
      return false;
  });

  //alert($('a[name=remove]'));
  $('.form li p').delegate('a[name=remove]','click',function(){
    	var len = $(this).parents('p[id="breed_p_tag"]').children('span').length;
        //至少有一个品种
        if(len > 1){
        	$(this).parents('.breedAdd').remove();
        }
        return false;
   });
  
  //删除品种
  /**
  $('a[name=remove]').on('click',function(){
      var len = $(this).parents('p[id="breed_p_tag"]').children('span').length;
      //至少有一个品种
      if(len > 1){
      	$(this).parents('.breedAdd').remove();
      }
      return false;
  });
  */
	
	//品种匹配--------------------------------------------------------------------------------
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
        		  url: "/getMemberManage/getBreeds",
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
	
  //品种验证-------------------------------------------------------------------------------------
	$(function(){
		//进入页面是显示品种标示
		$('input[id=breedText]').each(function(){ 
	      	var This = $(this);
	      	var inputVal = $.trim($(this).val());  
	      	if(inputVal == ''){
		    		This.next('i').hide();
	          }else{
	          	$.ajax({
		          		  type: 'POST',
		          		  url: "/getMemberManage/getBreeds",
		          		  data: {'param':inputVal},
		  				  dataType : "json",
		  				  async : false,//同步处理
		  				  success : function(data) {
		  				    var ok = data.ok;
		  				    if(ok){
		  				    	var breeds = data.obj;
		  				    	var arr =  new Array();
		  				    	$.each(breeds,function(i,breed){       
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
		  				    	This.next('i').addClass('wrong').css('display','inline-block');
		  				    	This.next('i').removeClass('ok');
		  				    }
		  				 }
	          	});
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
          	$.ajax({
	          		  type: 'POST',
	          		  url: "/getMemberManage/getBreeds",
	          		  data: {'param':inputVal},
	  				  dataType : "json",
	  				  async : false,//同步处理
	  				  success : function(data) {
	  				    var ok = data.ok;
	  				    if(ok){
	  				    	var breeds = data.obj;
	  				    	var arr =  new Array();
	  				    	$.each(breeds,function(i,breed){       
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
	  				    	This.next('i').addClass('wrong').css('display','inline-block');
	  				    	This.next('i').removeClass('ok');
	  				    }
	  				 }
          	});
          }
      });
      //保存成功
      /* $('input[id=save]').on('click',function(){
          var Alert = '<span class="min-alert"><i></i>保存成功！</span>';
          $(this).parent('div').append(Alert);
      }); */
  });
  function remove(){
      $('.min-alert').remove();
      setTimeout('remove()', 5000);
  }
  remove();
      
	//联系人信息-----------------------------------------------------------------
	$(function(){
		var dynamic_index = $('.contacterInfoForm').size() ;
      //添加联系人
      $('a[name=addContact]').on('click',function(){
          var contactBox = '<ul id="dynamic_ul_'+dynamic_index+'" class="form contacterInfoForm">'+
                  '<li>'+
                  '<label class="lab"><i class="red">*</i> 姓名：</label>'+
                  '<input id="userName" name="ucUserContact['+dynamic_index+'].name" class="text-sty2 col_333 mr10" value="" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;&nbsp;&nbsp;'+
                  '&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="name-msg"></span>'+
                  '<input type="radio" name="ucUserContact['+dynamic_index+'].sex" value="0" checked="checked"/> 先生&nbsp;&nbsp;&nbsp;'+
                  '<input type="radio" name="ucUserContact['+dynamic_index+'].sex" value="1"/> 女士'+
                  '</li>'+
                  '<li>'+
                  '<label class="lab">部门：</label>'+
                  '<input name="ucUserContact['+dynamic_index+'].department" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>'+
                  '<span class="dis-in-bk">（未做部门分工，可不填写）</span>'+
                  ' <span class="Validform_checktip"></span>'+
                  '</li>'+
                  '<li>'+
                  '<label class="lab">职务：</label>'+
                  '<input name="ucUserContact['+dynamic_index+'].job" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符"/>'+
                  '<span class="dis-in-bk">（未做部门分工，可不填写）</span>'+
                  ' <span class="Validform_checktip"></span>'+
                  '</li>'+
                  '<li>'+
                  '<label class="lab">联系座机：</label>'+
                  '<input name="ucUserContact['+dynamic_index+'].phone" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="n0-12"  errormsg="不可超过12位数字"/>'+
                  ' <span class="Validform_checktip"></span>'+
                  '</li>'+
                  '<li>'+
                  '<label class="lab"><i class="red">*</i> 联系手机：</label>'+
                  '<input id="mobilePhone" name="ucUserContact['+dynamic_index+'].mobile" class="text-sty2 text-wid210 col_333 mr10" value="" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>'+
                  ' <span class="Validform_checktip" id="mobile"></span>'+
                  '</li>'+
                  '<li>'+
                  '<label class="lab">电子邮箱：</label>'+
                  '<input name="ucUserContact['+dynamic_index+'].email" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>'+
                  ' <span class="Validform_checktip"></span>'+
                  '</li>'+
                  '<li class="mt20"><div align="center"><span class="blue f14 text-sty-udline hand" id="removeContact">删 除</span></div> </li>'+
                  '</ul>';
          var len = $(this).parents('div[id=len]').children('ul').length;
          if(len<=4){
              $(this).parents('div[id=len]').children('ul').last().after(contactBox);
              len++;
              $(this).css('color','#0088cc');
          }else{
              $(this).css('color','#666');
          }
          dynamic_index++;
          return false;
      });
      
      /////删除
      $('body').delegate('span[id=removeContact]','click',function(){
          $('[name=addContact]').css('color','#0088cc');
          if($(this).parents().parents('div[id=len]').children('ul').length>1){
              $(this).parents().parents('.form').remove();
          }
      });
  });
	
	$("#updateContacterInfoForm").Validform({
		btnSubmit:"#contactSave",
		tiptype:4,
		dragonfly:true,
		showAllError:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status == 'ok'){
				 var Alert = '<span class="min-alert"><i></i>保存成功！</span>';
		         $('#contactSave').parent('div').append(Alert);
		         
			}else if(data.status == 'nouserId'){
				 var Alert = '<span class="min-alert"><i></i>请先保存用户基本信息！</span>';
		         $('#contactSave').parent('div').append(Alert);
			}
		}
	});

</script>

</body>
</html>