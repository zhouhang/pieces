<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-仓单详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
</head>
<body>
<div class="sell-box-head">
    <a href="/wxWhlist/wxWhlistManage"><i class="back"></i></a>
    <div align="center" class="inStore-title">仓单挂牌</div>
</div>
<div class="sellDetail">
    <div class="wraper">
        <header id="scroll_pic_view" class="scroll_pic_view" style="overflow: hidden; ">
            <div id="scroll_pic_view_div" style="width: 3840px; -webkit-transition: -webkit-transform 0ms cubic-bezier(0.33, 0.66, 0.66, 1); -webkit-transform-origin: 0px 0px; -webkit-transform: translate(0px, 0px) translateZ(0px); ">
                <ul id="scroll_pic_view_ul">
                	<#if wxWhlist.piclist?? && (wxWhlist.piclist?size>0)>
                		<#list wxWhlist.piclist as pic>
                			<li><a onclick="return false;"><img src="${RESOURCE_IMG_UPLOAD_WX}/${pic.path!''}" /></a></li>
                		</#list>
                	</#if>
                </ul>
            </div>
            <div>
                <ol id="scroll_pic_nav" class="scroll_pic_nav">
                    <script>
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

                            var scroll_pic_view = new iScroll('scroll_pic_view', {
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
							var scrollLen = 1;
							setInterval(function(){
								if(scrollLen<len){
									scroll_pic_view.scrollToPage('next', 0);
									scrollLen++;
								}else{
									scroll_pic_view.scrollToPage(0, 0);
									scrollLen = 1;
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
        <h1>【珍药材】${wxWhlist.breedname!''}</h1>
        <strong>仓单编号：${wxWhlist.wlid!''}</strong>
        <p><span>等级/规格：</span>${wxWhlist.grade!''}<br/>
            <span>数量：</span>${wxWhlist.wltotal!''} ${wxWhlist.dictvalue!''}<br/>
            <span>产地：</span>${wxWhlist.origin!''}<br/>
            <span>所在仓库：</span>${wxWhlist.warehousename!''}<br/>
            <span>仓库入库时间：</span>${wxWhlist.wlrkdate?string('yyyy-MM-dd')!''}<br/>
        </p>
    </div>
    <div class="layout">
        <h2>官方质检</h2>
        <p>性状描述：${wxWhlist.levelEva!''}</p>
    </div>
    <div class="layout">
        <h2>理化检验</h2>
        <p><b>规格：</b>${wxWhlist.grade!''}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>产地：</b>${wxWhlist.origin!''}<br/><b>数量：</b>${wxWhlist.numberofjc!''} ${wxWhlist.dictvalue!''}</p>
        <table cellpadding="1" cellspacing="1" bgcolor="#a7a7a7">
            <tr bgcolor="#ededed">
                <th colspan="2" height="28" width="50%">检验项目</th>
                <th width="25%">标准规定</th>
                <th width="25%">检验结果</th>
            </tr>
            <#if wxWhlist.busiQualityItem?? && (wxWhlist.busiQualityItem?size>0)>
        		<#list wxWhlist.busiQualityItem as busiQualityItem>
        			<tr bgcolor="#cfcfcf">
		                <td height="25">${busiQualityItem.qualityItemName!''}</td>
		                <td>${busiQualityItem.qualityItemType!''}</td>
		                <td>${busiQualityItem.qualityItemStandard!''}</td>
		                <td>${busiQualityItem.qualityItemResult!''}</td>
		            </tr>
        		</#list>
        	</#if>
        </table>
    </div>
    <div class="layout pb15">
        <ul class="account">
            <li>仓单总量<br/>
                ${wxWhlist.wltotal!''} ${wxWhlist.dictvalue!''}</li>
            <li>已挂牌数量<br/>
                ${(wxWhlist.wltotal-wxWhlist.wlsurplus)!''} ${wxWhlist.dictvalue!''}</li>
            <li>可挂牌数量<br/>
                ${wxWhlist.wlsurplus!''} ${wxWhlist.dictvalue!''}</li>
        </ul>
    </div>
</div>
<div class="sDetail-foot">
    <a class="yellow" href="/preview?img=${wxWhlist.zjReportPic}">查看质检报告单</a><a class="red2" href="javascript:alert('暂未开放！');">我要挂牌</a>
</div>
<!--<div class="h-demand report-box" id="Report">
    <div class="close"></div>
    <div class="pic" id="reportPop"><img src="${RESOURCE_IMG_UPLOAD_WX}/${wxWhlist.zjReportPic!''}" /></div>
</div>
-->
<script>
    $(function(){
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
        show($('.h-demand'));
        //质检报告单
       // $('a[name=see]').on('click',function(){
         //   $('#Report').show();
         //   bgHiu();
          //  return false;
        //});
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