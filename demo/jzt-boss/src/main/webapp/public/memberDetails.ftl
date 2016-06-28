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
<!-- nav over -->

<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">会员管理<span class="subhead"> >> 查看会员信息</span></h1>
            <div class="listbox">
            	<dl>
                	<dt>基本资料</dt>
                    <dd>
                        <label>会员名：</label>
                        <p>${(ucUser.userName)}</p>
                    </dd>
                    <dd>
                    	<label>公司/姓名：</label>
                        <p>${(ucUser.companyName)}</p>
                    </dd>
                    <dd>
                      	<label>手机：</label>
                        <p>${(ucUser.mobile)}</p>
                    </dd>
                    <dd>
                    	<label>邮箱：</label>
                        <p>
                        <#if (ucUser.email??)>${(ucUser.email)}<#else>无</#if>
                        </p>
                    </dd>
                    <dd>
                    	<label>备注：</label>
                        <p><#if (ucUser.remark??)>${(ucUser.remark)}<#else>无</#if></p>
                    </dd>
                </dl>
                
                <dl>
                	<dt>经营信息</dt>
                	<#if (ucUserScope?exists)>
                    <dd>
                        <label>业务类型：</label>
                        <p>
                           <#if (ucUserScope.dealType==1)>买方</#if>
                           <#if (ucUserScope.dealType==2)>卖方</#if>
                           <#if (ucUserScope.dealType==3)>买卖双重身份</#if>
                        </p>
                    </dd>
                    <dd>
                    	<label>经营身份：</label>
                        <p>
                         	<#if (ucUserScope.dealRole==1)>产地经营户</#if>
                        	<#if (ucUserScope.dealRole==2)>市场经营大户</#if>
                        	<#if (ucUserScope.dealRole==3)>中药饮片厂</#if>
                        	<#if (ucUserScope.dealRole==4)>中成药厂</#if>
                        	<#if (ucUserScope.dealRole==5)>种植合作社</#if>
                        	<#if (ucUserScope.dealRole==6)>药农</#if>
                        	<#if (ucUserScope.dealRole==7)>其他</#if>
                        </p>
                    </dd>
                    <dd>
                      	<label>主营品种：</label>
                        <p>
                        	<#list breedList as bl>
                        		${(bl.breedName)}&nbsp;&nbsp;
                        	</#list>
                        </p>
                    </dd>
                    <dd>
                    	<label>经营规模：</label>
                        <p>
                        	<#if (ucUserScope.scale==1)>100万以下/年</#if>
                        	<#if (ucUserScope.scale==2)>100万—500万/年</#if>
                        	<#if (ucUserScope.scale==3)>500万—1000万/年</#if>
                        	<#if (ucUserScope.scale==4)>1000万—5000万/年</#if>
                        	<#if (ucUserScope.scale==5)>5000万以上/年</#if>
                        </p>
                    </dd>
                    <dd>
                    	<label>所在地址：</label>
                        <p>${(ucUserScope.provinceCode)}${(ucUserScope.cityCode)}${(ucUserScope.address)}</p>
                    </dd>
                    <dd>
                    	<label>邮编：</label>
                        <p><#if (ucUserScope.zipCode??)>${(ucUserScope.zipCode)}<#else>无</#if></p>
                    </dd>
                    <dd>
                    	<label>传真：</label>
                        <p>${(ucUserScope.areaCode)}${(ucUserScope.fax)}</p>
                    </dd>
                    </#if>
                </dl>
                
                <dl>
                	<dt>联系人信息</dt>
                	<#list ucUserContact as contact>
                    <dd>
                        <label>姓名：</label>
                        <p>${(contact.name)}</p>
                    </dd>
                    <dd>
                    	<label>部门：</label>
                        <p><#if (contact.department??)>${(contact.department)}<#else>无</#if></p>
                    </dd>
                    <dd>
                      	<label>职位：</label>
                        <p><#if (contact.job??)>${(contact.job)}<#else>无</#if></p>
                    </dd>
                    <dd>
                    	<label>联系座机：</label>
                        <p><#if (contact.phone??)>${(contact.phone)}<#else>无</#if></p>
                    </dd>
                    <dd>
                    	<label>联系手机：</label>
                        <p>${(contact.mobile)}</p>
                    </dd>
                    <dd>
                    	<label>电子邮箱：</label>
                        <p><#if (contact.email??)>${(contact.email)}<#else>无</#if></p>
                    </dd>
                    <dd class="link"></dd>                   
               		</#list>
                </dl>
            </div>
            <div align="center"><input type="button" value="返回" class="btn-hui mt20" onClick="javascript:history.go(-1);"></div>
        </div>
    </div>
<!-- pageCenter over -->
</div>
</body>
</html>