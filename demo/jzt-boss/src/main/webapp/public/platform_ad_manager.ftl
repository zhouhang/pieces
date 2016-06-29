<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet">
	<link href="${RESOURCE_CSS}/css/jzt-boss/admin.css" type="text/css" rel="stylesheet">
	<link href="${RESOURCE_CSS}/css/jzt-boss/store.css" type="text/css" rel="stylesheet">
	<link href="${RESOURCE_JS}/js/jquery-ui/jquery-ui.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" >
	
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
	
    <title>中药材电子商务管理系统</title>
</head>
<body>
<#import 'macro.ftl' as tools>	
<#include "home/top.ftl" />  
<div class="wapper">
	<#include "home/left.ftl" />
	<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">平台广告管理</h1>
            <form id="conditionForm" action="/platformad" method="get">
                <ul class="store-search">
                    <li>
                    <span>类型：</span>
                    <select class="text-store text text-2 register-text mr10" name="type">
						<option value="">--全部--</option>
						<#if flagMap??>
							<#list flagMap?keys as key>
								<option value="${key!'' }" <#if type == key>selected</#if>>${flagMap[key]!'' }</option>
							</#list>
						</#if>
					</select>
                    <span>
                    <span>描述：
                    </span><input name="alt" value="${alt}" class="text-store text-7" type="text" />
                    </span><input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
             <@shiro.hasPermission name="首页广告管理-添加广告">
					 <span class="btn-add mb10"><a href="javascript:addView();">添加广告</a> </span>
			 </@shiro.hasPermission>
               
               <table id="busiListingTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                    	<th width="50">编号</th>
                        <th width="70">类型</th>
                        <th width="150">描述</th>
                        <th width="50">排序</th>
                        <th >图片链接</th>
                        <th >更新时间</th>
                        <th width="80">最后更新人</th>
                        <th width="150">操作</th>
                    </tr>
	                    <#if (page.results?size>0)>
	                    	<#list page.results as ad>
	                    		<tr>
	                    			<td>${ad.adid}</td>
		                    		<td>
										${ad.chinesetype}
		                    		</td>
		                    		<td>${ad.alt}</td>
		                    		<td>${ad.sortno }</td>
		                    		<td>
		                    			<a href="#" name="see" id="seePicPath1" onclick="showPic1('${ad.picurl}')" >${ad.picurl}</a>
		                    		</td>
		                    		<td>${(ad.updatetime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
		                    		<td>${ad.updater}</td>
		                    		<td  class="opr-btn">
			                    		<@shiro.hasPermission name="首页广告管理-修改广告">
										 	<span class="operate-2" onclick="javascript:updateAd('${ad.adid}');" title="添加广告" > </span>
										</@shiro.hasPermission>
			                    		<@shiro.hasPermission name="首页广告管理-删除广告">
			                            	<span class="operate-4" onclick="javascript:deleteAd('${ad.adid}');"  title="删除广告"></span>
			                            </@shiro.hasPermission>
		                            </td>
	                    		</tr>
	                    	</#list>
	                    <#else>
	                    	<tr>
		                		<td colspan="7">暂无数据!</td>
		                	</tr>	
		                </#if>	
                </table>
            </div>
            <@tools.pages page=page form="conditionForm"/>
        </div>
    </div>
</div>    
<!-- pageCenter over -->
<!-- 图片预览  over -->
<div class="popup-box" id="picBox">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
</div>
<!-- 新增广告弹层 -->
<div class="" id="adBox" style="display:none;" title="添加广告">
    <form id="add" name="add" action="/platformad/saveAd" method="post">
    	<input type="hidden" id="adid" name="adid"/>
		<ul class="form-1 temp">
			<li><label>类型</label>
	        	<select id="type" name="type" class="text-store mr10" datatype="*" nullmsg="类型不能为空！">
	        		<#if flagMap??>
						<#list flagMap?keys as key>
							<option value="${key!'' }">${flagMap[key]!'' }</option>
						</#list>
					</#if>
	        	</select>
			</li>
			<li><label style="color:red;cursor:pointer;" title="1~3">排序</label>
				<input class="text text-1 register-text" id="sortno" datatype="n1-2" nullmsg="排序不能为空" errormsg="排序输入1-2位数字" name="sortno" type="text"/>
			</li>
			<li>
				<label>链接</label>
				<input class="text text-1 register-text" id="url" name="url" type="text" value="http://" />
			</li>
			<li>
				<label>图片描述(alt属性)</label>
				<input class="text text-1 register-text" id="alt" name="alt" type="text" datatype="*1-128"
							nullmsg="请输入图片描述！" errormsg="图片描述不能多于128个字符！"/>
			</li>
			<li>
				<label>图片</label>
				<p class="relative">
                   	<span class="storeimg">
                        <img id="alterPic" style="cursor:pointer; width:120px; height:120px;" width="120" src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                    </span>
                    
                    <span class="" style="position:absolute; top:0; left:0;">
						<input type="file" id="file" name="upload" class="" style="width:120px; cursor: pointer; height:120px; opacity:0; filter:alpha(opacity:0);"  title="上传新图片" value="" />
						<input type="hidden" id="picurl" nullmsg="图片不能为空"  name="picurl"/>
					</span>
				</p>
		
			</li>
			<li class="btn_cen">
                 <input type="submit" class="btn-blue" id="btn-submit" value="提交" />
            </li>
		</ul>
	</form>				
</div>
<script type="text/javascript">
	var addform;
	//删除广告
    function deleteAd(id){
    	bghui();
		Alert({
	            str: '确定删除吗？',
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         $.ajax({
							async : false,
							cache : false,
							type : "GET",
							data : {"id":id},
							dataType : "json",
							url : "/platformad/deleteAd",
							success : function(data) {
								var ok = data.status;
								var msg = data.info;
								if(ok==true){
									var busiListingTable = $("#busiListingTable tr");
									$.each(busiListingTable,function(idx,obj){
										var wxActivityTdId = $(obj).find('td:eq(0)').text();
										if(wxActivityTdId==id){
											$(obj).remove();
											return;
										}
									});
								}
								tips(msg);
								$("#conditionForm").submit();
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								tips('操作失败！');
							}
						});
                     }
	               }
	            }]
	    });
    };
    
	//修改广告
    function updateAd(id){
    	addform.resetForm();
    	$.ajax({
				async : false,
				cache : false,
				type : "GET",
				data : {"id":id},
				dataType : "json",
				url : "/platformad/getAdUpdate",
				success : function(data) {
					var obj = data;
					//修改弹窗
					$("#adBox").attr("title","修改广告");
					$("#add").attr("action","/platformad/updateAd");
					//填充数据
					$.each(obj,function(key,value){
						$("#"+key).val(value);
				 	});
				 	//$("#type").attr("disabled","disabled");
				 	$("#adid").attr("value",id);
				 	//显示图片
				 	$('#alterPic').attr('src',$('#picurl').val());
					//显示弹窗
					$("#adBox").dialog({
				      	autoOpen : true,
						modal : true,
						width : 600,
						resizable : false
				 	});
				 	$(".ui-dialog-title").attr("title","修改广告").text("修改广告");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					tips('操作失败！');
				}
			});
			$('.ui-button-icon-primary').attr('class','ui-button-icon-primary ui-icon-closethick');
    };
    
	var storeimg;
	//上传图片
	$('#file').fileupload({
		url: '/platformad/uploadpic',
		autoUpload: true,
		singleFileUploads: false,
        dataType: 'json',
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png|bmp)$/i,
        maxFileSize: 5000000,
        messages: {
            acceptFileTypes: '不支持的图片类型！',
            maxFileSize: '不支持大小超过5M的图片！'
        }
    }).on('fileuploadsubmit', function (e, data) {
       storeimg = $('#file').parent().parent().find(".storeimg img");
	   $(storeimg).attr('src','${RESOURCE_IMG}/images/loading.gif');
    }).on('fileuploadprocessalways', function (e, data) {
        if(data.files.error){
        	tips(data.files[0].error);
		}
    }).on('fileuploaddone', function (e, data) {
    	var result=data.result;
    	if(result.status.code==0){
			var imgSrc = result.con.path+result.con.dateDir+"/"+result.con.filename;
			$("#picurl").val(imgSrc);
			$(storeimg).attr('src', imgSrc);
		}else{
			tips("上传失败！");
		}
    }).on('fileuploadfail', function (e, data) {
        tips('操作失败！');
    });
    
	
	$('#Close').click (function(){
        $(this).parents('#picBox').hide();
        $('.bghui').remove();
    });
	
	function addView(){
		$('#add')[0].reset();
		$('#picurl').val('');
		$("#alterPic").attr("src","${RESOURCE_IMG}/images/jzt-boss/add.jpg");
		$(".ui-dialog-title").attr("title","添加广告");
    	$("#adBox").dialog({
	      	autoOpen : true,
			modal : true,
			width : 600,
			resizable : false
	 	});
	 	addform.resetForm();
	 	$(".ui-dialog-title").text("添加广告");
	 	$("#add").attr("action","/platformad/saveAd");
	 	$('.ui-button-icon-primary').attr('class','ui-button-icon-primary ui-icon-closethick');
	}
	
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
	function showPic1(path){
	  $('#picBox').show();
      var html = '<div class="bghui"></div>';
      var height = $(document).height();
      $('body').append(html);
      $('.bghui').css('height',height);
	  $('#showImg').attr('src',path);
	  $('#picBox').imageView({width:600, height:400});
      return false;
	}
	/* 初始化validform验证 */
	addform= $("#add").Validform({
		btnSubmit:"#btn-submit",
		tiptype:4,
		showAllError:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status=='yes'){
				$("#adBox").dialog("close");
				bghui();
		        Alert({
		            str:data.option+'成功！',
		            buttons:[{
		                name:'确定',
		                id:'1',
		                classname:'btn-style',
		                ev:{click:function(data){
		                        disappear();
		                        $(".bghui").remove();
		                        $("#conditionForm").submit();
		                    }
		                }
		            }]
		        });
			}
		}
	});
</script>
</body>
</html>