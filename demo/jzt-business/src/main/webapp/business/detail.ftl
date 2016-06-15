<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <#include "common/indexListTitle.ftl">
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/detail.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
    <#import 'macro.ftl' as tools>
</head>
<body>
<!--header-->
<#include "common/indexListHeader.ftl">
<!-- 主体开始 -->
<div class="area-1200 clearfix">
  <div class="area-192 fl mt10">
      <div class="box-1">
	      <div class="sals-box">
              <div class="person">
                  <#if sellerInfo.name??>
                  	<i class="icon-p dis-in-bk"></i>
                  	<h2>${sellerInfo.name}</h2>
                  <#else>
                  	<i class="icon-k dis-in-bk"></i>
                  	<h2>小珍</h2>
                  </#if>
                  <p>专属业务经理</p>
              </div>
              <div class="tel">
                  <i class="icon-tel dis-in-bk"></i><#if sellerInfo.mobilephone??>${sellerInfo.mobilephone}<#else>400-10-54315</#if>
              </div>
              <ul>
                  <li><span class="col_888">挂单数量</span><br/>${(sellerInfo.goodsCount)!''}</li>
                  <li><span class="col_888">已下单</span><br/>${(sellerInfo.orderSCount)!''}笔</li>
              </ul>
          </div>
          
          <!--<h2 class="title-1">卖家信息</h2>
          <div class="sals-box">
              <h3>货主：<#if (sellerInfo.certify)??>
              			 <#if (sellerInfo.certify)=="1">
                            <#if (sellerInfo.name)?length ==1>${sellerInfo.name}</#if>
                            <#if (sellerInfo.name)?length ==2>${(sellerInfo.name)?substring(0,1)}*</#if>
                            <#if (sellerInfo.name)?length ==3>${(sellerInfo.name)?substring(0,1)}*${(sellerInfo.name)?substring(2,3)}</#if>
                            <#if (sellerInfo.name)?length gt 3>${(sellerInfo.name)?substring(0,1)}**${(sellerInfo.name)?substring((sellerInfo.name)?length-1,(sellerInfo.name)?length)}</#if>
                          </#if>
                          <#if (sellerInfo.certify)=="2"> 
                          	 <#if (sellerInfo.name)?length ==4>${(sellerInfo.name)?substring(0,3)}*</#if>
                          	 <#if (sellerInfo.name)?length gt 4>${(sellerInfo.name)?substring(0,4)}*****</#if>
                          </#if>
                       </#if> 
                       <img src="${RESOURCE_IMG}/images/rz1.png"/> </h3>
              <p><span>联系方式：</span>400-10-54315</br>
                  <span>注册时间：</span>${(sellerInfo.registTime)!''}<br/>
                  <span>挂单数量：</span>${(sellerInfo.goodsCount)!''}<br/>
                  <span>成交单数：</span>${(sellerInfo.dealCount)!''}笔</p>
                  <span>已&nbsp;下&nbsp;单：</span>${(sellerInfo.orderSCount)!''}笔</p>
          </div> -->
      </div>

      <div class="box-1 mt10">
          <h2 class="title-1">相关药材</h2>
          <ul class="img-list1 mt10">
          <#list recommendGoodsList as recommendGoods>
              <li>
                  <a href="getBusiGoodsDetail?listingId=${recommendGoods.listingid}" title="${recommendGoods.title}" target="_blank">
                  <#if recommendGoods.picPath??>
                  	<#assign minImg = recommendGoods.picPath?substring((recommendGoods.picPath?last_index_of("/")+1),(recommendGoods.picPath?last_index_of(".")))+"_120"+recommendGoods.picPath?substring(recommendGoods.picPath?last_index_of("."),recommendGoods.picPath?length)>
  				  	<#assign tempurl = recommendGoods.picPath?substring(0,recommendGoods.picPath?last_index_of("/")+1)>
                  	<img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${minImg}" alt="${recommendGoods.title}"/>
                  <#else>
                  	<img src="" alt="${recommendGoods.title}"/>
                  </#if>
                  </a>
                  <h3>
                  <a href="getBusiGoodsDetail?listingId=${recommendGoods.listingid}" title="${recommendGoods.title}" target="_blank">
                  <#if (recommendGoods.title)?length gt 16>
                  	${recommendGoods.title?substring(0,16)}...
                  <#else>
                    ${recommendGoods.title}
                  </#if>
                  </a>
                  </h3>
                  <p>价格：<strong><@tools.money num=recommendGoods.price format="0.##"/></b></strong>元/${recommendGoods.unitName}</p>
              </li>
             </#list>
          </ul>
      </div>
  </div>
  
    <div class="area-998 fr mt10">
        <div class="show-box">
            <div class="pic-show fl">
                <span><img <#if (firstGoodsPic.path)?? >src="${RESOURCE_IMG_UPLOAD}/${(firstGoodsPic.path)!''}" data-zoom-image="${RESOURCE_IMG_UPLOAD}/${(firstGoodsPic.path)!''}" <#else> src="" </#if> id="elevate-zoom-img" /></span>
                <div class="small-pic" id="pro-thumbnail-img">
                <#if goodsInfo??>
                <#list goodsInfo.goodsPicList as qualitypic>
                	<#assign minImg = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_640"+qualitypic.path?substring(qualitypic.path?last_index_of("."),qualitypic.path?length)>
                	<#assign bigImg = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+qualitypic.path?substring(qualitypic.path?last_index_of("."),qualitypic.path?length)>
  					<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
                    <a 
                    <#if qualitypic_index == 0>
                    	class="hover" 
                    </#if>
                    href="javascript:;"  data-image="${RESOURCE_IMG_UPLOAD}/${tempurl}${minImg}" data-zoom-image="${RESOURCE_IMG_UPLOAD}/${tempurl}${bigImg}">
                        <img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${minImg}" />
                    </a>
				</#list>
				</#if>
                </div>
            </div>
            <div class="intro fr">
                <h1>${(goodsInfo.title)!''}</h1>
                <dl class="clearfix">
                <dd><span class="col_888">单价：</span><b id="price" class="charge1">¥<#if (goodsInfo.nobillPrice)??><@tools.money num=goodsInfo.nobillPrice format="0.##"/><#else>0</#if></b>元/${(goodsInfo.unitname)!''}</dd>
                <dd><span class="col_888">等级/规格：</span>${(goodsInfo.grade)!''}</dd>
                <dd style="float:right; margin-top:-25px;"><img src="${RESOURCE_IMG}/images/xhc.png" title="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" alt="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" />&nbsp;现货保证</dd>
                </dl>
                <dl class="clearfix">
                    <dt>基本信息</dt>
                    <dd><span class="col_888">数量：</span><#if (goodsInfo.amount)??><@tools.money num=goodsInfo.amount format="0.##"/><#else>0</#if>${(goodsInfo.unitname)!''}</dd>
                    <dd><span class="col_888">能否提供发票：</span><#if goodsInfo??><#if goodsInfo.hasbill==1>提供<#else>不提供</#if></#if></dd>
                    <input type="hidden" id="goodsnobillPrice" value="<#if (goodsInfo.nobillPrice)??>${(goodsInfo.nobillPrice)?c!0}<#else>0</#if>" />
                    <input type="hidden" id="goodsbillPrice" value="<#if (goodsInfo.billPrice)??>${(goodsInfo.billPrice)?c!0}<#else>0</#if>" />
                    <dd><span class="col_888">产地：</span>${(goodsInfo.origin)!''}</dd>
                    <dd><span class="col_888">包装规格：</span>${(goodsInfo.packingway)!''}</dd>
                </dl>
                <dl class="clearfix">
                    <dt>交收信息</dt>
                    <dd style="white-space:nowrap;"><span class="col_888">仓单编号：</span>${(goodsInfo.wlid)!''}</dd>
                    <dd><span class="col_888">所在仓库：</span>${(goodsInfo.warehouseName)!''}</dd>
                </dl>
                <input type="hidden" id="maxsalesAmount" name="maxsalesAmount" value="<#if (goodsInfo.surpluses)??>${(goodsInfo.surpluses)?c!0}<#else>0</#if>" />
                 <input type="hidden" id="minsalesAmount" name="minsalesAmount" value="<#if (goodsInfo.minsalesAmount)??>${(goodsInfo.minsalesAmount)?c!0}<#else>0</#if>" />
                 <input type="hidden" id="totalAmount" value="<#if (goodsInfo.amount)??>${(goodsInfo.amount)?c!0}<#else>0</#if>" />
                <form action="" name="buyForm" id="buyForm" method="post" target="_top">
                <div class="others box-1 mt10 relative">
                    <div>
                    <#if goodsInfo??>
                    <#if goodsInfo.hasbill==1>
                    <span class="col_888">是否需要发票：</span><select id="isneedBill" name="isneedBill" class="text" ><option value="0">不需要</option><option value="1">需要</option></select><br/>
                    </#if></#if>
                    <span class="col_888">我要订购：</span> <input id="orderNumber" maxlength="10" name="amount" class="text text-1" datatype="ordernum"  errormsg="请输入正确的订购数量！" onkeyup="clearNoNum(this)" />${(goodsInfo.unitname)!''}  <span class="col_888">（<#if (goodsInfo.minsalesAmount)??><@tools.money num=goodsInfo.minsalesAmount format="0.##"/><#else>0</#if>${(goodsInfo.unitname)!''}起订,可购数量<span id="maxSalesAmountHtml"><#if (goodsInfo.surpluses)??><@tools.money num=goodsInfo.surpluses format="0.##"/><#elseif (goodsInfo.maxsalesAmount)??><@tools.money num=goodsInfo.maxsalesAmount format="0.##"/><#else>0</#if></span>${(goodsInfo.unitname)!''}）</span>
                    <span id="orderNumberTip" class="Validform_checktip"></span>
                   <br/>
                    <span class="col_888">总价：</span> <span id="totalPriceHtml" class="red f14">0元</span><span>（不含运费）</span>
                    <span class="quse ml0">
                            <span class="tips" align="left"><span class="sj"></span>下单后会有跟单员与您联系，帮助您处理运费问题。</span>
                    </span> 
                    <span class="col_888">点击购买后可选择先付保证金方式，也可选择一次性支付全款方式</span>
                    <br/>
                    <span class="col_888">保证金：</span> <span id="depositHtml" class="red f14">0元</span>
                    <input type="hidden" id="totalPrice" name="totalPrice" />
                    <input type="hidden" id="goodsPrice" name="price" /><!--实际价格-->
                    <input type="hidden" id="deposit" name="deposit" /><!--保证金-->
                    <input type="hidden" name="listingerId" value="${(goodsInfo.userId)!''}"/>
                    <input type="hidden" name="breedcode" value="${(goodsInfo.breedId)!''}"/>
                    <input type="hidden" id="listingId" name="listingId" value="${(goodsInfo.listingid)!''}"/>
                    <input type="hidden" name="wlid" value="${(goodsInfo.wlid)!''}"/>
                    </div>
                    <div class="mt20">
                      <#if (goodsInfo.listingFlag)?? >
                      	<#if goodsInfo.listingFlag=="2">
                           <input type="button" class="btn-buy mr10" value="购 买" id="buyBtn"  /><input type="button" class="btn-collect" value="收 藏" id="collectBtn"  />
                        <#else>
                        	<#if goodsInfo.listingFlag=="3">
	                           	<p style="margin-top:-20px; color:#c40000; margin-left:-18px;">该商品已售完！</p>
	                        <#elseif goodsInfo.listingFlag=="4">
		                        <p style="margin-top:-20px; color:#c40000; margin-left:-18px;">该商品已下架，暂不可进行操作！</p>
                        	</#if>
                        	<input type="button" class="btn-buygray mr10" value="购 买"  disabled="disabled" /><input type="button" class="btn-collectgray" value="收 藏"  disabled="disabled"  />
                       </#if>
                       </#if>
                    </div>
                </div>
               </form>
            </div>
            <div class="clearfix"></div>
            <!--update by fanyuna 如果挂牌的仓单没有QC质检信息，则不显示官方质检版块-->
            <#if (goodsInfo.itemMap)??&&(goodsInfo.itemMap)?size gt 0>
            <h2 class="title-3 mt20">官方质检
            	<a href="javascript:;" class="btn-report btnReport_zhijian" data-zhijian="${RESOURCE_IMG_UPLOAD}/${zjPic!''}"></a>
            </h2>
            <div class="box-1  relative">                
                <div class="hr"></div>
                <ul class="quality clearfix mt20">
               		<#if (goodsInfo.levelEva)??>
                    <li>
                        <h4>性状描述：</h4>
                        <p>
                            ${(goodsInfo.levelEva)!'' }<br/>
                        </p>
                    </li>
                    </#if>
                    <li>
                        <h4>理化检验：</h4>
                        <p><strong>规格</strong>：${(goodsInfo.grade)!''}  <strong class="ml50">产地</strong>：${(goodsInfo.origin)!''}  <strong class="ml50">数量</strong>：${(goodsInfo.checkNumber)!''}克</p>
						
						 <#if (goodsInfo.itemMap)??>
                        <table cellspacing="1" cellpadding="1" bgcolor="#eecccb" class="check-table">
                        
                            <tr align="left" bgcolor="#ffffff">
                                <th width="138" height="42">检验项目</th>
                                <th width="138">检验方法</th>
                                <th width="415">标准规定</th>
                                <th width="232">检验结果</th>
                                <!-- <th width="90" height="30">检验项目</th>
                                <th>标准规定</th>
                                <th>检验结果</th> -->
                            </tr>
                            <#list goodsInfo.itemMap?keys as key>
                            	<#list goodsInfo.itemMap[key] as item>
                            		<tr align="left" <#if key_index % 2==0>bgcolor="#f8eeee"<#else>bgcolor="#ffffff"</#if> >
                            		<#if item_index=='0'>
                            			<td rowspan="${goodsInfo.itemMap[key]?size}">${key}</td>
                            		</#if>
	                                <td height="37">${(item.qualityItemName)!'' }</td>
	                                <td>${(item.qualityItemStandard)!'' }</td>
	                                <td>${(item.qualityItemResult)!'' }</td>
	                                </tr>
                            	</#list>
                            </#list>
                        </table>
                        <div class="tips">检验单位：九州通中药材电子商务有限公司</div>
						</#if>
                    </li>
                </ul>
                <div class="zhijianbiao"><a href="javascript:;" class="btnReport_zhijian" data-zhijian="${RESOURCE_IMG_UPLOAD}/${zjPic!''}"> <img src="${RESOURCE_IMG}/images/zhijianbiao.png"/></a> </div>
            </div>
            </#if>

			<!--<h2 class="title-3 mt20 fl">最近交易</h2><span class="fl title-3fu">累积交易<b class="col_red" id="totalTradeAmount"><#if (goodsInfo.amount)?? && (goodsInfo.surpluses)??><@tools.money num=(goodsInfo.amount-goodsInfo.surpluses) format="0.##"/><#else>0</#if></b>${(goodsInfo.unitname)!''}，交易<b class="col_red" id="tradeCount">0</b>单</span> -->
			<h2 class="title-3 mt20 fl">最近交易</h2><span class="fl title-3fu">交易<b class="col_red" id="tradeCount">0</b>单</span>
            <div class="box-1 relative clearfix">
                <div class="clearfix"></div>
                <div class="buy-notes" id="tradeRecord" >
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <th width="20%" height="26">购买用户</th>
                            <th width="25%">购买数量</th>
                            <!--<th width="15%">单价</th>-->
                            <th width="25%">购买申请时间</th>
                            <th width="25%">状态</th>
                        </tr>
                        <tr row-head-title="tradeRecordTitle" id="tr_id">
                            <td height="12" colspan="4" class="bn"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <h2 class="title-3 mt20">货主描述</h2>
            <div class="box-1 relative">
                <textarea id="textareaContent" style="display:none;">${(goodsInfo.content)!''}</textarea>
           		<iframe id="iframeContent" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
            </div>
        </div>
    </div>
</div>
<!--footer-->
<#include "common/listFooter.ftl">

<!--质检报告弹层 start-->
<div class="pop-report" id="reportPop">
    <img src="${RESOURCE_IMG_UPLOAD}/${zjPic!''}"/>
</div>
<div class="pop-report-border">
    <div class="close" id="rClose">关闭窗口</div>
</div>
<!--质检报告弹层 over-->

<!-- 登录窗口弹层  -->
<div class="logon-box">
    <iframe width="100%" height="390" src="" id="popLoginIframe" name="popLoginIframe" scrolling="0" frameborder="0"></iframe>
    <div class="close"></div>
</div>
<!-- 登录窗口弹层  over -->

<!-- 侧边固定操作项 start -->
<div class="sade-box">
    <div class="qc"><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001054315&aty=2&a=2&curl=&ty=1" target="_blank">在线客服</a></div>
    <div class="weixin">官方微信</div>
    <span class="wx-sm"><img src="${RESOURCE_IMG}/images/detailsm0826.png" /> </span>
    <div class="back">回到顶部</div>
</div>
<!-- 侧边固定操作项 end -->


<input type="hidden" value="" id="userOperate"/>
<input type="hidden" id="traceId" <#if trace!=null && trace.id!=null>value="${trace.id}"</#if> />

<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/elevatezoom.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
<script  type="text/javascript">
	//登录框
	function popLogin(type){
		$('#userOperate').val(type);
	    $('#popLoginIframe').attr('src','https://passport.54315.com/login?service=http://www.54315.com/casuc&isajax=true&isPopLogin=true&type='+type);
	    $('.logon-box').show();
	    bghui();
	}
	$('.logon-box .close').on('click',function(){
	    $('.logon-box').hide();
	    $('.bghui').remove();
	});

	function popCallBack(){
		if($('#userOperate').val()=="buyForm"){
	    	buyValidForm.submitForm();
	    	$('#popLoginIframe').remove();
		}else if($('#userOperate').val()=="collectBtn"){
			$("#collectBtn").click();
			$('#popLoginIframe').remove();
		}
	}

	var buyValidForm = $("#buyForm").Validform({
             tiptype:4,
             showAllError:true,
             ajaxPost:true,
             datatype:{
     			"ordernum":function(){
     				var totalPrice = 0;
     				var num = $("#orderNumber").val();
     				$("#totalPriceHtml").html(totalPrice+"元");
     				$("#totalPrice").val(totalPrice);
     				$("#depositHtml").html(0+"元");
     				$("#deposit").val(0);
     				if(num==null || num==''){
     					return "请输入订购数量！";
     				}
     				if(isNaN(num))
     					return false;
     				if(Number(num)<Number($("#minsalesAmount").val()))
     				    return false;
     				if(Number(num)>Number($("#maxsalesAmount").val()))
     				    return false;
 				 	if($("#isneedBill").children("option:selected").val()==1){
 				 		totalPrice = formatNum(accMul(num,$("#goodsbillPrice").val()));
 				 		$("#goodsPrice").val($("#goodsbillPrice").val());
 				 	}else{
 				 		totalPrice = formatNum(accMul(num,$("#goodsnobillPrice").val()));
 				 		$("#goodsPrice").val($("#goodsnobillPrice").val());
 				 	}
     				 $("#totalPriceHtml").html(totalPrice+"元");
     				 $("#totalPrice").val(totalPrice);
     				 
     				//获得支付保证金
     				$.ajax({
						cache: true,
						type: "POST",
						url:"/detail/getDeposit?totalPrice="+$("#totalPrice").val(),
						async: false,
						success: function(data) {
							$("#deposit").val(data);
							$("#depositHtml").html(data+"元");
						},
						error: function(error) {
							
						}
					});
     			}
     		},
     		callback:function(data){
				if(data.loginStatus=='0'){
					popLogin("buyForm");
					return;
				}else if(data.loginStatus=='1'){
		        	if(data.next){
						window.location.href=data.next;
					}
				}else if(data.loginStatus=='2'){
					if(data.next){
						window.location.href=data.next;
					}
				}else if(data.loginStatus=='3'){  //controller中发生异常或错误失败
					bghui();
					Alert({
					//str:data.errorMessages[0].message,
					str:data.extra,
					buttons:[{
						name:'确定',
						id:'1',
						classname:'btn-style',
						ev:{click:function(data){
								disappear();
								$(".bghui").remove();
							}
							}
						}]
					});
				}
			}
	});
	
	//收藏 collectBtn
	$("#collectBtn").click(function(){
		$.ajax({
			cache: true,
			type: "POST",
			url:"/detail/collectGoods?go=detail/getBusiGoodsDetail?listingId="+$("#listingId").val(),
			data:$('#buyForm').serialize(),// 你的formid
			async: false,
			dataType:'json',
			error: function(textStatus, errorThrown) {
				if(errorThrown=="error" || textStatus.status== 0){
					window.location.href="https://passport.54315.com/login?service=http://www.54315.com/casuc";
				}
			},
			success: function(data) {
				//data = eval('(' + data + ')');
				if(data.loginStatus=='0'){
					popLogin("collectBtn");
					return;
				}else if(data.loginStatus=='1'){
		        	if(data.next){
						window.location.href=data.next;
					}
				}else if(data.loginStatus=='2'){
					bghui();
					Alert({
						str:data.extra,
						buttons:[{
							name:'确定',
							id:'1',
							classname:'btn-style',
							ev:{click:function(data){
								disappear();
								$(".bghui").remove();
								}
								}
							}]
					});
				}else if(data.loginStatus=='3'){
					bghui();
					Alert({
						str:data.extra,
						buttons:[{
							name:'确定',
							id:'1',
							classname:'btn-style',
							ev:{click:function(data){
								disappear();
								$(".bghui").remove();
								}
								}
							}]
					});
				}
			}
		});
	});

    /**
	*特殊处理textarea从数据库中读取的样式在前端界面显示的样式错乱
	**/
	var iframeHeight;//iframe当前高度
	var iframeTimer;//iframe定时器
  	//iframe自适应高度
	function resizeIframe(iframeId){
	    var iframe = document.getElementById(iframeId);
	    try{
	        var bHeight = iframe.contentWindow.document.body.scrollHeight;
	        var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
	        var height = Math.max(bHeight, dHeight);
	        iframe.height = height;
	        if(iframeHeight==height){
	        	clearInterval(iframeTimer);
	        }
	        iframeHeight = height;
	        //alert(bHeight+"/"+dHeight);
	    }catch (ex){
	    	//alert(ex);
	    }
	};
	//加载iframe
	function loadIframe(textareaId,iframeId){
		$('#'+iframeId).contents().find('body').html($('#'+textareaId).val());
		//图片自适应宽度
		var iframeWidth = $('#'+iframeId).width();
		var imgs = $('#'+iframeId).contents().find('body').find('img');
		$(imgs).each(function(index,img){
			var imgWidth = $(img).width();
			if(imgWidth>iframeWidth){
				$(img).width('100%');
				$(img).height('');
			}
		});
		//iframe自适应高度
		iframeTimer = window.setInterval(function(){
			resizeIframe(iframeId);
		},1000);
	}; 	
    //显示iframe富文本
    loadIframe('textareaContent','iframeContent');
    //add by fanyuna 文本框只能输入数字，且小数位数最多为2位
    function clearNoNum(obj){
	  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    };
   
	//返回值：arg1除以arg2的精确结果
	function accDiv(arg1,arg2){
	    var t1=0,t2=0,r1,r2;
	    try{t1=arg1.toString().split(".")[1].length}catch(e){}
	    try{t2=arg2.toString().split(".")[1].length}catch(e){}
	    with(Math){
	        r1=Number(arg1.toString().replace(".",""));
	        r2=Number(arg2.toString().replace(".",""));
	        return (r1/r2)*pow(10,t2-t1);
	    }
	};
	//乘法函数，用来得到精确的乘法结果
	function accMul(arg1,arg2)
	{
	    var m=0,s1=arg1.toString(),s2=arg2.toString();
	    try{m+=s1.split(".")[1].length}catch(e){}
	    try{m+=s2.split(".")[1].length}catch(e){}
	    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
	};
	//加法函数，用来得到精确的加法结果
	function accAdd(arg1,arg2){
	    var r1,r2,m;
	    try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
	    try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
	    m=Math.pow(10,Math.max(r1,r2));
	    return (arg1*m+arg2*m)/m;
	};
	//减法函数
	function accSub(arg1,arg2){
	     var r1,r2,m,n;
	     try{
	         r1=arg1.toString().split(".")[1].length;
	         }catch(e){
	             r1=0;
	             }
	     try{
	         r2=arg2.toString().split(".")[1].length;
	         }catch(e){
	             r2=0;
	             }
	     m=Math.pow(10,Math.max(r1,r2));
	     //last modify by deeka
	     //动态控制精度长度
	     n=(r1>=r2)?r1:r2;
	     return ((arg2*m-arg1*m)/m).toFixed(n);
	};
   
	//交易记录
	function getTradeRecord(){
		var firstTable = $('#tradeRecord').pagination({
			targetId: 'tradeRecord',
			type: 'post',
			//url: '/order/getTradeRecordBy?listingId='+$("#listingId").val(),
			url: '/detail/getTradeRecordBy?listingId='+$("#listingId").val(),
			init:  true,
			pageTheme:'detail',
			rowHtml: function(rowData,pagingData) {
				var html="<tr>";
                    //html+="<td height='24'>"+(rowData.buyerName.length>0?rowData.buyerName.substring(0,1)+"**":"***")+"</td>";
                    html+="<td height='24'>"+rowData.buyerName+"</td>";
                    html+="<td class='col_7d7d7d'><span class='col_303030'>"+formatNum(rowData.buyAmount)+"</span>"+rowData.unitName+"</td>";
                    //html+="<td class='col_7d7d7d'><span class='col_red'>"+formatNum(rowData.unitprice)+"</span>元/"+rowData.unitName+"</td>";
                    html+="<td>"+rowData.buyTime+"</td>";
                    if(pagingData.extraData.status[rowData.orderState] == '已关闭'){
                    	html+="<td class='col_161616'>已下单</td>";
                    }else{
                    	html+="<td class='col_161616'>"+pagingData.extraData.status[rowData.orderState]+"</td>";
                    }
                    
                    html+="</tr>";
                   return html;
			},
			afterRefresh: function(rowData){
			//设置交易单数
			 $("#tradeCount").html(this.totalRecord);
			},
			emptyRow : function(page){
				var html = '<tr><td colspan="5" style="border:none;font-family:微软雅黑;font-size:14px;">暂无记录!</td></tr>';
				$("#tr_id").after(html);
				$("#tradeRecord_pagination").remove();
			}
		});
    };

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
 
	 $(function(){
		//tips,出现标题提示
		var Height = $('.tips').height()+18;
	/* 	$('.opr-btn .tips').css('top',-Height);
        $('.operate-1').hover(
                function(){
                    $(this).children('.tips').show();
                },
                function(){
                    $(this).children('.tips').hide();
                }
        ); */
		$('.quse .tips').css('top',-Height);
        $('.quse').hover(
                function(){
                    $(this).children('.tips').show();
                },
                function(){
                    $(this).children('.tips').hide();
                }
        );
		//调用搜索插件
		$('#searchEngineListingButton').searcher({
			onSearch:function() {
			var keyword=$('input[type="text"].search-text').val();
			if(keyword == "输入名称找药材"){
				keyword='';
			}
			window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
		 	}
		});
		
		//购买
        $("#buyBtn").click(function(){
        	$("#buyForm").attr("action","/detail/buyGoods?go=detail/getBusiGoodsDetail?listingId="+$("#listingId").val());
        	$("#buyForm").submit();
        });
        
    	$('#searchEngineListingText').focus(function(){
			if($(this).val() == "输入名称找药材"){
				$(this).val('');
			}
			}).blur(function(){
				if($(this).val() === ''){
				$(this).val('输入名称找药材');
			}
		});
		
		//详情页-放大镜	
        $("#elevate-zoom-img").elevateZoom({
            zoomWindowFadeIn: 300,
            zoomWindowFadeOut: 300,
            zoomWindowWidth: 422,
            zoomWindowHeight: 422,
            lensBorderSize:1,
            gallery : "pro-thumbnail-img",
            galleryActiveClass: "hover"
        });

        
        //调用分页方法，
		getTradeRecord();
     
         //是否需要发票下拉框值更改事件
         $("#isneedBill").change(function(){
         	var num = $("#orderNumber").val();
         	var totalPrice = 0,price=$("#goodsnobillPrice").val();
         	//var selectBill = ("#isneedBill  option:selected").text();
         	var selectBill = $("#isneedBill").children("option:selected").val();
         	if(selectBill==1){
         	  price=$("#goodsbillPrice").val();
         	}else{
         		price=$("#goodsnobillPrice").val();
         	}
         	if(num!=null && num!=''){
	         	totalPrice = formatNum(accMul(num,price));
	        }
         	$("#totalPriceHtml").html(totalPrice+"元");
     	    $("#totalPrice").val(totalPrice);
     	    $("#price").html("¥"+price);
     	    //获得支付保证金
     		$.ajax({
				cache: true,
				type: "POST",
				url:"/detail/getDeposit?totalPrice="+$("#totalPrice").val(),
				async: false,
				success: function(data) {
					$("#deposit").val(data);
					$("#depositHtml").html(data+"元");
				},
				error: function(error) {
					
				}
			});
         });
    });
	
 
  $(function(){
	    //unload 记录trace
	    $(window).unload(function(){
	    	$.ajax({
				data: {id:$('#traceId').val(),data:{listingId:$('#listingId').val()}},
				type: "POST",
				url:"/detail/unload",
				async: true,
				success: function(data) {
				},
				error: function(error) {
				}
			});
		});
        //图片点击查看大图拖动效果
        $('.btnReport_zhijian').click(function(){
            $('#reportPop').show();
            //$('#reportPop img').attr("src", $(this).data("zhijian"));
            $('.pop-report-border').show();
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
            return false;
        });
        
        /*$(document).bind("click",function(e){
            var target  = $(e.target);
            if(target.closest("#reportPop,.pop-report-border,#msgdiv,#buyBtn,#collectBtn").length == 0){
                $('#reportPop').hide();
                $('.pop-report-border').hide();
                $('.bghui').remove();
                $('#msgdiv').remove();
            }
            e.stopPropagation();
        });*/
	        
         $('#rClose').click (function(){
            $('#reportPop').hide();
            $('.pop-report-border').hide();
            $('.bghui').remove();
     	 });
        
        $('#reportPop').imageView({width:1060, height:500,ee:-297})
    });
  
  //回到顶部
  $(function(){
      var Wwid = $(window).width();
      var wid = (Wwid-1200)/2-83;
      $('.sade-box').css('right',wid);
      $('.weixin').hover(
          function(){
              $(this).next('.wx-sm').show();
          },
          function(){
              $(this).next('.wx-sm').hide();
          }
      );
      $(".back").click(function() {
          $('html,body').animate({scrollTop:0},300);
      });
  })
</script>
</body>
</html>