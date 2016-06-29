<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>珍药材-我的挂牌</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <style>
	    .loading{
	    	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
	    }
	    body{background:#f5f5f5;}
	</style>
</head>
<body>
<div class="sell-box-head">
    <a href="/myzyc"><i class="back"></i></a>
    <span class="fr" id="searchM" style="display: block;">
        <a href="#2" name="check"></a>
    </span>
</div>

    <div class="a-sell-top clearfix">
        <span class="fl">挂牌中药材 
        <#if map.SURPLUSES?c!='0' && map.SURPLUSES?c?contains(".")== true && map.SURPLUSES?c?substring(map.SURPLUSES?c?index_of(".")+1)?length gt 2>
        	${map.SURPLUSES?c?substring(0,map.SURPLUSES?c?index_of(".")+1)}${map.SURPLUSES?c?substring(map.SURPLUSES?c?index_of(".")+1,map.SURPLUSES?c?index_of(".")+3)} 
        <#else>
        	${map.SURPLUSES}
        </#if>吨</span>
        <span class="fr">摘牌药材
         <#if map.VOLUME?c!='0' && map.VOLUME?c?contains(".")== true && map.VOLUME?c?substring(map.VOLUME?c?index_of(".")+1)?length gt 2>
        	${map.VOLUME?c?substring(0,map.VOLUME?c?index_of(".")+1)}${map.VOLUME?c?substring(map.VOLUME?c?index_of(".")+1,map.VOLUME?c?index_of(".")+3)} 
        <#else>
        	${map.VOLUME} 
        </#if>吨</span>
    </div>
    <div class="a-sell-list1">
        <ul id="listings">
        </ul>
        <div id="more" class="load"></div>
    </div>
    <div><a href="/listing/wxWhlistManage" class="opr-tosell">我要挂牌</a></div>
<!-- 高级查询弹层 -->
<div  class="h-demand" id="Check"  style="height: 19.6em; margin-top: -9.8em;">
    <div class="close"></div>
    <form id="myListSearch" name="myListSearch">
	    <ul class="relative">
	        <li><input onfocus="vfocus(this);" placeholder="请输入品种名称"  id="breedname" name="breedname"  class="input-text1" /></li>
	        <li class="relative" id="state" datatype="unit">
	            <label class="col-ca">按状态</label><i class="allow-ri"></i>
	                <span id="selectflag" class="unit sty1">
	                    <a datatype="" href="javascript:void(0);">全部</a>
	                    <#if flagMap??>
		            		<#list flagMap?keys as key>
		            			<a datatype="${key}" href="javascript:void(0);">${flagMap[key]}</a>
		            		</#list>
	            		</#if>
	                </span>
	        </li>
	        <li class="bn pa" style="margin-top: 0.5em;"><label>挂牌日期</label></li>
	        <li class="bn"><input name="startdate" type="text" class="input-text2" readonly="readonly" id="wdate1" /> 至  <input type="text" name="enddate"  class="input-text2" readonly="readonly" id="wdate2"/></li>
	        <li class="relative" id="hasbill" datatype="unit" style="margin-top: 0.5em;">
	            <label class="col-ca">是否有订单</label><i class="allow-ri"></i>
	                <span id="selectorderflag" class="unit sty1">
	                    <a dataval="" href="javascript:void(0);">全部</a>
	                    <a dataval="1" href="javascript:void(0);">有订单</a>
	                    <a dataval="0" href="javascript:void(0);">无订单</a>
	                </span>
	        </li>
	        <input type="hidden" id="listingflag" name="listingflag"/>
	        <input type="hidden" id="orderflag" name="orderflag"/>
	        <span id="tips" style="display:none;float:left;margin-top:0.5em;"></span>
	        <li class="bn mt1" style="margin-top: 1.5em;"><input class="btn-red" type="button" id="hCheck" value="查 询" /></li>
	    </ul>
	 </form>   
</div>
<!-- 高级查询弹层end -->
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>

<script type="text/javascript">
	function vfocus(o){
		var viewH =document.body.clientHeight;      //可见高度  
        var tempH = viewH/2;                        //屏幕可见高度的一半
        var top = $("#Check").offset().top; 
        var top1 = $("#Check").position().top;
        var h =$("#Check").height(); //弹层高度
        //如果弹层显示所有查询条件则按原位置显示，如果只显示品种名称的弹层则按屏幕高度一半显示
        if(h > 150){ 
            if((top1-150) > 0){
        		$("#Check").css("top",top1);
        	}else{
        		$("#Check").css("top",'50%');
        	}
		}else{
			$("#Check").css("top",tempH);
		}
	}
	
	//当前页数
	var pageno = 1;
	$('#more').click(function(){
		loadMyListings();
	})
	
	//下拉框span加上事件，点击它周围地方层消失
	$(document).on("click",function(e){
		var target = $(e.target);
		if(target.closest("#selectflag").length == 0 && target.closest("#state").length == 0){
			$("#selectflag").hide();
		}
		if(target.closest("#selectorderflag").length == 0 && target.closest("#hasbill").length == 0){
			$("#selectorderflag").hide();
		}
	}) 
	
	//高级查询
	$('#hCheck').click(function(){
		pageno = 1;
		var wdate1 = $('#wdate1').val();
		var wdate2 = $('#wdate2').val();
		if((wdate1!='' && wdate2=='')  || (wdate2!='' && wdate1 == '')){
			$("#tips").text("请选择完整的日期范围").show();
			$("#wdate1").val("");
			$("#wdate2").val("");
		}else{
			$("#tips").text("").hide();
		}
		$("#listings").html('');
		loadMyListings();
	})
	
	//获取我的挂牌列表
    function loadMyListings(){
    	var breedname= $("#breedname").val();
    	if(breedname!=''){
    		//去前后空格
    		$("#breedname").val($("#breedname").val().trim());
    	}
    	if($('#more').hasClass('loading')){
			return false;
		}
		$('#more').addClass('loading');
    	var params ="pageNo="+pageno+"&"+$("#myListSearch").serialize();
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			data : params,
			dataType : 'json',
			url : '/listing/getMyListings',
			success : function(data) {
				$('#more').removeClass('loading');
				var mylists = data.results;
				var mylistsLength = mylists.length;
				if(mylistsLength>0){
					$.each(mylists,function(index,listing){
						 var flag='';
						 switch(listing.listingflag){
							case 0 : flag='待审核';break;
							case 1 : flag='审核失败';break;
							case 2 : flag='挂牌中';break;
							case 3 : flag='已售罄';break;
							case 4 : flag='已取消';break;
							default:break;
						 }
					 	 var list_str='<li><a href="/listing/detail/'+listing.listingid+'">';	
						 list_str+='<p class="tit">';
	                     list_str+='<span class="fl col_a9">挂牌编号：'+listing.listingid+' </span>';
	                     list_str+='<span id="'+listing.listingid+'Flag" class="fr col_red">'+flag+'</span>';
	                     list_str+='</p><dl>';
	                     list_str+='<dt>'+listing.title+'</dt>';
	                     var _dv =listing.dictvalue;
	                     var dv ='';
	                     if(_dv!=null && _dv !='') dv = _dv;
	                     list_str+='<dd><span>挂牌单价：</span><b>￥'+listing.lowunitprice+'</b> 元/'+dv+'<br/>';
	                     list_str+='<span>已有订单：</span><b>'+listing.ordernum+'</b><br/>';
	                     var wlrkDate = listing.expiretime;
						 var wlrkDateYMD = '';
						 if(wlrkDate!=null){
							wlrkDate = new Date(wlrkDate);
							var wlrkDateMonth = wlrkDate.getMonth()+1;
							var wlrkDateDay = wlrkDate.getDate();
							if(wlrkDateMonth<10){
								wlrkDateMonth = "0"+wlrkDateMonth;
							}
							if(wlrkDateDay<10){
								wlrkDateDay = "0"+wlrkDateDay;
							}
							wlrkDateYMD = wlrkDate.getFullYear()+'年'+wlrkDateMonth+'月'+wlrkDateDay+'日';
						 }
	                     list_str+='<span>有效期：</span><b>'+wlrkDateYMD+'</b></dd>';
	                	 list_str+='</dl></a>';
	                	 list_str+='<p id="'+listing.listingid+'Action" class="bottom" align="right">';
	                	 if(listing.listingflag==2 && listing.surpluses!=0){
		              		list_str+='<a  href="javascript:confirm('+listing.listingid+');" class="link-sty" name="xiajia">下 架</a>';
		              	 }
	                	 if(listing.listingflag==0 || listing.listingflag==1 || listing.listingflag==2){
		              		list_str+='<a href="/listing/update/'+listing.listingid+'" class="link-sty" name="edit">编 辑</a>';
		              	 }	
	                     list_str+='</p></li>';
					 	 $('#listings').append(list_str);
					});
					if(data!=null && data.pageNo < data.totalPage){
						$("#more").show();
					}else{
						$('#more').hide();
					}
					++pageno;
				}else{
					$('#more').hide();
					//判断只有第一页的时候才可能出现友情提示
					if(typeof($('#a_without'))!='undefined' && data.pageNo == 1){
						var warn='<div id="a_without" class="a-sell-list1" style="margin-top:-7.5em;">';
	        			warn+='<div id="tishi" align="center"><i class="tishi"></i><p>您还没有一个挂牌信息哦</p></div>';
	    				warn+='</div>';
	    				$('#listings').append(warn);
	    			}	
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#more').removeClass('loading');
				layerMsg('网络超时，请重试！');
			}
		});
   }
   
	//信息框
	function layerMsg(msg){
		layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
	}

	//日期控件
	$('#wdate1').click(function(){
		WdatePicker({
			maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#wdate2').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
			readOnly:true
		});
	});
	
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
    
    function confirm(listingid){
        var Remove = '<div class="aRemove xiajia"><p>确定要下架编号<br/>'+listingid+'<br/>的挂牌吗？</p><div><input type="button" class="qx" value="取消" /><input type="button" dataval="'+listingid+'" class="qd" value="确定" /></div></div>'
        $('body').append(Remove);
        bgHiu();
    }
    
    $('body').delegate('.aRemove .qx','click',function(){
        $('.bghui').remove();
        $('.aRemove').remove();
    });
    
    $('body').delegate('.aRemove .qd','click',function(){
        $('.bghui').remove();
        $('.aRemove').remove();
        var id = $(this).attr("dataval");
        $.ajax({
			async : false,
			cache : false,
			type : "POST",
			data : {"listingId":id},
			dataType : "json",
			url : "/listing/disabledListing",
			success : function(data) {
				var ok = data.ok;
				var msg = data.msg;
				if(ok==true){
					$('#'+id+'Flag').text('已取消');
					$('#'+id+'Action').html('<a href="/listing/update/'+id+'" class="link-sty" name="edit">编 辑</a>');
				}
				layerMsg(msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('网络超时，请重试！');
			}
		});
    });

    $(function(){
    	//初始化我的挂牌列表
    	loadMyListings();
    	
        //关闭层
        function close(els){
            els.on('click',function(){
                $('.h-demand').hide();
                $('.bghui').remove();
            })
        }
        close($('.close'));
        close($('#hCheck'));
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu();
            })
        }
        
        $("#searchM").on("click",function(){
            //$("#breedname").focus();
        	$("#breedname").blur();
        	
        	$('#Check').show();
            bgHiu();
        })
        
        //show($('#searchM'),$('#Check'));

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
        //Focus($('input[name=breedname]'),'请输入品种名称');

        //下拉选择
        $('[datatype=unit] .unit').hide();
        $('[datatype=unit]').on('click',function(){
            $(this).children('.unit').show();
        });
        
        //选择挂牌状态
        $('#selectflag a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            $("#listingflag").attr("value",$(this).attr("datatype"));
            return false;
        });
        
        //选择订单状态
        $('#selectorderflag a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            $("#orderflag").attr("value",$(this).attr("dataval"));
            return false;
        });
    });


    //touch事件替换CLICK事件
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').touchStart(function () {
        $(this).addClass('hover');
    });
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').touchMove(function () {
        $(this).addClass('hover');
    });
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').touchEnd(function () {
        $(this).removeClass('hover');
    });
    $('.a-sell-list1 ul li .bottom a,.a-sell-list1 ul li').tapOrClick(function () {
        $(this).removeClass('hover');
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