<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新闻公告-饮片B2B</title>
</head>

<body>

    <#include "./inc/nav.ftl"/>

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
                <h1>${articles.title}</h1>
                <div class="date">发布时间：${articles.createTime}</div>
                <div class="article">
                    ${articles.content}
                </div>
            </div>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>


    <#include "./inc/footer.ftl"/>
</body>
</html>