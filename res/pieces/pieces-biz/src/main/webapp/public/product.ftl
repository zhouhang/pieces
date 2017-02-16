<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>${commodity.title!}-上工好药</title>
    <meta name="description" content="上工好药sghaoyao.com提供${commodity.title!}的优质货源，为合作伙伴提供安全有保障、高效周到、高性价比的服务，采购饮片就来上工好药。"/>
    <meta name="Keywords" content="${commodity.title!},${commodity.name!}"/>
</head>

<body>
    <#include "./inc/header.ftl"/>

    <div class="main-body">
        <div class="wrap">
            <div class="sitemap">
                <span>商品分类</span>
                <em>&gt;</em>
                <a href="/commodity/index?categoryId=${categoryId}">${category}</a>
                <em>&gt;</em>
                <a href="/commodity/index?breedId=${commodity.categoryId}">${commodity.categoryName}</a>
                <em>&gt;</em>
                <span>${commodity.name}</span>
            </div>

            <div class="hr"></div>

            <div class="side">
                <dl>
                    <dt>—— <em>为您推荐</em> ——</dt>
                    <#list featured as commodity>
                        <dd>
                            <div class="pic">
                                <a href="/commodity/${commodity.id}"><img class="lazyload" src="images/blank.gif" data-original="${commodity.pictureUrl!}" width="80" height="80" alt="${commodity.title!}"></a>
                            </div>
                            <div class="desc">
                                <h3><a href="/commodity/${commodity.id}">${commodity.name}</a></h3>
                                <P>${commodity.spec}</P>
                            </div>
                        </dd>
                    </#list>
                </dl>
            </div>

            <div class="main">
                <div class="product-summary">
                    <div class="preview">
                        <img src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>"  width="360" height="360" alt="${commodity.title!}">
                    </div>
                    <div class="ext-info">
                        <h1 class="name">${commodity.title!}</h1>
                        <dl>
                            <dt>商品名称</dt>
                            <dd>${commodity.name!}</dd>

                            <dt>片&#12288;&#12288;型</dt>
                            <dd>${commodity.spec!}</dd>

                            <dt>原药产地</dt>
                            <dd>${commodity.originOf!}</dd>

                            <dt>外观描述</dt>
                            <dd>${commodity.exterior!}</dd>

                            <dt>执行标准</dt>
                            <dd>${commodity.executiveStandard!}</dd>
                        </dl>

                        <dl class="line" id="rank">
                            <dt>规格等级</dt>
                            <dd>
                                <#list relations as relCommodity>
                                    <a href="/commodity/${relCommodity.id!}" <#if relCommodity.id == commodity.id> class="current"</#if>>${relCommodity.level!}</a>
                                </#list>
                            </dd>
                        </dl>
                        <div class="buttons">
                            <a class="btn btn-red j_pop_login" href="/center/enquiry/index?commodityId=${commodity.id!}">询价</a>
                            <a class="btn btn-gray j_pop_login_collect" ajaxurl="/center/collect/add/${commodity.id!}" url="/commodity/${commodity.id!}"><i class="fa fa-heart"></i>收藏</a>
                        </div>
                    </div>
                </div>
                <div class="product-detail">
                    <div class="tab">
                        <span class="on">详细信息</span>
                    </div>
                    <div class="parameter">
                        <#if commodity.attributeView??>
                            <ul>
                                <#list commodity.attributeView?keys as key>
                                    <#if key!="生产厂家">
                                        <li title="${commodity.attributeView[key]}">${key}：${commodity.attributeView[key]}</li>
                                    </#if>
                                </#list>
                            </ul>
                        </#if>
                    </div>
                    <div class="tab-cont">
                        <div class="item">
                            ${commodity.details!}
                        </div>
                    </div>
                    <div class="guide">
                        <img src="images/uploads/quality_170116.jpg" alt="">
                    </div>
                    <div class="guide">
                        <img src="images/uploads/guide.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <#include "./inc/helper.ftl"/>
    <!-- footer start -->
    <#include "./inc/footer.ftl"/>

    <!-- footer end -->
    <script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
    <script>

        // 收藏结果回调
        function loginCall(status) {
            layer.closeAll('iframe');
            if(status === 'y') {
                layer.msg('收藏成功！', {icon: 1});
            }else{
                layer.msg('已收藏该商品！', {icon: 2});
            }
        }
        var _global = {
            v: {
            },
            fn: {
                init: function() {
                    this.addFav();
                    this.imgerror();
                },
                imgerror: function () {
                    var img = new Image(),
                            $img = $('.preview img');

                    img.onerror = function () {
                        img.onerror = null;
                        $img[0].src = 'images/blank.gif';
                        $img.addClass('miss');
                    }
                    img.src = $img[0].src;
                },
                // 收藏
                addFav: function() {
                    $('.j_pop_login_collect').on('click', function() {
                        var url = $(this).attr('url');
                        var ajaxurl = $(this).attr('ajaxurl');
                        // 检查登录状态
                        $.ajax({
                            url: "/pop",
                            type: "POST",
                            dataType : "json",
                            success: function(data){
                                var status = data.status;
                                if(status === 'y') {
                                    $.ajax({
                                        url: ajaxurl,
                                        type: "POST",
                                        dataType : "json",
                                        success: function(data){
                                            loginCall(data.status);
                                        }
                                    });
                                }else{
                                    layer.open({
                                        type: 2,
                                        title: '账户登录',
                                        area: ['360px', '360px'],
                                        content: ['/popLogin?url=' + url + '&ajaxurl=' + ajaxurl , 'no']
                                    });
                                }
                            }
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