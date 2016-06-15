<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的珍药材</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
 	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/store_foreground.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/zoom/zoom.css" />
	<style>
		.linomal li{list-style: auto; list-style-type: auto;}
	</style>
</head>
<body>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<#import 'macro.ftl' as tools>
<!-- 头部  -->
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
    <!-- 我的仓单左侧 -->
    <#include 'common/left.ftl'>
            <div class="hy-right fr">
                <div class="need-to-know" id="info_1"><h1>仓单挂牌流程与须知
                <a id="info_link_1" href="#"><img src="${RESOURCE_JS}/images/jzt-user-center/down.png"></a></h1>
	                	<div class="wire_content">
		                	<ul>
		                       <li><span>卖家挂牌</span></li>
		                       <li class="wire"></li> 
		                       <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
		                       <li class="intro">1. 填写挂牌信息<br/>
		                            2. 提交后，等待买家摘牌<br/>
		                            3. 买家摘牌后，生成订单，需支付保证金，才可锁定药材数量，如买方违约，将扣除保证金作为违约金
					           </li>
					       </ul>
		                   <ul>
		                    	<li><span>买家摘牌</span></li>
		                        <li class="wire"></li>
		                        <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
		                        <li class="intro">1. 买家摘牌后需缴纳保证金<br/>
		                            2. 买家支付保证金后，即锁定相应数量药材，如买方违约，将扣除保证金作为违约金
					            </li>
		                   </ul>
		                   <ul>
		                    	<li><span>仓库备货</span></li>
		                        <li class="wire"></li>
		                        <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
		                        <li class="intro">1. 仓库根据买家下单数量，按实际情况进行备货</li>
		                    </ul>
						   <ul>
		                    	<li><span>完成交易</span></li>
		                        <li class="wire"></li><li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
		                        <li class="intro">1. 买家支付保证金后5个工作日内必须支付剩余货款，否则视为违约，将扣除保证金支付给卖家<br/>
		                            2. 买家支付货款后，药材将生成新的仓单，交易完成</li>
		                    </ul>
	                	</div>
	                </div>
                <div class="cd-main">
               <div class="flow"><h1>挂牌详情</h1></div>
               <div class="left_order">
               	<ul>
               		<!--<li><span>挂牌编号： ${listingvo.listingid!''}</span></li>-->
                	<li><span>仓单编号： ${listingvo.wlid!''}</span></li>
                    <li><span style="margin-left:33px;">标题：${listingvo.title!''}</span></li>
                    <li><span>可挂牌数量/仓单总量：<@tools.money num=listingvo.wlsurplus format="0.##"/> ${listingvo.dictvalue!''}/<@tools.money num=listingvo.wltotal format="0.##"/> ${listingvo.dictvalue!''}</span></li>
                    <li><span>挂牌数量：<@tools.money num=listingvo.listingamount format="0.##"/>${listingvo.dictvalue!''}</span></li>
                    <li><span>挂牌价格：<@tools.money num=listingvo.lowunitprice format="0.##"/>元/${listingvo.dictvalue!''}</span></li>
                    <li><span>最低起订：<@tools.money num=listingvo.minsales format="0.##"/>${listingvo.dictvalue!''}</span></li>
                    <li><span>挂牌期限：${listingvo.listingtimelimit!''}天</span></li>
                	<#if listingvo.hasbill !=0>
            			<li><span>能否提供发票：	提供 </span></li>	
	            		<li><span>提供发票单价：<@tools.money num=listingvo.billprice format="0.##"/>元/${listingvo.dictvalue!''}</span></li>
            		<#else>
            		</#if>
                </ul>
            	</div>
                  <div class="right_orderpic">
            	<ul class="gallery">
            		<#assign i = 0>  
            		<#if listingvo.piclist ??>
                   		<#list listingvo.piclist as qualitypic>
                   			<#assign img = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i320}.jpg"> 
                   			<#assign img1 = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i640}.jpg"> 
                   			<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
                   			<#if qualitypic_index==0>
                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>散货照片</span></li>
                   			<#elseif qualitypic_index== listingvo.piclist?size-2>
                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>包装照</span></li>
                   			<#elseif qualitypic_index== listingvo.piclist?size-1>
                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>堆垛照</span></li>
                   			<#else>
                   				<#assign i = i + 1>
                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>细节照${i}</span></li>
                   			</#if>
                   		</#list>
		            </#if>  		
                </ul><div class="clear"></div>
               </div>        
       </div>
      <div class="editor cd-main" style="clear:both;padding-top:10px;">
			<div class="flow">
				<h1>药材详情</h1>
			</div>
			<textarea id="textareaContent" style="display:none;">${listingvo.content!''}</textarea>
            <iframe id="iframeContent" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
        </div>   
   </div>
     </div>
<!-- 详情 end  -->
<!-- 底部  -->
<#include 'common/footer.ftl'>
<script type="text/javascript" src="${RESOURCE_JS}/js/zoom/zoom.min.js"></script>
<script type="text/javascript">
	function display_table(link_id,id){ 
		var link_id = document.getElementById(link_id); 
		var id = document.getElementById(id); 
		if(id.style.display=='none'){ 
		id.style.display =""; 
		link_id.innerHTML = "隐藏信息"; 
		}else{ 
		id.style.display ="none"; 
		link_id.innerHTML = "显示信息"; 
		} 
	};
   // Link to open the add_detailst
   $( "#btn-add" ).click(function( event ) {
        $( "#add_detailst" ).dialog( "open" );
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
        event.preventDefault();
    });
    $('#info_link_1').on('click',function(){
        $(this).parents('#info_1').toggleClass('toogle');
        if($(this).parents('#info_1').hasClass('toogle')){
            $(this).children('img').attr('src','${RESOURCE_JS}/images/jzt-user-center/up.png');
        }else{
            $(this).children('img').attr('src','${RESOURCE_JS}/images/jzt-user-center/down.png');
        }
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
    // 关闭|显示div
	function display_table(link_id,id){ 
		var link_id = document.getElementById(link_id); 
		var id = document.getElementById(id); 
		if(id.style.display=='none'){ 
			id.style.display =""; 
			link_id.innerHTML = "隐藏信息"; 
		}else{ 
			id.style.display ="none"; 
			link_id.innerHTML = "显示信息"; 
		} 
	};
</script> 
</body>
</html>