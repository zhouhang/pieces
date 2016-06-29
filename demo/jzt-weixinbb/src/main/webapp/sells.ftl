<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <#setting url_escaping_charset='utf8'> 
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>品种行情-小珍现货</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <style>
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
        @media all and (max-width:320px){
        	.sells-box ul li{font-size:0.875em;}
        }
        
        ::-webkit-input-placeholder { /* WebKit browsers */
		    color:    #cacaca;
		}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    #cacaca;
		}
		::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    #cacaca;
		}
		:-ms-input-placeholder { /* Internet Explorer 10+ */
		    color:    #cacaca;
		}
    </style>
</head>
<body id="bodyid" style="font-size:1em;">
    <div class="sell-box-head">
        <span id="InStore" class="relative"><#if value ==null || value==''>在仓药材${tunnage}<#else>${value}<#if page.results?size lte 0>0<#else>${total}</#if></#if>吨 <i class="down"></i></span>
        <div class="sub-nav">
            <ul>
            	<li><a title="全部" href="javascript:;" data-href="/search"><i class="icon-12"></i>全部</a></li>
            	<#list categorys as category>
            		<li><a  title="${(category.CATEGORYS_NAME)!''}" href="javascript:;" data-href="/search/category/${category.ID}?type=one&value=${category.CATEGORYS_NAME?url}"><i class="icon-${category_index+1}"></i>${category.CATEGORYS_NAME}</a></li>
            	</#list>
            </ul>
        </div>
        <span class="fr" id="check">
            <a href="tel://4001054315" name="phone" tel="400">
            </a><a href="javascript:void(0);" name="check"></a>
        </span>
    </div>
    <!--当选择类目筛选，以及选择品种进行高级筛选才出现-->
    <#if flag == true>
	    <div class="tabsfixed">
	        <ul id="paixu" class="tabs tabs2 clearfix">
	        	<!--单价-->
	        	<#if sortPrice==''>
	        		<li id="priceSort" sort='desc'><span>价格<i class="up"></i></span></li>
	        	<#elseif sortPrice!=''>
	        		<#if sortPrice=='asc'>
	        			<li id="priceSort" sort='desc' class="cur"><span>价格<i class="up"></i></span></li>
	        		<#else>
	        			<li id="priceSort" sort='asc' class="cur"><span>价格<i></i></span></li>
	        		</#if>
	        	</#if>
	        	<!--库存-->
				 <#if sortListingSurplus==''>
	        		<li id='storeSort' sort='desc'><span>库存<i class="up"></i></span></li>
	        	<#elseif sortListingSurplus!=''>
	        		<#if sortListingSurplus=='asc'>
	        			<li id='storeSort' sort='desc' class="cur"><span>库存<i class="up"></i></span></li>
	        		<#else>
	        			<li id='storeSort' sort='asc' class="cur"><span>库存<i></i></span></li>
	        		</#if>
	        	</#if>
	        	<!--挂牌日期-->
	            <#if sortExamineTime==''>
	        		<li id='dateSort' sort='desc'><span>挂牌日期<i class="up"></i></span></li>
	        	<#elseif sortExamineTime!=''>
	        		<#if sortExamineTime=='asc'>
	        			<li id='dateSort' sort='desc' class="cur"><span>挂牌日期<i class="up"></i></span></li>
	        		<#else>
	        			<li id='dateSort' sort='asc' class="cur"><span>挂牌日期<i></i></span></li>
	        		</#if>
	        	</#if>
	        </ul>
	    </div>
	</#if>
    <#if flag ==''>
   	 	<div id="conts" class="sells-box" style="padding-top:4.3em;">
   	<#else>
   		<div id="conts" class="sells-box">
   	</#if> 
        <!--筛选内容列表 start-->
        <ul id="busilistings" class="clearfix">
        <#list page.results as busiListingSearchVo>
            <li data-val="${busiListingSearchVo.listingId}">
            	<a href="/detail/listingId/${busiListingSearchVo.listingId}/orderNumber/0/isneedBill/0">
	                <span class="past1 relative"><strong>${busiListingSearchVo.breedName!''}</strong>|<b>${busiListingSearchVo.grade!''},${busiListingSearchVo.origin!''}</b>
	                </span><span class="charge past1"><strong>￥${busiListingSearchVo.price}</strong></span>    
	                <div class="cont">
	                	<span>
	                	<#if busiListingSearchVo.listingPic??>
	                	<!-- ${RESOURCE_IMG_UPLOAD_WX} -->
		                  	<#assign minImg = busiListingSearchVo.listingPic?substring((busiListingSearchVo.listingPic?last_index_of("/")+1),(busiListingSearchVo.listingPic?last_index_of(".")))+"_120"+busiListingSearchVo.listingPic?substring(busiListingSearchVo.listingPic?last_index_of("."),busiListingSearchVo.listingPic?length)>
		  				  	<#assign tempurl = busiListingSearchVo.listingPic?substring(0,busiListingSearchVo.listingPic?last_index_of("/")+1)>
		                  	<img src="${RESOURCE_IMG_UPLOAD_WX}/${tempurl}${minImg}"/>
		                <#else>
		                  	<img src=""/>
		                </#if>
	                	</span>
	                    <h3>${busiListingSearchVo.title!''}</h3>
	                    <p><b>库存量：</b>${busiListingSearchVo.listingSurplus}${busiListingSearchVo.wlunitName}<br/>
                     	<b>存放仓库：</b>${busiListingSearchVo.warehouseName}</p>
	                </div>
	            </a>    
            </li>
         </#list> 
        </ul>
    </div>
    <#if page?? && page.pageNo lt page.totalPage>
        <div class="load" style="margin-top:-0.875em;" id="moreResult"></div>
    </#if>	
    <#if page.results?size lte 0>
        <div class="a-sell-list1"  style="margin-top:-7.5em;padding-left:0.8em;padding-right:0.8em;overflow-y: hidden;">
    		<div id="tishi" align="center"><i class="tishi"></i>
    		<#if value=='' && page?? && page.totalRecord gte 0>
    			<p style="color: #999;">网络加载失败 !</p>
    			<p><a class="btn-red" style="width:37%;height:2%;line-height: 1.8em;font-size:0.9em;" href="javascript:window.location.reload(true);">点击重新加载</a></p></div>
    		<#else>
	    		<p>"<#if value==''>${keyWords}<#else>${value}</#if>" 暂无现货库存</p>
	    		<p>请拨打屏幕右上方客服电话</p></div>
	    	</#if>	
	    </div>
	    <style>
	    	body{
	    		  background: #f5f5f5 none repeat scroll 0 0;
	    	}
	    </style>
    </#if> 
	<!-- 高级查询弹层 -->
    <div id="xx" class="h-demand">
        <div class="close"></div>
        <ul class="relative">
           <form>
            <li><input style="padding:0;" id="keyWords_k" onfocus="vfocus(this);"  placeholder="请输入品种名称" value="<#if keyWords!=null &&  keyWords!=''>${keyWords}</#if>"  class="input-text1" /></li>
            <#if keyWords!=null &&  keyWords!=''>
	            <li id="unit"  onClick="getWxSupplyByBreedName()">
	                <label class="col-ca"><#if grade==''||grade=='*'>请选择等级/规格<#else>${grade}</#if>
				</label><i class="allow-ri"></i>
	                <span id="breedStandardLevelConts" class="unit"></span>
	            </li>
	            <input type="hidden" id="_grade" value="${grade}"/>
	            <li id="Store"><input style="padding:0;" placeholder="请选择所在仓库" <#if warehouseName==''||warehouseName=='*'>value=""<#else>value="${warehouseName}"</#if>
	            class="input-text1" readonly="readonly"/><i class="allow-ri"></i></li>
	            <input type="hidden" id="_warehouseName" value="${warehouseName}"/>
	            <li><input style="padding:0;" type="text" onfocus="textfocus(this)" placeholder="请输入产地"
	            <#if origin==''||origin=='*'>value=""<#else>value="${origin}"</#if> id="origin_i"  class="input-text1" /></li>
	            <li class="bn"><label>价格</label></li>
	            <li class="bn"><input type="text" id="price_b" onfocus="textfocus(this)" placeholder="低价 如 :100" class="input-text2" <#if priceB==''||priceB=='*'>
						value=""
					<#else>
						value="${priceB}"
					</#if> />-<input type="text" id="price_e" onfocus="textfocus(this)" placeholder="高价 如:300" class="input-text2" <#if priceE==''||priceE=='*'>
						value=""
					<#else>
						value="${priceE}"
					</#if>	 />元</li>
            </#if>
            <label id="msg" style="float:left;margin-top:3px;margin-bottom:3px;display:none;"></label>
            <li class="bn mt1"><input class="btn-red" type="button" id="Submit" value="查 询" /></li>
          </form>
          <#--用来放置隐藏的表单(用来传递搜索的数据)-->
		  <form id="sorListForm" method="post" action="${action}">
			<input   name="categoryName" id="categoryName" type="hidden" value="${value}"/>
			<input type="hidden" id="grade" name="grade"
	             <#if grade==''||grade=='*'> 
					value=""
				 <#else>
					value="${grade}"
				</#if>
	            /><!--等级规格-->
			<input type="hidden" id="warehouseName" name="warehouseName"
	             <#if warehouseName==''||warehouseName=='*'> 
					value=""
				 <#else>
					value="${warehouseName}"
				</#if>
	            /><!--仓库-->
			<input   name="priceB" id="priceB" type="hidden" value="${priceB}"/>
			<input   name="priceE" id="priceE" type="hidden" value="${priceE}"/>
			<input   name="origin" id="origin" type="hidden" value="${origin}"/> <!--产地-->
			<input   name="sortListingSurplus" id="sortListingSurplus" type="hidden" value="${sortListingSurplus}"/>
			<input   name="sortPrice" id="sortPrice" type="hidden" value="${sortPrice}"/>
			<input   name="sortExamineTime" id="sortExamineTime" type="hidden" value="${sortExamineTime}"/>
			<input   name="keyWords" id="keyWords" type="hidden" value="${keyWords}"/>
			<input   name="type" id="type" type="hidden" value="${type}"/>
			<input   name="value" id="value" type="hidden" value="${value}"/>
			<input   name="id" id="id" type="hidden" value="${id}"/>
		</form>
        </ul>
    </div>
	<!-- 高级查询弹层end -->
	<!--产地弹层 start-->
    <div class="pop-up clearfix">
        <!--<div class="search-format">
            <p><input type="text" value="没有找到合适的产地？自己输入一个吧" /> <input type="button" value="确 定" /></p>
        </div>-->
        <ul class="tabs-nav">
            <li class="cur"><i class="icon2"></i>所在仓库<i class="arrow"></i></li>
        </ul>
        <div id="Choose" class="tabs-conts">
            <p id="warehouse" style="display: block;"></p>
        </div>
        <div class="btn-return">返 回</div>
    </div>
	<!--产地弹层 end-->

<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
<script type="text/javascript">
	//返回上一级
	$('.btn-return').on('click',function(){
		$('.pop-up').hide();
	})
	
	function vfocus(o){
		var viewH =document.body.clientHeight;      //可见高度  
        var tempH = viewH/2;                        //屏幕可见高度的一半
        var top = $("#xx").offset().top; 
        var top1 = $("#xx").position().top;
        var h =$("#xx").height(); //弹层高度
        //如果弹层显示所有查询条件则按原位置显示，如果只显示品种名称的弹层则按屏幕高度一半显示
        if(h > 150){ 
            if((top1-150) > 0){
        		$("#xx").css("top",top1);
        	}else{
        		$("#xx").css("top",'50%');
        	}
		}else{
			$("#xx").css("top",tempH);
		}
	}
	
	function textfocus(o){
		$("#xx").css("top",'');
	    var div_top = $("#xx").position().top; 
	    var top = $(o).position().top+150; 
        var viewH =document.body.clientHeight;      //可见高度  
        var contentH =document.body.scrollHeight;   //内容高度 
        var tempH = viewH/2;                        //屏幕可见高度的一半
		if(top > tempH){
			$(o).css("bottom",$(o).position().top);
		}else{
			$("#xx").css("top",div_top);
		}
    }
    
	//去掉tab中最后一个的li的竖分割线
	$("#paixu  li").last().find('span').css("border-right","0px");
	
	 //清空提交的表单的数据
	 function resetSubmitForm(){
		$(':input','#sorListForm').val('');
	 }
	
	 //查询微信供应品种信息
     function getWxSupplyByBreedName(){
        	var breedName = $('#keyWords_k').val();  //获取品种名称
        	if(breedName=='' || breedName=='请输入品种名称'){
        		return false;
        	}
			$.ajax({
				async : true,
				cache : false,
				type : "GET",
				data : {'breedName':breedName},
				dataType : "json",
				url : "/search/getWxSupplyByBreedName",
				success : function(data) {
					var ok = data.ok;
					var msg = data.msg;
					if(ok){
						var obj = data.obj;
						//ID
						var breedId = obj.breedId;
						//单位
						var qtyUnit = obj.qtyUnit;
						//等级规格
						var breedStandardLevels = obj.breedStandardLevels;
						//产地
						var breedPlaces = obj.breedPlaces;
						//加载等级规格
						$('#breedStandardLevelConts').empty();
						if($(breedStandardLevels).size()>0){
							$('#breedStandardLevelConts').append('<a href="#">全部</a>');
							$.each(breedStandardLevels,function(index,breedStandardLevel){
								$('#breedStandardLevelConts').append('<a href="#">'+breedStandardLevel+'</a>');
							});
						}
						$("#unit").children('.unit').show();
						
						//绑定下拉事件
				        $('.unit a').on('click',function(){
				            $(this).parents('li').children('label').text($(this).text());
				            $("#_grade").attr('value',$(this).text());
				            $(this).parent().hide();
				            return false;
				        });
					}else{
						$('#grade').val('');
						$('#origin').val('');
						if(msg!=undefined){
			    			$("#msg").text(msg).show();
			    		}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#grade').val('');
					$('#origin').val('');	
					tips('操作失败！');
				}
			});
        }
        
	//绑定排序按钮
	$('#paixu li').each(function() {
		$(this).click(function(){
			searchSort($(this));
		});
	});
	
	//排序
  	function searchSort($this){
		//排序字段
		if($this.attr('id')=='priceSort'){
			$('#sortListingSurplus').attr('value',''); //清除库存排序规则
			$('#sortExamineTime').attr('value','');    //清除挂牌日期排序规则
			
			if($this.attr('sort')=='asc'){
				$('#sortPrice').attr('value','asc');
				$this.attr('sort','desc');
			}else{
				$('#sortPrice').attr('value','desc');
				$this.attr('sort','asc');
			}
		}else if($this.attr('id')=='storeSort'){
			$('#sortPrice').attr('value','');           //清除价格排序规则
			$('#sortExamineTime').attr('value','');    //清除挂牌日期排序规则
			
			if($this.attr('sort')=='asc'){
				$('#sortListingSurplus').attr('value','asc');
				$this.attr('sort','desc');
			}else{
				$('#sortListingSurplus').attr('value','desc');
				$this.attr('sort','asc');
			}
		}else if($this.attr('id')=='dateSort'){
			$('#sortPrice').attr('value','');           //清除价格排序规则
			$('#sortListingSurplus').attr('value','');    //清除库存排序规则
			
			if($this.attr('sort')=='asc'){
				$('#sortExamineTime').attr('value','asc');
				$this.attr('sort','desc');
			}else{
				$('#sortExamineTime').attr('value','desc');
				$this.attr('sort','asc');
			}
		}
		//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
		var url =window.location.href;
		$("#sorListForm").attr("action",url);
		$("#sorListForm").submit();
  	}
  	
  	//信息框
    function layerMsg(msg){
        layer.open({
			content: msg,
			style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			time: 2
		});
    }
	
	//绑定筛选按钮
	$('#Submit').click(function() {
		//清空错误提示语句，且隐藏它。
		$("#msg").text('');
		$("#msg").hide();
		//清空表单
		resetSubmitForm();
		var price_b = $('#price_b').val();
		var price_e = $('#price_e').val();
		var keyWords_k = $('#keyWords_k').val();
		var origin_i = $('#origin_i').val();
		var _grade = $('#_grade').val();
		var _warehouseName =$('#_warehouseName').val();
		
		if(keyWords_k!=null && keyWords_k!=''&& keyWords_k!='请输入品种名称'){
			if((typeof(price_b)!='undefined' || typeof(price_e)!='undefined')
				&& ( price_b !='低价 如 :100' || price_e!='高价 如:300')){
				//价格验证
				var priceObj = {b:{def:'低价 如:100',value:price_b,name:'低价'}, e:{def:'高价 如:300',value:price_e,name:'高价'}};
				if(!validatePrice(priceObj)){
		    		return false;
		    	}
		     }
		}else{
			$("#msg").text("请输入品种名称").show();
			return false;
		}  
		
		$("#grade").attr('value',(_grade=='全部')?'':_grade);   //等级规格
		$("#warehouseName").attr('value',(_warehouseName=='全部')?'':_warehouseName); //仓库
		
		if(origin_i=='请输入产地'){
			$('#origin_i').attr("value","");
			$('#origin').attr("value","");
		}else{
			$('#origin').attr("value",origin_i);
		}	
		if(price_b=='低价 如 :100'){
			$('#priceB').attr("value","");
		}else{
			$('#priceB').attr("value",price_b);
		}	
		if(price_e=='高价 如:300'){
			$('#priceE').attr("value","");
		}else{
			$('#priceE').attr("value",price_e);
		}
		
		$('#keyWords').attr("value",keyWords_k);
		$("#sorListForm").attr("action","/search/senior?type=senior");
		$("#sorListForm").submit(); 
	});
			
	//分页页码
	var pageNo=2;
	//分页
	$('#moreResult').click(function(){
		if($('#moreResult').hasClass('loading')){
			return false;
		}
		$('#moreResult').addClass('loading');
		var params = "pageNo="+pageNo+"&"+$("#sorListForm").serialize();
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			data : params,
			dataType : 'json',
			url : '/search/searchByFenye',
			success : function(data) {	
				var v = data.results;
				$.each(v,function(i,listing){
					$('#moreResult').removeClass('loading');
					var html='<li data-val='+listing.listingId+'>';
            		html+='<a href="/detail/listingId/'+listing.listingId+'/orderNumber/0/isneedBill/0">';
	                html+='<span class="past1 relative">';
	                html+='<strong>'+listing.breedName+'</strong>|<b>'+listing.grade+','+listing.origin+'</b>';
	                html+='</span><span  class="charge past1"><strong>￥'+listing.price+'</strong></span>';    
	                html+='<div class="cont">';
	                html+='<span>';
	                var _pic =listing.listingPic;
	                if(_pic!=null && _pic!=''){
		                 var  minImg = listing.listingPic.substring((listing.listingPic.lastIndexOf("/")+1),(listing.listingPic.lastIndexOf(".")))+'_120'+listing.listingPic.substring(listing.listingPic.lastIndexOf("."),listing.listingPic.length);
		  				 var  tempurl = listing.listingPic.substring(0,listing.listingPic.lastIndexOf("/")+1);
		                 html+='<img src="${RESOURCE_IMG_UPLOAD_WX}/'+tempurl+minImg+'"/>';
		            }else{
		                 html+='<img src=""/>';
		            }
	                html+='</span>';
	                html+='<h3>'+listing.title+'</h3>';
	                html+='<p><b>库存量：</b>'+listing.listingSurplus+listing.wlunitName+'<br/>';
                    html+='<b>所在仓库：</b>'+listing.warehouseName+'</p>';
	                html+='</div></a></li>';
                	$("#busilistings").append(html);
				});
				pageNo++;
				if(data!=null && data.pageNo < data.totalPage){
					$("#moreResult").show();
				}else{
					$("#moreResult").hide();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#moreResult').removeClass('loading');
				tips('操作失败！');
			}
		}); 
	})

    $(function(){
    	//给规格等级span加上事件，点击它周围地方层消失
    	$(document).on("click",function(e){
			var target = $(e.target);
			if(target.closest("#breedStandardLevelConts").length == 0){
				$("#breedStandardLevelConts").hide();
			}
		})
    
    	//类目选择器
    	$('.tabs li').on('click',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $('#conts').children('ul').eq($(this).index()).show().siblings('ul').hide();
        });
        
        
        //在仓药材
        var hei = $(window).height();
        $('.sub-nav').height(hei);
        $('#InStore').on('click',function(){
        	close($('.h-demand'));
        	$(this).next('.sub-nav').css('left','-80%');
            $(this).next('.sub-nav').show().animate({left:0},150);
        });
        //类目选择
        $('.sub-nav ul li a').on('click',function(){
            $(this).parent('li').addClass('hover').siblings().removeClass('hover');
            $(this).parents('.sub-nav').css('left','0%');
            var value= $(this).attr("data-href");
            $(this).parents('.sub-nav').animate({left:'-100%'},450,function(){
            	  history.pushState('', '', value);
            	  $("#bodyid").load(value);
            });
        });

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
        
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }
        
        $('#check a[name=check]').on('click',function(){
        	$('.h-demand').show();
        	bgHiu();
        	$('.h-demand').css('height','auto');
        	$('.h-demand').css('margin-top', '-8em');
        })

        //价格、库存弹层
        $('.choose input[type=button]').on('click',function(){
            $(this).next('.select').show();
            $(this).addClass('cur');
        });

        //文本框初始值清空
        function Focus(els,text){
            els.focus(function(){
                if($(this).val() == text){
                    $(this).val('');
                }
            }).blur(function(){
                if($(this).val() == ''){
                    $(this).val(text);
                }
            })
        }
		
		//初始化隐藏等级规格
		$('#unit .unit').hide();

        //所在仓库分类层
        /*var Height = $(document).height();
        $('.pop-up').height(Height);*/
        $('#Store').on('click',function(){
        	$.ajax({
			async : false,
			cache : false,
			type : 'GET',
			dataType : 'json',
			url : '/search/getWarehouseName',
			success : function(data) {	
				$("#warehouse").append('<span><a href="#">全部</a></span>')
				$.each(data,function(i,warehouse){
					var html='';
					html+='<span><a href="#">'+warehouse.wareHouseName+'</a></span>';
                	$("#warehouse").append(html);
				});
				$('.pop-up').show().animate({left:0},300);
				//绑定事件
				$('#Choose a').on('click',function(){
		            $(this).parents('.pop-up').animate({left:'100%'},300);
		            $('#Store input').val($(this).text());
		            $('#_warehouseName').attr('value',$(this).text());
		            return false;
		        });
			 },
			 error : function(XMLHttpRequest, textStatus, errorThrown){}
		   }); 
        });

        //拨打号码
        $('input[mobiletype=call]').on('click',function(){
            var $this = $(this);
        	getMobile($this);
        });
    })
    
    //信息框
    function tips(msg){
        layer.open({
			content: msg,
			style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			time: 2
		});
    }
    
    //验证价格
	function validatePrice(priceObj){
		var reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
		for(var key in priceObj){
			if(reg.test(priceObj[key].value)){
		    	continue;
		    }else if(priceObj[key].value==priceObj[key].def){
		    	continue;
		    }else{
		    	$("#msg").text(priceObj[key].name+'可以是包含2位小数点的非负数!').show();
		    	return false;
		    }
		}
		//同时不是默认值则，判断大小 
		if(priceObj.b.value!=priceObj.b.def && priceObj.e.value!=priceObj.e.def){
			if(parseFloat(priceObj.b.value)>parseFloat(priceObj.e.value)){
	    		$("#msg").text('最低价格不能高于最高价格!').show();
	    		return false;
	    	}
		}
		return true;
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