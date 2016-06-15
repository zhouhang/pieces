<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>挂牌审核</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
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
            <h1 class="title1">挂牌审核</h1>
            <form action="/busiListingCheck" method="get" id="conditionForm">
                <ul class="store-search">
                    <li><span>挂牌编号：</span><input name="listingid" value="${page.params.busiListingSearchDto.listingid!''}" class="text-store text-7" type="text" />
                      <span>标题：</span><input name="title" value="${page.params.busiListingSearchDto.title!''}" class="text-store text-7" type="text" />
                      <span>品种：</span><input name="breedname" value="${page.params.busiListingSearchDto.breedname!''}" class="text-store text-7" type="text" />
                      <span>挂牌用户信息：</span><input name="userinfo" value="${page.params.busiListingSearchDto.userinfo!''}" class="text-store text-7" type="text" />
                       </li>
                      <li><span>挂牌时间：</span><input id="wdate1" name="startlistingdate" value="${page.params.busiListingSearchDto.startlistingdate!''}" type="text" class="text-store text-7 mr10 Wdate" />至<input id="wdate2" name="endlistingdate" value="${page.params.busiListingSearchDto.endlistingdate!''}" type="text" class="text-store text-7 ml10 Wdate" />
                      <span>业务员：</span><input name="salesman" value="${page.params.busiListingSearchDto.salesman!''}" class="text-store text-7" type="text" />                 
                      <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
               <table id="busiListingTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="100">挂牌编号</th>
                        <th width="150">标题</th>
                        <th width="100">品种</th>
                        <th width="100">挂牌数量</th>
                        <th width="100">挂牌单价</th>
                        <th width="100">起订数量</th>
                        <th width="100">挂牌期限</th>
                        <th width="100">挂牌用户</th>
                        <th width="100">提供发票单价</th>
                        <th width="150">挂牌时间</th>
                        <th width="100">业务员</th>
                        <th width="150">操作</th>
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as busiListing>
	                    	<tr>
	                    		<td>
	                    			<@shiro.hasPermission name="挂牌审核-查询挂牌详情">
	                    				<a href="javascript:;" class="col_blue" name="numbers" data-id="${busiListing.listingid!'' }" data-reclick="n">${busiListing.listingid!'' }</a>
	                    			</@shiro.hasPermission>
	                    			<@shiro.lacksPermission name="挂牌审核-查询挂牌详情">
	                    				${busiListing.listingid!'' }
	                    			</@shiro.lacksPermission>
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
	                    		<td><@tools.money num=busiListing.lowunitprice format="0.##"/>元/${busiListing.dictvalue!'' }</td>
	                    		<td><@tools.money num=busiListing.minsales format="0.##"/>${busiListing.dictvalue!'' }</td>
	                    		<td>${busiListing.listingtimelimit!'' }天</td>
	                    		<td><a href="/getMemberManage/getMemberByUserName?memberName=${busiListing.username!''}">${busiListing.username!''}</a></td>
	                    		<td>
		                    		<#if busiListing.hasbill == 1>
	                    				<@tools.money num=busiListing.billprice format="0.##"/>元/${busiListing.dictvalue!'' }
	                    			</#if>
	                    		</td>
	                    		<td>${busiListing.createtime?string("yyyy-MM-dd HH:mm:ss")!'' }</td>
	                    		<td>${busiListing.salesman!'' }</td>
	                        	<td class="opr-btn">
	                        		<@shiro.hasPermission name="挂牌审核-通过挂牌">
	                    				<a title="通过" href="javascript:checkSuccessBusiListing(${busiListing.listingid})"><span class="operate-7"></span></a>
	                    			</@shiro.hasPermission>
	                    			<@shiro.hasPermission name="挂牌审核-不通过挂牌">
										<a title="不通过" href="javascript:addDialog(${busiListing.listingid})"><span class="operate-9"></span></a>
									</@shiro.hasPermission>
		                        </td>
	                    	</tr>
	                    </#list>
                    <#else>
                    	<tr>
                    		<td colspan="11">没有数据!</td>
                    	</tr>
                    </#if>
                </table>
            </div>
            <@tools.pages page=page form="conditionForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 弹层  -->
<div id="checkFailureBusiListing" style="display: none;" title="挂牌编号">
     <form id="/busiListingCheck/checkFailureBusiListingForm" action="checkFailureBusiListing" method="post" >
     	<p style="color:#656565;line-height:27px;">请填写不通过的原因：</p>
     	<input id="listingid" name="listingid" type="hidden" />
        <textarea id="examinecontent" name="examinecontent" class="text-store" style="width:415px;height:100px;resize:none;"></textarea>
        <span class="col_red2" id="checkFailureBusiListingError"></span>
    </form>
</div>

<!-- 审核不通过 -->
<div class="order-popup detail">
    <div class="close"></div>
    <h1 class="title1">挂牌详情</h1>
    <div class="sellDetail-box">
    </div>
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	//日期控件
	$('#wdate1').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#wdate2').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			minDate:'#F{$dp.$D(\'wdate1\',{d:1});}'
		});
	});
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
    
    $('td a[name=numbers]').on('click',function(){
        getListingInfo(this);
    });
    $('.close').on('click',function(){
        $('.order-popup').hide();
        $('.order-popup .sellDetail-box').empty();
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
    		url:"/busiListingCheck/listingInfo",
    		data:{listingId:listingId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				bghui();
    				$('.order-popup').show();
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
    				html += '<form class="former">';
    				html += '<label><strong>不通过原因：</strong><br/>（若通过则不必填写）</label>';
    				html += '<textarea class="text" id="popContent"></textarea><br/>';
    				html += '<label></label>';
    				html += '<span id="popErrorMsg" class="col_red2"></span><br/>';
    				html += '<input type="hidden" id="popListingId" value="' + listingId + '"/>';
    				html += '<div class="mt20"><@shiro.hasPermission name='挂牌审核-通过挂牌'><input type="button" value="通过" class="btn-blue mr10" id="auditBtn"/></@shiro.hasPermission><@shiro.hasPermission name='挂牌审核-不通过挂牌'><input type="button" value="不通过" class="btn-hui" id="rejectBtn"/></@shiro.hasPermission></div>';
    				html += '</form>';
    				html += '</div>';
    				$('.order-popup .sellDetail-box').append(html);
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
    
	function tips(str,fn){
		bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         if(fn){
                         	fn();
                         }
                     }
	               }
	            }]
	    });
	};
	//按钮审核不通过
	function addDialog(listingid){
		$('#listingid').val(listingid);
		$('#examinecontent').val('');
		$('#checkFailureBusiListingError').text('');
		$("#checkFailureBusiListing").dialog({
	      	autoOpen : true,
			modal : true,
			width : 450,
			resizable : false,
			buttons : {
				提交 : function() {
						rejectListing('listingid', 'examinecontent', 'checkFailureBusiListingError', function(){
							$("#checkFailureBusiListing").dialog('close');
							tips('操作成功！', function(){
								$('#conditionForm').submit();
							});
						});
					}
				}
	 	});
	 	$('#ui-id-1').text('挂牌编号：'+listingid);
	};
	
	$(".order-popup").on("click", "#rejectBtn", function(){
		rejectListing("popListingId","popContent","popErrorMsg",function(){
			$('.order-popup').hide();
	        $('.order-popup .sellDetail-box').empty();
	        $('.bghui').remove();
	        tips("操作成功！", function(){
	        	$('#conditionForm').submit();
	        });
		});
	})
	
	
	//审核不通过操作
	function rejectListing(listingElemId, examineContentElemId, errMsgElemId, fnOk){
		var listingId = $('#' + listingElemId).val();
		var content = $.trim($('#' + examineContentElemId).val());
		if('' == content){
			$('#' + errMsgElemId).text('原因不能为空！');
			return false;
		} else if(content.length > 400){
			$('#' + errMsgElemId).text('原因长度超出限制！');
			return false;
		}
		
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			data : {listingid:listingId,examinecontent:content},
			dataType : 'json',
			url : '/busiListingCheck/checkFailureBusiListing',
			success : function(data) {
				var ok = data.ok;
				if(ok==true){
					if(fnOk){
						fnOk();
					}
				}else{
					$('#' + errMsgElemId).text('操作失败！');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#' + errMsgElemId).text('操作失败！');
			}
		}); 
	}
	
	$(".order-popup").on("click", "#auditBtn", function(){
		$('.order-popup').hide();
        $('.bghui').remove();
		auditListing($("#popListingId").val(), function(){
			$('.order-popup .sellDetail-box').empty();
	        tips("操作成功！", function(){
	        	$('#conditionForm').submit();
	        });
		}, function(){
			$('.order-popup .sellDetail-box').empty();
	        tips("操作失败！");
		});
	})
	
	//审核通过操作
	function auditListing(listingId, fnOk, fnNo){
		$.ajax({
			async : false,
			cache : false,
			type : 'GET',
			data : {listingid:listingId},
			dataType : 'json',
			url : '/busiListingCheck/checkSuccessBusiListing',
			success : function(data) {
				var ok = data.ok;
				if(ok==true){
					if(fnOk){
						fnOk();
					}
				}else{
					if(fnNo){
						fnNo();
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				if(fnNo){
					fnNo();
				}
			}
		}); 
	}
	
	//按钮审核通过
	function checkSuccessBusiListing(listingId){
		bghui();
		Alert({
	            str: '审核通过吗？',
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         auditListing(listingId, function(){
                         	tips('操作成功！', function(){
								$('#conditionForm').submit();
							});
                         }, function(){
                         	tips('操作失败！');
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
</script>
</body>
</html>