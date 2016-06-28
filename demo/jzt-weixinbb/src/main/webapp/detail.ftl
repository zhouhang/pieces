<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>${article.title}</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
    <div class="box-layout">
      <div class="detail-box">
          <h1>${article.title}</h1>
          <p class="timer">${article.dtm}
          	<#if ((article.writer != null)&&(article.writer != ""))>
          		&nbsp;&nbsp;&nbsp;&nbsp;
          		研究员：${article.writer}
          		&nbsp;&nbsp;&nbsp;&nbsp;
          		阅读：${article.tip!'0'}
          	</#if>
          </p>
          <p>${article.cont}</p>
      </div>

    </div>
	
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>