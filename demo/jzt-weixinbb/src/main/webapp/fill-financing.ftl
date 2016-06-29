<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-我要融资</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">
    <style>
    .select1{
    	border-bottom: 1px solid #cacaca;
		border-radius: 3px;
		height: 2.5em;line-height:2.5em;
		color: #cacaca;
		width:28%;
    }
</style>	
</head>
<body class="relative">
<div class="sell-box-head" style="position:relative;margin-bottom:-0.5em;">
    <a href="/myzyc"><i class="back"></i></a>
    <div align="center" class="inStore-title">填写融资信息</div>
</div>
<form id="financingForm" name="financingForm">
<div class="rz-demand" >
    <ul><li><input placeholder="请输入姓名" id="financeName" name="financeName" value="${user.companyName}"  class="input-text1" /></li>
        <li><input placeholder="请输入联系方式" id="financeMobile" name="financeMobile" onblur="checkMobile(this)" value="${user.mobile}" class="input-text1" /></li>
        <li><input placeholder="请输入品种名称" id="financeBreedName" name="financeBreedName"  class="input-text1" /></li>
        <li><input placeholder="请输入规格/等级" id="financeBreedStandardLevel" name="financeBreedStandardLevel"  class="input-text1" /></li>
        <li style="width:50%; display:inline-block;"><input placeholder="请输入数量" onkeyup="clearNoNum(this)" id="financeBreedAmount" name="financeBreedAmount"  class="input-text1" /></li>
        <li style="display:inline-block; border:0;padding-left:1em;padding-right: 1em;" >/</i></li>
        <li class="relative" id="unit_label" datatype="unit" style="width:30%; display:inline-block;">
            <label id="h_unit" class="col-ca">吨</label><i class="allow-ri"></i>
                <span id="span_unit" class="unit sty1" style="display: none;">
                    <a href="#">吨</a>
                    <a href="#">公斤</a>
                    <a href="#">条</a>
                    <a href="#">个</a> 
                </span>
        </li>
    </ul>
</div>
<div class="inStore-box2">
<h3 class="title_rz">货物所在地</h3>
    <div class="box-layout inStore-select">
        <select id="addWxSupplyAreaProvince" class="select1"><option value="" rel="">请选择省份</option>
        <#if areas??>
   			<#list areas as area>
				<option value="${(area.name)!'' }" rel="${(area.code)!'' }">${(area.name)!'' }</option>
    		</#list>
   		</#if>
        </select> - <select id="addWxSupplyAreaCity" class="select1" rel=""><option value="" rel="">请选择城市</option>
        </select> - <select id="addWxSupplyAreaPlace"  name="financeAddress" class="select1" rel=""><option value="" rel="">请选择区/县</option>
        </select>
    </div>
    <h3 class="title_rz">质押货物的产新时间</h3>
    <input id="financeDate" name="financeDate" type="text" placeholder="请输入货物产新时间" style="border-bottom: 1px solid #cacaca;border-radius: 3px;height: 1.7em;line-height:1.7em;color: #cacaca;margin-left:0.9em;width:90%;" readonly="readonly"/>
    <span id="postNameRule" class="box-layout col_red"></span>
    <div class="box-layout mt20" align="center"><input type="button" value="重置" class="btn-white" style="width:40%;" /><input id="addfinance" type="button" value="提交申请" class="btn-red1" style=" width:40%; margin-left:1em;" /></div>
</div>
</form>
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${RESOURCE_JS_WX}/js/flipsnap.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>	
<script type="text/javascript">
	//日期控件
	$('#financeDate').click(function(){
		WdatePicker({
			dateFmt:'yyyy-MM-dd',
			readOnly:true
		});
	});
	
	//重置form
	$('.btn-white').on('click',function(){
		resetSubmitForm();
	})
	
	//下拉框span加上事件，点击它周围地方层消失
	$(document).on("click",function(e){
		var target = $(e.target);
		if(target.closest("#unit_label").length == 0 && target.closest("#span_unit").length == 0){
			$("#span_unit").hide();
		}
	}) 
	
	// 区域级联
    $('#financingForm select').change(function(){
    	var id = $(this).attr('id');
    	var code = $(this).find('option:selected').attr('rel');
    	if(code==''){
    		return;
    	}
    	switch(id)
		{
			case 'addWxSupplyAreaProvince':
			  	$.ajax({
					async : true,
					cache : true,
					type : "GET",
					data : {'code' : code},
					dataType : "json",
					url : "/financing/getAreasByCode",
					success : function(data) {
						var ok = data.ok;
						if(ok){
							$('#addWxSupplyAreaCity option:first').nextAll().remove();
							var areas = data.obj;
							$.each(areas, function(index,area){
								$('#addWxSupplyAreaCity').append('<option value="' + area.name + '" rel="' + area.code + '">' + area.name + '</option>');
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						layerMsg('操作失败！');
					}
				});
				break;
				
			case 'addWxSupplyAreaCity':
			  	$.ajax({
					async : true,
					cache : true,
					type : "GET",
					data : {'code' : code},
					dataType : "json",
					url : "/financing/getAreasByCode",
					success : function(data) {
						var ok = data.ok;
						if(ok){
							$('#addWxSupplyAreaPlace option:first').nextAll().remove();
							var areas = data.obj;
							$.each(areas, function(index,area){
								$('#addWxSupplyAreaPlace').append('<option value="' + area.name + '" rel="' + area.code + '">' + area.name + '</option>');
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						layerMsg('操作失败！');
					}
				});
				break;
				
			default:
			 	break;
		}
    });
    
    //信息框
    function layerMsg(msg){
    	layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
    }
    
    //清空提交的表单的数据
	function resetSubmitForm(){
		$("form[id='financingForm'] :text").val(''); 
		$("form[id='financingForm'] select").val(''); 
	}
	
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }

    $(function(){
        //关闭层
        function close(els){
            els.on('click',function(){
                $('.h-demand').hide();
                $('.bghui').remove();
            })
        }
        //显示层
        function show(els,cont){
            els.on('click',function(){
                cont.show();
                bgHiu()
            })
        }
        show($('#searchM'),$('#Check'));

        //下拉选择
        $('[datatype=unit] .unit').hide();
        $('[datatype=unit]').on('click',function(){
            $(this).children('.unit').show();
        });
        $('.unit a').on('click',function(){
            $(this).parents('li').children('label').text($(this).text());
            $(this).parent().hide();
            return false;
        });
    });
    
    //表单验证
    function valid(){
    	var financeName = $("#financeName").val().trim();
    	var financeMobile = $("#financeMobile").val().trim();
		var financeBreedName = $("#financeBreedName").val().trim();
		var financeBreedStandardLevel = $("#financeBreedStandardLevel").val().trim();
		var financeBreedAmount = $("#financeBreedAmount").val().trim();
   		var financeAddress = $("#addWxSupplyAreaPlace").val().trim();
   		var financeDate = $("#financeDate").val().trim();
		var postNameRule = $("#postNameRule");
		
		if(financeName==null || financeName==''){
			postNameRule.html("请输入姓名").show();
			return false;
		}else if(financeMobile==null || financeMobile==''){
			postNameRule.html("请输入联系方式").show();
			return false;
		}else if(financeBreedName==null || financeBreedName==''){
			postNameRule.html("请输入品种名称").show();
			return false;
		}else if(financeBreedStandardLevel==null || financeBreedStandardLevel==''){
			postNameRule.html("请输入规格/等级").show();
			return false;
		}else if(financeBreedAmount==null || financeBreedAmount==''){
			postNameRule.html("请输入数量").show();
			return false;
		}else if(financeAddress==null || financeAddress==''){
			postNameRule.html("请选择质押货物所在地").show();
			return false;
		}else if(financeDate==null || financeDate==''){
			postNameRule.html("质押货物的产新时间").show();
			return false;
		}
		postNameRule.html("").hide();
	}
	
	function checkMobile(obj){
		var financeMobile = obj.value;
		var postNameRule = $("#postNameRule");
		if(financeMobile!=null && financeMobile!=''){
			if(ismobile(financeMobile)==false){
				postNameRule.html("请输入正确的手机号码").show();
				return false;
			}else{
				postNameRule.html("").hide();
				return true;
			}
		}	
	}
	
	//手机号码验证
	function ismobile(ss){
		var re=/[0-9]{11}$/;
        if(!re.test(ss))
         {
           return false;
         }
         return true;
	}

    function clearNoNum(obj){
      obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
	}
    
    //验证新增我要融资
	$('#addfinance').click(function(){
		if(valid() == false){
			return false;
		}
		var financeBreedAmount = $("#financeBreedAmount").val()+$("#h_unit").text();
		$("#financeBreedAmount").val(financeBreedAmount);
		var params = $("#financingForm").serialize();
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			data : params,
			dataType : 'json',
			url : '/financing/saveFinace',
			success : function(data) {
				if(!data){
					window.location.href='/myzyc?tag=1';
				}else{
					resetSubmitForm();
					layerMsg('我要融资提交失败！');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				resetSubmitForm();
				layerMsg('我要融资提交失败！');
			}
		});
    })
</script>
</body>
</html>