<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>公共系统-微信系统</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">

    <style>
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>

</head>
<body>

	<!-- 顶部 -->
	<div class="sell-box-head">
	    <a href="/WxBoss/manager"><i class="back"></i></a>
        <span class="fr" id="addCheck">
            <a href="#1" name="add" tel="400">
            </a><a href="#2" name="check"></a>
        </span>
        <span class="fr" id="searchM">
            <a href="#2" name="check"></a>
        </span>
	</div>
	
	<!-- tab -->
    <div class="tabsfixed">
        <ul id="tab_id" class="tabs tabs2 clearfix">
            <li id="tab_activity" class="cur"><span>活动管理</span></li>
            <li id="tab_ad"><span>广告管理</span></li>
            <li id="tab_opinion"><span class="na">意见管理</span></li>
        </ul>
    </div>

	<!-- 列表 -->
    <div id="conts" class="sells-box system">
        <!--活动列表 -->
        <ul id="activityList">
            <div id="moreActivity" style="display:none" class="load" onclick="queryActivity()"></div>
            <p id="noActivity" style="display:none; text-align: center; padding:0px 0px 15px">没有找到符合条件的活动！</p>
        </ul>
        
        <!--广告列表-->
        <ul id="adList">
            <div id="moreAd" style="display:none" class="load" onclick="queryAd()"></div>
            <p id="noAd" style="display:none; text-align: center; padding:0px 0px 15px">没有找到符合条件的广告！</p>
        </ul>
        
        <!--意见列表-->
        <ul id="opinionList">
            <div id="moreOpinion" style="display:none" class="load" onclick="queryOpinion()"></div>
            <p id="noOpinion" style="display:none; text-align: center; padding:0px 0px 15px">没有找到符合条件的意见！</p>
        </ul>
    </div>

	<!-- 活动高级查询弹层 -->
	<div class="h-demand" id="activityChecker"  style="height: 16em; margin-top: -8em;" onclick="closeUnitLayer()">
	    <div class="close"></div>
	    <ul class="relative">
		    <form id="queryActivityForm">
		        <li><input name="name" placeholder="请输入名称" class="input-text1" /></li>
		        <li class="relative" name="unit">
		            <label id="queryActivityType" class="col-ca">类型</label><i class="allow-ri"></i>
		                <span class="unit">
		                	<a href="#">全部</a>
	                    	<#if wxActivityTypeMap??>
	                   			<#list wxActivityTypeMap?keys as key>
	                   				<a href="#">${wxActivityTypeMap[key]!''}</a>
	                    		</#list>
	                   		</#if>	
		                </span>
		        </li>
		        <li class="bn pa"><label>创建时间</label></li>
		        <li class="bn">
		            <input type="text" id="activityWdate1" name="startWxActivityDate" class="input-text2" readonly="readonly" />
		             — 
		            <input type="text" id="activityWdate2" name="endWxActivityDate" class="input-text2" readonly="readonly" />
	            </li>
		        <li class="bn mt1" style="margin-top: 1.5em;"><input class="btn-red" type="button" id="queryActivitySubmit" value="查 询" onclick="queryActivity(true)"/></li>
		    </form>
	    </ul>
	</div>
	<!-- 活动高级查询弹层end -->
	
	<!-- 添加和修改活动弹层 -->
    <div class="h-demand auto" id="addActive" onclick="closeUnitLayer()">
        <div class="close"></div>
        <form id="activityForm" action="" method="post">
	        <ul class="relative">
	            <li>
	            	<input placeholder="名称"  id="name"  name="name" class="input-text1" />
	            	<input type="hidden" id="activityId" name="activityId" />
	            </li>
	            <li class="relative" name="unit">
	                <label id="activityFormType" class="col-ca">类型</label><i class="allow-ri"></i>
	                <input id="type" name="type" type="hidden" value=""/>
	                <span class="unit">
	                	<#if wxActivityTypeMap??>
	               			<#list wxActivityTypeMap?keys as key>
	               				<a href="#">${wxActivityTypeMap[key]!''}</a>
	                		</#list>
	               		</#if>	
	                </span>
	            </li>
	            <li><input placeholder="排序号" id="sortno" name="sortno"  class="input-text1" /></li>
	            <li><input placeholder="文字路径" id="url" name="url" class="input-text1" /></li>
	            <li><input placeholder="图片路径" id="picUrl" name="picUrl"  class="input-text1" /></li>
	            <li class="bn pa"><label>简介</label></li>
	            <li class="bn" style="height: 12em;">
	                <div>
	                    <textarea name="memo" id="memo" class="beizhu" size="500" maxlength="500" theme="simple" onblur="check()" onkeyup="checkLength(this)" accesskey="1" tabindex="11" placeholder="输入文本"></textarea>
	                    <div class="cl">
	                        <p id="subjectchk">
	                            可输入
	                            <strong id="checklen">500</strong>
	                            字
	                        </p>
	                        <span id="postNameRule" class="spn_flag_1" style="display:none"></span>
	                    </div>
	                </div>
	            </li>
	            
	            <!-- 验证表单提示语 -->
	        	<li style="height: 1.5em; margin-top: 0.5em; border: 0 none;"><span id="activityMsg" style="margin-left: 0.4em;display: none;color:black;"></span></li>
	            
	            <li class="bn mt1"><input class="btn-red" type="submit" id="addActivitySubmit" value="提 交" /></li>
	        </ul>
        </form>
    </div>
	<!-- 添加和修改活动弹层end -->


	<!-- 广告高级查询弹层 -->
	<div class="h-demand" id="adChecker"  style="height: 16em; margin-top: -8em;" onclick="closeUnitLayer()">
	    <div class="close"></div>
	    <ul class="relative">
		    <form id="queryAdForm">
		        <li><input placeholder="请输入标题" name="adTitle"  class="input-text1" /></li>
		        <li class="relative" name="unit">
		            <label id="queryAdType" class="col-ca">类型</label><i class="allow-ri"></i>
		                <span class="unit">
		                	<a href="#">全部</a>
	                    	<#if wxAdTypeMap??>
	                   			<#list wxAdTypeMap?keys as key>
	                   				<a href="#">${wxAdTypeMap[key]!''}</a>
	                    		</#list>
	                   		</#if>	
		                </span>
		        </li>
		        <li class="bn pa"><label>创建时间</label></li>
		        <li class="bn">
		            <input type="text" id="adWdate1" name="startWxAdDate" class="input-text2" readonly="readonly" />
		             — 
		            <input type="text" id="adWdate2" name="endWxAdDate" class="input-text2" readonly="readonly" />
		        </li>
		        <li class="bn mt1" style="margin-top: 1.5em;"><input class="btn-red" type="button" id="queryAdSubmit" value="查 询" onclick="queryAd(true)"/></li>
		    </form>
	    </ul>
	</div>
	<!-- 广告高级查询弹层end -->

    <!-- 添加和修改广告弹层 -->
    <div class="h-demand" id="addAD" style="height: 19.5em; margin-top: -8em;" onclick="closeUnitLayer()">
        <div class="close"></div>
        <form id="adForm" action="" method="post">
            <ul class="relative">
                <input type="hidden" id="adId" name="adId" />
                <li><input placeholder="标题" id="adTitle" name="adTitle"  class="input-text1" /></li>
                <li class="relative" name="unit">
                    <label id="adFormType" class="col-ca">类型</label><i class="allow-ri"></i>
                    <input id="adType" name="adType" type="hidden" value=""/>
	                <span class="unit">
                        <#if wxAdTypeMap??>
                            <#list wxAdTypeMap?keys as key>
                                <a href="#">${wxAdTypeMap[key]!''}</a>
                            </#list>
                        </#if>
                    </span>
                </li>
                <li><input placeholder="位置名称" id="adPostionName" name="adPostionName"  class="input-text1" /></li>
                <li><input placeholder="内容地址" id="adMemo" name="adMemo"  class="input-text1" /></li>
                <li><input placeholder="链接地址" id="adUrl" name="adUrl"  class="input-text1" /></li>

                <!-- 验证表单提示语 -->
                <li style="height: 1.5em; border: 0 none;"><span id="adMsg" style="margin-left: 0.4em;display: none;color:black;"></span></li>

                <li class="bn mt1"><input class="btn-red" type="submit" id="addAdSubmit" value="提 交" /></li>
            </ul>
        </form>
    </div>
    <!-- 添加和修改广告弹层end -->


    <!-- 意见高级查询弹层 -->
    <div class="h-demand" id="opinionChecker"  style="height: 13em; margin-top: -8em;" >
        <div class="close"></div>
        <ul class="relative">
            <form id="queryOpinionForm">
                <li><input placeholder="请输入联系人" name="userName"  class="input-text1" /></li>
                <li class="bn pa"><label>创建时间</label></li>
                <li class="bn">
                    <input type="text" id="opinionWdate1" name="startDate" class="input-text2" readonly="readonly" />
                    —
                    <input type="text" id="opinionWdate2" name="endDate" class="input-text2" readonly="readonly" />
                </li>
                <li class="bn mt1" style="margin-top: 1.5em;"><input class="btn-red" type="button" id="queryOpinionSubmit" value="查 询" onclick="queryOpinion(true)"/></li>
            </form>
        </ul>
    </div>
    <!-- 意见高级查询弹层end -->


    <!-- 处理意见弹层 -->
    <div class="h-demand" id="proIdea">
        <div class="close"></div>
            <dl class="idea">
                <dd><span class="relative">姓名：</span><span id="opName"></span></dd>
                <dd><span>手机号码：</span><span id="opPhone"></span></dd>
                <dd><span>意见描述：</span><span id="opMemo"></span></dd>
                <dd style="padding-top: 1em;" id="opCheck" onclick="checkOpinion()">
                    <input id="opId" type="hidden" value=""/>
                    <input class="btn-red" type="button" id="opSubmit" value="提 交" />
                </dd>
            </dl>
    </div>
    <!-- 处理意见弹层end -->


	<!-- 查看图片  -->
	<div class="seebox">
	    <div class="bigpic"><img src=""></div>
	    <div class="smallpic">
	        <ul style="transition-property: transform; -webkit-transition-property: transform; transition-timing-function: cubic-bezier(0, 0, 0.25, 1); -webkit-transition-timing-function: cubic-bezier(0, 0, 0.25, 1); transition-duration: 350ms; -webkit-transition-duration: 350ms; transform: translate3d(0px, 0px, 0px);">
	        </ul>
	    </div>
	</div>
	<div class="see-close"><img src="${RESOURCE_IMG_WX}/images/seeClose.png"> </div>
	<!-- 查看图片 over  -->

<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js" type="text/javascript"></script>

<!-- 公共交互  -->
<script>
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }


    $(function(){
        // tab切换
        $('.tabs li').on('click mouseover',function(){
            scrollTo(0,0);

            $(this).addClass('cur').siblings().removeClass('cur');
            $('#conts').children('ul').eq($(this).index()).show().siblings('ul').hide();
            if($('.tabs li:last-child').hasClass('cur')){
                $('#addCheck').hide();
                $('#searchM').show();
            }
            else{
                $('#addCheck').show();
                $('#searchM').hide();
            }

            var tab = $('.cur').attr('id');
            if(tab == 'tab_ad'){
                if(($('#moreAd').prevAll().length == 0) && (!$('moreAd').hasClass('loading')) && ($("#noAd").is(":hidden"))){
                    queryAd(true);
                }
            }else if(tab == 'tab_opinion'){
                if(($('#moreOpinion').prevAll().length == 0) && (!$('moreOpinion').hasClass('loading')) && ($("#noOpinion").is(":hidden"))){
                    queryOpinion(true);
                }
            }
        });


        // 显示高级查询弹层
        $($('#addCheck a[name=check]')).on('click', function(){
            var tab = $('.cur').attr('id');
            if(tab == 'tab_activity'){
                $('#activityChecker').show();
            }else if(tab == 'tab_ad'){
                $('#adChecker').show();
            }

            bgHiu();
        });


        // 显示添加弹层
        $($('#addCheck a[name=add]')).on('click', function(){
            var tab = $('.cur').attr('id');
            if(tab == 'tab_activity'){
                addActivity();
            }else if(tab == 'tab_ad'){
                addAd();
            }
        });


        // 关闭层
        function close(els){
            els.on('click',function(){
                closeLayer();
            })
        }
        close($('.close'));
        close($('#queryActivitySubmit'));
        close($('#queryAdSubmit'));
        close($('#queryOpinionSubmit'));


        // 显示下拉选择
        $('[name=unit]').on('click',function(){
            $(this).children('.unit').show();

            var event = window.event || arguments.callee.caller.arguments[0];
            event.cancelBubble = true;
        });


        // 下拉选择框选择子项
        $('.unit a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            return false;
        });

    });

    // 查看图片
    function seePic(picUrlList){
        if(picUrlList == '' || picUrlList =='null'){
            layerMsg('暂无图片！');
            return;
        }

        var picUrls = picUrlList.split(",");

        $('.smallpic ul').empty();
        $.each(picUrls, function(i, picUrl){
            if(i == 0){
                $('.bigpic img').attr('src', picUrl);
                $('.smallpic ul').append('<li class="cur"><img src="' + picUrl + '" rel="' + picUrl + '"></li>');
            }else{
                $('.smallpic ul').append('<li><img src="' + picUrl + '" rel="' + picUrl + '"></li>');
            }
        });

        $('.seebox').show();
        $('.see-close').show();
        bgHiu();

        // 图片点击切换
        $('.smallpic ul li').on('click',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $('.bigpic img').attr('src',$(this).children('img').attr('rel'));
        });
    };
    $('.see-close').on('click',function(){
        $('.seebox').hide();
        $(this).hide();
        $('.bghui').remove();
    });

    //小图拖动
    Flipsnap('.smallpic ul',{
        distance:98,
        maxPoint:7
    });


    //touch事件替换CLICK事件
    $('.img,.opr .icon1,.opr .icon2').touchStart(function () {
        $(this).addClass('hover');
    });
    $('.img,.opr .icon1,.opr .icon2').touchMove(function () {
        $(this).addClass('hover');
    });
    $('.img,.opr .icon1,.opr .icon2').touchEnd(function () {
        $(this).removeClass('hover');
    });
    $('.img,.opr .icon1,.opr .icon2').tapOrClick(function () {
        $(this).removeClass('hover');
    });


    //提示框
    function layerMsg(msg){
        layer.open({
            content: msg,
            style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
            time: 2
        });
    };


    //关闭弹层
    function closeLayer(){
        $('.h-demand').hide();
        $('.bghui').remove();
    };


    // 关闭下拉选择框
    function closeUnitLayer(){
        $('[name=unit] .unit').hide();
    }


    // 高级查询日期控件
    $(function(){
        function setDatePicker(date1, date2){
            $('#' + date1).click(function(){
                WdatePicker({
                    maxDate:'#F{$dp.$D(' + date2 + ',{d:-1});}',
                    readOnly:true
                });
            });
            $('#' + date2).click(function(){
                WdatePicker({
                    minDate:'#F{$dp.$D(' + date1 + ',{d:1});}',
                    readOnly:true
                });
            });
        }
        setDatePicker('activityWdate1', 'activityWdate2');
        setDatePicker('adWdate1', 'adWdate2');
        setDatePicker('opinionWdate1', 'opinionWdate2');
    });


    // 显示信息提示框
    function showMessageDialog(message){
        var Alert = '<div class="alert">' +  message + '</div>';
        $('body').append(Alert);

        setTimeout('$(".alert").remove();', 1000);
    }


    // 关闭删除对话框
    function closeDeleteDialog(){
        $('.bghui').remove();
        $('.aRemove').remove();
    }
</script>
<script>
    // 只要键盘一抬起就验证编辑框中的文字长度，最大字符长度可以根据需要设定
    function checkLength(obj)  {
        var maxChars = 500;//最多字符数
        var curr = maxChars - obj.val().length;
        if( curr > 0 ){
            document.getElementById("checklen").innerHTML = curr.toString();
        }else{
            document.getElementById("checklen").innerHTML = '0';
        }
    }
</script>
<!-- 公共交互 end -->


<!-- 初始化交互  -->
<script>
    // 活动类型
    var activityTypeMap = null;

    // 广告类型
    var adTypeMap = null;


    // 初始化操作
    $(function(){
        //去掉tab中最后一个的li的竖分割线
        $("#tab_id  li").last().find('span').css("border-right","0px");

        // 关闭下拉选择框
        closeUnitLayer();

        // 查询活动和广告类型
        $.ajax({
            async : false,
            cache : false,
            type : "GET",
            dataType : "json",
            contentType:"application/x-www-form-urlencoded:charset=UTF-8",
            url : "/Boss/wxBossWeiXinSys/getType",
            success : function(data) {
                if(data != undefined){
                    activityTypeMap = data.wxActivityTypeMap;
                    adTypeMap = data.wxAdTypeMap;
                }
            }
        });

        // 显示首屏活动
        queryActivity(true);
    })
</script>
<!-- 初始化交互 end  -->


<!-- 活动管理交互  -->
<script>
	// 分页标识
	var activityPageNo = 1;

		
    // 查询活动，isReload 是否重载页面
    function queryActivity(isReload){
    	// 加载效果
		if($('moreActivity').hasClass('loading')){
			return false;
		}
    	$('#moreActivity').addClass('loading');
    	
    	if(isReload){
            activityPageNo = 1;
    	}else{
    		$('#moreActivity').show();
            activityPageNo++;
    	}
    	
    	var params = "pageNo=" + activityPageNo + "&"+ $("#queryActivityForm").serialize();
    	
    	// 类型参数
		var activityType = $('#queryActivityType').text();
		var typeParam = getActivityTypeKey(activityType);
		if(typeParam != undefined){
			params += "&type=" + typeParam;
		}

    	$.ajax({
			async : true,
			cache : false,
			type : "GET",
			data : params,
			dataType : "json",
			contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			url : "/WxBoss/activityManager/getActivity",
			success : function(data) {
				$('#moreActivity').removeClass('loading');
				
				if(isReload){
					$('#moreActivity').prevAll().remove();
                    scrollTo(0,0);
				}

                var page = data.page;
				var activitys = page.results;
				
				// 查询无数据，并且是重载页面，则显示无结果提示
				if((activitys == null || activitys.length == 0) && isReload){
					$('#noActivity').show();
				}else{
					$('#noActivity').hide();
				}

				// 当前页码数小于总页码数，则显示“加载更多”
				if(activityPageNo < page.totalPage){
                    $('#moreActivity').show();
				}else{
                    $('#moreActivity').hide();
				}
				
		    	$.each(activitys, function(i, activity){
		    		// 设置日期
					var wlrkDate = activity.createTime;
					var wlrkDateYMD = '';
					if(wlrkDate != null){
						wlrkDate = new Date(wlrkDate);
						var wlrkDateMonth = wlrkDate.getMonth() + 1;
						if(wlrkDateMonth < 10){
							wlrkDateMonth = "0" + wlrkDateMonth;
						}
						var wlrkDateDate = wlrkDate.getDate();
						if(wlrkDateDate < 10){
							wlrkDateDate = "0" + wlrkDateDate;
						}
						wlrkDateYMD = wlrkDate.getFullYear() + '-' + wlrkDateMonth + '-' + wlrkDateDate;
					}
					
					// 设置图片
					var picUrlList = new Array();
					picUrlList[0] = activity.picUrl;
					
		    		var htmlStr = '';
					htmlStr += '<li id="activityItem' + activity.activityId + '">';
					htmlStr += '<span class="img relative" onclick="seePic(\'' + picUrlList + '\')"><img src="' + activity.picUrl + '" /><i></i></span>';
					htmlStr += '<div class="cont">';
					htmlStr += '<h3>' + activity.name + '</h3>';
					htmlStr += '<p><b>类型：</b>' + getActivityTypeValue(activity.type) + '<br/>';
					htmlStr += '<b>创建人：</b>' + activity.creater + '<br/>';
					htmlStr += '<b>创建时间：</b>' + wlrkDateYMD + '</p>';
					htmlStr += '</div>';
					htmlStr += '<div class="clearfix"></div>';
					htmlStr += '<div class="opr" align="right">';
					htmlStr += '<i class="icon1" onclick="updateActivity(' + activity.activityId + ')"></i>';
					htmlStr += '<i class="icon2" onclick="showDeleteActivityDialog(' + activity.activityId + ')"></i>';
					htmlStr += '</div>';
					htmlStr += '</li>';
					
		    		$('#moreActivity').before(htmlStr);
		    	});

			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#moreActivity').removeClass('loading');
				layerMsg('操作失败！');
			}
		});
    }
    
    
    //添加活动
    function addActivity(){
    	resetActivityForm();
    	$("#activityForm").attr("action", "/WxBoss/activityManager/addActivity");
    	showActivityForm();
    };
    
    
    // 更新活动
    function updateActivity(activityId){
    	$.ajax({
			async : false,
			cache : false,
			type : "GET",
			data : {"activityId" : activityId},
			dataType : "json",
			url : "/WxBoss/activityManager/getActivityById",
			success : function(data) {
				var ok = data.ok;
				if(ok == true){
					var obj = data.obj;

					// 填充数据
				    resetActivityForm();
					$.each(obj, function(key, value){
						if(value == null){
							return true;
						}

						$("#" + key).val(value);

						// 类型
				 		if(key == "type"){
				 			var typeStr = getActivityTypeValue(value);
				 			if(typeStr != ''){
				 				$('#activityFormType').text(typeStr);
				 			}
				 		}

				 	});

				 	// 显示表单
				 	$("#activityForm").attr("action", "/WxBoss/activityManager/updateActivity");
					showActivityForm();

				}else{
					var msg = data.msg;
					if(msg != undefined){
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
    }

    
    // 删除活动
    function deleteActivity(activityId){
    	$.ajax({
			async : false,
			cache : false,
			type : "POST",
			data : {"activityId" : activityId},
			dataType : "json",
			url : "/WxBoss/activityManager/deleteActivity",
			success : function(data) {
				var ok = data.ok;
				var msg = data.msg;
				if(ok == true){
                    showMessageDialog('删除成功！');
                    $('#activityItem' + activityId).remove();
				}else{
					if(msg != undefined){
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
    }
    
    // 显示删除活动对话框
    function showDeleteActivityDialog(activityId){
        var Remove = '<div class="aRemove"><p>确定删除此条信息</p><div><input type="button" class="qx" value="取消" onclick="closeDeleteDialog();"/><input type="button" class="qd" value="确定" onclick="closeDeleteDialog();deleteActivity(' + activityId + ');"/></div></div>'
        $('body').append(Remove);
        bgHiu();
    }


    // 重载活动列表
    function reloadActivityList(){
        $('#queryActivityForm')[0].reset();
        $('#queryActivityType').text('类型');
        queryActivity(true);
    }

	
	// 显示活动表单
	function showActivityForm(){
		checkLength($('#memo'));
		
		$("#addActive").show();
		bgHiu();
	}


	// 重置活动表单
	function resetActivityForm(){
    	activityForm.resetForm();
    	activityForm.resetStatus();
    	$('#activityFormType').text('类型');
    	$('#type').val('');
    	$('#activityMsg').text('').hide();
    }
    
    
    // 验证活动表单
    var activityForm = $("#activityForm").Validform({
	    tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip = $('#activityMsg');
				if(o.type == 3){
					cssctl(objtip, o.type);
					objtip.text(msg).show();
				}else{
					objtip.text('').hide();
				}
			}
		},
	    ajaxPost:true,
	    showAllError:false,
	    datatype:{
 			"activityTypeStr":function(gets,obj,curform,regxp){
 				var typeVal = $('#activityFormType').text();
 				if(typeVal == '' || typeVal == '类型'){
 					return false;
 				}

		    	// 设置类型
				var type = getActivityTypeKey(typeVal);
	 			if(type != undefined){
 					$('#activityFormType').nextAll("input[name='type']").val(type);
	 			}
 			}
 		},
 		
	    beforeSubmit:function(curform){
	    	var isok = true;
    		var activityId = $("#activityId").val();
			var typeStr = $("#activityFormType").text();
	    	var sortno = $("#sortno").val();
	    	
	    	var params = "activityId=" + activityId + "&sortno=" + sortno;
	    	
	    	// 添加类型参数
			var typeParam = getActivityTypeKey(typeStr);
			if(typeParam != undefined){
				params += "&type=" + typeParam;
			}
	    	
	    	$.ajax({
				async : false,
				cache : false,
				type : "GET",
				data : params,
				dataType : "json",
				url : "/WxBoss/activityManager/findByCondition",
				contentType:"application/x-www-form-urlencoded:charset=UTF-8",
				success : function(data) {
					var ok = data.ok;
					if(ok == true){
						$('#activityMsg').text("当前类型下排序号不唯一！").show();
						$("#sortno").val("");
						isok = false;
					}else{
						$('#activityMsg').text("").hide();
						$('#addActivitySubmit').val('提 交 中...').attr('disabled','disabled');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$('#activityMsg').text("验证失败！").show();
					isok = false;
				}
			});
			
			return isok;
	    },
	    callback:function(data){
	    	$('#addActivitySubmit').val('提 交').removeAttr('disabled');
	    	var ok = data.ok;
	    	var msg = data.msg;
	    	if(ok){
	    		closeLayer();
	    		var type = data.type;
	    		if(type != undefined && type == "update"){
                    showMessageDialog('修改成功！');
	    		}else{
                    showMessageDialog('添加成功！');
	    		}

                reloadActivityList();
	    	}else{
	    		if(msg != undefined){
	    			layerMsg(msg);
	    		}else{
	    			layerMsg('网络繁忙，请稍后再试！');
	    		}
	    	}
	    }
	});
	
    activityForm.addRule([
	    {
	        ele:"#name",
	        datatype:"*2-200",
	        nullmsg:"请输入名称！",
	        errormsg:"请填写2到200位任意字符！"
	    },
	    {
	        ele:"#activityFormType",
	        datatype:"activityTypeStr",
	        nullmsg:"请选择类型！",
	        errormsg:"请选择类型！"
	    },
	    {
	        ele:"#memo",
	        datatype:"*0-500",
	        ignore:"ignore",
	        nullmsg:"请输入简介！",
	        errormsg:"请填写0到500位任意字符！"
	    },
	    {
	        ele:"#url",
	        datatype:"url,*0-255",
			nullmsg:"请输入文字路径！",
	        errormsg:"请填写网址！"
	    },
	    {
	        ele:"#picUrl",
	        datatype:"url,*0-255",
	        nullmsg:"请输入图片路径！",
	        errormsg:"请填写网址！"
	    },
	    {
	        ele:"#sortno",
	        datatype:"n1-5",
	        nullmsg:"请输入排序号！",
	        errormsg:"请填写1到5位数字！"
	    }
	]);
	
	
	// 获取活动类型key
	function getActivityTypeKey(typeStr){
		var type;
		if(activityTypeMap != null){
			$.each(activityTypeMap, function(key, value) { 
				if(typeStr == value){
					type = key;
					return false;
				}
			}); 
		}
		
		return type;
	}
	
	// 获取活动类型value
	function getActivityTypeValue(type){
		var typeStr = '';
		if(activityTypeMap != null){
			$.each(activityTypeMap, function(key, value) { 
				if(type == key){
					typeStr = value;
					return false;
				}
			}); 
		}
		
		return typeStr;
	}
	
</script>
<!-- 活动管理交互 end -->


<!-- 广告管理交互  -->
<script>
	// 分页标识
	var adPageNo = 1;

	
    // 查询广告，isReload 是否重载页面
    function queryAd(isReload){
    	// 加载效果
		if($('moreAd').hasClass('loading')){
			return false;
		}
    	$('#moreAd').addClass('loading');
    	
    	if(isReload){
            adPageNo = 1;
    	}else{
    		$('#moreAd').show();
            adPageNo++;
    	}
    	
    	var params = "pageNo=" + adPageNo + "&"+ $("#queryAdForm").serialize();
    	
    	// 添加类型参数
		var adType = $('#queryAdType').text();
		var typeParam = getAdTypeKey(adType);
		if(typeParam != undefined){
			params += "&adType=" + typeParam;
		}

    	$.ajax({
			async : true,
			cache : false,
			type : "GET",
			data : params,
			dataType : "json",
			contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			url : "/WxBoss/adManager/getAd",
			success : function(data) {
				$('#moreAd').removeClass('loading');
				
				if(isReload){
					$('#moreAd').prevAll().remove();
                    scrollTo(0,0);
				}

                var page = data.page;
				var ads = page.results;
				
				// 查询无数据，并且是重载页面，则显示无结果提示
				if((ads == null || ads.length == 0) && isReload){
					$('#noAd').show();
				}else{
					$('#noAd').hide();
				}
				
				// 当前页码数小于总页码数，则显示“加载更多”
				if(adPageNo < page.totalPage){
					$('#moreAd').show();
				}else{
					$('#moreAd').hide();
				}
				
		    	$.each(ads, function(i, ad){
		    		// 设置日期
					var wlrkDate = ad.createTime;
					var wlrkDateYMD = '';
					if(wlrkDate != null){
						wlrkDate = new Date(wlrkDate);
						var wlrkDateMonth = wlrkDate.getMonth() + 1;
						if(wlrkDateMonth < 10){
							wlrkDateMonth = "0" + wlrkDateMonth;
						}
						var wlrkDateDate = wlrkDate.getDate();
						if(wlrkDateDate < 10){
							wlrkDateDate = "0" + wlrkDateDate;
						}
						wlrkDateYMD = wlrkDate.getFullYear() + '-' + wlrkDateMonth + '-' + wlrkDateDate;
					}

		    		var htmlStr = '';
					htmlStr += '<li id="adItem' + ad.adId + '">';
					htmlStr += '<div class="adbox">';
					htmlStr += '<h3>标题：' + ad.adTitle + '</h3>';
					htmlStr += '<dl>';
					htmlStr += '<dd><b>类型：</b>' + getAdTypeValue(ad.adType) + '</dd>';
					htmlStr += '<dd><b>发布人：</b>' + ad.creater + '</dd>';
					htmlStr += '<dd><b>修改人：</b>' + ad.updater + '</dd>';
					htmlStr += '<dd><b>创建时间：</b>' + wlrkDateYMD + '</dd>';
					htmlStr += '<dt><b>位置：</b>' + ad.adPostionName + '</dt>';
					htmlStr += '</dl>';
					htmlStr += '</div>';
					htmlStr += '<div class="clearfix"></div>';
					htmlStr += '<div class="opr clearfix">';
					htmlStr += '<span class="fl">';
					htmlStr += '<a href="' + ad.adUrl + '" class="link">链接地址查看</a>';
					htmlStr += '<a href="' + ad.adMemo + '" class="link">内容链接查看</a>';
					htmlStr += '</span>';
					htmlStr += '<span class="fr">';
					htmlStr += '<i class="icon1" onclick="updateAd(' + ad.adId + ')"></i>';
					htmlStr += '<i class="icon2" onclick="showDeleteAdDialog(' + ad.adId + ')"></i>';
					htmlStr += '</span>';
					htmlStr += '</div>';
					htmlStr += '</li>';
					
		    		$('#moreAd').before(htmlStr);
		    	});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#moreAd').removeClass('loading');
				layerMsg('操作失败！');
			}
		});
    }


    //添加广告
    function addAd(){
        resetAdForm();
        $("#adForm").attr("action", "/WxBoss/adManager/addAd");
        showAdForm();
    };


    // 更新广告
    function updateAd(adId){
        $.ajax({
            async : false,
            cache : false,
            type : "GET",
            data : {"adId" : adId},
            dataType : "json",
            url : "/WxBoss/adManager/getAdById",
            success : function(data) {
                var ok = data.ok;
                if(ok == true){
                    var obj = data.obj;

                    // 填充数据
                    resetAdForm();
                    $.each(obj, function(key, value){
                        if(value == null){
                            return true;
                        }

                        $("#" + key).val(value);

                        // 类型
                        if(key == "adType"){
                            var typeStr = getAdTypeValue(value);
                            if(typeStr != ''){
                                $('#adFormType').text(typeStr);
                            }
                        }

                    });

                    // 显示表单
                    $("#adForm").attr("action", "/WxBoss/adManager/updateAd");
                    showAdForm();

                }else{
                    var msg = data.msg;
                    if(msg != undefined){
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
    }

    // 删除广告
    function deleteAd(adId){
        $.ajax({
            async : false,
            cache : false,
            type : "POST",
            data : {"adId" : adId},
            dataType : "json",
            url : "/WxBoss/adManager/deleteAd",
            success : function(data) {
                var ok = data.ok;
                var msg = data.msg;
                if(ok == true){
                    showMessageDialog('删除成功！');
                    $('#adItem' + adId).remove();
                }else{
                    if(msg != undefined){
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
    }


    // 显示删除广告对话框
    function showDeleteAdDialog(adId){
        var Remove = '<div class="aRemove"><p>确定删除此条信息</p><div><input type="button" class="qx" value="取消" onclick="closeDeleteDialog();"/><input type="button" class="qd" value="确定" onclick="closeDeleteDialog();deleteAd(' + adId + ');"/></div></div>'
        $('body').append(Remove);
        bgHiu();
    }


    // 重载广告列表
    function reloadAdList(){
        $('#queryAdForm')[0].reset();
        $('#queryAdType').text('类型');
        queryAd(true);
    }


    // 显示广告表单
    function showAdForm(){
        $("#addAD").show();
        bgHiu();
    }


    // 重置广告表单
    function resetAdForm(){
        adForm.resetForm();
        adForm.resetStatus();
        $('#adFormType').text('类型');
        $('#adType').val('');
        $('#adMsg').text('').hide();
    }


    // 验证广告表单
    var adForm = $("#adForm").Validform({
        tiptype:function(msg,o,cssctl){
            if(!o.obj.is("form")){
                var objtip = $('#adMsg');
                if(o.type == 3){
                    cssctl(objtip, o.type);
                    objtip.text(msg).show();
                }else{
                    objtip.text('').hide();
                }
            }
        },
        ajaxPost:true,
        showAllError:false,
        datatype:{
            "adTypeStr":function(gets,obj,curform,regxp){
                var typeVal = $('#adFormType').text();
                if(typeVal == '' || typeVal == '类型'){
                    return false;
                }

                // 设置类型
                var type = getAdTypeKey(typeVal);
                if(type != undefined){
                    $('#adFormType').nextAll("input[name='adType']").val(type);
                }
            }
        },

        beforeSubmit:function(curform){
            $('#addAdSubmit').val('提 交 中...').attr('disabled','disabled');
        },
        callback:function(data){
            $('#addAdSubmit').val('提 交').removeAttr('disabled');
            var ok = data.ok;
            var msg = data.msg;
            if(ok){
                closeLayer();
                var type = data.type;
                if(type != undefined && type == "update"){
                    showMessageDialog('修改成功！');
                }else{
                    showMessageDialog('添加成功！');
                }

                reloadAdList();
            }else{
                if(msg != undefined){
                    layerMsg(msg);
                }else{
                    layerMsg('网络繁忙，请稍后再试！');
                }
            }
        }
    });

    adForm.addRule([
        {
            ele:"#adTitle",
            datatype:"*1-200",
            nullmsg:"请输入标题！",
            errormsg:"请填写0到200位任意字符！"
        },
        {
            ele:"#adType",
            datatype:"adTypeStr",
            nullmsg:"请选择类型！",
            errormsg:"请选择类型！"
        },
        {
            ele:"#adPostionName",
            datatype:"*1-200",
            nullmsg:"请输入位置名称！",
            errormsg:"请填写小于200位的任意字符！"
        },
        {
            ele:"#adMemo",
            datatype:"*1-1000",
            nullmsg:"请输入内容！",
            errormsg:"请填写0到1000位任意字符！"
        },
        {
            ele:"#adUrl",
            datatype:"url,*0-255",
            nullmsg:"请输入网址！",
            errormsg:"请填写正确的网址！"
        }
     ]);


	// 获取广告类型key
	function getAdTypeKey(typeStr){
		var type;
		if(adTypeMap != null){
			$.each(adTypeMap, function(key, value) { 
				if(typeStr == value){
					type = key;
					return false;
				}
			}); 
		}
		
		return type;
	}
	
	// 获取广告类型value
	function getAdTypeValue(type){
		var typeStr = '';
		if(adTypeMap != null){
			$.each(adTypeMap, function(key, value) { 
				if(type == key){
					typeStr = value;
					return false;
				}
			}); 
		}
		
		return typeStr;
	}
</script>	
<!-- 广告管理交互  -->


<!-- 意见管理交互  -->
<script>
    // 分页标识
    var opinionPageNo = 1;


    // 查询意见，isReload 是否重载页面
    function queryOpinion(isReload){
        // 加载效果
        if($('moreOpinion').hasClass('loading')){
            return false;
        }
        $('#moreOpinion').addClass('loading');

        if(isReload){
            opinionPageNo = 1;
        }else{
            $('#moreOpinion').show();
            opinionPageNo++;
        }

        var params = "pageNo=" + opinionPageNo + "&"+ $("#queryOpinionForm").serialize();

        $.ajax({
            async : true,
            cache : false,
            type : "GET",
            data : params,
            dataType : "json",
            contentType:"application/x-www-form-urlencoded:charset=UTF-8",
            url : "/WxBoss/opinionManager/getOpinion",
            success : function(data) {
                $('#moreOpinion').removeClass('loading');

                if(isReload){
                    $('#moreOpinion').prevAll().remove();
                    scrollTo(0,0);
                }

                var page = data.page;
                var opinions = page.results;

                // 查询无数据，并且是重载页面，则显示无结果提示
                if((opinions == null || opinions.length == 0) && isReload){
                    $('#noOpinion').show();
                }else{
                    $('#noOpinion').hide();
                }

                // 当前页码数小于总页码数，则显示“加载更多”
                if(opinionPageNo < page.totalPage){
                    $('#moreOpinion').show();
                }else{
                    $('#moreOpinion').hide();
                }

                $.each(opinions, function(i, opinion){
                    // 设置日期
                    var wlrkDate = opinion.createTime;
                    var wlrkDateYMD = '';
                    if(wlrkDate != null){
                        wlrkDate = new Date(wlrkDate);
                        var wlrkDateMonth = wlrkDate.getMonth() + 1;
                        if(wlrkDateMonth < 10){
                            wlrkDateMonth = "0" + wlrkDateMonth;
                        }
                        var wlrkDateDate = wlrkDate.getDate();
                        if(wlrkDateDate < 10){
                            wlrkDateDate = "0" + wlrkDateDate;
                        }
                        wlrkDateYMD = wlrkDate.getFullYear() + '-' + wlrkDateMonth + '-' + wlrkDateDate;
                    }

                    // 设置图片
                    var picUrlList = opinion.picUrls;
                    var firstPicUrl = '';
                    if(picUrlList != null && picUrlList.length > 0){
                        firstPicUrl = picUrlList[0];
                    }

                    var htmlStr = '';
                    htmlStr += '<li class="yj" id="opinionItem' + opinion.opId + '">';
                    htmlStr += '<span class="img relative" onclick="seePic(\'' + picUrlList +'\')"><img src="' + firstPicUrl + '" /><i></i></span>';
                    htmlStr += '<div class="cont">';
                    htmlStr += '<p class="yj"><b>联系人：</b>' + opinion.opName + '<br/>';
                    htmlStr += '<b>创建时间：</b>' + wlrkDateYMD + '<br/>';
                    htmlStr += '</p>';
                    htmlStr += '<div class="opr yj" align="right">';
                    htmlStr += '<i class="icon5 fl" onclick="window.location.href=\'tel:' + opinion.opPhone + '\';"></i>';

                    // 已处理的意见
                    if(opinion.status == 1){
                        htmlStr += '<i class="icon4" onclick="seeOpinion(' + opinion.opId +', false)"></i>';
                    }

                    htmlStr += '<i class="icon3" onclick="seeOpinion(' + opinion.opId +', true)"></i>';
                    htmlStr += '<i class="icon2" onclick="showDeleteOpinionDialog(' + opinion.opId + ')"></i>';
                    htmlStr += '</div>';
                    htmlStr += '</div>';
                    htmlStr += '<div class="clearfix"></div>';
                    htmlStr += '</li>';

                    $('#moreOpinion').before(htmlStr);
                });
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
                $('#moreOpinion').removeClass('loading');
                layerMsg('操作失败！');
            }
        });
    }


    // 查看意见
    function seeOpinion(opId, isShowCheckButton){
        $.ajax({
            async : false,
            cache : false,
            type : "GET",
            data : {"opId" : opId},
            dataType : "json",
            url : "/WxBoss/opinionManager/getOpinionById",
            success : function(data) {
                var ok = data.ok;
                if(ok == true){
                    var obj = data.obj;

                    // 填充数据
                    resetOpinionForm();

                    $.each(obj, function(key, value){
                        if(key == 'opId'){
                            $("#" + key).val(value);
                        }else{
                            $("#" + key).text(value);
                        }
                    });

                    // 显示表单
                    if(isShowCheckButton){
                        $("#opCheck").show();
                    }else{
                        $("#opCheck").hide();
                    }
                    $("#proIdea").show();
                    bgHiu();

                }else{
                    var msg = data.msg;
                    if(msg != undefined){
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
    }


    // 处理意见
    function checkOpinion(){
        $('#opSubmit').val('提 交 中...').attr('disabled','disabled');
        var opId = $('#opId').val();

        $.ajax({
            async : false,
            cache : false,
            type : "POST",
            data : {"opId" : opId},
            dataType : "json",
            url : "/WxBoss/opinionManager/checkOpinion",
            success : function(data) {
                $('#opSubmit').val('提 交').removeAttr('disabled');
                var ok = data.ok;
                var msg = data.msg;
                if(ok == true){
                	closeLayer();
                    showMessageDialog('处理成功！');
                    reloadOpinionList();
                }else{
                    if(msg != undefined){
                        layerMsg(msg);
                    }else{
                        layerMsg('网络繁忙，请稍后再试！');
                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
                $('#opSubmit').val('提 交').removeAttr('disabled');
                layerMsg('操作失败！');
            }
        });
    }


    // 重载意见列表
    function reloadOpinionList(){
        $('#queryOpinionForm')[0].reset();
        queryOpinion(true);
    }


    // 删除意见
    function deleteOpinion(opId){
        $.ajax({
            async : false,
            cache : false,
            type : "POST",
            data : {"opId" : opId},
            dataType : "json",
            url : "/WxBoss/opinionManager/deleteOpinion",
            success : function(data) {
                var ok = data.ok;
                var msg = data.msg;
                if(ok == true){
                    showMessageDialog('删除成功！');
                    $('#opinionItem' + opId).remove();
                }else{
                    if(msg != undefined){
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
    }


    // 显示删除意见对话框
    function showDeleteOpinionDialog(opId){
        var Remove = '<div class="aRemove"><p>确定删除此条信息</p><div><input type="button" class="qx" value="取消" onclick="closeDeleteDialog();"/><input type="button" class="qd" value="确定" onclick="closeDeleteDialog();deleteOpinion(' + opId + ');"/></div></div>'
        $('body').append(Remove);
        bgHiu();
    }


    // 重置意见表单
    function resetOpinionForm(){
        $('#opName').val('');
        $('#opPhone').val('');
        $('#opMemo').val('');
        $('#opId').val('');
    }


    // 意见高级搜索弹层
    $('#searchM').on('click', function(){
        $('#opinionChecker').show();
        bgHiu();
    })
</script>
<!-- 意见管理交互 end -->

<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>

</body>
</html>