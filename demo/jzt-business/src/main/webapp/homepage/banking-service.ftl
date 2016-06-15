<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材 中药材电子商务 有质量保障的仓储式中药材综合服务平台</title>
    <meta name="description" content="珍药材网-中国首创最大最有保障的线上线下相结合的电子商
务仓储式综合服务平台，提供各类大品种药材、小品种药材、涨跌价紧俏药材，保证现货，保证中药材
质量，提供线上交易、仓储服务、物流运输、融资服务、委托服务和价格行情资讯，让你感受到最全面
、最专业的中药材买卖及各类相关综合服务。
"/>
    <meta name="keywords" content="珍药材网，中药材，中药材价格行情，中药材交易，中药材仓储
物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <style>
		a:hover{ text-decoration:none;}
        .mt12{margin-top: 12px;}
        .border{border: 1px solid #edd4d4; border-top-color: #eeaead; border-left: 0 none;}
        .box-1{width: 195px; background-color: #eee; padding: 10px; position: relative;}
        .box-1 .icon-1,
        .box-1 .icon-2,
        .box-1 .icon-3{background: url("resources/images/jrbg2.png") no-repeat; position: absolute; bottom: 55px; right: 15px; width: 82px; height: 72px;}
        .box-1 .icon-1{background-position: 0 8px;}
        .box-1 .icon-2{background-position: 0 -98px;}
        .box-1 .icon-3{background-position: 0 -198px;}

        .box-1 h2{font-size: 14px; font-weight: bold; color: #c90301; line-height: 38px; border-bottom: 1px solid #e9c7c7; text-align: center;}
        .box-1 h3{color: #565656; line-height: 20px; text-align: center; padding: 10px 0;}
        .box-1 h3 strong{color: #c90301;}
        .box-1 p{color: #565656; line-height: 26px; padding: 0 10px; height: 78px; overflow: hidden;}
        .btn-red,.btn-hui{display:inline-block; line-height:35px; background: #e01f20; margin: 20px 0 20px 40px; border-radius: 3px; width: 119px; height: 35px; font-size: 14px; color: #fff; text-align: center;}
        .btn-red:hover,.btn-hui:hover{color: #fff;}
        .btn-hui{background: #999;}
        .box-2{width: 984px;}
        .tabs-1{background: #f3e3e3; height: 50px;}
        .tabs-1 li{float: left; padding: 0 48px; line-height: 50px; font-size: 14px; color: #2a2a2a; cursor: pointer;}
        .tabs-1 li.cur{background: url("resources/images/jr-bg.png") right -1px no-repeat #fff; color: #c90301; font-weight: bold;}
        .conts-1{padding: 20px 0 0 18px;}
        .conts-1 dl{float: left; width: 136px;}
        .conts-1 dl dt{background: url("resources/images/jrdlbg.png") no-repeat 0 0; width: 136px; height: 33px; line-height: 33px; font-weight: bold; text-align: center; color: #565656;}
        .conts-1 dl dt.cur{background-position: -138px 0; color: #fff;}
        .conts-1 dd{padding: 5px 15px 0; line-height: 20px; color: #565656;}
        .conts-2{background: url("resources/images/yr-pic1.png") no-repeat 50px 20px; height: 200px;}
        .conts-2 p{padding-left: 315px; font-size: 14px; padding-top: 25px; line-height: 28px;}
        .conts-2 p span{font-size: 12px;}
        .col_565656{color: #565656;}
        .conts-3{background: url("resources/images/yr-pic2.png") no-repeat 50px 20px; height: 200px;}
        .conts-3 p{padding-left: 315px; font-size: 14px; padding-top: 45px; line-height:32px;}
		.bank{ float:left; margin-top:20px; margin-bottom:10px;}
		.bank h1{ font-size:20px; line-height:30px; font-family:"微软雅黑"; margin-bottom:20px;}
    </style>
</head>
<body>
<!--topper strat-->
<#include "common/indexListHeader.ftl">
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200">
    <div style="padding-top: 12px;"><img src="${RESOURCE_IMG}/images/rz-1.jpg"/><img src="${RESOURCE_IMG}/images/rz-2.jpg" usemap="#Map2"/>
      <map name="Map2">
        <area shape="rect" coords="87,493,289,537" href="/busiWarehouseApply/iWillWarehousing">
      </map>
    <img src="${RESOURCE_IMG}/images/rz-3.jpg" usemap="#Map3"/>
    <map name="Map3">
      <area shape="rect" coords="909,454,1110,498" href="#">
    </map>
    <img src="${RESOURCE_IMG}/images/rz-4.jpg" usemap="#Map4"/>
    <map name="Map4">
      <area shape="rect" coords="87,428,288,473" href="#">
    </map>
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
    Hover('.box_2 .hover1,.box_2 .hover2,.box_2 .hover3,.box_4 .hover');
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