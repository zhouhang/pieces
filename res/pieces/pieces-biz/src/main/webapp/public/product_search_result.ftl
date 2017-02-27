<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>${keyword!}- 商品搜索-上工好药</title>
    <meta name="description" content="在上工好药找到了<#if commodityDocPage??>${commodityDocPage.totalElements!}</#if>个与${keyword!}相关的商品，其中包含了<#if (commodityDocPage??&&commodityDocPage.totalElements>0)><#list commodityDocPage.content as commodityDoc ><#if commodityDoc_index<2 > ${commodityDoc.categoryName!}</#if></#list></#if> 等品种的饮片。"/>
    <meta name="Keywords" content="${keyword!},上工好药,${keyword!}"/>
</head>

<body>
    <#include "./inc/header.ftl"/>
    <!-- 悬浮框 start -->
    <div class="header search-fixed">
        <div class="wrap">
            <div class="logo">
                <a href="/">上工好药首页</a>
            </div>
            <div class="cart">
                <div class="hd">
                    <i class="fa fa-cartlist"></i>
                    <span>我的询价单</span>
                    <em class="count">0</em>
                </div>
                <div class="bd"></div>
            </div>
            <div class="search">
                <div class="form">
                    <form id="_search_form2" action="commodity/search" method="get">
                        <input id="_search_ipt2" class="ipt" name="keyword" placeholder="请输入原药名称或饮片名称" value="${keyword!}" type="text">
                        <button class="btn" type="submit">搜索</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- 悬浮 end -->
<div class="main-body">

    <div class="wrap">
    <#if (commodityDocPage??&&commodityDocPage.totalElements>0)>
        <div class="sitemap">
            <a href="javascript:;">搜索结果</a>
            <em>&gt;</em>
            <span>${keyword!}</span>
        </div>
        <div class="fa-pro-list">
            <table>
                <thead>
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
                    <#list commodityDocPage.content as commodityDoc>
                        <tr>
                            <td><a href="/commodity/${commodityDoc.id!}"><img class="lazyload" src="images/blank.gif" data-original="${commodityDoc.pictureUrl!}" width="130" height="130" alt="${commodityDoc.title!}"></a></td>
                            <td class="tl">
                                <div class="desc">
                                    <h3><a href="/commodity/${commodityDoc.id!}">${commodityDoc.name!}</a></h3>
                                    <p>${commodityDoc.title!}</p>
                                </div>
                            </td>
                            <td>${commodityDoc.level!}</td>
                            <td>${commodityDoc.spec!}</td>
                            <td>${commodityDoc.originOf!}</td>
                            <td>${commodityDoc.executiveStandard!}</td>
                            <td>
                                <button data-s="${commodityDoc.id}|${commodityDoc.name}|${commodityDoc.level}" class="btn btn-white btn-cart">加入询价单</button>
                                <a href="/commodity/${commodityDoc.id }" class="link">查看详情</a>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <div class="fa-pro-empty">
                        <div class="fa fa-frown"></div>
                        <div class="text">
                            <h1 class="title">对不起，没有找到关于“${keyword!}”的商品！</h1>
                            <dl>
                                <dt>建议您：</dt>
                                <dd>1. 检查输入文字是否有误后重新搜索 </dd>
                                <dd>2. 使用 <a href="/center/enquiry/index">快速询价</a> 功能，提交您的采购单</dd>
                            </dl>
                        </div>
                    </div>
                </#if>
                </tbody>
            </table>
        </div>
        <#if commodityDocPage??>
            <@p.pager inPageNo=commodityDocPage.number pageSize=commodityDocPage.size recordCount=commodityDocPage.totalElements toURL="/commodity/search?keyword=${keyword}"/>
        </#if>
    </div>
</div>
    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>

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