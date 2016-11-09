<!DOCTYPE html>
<html lang="en">
<head>
    <title>${special.title!}-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body">
<header class="ui-header">
    <div class="title">${special.title!}</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
    <div class="abs-r mid">
        <a href="category/search"><i class="fa fa-search"></i></a>
    </div>
</header><!-- /ui-header -->

<#include "./common/navigation.ftl">

<section class="ui-content">
    <div class="ui-banner">
        <img src="${special.pictuerUrl!}" alt="${special.title!}">
    </div>
    <div class="plist">
        <ul>
            <#list commodities as commodity >
            <li>
                <a href="/commodity//detail/${commodity.id}">
                    <div class="cnt">
                        <div class="title">${commodity.name!}</div>
                        <div class="summary">
                            ${commodity.title!}
                        </div>
                        <div class="price">
                            <i>&yen;</i>
                            <#if commodity.mark == 1 >
                                <em>${commodity.detail!}</em>
                                <#else>
                                <em>${commodity.price!}</em>
                            </#if>

                            <b>元/${commodity.unitName!}</b>
                            <#if commodity.mark == 1 >
                                <span>量大价优</span>
                            </#if>
                        </div>
                    </div>
                    <div class="pic">
                        <img src="${commodity.thumbnailUrl!}" width="110" height="90" alt="">
                    </div>
                </a>
            </li>
            </#list>
        </ul>
    </div>

</section><!-- /ui-content -->


<#include "./common/footer.ftl"/>
<script>
</script>
</body>
</html>