<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>品种行情-分析报告</title>
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/common.css">
	
	<style type="text/css">
		a:link { 
			color: #000000; 
			text-decoration: none; 
		} 
		a:visited { 
			color: #000000; 
			text-decoration: none; 
		} 
		a:hover { 
			color: #000000; 
			text-decoration: underline; 
		} 
    </style>
</head>
<body>
	<!--
	<ul class="tabs clearfix">
		<li class="cur" id="1"><a href="#">品种分析</a></li> 
		<li id="9"><a href="#">研究报告</a></li>
	</ul>
	-->
	<div class="box-layout">
		<ul class="search-report">
			<li class="search-input"><input type="text" id="pz"
				value="请输入品种名称"></li>
			<li><input type="button" class="btn-1 mt20" id="search"
				value="查 询"></li>
		</ul>
		<input type="hidden" id="pz_search" value=""/>
	</div>
	<div id="conts">
		<div class="bge5" id="bge5_1" style="display: block;">
			<#list articles_1 as article>
			<div class="box-report" id="${article.acid}">
				<h1><a href="/articleDetail?acid=${article.acid}">${article.title}</a></h1>
				<p class="timer">${article.dtm} <#if ((article.writer !=
					null)&&(article.writer != ""))> &nbsp;&nbsp;&nbsp;&nbsp;
					研究员：${article.writer} </#if></p>
				<p class="mt20 maxhei">
					<span>【摘要】</span>${article.ac_abstract}…
				</p>
				<div class="links">
					<a href="/articleDetail?acid=${article.acid}">查看全文</a>
				</div>
			</div>
			</#list> <input type="button" class="btn-1 more" id="more_1"
				value="查看更多">
		</div>

        <!--
		<div class="bge5" id="bge5_9">
			<#list articles_9 as article>
			<div class="box-report" id="${article.acid}">
				<h1>${article.title}</h1>
				<p class="timer">${article.dtm} <#if ((article.writer !=
					null)&&(article.writer != ""))> &nbsp;&nbsp;&nbsp;&nbsp;
					研究员：${article.writer} </#if></p>
				<p class="mt20 maxhei">
					<span>【摘要】</span>${article.ac_abstract}…
				</p>
				<div class="links">
					<a href="/articleDetail?acid=${article.acid}">查看全文</a>
				</div>
			</div>
			</#list><input type="button" class="btn-1 more" id="more_9"
				value="查看更多">
		</div>
		-->
	</div>

	<input type="hidden" id="pageNo_1" name="pageNo_1" value="${pageNo_1}"/>
	<!-- 
	<input type="hidden" id="pageNo_9" name="pageNo_9" value="${pageNo_9}"/>
	-->
	<input type="hidden" value="${lmid}" id="lmid" />
	
	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
	<script>
		$(function() {
			//初始化
			
//			$('#' + $('#lmid').val()).addClass('cur').siblings().removeClass(
//					'cur');
//			$('#conts').children('.bge5').eq($('#' + $('#lmid').val()).index())
//					.show().siblings().hide();

//			$('.tabs li').on(
//					'click mouseover',
//					function() {
//						$(this).addClass('cur').siblings().removeClass('cur');
//						$('#conts').children('.bge5').eq($(this).index())
//								.show().siblings().hide();
//						$('#lmid').val($(this).attr('id'));
//					});
					
//			$('.box-report').on(
//					'click',
//					function(){
//						var id = $(this).attr('id');
//						window.location.href="/articleDetail?acid="+id;
//					});	
					
			//搜索
			$('.search-input input').focus(function() {
				if ($(this).val() == "请输入品种名称") {
					$(this).val('');
				}
			}).blur(function() {
				if ($(this).val() === '') {
					$(this).val('请输入品种名称');
				}
			});

			//查询加载
			$('#search').click(
					function() {
						var pz = $('#pz').val();
						if (pz == '请输入品种名称') {
							pz = '';
						}
						$('#pz_search').val(pz);
						$.get('/analysis_ajax?lmid=1&pageNo=0&pz=' + pz,
								function(data) {
									$("#bge5_1 .box-report").remove();
									$("#pageNo_1").val(1);
									addArticles(data, 1);
								}, 'json');
//						$.get('/analysis_ajax?lmid=9&pageNo=0&pz=' + pz,
//								function(data) {
//									$("#bge5_9 .box-report").remove();
//									addArticles(data, 9);
//								}, 'json');
					});

			//查看更多
			$('.more').on(
					'click',
					function() {
						var pz = $('#pz_search').val();
						var lmid = $('#lmid').val();
						if (pz == '请输入品种名称') {
							pz = '';
						}
						$.get('/analysis_ajax?pageNo='
								+ $('#pageNo_' + lmid).val() + '&lmid=' + lmid
								+ '&pz=' + pz, function(data) {
							$("#pageNo_"+lmid).val(parseInt($("#pageNo_"+lmid).val())+1);
							addArticles(data, lmid);
						}, 'json');
					});

			//回调函数生成页面元素
			function addArticles(data, lmid) {
				var str = '';
				for (i = 0; i < data.length; i++) {
					str = '<div class="box-report"><h1>' + '<a href="/articleDetail?acid=' + data[i].acid + '">' + data[i].title +'</a>'
							+ '</h1><p class="timer">' + data[i].dtm;
					if (data[i].writer != null) {
						str += '&nbsp;&nbsp;&nbsp;&nbsp;研究员：' + data[i].writer
								+ '</p>';
					}
					str += '<p class="mt20 maxhei"><span>【摘要】'
							+ data[i].ac_abstract
							+ '…</span></p><div class="links"><a href="/articleDetail?acid='
							+ data[i].acid + '">查看全文</a></div></div>';
					$("#more_" + lmid).before(str);
				}
				if (data.length < 10) {
					$("#more_" + lmid).attr("type", "hidden");
				}
				if (data.length == 10) {
					$("#more_" + lmid).attr("type", "button");
				}
				
				var page = $("#pageNo_"+lmid).val();
				if (data.length == 0 && page == 1) {
					$("#bge5_" + lmid)
							.append(
									'<div class="box-report"><p>抱歉，我们的努力白费了，还没有找到相关的数据。我们会更努力的去找这些数据，下次一定会有。</p></div>');
				}
			}
			
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