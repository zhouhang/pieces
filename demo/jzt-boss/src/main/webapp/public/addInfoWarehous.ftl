<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>添加入仓信息</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body>
<#include "home/top.ftl" />
<div class="wapper">
	<#include "home/left.ftl" />
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">添加入仓信息</h1>
           <form action="/infoWarehous/addInfoWarehous" method="POST" id="addInfoWarehousForm">
                <ul class="store-search layout1 layout2 mt20">
                	  <li>
                	 	<span><font color="red">*</font>&nbsp;发布人：</span>
                    	<input name="applyName" value="" class="text-store text-7" type="text"/>
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;手机号：</span>
                    	<input name="applyMobile" value="" class="text-store text-7" type="text"/>
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;品种名称：</span>
                    	<input id="autoCompleteBreedName" name="breedName" value="" class="text-store text-7" type="text"/>
                    	<input name="breedId" type="hidden" value="">
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;入仓数量：</span>
                    	<input name="breedAmount" value="" class="text-store text-7" type="text"/>
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;仓库类型：</span>
                    	<input name="warehouseType" value="" class="text-store text-7" type="text"/>
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;预计面积：</span>
                    	<input name="warehouseArea" value="" class="text-store text-7" type="text"/>
                      </li>
                      <li>
                      <span><font color="red">*</font>&nbsp;货物所在地：</span>
                      <div id="select-bg">
	                    <span>
		               	 <select id="areaProvince" name="warehouseAddress" style="*margin-top:-6px;">
		               	 	<option value="" rel="">请选择省份</option>
				            <#if areas??>
			            		<#list areas as area>
									<option value="${area.name!'' }" rel="${area.code!'' }">${area.name!'' }</option>
			            		</#list>
			            	</#if>
		               	 </select>
	           	 		</span>
           	 	 	  </div>
           	 	 	   &nbsp;
           	 	 	  <div id="select-bg">
	                    <span>
	                     <select id="areaCity" name="warehouseAddress" style="*margin-top:-6px;">
		               	 	<option value="" rel="">请选择城市</option>
		               	 </select>
	                    </span>
	                   </div>
	                   &nbsp;
	                   <div id="select-bg">
	                    <span>
		                     <select id="areaPlace" name="warehouseAddress" style="*margin-top:-6px;">
			               	 	<option value="">请选择区/县</option>
			               	 </select>
		                    </span>
	                   </div>
	                  </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;期望服务：</span>
                    	<input name="expectedService" id="pledge" value="药材质押" type="checkbox"/>&nbsp;<label for="pledge">药材质押</label>
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<input name="expectedService" id="sale" value="在线销售" type="checkbox"/>&nbsp;<label for="sale">在线销售</label>
                      </li>
                      <li class="pt20 clear">
                      <input id="addBtn" value="确认" class="btn-blue" type="submit" />
                      <input id="clear-btn" value="重置" class="btn-hui" type="button" />
					  <input id="addCancel" value="取消" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" /></li>                  
                </ul>
            </form>
    	</div>
    </div>
</div>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/autocomplete-styles.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-autocomplete/jquery.autocomplete.js"></script>
<script>
//是否存在品种记录 有则为true 没有则为false 
var isData ;
$(function(){
	/**
	 * 文本自动填充
	 */
	$('#autoCompleteBreedName').autocomplete({  
		deferRequestBy:200,//keyUp之后发起请求的间隔时间  Default: 0
		serviceUrl: '/infoWarehous/getTypeName',//ajax后台请求地址
		onSearchComplete: function (query) {//请求成功之后的回调
			//serviceUrl请求成功后,验证其品种是否存在
			var url = "/infoWarehous/getTypeName";
			$.ajax({
				type : "POST",
				url : url,
				data : {query:query},
				dataType : "json",
				success : function(map){
					var data = map.list;
					isData = map.isData;
					if(data == '' || data == null){
						bghui();
						Alert({str:"请先在品种管理中添加该品种！"});
					}
				}
			});
		},
		onSelect: function (suggestion) {
			//下拉层选择触发事件 回写breedId
			var url = "/infoWarehous/getTypeName";
			$.ajax({
				type : "POST",
				url : url,
				data : {query:suggestion.value},
				dataType : "json",
				success : function(map){
					var data = map.list[0];
					$("input[name='breedId']").val(data.breedId);
				}
			});
   		}
    }); 
	
	//重置
	$("#clear-btn").click(function(){
		$("#addInfoWarehousForm input[type='text']").val('');
		
		var selectArr = $("#addInfoWarehousForm select");
		for (var i = 0; i < selectArr.length; i++) {
			selectArr[i].options[0].selected = true; 
		}
		
		var checkArr = $("#addInfoWarehousForm input[type='checkbox']");
		for (var i = 0; i < checkArr.length; i++) {
			checkArr[i].checked = false; 
		}
	});
	
	
	//表单验证
	$('#addInfoWarehousForm').on('submit',function(){
		var isSubmit=false;
		var applyName = $("input[name='applyName']").val();
		var applyMobile = $("input[name='applyMobile']").val();
		var breedName = $("input[name='breedName']").val();
		var breedId = $("input[name='breedId']").val();
		var breedAmount = $("input[name='breedAmount']").val();
		var warehouseType = $("input[name='warehouseType']").val();
		var warehouseArea = $("input[name='warehouseArea']").val();
		
		if(applyName =='' || applyName == null){
			bghui();
			Alert({str:"发布人不能为空!"});
			return isSubmit; 
		}
		if(applyMobile =='' || applyMobile == null){
			bghui();
			Alert({str:"手机号不能为空!"});
			return isSubmit; 
		}
		if(!/^1\d{10}$/.test(applyMobile)){
			bghui();
			Alert({str:"请填写正确的手机号！"});
			return isSubmit; 
		}
		if(!isData || breedId == '' || breedId == null){
			bghui();
			Alert({str:"请填写正确的品种名称且不能为空！"});
			return isSubmit;
		}
		if(breedAmount == '' || breedAmount == null){
			bghui();
			Alert({str:"请填写有效的入仓数量！"});
			return isSubmit;
		}
		if(warehouseType == '' || warehouseType == null){
			bghui();
			Alert({str:"请填写有效的仓库类型！"});
			return isSubmit;
		}
		if(warehouseArea == '' || warehouseArea == null){
			bghui();
			Alert({str:"预计面积不能为空！"});
			return isSubmit;
		}
		var warehouseAddress = $('#addInfoWarehousForm select[name=warehouseAddress]:eq(0)');
        if(warehouseAddress.val() == ''){
        	bghui();
			Alert({str:"请选择所在地！"});
			return isSubmit; 
        }
		var count = 0;
		$("input[name='expectedService']:checkbox").each(function() {
			if($(this).is(":checked")){
				count += 1; 
			}
		});
		if(count == 0){
			bghui();
			Alert({str:"至少选择一种期望服务类型!"});
			return isSubmit; 
		}
	})
	
	//省市三级联动
	$('#addInfoWarehousForm select').change(function(){
    	var id = $(this).attr('id');
    	var code = $(this).find('option:selected').attr('rel');
    	if(code==''){
    		return;
    	}
    	switch(id){
			case 'areaProvince':
			  	$.ajax({
					async : true,
					cache : true,
					type : "GET",
					data : {'code':code},
					dataType : "json",
					url : "/infoWarehous/getAreasByCode",
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
					url : "/infoWarehous/getAreasByCode",
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
})
</script>
</body>
</html>