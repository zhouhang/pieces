<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>管理采购</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/popup.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
    <#import 'page.ftl' as fenye>
</head>
<body>

<!-- 头部  -->
    <#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
    <!-- 会员左侧 -->
    <#include 'common/left.ftl'>
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="order-box stock-box">
            <h2 class="o-title">我的采购</h2>
            <ul class="tabs clearfix">
                <li><a href="/purchase/pub">发布采购</a></li>
                <li class="cur"><a href="/purchase/manager">管理采购</a></li>
            </ul>
            <div class="stocks-box">
                <p class="caption">您的专属交易员是：${salesmanInfo.name}，电话${salesmanInfo.phone}，您在采购药材时碰到任何问题，可以电话寻求帮助。</p>
            </div>
            <form action="/purchase/manager" method="post" id="conditionForm">
            <div class="o-search mt10">
                <span class="mr10">采购批次号：<input type="text" class="text-sty2 text-80" name="purchaseCode" value="${page.params.busiPurchaseSearchDto.purchaseCode}" /></span>
                <span class="mr10"> 采购品种：<input type="text" class="text-sty2 text-80" name="breedName" value="${page.params.busiPurchaseSearchDto.breedName}" /></span>
                <span class="mr10">采购状态：<select id="purchaseStatus" name="status" class="text-store text-select">
                        <option value="">全部</option>
                        <#if statusMap??>
		            		<#list statusMap?keys as key>
		            			<option value="${key}" <#if page.params.busiPurchaseSearchDto.status == key>selected</#if>>${statusMap[key]}</option>
		            		</#list>
	            		</#if>
                    </select>
                </span>
                <span class="mr10">发布时间：<input type="text" class="text-sty2 dat text-80" id="date_b" name="createTimeStart" value="${page.params.busiPurchaseSearchDto.createTimeStart}" /> - <input type="text" class="text-sty2 text-80 dat" id="date_e" name="createTimeEnd" value="${page.params.busiPurchaseSearchDto.createTimeEnd}" /></span>
                <span><input type="submit" class="btn-red mr10" value="查询" /><a href="javascript:void(0);" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');" class="col_999">清空</a></span>
            </div>
			</form>
                <!-- 采购中 -->
                <table cellspacing="1" cellpadding="1" width="970" class="mt10" bgcolor="#e1e1e1" style="display: block;">
                    <tr bgcolor="#f5f5f5" align="center">
                        <th width="92" height="35"><strong>采购批次号</strong></th>
                        <th width="75"><strong>采购品种</strong></th>
                        <th width="75"><strong>规格等级</strong></th>
                        <th width="87"><strong>产地</strong></th>
                        <th width="77"><strong>采购数量</strong></th>
                        <th width="97"><strong>采购状态</strong></th>
                        <th width="82"><strong>最低报价</strong></th>
                        <th width="144"><strong>发布时间</strong></th>
                        <th width="144"><strong>到期时间</strong></th>
                        <th width="97"><strong>操作</strong></th>
                    </tr>
                   <#if page.results?? && page.results?size gt 0>
	                <#list page.results as busiPurchase>
                    <tr bgcolor="#ffffff">
                        <td>${busiPurchase.purchaseCode}</td>
                        <td>${busiPurchase.breedName}</td>
                        <td>${busiPurchase.standardLevel}</td>
                        <td>${busiPurchase.origin}</td>
                        <td>${busiPurchase.quantity}${busiPurchase.wunitName}</td>
                        <td>
                        <#if busiPurchase.status==0>
                        	待审核
                        	<#elseif busiPurchase.status==10 >
                        	待报价
                        	<#elseif busiPurchase.status==20 >
                        	<span class="col_red">洽谈中</span>
                        	<#elseif busiPurchase.status==30 >
                        	<span class="col_green">交易成功</span>
                        	<#elseif busiPurchase.status==-10 >
                        	<span class="col_999">审核不通过</span>
                        	<#elseif busiPurchase.status==-20 >
                        	<span class="col_999">已结束</span>
                        </#if>
                        
                         </td>
                        <td>
                        	<#if busiPurchase.minQuotePrice??>
                        		${busiPurchase.minQuotePrice}元
                        	<#else>
                        		暂无
                        	</#if>
                        </td>
                        <td>
                        <#if busiPurchase.createTime??>
		                        		${busiPurchase.createTime?string("yyyy-MM-dd HH:mm:ss")!''}
		                </#if>
                        </td>
                        <td>
                        <#if busiPurchase.overTime??>
		                        		${busiPurchase.overTime?string("yyyy-MM-dd HH:mm:ss")!''}
		                </#if>
                        </td>
                        <td><a href="/purchase/detail?purchaseId=${busiPurchase.purchaseId}" class="blue">查看详情</a>
                        <!--审核不通过，可重新发布-->
                        <#if busiPurchase.status==-10>
                        	<a href="/purchase/pub?purchaseId=${busiPurchase.purchaseId}" class="blue">重新提交</a>
                        </#if>
                        </td>
                    </tr>
                    </#list>
                    <#else>
	                    <tr align="center" bgcolor="#ffffff">
					    	<td  colspan="10" style="font-family:微软雅黑;font-size:14px;">没有记录！</td>
					    </tr>	
                 </#if>
                    
                </table>
                <!-- 采购中 end -->

		
            <@fenye.pages page=page form="conditionForm"/>
            
        </div>
    </div>
</div>
     

<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script>

$(function(){
//日期控件
    $('#date_b').click(function(){
        WdatePicker({
            dateFmt:'yyyy/MM/dd',
            maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
            readOnly:true
        });
    });
    $('#date_e').click(function(){
        WdatePicker({
            dateFmt:'yyyy/MM/dd',
            minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
            readOnly:true
        });
    });
   
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