<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>采购信息管理详情</title>
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
        <div class="page-main">
			<input id="purchaseId" type="hidden" value="${purchase.purchaseId!''}"/>
            <h1 class="title1">采购信息管理详情</h1>
            <div class="store-box mt20">
                <span class="fr col_888 mt10 dis-in-bk">采购批次号：${purchase.purchaseCode!''}</span>
                <h1 class="s-title">${purchase.breedName!''} ${purchase.standardLevel!''} ${purchase.origin!''} <#if purchase.quantity??>${purchase.quantity}<#else>0</#if>${purchase.wunitName!''}</h1>
                <p class="s-sty1 clearfix"><span class="wid1 dis-in-bk"><span class="col_888">品种名称：</span>${purchase.breedName!''}</span>
                    <span class="wid1 dis-in-bk"><span class="col_888">规格等级：</span>${purchase.standardLevel!''}</span>
                    <span class="wid1 dis-in-bk"><span class="col_888">产地：</span>${purchase.origin!''}</span>
                    <span class="wid1 dis-in-bk"><span class="col_888">数量：</span><#if purchase.quantity??>${purchase.quantity}<#else>0</#if>${purchase.wunitName!''}</span><br/>
                    <span><span class="col_888 fl">质量要求：</span><span class="fl wid500">${purchase.qualityDescription!''}</span></span>
                </p>
                <p class="s-sty1 clearfix">
                    <span class="wid2 dis-in-bk"><span class="col_888">交货地点：</span>${purchase.deliveryAddress!''}</span>&nbsp;
                    <span class="wid1 dis-in-bk"><span class="col_888"> 交货时间：</span><#if purchase.expectDeliveryTime??>${purchase.expectDeliveryTime?string("yyyy-MM-dd")!''}<#else>未定</#if></span>
                    <span class="wid1 dis-in-bk"><span class="col_888">发票要求：</span>${purchase.receipt!''}</span><br/>
                    <span><span class="col_888 fl">其&nbsp;&nbsp;他：</span><span class="fl wid500">${purchase.note!''}</span></span>
                </p>
            </div>

            <div class="use-item1 mt20" id="quoteRecord">
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr row-head-title="quoteRecordTitle" id="tr_id">
                        <th width="10%" class="bgf5">报价</th>
                        <th width="40%" class="bgf5">备注</th>
                        <th width="10%" class="bgf5">报价帐号</th>
                        <th width="10%" class="bgf5">联系电话</th>
                        <th width="10%" class="bgf5">报价时间</th>
                        <th width="10%" class="bgf5">交易状态</th>
                        <th width="10%" class="bgf5">操作</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
<!-- pageCenter over -->
</div>

<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
<script>
$(function(){
	getQuoteRecord();
	
	$('#quoteRecord').on('click','a[name=dealsuccess]',function(){
		setDealFinished(this);
	});
});

//设置交易已完成
function setDealFinished(obj){
	var _this = $(obj),
		quoteId = _this.data("quoteid");
		reclick = _this.data("reclick");
	if(reclick == "n") {
		_this.data("reclick", "y");
	} else {
		return false;
	}

	var purchaseId = $("#purchaseId").val();
	$.ajax({
		type:"POST",
		url:"/purchaseManage/dealsuccess",
		data:{purchaseId:purchaseId,quoteId:quoteId},
		dataType:"json",
		success:function(data){
			if(data.state=="success"){
				customAlert(data.info, function(){
					getQuoteRecord();
				});
			} else {
				customAlert(data.info);
			}
			_this.data("reclick", "n");
		},
		error:function(textStatus){
			customAlert("标记已交易操作失败！");
			_this.data("reclick", "n");
		}
	});
}

//报价记录
function getQuoteRecord(){
	var firstTable = $('#quoteRecord').pagination({
		targetId: 'quoteRecord',
		type: 'post',
		url: '/purchaseManage/quoteList?purchaseId='+$("#purchaseId").val(),
		init:  true,
		pageTheme:'default',
		rowHtml: function(rowData,pagingData) {
			var html = '<tr>';
            html += '<td>' + formatNum(rowData.quotePrice) + '元</td>';
            html += '<td><div style="padding: 8px; text-align: left;">' + strNvl(rowData.quoteDescription,'') + '</div></td>';
            html += '<td><a href="/getMemberManage/getMemberByUserName?memberName=' + rowData.quoter + '">' + rowData.quoter + '</a></td>'
            html += '<td>' + rowData.phone + '</td>';
            html += '<td>' + new Date(rowData.createTime).pattern("yyyy-MM-dd hh:mm:ss") + '</td>';
            if(rowData.status != 10){
            	html += '<td><span class="col_999">未达成交易</span></td>';
            } else {
            	html += '<td><span class="col_green">已完成交易</span></td>';
            }
            if(pagingData.extraData.dealOk){
                html += '<td></td>';
            } else {
            	html += '<td><a name="dealsuccess" href="javascript:;" class="blue" data-quoteid="' + rowData.quoteId + '" data-reclick="n">标记为已交易</a></td>';
            }
            html += "</tr>";
            return html;
		},
		emptyRow : function(page){
			var html = '<tr><td colspan="7">暂无记录!</td></tr>';
			$("#tr_id").after(html);
			$("#quoteRecord_pagination").remove();
		}
	});
}

 //保留两位小数  不四舍五入	   
   function formatNum(num){
	  	var bb = num+"";  
	    var dian = bb.indexOf('.');  
	    var result = "";  
	    if(dian == -1){  
	        result =  num;  
	    }else{  
	        var cc = bb.substring(dian+1,bb.length);  
	        if(cc.length >=3){  
	            //result =  (Number(num.toFixed(2)))*100000000000/100000000000; 
				result = bb.substring(0,dian)+"."+bb.substring(dian+1,dian+3);
	        }else{  
	            result =  num;  
	        }
	        if(bb.substring(0,dian)=="")
	        	result =0+result;
	    }  
		return result;
	 };
	 
Date.prototype.pattern=function(fmt) {          
    var o = {          
    "M+" : this.getMonth()+1, //月份          
    "d+" : this.getDate(), //日          
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时          
    "H+" : this.getHours(), //小时          
    "m+" : this.getMinutes(), //分          
    "s+" : this.getSeconds(), //秒          
    "q+" : Math.floor((this.getMonth()+3)/3), //季度          
    "S" : this.getMilliseconds() //毫秒          
    };          
    var week = {          
    "0" : "\u65e5",          
    "1" : "\u4e00",          
    "2" : "\u4e8c",          
    "3" : "\u4e09",          
    "4" : "\u56db",          
    "5" : "\u4e94",          
    "6" : "\u516d"         
    };          
    if(/(y+)/.test(fmt)){          
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));          
    }          
    if(/(E+)/.test(fmt)){          
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);          
    }          
    for(var k in o){          
        if(new RegExp("("+ k +")").test(fmt)){          
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));          
        }          
    }          
    return fmt;          
}

function strNvl(arg1, repStr){
	if(arg1){
		return arg1;
	} else {
		return repStr;
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