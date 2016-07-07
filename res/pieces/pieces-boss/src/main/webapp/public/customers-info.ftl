<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>客户信息-boss-饮片B2B</title>
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
                        <a  href="/user/index">客户界面</a>
                        <a class="curr" href="/user/info/${user.id}">账户信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-people"></i>${user.userName}</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="button" class="btn btn-gray">重置</button>
                        <button type="button" class="btn btn-red">保存</button>
                    </div>
                </div>
                <div class="user-info">
                    <h3>个人信息</h3>
                    <div class="info">
                        <table>
                            <tbody>
                                <tr>
                                    <th>当前状态：</th>
                                    <td><#if (user.onlineStatus==1)>
	                            			在线
		                            	<#else>
		                            		离线
		                            	</#if>
		                            </td>
                                    <th>最后一次修改于：</th>
                                    <td><#if (user.updateTime)??>${(user.updateTime)?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                                </tr>
                                <tr>
                                    <th>上次登录时间：</th>
                                    <td></td>
                                    <th>修改途径：</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>是否与ERP关联：</th>
                                    <td>
                                    	<#if (user.bindErp==1)>
	                            			已绑定
		                            	<#else>
		                            		未绑定
		                            	</#if>
                                    </td>
                                    <th>修改人员账号：</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>账号创建日期：</th>
                                    <td>${user.createTime?string("yyyy-MM-dd HH:mm:ss") }</td>
                                    <th>修改内容：</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>账号创建方式：</th>
                                    <td>
                                    	<#if (user.source==1)>
	                            			后台注册
		                            	<#else>
		                            		前台注册
		                            	</#if>
                                    </td>
                                    <th></th>
                                    <td></td>
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

    <script src="/js/jquery.min.js"></script>
</body>
</html>