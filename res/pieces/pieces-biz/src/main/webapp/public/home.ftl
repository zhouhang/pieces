<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>上工好药sghaoyao.com - 中药饮片采购首选 - 正品底价、品质保障、配送及时、轻松采购！</title>
    <meta name="description" content="上工好药sghaoyao.com - 专业的中药饮片采购平台，整合中药饮片各品种优势货源，为合作伙伴提供安全有保障、高效周到、高性价比的服务，采购饮片就来上工好药。" />
    <meta name="Keywords" content="中药,饮片,中药饮片,饮片采购,饮片批发,精致饮片,毒性饮片,药食同源,炮制,古法炮制,上工好药" />
</head>
<body  class="bg-gray">

<#include "./inc/header.ftl"/>


<!-- banner start -->
<div class="banner-slider" id="jslide">
    <div class="bd">
        <ul>
            <#list AD_BANNER as ad>
                <li style="background-color:${ad.color!};">
                    <a title="${ad.title!}" style="background-image:url(${ad.pictureUrl!})" href="${ad.link!"#!"}"></a>
                </li>
            </#list>
        </ul>
    </div>
    <div class="hd"></div>
    <div class="side-notice">
        <h3>服务公告</h3>
        <ul>
            <#list articles as article>
                <li><a href="news/${article.id!}">${article.title!}</a></li>
            </#list>
        </ul>

        <h3>知名厂家</h3>
        <div class="brands" id="jbrands">
            <div class="inner">
                <div class="col" index="0">
                    <#list AD_MANUFACTURERS as ad>
                        <#if ad_index<4>
                        <a target="_blank" href="${ad.link!"#!"}"><img src="${ad.pictureUrl!}" alt="${ad.title!}"></a>
                        </#if>
                    </#list>
                </div>
                <#if (AD_MANUFACTURERS?size>4)>
                    <div class="col" index="1">
                        <#list AD_MANUFACTURERS as ad>
                            <#if (ad_index>3)&&ad_index<8>
                                <a href="${ad.link!"#!"}"><img src="${ad.pictureUrl!}" alt="${ad.title!}"></a>
                            </#if>
                        </#list>
                    </div>
                </#if>

            </div>
            <div class="ctrl">
                <i class="prev">&lt;</i><i class="next">&gt;</i>
            </div>
        </div>
    </div>
</div><!-- banner end -->

<div class="activity">
    <div class="wrap">
        <ul>
            <#list commodityList as commodity>
                <li>
                    <a href="/commodity/${commodity.id!}">
                        <div class="txt">
                            <h4>${commodity.name!}</h4>
                            <p>规格：${commodity.spec!}</p>
                            <p><span class="t-orange">热销</span></p>
                        </div>
                        <div class="img">
                            <img src="${commodity.pictureUrl!}" width="180" height="180" alt="${commodity.title!}">
                        </div>
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</div>

<div class="wrap idx-main">
    <#list categoryList as category>
        <!-- start -->
        <div class="idx-floor" id="floor${category_index}">
                <div class="idx-hd">
                    <h2>${category.title!}</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <#if category.breedList??>
                                <#list category.breedList as breed>
                                    <li><a href="commodity/index?breedId=${breed.id!}" >${breed.name!}</a></li>
                                </#list>
                            </#if>
                        </ul>
                        <div class="img">
                            <img src="images/blank.gif" alt="${category.title!}" class="lazyload" data-original="${category.pictureUrl!}" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <#if category.showcase??>
                                    <a href="${category.showcase.link!}"><img alt="${category.title!}" src="images/blank.gif" class="lazyload" data-original="${category.showcase.pictureUrl!}" width="400" height="270"></a>
                                </#if>
                            </dt>
                            <#if category.breedList??>
                                <#list category.commodityList as commodity>
                                    <dd>
                                        <a href="/commodity/${commodity.id!}"><img src="images/blank.gif" alt="${commodity.title!}" class="lazyload" data-original="${commodity.pictureUrl!}" width="180" height="180"></a>
                                        <a href="/commodity/${commodity.id!}">${commodity.name!}</a>
                                        <span>片型：${commodity.spec!}</span>
                                    </dd>
                                </#list>
                            </#if>
                        </dl>
                    </div>
                </div>
        </div><!-- end -->
        <#if category_index%2!=0 >
            <#assign adindex=(category_index/2)>
            <#if AD_SHOWCASE_BAR[adindex]?? >
                <div class="wide-banner">
                    <a href="${AD_SHOWCASE_BAR[adindex?int].link!}"><img alt="${AD_SHOWCASE_BAR[adindex?int].title!}" src="images/blank.gif" class="lazyload" data-original="${AD_SHOWCASE_BAR[adindex?int].pictureUrl!}" width="1200" height="90"></a>
                </div>
            </#if>
        </#if>

    </#list>
</div>

<#include "./inc/helper.ftl"/>

<div class="elevator" id="jelevator"></div>


<#include "./inc/footer.ftl"/>

<script src="${urls.getForLookupPath('/js/jquery.nav.js')}"></script>
<script src="${urls.getForLookupPath('/js/index_2016.js')}"></script>
</body>
</html>