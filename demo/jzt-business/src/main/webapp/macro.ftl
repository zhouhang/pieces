<!-- 隐藏电话号码中间5位 -->
<#macro phonemidhid phoneno>
	<#if phoneno??>
		${phoneno?substring(0, 3)}****${phoneno?substring((phoneno?length - 4), phoneno?length)}
	</#if>
</#macro>
<!-- 数字处理 -->
<#macro number num, format><#if num?? && num gt 0>${num?string(format)?trim}<#else>0</#if></#macro>
<!-- 直接截取小数点后2位，不是四舍五入 -->
<#macro money num, format><#if num??><#if num?c?index_of(".")!=-1 && (num?c?substring(num?c?index_of(".")+1,num?c?length))?length gt 2>${num?c?substring(0,num?c?index_of("."))}.${num?c?substring(num?c?index_of(".")+1,num?c?index_of(".")+3)}<#else>${num?string(format)}</#if><#else>0</#if></#macro>