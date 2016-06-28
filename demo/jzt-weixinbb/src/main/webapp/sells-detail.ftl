<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>${(goodsInfo.grade)!''}_${(goodsInfo.title)!''} -珍药材</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/js/Validform/css/style.css" />
    <script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>
    <script type="text/javascript" src="${RESOURCE_CSS_WX}/js/iscroll.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
	<style>
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>    
</head>
<body>
<div class="sell-box-head">
    <!--<a href="/search" style="display:block;z-index:3;" class="relative"><i class="back"></i></a>
    <div align="center" class="inStore-title relative">挂牌详情</div>-->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    		<td width="12%"><a href="/search"><i class="back"></i></a></td>
    		<td width="76%"><div align="center" style="width:100%;" class="inStore-title relative">挂牌详情</div></td>
    		<td width="12%">&nbsp;</td>
    	</tr>
    </table>
</div>
<div class="sellDetail">
    <div class="wraper">
        <header id="scroll_pic_view" class="scroll_pic_view" style="overflow: hidden; ">
            <div id="scroll_pic_view_div" style="width: 3840px; -webkit-transition: -webkit-transform 0ms cubic-bezier(0.33, 0.66, 0.66, 1); -webkit-transform-origin: 0px 0px; -webkit-transform: translate(0px, 0px) translateZ(0px); ">
                <ul id="scroll_pic_view_ul">
                    <#if goodsInfo??>
	                	<#list goodsInfo.goodsPicList as qualitypic>
		                	<#assign minImg = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_640"+qualitypic.path?substring(qualitypic.path?last_index_of("."),qualitypic.path?length)>
		  					<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
		                   <li><a onclick="return false;">
		                        <img src="${RESOURCE_IMG_UPLOAD_WX}/${tempurl}${minImg}" width="640" height="300"/>
		                    </a></li> 
						</#list>
					</#if>
                </ul>
            </div>
            <div>
                <ol id="scroll_pic_nav" class="scroll_pic_nav">
                    <script>
                        (function(d, $){
                            var scrollPicView = d.getElementById("scroll_pic_view"),
                                    scrollPicViewDiv = d.getElementById("scroll_pic_view_div"),
                                    lis = scrollPicViewDiv.querySelectorAll("li"),
                                    w = scrollPicView.offsetWidth,
                                    len = lis.length;
                            for(var i=0; i<len; i++){
                                lis[i].style.width = w+"px";
                                if(i == len-1){
                                    scrollPicViewDiv.style.width = w * len + "px";
                                }
                            }

                            var scroll_pic_view = new iScroll('scroll_pic_view', {
                                snap: true,
                                momentum: false,
                                hScrollbar: false,
                                useTransition: true,
                                onScrollEnd: function() {
                                    $("#scroll_pic_nav li").removeClass("on").eq(this.currPageX).addClass("on");
                                    //$("#scroll_pic_nav li.on").prev().addClass("left");
                                    //$("#scroll_pic_nav li.on").next().removeClass("left");

                                    var  list=$("#scroll_pic_nav li");
                                    for(var k=0;k<list.length;k++){
                                        if(k<this.currPageX)
                                            $(list[k]).addClass("left");
                                        else
                                            $(list[k]).removeClass("left");
                                    }
                                }
                            });
                            //图片轮播
							var scrollLen = 1;
							setInterval(function(){
								if(scrollLen<len){
									scroll_pic_view.scrollToPage('next', 0);
									scrollLen++;
								}else{
									scroll_pic_view.scrollToPage(0, 0);
									scrollLen = 1;
								}
							}, 5000);

                            var nav_lis = new Array(lis.length);
                            d.write('<li class="on"></li>');
                            for(var i=1; i<nav_lis.length; i++){
                                d.write("<li></li>");
                            }
                        })(document, $);
                    </script>
                </ol>
            </div>
        </header>
    </div>
    <form action="" name="buyForm" id="buyForm" method="post" target="_top">
    <div class="layout mt">
        <h1>${(goodsInfo.title)!''}</h1>
        <strong id="price">￥ <#if (goodsInfo.nobillPrice)??><@tools.money num=goodsInfo.nobillPrice format="0.##"/><#else>0</#if></strong>
        <p style="table-layout:fixed; word-break: break-all; overflow:auto;"><span>等级/规格：</span>${(goodsInfo.grade)!''}<br/>
            <span>数量：</span><#if (goodsInfo.amount)??><@tools.money num=goodsInfo.amount format="0.##"/><#else>0</#if>${(goodsInfo.unitname)!''}<br/>
            <span class="col_888">能否提供发票：</span><#if goodsInfo??><#if goodsInfo.hasbill==1>提供<#else>不提供</#if></#if><br/>
            <input type="hidden" id="goodsnobillPrice" value="<#if (goodsInfo.nobillPrice)??>${(goodsInfo.nobillPrice)?c!0}<#else>0</#if>" />
            <input type="hidden" id="goodsbillPrice" value="<#if (goodsInfo.billPrice)??>${(goodsInfo.billPrice)?c!0}<#else>0</#if>" />
            <span>产地：</span>${(goodsInfo.origin)!''}<br/>
            <span>包装规格：</span>${(goodsInfo.packingway)!''}<br/>
            <span>仓单编号：</span>${(goodsInfo.wlid)!''}<br/>
            <span>所在仓库：</span>${(goodsInfo.warehouseName)!''}<br/>
            <input type="hidden" id="maxsalesAmount" name="maxsalesAmount" value="<#if (goodsInfo.surpluses)??>${(goodsInfo.surpluses)?c!0}<#else>0</#if>" />
            <input type="hidden" id="minsalesAmount" name="minsalesAmount" value="<#if (goodsInfo.minsalesAmount)??>${(goodsInfo.minsalesAmount)?c!0}<#else>0</#if>" />
            <input type="hidden" id="totalAmount" value="<#if (goodsInfo.amount)??>${(goodsInfo.amount)?c!0}<#else>0</#if>" />
	           	<#if goodsInfo??>
		            <#if goodsInfo.hasbill==1>
		           	 	<span>是否需要发票：</span><select id="isneedBill"  name="isneedBill" style="border:1px solid grey;width:80px;" ><option <#if isneedBill==0>selected</#if> value="0">不需要</option><option <#if isneedBill==1>selected</#if> value="1">需要</option></select><br/>
		            </#if>
	            </#if>
	            <span>我要订购：</span><input id="orderNumber" value="<#if orderNumber!=0>${orderNumber}</#if>" maxlength="10" name="amount" style="border:1px solid grey;width:80px;" datatype="ordernum"  errormsg="订购数量有误" onkeyup="clearNoNum(this)" /><span id="orderNumberTip" class="Validform_checktip" style="font-size:85%;"></span><br/>
	            <span style="font-size:85%;">(<#if (goodsInfo.minsalesAmount)??><@tools.money num=goodsInfo.minsalesAmount format="0.##"/><#else>0</#if>${(goodsInfo.unitname)!''}起订,可购数量<span id="maxSalesAmountHtml"><#if (goodsInfo.surpluses)??><@tools.money num=goodsInfo.surpluses format="0.##"/><#elseif (goodsInfo.maxsalesAmount)??><@tools.money num=goodsInfo.maxsalesAmount format="0.##"/><#else>0</#if></span>${(goodsInfo.unitname)!''})</span><br/>
	            <span>总价：</span> <d id="totalPriceHtml" class="red f14">0元</d>（不含运费）<br/>
	            <span>保证金：</span> <d id="depositHtml" class="red f14">0元</d><br/>
	            <input type="hidden" id="totalPrice" name="totalPrice" />
	            <input type="hidden" id="goodsPrice" name="price" /><!--实际价格-->
	            <input type="hidden" id="deposit" name="deposit" /><!--保证金-->
	            <input type="hidden" name="listingerId" value="${(goodsInfo.userId)!''}"/>
	            <input type="hidden" name="breedcode" value="${(goodsInfo.breedId)!''}"/>
	            <input type="hidden" id="listingId" name="listingId" value="${(goodsInfo.listingid)!''}"/>
	            <input type="hidden" name="wlid" value="${(goodsInfo.wlid)!''}"/>
        </p>
    </div>
    <div class="layout">
        <h2>官方质检</h2>
        <p>性状描述：${(goodsInfo.levelEva)!'' }
        </p>
    </div>
    <div class="layout">
        <h2>理化检验</h2>
	    <#if (goodsInfo.itemMap)?? && (goodsInfo.itemMap)?size gt 0>
		<p>抽样数量：${goodsInfo.checkNumber!''} &nbsp;克</p>
		<#if (goodsInfo.itemMap)??>
			<table cellspacing="1" cellpadding="1" bgcolor="#f1d5d5" class="check-table">
				<tr align="left" bgcolor="#ffffff">
					<th width="90" height="30">检验项目</th>
					<th>标准规定</th>
					<th width="65">检验结果</th>
				</tr>
				<#list goodsInfo.itemMap?keys as key>
				<tr align="left">
					<td colspan="3" bgcolor="#f6f6f6" height="30">【${key}】</td>
				</tr>
    				<#list goodsInfo.itemMap[key] as item>
    				<tr align="left" bgcolor="#ffffff">
    					<td height="30">${(item.qualityItemName)!'' }</td>
    					<td>${(item.qualityItemStandard)!'' }</td>
    					<td>${(item.qualityItemResult)!'' }</td>
    				</tr>
    				</#list>
				</#list>
			</table>
		</#if>
	    <#else>
	     <p class="fontstyle">暂无理化检验信息</p>
	    </#if>
    </div>
    <!--购买记录-->
    <div class="a-sell-list1" style="padding-top: 0; margin-top: -1em;">
        <ul class="backN" id="buyResult">
            <li style="border-bottom: 0 none; margin-bottom: -1px; background-image: none;"><p class="adm-tit">最近交易 | <b class="fsm"><!--累积交易：<b class="col_red"><#if (goodsInfo.amount)?? && (goodsInfo.maxsalesAmount)??><@tools.money num=(goodsInfo.amount-goodsInfo.maxsalesAmount) format="0.##"/><#else>0</#if></b>${(goodsInfo.unitname)!''}，-->订单：<b class="col_red" id="tradeCount">0</b>单</b></p> </li>
        </ul>
        <div id="more" class="load" style="display:none;"></div>
    </div>
</div>
<div class="sDetail-foot">
    <a class="yellow" href="/preview?img=${zjPic}">查看质检报告单</a><a id="order_buy" class="red2" href="javascript:void(0);">下订单</a>
</div>
</form>
<!-- 下单成功提示框 -->
<div class="h-demand pay" id="Pay" class="relative">
    <div id="pay_close" class="close"></div>
    <div class="intro">
        <p><i class="icon1"></i>您已摘牌，请支付保证金才能锁定药材，
            请及时登录PC端珍药材平台支付保证金。
            <i class="icon2"></i>摘牌后<span class="col-red" id="hours">0小时</span>仍没缴纳保证金的订单，
            系统会<span class="col-red">自动关闭</span>此订单。
        </p>
    </div>
    <div class="pros">
        <p id="pay_line1" style="text-algin:center;margin-let:0em;margin-right:0em;">您还有 <strong class="col_red f14" id="day">0</strong>  天 <strong class="col_red f14" id="hms">00:00:00</strong>支付保证金</p>
        <div align="center">
            <div class="pros-box">平台网址:www.54315.com
            <a href="tel:4001054315">电询小珍</a></div>
        </div>
    </div>
</div>
<!-- 支付end -->
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript">
	//背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
        
	if('${orderNumber}'!=0){
		$('#orderNumber').focus();  //焦点移除，方便计算总价跟保证金
	}
		
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
	
 	//信息框
    function layerMsg(msg){
        layer.open({
			content: msg,
			style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			time: 2
		});
    }
    
    //提示框
    function alert(msg){
	    layer.open({
			content: msg,
			shadeClose: false,
			style: 'text-align:center;',
			btn: ['OK'],
			yes: function(index){
		    	layer.close(index);
				window.location.reload(true);
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
	 }
	 
	var deadline ; 
	//倒计时
	var s=1000;
	var m=60*1000;
	var h=60*60*1000;
	var d=24*60*60*1000;
					
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
					window.location.href=data.next;
				}else if(data.loginStatus=='1'){
		        	if(data.next){
						window.location.href=data.next;
					}
				}else if(data.loginStatus=='2'){//下单成功
					bgHiu();
					$('#hours').html(data.next+'小时');
					var expiretime = data.extra;
					expiretime = expiretime.replace(/-/g,"/");
					var expire = new Date(expiretime);
					deadline = expire.getTime();
					getDeadline();
					var timer = setInterval('getDeadline()', 1000); 
					$("#Pay").show();
				}else if(data.loginStatus=='3'){  //controller中发生异常或错误失败
					window.location.href='/delist-failure';
				}
			}
	});

	 //倒计时
	 function getDeadline(){
		var day,hour,min,sec;
		var cur = new Date().getTime();
		var dur = deadline - cur;
		if(dur<=0){
			day=0;
			hour=0;
			min=0;
			sec=0;
		}else{
			day=parseInt(dur/d);
			dur=dur%d;
			hour=parseInt(dur/h);
			dur=dur%h;
			min=parseInt(dur/m);
			dur=dur%m;
			sec=parseInt(dur/s);
		}
		$("#day").html(day);
		$("#hms").html(hour+":"+min+":"+sec);
		
		if(day=='0'&&hour=='0'&&min=='0'&&sec=='0'){
			$('#pay_line1').remove();
			clearInterval(timer);
		}
	}
	
	//当前页数
	var pageno = 1;
	$('#more').click(function(){
		getTradeRecord();
	})
    		
    $(function(){
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
     	    $("#price").html("￥ "+price);
     	    getDeposit();
         });
        
        $('#pay_close').on('click',function(){
        	window.location.href='/search'; 
        }) 
        
        //绑定下订单事件
        $('#order_buy').on('click',function(){
        	var flag ='${goodsInfo.listingFlag}';
        	var listingId ='${goodsInfo.listingid}';
        	var _orderNumber =$("#orderNumber").val();
        	var _isneedBill  = $("#isneedBill").val();
        	var orderNumber,isneedBill;
        	if(typeof(_orderNumber)=="undefined"){ 
        		orderNumber = 0;
        	}else{
        		orderNumber = _orderNumber;
        	}
        	if(typeof(_isneedBill)=="undefined"){ 
        		isneedBill = 0;
        	}else{
        		isneedBill = _isneedBill;
        	}	 
        	if(flag!=null && flag !=''){
        		if(flag=='3'){
        			//该商品已售完
        			window.location.href="/delist-void";
        		}else if(flag=='4'){
        			//该商品已下架
        			window.location.href="/delist-unShelve";
        		}else if(flag=='2'){
	        		$("#buyForm").attr("action","/detail/buyGoods?go=/detail/listingId/"+listingId+"/orderNumber/"+orderNumber+"/isneedBill/"+isneedBill);
	        		$("#buyForm").submit();
     			}
        	}
        });

        //关闭层
        function close(els){
            els.on('click',function(){
                $('.h-demand').hide();
                $('.bghui').remove();
            })
        }
        close($('.close'));
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }
		//购买记录
		getTradeRecord();
    })
    
    //交易记录
	function getTradeRecord(){
		if($('#more').hasClass('loading')){
			return false;
		}
		$('#more').addClass('loading');
    	var params ='listingId='+$("#listingId").val()+'&pageNo='+pageno;
		$.ajax({
			async : true,
			cache : false,
			type : 'GET',
			data : params,
			dataType : 'json',
			url : '/detail/getTradeRecordBy',
			success : function(data) {
				if(data==null) return false;
				$("#tradeCount").html(data.page.totalRecord);
				$('#more').removeClass('loading');
				var buys = data.page.results;
				var buysLength = buys.length;
				if(buysLength>0){
					$.each(buys,function(index,buy){
						var list_str='<li><p class="tit">';
			            list_str+='<span class="fl col_a9">买家：<span class="col_3e">'+(buy.buyerName.length>0?buy.buyerName.substring(0,1)+"**":"***")+'</span> </span>';
			            list_str+='<span class="fr col_red">'+data.page.params.status[buy.orderState]+'</span>';
			            list_str+='</p><dl>';
			            list_str+='<dd><span>数量：</span><b>'+formatNum(buy.buyAmount)+'</b> '+buy.unitName+'<br>';
			            list_str+='<span>申请时间：</span><b>'+buy.buyTime+'</b><br>';
			            list_str+='</dd></dl></li>';
					 	$('#buyResult').append(list_str);
					});
					if(data.page.pageNo < data.page.totalPage){
						$("#more").show();
					}else{
						$('#more').hide();
					}
					++pageno;
				}else{
					$('#more').hide();
					//判断只有第一页的时候才可能出现友情提示
					if(typeof($('#noresult'))!='undefined' && data.page.pageNo == 1){
	    				$('#buyResult').append('<li style="border:none;" id="noresult"><p style="border:none;padding-top:1em;">暂无购买记录!</p></li>');
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#more').removeClass('loading');
				layerMsg('网络超时，请重试！');
			}
		});
    }
</script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>