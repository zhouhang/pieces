<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>站点列表</title>
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
         <h1 class="title1">站点列表</h1><br/>
         <div class="use-item1 mt25">
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="10%">编号</th>
                        <th width="10%">站点名称</th>
                        <th width="10%">站点标题</th>
                        <th width="10%">站点Logo</th>
                        <th width="10%">站点域名</th>
                        <th width="10%">描述</th>
                        <th width="10%">关键字</th>
                        <th width="10%">主题</th>
                        <th width="10%">版权信息</th>
                        <th width="10%">创建时间</th>
                    </tr>
                    <tbody id="cmsSites">
                    <#list cmsSites as cmsSite>
                    	<tr>
			              <td align="center">${cmsSite.id}</td>
			              <td align="center">${cmsSite.name}</td>
			              <td align="center">${cmsSite.title}</td>
			              <td align="center">${cmsSite.logo}</td>
			              <td align="center">${cmsSite.domain}</td>
			              <td align="center">${cmsSite.description}</td>
			              <td align="center">${cmsSite.keywords}</td>
			              <td align="center">${cmsSite.theme}</td>
			              <td align="center">${cmsSite.copyright}</td>
			              <td align="center">${cmsSite.create_date}</td>
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
	      containerID : "cmsSites",  
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