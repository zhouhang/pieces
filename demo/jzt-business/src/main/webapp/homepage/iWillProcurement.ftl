<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材 中药材电子商务 有质量保障的仓储式中药材综合服务平台</title>
    <meta name="description" content="珍药材网-中国首创最大最有保障的线上线下相结合的电子商务仓
储式综合服务平台，提供各类大品种药材、小品种药材、涨跌价紧俏药材，保证现货，保证中药材质量
，提供线上交易、仓储服务、物流运输、融资服务、委托服务和价格行情资讯，让你感受到最全面、最
专业的中药材买卖及各类相关综合服务。
"/>
    <meta name="keywords" content="珍药材网，中药材，中药材价格行情，中药材交易，中药材仓储物流
，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/submitApply.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!--topper strat-->
<#include "common/indexListHeader.ftl">
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
    <div class="area-910 fl">
        <p class="title">请填写以下内容，我们将在一个工作日内给您答复，全国业务电话：400-10-54315</p>
        <div class="box-layout">
            <h2>珍药材采购需求申请</h2>
            <form id="busiPurchaseApplyForm" action="/busiPurchaseApply/addBusiPurchaseApply" method="post">
	            <p class="fl"><label>姓名</label><input type="text" name="applyName" class="text" /><br/>
	            	<span class="error">请填写姓名！</span>
	            </p>
	            <p class="fr"><label>手机</label><input type="text" name="applyMobile" class="text" /><br/>
	            	<span class="error">请填写手机号！</span>
	            </p>
	            <div class="clearfix"></div>
	            <h3>采购药材数量及产地</h3>
	            <table cellpadding="0" cellspacing="0" width="788"  class="table1" id="tb">
	                <tr bgcolor="f5e4c1">
	                    <th width="110" height="38">药材名称<br/><span>(例：三七)</span></th>
	                    <th width="90">等级规格<br/><span>(120头)</span></th>
	                    <th width="90">数量<br/><span>(50吨)</span></th>
	                    <th width="90">价格<br/><span>(120元/公斤)</span></th>
	                    <th width="90">产地<br/><span>(云南)</span></th>
	                    <th width="150">描述<br/><span>(水分：18% 总灰分：18%)</span></th>
	                    <th width="90">操作<br/></th>
	                </tr>
	                <tr datatype="mes" id="tr_0">
	                    <td height="50"><input type="text" name="busiPurchaseApplies[0].breedName" class="text" style="width:110px;" /></td>
	                    <td><input type="text" name="busiPurchaseApplies[0].breedStandardLevel" class="text" style="width: 90px;" /></td>
	                    <td><input type="text" name="busiPurchaseApplies[0].breedAmount" class="text" style="width: 90px;" /></td>
	                    <td><input type="text" name="busiPurchaseApplies[0].breedPrice" class="text" style="width: 90px;" /></td>
	                    <td><input type="text" name="busiPurchaseApplies[0].breedPlace" class="text" style="width: 90px;" /></td>
	                    <td><input type="text" name="busiPurchaseApplies[0].breedDesc" class="text" style="width: 150px;" /></td>
	                    <td></td>
	                </tr>
	                <tr>
	                    <td colspan="7" height="38" align="center"><a href="#" class="blue" id="add">+ 添加一行</a> </td>
	                </tr>
	            </table>
	            <div align="center">
	            	<input type="submit" class="btn-red" value="申 请" />
	            </div>
            </form>
        </div>
    </div>
    <div class="area-260 fr">
		<h2 class="tit1">联系客服</h2>
		<div class="hr"></div>
		<div class="contact-box">
			<p>
				<span style="display:inline-block; padding:8px 0;">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<span class="col_red">400-10-54315</span></span>
			<br /> <span style="display:inline-block; padding:8px 0;">在线QQ：
				<span style="display:inline-block; vertical-align:middle;"><script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAxNjQ5OF8yMzgzMjlfNDAwMTA1NDMxNV8"></script>
				</span></span>
			<br /> <span style="display:inline-block; padding:8px 0;">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<b>周一至周五</b><br /> <b
				style="padding-left: 60px; padding-bottom: 10px; display: block; line-height: 12px;">9:00
				~ 17:30</b> (12:30-13:30及法定假日除外)</span>
			</p>
		</div>
		<div class="hr"></div>

		<ul class="tabs-2 clearfix" id="tabs2">
			<li class="cur">常见问题</li>
			<li>交易安全</li>
		</ul>
		<div id="tabsCont2">
			<ul class="tabs-cont2" style="display: block;">
				<li><a href="http://help.54315.com/view-1770909150-4890870192.html" target="_blank">我要了解珍药材？</a></li>
				<li><a href="http://help.54315.com/view-826324128-134455933.html"  target="_blank">如何注册成为会员？</a></li>
				<li><a href="http://help.54315.com/view-1222808050-4277461942.html" target="_blank">采购商怎么加入珍药材？</a></li>
				<li><a href="http://help.54315.com/view-1717367380-9370413524.html" target="_blank">如何采购药材？</a></li>
				<li><a href="http://help.54315.com/view-2011458294-7859114524.html" target="_blank">供应商怎么加入珍药材？</a></li>
				<li><a href="http://help.54315.com/view-1442508962-9836334809.html" target="_blank">销售后如何结算？</a></li>
			</ul>
			<ul class="tabs-cont2">
				<li><a href="http://help.54315.com/view-1763402417-5532681146.html" target="_blank">珍药材交易保障？</a></li>
				<li><a href="http://help.54315.com/view-1148978752-4550043483.html" target="_blank">珍药材仓库保管安全？</a></li>
				<li><a href="http://help.54315.com/view-1291479146-6465076424.html" target="_blank">入仓后可远程查看？</a></li>
				<li><a href="http://help.54315.com/view-1393195069-8959880481.html" target="_blank">委托配送是怎么样的？</a></li>
				<li><a href="http://help.54315.com/view-443979987-1583418185.html" target="_blank">珍药材委托养护？</a></li>
				<li><a href="http://help.54315.com/view-296408919-7787049031.html" target="_blank">委托采购有哪些注意事项？</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/listFooter.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>

<script type="text/javascript">
//var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254171531'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1254171531' type='text/javascript'%3E%3C/script%3E"));
$(function(){
	$('#searchEngineListingButton').searcher({
		onSearch:function() {
		var keyword=$('input[type="text"].search-text').val();
		if(keyword == "输入名称找药材"){
			keyword='';
		}
		window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
	 	}
	});
	var index = 1;
	//添加一行
	$('#add').on('click',function(){
	    var string = '<tr datatype="mes" id="tr_'+index+'">'
	    			+'<td height="50"><input type="text" style="width:110px;" class="text" name="busiPurchaseApplies['+index+'].breedName" /></td>'
	    			+'<td><input type="text" name="busiPurchaseApplies['+index+'].breedStandardLevel" style="width: 90px;" class="text" /></td>'
	    			+'<td><input type="text" name="busiPurchaseApplies['+index+'].breedAmount" style="width: 90px;" class="text" /></td>'
	    			+'<td><input type="text" style="width: 90px;" name="busiPurchaseApplies['+index+'].breedPrice" class="text" /></td>'
	    			+'<td><input type="text" name="busiPurchaseApplies['+index+'].breedPlace" style="width: 90px;" class="text" /></td>'
	    			+'<td><input type="text" name="busiPurchaseApplies['+index+'].breedDesc" style="width: 150px;" class="text" /></td>'
	    			+'<td><input type="button"  value="删除行" class="btn-gray ml10"  onclick="delRow('+index+');"/></td></tr>';
	    $('.table1 tr:last').before(string);
	    index++;
	    return false;
	});
	//非空判断
    $('#busiPurchaseApplyForm').on('submit',function(){
    	var focusIndex = $('#busiPurchaseApplyForm input[type=text]').length;
    	var submitOk = true;
        $('#busiPurchaseApplyForm input[type=text]').each(function(i) {
            var text = $(this).val();
            if(text == '')
            {
            	if(i<focusIndex){
                	focusIndex = i;
                }
                $(this).addClass('borderRed');
                if($(this).parent().has('span')){
                    $(this).parent().children('.error').show();
                }
                
               $('.table1').next('.msg').remove();
                var msg ='<div class="msg">请填写内容！</div>';
                $('.table1').after(msg);
                submitOk = false;
            }
            else
            {
            	var name = $(this).attr('name');
            	if(name == 'applyMobile' && !/^1\d{10}$/.test(text)){
            		if(i<focusIndex){
	                	focusIndex = i;
	                }
	                $(this).val('').addClass('borderRed');
	                if($(this).parent().has('span')){
	                    $(this).parent().children('.error').show();
	                }
	                submitOk = false;
            	}else{
	                $(this).removeClass('borderRed');
	                if($(this).parent().has('span')){
	                    $(this).parent().children('.error').hide();
	                }
	                $('.table1').next('.msg').remove();
                }
            }
        });
        if(submitOk){
        	$('#busiPurchaseApplyForm input[type=submit]').attr("disabled",'disabled');
        }else{
        	$('#busiPurchaseApplyForm input[type=text]:eq('+focusIndex+')').focus();
        	$('#busiPurchaseApplyForm input[type=submit]').removeAttr('disabled');
        }
        return submitOk;
    });
	
    $("#clear-btn").click(function(){
    	$("#busiPurchaseApplyForm input[type='text']").val('');
    });
	//tabs
    $(function() {
        function tabs(tab, tabCont) {
            $(tab).on(
                    'click mousemove',
                    function() {
                        $(this).addClass('cur').siblings().removeClass(
                                'cur');
                        $(tabCont).children('ul').eq($(this).index())
                                .show().siblings().hide();
                    })
        }
        tabs('#tabs2 li', '#tabsCont2');
    });
});
//删除行
function delRow(rowId) {
	if(rowId!=0){
		$("#tr_" + rowId).remove();
	}
}
</script>
</body>
</html>