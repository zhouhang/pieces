<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" 	href="${RESOURCE_CSS}/js/jzt-boss/ztree/zTreeStyle/zTreeStyle.css" 	type="text/css">
	<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/jquerytab.css" />
	
	<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery.min.js"></script>
	<style type="text/css">
	.close1{ display: inline-block;
	    *display: inline;
	    zoom:1;
	    width: 30px;
	    height: 24px;
	    background: url("${RESOURCE_IMG}/images/jzt-boss/operate-icon.png") no-repeat;
	    margin: 0 3px;
	    cursor: pointer;
	    vertical-align: middle;
	    line-height: 9999px;
	    overflow: hidden;
	    position: absolute;
	    right: 0;
	    top:0;
	    background-position:-36px -64px;
	    width: 25px;
	    height: 22px;
	    margin: 0;}
	</style>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<div class="wapper">
<#include "home/left.ftl" /> 
<div class="main">
    <div class="close1" onclick="javascript:closeDiv();">关闭</div>
    <div class="box">
        <h1 class="title1">关联品种</h1>
       		<form id="queryBreedForm" action="getNotAssociateBreedBy" method="post" >
            <ul class="form-1 form-bk">
                <li>
                    <label>辅助查找：</label><input type="hidden" name="catId" value="${page.params.categoryId}"/><input type="hidden" name="classId" value="${classId}"/>
                    <p><input class="text text-1" type="text" name="name" value="品种名称/编码/别名/产地" /></p>
                    <p><input type="button" class="btn-blue" value="查询" onclick="javascript:getNotAssociateBreedBy();" />
                </li></ul>
              </form> 
              <form id="associateBreedForm" action="associateCategoryAndBreed" method="post">
              <input type="hidden" name="classId" value="${classId}"/>
              <input type="hidden" name="catId" value="${page.params.categoryId}"/> 
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="50"></th>
                        <th width="125">品种名称</th>
                        <th width="135">品种编码</th>
                        <th width="135">别名</th>
                        <th width="135">规格等级</th>
                        <th width="120">数量单位</th>
                        <th width="200">产地</th>
                    </tr>
                    <#if (page.results?size)==0>
                    	<tr>
                    		<td colspan="7">没有数据!</td>
                    	</tr>
                    </#if>
                  <#list page.results as breed>
                    <tr>
                        <td><input name="breedId" value="${breed.breedId}" type="checkbox"></td>
                        <td>${breed.breedName}</td>
                        <td>${breed.breedCode}</td>
                        <td class="opr-btn">
                         <span class="operate-1 operate-a">
                         <#if (breed.breedCname)?length gt 5>
                        	${breed.breedCname?substring(0,5)}...
                          <#else>
                        	${breed.breedCname}
                         </#if>
                         <div class="tips tipa" align="left"><span class="sj"></span>${breed.breedCname}</div></span>
                        </td>
                        <td class="opr-btn">
                         <span class="operate-1 operate-a">
                         <#if (breed.breedStandardLevel)?length gt 5>
                        	${breed.breedStandardLevel?substring(0,5)}...
                          <#else>
                        	${breed.breedStandardLevel}
                         </#if>
                         <div class="tips tipa" align="left"><span class="sj"></span>${breed.breedStandardLevel}</div></span>
                        </td>
                        <td>${breed.breedCountUnit}</td>
                        <td class="opr-btn">
                         <span class="operate-1 operate-a">
                         <#if (breed.breedPlace)?length gt 5>
                        	${(breed.breedPlace)?substring(0,5)}...
                          <#else>
                        	${breed.breedPlace}
                         </#if>
                         <div class="tips tipa" align="left"><span class="sj"></span>${breed.breedPlace}</div></span>
                        </td>
                    </tr>
                   </#list>
                </table>
				</form>
				<@tools.pages page=page form="queryBreedForm"/>
							
                <ul class="form-1 form-bk">
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="关联" onclick="javascript:associateBreed();" />
                    <input type="button" class="btn-blue close"  value="关闭" onclick="javascript:closeDiv();" /> </p>
                </li>
            </ul>
</div>
</div>
</div>
<div class="de-position" id="associateBreedMsg" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <p id="associateBreedMsgInfo"></p>
                </li>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="确定" id="associateBreedMsgBtn"/>
                     </p>
                     
                </li>
            </ul>
        </form>
    </div>
</div>


<script>
function getNotAssociateBreedBy(){
	$("#queryBreedForm").submit();
}
var isClose = false;
function associateBreed(){
	var boo = false;
	$("input[name='breedId']").each(function(){
		if($(this).prop("checked")){
			boo =true;
			return false;
		}
	});
	if(boo){
		
		$.ajax({
            cache: true,
            type: "POST",
            url:"associateCategoryAndBreed",
            data:$('#associateBreedForm').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                if(data){
                	$("#associateBreedMsgInfo").html("关联成功！");
					$("#associateBreedMsg").show();
                	isClose = true;
                	//$("#addQccount",window.parent.document).hide();
		          //  window.parent.location.reload();
                }
            }
        });
		
	}else{
		//alert("请选择关联品种！");
		$("#associateBreedMsgInfo").html("请选择关联品种！");
		$("#associateBreedMsg").show();
		return false;
	}
}
function closeDiv(){
	$("#addQccount",window.parent.document).hide();
	$('.bghui',window.parent.document).hide();
}
$("#associateBreedMsgBtn").click(function(){
	$("#associateBreedMsg").hide();
	if(isClose){
		isClose = false;
		$("#addQccount",window.parent.document).hide();
		 window.parent.location.reload();
	}
});

</script></body>
</body>
</html>