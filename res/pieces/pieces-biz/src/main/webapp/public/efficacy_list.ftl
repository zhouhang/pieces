<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>功效-上工好药</title>
    <meta name="description" content="上工好药为您提供一些常见功效的中药饮片，为合作伙伴提供安全有保障、高效周到、高性价比的服务，采购饮片就来上工好药。" />
    <meta name="Keywords" content="功效,清热药,解表药,利水渗湿药,泻下药,活血化瘀药,止血药,补虚药,收涩药,平肝熄风药,安神药,化痰止咳平喘药,祛风湿药,上工好药" />
</head>

<body>
<#include "./inc/header.ftl"/>

<!-- banner start -->
<div class="banner-slider" style="background:url(images/banner-efficacy.jpg) no-repeat 50% 0;">
</div><!-- banner end -->


<div class="wrap">
    <div class="efficacy">
        <#list efficacies?keys as key >
            <div class="hd">
                <h2 id="${key}">${key}</h2>
            </div>
            <div class="bd">
                <ul>
                    <#list efficacies[key] as commodity>
                        <li>
                            <a href="/commodity/${commodity.id!}"><img  src="images/blank.gif" class="lazyload" data-original="${commodity.pictureUrl180!}" width="220" height="220" alt="${commodity.title!}"></a>
                            <a href="/commodity/${commodity.id!}" class="title"><b>${commodity.title!}</b></a>
                        </li>
                    </#list>
                </ul>
            </div>
        </#list>
    </div>
</div>

<#include "./inc/helper.ftl"/>
<#include "./inc/footer.ftl"/>
    
<div class="toolbar"></div>
</body>
</html>