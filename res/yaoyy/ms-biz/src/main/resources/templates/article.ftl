<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>文章详情-药优优</title>
</head>
<body class="ui-body-nofoot">
<header class="ui-header">
    <div class="title">${article.title}</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->


<section class="ui-content">
    <div class="article">
        ${article.content}
    </div>
</section><!-- /ui-content -->
</body>
</html>