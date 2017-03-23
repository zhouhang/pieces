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

    <div class="news">
        <div class="wrap">
            <div class="side">
                <h2 class="hd">最近公告</h2>
                <div class="bd">
                    <ul>
                        <#list articleList as articles>
                            <li><a <#if articles.id == article.id>class="curr"</#if> href="/news/${articles.id}">${articles.title}</a></li>
                        </#list>
                    </ul>
                </div>
            </div>
            <div class="main">
                <h1>${article.title}</h1>
                <div class="date">发布时间：${article.createTime?date}</div>
                <div class="article">
                    ${article.content}
                </div>
            </div>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>

    <#include "./inc/footer.ftl"/>
</body>
</html>