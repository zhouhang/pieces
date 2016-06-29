<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>买方订单</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/js/Validform/css/style.css" >
</head>
<body>
<div class="sell-box-head">
    <i class="back"></i>
        <span class="fr" id="searchM" style="display: block;">
            <a href="#2" name="check"></a>
        </span>
</div>

    <div class="oBuyer-sider clearfix">
        <div style="margin-right: 1em; width: 96%; overflow: hidden;">
        <ul class="main_title" style="transition-property: transform; -webkit-transition-property: transform; transition-timing-function: cubic-bezier(0, 0, 0.25, 1); -webkit-transition-timing-function: cubic-bezier(0, 0, 0.25, 1); transition-duration: 350ms; -webkit-transition-duration: 350ms; transform: translate3d(0px, 0px, 0px);">
            <li name="default">全部订单</li>
            <li name="0">待付保证金</li>
            <li name="3">待平台备货</li>
            <li name="5">待付货款</li>
            <li name="1">已完成交易</li>
            <li name="2">已取消订单</li>
            <li name="-1">已关闭</li>
        </ul>
        </div>
    </div>
    
    <div class="pross-1" style="display:none;">
        <ul>
            <li>
                <a href="#"><i class="icon-1"></i>
                <p>等付保证金</p></a>
            </li>
            <li>
                <i class="icon-2"></i>
                <p>待平台备货</p>
            </li>
            <li>
                <i class="icon-3"></i>
                <p>待付货款</p>
            </li>
            <li>
                <i class="icon-4"></i>
                <p>已完成交易</p>
            </li>
        </ul>
    </div>

    <div class="a-sell-list1" style="padding-bottom: 0;">
    	<!--无查询结果显示 start-->
    	<#if page.results?size == 0>
    	<div id="no-info">
	    	<div class="delist_box">
			    <div class="void"></div>
			    <div class="words">订单不存在</div>
			</div>
			<div class="delist_red">
			    <div class="words">若有疑问请电询小珍</div>
				<div align="center">
			            <a href="tel://4001054315" name="phone" tel="400">
			            <input type="button" class="knob" value="按我电询小珍" /></a>
			    </div>
				</div>
			</div>
		</div>
		</#if>
    	<!--无查询结果显示 end-->
        <!--活动管理 start-->
        <ul id='main_content'>
        	<#list page.results as order>
            <li><a href="javascript:void(0);" onclick="wopen('${order.orderid}')">
                <p class="tit">
                    <span class="fl col_a9">订单号：${order.orderid}</span>
                    <span class="fr col_red">
                    	<#if order.orderstate=="0">
                    		待付保证金
                    	<#elseif order.orderstate=="1">
                    		已完成
                    	<#elseif order.orderstate=="2">
                    		已取消 
                    	<#elseif order.orderstate=="3">
                    		待平台备货
                    	<#elseif order.orderstate=="5">
                    		待付货款
                    	<#elseif order.orderstate=="-1">
                    		已关闭
                    	</#if>
                    </span>
                </p>
                <h3 class="title3">${order.title}</h3>
                <span class="img relative" datatype="seePic">
					<#if order.path==''>
						<img src="${RESOURCE_IMG_WX}/images/jzt-user-center/purchase.jpg">
					<#else>
						<#assign img = order.path?substring((order.path?last_index_of("/")+1),(order.path?last_index_of(".")))+"_120.jpg"> 
						<#assign tempurl = order.path?substring(0,order.path?last_index_of("/")+1)> 
						<img src="${RESOURCE_IMG_UPLOAD_WX}/${tempurl}${img}"/>
					</#if>
	           	<i onclick="clickImg('${order.wlid}');"></i></span>
                <div class="cont">
                    <p><b>单价:</b><@tools.money num=order.unitprice format="0.##"/>元/${(order.wlunit)!''}<br>
                        <b>数量:</b><@tools.money num=order.amount format="0.##"/>${(order.wlunit)!''}<br>
                        <b>成交数量:</b><@tools.money num=order.volume format="0.##"/>${(order.wlunit)!''}<br>
                        <b>实际付款:</b><@tools.money num=order.totalprice format="0.##"/>元<br>
                        <b>摘牌日期:</b><#if order.updatetime??>${order.updatetime?string('yyyy/MM/dd')}</#if></p>
                </div>
                </a>
                	<#if order.orderstate=="0">
                		<p class="bottom" align="right">
                			<a href="#" class="link-sty" name="pay" id="${order.orderid!''}_pay" createtime="${order.createtime?string("yyyy/MM/dd hh:mm:ss")!''}">支付保证金</a>
                		</p>
                    <#elseif order.orderstate=="1">
                    	<#if order.appealState==1>
                    		<p class="bottom" align="right">
                    			<a href="#" class="arrow" flag="0" name="seeapp" id="${order.orderid!''}_seeapp">查看申诉进度</a>
                    		</p>
                    		<!-- 查看申诉进度 -->
			                <div class="apprepay relative">
			                    <i class="allow-top"></i>
			                    <div>
			                        <div class="bg">
			                            <div id="${order.orderid!''}_step" class="pros">
			                                <span class="step1"></span>
			                            </div>
			                        </div>
			                        <ul>
			                            <li>
			                                <label>取消原因：</label>
			                                <p id="${order.orderid!''}_appealtype"></p>
			                            </li>
			                            <li>
			                                <label>具体描述：</label>
			                                <p id="${order.orderid!''}_reason"></p>
			                            </li>
			                            <li id="${order.orderid!''}_evidenceli" style="height: 8.5em;">
			                                <div class="see-pic-upfile">
			                                    <ul id="${order.orderid!''}_evidencepic">
			                                    </ul>
			                                </div>
			                            </li>
			                            <li style="padding-bottom: 1em; color: #ff0000; clear:both; clear:both;"><a href="tel:4001054315">申诉电话：4001054315</a></li>
			                        </ul>
			                    </div>
			                </div>
			                <!-- 查看申诉进度end -->
                    	</#if>
                    <#elseif order.orderstate=="2">
                    	<#if order.appealState==1>
                    		<p class="bottom" align="right">
                    			<a href="#" class="arrow" flag="0" name="seeapp" id="${order.orderid!''}_seeapp">查看申诉进度</a>
                    		</p>
                    		<!-- 查看申诉进度 -->
			                <div class="apprepay relative">
			                    <i class="allow-top"></i>
			                    <div>
			                        <div class="bg">
			                            <div id="${order.orderid!''}_step" class="pros">
			                                <span class="step1"></span>
			                            </div>
			                        </div>
			                        <ul>
			                            <li>
			                                <label>取消原因：</label>
			                                <p id="${order.orderid!''}_appealtype"></p>
			                            </li>
			
			                            <li>
			                                <label>具体描述：</label>
			                                <p id="${order.orderid!''}_reason"></p>
			                            </li>
			                            <li id="${order.orderid!''}_evidenceli" style="height: 8.5em;">
			                                <div class="see-pic-upfile">
			                                    <ul id="${order.orderid!''}_evidencepic">
			                                    </ul>
			                                </div>
			                            </li>
			                            <li style="padding-bottom: 1em; color: #ff0000; clear:both; clear:both;"><a href="tel:4001054315">申诉电话：4001054315</a></li>
			                        </ul>
			                    </div>
			                </div>
			                <!-- 查看申诉进度end -->
                    	</#if>
                    <#elseif order.orderstate=="3">
                    <#elseif order.orderstate=="4">
                    	<#if order.appealState==1>
                    		<p class="bottom" align="right">
                    			<a href="#" class="arrow" flag="0" name="seeapp" id="${order.orderid}_seeapp">查看申诉进度</a>
                    		</p>
                    		<!-- 查看申诉进度 -->
			                <div class="apprepay relative">
			                    <i class="allow-top"></i>
			                    <div>
			                        <div class="bg">
			                            <div id="${order.orderid!''}_step" class="pros">
			                                <span class="step1"></span>
			                            </div>
			                        </div>
			                        <ul>
			                            <li>
			                                <label>取消原因：</label>
			                                <p id="${order.orderid!''}_appealtype"></p>
			                            </li>
			
			                            <li>
			                                <label>具体描述：</label>
			                                <p id="${order.orderid!''}_reason"></p>
			                            </li>
			                            <li id="${order.orderid!''}_evidenceli" style="height: 8.5em;">
			                                <div class="see-pic-upfile">
			                                    <ul id="${order.orderid!''}_evidencepic">
			                                    </ul>
			                                </div>
			                            </li>
			                            <li style="padding-bottom: 1em; color: #ff0000; clear:both; clear:both;"><a href="tel:4001054315">申诉电话：4001054315</a></li>
			                        </ul>
			                    </div>
			                </div>
			                <!-- 查看申诉进度end -->
                    	</#if>
                    <#elseif order.orderstate=="5">
                    	<p class="bottom" align="right">
                    	<#if order.appealState==0||order.examineState==3>
                    		<a href="#" class="link-sty" name="pay_tail" id="${order.orderid!''}_pay_tail" expiretime="${order.expiretime?string("yyyy/MM/dd hh:mm:ss")!''}">立即付款</a>
                    	</#if>
                    	<#if order.appealState==0>
                    		<a href="#" class="arrow four up" name="edit" id="${order.orderid}_edit">申请取消</a>
                        <#elseif order.appealState==1>
                        	<a href="#" class="arrow" name="seeapp" flag="0" id="${order.orderid}_seeapp">查看申诉进度</a>
                        </#if>
                    	</p>
                    	<#if order.appealState==0>
                    		<!-- 申请取消 -->
			                <div class="apprepay relative apprepay_cancel">
			                    <i class="allow-top"></i>
			                    <div>
			                        <div class="bg">
			                            <div class="pros">
			                                <span class="step1"></span>
			                            </div>
			                        </div>
			                        <ul>
			                            <form action="/order/insertApplyReimburse" method="post" name="${order.orderid}_applyForm" id="${order.orderid}_applyForm">
			                            <input type="hidden" name="orderId" value="${order.orderid!''}">
			                            <li>
			                                <label>取消原因：</label>
			                                <input name="appealType" value="1" datatype="*" type="radio" /> 质量问题
			                                <input type="radio" name="appealType" value="2" datatype="*"/> 其他原因
			                                
			                            </li>
			                            <li style="height: 12em;">
			                                <div>
			                                    <textarea id="subject" name="reason" nullmsg="请输入具体描述！" class="beizhu" maxlength="200" theme="simple" dataType="*1-200" onkeyup="checkLength(this)" accesskey="1" tabindex="11" placeholder="具体原因"></textarea>
			                                    <div class="cl">
			                                        <p id="subjectchk">
			                                            可输入
			                                            <strong id="checklen">200</strong>
			                                            字
			                                        </p>
			                                        <span id="postNameRule" class="spn_flag_1" style="display:none"></span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li style="height: 11.5em;">
			                                <div class="pic-upfile">
			                                    <ul id="${order.orderid}_">
			                                        <li class="file-bg" ><input type="file" name="upload" class="file" id="${order.orderid}_pic"></li>
			                                        <input type="hidden" name="pic1" value="">
			                                        <input type="hidden" name="pic2" value="">
			                                        <input type="hidden" name="pic3" value="">
			                                    </ul>
			                                </div>
			                                <div style="padding: 1.2em 0 0 0.5em; line-height: 1.2em; font-size:0.875em; color: #a9a9a9;">可上传3张证据，证据上传不是必填项，但可能会影响
			                                    申诉结果！（图片大小需小于5M）</div>
			                            </li>
			                            <li style="height: 2.5em;">
			                                <input type="button" id="${order.orderid}_reset" class="a-btn-1 fl" value="重 置" style="padding: 0.3em 1.5em;">
			                                <input type="button" id="${order.orderid}_submit" style="padding: 0.3em 1.5em;" class="a-btn-red fr submitBut" value="提交申请">
			                            </li>
			                            </form>
			                            <li style="margin-top: 0.5em; padding-bottom:0.5em; "><div style="font-size: 0.875em; line-height: 1.5em; color: #cacaca;">注：只有付了保证金后与平台备货完成前可以点击申请退款</div></li>
			                        </ul>
			                    </div>
			                </div>
			                <!-- 申请取消end -->
                        <#elseif order.appealState==1>
                        	<!-- 查看申诉进度 -->
			                <div class="apprepay relative">
			                    <i class="allow-top"></i>
			                    <div>
			                        <div class="bg">
			                            <div id="${order.orderid!''}_step" class="pros">
			                                <span class="step1"></span>
			                            </div>
			                        </div>
			                        <ul>
			                            <li>
			                                <label>取消原因：</label>
			                                <p id="${order.orderid!''}_appealtype"></p>
			                            </li>
			
			                            <li>
			                                <label>具体描述：</label>
			                                <p id="${order.orderid!''}_reason"></p>
			                            </li>
			                            <li id="${order.orderid!''}_evidenceli" style="height: 8.5em;">
			                                <div class="see-pic-upfile">
			                                    <ul id="${order.orderid!''}_evidencepic">
			                                    </ul>
			                                </div>
			                            </li>
			                            <li style="padding-bottom: 1em; color: #ff0000; clear:both; clear:both;"><a href="tel:4001054315">申诉电话：4001054315</a></li>
			                        </ul>
			                    </div>
			                </div>
			                <!-- 查看申诉进度end -->
                        </#if>
                    <#elseif order.orderstate=="-1">
                    	<!-- <p class="bottom" align="right">
                    		<a href="#" class="link-sty" id="${order.orderid!''}_remove" name="remove">删除</a>
                    	</p> -->
                    </#if>
            </li>
            </#list>
        </ul>
        <!--活动管理 end-->
        <#if ismore == 1>
        	<div class="load" id="load"><input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/></div>
        </#if>
    </div>

<!-- 高级查询弹层 -->
<div class="h-demand" id="Check"  style="height: 19.6em; margin-top: -9.8em;">
    <div class="close"></div>
    <form action="/order/order_buyer" method="post" id="search">
    <input type="hidden" value="<#if orderstate=='default'><#else>${orderstate!''}</#if>" name="orderstate">
    <ul class="relative">
        <li><input placeholder="请输入关键字" value="${(busiOrder.title)!''}" name="title" class="input-text1" /></li>
        <li class="bn pa" style="margin-top: 0.5em;"><label>订单金额</label></li>
        <li class="bn"><input class="input-text2" type="text" id="orderStartPrice" onkeyup="clearNoNum(this)" name="orderStartPrice" value="${(busiOrder.orderStartPrice)!''}" /> — <input class="input-text2" type="text" id="orderEndPrice" name="orderEndPrice" value="${(busiOrder.orderEndPrice)!''}" onkeyup="clearNoNum(this)"/></li>
        <li class="bn pa" style="margin-top: 0.5em;"><label>摘牌时间</label></li>
        <li class="bn"><input class="input-text2" name="orderStartDate" placeholder="2015-05-27" id="date_b" value="${(busiOrder.orderStartDate)!''}"/> — <input class="input-text2" id="date_e" name="orderEndDate" value="${(busiOrder.orderEndDate)!''}" placeholder="2015-07-20"></li>
        <li class="bn mt1" style="margin-top: 1.5em;"><input class="btn-red" type="button" id="hCheck" value="查 询" /></li>
    	<span class="ts_error" id="checkMsg" style="display:none;margin-left:0.4em;background:none;">
    </ul>
    </form>
</div>
<!-- 高级查询弹层end -->
<!-- 支付 -->
<div class="h-demand pay" id="Pay">
    <div class="close"></div>
    <div class="intro">
        <p><i class="icon1"></i>您已摘牌，请支付保证金才能锁定药材，
            请及时登录PC端珍药材平台支付保证金。
            <i class="icon2"></i>摘牌后<span class="col-red">72小时</span>仍没缴纳保证金的订单，
            系统会<span class="col-red">自动关闭</span>此订单。
        </p>
    </div>
    <div class="pros">
        <p>您还有 <strong id="day">0</strong> 天 <strong id="hms">00：00：00</strong> 支付保证金</p>
        <div align="center">
            <div class="pros-box">如须帮助请
            <a href="tel:4001054315">电询小珍</a></div>
        </div>
    </div>
</div>
<!-- 支付end -->
<!-- 支付尾款 -->
<div class="h-demand pay" id="Pay_tail">
    <div class="close"></div>
    <div class="intro">
        <p><i class="icon1"></i>您的药材已备齐，请及时登录PC端珍药材平台支付尾款，
            <i class="icon2"></i>药材备齐后<span class="col-red">${deposit_delay!''}天</span>仍没支付货款，
            系统会<span class="col-red">自动取消</span>此订单。
        </p>
    </div>
    <div class="pros">
        <p>您还有 <strong id="day1">0</strong> 天 <strong id="hms1">00：00：00</strong> 支付尾款</p>
        <div align="center">
            <div class="pros-box">如须帮助请
            <a href="tel:4001054315">电询小珍</a></div>
        </div>
    </div>
</div>
<!-- 支付尾款end -->
<!-- 查看图片  -->
<div class="seebox">
    <div class="bigpic" id="firstpic"></div>
    <div class="smallpic">
        <ul id="pics" style="transition-property: transform; -webkit-transition-property: transform; transition-timing-function: cubic-bezier(0, 0, 0.25, 1); -webkit-transition-timing-function: cubic-bezier(0, 0, 0.25, 1); transition-duration: 350ms; -webkit-transition-duration: 350ms; transform: translate3d(0px, 0px, 0px);">
            <!--li class="cur"><img src="${RESOURCE_IMG_WX}/images/pic-1.jpg" rel="images/ad1.png"></li-->
        </ul>
    </div>
</div>
<div class="see-close"><img src="${RESOURCE_IMG_WX}/images/seeClose.png"> </div>
<!-- 查看图片 over  -->
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }

    $(function(){
    	//初始化
    	var orderstate = "${orderstate!''}";
    	if(!orderstate){
    		orderstate = 'default';
    	}
    	if(orderstate=='default'||orderstate=='-1'||orderstate=='2'){
    	}else{
    		$('.pross-1').attr("style","");
    		$(".a-sell-list1").css('padding-top','13.8em');
    		var index = $('li[name=${orderstate!""}]').index();
    		$('.pross-1 li i:lt('+index+')').addClass("on");
    		if(index>=1){
    			index--;
    			$('.pross-1 li i:lt('+index+')').addClass("con");
    		}
    	}
    	$(".main_title li:not(li[name=${orderstate!''}])").click(function(){
    		var state = $(this).attr('name');
    		if(state=='default'){
    			location.replace("/order/order_buyer");
    		}else{
    			location.replace("/order/order_buyer?orderstate=" + state);
    		}
    		//location.replace("/order/order_buyer?orderstate=" + (state=='default'?'':state));
    	});
    	$("li[name='"+orderstate+"']").addClass('hover').siblings().removeClass('hover');
    	
    	//日期控件
	    $('#date_b').click(function(){
	        WdatePicker({
	            maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
	            readOnly:true
	        });
	    });
	    $('#date_e').click(function(){
	        WdatePicker({
	            minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
	            readOnly:true
	        });
	    });
    
        //关闭层
        function close(els){
            els.on('click',function(){
                $('.h-demand').hide();
                $('.bghui').remove();
            })
        }
        close($('.close'));

        //文本框初始值清空
        function Focus(els,text){
            els.focus(function(){
                if($(this).val() == text){
                    $(this).val('');
                }
            }).blur(function(){
                if($(this).val() == ''){
                    $(this).val(text);
                }
            })
        }
        
        //显示层
        $('#searchM').on('click',function(){
            $('#Check').show();
            bgHiu();
        });
        
        //删除
        /*
        $('a[name=remove]').on('click',function(){
        	var curid = $(this).attr('id').substr(0,16);
            var Remove = '<div class="aRemove"><p>确定删除此条信息</p><div><input type="button" class="qx" value="取消" /><input type="button" id="'+curid+'_qd" class="qd" value="确定" /></div></div>'
            $('body').append(Remove);
            bgHiu();
        });
        $('body').delegate('.aRemove input[class=qx]','click',function(){
            $('.bghui').remove();
            $('.aRemove').remove();
        });
        $('body').delegate('.aRemove input[class=qd]','click',function(){
        	var curid = $(this).attr('id').substr(0,16);
            $('.bghui').remove();
            $('.aRemove').remove();
        });
        */

        
        
        var fontsize = $("body").css("font-size").substr(0,2);
    	var liIndex = new Array(0,4.5,5.5,5.5,4.5,5.5,5.5,3.5);
    	//标题左右滑动效果
        var flipsnapWidth = 41 * fontsize;
        var windowWidth = document.documentElement.clientWidth;
        var index = $('li[name=${orderstate!""}]').index();
        var currentPostion = 0;
        for(i=0;i <= index;i++){
        	currentPostion += liIndex[i];
        }
        currentPostion = currentPostion*fontsize;
        
        if(flipsnapWidth >= windowWidth){
        	var dist = flipsnapWidth - windowWidth;
        	mp = 3;
        	dist = dist/3;
        	
        	/*
	        Flipsnap('.oBuyer-sider ul',{
	            distance:dist,
	            maxPoint:mp
	        });
			//初始化化定位
			var leftCurrentPostion = flipsnapWidth-currentPostion;
			if(leftCurrentPostion>=windowWidth){
				$(".main_title").css("transform","translate3d(-"+currentPostion+"px, 0px, 0px)");
			}else{
				leftCurrentPostion = flipsnapWidth-windowWidth;
				$(".main_title").css("transform","translate3d(-"+leftCurrentPostion+"px, 0px, 0px)");
			}
			*/
			
			//修改滑动  by 2015.9.25 aizhengdong
			var flipsnap = Flipsnap('.oBuyer-sider ul',{
	            distance:dist,
	            maxPoint:mp
	        });
			flipsnap.moveToPoint(index); 
        }else{
        	Flipsnap('.oBuyer-sider ul',{
	            distance:0,
	            maxPoint:0
	        });
        }
        
        //查看申诉进度
        $('body').delegate('a[name=seeapp]','click',function(){
        	var thisid = $(this).attr('id');
        	var curid = thisid.substr(0,16);
        	if($(this).attr('flag')=='0'){
        		$.ajax({
					async : true,
					cache : false,
					type : 'GET',
					dataType : 'json',
					url : '/order/getBuyerOrderAppeal?orderId='+curid,
					success : function(data) {
						if(data.examinestate==1){
							$('#'+curid+'_step').attr('class','pros1')
							$('#'+curid+'_step span').attr('class','step1');
						}
						if(data.examinestate==2){
							$('#'+curid+'_step').attr('class','pros1')
							$('#'+curid+'_step span').attr('class','step2');
						}
						if(data.examinestate==3){
							$('#'+curid+'_step').attr('class','pros2')
							$('#'+curid+'_step span').attr('class','step3');
						}
						$('#'+curid+'_appealtype').text(data.appealtype);
						$('#'+curid+'_reason').text(data.reason);
						if(data.evidencepics){
							var imgs = '';
							for(var i=0;i<data.evidencepics.length;i++){
	                   			var pic = data.evidencepics[i];
	                   			var  minImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_120'+pic.substring(pic.lastIndexOf("."),pic.length);
			  				 	var  tempurl = pic.substring(0,pic.lastIndexOf("/")+1);
			  				 	var  maxImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_640'+pic.substring(pic.lastIndexOf("."),pic.length);
	                   		 	imgs+='<li><img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+minImg+'" rel="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+maxImg+'"/><i></i></li>';
	                   		}
							$('#'+curid+'_evidencepic').append(imgs);
							$('#'+curid+'_evidenceli').show();
						}else{
							$('#'+curid+'_evidenceli').hide();
						}
						$("#"+thisid).attr('flag','1');
					}
				});
        	}
            $(this).toggleClass('up');
            $(this).parent('p').next().toggleClass('db');
            if($(this).parent('p').next('.apprepay').css('display') == 'block'){
                $(this).parents('li').css('background','#ffffff');
            }
            return false;
        });

        //申请取消提交   绑定事件
        $('body').delegate('a[name=edit]','click',function(){
            $(this).toggleClass('up');
            $(this).parent('p').next().toggleClass('db');
            if($(this).parent('p').next('.apprepay').css('display') == 'block'){
                $(this).parents('li').css('background','#ffffff');
            }
            var thisid = $(this).attr('id');
            var curid = thisid.substr(0,17);
            validSubmit(curid);
            return false;
        });
    });
    function remove(){
        $('.alert').remove();
        setTimeout('remove()', 3000);
    }
    remove();
    //点击查看图片显示大图
    $(function(){
        $('.see-close').on('click',function(){
            $('.seebox').hide();
            $(this).hide();
            $('.bghui').remove();
        });
        //textarea
        $('#subject').focus(function(){
            if($(this).text() == '具体原因'){
                $(this).text('');
            }
        }).blur(function(){
            if($(this).text() == ''){
                $(this).text('具体原因');
            }
        });
    });

    //touch事件替换CLICK事件
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').touchStart(function () {
        $(this).addClass('hover');
    });
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').touchMove(function () {
        $(this).addClass('hover');
    });
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').touchEnd(function () {
        $(this).removeClass('hover');
    });
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').tapOrClick(function () {
        $(this).removeClass('hover');
    });

</script>
<script>
    // 只要键盘一抬起就验证编辑框中的文字长度，最大字符长度可以根据需要设定
    function checkLength(obj)  {
        var maxChars = 200;//最多字符数
        var curr = maxChars - obj.value.length;
        if( curr > 0 ){
            document.getElementById("checklen").innerHTML = curr.toString();
        }else{
            document.getElementById("checklen").innerHTML = '0';
            document.getElementById("subject").readOnly=true;
        }
    }
</script>
<script>
$(function(){
	//申请取消图片上传
	$('ul').delegate('.file','change',function(){
		var number = $(this).parent().parent().find('img').size();
		var thisid = $(this).attr('id');//当前id
		var pic = $(this).val();
		if(number<3){
			$("#"+thisid).parent().before('<li><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"><i></i></li>');
		}else{
			return;
		}
		if(!/.(gif|jpg|jpeg|png|bmp)$/.test(pic.toLowerCase())){
			alert("请选择图片!");
			return false;
		}
		$.ajaxFileUpload(
			{
				url:'/order/uploadpic',
				secureuri:false,
				fileElementId:thisid,
				dataType: 'json',
				type: 'POST',
				success: function (data, status)
				{
					if(data.status.code==0){
						var imgSrc = data.con.path+data.con.dateDir+"/"+data.con.filename;
						$("#"+thisid).parent().parent().find('img:last').attr('src',imgSrc);
						if($("#"+thisid).parent().parent().find('img').size()>=3){
							$("#"+thisid).attr('disabled','disabled');
						}
					}else{
						alert("上传失败！");
						$("#"+thisid).parent().parent().find('img:last').parent().remove();
						if($("#"+thisid).parent().parent().find('img').size()<3){
							$("#"+thisid).romoveAttr('disabled');
						}
						return false;
					}
				},
				error: function (data, status, e)
				{
					alert('操作失败！');
					$("#"+thisid).parent().parent().find('img:last').parent().remove();
						if($("#"+thisid).parent().parent().find('img').size()<3){
							$("#"+thisid).romoveAttr('disabled');
					}
					if($("#"+thisid).parent().parent().find('img').size()>=3){
						$("#"+thisid).attr('disabled','disabled');
					}
				}
			}
		);
	});
	
	$('.pic-upfile').delegate('i','click',function(){
		var curOrderid = $(this).parent().parent().attr('id');
		if($(this).parent().find('img').attr('src') != ''){
			$(this).parent().remove();
			$("#"+curOrderid+"pic").removeAttr('disabled');
		}
	});
	
	//查看更多
	$('#load').on('click',function(){
		$.ajax({
			url:"/order/order_buyer_info?pageNo="+$('#pageNo').val(),
			type: "POST",
			data:$('#search').serialize(),
			dataType:'JSON',
			success: function(data){
				if(data.ismore == 1){
					//设置页数
					$('#pageNo').val(data.page.pageNo);
				}else{
					//设置页数
					$('#pageNo').val(data.page.pageNo);
					$('#load').css('display','none');
				}
				
				//填充元素
				for(var o in data.page.results){
					var result = data.page.results[o];
					
					var html = '';
					html += '<li><a href="${RESOURCE_WWW_WX}/order/getBuyOrderDetail?orderId='
							+ result.orderid +
							'"><p class="tit"><span class="fl col_a9">订单号：'
							+ result.orderid +
							'</span><span class="fr col_red">';
					
					switch(result.orderstate){
						case 0: html += '待付保证金';break;
						case 1: html += '已完成';break;
						case 2: html += '已取消';break;
						case 3: html += '待平台备货';break;
						case 5: html += '待付货款';break;
						case -1: html += '已关闭';break;
					}
					html += '</span></p><h3 class="title3">'
							+ result.title +
							'</h3><span class="img relative" datatype="seePic">';
					if(result.path){
						 var  minImg = result.path.substring((result.path.lastIndexOf("/")+1),(result.path.lastIndexOf(".")))+'_120'+result.path.substring(result.path.lastIndexOf("."),result.path.length);
			  			 var  tempurl = result.path.substring(0,result.path.lastIndexOf("/")+1);
			             html+='<img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+minImg+'"/>';
					}else{
						html += '<img src="${RESOURCE_IMG_WX}/images/jzt-user-center/purchase.jpg">'
					}
					html += '<i onclick="clickImg(\''+ result.wlid +'\');"></i></span><div class="cont"><p><b>单价:</b>'
								+ result.unitprice +
							'元/'
								+ result.wlunit +
							'<br><b>数量:</b>'
								+ result.amount + result.wlunit +
							'<br><b>成交数量:</b>'
								+result.volume + result.wlunit +
							'<br><b>实际付款:</b>'
								+ result.totalprice +
							'元<br><b>摘牌日期:</b>'
								+ result.updatetime.substr(0,10) +
							'</p></div></a>';
					switch(result.orderstate){
						case 0:
							html += '<p class="bottom" align="right"><a href="#" class="link-sty" name="pay" id="'
								+ result.orderid +
							'_pay" createtime="'
								+ result.createtime +
							'">支付保证金</a></p>';
							break;
						case 1:
							if(result.appealState==1){
								html += '<p class="bottom" align="right"><a href="#" class="arrow" flag="0" name="seeapp" id="'
									+result.orderid+
								'_seeapp">查看申诉进度</a></p><div class="apprepay relative arrow"><i class="allow-top"></i><div><div class="bg"><div id="'
									+result.orderid+
								'_step" class="pros"><span class="step1"></span></div></div><ul><li><label>取消原因：</label><p id="' 
									+ result.orderid +
								'_appealtype"></p></li><li><label>具体描述：</label><p id="'
									+ result.orderid +
								'_reason"></p></li><li id="'
									+ result.orderid +
								'_evidenceli" style="height: 8.5em;"><div class="see-pic-upfile"><ul id="'
									+ result.orderid +
								'_evidencepic"></ul></div></li><li style="padding-bottom: 1em; color: #ff0000; clear:both; clear:both;"><a href="tel:4001054315">申诉电话：4001054315</a></li></ul></div></div>';
							}
							break;
						case 2: 
							if(result.appealState==1){
								html += '<p class="bottom" align="right"><a href="#" class="arrow" flag="0" name="seeapp" id="'
									+result.orderid+
								'_seeapp">查看申诉进度</a></p>';
								html += '<div class="apprepay relative"><i class="allow-top"></i><div><div class="bg"><div id="'
									+result.orderid+
								'_step" class="pros"><span class="step1"></span></div></div><ul><li><label>取消原因：</label><p id="' 
									+ result.orderid +
								'_appealtype"></p></li><li><label>具体描述：</label><p id="'
									+ result.orderid +
								'_reason"></p></li><li id="'
									+ result.orderid +
								'_evidenceli" style="height: 8.5em;"><div class="see-pic-upfile"><ul id="'
									+ result.orderid +
								'_evidencepic"></ul></div></li><li style="padding-bottom: 1em; color: #ff0000; clear:both; clear:both;">申诉电话：4001054315</li></ul></div></div>';
							}
							break;
						case 4:
							if(result.appealState==1){
								html += '<p class="bottom" align="right"><a href="#" class="arrow" flag="0" name="seeapp" id="'
									+result.orderid+
								'_seeapp">查看申诉进度</a></p>';
								html += '<div class="apprepay relative arrow"><i class="allow-top"></i><div><div class="bg"><div id="'
									+result.orderid+
								'_step" class="pros"><span class="step1"></span></div></div><ul><li><label>取消原因：</label><p id="' 
									+ result.orderid +
								'_appealtype"></p></li><li><label>具体描述：</label><p id="'
									+ result.orderid +
								'_reason"></p></li><li id="'
									+ result.orderid +
								'_evidenceli" style="height: 8.5em;"><div class="see-pic-upfile"><ul id="'
									+ result.orderid +
								'_evidencepic"></ul></div></li><li style="padding-bottom: 1em; color: #ff0000; clear:both;">申诉电话：4001054315</li></ul></div></div>';
							}
							break;
						case 5: 
							html+='<p class="bottom" align="right">';
							if(result.appealState==0||result.examineState==3){
								html+='<a href="#" class="link-sty" name="pay_tail" id="'
									+ result.orderid +
								'_pay_tail" expiretime="'
									+ result.expiretime +
								'">立即付款</a>';
							}
							if(result.appealState==0){
								html+='<a href="#" class="arrow four" name="edit" id="'
									+result.orderid+
								'_edit">申请取消</a>';
							}else if(result.appealState==1){
								html+='<a href="#" class="arrow" flag="0" name="seeapp" id="'
									+result.orderid+
								'_seeapp">查看申诉进度</a>';
							}
							html+='</p>';
							if(result.appealState==0){
								html+='<div class="apprepay relative apprepay_cancel"><i class="allow-top"></i><div><div class="bg"><div class="pros"><span class="step1"></span></div></div><ul><form action="/order/insertApplyReimburse" method="post" name="'
										+result.orderid+
									'_applyForm" id="'
										+result.orderid+
									'_applyForm"><input type="hidden" name="orderId" value="'
										+result.orderid+
									'"><li><label>取消原因：</label><input name="appealType" value="1" datatype="*" nullmsg="请选择单位性质！"  type="radio" /> 质量问题    <input type="radio" name="appealType" value="2" datatype="*" nullmsg="请选择单位性质！" /> 其他原因</li><li style="height: 12em;"><div><textarea id="subject" name="reason" nullmsg="请输入具体描述！" class="beizhu" size="80" maxlength="200" theme="simple" dataType="*1-200" onkeyup="checkLength(this)" accesskey="1" tabindex="11" placeholder="具体原因"></textarea><div class="cl"><p id="subjectchk">可输入<strong id="checklen">200</strong>字</p><span id="postNameRule" class="spn_flag_1" style="display:none"></span></div></div></li><li style="height: 11.5em;"><div class="pic-upfile"><ul id="'
										+result.orderid+
									'_"><li><img src=""><i></i></li><li class="file-bg"><input type="file" class="file" name="upload" id="'
										+result.orderid+
									'_pic"></li><input type="hidden" name="pic1" value=""><input type="hidden" name="pic2" value=""><input type="hidden" name="pic3" value=""></ul></div><div style="padding: 1.2em 0 0 0.5em; line-height: 1.2em; font-size:0.875em; color: #a9a9a9;">可上传3张证据，证据上传不是必填项，但可能会影响申诉结果！（图片大小需小于5M）</div></li><li style="height: 2.5em;"><input type="button" id="'
										+result.orderid+
									'_reset" class="a-btn-1 fl" value="重 置" style="padding: 0.3em 1.5em;"><input type="button" id="'
										+result.orderid+
									'_submit" style="padding: 0.3em 1.5em;" class="a-btn-red fr submitBut" value="提交申请"></li></form><li style="margin-top: 0.5em; padding-bottom:0.5em; "><div style="font-size: 0.875em; line-height: 1.5em; color: #cacaca;">注：只有付了保证金后与平台备货完成前可以点击申请退款</div></li></ul></div></div>';
							}else if(result.appealState==1){
								html += '<div class="apprepay relative"><i class="allow-top"></i><div><div class="bg"><div id="'
									+result.orderid+
								'_step" class="pros"><span class="step1"></span></div></div><ul><li><label>取消原因：</label><p id="' 
									+ result.orderid +
								'_appealtype"></p></li><li><label>具体描述：</label><p id="'
									+ result.orderid +
								'_reason"></p></li><li id="'
									+ result.orderid +
								'_evidenceli" style="height: 8.5em;"><div class="see-pic-upfile"><ul id="'
									+ result.orderid +
								'_evidencepic"></ul></div></li><li style="padding-bottom: 1em; color: #ff0000; clear:both;">申诉电话：4001054315</li></ul></div></div>';
							}
							break;
						//case -1: 
							//html+='<p class="bottom" align="right"><a href="#" class="link-sty" name="del">删除</a></p>';break;
					}
					html += '</li>';
					$('#main_content').append(html);
				}
			},
			error: function(){
				
			}
		});
	});
});

	//申请取消提交公共
	function validSubmit(formName){
			var form = "#" + formName + "applyForm";
			var validForm = $(form).Validform({
		    	btnSubmit:"#" + formName + "submit",
		    	btnReset:"#" + formName + "reset",
		    	beforeSubmit:function(){
		    		//把图片路径放到pic下
			    	var imgs = $("#"+formName).find('img');
			    	if(imgs.size()>0){
			    		for(var i=0;i<imgs.size();i++){
			    			var j = i+1;
			    			$("#"+formName).find('input[name="pic'+j+'"]').val(imgs[i].src);
			    		}
			    	}
		    	},
		    	tiptype:function(msg,o,cssctl){
					//msg：提示信息;
					//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
					//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
					if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
						var objtip=o.obj;
						if(o.type==2){
							$(objtip).removeClass('red');
						}else if(o.type==3){
							if(objtip.attr('name')=='reason'){
								$(objtip).val('');
							}
							$(objtip).addClass('red');
						}
						$(objtip).attr('placeholder',msg);
					}
				},
		    	showAllError:true,
		        ajaxPost:true,
		        callback:function(res){
		        	if(res.responseText=='y'){
		        		//提示
						var Alert = '<div class="alert">提交成功！<br/>请等待小珍回复哦！</div>';
            			$('body').append(Alert);
            			$(this).parents('.h-demand').hide();
            			$('.bghui').remove();
            			//刷新
            			$('#search').submit();
					}else{
						//提示
						var Alert = '<div class="alert">'+res.responseText+'</div>';
            			$('body').append(Alert);
            			$(this).parents('.h-demand').hide();
            			$('.bghui').remove();
					}
				}
			});
			return validForm;
		}
	
	//点击小图看大图
	function clickImg(wlid){
			$('#pics').html('');
			$.ajax({
				async : true,
				cache : false,
				type : 'GET',
				dataType : 'json',
				url : '/order/getWlidPic/'+wlid,
				success : function(data) {
					if(data!='[]'){
						var pic = data[0].path;
						if(pic!=null && pic !=''){
							var  maxImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_640'+pic.substring(pic.lastIndexOf("."),pic.length);
			  				var  tempurl = pic.substring(0,pic.lastIndexOf("/")+1);
			                $("#firstpic").html('<img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+maxImg+'"/>');
						}
						$.each(data,function(i,qualiy){
							 var pic = qualiy.path;
							 var  maxImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_640'+pic.substring(pic.lastIndexOf("."),pic.length);
			  				 var  minImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_120'+pic.substring(pic.lastIndexOf("."),pic.length);
			  				 var  tempurl = pic.substring(0,pic.lastIndexOf("/")+1);
			  				 var str='';
			  				 str+='<li ';
			  				 if(i==0){
			  				 	 str+='class="cur"';
			  				 }
			  				 str+='><img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+minImg+'" rel="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+maxImg+'"></li>';
						 	 $('#pics').append(str);
						});
						//图片加载完毕后进行以下操作
						$('.seebox').show();
			            $('.see-close').show();
			            bgHiu();
				        //动态绑定关闭按钮    
				        $('.see-close').on('click',function(){
				            $('.seebox').hide();
				            $(this).hide();
				            $('.bghui').remove();
				        });
				        $('.smallpic ul li').on('click',function(){
				            $(this).addClass('cur').siblings().removeClass('cur');
				            $('.bigpic img').attr('src',$(this).children('img').attr('rel'));
				        });
				        //小图拖动
				        var wid = $('.seebox .smallpic ul li img').width();			        
				        Flipsnap('.smallpic ul',{
				            distance:wid,
				            maxPoint:(data.length-3)
				        });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#more').removeClass('loading');
					layerMsg('网络超时，请重试！');
				}
			});
		//阻止向上冒泡
		var event = window.event || arguments.callee.caller.arguments[0];
		event.cancelBubble = true;
	}
	
	function wopen(orderid){
		window.location.href="/order/getBuyOrderDetail?orderId="+orderid;
	}
</script>
<script>
	//文本框只能输入数字，且小数位数最多为2位
    function clearNoNum(obj){
	  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    };

	var s=1000;
	var m=60*1000;
	var h=60*60*1000;
	var d=24*60*60*1000;
	var afterdays = ${afterdays!''};

	$("body").delegate("a[name=pay]","click",function(){
		var createtime = $(this).attr('createtime');
		var start = new Date(createtime);
		var deadline = start.getTime()+afterdays*d;
		getDeadline(deadline);
		$('#Pay').show();
		bgHiu();
	});
	
	$("body").delegate("a[name=pay_tail]","click",function(){
		var expiretime = $(this).attr('expiretime');
		var deadline = new Date(expiretime);
		getDeadline1(deadline);
		$('#Pay_tail').show();
		bgHiu();
	});
	
	var timer;
	function getDeadline(endtime){
		var day,hour,min,sec;
		var cur = new Date().getTime();
		var dur = endtime - cur;
		if(dur<=0){
			day=0;
			hour=0;
			min=0;
			sec=0;
		}else{
			day=parseInt(dur/d);
			dur=dur%d;
			hour=parseInt(dur/h);
			if(hour<10){
				hour = '0'+hour;
			}
			dur=dur%h;
			min=parseInt(dur/m);
			if(min<10){
				min = '0'+min;
			}
			dur=dur%m;
			sec=parseInt(dur/s);
			if(sec<10){
				sec = '0'+sec;
			}
		}
		$("#day").html(day);
		$("#hms").html(hour+":"+min+":"+sec);
		if(timer==null){
			timer = setInterval(function(){
				getDeadline(endtime)
			}, 1000);
		}
		if(day=='0'&&hour=='0'&&min=='0'&&sec=='0'){
			clearInterval(timer);
		}
	}
	
	var timer1;
	function getDeadline1(endtime){
		var day,hour,min,sec;
		var cur = new Date().getTime();
		var dur = endtime - cur;
		if(dur<=0){
			day=0;
			hour=0;
			min=0;
			sec=0;
		}else{
			day=parseInt(dur/d);
			dur=dur%d;
			hour=parseInt(dur/h);
			if(hour<10){
				hour = '0'+hour;
			}
			dur=dur%h;
			min=parseInt(dur/m);
			if(min<10){
				min = '0'+min;
			}
			dur=dur%m;
			sec=parseInt(dur/s);
			if(sec<10){
				sec = '0'+sec;
			}
		}
		$("#day1").html(day);
		$("#hms1").html(hour+":"+min+":"+sec);
		if(timer1==null){
			timer1 = setInterval(function(){
				getDeadline1(endtime)
			}, 1000);
		}
		if(day=='0'&&hour=='0'&&min=='0'&&sec=='0'){
			clearInterval(timer1);
		}
	}
</script>
<script>
	$(function(){
		$(".back").click(function(){
			location.replace("/order/myorder");
		});
		
		$("#hCheck").click(function(){
	     	var start= $("#orderStartPrice").val();
	    	var end= $("#orderEndPrice").val();
	    	if(start>end){
	    	//if((start)||(end)||start>end){
	    		$("#checkMsg").text("最低价格不能高于最高价格！");
	    		$("#checkMsg").show();
	    		return;
	    	}
	    	$("#search").submit();
    	})
	})
</script>
</body>
</html>