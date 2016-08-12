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
                        <img src="${commodity.pictureUrl}" width="360" height="360" alt="">
                    </div>
                    <div class="ext-info">
                        <h1 class="name">${commodity.name}</h1>
                        <div class="summary">
                            <ul>
                                <li>
                                    <div class="dt">切制规格</div>
                                    <div class="dd">${commodity.specName}</div>
                                </li>
                                <li>
                                    <div class="dt">等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 级</div>
                                    <div class="dd">${commodity.levelName}</div>
                                </li>
                                <li>
                                    <div class="dt">原药产地</div>
                                    <div class="dd">${commodity.originOfName}</div>
                                </li>
                                <li>
                                    <div class="dt">外观描述</div>
                                    <div class="dd">${commodity.exterior}</div>
                                </li>
                                <li>
                                    <div class="dt">执行标准</div>
                                    <div class="dd">${commodity.executiveStandard}</div>
                                </li>
                                <li>
                                    <div class="dt">生产厂家</div>
                                    <div class="dd">${commodity.factory}</div>
                                </li>
                            </ul>
                        </div>
                        <div class="buttons">
                            <a class="btn btn-red j_pop_login" href="/center/enquiry/index?commodityId=${commodity.id!}">询价</a>
                            <a class="btn btn-gray" href="#"><i class="fa fa-heart"></i>收藏</a>
                        </div>
                    </div>
                </div>
                <div class="product-detail">
                    <div class="tab">
                        <span class="on">详细信息</span>
                    </div>
                    <div class="tab-cont">
                        <div class="item">
                            ${commodity.details}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>
    <script src="/js/layer/layer.js"></script>

</body>
</html>