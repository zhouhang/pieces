<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>我的收藏-上工好药</title>
</head>

<body>
	<#include "./inc/header-center.ftl"/>

    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>我的收藏</h3>
                    <div class="extra"></div>
                </div>
                
                <div class="mycollect">
                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>
                                    <th width="150">商品名称</th>
                                    <th width="70">片型</th>
                                    <th>规格等级</th>
                                    <th>外观描述</th>
                                    <th width="90">产地</th>
                                    <th width="90">操作</th>
                                </tr>
                            </thead>
                            
                            <#if (commodityVoList??&&commodityVoList?size>0)>
                            <tbody>
								<#list commodityVoList as commodity>
	                                <tr>
	                                    <td>${commodity.name}</td>
	                                    <td>${commodity.spec}</td>
	                                    <td>${commodity.level}</td>
	                                    <td>${commodity.exterior}</td>
	                                    <td>${commodity.originOf}</td>
	                                    <td>
	                                        <#if commodity.status == 1><a href="/center/enquiry/index?commodityId=${commodity.id!}" class="c-blue">询价</a></#if>
	                                        <a href="/center/collect/delete/${commodity.id!}" class="c-red jdel">删除</a>
	                                    </td>
	                                </tr>
                                </#list>
                            </tbody>
                            <#else>
                            <tbody>
								<tr>
                                    <td colspan="6">
                                        <div class="empty">
                                           	 您还没有收藏任何商品！
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                           </#if> 
                        </table>
                    </div>
                <#if pageInfo?? && pageInfo.size &gt; 0>
                    <@p.pager inPageNo=pageInfo.pageNum-1 pageSize=pageInfo.pageSize recordCount=pageInfo.total toURL="/center/collect/index"/>
                </#if>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

    <#include "./inc/footer.ftl"/>

    <script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
    <script>
        var _global = {
            v: {
            },
            fn: {
                init: function() {
                    this.delFav();
                },
                // 删除
                delFav: function(id) {
                    $('.fa-chart').on('click', '.jdel', function() {
                        var url = this.href;
                        layer.confirm('确认从收藏夹中删除这个商品吗？', {icon: 3, title:'提示'}, function(index){
                            window.location.href = url;
                        });
                        return false;
                    })
                }
            }
        }
        //加载页面js
        $(function() {
            _global.fn.init();
        });
    </script>
</body>
</html>