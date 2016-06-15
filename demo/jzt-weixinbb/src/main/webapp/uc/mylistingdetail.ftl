<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的挂牌-挂牌详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
    <style>
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>
    <#import 'macro.ftl' as tools>
    
</head>
<body>
<div class="sell-box-head">
   <!-- <a href="/listing" style="display:block;z-index:3;" class="relative"><i class="back"></i></a>
    <div align="center" class="inStore-title relative">挂牌详情</div>-->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    		<td width="12%"><i class="back"></i></td>
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
                   <#assign i = 0>  
            		<#if listingvo?? && listingvo.piclist ??>
                   		<#list listingvo.piclist as qualitypic>
                   			<#assign img = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_640.jpg"> 
                   			<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
                   			<li><img src="${RESOURCE_IMG_UPLOAD_WX}/${tempurl}${img}" width="640" height="300"/></li>
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

                                    var list=$("#scroll_pic_nav li");
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
                            }, 3000);
                            //
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
    <div class="layout mt">
        <h1>${(listingvo.title)!''}</h1>
        <p class="admin"><span>挂牌价格：</span><@tools.money num=listingvo.lowunitprice format="0.##"/>元/${listingvo.dictvalue!''}<br/>
            <span>挂牌数量：</span><@tools.money num=listingvo.listingamount format="0.##"/>${listingvo.dictvalue!''}<br/>
            <span>可挂牌数量/仓单总量：</span><@tools.money num=listingvo.wlsurplus format="0.##"/> ${listingvo.dictvalue!''}/<@tools.money num=listingvo.wltotal format="0.##"/> ${listingvo.dictvalue!''}<br/>
            <span>最低起订：</span><@tools.money num=listingvo.minsales format="0.##"/>${listingvo.dictvalue!''}<br/>
            <span>挂牌期限：</span>${listingvo.listingtimelimit!''}天<br/>
            <#if listingvo.hasbill !=0>
				<span>能否提供发票：</span> 提供 <br/>
	    		<span>提供发票单价：</span> <@tools.money num=listingvo.billprice format="0.##"/>元/${listingvo.dictvalue!''}<br/>
			</#if>
            <span>挂牌编号：</span> ${listingvo.listingid!''}<br/>
            <span>药材详情：</span> ${listingvo.content!''}
            <input id="wlid" type="hidden" value="${listingvo.wlid}"/>
            <input id="listingid" type="hidden" value="${listingvo.listingid}"/>
        </p>
    </div>
</div>
    <div class="a-sell-list1" style="padding-top: 0; margin-top: -1em;">
        <ul>
        	<li id="h-li" style="border-bottom: 0 none; margin-bottom: -1px; background-image: none;"><p class="adm-tit">历史挂牌</p></li>
        	<d id="history"></d>
        </ul>
        <div id="moreWxWhlists" class="load"></div>
    </div>
    <div id="op">
    <#if listingvo.listingflag??>
      	<#if listingvo.listingflag==0 || listingvo.listingflag==1 || listingvo.listingflag==2>
      		<a id="bj" href="/listing/update/${listingvo.listingid!''}" class="opr-red">编 辑</a>
      	</#if>	
      	<#if listingvo.listingflag==2 && listingvo.surpluses!=0>
      		<a id="xj" class="opr-yellow" href="javascript:confirm('${listingvo.listingid!''}');">下 架</a>
      	</#if>
  	</#if>
    </div>
	<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
	<script type="text/javascript">
		//隐藏下单提示框
        $('#Pay .close').on('click',function(){
            //$('#Pay').hide();
            //$('.bghui').remove();
            window.location.href="/search";
        })
        
        //返回按钮事件
	    $('.back').on('click',function(){
	    	history.go(-1); 
	    })
	    
	    //信息框
		function layerMsg(msg){
			layer.open({
			    content: msg,
			    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			    time: 2
			});
		}
		
		function confirm(listingid){
	        var Remove = '<div class="aRemove xiajia"><p>确定要下架编号<br/>'+listingid+'<br/>的挂牌吗？</p><div><input type="button" class="qx" value="取消" /><input type="button" dataval="'+listingid+'" class="qd" value="确定" /></div></div>'
	        $('body').append(Remove);
	        bgHiu();
	    }
	    
	    $('body').delegate('.aRemove .qx','click',function(){
	        $('.bghui').remove();
	        $('.aRemove').remove();
	    });
	    
	    $('body').delegate('.aRemove .qd','click',function(){
	        $('.bghui').remove();
	        $('.aRemove').remove();
	        var id = $(this).attr("dataval");
	        $.ajax({
				async : false,
				cache : false,
				type : "POST",
				data : {"listingId":id},
				dataType : "json",
				url : "/listing/disabledListing",
				success : function(data) {
					var ok = data.ok;
					var msg = data.msg;
					if(ok==true){
						$('#op').html('<a id="bj" class="opr-red" href="/listing/update?id=201508081000526" style="width: 100%; left: 0%;">编 辑</a>');
					}
					layerMsg(msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					layerMsg('网络超时，请重试！');
				}
			});
	    });
	    
		//当前页数
		var pageno = 1;
		$('#moreWxWhlists').click(function(){
			if($('moreWxWhlists').hasClass('loading')){
				return false;
			}
			$('#moreWxWhlists').addClass('loading');
			loadHistoryListing();
		})
	
		//获取历史挂牌
	    function loadHistoryListing(){
	    	var params ="pageNo="+pageno+"&op=2&wlid="+$("#wlid").val()+"&listingid="+$("#listingid").val();
			$.ajax({
				async : true,
				cache : false,
				type : 'GET',
				data : params,
				dataType : 'json',
				url : '/listing/historylisting',
				success : function(data) {
					$('#moreWxWhlists').removeClass('loading');
					var wxWhlists = data.results;
					var wxWhlistsLength = wxWhlists.length;
					if(wxWhlistsLength>0){
						$('#h-li').show();
						$.each(wxWhlists,function(index,wxWhlist){
							var flag='';
							switch(wxWhlist.listingflag){
								case 0 : flag='待审核';break;
								case 1 : flag='审核失败';break;
								case 2 : flag='挂牌中';break;
								case 3 : flag='已售罄';break;
								case 4 : flag='已取消';break;
								default:break;
							}
							var moreWxWhlists = '<li><a href="/listing/detail/'+wxWhlist.listingid+'"><p class="tit"><span class="fl col_a9">挂牌编号：'+wxWhlist.listingid+'</span><span class="fr col_red">'+flag+'</span></p>';
	                		moreWxWhlists+='<dl><dt>'+wxWhlist.title+'</dt>';
			                moreWxWhlists+='<dd><span>挂牌单价：</span><b>￥'+wxWhlist.lowunitprice+'</b> 元/'+wxWhlist.dictvalue+'<br/>';
			                moreWxWhlists+='<span>已有订单：</span><b>'+wxWhlist.ordernum+'</b><br/>';
			                var wlrkDate = wxWhlist.expiretime;
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
								wlrkDateYMD = wlrkDate.getFullYear()+'年'+wlrkDateMonth+'月'+wlrkDateDay+'日';
							}
			                moreWxWhlists+='<span>有效期：</span><b>'+wlrkDateYMD+'</b></dd></dl></a></li>';
						
							$('#history').append(moreWxWhlists);
						});
						if(data!=null && data.pageNo < data.totalPage){
							$("#moreWxWhlists").show();
						}else{
							$('#moreWxWhlists').hide();
						}
						++pageno;
					}else{
						$('#h-li').hide();
						$('#moreWxWhlists').hide();
						$('#history').append('<div style="padding-top:0.8em;text-align:center;">暂无历史挂牌!</div>');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#moreWxWhlists').removeClass('loading');
					layerMsg('网络超时，请重试！');
				}
			});
	   }
	
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
	
	 $(function(){
	 	/*********初始化底部按钮CSS start ***********/
	 	if($("#xj").length <=0){
	 		$("#bj").css("width","100%");
	 		$("#bj").css("left","0%");
	 	}
	 	if($("#bj").length <=0){
	 		$("#xj").css("width","100%");
	 		$("#xj").css("left","0%");
	 	}
	 	/*********初始化底部按钮CSS end ************/
	 	
     	//加载历史挂牌
    	loadHistoryListing();
    	
        $('a[name=xiajia]').on('click',function(){
            var Remove = '<div class="aRemove xiajia"><p>确定要下架编号<br/>20154864364646<br/>的挂牌吗？</p><div><input type="button" class="qx" value="取消" /><input type="button" class="qd" value="确定" /></div></div>'
            $('body').append(Remove);
            bgHiu();
        });
        $('body').delegate('.aRemove input[type=button]','click',function(){
            $('.bghui').remove();
            $('.aRemove').remove();
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
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>