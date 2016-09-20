<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>上工好药</title>
</head>

<body>
    <#include "./inc/nav.ftl"/>
    <!-- header start -->
    <div class="header header-red">
        <div class="wrap">
            <div class="logo">
                <a href="/">上工好药首页</a>
            </div>
            <div class="title">
                <h1>帮助中心</h1>
            </div>
            <div class="plus">
                <a href="/" class="back"><i class="fa fa-chevron-left"></i> 返回商城首页</a>
            </div>
        </div>
    </div><!-- header end -->

<!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <div class="side side-helper">
            <#list articleCategorylist as articleCategory>

                <dl <#if (articleCategory.id == article.categoryId)>class="expand"</#if>>
                    <dt>                        
                        <span>${articleCategory.name}</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                    <dd>
                        <#list articleCategory.articles as articles>
                            <a <#if (articles.id == article.id)>class="curr"</#if> href="/help/${articles.id}">${articles.title}</a>
                        </#list>
                    </dd>
                </dl>

            </#list>
            </div>

            <div class="main">
                <div class="title">
                    <h3>${article.title}</h3>
                    <div class="extra"></div>
                </div>

                <div class="article">
                    ${article.content}
                </div>
            </div>
        </div>
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
</body>
</html>