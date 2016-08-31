<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>商品列表-饮片B2B</title>
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
                                    <th width="70">切制规格</th>
                                    <th width="70">等级</th>
                                    <th>外观描述</th>
                                    <th width="120">产地</th>
                                    <th width="120">操作</th>
                                </tr>
                            </thead>
                            
                            <#if (commodityVoList??&&commodityVoList?size>0)>
                            <tbody>
								<#list commodityVoList as commodity>
	                                <tr>
	                                    <td>${commodity.name}</td>
	                                    <td>${commodity.specName}</td>
	                                    <td>${commodity.levelName}</td>
	                                    <td>${commodity.exterior}</td>
	                                    <td>${commodity.originOfName}</td>
	                                    <td>
	                                        <#if commodity.status == 1><a href="/center/enquiry/index?commodityId=${commodity.id!}" class="c-blue">询价</a></#if>
	                                        <a onclick="_global.fn.delFav(${commodity.id!});" href="javascript:;" class="c-red jdel">删除</a>
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
	
	<!-- 输入框联想 start -->
    <div class="suggestions" id="suggestions">
		<div class="hd">
			<div class="group">
				<span class="w1">商品名称</span><span class="w2">切制规格</span><span class="w3">等级</span><span class="w4">产地</span>
			</div>
		</div>
		<div class="bd"></div>
	</div><!-- 输入框联想 end -->

    <script src="js/jquery.min.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/common.js"></script>
    <script>
        var _global = {
            v: {
            },
            fn: {
                init: function() {
                },
                // 删除
                delFav: function(id) {
                        layer.confirm('确认从收藏夹中删除这个商品吗？', {icon: 3, title:'提示'}, function(index){
                            window.location.href = '/center/collect/delete/' + id,
                            layer.close(index); // 关闭弹层
                        });  
                        return false; // 组织默认事件
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