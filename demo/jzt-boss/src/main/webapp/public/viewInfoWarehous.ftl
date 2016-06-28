<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>入仓信息审核</title>
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
    		<h1 class="title1">入仓信息审核</h1>
           <form action="/infoWarehous/addInfoWarehous" method="POST" id="addInfoWarehousForm">
           	<input type="hidden" id="warehousId" value="${model.warehouseId!'' }"/>
                <ul class="store-search layout1 layout2 mt20">
                	  <li>
                	 	<span><font color="red">*</font>&nbsp;发布人：</span>
                    	${model.applyName!''}
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;手机号：</span>
                    	${model.applyMobile!'' }
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;品种名称：</span>
                    	${model.breed_Name!'' }
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;入仓数量：</span>
                    	${model.breedAmount!''}
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;仓库类型：</span>
                    	${model.warehouseType!'' }
                      </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;预计面积：</span>
                    	${model.warehouseArea!'' }
                      </li>
                      <li>
                      <span><font color="red">*</font>&nbsp;货物所在地：</span>
                      	${model.warehouseAddress!''}
	                  </li>
                      <li>
                	 	<span><font color="red">*</font>&nbsp;期望服务：</span>
                	 	${model.expectedService!'' }
                      </li>
                      <#if (model.status) = 0>
                       	<li>
                      		<span>&nbsp;备注：</span>
                      		<textarea id="remarksId" name="remarks"  class="text-store" nullmsg="" datatype="*" style=" height: 100px; resize: none;width: 72%;"></textarea>
                      	</li>
                      	<li>&nbsp;</li>
                      </#if>
                      <li class="pt20 clear">
                      <#if (model.status) = 0>
                       	<input id="valid" value="有效" class="btn-blue" type="button" onclick="checkInfoWarehous(1);" />
                      	<input id="valid" value="无效" class="btn-hui" type="button" onclick="checkInfoWarehous(2);"/>
					  	<input id="addCancel" value="返回" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" />
					  	<#else>
					  	<input id="addCancel" value="返回" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" />
                      </#if>
                      </li>             
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
function checkInfoWarehous(id){
	var warehouseId = $("#warehousId").val();
	var remarks = $("#remarksId").val();
	var status = id;
	var url = "/infoWarehous/checkInfoWarehous";
	$.ajax({
		type : "post",
		url : url,
		data : {warehouseId:warehouseId,remarks:remarks,status:status},
		dataType : "json",
		success : function(data){
			if(data.ok){
				bghui();
				Alert({
	    			str:'操作成功！',
	    			buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/infoWarehous";
	    				}
	    			}
	    			}]
	    		});
			}else{
				bghui();
				Alert({
	    			str:'操作失败！',
	    			buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/infoWarehous";
	    				}
	    			}
	    			}]
	    		});
			}
		}
	});
}
</script>
</body>
</html>