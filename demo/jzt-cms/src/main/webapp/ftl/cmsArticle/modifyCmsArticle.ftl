<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>站点管理</title>
	<#include "/home/common.ftl">
	<#--分页Jpage需要的js和css-->
	<link rel="stylesheet" href="/html/resources/css/jPage/jPages.css"/>  
    <link rel="stylesheet" href="/html/resources/css/jPage/animate.css"/>  
    <script type="text/javascript" src="/html/resources/js/jPage/highlight.pack.js"></script>  
    <script type="text/javascript" src="/html/resources/js/jPage/tabifier.js"></script>
    <script type="text/javascript" src="/html/resources/js/jPage/jPages.js"></script> 
</head>
<body>
<div class="main">
        <div class="page-main">
            <h1 class="title1">文章管理</h1>
            <form action="/queryArticle" method="post" id="queryForm">
                <ul class="form-search">
                    <li>
                        创建时间段：<input type="text" value="${starttime}"   name="starttime"  id="starttime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2014-03-10 00:00:00'})" />至
                   <input type="text" value="${endtime}"  name="endtime"  id="endtime"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2014-03-10 00:00:00'})"/>
                    </li>
                    <li>
                    	<span>&nbsp;&nbsp;文章标题：</span><input class="text text-2 mr10" type="text" name="title" id="title" value="${title}"/>
                        <span>文章关键字：</span><input class="text text-2 mr10" type="text" name="keywords" id="keywords" value="${keywords}"/>
                     </li>
                    <li><div align="center"><input type="submit" class="btn-blue" id="btn-blue" value="查询" /></div></li>
                </ul>
            </form>
            <div class="use-item1">
                <a href="/addArticle" target="mainFrame">添加文章</a>
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="5%">编号</th>
                        <th width="15%">标题</th>
                        <th width="10%">关键字</th>
                        <th width="15%">描述</th>
                        <th width="8%">入库方式</th>
                        <th width="8%">审核状态</th>
                        <th width="10%">所属栏目</th>
                        <th width="13%">创建时间</th>
                        <th width="216">操作</th>
                    </tr>
                    <tbody id="cmsarticle">
                       <#if articlelist ??>
	                     <#list articlelist as article>
	                    	<tr>
				              <td align="center">${article.id}</td>
				              <td align="center">${article.title}</td>
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
				              <td width="216" class="opr-btn">
	                            <span class="operate-1 cur">
	                                <div class="tips">未开通</div>
	                            </span>
	                            <span class="operate-2"></span>
	                            <span class="operate-4"></span>
	                            <span class="operate-5"></span>
	                         </td>
				       	   </tr>
					    </#list>
					    <#else>
					    	<tr>
					    		<td colspan="9" style="font-family:微软雅黑;font-size:14px;">对不起，暂无数据!</td>
					    	</tr>
					    </#if>
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