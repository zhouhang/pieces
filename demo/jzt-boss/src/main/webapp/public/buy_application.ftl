<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>购买申请</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
	<#import 'macro.ftl' as tools>
</head>
<body>
<#include "home/top.ftl" />
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">购买申请</h1>
            <form action="/busiOrderApply" method="get" id="conditionForm">
                <ul class="store-search">
                    <li><span>订单编号：</span><input name="orderid" value="${page.params.busiOrderSearchDto.orderid!''}" class="text-store text-7" type="text" />
                      <span>标题：</span><input name="title" value="${page.params.busiOrderSearchDto.title!''}" class="text-store text-7" type="text" />
                      <span>订单状态：</span><select name="orderstate" class="text-store text-7">
                        <option value="">全部</option>
                   		<#if orderstateMap??>
                   			<#list orderstateMap?keys as key>
                    			<option value="${key!'' }" <#if page.params.busiOrderSearchDto.orderstate == key>selected</#if>>${orderstateMap[key]!'' }</option>
                    		</#list>
                   		</#if>
                    </select>
                    <span>挂牌时间：</span><input id="wdate1" name="startlistingdate" value="${page.params.busiOrderSearchDto.startlistingdate!''}" type="text" class="text-store text-7 mr10 Wdate" />至<input id="wdate2" name="endlistingdate" value="${page.params.busiOrderSearchDto.endlistingdate!''}" type="text" class="text-store text-7 ml10 Wdate" />                   
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
               <table id="busiOrderTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="100">订单编号</th>
                        <th width="150">标题</th>
                        <th width="100">挂牌单价</th>
                        <th width="100">订购数量</th>
                        <th width="100">申请用户</th>
                        <th width="100">挂牌用户</th>
                        <th width="100">总价</th>
                        <th width="150">申请时间</th>
                        <th width="100">状态</th>
                        <th width="150">操作</th>
                    </tr>
                    <#if (page.results?size>0)>
	                     <#list page.results as busiOrder>
	                    	<tr>
	                    		<td>${busiOrder.orderid!''}</td>
	                    		<#if (busiOrder.title?length > 6)>
                    				<td class="opr-btn"><span class="operate-1 operate-a">${busiOrder.title?substring(0,6)} …<div class="tips tip_store" align="left"><span class="sj"></span>${busiOrder.title!''}</div></span></td>
                    			<#else>
                    				<td>${busiOrder.title!''}</td>
                    			</#if>
	                    		<td><@tools.money num=busiOrder.unitprice format="0.##"/>元/${busiOrder.wlunit!''}</td>
	                    		<td><@tools.money num=busiOrder.amount format="0.##"/>${busiOrder.wlunit!''}</td>
	                    		<td><a href="/getMemberManage/getMemberByUserName?memberName=${busiOrder.buyername!''}">${busiOrder.buyername!''}</a></td>
	                    		<td><a href="/getMemberManage/getMemberByUserName?memberName=${busiOrder.username!''}">${busiOrder.username!''}</a></td>
	                    		<td>
	                    			<#if busiOrder.orderstate == 1>
	                    				<input value=<@tools.money num=busiOrder.discountprice format="0.##"/> class="text-23 ${busiOrder.orderid}" type="text" onclick="addDialog('${busiOrder.orderid}')" />
	                    			<#else>
	                    				<span class="${busiOrder.orderid}"><@tools.money num=busiOrder.discountprice format="0.##"/></span>元
	                    			</#if>
	                    		</td>
	                    		<td>${busiOrder.createtime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
	                    		<td>
	                    			<#if busiOrder.orderstate == 0>
		                    			洽谈中
									<#elseif busiOrder.orderstate == 1> 
										已完成
									<#elseif busiOrder.orderstate == 2> 
										已取消
									</#if>
	                    		</td>
	                        	<td class="opr-btn">
	                        		<#if busiOrder.orderstate = 0>
		                    			<a title="完成交易" href="javascript:alterBusiOrderState('/busiOrderApply/finishBusiOrder?orderid=${busiOrder.orderid}',1)"><span class="operate-7"></span></a>
										<a title="关闭交易" href="javascript:alterBusiOrderState('/busiOrderApply/cancelBusiOrder?orderid=${busiOrder.orderid}',2)"><span class="operate-4"></span></a>
									</#if>
		                        </td>
	                    	</tr>
	                    </#list>
                    <#else>
                    	<tr>
                    		<td colspan="10">没有数据!</td>
                    	</tr>
                    </#if>
                </table>
            </div>
            <@tools.pages page=page form="conditionForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 弹层  -->
<div id="alterDiscountPrice" style="display: none;" title="订单编号">
	<p style="color:#656565;line-height:27px;">请填写总价：<span id="alterDiscountPriceError" style="color:red;display:none;"></span></p>
	<input id="discountPrice" class="text-store text-23" style="width:415px;" />
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	//日期控件
	$('#wdate1').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#wdate2').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
			readOnly:true
		});
	});
	//tipa
	var Height = $('.tips').height()+18;
	$('.opr-btn .tips').css('top',-Height);
    $('.operate-1').hover(
            function(){
                $(this).children('.tips').show();
            },
            function(){
                $(this).children('.tips').hide();
            }
    );
	function tips(str){
		bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                     }
	               }
	            }]
	    });
	};
	//TABLE行高亮
	function alterTdBg(tableId,tdId,flag){
		var trs = $('#'+tableId+' tr');
		$.each(trs,function(idx,obj){
			var trTd = $(obj).find('td:eq(0)').text();
			var tdBg = $(trTd).css('backgroundColor');
			if(trTd==tdId){
				alert(tdBg);
				var trTds = $(obj).find('td');
				if(flag){
					$.each(trTds,function(i,o){
						$(o).css('backgroundColor','silver');
					});
				}else{
					$.each(trTds,function(i,o){
						if(tdBg=='undeifined'){
							$(o).css('backgroundColor','');
						}else{
							$(o).css('backgroundColor',tdBg);
						}
					});
				}
				return;
			}
		});
	};
	//修改总价
	function addDialog(orderid){
		$('#alterDiscountPriceError').hide();
		var oldDiscountPrice = $('.'+orderid).val();
		$('#discountPrice').val(oldDiscountPrice);
		$("#alterDiscountPrice").dialog({
	      	autoOpen : true,
			modal : true,
			width : 450,
			resizable : false,
			buttons : {
				修改 : function() {
					var newDiscountPrice = $('#discountPrice').val();
					var reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
					if(newDiscountPrice==''){
						$('#alterDiscountPriceError').text('总价不能为空！').show();
					}else{
						if(!reg.test(newDiscountPrice)){
							$('#alterDiscountPriceError').text('总价为正数,且至多2位小数！').show();
						}else{
							if(parseFloat(newDiscountPrice)>parseFloat(oldDiscountPrice)){
								$('#alterDiscountPriceError').text('总价不能高于 '+oldDiscountPrice+' ！').show();
							}else{
								var url = '/busiOrderApply/alterDiscountPrice?orderid='+orderid+'&&discountprice='+newDiscountPrice;
								$.ajax({
									async : false,
									cache : false,
									type : 'GET',
									dataType : 'json',
									url : url,
									success : function(data) {
										var ok = data.ok;
										if(ok==true){
											$('.'+orderid).val(parseFloat(newDiscountPrice));
											$('#alterDiscountPrice').dialog('close');
											tips('操作成功！');
										}else{
											tips('操作失败！');
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown){
										tips('操作失败！');
									}
								});
								$('#alterDiscountPriceError').hide();
							}
						}
					}
				}
			}
	 	});
		$('#ui-id-1').text('订单编号：'+orderid);
	};
    //交易状态
    function alterBusiOrderState(url,index){
    	var str = '关闭交易吗？';
    	if(index==1){
    		str = '完成交易吗？';
    	}
    	bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         $.ajax({
							async : false,
							cache : false,
							type : 'GET',
							dataType : 'json',
							url : url,
							success : function(data) {
								var ok = data.ok;
								if(ok==true){
									var orderid = data.obj;
									var orderidsTrs = $('#busiOrderTable tr');
									$.each(orderidsTrs,function(idx,obj){
										var orderidTd = $(obj).find('td:eq(0)').text();
										if(orderidTd==orderid){
											$(obj).find('td:last').html('');
											if(url.indexOf('finishBusiOrder')!=-1){
												$(obj).find('td:last').prev().html('已完成');
												var discountprice = $('.'+orderid).text();
												$(obj).find('td:last').prev().prev().prev().html('<input value='+discountprice+' class="text-23 '+orderid+'" type="text" onclick="addDialog('+'\''+orderid+'\''+')" />');
											}else if(url.indexOf('cancelBusiOrder')!=-1){
												$(obj).find('td:last').prev().html('已取消');
											}
											return;
										}
									});
									tips('操作成功！');
								}else{
									tips('操作失败！');
								}
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