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
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<#import 'page.ftl' as fenye>
<#import 'macro.ftl' as tools>
<#assign TIPS="对不起，暂无数据!" /> <!--配置常用提示语-->
<script>
/**
 * Created by zhouli on 2015/1/19.
 */
$(function(){
    //顶部扫码关闭
    $('#top-saoma').on('click',function(){
        $(this).parent().hide();
    });

    //顶部菜单弹层
    function hoverer(id){
        $(id).hover(
            function(){
                $(this).children('span').addClass('cur1');
                $(this).children().children('i').addClass('cur');
                $(this).children('.sub').show();
            },
            function(){
                $(this).children('span').removeClass('cur1');
                $(this).children('span').children('i').removeClass('cur');
                $(this).children('.sub').hide();
            })
    }
    hoverer('#myZYC,#Service,#QA,#webNav');


    //搜索框分类切换
    $('.search ul>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    });
    /*//搜索匹配
    $('.search .text-bg').hover(
        function(){
            $(this).children('.match-bg').show();
        },
        function(){
            $(this).children('.match-bg').hide();
        }
    );
    $('.match-bg h5').click(function(){
        var _val = $(this).text();
        $('#Search').val(_val);
        $(this).parent().hide();
    });

    $('#Search').keydown(function(){
        $(this).children('.match-bg').show();


    });*/

    //一级菜单点击增亮效果
    $('.nav ul>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    });

    //左侧菜层级菜单效果
    $('.sub-nav .sub-nav-box').hover(
        function(){
            $(this).children('.tit').addClass('cur');
            //alert($(this).next('sub-navs'));
            $(this).children('.sub-navs').show();
        },
        function(){
            $(this).children('.tit').removeClass('cur');
            $(this).children('.sub-navs').hide();
        }
    );
    $('.sub-nav .sub-nav-box').slice(6, 11).children('.sub-navs').css('bottom','-2px');
    $('.sub-nav .sub-nav-box').slice(0, 6).children('.sub-navs').css('top','0');

    //tabs
    function Tabs(tabs,conts){
        $(tabs).bind('click mousemove',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $(conts).children().eq($(this).index()).show().siblings().hide();
        });
    }
    Tabs('#tabs1 li','#tabCont1');
    //删除订单功能
    $("a[name='btnDelete']").on('click',function(){
    	var orderid = $(this).attr('value');
    	bghui();
		Alert({
            str: "您确认要删除该关闭订单的信息吗?",
            title: "删除订单",
            buttons:[{
                name:'确定',
                classname:'btn-style',
                ev:{click:function(data){
                	$.ajax({
	                   	 url: "/order/deleteOrder",
	       				 type: 'post', 
	       				 data: {'orderid':orderid},
	       				 dataType:"json",
	       				 success:function(data){
	       					var msg = '';
	       					if(data&&data.ok){
	       						msg = '操作成功！';
	       					}else{
	       						if(data.errorMessages&&data.errorMessages.length>0){
	       							msg = data.errorMessages[0].message;
	       						}else{
	       							msg = '操作失败！';
	       						}
	       					}
	       					disappear();
	                        $(".bghui").remove();
	                        //提示
	                        Alert.remind(msg,function(){
	                        	//删除页面重载
	                        	 $('#conditionForm').submit();
	                        });
	       				}
                     });
                 }
               }
            },
            {
            	name:'取消',
            	classname:'btn-style',
            	ev:{click:function(data){
   					disappear();
                    $(".bghui").remove();
            	}}
            }]
	    });
    });
    
});
</script>
</head>
<body>
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
	<#include 'common/left.ftl'>
    <div class="hy-right fr">
        <div class="cd-main">
            <h1 class="title_deal">卖方订单</h1>
            <form action="/order/getSellOrderList" method="get" id="conditionForm">
                <ul class="stage-search">
                    <li>
                      <span>按状态：</span><div id="select-bg"><span><select id="orderstate" name="orderstate">
                        <option value="">全部</option>
                   		<#if busiOrderStateMap??>
                   			<#list busiOrderStateMap?keys as key>
                    			<option value="${key!'' }" <#if page.params.orderstate == key>selected</#if>>${busiOrderStateMap[key]!'' }</option>
                    		</#list>
                   		</#if>
                    </select></span></div>
                    <span class="pl15">按摘牌日期：</span><input type="text" class="text-store text-6 mr10 Wdate" id="datetimepicker1" name="orderStartDate" value="${(page.params.orderStartDate)!''}"  />至<input type="text" class="text-store text-6 ml10 Wdate" id="datetimepicker2" name="orderEndDate" value="${(page.params.orderEndDate)!''}"/>
                    <span class="pl15">订单编号：</span><input class="text-store text-5" type="text" id="orderid" name="orderid" value="${(page.params.orderid)!''}"/>
                    <br/><span>品&nbsp;&nbsp;种：</span><input class="text-store text-5" type="text" id="breedName" name="breedName" value="${(page.params.breedName)!''}"/>
                    <span><a class="col_999" href="javascript:void(0);" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');">清空</a></span><input type="submit" class="btn-red ml10" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div>
               <table class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="100">订单编号</th>
                        <th width="150">标题</th>
                        <th width="100">订单数量</th>
                        <th width="100">订单状态</th>
                        <th width="200">药材单价</th>
                        <th width="100">成交数量</th>
                        <th width="150">订单总价</th>
                        <th width="100">摘牌时间</th>
                        <th width="150">操作</th>
                    </tr>
                    <#if page.results?? && page.results?size gt 0>
	                   	<#list page.results as busiOrder>
		                    <tr>
		                        <td>${busiOrder.orderid!''}</td>
		                        
		                        <td class="opr-btn">
			                        <span class="operate-1 operate-a">
			                        	<a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${busiOrder.listingid}'  target='_blank'>
			                        	<#if busiOrder.title?length gt 6>
			                    			${busiOrder.title?substring(0,6)}...
			                    		<#else>
			                    			${busiOrder.title!''}
			                    		</#if>
			                    		</a>
			                        	<div class="tips tipa" align="left">
				                        	<span class="sj"></span>
				                        	${busiOrder.title!''}
			                        	</div>
			                        </span>
			                        <input type="hidden" id="${busiOrder.orderid!''}TitleTips" value="${busiOrder.title!''}">
			                    </td>
			                    <td><@tools.money num=busiOrder.amount format="0.##"/>${busiOrder.wlunit!''}</td>
	                        	<#if busiOrder.orderstate==0>
				              		<td class="text_green">买家已下单</td>
				              	<#elseif busiOrder.orderstate==1>
				              		<td>交易已完成</td>
				              	<#elseif busiOrder.orderstate==2>
				              		<td class="text_gray">买家已取消</td>
				              	<#elseif busiOrder.orderstate==3>
				              		<td class="text_green">已付保证金</td>
				              	<#elseif busiOrder.orderstate==4>
				              		<td class="text_red">买家已付款</td>
				              	<#elseif busiOrder.orderstate==5>
				              		<td class="text_red">已备货</td>
				              	<#else>
				              		<td>已关闭</td>
				              	</#if>
		                        <td>
		                        	<#if busiOrder.orderstate==0>
					              		<span class="pl15" style="white-space:nowrap;"><input id="${busiOrder.orderid!''}UnitPriceHidden" type="hidden" value="${busiOrder.unitprice!''}" /><input id="${busiOrder.orderid!''}UnitPrice" type="text" value="<@tools.money num=busiOrder.unitprice format="0.##"/>" class="txt textmini" style="text-align:center; *text-align:left;"> /公斤&nbsp;&nbsp;<input type="button" class="btn-gray" value="保存" onclick="updateOrderUnitPrice('${busiOrder.orderid!''}');"></span>
					              	<#else>
										<@tools.money num=busiOrder.unitprice format="0.##"/>元/${busiOrder.wlunit!''}
					              	</#if>
		                        </td>
		                        <td><@tools.money num=busiOrder.volume format="0.##"/>${busiOrder.wlunit!''}</td>
		                        <td id="${busiOrder.orderid!''}TotalPrice"><@tools.money num=busiOrder.totalprice format="0.##"/>元</td>
		                        <td>${busiOrder.createtime?string("yyyy-MM-dd")!''}</td>
		                        <td>
		                        	<a href="/order/getSellOrderDetail?orderId=${busiOrder.orderid!''}">详细</a>  
					              	<#if busiOrder.orderstate==2>
					              	  | <a href="/order/getOrderAppeal?orderId=${busiOrder.orderid!''}">查看进度</a> 
					              	<#elseif busiOrder.orderstate==-1>
					              	  <!-- <a href="javascript:void(0);" name="btnDelete" value="${busiOrder.orderid!''}">删除</a> -->
					              	</#if>
		                        </td>
		                    </tr> 
		                 </#list>
	                 <#else>
                    <tr align="center">
				    	<td colspan="9" style="font-family:微软雅黑;font-size:14px;">${TIPS}</td>
				    </tr>	
	                 </#if>  
                </table>
            </div>
            <@fenye.pages page=page form="conditionForm"/>
        </div>
	</div>
</div>
<#include 'common/footer.ftl'>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"> 
	//日期控件初始化
	$('#datetimepicker1').click(function(){
		WdatePicker({
			dateFmt:'yyyy/MM/dd',
			maxDate:'#F{$dp.$D(\'datetimepicker2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#datetimepicker2').click(function(){
		WdatePicker({
			dateFmt:'yyyy/MM/dd',
			minDate:'#F{$dp.$D(\'datetimepicker1\',{d:1});}',
			readOnly:true
		});
	});
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
	}
    //修改订单单价
    function updateOrderUnitPrice(orderId){
    	var orderUnitPriceHidden = $('#'+orderId+'UnitPriceHidden').val();	
    	var orderUnitPrice = $('#'+orderId+'UnitPrice').val();	
    	var title = $('#'+orderId+'TitleTips').val();
    	var reg=/^[0-9]+(.[0-9]{1,2})?$/;
		if(!reg.test(orderUnitPrice)){
			tips('请填写正数（至多两位小数）！');
			$('#'+orderId+'UnitPrice').val(orderUnitPriceHidden);
			return;
		 }
    	if(Number(orderUnitPrice)<=Number(0)){
	    	tips('单价不可修改为0！');
	    	$('#'+orderId+'UnitPrice').val(orderUnitPriceHidden);
			return;
    	}
    	bghui();
		Alert({
	            str: '确定要修改订单"'+title+'"的价格吗？',
	            buttons:[{
	                name:'确定',
	                classname:'btn-style',
	                ev:{click:function(data){
	                	 $.ajax({
							async : false,
							cache : false,
							type : "POST",
							data : {"orderId":orderId,"orderUnitPrice":orderUnitPrice},
							dataType : "json",
							url : "/order/updateOrderUnitPrice",
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									var orderTotalPrice = data.orderTotalPrice;
									$('#'+orderId+'UnitPriceHidden').val(orderUnitPrice);
									$('#'+orderId+'TotalPrice').text(orderTotalPrice+'元');
								}else{
									$('#'+orderId+'UnitPrice').val(orderUnitPriceHidden);
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
    }
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
</script>
</body>
</html>