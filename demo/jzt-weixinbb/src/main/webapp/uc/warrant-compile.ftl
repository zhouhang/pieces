<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我要挂牌-新增挂牌</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
    <#import 'macro.ftl' as tools>
     <style>
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>
</head>
<body>
<div class="sell-box-head">
    <!--<i class="back"></i>
    <div align="center" class="inStore-title">仓单挂牌</div>-->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    		<td width="12%"><i class="back"></i></td>
    		<td width="76%"><div align="center" style="width:100%;" class="inStore-title relative">仓单挂牌</div></td>
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
            		<#if whlistvo?? && whlistvo.piclist ??>
                   		<#list whlistvo.piclist as qualitypic>
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
    <!--<div><img src="images/sellDetailimg0721.png" /></div>-->
	<div class="compile_box">
	<form id="addBusiListing" name="addBusiListing">
    <ul>
    	<li>仓单编号<input id="wlid" name="wlid" type="text" style="width:70%;" class="line" value="${whlistvo.wlid}"><a title="选择仓单" href="/listing/wxWhlistManage"><i class="shuttle"></i></a></li>
        <li>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题 <input id="title" name="title"  maxlength="30" style="width:70%;" type="text" class="line2"></li>
        <li style="line-height:2em;">
          <p>举例：60头三七 产地云南省文山壮族苗族自治</p> 
          <p>州文山县2000公斤可售</p></li>
        <li><span>可挂牌数量/仓单总量：<@tools.money num=whlistvo.wlsurplus format="0.##"/> ${whlistvo.dictvalue!''}/<@tools.money num=whlistvo.wltotal format="0.##"/> ${whlistvo.dictvalue!''}</span><input type="hidden" id="wlsurplus" name="wlsurplus" value="${(whlistvo.wlsurplus)?c!0}" />
                    <input type="hidden" id="wltotal" name="wltotal" value="${(whlistvo.wltotal)?c!0}"/></li>
        <li>挂牌价格 <input id="lowunitprice" name="lowunitprice" class="line3" style="width:35%;" type="text" onkeyup="clearNoNum(this)" /><span>元/${whlistvo.dictvalue!''}</span><d style="font-size:0.7em;"> (不含运费)</d></li>
        <li>挂牌数量 <input id="listingamount" name="listingamount" class="line3" type="text" recheck="wlsurplus" onkeyup="clearNoNum(this)" /><span>${whlistvo.dictvalue!''}</span></li>
        <li>最低起订 <input id="minsales" name="minsales" class="line3" type="text" maxlength="10"  onkeyup="clearNoNum(this)"/><span>${whlistvo.dictvalue!''}</span></li>
        <li class="bn pa"><label class="la">挂牌期限 </label>
                <span class="relative"><input type="text"  style="margin-top:-12px;width:48%;" id="listingtimelimit" name="listingtimelimit" datatype="ZhaTai"  class="input-text2a" value="${daylimit}" readonly="readonly" />
                <span id="span_selectdays" datatype="select-option" class="unit sty1" style="display:none;"> 
                    <a href="#">7</a>
                    <a href="#">14</a>
                    <a href="#">30</a>
                    <a href="#">60</a>
                    <a href="#">90</a>
                </span>
                                        天
                </span>
        </li>
        <li class="bn pa"><label class="la">能否提供发票 </label>
                <span class="relative"><input type="text" style="margin-top:-12px;width:44%;" datatype="ZhaTai" id="selectbill" class="input-text1a" readonly="readonly" value="不提供"/>
                <span id="span_selectbill" datatype="select-option" class="unit sty1" style="display:none;">
                	<a  href="javascript:void(0);">不提供</a>
                    <a  href="javascript:void(0);">提供</a>
                </span>
                </span>
                <input id="hasbill" name="hasbill" type="hidden" value="0"/>
        </li>
        <li  id="s_billprice">提供发票单价 <input id="billprice" maxlength="10" name="billprice" class="line3" type="text" onkeyup="clearNoNum(this)" /><span>元/${whlistvo.dictvalue!''}</span></li>
        <li>药材详情</li>
        <li><div class="op_describe">
        <textarea  name="content" id="subject" class="op_textarea" theme="simple" onkeyup="return checkLength(this)" accesskey="1" tabindex="11"></textarea>
    <div class="cl">                                
        <em id="subjectchk" class="limit500">
            可输入
            <strong id="checklen">500</strong>
            字
        </em>
    </div>
        </div></li>
        <span id="postNameRule" style="float:left;color:#3e3e3e;font-size: 0.875em;padding-left:1em;display:none;margin-top:-10px;"></span>
        <li>
        <input type="button" class="btn-1" value="提交" id="Register"></li>
        </ul>
     <form>   
    </div> 
    <div class="notice"><h1>注意事项：</h1>
<p>1. 必填项：标题、挂牌数量、挂牌价格、最低起订、挂牌期限、
   是否提供发票及提供发票价格、商品详细描述。</p>
<p>2. 提交后，会有相关工作人员进行审核，审核通过后，挂牌信息
   显示在商品列表中。</p></div> 
   
   <div class="history mt"><h1>历史挂牌</h1>
        <ul id="history">
       </ul>
       <div id="moreWxWhlists" class="load"></div>
   </div>
</div></div>
	
<!-- 提交弹层 -->
    <div style="display: none;" class="h-view" id="checker">
        <div class="h-view1">
            <ul>
                <li><h1>您的挂牌信息已提交！</h1></li>
                <li><p>小珍会审核您的挂牌信息后，将挂牌信息显示在小珍挂牌中，同时也会同步显示在珍药材平台中哦！</p></li>     
            </ul>
        </div>
        <div class="hang"><a href="/listing"><input class=" btn-gray1" type="button" id="Submit" value="查看我的挂牌" /></a></div>
    </div>
<!-- 提交弹层end -->
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
<script type="text/javascript">
	function valid(){
		var wlid = $("#wlid").val();
		var title = $("#title").val();
		var lowunitprice = $("#lowunitprice").val();
		var listingamount= $("#listingamount").val();
		var minsales = $("#minsales").val();
		var content= $("#subject").val();
		
		var postNameRule = $("#postNameRule");
		
		if(wlid==null || wlid==''){
			postNameRule.text("请选择仓单号").show();
			return false;
		}else if(title==null || title==''){
			postNameRule.text("请输入标题内容").show();
			return false;
		}else if(lowunitprice==null || lowunitprice==''){
			postNameRule.text("请输入挂牌价格").show();
			return false;
		}else if(listingamount==null || listingamount==''){
			postNameRule.text("请输入挂牌数量").show();
			return false;
		}else if(minsales==null || minsales==''){
			postNameRule.text("请输入最低起订数量").show();
			return false;
		}else if(content==null || content==''){
			postNameRule.text("请填写药材详情").show();
			return false;
		}
		
		var wlsurplus = $("#wlsurplus").val();
		if(Number(listingamount) > Number(wlsurplus)){
			postNameRule.text("挂牌数量不能大于可挂牌数量").show();
			return false;
		}
		if(Number(minsales) > Number(listingamount)){
			postNameRule.text("最低起订不能大于挂牌数量").show();
			return false;
		}
		postNameRule.text("").hide();
	}

    function clearNoNum(obj){
      obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
	}
	
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
    	var params ="pageNo="+pageno+"&op=1";
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
						var moreWxWhlists = '<li><a href="/listing/detail/'+wxWhlist.listingid+'"><p> 挂牌编号：'+wxWhlist.listingid+'<em>'+flag+'</em></p>';
                		moreWxWhlists+='<span>标   题：'+wxWhlist.title+'</span>';
		                moreWxWhlists+='<span><b>挂牌单价：</b>￥'+wxWhlist.lowunitprice+'元/'+wxWhlist.dictvalue+'</span>';
		                moreWxWhlists+='<span><i class="arrow-right"></i></span>';
		                moreWxWhlists+='<span><b>已有订单：</b>'+wxWhlist.ordernum+'</span>';
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
		                moreWxWhlists+='<span><b>有效期：</b> '+wlrkDateYMD+'</span></a></li>';
					
						$('#history').append(moreWxWhlists);
					});
					if(data!=null && data.pageNo < data.totalPage){
						$("#moreWxWhlists").show();
					}else{
						$('#moreWxWhlists').hide();
					}
					++pageno;
				}else{
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
	
    //信息框
    function layerMsg(msg){
    	layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
    }
    
	var hasbill = $('#hasbill');
    var s_billprice= $('#s_billprice');
    var billprice= $('#billprice');
   	if(hasbill.val()=='0'){
   		s_billprice.hide();
   		billprice.val('0');
   	}else{
   		s_billprice.show();
   	}
	    
	//返回按钮事件
    $('.back').on('click',function(){
    	history.go(-1); 
    })
    
    var maxChars = 500;//设置全局变量,文本域最多字符数    
    
	//给规格等级span加上事件，点击它周围地方层消失
	$(document).on("click",function(e){
		var target = $(e.target);
		if(target.closest("#span_selectdays").length == 0 && target.closest("#listingtimelimit").length == 0){
			$("#span_selectdays").hide();
		}
		if(target.closest("#span_selectbill").length == 0 && target.closest("#selectbill").length == 0){
			$("#span_selectbill").hide();
		}
	}) 
        
    $(function(){
    	//加载历史挂牌
    	loadHistoryListing();
    	
    	var len = $("#subject").val().length ;
    	if(len >=maxChars){
    		$("#checklen").text(0);    //超过或等于规定长度，剩余文字长度设置0
        }else{
        	var curr = maxChars - len; 
        	$("#checklen").text(curr);  //设置实际剩余文字长度
        }
        
        //背景变灰
        function bgHiu(){
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
        }
        //关闭层
        function close(els){
            els.on('click',function(){
                $('.h-demand').hide();
                $('.bghui').remove();
            })
        }
        close($('.close'));
        close($('#Submit'));
		
		//显示层
		 function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }
        show($('#check'),$('.h-demand'));


		//关闭层
        function close(els){
            els.on('click',function(){
                $('.h-view').hide();
                $('.bghui').remove();
            })
        }
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }
        show($('#check'),$('.h-view'));
        
		 //高级查询
        $('.unit').hide();
        $('input[datatype=ZhaTai]').on('click',function(){
            $(this).next('.unit').show();
        });
        $('.unit a').on('click',function(){
        	$(this).parent().prev().val($(this).text());
            $(this).parent().hide();
            
            //是否提供发票
	    	var billselect = $(this).text(); 
	    	if(billselect=='不提供'){
	   			s_billprice.hide();
	   			billprice.val('0');
	       	}else if(billselect=='提供'){
	       		s_billprice.show();
	       	}
            return false;
        });
    })
    
	//提交
	$('#Register').click(function(){
		if(valid() == false){
			return false;
		}
		$("#Register").attr("disabled", "disabled"); 
		$("#Register").val("提交中...");
		var params = $("#addBusiListing").serialize();
		$.ajax({
			async : true,
			cache : false,
			type : 'post',
			data : params,
			dataType : 'json',
			url : '/listing/addBusiListing',
			success : function(data) {
				$("#Register").val("提交");
				$("#Register").removeAttr("disabled");
				var ok =data.ok;
		        var msg = data.msg;
				if(ok){
					$("#checker").show();
				}else{
					if(data.url!=null && data.url!=''){
						window.location.href=data.url;
					}
					$("#postNameRule").text(data.msg).show();
					setTimeout(function () {
       					 $("#postNameRule").hide();
    				}, 10000);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('新增挂牌失败！');
			}
		});
    })
</script>
<script type="text/javascript">   
    // 只要键盘一抬起就验证编辑框中的文字长度，最大字符长度可以根据需要设定
    function checkLength(obj)  { 
        var curr = maxChars - obj.value.length; 
        if( curr > 0 ){
            document.getElementById("checklen").innerHTML = curr.toString(); 
        }else{
            document.getElementById("checklen").innerHTML = '0';
            var temp = document.getElementById("subject").value;
            document.getElementById("subject").value=temp.substring(0,maxChars);
            return false;
        }
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