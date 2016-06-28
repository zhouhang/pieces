<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>品种行情-供求信息</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
    <ul id="tab_id" class="tabs clearfix">
        <li class="cur" id="supply"><a href="#"><span>供应信息</span></a> </li>
        <li id="purchase"><a href="#"><span>求购信息</span></a> </li>
    </ul>
	<div class="box-layout">
		<ul class="search-report charge">
			<li class="search-input"><input type="text" id="yc"
				value="请输入品种名称"></li>
			<li><input type="button" class="btn-1 mt20" id="Demand"
				value="查 询"></li>
		</ul>
		<input type="hidden" id="yc_search" value="${yc}" /> 
	</div>
	
	<!--搜索无结果 start-->
	<div class="box-layout">
		<div class="search-none">
			<div align="center" class="tishi">
				<img src="${RESOURCE_IMG_WX}/images/tishi.png" />
			</div>
			<p id="tip">抱歉，我们的努力白费了，还没有找到相关的数据。我们会更努力的去找这些数据，下次一定会有。</p>
		</div>
		<input type="hidden" id="search-supply" value="0" /> 
		<input type="hidden" id="search-purchase" value="0" />
		<input type="hidden" id="pageNo_supply" value="0" /> 
		<input type="hidden" id="pageNo_purchase" value="0" />
	</div>
	<!--搜索无结果 end-->
	   
	

   	<div id="conts" class="charge-conts">
		<div style="display: block;" id="div_supply">
			<input type="hidden" id="supply_flag" />
			<input type="button" class="btn-1 more" id="supply_more"
				value="查看更多">
		</div>

		<div id="div_purchase">
			<input type="hidden" id="purchase_flag" />
			<input type="button" class="btn-1 more" id="purchase_more"
				value="查看更多">
		</div>
	</div>

<input type="hidden" id="type" value="${type}" />
<input type="hidden" id="ycWeixin" value="${yc}" />

<!--查看图片弹层 start-->
<div class="seebox" id="supplypic">

</div>
<div class="see-close"><img src="${RESOURCE_JS_WX}/images/seeClose.png" /> </div>
<!--查看图片弹层 end-->


<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/cloud-zoom.1.0.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	//去掉tab中最后一个的li的竖分割线
	$("#tab_id  li").last().find('span').css("border-right","0px");

//===========================start 默认加载的文字描述==================================//	
	var no_data_tip = '抱歉，我们的努力白费了，还没有找到相关的数据。我们会更努力的去找这些数据，下次一定会有。'//查询没有结果时提示
	var no_yc_tip = '抱歉，没有输入品种名称是不能查询求购信息的，请输入正确的品种名称!'//查询求购信息时没有代品种的提示
//===========================end 默认加载的文字描述==================================//

//===========================start 配合显示更更多和分类==================================//
    $(function(){
    	//init
    	//if(!$('#type').val()==''){
    		$('#' + $('#type').val()).addClass('cur').siblings().removeClass(
					'cur');
    		$('#conts').children('div').eq($('.cur').index())
				.show().siblings().hide();
    	//}
		$('#supply_more').hide();
		$('#purchase_more').hide();
	
	//文字缩放
    function fontResize(id){
    	$(id+' dl.dl2').each(function(i){
    		$(this).find('dd').each(function(j){
    			while(true){
					var ddHeight = $(this).height();
					if(ddHeight>60){
						var ddFontSize = parseInt($(this).css('font-size'));
			        	$(this).css('font-size',ddFontSize-1 +'px');
			        	if(ddFontSize == 0){
			        		break;
			        	}
					}else{
						break;
					}
				}
	    	});
    	});
    	$(id+' dl.supply-box').each(function(i){
			$(this).find('dd').each(function(j){
				while(true){
					//var ddHeight = $(this).height();
					var section1Height = $(this).find('section').first().height();
					var section2Height = $(this).find('section').last().height();
					//var articleHeight = $(this).find('article').height();
					if(section1Height>81 || section2Height>80){
						var ddFontSize = parseInt($(this).css('font-size'));
			        	$(this).css('font-size',ddFontSize-1 +'px');
			        	if(ddFontSize == 0){
			        		break;
			        	}
					}else{
						break;
					}
				}
	        });
        });
    };
	$('.tabs li').on('click',function() {
		$(this).addClass('cur').siblings().removeClass('cur');
		$('#conts').children('div').eq($(this).index()).show()
			.siblings().hide();
		var type = $(this).attr('id')
		$('#type').val(type);
		if ($('#search-' + type).val() == '1') {
			document.getElementById("tip").innerHTML=no_data_tip;
			$('.search-none').show();
		} else {
			$('.search-none').hide();
		}
		var Height = $(document).height();
		$('.pop-up').height(Height);
		fontResize('#div_'+type);
	});
    //厂地分类层
    var Height = $(document).height();
    $('.pop-up').height(Height);
    $('#Choose').on('click',function(){
        $('.pop-up').show().animate({left:0},100);
    });

    $('#tabsNav>li:not(:first-child)').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        $('#tabsConts p').eq($(this).index()).show().siblings().hide();
    });
    $('#tabsNav>li:first-child').on('click',function(){
    	$('#chandi').val('全部');
        $('.pop-up').hide().animate({left:'100%'},100);
    });

	$('.btn-return').on('click',function(){
		$('.pop-up').hide().animate({left:'100%'},100);
	});
    
    $('#tabsConts a').on('click',function(){
        $(this).parents('.pop-up').hide().animate({left:'100%'},100);
        $('#chandi').val($(this).text());
        return false;
    });
   

    
    
  	//弹层a元素事件
	$('#tabsConts').delegate('a', 'click', function() {
		$(this).parents('.pop-up').hide().animate({
			left : '100%'
		}, 100);
		return false;
	});
    
//===========================end 配合显示更更多和分类====================================//

//===========================start 默认加载分页的供求信息==================================//			
		//加载供应信息数据
		$.get('/supply_default_ajax?yc=' + $('#ycWeixin').val(), function(data) {
			add_supply(data);
			$('#supply_more').show();
			$('#pageNo_supply').val(1);
			
			fontResize('#div_supply');
		}, 'json');
		
		//默认显示供应信息数据
		function add_supply(data) {
			var str = '';
			//显示默认珍药材审核通过后按时间倒序排列的挂牌信息
			if (data.wx_supplyPrices.length > 0) {
				if (!($('#wx_supplyPrices').length > 0)) {
					str = '<dl class="dl2" id="wx_supplyPrices"><dt><span class="col-red">珍药材交易</span></dt></dl>';
					$('#supply_flag').before(str);
					
				}
				for (i = 0; i < data.wx_supplyPrices.length; i++) {
					var grade = (data.wx_supplyPrices[i].grade == null) ? '' : data.wx_supplyPrices[i].grade;
					var amountUnit = (data.wx_supplyPrices[i].amountUnit == null) ? '' : data.wx_supplyPrices[i].amountUnit;
					str = '<dd><span class="fr">￥'
							+ data.wx_supplyPrices[i].price + '</span> 名称：'
							+ data.wx_supplyPrices[i].name + '<br /> 规格：'
							+ grade + '<br /> 挂牌量：'
							+ data.wx_supplyPrices[i].amount + amountUnit + '</dd>';
					$('#wx_supplyPrices').append(str);
				}
			}
			//显示微信发布的供应信息和东网发布的供应信息
			if (data.wx_supplyInfo.length > 0) {
				for (i = 0; i < data.wx_supplyInfo.length; i++) {
					if (!($('#' + data.wx_supplyInfo[i].huoYuandi).length > 0)) {
						str = '<dl class="supply-box" id="'+data.wx_supplyInfo[i].huoYuandi+'"><dt id="'+data.wx_supplyInfo[i].huoYuandi+'_dt">'
								+ data.wx_supplyInfo[i].huoYuandi
								+ '</dt></dl>'
						$('#supply_flag').before(str);
					}
					
					str = '<dd><section><h3>'
							+ data.wx_supplyInfo[i].breedName.substring(0,5)
							+ '</h3>规格：'
							+ data.wx_supplyInfo[i].breedStandardLevel.substring(0,5) + '<br/>发布人：'
							+ data.wx_supplyInfo[i].userName.substring(0,5) + '<br /></section>'
							+ '<section><br/>价格：';
					if(data.wx_supplyInfo[i].supplyPrice=="0元/公斤" || data.wx_supplyInfo[i].supplyPrice=="协商"){
						str += '电议';
					}else{
						str += data.wx_supplyInfo[i].supplyPrice;
					}	
							
					str +=	'<br/>库存：'
					if(data.wx_supplyInfo[i].qty!=0){
						str += data.wx_supplyInfo[i].qty + data.wx_supplyInfo[i].qtyUnit;
					}else{
						str += '保密';
					}
							
							str += '<br/>电话：<a href="tel:'
							+ data.wx_supplyInfo[i].userMobile
							+ '">'
							+ data.wx_supplyInfo[i].userMobile.substring(0,11) + '</a><br/></section>'
							+ '<article>';
							
							
					if(data.wx_supplyInfo[i].supplyPicOne!="0.jpg")	{	
						str +=  '<a href="javascript:see(' + '\'' + data.wx_supplyInfo[i].supplyPicOne + '\'' + ');" class="see">查看图片</a>';
					}
					str +=	'<a href="tel:' + data.wx_supplyInfo[i].userMobile
							+ '" class="call" title="拔打电话"></a></article></dd>';
					$('#' + data.wx_supplyInfo[i].huoYuandi).append(str);
				}
			}
			
		}

		
		//加载求购信息数据
		$.get('/purchase_default_ajax', function(data) {
			add_purchase(data);
			$('#purchase_more').show();
			$('#pageNo_purchase').val(1);
			
			fontResize('#div_purchase');
		}, 'json');		
			
		//默认显示求购信息数据
		function add_purchase(data) {
			var str = '';
			//显示默认珍药材审核通过后按时间倒序排列的挂牌信息
			if (data.wx_supplyPrices.length > 0) {
				if (!($('#wx_demandPrices').length > 0)) {
					str = '<dl class="dl2" id="wx_demandPrices"><dt><span class="col-red">珍药材交易</span></dt></dl>';
					$('#purchase_flag').before(str);
				}
				for (i = 0; i < data.wx_supplyPrices.length; i++) {
					var grade = (data.wx_supplyPrices[i].grade == null) ? '' : data.wx_supplyPrices[i].grade;
					var amountUnit = (data.wx_supplyPrices[i].amountUnit == null) ? '' : data.wx_supplyPrices[i].amountUnit;
					str = '<dd><span class="fr">￥'
							+ data.wx_supplyPrices[i].price + '</span> 名称：'
							+ data.wx_supplyPrices[i].name + '<br /> 规格：'
							+ grade + '<br /> 挂牌量：'
							+ data.wx_supplyPrices[i].amount + amountUnit + '</dd>';
					$('#wx_demandPrices').append(str);
				}
			}
			//显示微信发布的供应信息和东网发布的求购信息
			if (data.wx_demandInfo.length > 0) {
				for (i = 0; i < data.wx_demandInfo.length; i++) {
					if (!($('#p_' + data.wx_demandInfo[i].huoYuandi).length > 0)) {
						str = '<dl class="supply-box" id="p_'+data.wx_demandInfo[i].huoYuandi+'"><dt id="'+data.wx_demandInfo[i].huoYuandi+'_dt">'
								+ data.wx_demandInfo[i].huoYuandi
								+ '</dt></dl>'
						$('#purchase_flag').before(str);
					}
					str = '<dd><section><h3>'
							+ data.wx_demandInfo[i].breedName.substring(0,5)
							+ '</h3>规格：'
							+ data.wx_demandInfo[i].breedStandardLevel.substring(0,5) + '<br/>发布人：'
							+ data.wx_demandInfo[i].userName.substring(0,5) + '<br /></section>'
							+ '<section><br/><br/>求购量：';
						if(data.wx_demandInfo[i].qtyUnitQty!=null){
							str +=data.wx_demandInfo[i].qtyUnitQty;
						}else{
							str +='面议';
						}	
						str +='<br/>电话：<a href="tel:'
							+ data.wx_demandInfo[i].userMobile
							+ '">'
							+ data.wx_demandInfo[i].userMobile.substring(0,11) + '</a><br/></section>'
							+ '<article>'
							+ '<a href="tel:' + data.wx_demandInfo[i].userMobile
							+ '" class="call" title="拔打电话"></a></article></dd>';
					$('#p_' + data.wx_demandInfo[i].huoYuandi).append(str);
					
				}
			}
		}
		
//===========================end 默认加载分页的供求信息==================================//		


//===========================start 加载更多数据分页的供求信息==================================//	
			$('#div_supply').delegate('#supply_more','click',function(){
				var yc = $('#yc_search').val();
				var type = $('#type').val();
				var pageNo = $('#pageNo_supply').val();
				if(pageNo != null){
					pageNo = parseInt(pageNo);
					pageNo+=1;
				}
				$.get(
					'/supply_default_ajax?pageNo='
							+ pageNo
							+ '&yc='
							+ yc,
					function(data) {
						if (data.wx_supplyPrices.length == 0
								&& data.wx_supplyInfo.length == 0) {
							$('#supply_more').hide();
							$('#pageNo_supply').val(data.pageNo);
						} else {
							add_supply(data);
							$('#pageNo_supply').val(data.pageNo);
							
							fontResize('#div_supply');
						}
					}, 'json');
			});
			
			$('#div_purchase').delegate('#purchase_more','click',function(){
				var yc = $('#yc_search').val();
				var type = $('#type').val();
				var pageNo = $('#pageNo_purchase').val();
				if(pageNo != null){
					pageNo = parseInt(pageNo);
					pageNo+=1;
				}
				$.get('/purchase_default_ajax?pageNo='
						+ pageNo
						+ '&yc='
						+ yc,
					function(data) {
						if (data.wx_supplyPrices.length == 0
								&& data.wx_demandInfo.length == 0) {
							$('#purchase_more').hide();
							$('#pageNo_purchase').val(data.pageNo);
						} else {
							
							add_purchase(data);
							$('#pageNo_purchase').val(data.pageNo);
							
							fontResize('#div_purchase');
					}
				}, 'json');
			});
//===========================end 加载更多数据分页的供求信息==================================//	

//============================start 点击查询钮==================================================================================//	       
        //搜索
        $('.search-input input').focus(function(){
            if($(this).val() == "请输入品种名称" || $(this).val() == "全部"){
                $(this).val('');
            }
            $(this).parent('li').css('borderColor','#f59e73');
        }).blur(function(){
            if($(this).val() === ''){
            	if($(this).attr('id')=='yc'){$(this).val('请输入品种名称');}
            }
            $(this).parent('li').css('borderColor','#cfcfcf');
        });
        
      	//查询加载
		$('#Demand')
				.click(
						function() {
							//参数：品种名称
							var yc = $('#yc').val();
							if (yc == '请输入品种名称') {
								yc = '';
							}
							$('#yc_search').val(yc);
							$('.search-none').hide();
							var type = $('#type').val();
							//供应信息
							//if (type == 'supply') {
								$.get('/supply_default_ajax?pageNo=1&yc='
														+ yc,
												function(data) {
													if (data.wx_supplyPrices.length == 0
															&& data.wx_supplyInfo.length == 0) {
														$('#div_supply')
																.empty();
														$('#search-' + type)
																.val('1');
														document.getElementById("tip").innerHTML=no_data_tip;		
														$('.search-none')
																.show();
														$('#supply_more').hide();
														$('#pageNo_today').val(data.pageNo);
													} else {
														
														$('#div_supply')
																.empty();
														$('#search-' + type)
																.val('0');
														$('.search-none')
																.hide();
														$('#div_supply')
																.append(
																		'<input type="hidden" id="supply_flag" /><input type="button" class="btn-1 more" id="supply_more" value="查看更多">');
														add_supply(data);
														$('#pageNo_supply').val(data.pageNo);
														
														fontResize('#div_supply');
													}
												}, 'json');
							//}
							//求购信息
							//if (type == 'purchase') {
								//参数：品种名称
							    //if(yc == ''){
							       //document.getElementById("tip").innerHTML=no_yc_tip;
							    //}
							      $.get('/purchase_default_ajax?pageNo=1&yc='
														+ yc,
												function(data) {
													if (data.wx_supplyPrices.length == 0
															&& data.wx_demandInfo.length == 0) {
														$('#div_purchase')
																.empty();
														$('#search-' + type)
																.val('1');
														document.getElementById("tip").innerHTML=no_data_tip;		
														$('.search-none')
																.show();
														$('#purchase_more').hide();
														$('#pageNo_history').val(data.pageNo);
													} else {
														
														$('.search-none').hide();
														$('#div_purchase')
																.empty();
														$('#search-' + type)
																.val('0');
														$('#div_purchase')
																.append(
																		'<input type="hidden" id="purchase_flag" /><input type="button" class="btn-1 more" id="purchase_more" value="查看更多">');
														add_purchase(data);
														$('#pageNo_purchase').val(data.pageNo);
														
														fontResize('#div_purchase');
													}
												}, 'json');
							    
							//}
						});
	//============================end 点击查询钮==================================================================================//	   
	});

	//============================start touch事件替换CLICK事件============================/
    $('input[type=button],.see,.call').touchStart(function () {
        $(this).addClass('hover');
    });
    $('input[type=button],.see,.call').touchMove(function () {
        $(this).addClass('hover');
    });
    $('input[type=button],.see,.call').touchEnd(function () {
        $(this).removeClass('hover');
    });
    $('input[type=button],.see,.call').tapOrClick(function () {
        $(this).removeClass('hover');
    });
   
   //============================end touch事件替换CLICK事件============================/
   
   //点击查看图片显示大图
        function bgHui(){
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
        }
   
   
        
   		//显示图片层
        function see(supplyPicOne){
        	//加载求购信息图片
    		$.get('/supply_pic_ajax?picOne='+supplyPicOne, function(data) {
    			
    			if (data.wx_supplyPic.length == 1) {//东网供应信息图片处理
    				$('#supplypic').empty();
    				var str = '';
        			str ='<div class="bigpic"><a href="';
        			str +=data.wx_supplyPic[0].picPath;
        			str +='" class="cloud-zoom" rel="position:&#39;inside&#39;,showTitle:false,adjustX:0,adjustY:0">';
        			str +='<img src="'; 
        			str += data.wx_supplyPic[0].picPath;
        			str += '"/></a></div><div class="smallpic"><ul><li class="cur"><img src="';
        			str += data.wx_supplyPic[0].picPath;
        			str += '" rel="';
        			str += data.wx_supplyPic[0].picPath;
        			str += '"/></li></ul></div>';
        			$('#supplypic').append(str);
        			
    			}else if(data.wx_supplyPic.length > 1){//珍药材供应信息图片处理（缩略图排列显示，点击显示原图）
    				
    				$('#supplypic').empty();
    				var str = '';
    				
    				str ='<div class="bigpic"><a href="${RESOURCE_IMG_UPLOAD_WX}/';
    				str +=data.wx_supplyPic[1].picPath;
    				str +='" class="cloud-zoom" rel="position:&#39;inside&#39;,showTitle:false,adjustX:0,adjustY:0">';
    				str +='<img src="${RESOURCE_IMG_UPLOAD_WX}'; 
    				str +='/';
        			str += data.wx_supplyPic[1].picPath;
        			str += '"/></a></div><div class="smallpic"><ul>';
    				for (i = 0; i < data.wx_supplyPic.length; i++) {
    					if(data.wx_supplyPic.length>i){
    						str += '<li class="cur"><img src="${RESOURCE_IMG_UPLOAD_WX}';
    						str += '/';
    						str += data.wx_supplyPic[i].picPath;
    						str += '" rel="${RESOURCE_IMG_UPLOAD_WX}';
    						str += '/';
    						str += data.wx_supplyPic[i+1].picPath;//i只为0或者偶数，取奇数为大图的地址
    						str +='"/></li>';
    					}
    					i++;//取0或者能被2整除的值为小图
    					}
    				str += '</ul></div>';
    				$('#supplypic').append(str);
    				}
    			Flipsnap('.smallpic ul',{
    	            distance:98,
    	            maxPoint:2
    	        });
    		}, 'json');	
        	
        	$('.seebox').show();
            $('.see-close').show();
            bgHui();
            
        };
        $('.see-close').on('click',function(){
            $('.seebox').hide();
            $(this).hide();
            $('.bghui').remove();
        });
        $('#supplypic').on('click','.smallpic ul li',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $('.bigpic img').attr('src',$(this).children('img').attr('rel'));
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