<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的珍药材</title>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />    
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/store_foreground.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/list.css"  />
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<#import 'page.ftl' as fenye>
<#import 'macro.ftl' as tools>
<#assign TIPS="对不起，暂无数据!" /> <!--配置常用提示语-->
</head>
<body>
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
	<#include 'common/left.ftl'>
    <div class="hy-right fr">
                <div class="cd-main">
            <h1 class="title_deal">我的挂牌</h1>
            <form action="/listing/manager" method="get" id="conditionForm">
                <ul class="stage-search">
                    <li>
                    <span>按状态：</span><select id="listingflag" name="listingflag" class="text-store text-select">
                        <option value="">全部</option>
                        <#if flagMap??>
		            		<#list flagMap?keys as key>
		            			<option value="${key}" <#if listingflag == key>selected</#if>>${flagMap[key]}</option>
		            		</#list>
	            		</#if>
                    </select>
                    <span>按挂牌日期：</span><input name="startdate" value="${(page.params.startdate)!''}" type="text" class="text-store text-6 mr10 Wdate" id="datetimepicker1" value=""  />至<input type="text" name="enddate" value="${(page.params.enddate)!''}" class="text-store text-6 ml10 Wdate" id="datetimepicker2"/>
                    <span>品种：</span><input id="breedname" name="breedname" class="text-store text-5" type="text" value="${(page.params.breedname)!''}" />
                    <span><a class="col_999" href="javascript:void(0);" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');">清空</a></span><input type="submit" class="btn-red ml10" id="btn-blue" value="查询" />
                    <br/><span>是否已有订单：</span><select name="orderflag" class="text-store text-select">
                        <option value="">全部</option>
                        <option value="1" <#if page.params.orderflag == 1>selected</#if>>已有订单</option>
                        <option value="0" <#if page.params.orderflag == 0>selected</#if>>没有订单</option>
                    </select>
                    </li>
                </ul>
            </form>
            <div class="use-item1">
               <table class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="100">挂牌编号</th>
                        <th width="200">标题</th>
                        <th width="100">已有订单</th>
                        <th width="200">摘牌数量/挂牌数量</th>
                        <th width="100">挂牌期限</th>
                        <th width="150">挂牌单价</th>
                        <th width="100">挂牌货值</th>
                        <th width="150">状态</th>
                        <th width="150">存放仓库</th>
                        <th width="100">挂牌时间</th>
                        <th width="150">操作</th>
                    </tr>
                    <#if page.results??>
	                   <#list page.results as busiListing>
		                    <tr>
		                        <td><a href="/listing/detail?id=${busiListing.listingid!''}">${busiListing.listingid!''}</a></td>
		                        <td class="opr-btn">
			                        <span class="operate-1 operate-a">
			                        	<#if busiListing.title?? && busiListing.title?length gt 6>
			                    			${busiListing.title?substring(0,6)}...
			                    		<#else>
			                    			${busiListing.title!''}
			                    		</#if>
				                        <div class="tips tipa" align="left">
					                        <span class="sj"></span>
					                        ${busiListing.title!''}
				                        </div>
			                        </span>
		                        </td>
		                        <td>${busiListing.ordernum!''}</td>
		                        <td><span id="${busiListing.listingid!''}OrderAmount">
		                        <#if busiListing.listingflag??>
					              	<#if busiListing.listingflag==4>
					              		--
					              	<#else>
		                        		<@tools.money num=(busiListing.listingamount-busiListing.surpluses) format="0.##"/>${busiListing.dictvalue!''}
		                        	</#if>
				              	</#if></span>
		                        /<@tools.money num=busiListing.listingamount format="0.##"/>${busiListing.dictvalue!''}</td>
		                        <td>
		                        	<#if busiListing.listingflag??>
						              	<#if busiListing.listingflag==2>
						              		<#if busiListing.expiretime??>
							              		${busiListing.expiretime?string("yyyy-MM-dd HH:mm:ss")!''}
							              	<#else>
				                        		--
				                        	</#if>
						              	<#else>
						              		--
						              	</#if>
					              	</#if>
		                        </td>
		                        <td><@tools.money num=busiListing.lowunitprice format="0.##"/>元/${busiListing.dictvalue!''}</td>
		                        <td><@tools.money num=busiListing.totalprice format="0.##"/>元</td>
	                        	<#if busiListing.listingflag??>
	                        		<td id="${busiListing.listingid!''}Flag"
						              	<#if busiListing.listingflag==0>
						              		>待审核
						              	<#elseif busiListing.listingflag==1>
						              		class="opr-btn text_red">
						                        <span class="operate-1 operate-a">
						                        	审核失败
							                         <div class="tips tipa" align="left">
						                        		<span class="sj"></span>
								                       	 您的挂牌审核不通过,原因是:${busiListing.examinecontent?trim}</div>
						                        </span>
					                        
						              	<#elseif busiListing.listingflag==2>
						              		class="text_green">挂牌中
						              	<#elseif busiListing.listingflag==3>
						              		class="text_gray">已完成
						              	<#elseif busiListing.listingflag==4>
						              		class="text_gray">已取消
						              	</#if>
					              	</td>
					            <#else>
					            	<td>&nbsp;</td>  	
				              	</#if>
		                        <td>${busiListing.warehousename!''}</td>
		                        <td>
		                        	<#if busiListing.createtime??>
		                        		${busiListing.createtime?string("yyyy-MM-dd HH:mm:ss")!''}
		                        	</#if>	
		                        </td>
		                        <td id="${busiListing.listingid!''}Action">
		                        	<#if busiListing.listingflag??><!--update by fanyuna 2015.11.26 当处于挂牌中状态时，只有可摘大于0时，才可以修改挂牌-->
						              	<#if (busiListing.surpluses!=0) && (busiListing.listingflag==0 || busiListing.listingflag==1 || busiListing.listingflag==2)>
						              		<a style="color:#0088cc;" href="/listing/update?id=${busiListing.listingid!''}">修改</a> 
						              	</#if>	
						              	<#if busiListing.listingflag==2 && busiListing.surpluses!=0>
						              		<span><a style="color:#0088cc;" href="javascript:disabledListing('${busiListing.listingid!''}','${busiListing.title!''}')">下架</a></span>
						              	</#if>
					              	</#if>
		                        </td>
		                    </tr> 
                       	</#list>
                    <#else>
	                    <tr align="center">
					    	<td colspan="11" style="font-family:微软雅黑;font-size:14px;">${TIPS}</td>
					    </tr>	
	                </#if>
                </table>
            </div>
            <@fenye.pages page=page form="conditionForm"/>
        </div>
	</div>
</div>
<!-- 表格底部 end  -->
<#include 'common/footer.ftl'>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript"> 
	//标题hover提示
	var Height = $('.tips').height()+18;
	$('.opr-btn .tips').css('top',-Height);
	$('.operate-1').hover(
		        function(){
		        	var Height = $(this).children('.tips').height()+8;
		        	$(this).children('.tips').show();
		            $(this).children('.tips').show();
		            $(this).children('.tips').css('top',-Height);
		        },
		        function(){
		            $(this).children('.tips').hide();
		        }
	);
    //日期控件初始化
	$('#datetimepicker1').click(function(){
		WdatePicker({
			//startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			maxDate:'#F{$dp.$D(\'datetimepicker2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#datetimepicker2').click(function(){
		WdatePicker({
			//startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			minDate:'#F{$dp.$D(\'datetimepicker1\',{d:1});}',
			readOnly:true
		});
	});
	//tips提示框
	function tips(str){
		bghui();
		Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-style',
                ev:{click:function(data){
                	 disappear();
                     $(".bghui").remove();
                 }
               }
            }]
	    });
	};
    //下架挂牌
    function disabledListing(listingId,listingtitle){
    	bghui();
		Alert({
           // str: '确定要下架编号 "'+listingId+'" 的挂牌吗？',
           str: '确定要下架标题为 "'+listingtitle+'" 的挂牌吗？',
            buttons:[{
                name:'确定',
                classname:'btn-style',
                ev:{click:function(data){
                	 disappear();
                     $(".bghui").remove();
                     $.ajax({
						async : false,
						cache : false,
						type : "POST",
						data : {"listingId":listingId},
						dataType : "json",
						url : "/listing/disabledListing",
						success : function(data) {
							var ok = data.ok;
							var msg = data.msg;
							if(ok==true){
								$('#'+listingId+'OrderAmount').text('--');
								$('#'+listingId+'Flag').attr('class','text_gray').text('已取消');
								$('#'+listingId+'Action').html('<a style="color:#0088cc;" href="/listing/update?id='+listingId+'">修改</a>');
							}
							tips(msg);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							tips('操作失败！');
						}
					});
                 }
               }
            }]
	    });
	};		
</script>
</body>
</html>