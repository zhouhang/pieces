/**
 * Created by zhouli on 2014/12/3.
 */
//$(function(){
	
	var zNodes;
	
		/***************************************************树排序功能************************************************/
		
		//拖拽放下时候触发的逻辑，此处是拖到前面的逻辑
		function dropPrev(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		
		//拖拽放下时候触发的逻辑，此处是防止外面节点拖到里面的逻辑
		function dropInner(treeId, nodes, targetNode) {
			if (targetNode && targetNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					if (!targetNode && curDragNodes[i].dropRoot === false) {
						return false;
					} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
						return false;
					}	
				}
			}
			return true;
		}
		
		//拖拽放下时候触发的逻辑，此处是拖到后面的逻辑
		function dropNext(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}

		var log, className = "dark", curDragNodes, autoExpandNode,dragId;
		function beforeDrag(treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
			for (var i=0,l=treeNodes.length; i<l; i++) {
				dragId = treeNodes[i].pId;
				if (treeNodes[i].drag === false) {
					curDragNodes = null;
					return false;
				} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
					curDragNodes = null;
					return false;
				}
			}
			curDragNodes = treeNodes;
			return true;
		}
		function beforeDragOpen(treeId, treeNode) {
			autoExpandNode = treeNode;
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
			if(targetNode.pId == dragId){  
             var data = {id:treeNodes[0].id,targetId:targetNode.id};  
             var confirmVal = false;  
             $.ajax({  
                  async: false,  
                  type: "post",  
                  data:data,  
                  url:"updateCategoryOrder",  
                  success: function(json){  
                        if(json=="success" ){  
                             confirmVal = true;  
				 alert('操作成功!');  
										} else{  
				 alert('操作失败');  
										}  
								  },  
				error: function(){  
				   alert('网络有点不给力呀！');  
								  }  
							 });  
							return confirmVal;  
						} else{  
				            alert('只能进行同级排序！');  
					        return false;  
					  }  
	  
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
			showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
			return true;
			
		}
		function onDrag(event, treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
		}
		function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
			showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))
		}
		function onExpand(event, treeId, treeNode) {
			if (treeNode === autoExpandNode) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
			}
		}

		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

	/***************************************************右键菜单功能************************************************/

		function OnRightClickTree(event, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenuTree("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenuTree("node", event.clientX, event.clientY);
			}
		}
		//zTree右键菜单
		function showRMenuTree(type, x, y) {
			$("#rMenuTree").show();
			if (type=="root") {
				$("#m_add_tree").hide();
				$("#m_del_tree").hide();
				$("#m_mod_tree").hide();
				$("#rMenuTree").css({"top":y+"px", "left":x+"px", "visibility":"hidden"});
			} else {
				$("#m_add_tree").show();
				$("#m_del_tree").show();
				$("#m_mod_tree").show();
				$("#rMenuTree").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
				//$("#m_unCheck").show();
			}
			//y = y-100;
		}
		
		function hideRMenu() {
			if ($("#rMenuTree")) $("#rMenuTree").css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDownTree);
		}
		/*function onBodyMouseDownTree(event){
			if (!(event.target.id == "rMenuTree" || $(event.target).parents("#rMenuTree").length>0)) {
				$("#rMenuTree").css({"visibility" : "hidden"});
			}
		}*/
		
		//添加子类目
		$("#addChildCategory").click(function(){
			if($("#addCatName").val()==undefined ||$("#addCatName").val()==null ||$("#addCatName").val()=="")
				{
					$("#addChildCatMsg").html("<font color='#FF0000' class='Validform_wrong'>该输入类目名!</font>");
					return false;
				}
			if($("#addCatName").val().length>6){
				$("#addChildCatMsg").html("<font color='#FF0000' class='Validform_wrong'>该输入6个字以内!</font>");
				return false;
			}
			var regu = "^[\u4e00-\u9fa5]+$"; 
			var re = new RegExp(regu);
			if(!re.test($("#addCatName").val())){
				$("#addChildCatMsg").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
				return false;
			}
			$("#addChildCatMsg").html("");
			$.post("validateCategoryName",{
							"classId":$("#currentClassId").val(),
							"catName":$("#addCatName").val()
						},function(date){
							if(date){
								$("#addChildCatMsg").html("<font color='#FF0000' class='Validform_wrong'>该类目名被占用!</font>");
								//alert("该类目名被占用,请重新输入!");
								return false;
							}else{
								//将添加按钮置灰，防止重复提交
								//$("#addChildCategory").attr("disabled", true); 
								//将添加框关闭
								$('#addGccountTree').hide();
								$('.bghui').remove();
								//$("#addChildCatForm").submit();
								$("#addChildCategoryClassId").val($("#currentClassId").val());
								$.ajax({
									cache: true,
									type: "POST",
									url:"addTopCategory",
									data:$('#addChildCatForm').serialize(),// 你的formid
									async: false,
									error: function(request) {
										alert("Connection error");
									},
									success: function(data) {
										if(data){
											$("#categoryMsgInfo").html("添加成功!");
											$("#showCategoryMsg").show();
											
											$("#msgClassId").val($("#currentClassId").val());
										    $("#msgcategoryId").val($("#parentCatId").val());
											
										}else{
											$("#categoryMsgInfo").html("添加失败!");
											$("#showCategoryMsg").show();
											$("#msgClassId").val($("#currentClassId").val());
										    $("#msgcategoryId").val($("#parentCatId").val());
										}
									}
								});
							}
						});
		});
		
		//修改类目
		$("#alertCategoryBtn").click(function(){
			if($("#alertCatName").val()==undefined ||$("#alertCatName").val()==null ||$("#alertCatName").val()=="")
			{
				$("#alertCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>该输入类目名!</font>");
				return false;
			}
			if($("#alertCatName").val().length>6){
				$("#alertCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>该输入6个字以内!</font>");
				return false;
			}
			var regu = "^[\u4e00-\u9fa5]+$"; 
			var re = new RegExp(regu);
			if(!re.test($("#alertCatName").val())){
				$("#alertCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
				return false;
			}
			$("#alertCategoryMsg").html("");
			$.post("validateCategoryName",{
							"classId":$("#currentClassId").val(),
							"catName":$("#alertCatName").val(),
							"categoryId":$("#alertCatId").val()
						},function(date){
							if(date){
								$("#alertCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>该类目名被占用!</font>");
								//alert("该类目名被占用,请重新输入!");
								return false;
							}else{
								//$("#alertSystemForm").submit();
								//将添加框关闭
								$('#addHccountTree').hide();
								$('.bghui').remove();
								$("#alertCategoryClassId").val($("#currentClassId").val());
								$.ajax({
									cache: true,
									type: "POST",
									url:"updateCategory",
									data:$('#alertCatForm').serialize(),// 你的formid
									async: false,
									error: function(request) {
										alert("Connection error");
									},
									success: function(data) {
										if(data){
											$("#categoryMsgInfo").html("修改成功!");
											$("#showCategoryMsg").show();
											
											$("#msgClassId").val($("#currentClassId").val());
										    $("#msgcategoryId").val($("#alertCatId").val());
											
										}else{
											$("#categoryMsgInfo").html("修改失败!");
											$("#showCategoryMsg").show();
											$("#msgClassId").val($("#currentClassId").val());
										    $("#msgcategoryId").val($("#alertCatId").val());
										}
									}
								});
							}
						});
		});
		
		

//});
function getCategoryBy(classId){
	
	var setting = {
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: false,
					next: dropNext,
					//设置不能复制
					isCopy: false
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onRightClick: OnRightClickTree,
				onClick: zTreeOnClick,
				onDrag: onDrag,
				onDrop: onDrop,
				onExpand: onExpand
							
			}
		};
		
        $.ajax({
			async : false,
			cache : false,
			type : "POST",
			dataType : "json",
			url : "getCategoryTreeByClass?classId="+classId,// 类目树
			success : function(data) { // 请求成功后处理函数。
			  
				zNodes = data; // 把后台封装好的简单Json格式赋给zNodes
			if(data !="" && data.length>0){
				//update 2015.01.11
				var htmlStr="";
				htmlStr += "<form id='getBreedForm' action='getCategoryBy' method='post'><ul class='form-search'>"
                    htmlStr += "<input type='hidden' name='pageNo' id='associateBreedPageNo' /><input type='hidden' name='classId' id='associateClassId' value='"+classId+"' /><input type='hidden' name='categoryId' id='associateCategoryId'  />"
                    htmlStr += "<li><span>&nbsp;&nbsp;&nbsp;&nbsp;品名名称/别名：</span><input class='text text-2 mr10' type='text' name='name' id='associateBreedName' />"
                    htmlStr += "<span>品种编码：</span><input class='text text-2 mr10' type='text' name='code' id='associateBreedCode' />"
                    htmlStr += "<span>产地：</span><input class='text text-2 mr10' type='text' name='place' id='associateBreedPlace' /></li>"
                    htmlStr += "<li><div align='center'><input type='button' class='btn-blue' value='查询' onclick=javascript:AjaxGetAssociateBreed('getBreedForm','associateBreedPageNo'); /></div></li></ul></form>";
					$("#associateBreedFormDiv").html(htmlStr);
					//点击分类 未点击类目时categoryId为""
					var categoryId = $("#currentCategoryId").val();
					if(categoryId != null &&categoryId !="")
					   $("#associateCategoryId").val(categoryId);
					$("#associateClassId").val(classId);
					AjaxGetAssociateBreed("getBreedForm","associateBreedPageNo");
					
					if(categoryId != null &&categoryId !=""){
						$("#notAssociateClass").val(classId);
	                    $("#notAssociateCategory").val(categoryId);
	                  //ajax获取未关联的品种
	                    AjaxGetData("queryBreedForm","associatePageNo");
					}
					else{
					//获取该分类下的第一个类目
					$.ajax({
						async : false,
						cache : false,
						type : "POST",
						url : "getFirstCategoryByClass?classId="+classId,// 类目树
						success : function(data) {
							if(data != null){
								$("#currentCategoryId").val(data.id);//设置页面上的隐藏域-当前类目ID
								$("#notAssociateClass").val(classId);
			                    $("#notAssociateCategory").val(data.id);
			                    //ajax获取未关联的品种
			                    AjaxGetData("queryBreedForm","associatePageNo");
							}
						}
					});
					}
				
			  }else{
				  $("#associateBreedFormDiv").html("");
				  $("#associateBreedData").html("");
				  
			  }
			}
		}); 
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
	}
	
	function zTreeOnClick(event, treeId, treeNode){
		var classId= $("#currentClassId").val();
		$("#currentCategoryId").val(treeNode.id);
		$("#associateCategoryId").val(treeNode.id);
		$("#associateClassId").val(classId);
		//点击类目时需要保存的当前页数置为1，否则会保留最后一次操作的
		$("#associateBreedPageNo").val(1);
		$("#associatePageNo").val(1);
		//window.location.replace("getCategoryBy?classId="+classId+"&categoryId="+treeNode.id);
		AjaxGetAssociateBreed("getBreedForm","associateBreedPageNo");
		$("#notAssociateClass").val(classId);
        $("#notAssociateCategory").val(treeNode.id);
        //ajax获取未关联的品种
        AjaxGetData("queryBreedForm","associatePageNo");
		
	}
	
	
    $("#cancleCatBtn").click(function(){
        	$("#addZccountTree").hide();
			$('.bghui').remove();
       });
	   
	 $("#deleteCatBtn").click(function(){
        	var catId = $("#deleteCatId").val();
			//var classId = $("#deleteClassId").val();
        	var classId = $("#currentClassId").val();
        	$.post("validateCatChildrenOrhasBreed",{
        		"catId":catId,
				"classId":classId
      		},function(date){
        		if(date){
					$("#addZccountTree").hide();
					$('.bghui').remove();
					
        			//window.location.replace("deleteCategory?catId="+catId+"&classId="+$("#currentClassId").val());
					$.post("deleteCategory",{
						"classId":$("#currentClassId").val(),
						"catId":catId
						},function(date){
							if(date){
								$("#categoryMsgInfo").html("删除成功!");
								$("#showCategoryMsg").show();
											
								$("#msgClassId").val($("#currentClassId").val());
								$("#msgcategoryId").val("");
								
							}else{
								$("#categoryMsgInfo").html("删除失败!");
								$("#showCategoryMsg").show();
											
								$("#msgClassId").val($("#currentClassId").val());
								
								
							}
						});
                	
        		}else{
        			$("#addZccountTree").hide();
					$('.bghui').remove();
					$("#categoryMsgInfo").html("该类目下有子类目或品种，不能进行删除!");
					$("#showCategoryMsg").show();
        			isHandleCat=false;
        		}
        	});
        });
		
		
	
/************************************Tab分类*************************************/	

		var $tab_title_input = $( "#tab_title"),
			$tab_content_input = $( "#tab_content" );
		var tab_counter = 2;
		
		// tabs init with a custom tab template and an "add" callback filling in the content
		var $tabs = $( "#tabs").tabs({
			tabTemplate: "<li class='onRC'><a href='#{href}' onclick='toClassCategory(#{href});' style='text-align:center; width:112px;' id='#{href}'>#{label}</a></li>",
			add: function( event, ui ) {
				var tab_content = $tab_content_input.val() ;
				$( ui.panel ).append( "<p>" + tab_content + "</p>" );
				
			}
			
		});
		//tabTemplate.before('#button');

		// modal dialog init: custom buttons and a "close" callback reseting the form inside
		var $dialog = $( "#dialog" ).dialog({
			autoOpen: false,
			modal: true,
			buttons: {
				增加: function() {
				var className =$tab_title_input.val();
			    if(className==undefined || className==null||className==""){
					$("#classNameError").html("<font color='#FF0000' class='Validform_wrong'>请输入分类名！</font>");
					 return false;
					}
			    if(className.length>6){
			    	$("#classNameError").html("<font color='#FF0000' class='Validform_wrong'>请输入6个字以内！</font>");
					return false;
			    }
				var regu = "^[\u4e00-\u9fa5]+$"; 
				var re = new RegExp(regu); 
				if(!re.test(className)){
					$("#classNameError").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
					 return false;
				}
				$("#classNameError").html("");
			    var strage = $tab_content_input.val();
				if(strage!=undefined && strage!=null && strage!="" &&strage.length>40){
					 $("#classStrageError").html("<font color='#FF0000' class='Validform_wrong'>请输40个字以内！</font>");
					  return false;
					}
				if(strage!=undefined && strage!=null && strage!="" &&!re.test(strage)){
					 $("#classStrageError").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
					  return false;
				}
				$("#classStrageError").html("");
			//add by fanyuna
			$.post("validateClassbyName",{
        		"className":$tab_title_input.val()
				
      		},function(date){
        		if(date){
        			$.ajax({
						 type: "POST",
						 url: "addClassInfo",
						 data:   "className="+$tab_title_input.val()+"&operateStrage="+$tab_content_input.val(),
						 success: function(id){
							 
							 if(id!=null && id!="null" && id!="0"){
								
							     addTab(id);
								 $("#classMsgInfo").html("添加成功！");
								$("#classMsg").show();
								$( "#dialog" ).dialog( "close" );
								
								
							   }
							   else{
								   $("#classMsgInfo").html("添加失败！");
								$("#classMsg").show();
								$( "#dialog" ).dialog( "close" );
								   return false;
							   }
							 } 
						}); 
                	
        		}else{
					$("#classNameError").html("<font color='#FF0000'>此分类名称已存在！</font>");
					return false;
        			
        		}
        	});
				},
				取消: function() {
					$( this ).dialog( "close" );
				}
			},
			open: function() {
				$tab_title_input.focus();
			},
			close: function() {
				//$form[ 0 ].reset();
				$( this ).dialog( "close" );
			}
		});

		
		// actual addTab function: adds new tab using the title input from the form above
		function addTab(id) {
			var tab_title = $tab_title_input.val() || "Tab " + tab_counter;
			
			
			$tabs.tabs( "add", id, tab_title );

			tab_counter++;
		}

		// addTab button: just opens the dialog
		$( "#add_tab" )
			
			.click(function() {
				$( "#tab_title").val("");
				$( "#tab_content" ).val("");
				$dialog.dialog( "open" );
			});
			
		//绑定右键菜单到TAB页签
		$("#tabs").on('contextmenu', '.ui-state-default', function(e) {
			//function code here.
			e.preventDefault();
			//判断选中状态则显示菜单否则不显示
			var tabRoot = $("#tabs").tabs();
			var $tabIndex = tabRoot.tabs('option', 'selected');
    		var selectedHref = $("#tabs ul>li a").eq($tabIndex).attr('href');
			var currentHref = $(this).find('a').attr('href');
			if(selectedHref==currentHref){
				showRMenuTab(e.clientX,e.clientY);
			}
		});
		
		
		function showRMenuTab(x, y) {
			$("#rMenu").show();
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		}	
		
		/************************************关闭框*************************************/	
		 var Close = $('.close');
        var maskDiv = '<div class="bghui"></div>';
        Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
            })
        });
		
		/************************************body鼠标事件*************************************/	
		//绑定body鼠标按下事件
		$("body").bind("mousedown", function(event){
			if(event.target.id == "m_add" || event.target.id == "m_del" || event.target.id == "m_mod"){
				onBodyMouseDownTab(event);
			}else if(event.target.id == "m_add_tree" || event.target.id == "m_del_tree" || event.target.id == "m_mod_tree"){
				onBodyMouseDownTree(event);
			}
			//隐藏所有右键菜单
			$("#rMenuTree").css({"visibility" : "hidden"});
			$("#rMenu").css({"visibility" : "hidden"});
		});

		//树鼠标事件
		function onBodyMouseDownTree(event){
			//Tree
			if (event.target.id == "m_add_tree") {//新增子类目
				var id = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].id;
				$("#parentCatId").val(id);
				
				$("#addCatName").val("");
				$('#addGccountTree').show();
				$('body').append(maskDiv);
				$('.bghui').css('height',$(document).height()-20);
			}else if(event.target.id == "m_del_tree"){//删除目录
				var id = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].id;
				$("#deleteCatId").val(id);
				
				$('#addZccountTree').show();
				$('body').append(maskDiv);
				$('.bghui').css('height',$(document).height()-20);
			}else if(event.target.id == "m_mod_tree"){//修改目录
				var id = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].id;
				var name = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].name;
				$("#alertCatId").val(id);
				$("#alertCatName").val(name);
				
				$('#addHccountTree').show();
				$('body').append(maskDiv);
				$('.bghui').css('height',$(document).height()-20);
			}
			$("#rMenuTree").css({"visibility" : "hidden"});
		}
		//Tab分类鼠标事件
		function onBodyMouseDownTab(event){
			if (event.target.id == "m_add") {//新增目录
				//设置添加类目时的分类ID的值
				var tabRoot = $("#tabs").tabs();
			    var $tabIndex = tabRoot.tabs('option', 'selected');
    		    var selectedId = $("#tabs ul>li a").eq($tabIndex).attr('id');
				$('#addClassId').val(selectedId);
				
				$('#addGccount').show();
				$('body').append(maskDiv);
				$('.bghui').css('height',$(document).height()-20);
			}else if(event.target.id == "m_del"){//删除目录
				//设置添加类目时的分类ID的值
				var tabRoot = $("#tabs").tabs();
			    var $tabIndex = tabRoot.tabs('option', 'selected');
    		    var selectedId = $("#tabs ul>li a").eq($tabIndex).attr('id');
				$('#delClassId').val(selectedId);
			
				$('#addZccount').show();
				$('body').append(maskDiv);
				$('.bghui').css('height',$(document).height()-20);
			}else if(event.target.id == "m_mod"){//修改目录
				//设置添加类目时的分类ID的值
				var tabRoot = $("#tabs").tabs();
			    var $tabIndex = tabRoot.tabs('option', 'selected');
				var selectedId = $("#tabs ul>li a").eq($tabIndex).attr('id');
    		    var selectedName = $("#tabs ul>li a").eq($tabIndex).html();
				$('#defaultUpdateClassName').val(selectedName);
				$('#updateClassName').val(selectedName);
				$('#updateClassId').val(selectedId);
				var data = {classId:selectedId};  
				$.ajax({  
                  async: false,  
                  type: "post",  
                  data:data,  
                  url:"getClassStrageBy",  
                  success: function(data){  
                      $('#updateClassStrage').val(data.operateStrate); 
								  },  
				error: function(){  
				   alert('网络有点不给力呀！');  
								  }  
							 });  
				
				
				
				$('#addHccount').show();
				$('body').append(maskDiv);
				$('.bghui').css('height',$(document).height()-20);
			}
			$("#rMenu").css({"visibility" : "hidden"});
			//$("#rMenuTree").css({"visibility" : "hidden"});
		}
		
		//删除分类
		$("#delClassBtn").click(function(){
        	
			var classId = $("#delClassId").val();
        	$.post("validateClasshasCat",{
        		
				"classId":classId
      		},function(date){
        		if(date){
					$("#addZccount").hide();
					$('.bghui').remove();
        			//window.location.replace("deleteClass?classId="+classId);
					$.post("deleteClass",{
						"classId":classId
						},function(date){
							if(date){
								$("#classMsgInfo").html("删除成功!");
								$("#classMsg").show();
								isDelete=true; //用于跳转到后台getClass路径
								
								
							}else{
								$("#classMsgInfo").html("删除失败!");
								$("#classMsg").show();
								return false;
								
							}
						});
					
                	
        		}else{
					//alert("该分类下存在类目，不能删除!");
					$("#addZccount").hide();
					$('.bghui').remove();
					
					$("#classMsgInfo").html("该分类下存在类目，不能删除!");
					$("#classMsg").show();
        			
        		}
        	});
        });
		
		//修改分类
		$("#updateClassBtn").click(function(){
			var defaultClassName = $("#defaultUpdateClassName").val();
        	var className= $("#updateClassName").val();
			var classId = $("#updateClassId").val();
			if(className==undefined || className==null||className==""){
				$("#updateClassNameMsg").html("<font color='#FF0000' class='Validform_wrong'>请输入分类名！</font>");
				 return false;
			}
			if(className.length>6){
				$("#updateClassNameMsg").html("<font color='#FF0000' class='Validform_wrong'>请输入6个字以内！</font>");
				 return false;
			}
			var regu = "^[\u4e00-\u9fa5]+$"; 
			var re = new RegExp(regu);
			if(!re.test(className)){
				$("#updateClassNameMsg").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
				 return false;
			}
			$("#updateClassNameMsg").html("");
			if($("#updateClassStrage").val()!=undefined && $("#updateClassStrage").val()!=null && $("#updateClassStrage").val()!="" &&$("#updateClassStrage").val().length>40){
				$("#updateoperateStrageMsg").html("<font color='#FF0000' class='Validform_wrong'>请输40个字以内！</font>");
				 return false;
			}
			if($("#updateClassStrage").val()!=undefined && $("#updateClassStrage").val()!=null && $("#updateClassStrage").val()!="" &&!re.test($("#updateClassStrage").val())){
				$("#updateoperateStrageMsg").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
				 return false;
			}
			$("#updateoperateStrageMsg").html("");
			if(defaultClassName == className){
				  $("#addHccount").hide();
				  $('.bghui').remove();
			}
        	$.post("validateClassbyName",{
        		"className":className,
				"classId":classId
      		},function(date){
        		if(date){
        			$.post("updateClassName",{
						"className":className,
						"classId":classId,
						"classStrage":$("#updateClassStrage").val()
					},function(date){
						if(date){
							var tabRoot = $("#tabs").tabs();
							var $tabIndex = tabRoot.tabs('option', 'selected');
							 $("#tabs ul>li a").eq($tabIndex).html(className);
							 $("#cont").html($("#updateClassStrage").val()); 
							 $("#addHccount").hide();
							$('.bghui').remove();
							
							 $("#classMsgInfo").html("修改成功！");
							 $("#classMsg").show();
						}else{
							$("#classMsgInfo").html("sorry,修改不成功！");
							 $("#classMsg").show();							
							return false;
							
						}
					});
                	
        		}else{
					$("#updateClassNameMsg").html("<font color='#FF0000' class='Validform_wrong'>此分类名称已存在！</font>");
					return false;
        			
        		}
        	});
        });
		
		//分类下新增类目
		$("#addTopCategory").click(function(){
        	if($("#addCategoryName").val()==undefined || $("#addCategoryName").val()==null || $("#addCategoryName").val()==""){
        		$("#topCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>请输入类目名！</font>");
				 return false;
        	}
        	if($("#addCategoryName").val().length>6){
        		$("#topCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>请输入6个字以内！</font>");
				 return false;
        	}
        	var regu = "^[\u4e00-\u9fa5]+$"; 
			var re = new RegExp(regu);	
			if(!re.test($("#addCategoryName").val())){
				$("#topCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>只能输入汉字！</font>");
				 return false;
			}
			$("#topCategoryMsg").html("");
			$.post("validateCategoryName",{
			"classId":$("#addClassId").val(),
			"catName":$("#addCategoryName").val()
			},function(date){
			if(date){
			//alert("该类目名被占用,请重新输入!");
				$("#topCategoryMsg").html("<font color='#FF0000' class='Validform_wrong'>此分类名称已存在！</font>");
				return false;
			}else{
				$.ajax({
					cache: true,
					type: "POST",
					url:"addTopCategory",
					data:$('#addTopCategoryForm').serialize(),// 你的formid
					async: false,
					error: function(request) {
						alert("Connection error");
					},
					success: function(data) {
						if(data){
							//alert("添加成功！");
							$("#classMsgInfo").html("添加成功！");
							 $("#classMsg").show();	
							 
							$("#addCategoryName").val("");
							$("#addGccount").hide();
							$('.bghui').remove();
							//$("#down").attr("src","getCategoryBy?classId="+$("#addClassId").val()).ready();
							getCategoryBy($("#addClassId").val());
						}else{
							alert("添加失败！");
							return false;
						}
					}
				});
				//$("#addTopCategoryForm").submit();
			}
			});
        });
		
		//关联品种
        //tipa
		var Height = $('.tips').height()+18;
		$('.opr-btn .tips').css('top',-Height);
        $('.operate-1').hover(
                function(){
                    $(this).children('.tips').show();
                },
                function(){
                    $(this).children('.tips').hide();
                }
        );
        $("#associateBreedData").delegate('.operate-1', 'mouseenter', function(){
	            $(this).children('.tips').show();
	            
	        });
        $("#associateBreedData").delegate('.operate-1', 'mouseleave', function(){
            $(this).children('.tips').hide();
            
        });
        $("#searchData").delegate('.operate-1', 'mouseenter', function(){
            $(this).children('.tips').show();
            
        });
	    $("#searchData").delegate('.operate-1', 'mouseleave', function(){
	        $(this).children('.tips').hide();
	        
	    });
        
		      //tipa
		var Height = $('.tipb').height()+17;
		$('.opr-btn .tipb').css('top',-Height);
        $('.operate-6').hover(
                function(){
                    $(this).children('.tipb').show();
                },
                function(){
                    $(this).children('.tipb').hide();
                }
        );
       $("#associateBreedData").delegate('.operate-6', 'mouseenter', function(){
    	   $(this).children('.tipb').show();
            
        });
    $("#associateBreedData").delegate('.operate-6', 'mouseleave', function(){
    	 $(this).children('.tipb').hide();
        
    });
		
		/************************************关联品种弹*************************************/	

        $("#associateBreedData").delegate('#aaa', 'click', function() { 
        	$('#addQccount').show();
            $('body').append(maskDiv);
			$('.bghui').css('height',$(document).height()-20);
        });
        $('#aaa').on('click',function(){
            $('#addQccount').show();
            $('body').append(maskDiv);
			$('.bghui').css('height',$(document).height()-20);
        });
		
		//iframe高度自适应
		function iFrameHeight(id) {

        var ifm= document.getElementById(id);

        var subWeb = document.frames ? document.frames[id].document :ifm.contentDocument;

            if(ifm != null && subWeb != null) {

            ifm.height = subWeb.body.scrollHeight;

            }

    }
	
	var isDelete = false; //用于删除分类时是否跳转到后台getClass路径
	
	
	$("#classMsgBtn").click(function(){
		$("#classMsg").hide();
		if(isDelete){
			isDelete = false;
			window.location.replace("getClass");
			
		}

	});
	
	
	var isHandleCat = true;  //用于操作类目后是否跳转getCategoryBy路径
	$("#categoryMsgBtn").click(function(){
		$("#showCategoryMsg").hide();
		if(isHandleCat){
			isHandleCat = true;
			var classId = $("#msgClassId").val();
			var catId = $("#msgcategoryId").val();
			//window.location.replace("getCategoryBy?classId="+classId+"&categoryId="+catId);
			$("#currentCategoryId").val(catId);
			getCategoryBy(classId);
		}else{
			isHandleCat = true;
		}

	});
	
		
		