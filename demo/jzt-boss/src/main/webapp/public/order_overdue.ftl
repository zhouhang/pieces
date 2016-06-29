<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>过期订单处理</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/Validform/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/datetimepicker/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#import 'macro.ftl' as tools>
<!-- head start -->
<#include "home/top.ftl" />
<!-- head over -->
<div class="wapper">
<!-- nav start -->
<#include "home/left.ftl" />
<!-- nav over -->

<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">过期订单处理</h1>
            <form action="/depositOverdue" method="post" id="queryForm">
                <ul class="form-search ac-search">
                    <li><span>订单编号：</span><input class="text text-4" type="text" name="orderId" value="${query.orderId!''}"/>&nbsp;
                        <span>买家：</span><input class="text text-4" type="text" name="buyerName" value="${query.buyerName!''}"/>&nbsp;
                        <span>卖家：</span><input class="text text-4" type="text" name="sellerName" value="${query.sellerName!''}"/>&nbsp;
                         <span>按状态：</span><select class="selecte text-4" name="depositState" value="${query.depositState!''}">
                            <option value="">全部</option>
                            <#if stateMap??>
                            	<#list stateMap?keys as key>
                            		<option value="${key!''}" <#if query.depositState == key>selected</#if>>${stateMap[key]!'' }</option>
                            	</#list>
                            </#if>
                        </select>&nbsp;
                        <span>订单过期时间：</span><input class="text text-date" id="date_b" type="text" name="orderDateStart" value="${query.orderDateStart!''}"/> — <input class="text text-date" id="date_e" type="text" name="orderDateEnd" value="${query.orderDateEnd!''}"/>&nbsp;
                        <!--add by fanyuna 2015.07.30 增加业务员-->
					 <span>业务人员：</span><input class="text text-4" type="text" name="salesmanName" value="${query.salesmanName!''}"/>&nbsp;
&nbsp;<a href="javascript:;" onclick="resetForm('queryForm')" class="col_999">清除</a>&nbsp;
                      <input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1 mt20">
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="10%" class="bgf5">订单编号</th>
                        <th width="12%" class="bgf5">商品名称</th>
                        <th width="8%" class="bgf5">买家</th>
                        <th width="8%" class="bgf5">卖家</th>
                        <th width="6%" class="bgf5">状态</th>
                        <th width="10%" class="bgf5">订单过期时间</th>
                        <th width="10%" class="bgf5">处理完成时间</th>
                        <th width="8%" class="bgf5">操作人</th>
                        <th width="10%" class="bgf5">买家业务人员</th>
                        <th width="10%" class="bgf5">卖家业务人员</th>
                        <th width="15%" class="bgf5">操作</th>
                    </tr>
                    <#if page.results?? && (page.results?size>0)>
                    	<#list page.results as orderDeposit>
                    		<tr>
		                        <td>
		                        	<@shiro.hasPermission name="过期订单处理-订单详情">
		                        		<a class="col_blue" name="nub" href="javascript:;" data-id="${orderDeposit.orderId!''}" data-reclick="n">${orderDeposit.orderId!''}</a>
		                        	</@shiro.hasPermission>
		                        	<@shiro.lacksPermission name="过期订单处理-订单详情">
		                        		${orderDeposit.orderId!''}
		                        	</@shiro.lacksPermission>
		                        </td>
		                        <td>${orderDeposit.title!''}</td>
		                        <td class="buyer_id">${orderDeposit.buyerName!''}</td>
		                        <td class="seller_id">${orderDeposit.sellerName!''}</td>
		                        <td>
		                        	<#if orderDeposit.depositState==3>
		                        		<font color="red">${stateMap[orderDeposit.depositState]!''}</font>
		                        	<#elseif orderDeposit.depositState==4>
		                        		<@shiro.hasPermission name="过期订单处理-过期拒绝理由">
		                        			<a class="col_blue" name="denial" href="javascript:;" data-id="${orderDeposit.orderId!''}" data-reclick="n">${stateMap[orderDeposit.depositState]!''}</a>
			                        	</@shiro.hasPermission>
			                        	<@shiro.lacksPermission name="过期订单处理-过期拒绝理由">
			                        		${stateMap[orderDeposit.depositState]!''}
			                        	</@shiro.lacksPermission>
		                        	<#else>
		                        		${stateMap[orderDeposit.depositState]!''}
		                        	</#if>
		                        </td>
		                        <td>${orderDeposit.orderUpd?string("yyyy-MM-dd HH:mm:ss")!''}</td>
		                        <td  class="payedtime">
			                        <#if orderDeposit.payedTime??>
			                       	 	${orderDeposit.payedTime?string("yyyy-MM-dd HH:mm:ss")}
			                        <#else>
			                        — —
			                        </#if>
		                        </td>
		                        <td class="oper">${orderDeposit.operator!''}</td>
		                        <td>${orderDeposit.buyerSalesmanName!''}</td>
		                        <td>${orderDeposit.sellerSalesmanName!''}</td>
		                        <td>
		                        	<input type="hidden" class="buyer_moeny" value="${orderDeposit.buyerAmount!''}"/>
		                        	<input type="hidden" class="seller_moeny" value="${orderDeposit.sellerAmount!''}"/>
		                        	<input type="hidden" class="platform_moeny" value="${orderDeposit.platformAmount!''}"/>
		                        	<input type="hidden" class="all_moeny" value="${orderDeposit.depositAmount!''}"/>
	                        		<a class="col_blue mr10" name="reDetail" href="javascript:;">赔付详情</a>
			                        <#if orderDeposit.depositState == '1' || orderDeposit.depositState == '4'>
				                        <@shiro.hasPermission name="过期订单处理-赔付"><a href="javascript:;" class="col_blue" name="account" data-orderid="${orderDeposit.orderId!''}" data-buyer="<@tools.money num=orderDeposit.buyerAmount format='0.##'/>" data-seller="<@tools.money num=orderDeposit.sellerAmount format='0.##'/>" data-total="<@tools.money num=orderDeposit.depositAmount format='0.##'/>" data-platform="<@tools.money num=orderDeposit.platformAmount format='0.##'/>">赔付</a></@shiro.hasPermission>
			                        </#if>
		                        </td>
		                    </tr>
                    	</#list>
                    <#else>
                    	<tr>
                    		<td colspan="11">暂无数据!</td>
                    	</tr>
                    </#if>
                </table>
            </div>

            <@tools.pages page=page form="queryForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>

<!--赔付 弹层-->
<div class="order-popup profit-box" datatype="losePay">
    <div class="close"></div>
        <h2 class="title">赔付比例设置：</h2>
    <div id="Set">
        <form id="accountForm" action="/depositOverdue/executeDeposit">
        	<input type="hidden" name="orderId"/>
        	<input type="hidden" id="flag" value="0"/>
            <ul>
                <li><label>买家：</label><input type="text" class="text" name="buyerAmount" datatype="numpoint2" nullmsg="请输入买家金额" errormsg="买家格式不正确" /> 元<span class="Validform_checktip"></span><!--<span id="buyer"></span>元<input type="hidden" name="buyerAmount" />--></li>
                <li><label>卖家：</label><input type="text" class="text" name="sellerAmount" datatype="numpoint2" nullmsg="请输入卖家金额" errormsg="卖家格式不正确" /> 元<span class="Validform_checktip"></span></li>
                <li><label>平台：</label><input type="text" class="text" name="platformAmount" datatype="numpoint2" nullmsg="请输入平台金额" errormsg="平台格式不正确" /> 元<span class="Validform_checktip"></span></li>
                <li><label>总额：</label><span id="totalAmount"></span>元&nbsp;&nbsp;<input type="hidden" name="depositAmount" datatype="total" errormsg="比例之和与总额不一致"/><span class="Validform_checktip"></span></li>
                <li class="mt10"><label></label> <input type="button" value="确定" id="Certain1" class="btn-blue"> <input type="button" value="取消" class="btn-hui ml10" id="Cancel"></li>
            </ul>
        </form>

    </div>

    <div id="See" style="display: none;">
        <div style="padding-top: 40px;" align="center" id="goingon"><img src="${RESOURCE_IMG}/images/loading.gif" width="50" /></div>
        <ul id="confirmShow">
            <li><label>买家：</label> <strong class="col_red2" id="buyer"></strong> 元</li>
            <li> <label>卖家：</label> <strong class="col_red2" id="seller"></strong> 元</li>
            <li><label>平台：</label> <strong class="col_red2" id="platform"></strong> 元</li>
            <li> 确定按这个比例赔付吗？</li>
            <li class="mt10"><input type="button" value="确定" class="btn-blue" id="Certain2"> <input type="button" value="取消" class="btn-hui ml10"></li>
        </ul>
    </div>
</div>
<!--赔付 弹层 end-->

<!--订单详情 弹层-->
<div class="order-popup tempHei" datatype="order">
</div>
<!--订单详情 弹层 end-->
<!-- 赔付详情 -->
<div class="order-popup re-detail" datatype="reDetail">
    <div class="close"></div>
    <dl>
        <dt>默认赔付如下：</dt>
    </dl>
    <table cellspacing="1" cellpadding="1" width="570" bgcolor="#dddddd" class="table-2" align="center">
        <tr bgcolor="#ffffff">
            <th height="22" width="450">分润方</th>
            <th width="120">应得金额</th>
        </tr>
        <tr bgcolor="#ffffff">
            <td height="22"><label id="buyer_id"></label>（买家）</td>
            <td><label id="buyer_moeny"></label>元</td>
        </tr>
        <tr bgcolor="#ffffff">
            <td height="22"><label id="seller_id"></label>（卖家）</td>
            <td><label id="seller_moeny"></label>元</td>
        </tr>
        <tr bgcolor="#ffffff">
            <td height="22">平台</td>
            <td><label id="platform_moeny"></label>元</td>
        </tr>
    </table>
    <dl>
        <dd>保证金金额：<label id="all_moeny"></label>元</dd>
        <dd>操作人：<label id="oper"></label></dd>
        <dd>处理时间：<label id="payedtime"></label></dd>
    </dl>
</div>
<!-- 赔付详情 over -->

<!-- 已拒绝弹层 -->
<div class="order-popup denial" datatype="denial">
    <div class="close"></div>
    <table cellpadding="1" cellspacing="1" width="668" align="center" bgcolor="#dddddd">
        <tr bgcolor="#ffffff" id="reject">
            <th width="100" bgcolor="#eeeeee">次数</th>
            <th width="428">拒绝原因</th>
            <th width="100">财务操作人</th>
            <th width="100">拒绝时间</th>
        </tr>
    </table>
</div>
<!-- 已拒绝弹层 over -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
    $(function(){
        //日期控件
        $('#date_b').click(function(){
            WdatePicker({
                maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
                readOnly:true
            });
        });
        $('#date_e').click(function(){
            WdatePicker({
                minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
                readOnly:true
            });
        });
        
        //背景变灰
        function bghui(){
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
        }
        
        //划账弹层
        $('td a[name=account]').on('click',function(){
        	var _this = $(this),
    		_orderId = $.trim($(this).data('orderid')),
    		_buyerAmount = $.trim($(this).data('buyer')),
    		_sellerAmount = $.trim($(this).data('seller')),
    		_platformAmount = $.trim($(this).data('platform')),
    		_depositAmount = $.trim($(this).data('total'));
        	$('#accountForm input[name=orderId]').val(_orderId);
        	$('#accountForm input[name=depositAmount]').val(_depositAmount);
        	$('#accountForm input[name=buyerAmount]').val(_buyerAmount);
        	//临时加入
        	$('#accountForm #buyer').text(_buyerAmount);
        	$('#accountForm input[name=sellerAmount]').val(_sellerAmount);
        	$('#accountForm input[name=platformAmount]').val(_platformAmount);
        	$('#accountForm input[id=flag]').val('0');
        	$('#accountForm #totalAmount').text(_depositAmount);
            bghui();
            $('.order-popup[datatype=losePay]').show();
            $('#Set').show();
            $('#See').hide();
        });
        
        var validForm = $("#accountForm").Validform({
            btnSubmit:"#Certain1",
	    	btnReset:"#Cancel",
	        tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.next();
					//objtip.css('marginLeft','115px');
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			},
			dragonfly:false,
			ignoreHidden:false,
	        showAllError:true,
	        ajaxPost:true,
	        datatype:{
				"numpoint2": /^(\d+)(\.\d{1,2})?$/,
				"total":function(gets,obj,curform,regxp){
					//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
					var buyerAmount = Number($('input[name=buyerAmount]', $(curform)).val());
		        	var sellerAmount = Number($('input[name=sellerAmount]', $(curform)).val());
		        	var platformAmount = Number($('input[name=platformAmount]', $(curform)).val());
		        	var half=accAdd(buyerAmount,sellerAmount);
		        	var total = accAdd(half,platformAmount);
					if(Number(gets) == total){
						return true;
					} else {
						return false;
					}
				}
			},
			beforeSubmit:function(curform){
				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
				//这里明确return false的话表单将不会提交;
				if('0' == $('input[id=flag]', $(curform)).val()){
					$('#Set').hide();
	            	$('#See').show();
	            	$('#See #goingon').hide();
	            	$('#See #confirmShow').show();
	            	$('#See #buyer').text($('input[name=buyerAmount]', $(curform)).val());
	            	$('#See #seller').text($('input[name=sellerAmount]', $(curform)).val());
	            	$('#See #platform').text($('input[name=platformAmount]', $(curform)).val());
	            	$('input[id=flag]', $(curform)).val('1')
					return false;
				} else {
					return true;
				}
			},
			callback:function(data){
				$('.order-popup').hide();
                $('.bghui').remove();
				if('y' == data.status){
					customAlert(data.info, function(){
						$("#queryForm").submit();
					});
				} else {
					validForm.resetForm();
					$('.Validform_checktip').empty();
					customAlert(data.info);
				}
			}
        });
        
        $('#Certain2').on('click', function () {
            $('#Set').hide();
        	$('#See').show();
        	$('#See #goingon').show();
        	$('#See #confirmShow').hide();
        	validForm.ajaxPost(true);
        });
        
        //订单详情弹层
        $('td a[name=nub]').on('click',function(){
        	getOrderInfoById(this);
        });
        
        $('.order-popup[datatype=order]').on('click','.close',function(){
            $('.order-popup[datatype=order]').hide();
            $('.order-popup[datatype=order]').empty();
            $('.bghui').remove();
        });
        
        //赔付详情弹层
	    $('td a[name=reDetail]').on('click',function(){
        	var checkOrderRows = $(this).parent('td').parent('tr').find('td');
			var checkOrderHiddens =  $(this).parent('td').find('input:hidden');
	       $(checkOrderRows).each(function(checkOrderRowIndex,checkOrderRow){
				var checkOrderRowFlag = $(checkOrderRow).attr('class');
				var checkOrderRowText = $(checkOrderRow).text();
				$('#'+checkOrderRowFlag).text(checkOrderRowText);
			});
	        $(checkOrderHiddens).each(function(checkOrderHiddenIndex,checkOrderHidden){
				var checkOrderHiddenFlag = $(checkOrderHidden).attr('class');
				var checkOrderHiddenValue = $(checkOrderHidden).val();
				$('#'+checkOrderHiddenFlag).text(checkOrderHiddenValue);
			});
			bghui();
	        $('.order-popup[datatype=reDetail]').show();
	    });
	    
	     //获取过期赔付拒绝理由列表
        function getReject(obj){
        	//防止重复弹层
        	var _this = $(obj),
    		orderId = _this.data("id"),
    		reclick = _this.data("reclick");
	    	if(reclick == "n") {
	    		_this.data("reclick", "y");
	    	} else {
	    		return false;
	    	}
            //后台数据请求
    		$.ajax({
	    		type:"POST",
	    		url:"/depositOverdue/orderOverdueDepositRejectList",
	    		data:{orderId:orderId,remitType:'2'},
	    		dataType:"json",
	    		success:function(data){
	    			if(data!=""){
	    				var html='';
	    				$(data).each(function(i,remitflow){
	    					html+='<tr  bgcolor="#ffffff"><td align="center" bgcolor="#eeeeee"><strong>'+(i+1)+'</strong></td>';
	            			html+='<td>'+remitflow.memo+'</td>';
				            html+='<td align="center">'+remitflow.opraterName+'</td>';
				            html+='<td align="center">'+remitflow.opraterTime+'</td></tr>';
	    				})
	    				$("#reject").nextAll().remove();
	    				$("#reject").after(html);
	    				bghui();
	    				$('.order-popup[datatype=denial]').show();
	    			}
	    			_this.data("reclick", "n");
	    		},
	    		error:function(textStatus){
	    			customAlert("操作失败！");
	    			_this.data("reclick", "n");
	    		}
	    	});
        }
	    
	    //已拒绝弹层
        $('td a[name=denial]').on('click',function(){
        	getReject(this);
        });

        //关闭弹层
        function hid(els) {
	        $(els).on('click', function () {
		        validForm.resetForm();
				$('.Validform_checktip').empty();
                $('.order-popup').hide();
                $('.bghui').remove();
        	});
        }
        hid('.btn-hui');
        hid('.close');
        hid('#Certain2');
    })
    
    //获取订单详情
    function getOrderInfoById(obj){
    	var _this = $(obj),
    		orderId = _this.data("id"),
    		reclick = _this.data("reclick");
    	if(reclick == "n") {
    		_this.data("reclick", "y");
    	} else {
    		return false;
    	}
    
    	var imgServer = "${RESOURCE_IMG_UPLOAD}";
    	$.ajax({
    		type:"POST",
    		url:"/depositOverdue/orderinfo",
    		data:{orderId:orderId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				var orderInfo = parseJson(data.result);
    				var html = '';
    				html += '<div class="close"></div>';
    				html += '<h2 class="title">订单详情</h2>';
    				html += '<p class="message"><strong>订单信息：</strong>&nbsp;订单编号：'+ orderInfo.orderId +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;摘牌日期：'+ orderInfo.orderDate +'</p>';
    				html += '<table class="table" cellpadding="0" cellspacing="0">';
    				html += '<tr>';
    				html += '<th width="40%">药材</th>';
    				html += '<th width="15%">单价</th>';
    				html += '<th width="15%">订单数量</th>';
    				html += '<th width="15%">成交数量</th>';
    				html += '<th width="15%">商品总价</th>';
    				html += '</tr>';
    				html += '<tr>';
    				html += '<td class="intro">';
    				html += '<span><img src="'+ imgServer + '/' + orderInfo.goodsPic +'" /></span>';
    				html += '<h3>'+ orderInfo.goodsTitle +'</h3>';
    				html += '</td>';
    				html += '<td align="center">'+ orderInfo.unitPrice +'元/'+ orderInfo.wlunit +'</td>';
    				html += '<td align="center">'+ orderInfo.amount + orderInfo.wlunit +'</td>';
    				html += '<td align="center">'+ orderInfo.volume + orderInfo.wlunit +'</td>';
    				html += '<td align="center">'+orderInfo.totalPrice+'元</td>';
    				html += '</tr>';
    				html += '</table>';
    				html += '<div class="pay-cash" align="right">实际付款：<strong> '+ orderInfo.actualPayment +'</strong> 元</div>';
    				$('.order-popup[datatype=order]').append(html);
    				bghui();
    				$('.order-popup[datatype=order]').show();
    			} else {
    				customAlert(data.result);
    			}
    			_this.data("reclick", "n");
    		},
    		error:function(textStatus){
    			customAlert("操作失败！");
    			_this.data("reclick", "n");
    		}
    	});
    }
    
    //清除
    function resetForm(id){
    	$("#" + id + " :text").val("");
    	$("#" + id + " select").val("");
    }
    
    //自定义的alert框
    function customAlert(str, fnOk){
    	bghui();
    	Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-blue',
                ev:{click:function(){
                	 disappear();
                     $(".bghui").remove();
                     if(fnOk){
                     	fnOk();
                     }
                 }
               }
            }]
	    });
    }
    
	function parseJson(text){
		try{
		    return JSON.parse(text);//ie 89 ff ch
		}catch(e){
		    return eval('('+text+')'); //ie7
		}
	}
	
	
		//added by biran 20150618 用于小数的加法计算
	/**
	   ** 加法函数，用来得到精确的加法结果
	   ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
	   ** 调用：accAdd(arg1,arg2)
	  ** 返回值：arg1加上arg2的精确结果
	   **/
	  function accAdd(arg1, arg2) {
	      var r1, r2, m, c;
	      try {
	         r1 = arg1.toString().split(".")[1].length;
	     }
	     catch (e) {
	         r1 = 0;
	     }
	     try {
	         r2 = arg2.toString().split(".")[1].length;
	     }
	     catch (e) {
	         r2 = 0;
	     }
	     c = Math.abs(r1 - r2);
	     m = Math.pow(10, Math.max(r1, r2));
	     if (c > 0) {
	         var cm = Math.pow(10, c);
	         if (r1 > r2) {
	             arg1 = Number(arg1.toString().replace(".", ""));
	             arg2 = Number(arg2.toString().replace(".", "")) * cm;
	         } else {
	             arg1 = Number(arg1.toString().replace(".", "")) * cm;
	             arg2 = Number(arg2.toString().replace(".", ""));
	         }
	     } else {
	         arg1 = Number(arg1.toString().replace(".", ""));
	         arg2 = Number(arg2.toString().replace(".", ""));
	     }
	     return (arg1 + arg2) / m;
	 }
	 
</script>
</body>
</html>