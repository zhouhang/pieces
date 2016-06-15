<#setting url_escaping_charset='utf8'> 
<!-- update by Calvin.wh seo优化 2015.10.27 START -->
<title>
	<#if hearder_type==0>
		${keyWords}_${keyWords}中药材搜索-珍药材
	<#elseif hearder_type==1>
		${search_keywords}_${search_keywords}批发-珍药材
	<#elseif hearder_type==2>
		${search_keywords}_${search_keywords}批发-珍药材
	<#elseif hearder_type==3>
		${goodsInfo.breedName}_${goodsInfo.title}-珍药材
	<#else>
		珍药材 中药材电子商务 有质量保障的仓储式中药材综合服务平台
	</#if>
</title>

<#if hearder_type==0>
	<meta name="description" content="珍药材电子商务平台为您提供大量${keyWords}采购、${keyWords}销售、${keyWords}价格、${keyWords}批发等${keyWords}信息，更多更全中药材交易信息尽在珍药材网"/>
	<meta name="keywords" content="${keyWords},${keyWords}批发价格,${keyWords}产地,${keyWords}市场"/>
<#elseif hearder_type==1>
	<meta name="description" content="珍药材网是国内专业的${search_keywords}现货交易平台，为您提供${search_keywords}市场，${search_keywords}批发，${search_keywords}价格，${search_keywords}规格等相关${search_keywords}信息"/>
	<meta name="keywords" content="${search_keywords}规格，${search_keywords}产地，${search_keywords}价格"/>
<#elseif hearder_type==2>
	<meta name="description" content="珍药材网是国内专业的${search_keywords}现货交易平台，为您提供${search_keywords}市场，${search_keywords}批发，${search_keywords}价格，${search_keywords}规格等相关${search_keywords}信息"/>
	<meta name="keywords" content="${search_keywords}规格，${search_keywords}产地，${search_keywords}价格"/>
<#elseif hearder_type==3>
	<meta name="description" content="珍药材网为您提供${goodsInfo.title}供求，报价，规格，产地等在线交易信息"/>
	<meta name="keywords" content="${goodsInfo.breedName}，${goodsInfo.breedName}${goodsInfo.grade}，${goodsInfo.origin }${goodsInfo.breedName}"/>
<#else>
	<meta name="description" content="珍药材网-中国首创最大最有保障的线上线下相结合的电子商务仓储式综合服务平台，提供各类大品种药材、小品种药材、涨跌价紧俏药材，保证现货，保证中药材质量，提供线上交易、仓储服务、物流运输、融资服务、委托服务和价格行情资讯，让你感受到最全面、最专业的中药材买卖及各类相关综合服务。"/>
	<meta name="keywords" content="珍药材网，中药材，中药材价格行情，中药材交易，中药材仓储物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
</#if>
<!-- update by Calvin.wh seo优化  2015.10.27 END -->
