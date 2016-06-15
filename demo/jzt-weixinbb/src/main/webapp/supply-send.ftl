<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-发布供求信息</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <style type="text/css">
    	a:visited {
    		color:#111111;
    	}
		.red {
		    color:red!important;
		}
		.pic-up {
			border: none;
		}
		.loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>
</head>
<body>
    <ul id="tab_id" class="tabs clearfix">
        <li id="supply" class="cur"><a href="#supply"><span>发布供应信息</span></a> </li>
        <li id="demand" ><a href="#demand"><span>发布求购信息</span></a> </li>
    </ul>
    <div id="conts" class="charge-conts">
		<!--供应信息 start-->
	    <div id="supplyCont" class="box-layout" style="display: block;">
	    	<form id="supplyForm" action="/wxSupplySend/addWxSupply" method="post">
	            <div class="pic-upfile">
	                <ul id="wxSupplyPic">
	                    <li class="file-bg"><input type="file" name="file" class="file" accept="image/*" multiple="multiple" /></li>
	                </ul>
	            </div>
	            <ul class="supply-form">
	                <li>
	                	<input id="supplyId" name="supplyId" type="hidden" />
	                	<input id="supplyBreedId" name="breedId" type="hidden" />
	                    <input id="supplyBreedName" name="breedName" type="text" placeholder="品种名称" />
	                </li>
	                <li rel="Format">
	                    <input id="supplyBreedStandardLevel" name="breedStandardLevel" type="text" placeholder="品种规格" readonly="readonly" />
	                    <i class="format"></i>
	                </li>
	                <li class="choose relative">
	                	<input id="supplyPrice" name="price" type="text" class="text2" placeholder="价格" />
	                	<input id="supplyPriceUnit" name="priceUnit" type="hidden" value="公斤" />
	                	<input type="button" name="priceUnit" value="元/公斤" />
	                    <div class="select">
	                        <#if dicts??>
			            		<#list dicts as dict>
			            			<a href="#">元/${dict.dictValue!''}</a>
			            		</#list>
			            	</#if>
	                        <i class="allow"></i>
	                    </div>
	                </li>
	                <li class="choose relative">
	                    <input id="supplyQty" name="qty" type="text" placeholder="库存" class="text2" />
	                    <input id="supplyQtyUnit" name="qtyUnit" type="hidden" value="公斤" />
	                    <input type="button" name="qtyUnit" value="公斤" />
	                    <div class="select">
	                    	<#if dicts??>
			            		<#list dicts as dict>
			            			<a href="#">${dict.dictValue!''}</a>
			            		</#list>
			            	</#if>
	                        <i class="allow"></i>
	                    </div>
	                </li>
	                <li rel="Format">
	                    <input id="supplyBreedPlace" name="breedPlace" type="text" placeholder="品种产地" readonly="readonly" />
	                    <input id="supplyBreedPlaceId" name="placeId" type="hidden" />
		                <i class="format"></i>
	                </li>
	                <li rel="Format">
	                    <input id="supplyBreedArea" name="areaName" type="text" placeholder="货物所在地" readonly="readonly" />
	                    <input id="supplyBreedAreaId" name="areaCode" type="hidden" />
	                    <i class="format"></i>
	                </li>
	                <li class="demand"><input type="submit" class="btn-1" id="supplySubmit" value="发 布"></li>
	            </ul>
	    	</form>
	    	<#if wxSupplys??>
	    		<#if (wxSupplys?size>0)>
	    			<div id="wxSupplyBox" class="sended-box">
		    			<p>已发布的供应信息【<strong id="wxSupplyNum">${wxSupplyTotalRecordNum!'0'}</strong>】</p>
	            		<#list wxSupplys as wxSupply>
	            			<ul id="wxSupply${wxSupply.supplyId!''}">
			                    <li>
			                        <dl>
			                            <dt>名称：${wxSupply.breedName!''}</dt>
			                            <dd>规格：${wxSupply.breedStandardLevel!''}</dd>
			                            <dd>库存：${wxSupply.qty!''}${wxSupply.qtyUnit!''}</dd>
			                        </dl>
			                        <#if wxSupply.picVoList??>
			                        	<div class="pic-upfile pic-up">
							                <ul id="wxSupplyPic${wxSupply.supplyId!''}">
							                	<#list wxSupply.picVoList as pic>
							            			<li class="see"><img src="${RESOURCE_IMG_UPLOAD_WX}/${pic.thumbnailPath!''}" rel="${RESOURCE_IMG_UPLOAD_WX}/${pic.originalPath!''}" /></li>
							            		</#list>
							                </ul>
							            </div>
					            	</#if>
			                        <span class="fr">
				                        <#if wxSupply.status==0>
				                        	待审核
				                        <#elseif wxSupply.status==1>
				                        	已发布
				                        <#elseif wxSupply.status==2>
				                        	审核未通过
						            	</#if>	
			                        </span>
			                        <p class="clearfix">
			                            <span class="fl">刷新一下，排名就靠前</span>
			                            <span class="fr">
			                            	<i rel="${wxSupply.supplyId!''}"></i>	
			                            	<#if wxSupply.status==0>
				                                <i style="display:none;"></i>
				                            <#else>
				                                <i rel="${wxSupply.supplyId!''}"></i>
							            	</#if>
			                                <i rel="${wxSupply.supplyId!''}"></i>
			                            </span>
			                        </p>
			                    </li>
			                </ul>
	            		</#list>
	            		<#if (wxSupplys?size==5)>
		        			<p id="moreWxSupplys">
								<input class="btn-1" type="button" value="查看更多">
							</p>
						</#if>
  					</div>
	        	</#if>
	        </#if>
		</div>
		<!--供应信息 end-->
		<!--求购信息 start-->
	    <div id="demandCont" class="box-layout">
	    	<form id="demandForm" action="/wxSupplySend/addWxDemand" method="post">
	            <ul class="supply-form">
	                <li>
	                	<input id="demandId" name="demandId" type="hidden" />
	                	<input id="demandBreedId" name="breedId" type="hidden" />
	                    <input id="demandBreedName" name="breedName" type="text" placeholder="品种名称" />
	                </li>
	                <li rel="Format">
	                	<input id="demandBreedStandardLevel" name="breedStandardLevel" type="text" placeholder="品种规格" readonly="readonly" />
	                    <i class="format"></i>
	                </li>
	                <li class="choose relative">
	                    <input id="demandQty" name="qty" type="text" placeholder="采购数量" class="text2" />
	                    <input id="demandQtyUnit" name="qtyUnit" type="hidden" value="公斤" />
	                    <input type="button" name="qtyUnit" value="公斤" />
	                    <div class="select">
	                    	<#if dicts??>
			            		<#list dicts as dict>
			            			<a href="#">${dict.dictValue!''}</a>
			            		</#list>
			            	</#if>
	                        <i class="allow"></i>
	                    </div>
	                </li>
	                <li rel="Format">
	                    <input id="demandBreedPlace" name="breedPlace" type="text" placeholder="品种产地" readonly="readonly" />
	                    <input id="demandBreedPlaceId" name="placeId" type="hidden" />
		                <i class="format"></i>
	                </li>
	                <li rel="Format">
	                    <input id="demandBreedArea" name="areaName" type="text" placeholder="货物所在地" readonly="readonly" />
	                    <input id="demandBreedAreaId" name="areaCode" type="hidden" />
	                    <i class="format"></i>
	                </li>
	                <li class="demand" style="padding-top: 0.5em;"><input type="submit" class="btn-1" id="demandSubmit" value="发 布"></li>
	            </ul>
	        </form>
	        <#if wxDemands??>
	    		<#if (wxDemands?size>0)>
	    			<div id="wxDemandBox" class="sended-box">
		    			<p>已发布的求购信息【<strong id="wxDemandNum">${wxDemandTotalRecordNum!'0'}</strong>】</p>
	            		<#list wxDemands as wxDemand>
	            			<ul id="wxDemand${wxDemand.demandId!''}">
			                    <li>
			                        <dl>
			                            <dt>名称：${wxDemand.breedName!''}</dt>
			                            <dd>规格：${wxDemand.breedStandardLevel!''}</dd>
			                            <dd>采购数量：${wxDemand.qty!''}${wxDemand.qtyUnit!''}</dd>
			                        </dl>
			                        <span class="fr">
				                        <#if wxDemand.status==0>
				                        	待审核
				                        <#elseif wxDemand.status==1>
				                        	已发布
				                        <#elseif wxDemand.status==2>
				                        	审核未通过
						            	</#if>	
			                        </span>
			                        <p class="clearfix">
			                            <span class="fl">刷新一下，排名就靠前</span>
			                            <span class="fr">
			                            	<i rel="${wxDemand.demandId!''}"></i>	
			                            	<#if wxDemand.status==0>
				                                <i style="display:none;"></i>
				                            <#else>
				                                <i rel="${wxDemand.demandId!''}"></i>
							            	</#if>
			                                <i rel="${wxDemand.demandId!''}"></i>
			                            </span>
			                        </p>
			                    </li>
			                </ul>
	            		</#list>
	            		<#if (wxDemands?size==5)>
		        			<p id="moreWxDemands">
								<input class="btn-1" type="button" value="查看更多">
							</p>
						</#if>		
					</div>
	        	</#if>
	        </#if>
       	</div>
		<!--求购信息 end-->
	</div>
	<!--产地弹层 start-->
    <div class="pop-up clearfix">
        <div class="search-format">
            <p><input type="text" placeholder="没有找到合适的？自己填写吧！" /><input type="button" value="确 定" /></p>
        </div>
        <ul class="tabs-nav" id="tabsNav">
        	<li id="breedStandardLevelTab"><i class="icon1"></i>规格<i class="arrow"></i></li>
            <li id="breedPlaceTab" class="cur"><i class="icon2"></i>产地<i class="arrow"></i></li>
            <li id="areaNameTab"><i class="icon3"></i>所在地<i class="arrow"></i></li>
        </ul>
        <div id="tabsConts" class="tabs-conts">
        	<p id="breedStandardLevelConts"></p>
            <p id="breedPlaceConts" style="display: block;">
            	<#if places??>
            		<#list places as place>
            			<span><a href="#" rel="${place.code!''}">${place.name!''}</a></span>
            		</#list>
            	</#if>
            </p>
            <p id="areaNameConts">
                <#if areas??>
            		<#list areas as area>
            			<span><a href="#" rel="${area.code!''}">${area.name!''}</a></span>
            		</#list>
            	</#if>
            </p>
        </div>
        <div class="btn-return">返 回</div>
    </div>
	<!--产地弹层 end-->
	<!--查看图片弹层 start-->
	<div class="seebox">
		<div class="bigpic"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif" /></div>
	    <div class="smallpic"><ul id="wxSupplyPicPop"></ul></div>
	</div>
	<div class="see-close"><img src="${RESOURCE_IMG_WX}/images/seeClose.png" /></div>
	<!--查看图片弹层 end-->
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/flipsnap.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
<script type="text/javascript">
	//返回上一级
	$('.btn-return').on('click',function(){
		$('.pop-up').hide();
	})

	//去掉tab中最后一个的li的竖分割线
	$("#tab_id  li").last().find('span').css("border-right","0px");

    $(function(){
    	var layor;
    	var pos = 'supply';
    	var url = window.location.href;
        if(url.indexOf('#')!=-1){
        	pos = url.substring(url.indexOf('#')+1);
        	if(pos!='supply' && pos!='demand'){
   				pos = 'supply';
        	}
        	$('#'+pos).addClass('cur').siblings().removeClass('cur');
            $('#'+pos+'Cont').show().siblings().hide();
        }
        //信息框
        function layerMsg(msg){
        	layer.open({
			    content: msg,
			    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			    time: 2
			});
        };
        //加载层
        function layerLoad(msg){
        	layer.open({
			    type: 2,
			    content: msg
			});
        };
        //文字缩放
        function fontResize(id){
        	$(id+' div.sended-box').each(function(i){
				$(this).find('ul li dl').each(function(j){
					while(true){
			        	var dlHeight = $(this).height();
			        	var dtHeight = $(this).find('dt').height();
			        	var dd1Height = $(this).find('dd').first().height();
			        	var dd2Height = $(this).find('dd').last().height();
			        	//alert(dlHeight+"："+dtHeight+"/"+dd1Height+"/"+dd2Height);
			        	if(dtHeight+dd1Height>dlHeight || dtHeight+dd2Height>dlHeight){
			        		var dlFontSize = parseInt($(this).css('font-size'));
			        		$(this).css('font-size',dlFontSize-1 +'px');
			        	}else{
			        		break;
			        	}
		        	}
		        });
	        });
        };
        fontResize('#'+pos+'Cont');
        //菜单切换
        $('.tabs li').on('click',function(){
        	pos = $(this).attr('id');
			if(url.indexOf('#')!=-1){
	        	url = url.substring(0,url.indexOf('#')+1)+pos;
	        }else{
	        	url = url+'#'+pos;
	        }
        	window.location.href=url;
            $(this).addClass('cur').siblings().removeClass('cur');
            $('#conts').children('div').eq($(this).index()).show().siblings().hide();

            fontResize('#'+pos+'Cont');
        });
        //背景变灰
        function bgHui(){
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
        };
		//击活改变边框色
        $(':input[type=text]').click(function(){
            $(this).parent().css('borderColor','#f59e73');
        }).blur(function(){
            $(this).parent().css('borderColor','#cfcfcf');
        });
        //touch事件替换CLICK事件
        $('input[type=button],.pop-up2 a,.sended-box ul li i').touchStart(function () {
            $(this).addClass('hover');
        });
        $('input[type=button],.pop-up2 a,.sended-box ul li i').touchMove(function () {
            $(this).addClass('hover');
        });
        $('input[type=button],.pop-up2 a,.sended-box ul li i').touchEnd(function () {
            $(this).removeClass('hover');
        });
        $('input[type=button],.pop-up2 a,.sended-box ul li i').tapOrClick(function () {
            $(this).removeClass('hover');
        });
       //击活改变边框色
        $(':input[type=text]').touchStart(function(){$(this).parent().css('borderColor','#f59e73')});
        $(':input[type=text]').touchMove(function(){$(this).parent().css('borderColor','#f59e73')});
        $(':input[type=text]').touchEnd(function(){$(this).parent().css('borderColor','#cfcfcf')});
        $(':input[type=text]').tapOrClick(function(){$(this).parent().css('borderColor','#f59e73')});
        $(document).touchStart(function(e){
            var target  = $(e.target);
            if(target.closest(".select,.choose input[type=button]").length == 0){
                $('.select').hide();
                $('.choose input[type=button]').removeClass('cur');
            }
            e.stopPropagation();
        });
        //
        $('.select').each(function(){
           $(this).children('a:last').css('border','none');
        })
        //价格、库存单位弹层
        $('.choose input[type=button]').on('click',function(){
            $(this).next('.select').show();
            $(this).addClass('cur');
           // bgHui();
        });
        //选择单位
        $('.select a').on('click',function(){
            var unit = $(this).text();
            if(unit.indexOf('/')!=-1){
            	unit = unit.substring(unit.indexOf('/')+1);
            }
            $(this).parent().prev().val($(this).text()).prev().val(unit);
            $(this).parent().prev().removeClass('cur');
            $(this).parent().hide();
            return false;
        });
        $(document).bind("click",function(e){
            var target  = $(e.target);
            if(target.closest(".pop-up2,.choose input[type=button]").length == 0){
                $(this).find('.select').hide();
                $('.choose input[type=button]').removeClass('cur');
                //$('.bghui').remove();
            }
            e.stopPropagation();
        });
		//点击弹层输入框ID
		var formatValId;
        //产地分类层
        /*var Height = $(document).height();
        $('.pop-up').height(Height);*/
        //弹层匹配
        $('li[rel=Format]').on('click',function(){
        	var formatVal = $(this).find('input[type=text]').first();
        	formatValId = '#'+$(formatVal).attr('id');
        	var formatValName = $(formatVal).attr('name');
        	$('.search-format input[type=text]').val($(formatVal).val());
        	$('#'+formatValName+'Tab').click();
        	if(pos=='supply'){
        		//加载微信供应品种信息
	        	getWxSupplyByBreedName();
        	}
        	if(pos=='demand'){
        		//加载微信求购品种信息
	        	getWxDemandByBreedName();
        	}
            $('.pop-up').show().animate({left:0},100);
        });
		//弹层点击切换
        $('#tabsNav>li').on('click',function(){
            $(this).addClass('cur').show().siblings().removeClass('cur').hide();
            $('#tabsConts p').eq($(this).index()).show().siblings().hide();
        });
        //自定义填写值
        $('.search-format input[type=button]').on('click',function(){
        	var textVal = $(this).prev('input[type=text]').val();
        	$(formatValId).removeClass('red').val(textVal);
            $('.pop-up').hide().animate({left:'100%'},100);
        });
		//弹层点击值
        $('#tabsConts').on('click','a',function(){
        	var aName = $(this).text();
        	$(formatValId).removeClass('red').val(aName);
        	var aId = $(this).attr('rel');
        	if(aId!=undefined){
        		$(formatValId+"Id").val(aId);
        	}
            $(this).parents('.pop-up').hide().animate({left:'100%'},100);
            return false;
        });
//<!--微信供应信息 start-->
		//小图拖动
		function slidePic(){
			$('li.see').each(function(index){
	        	var id = $(this).parent('ul').attr('id');
	        	if(id!=undefined){
	        		var maxNum = $(this).siblings().length+1;
			        Flipsnap('#'+id,{
			            distance:98,
			            maxPoint:maxNum
			        });
	        	}
	        });
		}
        slidePic();
        //微信供应信息查看图片
        $('.sended-box').on('click','li.see',function(){
        	$('.seebox .smallpic ul').html($(this).parent().html());
        	$('.seebox .smallpic ul li').eq($(this).index()).addClass('cur');
        	
        	slidePic();
        	
        	$('.seebox').show();
            $('.see-close').show();
            bgHui();
            
	        $('.bigpic img').attr('src','${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif');
        	//加载图片
        	var img = new Image();
        	var bigPic = $(this).children('img').attr('rel'); 
	    	img.onload = function(){
	    		$('.bigpic img').attr('src',bigPic);
	    	};
	    	img.onerror = function(){
	    		layerMsg('图片加载失败！');
	    	};	
	    	img.src = bigPic;
        });
        //微信供应信息关闭查看
        $('.see-close').on('click',function(){
            $('.seebox').hide();
            $(this).hide();
            $('.bghui').remove();
        });
        //微信供应信息切换图片
        $('.seebox').on('click','.smallpic ul li',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $('.bigpic img').attr('src','${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif');
        	//加载图片
        	var img = new Image();
        	var bigPic = $(this).children('img').attr('rel'); 
	    	img.onload = function(){
	    		$('.bigpic img').attr('src',bigPic);
	    	};
	    	img.onerror = function(){
	    		layerMsg('图片加载失败！');
	    	};	
	    	img.src = bigPic;
        });
		//微信供应信息删除图片
		$('.pic-upfile ul').on('click','li i',function(){
			var pic = $(this).parent('li');
			var supplyId = $('#supplyId').val();
			var supplyPicIds = $(this).attr('rel');
			if(supplyId!='' && supplyPicIds!=''){
				$.ajax({
					async : true,
					cache : false,
					type : 'GET',
					data : {'supplyId':supplyId,'supplyPicIds':supplyPicIds},
					dataType : 'json',
					url : '/wxSupplySend/deleteWxSupplyPicById',
					success : function(data) {
						var ok = data.ok;
						var msg = data.msg;
						if(ok){
							$(pic).remove();
							$('.file-bg').show();
							
							slidePic();
						}else{
							if(msg!=undefined){
				    			layerMsg(msg);
				    		}else{
				    			layerMsg('网络繁忙，请稍后再试！');
				    		}
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						layerMsg('操作失败！');
					}
				}); 
			}else{
				$(pic).remove();
				$('.file-bg').show();
				
				slidePic();
			}
        });
		//微信供应信息上传图片
		$('.file').fileupload({
			url: '/wxSupplySend/uploadPic',
			autoUpload: true,
			singleFileUploads: false,
	        dataType: 'json',
	        acceptFileTypes: /(\.|\/)(gif|jpe?g|png|bmp)$/i,
	        maxFileSize: 5000000,
	        messages: {
                acceptFileTypes: '不支持的文件类型！',
                maxFileSize: '不支持文件大小超过5M的图片！'
            }
	    }).on('fileuploadsubmit', function (e, data) {
	       	var filesLength = data.files.length;
	       	var picImgsLength = $(this).parent('li').prevAll('li').find('img').length;
	       	//layerMsg(filesLength+"/"+picImgsLength);
			if(filesLength+picImgsLength>6 || picImgsLength>6){
				layerMsg("最多添加6张图片！");
				return false;
			}
			$.each(data.files, function (index,file) {
	           $('.file-bg').before('<li class="see"><img class="uploading" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"><i rel=""></i></li>');
	        });
	        
	        slidePic();
	        
	        picImgsLength = $(this).parent('li').prevAll('li').find('img').length;
	       	if(picImgsLength==6){
				$('.file-bg').hide();
			}
	    }).on('fileuploadprocessalways', function (e, data) {
	        if(data.files.error){
	        	layerMsg(data.files[0].error);
			}
	    }).on('fileuploaddone', function (e, data) {
	    	//layerMsg(JSON.stringify(data.result))
	    	var picImgs = $('.file-bg').prevAll('li').find('img.uploading');
        	var ok = data.result.ok;
        	if(ok){
        		var picPaths = data.result.obj;
        		$.each(picPaths, function (index,picPath) {
        			picPath = '${RESOURCE_IMG_UPLOAD_WX}/'+picPath;
        			//加载图片
		        	var img = new Image();
			    	img.onload = function(){
			    		$(picImgs[index]).removeClass('uploading').attr('src',picPath).after('<input type="hidden" name="picPath" value="'+picPath+'" />');
			    	};
			    	img.onerror = function(){
			    		layerMsg('图片加载失败！');
			    	};	
			    	img.src = picPath;
		        });
			}
			var msg = data.result.msg;
			if(msg!=undefined){
				layerMsg(msg);
			}
	    }).on('fileuploadfail', function (e, data) {
	        layerMsg('图片上传失败！');
	    });
        //验证微信供应信息表单
	    var supplyForm = $("#supplyForm").Validform({
		    tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj;
					cssctl(objtip,o.type);
					//objtip.text(msg);
					if(o.type==2){
						$(objtip).removeClass('red');
					}else if(o.type==3){
						$(objtip).val('');
						$(objtip).addClass('red');
					}
					$(objtip).attr('placeholder',msg);
				}	
			},
		    ajaxPost:true,
		    showAllError:true,
		    beforeSubmit:function(curform){
		    	//验证图片上传状态
		    	var uploadingNum = $('.pic-upfile').find('img.uploading').length;
		    	if(uploadingNum>0){
		    		layerMsg('请等待图片上传完成再发布！');
		    		return false;
		    	}
		    	//验证当前提交状态
		    	var supplyId = $('#supplyId').val();
		    	if(supplyId!=''){
		    		//更新微信供应信息
		    		var picImgsLength = $('.file-bg').prevAll('li').length;
		    		if(picImgsLength==0){
		    			layerMsg('最少添加1张图片！');
		    			return false;
		    		}
		    		$("#supplyForm").attr('action','/wxSupplySend/updateWxSupply');
		    	}else{
		    		//新增微信供应信息
		    		var picPathsLength = $('#supplyForm input[name=picPath][value!=""]').length;
		    		if(picPathsLength==0){
		    			layerMsg('您需要添加图片才能发布信息！');
		    			return false;
		    		}else if(picPathsLength>6){
		    			layerMsg('最多添加6张图片！');
		    			return false;
		    		}
		    		$("#supplyForm").attr('action','/wxSupplySend/addWxSupply');
		    	}
	    		$('#supplySubmit').val('发 布 中').attr('disabled','disabled');
		    },
		    callback:function(data){
		    	$('#supplySubmit').val('发 布').removeAttr('disabled');
		    	var ok = data.ok;
		    	var msg = data.msg;
		    	if(ok){
		    		layer.open({
					    content: msg,
					    shadeClose: false,
					    style: 'text-align:center;',
					    btn: ['OK'],
					    yes: function(index){
						    layer.close(index);
						    window.location.reload(true);
						}
					});
		    	}else{
		    		if(msg!=undefined){
		    			layerMsg(msg);
		    		}else{
		    			layerMsg('网络繁忙，请稍后再试！');
		    		}
		    	}
		    }
		});
		supplyForm.addRule([
		    {
		        ele:"#supplyBreedName",
		        datatype:"*1-200",
		        nullmsg:"请填写品种名称！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"品种名称"
		    },
		    {
		        ele:"#supplyBreedStandardLevel",
		        datatype:"*1-200",
		        nullmsg:"请填写品种规格！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"品种规格"
		    },
		    {
		        ele:"#supplyPrice",
		        datatype:"n1-10",
		        nullmsg:"请填写价格！",
		        errormsg:"请填写1到10位数字！",
		        sucmsg:"价格"
		    },
		    {
		        ele:"#supplyPriceUnit",
		        datatype:"*1-2",
		        nullmsg:"请选择价格单位！",
		        errormsg:"请填写1到2位任意字符！",
		        sucmsg:"价格单位"
		    },
		    {
		        ele:"#supplyQty",
		        datatype:"n1-10",
		        nullmsg:"请填写库存！",
		        errormsg:"请填写1到10位数字！",
		        sucmsg:"库存"
		    },
		    {
		        ele:"#supplyQtyUnit",
		        datatype:"*1-2",
		        nullmsg:"请选择库存单位！",
		        errormsg:"请填写1到2位任意字符！",
		        sucmsg:"库存单位"
		    },
		    {
		        ele:"#supplyBreedPlace",
		        datatype:"*1-200",
		        nullmsg:"请填写品种产地！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"品种产地"
		    },
		    {
		        ele:"#supplyBreedArea",
		        datatype:"*1-200",
		        nullmsg:"请填写货物所在地！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"货物所在地"
		    }
		]);
        //产地信息
        var breedPlaceConts = $('#breedPlaceConts').html();
        //查询微信供应品种信息
        function getWxSupplyByBreedName(){
        	var breedName = $('#supplyBreedName').val();
        	if(breedName==''){
        		$('#breedStandardLevelConts').empty();
				$('#breedPlaceConts').html(breedPlaceConts);
        		return;
        	}
			$.ajax({
				async : true,
				cache : false,
				type : "GET",
				data : {'breedName':breedName},
				dataType : "json",
				url : "/wxSupplySend/getWxSupplyByBreedName",
				success : function(data) {
					var ok = data.ok;
					var msg = data.msg;
					if(ok){
						var obj = data.obj;
						//ID
						var breedId = obj.breedId;
						//单位
						var qtyUnit = obj.qtyUnit;
						//规格
						var breedStandardLevels = obj.breedStandardLevels;
						//产地
						var breedPlaces = obj.breedPlaces;
						//加载ID
						$('#supplyBreedId').val(breedId);
						//加载单位
						//if(qtyUnit!=undefined && qtyUnit!=''){
						//	$('#supplyPriceUnit').val(qtyUnit).next().val('元/'+qtyUnit);
						//	$('#supplyQtyUnit').val(qtyUnit).next().val(qtyUnit);
						//}
						//加载规格
						$('#breedStandardLevelConts').empty();
						if($(breedStandardLevels).size()>0){
							$.each(breedStandardLevels,function(index,breedStandardLevel){
								$('#breedStandardLevelConts').append('<span><a href="#">'+breedStandardLevel+'</a></span>');
							});
						}
						//加载产地
						$('#breedPlaceConts').empty();
						if($(breedPlaces).size()>0){
							$.each(breedPlaces,function(index,breedPlace){
								$('#breedPlaceConts').append('<span><a href="#">'+breedPlace+'</a></span>');
							});
						}else{
							$('#breedPlaceConts').html(breedPlaceConts);
						}
					}else{
						$('#supplyBreedId').val('');		
						$('#breedStandardLevelConts').empty();
						$('#breedPlaceConts').html(breedPlaceConts);
						if(msg!=undefined){
			    			layerMsg(msg);
			    		}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#supplyBreedId').val('');		
					$('#breedStandardLevelConts').empty();
					$('#breedPlaceConts').html(breedPlaceConts);						
					layerMsg('操作失败！');
				}
			});
        };
		//查看更多的微信供应信息
		var wxSupplyPageNo = 2;
		$('#moreWxSupplys').click(function(){
			if($('#moreWxSupplys input[type=button]').hasClass('loading')){
				return false;
			}
			$('#moreWxSupplys input[type=button]').val('').addClass('loading');
			$.ajax({
				async : true,
				cache : false,
				type : 'GET',
				data : {'pageNo':wxSupplyPageNo},
				dataType : 'json',
				url : '/wxSupplySend/getMoreWxSupplys',
				success : function(data) {
					var ok = data.ok;
					var msg = data.msg;
					if(ok){
						$('#moreWxSupplys input[type=button]').val('查看更多').removeClass('loading'); 
						var wxSupplys = data.wxSupplys;
						var wxSupplysLength = wxSupplys.length;
						if(wxSupplysLength>0){
							$.each(wxSupplys,function(index,wxSupply){
								var supplyId = wxSupply.supplyId;
								var breedName = wxSupply.breedName;
								var breedStandardLevel = wxSupply.breedStandardLevel;
								var qty = wxSupply.qty;
								var qtyUnit = wxSupply.qtyUnit;
								var picVoList = wxSupply.picVoList;
								var status = wxSupply.status;
								
								var moreWxSupplys = '<ul id="wxSupply'+supplyId+'"><li><dl><dt>名称：'+breedName+'</dt><dd>规格：'+breedStandardLevel+'</dd><dd>库存：'+qty+qtyUnit+'</dd></dl><div class="pic-upfile pic-up"><ul id="wxSupplyPic'+supplyId+'">';
				                if(picVoList!=null){
					                $.each(picVoList,function(index,pic){
					                	var thumbnailPath = pic.thumbnailPath;
					                	var originalPath = pic.originalPath;
										moreWxSupplys = moreWxSupplys + '<li class="see"><img src="${RESOURCE_IMG_UPLOAD_WX}/'+thumbnailPath+'" rel="${RESOURCE_IMG_UPLOAD_WX}/'+originalPath+'" /></li>';
									});
								}
								moreWxSupplys = moreWxSupplys + '</ul></div><span class="fr">';        
								if(status==0){
									moreWxSupplys = moreWxSupplys + '待审核';
								}else if(status==1){
									moreWxSupplys = moreWxSupplys + '已发布';
								}else if(status==2){
									moreWxSupplys = moreWxSupplys + '审核未通过';
								}
								moreWxSupplys = moreWxSupplys + '</span><p class="clearfix"><span class="fl">刷新一下，排名就靠前</span><span class="fr"><i rel="'+supplyId+'"></i>';	
				                if(status==0){
									moreWxSupplys = moreWxSupplys + '<i style="display:none;"></i>';
								}else{
									moreWxSupplys = moreWxSupplys + '<i rel="'+supplyId+'"></i>';
								}
								moreWxSupplys = moreWxSupplys + '<i rel="'+supplyId+'"></i></span></p></li></ul>';      
								$('#moreWxSupplys').before(moreWxSupplys);
								
								slidePic();
							});
							if(wxSupplysLength<5){
								$('#moreWxSupplys').hide();
							}
							++wxSupplyPageNo;
						}else{
							$('#moreWxSupplys').hide();
						}
						fontResize('#supplyCont');
					}else{
						if(msg!=undefined){
			    			layerMsg(msg);
			    		}else{
			    			layerMsg('网络繁忙，请稍后再试！');
			    		}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#moreWxSupplys input[type=button]').val('查看更多').removeClass('loading');
					layerMsg('操作失败！');
				}
			});
		});
		//操作微信供应信息
		$('#wxSupplyBox').on('click','.clearfix .fr i',function(){
			var i = $(this).index();
			var msg;
			if(i==0){
				msg = '撤销';
			}else if(i==1){
				msg = '编辑';
			}else if(i==2){
				msg = '刷新';
			}else{
				layerMsg('操作失败！');
				return;
			}
			var supplyId = $(this).attr('rel');
			layer.open({
			    content: '确定'+msg+'供应信息吗？',
			    btn: ['确定', '取消'],
			    shadeClose: false,
			    style: 'text-align:center;',
			    yes: function(index){
			    	layer.close(index);
					if(i==0){
						$.ajax({
							async : true,
							cache : false,
							type : 'GET',
							data : {'supplyId':supplyId},
							dataType : 'json',
							url : '/wxSupplySend/deleteWxSupply',
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									$('#wxSupply'+supplyId).remove();
									var wxSupplyNum = Number($('#wxSupplyNum').text()) - 1;
									if(wxSupplyNum>=0){
										$('#wxSupplyNum').text(wxSupplyNum);
										if(wxSupplyNum==0){
											$('#wxSupplyBox').hide();
										}
									}
								}
								if(msg!=undefined){
					    			layerMsg(msg);
					    		}else{
					    			layerMsg('网络繁忙，请稍后再试！');
					    		}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								layerMsg('操作失败！');
							}
						}); 
					}else if(i==1){
						$.ajax({
							async : true,
							cache : false,
							type : 'GET',
							data : {'supplyId':supplyId},
							dataType : 'json',
							url : '/wxSupplySend/getWxSupply',
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									//清空图片
									$('.file-bg').prevAll('li').remove();
									var wxSupply = data.wxSupply;
									$.each(wxSupply,function(key,value){
										//重置表单
										$('#supplyForm input[name='+key+']').val('');
										if(value!=null){
											//表单赋值
											$('#supplyForm input[name='+key+']').removeClass('red').val(value);
											//图片填充
											if(key=='picVoList'){
												var pics = value;
												if($(pics).length==6){
													$('.file-bg').hide();
												}
												$.each(pics,function(idx,pic){
													var thumbnailPicId = pic.thumbnailPicId;
													var originalPicId = pic.originalPicId;
													var thumbnailPath = '${RESOURCE_IMG_UPLOAD_WX}/'+pic.thumbnailPath;
													$('.file-bg').before('<li class="see"><img src="'+thumbnailPath+'"><i rel="'+thumbnailPicId+'-'+originalPicId+'"></i></li>');
												});
												slidePic();
											}
										}
									});
									//跳转到表单
									$('html,body').animate({scrollTop: $('#supplyForm').offset().top}, 200);
								}
								if(msg!=undefined){
					    			layerMsg(msg);
					    		}else{
					    			layerMsg('网络繁忙，请稍后再试！');
					    		}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								layerMsg('操作失败！');
							}
						}); 
					}else if(i==2){
						$.ajax({
							async : true,
							cache : false,
							type : 'GET',
							data : {'supplyId':supplyId},
							dataType : 'json',
							url : '/wxSupplySend/refreshWxSupply',
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									$('#wxSupply'+supplyId).prevAll().last().after($('#wxSupply'+supplyId));
								}
								if(msg!=undefined){
					    			layerMsg(msg);
					    		}else{
					    			layerMsg('网络繁忙，请稍后再试！');
					    		}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								layerMsg('操作失败！');
							}
						}); 
					}else{
						layerMsg('操作失败！');
						return;
					}
			    }
			});
		});
//<!--微信供应信息 end-->	

//<!--微信求购信息 start-->	
		//验证微信供应信息表单
	    var demandForm = $("#demandForm").Validform({
		    tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj;
					cssctl(objtip,o.type);
					//objtip.text(msg);
					if(o.type==2){
						$(objtip).removeClass('red');
					}else if(o.type==3){
						$(objtip).val('');
						$(objtip).addClass('red');
					}
					$(objtip).attr('placeholder',msg);
				}	
			},
		    ajaxPost:true,
		    showAllError:true,
		    beforeSubmit:function(curform){
		    	//验证当前提交状态
		    	var demandId = $('#demandId').val();
		    	if(demandId!=''){
		    		//更新微信求购信息
		    		$("#demandForm").attr('action','/wxDemandSend/updateWxDemand');
		    	}else{
		    		//新增微信求购信息
		    		$("#demandForm").attr('action','/wxDemandSend/addWxDemand');
		    	}
	    		$('#demandSubmit').val('发 布 中').attr('disabled','disabled');
		    },
		    callback:function(data){
		    	$('#demandSubmit').val('发 布').removeAttr('disabled');
		    	var ok = data.ok;
		    	var msg = data.msg;
		    	if(ok){
		    		layer.open({
					    content: msg,
					    shadeClose: false,
					    style: 'text-align:center;',
					    btn: ['OK'],
					    yes: function(index){
						    layer.close(index);
						    window.location.reload(true);
						}
					});
		    	}else{
		    		if(msg!=undefined){
		    			layerMsg(msg);
		    		}else{
		    			layerMsg('网络繁忙，请稍后再试！');
		    		}
		    	}
		    }
		});
		demandForm.addRule([
		    {
		        ele:"#demandBreedName",
		        datatype:"*1-200",
		        nullmsg:"请填写品种名称！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"品种名称"
		    },
		    {
		        ele:"#demandBreedStandardLevel",
		        datatype:"*1-200",
		        nullmsg:"请填写品种规格！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"品种规格"
		    },
		    {
		        ele:"#demandQty",
		        datatype:"n1-10",
		        nullmsg:"请填写采购数量！",
		        errormsg:"请填写1到10位数字！",
		        sucmsg:"采购数量"
		    },
		    {
		        ele:"#demandQtyUnit",
		        datatype:"*1-2",
		        nullmsg:"请选择采购数量单位！",
		        errormsg:"请填写1到2位任意字符！",
		        sucmsg:"采购数量单位"
		    },
		    {
		        ele:"#demandBreedPlace",
		        datatype:"*1-200",
		        nullmsg:"请填写品种产地！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"品种产地"
		    },
		    {
		        ele:"#demandBreedArea",
		        datatype:"*1-200",
		        nullmsg:"请填写货物所在地！",
		        errormsg:"请填写1到200位任意字符！",
		        sucmsg:"货物所在地"
		    }
		]);
		//查看更多的微信求购信息
		var wxDemandPageNo = 2;
		$('#moreWxDemands').click(function(){
			if($('#moreWxDemands input[type=button]').hasClass('loading')){
				return false;
			}
			$('#moreWxDemands input[type=button]').val('').addClass('loading');
			$.ajax({
				async : true,
				cache : false,
				type : 'GET',
				data : {'pageNo':wxDemandPageNo},
				dataType : 'json',
				url : '/wxDemandSend/getMoreWxDemands',
				success : function(data) {
					$('#moreWxDemands input[type=button]').val('查看更多').removeClass('loading');
					var ok = data.ok;
					if(ok){
						var wxDemands = data.wxDemands;
						var wxDemandsLength = wxDemands.length;
						if(wxDemandsLength>0){
							$.each(wxDemands,function(index,wxDemand){
								var demandId = wxDemand.demandId;
								var breedName = wxDemand.breedName;
								var breedStandardLevel = wxDemand.breedStandardLevel;
								var qty = wxDemand.qty;
								var qtyUnit = wxDemand.qtyUnit;
								var status = wxDemand.status;
								
								var moreWxDemands = '<ul id="wxDemand'+demandId+'"><li><dl><dt>名称：'+breedName+'</dt><dd>规格：'+breedStandardLevel+'</dd><dd>采购数量：'+qty+qtyUnit+'</dd></dl>';
								moreWxDemands = moreWxDemands + '<span class="fr">';        
								if(status==0){
									moreWxDemands = moreWxDemands + '待审核';
								}else if(status==1){
									moreWxDemands = moreWxDemands + '已发布';
								}else if(status==2){
									moreWxDemands = moreWxDemands + '审核未通过';
								}
								moreWxDemands = moreWxDemands + '</span><p class="clearfix"><span class="fl">刷新一下，排名就靠前</span><span class="fr"><i rel="'+demandId+'"></i>';	
				                if(status==0){
									moreWxDemands = moreWxDemands + '<i style="display:none;"></i>';
								}else{
									moreWxDemands = moreWxDemands + '<i rel="'+demandId+'"></i>';
								}
								moreWxDemands = moreWxDemands + '<i rel="'+demandId+'"></i></span></p></li></ul>';      
								$('#moreWxDemands').before(moreWxDemands);
								
								slidePic();
							});
							if(wxDemandsLength<5){
								$('#moreWxDemands').hide();
							}
							++wxDemandPageNo;
						}else{
							$('#moreWxDemands').hide();
						}
						fontResize('#demandCont');
					}else{
						var msg = data.msg;
						if(msg!=undefined){
			    			layerMsg(msg);
			    		}else{
			    			layerMsg('网络繁忙，请稍后再试！');
			    		}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#moreWxDemands input[type=button]').val('查看更多').removeClass('loading');
					layerMsg('操作失败！');
				}
			}); 
		});
		//操作微信求购信息
		$('#wxDemandBox').on('click','.clearfix .fr i',function(){
			var i = $(this).index();
			var msg;
			if(i==0){
				msg = '撤销';
			}else if(i==1){
				msg = '编辑';
			}else if(i==2){
				msg = '刷新';
			}else{
				layerMsg('操作失败！');
				return;
			}
			var demandId = $(this).attr('rel');
			layer.open({
			    content: '确定'+msg+'求购信息吗？',
			    btn: ['确定', '取消'],
			    shadeClose: false,
			    style: 'text-align:center;',
			    yes: function(index){
			    	layer.close(index);
					if(i==0){
						$.ajax({
							async : true,
							cache : false,
							type : 'GET',
							data : {'demandId':demandId},
							dataType : 'json',
							url : '/wxDemandSend/deleteWxDemand',
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									$('#wxDemand'+demandId).remove();
									var wxDemandNum = Number($('#wxDemandNum').text()) - 1;
									if(wxDemandNum>=0){
										$('#wxDemandNum').text(wxDemandNum);
										if(wxDemandNum==0){
											$('#wxDemandBox').hide();
										}
									}
								}
								if(msg!=undefined){
					    			layerMsg(msg);
					    		}else{
					    			layerMsg('网络繁忙，请稍后再试！');
					    		}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								layerMsg('操作失败！');
							}
						}); 
					}else if(i==1){
						$.ajax({
							async : true,
							cache : false,
							type : 'GET',
							data : {'demandId':demandId},
							dataType : 'json',
							url : '/wxDemandSend/getWxDemand',
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									var wxDemand = data.wxDemand;
									$.each(wxDemand,function(key,value){
										//重置表单
										$('#demandForm input[name='+key+']').val('');
										if(value!=null){
											//表单赋值
											$('#demandForm input[name='+key+']').removeClass('red').val(value);
										}
									});
									//跳转到表单
									$('html,body').animate({scrollTop: $('#demandForm').offset().top}, 200);
								}
								if(msg!=undefined){
					    			layerMsg(msg);
					    		}else{
					    			layerMsg('网络繁忙，请稍后再试！');
					    		}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								layerMsg('操作失败！');
							}
						}); 
					}else if(i==2){
						$.ajax({
							async : true,
							cache : false,
							type : 'GET',
							data : {'demandId':demandId},
							dataType : 'json',
							url : '/wxDemandSend/refreshWxDemand',
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok){
									$('#wxDemand'+demandId).prevAll().last().after($('#wxDemand'+demandId));
								}
								if(msg!=undefined){
					    			layerMsg(msg);
					    		}else{
					    			layerMsg('网络繁忙，请稍后再试！');
					    		}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								layerMsg('操作失败！');
							}
						}); 
					}else{
						layerMsg('操作失败！');
						return;
					}
			    }
			});
		});
		//查询微信求购品种信息
        function getWxDemandByBreedName(){
        	var breedName = $('#demandBreedName').val();
        	if(breedName==''){
				$('#breedStandardLevelConts').empty();
				$('#breedPlaceConts').html(breedPlaceConts);
        		return;
        	}
			$.ajax({
				async : true,
				cache : false,
				type : "GET",
				data : {'breedName':breedName},
				dataType : "json",
				url : "/wxDemandSend/getWxDemandByBreedName",
				success : function(data) {
					var ok = data.ok;
					var msg = data.msg;
					if(ok){
						var obj = data.obj;
						//ID
						var breedId = obj.breedId;
						//规格
						var breedStandardLevels = obj.breedStandardLevels;
						//产地
						var breedPlaces = obj.breedPlaces;
						//加载ID
						$('#demandBreedId').val(breedId);
						//加载规格
						$('#breedStandardLevelConts').empty();
						if($(breedStandardLevels).size()>0){
							$.each(breedStandardLevels,function(index,breedStandardLevel){
								$('#breedStandardLevelConts').append('<span><a href="#">'+breedStandardLevel+'</a></span>');
							});
						}
						$('#breedPlaceConts').empty();
						//加载产地
						if($(breedPlaces).size()>0){
							$('#breedPlaceConts').empty();
							$.each(breedPlaces,function(index,breedPlace){
								$('#breedPlaceConts').append('<span><a href="#">'+breedPlace+'</a></span>');
							});
						}else{
							$('#breedPlaceConts').html(breedPlaceConts);
						}
					}else{
						$('#demandBreedId').val('');
						$('#breedStandardLevelConts').empty();
						$('#breedPlaceConts').html(breedPlaceConts);
						if(msg!=undefined){
							layerMsg(msg);
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#demandBreedId').val('');
					$('#breedStandardLevelConts').empty();
					$('#breedPlaceConts').html(breedPlaceConts);
					layerMsg('操作失败！');
				}
			});
        };
//<!--微信求购信息 end-->
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