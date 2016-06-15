<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>挂牌查询</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">挂牌查询</h1>
            <form action="/busiListingQuery" method="get" id="conditionForm">
                <ul class="store-search">               
                    <li>
                      <span>挂牌编号：</span><input name="listingid" value="${page.params.busiListingSearchDto.listingid!''}" class="text-store text-7" type="text" />
                      <span>仓单编号：</span><input name="wlid" value="${page.params.busiListingSearchDto.wlid!''}" class="text-store text-7" type="text" />
                      <span>标题：</span><input name="title" value="${page.params.busiListingSearchDto.title!''}" class="text-store text-7" type="text" />
                      <span>品种：</span><input name="breedname" value="${page.params.busiListingSearchDto.breedname!''}" class="text-store text-7" type="text" />
                      <span>挂牌用户：</span><input name="userinfo" value="${page.params.busiListingSearchDto.userinfo!''}" class="text-store text-7" type="text" />
                      <span>挂牌状态：</span><div id="select-bg"><span><select name="listingflag" class="text-7">
                        <option value="-1">全部</option>
                   		<#if listingStateMap??>
                   			<#list listingStateMap?keys as key>
                    			<option value="${key!'' }" <#if page.params.busiListingSearchDto.listingflag == key>selected</#if>>${listingStateMap[key]!'' }</option>
                    		</#list>
                   		</#if>
                      </select></span></div>
                      <span>业务员：</span><input name="salesman" value="${page.params.busiListingSearchDto.salesman!''}" class="text-store text-7" type="text" />
                   </li>
                   <li>
                    <span>挂牌期限：</span><div id="select-bg"><span><select name="listingtimelimit" class="text-7">
                        <option value="">全部</option>
                   		<#if listingtimelimitMap??>
                   			<#list listingtimelimitMap?keys as key>
                    			<option value="${key!'' }" <#if page.params.busiListingSearchDto.listingtimelimit == key>selected</#if>>${listingtimelimitMap[key]!'' }</option>
                    		</#list>
                   		</#if>
                      </select></span></div>
                    <span>挂牌时间：</span><input id="wdate1" name="startlistingdate" value="${page.params.busiListingSearchDto.startlistingdate!''}" type="text" class="text-store text-7 mr10 Wdate" />至<input id="wdate2" name="endlistingdate" value="${page.params.busiListingSearchDto.endlistingdate!''}" type="text" class="text-store text-7 ml10 Wdate" />
                    <span>到期时间：</span><input id="wdate3" name="startoverduedate" value="${page.params.busiListingSearchDto.startoverduedate!''}" type="text" class="text-store text-7 mr10 Wdate" />至<input id="wdate4" name="endoverduedate" value="${page.params.busiListingSearchDto.endoverduedate!''}" type="text" class="text-store text-7 ml10 Wdate" />
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    <@shiro.hasPermission name="挂牌查询-挂牌查询导出">
                    	<input type="button" class="btn-blue" id="exportExcel" value="导出Excel" />
                     </@shiro.hasPermission>
                  </li>
                </ul>
            </form>
        	<form action="" method="POST" id="exportForm">
        		<input type="hidden" name="listingid" value="${page.params.busiListingSearchDto.listingid!''}"  />  
        		<input type="hidden" name="wlid" value="${page.params.busiListingSearchDto.wlid!''}" />
                <input type="hidden" name="title" value="${page.params.busiListingSearchDto.title!''}" />
                <input type="hidden" name="breedname" value="${page.params.busiListingSearchDto.breedname!''}" />
                <input type="hidden" name="userinfo" value="${page.params.busiListingSearchDto.userinfo!''}"  />
                <input type="hidden" name="listingflag" value="${page.params.busiListingSearchDto.listingflag  }"/>
                <input type="hidden" name="listingtimelimit" value="${page.params.busiListingSearchDto.listingtimelimit!''  }"/>
                <input type="hidden" name="startlistingdate" value="${page.params.busiListingSearchDto.startlistingdate}" />
                <input type="hidden" name="endlistingdate" value="${page.params.busiListingSearchDto.endlistingdate}"/>
                <input type="hidden" name="startoverduedate" value="${page.params.busiListingSearchDto.startoverduedate}"  />
                <input type="hidden" name="endoverduedate" value="${page.params.busiListingSearchDto.endoverduedate}" />    
        	</form>
            <div class="use-item1" style=" margin-top:20px;">
               <table id="busiListingTable" class="table-store" width="102%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="6%">挂牌编号</th>
                        <th width="5%">仓单编号</th>
                        <th width="7%">标题</th>
                        <th width="4%">品种</th>
                        <th width="6%">挂牌数量</th>
                        <th width="7%">挂牌单价</th>
                        <th width="5%">起订数量</th>
                        <th width="3%">挂牌期限</th>
                        <th width="5%">挂牌用户</th>
                        <th width="6%">提供发票单价</th>
                        <th width="4%">挂牌状态</th>
                        <th width="10%">挂牌时间</th>
                        <th width="10%">修改时间</th>
                        <th width="10%">到期时间</th>
                        <th width="3%">是否推荐</th>
                        <th width="4%">业务员</th>
                        <th width="5%">操作</th>
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as busiListing>
	                    	<tr>
	                    		<td>
	                    			<@shiro.hasPermission name="挂牌查询-查看挂牌详情">
	                    				<a class="col_blue" name="nub" href="javascript:;" data-id="${busiListing.listingid!'' }" data-reclick="n">${busiListing.listingid!'' }</a>
	                    			</@shiro.hasPermission>
	                    			<@shiro.lacksPermission name="挂牌查询-查看挂牌详情">
	                    				${busiListing.listingid!'' }
	                    			</@shiro.lacksPermission>
	                    		</td>
	                    		<td>
	                    			<!--<a class="col_blue" name="wlidInfo" href="javascript:;" data-id="${busiListing.wlid!'' }" data-reclick="n">${busiListing.wlid!'' }</a>-->
	                    			${busiListing.wlid!'' }
	                    			
	                    		</td>
                    			<#if (busiListing.title?length > 6)>
                    				<td class="opr-btn"><span class="operate-1 operate-a">${busiListing.title?substring(0,6)} …<div class="tips tip_store" align="left"><span class="sj"></span>${busiListing.title!''}</div></span></td>
                    			<#else>
                    				<td>${busiListing.title!''}</td>
                    			</#if>
                    			<#if (busiListing.breedname?length > 3)>
                    			<td class="opr-btn"><span class="operate-1 operate-a">${busiListing.breedname?substring(0,3)} …<div class="tips tip_store" align="left"><span class="sj"></span>${busiListing.breedname!''}</div></span></td>
                    			<#else>
                    			<td>${busiListing.breedname!''}</td>
                    			</#if>
                    			<td><@tools.money num=busiListing.listingamount format="0.##"/>${busiListing.dictvalue!''}</td>
	                    		<td><@tools.money num=busiListing.lowunitprice format="0.##"/>元/${busiListing.dictvalue!''}</td>
	                    		<td><@tools.money num=busiListing.minsales format="0.##"/>${busiListing.dictvalue!''}</td>
	                    		<td>${busiListing.listingtimelimit!'' }天</td>
	                    		<td>
	                    			<a href="/getMemberManage/getMemberByUserName?memberName=${busiListing.username!''}">${busiListing.username!''}</a>
	                    		</td>
	                    		<td>
	                    			<#if busiListing.hasbill == 1>
	                    				<@tools.money num=busiListing.billprice format="0.##"/>元/${busiListing.dictvalue!'' }
	                    			</#if>
	                    		</td>
	                    		<td>
	                    			<#if busiListing.listingflag?string == '2'>
	                    				<span class="col_green">${listingStateMap[busiListing.listingflag?string]!''}</span>
	                    			</#if>
	                    			<#if busiListing.listingflag?string == '4'>
	                    				<span class="col_999">${listingStateMap[busiListing.listingflag?string]!''}</span>
	                    			</#if>
	                    			<#if busiListing.listingflag?string == '1'>
	                    				<span class="col_red">${listingStateMap[busiListing.listingflag?string]!''}</span>
	                    			</#if>
	                    		</td>
	                    		<td>${busiListing.createtime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
	                    		<td>${busiListing.updatetime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
	                    		<td>${busiListing.expiretime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
	                    		<td class="opr-btn">
		                    		<#if busiListing.listingflag?string == '2'>
		                    			<#if busiListing.isrecommend == 0>
										<#else> 
	                            		<span class="operate-8"></span>
										</#if>
									</#if>
								</td>
								<td>${busiListing.salesman!''}</td>
	                        	<td class="opr-btn">
	                        		<#if busiListing.listingflag?string == '2'>
	                    				<#if busiListing.isrecommend == 0>
			                    			<a href="javascript:isrecommendBusiListing('/busiListingQuery/okrecommendBusiListing?listingid=${busiListing.listingid!''}',1)">设为推荐</a>
										<#else> 
											<a href="javascript:isrecommendBusiListing('/busiListingQuery/norecommendBusiListing?listingid=${busiListing.listingid!''}',0)">取消推荐</a>
										</#if>
										<@shiro.hasPermission name="挂牌查询-挂牌下架">
										
										<a href="javascript:updateListingFlagDisabled('${busiListing.listingid!''}')">下架</a>
										</@shiro.hasPermission>
	                    			</#if>
	                    			<#if busiListing.listingflag?string == '1'>
	                    				<a href="javascript:;" name="fail" data-content='${busiListing.examinecontent!''}'>查看失败原因</a>
	                    			</#if>
		                        </td>
	                    	</tr>
	                    </#list>
                    <#else>
                    	<tr>
                    		<td colspan="16">没有数据!</td>
                    	</tr>
                    </#if>
                </table>
            </div>
            <@tools.pages page=page form="conditionForm"/>
         
        </div>
    </div>
<!-- pageCenter over -->
</div>

<!--挂牌详情 弹层-->
<div class="order-popup detail" id="Detail">
    <div class="close"></div>
    <h1 class="title1">挂牌详情</h1>
    <div class="sellDetail-box">
    </div>
</div>
<!--挂牌详情 弹层 end-->
<!--失败原因 弹层 start-->
<div class="order-popup fail" id="Fail">
    <div class="close"></div>
    <h1 class="title1">审核失败原因</h1>
    <p></p>
</div>
<!--失败原因 弹层 end-->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	//日期控件
	function bindDatePicker(startId,endId){
		$('#'+startId).click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				maxDate:'#F{$dp.$D(\''+endId+'\',{d:-1});}',
				readOnly:true
			});
		});
		$('#'+endId).click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				minDate:'#F{$dp.$D(\''+startId+'\',{d:1});}',
				readOnly:true
			});
		});
	}
	//绑定挂牌时间
	bindDatePicker('wdate1','wdate2');
	//绑定到期时间
	bindDatePicker('wdate3','wdate4');

	//查看挂牌详情
	function bghui(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
    $('td a[name=nub]').on('click',function(){
        getListingInfo(this);
    });
    $('#Detail .close').on('click',function(){
        $('#Detail').hide();
        $('#Detail .sellDetail-box').empty();
        $('.bghui').remove();
    });
    
    $('td a[name=fail]').on('click',function(){
    	var _content = $(this).data('content');
    	$('#Fail p').text('');
    	$('#Fail p').text(_content);
        bghui();
        $('#Fail').show();
    });
    $('#Fail .close').on('click',function(){
        $('#Fail').hide();
        $('.bghui').remove();
    });
	
	//获取挂牌详情
	function getListingInfo(obj){
		var _this = $(obj),
    		listingId = _this.data("id"),
    		reclick = _this.data("reclick");
    	if(reclick == "n") {
    		_this.data("reclick", "y");
    	} else {
    		return false;
    	}
		var imgServer = "${RESOURCE_IMG_UPLOAD}";
    	$.ajax({
    		type:"POST",
    		url:"/busiListingQuery/listingInfo",
    		data:{listingId:listingId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				bghui();
    				$('#Detail').show();
    				var listingInfo = parseJson(data.result);
    				var html = '';
    				html += '<div class="box-1 fl">';
    				html += '<ul>';
    				html += '<li><label>仓单编号：</label><span>' + listingInfo.wlid + '</span></li>';
    				html += '<li><label>标    题：</label><span>' + listingInfo.title + '</span></li>';
    				html += '<li><label>品    种：</label><span>' + listingInfo.breedname + '</span></li>';
    				html += '<li><label>仓单总量/可挂数量：</label><span>' + listingInfo.wltotal + listingInfo.dictvalue + '/' + listingInfo.wlsurplus + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>挂牌重量：</label><span>' + listingInfo.listingamount + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>挂牌价格：</label><span>' + listingInfo.lowunitprice +'元</span></li>';
    				html += '<li><label>最低起订：</label><span>' + listingInfo.minsales + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>包装规格：</label><span>' + listingInfo.packingway +'</span></li>';
    				html += '<li><label>挂牌期限：</label><span>' + listingInfo.listingtimelimit +'天</span></li>';
    				if(0 == listingInfo.hasbill){
    					html += '<li><label>能否提供发票：</label><span>不提供</span></li>';
    				} else {
    					html += '<li><label>能否提供发票：</label><span>提供</span></li>';
    					html += '<li><label>提供发票单价：</label><span>' + listingInfo.billprice + '元/' + listingInfo.dictvalue + '</span></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				html += '<div class="box-2 fl">';
    				html += '<ul>';
    				if(listingInfo.piclist && listingInfo.piclist.length > 0){
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[0].path + '"/> </span><p>散货照片</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[1].path + '"/> </span><p>细节照1</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[2].path + '"/> </span><p>细节照2</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[3].path + '"/> </span><p>细节照3</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[4].path + '"/> </span><p>包装照</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[5].path + '"/> </span><p>堆垛照</p></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				html += '<div class="box-3 clearfix">';
    				html += '<h3 class="title">药材详情</h3>';
    				html += '<p>' + listingInfo.content + '</p>';
    				html += '</div>';
    				$('#Detail .sellDetail-box').append(html);
    			} else {
    				tips(data.result);
    			}
    			_this.data("reclick", "n");
    		},
    		error:function(textStatus){
    			tips("操作失败！");
    			_this.data("reclick", "n");
    		}
    	});
	}
	
	//tipa
	var Height = $('.tips').height()+18;
	$('.opr-btn .tips').css('top',-Height);
    $('.operate-1').hover(
            function(){
                $(this).children('.tips').show();
            },
            function(){
                $(this).children('.tips').hide();
            }
    );
	function tips(str){
		bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                     }
	               }
	            }]
	    });
	};
    //是否推荐
    function isrecommendBusiListing(url,index){
    	var str = '取消推荐吗？';
    	if(index==1){
    		str = '设为推荐吗？';
    	}
    	bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         $.ajax({
							async : false,
							cache : false,
							type : 'GET',
							dataType : 'json',
							url : url,
							success : function(data) {
								var ok = data.ok;
								if(ok){
									var listingid = data.obj;
									var listingidTrs = $('#busiListingTable tr');
									$.each(listingidTrs,function(idx,obj){
										var listingidTd = $(obj).find('td:eq(0) a').text();
										if(listingidTd==listingid){
											if(url.indexOf('okrecommendBusiListing')!=-1){
												$(obj).find('td:last').prev().html('<span class="operate-8">');
												$(obj).find('td:last a:first').attr('href','javascript:isrecommendBusiListing("/busiListingQuery/norecommendBusiListing?listingid='+listingid+'",0)').text('取消推荐');
											}else if(url.indexOf('norecommendBusiListing')!=-1){
												$(obj).find('td:last').prev().html('');
												$(obj).find('td:last a:first').attr('href','javascript:isrecommendBusiListing("/busiListingQuery/okrecommendBusiListing?listingid='+listingid+'",1)').text('设为推荐');
											}
											return;
										}
									});
									tips('操作成功！');
								}else{
									tips('操作失败！');
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								tips('操作失败！');
							}
						}); 
                     }
	               }
	            }]
	    });
    };
    //下架挂牌
    function updateListingFlagDisabled(listingid){
    	bghui();
		Alert({
	            str: '确定下架吗？',
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         $.ajax({
							async : false,
							cache : false,
							type : 'GET',
							data : {'listingid':listingid},
							dataType : 'json',
							url : '/busiListingQuery/updateListingFlagDisabled',
							success : function(data) {
								var ok = data.ok;
								if(ok){
									var listingidTrs = $('#busiListingTable tr');
									$.each(listingidTrs,function(idx,obj){
										var listingidTd = $(obj).find('td:eq(0) a').text();
										if(listingidTd==listingid){
											$(obj).remove();
											var totalPageDom = $('div.page-file span:last b');
											var currentPageDom = $('div.page-file span:last').prev('span').find('b');
											var totalPageNum = Number($(totalPageDom).text())-1;
											var currentPageNum = Number($(currentPageDom).text())-1;
											if(totalPageNum>=0 && currentPageNum>=0){
												$(totalPageDom).text(totalPageNum);
												$(currentPageDom).text(currentPageNum);
											} 
											return;
										}
									});
									tips('操作成功！');
								}else{
									tips('操作失败！');
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								tips('操作失败！');
							}
						}); 
                     }
	               }
	            }]
	    });
    };
    
	function parseJson(text){
		try{
		    return JSON.parse(text);//ie 89 ff ch
		}catch(e){
		    return eval('('+text+')'); //ie7
		}
	}
	//导出excel
	$("#exportExcel").click(function(){
		$("#exportForm").attr("action","/busiListingQuery/export").submit();
	}); 
</script>
</body>
</html>