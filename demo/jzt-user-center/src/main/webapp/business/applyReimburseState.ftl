<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>交易异常-平台处理退款申请</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#include 'common/header.ftl'>
<div class="area-1200 mb45 clearfix">
    <!-- 会员左侧 -->
    <div class="hy-left fl">
        <div align="center" class="mt10"><img src="${RESOURCE_IMG}/images/jzt-user-center/baozhang.png" /> </div>
        <div class="border-1 mt10">
            <h2 class="title">订单信息</h2>
            <div class="order-state">
                <div align="center"><img src="${RESOURCE_IMG_UPLOAD}/${busiAppeal.path!''}" /> </div>
                <p>${busiAppeal.title!''}<br /></p>
                <p>
		                    订单编号：<span class="col_blue">${busiAppeal.orderid!''}</span> <br />
		                    单    价：<span class="col_red">${busiAppeal.unitprice!''}</span> 元 / ${busiAppeal.dictvalue!''}<br />
		                    数    量：${busiAppeal.amount!''} ${busiAppeal.dictvalue!''}<br />
		                    总    价：<span class="col_red">${busiAppeal.totalprice!''}</span> 元<br />
		                    所在仓库：${busiAppeal.warehousename!''}
                </p>
            </div>
        </div>
    </div>
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="reimburse-box">
        	<#if busiAppeal.examinestate == 1>     	
	            <div class="process">
	                <span class="step2"></span>
	            </div>
	        <#elseif busiAppeal.examinestate == 2>
	        	<div class="process">
	                <span class="step3"></span>
	            </div>
	        <#elseif busiAppeal.examinestate == 3>
	        	<div class="process-re">
	                <span class="step3"></span>
	            </div>
			</#if>
			
            <h2 class="title1 mt20">申请退款</h2>
            <ul class="reim-form mt20">
                <li>
                    <label>取消原因：</label>
                    <span class="intro">${busiAppeal.appealtype!''}</span>
                </li>
                <li>
                    <label>具体描述：</label>
                    <span class="intro">${busiAppeal.reason!''}</span>
                </li>
                <li>
                    <label>证据上传：</label>
                    <span class="problem-pic">
                    	<#if busiAppeal.evidencepics??>
	                   		<#list busiAppeal.evidencepics as evidencepic>
	                   			 <a href="#"><img src="${RESOURCE_IMG_UPLOAD}/${evidencepic!''}" /><br/>${(evidencepic_index+1)!''}.证据${(evidencepic_index+1)!''}.jpg </a>
	                   		</#list>
	                   	</#if>
                    </span>
                </li>
                <#if busiAppeal.examinestate == 3>     	
					<li>
	                    <label>驳回原因：</label>
	                    <span class="intro">${busiAppeal.rejectreason!''}</span>
	                </li>
				</#if>
				<!--add by fanyuna 2015.08.18  增加申诉电话-->
				<li>
	                    <label><strong style="color:#F00">申诉电话：</strong></label>
	                    <span class="intro"><strong style="color:#F00;font-size:13px;">400-10-54315</strong></span>
	                </li>
            </ul>
        </div>
    </div>
</div>
<#include 'common/footer.ftl'>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
</body>
</html>