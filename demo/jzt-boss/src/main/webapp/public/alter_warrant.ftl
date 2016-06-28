<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改仓单</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/zoom/zoom.css" media="all" />
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
    	<div class="store-main">
    		<h1 class="title1">修改仓单</h1>
	        	<div class="store-left">
	        	 <form id="alterBusiWhlistForm" action="/busiWhlistManage/alterBusiWhlistFormAction" method="post" onsubmit="return checkForm()">
	                <ul class="store-add">
	                    <li>
	                    <span class="inp_width1">品种：</span>
		                    <#if categorys??>
		               			<#list categorys as category>
		                			<#if busiWhlist.categoryid == category.id>
		                				${category.categorysName!''}
		                			</#if>
		                		</#list>
		               		</#if>
		                    <#if breeds??>
		               			<#list breeds as breed>
		                			<#if busiWhlist.breedid == breed.breedId>
		                				${breed.breedName!''}
		                			</#if>
		                		</#list>
		               		</#if>
	                    </li>
	                    <li>
	                    <span class="inp_width1">货主：</span><input id="account" name="account" value="${busiWhlist.username!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />
	                    <input id="userId" name="userid" value="${busiWhlist.userid!''}" type="hidden" />
	                    </li>
	                    <li>
	                      <span class="inp_width1">仓单号：</span>${busiWhlist.wlid!''}<input name="wlid" value="${busiWhlist.wlid!''}" type="hidden" nullmsg="" datatype="*" />
	                    </li>
	                    <li> 
	                      <span class="inp_width1">入库日期：</span><input name="wlrkdate" value="${(busiWhlist.wlrkdate?string("yyyy-MM-dd HH:mm:ss"))!''}" class="text-store text-13 Wdate" type="text" nullmsg="" datatype="*" />    
	                    </li>
	                    <li>
	                    <span class="inp_width1">仓单状态：</span>
	                    <select name="wlstate" class="text-store text-11" nullmsg="" datatype="*" >
	                        <#if wlStateMap??>
	                   			<#list wlStateMap?keys as key>
	                    			<option value="${key!'' }" <#if busiWhlist.wlState == key>selected</#if>>${wlStateMap[key]!'' }</option>
	                    		</#list>
	                   		</#if>
	                    </select>
	                    </li>
	                    <li>
	                    <span class="inp_width1">产地：</span><input name="origin" value="${busiWhlist.origin!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />
	                    </li>
	                    <li>
	                    <span class="inp_width1">仓单数量/单位：</span>
	                    <input name="wltotal" value="${busiWhlist.wltotal!''}" maxlength="10" class="text-store text-8" type="text" nullmsg="" datatype="nozero" />/
		                   	<#if dictInfo??>
		               			<#list dictInfo as dictInfo>
		               				<#if busiWhlist.dictvalue == dictInfo.dictValue>
		                				${dictInfo.dictValue!''}
		                			</#if>
		                		</#list>
		               		</#if>
	                    </li>
	                    <li>
	                    <span class="inp_width1">合同编号：</span><input name="contractnum" value="${busiWhlist.contractnum!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />
	                    </li>
	                    <li>
	                    <span class="inp_width1">所在仓库：</span>
	                    <select name="warehouseid" class="text-store text-11" nullmsg="" datatype="*" >
	                        <option></option>
	                        <#if busiWareHouses??>
	                   			<#list busiWareHouses as busiWareHouse>
	                    			<option value="${busiWareHouse.wareHouseId!''}" <#if busiWhlist.warehouseid == busiWareHouse.wareHouseId>selected</#if>>${busiWareHouse.wareHouseName!''}</option>
	                    		</#list>
	                   		</#if>
	                    </select>
	                    <li>
	                    <span class="inp_width1">包装方式：</span><input name="packingway" value="${busiWhlist.packingway!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />
	                    </li>
	                    <li>
	                    <span class="inp_width1">区域：</span>
	                    <select id="area0" class="text-store text-11 area" nullmsg="" datatype="*" >
	                    	<#if areasMap??>
	                   			<#list areasMap?keys as key>
	                   				<#if key == 0>
	                   					<#list areasMap[key] as area0>
											<option value="${area0.code!'' }" <#if busiWhlist.areaid0 == area0.code>selected</#if>>${area0.name!'' }</option>
	                    				</#list>
	                    				<#break>
	                   				</#if>
	                    		</#list>
	                   		</#if>
	                   	</select>
	                   	</li>
	                   	<li>
	                   	<span class="inp_width1">&nbsp;&nbsp;&nbsp;</span>
	                   	<select id="area1" class="text-store text-11 area" nullmsg="" datatype="*" >
	                    	<#if areasMap??>
	                   			<#list areasMap?keys as key>
	                   				<#if key == 1>
	                   					<#list areasMap[key] as area1>
											<option value="${area1.code!'' }" <#if busiWhlist.areaid1 == area1.code>selected</#if>>${area1.name!'' }</option>
	                    				</#list>
	                    				<#break>
	                   				</#if>
	                    		</#list>
	                   		</#if>
	                   	</select>
	                   	</li>
	                   	<li>
	                   	<span class="inp_width1">&nbsp;&nbsp;&nbsp;</span>
	                   	<select id="area2" class="text-store text-11 area" nullmsg="" datatype="*" >
	                    	<#if areasMap??>
	                   			<#list areasMap?keys as key>
	                   				<#if key == 2>
	                   					<#list areasMap[key] as area2>
											<option value="${area2.code!'' }" <#if busiWhlist.areaid2 == area2.code>selected</#if>>${area2.name!'' }</option>
	                    				</#list>
	                    				<#break>
	                   				</#if>
	                    		</#list>
	                   		</#if>
	                   	</select>
	                   	</li>
	                   	<li>
	                   	<span class="inp_width1">&nbsp;&nbsp;&nbsp;</span>
	                   	<select id="area3" name="areaid" class="text-store text-11" nullmsg="" datatype="*" >
	                    	<option></option>
	                    	<#if areasMap??>
	                   			<#list areasMap?keys as key>
	                   				<#if key == 3>
	                   					<#list areasMap[key] as area3>
											<option value="${area3.code!'' }" <#if busiWhlist.areaid == area3.code>selected</#if>>${area3.name!'' }</option>
	                    				</#list>
	                    				<#break>
	                   				</#if>
	                    		</#list>
	                   		</#if>
	                   	</select>		
	                    </li>
	                </ul>
	                <h2 class="title2">质检信息</h2>
	                <ul class="store-add">
	                    <li>
	                    <span class="inp_width1">收检日期：</span><input name="acceptchecktime" value="${(busiWhlist.acceptchecktime?string("yyyy-MM-dd HH:mm:ss"))!''}" class="text-store text-13 Wdate" type="text" nullmsg="" datatype="*" />
	                    </li>
	                    <li>
	                    <span class="inp_width1">报告日期：</span><input name="reportdate" value="${(busiWhlist.reportdate?string("yyyy-MM-dd HH:mm:ss"))!''}" class="text-store text-13 Wdate" type="text" nullmsg="" datatype="*" />
	                    </li>
	                    <li style="width:100%; height:100px;">
	                    <span class="inp_width2">主要性状：</span><textarea id="mainparacter" name="mainparacter" class="text-store" nullmsg="" datatype="*" >${busiWhlist.mainparacter!''}</textarea>
	                    </li>
	                    <li>
	                    <span class="inp_width1">等级/规格：</span>
	                       <#if breedStandardLevels??>
	                   			<#list breedStandardLevels as breedStandardLevel>
	                   				<#if busiWhlist.grade == breedStandardLevel>
		                				${breedStandardLevel!''}
		                			</#if>
	                    		</#list>
	                   		</#if>
	                    </li>
	                    <li></li>
	                    <li style="width:100%;">
	                      <span class="inp_width2">具体描述：</span><input name="detailed" value="${busiWhlist.detailed!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" style="width:72%;" />
	                    </li>
	                    <li> 
	                      <span class="inp_width1">检品数量：</span><input name="numberofjc" value="${busiWhlist.numberofjc!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />    
	                    </li>
	                    <li> 
	                      <span class="inp_width1">水分：</span><input name="water" value="${busiWhlist.water!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />    
	                    </li>
	                    <li> 
	                      <span class="inp_width1">总灰分：</span><input name="ash" value="${busiWhlist.ash!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />    
	                    </li>
	                    <li> 
	                      <span class="inp_width1">二氧化硫残留量：</span><input name="sulfurdioxidein" value="${busiWhlist.sulfurdioxidein!''}" class="text-store text-13" type="text" nullmsg="" datatype="*" />    
	                    </li>
	                    <li style="width:100%; height:250px;">
	                    <span class="inp_width2">含量测定：</span><textarea id="description" name="contentcheck" nullmsg="" datatype="*" >${busiWhlist.contentcheck!''}</textarea>
	                    </li>
	                    <li>
	                        <span class="inp_width1">质检报告：</span>
	                        <a href="#" class="upfile-zj"><input type="file" id="upFile" name="upload" value="" /></a>
	                        <#if zjPic??>
	                			<#assign zjbg="${RESOURCE_IMG_UPLOAD}/${zjPic}">
	                   			<span class="blue"><a href="javascript:showImg('${zjbg!"#"}')">预览</a></span>
	                   		<#else>
	                   			<span class="blue"></span>
	                   		</#if>
	                    </li>
	                    <li class="clearfix">
	                        <span class="inp_width1">&nbsp;</span>
	                        <span id="upFileVal"></span>
	                    </li>
	                    <li>
	                    	<div id="fileBox"></div>
	                    </li>
		                <li class="btn_cen clearfix" style="width:100%;margin-bottom:25px;">
		                    <input type="button" class="btn-blue" value="返回" onclick="javascript:window.location.replace('/busiWhlistManage');" />
		                </li>
		            </ul>
					<div class="clear"></div>
				</form>
	       	</div>
            <div class="store-right">
				<ul class="form-2 mt15 gallery">
                	<li>
                    	<#if piclistMap["1"]??>
                    		<span class="storeimg">
                   				<img src="${RESOURCE_IMG_UPLOAD}/${piclistMap['1']}" />
                   			</span>
                   			<p>散货照片</p>
                   			<a href="${RESOURCE_IMG_UPLOAD}/${piclistMap['1']?substring(0,(piclistMap['1']?last_index_of('_')))+piclistMap['1']?substring((piclistMap['1']?last_index_of('.')))}" name="see"  class="">预览</a>
                   		<#else>
                   			<span class="storeimg">
                   				<img src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                   			</span>
                   			<p>散货照片</p>	
                   			<a href="#" name="see"  class=""></a>
                   		</#if>
                        <span class="bulk sc-null relative" style="margin-top:-240px">
                            <input type="file" id="file1" name="upload" class="btn-none1 file" title="上传新图片" value="" />
                        </span>
                	</li>
                    <li>
                        <#if piclistMap["2"]??>
                    		<span class="storeimg">
                   				<img src="${RESOURCE_IMG_UPLOAD}/${piclistMap['2']}" />
                   			</span>
                   			<p>细节照1</p>
                   			<a href="${RESOURCE_IMG_UPLOAD}/${piclistMap['2']?substring(0,(piclistMap['2']?last_index_of('_')))+piclistMap['2']?substring((piclistMap['2']?last_index_of('.')))}" name="see"  class="">预览</a>
                   		<#else>
                   			<span class="storeimg">
                   				<img src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                   			</span>
                   			<p>细节照1</p>	
                   			<a href="#" name="see"  class=""></a>
                   		</#if>
                        <span class="bulk sc-null relative" style="margin-top:-240px">
                            <input type="file" id="file2" name="upload" class="btn-none1 file" title="上传新图片" value=""  />
                        </span>
                	</li>
                    <li>
                       <#if piclistMap["3"]??>
                    		<span class="storeimg">
                   				<img src="${RESOURCE_IMG_UPLOAD}/${piclistMap['3']}" />
                   			</span>
                   			<p>细节照2</p>
                   			<a href="${RESOURCE_IMG_UPLOAD}/${piclistMap['3']?substring(0,(piclistMap['3']?last_index_of('_')))+piclistMap['3']?substring((piclistMap['3']?last_index_of('.')))}" name="see"  class="">预览</a>
                   		<#else>
                   			<span class="storeimg">
                   				<img src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                   			</span>
                   			<p>细节照2</p>	
                   			<a href="#" name="see"  class=""></a>
                   		</#if>
                        <span class="bulk sc-null relative" style="margin-top:-240px">
                            <input type="file" id="file3" name="upload" class="btn-none1 file" title="上传新图片" value=""  />
                        </span>
                	</li>
                    <li>
                       <#if piclistMap["4"]??>
                    		<span class="storeimg">
                   				<img src="${RESOURCE_IMG_UPLOAD}/${piclistMap['4']}" />
                   			</span>
                   			<p>细节照3</p>
                   			<a href="${RESOURCE_IMG_UPLOAD}/${piclistMap['4']?substring(0,(piclistMap['4']?last_index_of('_')))+piclistMap['4']?substring((piclistMap['4']?last_index_of('.')))}" name="see"  class="">预览</a>
                   		<#else>
                   			<span class="storeimg">
                   				<img src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                   			</span>
                   			<p>细节照3</p>
                   			<a href="#" name="see"  class=""></a>	
                   		</#if>
                        <span class="bulk sc-null relative" style="margin-top:-240px">
                            <input type="file" id="file4" name="upload" class="btn-none1 file" title="上传新图片" value=""  />
                        </span>
                	</li>
                    <li>
                       <#if piclistMap["5"]??>
                    		<span class="storeimg">
                   				<img src="${RESOURCE_IMG_UPLOAD}/${piclistMap['5']}" />
                   			</span>
                   			<p>包装照</p>
                   			<a href="${RESOURCE_IMG_UPLOAD}/${piclistMap['5']?substring(0,(piclistMap['5']?last_index_of('_')))+piclistMap['5']?substring((piclistMap['5']?last_index_of('.')))}" name="see"  class="">预览</a>
                   		<#else>
                   			<span class="storeimg">
                   				<img src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                   			</span>
                   			<p>包装照</p>
                   			<a href="#" name="see"  class=""></a>	
                   		</#if>
                        <span class="bulk sc-null relative" style="margin-top:-240px">
                            <input type="file" id="file5" name="upload" class="btn-none1 file" title="上传新图片" value=""  />
                        </span>
                	</li>
                    <li>
                       <#if piclistMap["6"]??>
                    		<span class="storeimg">
                   				<img src="${RESOURCE_IMG_UPLOAD}/${piclistMap['6']}" />
                   			</span>
                   			<p>堆垛照</p>
                   			<a href="${RESOURCE_IMG_UPLOAD}/${piclistMap['6']?substring(0,(piclistMap['6']?last_index_of('_')))+piclistMap['6']?substring((piclistMap['6']?last_index_of('.')))}" name="see"  class="">预览</a>
                   		<#else>
                   			<span class="storeimg">
                   				<img src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                   			</span>
                   			<p>堆垛照</p>
                   			<a href="#" name="see"  class=""></a>	
                   		</#if>
                        <span class="bulk sc-null relative" style="margin-top:-240px">
                            <input type="file" id="file6" name="upload" class="btn-none1 file" title="上传新图片" value=""  />
                        </span>
                	</li>
                	<li>
                   		<a href="${zjbg!'#'}" id="zjPic" name="see" class=""></a>
                	</li>
                </ul>             
			</div>
		</div>
	</div>
<!-- pageCenter over -->
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/kindeditor-4.1.10/kindeditor-min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/zoom/zoom.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	//日期控件
	$('.Wdate').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd HH:mm:ss',
			maxDate:'%y-%M-%d',
			readOnly:true
		});
	});
	//文本框控件
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true
		});
		editor = K.create('textarea[id="description"]', {
			width : '73%',
			height : '250px',
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'link'],
			afterBlur: function(){this.sync();checkKindEditor();}
		});
	}); 
	function tips(str){
		bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                     }
	               }
	            }]
	    });
	};
	//查看质检报告
    function showImg(imgUrl){
    	$('#zjPic').attr('href',imgUrl).click();
    };
	//上传质检报告
	$("#upFile").live('change',function(){
		tips('此功能已禁用！');
		return false;
		var pic = $(this).val();
		if(!/.(gif|jpg|jpeg|png|bmp)$/.test(pic.toLowerCase())){
			tips("请选择图片!");
			return false;
		}
		var upFileVal = $('#upFile').parent().parent().find('span.blue');
		upFileVal.html('<img width="30" height="30" src="${RESOURCE_IMG}/images/loading.gif">');
		$.ajaxFileUpload(
			{
				url:'/busiWhlistManage/uploadpic', 
				secureuri:false,
				fileElementId:'upFile',
				dataType: 'json',
				success: function (data, status)
				{
					if(data.status.code==0){
						var imgSrc = data.con.path+data.con.dateDir+"/"+data.con.filename;
						var orignImgSrc = imgSrc.substring(0,imgSrc.lastIndexOf('_'))+imgSrc.substring(imgSrc.lastIndexOf('.'));
						upFileVal.html('<a href="javascript:showImg(\''+orignImgSrc+'\')">预览</a>');
						var fileInputs = $('#fileBox input');
						$.each(fileInputs, function(index, fileInput){
						  var inpuName = $(fileInput).attr('name');
						  if(inpuName=='file7'){
						  	$(fileInput).remove();
						  }
						});  
						$('#fileBox').append('<input name="file7" type="hidden" title="质检报告" value="'+orignImgSrc+'" />');
						$('#upFile').parent().removeClass('Validform_error');
						$('#upFileVal').text(pic);
					}else{
						upFileVal.html('');
						tips("上传失败！");
						return false;
					}
				},
				error: function (data, status, e)
				{
					upFileVal.html('');
					tips('操作失败！');
				}
		  });
	});	
	//上传图片
	$(".file").live('change',function(){
		tips('此功能已禁用！');
		return false;
		var fileId = $(this).attr('id');
		var pic = $(this).val();
		if(!/.(gif|jpg|jpeg|png|bmp)$/.test(pic.toLowerCase())){
			tips("请选择图片!");
			return false;
		}
		var storeimg = $('#'+fileId).parent().parent().find(".storeimg");
		var storeimghtml = storeimg.html();
		storeimg.html('<img width="180" height="180" src="${RESOURCE_IMG}/images/loading.gif">');
		$.ajaxFileUpload(
			{
				url:'/busiWhlistManage/uploadpic', 
				secureuri:false,
				fileElementId:fileId,
				dataType: 'json',
				success: function (data, status)
				{
					if(data.status.code==0){
						var imgSrc = data.con.path+data.con.dateDir+"/"+data.con.filename;
						var orignImgSrc = imgSrc.substring(0,imgSrc.lastIndexOf('_'))+imgSrc.substring(imgSrc.lastIndexOf('.'));
						storeimg.html("<span style='margin-right:10px;'>"
						  +"<img width='180' height='180' src='"+imgSrc+"'></img></span>");
						storeimg.next('p').next('a').attr('href',orignImgSrc).text('预览');
						var fileInputs = $('#fileBox input');
						$.each(fileInputs, function(index, fileInput){
						  var inpuName = $(fileInput).attr('name');
						  if(inpuName==fileId){
						  	$(fileInput).remove();
						  }
						});  
						var imgTitle = storeimg.next('p').text();
						$('#fileBox').append('<input name='+fileId+' type="hidden" title='+imgTitle+' value='+orignImgSrc+' />');
						storeimg.removeClass('Validform_error');
					}else{
						storeimg.html(storeimghtml);
						tips("上传失败！");
						return false;
					}
				},
				error: function (data, status, e)
				{
					storeimg.html(storeimghtml);
					tips('操作失败！');
				}
		  });
	});
    //区域级联
    $(".area").change(function(){
		var areaId = $(this).attr("id");
		var areaCode = $(this).val();
    	$.ajax({
			async : false,
			cache : false,
			type : "GET",
			data : {"code":areaCode},
			dataType : "json",
			url : "/busiWhlistManage/getAreasByCode",
			success : function(data) {
				$.each(data,function(idx,obj){
					$("#area"+idx).empty();
					$.each(obj,function(i,o){
						$("#area"+idx).append("<option value="+o.code+">"+o.name+"</option>");
					});
				});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				tips('操作失败！');
			}
		});
	});
	//验证文本框控件
	function checkKindEditor(){
		var description = $('#description').val();
    	if(description==''){
    		$('.ke-container').css('border','1px solid #e01f20');
    	}else{
    		$('.ke-container').css('border','1px solid #cccccc');
    	}
	};
	//验证图片
	function checkFileUpload(){
		var isok = true;
		var file1 = $('#file1').parent().parent().find("a").text();
		var file2 = $('#file2').parent().parent().find("a").text();
		var file3 = $('#file3').parent().parent().find("a").text();
		var file4 = $('#file4').parent().parent().find("a").text();
		var file5 = $('#file5').parent().parent().find("a").text();
		var file6 = $('#file6').parent().parent().find("a").text();
		var file7 = $('#upFile').parent().parent().find('span.blue').html();
		if(file1==''){
			$('#file1').parent().parent().find('.storeimg').addClass('Validform_error');
			isok = false;
		}
		if(file2==''){
			$('#file2').parent().parent().find('.storeimg').addClass('Validform_error');
			isok = false;
		}
		if(file3==''){
			$('#file3').parent().parent().find('.storeimg').addClass('Validform_error');
			isok = false;
		}
		if(file4==''){
			$('#file4').parent().parent().find('.storeimg').addClass('Validform_error');
			isok = false;
		}
		if(file5==''){
			$('#file5').parent().parent().find('.storeimg').addClass('Validform_error');
			isok = false;
		}
		if(file6==''){
			$('#file6').parent().parent().find('.storeimg').addClass('Validform_error');
			isok = false;
		}
		if(file7==''){
			$('#upFile').parent().addClass('Validform_error');
			isok = false;
		}
		return isok;
	}
	//表单提交
	function checkForm(){
		var isok = alterBusiWhlistForm.check();
		checkKindEditor();
		checkFileUpload();
    	return false; 
	};
	//表单验证
	var alterBusiWhlistForm = $("#alterBusiWhlistForm").Validform({
	    tiptype:function(msg,o,cssctl){
		    //msg：'',
		    //o:{obj:*,type:*,curform:*},
		    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
		    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
		    //curform为当前form对象;
		    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
		},
	    ajaxPost:true,
	    showAllError:true,
	    datatype:{
			"nozero": function(gets,obj,curform,regxp){
				if(gets==""||gets==0){
					return false;
				}
			}
		},
	    beforeSubmit:function(data){
	    	var isok = checkFileUpload();
	    	if(isok){
	    		var fileUploads = $('.gallery .storeimg img');
		    	$.each(fileUploads,function(idx,obj){
					var fileUploadSrc = $(obj).attr('src');
					var fileUploadOk = fileUploadSrc.indexOf('loading');
					if(fileUploadOk!=-1){
						tips('请等待照片上传完成再操作！');
						isok = false;
						return isok;
					}
				});
				var upFileVal = $('#upFile').parent().parent().find('span.blue').text();
				if(upFileVal==''){
					tips('请等待质检报告上传完成再操作！');
					isok = false;
					return isok;
				}
				if(isok){
		    		isok = checkAccount();
		    		if(isok){
		    			$('#btn-submit').val('提交中...').attr('disabled','disabled');
		    		}
		    	}
	    	}
	    	return isok;
	    },
	    callback:function(data){
	    	$('#btn-submit').val('提交').removeAttr('disabled');
	    	if(data.status){
	    		var str = data.info;
	    		var redirect = data.redirect;
	    		bghui();
				Alert({
			            str: str,
			            buttons:[{
			                name:'确定',
			                classname:'btn-blue',
			                ev:{click:function(data){
			                	 disappear();
		                         $(".bghui").remove();
		                         window.location.replace(redirect);
		                     }
			               }
			            }]
			    });
	    	}else{
	    		tips(data.info);
	    	}
	    }
	});
	//验证货主
    function checkAccount(){
        var isok = false;
    	var account = $("#account").val();
    	if(account!=""){
    		$.ajax({
				async : false,
				cache : false,
				type : "GET",
				data : {"account":account},
				dataType : "json",
				url : "/busiWhlistManage/getUcUserByAccount",
				success : function(data) {
					var ok = data.ok;
					if(ok==true){
						$("#userId").val(data.obj);
					}else{
						tips('此货主不存在哦！');
					}
					isok = ok;
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					tips('操作失败！');
				}
			}); 
    	}
    	return isok;
    };
</script>
</body>
</html>