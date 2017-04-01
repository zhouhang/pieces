<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>客户信息-boss-上工好药</title>
</head>

<body>

    <#include "./inc/header.ftl">


<!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>客户信息</dt>
                    <dd>
                        <a class="curr" href="/user/info/${user.id}">客户界面</a>
                        <a  href="/user/edit/${user.id}">账户信息</a>
                        <#if user.type==1>
                        <a  href="/user/certify/${user.id}">企业资质</a>
                        </#if>
                        <a href="/user/trail/${user.id}">跟进记录</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-people"></i>${user.userName}</h3>
                    <div class="extra">
                        <a  class="btn btn-gray" href="/user/index">返回</a>
                        <!--<button type="button" class="btn btn-red">保存</button>-->
                    </div>
                </div>
                <div class="user-info">
                    <h3>个人信息</h3>
                    <div class="info">
                        <table>
                            <tbody>
                                <tr>
                                    <th>用户名：</th>
                                    <td>
                                        ${user.userName}
		                            </td>
                                    <th>注册时间：</th>
                                    <td>${user.createTime?string("yyyy-MM-dd HH:mm:ss") }</td>
                                </tr>
                                <tr>
                                    <th>注册途径：</th>
                                    <td><#if (user.source==1)>
                                        后台注册
                                    <#else>
                                        前台注册
                                    </#if></td>
                                    <th>用户类型：</th>
                                    <td><#if (user.type==1)>
                                        终端用户
                                    <#else>
                                       代理商
                                    </#if></td>
                                </tr>
                                <tr>
                                    <th>认证状态：</th>
                                    <td>
                                    	<#if (user.certifyStatus==1)>
	                            			已认证
		                            	<#else>
		                            		未认证
		                            	</#if>
                                    </td>
                                    <th>认证完成时间：</th>
                                    <td>
                                    <#if user.certifyTime?exists>${(user.certifyTime?datetime)!}<#else>-</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>代理商：</th>
                                    <td>
                                        <#if user.agentName?exists>
                                            ${user.agentName!}
                                        <#else>
                                            -
                                        </#if>
                                    </td>
                                    <th>跟单员：</th>
                                    <td>
                                    <#if user.serviceName?exists>
                                        ${user.serviceName!}
                                    <#else>
                                        -
                                    </#if>
                                    </td>
                                </tr>
                                <tr>

                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->

</body>
</html>