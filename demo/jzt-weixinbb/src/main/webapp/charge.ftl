<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>品种行情-药材价格</title>
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>

	<ul id="tab_id" class="tabs clearfix">
		<li class="cur" id="today"><a href="#"><span>今日价格</span></a></li>
		<li id="history"><a href="#"><span>历史价格</span></a></li>
	</ul>
	<div class="box-layout">
		<ul class="search-report charge">
			<li class="search-input"><input type="text" id="yc"
				value="请输入品种名称"></li>
			<!-- <li class="search-input select"><input type="text" value="全部"
				id="chandi"> <i id="Choose"></i></li> -->
			<li><input type="button" class="btn-1 mt20" id="Demand"
				value="查 询"></li>
		</ul>
		<input type="hidden" id="yc_search" value="" /> <input type="hidden"
			id="chandi_search" value="" />
	</div>
	<!--搜索无结果 start-->
	<div class="box-layout">
		<div class="search-none">
			<div align="center" class="tishi">
				<img src="${RESOURCE_IMG_WX}/images/tishi.png" />
			</div>
			<p id="tip">抱歉，我们的努力白费了，还没有找到相关的数据。我们会更努力的去找这些数据，下次一定会有。</p>
		</div>
		<input type="hidden" id="search-today" value="0" /> 
		<input type="hidden" id="search-history" value="0" />
		<input type="hidden" id="pageNo_today" value="0" /> 
		<input type="hidden" id="pageNo_history" value="0" />
	</div>
	<!--搜索无结果 end-->

	<div id="conts" class="charge-conts">
		<div style="display: no;" id="div_today">
			<input type="hidden" id="today_flag" />
			<input type="button" class="btn-1 more" id="today_more"
				value="查看更多">
		</div>

		<div style="display: no;" id="div_history">
			<input type="hidden" id="main_flag" />
			
			<div style="display: block;" id="div_history_list">
			<input type="hidden" id="history_flag" />
			<input type="button" class="btn-1 more" id="history_more"
				value="查看更多">
			</div>
 		</div>
		
	</div>

	<!--产地弹层 start-->
	<div class="pop-up clearfix">
		<ul class="tabs-nav" id="tabsNav">
			<li><i class="icon1"></i>全部</li>
			<li class="cur"><i class="icon2"></i>产地<i class="arrow"></i></li>
		</ul>
		<div id="tabsConts" class="tabs-conts">
			<p></p>
			<p style="display: block;" id="chandis"></p>
			</p>
		</div>
		<input class="btn-return" value="返 回" type="button" />
	</div>
	<!--产地弹层 end-->
	<input type="hidden" id="type" value="${type}" />
	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
		
	<script src="${RESOURCE_JS_WX}/js/echarts.js"
		type="text/javascript"></script>
	<script type="text/javascript">
        require.config({
            paths: {
                echarts: '${RESOURCE_JS_WX}/js/dist'
            }
        });
    </script>
    
	<script type="text/javascript">
	//去掉tab中最后一个的li的竖分割线
	$("#tab_id  li").last().find('span').css("border-right","0px");
	
	var mChart = null;
	var mOption = null;
	var effectIndex = -1;
    var effect = ['spin' , 'bar' , 'ring' , 'whirling' , 'dynamicLine' , 'bubble'];
        
	var no_data_tip = '抱歉，我们的努力白费了，还没有找到相关的数据。我们会更努力的去找这些数据，下次一定会有。'
	var no_yc_tip = '抱歉，没有输入品种名称是不能查询历史价格的，请输入正确的品种名称!'
	var error_tip = '抱歉，暂时无法查询，请稍后再试!'
	$(function() {
			$('#' + $('#type').val()).addClass('cur').siblings().removeClass(
					'cur');
			$('#conts').children('div').eq($('#' + $('#type').val()).index())
					.show().siblings().hide();
			$('#today_more').hide();
			$('#history_more').hide();

			//加载今日数据
			$.get('/ycprice_today_ajax', function(data) {
				add_today(data);
				$('#div_today').show();
				$('#today_more').show();
				$('#pageNo_today').val(2);
			}, 'json');

			//加载产地
			$.get('/allchandi', function(chandis) {
				var str = '';
				for (i = 0; i < chandis.length; i++) {
					str = '<span><a href="#">' + chandis[i] + '</a></span>';
					$('#chandis').append(str);
				}
			});
             
			$('.tabs li').on(
					'click mouseover',
					function() {
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
						
						var tab = $('.cur').attr('id');
						if(tab == 'history' && mOption != null){
							initChart();
							loadChart();
							mChart.hideLoading();
						}else if(tab == 'today' && mChart != null){
						    mChart.clear();
							$('#main').remove();
							mChart = null;
						}
						
					});
			//搜索
			$('.search-input input').focus(function() {
				if ($(this).val() == "请输入品种名称" || $(this).val() == "全部") {
					$(this).val('');
				}
				$(this).parent('li').css('borderColor', '#f59e73');
			}).blur(function() {
				if ($(this).val() === '') {
					if($(this).attr('id')=='yc'){$(this).val('请输入品种名称');}
					if($(this).attr('id')=='chandi'){$(this).val('全部');}
				}
				$(this).parent('li').css('borderColor', '#cfcfcf');
			});

			//查询加载
			$('#Demand')
					.click(
							function() {
								var yc = $('#yc').val();
								var chandi = $('#chandi').val();
								if (yc == '请输入品种名称') {
									yc = '';
								}
								if (chandi == '全部'||chandi=='请输入品种名称') {
									chandi = '';
								}
								$('#yc_search').val(yc);
								$('#chandi_search').val(chandi);
								var type = $('#type').val()
								//今日
								if (type == 'today') {
									$.get('/ycprice_today_ajax?chandi='
															+ chandi
															+ '&pageNo=1&yc='
															+ yc,
													function(data) {
														if (data.wx_todayPrices.length == 0
																&& data.east_todayPrices.length == 0) {
															$('#div_today')
																	.empty();
															$('#search-' + type)
																	.val('1');
															document.getElementById("tip").innerHTML=no_data_tip;		
															$('.search-none')
																	.show();
															$('#today_more').hide();
															$('#pageNo_today').val(data.pageNo);
														} else {
															$('#div_today')
																	.empty();
															$('#search-' + type)
																	.val('0');
															$('.search-none')
																	.hide();
															$('#div_today')
																	.append(
																			'<input type="hidden" id="today_flag" />');
															if(data.wx_todayPrices.length >= 5 || data.east_todayPrices.length >= 5){
																$('#div_today')
																	.append(
																			'<input type="button" class="btn-1 more" id="today_more" value="查看更多">');
															}
															
															add_today(data);
															$('#pageNo_today').val(data.pageNo);
														}
													}, 'json');
								}
								//历史
								if (type == 'history') {
									clearCharData();
									$('#div_history_list').empty();
									$('#search-' + type).val('0');
									
								    if(yc == ''){
								        // 移除图表
								        $('#main').remove();
								        mChart = null;
								        document.getElementById("tip").innerHTML=no_yc_tip;
								        $('.search-none').show();
								    }else{
								        $('.search-none').hide();
								        initChart();
								      	$('#main').show();
								      	
										$.ajax({
											cache : false,
											type : "GET",
											dataType : "json",
											url : '/ycprice_all_history_ajax?chandi=' + chandi + '&yc=' + yc,
											success : function(data) {
												if (data.wx_historyPrices.length == 0
																&& data.east_historyPrices.length == 0 && data.series.length == 0) {
															$('#main').hide();
															$('#search-' + type)
																	.val('1');
															document.getElementById("tip").innerHTML=no_data_tip;		
															$('.search-none')
																	.show();
															$('#history_more').hide();
															$('#pageNo_history').val(data.pageNo);
														} else {
														    if(data.series.length > 0){
														        $('#main').show();
														        mChart.hideLoading();
														    	showChart(data);
														    }else{
														     	$('#main').hide();
														    }
															
															if(data.wx_historyPrices.length > 0 || data.east_historyPrices.length > 0){
															    $('#search-' + type).val('0');
															    $('.search-none').hide();
																$('#div_history_list')
																		.append(
																				'<input type="hidden" id="history_flag" /><input type="button" class="btn-1 more" id="history_more" value="查看更多">');
																add_history(data);
															}else{
																$('#history_more').hide();
															}
															
															$('#pageNo_history').val(data.pageNo);
														}
											},
											error : function(XMLHttpRequest, textStatus, errorThrown){
												$('#div_history_list').empty();
												$('#main').hide();
												document.getElementById("tip").innerHTML=error_tip;		
															$('.search-none')
																	.show();
											}
										});
								     
								    }
								}
							});
							
			$('#div_today').delegate('#today_more','click',function(){
				var yc = $('#yc_search').val();
				var chandi = $('#chandi_search').val();
				var type = $('#type').val();
				var pageNo = $('#pageNo_today').val();
				$.get(
					'/ycprice_today_ajax?chandi='
							+ chandi
							+ '&pageNo='
							+ pageNo
							+ '&yc='
							+ yc,
					function(data) {
						if (data.wx_todayPrices.length == 0
								&& data.east_todayPrices.length == 0) {
							$('#today_more').hide();
							$('#pageNo_today').val(data.pageNo);
						} else {
							add_today(data);
							$('#pageNo_today').val(data.pageNo);
						}
					}, 'json');
			});
			
			$('#div_history_list').delegate('#history_more','click',function(){
				var yc = $('#yc_search').val();
				var chandi = $('#chandi_search').val();
				var type = $('#type').val();
				var pageNo = $('#pageNo_history').val();
				$.get('/ycprice_history_ajax?chandi='
						+ chandi
						+ '&pageNo='
						+ pageNo
						+ '&yc='
						+ yc,
					function(data) {
						if (data.wx_historyPrices.length == 0
								&& data.east_historyPrices.length == 0) {
							$('#history_more').hide();
							$('#pageNo_history').val(data.pageNo);
						} else {
							add_history(data);
							$('#pageNo_history').val(data.pageNo);
					}
				}, 'json');
			});

			//厂地分类层
			$('#Choose').on('click', function() {
				$('.pop-up').show().animate({
					left : 0
				}, 100);
			});

			$('#tabsNav>li:not(:first-child)').on('click', function() {
				$(this).addClass('cur').siblings().removeClass('cur');
				$('#tabsConts p').eq($(this).index()).show().siblings().hide();
			});
			$('#tabsNav>li:first-child').on('click', function() {
				$('#chandi').val('全部');
				$('.pop-up').hide().animate({
					left : '100%'
				}, 100);
			});
			$('.btn-return').on('click',function(){
				$('.pop-up').hide().animate({left:'100%'},100);
			});
			$('#tabsConts a').on('click',function(){
				$(this).parents('.pop-up').hide().animate({left:'100%'},100);
				$('#Value').val($(this).text());
				return false;
			})

			

			// 初始化图表
			function initChart(){
			    if(mChart == null){
			    	var str = '<div id="main" style="height:400px;"></div>';
					$('#main_flag').before(str);
		         	setChartInitData();
		         	setChartInitData();
			    }else{
			    	showChartLoad();
			    }
			}
			
			// 设置默认数据
			function setChartInitData(){
				 var option = 
			  				{
			  				legend : {data: [' ']},
			  				xAxis: [{
			  				    type: 'category',     
			  				    data: [' ']
			  					}],
						    yAxis : [{
			  				    type: 'value',     
			  				    splitNumber: 1
			  				    }],
			  				series : [{
			  					name: ' ',
			  					type: 'line',
			  					data: [0]
			  					}]
			  				};
			  				
				  	require(
			                [
			                    'echarts',
			                    'echarts/chart/line',
			                    'echarts/chart/bar'
			                ],
			                
			                function (ec) {
					  			mChart = ec.init(document.getElementById('main'));
					  			window.onresize = mChart.resize;
			    				mChart.setOption(option);
			    				mChart.clear();
					  			showChartLoad();
			                }
	            	);   
			}
			
			// 显示图表加载画面
			function showChartLoad(){
				effectIndex = ++effectIndex % effect.length;
				mChart.showLoading({
				    text : '正在努力读取数据中...',
				    effect : effect[effectIndex],
				    textStyle : {
				        fontSize : 20
				    }
				});
			}
			
			// 显示图表
			function showChart(data){
				mOption = {
					    title : {
					        text: data.breedName || '历史价格图',
					        subtext: '历年数据'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					    	x: 'left' | 100,
					        data:[data.breedName]
					    },
					    toolbox: {
					        show : true,
					        orient: 'horizontal',      // 布局方式，默认为水平布局，可选为：
					                                   // 'horizontal' | 'vertical'
					        x: 'right',                // 水平安放位置，默认为全图右对齐，可选为：
					                                   // 'center' | 'left' | 'right'
					                                   // | {number}（x坐标，单位px）
					        y: 'top',                  // 垂直安放位置，默认为全图顶端，可选为：
					                                   // 'top' | 'bottom' | 'center'
					                                   // | {number}（y坐标，单位px）
					        color : ['#1e90ff','#22bb22','#4b0082','#d2691e'],
					        backgroundColor: 'rgba(0,0,0,0)', // 工具箱背景颜色
					        borderColor: '#ccc',       // 工具箱边框颜色
					        borderWidth: 0,            // 工具箱边框线宽，单位px，默认为0（无边框）
					        padding: 5,                // 工具箱内边距，单位px，默认各方向内边距为5，
					        itemGap : 20,
					        itemSize : 20,
					        showTitle: true,
					        feature : {
					            mark : {
					                show : false,
					                title : {
					                    mark : '辅助线-开关',
					                    markUndo : '辅助线-删除',
					                    markClear : '辅助线-清空'
					                },
					                lineStyle : {
					                    width : 1,
					                    color : '#1e90ff',
					                    type : 'dashed'
					                }
					            },
					            dataZoom : {
					                show : false,
					                title : {
					                    dataZoom : '区域缩放',
					                    dataZoomReset : '区域缩放-后退'
					                }
					            },
					            dataView : {
					                show : false,
					                title : '数据视图',
					                readOnly: true,
					                lang : ['数据视图', '关闭', '刷新'],
					                optionToContent: function(opt) {
					                    var axisData = opt.xAxis[0].data;
					                    var series = opt.series;
					                    var table = '<table style="width:100%;text-align:center"><tbody><tr>'
					                                 + '<td>时间</td>'
					                                 + '<td>' + series[0].name + '</td>'
					                                 + '<td>' + series[1].name + '</td>'
					                                 + '</tr>';
					                    for (var i = 0, l = axisData.length; i < l; i++) {
					                        table += '<tr>'
					                                 + '<td>' + axisData[i] + '</td>'
					                                 + '<td>' + series[0].data[i] + '</td>'
					                                 + '<td>' + series[1].data[i] + '</td>'
					                                 + '</tr>';
					                    }
					                    table += '</tbody></table>';
					                    return table;
					                }
					            },
					            magicType: {
					                show : true,
					                title : {
					                    line : '动态类型切换-折线图',
					                    bar : '动态类型切换-柱形图'
					                },
					                type : ['line', 'bar']
					            },
					            restore : {
					                show : true,
					                title : '还原',
					                color : 'black'
					            },
					            saveAsImage : {
					                show : true,
					                title : '保存为图片',
					                type : 'jpeg',
					                lang : ['点击本地保存'] 
					            },
					            myTool : {
					                show : false,
					                title : '自定义扩展方法',
					                icon : 'image://../asset/ico/favicon.png',
					                onclick : function (){
					                    alert('myToolHandler')
					                }
					            }
					        }
					    },
					    calculable : true,
					    dataZoom : {
					        show : true,
					        realtime : true,
					        start : 0,
					        end : 100
					    },
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : data.xAxis
					        }
					    ],
					    yAxis : [
					        {
					          	splitNumber: 10,
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:data.breedName,
					            type:'line',
					            data:data.series
					        }
					    ]
					};
					
					loadChart();
			}
			
			// 加载图表
			function loadChart(){
		         if(mOption != null){
		            mChart.clear();
	               	mChart.setOption(mOption); 
	               	mChart.resize();
	             }  
			}
			
			// 清除图表数据
			function clearCharData(){
				if(mChart != null){
					mChart.clear();
					mOption = null;
				}
			}
			
			// 历史价格列表
			function add_history(data) {
				var str = '';
				if (data.wx_historyPrices.length > 0) {
					if (!($('#wx_historyPrices').length > 0)) {
						str = '<dl class="dl2" id="wx_historyPrices"><dt><span class="col-red">珍药材交易</span></dt></dl>';
						$('#history_flag').before(str);
					}
					for (i = 0; i < data.wx_historyPrices.length; i++) {
					    var grade = (data.wx_historyPrices[i].grade == null) ? '' : data.wx_historyPrices[i].grade;
					    var amountUnit = (data.wx_historyPrices[i].amountUnit == null) ? '' : data.wx_historyPrices[i].amountUnit;
						str = '<dd><span class="fr">￥'
								+ data.wx_historyPrices[i].price
								+ '</span>规格：' + grade
								+ '<br /> 挂牌量：'
								+ data.wx_historyPrices[i].amount +  amountUnit + '</dd>';
						$('#wx_historyPrices').append(str);
					}
				}
				if (data.east_historyPrices.length > 0) {
					for (i = 0; i < data.east_historyPrices.length; i++) {
						if (!($('#' + data.east_historyPrices[i].ttm).length > 0)) {
							str = '<dl class="dl2" id="'+data.east_historyPrices[i].ttm+'"><dt class="time">'
									+ data.east_historyPrices[i].ttm
									+ '</dt></dl>'
							$('#history_flag').before(str);
						}
						str = '<dd><span class="history fr">￥'
								+ data.east_historyPrices[i].price
								+ '</span> 规格：'
								+ data.east_historyPrices[i].guige 
								+ '<br /> 产地：'
								+ data.east_historyPrices[i].chandi
								+ '</dd>';
						$('#' + data.east_historyPrices[i].ttm).append(str);
					}
				}
			}
			
			//今日价格列表
			function add_today(data) {
				var str = '';
				if (data.wx_todayPrices.length > 0) {
					if (!($('#wx_todayPrices').length > 0)) {
						str = '<dl class="dl2" id="wx_todayPrices"><dt><span class="col-red">珍药材交易</span></dt></dl>';
						$('#today_flag').before(str);
					}
					for (i = 0; i < data.wx_todayPrices.length; i++) {
						var grade = (data.wx_todayPrices[i].grade == null) ? '' : data.wx_todayPrices[i].grade;
					    var amountUnit = (data.wx_todayPrices[i].amountUnit == null) ? '' : data.wx_todayPrices[i].amountUnit;
					    var yc_search=$('#yc_search').val();
						str = '<dd><span class="fr">￥'
								+ data.wx_todayPrices[i].price + '</span> ';
						if(yc_search==''){
							str = str + '名称：'+data.wx_todayPrices[i].name+'<br /> ';
						}		
						str =str + '规格：'
								+ grade + '<br /> 挂牌量：'
								+ data.wx_todayPrices[i].amount + amountUnit + '</dd>';
						$('#wx_todayPrices').append(str);
					}
				}
				if (data.east_todayPrices.length > 0) {
					for (i = 0; i < data.east_todayPrices.length; i++) {
						if (!($('#' + data.east_todayPrices[i].chandi).length > 0)) {
							str = '<dl class="dl2" id="'+data.east_todayPrices[i].chandi+'"><dt class="time" id="'+data.east_todayPrices[i].chandi+'_dt">'
									+ data.east_todayPrices[i].chandi
									+ '</dt></dl>'
							$('#today_flag').before(str);
						}
						str = '<dd><span class="history fr">￥'
								+ data.east_todayPrices[i].price +'</span> ';
						var yc_search=$('#yc_search').val();
						if(yc_search==''){
							str = str + '名称：' + data.east_todayPrices[i].ycnam + '<br /> ';
						}		
						str = str + '规格：'
								+ data.east_todayPrices[i].guige + '</dd>';
						$('#' + data.east_todayPrices[i].chandi).append(str);
					}
				}
			}

			//弹层a元素事件
			$('#tabsConts').delegate('a', 'click', function() {
				$(this).parents('.pop-up').hide().animate({
					left : '100%'
				}, 100);
				$('#chandi').val($(this).text());
				return false;
			});

			//touch事件替换CLICK事件
			$('input[type=button]').touchStart(function() {
				$(this).addClass('hover');
			});
			$('input[type=button]').touchMove(function() {
				$(this).addClass('hover');
			});
			$('input[type=button]').touchEnd(function() {
				$(this).removeClass('hover');
			});
			$('input[type=button]').tapOrClick(function() {
				$(this).removeClass('hover');
			});
			
			$(window).on("orientationchange", function (event) {
		        loadChart();
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