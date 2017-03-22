<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>我的物流-${baseSetting.title!}</title>
    <meta name="description" content="${baseSetting.intro!}" />
    <meta name="Keywords" content="${baseSetting.keyWord!}" />
</head>

<body>

    <#include "./inc/header-center.ftl"/>


    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>我的物流</h3>
                </div>
                
                <div class="mybill">
                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>
                                    <th>运单号</th>
                                    <th>订单号</th>
                                    <th>订单商品总数（个）</th>
                                    <th>本次发货商品数（个）</th>
                                    <th>发货日期</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if (pageInfo??&&pageInfo.list?size>0)>
                        		<#list pageInfo.list as logistics>
                                <tr>
                                    <td>${logistics.lCode}</td>
                                    <td>${logistics.oCode}</td>
                                    <td>${logistics.total}</td>
                                    <td>${logistics.shipNumber}</td>
                                    <td>${logistics.shipDate?date}</td>
                                    <td>
                                        <a href="/center/logistics/${logistics.id}" class="c-blue">查看详情</a>
                                    </td>
                                </tr>
                                </#list>
                            <#else>
                            	<tr>
                                    <td colspan="6">
                                        <div class="empty">
                                       		 您还没有任何物流信息！
                                        </div>
                                    </td>
                                </tr>
                    		</#if>
                            </tbody>
                        </table>
                        <#if pageInfo?? && pageInfo.size &gt; 0>
			                <@p.pager inPageNo=pageInfo.pageNum-1 pageSize=pageInfo.pageSize recordCount=pageInfo.total toURL="/center/logistics/list"/>
			            </#if>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
</body>
</html>