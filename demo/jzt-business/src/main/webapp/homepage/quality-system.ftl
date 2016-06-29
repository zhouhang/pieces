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
    <style>
        .box_1{background: url("${RESOURCE_IMG}/images/quality_s1.png") no-repeat; width: 1200px; height: 317px; overflow: hidden;}
        .box_3{background: url("${RESOURCE_IMG}/images/quality_s2.png") no-repeat; width: 1200px; height: 489px; overflow: hidden; margin:60px auto;}
        .box_4{background: url("${RESOURCE_IMG}/images/quality_s3.png") no-repeat; position: relative; width: 1200px; height: 1173px; overflow: hidden;}
		.box_5{background: url("${RESOURCE_IMG}/images/quality_s4.png") no-repeat; width: 1200px; height: 648px; overflow: hidden; margin:70px auto; }
                .title{font-family: "microsoft yahei"; font-size: 24px; font-weight: 400; padding-top: 30px; color: #bd0200; text-align:center; margin-top:60px;}
        .p_text{padding:5px 50px 0 505px; color: #565656; line-height: 32px; font-family:"微软雅黑";}
        .p_text2{color: #666666; line-height: 30px; font-size: 14px; padding-top:60px; padding-bottom:60px; font-family:"微软雅黑";}

    </style>
</head>
<body>

<!--topper start-->
<#include "common/indexListHeader.ftl">
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
    <div class="box_1"></div>
    <!--<div class="box_2 cur">
        <div class="pd1 arrow"></div>
        <div class="pd2 arrow"></div>
        <div class="pd2 arrow"></div>
        <div class="pd2 arrow"></div>
        <div class="pd3 arrow"></div>
        <div class="hover1"></div>
        <div class="hover2"></div>
        <div class="hover3"></div>
    </div>-->
    <h2 class="title">覆盖全国的检测网络</h2>
    <div class="box_3">
        
        <p class="p_text">九州通中药材电子商务有限公司为保障线上线下交易的中药材实现批批检验，不合格不入库，不合格不挂牌交易，充分考虑中药材品种多，产地分散，交易频次高，中间环节多，人为因素复杂，交易要求快速得到检
验结果等特点，在全国主要药材产地和药材市场建设中药材检验实验室，形成覆盖全国的检测网络。其中包
括武汉总部的检验中心，16个检验分中心，50个产地监测站。监测网络结构图如下：</p>
    </div>
    <h2 class="title">专业的质检团队与设备</h2>
    <p class="p_text2">质量检验和质量管理工作都需要相应的专业人员才能实现，高技能人才是实现中药材质量保证的关键因素。中药电商公司目前具有中药材质量检验人员20人，质量管理人员15人。其中全面负责中药电商公司质量工作的质量总监，大学本科中药学专业毕业，在中药行业工作25年，具有较丰富的中药材质量管理经验；负责质量检验的人员均有大专以上学历，具有中药学或相关专业知识， 本科以上学历的占90%，其中硕士1人，博士1人。负责中药材质量管理的主要人员在中药行业工作20年以上具有丰富的中药材经营和信息管理经验，对中药材商品规格等级及交易规则非常熟悉。</p>

    
    <div class="box_4"></div>
    	<h2 class="title">严谨的质量检测程序</h2>
    <div class="box_5"></div>
</div>

<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/listFooter.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript">
//var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254171531'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1254171531' type='text/javascript'%3E%3C/script%3E"));
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
</body>
</html>