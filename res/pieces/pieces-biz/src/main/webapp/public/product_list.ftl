<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
        <title>${title!}</title>
        <meta name="description" content="${description!}"/>
        <meta name="Keywords" content="${Keywords!}"/>
</head>

<body>
	<#include "./inc/header.ftl"/>
    <script>
        // 开启页面搜索悬浮
        var searchFixed = true;
    </script>
    <div class="main-body">
        <div class="wrap">
            <div class="sitemap">
                <a href="/commodity/index">商品分类</a>
                <#if (parent??&&parent.name??)>
                <em>&gt;</em>
                <a href="/commodity/index?categoryId=${parent.id }">${parent.name }</a>
                </#if>
                <#if (category??&&category.name??)>
                <em>&gt;</em>
                <span>${category.name!}</span>
                </#if>
            </div>
			
			<#if (lxCommodity??&&lxCommodity?size>0)>
            <div class="fa-filter">
                <dl>
                    <dt>商品名称：</dt>
					<dd class="bd" id="screen">
                        <a href="/commodity/index?breedId=${category.id }" <#if commodity.eqName == "">class="current" </#if>>全部</a>
                    	<#list lxCommodity as lc>
                        	<a href="/commodity/index?eqName=${lc.name}" <#if commodity.eqName == lc.name>class="current" </#if>>${lc.name }</a>
                        </#list>
                    </dd>
                </dl>
            </div>
            </#if>

            <div class="fa-pro-list">
                <table>
                    <thead class="tc">
                        <tr>
                            <th width="150"></th>
                            <th width="260">商品信息</th>
                            <th width="160">规格等级</th>
                            <th width="110">片型</th>
                            <th width="120">原药产地</th>
                            <th width="">执行标准</th>
                            <th width="140">操作</th>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#if (pageInfo??&&pageInfo.list?size>0)>
	                    <#list pageInfo.list as commodity>
	                        <tr>
	                            <td><a href="/commodity/${commodity.id }"><img class="lazyload" src="images/blank.gif" data-original="${commodity.pictureUrl!}" width="130" height="130" alt="${commodity.title!}"></a></td>
	                            <td class="tl">                                
	                                <div class="desc">
	                                    <h3><a href="/commodity/${commodity.id}">${commodity.name }</a></h3>
	                                    <p>${commodity.title!}</p>
	                                </div>
	                            </td>
	                            <td>${commodity.level}</td>
                                <td>${commodity.spec}</td>
	                            <td>${commodity.originOf}</td>
	                            <td>${commodity.executiveStandard}</td>
	                            <td>
                                    <button data-s="${commodity.id}|${commodity.name}|${commodity.level}" class="btn btn-white btn-cart">加入询价单</button>
                                    <a href="/commodity/${commodity.id }" class="link">查看详情</a>
                                </td>
	                        </tr>
	                    </#list>
	                </#if>
                    </tbody>
                </table>
            </div>
            
            <#if !pageInfo??||(pageInfo??&&pageInfo.list?size == 0)>
	            <div class="fa-pro-empty">
	                <p class="tc">对不起，找不到您需要的商品，建议您：重新选择筛选条件。</p>
	            </div>
			</#if>
			
			<#if pageInfo?? && pageInfo.total &gt; 0>
            	<@p.pager inPageNo=pageInfo.pageNum-1 pageSize=pageInfo.pageSize recordCount=pageInfo.total toURL="/commodity/index?${commodityParam}"/>
        	</#if>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>

    <div class="toolbar"></div>
    <script>
    var _global = {
        fn: {
            init: function(){
                this.addToCart();
                // this.skip();
            },
            addToCart: function() {
                // 加入询价单
                $('.fa-pro-list').on('click', '.btn-white', function() {
                    var data = ($(this).data('s') || '').split('|'); // data-s = "id|name|norms"
                    if (data.length === 3) {
                        shopcart.addToCart(data);
                        $(this).addClass('btn-gray').prop('disabled', true).html('已加入询价单');
                    } else {
                        layer.alert('加入询价单失败',{icon: 2});
                    }
                    return false;
                    
                }).find('.btn-white').each(function() {
                    var data = ($(this).data('s') || '').split('|'),
                        id = data[0];

                    if (id != '' && shopcart.isInCart(id)) {
                        $(this).addClass('btn-gray').prop('disabled', true).html('已加入询价单');
                    } else {
                        $(this).prop('disabled', false).html('加入询价单');
                    }
                })
            },
            skip: function() {
                // 页面跳转
                $('.fa-pro-list').on('click', 'tr', function() {
                    window.location.href = $(this).find('td:eq(0)').find('a')[0].href;
                })
            }

        }
    }
    $(function() {
        _global.fn.init();
    })
    </script>
</body>
</html>