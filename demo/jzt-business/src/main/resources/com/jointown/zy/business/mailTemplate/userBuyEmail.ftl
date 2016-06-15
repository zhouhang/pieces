<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>买家下单</title>
<style type="text/css">
body {margin:0; padding:0; font-size:12px;font-family:"Microsoft YaHei","微软雅黑";}
div,p{padding:0; margin:0;}
i{font-style:normal;}
.approve{ width:1200px; margin:40px auto; font-size:14px; color:#535353;}
.mb25{ margin-bottom:25px; font-size:14px; color:#666; margin-top:15px;}
.mt30{ margin-top:30px; margin-bottom:10px;}
.approve span{font-size:14px; color:#535353;}
.approve p{ text-indent:80px;display:block; line-height:28px; font-size:14px; color:#535353;}
.red{ color:#F30;}
</style>
</head>
<body>
<!--客户购买-->
	<div class="approve">
    	<span>亲爱的客服人员：</span>
     	<p class="mb25">你好！会员<i class="red">${buyName}</i>已对挂牌编号为：<i class="red">${listingId}</i>的药材下单！</p>
        <p>买家信息：</p>
        <p>姓名/企业名称：<i class="red">${buyName}</i> &nbsp;&nbsp;&nbsp;&nbsp;联系方式：<i class="red">${buyPhone}</i></p>
        <p>卖家信息：</p>
        <p>姓名/企业名称：<i class="red">${SELLNAME}</i> &nbsp;&nbsp;&nbsp;&nbsp;联系方式：<i class="red">${SELLPHONE}</i></p>
        <p class="mt30">此致，</p>
        <p>中药材电商团队</p>
    </div>
<!--客户购买-->

</body>
</html>
