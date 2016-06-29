<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材 中药材电子商务 有质量保障的仓储式中药材综合服务平台</title>
    <meta name="description" content="珍药材网-中国首创最大最有保障的线上线下相结合的电子商务仓储式综合服务平台，提供各类大品种药材、小品种药材、涨跌价紧俏药材，保证现货，
保证中药材质量，提供线上交易、仓储服务、物流运输、融资服务、委托服务和价格行情资讯，让你感受到最全面、最专业的中药材买卖及各类相关综合服务。
"/>
    <meta name="keywords" content="珍药材网，中药材，中药材价格行情，中药材交易，中药材仓储物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <!-- <style>
        .box_1{background: url("${RESOURCE_IMG}/images/i1-pic8.png") no-repeat center; width: 1200px; height: 493px; overflow: hidden; margin-top: 20px;}
        .area-1078{width: 1078px; margin: 0 auto;}
        .box_2{background: url("${RESOURCE_IMG}/images/i1-pic9.png") no-repeat;  height: 423px; overflow: hidden;}
        .box_3{background: url("${RESOURCE_IMG}/images/i1-pic10.png") 30px bottom no-repeat; width: 640px; height: 420px; overflow: hidden; float: left;}
        .box_4{background: url("${RESOURCE_IMG}/images/i1-pic11.png") no-repeat; position: relative; width: 430px; height: 351px; overflow: hidden; margin: 55px 0 40px;}
        .title{font-family: "microsoft yahei"; font-size: 24px; font-weight: 400; padding-top: 50px; color: #363636;}
        .p_text{padding:20px 0; color: #666; font-family: "microsoft yahei"; line-height: 28px; width: 455px; font-size: 13px;}
        .p_text2{padding:20px 0; color: #666; font-family: "microsoft yahei"; line-height: 28px; width: 590px; font-size: 13px;}
        .box_4 div{float: left; width: 208px; height: 168px; margin: 0 0 15px;}
        .box_4 div.hover1{margin-right: 14px;}
        .box_4 div.hover3{margin-right: 14px;}
        .box_4 div.hover1.cur,
        .box_4 div.hover2.cur,
        .box_4 div.hover3.cur,
        .box_4 div.hover4.cur{background: #9f0200;}
        .box_4 div.hover1 p,
        .box_4 div.hover2 p,
        .box_4 div.hover3 p,
        .box_4 div.hover4 p{display: none; padding: 32px 20px; color: #fff;  line-height: 26px;}
        .box_4 div.hover1.cur p,
        .box_4 div.hover2.cur p,
        .box_4 div.hover3.cur p,
        .box_4 div.hover4.cur p{display: block;}
    </style> -->
    <style>
	    .area-1066{width: 1066px; margin: 0 auto;}
		.service_box{ background: url(${RESOURCE_IMG}/images/stock_s.png) no-repeat center; height: 519px; overflow: hidden; margin-top: 20px;}
		.service_box1{ background: url(${RESOURCE_IMG}/images/stock_s1.png) no-repeat center; height: 596px; overflow: hidden; margin-top: 18px;}
		.service_box2{ background: url(${RESOURCE_IMG}/images/stock_s2.png) no-repeat center; height: 818px; overflow: hidden; margin-top: 60px;}
		.service_box3{ background: url(${RESOURCE_IMG}/images/stock_s3.png) no-repeat center; height:   1114px; overflow: hidden; margin-top: 60px;}
		.service_box4{ background: url(${RESOURCE_IMG}/images/stock_s4.png) no-repeat center; height:   479px; overflow: hidden; margin-top: 60px;}
		.service_box5{ background: url(${RESOURCE_IMG}/images/stock_s5.png) no-repeat center; height:   499px; overflow: hidden; margin-top: 60px;}
	</style>
</head>
<body>
<!--topper strat-->
<#include "common/indexListHeader.ftl">
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
    <div class="area-1066">
	    <div class="service_box"></div>
	    <div class="service_box1"></div>
	    <div class="service_box2"></div>
	    <div class="service_box3"></div>
	    <div class="service_box4"></div>
	    <div class="service_box5"></div>
  	</div>
</div>

<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/listFooter.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script>
    function Hover(eml){
        $(eml).hover(
                function(){
                    $(this).addClass('cur');
                },
                function(){
                    $(this).removeClass('cur');
                }
        )
    }
    Hover('.box_4>div');
    $(function(){
    	$('#searchEngineListingButton').searcher({
		onSearch:function() {
		var keyword=$('input[type="text"].search-text').val();
		if(keyword == "输入名称找药材"){
			keyword='';
		}
		window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
	 	}
	});
    });
</script>
<script type="text/javascript">
//var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254171531'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1254171531' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>