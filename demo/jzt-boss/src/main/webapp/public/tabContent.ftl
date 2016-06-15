<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/reset.css" />
<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
<link rel="stylesheet" 	href="${RESOURCE_CSS}/js/jzt-boss/ztree/zTreeStyle/zTreeStyle.css" 	type="text/css">
<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/jquerytab.css" />
<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/js/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />

<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery.min.js"></script>
<#import 'macro.ftl' as tools>
<input type="hidden" id="currentClassId" value="${classId}"/>
<!-- pageCenter start -->

    <div class="main">
        <div class="page-main">
            <div class="system_box1 fl">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
           
<!-- ztreejs over -->
<#if page??>
  <input type="hidden" id="currentCategoryId" value="${page.params.categoryId}" />
<div class="fr system_box2 relate">
            <form id="getBreedForm" action="getCategoryBy" method="post">
                <ul class="form-search">
                    <input type="hidden" name="classId" value="${classId}" />
                    <input type="hidden" name="categoryId" value="${page.params.categoryId}" />
                    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;品名名称/别名：</span><input class="text text-2 mr10" type="text" name="name" value="${page.params.name}" />
                        <span>品种编码：</span><input class="text text-2 mr10" type="text" name="code" value="${page.params.code}" />
                        <span>产地：</span><input class="text text-2 mr10" type="text" name="place" value="${page.params.place}" /></li>
                    <li><div align="center"><input type="button" class="btn-blue" value="查询" onclick="javascript:getBreedBy();" /></div></li>
                </ul>
            </form>
<div class="use-item1 mt25">
               <span class="btn-add mb10" id="aaa"><a href="#">关联品种</a> </span>
              
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th>品种名称</th>
                        <th>品种编码</th>
                        <th>别名</th>
                        <th>规格等级</th>
                        <th>数量单位</th>
                        <th>产地</th>
                        <th width="200">上级路径</th>
                        <th width="100">操作</th>
                    </tr>
                    <#list page.results as breed>
                    <tr>
                        <td>${breed.BREED_NAME}</td>
                        <td>${breed.BREED_CODE}</td>
                        <td class="opr-btn">
                          <span class="operate-1 operate-a">
                          	<#if breed.BREED_CNAME?length gt 5>
                        		${breed.BREED_CNAME?substring(0,5)}...
                        		<#else>
                        		${breed.BREED_CNAME}
                        	</#if>
                          	<div class="tips tipa" align="left"><span class="sj"></span>
                          		${breed.BREED_CNAME}</div>
                          	</span>
                        
                        </td>
                        <td class="opr-btn">
                          <span class="operate-1 operate-a">
                          	<#if breed.BREED_STANDARD_LEVEL?length gt 5>
                        		${breed.BREED_STANDARD_LEVEL?substring(0,5)}...
                        		<#else>
                        		${breed.BREED_STANDARD_LEVEL}
                        	</#if>
                          	<div class="tips tipa" align="left"><span class="sj"></span>
                          		${breed.BREED_STANDARD_LEVEL}</div>
                          	</span>
                        
                        </td>
                        
                        <td>${breed.BREED_COUNT_UNIT}</td>
                        <td class="opr-btn">
                          <span class="operate-1 operate-a">
                          	<#if breed.BREED_PLACE?length gt 5>
                        		${breed.BREED_PLACE?substring(0,5)}...
                        		<#else>
                        		${breed.BREED_PLACE}
                        	</#if>
                          	<div class="tips tipa" align="left"><span class="sj"></span>
                          		${breed.BREED_PLACE}</div>
                          	</span>
                        
                        </td>
                        <td class="opr-btn">
                          <span class="operate-1 operate-a">
                          	<#if breed.path?length gt 5>
                        		${breed.path?substring(0,5)}...
                        		<#else>
                        		${breed.path}
                        	</#if>
                          	<div class="tips tipa" align="left"><span class="sj"></span>
                          		${breed.path}</div>
                          	</span>
                        
                        </td>
                        <td class="opr-btn">
                           <a onclick="deleteBreed(${classId},${breed.CATEGORY_ID},${breed.BREED_CATEGORY_ID});">
                            <span class=" operate-1 operate-6"><div class="tips tipb" align="left"><span class="sj"></span>取消与该目录的关联</div></span>
                           </a>
                        </td>
                    </tr>
                 </#list>    
                    
                </table>
              <@tools.pages page=page form="getBreedForm"/>
        </div>
   </div>
 
</div>

<!-- 关联品种 -->

<div style="display: none;" id="addQccount" class="pop-position" >

<div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">关联品种</h1>
       		<form id="queryBreedForm" action="getNotAssociateBreedBy" method="post" >
            <ul class="form-1 form-bk">
                <li>
                	<input type="hidden" name="associatePageNo" id="associatePageNo" />
                    <label>辅助查找：</label><input type="hidden" name="catId" value="${page.params.categoryId}"/><input type="hidden" name="classId" value="${classId}"/>
                    <p><input class="text text-1" type="text" name="name" id="notAssociateName" value="" /></p>
                    <p><input type="button" class="btn-blue" value="查询" onclick="javascript:AjaxGetData('queryBreedForm','associatePageNo');" />
                </li></ul>
              </form> 
             <div id="searchData">
                
				</div>
				
							
                <ul class="form-1 form-bk">
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="关联" onclick="javascript:associateBreed();" />
                    <input type="button" class="btn-blue"  value="关闭" /> </p>
                </li>
            </ul>
</div>
</div>

<!-- 关联品种 over  -->  
<!-- 右菜单 -->
<div id="rMenuTree" class="rMenu">
    <ul>
        <li id="m_add_tree"><i class="icon-4"></i>新增子类目</li>
        <li id="m_mod_tree"><i class="icon-5"></i>修改类目</li>
        <li id="m_del_tree"><i class="icon-6"></i>删除类目</li>
    </ul>
</div>
<!-- 右菜单 over -->
<!-- 新增类目 -->
<div class="pop-position" id="addGccountTree" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">添加子类目</h1>
        <form id="addChildCatForm" action="addCategory" method="post">
            <ul class="form-1 form-bk">
                
                <li>
                  <label>类目名称：</label>
                  <input class="text text-1 register-text" maxlength="6" datatype="*1-6"  nullmsg="请输入类目名！" errormsg="名称在6个字以内！" id="addCatName" name="catName" type="text" />
                  <span id="addChildCatMsg"></span>
                                    
               </li>
                <input type="hidden" id="parentCatId" name="parentCatId"/>
              	 <input type="hidden" name="classId" value="${classId}"/>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="添加" id="addChildCategory" /> </p>
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 新增类目 over  -->
<!-- 修改类目  -->
<div class="pop-position" id="addHccountTree" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">修改类目</h1>
        <form id="alertCatForm" action="updateCategory" method="post">
            <ul class="form-1 form-bk">
                <li>
                    <label>类目名称：</label>
                    <input class="text text-1 register-text" maxlength="6" datatype="*1-6"  nullmsg="请输入类目名！" errormsg="名称在6个字以内！"  name="catName" id="alertCatName" />
                    <span id="alertCategoryMsg"></span>
                </li>
                <input type="hidden" id="alertCatId" name="catId"/>
                    <input type="hidden" name="classId" value="${classId}"/>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="修改" id="alertCategoryBtn" /> </p>
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 修改类目 over  -->
<!-- 删除提示框  -->
<div class="de-position" id="addZccountTree" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <p>您确定要删除此类目吗?</p>
                </li>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="确定" id="deleteCatBtn" />
                    <input type="button" class="btn-blue" value="取消" id="cancleCatBtn" /> </p>
                </li>
                <input type="hidden" id="deleteCatId"/>
                <input type="hidden" id="deleteClassId" value="${classId}" />
            </ul>
        </form>
    </div>
</div>
  <!-- 删除提示框 over  --> 
</#if>
<!-- 信息提示框  -->
<div class="de-position" id="showCategoryMsg" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <p id="categoryMsgInfo"></p>
                </li>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" class="btn-blue" value="确定" id="categoryMsgBtn"/>
                     </p>
                     <input type="hidden" id="msgClassId" />
                     <input type="hidden" id="msgcategoryId" />
                     
                </li>
            </ul>
        </form>
    </div>
</div>

  <!-- 信息提示框 over  --> 
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
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.position.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.core.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.widget.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.button.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.dialog.js"></script>

<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/directory-manage-dyn.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
	 $(function(){
     if($("#currentClassId").val()!=null && $("#currentClassId").val()!='')
       	getCategoryBy($("#currentClassId").val());
   	 	
   	 	AjaxGetData("queryBreedForm","associatePageNo");
       	
       	});
       	
       	function deleteBreed(classId,categoryId,breedCategoryId){
       		window.location.href="deleteBreedByCategory?classId="+classId+"&catId="+categoryId+"&breedCategoryId="+breedCategoryId;
       	}
       	function getBreedBy(){
       		$("#getBreedForm").submit();
       	}
       	
       	$("#addChildCatForm").Validform({
		    tiptype:3
		});
		$("#alertCatForm").Validform({
		    tiptype:3
		});
		
		//ajax分页 form 查询form的ID,action 请求地址,currentPageID form里当前页控件的ID
		function AjaxGetData(form,currentPageID) {
		var classId = $("#currentClassId").val();
            $.ajax({
                url: "getNotAssociateBreedBy",
                type: "post",
                data: $("#"+form).serialize(),
                dataType: "json",
                success: function (data) {
                	$("#notAssociateName").val(data.params.name);
                    var htmlStr = "<form id='associateBreedForm' action='associateCategoryAndBreed' method='post'><input type='hidden' name='catId' value='"+data.params.categoryId+"'/><input type='hidden' name='classId' value='"+classId+"'/>";
                    htmlStr += "<table class='table-1' width='100%' cellpadding='1' cellspacing='1'>"
                    htmlStr += "<tr>"
                    htmlStr += "<th width='50'></th><th width='125'>品种名称</th><th width='135'>品种编码</th><th width='135'>别名</th><th width='135'>规格等级</th><th width='120'>数量单位</th><th width='200'>产地</th>"
                    htmlStr += "</tr>";
                    if(data.totalPage==0){
						 $("#searchData").html("<tr><td colspan='7'>没有数据!</td></tr>");
					}
					else{
						for (var i = 0; i < data.results.length; i++) {
							htmlStr += "<tr>";
							htmlStr += "<td><input name='breedId' value='"+data.results[i].breedId+"' type='checkbox'></td>"
											  + "<td>" + data.results[i].breedName + "</td>"
											  + "<td>" + data.results[i].breedCode + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].breedCname.length>5)
											  htmlStr += data.results[i].breedCname.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].breedCname;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].breedCname+"</div></span></td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].breedStandardLevel.length>5)
											  htmlStr += data.results[i].breedStandardLevel.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].breedStandardLevel;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].breedStandardLevel+"</div></span></td>"
											  + "<td>" + data.results[i].breedCountUnit + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].breedPlace.length>5)
											  htmlStr += data.results[i].breedPlace.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].breedPlace;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].breedPlace+"</div></span></td>";
							htmlStr += "</tr>";
						}
						htmlStr += "</table></form>";
						var info = pageInfo(form,data,currentPageID);
						htmlStr +=info;
						

						$("#searchData").html(htmlStr);
					}
                },
                error: function (){
                           alert("error");
                }
            });
        }
        //分页
        function pageInfo(form,data,currentPageID){
        	var htmlStr = "";
        	htmlStr += "<div class='page-file fr'>";				
						
						htmlStr += "<a href='javascript:void' onclick=GoToFirstPage('"+form+"','"+currentPageID+"') id='aFirstPage' >首    页</a>   ";
						htmlStr += "<a href='javascript:void' onclick=GoToPrePage('"+form+"',"+data.pageNo+",'"+currentPageID+"') id='aPrePage' >上一页</a>   ";
						htmlStr += "<a href='javascript:void' onclick=GoToNextPage('"+form+"',"+data.pageNo+","+data.totalPage+",'"+currentPageID+"') id='aNextPage'>下一页</a>   ";
						htmlStr += "<a href='javascript:void' onclick=GoToEndPage('"+form+"',"+data.totalPage+",'"+currentPageID+"') id='aEndPage' >末    页</a>   ";
						htmlStr += "<input type='text' class='text fy'  /><input type='button'  value='跳转' onclick=GoToAppointPage(this,'"+form+"',"+data.pageNo+","+data.totalPage+",'"+currentPageID+"') /> ";
						htmlStr += "<span>当前：<b>" + data.pageNo + "/"+data.totalPage+"</b>页</span><span>本页<b>"+data.results.length+"</b>条记录</span><span>全部<b>"+data.totalRecord+"</b>条记录</span>";
						htmlStr += "</div>";
						
			return htmlStr;			
		
        }
        
        //首页
        function GoToFirstPage(form,currentPageID) {
            $("#"+currentPageID).val(1);
            AjaxGetData(form,currentPageID);
        }
        //前一页
        function GoToPrePage(form,pageNo,currentPageID) {
            pageNo -= 1;
            pageNo = pageNo > 0 ? pageNo : 1;
            $("#"+currentPageID).val(pageNo);
            AjaxGetData(form,currentPageID);
        }
        //后一页
        function GoToNextPage(form,pageNo,totalPage,currentPageID) {
        	$("#"+currentPageID).val(pageNo + 1);
            if (pageNo + 1 > parseInt(totalPage)) {
                $("#associatePageNo").val(pageNo);
            }
                AjaxGetData(form,currentPageID);
        }
        //尾页
        function GoToEndPage(form,totalPage,currentPageID) {
            $("#"+currentPageID).val(totalPage);
            AjaxGetData(form,currentPageID);
        }
        //跳转
        function GoToAppointPage(e,form,pageNo,totalPage,currentPageID) {
            var page = $(e).prev().val();
            if (isNaN(page)) {
                alert("请输入数字!");
            }
            else {
                var tempPageIndex = pageNo;
                pageNo = parseInt($(e).prev().val());
                if (pageNo < 1 || pageNo > parseInt(totalPage)) {
                    pageNo = tempPageIndex;
                    alert("请输入有效的页面范围!");
                }
                else {
                	$("#"+currentPageID).val(pageNo);
                    AjaxGetData(form,currentPageID);
                }
            }
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
                	$("#addQccount").hide();
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

$("#associateBreedMsgBtn").click(function(){
	$("#associateBreedMsg").hide();
	if(isClose){
		isClose = false;
		$("#down",window.parent.document).attr("src","getCategoryBy?classId="+$("#currentClassId").val()+"&categoryId="+$("#currentCategoryId").val()).ready();
		// window.parent.location.reload();
	}
});    
</script>


