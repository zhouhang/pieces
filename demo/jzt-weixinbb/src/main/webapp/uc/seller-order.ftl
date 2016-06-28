<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的订单-卖家订单</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <style>
	    .loading{
	    	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
	    }
	    ::-webkit-input-placeholder { /* WebKit browsers */
	        color:    #cacaca;
	    }
	    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	        color:    #cacaca;
	    }
	    ::-moz-placeholder { /* Mozilla Firefox 19+ */
	        color:    #cacaca;
	    }
	    :-ms-input-placeholder { /* Internet Explorer 10+ */
	        color:    #cacaca;
	    }
        body{background:#f5f5f5;}
	</style>
</head>
<body>
<div class="sell-box-head">
    <a href="/order/myorder"><i class="back"></i></a>
        <span class="fr" id="searchM" style="display: block;">
            <a href="#2" name="check"></a>
        </span>
</div>
    <div class="a-sell-list1" style="padding-top:3em;padding-bottom:0em;">
        <ul id="sellorders">
        </ul>
        <div id="more" class="load loading"></div>
    </div>
<!-- 高级查询弹层 -->
<div class="h-demand" id="Check"  style="height: 18.3em; margin-top: -9.8em;">
    <div class="close"></div>
    <form id="busiOrder" name="busiOrder">
	    <ul class="relative">
	        <li><input placeholder="请输入订单编号"  onfocus="vfocus(this);" type="text" id="orderid" name="orderid"  class="input-text1" /></li>
	        <li><input placeholder="请输入品种名称"  type="text" id="breedName" name="breedName"  class="input-text1" /></li>
	        <li class="relative" id="state" datatype="unit">
	            <label class="col-ca">状态</label><i class="allow-ri"></i>
	                <span id="selectflag" class="unit sty1">
	                    <a datatype="" href="javascript:void(0);">全部</a>
	                    <#if busiOrderStateMap??>
		            		<#list busiOrderStateMap?keys as key>
		            			<a dataval="${key}" href="javascript:void(0);">${busiOrderStateMap[key]}</a>
		            		</#list>
	            		</#if>
	                </span>
	                <input type="hidden" id="orderstate" name="orderstate"/>
	        </li>
	        <li class="bn pa" style="margin-top: 0.5em;"><label>摘牌时间</label></li>
	        <li class="bn"><input name="orderStartDate" placeholder="开始时间" type="text" class="input-text2" readonly="readonly" id="wdate1" /> 至  <input type="text" placeholder="结束时间" name="orderEndDate"  class="input-text2" readonly="readonly" id="wdate2"/></li>
	        <li class="bn mt1" style="margin-top: 1.5em;"><input class="btn-red" type="button" id="hCheck" value="查 询" /></li>
	    </ul>
	 </form>   
</div>
<!-- 高级查询弹层end -->
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
<!--质检报告弹层-->
<div class="h-demand report-box" id="Report">
    <div class="close"></div>
    <div class="pic" id="reportPop"></div>
</div>
<!-- 查看图片 over  -->
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
<script type="text/javascript">
	function vfocus(o){
		var viewH =document.body.clientHeight;      //可见高度  
        var tempH = viewH/2;                        //屏幕可见高度的一半
        var top = $("#Check").offset().top; 
        var top1 = $("#Check").position().top;
        var h =$("#Check").height(); //弹层高度
        //如果弹层显示所有查询条件则按原位置显示，如果只显示品种名称的弹层则按屏幕高度一半显示
        if(h > 150){ 
            if((top1-150) > 0){
        		$("#Check").css("top",top1);
        	}else{
        		$("#Check").css("top",'50%');
        	}
		}else{
			$("#Check").css("top",tempH);
		}
	}
	
	//当前页数
	var pageno = 1;
	//查看更多
	$('#more').click(function(){
		if($('more').hasClass('loading')){
			return false;
		}
		$('#more').addClass('loading');
		loadSellOrders();
	})
	
	//下拉框span加上事件，点击它周围地方层消失
	$(document).on("click",function(e){
		var target = $(e.target);
		if(target.closest("#selectflag").length == 0 && target.closest("#state").length == 0){
			$("#selectflag").hide();
		}
	}) 
	
	//高级查询
	$('#hCheck').click(function(){
		pageno = 1;
		$("#sellorders").html('');
		loadSellOrders();
	})
	
	//获取卖家订单列表
    function loadSellOrders(){
    	var orderid= $("#orderid").val();
    	if(orderid!=''){
    		//去前后空格
    		$("#orderid").val($("#orderid").val().trim());
    	}
    	var params ="pageNo="+pageno+"&"+$("#busiOrder").serialize();
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			data : params,
			dataType : 'json',
			url : '/order/getsellorders',
			success : function(data) {
				var mylists = data.results;
				var mylistsLength = mylists.length;
				if(mylistsLength > 0){
					$.each(mylists,function(index,busiOrder){
						 var flag='';
						 switch(busiOrder.orderstate){
						 	case -1 : flag='已关闭';break;
						 	case 0 : flag='买家已下单';break;
							case 1 : flag='交易已完成';break;
							case 2 : flag='买家已取消';break;
							case 3 : flag='已付保证金';break;
							case 4 : flag='买家已付款';break;
							case 5 : flag='已备货';break;
							default:break;
						 }
					 	 var orders_str='<li><a href="javascript:void(0);" onclick="wopen(\''+busiOrder.orderid+'\')">';	
					 	 orders_str+='<p class="tit" style="padding-left:0em;padding-right:0em;">';
                    	 orders_str+='<span class="fl col_a9">订单编号:'+busiOrder.orderid+' </span>';
                     	 orders_str+='<span class="fr col_red">'+flag+'</span></p>';
                		 orders_str+='<h3 class="title3">'+busiOrder.title+'</h3><span class="img relative" datatype="seePic">';
                		 var _pic =busiOrder.path;
		                 if(_pic!=null && _pic!=''){
			                 var  minImg = busiOrder.path.substring((busiOrder.path.lastIndexOf("/")+1),(busiOrder.path.lastIndexOf(".")))+'_120'+busiOrder.path.substring(busiOrder.path.lastIndexOf("."),busiOrder.path.length);
			  				 var  tempurl = busiOrder.path.substring(0,busiOrder.path.lastIndexOf("/")+1);
			                 orders_str+='<img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+minImg+'"/>';
			             }else{
			                 orders_str+='<img src=""/>';
			             }
                 		 orders_str+='<i onclick="clickImg(\''+busiOrder.wlid+'\');"></i></span><div class="cont" style="margin-top: 0px;">';
						 orders_str+='<p style="margin-left:-0.2em;"><b>单价：</b>'+busiOrder.unitprice+'元/'+busiOrder.wlunit+'<br>';
                         orders_str+='<b>订单数量：</b>'+busiOrder.amount+busiOrder.wlunit+'<br>';
                         orders_str+='<b>成交数量：</b>'+busiOrder.volume+busiOrder.wlunit+'<br>';
                         orders_str+='<b>订单总价：</b>'+busiOrder.totalprice+'元<br>';
                         var wlrkDate = busiOrder.createtime;
						 var wlrkDateYMD = '';
						 if(wlrkDate!=null){
							wlrkDate = new Date(wlrkDate);
							var wlrkDateMonth = wlrkDate.getMonth()+1;
							var wlrkDateDay = wlrkDate.getDate();
							if(wlrkDateMonth<10){
								wlrkDateMonth = "0"+wlrkDateMonth;
							}
							if(wlrkDateDay<10){
								wlrkDateDay = "0"+wlrkDateDay;
							}
							wlrkDateYMD = wlrkDate.getFullYear()+'/'+wlrkDateMonth+'/'+wlrkDateDay;
						 }
                         orders_str+='<b>摘牌日期：</b>'+wlrkDateYMD+'</p>';
                		 orders_str+='</div></a>';
                		 if(busiOrder.orderstate==2){
		              	  	orders_str+='<p class="bottom" style="margin-top:1em;" align="right"><a href="javascript:void(0);" dataval="'+busiOrder.orderid+'"  class="arrow1" name="edit">查看进度</a>'; 
		              	 }else{
		              	 	orders_str+='<p style="margin-top:1em;">';
		              	 }
                		 orders_str+='</p></li>';
					 	 $('#sellorders').append(orders_str);
					});
					$('#more').removeClass('loading');
					
					//查看申诉进度
				    $('a[name=edit]').on('click',function(){
				        $(this).toggleClass('up');
				        var orderId = $(this).attr('dataval');
				        if($("#appeal_"+orderId).length <= 0){	
				        	$("#appeal_"+orderId).addClass("db");
				        	getAppeal($(this),orderId);
				        	$(this).parents('li').css('background','#ffffff');
						}else{
							if($("#appeal_"+orderId).is(":hidden")==true){
								$("#appeal_"+orderId).show();
							}else{
								$("#appeal_"+orderId).hide();
							}	
						}
				    })
				    
					if(data!=null && data.pageNo < data.totalPage){
						$("#more").show();
					}else{
						$("#more").hide();
					}
					++pageno;
				}else{
					$('#more').hide();
					//判断只有第一页的时候才可能出现友情提示
					if(typeof($('#a_without'))!='undefined' && data.pageNo == 1){
						var warn='<div id="a_without" class="a_without"><div class="no_order"></div>';
				        warn+='<div align="center">';
				        warn+='<p>目前还没有客户下单</p>';
				        warn+='<p>你可加大推广力度哟</p>';
				        warn+='</div></div>';
	    				$('#sellorders').append(warn);
	    			}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#more').removeClass('loading');
				$('#more').hide();
				layerMsg('网络超时，请重试！');
			}
		});
   }
   
   //获取卖家订单-查看申诉进度
   function getAppeal($this,orderId){
   		$.ajax({
			async : false,
			cache : false,
			type : 'GET',
			dataType : 'json',
			url : '/order/getOrderAppeal?orderId='+orderId,
			success : function(busiAppeal) {
				var str='';
				if(busiAppeal!=null && busiAppeal!=''){
					str+='<div id="appeal_'+orderId+'" class="apprepay relative db">';
                    str+='<i class="allow-top"></i>';
                    str+='<div><div class="bg">';
                    if(busiAppeal.examinestate == 1){     	
	                	str+='<div class="pros1"><span class="step1"></span>';
			        }else if(busiAppeal.examinestate == 2){
			            str+='<div class="pros1"><span class="step2"></span>';
			        }else if(busiAppeal.examinestate == 3){
			            str+='<div class="pros2"><span class="step3"></span>';
					}
                    str+='</div></div><ul>';
                    str+='<li><label>取消原因：</label>';
                    var _appealtype='暂无';
                    var appealtype = busiAppeal.appealtype;
                    if(appealtype!=null && appealtype!=''){
                    	_appealtype = appealtype;
                    }
                    str+='<p>'+_appealtype+'</p>';
                    str+='</li><li><label>具体描述：</label>';
                    var _reason='暂无';
                    var reason = busiAppeal.reason;
                    if(reason!=null && reason!=''){
                    	_reason = reason;
                    }
                    str+='<p>'+_reason+'</p></li>';
                    if(busiAppeal.evidencepics!=null){
                    	str+='<li style="height: 8.5em;">';
                    	str+='<div class="see-pic-upfile"><ul>';
	                   	for(var i=0;i<busiAppeal.evidencepics.length;i++){
	                   		 var pic = busiAppeal.evidencepics[i];
	                   		 var  minImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_120'+pic.substring(pic.lastIndexOf("."),pic.length);
			  				 var  tempurl = pic.substring(0,pic.lastIndexOf("/")+1);
			  				 var  maxImg = pic.substring((pic.lastIndexOf("/")+1),(pic.lastIndexOf(".")))+'_640'+pic.substring(pic.lastIndexOf("."),pic.length);
	                   		 str+='<li><img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+minImg+'" rel="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+maxImg+'"/><i></i></li>';
	                   	}
	                   	 str+='</ul></div></li>';
	                }  	
                    str+='<li style="padding-bottom: 1em; color: #ff0000; clear:both;"><a href="tel:4001054315">申诉电话：4001054315</a></li>';
                    str+='</ul></div></div>';
                    $this.parent('p').after(str);
                    
                    //查看申退图片
				    $('.see-pic-upfile ul li').on('click',function(){
					      $("#reportPop").html('<img src="'+$(this).children('img').attr('rel')+'" />');
				          $('#Report').show();
				          bgHiu();
				          return false;
					});
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('网络超时，请重试！');
			}
		});
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
   	 window.location.href="/order/getSellOrderDetail/"+orderid;
   }
   
   	//信息框
	function layerMsg(msg){
		layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
	}

	//日期控件
	$('#wdate1').click(function(){
		WdatePicker({
			maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#wdate2').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
			readOnly:true
		});
	});

	//背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
    
    $(function(){
    	//选择订单状态
        $('#selectflag a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            $("#orderstate").attr("value",$(this).attr("dataval"));
            return false;
        });
    
    	//初始化卖家列表
    	loadSellOrders();
        //关闭层
        function close(els){
            els.on('click',function(){
                $('.h-demand').hide();
				$('.mj-order').hide();
                $('.bghui').remove();
            })
        }
        show($('#searchM'),$('#Check'));
        close($('.close'));
         close($('#hCheck'));
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }

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

        //下拉选择
        $('[datatype=unit] .unit').hide();
        $('[datatype=unit]').on('click',function(){
            $(this).children('.unit').show();
        });
        $('.unit a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            return false;
        });
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
        
        //修改
        $('input[datatype=reSubmit]').on('click',function(){
            var Alert = '<div class="alert">修改成功！</div>';
            $('body').append(Alert);
            $(this).parents('.h-demand').hide();
			$(this).parents('.mj-order').hide();
            $('.bghui').remove();
        });

        //
        $('.opr .icon2').on('click',function(){
            var Remove = '<div class="aRemove"><p>确定删除此条信息</p><div><input type="button" class="qx" value="取消" /><input type="button" class="qd" value="确定" /></div></div>'
            $('body').append(Remove);
            bgHiu();
        });
        $('body').delegate('.aRemove input[type=button]','click',function(){
            $('.bghui').remove();
            $('.aRemove').remove();
        });


    });
    function remove(){
        $('.alert').remove();
        setTimeout('remove()', 3000);
    }
    remove();

    //touch事件替换CLICK事件
    $('.img,.opr .icon1,.opr .icon2').touchStart(function () {
        $(this).addClass('hover');
    });
    $('.img,.opr .icon1,.opr .icon2').touchMove(function () {
        $(this).addClass('hover');
    });
    $('.img,.opr .icon1,.opr .icon2').touchEnd(function () {
        $(this).removeClass('hover');
    });
    $('.img,.opr .icon1,.opr .icon2').tapOrClick(function () {
        $(this).removeClass('hover');
    });

</script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>