<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>商品详情-饮片B2B</title>
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
                                <a href="/commodity/${commodity.id}"><img class="lazyload" src="/images/blank.gif" data-original="${commodity.pictureUrl}" width="80" height="80" alt=""></a>
                            </div>
                            <div class="desc">
                                <h3><a href="/commodity/${commodity.id}">${commodity.name}</a></h3>
                                <P>${commodity.specName}</P>
                            </div>
                        </dd>
                    </#list>
                </dl>
            </div>

            <div class="main">
                <div class="product-summary">
                    <div class="preview">
                        <img src="${commodity.pictureUrl!}" width="360" height="360" alt="">
                    </div>
                    <div class="ext-info">
                        <h1 class="name">${commodity.title!}</h1>

                        <dl>
                            <dt>商品名称</dt>
                            <dd>${commodity.name!}</dd>

                            <dt>切制规格</dt>
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
                                <a href="javascript:;" class="current">选装18-20号筛</a>
                                <a href="javascript:;">选装16-18号筛</a>
                                <a href="javascript:;">选装14-16号筛</a>
                                <a href="javascript:;">选装12-14号筛</a>
                                <a href="javascript:;">选装18-20号筛</a>
                                <a href="javascript:;">选装16-18号筛</a>
                                <a href="javascript:;">选装14-16号筛</a>
                                <a href="javascript:;">选装12-14号筛</a>
                            </dd>
                        </dl>
                        <div class="buttons">
                            <a class="btn btn-red" href="#">询价</a>
                            <a class="btn btn-gray faved" href="#"><i class="fa fa-heart"></i>已收藏</a>
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
                                    <li>${key}：${commodity.attributeView[key]}</li>
                                </#list>
                            </ul>
                        </#if>
                    </div>
                    <div class="tab-cont">
                        <div class="item">
                            ${commodity.details!}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <#include "./inc/helper.ftl"/>
    <!-- footer start -->
    <#include "./inc/footer.ftl"/>

    <!-- footer end -->


</body>
</html>