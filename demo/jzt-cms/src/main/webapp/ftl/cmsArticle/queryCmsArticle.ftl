<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>文章列表</title>
	<#include "/home/common.ftl">   
	<#--分页Jpage需要的js和css-->
	<link rel="stylesheet" href="/html/resources/css/jPage/jPages.css"/>  
    <link rel="stylesheet" href="/html/resources/css/jPage/animate.css"/>  
    <script type="text/javascript" src="/html/resources/js/jPage/highlight.pack.js"></script>  
    <script type="text/javascript" src="/html/resources/js/jPage/tabifier.js"></script>
    <script type="text/javascript" src="/html/resources/js/jPage/jPages.js"></script> 
</head>
<body>
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
         <h1 class="title1">文章列表</h1><br/>
         <div class="use-item1 mt25">
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="5%">编号</th>
                        <th width="17%">标题</th>
                        <th width="10%">外链</th>
                        <th width="5%">作者</th>
                        <th width="10%">关键字</th>
                        <th width="17%">描述</th>
                        <th width="8%">入库方式</th>
                        <th width="8%">审核状态</th>
                        <th width="10%">所属栏目</th>
                        <th width="10%">创建时间</th>
                    </tr>
                    <tbody id="cmsarticle">
                    <#list articlelist as article>
                    	<tr>
			              <td align="center">${article.id}</td>
			              <td align="center">${article.title}</td>
			              <td align="center">${article.link}</td>
			              <td align="center">${article.createBy}</td>
			              <td align="center">${article.keywords}</td>
			              <td align="center">${article.description}</td>
			              <td align="center">
			              	 <#if article.toFrom ??>
				              	<#if article.toFrom==1>
				              		爬虫采集
				              	<#elseif article.toFrom==2>
				              		官方录入
				              	<#else>
				              		用户录入
				              	</#if>
				             <#else>
				              	缺省...
				             </#if>	
			              </td>
			              <td align="center">
			              	<#if article.state ??>
				              	<#if article.state==0>
				              		未审核
				              	<#elseif article.state==1>
				              		已审核
				              	<#else>
				              		审核不通过
				              	</#if>	
				             <#else>
				              	缺省...
				             </#if>	
			              </td>
			              <td align="center">${article.name}</td>
			              <td align="center">${article.createDate}</td>
			            </tr>
					</#list>
					</tbody>
                </table>
            </div>
        <div class="holder"></div>
     </div>
  </div>
</body>
<script type="text/javascript">
	  $(function(){ 
	    $("div.holder").jPages({  
	      containerID : "cmsarticle",  
	      first:'首页',
	      last:'末页',
	      previous : "上一页",  
	      next : "下一页",
	      perPage : 10,  
	      startPage:1,
	      startRange:2,
	      midRange:5,
	      endRange:2,
	      keyBrowse:true,
	      delay : 10  
	    });  
	  });  
  </script>
</html>