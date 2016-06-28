<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>管理采购</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/popup.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<!-- 头部  -->
    <#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
    <!-- 会员左侧 -->
    <#include 'common/left.ftl'>
    <!-- 会员左侧 over -->
    <div class="hy-right fr mb10">
        <div class="order-box stock-box">
            <h2 class="o-title">我的采购</h2>
            <ul class="tabs clearfix">
                <li><a href="/purchase/pub">发布采购</a></li>
                <li class="cur"><a href="/purchase/manager">管理采购</a></li>
            </ul>
			<div class="stocks-box detail">
                <p class="caption mb10">您的专属交易员是：${salesmanInfo.name}，电话${salesmanInfo.phone}，您在采购药材时碰到任何问题，可以电话寻求帮助。</p>
            <dl class="clearfix">
            <dt>采购单位</dt><input type="hidden" id="purchaseId" value="${purchase.purchaseId}" />
            <dd><span>采购单位：</span>${purchase.purchaserOrg}</dd>
			<dd class="fl wid"><span>联系人：</span>${purchase.contact}</dd>
            <dd class="fl wid"><span>电话：</span>${purchase.contactPhone}</dd>
            </dl>
            <dl class="clearfix ">
            <dt>采购品种</dt>
            <dd class="fl wid"><span>品种：</span>${purchase.breedName}</dd>
            <dd class="fl wid"><span>采购数量：</span>${purchase.quantity}${qualityUnit!''}</dd>
            <dd class="fl wid"><span>规格等级：</span>${purchase.standardLevel}</dd>
            <dd class="fl wid"><span>产地要求：</span>${purchase.origin}</dd>
            <dd class="clearfix">
            	<span class="fl">质量要求：</span>
            	<span class="fl wid600">
	            <#if purchase.qualityDescription??>
	            ${purchase.qualityDescription?replace("\r\n","<br/>")}
	            </#if>
	            </span>
            </dd>
             
            </dl>
            <dl class="clearfix">
            <dt>交收要求</dt>
            <dd><span>交货地点：</span>${purchase.deliveryAddress}</dd>
            <dd class="fl wid"><span>交货时间：</span><#if purchase.expectDeliveryTime??>
		                        		${purchase.expectDeliveryTime?string("yyyy-MM-dd")!''}
		                </#if></dd>
            <dd class="fl wid"><span>发票要求：</span>${purchase.receipt}</dd>
            <dd class="clearfix">
            	<span class="fl">其他：</span>
            	<span class="fl wid600">${purchase.note}</span>
            </dd>
            </dl>
            <dl>
            <dt>采购信息有效期</dt>
            <dd><span>有效期：</span>${purchase.validPeriod}天</dd>
            </dl>
            <dl class="clearfix">
            <dt>报价信息</dt>
            <dd id="purchaseQuote">
                <table cellpadding="1" cellspacing="1" bgcolor="#e0e0e0" width="906" align="center">
                    <tr bgcolor="#ffffff">
                        <th>报价日期</th>
                        <th>价格</th>
                    </tr>
                    <tr bgcolor="#ffffff" row-head-title="purchaseQuoteTitle" id="tr_id">
                    </tr>
                    
                </table>
                <!--<strong class="fr fy dis-in-bk"><a class="col_333" href="#">首页</a>  <a class="col_999">上一页</a> <a class="col_333" href="#">下一页</a> <a class="col_333" href="">末页</a></strong>-->
            </dd>
            </dl>
            </div>

        </div>
    </div>
</div>

<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
<script>
getPurchaseQuote();
//交易记录
	function getPurchaseQuote(){
		var firstTable = $('#purchaseQuote').pagination({
			targetId: 'purchaseQuote',
			type: 'post',
			url: '/purchase/getPurchaseQuoteBy?purchaseId='+$("#purchaseId").val(),
			init:  true,
			pageTheme:'detail',
			rowHtml: function(rowData,pagingData) {
				var html="<tr bgcolor='#ffffff'>";
                    html+="<td height='24'>"+formatDate(rowData.createTime)+"</td>";
                    if(rowData.status==10){
                   		html+="<td>"+rowData.quotePrice+"元<i class='icon-right dis-in-bk'></i></td>";
                    }
                    else{
                    	html+="<td>"+rowData.quotePrice+"元</td>";
                    }
                    html+="</tr>";
                   return html;
			},
			afterRefresh: function(rowData){
			},
			emptyRow : function(page){
				var html = '<font style="border:none;font-family:微软雅黑;font-size:14px;">暂无报价信息!</font>';
				$("#purchaseQuote").html(html);
				$("#purchaseQuote_pagination").remove();
			}
		});
    };
    
    function   formatDate(now)   {
		   var datetime = new Date();
		   	   datetime.setTime(now);   
		  var year=datetime.getFullYear();   
		  var month=datetime.getMonth()+1;   
		  var date=datetime.getDate();   
		  return   year+"-"+month+"-"+date;   
		  }   

$(function(){
//日期控件
    $('#date_b').click(function(){
        WdatePicker({
            startDate:'%y/%M/%d',
            dateFmt:'yyyy/MM/dd',
            maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
            readOnly:true
        });
    });
    $('#date_e').click(function(){
        WdatePicker({
            startDate:'%y/%M/%d',
            dateFmt:'yyyy/MM/dd',
            minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
            readOnly:true
        });
    });
    $('#date_c').click(function(){
        WdatePicker({
            startDate:'%y/%M/%d',
            dateFmt:'yyyy/MM/dd',
            readOnly:true
        });
    });

    //筛选
    $('.tabs li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        $('#tabsCont').children('table').eq($(this).index()).show().siblings().hide();
    })

    //提前结束
    $('div[datatype=over]').hover(
         function(){
            $(this).children('.over').show();
         },
         function(){
             $(this).children('.over').hide();
         }
    );
    //弹层显示垂直居中位置
    function popUp(par,elm){
        var wid = elm.width();
        var Hei = elm.height();
        par.css({
            'position':'fixed',
            'top':'50%',
            'left':'50%',
            'zIndex':10001,
            'marginLeft':-wid/2,
            'marginTop':-Hei/2,
            'border':'3px solid #cf5445',
            'background':'#ffffff'
        })
    }
    popUp($('.popup-box'),$('.popup-box .box2'));

//背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
//关闭层
    function close(els,cont){
        els.on('click',function(){
            cont.hide();
            $('.bghui').remove();
        })
    }
    close($('.close'),$('.popup-box'));
//显示层
    function show(els,cont){
        els.on('click',function(){
            cont.show();
            bgHiu()
        })
    }
    show($('a[name=edit]'),$('[datatype=edit-pop]'));
    show($('a[name=detail]'),$('[datatype=detail-pop]'));
    show($('a[name=reCer]'),$('[datatype=reCer-pop]'));
    show($('a[name=again]'),$('[datatype=again-pop]'));
})
</script>
</body>
</html>