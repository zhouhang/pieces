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
            <h1 class="title1">站点查询</h1>
            <form action="/cmsSite/queryDynamicCmsSite" method="post" id="queryForm">
                <ul class="form-search">
                    <li>
                        创建时间段：<input type="text"   name="datetimepicker1"  id="datetimepicker1" value="${(queryMap.datetimepicker1)!''}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2014-03-10 00:00:00'})" />至
                   <input type="text"   name="datetimepicker2"  id="datetimepicker2"  value="${(queryMap.datetimepicker2)!''}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2014-03-10 00:00:00'})"/>
                    </li>
                    <li><span>&nbsp;&nbsp;站点名称：</span><input class="text text-2 mr10" type="text" name="name" id="name" value="${(queryMap.name)!''}"/>
                        <span>关键字：</span><input class="text text-2 mr10" type="text" name="keywords" id="keywords" value="${(queryMap.keywords)!''}" />
                        <span>主题：</span><input class="text text-2 mr10" type="text"  name="theme" id="theme" value="${(queryMap.theme)!''}"/></li>
                    <li><div align="center"><input type="button" class="btn-blue" id="btn-blue" value="查询" /></div></li>
                </ul>
            </form><br/>
            <div class="use-item1">
                <#--<span class="btn-add mb10"><a href="#">添加站点</a> </span>-->
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="8%">编号</th>
                        <th width="10%">站点名称</th>
                        <th width="10%">站点标题</th>
                        <th width="10%">站点域名</th>
                        <th width="10%">描述</th>
                        <th width="10%">关键字</th>
                        <th width="10%">主题</th>
                        <th width="10%">创建时间</th>
                        <th width="216">操作</th>
                    </tr>
                    <tbody id="cmsSites">
                      <#if cmsSites ??>
	                    <#list cmsSites as cmsSite>
	                    	<tr>
				              <td align="center" id="id${cmsSite.id}">${cmsSite.id}</td>
				              <td align="center" id="name${cmsSite.id}">${cmsSite.name}</td>
				              <td align="center" id="title${cmsSite.id}">${cmsSite.title}</td>
				              <td align="center" id="domain${cmsSite.id}">${cmsSite.domain}</td>
				              <td align="center" id="description${cmsSite.id}">${cmsSite.description}</td>
				              <td align="center" id="keywords${cmsSite.id}">${cmsSite.keywords}</td>
				              <td align="center" id="theme${cmsSite.id}">${cmsSite.theme}</td>
				              <td align="center id="createDate${cmsSite.id}">
				              ${(cmsSite.createDate?string("yyyy-MM-dd HH:mm:ss"))!''}
				              </td>
				             <td width="216" class="opr-btn">
	                            <span class="operate-2"></span>
	                            <span class="operate-4"></span>
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
</div>

<!-- 修改站点  -->
<div class="pop-position" style="display: none;width: 400px;" id="reMember">
 	<div class="close">关闭</div>
    <div class="box">
    	<h1 class="title1">修改站点</h1>
        <form id="reForm" action="" method="post">
            <ul class="form-1">
                <li>
                    <label >站点编号：</label>
                    <p id="reFrom-id"></p>
                    <p></p>
                </li>
                <li>
                    <label><i class="red">*</i> 站点名称：</label>
                    <p><input class="text text-1" type="text" value="" id="reFrom-name" /></p>
                </li>
                <li>
                    <label><i class="red">*</i> 站点标题：</label>
                    <p><input class="text text-1" type="text" value="" id="reFrom-title" /></p>
                </li>
                <li>
                    <label>关键字：</label>
                    <p><input class="text text-1" type="text" value="" id="reFrom-keywords" /></p>
                </li>
                <li>
                    <label>描述：</label>
                    <p><textarea class="text textarea-1" draggable="false"   id="reFrom-description"></textarea> </p>
                </li>
               <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="修改"/>
                    <input type="button" class="btn-hui ml10" value="取消" /> </p>
                </li>
            </ul>
        </form>
    </div>
 </div>
<!-- 修改站点 over  -->
<script>
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
	    
    	//查询按钮
         $("#btn-blue").click(function(){
         	//查询之前将body的东西清除掉
          	$("#cmsSites").empty();
    		$("#queryForm").submit();
   		 });
   		 
        $( "#reMember" ).dialog({
            autoOpen: false,
            width: 400,
            buttons: [
                {
                    click: function() {
                   //通过ajax的异步传递数据到后台
	               $.ajax({
					 url: "/cmsSite/modifyCmsSiteById",
					 type: 'post', 
					 data: 
					 {
						 "id":$("#reFrom-id").html(),
						 "name":$("#reFrom-name").val(),
						 "title":$("#reFrom-title").val(),
						 "keywords":$("#reFrom-keywords").val(),
						 "description":$("#reFrom-description").val()
					 },
					 dataType:"json",
					 success:function(data){
					 	if(data){
					 		var id=data.id;
					 		var name = data.name;
					 		var title=data.title;
					 		var keywords= data.keywords;
					 		var description = data.description;
					 		//替换在前台显示的数据
					 		$("#name"+id).html(name);
					 		$("#title"+id).html(title);
					 		$("#keywords"+id).html(keywords);
					 		$("#description"+id).html(description);
					 	}
	                },
	                 error:function(data){
	                   	alert("操作失败");
	                    }
					});
					//不管成功或者是失败都要清楚数据
					 $("#reFrom").removeData();
					 //$("#reFrom-description").val('');
                        $('.bghui').remove();
                    }
                }
            ]
        });

        var ReMember = $('.operate-2');
        ReMember.each(function(){
            $(this).click(function( event ){
				//获取当前的数据通过ajax传递给后台
				var id=$(this).parent().parent().children().first().html();
				$.ajax({
				 url: "/cmsSite/queryCmsSiteById",
				 type: 'post', 
				 data: {"id":id},
				 dataType:"json",
				 success:function(data){
                    //成功后返回json格式的字符串，并且解析为js对象显示在前台页面
                    //var obj = jQuery.parseJSON(data); 
                   
                    $("#reFrom-id").html(data.id);
                    $("#reFrom-name").val(data.name);
                    $("#reFrom-title").val(data.title);
                    $("#reFrom-keywords").val(data.keywords);
                    $("#reFrom-description").val(data.description);
                	},
                 error:function(data){
                   	alert("操作失败");
                    }
				});
				
				var html = '<div class="bghui"></div>';
                $('body').append(html);
               $( "#reMember" ).dialog( "open" );
            })
        });


	//删除站点
	var deleteMember = $('.operate-4');
 	deleteMember.each(function(){
 	$(this).click(function(event){
 		//通过ajax传递id到后台，然后进行删除操作
 		if(confirm("确定要删除站点吗？")){
 			//点击确认后面的操作
 			var id=$(this).parent().parent().children().first().html();
 			$.ajax({
				 url: "/cmsSite/deleteCmsSiteById",
				 type: 'post', 
				 data: {"id":id},
				 dataType:"json",
				 success:function(data){
                    //成功后返回json格式的字符串，并且解析为js对象显示在前台页面
					     if(data.flag){
					     	$("#id"+id).parent().remove();	
					     }else{
					     	alert("操作失败");
					     }            
                	},
                 error:function(data){
                   	alert("操作失败");
                    }
				});
 		}
 		})
 	});

        var ReMember = $('.operate-2');
        var Close = $('.close');
        var html = '<div class="bghui"></div>';
        ReMember.each(function(){
            $(this).click(function(){
                $('#reMember').show();
                $('body').append(html);
                
            })
        });
       Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
            })
        });
        $('.btn-add').on('click',function(){
            $('body').append(html);
        })
    });
</script>
</body>
</html>