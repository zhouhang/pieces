<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的采购-批量发布</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
    <link href="${RESOURCE_CSS}/css/popup.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<!-- 头部  -->
    <#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
    <!-- 会员左侧 -->
    <#include 'common/left.ftl'>
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="order-box">
            <h2 class="o-title">我的采购</h2>
            <ul class="tabs clearfix">
                <li class="cur"><a href="/purchase/pub">发布采购</a></li>
                <li><a href="/purchase/manager">管理采购</a></li>
            </ul>
            <div class="stocks-box">
                <p class="caption">您的专属交易员是：${salesmanInfo.name}，电话${salesmanInfo.phone}，您在采购药材时碰到任何问题，可以电话寻求帮助。</p>
                <div class="chose">
                    <input type="radio" name="choose" value="1" /> 单条发布  <input type="radio" class="ml28"  name="choose" value="2" checked /> 批量发布
                </div>
                <dl class="min-hei">
                    <dd class="batch">
                      <!--<form  method="post" action="/purchase/batchSave" id="uploadExcelForm" enctype="multipart/form-data">-->
                        <input type="text" class="text-sty2 text-2 col_333" id="filePath" value="" datatype="fileType"  nullmsg="请选择正确的模版文件" />
                        <span class="relative dis-in-bk file ml10"><input type="file" value="选择文件" id="excelFile" name="excelFile"  />选择文件</span>
                        <input type="button" value="发布" class="send ml10" id="batchPub" />
                        <span class="Validform_checktip" id="tips"></span>
                      <!--</form>-->
                        <p class="mobai mt10"><a href="/purchase/download" class="dis-in-bk">下载模版</a> </p>
                    </dd>
                    <dd class="batch">
                        <p class="step">
                            操作步骤：<br/>
                            1、下载采购单模版，如果已有模版可以跳过；<br/>
                            2、点击“选择文件”，在电脑中找到模版文件；<br/>
                            3、点击“发布”，上传采购模版。<br/>
                        </p>
                    </dd>
                </dl>
            </div>




        </div>
    </div>

</div>

<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->

<!-- 提交成功弹层 -->
<div class="popup-box">
    <div class="close"></div>
    <div class="box4">
        <div align="center">
            <p class="sty1">提交成功！</p>
        </div>

        <p class="sty2" align="center" id="sucMsg"></p>
    </div>
</div>
<!-- 提交成功弹层 over -->

<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" charset="utf-8" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>

$(function(){

	//发布类型 单选or批量
	$("input[name='choose']").bind("click",function(){
	var selectType = $('input[name="choose"]:checked').val();
		if(selectType=='1'){
			window.location.replace("/purchase/pub");
		}
	}); 
	
    
    $("#batchPub").click(function(){
    	var excel = $("#excelFile").val();
		if(!/.(xls|xlsx)$/.test(excel)){
	     $("#tips").html("<font color='red'>请选择正确的模版文件</font>");
			return false;
		 }else{
		 $("#tips").html("");
		 }
		$.ajaxFileUpload({
			url:'/purchase/batchSave', 
			secureuri:false,
			fileElementId:'excelFile',
			dataType: 'json',
			success: function (data, status)
			{
				var ok =data.ok;
		   		var msg = data.msg;	
		   		if(ok){
		    	$('#sucMsg').html(msg);
		    	
		    	$('.popup-box').show();
            	bgHiu();
            	
            	//关闭层，刷新页面
		    $('.close').on('click',function(){
	            $('.popup-box').hide();
	            $('.bghui').remove();
	            window.location.replace(data.url);
       		 });
		    }
		    else{
		    	tips(msg);
		    }
			},
			error: function (data, status, e)
			{
		       tips(data.msg);
			}
	      });
    });
    
    //不需要刷新当前界面的tips
	function tips(str){
		bghui();
		Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-style',
                ev:{click:function(data){
                	 disappear();
                     $(".bghui").remove();
                 }
               }
            }]
	    });
	};
	
	$("#excelFile").change(function(){
		var excel = $("#excelFile").val();
		$("#filePath").val(excel);
		if(!/.(xls|xlsx)$/.test(excel)){
	     $("#tips").html("<font color='red'>请选择正确的模版文件</font>");
			return false;
		 }
		 return true;
	});
	
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }

    //关闭层
    function close(els,cont){
        els.on('click',function(){
            cont.hide();
            $('.bghui').remove();
        })
    }
    close($('.close'),$('.popup-box'));
    close($('.close'),$('.breed-list'));

    //显示层
    function show(els,cont){
        els.on('click',function(){
            cont.show();
            bgHiu()
        })
    }
    show($('#Confirm'),$('.popup-box'));

    //弹层显示垂直居中位置
    function popUp(par,elm){
        var wid = elm.width();
        var Hei = elm.height();
        par.css({
            'position':'fixed',
            'top':'50%',
            'left':'50%',
            'zIndex':10001,
            'marginLeft':-wid/2,
            'marginTop':-Hei/2,
            'border':'3px solid #cf5445',
            'background':'#ffffff'
        })
    }
    popUp($('.popup-box'),$('.popup-box .box4'));

    //验证
    $(".stocks-box").Validform({
        tiptype:3
    });

    //日期控件
    $('#Time').click(function(){
        WdatePicker({
            startDate:'%y/%M/%d',
            dateFmt:'yyyy/MM/dd',
            readOnly:true
        });
    });
    //获取焦点隐藏默认值
    function Focus(els){
        var default_value = els.val();
        els.focus(function(){
            if($(this).val() == default_value){
                $(this).val('');
            }
        }).blur(function(){
            if($(this).val() == ''){
                $(this).val(default_value);
            }
        })
    }
    Focus($('input[name=breed]'));
    Focus($('input[name=account]'));

    // 品种弹层联想效果
    var value = 'amc';
    var isMouseIn = false;
    $('input[name=breed]').keyup(function(){
        //ajax get value
        if(value){
            $(this).next('.breed-list').show();
        }else{
            $(this).next('.breed-list').hide();
        }

        $('.breed-list a').on('click',function(){
            $('input[name=breed]').val($(this).text());
            $(this).parent('.breed-list').hide();
            return false;
        })
    });
    $('input[name=breed]').keydown(function(){
        $(this).next('.breed-list').hide();
    });
    $('input[name=breed]').blur(function(){
        if(!isMouseIn){
            $(this).next('.breed-list').hide();
        }
    });
    $('.breed-list').hover(
            function(){isMouseIn=true;},
            function(){isMouseIn=false;}
    )

});
</script>
</body>
</html>