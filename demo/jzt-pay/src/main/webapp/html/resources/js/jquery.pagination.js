/**
 * author:刘漂
 * description:ajax分页用jquery插件
 * 
 * 
 * 最简洁的调用方式如下:
 * $(function() {
		$('#XXX').pagination({
			targetId : 'XXX',
			url : '/xx/xx',
			rowHtml : function(rowData) {
				var html = "<tr>";
				html+="<td id='id"+rowData.breedId+"'>"+rowData.breedId+"</td>";
				html+="<td>"+rowData.breedName+"</td>";
				html+="<td>"+rowData.breedCode+"</td>";
				html+="<td class='opr-btn'><span class='operate-1 operate-a'>"+rowData.breedCname+"</td>";
				html+="<td class='opr-btn'><span class='operate-1 operate-a'>"+rowData.breedStandardLevel+"</td>";
				html+="<td>"+rowData.breedCountUnit+"</td>";
				html+="<td class='opr-btn'><span class='operate-1 operate-a'>"+rowData.breedPlace+"</td>";
				html+="<td class='opr-btn'><span class='operate-2'></span><span class='operate-4'></span><span class='operate-5'></span></td></tr>";
				return html;
			}
		});
 * 
 */
(function($, window, document){ 
	$.fn.pagination = function(options){ 
		var settings;
		//default settings:
		var defaults = {
			/* 必填 ,分页容器ID*/
			targetId: '',
			/* 必填 ,分页URL*/
			url: '',
			/* 必填  ,	rowData是当页所有的行数据*/
			rowHtml: function(rowData){alert('请定义列表行数据显示HTML,返回数据是:'+rowData+'!')},
			/* 选填  ,	emptyRow是当页没有行数据时候的情况。*/
			emptyRow: function(pageSettings){},
			/* 页面必加  ,	rowHeadTitleName是页面数据表行头标记,必须加.后续数据会在其后生成表格数据 */
			rowHeadTitleName: "row-head-title",		//页面行头标记
			/* 选填  ,	rowData是当页所有的行数据。 此回调函数在分页数据渲染后触发 */
			afterRefresh: function(rowsData,pageSetting){},	 //绑定事件函数,rowsData是当页所有的行数据,是数组[{},...],pageSetting是页面对象。	 
			init: true,				//是否初始化page对象数据
		    pageNo: 1,				//当前页面, 非初始化时为必填
		    totalPage: 1,			//总页面, 非初始化时为必填
		    curRecord: 0,			//当前页面记录数, 非初始化时为必填
		    totalRecord: 0,			//总记录数, 非初始化时为必填
		    pageSize: 10,			//页面最大记录数
			type: "get",			//ajax请求方法类型
			extraData: {},			//额外请求数据对象
			dataType: "json",
			ajaxSetting: {},		//外界jquery的ajax设置如： {type:'json','processData',false}等，其中会覆盖defaults中相同的ajax参数，如type,dataType		
			pageContainer: '',		//分页容器ID
			//rowTitleName: "row-title",
			//colTitleName: "col-title",
			pageTheme:	'default',			//分页模板主题,默认是default样式
			pageTemplates:	{noOverride:true}, 			//分页模板,可用外部用参数覆盖定义(详见pageTemplatesDef格式)
			//columns: {}, //{ {<col-title>:{content:<functio returned value>,events:[{<eventName>:<binded function>}]}},... }  形如 [{id:<columnId>,content:<functio returned value>,events:[{<eventName>:<binded function>}]}] ,如果不写默认是替换 columnId中的值
			bindPaginationEvents: '', //外部传入分页绑定事件可覆盖
			beforePrev:	function(){return true},	//上一页前置回调函数
			beforeNext:	function(){return true},	//上一页后置回调函数
			afterPrev:	function(){return true},	//下一页前置回调函数
			afterNext:	function(){return true},	//下一页后置回调函数
			afterFirst:	function(){return true},	//下一页后置回调函数
			afterEnd:	function(){return true}	//下一页后置回调函数
		};
		
		
		/*分页模板,可用参数自定义,也可以指定分页模板主题
		 * 注意：模板中必须用#{pageNo}表示当前页码,#{totalPage}表示总页数,#{curRecord}表示当前页记录数,#{totalRecord}表示总记录数
		 * prevSelector: 上一页元素选择器
		 * nextSelector:  下一页元素选择器
		 * firstSelector:  首页元素选择器
		 * endSelector:  末页元素选择器
		 * skipSelector:  跳转元素选择器
		 * skipInputSelector: 跳转页输入元素选择器
		 * */
		var pageTemplatesDef = {
			'default' : {
				template:	"<div class='page-file fr'><a selector='firstPage' href='javascript:;' pageNo='1'>首页</a><a selector='prevPage' href='javascript:;'>上一页</a><a selector='nextPage' href='javascript:;'>下一页</a><a selector='endPage' href='javascript:;' pageNo='#{totalPage}'>末页</a>"+"<span><a selector='goPage' href='javascript:;'>跳转</a> <input id='' type='text' value='1' class='text fy' onkeyup='' onpaste='return false;'/> 页</span>"+"<span>当前：<b>#{pageNo}/#{totalPage}</b>页</span><span>本页<b>#{curRecord}</b>条记录</span><span>全部<b>#{totalRecord}</b>条记录</span></div>",
				selectors:	{
					prevSelector: "a[selector='prevPage']",
					nextSelector: "a[selector='nextPage']",
					firstSelector:  "a[selector='firstPage']",
					endSelector:  "a[selector='endPage']",
					skipSelector:  "a[selector='goPage']",
					skipInputSelector:  '.text.fy'
				},
				events:	function(settings){
					var pageTheme = settings.pageTheme;
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['prevSelector'], 'click', prevPage);
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['nextSelector'], 'click', nextPage);
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['firstSelector'], 'click', firstPage);
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['endSelector'], 'click', endPage);
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['skipSelector'], 'click', skipPage);
				}
			},'thin'	: {
				template:	"",
				selectors:	{
					prevSelector: '',
					nextSelector:  '',
					firstSelector:  '',
					endSelector:  '',
					skipSelector:  '',
					skipInputSelector:  ''
				},
				events:	function(settings){
					//
				}
			},'detail' : {
				template:	"<div class='mt10 mb10' align='center'><a selector='prevPage' class='col_888' href='javascript:;'>上一页</a>&nbsp;&nbsp;<a selector='nextPage' class='col_888' href='javascript:;'>下一页</a></div>",
				selectors:	{
					prevSelector: "a[selector='prevPage']",
					nextSelector:  "a[selector='nextPage']"
				},
				events:	function(settings){
					var pageTheme = settings.pageTheme;
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['prevSelector'], 'click', prevPage);
					bindEvent(settings.pageTemplates[pageTheme]['selectors']['nextSelector'], 'click', nextPage);
					
				}
			}
		};
		
		/*
		 * 定义保留函数(不能被覆盖的)定义
		 */
		var funcs = {
			refresh : function(url,data,type){
				if(!url){
					alert('必须输入url');
					return;
				}
				settings.url = url;
				data = data||{pageNo:1};
				data.pageNo = 1;
				settings.extraData = data;
				getResponse(data,null,type);
			}	
		};
		
		//配置settings
		function configSettings(){
			//继承外部设置参数
			var settings = $.extend( {}, defaults, options );
			//校验必填项
			if(!settings.targetId){
				alert('分页容器ID为空,请指定一个!');
				return '';
			}else if(!settings.rowHtml){
				alert('请定义列表行数据显示HTML方法:function rowHtml(rowData),返回数据是表示行数据的json对象!');
				return '';
			}
			settings.pageContainer = settings.targetId+"_pagination";
			//todo: 矫正页面参数
			settings.pageNo = parseInt(settings.pageNo);
			settings.totalPage = parseInt(settings.totalPage);
			settings.curRecord = parseInt(settings.curRecord);
			settings.totalRecord = parseInt(settings.totalRecord);
			settings.pageSize = parseInt(settings.pageSize);
			//配置调用方法定义
			for(var funcName in funcs){
				settings[funcName] = funcs[funcName];
			}
			//配置模板主题定义
			if(settings.pageTemplates.noOverride){//外部没有覆盖则在此初始化自带的模板们
				for(var pageThemeName in pageTemplatesDef){
					settings.pageTemplates[pageThemeName] = {};
					settings.pageTemplates[pageThemeName]['template'] = pageTemplatesDef[pageThemeName]['template'];
					settings.pageTemplates[pageThemeName]['selectors'] = {};
					for(var sel in pageTemplatesDef[pageThemeName]['selectors']){
						settings.pageTemplates[pageThemeName]['selectors'][sel] = pageTemplatesDef[pageThemeName]['selectors'][sel];
					}
					settings.pageTemplates[pageThemeName]['events'] = pageTemplatesDef[pageThemeName]['events'];
				}
			}
			//校验模板主题
			settings.pageTheme = settings.pageTheme||'default';
			if(!settings.pageTemplates[settings.pageTheme]){
				alert('请定义一个已经存在的分页模板主题,主题:['+settings.pageTheme+']不存在!');
				return '';
			}
			//绑定分页事件定义方法,如果没有外部覆盖方法则取当前主题对应的绑定事件方法
			if((!settings.bindPaginationEvents) || (typeof settings.bindPaginationEvents!='function')){
				settings.bindPaginationEvents = settings.pageTemplates[settings.pageTheme]['events'];
			}
			//放入缓存
			if(!$.fn.pagination.caches[settings.targetId]){
				$.fn.pagination.caches[settings.targetId] = settings;
			}
			return settings;
		}
		
		
		//上一页
		function prevPage() {
			var curPage = settings.pageNo;
			curPage = curPage - 1;
			//执行上一页操作前自定义操作
			var flag = settings.beforePrev.call();
			if(flag){
				//执行上一页操作
				if(curPage<=0){
					curPage = 1;
					//隐藏上一页链接
				}else{
					//执行上一页操作,后执行自定义操作
					settings.extraData.pageNo = curPage;
					getResponse(settings.extraData,settings.afterPrev);
				}
			}
			settings.pageNo = curPage;
		}
		
		//下一页
		function nextPage() {
			var curPage = settings.pageNo;
			curPage = curPage + 1;
			//执行上一页操作前自定义操作
			var flag = settings.beforeNext.call();
			if(flag){
				//执行上一页操作
				if(curPage>settings.totalPage){
					curPage = settings.totalPage;
					//隐藏上一页链接
				}else{
					//执行上一页操作，后执行自定义操作
					settings.extraData.pageNo = curPage;
					getResponse(settings.extraData,settings.afterNext);
				}
			}
			settings.pageNo = curPage;
		}
		
		//首页
		function firstPage() {
			settings.pageNo = 1;
			settings.extraData.pageNo = settings.pageNo;
			getResponse(settings.extraData,settings.afterFirst);
		}
		
		//末页
		function endPage() {
			settings.pageNo = settings.totalPage;
			settings.extraData.pageNo = settings.pageNo;
			getResponse(settings.extraData,settings.afterEnd);
		}
		
		//跳转页
		function skipPage() {
			var $skipValEle = $('#'+settings.targetId).find(settings.pageTemplates[settings.pageTheme]['selectors']['skipInputSelector']);
			var page = $skipValEle.val();
            if (isNaN(page)) {
            	$($skipValEle).val('');
                alert("请输入数字!");
            }else {
            	if(page<=0){
            		page = 1;
				}else if(page>settings.totalPage){
					page = settings.totalPage;
				}
            	settings.extraData.pageNo = page;
                getResponse(settings.extraData,function(){$('#'+settings.targetId).find(settings.pageTemplates[settings.pageTheme]['selectors']['skipInputSelector']).val(settings.pageNo);});
            }
		}
		
		//page相关settings赋值
		function setPageSettings(pData){
			if(pData){
				settings.pageNo = pData.pageNo||settings.pageNo;
				settings.totalPage = pData.totalPage||settings.totalPage;
				settings.curRecord = (pData.results?pData.results.length:0)||settings.curRecord;
				settings.totalRecord = pData.totalRecord||settings.totalRecord;	
				//settings.totalPage = settings.totalRecord%settings.pageSize==0 ? settings.totalRecord/settings.pageSize : settings.totalRecord/settings.pageSize + 1;
			}
		}
		
		//获取返回数据时
		function getResponse(reqData,callback,methodType,dataType){
			var datas = reqData||{pageNo:settings.pageNo};
			if(!datas.pageNo){
				datas.pageNo = settings.pageNo;
			}
			var ajaxOptions = {
				 url: settings.ajaxSetting.url||settings.url,
				 type: methodType||settings.ajaxSetting.type||settings.type, 
				 data: datas,
				 dataType: dataType||settings.ajaxSetting.dataType||settings.dataType,
				 processData: settings.ajaxSetting.processData,
				 success:function(data){
				    //刷新数据
					refreshData(data);
					//执行回调
					if(callback && (typeof callback=='function')){
						callback(data);
					}
				 }
			};
			if(settings.ajaxSetting.contentType){
				ajaxOptions.contentType=settings.ajaxSetting.contentType;
			}
			$.ajax(ajaxOptions);
		}
		
		//查询成功刷新数据操作
		function refreshData(data){
			if(data.page){
				//设置页面参数值
				setPageSettings(data.page);
				//放置数据表html
				placeRowsHtml(data.page.results);
				if(data.page.results){
					if(data.page.results.length>0){
						//替换分页数据后绑定事件
						settings.afterRefresh(data.page.results,{"extraData":data.page.params||'',"pageNo":settings.pageNo,"totalPage":settings.totalPage,"curRecord":settings.curRecord,"totalRecord":settings.totalRecord,"pageSize":settings.pageSize});
					}else{
						//空数据自定义逻辑
						settings.emptyRow({"pageNo":settings.pageNo,"totalPage":settings.totalPage,"curRecord":settings.curRecord,"totalRecord":settings.totalRecord,"pageSize":settings.pageSize});
					}
				}else{
					//空数据自定义逻辑
					settings.emptyRow({"pageNo":settings.pageNo,"totalPage":settings.totalPage,"curRecord":settings.curRecord,"totalRecord":settings.totalRecord,"pageSize":settings.pageSize});
				}
				//放置分页html
				placePageHtml(data.page);
			}
		}
		
		//放置分页html
		function placePageHtml(pData){
			//装配分页后的分页数据
			var htmlPageStr = assemblePageHtml(pData);
			//替换分页数据
			$('#'+settings.pageContainer).html(htmlPageStr);
		}
		
		//装配分页后的分页数据
		function assemblePageHtml(pData){
			var htmlStr = settings.pageTemplates[settings.pageTheme].template;
			if(pData){
				//替换模板数据
				htmlStr = htmlStr.replace(/#{pageNo}/g, pData.pageNo);
				htmlStr = htmlStr.replace(/#{totalPage}/g, pData.totalPage);
				htmlStr = htmlStr.replace(/#{curRecord}/g, pData.results?pData.results.length:pData.curRecord);
				htmlStr = htmlStr.replace(/#{totalRecord}/g, pData.totalRecord);
			}
			return htmlStr;
		}
		
		//放置数据表html
		function placeRowsHtml(rData){
			//装配分页后的数据表数据
			var rowHtml = assembleRowsHtml(rData);
			//替换表格数据
			var th = findRowHeadHtml();
			th.nextAll().remove();
			th.parent().append(rowHtml);
		}
		
		//装配分页后的数据表数据
		function assembleRowsHtml(rowsData){
			var rowHtmlStr = '';
			if(rowsData){
				//遍历页面元素
				for(var i=0;i<rowsData.length;i++){
					rowHtmlStr+=settings.rowHtml(rowsData[i]);
				}
			}
			return rowHtmlStr;
//			if(rowsData){
//				//遍历页面元素
//				for(var i=0;i<rowsData.length;i++){
//					
//				}
//				
//
//				findColumnsHtml().each(function(index,element){//遍历页面列元素进行操作
//					$(this).attr()
//					if(settings.columns){
//						
//					}
//					if(settings.columns.length>0){//遍历columns定义进行操作
//						var colsDefLeng = settings.columns.length;
//						for(var i=0;i<colsDefLeng;i++){//形如：[{id:<columnId>,content:<functio returned value>,events:[{<eventName>:<binded function>}]}]
//							if(typeof settings.columns[i]=='object'){//
//								
//							}
//						
//						}
//					
//					}
//				
//				});
//			}
		}
		
		//获取行头html
		function findRowHeadHtml(){
			return $('#'+settings.targetId).find('['+settings.rowHeadTitleName+']');
		}
		
		//获取行html
		function findRowsHtml(){
			return $('#'+settings.targetId).find('['+settings.rowTitleName+']');
		}
		
		//获取列html数组
		function findColumnsHtml(){
			return $('#'+settings.targetId).find('['+settings.colTitleName+']');
		}
		
		
		//给容器绑定事件
		function bindEvent(cssSelector,eventStr,func){
			$('#'+settings.targetId).delegate(cssSelector, eventStr, func); 
		}
		
		// 主函数入口
		this.each(function() {
			if(typeof options=='string'){//获取对象
				if(!$.fn.pagination.caches[options]){
					alert('分页对象:'+options+'不存在请检查参数是否错误');
				}else{
					settings = $.fn.pagination.caches[options];
				}
			}else if(typeof options=='object'){//初始化对象
				// setp1. 配置settings
				settings = configSettings();
				if(!settings){
					return ;
				}
				// step2. 执行初始化逻辑
				//添加分页container
				$('#'+settings.targetId).append('<div id="'+settings.pageContainer+'"></div>');
				//如果要初始化,则初始化
				if(settings.init){
					settings.extraData.pageNo = settings.pageNo;
					getResponse(settings.extraData);
				}else{
					//放置分页html
					placePageHtml(settings);
				}
				//绑定分页操作
				settings.bindPaginationEvents(settings);
				//绑定其他事件
			}
			
		});
		
		// step3. 返回
		return settings;
	}; 
	
	$.fn.pagination.caches = {};

})(jQuery); 
