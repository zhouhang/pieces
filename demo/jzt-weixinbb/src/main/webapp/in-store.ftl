<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-我要入仓</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <style type="text/css">
    	.red{
		    color:red!important;
		}
		.borderRed{
		    border:1px solid red!important;
		}
    </style>
</head>
<body>
<div class="sell-box-head">
    <a href="/myzyc"><i class="back"></i></a>
    <div align="center" class="inStore-title">我要入仓</div>
</div>
<form id="wxWarehouseApplyForm" action="/wxWarehouseApply/addBusiWarehouseApply" method="post">
	<div class="box-layout inStore-box">
	    <ul class="search-report login" id="loginBox">
	        <li class="search-input login-box">
	            <input type="text" id="applyName" name="applyName" value="${user.companyName}" placeholder="请输入姓名" />
	        </li>
	        <li class="search-input phone">
	            <input type="text" id="applyMobile" name="applyMobile" value="${user.mobile}" placeholder="请输入您的手机号码" />
	        </li>
	    </ul>
	</div>
	<div class="inStore-box2">
	    <h3 class="title">入仓药材及数量</h3>
	    <table cellpadding="0" cellspacing="0" width="100%">
	        <tr>
	            <th width="20%">名称<br/><span>例：三七</span></th>
	            <th width="20%">数量<br/><span>50吨</span></th>
	            <th width="20%">仓库<br/><span>常温库</span></th>
	            <th width="20%">预计面积<br/><span>30平米</span></th>
	            <th width="20%">操作</th>
	        </tr>
	        <tr>
	            <td><input name="busiWarehouseApplies[0].breedName" class="text" type="text" /></td>
	            <td><input name="busiWarehouseApplies[0].breedAmount" class="text" type="text" /></td>
	            <td><input name="busiWarehouseApplies[0].warehouseType" class="text" type="text" /></td>
	            <td><input name="busiWarehouseApplies[0].warehouseArea" class="text" type="text" /></td>
	            <td></td>
	        </tr>
	        <tr>
	            <td colspan="5" class="add"><a href="#" class="addrow" id="add"><i></i>添加一行</a></td>
	        </tr>
	    </table>
	    <h3 class="title">货物所在地</h3>
	    <div class="box-layout inStore-select">
	        <select id="areaProvince" name="warehouseAddress" class="select"><option value="" rel="">请选择省份</option>
	        	<#if areas??>
	       			<#list areas as area>
						<option value="${area.name!'' }" rel="${area.code!'' }">${area.name!'' }</option>
	        		</#list>
	       		</#if>
	        </select> — <select id="areaCity" name="warehouseAddress" class="select"><option value="" rel="">请选择城市</option></select> — <select id="areaPlace" name="warehouseAddress" class="select"><option value="">请选择区/县</option></select>
	    </div>
	    <h3 class="title">期望服务</h3>
	    <div class="box-layout inStore-select">
	        <input name="expectedService" type="checkbox" class="checkbox" value="药材质押" />  药材质押&nbsp;&nbsp;&nbsp;&nbsp;<input name="expectedService" type="checkbox" class="checkbox" value="在线销售" /> 在线销售
	    </div>
	    <div class="box-layout">
	        <span id="msg" style="display: none;"></span>
	    </div>
	    <div class="box-layout mt20" style="padding-left:5px;"><input id="wxWarehouseApplySubmit" type="submit" value="提 交" class="btn-red" style="height: 2.5em; line-height: 2.5em;" /></div>
	</div>
</form>

<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>	
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
<script>
    $(function(){
    	//信息框
        function layerMsg(msg){
        	layer.open({
			    content: msg,
			    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
			    time: 2
			});
        };
        //验证入仓信息表单
		var wxWarehouseApplyForm = $("#wxWarehouseApplyForm").Validform({
		    tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=$('#msg');
					//cssctl(objtip,o.type);
					objtip.text(msg).show();
				}	
			},
		    ajaxPost:true,
		    showAllError:false,
		    datatype:{
     			"cn":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				var reg1=/[\u4E00-\u9FA5]{2,50}/,
     					reg2=/^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
     				if(gets==""){
     					return false;
     				}
     				if(!reg1.test(gets)){
     					return false;
     				}
     				if(reg2.test(gets)){
     					return false;
     				}
     				return true;
     			},
     			"ck":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				$('.checkbox:first').val('药材质押');
					$('.checkbox:last').val('在线销售');  
     				var checkedbox = $('.checkbox:checked');
					if(checkedbox.length==0){
						return false;
					}
     				return true;
     			},
     			"st":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				var ok = false;  
     				var selects = $('.select option:selected');
     				$(selects).each(function(){
     					var selectVal = $(this).val();
     					if(selectVal!=''){
     						ok = true;
     						return false;
     					}
     				});
     				return ok;
     			}
     		},
		    beforeSubmit:function(curform){
			    $('#wxWarehouseApplySubmit').val('提 交 中').attr('disabled','disabled');
		    },
		    callback:function(data){
		    	$('#wxWarehouseApplySubmit').val('提 交').removeAttr('disabled');
		    	var ok = data.ok;
		    	if(ok==undefined){
		    		$('#msg').text('网络繁忙，请稍后再试！');
				}else{
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
			    		layerMsg(msg);
			    	}
				}
		    }
		});
		wxWarehouseApplyForm.addRule([
			{
		        ele:"#applyName",
		        datatype:"cn",
		        nullmsg:"请输入姓名！",
		        errormsg:"姓名：5-50个字符，必须包含中文！",
		        sucmsg:""
		    },
		    {
		        ele:"#applyMobile",
		        datatype:"m",
		        nullmsg:"请输入您的手机号码！",
		        errormsg:"请输入正确的手机号码！",
		        sucmsg:""
		    },
		    {
		        ele:".text",
		        datatype:"*",
		        nullmsg:"请填写入仓信息！",
		        errormsg:"请填写入仓信息！",
		        sucmsg:""
		    },
		    {
		        ele:".select",
		        datatype:"st",
		        nullmsg:"请选择货物所在地！",
		        errormsg:"请选择货物所在地！",
		        sucmsg:""
		    },
		    {
		        ele:".checkbox",
		        datatype:"ck",
		        nullmsg:"请选择期望服务！",
		        errormsg:"请选择期望服务！",
		        sucmsg:""
		    }
		]);		
		//地区级联
	    $('#wxWarehouseApplyForm select').change(function(){
	    	var id = $(this).attr('id');
	    	var code = $(this).find('option:selected').attr('rel');
	    	if(code==''){
	    		return;
	    	}
	    	switch(id)
			{
				case 'areaProvince':
				  	$.ajax({
						async : true,
						cache : true,
						type : "GET",
						data : {'code':code},
						dataType : "json",
						url : "/wxWarehouseApply/getAreasByCode",
						success : function(data) {
							var ok = data.ok;
							if(ok){
								$('#areaCity option:first').nextAll().remove();
								var areas = data.obj;
								$.each(areas,function(index,area){
									$('#areaCity').append('<option value="'+area.name+'" rel="'+area.code+'">'+area.name+'</option>');
								});
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							alert('操作失败！');
						}
					});
					break;
				case 'areaCity':
				  	$.ajax({
						async : true,
						cache : true,
						type : "GET",
						data : {'code':code},
						dataType : "json",
						url : "/wxWarehouseApply/getAreasByCode",
						success : function(data) {
							var ok = data.ok;
							if(ok){
								$('#areaPlace option:first').nextAll().remove();
								var areas = data.obj;
								$.each(areas,function(index,area){
									$('#areaPlace').append('<option value="'+area.name+'" rel="'+area.code+'">'+area.name+'</option>');
								});
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							alert('操作失败！');
						}
					});
					break;
				default:
				 	break;
			}
	    });
	    var index = 1;
	    //添行加
        $('#add').on('click',function(){
            var string = '<tr><td><input name="busiWarehouseApplies['+index+'].breedName" class="text" type="text" /></td><td><input name="busiWarehouseApplies['+index+'].breedAmount" class="text" type="text" /></td><td><input name="busiWarehouseApplies['+index+'].warehouseType" class="text" type="text" /></td><td><input name="busiWarehouseApplies['+index+'].warehouseArea" class="text" type="text" /></td><td><i class="remove"></i></td></tr>';
            $('.inStore-box2 table tr:last').before(string);
            wxWarehouseApplyForm.addRule([
			    {
			        ele:".text",
			        datatype:"*",
			        nullmsg:"请填写入仓信息！",
			        errormsg:"请填写入仓信息！",
			        sucmsg:""
			    }
			]);		
			index++;
            return false;
        });
        //删除行
        $('#wxWarehouseApplyForm').on('click','.remove',function(){
            $(this).parents('tr').remove();
        })
		//击活改变边框色
        $(':input[type=text]').click(function(){
            $(this).parent().css('borderColor','#f59e73');
        }).blur(function(){
            $(this).parent().css('borderColor','#cfcfcf');
        });
//touch事件替换CLICK事件
        $('input[type=button],.pop-up2 a,i[datatype=person]').touchStart(function () {
            $(this).addClass('hover');
        });
        $('input[type=button],.pop-up2 a,i[datatype=person]').touchMove(function () {
            $(this).addClass('hover');
        });
        $('input[type=button],.pop-up2 a,i[datatype=person]').touchEnd(function () {
            $(this).removeClass('hover');
        });
        $('input[type=button],.pop-up2 a,i[datatype=person]').tapOrClick(function () {
            $(this).removeClass('hover');
        });
        //击活改变边框色
        $(':input[type=text]').touchStart(function(){$(this).parent().css('borderColor','#f59e73')});
        $(':input[type=text]').touchMove(function(){$(this).parent().css('borderColor','#f59e73')});
        $(':input[type=text]').touchEnd(function(){$(this).parent().css('borderColor','#f59e73')});
        $(':input[type=text]').tapOrClick(function(){$(this).parent().css('borderColor','#cfcfcf')});
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