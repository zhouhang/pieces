<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>功效-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl"/>

    <!-- banner start -->
    <div class="banner-slider" style="background:url(images/banner-efficacy.jpg) no-repeat 50% 0;">
    </div><!-- banner end -->

    
    <div class="wrap">
        <div class="pro-floor">
            <#list efficacies?keys as key >
                <div class="hd">
                    <h2 id="${key}">${key}</h2>
                </div>
                <div class="bd">
                    <ul>
                        <#list efficacies[key] as commodity>
                            <li>
                                <a href="/commodity/${commodity.id!}"><img src="images/blank.gif" class="lazyload" data-original="${commodity.pictureUrl!}" width="220" height="220" alt=""></a>
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
</body>
</html>