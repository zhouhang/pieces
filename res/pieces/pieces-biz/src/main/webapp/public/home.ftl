<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>上工之选</title>
</head>

<#include "./inc/header.ftl"/>

<!-- banner start -->
<div class="banner-slider" id="jslide">
    <div class="bd">
        <ul>
            <#list AD_BANNER as ad>
                <li style="background-color:#0074d4;">
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
        <div class="brands">
            <div class="inner">
                <div class="wrapper">
                    <div class="col">
                        <a href="#"><img src="images/brand-jhjt.png" alt=""></a>
                        <a href="#"><img src="images/brand-yonggang.png" alt=""></a>
                        <a href="#"><img src="images/brand-jrt.png" alt=""></a>
                        <a href="#"><img src="images/brand-wsc.png" alt=""></a>
                    </div>
                    <div class="col">
                        <a href="#"><img src="images/brand-jhjt.png" alt=""></a>
                        <a href="#"><img src="images/brand-yonggang.png" alt=""></a>
                        <a href="#"><img src="images/brand-jrt.png" alt=""></a>
                        <a href="#"><img src="images/brand-wsc.png" alt=""></a>
                    </div>
                </div>
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
                            <p>规格：${commodity.specName!}</p>
                            <p><span class="t-orange">热销</span></p>
                        </div>
                        <div class="img">
                            <img src="uploads/a1.jpg" width="200" height="180">
                        </div>
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</div>

<div class="wrap idx-main" id="lazyDom">
    <#list categoryList as category>
        <!-- start -->
        <div class="idx-floor" id="floor${category_index}">
                <div class="idx-hd">
                    <h2>${category.name!}</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <#if category.breedList??>
                                <#list category.breedList as breed>
                                    <li><a href="#">${breed.name!}</a></li>
                                </#list>
                            </#if>
                        </ul>
                        <div class="img">
                            <img src="${category.pictureUrl!}" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-01.jpg" width="400" height="270"></a>
                            </dt>
                            <#if category.breedList??>
                                <#list category.commodityList as commodity>
                                    <dd>
                                        <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                        <a href="#">${commodity.name!}</a>
                                        <span>切制规格：${commodity.specName!}</span>
                                    </dd>
                                </#list>
                            </#if>
                        </dl>
                    </div>
                </div>
        </div><!-- end -->
    </#list>

</div>

<#include "./inc/helper.ftl"/>
<#include "./inc/footer.ftl"/>

<div class="elevator" id="jelevator">
    <ul>
        <#list categoryList as category>
            <li <#if category_index==0>class="curr"</#if>><a href="#floor${category_index}">${category.name!}</a></li>
        </#list>
    </ul>
</div>

<div class="gotop" id="jgotop">
    <a href="javascript:;">返回顶部</a>
</div>

<script src="js/jquery.nav.js"></script>
<script src="js/common.js"></script>
<script src="js/index_2016.js"></script>
</body>
</html>