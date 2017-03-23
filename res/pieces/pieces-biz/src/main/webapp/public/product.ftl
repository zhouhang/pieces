<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>${title!}</title>
    <meta name="description" content="${description!}"/>
        <meta name="Keywords" content="${keyWords!}"/>
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
                                <a href="/commodity/${commodity.id}"><img class="lazyload" src="images/blank.gif" data-original="${commodity.pictureUrl90!}" width="80" height="80" alt="${commodity.title!}"></a>
                            </div>
                            <div class="desc">
                                <h3><a href="/commodity/${commodity.id}" title="${commodity.name}">${commodity.name}</a></h3>
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
                            <a class="btn btn-red" href="javascript:;" id="buying" data-s="${commodity.id}|${commodity.name}|${commodity.level}">加入询价单</a>

                            <#if collect?exists && collect>
                                <a href="javascript:;" id="jfave" class="btn btn-gray faved"><i class="fa fa-heart"></i>已收藏</a>
                            <#else >
                                <a href="javascript:;" id="jfave" class="btn btn-gray"><i class="fa fa-heart"></i>收藏</a>
                            </#if>
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
    <script>

        // 收藏结果回调
        function loginCall(status) {
            layer.closeAll('iframe');
            if(status === 'y') {
                layer.msg('收藏成功！', {icon: 1});
            } else {
                layer.msg('已收藏该商品！', {icon: 2});
            }
            $('#jfave').off().addClass('faved').html('<i class="fa fa-heart"></i>已收藏</a>');
        }
        var _global = {
            v: {
            },
            fn: {
                init: function() {
                    this.addToCart();
                    this.addFav();
                    this.imgerror();
                },
                // 加入询价单
                addToCart: function() {
                    var that    = this,
                        $buying = $('#buying'),
                        data    = ($buying.data('s') || '').split('|'), // data-s = "id|name|norms"
                        id      = data[0];

                    if (id != '' && shopcart.isInCart(id)) {
                        $buying.addClass('disabled').html('已加入询价单');
                    } else {
                        $buying.removeClass('disabled').html('加入询价单');
                    }

                    $buying.on('click', function() {
                        if ($(this).hasClass('disabled')) {
                            // do nothing
                        } else if (data.length === 3) {
                            shopcart.addToCart(data);                            
                            layer.confirm('加入成功，是否立即查看询价单？', {
                                icon: 1
                            }, function(index) {
                                layer.close(index);
                                window.location.href = '/cart/index';
                            });
                            $buying.addClass('disabled').html('已加入询价单');
                        } else {
                            layer.alert('加入询价单失败',{icon: 2});
                        }
                    })
                },
                imgerror: function () {
                    var img = new Image(),
                        $img = $('.preview img');

                    img.onerror = function() {
                        img.onerror = null;
                        $img[0].src = 'images/blank.gif';
                        $img.addClass('miss');
                    }
                    img.src = $img[0].src;
                },
                // 收藏
                addFav: function() {
                    var ajaxurl = '/center/collect/add/${commodity.id!}',
                        url = '/commodity/${commodity.id!}',
                        disabled = false;

                    $('#jfave').on('click', function() {
                        if (disabled) {
                            return false;
                        }
                        enabled = true;
                        $.ajax({
                            url: '/pop',
                            type: 'POST',
                            dataType : 'json',
                            success: function(res){
                                if(res.status === 'y') {
                                    $.ajax({
                                        url: ajaxurl,
                                        type: 'POST',
                                        dataType : 'json',
                                        success: function(data){
                                            loginCall(data.status);
                                        }
                                    });
                                } else {
                                    layer.open({
                                        type: 2,
                                        title: '账户登录',
                                        area: ['360px', '360px'],
                                        content: ['/popLogin?url=' + url + '&ajaxurl=' + ajaxurl , 'no']
                                    });
                                }
                            },
                            complete: function() {
                                enabled = false;
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