<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>仓单详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" /> 
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
</head>
<body>
<!-- head start -->
<#include "home/top.ftl" />
<!-- head over -->
<div class="wapper">
<!-- nav start -->
<#include "home/left.ftl" />
<!-- nav over -->

<!-- pageCenter start -->
    <div class="main">
    	<input id="purchaseId" type="hidden" value="${purchase.purchaseId!''}"/>
    	<input id="purchaseCode" type="hidden" value="${purchase.purchaseCode!''}"/>
    	<div class="page-main">
    		<h1 class="title1">采购审核详情</h1>
            <div class="store-audit detail">   
            	<dl class="clearfix">
                <dt>采购单位</dt>
                <dd><span>采购单位：</span>${purchase.purchaserOrg!''}</dd>
                <dd class="fl wid"><span>联系人：</span>${purchase.contact!''}</dd>
                <dd class="fl wid"><span>电话：</span>${purchase.contactPhone!''}</dd>
                </dl>
                <dl class="clearfix ">
                <dt>采购品种</dt>
                <dd class="fl wid"><span>品种：</span>${purchase.breedName!''}</dd>
                <dd class="fl wid"><span>采购数量：</span><#if purchase.quantity??>${purchase.quantity}<#else>0</#if>${purchase.wunitName!''}</dd>
                <dd class="fl wid"><span>规格等级：</span>${purchase.standardLevel!''}</dd>
                <dd class="fl wid"><span>产地要求：</span>${purchase.origin!''}</dd>
                <dd class="clearfix"><span class="fl">质量要求：</span><span class="fl wid500">${purchase.qualityDescription!''}</span></dd>
                </dl>
                <dl class="clearfix">
                <dt>交收要求</dt>
                <dd><span>交货地点：</span>${purchase.deliveryAddress!''}</dd>
                <dd class="fl wid"><span>交货时间：</span><#if purchase.expectDeliveryTime??>${purchase.expectDeliveryTime?string("yyyy-MM-dd")!''}<#else>未定</#if></dd>
                <dd class="fl wid"><span>发票要求：</span>${purchase.receipt!''}</dd>
                <dd class="clearfix"><span class="fl">其他：</span><span class="fl wid500">${purchase.note!''}</span></dd>
                </dl>
                <dl>
                <dt>采购信息有效期</dt>
                <dd><span>有效期：</span><#if purchase.validPeriod??>${purchase.validPeriod}天</#if></dd>
            	</dl>
            	<#if purchase.status?string == '-10'>
	                <dl>
	                    <dt>审核不通过原因</dt>
	                    <dd>${purchase.rejectReason!''}</dd>
               		</dl> 
            	</#if>
              	<div class="mt20">
                	<#if purchase.status?string == '0'>
	                	<a class="store_valid"><input type="button" value="有效" class="btn-blue mr10"></a>
	                    <a class="store_void"><input type="button" value="无效" class="btn-hui mr10"></a>
                    </#if>
                    <a><input type="button" value="返回" class="btn-hui" onclick="goback();"></a>
                </div>  
            </div>
    </div>
<!-- pageCenter over -->
</div>

<!-- 有效确认框  -->
<div class="pop-void" id="valid" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">确认通过审核</h1>
            <ul class="fo_void">
                <li> 
                   确认此采购单能够在前台页面显示吗？
                </li>
                <li class="fr">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="确定" id="querenBtn1" data-reclick="n"/> </p>
                    <p><input type="button" class="btn-hui" value="取消" id="quxiaoBtn1"/> </p>
                </li>
            </ul>
    </div>
</div>
<!-- 有效理由 over  -->

<!-- 无效理由  -->
<div class="pop-void" id="void" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">无效理由</h1>
        <form>
            <ul class="fo_void">
                <li> 
                   <textarea id="rejectReason" name="remark" class="text textarea-st"></textarea>
                </li>
                <li class="fr">
                    <label id="errorMsg" style="color:red">aa</label>
                    <p><input type="button" class="btn-blue" value="确定" id="querenBtn" data-reclick="n"/> </p>
                    <p><input type="button" class="btn-hui" value="取消" id="quxiaoBtn"/> </p>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 无效理由 over  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
$(function(){
    //弹层
    var Close = $('.close');
    Close.each(function(){
        $(this).click(function(){
            $(this).parent().hide();
            $('.bghui').remove();
        })
    });
    $('.store_void').on('click',function(){
    	$('#rejectReason').val("");
    	$('#errorMsg').text("");
        $('#void').show();
        if($('.bghui').size() < 1){
	         bghui();
        }
    });
    
    $('.store_valid').on('click',function(){
        $('#valid').show();
        if($('.bghui').size() < 1){
	         bghui();
        }
    })
    
    $('#querenBtn').on('click',function(){
    	auditUnPassPurchase(this);
    });
    
    $('#quxiaoBtn').on('click',function(){
    	$('#void').hide();
        $('.bghui').remove();
    });
    
    $('#querenBtn1').on('click',function(){
        auditPassPurchase(this);
    });
    $('#quxiaoBtn1').on('click',function(){
    	$('#valid').hide();
        $('.bghui').remove();
    });
    
    $("#rejectReason").blur(function(){
    	$(this).val($(this).val().replace(/(^\s*)|(\s*$)/g, ""));
    });
});

function goback(){
	window.location.href = '/purchaseAudit';
}

//审核有效
function auditPassPurchase(obj){
	var _this = $(obj),
		reclick = _this.data("reclick");
	if(reclick == "n") {
		_this.data("reclick", "y");
	} else {
		return false;
	}

	var purchaseId = $("#purchaseId").val();
	var purchaseCode = $("#purchaseCode").val();
	$.ajax({
		type:"POST",
		url:"/purchaseAudit/passed",
		data:{purchaseId:purchaseId,purchaseCode:purchaseCode},
		dataType:"json",
		success:function(data){
			$('#valid').hide();
        	$('.bghui').remove();
			if(data.state=="success"){
				customAlert(data.info, function(){
					goback();
				});
			} else {
				customAlert(data.info);
			}
			_this.data("reclick", "n");
		},
		error:function(textStatus){
			$('#valid').hide();
        	$('.bghui').remove();
			customAlert("审核通过操作失败！");
			_this.data("reclick", "n");
		}
	});
}

//审核无效
function auditUnPassPurchase(obj){
	var _this = $(obj),
		reclick = _this.data("reclick");
	if(reclick == "n") {
		_this.data("reclick", "y");
	} else {
		return false;
	}

	var purchaseId = $("#purchaseId").val();
	var purchaseCode = $("#purchaseCode").val();
	var rejectReason = $('#rejectReason').val();
	if('' == rejectReason){
		$('#errorMsg').text("请填写无效理由！");
		_this.data("reclick", "n");
	} else if(rejectReason.length > 200){
		$('#errorMsg').text("字符长度超过200！");
		_this.data("reclick", "n");
	} else {
		$.ajax({
			type:"POST",
			url:"/purchaseAudit/unpassed",
			data:{purchaseId:purchaseId,purchaseCode:purchaseCode,rejectReason:rejectReason},
			dataType:"json",
			success:function(data){
				$('#void').hide();
        		$('.bghui').remove();
				if(data.state=="success"){
					customAlert(data.info, function(){
						goback();
					});
				} else {
					customAlert(data.info);
				}
				_this.data("reclick", "n");
			},
			error:function(textStatus){
				$('#void').hide();
        		$('.bghui').remove();
				customAlert("审核不通过操作失败！");
				_this.data("reclick", "n");
			}
		});
	}
}

//自定义的alert框
function customAlert(str,fn){
	bghui();
	Alert({
        str: str,
        buttons:[{
            name:'确定',
            classname:'btn-blue',
            ev:{click:function(){
            	 disappear();
                 $(".bghui").remove();
                 if(fn){
                 	fn();
                 }
             }
           }
        }]
    });
}
</script>
</body>
</html>