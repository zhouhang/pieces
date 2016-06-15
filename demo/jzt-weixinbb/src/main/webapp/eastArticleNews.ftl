<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>品种行情-行情快讯</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
	<ul id="tab_id" class="tabs clearfix">
		<li class="cur" id="market"><a href="#"><span>市场快讯</span></a></li> 
		<li id="place"><a href="#"><span>产地快讯</span></a></li>
	</ul>
    <div class="box-layout">
        <ul class="search-report">
            <li class="search-input">
                <input type="text" id="ycnam" value="请输入品种名称">
            </li>
            <li><input type="button" id="search" class="btn-1 mt20" value="查 询"></li>
        </ul>
        <div class="search-none">
            <div align="center" class="tishi"><img src="${RESOURCE_IMG_WX}/images/tishi.png"/> </div>
            <p>抱歉，我们的努力白费了，还没有找到相关的数据。我们会更努力的去找这些数据，下次一定会有。</p>
        </div>
    </div>


	<div id="conts">
		<div class="bge5" id="bge5_market" style="display: block;">
			<#list market_articles as article>
			<div class="box-report" id="${article.acid}">
				<h1>${article.ycnam}</h1>
				<p class="timer">${article.dtm?string("yyyy-MM-dd")!''} 
					<#if ((article.writer != null)&&(article.writer != ""))> 
					&nbsp;&nbsp;&nbsp;&nbsp;研究员：${article.writer} 
					</#if>
				</p>
				<p class="mt20">
					${article.cont}
				</p>
			</div>
			</#list> <input type="button" class="btn-1 more" id="market_more"
				value="查看更多">
		</div>

		<div class="bge5" id="bge5_place" style="display: block;">
			<#list place_articles as article>
			<div class="box-report" id="${article.acid}">
				<h1>${article.title}</h1>
				<p class="timer">${article.dtm?string("yyyy-MM-dd")!''} 
					<#if ((article.writer != null)&&(article.writer != ""))> 
					&nbsp;&nbsp;&nbsp;&nbsp;研究员：${article.writer} 
					</#if>
				</p>
				<p class="mt20">
					${article.cont}
				</p>
			</div>
			</#list> <input type="button" class="btn-1 more" id="place_more"
				value="查看更多">
		</div>
	</div>

	<input type="hidden" id="market_pageNo" name="market_pageNo" value="${market_pageNo}"/>
	<input type="hidden" id="place_pageNo" name="place_pageNo" value="${place_pageNo}"/>
	<input type="hidden" value="${type}" id="type" />

<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
<script>
	//去掉tab中最后一个的li的竖分割线
	$("#tab_id  li").last().find('span').css("border-right","0px");
	
	//初始化
	$('#' + $('#type').val()).addClass('cur').siblings().removeClass('cur');
	
	$('#conts').children('.bge5').eq($('#' + $('#type').val()).index()).show().siblings().hide();

	$('.tabs li').on(
			'click mouseover',
			function() {
				$(this).addClass('cur').siblings().removeClass('cur');
				$('#conts').children('.bge5').eq($(this).index())
						.show().siblings().hide();
				$('#type').val($(this).attr('id'));
			});
			
	$(function(){
		//touch事件替换CLICK事件
	    $('input[type=button]').touchStart(function () {
	        $(this).addClass('hover');
	    });
	    $('input[type=button]').touchMove(function () {
	        $(this).addClass('hover');
	    });
	    $('input[type=button]').touchEnd(function () {
	        $(this).removeClass('hover');
	    });
	    $('input[type=button]').tapOrClick(function () {
	        $(this).removeClass('hover');
	    });
	})
    //搜索
    $('.search-input input').focus(function(){
        if($(this).val() == "请输入品种名称"){
            $(this).val('');
        }
    }).blur(function(){
        if($(this).val() === ''){
            $(this).val('请输入品种名称');
        }
    });
    //当前页数
	var pageNo = 1;
	//搜索字段
	var ycNam = '';
	//得到行情快讯
	function eastArticleNews(ycnam){
		var type = $('#type').val();
		var pageno;
		
		if(type == 'market'){
			pageno = $('#market_pageNo').val();
		}else{
			pageno = $('#place_pageNo').val();
		}
			
		$.ajax({
			async : false,
			cache : false,
			type : 'GET',
			data : {'pageNo':pageno,'ycnam':ycnam,'type':type},
			dataType : 'json',
			url : '/getEastArticleNews',
			success : function(data) {
				if(type == 'market'){
					$('#search_market_more').remove();
				}else{
					$('#search_place_more').remove();
				}
				
				var ok = data.ok;
				if(ok>0){
					$('.search-none').hide();
					
					if(pageno==1){
						if(type == 'market'){
							$("#bge5_market .box-report").remove();
						}else{
							$("#bge5_place .box-report").remove();
						}
					}
					
					if(type == 'market'){
						$('#market_pageNo').val(++pageno);
					}else{
						$('#place_pageNo').val(++pageno);
					}
				
					var eastArticles = data.eastArticles;
					$.each(eastArticles,function(eastArticleIndex,eastArticleObj){
						var writer = eastArticleObj.writer;
						var cont = eastArticleObj.cont;
						if(writer==null){
							if(type == 'market'){
								$('#bge5_market').append('<div class="box-report"><h1>'+eastArticleObj.ycnam+'</h1><p class="timer">'+ eastArticleObj.dtm +'&nbsp;&nbsp;&nbsp;&nbsp;</p><p class="mt20">'+cont+'</p></div>');
							}else{
								$('#bge5_place').append('<div class="box-report"><h1>'+eastArticleObj.title+'</h1><p class="timer">'+ eastArticleObj.dtm +'&nbsp;&nbsp;&nbsp;&nbsp;</p><p class="mt20">'+cont+'</p></div>');
							}
						}else{
							if(type == 'market'){
								$('#bge5_market').append('<div class="box-report"><h1>'+eastArticleObj.ycnam+'</h1><p class="timer">'+ eastArticleObj.dtm +'&nbsp;&nbsp;&nbsp;&nbsp;研究员：'+writer+'</p><p class="mt20">'+cont+'</p></div>');
							}else{
								$('#bge5_place').append('<div class="box-report"><h1>'+eastArticleObj.title+'</h1><p class="timer">'+ eastArticleObj.dtm +'&nbsp;&nbsp;&nbsp;&nbsp;研究员：'+writer+'</p><p class="mt20">'+cont+'</p></div>');
							}
						}
						
					});
					
					if(ok==10){
						if(type == 'market'){
							$('#bge5_market').append('<input type="button" id="search_market_more" class="btn-1" value="查看更多" onclick="eastArticleNews(ycNam);" />');
						}else{
							$('#bge5_place').append('<input type="button" id="search_place_more" class="btn-1" value="查看更多" onclick="eastArticleNews(ycNam);" />');
						}
					}
				}else{
					if(pageno==1){
						if(type == 'market'){
							$('#bge5_market').empty();
						}else{
							$('#bge5_place').empty();
						}
						
						$('.search-none').show();
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				//alert('操作失败！');
			}
		}); 
	};
    //搜索结果
    $('#search').click(function(){
       	var ycnam = $('#ycnam').val();
       	var type = $('#type').val();
       	if(ycnam=='请输入品种名称'){
       		ycNam = '';
       	}else{
       		ycNam = ycnam;
       	}
       	
       	if(type == 'market'){
			$('#market_more').remove();
			$('#market_pageNo').val(1);
		}else{
			$('#place_more').remove();
			$('#place_pageNo').val(1);
		}
       	eastArticleNews(ycNam);
    });
		
	//查看更多
	$('#market_more').click(function() {
		$('#market_more').remove();
		$('#market_pageNo').val(2);
		eastArticleNews(ycNam);
	});		
	$('#place_more').click(function() {
		$('#place_more').remove();
		$('#place_pageNo').val(2);
		eastArticleNews(ycNam);
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