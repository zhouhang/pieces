<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>微信-查看订单详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
</head>
<body style="background: #f5f5f5;">
<div class="sell-box-head">
    <i class="back"></i>
    <div align="center" class="inStore-title">待付保证金</div>
</div>
<div class="orDe-box">
    <div class="pross-bg relative">
        <span class="step1"></span>
    </div>

    <div class="state">
        <p class="sty1">当前订单状态：您已摘牌，请支付保证金才能锁定药材，请及时登录PC端珍药材平台支付保证金。 摘牌后72小时仍没缴纳保证金的订单，系统会自动关闭此订单。
        </p>
        <p class="sty2">您还有 <span id="day">0</span> 天 <span id="hms">00:00:00</span> 支付保证金</p>
        <p class="sty3">如须帮助请 &nbsp;&nbsp;<a href="tel:4001054315">电询小珍</a> </p>
    </div>

    <div class="orDetail">
        <h3>订单号：${order.orderid!""}</h3>
        <dl>
            <dt><span><img src="${RESOURCE_IMG_UPLOAD_WX}/${order.path!""}"/></span></dt>
            <dd>
                <h4>${order.title!""}</h4>
                <p>
                    <span>单价 :</span><@tools.money num=order.unitprice format="0.##"/>元/${order.wlunit!''}<br/>
                    <span>数量：</span><@tools.money num=order.amount format="0.##"/>${order.wlunit!''}<br/>
                    <span>实际付款：</span><@tools.money num=order.actualPayment format="0.##"/>元<br/>
                    <span>摘牌日期：</span>${order.createtime?string('yyyy-MM-dd')}<br/>
                    <span>买方：</span>${sellerName!''}<br/>
                </p>
            </dd>
            <div class="clearfix"></div>
        </dl>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td align="left">保证金金额</td>
                        <td align="right"><strong>￥<@tools.money num=order.deposit format="0.##"/></strong></td>
                    </tr>
                    <tr>
                        <td align="left">已付保证金金额</td>
                        <td align="right"><strong>￥0.00</strong></td>
                    </tr>
                    <tr>
                        <td align="left">尾款金额</td>
                        <td align="right"><strong>￥<@tools.money num=(order.totalprice-order.deposit) format="0.##"/></strong></td>
                    </tr>
                    <tr>
                        <td align="left">已付尾款金额</td>
                        <td align="right"><strong>￥0.00</strong></td>
                    </tr>
                </table>
            </dd>
            <div class="clearfix"></div>
        </dl>
        <dl style="border: 0 none;">
            <dt>&nbsp;</dt>
            <dd>
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td align="left">订单金额</td>
                        <td align="right"><strong class="col-red">￥<@tools.money num=order.totalprice format="0.##"/></strong></td>
                    </tr>
                </table>
            </dd>
            <div class="clearfix"></div>
        </dl>
    </div>
</div>
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script>
	$(function(){
		$(".back").click(function(){
			history.go(-1);
		})
	})
</script>
<script>
	var s=1000;
	var m=60*1000;
	var h=60*60*1000;
	var d=24*60*60*1000;
	var createtime = "${order.createtime?datetime}";
	createtime = createtime.replace(/-/g,"/");
	var expire = new Date(createtime);
	var afterdays = ${afterdays!''};
	var deadline = expire.getTime()+afterdays*d;
	
	function getDeadline(){
		var day,hour,min,sec;
		var cur = new Date().getTime();
		var dur = deadline - cur;
		if(dur<=0){
			day=0;
			hour=0;
			min=0;
			sec=0;
		}else{
			day=parseInt(dur/d);
			dur=dur%d;
			hour=parseInt(dur/h);
			if(hour<10){
				hour = '0'+hour;
			}
			dur=dur%h;
			min=parseInt(dur/m);
			if(min<10){
				min = '0'+min;
			}
			dur=dur%m;
			sec=parseInt(dur/s);
			if(sec<10){
				sec = '0'+sec;
			}
		}
		$("#day").html(day);
		$("#hms").html(hour+":"+min+":"+sec);
		
		if(day=='0'&&hour=='0'&&min=='0'&&sec=='0'){
			clearInterval(timer);
		}
	}
	
	window.onload = getDeadline();
	var timer = setInterval('getDeadline()', 1000);
</script>
</body>
</html>