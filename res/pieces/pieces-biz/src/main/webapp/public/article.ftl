<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新闻公告-上工好药sghaoyao.com - 中药饮片采购首选 - 正品底价、品质保障、配送及时、轻松采购！</title>
    <meta name="description" content="上工好药sghaoyao.com - 专业的中药饮片采购平台，整合中药饮片各品种优势货源，为合作伙伴提供安全有保障、高效周到、高性价比的服务，采购饮片就来上工好药。" />
    <meta name="Keywords" content="中药,饮片,中药饮片,饮片采购,饮片批发,精致饮片,毒性饮片,药食同源,炮制,古法炮制,上工好药" />
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