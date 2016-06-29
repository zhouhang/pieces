<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>资金流水列表</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body onload="javascript:init();">
<#include "home/top.ftl" />
<div class="wapper">
	<#include "home/left.ftl" />
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">线下支付流水记录新增</h1>
            <form action="/payVoucherManage/addPayVoucher" method="POST" id="payVoucherForm" enctype="multipart/form-data">
                <ul class="store-search layout1 mt20">
                	 <li><span>系统来源：</span>
                    	<select name="sourceSys" class="text-store text-7">
                    		<option value="0">交易系统</option>
                    	</select>
                      </li>
                      <li><span>&nbsp;</span></li>
                      <li><span>款项：</span>
                    	<select name="amtType" onchange="javascript:init();" class="text-store text-7">
                    		<option value="">-请选择-</option>
                    		<#if AmtTypes??>
	                   			<#list AmtTypes?keys as key>
	                    			<option value="${key!'' }" >${AmtTypes[key]!'' }</option>
	                    		</#list>
                   			</#if>
                    	</select>
                      </li>
                      <li><span>订单号：</span><input name="orderId" value="" class="text-store text-7" type="text" onblur="javascript:getOrderInfo();"/></li>
                      <li><span>收款人账号：</span><input name="payeeAccount" value="" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>收款人名称：</span><input name="payeeName" value="" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>付款人账号：</span><input name="payerAccount" value="" readonly="readonly"  class="text-store text-7" type="text" /></li>
                      <li><span>付款人名称：</span><input name="payerName" value="" readonly="readonly"  class="text-store text-7" type="text" /></li>
                      <li><span>金额：</span><input name="amount" value="" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>支付流水号：</span><input name="payFlowId" value="" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>支付渠道：</span><input name="payChannel" value="线下付款" disabled="disabled" class="text-store text-7" type="text" /></li>
                      <li><span>付款时间：</span><input id="wdate1" name="payDate" value="" type="text" class="text-store text-7 mr10 Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})" /></li>
                      <li><span>付款回单：</span><input name="file" id="file" value="" type="file" /></li>
<!--                       <input type="hidden" id="payVoucher" name="payVoucher" value=""/> -->
                      <li>
                      		<span>&nbsp;</span>
<!--                       	<span>付款状态：</span><input type="radio" name="payStatus" value="1" /> 支付成功&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="payStatus" value="2"/> 支付失败 -->
                      </li>
                      <li class="pt20 clear"><input id="addBtn" value="确认" class="btn-blue" type="button" />
											 <input id="addCancel" value="取消" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" /></li>                  
                </ul>
            </form>
            
           
    	</div>
    </div>
<!-- pageCenter over -->
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

$("#exportExcel").click(function(){
	$("#exportPayFlowForm").attr("action","/payManager/exportPayFlow").submit();
}); 

function init(){
	$("input[name=orderId]").focus();
}
//获取交易订单信息
$("input[name=orderId]").blur(function(){
	var _amtType = $("select[name=amtType]").val();
	var _orderId = $("input[name=orderId]").val();
	if(_orderId == '' || _orderId == null){
		/* bghui();
		Alert({str:"交易订单号不能为空!"}); */
		$("input[name=orderId]").focus();
		return;
	}
	if(_amtType == '' || _amtType == null){
		bghui();
		Alert({
		   str:"请选择款项类型!"
		});
		return;
	}
	
	$.post("/payVoucherManage/getOrderInfo",{amtType:_amtType,orderId:_orderId},function(data){
		if(data.ok){
			$("input[name=payeeAccount]").val(data.obj.payeeAccount);
			$("input[name=payeeName]").val(data.obj.payeeName);
			$("input[name=payerAccount]").val(data.obj.payerAccount);
			$("input[name=payerName]").val(data.obj.payerName);
			$("input[name=amount]").val(data.obj.amount);
			$("input[name=payFlowId]").val(data.obj.payFlowId);
		}else{
			bghui();
			Alert({str:data.errorMessages[0].message,
					buttons:[{
		                name:'确定',
		                id:'1',
		                classname:'btn-style',
		                ev:{click:function(data){
		                        disappear();
		                        $(".bghui").remove();
		                        $("input[name=orderId]").focus();
		                    }
		                }
		            }]	
			});
			
		}
	});
});
	
	//添加线下支付流水
	$("#addBtn").click(function(){
		var _orderId = $("input[name=orderId]").val();
		var _payDate = $("input[name=payDate]").val();
		var _payStatus = $("input[name=payStatus]:checked").val();
		var _payVoucher = $("input[name=payVoucher]").val();
		var _amtType = $("select[name=amtType]").val();
		var _pic = $("#file").val();
		
		if(_orderId == '' || _orderId == null){
			bghui();
			Alert({str:"交易订单号不能为空!"});
			return; 
		}
		if(_amtType == '' || _amtType == null){
			bghui();
			Alert({str:"请选择款项类型!"});
			return;
		}
		
		//校验图片
		if(_pic !=''){
			if(!/.(gif|jpg|jpeg|bmp|png)$/.test(_pic)){
				bghui();
				Alert({str:'付款回单请选择图片!'});
				return;
			}
		}
		if(_payDate == '' || _payDate == null){
			bghui();
			Alert({str:"付款时间不能为空!"});
			return;
		}
		
		$.post("/payVoucherManage/verifyOrder",{orderId:_orderId,amtType:_amtType},function(data){
			if(data.ok){
				$("#payVoucherForm").submit();
			}else{
				bghui();
				Alert({str:data.errorMessages[0].message});
			}
		});
		
	});
	

</script>
</body>
</html>