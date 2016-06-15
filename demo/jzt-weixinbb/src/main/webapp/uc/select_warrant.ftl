<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我要挂牌-选择仓单</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <style>
        body{background:#f5f5f5;}
        .sells-box{padding-top: 0;}
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>
</head>
<body>
	    <div class="sell-box-head relative">
	        <i class="back"></i>
	        <a href="javascript:void(0);" id="search_div" name="inspect">
	       		<div class="inStore-title" style="text-align:center;margin-top:-0.1em; padding-left:2em;margin-right:0.5em;">选择仓单
	        	<span class="fr" id="inspect">           
	        	</span></div>
	        </a>
	    </div>
	    
 <#if (page?? && page.results?size gt 0)>
	<div class="select_cd" align="center">
        <img src="${RESOURCE_IMG_WX}/images/count1.png">
        <div class="se_wz"><p>挂牌交易基于仓单</p>
        <p>请先选择要挂牌的仓单</p></div>
    </div>

    <div class="sells-box store-box">
        <!--仓单号列表 start-->
        	<ul id="wxWhlists" style="padding-top:0em;">
	    		<#list page.results as wxWhlist>
	    		 	<a href="/listing/add/${wxWhlist.wlid}">
		        		<li id="${wxWhlist.wlid!''}">
			                <p class="tit">仓单号：${wxWhlist.wlid!''}<i class="arrow-right"></i></p>
			                <span class="cont">品    名：${wxWhlist.breedname!''}</span>
			                <span class="cont">总    量：${wxWhlist.wltotal!''}${wxWhlist.dictvalue!''}</span>
			                <span class="cont">所在仓库：${wxWhlist.warehousename!''}</span>
			                <span class="cont">入库日期：${wxWhlist.wlrkdate?string('yyyy-MM-dd')!''}</span>
			            </li>
		         	</a>   
	            </#list>
	        </ul>
	        <#if page?? && page.pageNo lt page.totalPage>
				<div id="moreWxWhlists" class="load"></div>
			</#if>
        <!--仓单号列表 end-->
    </div>
	
    <h3 class="myStore-tit">现货交易流程</h3>
    <div class="course">
        <div class="bg">
            <i class="trad1"></i>
            <i class="trad2"></i>
            <i class="trad3"></i>
            <i class="trad4"></i>
        </div>
    </div>
    <h3 class="myStore-tit">药材质押流程</h3>
    <div class="course zhiya"></div>
    <div style="height:2.5em;">&nbsp;</div>
    <#else>
	    <div class="a-sell-list1">
			<div id="tishi" align="center"><i class="tishi"></i><p>没有找到符合条件的仓单！</p></div>
		</div>
	</#if>
	<div><a href="/wxWarehouseApply/iWillWarehousing" class="opr-tosell">我要入仓</a></div>
<!-- 高级查询弹层 -->
   <div id="xx" class="h-demand store" style="height:16.6em;">
        <div class="close"></div>
        <form id="wxWhlistSearch" action="/listing/wxWhlistManage" method="get">
	        <ul class="relative">
	            <li><input type="text" id="breedName" onfocus="vfocus(this);" placeholder="请输入品种名称" name="breedName" value="${wxWhlistSearchDto.breedName}" class="input-text1" /></li>
	            <li><input type="text" placeholder="请输入仓单编号" id="wlId" name="wlId" value="${wxWhlistSearchDto.wlId!''}" class="input-text1" /></li>
	            <li class="relative" id="Store"><input id="wxWareHouseId" name="wareHouseId" value="${wxWhlistSearchDto.wareHouseId!''}" type="hidden" /><input id="wxWareHouseName" placeholder="请选择所在仓库"  value="${wxWhlistSearchDto.wareHouseName!''}" class="input-text1" readonly="readonly"/><i class="allow-ri"></i></li>
	            <li class="bn"><label>入库时间</label></li>
	            <li class="bn"><input type="text" id="wdate1" name="startWlrkDate" value="${wxWhlistSearchDto.startWlrkDate!''}" class="input-text2" readonly="readonly" /> — <input type="text" id="wdate2" name="endWlrkDate" value="${wxWhlistSearchDto.endWlrkDate!''}" class="input-text2" readonly="readonly" /></li>
	            <li class="bn mt1"><input class="btn-red" type="submit" id="Submit" value="查 询" /></li>
	        </ul>
        </form>
    </div>
<!-- 高级查询弹层end -->

<!--仓库弹层 start-->
   <div class="pop-up clearfix">
        <!--<div class="search-format">
            <p><input type="text" value="没有找到合适的仓库？自己输入一个吧" /> <input type="button" value="确 定" /></p>
        </div>-->
        <ul class="tabs-nav">
            <li class="cur"><i class="icon2"></i>所在仓库<i class="arrow"></i></li>
        </ul>
        <div id="Choose" class="tabs-conts">
            <p style="display: block;">
            	<span><a href="javascript:void(0)" rel="">全部仓库</a></span>
            	<#if (wxWarehouses?? && wxWarehouses?size>0)>
            		<#list wxWarehouses as wxWarehouse>
			        	<span><a href="javascript:void(0)" rel="${wxWarehouse.wareHouseId!''}">${wxWarehouse.wareHouseName!''}</a></span>
		            </#list>
            	</#if>
            </p>
        </div>
        <div class="btn-return">返 回</div>
    </div>
<!--仓库弹层 end-->
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
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
	
    $(function(){
    	//紧急处理表单内的初始化值
    	$('#Submit').on('click',function(){
			$("#wxWhlistSearch").submit(); 
    	})
    	
       //返回按钮事件
       $('.back').on('click',function(){
        	window.location.href="/listing"; 
       }) 
       
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
        close($('#Submit'));
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }
        //show($('#check a[name=check]'),$('.h-demand'));
		show($('#search_div'),$('.h-demand'));
		

        //高级查询
        $('#unit .unit').hide();
        $('#unit').on('click',function(){
            $(this).children('.unit').show();
        });
        $('.unit a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            return false;
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
        //Focus($('input[name=breedName]'),'请输入品种名称');

        //仓库层
	    /*var Height = $(document).height();
	    $('.pop-up').height(Height);*/
	    $('#Store').on('click',function(){
	        $('.pop-up').show().animate({left:0},100);
	    });
	    $('#Choose a').on('click',function(){
	        var wareHouseId = $(this).attr('rel');
	        $('#wxWareHouseId').val(wareHouseId);
	        var wareHouseName = $(this).text();
	        $('#wxWareHouseName').val(wareHouseName);
	        $(this).parents('.pop-up').hide().animate({left:'100%'},100);
	    });
        
        //信息框
    	function layerMsg(msg){
    	layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
    };
    
    //当前页数
	var wxWhlistPageNo = 2;
	//查看更多的我的仓单
	$('#moreWxWhlists').click(function(){
		if($('moreWxWhlists').hasClass('loading')){
			return false;
		}
		$('#moreWxWhlists').addClass('loading');
		var breedName = $('#breedName').val();
		if(breedName =='请输入品种名称' ){
			$('#breedName').attr("value","");
		}	
		var params = "pageNo="+wxWhlistPageNo+"&"+$("#wxWhlistSearch").serialize();
		$.ajax({
			async : true,
			cache : false,
			type : 'GET',
			data : params,
			dataType : 'json',
			url : '/wxWhlist/getMoreWxWhlists',
			success : function(data) {
				$('#moreWxWhlists').removeClass('loading');
				var wxWhlists = data.results;
				var wxWhlistsLength = wxWhlists.length;
				if(wxWhlistsLength>0){
					$.each(wxWhlists,function(index,wxWhlist){
						var wlId = wxWhlist.wlid==null?'':wxWhlist.wlid;
						var breedName = wxWhlist.breedname==null?'':wxWhlist.breedname;
						var wlTotal = wxWhlist.wltotal==null?'':wxWhlist.wltotal;
						var dictValue = wxWhlist.dictvalue==null?'':wxWhlist.dictvalue;
						var wareHouseName = wxWhlist.warehousename==null?'':wxWhlist.warehousename;
						var wlrkDate = wxWhlist.wlrkdate;
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
							wlrkDateYMD = wlrkDate.getFullYear()+'-'+wlrkDateMonth+'-'+wlrkDateDay;
						}
						
						var moreWxWhlists = '<a href="/listing/add/'+wxWhlist.wlid+'"><li id='+wlId+'><p class="tit">仓单号：'+wlId+'<i class="arrow-right"></i></p>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">品    名：'+breedName+'</span>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">总    量：'+wlTotal+dictValue+'</span>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">所在仓库：'+wareHouseName+'</span>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">入库日期：'+wlrkDateYMD+'</span></li></a>';
		                   
						$('#wxWhlists').append(moreWxWhlists);
					});
					if(data!=null && data.pageNo < data.totalPage){
						$("#moreWxWhlists").show();
					}else{
						$('#moreWxWhlists').hide();
					}
					++wxWhlistPageNo;
				}else{
					$('#moreWxWhlists').hide();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$('#moreWxWhlists').removeClass('loading');
				layerMsg('网络超时，请重试！');
			}
		});
	});
    })
</script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>