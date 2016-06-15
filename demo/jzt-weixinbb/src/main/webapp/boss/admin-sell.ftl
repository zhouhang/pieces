<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>公共系统-供求信息</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">
    <link rel="stylesheet" href="${RESOURCE_JS_WX}/js/jquery-ui/jquery-ui.css">
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/flipsnap.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>	
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-ui/jquery-ui.js"></script>
</head>
<body>

	<!-- 顶部 -->
    <div class="sell-box-head">
        <a href="/WxBoss/manager"><i class="back"></i></a>
        <span class="fr" id="addCheck">
            <a href="#1" name="add" tel="400">
            </a><a href="#2" name="check"></a>
        </span>
    </div>
    
	<!-- tab -->
    <div class="tabsfixed">
        <ul id="tab_id" class="tabs tabs2 clearfix">
            <li id="tab_supply" class="cur"><span>供应信息</span></li>
            <li id="tab_demand"><span>求购信息</span></li>
            <li id="tab_warehous"><span class="na">入仓信息</span></li>
        </ul>
    </div>

	<!-- 信息列表 -->
    <div id="conts" class="sells-box">
        <!-- 供应信息列表 -->
        <ul id="supply">
        	<#if supplyList?? && supplyList?size gt 0>
        		<#list supplyList as wxSupplyVo>
		            <li><div onclick="window.location.href='/Boss/wxBossSupply/supplyDetail?supplyId=${(wxSupplyVo.supplyId)!''}&applyResource=${(wxSupplyVo.sypplyResource)!''}'">
			                <span class="past1">
			                  <strong>${(wxSupplyVo.breedName)!''}</strong>| <b>${(wxSupplyVo.breedStandardLevel)!''}，${(wxSupplyVo.breedPlace)!''}</b>
			                </span><span class="charge past1"><strong class="red">
								                    			<#if statusMap??>
										                   			<#list statusMap?keys as key>
								               							<#if wxSupplyVo.status == key>
								                   							${statusMap[key]!''}
									                   					</#if>
										                    		</#list>
									                   			</#if> 
								                			  </strong>
	                				</span>
			                <div class="cont2">
			                    <p>
			                        <span>发布人：</span>${(wxSupplyVo.userName)!''}<br/>
			                        <span>联系电话：</span>${(wxSupplyVo.userMobile)!''}<img onclick="call(${(wxSupplyVo.userMobile)!''});event.cancelBubble=true;" src="${RESOURCE_IMG_WX}/images/phone0727.png" style="width:45px; padding-left: 0.5em;"/><br/>
			                        <span>创建时间：</span>${(wxSupplyVo.createTime?string("yyyy-MM-dd"))!''}<br/>
			                        <span>来源：</span><#if supplyResourcesMap??><#list supplyResourcesMap?keys as key><#if wxSupplyVo.sypplyResource == key>${supplyResourcesMap[key]!''}</#if></#list></#if><br/>
			                    </p>
			                </div>
		                </div>
		            </li>
        		</#list>
        	</#if>
			
			<#if supplyList?? && supplyList?size gt 0>
				<#if supplyList?size gte 10>
					<!-- 数据大于等于10条显示更多 -->
		        	<div id="moreSupply" class="load" onclick="querySupply()"></div>
		        <#else>
		        	<!-- 数据少于10条隐藏更多 -->
		        	<div id="moreSupply" style="display:none" class="load" onclick="querySupply()"></div>
		        </#if>
		        
		        <!-- 有数据隐藏提示 -->
		        <p id="noSupply" style="display:none; text-align: center; margin:0px 0px 15px">没有找到符合条件的供应信息！</p>
			<#else>
				<!-- 没有数据隐藏更多，显示提示 -->
				<div id="moreSupply" style="display:none" class="load" onclick="querySupply()"></div>
				<p id="noSupply" style="text-align: center; margin:0px 0px 15px">没有找到符合条件的供应信息！</p>
			</#if>
        </ul>
       
        <!-- 求购信息列表 -->
        <ul id="demand"></ul>
    
        <!-- 入仓信息列表 -->
        <ul id="warehous"></ul>
    </div>
    
    
	<!-- 供应信息高级查询弹层 -->
    <div class="h-demand" id="supplyChecker" onclick="closeUnitLayer()">
        <div class="close"></div>
        <ul class="relative">
        <form id="supplyListForm">
            <li><input name="userName" placeholder="请填写发布人" class="input-text1"/></li>
            <li><input name="userMobile" placeholder="请填联系电话" class="input-text1"/></li>
            <li><input name="breedName" placeholder="请填品种名称" class="input-text1"/></li>
            <li class="bn pa"><label class="la">状态</label>
            	<span class="relative"><input id="supplyStatus" type="text" datatype="ZhaTai" readonly="readonly" class="input-text2a" value=""/>
                <span class="unit sty1">
                	<a href="#">全部</a>
        			<#if statusMap??>
               			<#list statusMap?keys as key>
   							<a href="#">${statusMap[key]!''}</a>
                		</#list>
           			</#if>                 
                </span>
                </span>
            </li>
            <li class="bn pa"><label class="la">信息来源</label>
                <span class="relative"><input id="supplyResource" type="text" datatype="laiY" readonly="readonly" class="input-text2a" value="" />
                <span class="unit sty1">
                	<a href="#">全部</a>
        			<#if supplyResourcesMap??>
               			<#list supplyResourcesMap?keys as key>
   							<a href="#">${supplyResourcesMap[key]!''}</a>
                		</#list>
           			</#if> 
                </span>
                </span>
            </li>
            <li class="bn pa"><label>创建时间</label></li>
            <li class="bn">
	            <input type="text" id="supplyWdate1" name="startDate" class="input-text2" readonly="readonly" />
	             — 
	            <input type="text" id="supplyWdate2" name="endDate" class="input-text2" readonly="readonly" />
            </li>
            <li class="bn mt1"><input class="btn-red" type="button" id="supplySubmit" value="查 询" onclick="querySupply(true)"/></li>
        </form>
        </ul>
    </div>
    <!-- 供应信息高级查询弹层  end -->

	<!-- 求购信息高级查询弹层 -->
    <div class="h-demand" id="demandChecker" onclick="closeUnitLayer()">
        <div class="close"></div>
        <ul class="relative">
        <form id="demandListForm">
            <li><input name="userName" placeholder="请填写发布人" class="input-text1"/></li>
            <li><input name="userMobile" placeholder="请填联系电话" class="input-text1"/></li>
            <li><input name="breedName" placeholder="请填品种名称" class="input-text1"/></li>
            <li class="bn pa"><label class="la">状态</label>
            	<span class="relative"><input id="demandStatus" type="text" datatype="ZhaTai" readonly="readonly" class="input-text2a" value=""/>
                <span class="unit sty1">
                	<a href="#">全部</a>
        			<#if statusMap??>
               			<#list statusMap?keys as key>
   							<a href="#">${statusMap[key]!''}</a>
                		</#list>
           			</#if>    
                </span>
                </span>
            </li>
            <li class="bn pa"><label class="la">信息来源</label>
                <span class="relative"><input id="demandResource" type="text" datatype="laiY" readonly="readonly" class="input-text2a" value="" />
                <span class="unit sty1">
                	<a href="#">全部</a>
        			<#if demandResourcesMap??>
               			<#list demandResourcesMap?keys as key>
   							<a href="#">${demandResourcesMap[key]!''}</a>
                		</#list>
           			</#if> 
                </span>
                </span>
            </li>
            <li class="bn pa"><label>创建时间</label></li>
            <li class="bn">
	            <input type="text" id="demandWdate1" name="startDate" class="input-text2" readonly="readonly" />
	             — 
	            <input type="text" id="demandWdate2" name="endDate" class="input-text2" readonly="readonly" />
            </li>
            <li class="bn mt1"><input class="btn-red" type="button" id="demandSubmit" value="查 询" onclick="queryDemand(true)"/></li>
        </form>
        </ul>
    </div>
	<!-- 求购信息高级查询弹层  end-->
	
	<!-- 入仓信息高级查询弹层 -->
    <div class="h-demand" id="warehousChecker" onclick="closeUnitLayer()">
        <div class="close"></div>
        <ul class="relative">
        <form id="warehousListForm">
            <li><input name="applyName" placeholder="请填写发布人" class="input-text1"/></li>
            <li><input name="applyMobile" placeholder="请填联系电话" class="input-text1"/></li>
            <li><input name="breedName" placeholder="请填品种名称" class="input-text1"/></li>
            <li class="bn pa"><label class="la">状态</label>
            	<span class="relative"><input id="warehousStatus" type="text" datatype="ZhaTai" readonly="readonly" class="input-text2a" value=""/>
                <span class="unit sty1">
                	<a href="#">全部</a>
        			<#if statusMap??>
               			<#list statusMap?keys as key>
   							<a href="#">${statusMap[key]!''}</a>
                		</#list>
           			</#if>    
                </span>
                </span>
            </li>
            <li class="bn pa"><label class="la">信息来源</label>
                <span class="relative"><input id="warehousResource" type="text" datatype="laiY" readonly="readonly" class="input-text2a" value="" />
                <span class="unit sty1">
                	<a href="#">全部</a>
        			<#if warehousResourcesMap??>
               			<#list warehousResourcesMap?keys as key>
   							<a href="#">${warehousResourcesMap[key]!''}</a>
                		</#list>
           			</#if> 
                </span>
                </span>
            </li>
            <li class="bn pa"><label>创建时间</label></li>
            <li class="bn">
	            <input type="text" id="warehousWdate1" name="createTimeStart" class="input-text2" readonly="readonly" />
	             — 
	            <input type="text" id="warehousWdate2" name="createTimeEnd" class="input-text2" readonly="readonly" />
            </li>
            <li class="bn mt1"><input class="btn-red" type="button" id="warehousSubmit" value="查 询" onclick="queryWarehous(true)"/></li>
        </form>
        </ul>
    </div>
	<!-- 入仓信息高级查询弹层  end-->
	
	
	<!-- 添加供应信息弹层 -->
    <div class="h-demand" id="addGY" onclick="closeUnitLayer()">
    	<form id="addWxSupplyForm" action="/Boss/wxBossSupply/addWxSupply" method="post">
	        <div class="close"></div>
	        <ul>
	            <li><input placeholder="请输入发布人"   id="addWxSupplyUserName"           name="userName" class="input-text1" /></li>
	            <li><input placeholder="请输入联系电话" id="addWxSupplyUserMobile"         name="userMobile" class="input-text1" /></li>
	            <li><input placeholder="请输入品种名称" id="addWxSupplyBreedName"		  name="breedName" class="input-text1" /></li>
	            <li><input placeholder="请输入规格"     id="addWxSupplyBreedStandardLevel" name="breedStandardLevel" class="input-text1" /></li>
	            <li><input placeholder="请输入产地"     id="addWxSupplyBreedPlace"         name="breedPlace" class="input-text1" /></li>
	            
	            <!-- 价格 -->
	            <li class="bn pa"><label class="la">价格</label>
	                <input id="addWxSupplyPrice" name="price" type="text" class="input-text2b" value="" />元/
	                <span class="relative">
	                	<input id="addWxSupplyPriceUnit" name="priceUnit" type="text" datatype="ZhaTai" readonly="readonly" class="input-text2a" value="" style="width: 30%" />
		                <span class="unit sty1">
	                    	<#if dicts??>
			            		<#list dicts as dict>
			            			<a href="#">${(dict.dictValue)!''}</a>
			            		</#list>
			            	</#if>
		                </span>
	                </span>
	            </li>
	            
	            <!-- 库存 -->
	            <li class="relative bn pa"><label class="la">库存</label>
	                <input id="addWxSupplyQty" name="qty" type="text" class="input-text2b" value="" />
	                <span class="relative">
	                	<input id="addWxSupplyQtyUnit" name="qtyUnit" type="text" datatype="ZhaTai" readonly="readonly" class="input-text2a" value="" style="width: 30%" />
		                <span class="unit sty1">
	                    	<#if dicts??>
			            		<#list dicts as dict>
			            			<a href="#">${(dict.dictValue)!''}</a>
			            		</#list>
			            	</#if>
		                </span>
	                </span>
	            </li>
	            
	            <!-- 货物所在地 -->
	            <li class="bn pa"><label>货物所在地</label></li>
	            <li class="bn">
	                <select id="addWxSupplyAreaProvince" class="input-text2 select" style="width:32%;"><option value="" rel="">请选择省份</option>
			        	<#if areas??>
			       			<#list areas as area>
								<option value="${(area.name)!'' }" rel="${(area.code)!'' }">${(area.name)!'' }</option>
			        		</#list>
			       		</#if>
	                </select>
	                <select id="addWxSupplyAreaCity" class="input-text2 select" style="width:32%;"><option>请选择城市</option></select>
	                <select id="addWxSupplyAreaPlace" name="areaName" class="input-text2 select" style="width:32%;"><option value="">请选择区/县</option></select>
	            </li>
	            
	            <!-- 图片 -->
	            <li style="height: auto; border: 0 none;">
	                <div class="pic-upfile">
	                    <ul id="wxSupplyPic">
	                        <li class="file-bg"><input name="file" type="file" class="file" accept="image/*" multiple="multiple"></li>
	                    </ul>
	                </div>
	                <div style="padding: 0.8em 0 0 1em;">可上传6张照片</div>
	            </li>
	            
	            <!-- 验证表单提示语 -->
	            <li style="height: auto; border: 0 none;"><span id="addSupplyMsg" style="margin-left: 0.4em;display: none;color:black;"></span></li>
	            
	            <!-- 提交重置按钮 -->
	            <li style="height: auto; border: 0 none;">
	                <input value="重置" type="reset" class="a-btn-1 fl" style="padding: 0.3em 1.5em;" />
	                &nbsp;&nbsp;
	                <input id="addSupplySubmit" value="添加" type="submit" class="a-btn-1 fr" style="padding: 0.3em 1.5em;" />
	            </li>
	        </ul>
		</form>
    </div>
	<!-- 添加供应信息弹层 end -->
	
    <!-- 添加求购信息弹层 -->
    <div class="h-demand" id="addQG" onclick="closeUnitLayer()">
    	<form id="addBusiPurchaseApplyForm" action="/WxBoss/demandManager/addDemand" method="post">
	        <div class="close"></div>
	        <ul>
	            <li><input placeholder="请输入发布人"   id="addBusiPurchaseApplyUserName" name="userName"  class="input-text1" /></li>
	            <li><input placeholder="请输入联系电话" id="addBusiPurchaseApplyUserMobile" name="userMobile"  class="input-text1" /></li>
	            <li><input placeholder="请输入品种名称" id="addBusiPurchaseApplyBreedName" name="breedName"  class="input-text1" /></li>
	            <li><input placeholder="请输入规格"     id="addBusiPurchaseApplyBreedStandardLevel" name="breedStandardLevel"  class="input-text1" /></li>
	            <li><input placeholder="请输入数量"     id="addBusiPurchaseApplyQtyUnitQty" name="qtyUnitQty"  class="input-text1" /></li>
	            <li><input placeholder="请输入产地"     id="addBusiPurchaseApplyPlace" name="place"  class="input-text1" /></li>
	            
	            <!-- 价格 -->
	            <li class="bn pa"><label class="la">价格</label>
	                <input id="addBusiPurchaseApplyBreedPrice" name="breedPrice" type="text"  class="input-text2b" value="" />元/
	                <span class="relative">
	                <input id="addBusiPurchaseApplyBreedPriceUnit" name="breedPriceUnit" type="text" datatype="ZhaTai" readonly="readonly" class="input-text2a" value="" style="width: 30%" />
		                <span class="unit sty1">
	                    	<#if dicts??>
			            		<#list dicts as dict>
			            			<a href="#">${(dict.dictValue)!''}</a>
			            		</#list>
			            	</#if>
		                </span>
	                </span>
	            </li>
	
				<!-- 描述 -->
	            <li class="relative bn pa"><label>描述</label></li>
	            <li class="bn" style="height: 13.5em;">
	                <div>
	                    <textarea id="addBusiPurchaseApplyDepict" name="depict" id="subject" class="beizhu" size="500" maxlength="500" theme="simple" onblur="check()" onkeyup="checkLength(this)" accesskey="1" tabindex="11" placeholder="输入文本"></textarea>
	                    <div class="cl">
			        		<p id="subjectchk">
			        			可输入
					            <strong id="checklen">500</strong>
				            	字
					        </p>
	                        <span id="postNameRule" class="spn_flag_1" style="display:none"></span>
	                    </div>
	                </div>
	            </li>

	            <!-- 验证表单提示语 -->
	            <li style="height: auto; border: 0 none;"><span id="addBusiPurchaseApplyMsg" style="margin-left: 0.4em;display: none;color:black;"></span></li>
	            
				<!-- 提交重置按钮 -->
	            <li style="height: auto; border: 0 none;">
	                <input value="重置" type="reset" class="a-btn-1" style="padding: 0.3em 2em;" />
	                &nbsp;&nbsp;
	                <input id="addBusiPurchaseApplySubmit"  value="添加" type="submit" class="a-btn-1" style="padding: 0.3em 2em;" />
	            </li>
	        </ul>
		</form>
    </div>
    <!-- 添加求购信息弹层 end -->

    <!-- 添加入仓信息弹层  -->
    <div class="h-demand" id="addRC" onclick="closeUnitLayer()">
    	<form id="addWarehousForm" action="/WxBoss/warehousManager/addWarehous" method="post">
	        <div class="close"></div>
	        <ul>
	            <li><input placeholder="请输入发布人"   id="addWarehousApplyName" name="applyName"  class="input-text1" /></li>
	            <li><input placeholder="请输入联系电话" id="addWarehousApplyMobile" name="applyMobile"  class="input-text1" /></li>
	            <li>
	            	<input name="breedId" type="hidden" value=""/>
	            	<input placeholder="请输入品种名称" id="addWarehousBreedName" name="breedName"  class="input-text1" />
	            </li>
	            <li><input placeholder="请输入入仓数量" id="addWarehousBreedAmount" name="breedAmount"  class="input-text1" /></li>
	            <li><input placeholder="请输入预计面积" id="addWarehousWarehouseArea" name="warehouseArea"  class="input-text1" /></li>
	            <li class="relative" id="format">
	            	<label class="col-ca" id="addWarehousWarehouseType">请选择仓库类型</label><i class="allow-ri"></i>
	            	<!-- form只不接受label的值 -->
	            	<input name="warehouseType" type="hidden" value=""/>
	                <span class="unit" style="display: block;">
	                    <a href="#">常温库</a>
	                    <a href="#">阴凉库</a>
	                    <a href="#">冷库</a>
	                </span>
	            </li>
	
				<!-- 货物所在地 -->
	            <li class="bn pa" style="padding-top: 0.8em;"><label>货物所在地</label></li>
	            <li class="bn">
	                <select id="addWarehousAreaProvince" class="input-text2 select" style="width:32%;"><option>请选择省份</option>
			        	<#if areas??>
			       			<#list areas as area>
								<option value="${(area.name)!'' }" rel="${(area.code)!'' }">${(area.name)!'' }</option>
			        		</#list>
			       		</#if>
	                </select>
	                <select id="addWarehousAreaCity" class="input-text2 select" style="width:32%;"><option>请选择城市</option></select>
	                <select id="addWarehousAreaPlace" name="warehouseAddress" class="input-text2 select" style="width:32%;"><option value="">请选择区/县</option></select>
	            </li>
	            
	            <!-- 期望服务 -->
	            <li class="bn pa" style="padding-top: 0.8em;"><label>期望服务</label></li>
	            <li class="bn" style="margin-top: -0.5em; color: #3e3e3e;">
	                <input id="expectedService" name="expectedService" value="药材质押" type="checkbox" /> 药材质押&nbsp;&nbsp;&nbsp;
	                <input id="expectedService" name="expectedService" value="在线销售" type="checkbox" /> 在线销售
	            </li>
	
	            <!-- 验证表单提示语 -->
	            <li style="height: auto; border: 0 none;"><span id="addWarehousMsg" style="margin-left: 0.4em;display: none;color:black;"></span></li>
	
				<!-- 提交重置按钮 -->
	            <li style="height: auto; border: 0 none; margin-top: 1em;">
	                <input id="addWarehousReset" value="重置" type="reset" class="a-btn-1" style="padding: 0.3em 2em;" />
	                &nbsp;&nbsp;
	                <input id="addWarehousSubmit" value="添加" type="submit" class="a-btn-1" style="padding: 0.3em 2em;" />
	            </li>
	        </ul>
		</form>
    </div>
    <!-- 添加入仓信息弹层 end -->

<!-- 供应信息交互  -->
<script>
	// 分页标识
	var pageNoSupplly = 1;


	// 供应信息高级查询日期控件
	$('#supplyWdate1').click(function(){
		WdatePicker({
			maxDate:'#F{$dp.$D(\'supplyWdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#supplyWdate2').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'supplyWdate1\',{d:1});}',
			readOnly:true
		});
	});
	
	
    // 查询供应信息
    function querySupply(isReload){
    	if(isReload){
			pageNoSupplly = 1;    	
    	}else{
    		// 查询更多供应信息
    		pageNoSupplly++;
    	}
    	
    	var params = "pageNo=" + pageNoSupplly + "&"+ $("#supplyListForm").serialize();
    	
    	// 设置状态
		var supplyStatus = $('#supplyStatus').val();
    	if(supplyStatus != null && supplyStatus != '' && supplyStatus != '全部'){
    		if(supplyStatus == '未处理'){
    			params += "&status=0";
    		}else if(supplyStatus == '有效'){
    			params += "&status=1";
    		}else if(supplyStatus == '无效'){
    			params += "&status=2";
    		}else if(supplyStatus == '用户撤销'){
    			params += "&status=3";
    		}
    	}
    	
    	// 设置来源
		var supplyResource = $('#supplyResource').val();
    	if(supplyResource != null && supplyResource != '' && supplyResource != '全部'){
    		if(supplyResource == '微信'){
    			params += "&sypplyResource=0";
    		}else if(supplyResource == '客服'){
    			params += "&sypplyResource=2";
    		}
    	}
    	
    	$.ajax({
			async : false,
			cache : false,
			type : "GET",
			data : params,
			dataType : "json",
			contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			url : "/Boss/wxBossSupply/getSupply",
			success : function(data) {
				// 重载列表时，清空原列表数据
				if(isReload){
					$('#supply').empty();

					// 没有供应信息提示
					$('#supply').append('<p id="noSupply" style="display:none; text-align: center; padding:0px 0px 20px">没有找到符合条件的供应信息！</p>');
					
					// 加载更多供应信息
					$('#supply').append('<div id="moreSupply" class="load" style="display:none" onclick="querySupply()"></div>');
				}
					
				var supplys = data.supplyList;
				
				// 查询无数据，并且是重载页面，则显示无结果提示
				if(isReload && (supplys == null || supplys.length == 0)){
					$('#noSupply').show();
				}
				
				// 查询无数据或者数据少于10条，则不显示“加载更多”
				if(supplys == null || supplys.length < 10){
					$('#moreSupply').hide();
				}else{
					$('#moreSupply').show();
				}
				
		    	$.each(supplys, function(i, supply){
			    	// 设置状态
					var status = '';
			    	if(supply.status != null){
			    		if(supply.status == 0){
			    			status = '未处理';
			    		}else if(supply.status == 1){
			    			status = '有效';
			    		}else if(supply.status == 2){
			    			status = '无效';
			    		}else if(supply.status == 3){
			    			status = '用户撤销';
			    		}
			    	}
			    	
			    	// 设置来源
					var resource = '';
			    	if(supply.sypplyResource != null){
			    		if(supply.sypplyResource == 0){
			    			resource = '微信';
			    		}else if(supply.sypplyResource == 2){
			    			resource = '客服';
			    		}
			    	}

					// 设置日期
					var wlrkDate = supply.createTime;
					var wlrkDateYMD = '';
					if(wlrkDate != null){
						wlrkDate = new Date(wlrkDate);
						var wlrkDateMonth = wlrkDate.getMonth() + 1;
						if(wlrkDateMonth < 10){
							wlrkDateMonth = "0" + wlrkDateMonth;
						}
						var wlrkDateDate = wlrkDate.getDate();
						if(wlrkDateDate < 10){
							wlrkDateDate = "0" + wlrkDateDate;
						}
						wlrkDateYMD = wlrkDate.getFullYear() + '-' + wlrkDateMonth + '-' + wlrkDateDate;
					}

		    		var htmlStr = '';
					htmlStr += '<li><div onclick="window.location.href=\'/Boss/wxBossSupply/supplyDetail?supplyId=' + supply.supplyId + '&applyResource=' + supply.sypplyResource + '\'">';
					htmlStr += '<span class="past1">';
					htmlStr += '<strong>' + supply.breedName + '</strong>| <b>' + supply.breedStandardLevel + '，' + supply.breedPlace + '</b>';
					htmlStr += '</span><span class="charge past1"><strong class="red">' + status + '</strong></span>';
					htmlStr += '<div class="cont2">';
					htmlStr += '<p>';
					htmlStr += '<span>发布人：</span>' + supply.userName + '<br/>';
					htmlStr += '<span>联系电话：</span>' + supply.userMobile + '<img src="${RESOURCE_IMG_WX}/images/phone0727.png" style="width:45px; padding-left: 0.5em;" onclick="call(' + supply.userMobile + ');event.cancelBubble=true;"/><br/>';
					htmlStr += '<span>创建时间：</span>' + wlrkDateYMD + '<br/>';
					htmlStr += '<span>来源：</span>' + resource + '<br/>';
					htmlStr += '</p>';
		    		htmlStr += '</div>';
		    		htmlStr += '</div>';
		    		htmlStr += '</li>';
		    		
		    		$('#moreSupply').before(htmlStr);
		    	});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('操作失败！');
			}
		});
		
    }
    
    
   	// 区域级联
    $('#addWxSupplyForm select').change(function(){
    	var id = $(this).attr('id');
    	var code = $(this).find('option:selected').attr('rel');
    	if(code == undefined || code ==''){
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
					url : "/Boss/wxBossSupply/getAreasByCode",
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
					url : "/Boss/wxBossSupply/getAreasByCode",
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

    
	// 添加供应信息上传图片
	$('.file').fileupload({
		url: '/Boss/wxBossSupply/uploadPic',
		autoUpload: true,
		singleFileUploads: false,
        dataType: 'json',
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png|bmp)$/i,
        maxFileSize: 5000000,
        messages: {
            acceptFileTypes: '不支持的文件类型！',
            maxFileSize: '不支持文件大小超过5M的图片！'
        }
    }).on('fileuploadsubmit', function (e, data) {
       	var filesLength = data.files.length;
       	var picImgsLength = $(this).parent('li').prevAll('li').find('img').length;
		if(filesLength + picImgsLength > 6 || picImgsLength > 6){
			layerMsg("最多添加6张图片！");
			return false;
		}
		$.each(data.files, function (index,file) {
           $('.file-bg').before('<li class="see" style="height: auto; border: 0 none;"><img class="uploading" src="${RESOURCE_JS_WX}/js/jQuery-File-Upload/images/loading.gif"><i rel=""></i></li>');
        });
        
        slidePic();
        
        picImgsLength = $(this).parent('li').prevAll('li').find('img').length;
       	if(picImgsLength == 6){
			$('.file-bg').hide();
		}
    }).on('fileuploadprocessalways', function (e, data) {
        if(data.files.error){
        	layerMsg(data.files[0].error);
		}
    }).on('fileuploaddone', function (e, data) {
    	var picImgs = $('.file-bg').prevAll('li').find('img.uploading');
    	var ok = data.result.ok;
    	if(ok){
    		var picPaths = data.result.obj;
    		$.each(picPaths, function (index,picPath) {
    			picPath = '${RESOURCE_IMG_UPLOAD}/' + picPath;
    			//加载图片
	        	var img = new Image();
		    	img.onload = function(){
		    		$(picImgs[index]).removeClass('uploading').attr('src',picPath).after('<input type="hidden" name="picPath" value="'+picPath+'" />');
		    	};
		    	img.onerror = function(){
		    		layerMsg('图片加载失败！');
		    	};	
		    	img.src = picPath;
	        });
		}else{
			var msg = data.result.msg;
			if(msg != undefined){
				layerMsg(msg);
			}
		}
    }).on('fileuploadfail', function (e, data) {
        layerMsg('图片上传失败！');
    });
    
    
	//删除图片
	$('.pic-upfile ul').on('click','li i',function(){
		var pic = $(this).parent('li');
		$(pic).remove();
		$('.file-bg').show();
    });
        
        
    //验证新增微信供应信息表单
    var addWxSupplyForm = $('#addWxSupplyForm').Validform({
    	btnSubmit:"#addSupplySubmit",
	    tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip = $('#addSupplyMsg');
				if(o.type == 3){
					cssctl(objtip, o.type);
					objtip.text(msg).show();
				}else{
					objtip.text('').hide();
				}
			}
		},
	    ajaxPost:true,
	    showAllError:false,
	    datatype:{
 			"priceUnitStr":function(gets,obj,curform,regxp){
 				var priceVal = $('#addWxSupplyPrice').val();
 				var priceUnitVal = $('#addWxSupplyPriceUnit').val();
 				if(priceVal != '' && priceUnitVal == ''){
 					return false;
 				}
 				return true;
 			},
 			"qtyUnitStr":function(gets,obj,curform,regxp){
 				var qtyVal = $('#addWxSupplyQty').val();
 				var qtyUnitVal = $('#addWxSupplyQtyUnit').val();
 				if(qtyVal != '' && qtyUnitVal == ''){
 					return false;
 				}
 				return true;
 			}
 		},
	    ajaxurl:{
	        success:function(data,obj){
	            var ok = data.ok;
	            if(ok == undefined){
	            	$('#addSupplyMsg').text('网络繁忙，请稍后再试！');
	            }
	        },
	        error:function(data,obj){
	            var readyState = data.readyState;
	            if(readyState != 0){
	            	$('#addSupplyMsg').text('网络繁忙，请稍后再试！');
	            }
	        }
	    },
	    
	    // 提交前验证图片
	    beforeSubmit:function(curform){
			//验证图片上传状态
	    	var uploadingNum = $('.pic-upfile').find('img.uploading').length;
	    	if(uploadingNum > 0){
	    		layerMsg('请等待图片上传完成再添加！');
	    		return false;
	    	}
	    	
	    	//验证是否上传图片
    		var picPathsLength = $('#addWxSupplyForm input[name=picPath][value!=""]').length;
    		if(picPathsLength == 0){
    			$('#addSupplyMsg').text('最少上传1张图片！').show();
    			return false;
    		}else if(picPathsLength > 6){
    			$('#addSupplyMsg').text('最多上传6张图片！').show();
    			return false;
    		}else{
    			$('#addSupplyMsg').text('').hide();
    		}
    		
	    	$('#addSupplySubmit').val('添加中').attr('disabled','disabled');
	    },
	    
	    // 提交完成
	    callback:function(data){
	    	$('#addSupplySubmit').val('添加').removeAttr('disabled');
	    	var ok = data.ok;
	    	var msg = data.msg;
	    	if(ok){
	    		layer.open({
				    content: msg,
				    shadeClose: false,
				    style: 'text-align:center;',
				    btn: ['OK'],
				    yes: function(index){
					    layer.close(index);
					    closeLayer();
					    window.location.reload(true);
					}
				});
	    	}else{
	    		if(msg != undefined){
	    			layerMsg(msg);
	    		}else{
	    			layerMsg('网络繁忙，请稍后再试！');
	    		}
	    	}
	    }
	});
	
	addWxSupplyForm.addRule([
		{
	        ele:"#addWxSupplyUserName",
	        datatype:"*1-50",
	        nullmsg:"请填写发布人！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyUserMobile",
	        datatype:"m",
	        nullmsg:"请填写联系电话！",
	        errormsg:"请填写正确的电话号码！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyBreedName",
	        ajaxurl:"/Boss/wxBossSupply/checkBreedName",
	        datatype:"*1-50",
	        nullmsg:"请填写品种名称！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyAreaPlace",
	        datatype:"*1-50",
	        nullmsg:"请选择货物所在地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyBreedStandardLevel",
	        ignore:"ignore", 
	        datatype:"*1-50",
	        nullmsg:"请填写规格！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyPrice",
	        ignore:"ignore", 
	        datatype:"n1-10",
	        nullmsg:"请填写价格！",
	        errormsg:"请填写1到10位数字！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyPriceUnit",
	        datatype:"priceUnitStr",
	        nullmsg:"请选择价格单位！",
	        errormsg:"请填写1到2位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyQty",
	        ignore:"ignore", 
	        datatype:"n1-20",
	        nullmsg:"请填写库存！",
	        errormsg:"请填写1到20位数字！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyQtyUnit",
	        datatype:"qtyUnitStr",
	        nullmsg:"请选择库存单位！",
	        errormsg:"请填写1到2位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWxSupplyBreedPlace",
	        ignore:"ignore", 
	        datatype:"*1-50",
	        nullmsg:"请填写产地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    }
	]);
</script>
<!-- 供应信息交互 end -->

<!-- 求购信息交互 -->
<script>
	// 分页标识
	var pageNoDemand = 1;
	
	// 求购信息高级查询日期控件
	$('#demandWdate1').click(function(){
		WdatePicker({
			maxDate:'#F{$dp.$D(\'demandWdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#demandWdate2').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'demandWdate1\',{d:1});}',
			readOnly:true
		});
	});
	
	
    // 查询求购信息
    function queryDemand(isReload){
    	if(isReload){
			pageNoDemand = 1;    	
    	}else{
    		// 查询更多求购信息
    		pageNoDemand++;
    	}
    	
    	var params = "pageNo=" + pageNoDemand + "&"+ $("#demandListForm").serialize();
    	
    	// 设置状态
		var demandStatus = $('#demandStatus').val();
    	if(demandStatus != null && demandStatus != '' && demandStatus != '全部'){
    		if(demandStatus == '未处理'){
    			params += "&status=0";
    		}else if(demandStatus == '有效'){
    			params += "&status=1";
    		}else if(demandStatus == '无效'){
    			params += "&status=2";
    		}else if(demandStatus == '用户撤销'){
    			params += "&status=3";
    		}
    	}
    	
    	// 设置来源
		var demandResource = $('#demandResource').val();
    	if(demandResource != null && demandResource != '' && demandResource != '全部'){
    		if(demandResource == '微信'){
    			params += "&applyResource=0";
    		}else if(demandResource == '珍药材'){
    			params += "&applyResource='珍药材'";
    		}else if(demandResource == '客服'){
    			params += "&applyResource='客服'";
    		}
    	}
    	
    	$.ajax({
			async : false,
			cache : false,
			type : "GET",
			data : params,
			dataType : "json",
			contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			url : "/WxBoss/demandManager/getDemand",
			success : function(data) {
				if(isReload){
					$('#demand').empty();
					$('#demand').append('<p id="noDemand" style="display:none; text-align: center; padding:0px 0px 20px">没有找到符合条件的求购信息！</p>');
					$('#demand').append('<div id="moreDemand" class="load" style="display:none" onclick="queryDemand()"></div>');
				}
				
				var demands = data.demandList;
				
				// 查询无数据，并且是重载页面，则显示无结果提示
				if((demands == null || demands.length == 0) && isReload){
					$('#noDemand').show();
				}
				
				// 查询无数据或者数据少于10条，则不显示“加载更多”
				if(demands == null || demands.length < 10){
					$('#moreDemand').hide();
				}else{
					$('#moreDemand').show();
				}
				
		    	$.each(demands, function(i, demand){
			    	// 设置状态
					var status = '';
			    	if(demand.status != null){
			    		if(demand.status == 0){
			    			status = '未处理';
			    		}else if(demand.status == 1){
			    			status = '有效';
			    		}else if(demand.status == 2){
			    			status = '无效';
			    		}else if(demand.status == 3){
			    			status = '用户撤销';
			    		}
			    	}
			    	
			    	// 设置来源
					var resource = '';
			    	if(demand.applyResource != null){
			    		if(demand.applyResource == 0){
			    			resource = '微信';
			    		}else if(demand.applyResource == '珍药材'){
			    			resource = '珍药材';
			    		}else if(demand.applyResource == '客服'){
			    			resource = '客服';
			    		}
			    	}
		    	
		    		// 设置日期
					var wlrkDate = demand.createTime;
					var wlrkDateYMD = '';
					if(wlrkDate != null){
						wlrkDate = new Date(wlrkDate);
						var wlrkDateMonth = wlrkDate.getMonth() + 1;
						if(wlrkDateMonth < 10){
							wlrkDateMonth = "0" + wlrkDateMonth;
						}
						var wlrkDateDate = wlrkDate.getDate();
						if(wlrkDateDate < 10){
							wlrkDateDate = "0" + wlrkDateDate;
						}
						wlrkDateYMD = wlrkDate.getFullYear() + '-' + wlrkDateMonth + '-' + wlrkDateDate;
					}
					
		    		var htmlStr = '';
					htmlStr += '<li><div onclick="window.location.href=\'/WxBoss/demandManager/demandDetail?demandId=' + demand.demandId + '&applyResource=' + demand.applyResource + '\'">';
					htmlStr += '<span class="past1">';
					htmlStr += '<strong>' + demand.breedName + '</strong>| <b>' + demand.breedStandardLevel + '，' + demand.breedPlace + '</b>';
					htmlStr += '</span><span class="charge past1"><strong class="red">' + status + '</strong></span>';
					htmlStr += '<div class="cont2">';
					htmlStr += '<p>';
					htmlStr += '<span>发布人：</span>' + demand.userName + '<br/>';
					htmlStr += '<span>联系电话：</span>' + demand.userMobile + '<img src="${RESOURCE_IMG_WX}/images/phone0727.png" style="width:45px; padding-left: 0.5em;" onclick="call(' + demand.userMobile + ');event.cancelBubble=true;"/><br/>';
					htmlStr += '<span>创建时间：</span>' + wlrkDateYMD + '<br/>';
					htmlStr += '<span>来源：</span>' + resource + '<br/>';
					htmlStr += '</p>';
		    		htmlStr += '</div>';
		    		htmlStr += '</div>';
		    		htmlStr += '</li>';
		    		
		    		$('#moreDemand').before(htmlStr);
		    	});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('操作失败！');
			}
		});
		
    }
    

    // 验证新增求购信息表单
    var addBusiPurchaseApplyForm = $('#addBusiPurchaseApplyForm').Validform({
    	btnSubmit:"#addBusiPurchaseApplySubmit",
	    tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip = $('#addBusiPurchaseApplyMsg');
				if(o.type == 3){
					cssctl(objtip, o.type);
					objtip.text(msg).show();
				}else{
					objtip.text('').hide();
				}
			}
		},
	    ajaxPost:true,
	    showAllError:false,
	    datatype:{
 			"priceUnitStr":function(gets,obj,curform,regxp){
 				var priceVal = $('#addBusiPurchaseApplyBreedPrice').val();
 				var priceUnitVal = $('#addBusiPurchaseApplyBreedPriceUnit').val();
 				if(priceVal != '' && priceUnitVal == ''){
 					return false;
 				}
 				return true;
 			}
 		},
	    ajaxurl:{
	        success:function(data,obj){
	            var ok = data.ok;
	            if(ok == undefined){
	            	$('#addBusiPurchaseApplyMsg').text('网络繁忙，请稍后再试！');
	            }
	        },
	        error:function(data,obj){
	            var readyState = data.readyState;
	            if(readyState != 0){
	            	$('#addBusiPurchaseApplyMsg').text('网络繁忙，请稍后再试！');
	            }
	        }
	    },
	    beforeSubmit:function(curform){
	    	$('#addBusiPurchaseApplySubmit').val('添加中').attr('disabled','disabled');
	    },
	    callback:function(data){
	    	$('#addBusiPurchaseApplySubmit').val('添加').removeAttr('disabled');
	    	var ok = data.ok;
	    	var msg = data.msg;
	    	if(ok){
	    		layer.open({
				    content: msg,
				    shadeClose: false,
				    style: 'text-align:center;',
				    btn: ['OK'],
				    yes: function(index){
					    layer.close(index);
					    closeLayer();
					    window.location.reload(true);
					}
				});
	    	}else{
	    		if(msg != undefined){
	    			layerMsg(msg);
	    		}else{
	    			layerMsg('网络繁忙，请稍后再试！');
	    		}
	    	}
	    }
	});
	
	addBusiPurchaseApplyForm.addRule([
		{
	        ele:"#addBusiPurchaseApplyUserName",
	        datatype:"*1-50",
	        nullmsg:"请填写发布人！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyUserMobile",
	        datatype:"m",
	        nullmsg:"请填写联系电话！",
	        errormsg:"请填写正确的电话号码！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyBreedName",
	        ajaxurl:"/Boss/wxBossSupply/checkBreedName",
	        datatype:"*1-50",
	        nullmsg:"请填写品种名称！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyBreedStandardLevel",
	        ignore:"ignore", 
	        datatype:"*1-50",
	        nullmsg:"请填写规格！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyQtyUnitQty",
	        datatype:"*0-50",
	        ignore:"ignore",
	        nullmsg:"请填写数量！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyPlace",
	        datatype:"*0-50",
	        ignore:"ignore",
	        nullmsg:"请填写产地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyBreedPrice",
	        ignore:"ignore", 
	        datatype:"n1-10",
	        nullmsg:"请填写价格！",
	        errormsg:"请填写1到10位数字！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyBreedPriceUnit",
	        datatype:"priceUnitStr",
	        nullmsg:"请选择价格单位！",
	        errormsg:"请填写1到2位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addBusiPurchaseApplyDepict",
	        datatype:"*0-500",
	        ignore:"ignore",
	        nullmsg:"请填写描述！",
	        errormsg:"请填写1到500位任意字符！",
	        sucmsg:""
	    }
	]);
	
</script>
<!-- 求购信息交互 end -->

<!-- 入仓信息交互 -->
<script>
	// 分页标识
	var pageNoWarehous = 1;
	
	// 入仓信息高级查询日期控件
	$('#warehousWdate1').click(function(){
		WdatePicker({
			maxDate:'#F{$dp.$D(\'warehousWdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#warehousWdate2').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'warehousWdate1\',{d:1});}',
			readOnly:true
		});
	});
	
	
	// 重置表单
	$('#addWarehousReset').click(function (){
		$('#addWarehousWarehouseType').text('请选择仓库类型');
		$("input[name='warehouseType']").val('');
	});
	
	
    // 查询入仓信息
    function queryWarehous(isReload){
    	if(isReload){
			pageNoWarehous = 1;    	
    	}else{
    		// 查询更多入仓信息
    		pageNoWarehous++;
    	}
    	
    	var params = "pageNo=" + pageNoWarehous + "&"+ $("#warehousListForm").serialize();
    	
    	// 设置状态
		var warehousStatus = $('#warehousStatus').val();
    	if(warehousStatus != null && warehousStatus != '' && warehousStatus != '全部'){
    		if(warehousStatus == '未处理'){
    			params += "&status=0";
    		}else if(warehousStatus == '有效'){
    			params += "&status=1";
    		}else if(warehousStatus == '无效'){
    			params += "&status=2";
    		}else if(warehousStatus == '用户撤销'){
    			params += "&status=3";
    		}
    	}
    	
    	// 设置来源
		var warehousResource = $('#warehousResource').val();
    	if(warehousResource != null && warehousResource != '' && warehousResource != '全部'){
    		params += "&applyResource=" + warehousResource;
    	}
    	
    	$.ajax({
			async : false,
			cache : false,
			type : "GET",
			data : params,
			dataType : "json",
			contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			url : "/WxBoss/warehousManager/getWarehous",
			success : function(data) {
				if(isReload){
					$('#warehous').empty();
					$('#warehous').append('<p id="noWarehous" style="display:none; text-align: center; padding:0px 0px 20px">没有找到符合条件的入仓信息！</p>');
					$('#warehous').append('<div id="moreWarehous" class="load" style="display:none" onclick="queryWarehous()"></div>');
				}
				
				var warehouses = data.warehousList;
				
				// 查询无数据，并且是重载页面，则显示无结果提示
				if((warehouses == null || warehouses.length == 0) && isReload){
					$('#noWarehous').show();
				}
				
				// 查询无数据或者数据少于10条，则不显示“加载更多”
				if(warehouses == null || warehouses.length < 10){
					$('#moreWarehous').hide();
				}else{
					$('#moreWarehous').show();
				}
				
		    	$.each(warehouses, function(i, warehous){
			    	// 设置状态
					var status = '';
			    	if(warehous.status != null){
			    		if(warehous.status == 0){
			    			status = '未处理';
			    		}else if(warehous.status == 1){
			    			status = '有效';
			    		}else if(warehous.status == 2){
			    			status = '无效';
			    		}else if(warehous.status == 3){
			    			status = '用户撤销';
			    		}
			    	}
		    	
		    		// 设置日期
					var wlrkDate = warehous.createTime;
					var wlrkDateYMD = '';
					if(wlrkDate != null){
						wlrkDate = new Date(wlrkDate);
						var wlrkDateMonth = wlrkDate.getMonth() + 1;
						if(wlrkDateMonth < 10){
							wlrkDateMonth = "0" + wlrkDateMonth;
						}
						var wlrkDateDate = wlrkDate.getDate();
						if(wlrkDateDate < 10){
							wlrkDateDate = "0" + wlrkDateDate;
						}
						wlrkDateYMD = wlrkDate.getFullYear() + '-' + wlrkDateMonth + '-' + wlrkDateDate;
					}
					
		    		var htmlStr = '';
					htmlStr += '<li><div onclick="window.location.href=\'/WxBoss/warehousManager/warehousDetail?warehousId=' + warehous.warehouseId + '\'">';
					htmlStr += '<span class="past1">';
					if(warehous.breedId == 0){
						htmlStr += '<strong>' + warehous.breedName + '</strong>';
					}else{
						htmlStr += '<strong>' + warehous.breed_Name + '</strong>';
					}
					htmlStr += '| <b>' + warehous.expectedService + '，' + warehous.warehouseType + '</b>';
					htmlStr += '</span><span class="charge past1"><strong class="red">' + status + '</strong></span>';
					htmlStr += '<div class="cont2">';
					htmlStr += '<p>';
					htmlStr += '<span>发布人：</span>' + warehous.applyName + '<br/>';
					htmlStr += '<span>联系电话：</span>' + warehous.applyMobile + '<img src="${RESOURCE_IMG_WX}/images/phone0727.png" style="width:45px; padding-left: 0.5em; " onclick="call(' + warehous.applyMobile + ');event.cancelBubble=true;"/><br/>';
					htmlStr += '<span>创建时间：</span>' + wlrkDateYMD + '<br/>';
					htmlStr += '<span>来源：</span>' + warehous.applyResource + '<br/>';
					htmlStr += '</p>';
		    		htmlStr += '</div>';
		    		htmlStr += '</div>';
		    		htmlStr += '</li>';
		    		
		    		$('#moreWarehous').before(htmlStr);
		    	});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('操作失败！');
			}
		});
		
    }
    
    // 验证新增入仓信息表单
    var addWarehousForm = $('#addWarehousForm').Validform({
    	btnSubmit:"#addWarehousSubmit",
	    tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){
				var objtip = $('#addWarehousMsg');
				if(o.type == 3){
					cssctl(objtip, o.type);
					objtip.text(msg).show();
				}else{
					objtip.text('').hide();
				}
			}
		},
	    ajaxPost:true,
	    showAllError:false,
	    datatype:{
 			"warehouseTypeStr":function(gets,obj,curform,regxp){
 				var warehouseTypeVal = $('#addWarehousWarehouseType').text();
 				if(warehouseTypeVal == '' || warehouseTypeVal == '请选择仓库类型'){
 					return false;
 				}
 				
 				$("input[name='warehouseType']").val(warehouseTypeVal);
 				return true;
 			}
 		},
	    ajaxurl:{
	        success:function(data,obj){
	            //data是返回的json数据;
	            //obj是当前正做实时验证表单元素的jquery对象;
	            //注意：5.3版中，实时验证的返回数据须是含有status值的json数据！
	            //跟callback里的ajax返回数据格式统一，建议不再返回字符串"y"或"n"。目前这两种格式的数据都兼容。
	            
	            var status = data.status;
	            if(status == undefined){
	            	$('#addWarehousMsg').text('网络繁忙，请稍后再试！');
	            }else{
	            	var id = $(obj).attr('id');
	            	if(id == 'addWarehousBreedName' && status == 'y'){
	            		$("input[name='breedId']").val(data.breedId);
	            	}
	            }
	        },
	        error:function(data,obj){
	            //data是{ status:**, statusText:**, readyState:**, responseText:** };
	            //obj是当前表单的jquery对象;
	        
	            var readyState = data.readyState;
	            if(readyState != 0){
	            	$('#addWarehousMsg').text('网络繁忙，请稍后再试！');
	            }
	        }
	    },
	    beforeSubmit:function(curform){
	    	$('#addWarehousSubmit').val('添加中').attr('disabled','disabled');
	    },
	    callback:function(data){
	    	$('#addWarehousSubmit').val('添加').removeAttr('disabled');
	    	var ok = data.ok;
	    	var msg = data.msg;
	    	if(ok){
	    		layer.open({
				    content: msg,
				    shadeClose: false,
				    style: 'text-align:center;',
				    btn: ['OK'],
				    yes: function(index){
					    layer.close(index);
					    closeLayer();
					    window.location.reload(true);
					}
				});
	    	}else{
	    		if(msg != undefined){
	    			layerMsg(msg);
	    		}else{
	    			layerMsg('网络繁忙，请稍后再试！');
	    		}
	    	}
	    }
	});
	
	addWarehousForm.addRule([
		{
	        ele:"#addWarehousApplyName",
	        datatype:"*1-50",
	        nullmsg:"请填写发布人！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWarehousApplyMobile",
	        datatype:"m",
	        nullmsg:"请填写联系电话！",
	        errormsg:"请填写正确的电话号码！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWarehousBreedName",
	        ajaxurl:"/Boss/wxBossSupply/checkBreedName",
	        datatype:"*1-50",
	        nullmsg:"请填写品种名称！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWarehousBreedAmount",
	        datatype:"*1-50",
	        nullmsg:"请填写入仓数量！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWarehousWarehouseType",
	        datatype:"warehouseTypeStr",
	        nullmsg:"请选择仓库类型！",
	        errormsg:"请选择仓库类型！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWarehousWarehouseArea",
	        datatype:"*1-50",
	        nullmsg:"请填写预计面积！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#addWarehousAreaPlace",
	        datatype:"*1-50",
	        nullmsg:"请选择货物所在地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#expectedService",
	        datatype:"*",
	        nullmsg:"请勾选一种期望服务！",
	        errormsg:"请勾选一种期望服务！",
	        sucmsg:""
	    }
	]);
	
	
   	// 区域级联
    $('#addWarehousForm select').change(function(){
    	var id = $(this).attr('id');
    	var code = $(this).find('option:selected').attr('rel');
        if(code == undefined || code ==''){
    		return;
    	}
    	switch(id)
		{
			case 'addWarehousAreaProvince':
			  	$.ajax({
					async : true,
					cache : true,
					type : "GET",
					data : {'code' : code},
					dataType : "json",
					url : "/Boss/wxBossSupply/getAreasByCode",
					success : function(data) {
						var ok = data.ok;
						if(ok){
							$('#addWarehousAreaCity option:first').nextAll().remove();
							var areas = data.obj;
							$.each(areas, function(index,area){
								$('#addWarehousAreaCity').append('<option value="' + area.name + '" rel="' + area.code + '">' + area.name + '</option>');
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						layerMsg('操作失败！');
					}
				});
				break;
				
			case 'addWarehousAreaCity':
			  	$.ajax({
					async : true,
					cache : true,
					type : "GET",
					data : {'code' : code},
					dataType : "json",
					url : "/Boss/wxBossSupply/getAreasByCode",
					success : function(data) {
						var ok = data.ok;
						if(ok){
							$('#addWarehousAreaPlace option:first').nextAll().remove();
							var areas = data.obj;
							$.each(areas, function(index,area){
								$('#addWarehousAreaPlace').append('<option value="' + area.name + '" rel="' + area.code + '">' + area.name + '</option>');
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
</script>
<!-- 入仓信息交互 end -->

<!-- 公共交互  -->
<script>
	// 去掉tab中最后一个的li的竖分割线
	$("#tab_id  li").last().find('span').css("border-right","0px");
	
	
	// 重置表单
	function resetForm(cont){
        document.getElementById(cont).reset();
    }
    resetForm('supplyListForm');
	resetForm('demandListForm');
	resetForm('warehousListForm');
	
	
	// tab切换
    $('.tabs li').on('click mouseover',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        $('#conts').children('ul').eq($(this).index()).show().siblings('ul').hide();
        
        var tab = $('.cur').attr('id');
        if(tab == 'tab_demand' && !($('#demand').children().length > 0)){
        	queryDemand(true);
        }else if(tab == 'tab_warehous' && !($('#warehous').children().length > 0)){
        	queryWarehous(true);
        }
        
    });


   //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
    
    
    //关闭层
    function closeLayer(){
        $('.h-demand').hide();
        $('.bghui').remove();
    };
    function close(els){
        els.on('click',function(){
            closeLayer();
        })
    }
    close($('.close'));
    close($('#supplySubmit'));
    close($('#demandSubmit'));
    close($('#warehousSubmit'));
   
    
	// 显示添加信息弹层
	$($('a[name=add]')).on('click', function(){
		var tab = $('.cur').attr('id');
        if(tab == 'tab_supply'){
        	$('#addGY').show();
        }else if(tab == 'tab_demand'){
        	$('#addQG').show();
        }else if(tab == 'tab_warehous'){
        	$('#addRC').show();
        }
		
		bgHiu();
	});


	// 显示高级查询弹层
	$($('a[name=check]')).on('click', function(){
		var tab = $('.cur').attr('id');
        if(tab == 'tab_supply'){
        	$('#supplyChecker').show();
        }else if(tab == 'tab_demand'){
        	$('#demandChecker').show();
        }else if(tab == 'tab_warehous'){
        	$('#warehousChecker').show();
        }
		
		bgHiu();
	});
	

    // 下拉选择框选择子项
    $('.unit a').on('click',function(){
        $(this).parent().prev().val($(this).text());
        $(this).parent().hide();
        return false;
    });


	// 关闭单位下拉选择框
	closeUnitLayer();
	function closeUnitLayer(){
		$('.unit').hide();
	}


    //显示单位下拉选择框
    function showUnitLayer(cont){
        cont.on('click',function(){
        	closeUnitLayer();
            $(this).next('.unit').show();
            
			var event = window.event || arguments.callee.caller.arguments[0]; // 获取event对象
        	event.cancelBubble = true;
        })
    }
    showUnitLayer($('input[datatype=ZhaTai],input[datatype=laiY]'));
    showUnitLayer($('#addWxSupplyPriceUnit'));
    showUnitLayer($('#addWxSupplyQtyUnit'));
    showUnitLayer($('#addBusiPurchaseApplyBreedPriceUnit'));
        
        
    // 添加入仓信息弹层单位下拉框
    $('#format').on('click',function(){
        $(this).children('.unit').show();
        var event = window.event || arguments.callee.caller.arguments[0];
    	event.cancelBubble = true;
    });
    $('#format .unit a').on('click',function(){
        $(this).parents('li').children('label').text($(this).text());
        $(this).parent().hide();
        return false;
    });

    
    // 只要键盘一抬起就验证编辑框中的文字长度，最大字符长度可以根据需要设定
    function checkLength(obj){
    	//最多字符数
        var maxChars = 500;
        var curr = maxChars - obj.value.length;
        if( curr > 0 ){
            document.getElementById("checklen").innerHTML = curr.toString();
        }else{
            document.getElementById("checklen").innerHTML = '0';
        }
    }
    
    
    //提示框
    function layerMsg(msg){
    	layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 2
		});
    };
    
    
	//小图拖动
	function slidePic(){
		$('li.see').each(function(index){
        	var id = $(this).parent('ul').attr('id');
        	if(id != undefined){
        		var maxNum = $(this).siblings().length+1;
		        Flipsnap('#'+id,{
		            distance:98,
		            maxPoint:maxNum
		        });
        	}
        });
	}
	
	
	//品种名称联想
    function autoComplete(els, cont){
	    els.autocomplete({
		    source: function(request, response){
		    	$.ajax({
					async : false,
					cache : false,
					type : "GET",
					data : { "query" : request.term},
					dataType : "json",
					contentType:"application/x-www-form-urlencoded:charset=UTF-8",
					url : "/Boss/wxBossSupply/getBreedNames",
					success : function(data) {
						var dataArray = data.suggestions;
						response( dataArray );
					}
				});
		    }
		});

    }
    autoComplete($('#addWxSupplyBreedName'), addWxSupplyForm);
	autoComplete($('#addBusiPurchaseApplyBreedName'), addBusiPurchaseApplyForm);
	autoComplete($('#addWarehousBreedName'), addWarehousForm);
    
    
    // 拨打电话
    function call(mobile){
    	window.location.href='tel:' + mobile;
    }
</script>
<!-- 公共交互 end -->

<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>