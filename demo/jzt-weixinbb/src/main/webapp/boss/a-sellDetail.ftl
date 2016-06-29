<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>微信后台管理-供应详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/flipsnap.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
</head>
<body>
<div class="sell-box-head">
    <i class="back" onclick="history.go(-1)"></i>
    <div align="center" class="inStore-title">供应详情</div>
</div>
<div class="pt32"></div>
<div class="a-headbg"></div>
<div class="box-layout">

    <p class="bd-font1">
	        发布人：${(supply.userName)!''}<br/>
	        联系电话：${(supply.userMobile)!''}<br/>
	        品种名称：${(supply.breedName)!''}<br/>
	        规格：${(supply.breedStandardLevel)!''}<br/>
	        价格：${(supply.priceUnitPrice)!''}<br/>
	        库存：${(supply.qtyUnitQty)!''}<br/>
	        产地：${(supply.breedPlace)!''}<br/>
	        货物所在地：${(supply.areaName)!''}<br/>

    	<#if (supply.picVoList??) && (supply.picVoList?size gt 0)>
    		<input type="button" class="a-btn-red" value="查看图片" id="See" style="margin: 0.5em 0;" />
    	</#if>
    </p>
    
    <#if (supply.status)?? && (supply.status == 0)>
	    <div class="a-bd-box1">
	        <div>
	            <textarea name="mcpForumPost.postTitle" id="subject" class="beizhu" size="500" maxlength="500" theme="simple" onblur="check()" onkeyup="checkLength(this)" accesskey="1" tabindex="11" placeholder="输入备注"></textarea>
	            <div class="cl">
	        		<p id="subjectchk">
	        			可输入
			            <strong id="checklen">500</strong>
		            	字
			        </p>
	                <span id="postNameRule" class="spn_flag_1" style="display:none"></span>
	            </div>
	        </div>
	
	        <div align="center" class="opr-1">
	            <input type="button" class="a-btn-1" value="无效" onclick="checkSupply('${(supply.supplyId)!''}', '${(supply.sypplyResource)!''}', '2')"/>&nbsp;&nbsp;
	            <input type="button" class="a-btn-1" value="有效" onclick="checkSupply('${(supply.supplyId)!''}', '${(supply.sypplyResource)!''}', '1')"/>
	        </div>
	    </div>
    </#if>
</div>
<!--查看图片弹层 start-->
<div class="seebox">
	<#if (supply.picVoList??) && (supply.picVoList?size gt 0)>
		<#list supply.picVoList as pic>
			<#if pic_index == 0>
				<div class="bigpic"><img src="${RESOURCE_IMG_UPLOAD}/${(pic.originalPath)!''}"/> </div>
			</#if>
		</#list>
	    
	    <div class="smallpic">
	        <ul>
				<#list supply.picVoList as pic>
					<#if pic_index == 0>
						<li class="cur"><img src="${RESOURCE_IMG_UPLOAD}/${(pic.thumbnailPath)!''}" rel="${RESOURCE_IMG_UPLOAD}/${(pic.originalPath)!''}" /></li>
					<#else>
						<li><img src="${RESOURCE_IMG_UPLOAD}/${(pic.thumbnailPath)!''}" rel="${RESOURCE_IMG_UPLOAD}/${(pic.originalPath)!''}" /></li>
					</#if>
				</#list>
	        </ul>
	    </div>
    </#if>
</div>
<div class="see-close"><img src="${RESOURCE_IMG_WX}/images/seeClose.png" /> </div>
<!--查看图片弹层 end-->


<script>
    // 审核供应信息
    function checkSupply(supplyId, applyResource, status){
    	var remarks = $('#subject').val();
    	$.ajax({
				async : false,
				cache : false,
				type : "POST",
				data : {"supplyId" : supplyId, "applyResource" : applyResource, "remarks" : remarks, "status" : status},
				dataType : "json",
				url : "/Boss/wxBossSupply/checkSupply",
				success : function(data) {
					var ok = data.ok;
					var msg = data.msg;
					if(ok == true){
			    		layer.open({
						    content: msg,
						    shadeClose: false,
						    style: 'text-align:center;',
						    btn: ['OK'],
						    yes: function(index){
							    layer.close(index);
							    history.go(-1);
							}
						    
						});
						 
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
    };
    
    
    //提示框
    function layerMsg(msg){
    	layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
    };


    //背景变灰效果
    function bgHui(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
    
    
    //点击查看图片显示大图
    $(function(){
        $('#See').on('click',function(){
            $('.seebox').show();
            $('.see-close').show();
            bgHui();
        });
        $('.see-close').on('click',function(){
            $('.seebox').hide();
            $(this).hide();
            $('.bghui').remove();
        });
        $('.smallpic ul li').on('click',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $('.bigpic img').attr('src',$(this).children('img').attr('rel'));
        });
        
        
        //小图拖动
        Flipsnap('.smallpic ul',{
            distance:98,
            maxPoint:7
        });
        
        
    });
</script>
<script>
    // 只要键盘一抬起就验证编辑框中的文字长度，最大字符长度可以根据需要设定
    function checkLength(obj)  {

        var maxChars = 500;//最多字符数
        var curr = maxChars - obj.value.length;
        if( curr > 0 ){
            document.getElementById("checklen").innerHTML = curr.toString();
        }else{
            document.getElementById("checklen").innerHTML = '0';
        }

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