<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-我的仓库</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
    <style>
        body{background:#f5f5f5;}
        .sells-box{padding-top: 0;}
        .loading{
        	background: #fff url("${RESOURCE_JS_WX}/js/layer/skin/default/loading-0.gif") no-repeat scroll center center;
        }
    </style>
</head>
<body>
<!--我的仓单 start-->
	<div id="wxWhlist" style="position: relative;">
	<div class="sell-box-head">
        <a href="/myzyc"><i class="back"></i></a>
        <span id="check" class="fr">
        	<a href="tel:4001054315" name="phone"></a><a href="javascript:void(0)" name="check"></a>
        </span>
    </div>
    <!--仓单号列表 start-->
    <div class="sells-box store-box">
		 <#if (page?? && page.results?size gt 0)>
			<ul id="wxWhlists">
	    		<#list page.results as wxWhlist>
	        		<li id="${wxWhlist.wlid!''}">
		                <a href="javascript:void(0);"><p class="tit">仓单号：${wxWhlist.wlid!''}<i class="arrow-right"></i></p>
		                <span class="cont">品    名：${wxWhlist.breedname!''}</span>
		                <span class="cont">总    量：${wxWhlist.wltotal!''}${wxWhlist.dictvalue!''}</span>
		                <span class="cont">所在仓库：${wxWhlist.warehousename!''}</span>
		                <span class="cont">入库日期：${wxWhlist.wlrkdate?string('yyyy-MM-dd')!''}</span>
		            	</a>
		            </li>
	            </#list>
	        </ul>
			<#if page?? && page.pageNo lt page.totalPage>
				<div id="moreWxWhlists" class="load"></div>
			</#if>
		<#else>
			<li>
    			<div id="tishi" align="center" style="padding-top:22%;padding-bottom:5%;border-bottom: 1px solid #CFCFCF;"><i class="tishi"></i>
    			<p style="color:#666">暂无仓单 , 立即<a style="color:#965959;" href="/wxWarehouseApply/iWillWarehousing">申请入仓</a></p>
			</li>
		</#if>
	</div>
    <!--仓单号列表 end-->
    
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

<!-- 高级查询弹层 -->
    <div id="xx" class="h-demand store" style="height:16.6em;">
        <div class="close"></div>
        <form id="wxWhlistSearch" action="/wxWhlist/wxWhlistManage" method="get">
	        <ul class="relative">
	            <li><input type="text" onfocus="vfocus(this);" placeholder="请输入品种名称" name="breedName" value="${wxWhlistSearchDto.breedName!''}" class="input-text1" /></li>
	            <li><input type="text" placeholder="请输入仓单编号" id="wlId" name="wlId" value="${wxWhlistSearchDto.wlId!''}" class="input-text1" /></li>
	            <li class="relative" id="Store"><input id="wxWareHouseId" name="wareHouseId" value="${wxWhlistSearchDto.wareHouseId!''}" type="hidden" /><input type="text" id="wxWareHouseName" placeholder="请选择所在仓库" name="wareHouseName" value="${wxWhlistSearchDto.wareHouseName!''}" class="input-text1" readonly="readonly" /><i class="allow-ri"></i></li>
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
</div>
<!--我的仓单 end-->

<!--仓单详情 start-->
<div id="wxWhlistDetail" style="position: relative;">
<div class="sell-box-head">
    <i id="backWxWhlist" class="back"></i>
    <div align="center" class="inStore-title">仓单信息</div>
</div>
<div class="sellDetail">
    <div class="wraper">
        <header id="scroll_pic_view" class="scroll_pic_view" style="overflow: hidden; ">
            <div id="scroll_pic_view_div"">
                <ul id="scroll_pic_view_ul">
                    <li><a onclick="return false;"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"></a></li>
                    <li><a onclick="return false;"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"></a></li>
                    <li><a onclick="return false;"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"></a></li>
                    <li><a onclick="return false;"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"></a></li>
                    <li><a onclick="return false;"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"></a></li>
                    <li><a onclick="return false;"><img src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"></a></li>
                </ul>
            </div>
            <div>
                <ol id="scroll_pic_nav" class="scroll_pic_nav">
                    <script>
                    	var scroll_pic_view;
                    	var scrollLen = 1;
                        (function(d, $){
                            var scrollPicView = d.getElementById("scroll_pic_view"),
                                    scrollPicViewDiv = d.getElementById("scroll_pic_view_div"),
                                    lis = scrollPicViewDiv.querySelectorAll("li"),
                                    w = scrollPicView.offsetWidth,
                                    len = lis.length;
                            for(var i=0; i<len; i++){
                                lis[i].style.width = w+"px";
                                if(i == len-1){
                                    scrollPicViewDiv.style.width = w * len + "px";
                                }
                            }
                            scroll_pic_view = new iScroll('scroll_pic_view', {
                                snap: true,
                                momentum: false,
                                hScrollbar: false,
                                useTransition: true,
                                onScrollEnd: function() {
                                    $("#scroll_pic_nav li").removeClass("on").eq(this.currPageX).addClass("on");
                                    //$("#scroll_pic_nav li.on").prev().addClass("left");
                                    //$("#scroll_pic_nav li.on").next().removeClass("left");

                                    var list=$("#scroll_pic_nav li");
                                    for(var k=0;k<list.length;k++){
                                        if(k<this.currPageX)
                                            $(list[k]).addClass("left");
                                        else
                                            $(list[k]).removeClass("left");
                                    }
                                }
                            });
							//图片轮播
							setInterval(function(){
								if(!$('#wxWhlistDetail').is(':hidden')){
									if(scrollLen<len){
										scroll_pic_view.scrollToPage('next', 0);
										scrollLen++;
									}else{
										scroll_pic_view.scrollToPage(0, 0);
										scrollLen = 1;
									}
								}
							}, 3000);
                            //
                            var nav_lis = new Array(lis.length);
                            d.write('<li class="on"></li>');
                            for(var i=1; i<nav_lis.length; i++){
                                d.write("<li></li>");
                            }
                        })(document, $);
                    </script>
                </ol>
            </div>
        </header>
    </div>
    <!--<div><img src="images/sellDetailimg0721.png" /></div>-->
    <div class="layout mt">
        <h1>【珍药材】<label id="breedName"></label></h1>
        <strong>仓单编号：<label id="wlId"></label></strong>
        <p>
        	<span>仓单状态：</span><label id="wlstate"></label><br/>
        	<span>合同编号：</span><label id="contractnum"></label><br/>
        	<span>等级/规格：</span><label id="grade"></label><br/>
            <span>数量：</span><label class="wlTotal"></label> <label class="dictValue"></label><br/>
            <span>产地：</span><label id="origin"></label><br/>
            <span>所在仓库：</span><label id="wareHouseName"></label><br/>
            <span>包装方式：</span><label id="packingway"></label><br/>
            <span>入库时间：</span><label id="wlrkDate"></label><br/>
        </p>
    </div>

    <div id="levelEvaDiv" class="layout">
        <h2>官方质检</h2>
        <p>性状描述：<label id="levelEva"></label></p>
    </div>

    <div class="layout">
        <h2>理化检验</h2>
        <p id="checknum" style="margin-left:0.2em;margin-top:-0.1em;"><b>抽检数量：</b><label id="numberOfjc"></label> <label>克</label></p>
        <table id="busiQualityItemTable" cellpadding="1" cellspacing="1" bgcolor="#a7a7a7">
            <tr id="busiQualityItem" bgcolor="#ededed">
                <th colspan="2" height="28" width="50%">检验项目</th>
                <th width="25%">标准规定</th>
                <th width="25%">检验结果</th>
            </tr>
        </table>
    </div>

    <div class="layout pb15">
        <ul class="account">
            <li>仓单总量<br/>
                <label class="wlTotal"></label> <label class="dictValue"></label></li>
            <li>已挂牌数量<br/>
                <label id="wlPlus"></label> <label class="dictValue"></label></li>
            <li>可挂牌数量<br/>
                <label id="wlSurplus"></label> <label class="dictValue"></label></li>
        </ul>
    </div>

</div>
<div class="sDetail-foot">
    <a class="yellow" id="zjReportPic" href="javascript:void(0);">查看质检报告单</a><a id="busilisting" class="red2" href="javascript:void(0);">我要挂牌</a>
</div>
<!--
<div class="h-demand report-box" id="zjReportPicDiv">
    <div class="close"></div>
    <div class="pic"><img id="zjReportPic" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif" /></div>
</div>
-->
</div>
<!--仓单详情 end-->

<script>
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
	
	//初始隐藏我的仓单详情
	$('#wxWhlistDetail').hide();
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
    show($('#check a[name=check]'),$('.h-demand'));
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
						
						var moreWxWhlists = '<li id='+wlId+'><a href="javascript:void(0);"><p class="tit">仓单号：'+wlId+'<i class="arrow-right"></i></p>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">品    名：'+breedName+'</span>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">总    量：'+wlTotal+dictValue+'</span>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">所在仓库：'+wareHouseName+'</span>';
			            moreWxWhlists = moreWxWhlists +'<span class="cont">入库日期：'+wlrkDateYMD+'</span></a></li>';
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
				layerMsg('操作失败！');
			}
		});
	});
	//查看我的仓单详情
	$('#wxWhlists').on('click','li a',function(){
		var wlId = $(this).parent('li').attr('id');
		$.ajax({
			async : true,
			cache : false,
			type : 'GET',
			data : {'wlId':wlId},
			dataType : 'json',
			url : '/wxWhlist/getWxWhlist',
			success : function(data) {
				var ok = data.ok;
				var msg = data.msg;
				if(ok){
					var wxWhlist = data.wxWhlist;
					//图片轮播
					var piclist = wxWhlist.piclist;
					$(piclist).each(function(i,o){
						var path = o.path;
						$('#scroll_pic_view_ul li a img').eq(i).attr('src','${RESOURCE_IMG_UPLOAD_WX}/'+path);
					});
					//仓单详情
					var wlId = wxWhlist.wlid==null?'':wxWhlist.wlid;
					var breedName = wxWhlist.breedname==null?'':wxWhlist.breedname;
					var grade = wxWhlist.grade==null?'':wxWhlist.grade;
					var dictvalue = wxWhlist.dictvalue==null?'':wxWhlist.dictvalue;
					var _wlstate = wxWhlist.wlstate;
					var wlstate ;
					if(_wlstate=='0'){
						wlstate ='未质押';
					}else{
						wlstate ='已质押';
					}
					var contractnum = wxWhlist.contractnum==null?'':wxWhlist.contractnum;
					var wlTotal = wxWhlist.wltotal==null?'0':wxWhlist.wltotal;
					var origin = wxWhlist.origin==null?'':wxWhlist.origin;
					var wareHouseName = wxWhlist.warehousename==null?'':wxWhlist.warehousename;
					var packingway = wxWhlist.packingway==null?'':wxWhlist.packingway;
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
					$(".sellDetail #wlId").text(wlId);
					$('#breedName').text(breedName);
					$('#grade').text(grade);
					$('#wlstate').text(wlstate);
					$('#packingway').text(packingway);
					$('#contractnum').text(contractnum);
					$('.wlTotal').each(function(){
						$(this).text(wlTotal);
					});
					$('.dictValue').each(function(){
						$(this).text(dictvalue);
					});
					$('#origin').text(origin);
					$('#wareHouseName').text(wareHouseName);
					$('#wlrkDate').text(wlrkDateYMD);
					//官方质检
					var levelEva = wxWhlist.levelEva==null?'':wxWhlist.levelEva;
					var numberOfjc = wxWhlist.numberofjc==null?'':wxWhlist.numberofjc;
					var busiQualityItem = wxWhlist.busiQualityItem==null?'':wxWhlist.busiQualityItem;
					var wlSurplus = wxWhlist.wlsurplus==null?'0':wxWhlist.wlsurplus;
					var wlPlus = accSub(wlTotal,wlSurplus);
					$('#levelEva').text(levelEva);
					$('#bqGrade').text(grade);
					$('#bqOrigin').text(origin);
					if(numberOfjc!=null && numberOfjc!=''){
						$('#numberOfjc').text(numberOfjc);
						$("#checknum").show();
					}else{
						$("#checknum").hide();
					}
					$('#busiQualityItem').nextAll().remove();
					$(busiQualityItem).each(function(i,o){
						var qualityItemName = o.qualityItemName;
						var qualityItemType = o.qualityItemType;
						var qualityItemStandard = o.qualityItemStandard;
						var qualityItemResult = o.qualityItemResult;
						$('#busiQualityItem').after('<tr bgcolor="#cfcfcf"><td height="25">'+qualityItemName+'</td><td>'+qualityItemType+'</td><td>'+qualityItemStandard+'</td><td>'+qualityItemResult+'</td></tr>');
					});
					$('#wlSurplus').text(wlSurplus);
					$('#wlPlus').text(wlPlus);
					//质检报告
					var zjReportPic = wxWhlist.zjReportPic==null?'':wxWhlist.zjReportPic;
					$('#zjReportPic').attr('href','/preview?img='+zjReportPic);
					
					//仓单挂牌
					var busilisting = wxWhlist.wlid==null?'':wxWhlist.wlid;
					$('#busilisting').attr('href','/listing/add/'+busilisting);
					
					//控制显示/隐藏
					if(levelEva==''){
						$('#levelEvaDiv').hide();
					}else{
						$('#levelEvaDiv').show();
					}
					if(busiQualityItem==''){
						$('#busiQualityItemTable').hide();
					}else{
						$('#busiQualityItemTable').show();
					}
					//切换效果
					$('#wxWhlist').hide();
					$('#wxWhlistDetail').show().animate({left:0},100);
					//重置轮播
					$("#scroll_pic_nav li").removeClass("on").eq(0).addClass("on");
					scroll_pic_view.scrollToPage(0, 0);
					scrollLen = 1;
					
					window.location.hash = '#wxWhlistDetail';
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
	});
    //返回我的仓单
	$('#backWxWhlist').click(function(){
		$('#wxWhlistDetail').hide();
		$('#wxWhlist').show().animate({left:0},100);
		
		var wlId = $('#wlId').text();
		window.location.hash = '#'+wlId;
	});
    //质检报告单
   // $('a[name=see]').click(function(){
     //   $('#zjReportPicDiv').show();
     //   bgHiu();
    //});
	//减法函数
	function accSub(arg1,arg2){
	　　 var r1,r2,m,n;
	　　 try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	　　 try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	　　 m=Math.pow(10,Math.max(r1,r2));
	　　 //动态控制精度长度
	　　 n=(r1>=r2)?r1:r2;
	　　 return ((arg1*m-arg2*m)/m).toFixed(n);
	};
</script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>