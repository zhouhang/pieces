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
<body>
<#include "home/top.ftl" />
<div class="wapper">
	<#include "home/left.ftl" />
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">线下支付确认</h1>
            <form action="/payVoucherManage/addPayVoucher" method="POST" id="payVoucherForm" enctype="multipart/form-data">
                <ul class="store-search layout1 mt20">
                	 <li><span>系统来源：</span>
                    	<select name="sourceSys" class="text-store text-7">
                    		<option value="0">交易系统</option>
                    	</select>
                      </li>
                      <li><span>&nbsp;</span></li>
                      <li><span>款项：</span>
                    		<#if amtTypeMap??>
	                   			<#list amtTypeMap?keys as key>
	                    			<#if (payFlowListVo.payType == key)><input type="text" value="${amtTypeMap[key]!'' }"  class="text-store text-7" readonly="readonly" /></#if>
	                    		</#list>
                   			</#if>
                      </li>
                      <li><span>订单号：</span><input name="orderId" value="${payFlowListVo.orderId!''}" readonly="readonly" class="text-store text-7" type="text" onblur="javascript:getOrderInfo();"/></li>
                      <li><span>收款人账号：</span><input name="payeeAccount" value="${payFlowListVo.payeeAccount!''}" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>收款人名称：</span><input name="payeeName" value="${payFlowListVo.payeeName!''}" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>付款人账号：</span><input name="payerAccount" value="${payFlowListVo.payerAccount!''}" readonly="readonly"  class="text-store text-7" type="text" /></li>
                      <li><span>付款人名称：</span><input name="payerName" value="${payFlowListVo.payerName!''}" readonly="readonly"  class="text-store text-7" type="text" /></li>
                      <li><span>金额：</span><input name="amount" value="${payFlowListVo.amount!''}" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>支付流水号：</span><input name="payFlowId" value="${payFlowListVo.payFlowId!''}" readonly="readonly" class="text-store text-7" type="text" /></li>
                      <li><span>支付渠道：</span><input name="payChannel" value="线下付款" disabled="disabled" class="text-store text-7" type="text" /></li>
                      <li><span>付款时间：</span><input id="wdate1" name="payDate" value="" type="text" class="text-store text-7 mr10 Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})" /></li>
                      <li>
	                      <span>付款回单：</span>
	                      <#if (payFlowListVo.payVoucher != '')>
	                      		<span><a href="javascript:void(0);" id="${RESOURCE_IMG_UPLOAD}/${payFlowListVo.payVoucher!''}">查看</a></span>
	                      <#else>	
	                      	<!-- <input name="file" id="file" value="" type="file" /> -->
	                      	无
	                      </#if>
                      </li>
                      <li><span>付款状态：</span><input type="radio" name="payStatus" value="1" checked/> 支付成功&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="payStatus" value="2"/> 支付失败</li>
                      <li class="pt20 clear"><input id="addBtn" value="确认" class="btn-blue" type="button" onClick="javascript:checkPayVoucher();" />
											 <input id="addCancel" value="取消" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" /></li>                  
                </ul>
            </form>
            
           
    	</div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 图片弹层 -->
<div class="popup-box" id="picBox">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
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

//确认付款
function checkPayVoucher(){
	var _flowId = $("input[name=payFlowId]").val();
	var _payDate = $("input[name=payDate]").val();
	var _payStatus = $("input[name=payStatus]:checked").val();
	if(_flowId == '' || _flowId == null){
		bghui();
		Alert({str:"支付流水号不能为空!"});
		return;
	}
	if(_payDate == null || _payDate == ''){
		bghui();
		Alert({str:"付款时间不能为空!"});
		return;
	}
	bghui();
	Alert({str:"确定已经付款了吗?",
		 buttons:[{
			name:'确定',
			id:'1',
			classname:'btn-style',
			ev:{click:function(data){
				disappear();
				$(".bghui").remove();
				$.post("/payVoucherManage/checkPayVoucher",{flowId:_flowId,payDate:_payDate,payStatus:_payStatus},function(data){
					if(data.ok){
						Alert({str:"确认付款操作成功!",
							buttons:[
							 {
				                name:'确定',
				                id:'1',
				                classname:'btn-style',
				                ev:{click:function(data){
				                        disappear();
				                        $(".bghui").remove();
				                        location.href="/payVoucherManage";
				                    }
				                }
				            }]		
						});
					}else{
						bghui();
						Alert({str:data.errorMessages[0].message});
					}
				});
			}
		}
		},
        {
            name:'取消',
            id:'2',
            classname:'btn-hui',
            ev:{click:function(data){
                disappear();
                $(".bghui").remove();
                return;
            }}
       }]
	});
	
}
//图片预览	
$("li span a").click(function(){
	var path = $(this).attr("id");
	  $('#picBox').show();
      var html = '<div class="bghui"></div>';
      var height = $(document).height();
      $('body').append(html);
      $('.bghui').css('height',height);
	  $('#showImg').attr('src',path);
	  $('#picBox').imageView({width:600, height:400});
      return false;
});
$('#Close').click (function(){
    $(this).parents('#picBox').hide();
    $('.bghui').remove();
});


</script>
</body>
</html>