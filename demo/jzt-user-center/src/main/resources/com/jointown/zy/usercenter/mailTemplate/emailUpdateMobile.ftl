<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改手机号码</title>
    <style>
        body,h1,h2,h3,div{margin: 0; padding: 0;}
        .page{border: 1px solid #ccc; width: 598px; border-radius: 3px; margin: 0 auto;}
        .page .top{background: #c90301; width: 598px; height: 59px;}
        .page .top h1{font-size:32px; display: inline; font-family: "宋体"; color: #fff; line-height: 59px; padding-left: 15px; float: left;}
        .page .top p{float: right; display: inline; padding-right: 15px; color: #fff;}
        .page .box{padding: 20px 30px;}
        .page .box h2{font-size: 18px; color: #000; line-height: 45px; margin: 0; padding: 0; font-family: microsoft; font-weight: 600;}
        .page .box .btn-sty{padding: 20px 0 50px;}
        .page .box a.btn{background: #c90301; border: 0 none; text-decoration: none; border-radius: 3px; cursor: pointer; padding: 8px 25px; font-size: 16px; color: #fff; font-weight: bold;}
        .page .box .links{font-size: 16px; color: #a9a9a9; line-height: 24px;}
        .page .box .links a{font-size: 12px; line-height: 22px; color: #3399ff; text-decoration: none;}
        .page .box .foot{width: 540px; height: 46px; margin: 35px auto 0; border-top: 1px dashed #ccc;}
        .page .box .foot p{text-align: center; font-size: 12px; color: #a9a9a9; line-height: 18px;}
    </style>
</head>
<body>
    <div class="page">
        <div class="top">
        	<h1>珍药材</h1>
            <p style="float:right; line-height:59px;">聚好药商，卖珍药材</p>
        </div>
        <div class="box">
            <h2>亲爱的会员：</h2>
            <p>您正在修改手机号码。请点击下面的按钮，完成修改。</p>
            <div class="btn-sty" align="center"><a href="${ueUrl}=${emailActive }" style="color: #fff; text-decoration: none;" class="btn"><span style="color: #fff;">立即完成${optTitle }</span></a> </div>
            <div class="links">
                如果点击无效<br/>
                请复制下方网页地址到浏览器地址栏中打开：<br/>
                <a href="${ueUrl}=${emailActive }" target="_blank" style="word-break:break-all;">${ueUrl}=${emailActive }</a>
            </div>
            <div class="foot">
            	<p>此为系统邮件，请勿回复<br/>©珍药材 2014-2015 All Right Reserved</p>
            </div>
        </div>
    </div>
</body>
</html>