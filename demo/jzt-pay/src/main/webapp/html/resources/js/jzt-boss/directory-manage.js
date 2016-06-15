/**
 * Created by zhouli on 2014/12/3.
 */
 

		var zNodes =[{id:0, pId:-1, name:"分类", open:true,  childOuter:false},
			{ id:1, pId:0, name:"同级目录排序 1", open:true,  childOuter:false},
			{ id:11, pId:1, name:"同级目录排序 1-1", open:true,  childOuter:false},
			{ id:12, pId:1, name:"同级目录排序 1-2", open:true, childOuter:false},
			{ id:121, pId:12, name:"同级目录排序 1-2-1", open:true, childOuter:false},
			{ id:122, pId:12, name:"同级目录排序 1-2-2", open:true, childOuter:false},
			{ id:123, pId:12, name:"同级目录排序 1-2-3", open:true, childOuter:false},
			{ id:13, pId:1, name:"同级目录排序 1-3", open:true,  childOuter:false},
			{ id:131, pId:13, name:"同级目录排序 1-3-1", open:true,  childOuter:false},
			{ id:132, pId:13, name:"同级目录排序 1-3-2", open:true,  childOuter:false},
			{ id:132, pId:13, name:"同级目录排序 1-3-3", open:true,  childOuter:false},
			{ id:2, pId:0, name:"同级目录排序 2", open:true,  childOuter:false},
			{ id:21, pId:2, name:"同级目录排序 2-1", open:true,  childOuter:false},
			{ id:22, pId:2, name:"同级目录排序 2-2", open:true,  childOuter:false},
			{ id:23, pId:2, name:"同级目录排序 2-3", open:true,  childOuter:false},
			{ id:3, pId:0, name:"同级目录排序 3", open:true,  childOuter:false},
			{ id:31, pId:3, name:"同级目录排序 3-1", open:true,  childOuter:false},
			{ id:32, pId:3, name:"同级目录排序 3-2", open:true,  childOuter:false},
			{ id:33, pId:3, name:"同级目录排序 3-3", open:true,  childOuter:false}
		];
		

		
		
		
		
/*		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#callbackTrigger").bind("change", {}, setTrigger);
		});*/
		
		
		
$(function(){
	
		/***************************************************树初始化***************************************************/
	
	    var setting = {
			
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
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
					enable: true
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onRightClick: OnRightClickTree,
				onDrag: onDrag,
				onDrop: onDrop,
				onExpand: onExpand
			}
			
		};
		
		//绑定body的鼠标事件
		$("body").bind("mousedown", onBodyMouseDown);
		$("body").bind("mousedown", onBodyMouseDownTree);
		
		
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

		var log, className = "dark", curDragNodes, autoExpandNode;
		function beforeDrag(treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
			for (var i=0,l=treeNodes.length; i<l; i++) {
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

		function setTrigger() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
		}
		
		
		/**************************************************zTree右键菜单功能****************************************/
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
				//$("#m_unCheck").hide();
			} else {
				$("#m_add_tree").show();
				$("#m_del_tree").show();
				$("#m_mod_tree").show();
				//$("#m_unCheck").show();
			}
			$("#rMenuTree").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		}
		
		//隐藏右键菜单
		//function hideRMenu() {
			//if ($("#rMenu")){
				//$("#rMenu").css({"visibility": "hidden"});
			//} 
			//$("body").unbind("mousedown", onBodyMouseDown);
		//}
		
		
		//初始化
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#callbackTrigger").bind("change", {}, setTrigger);
		
		
		/************************************Tab分类*************************************/	

		var $tab_title_input = $( "#tab_title"),
			$tab_content_input = $( "#tab_content" );
		var tab_counter = 2;

		// tabs init with a custom tab template and an "add" callback filling in the content
		var $tabs = $( "#tabs").tabs({
			tabTemplate: "<li class='onRC'><a href='#{href}' style='text-align:center; width:112px;'>#{label}</a> </li>",
			add: function( event, ui ) {
				var tab_content = $tab_content_input.val() || "Tab " + tab_counter + " content.";
				$( ui.panel ).append( "<p>" + tab_content + "</p>" );
				
				//alert($( ui.panel ).text());
				
			}
			
		});
		//tabTemplate.before('#button');

		// modal dialog init: custom buttons and a "close" callback reseting the form inside
		var $dialog = $( "#dialog" ).dialog({
			autoOpen: false,
			modal: true,
			buttons: {
				增加: function() {
					
					addTab();
					$( this ).dialog( "close" );
				},
				取消: function() {
					$( this ).dialog( "close" );
				}
			},
			open: function() {
				$tab_title_input.focus();
			},
			close: function() {
				$form[ 0 ].reset();
			}
		});

		// addTab form: calls addTab function on submit and closes the dialog
		var $form = $( "form", $dialog ).submit(function() {
			addTab();
			$dialog.dialog( "close" );
			return false;
		});

		// actual addTab function: adds new tab using the title input from the form above
		function addTab() {
			var tab_title = $tab_title_input.val() || "Tab " + tab_counter;
			
			$tabs.tabs( "add", "#tabs-" + tab_counter, tab_title );

			tab_counter++;
		}

		// addTab button: just opens the dialog
		$( "#add_tab" )
			
			.click(function() {
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
		
		
		/************************************关联品种弹*************************************/	

       
        $('#aaa').on('click',function(){
            $('#addQccount').show();
            $('body').append(maskDiv);
        });
		
		
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
		//Tab分类鼠标事件
		function onBodyMouseDown(event){
			if (event.target.id == "m_add") {//新增目录
				$('#addGccount').show();
				$('body').append(maskDiv);
			}else if(event.target.id == "m_del"){//删除目录
				$('#addZccount').show();
				$('body').append(maskDiv);
			}else if(event.target.id == "m_mod"){//修改目录
				$('#addHccount').show();
				$('body').append(maskDiv);
			}
			$("#rMenu").css({"visibility" : "hidden"});
			//$("#rMenuTree").css({"visibility" : "hidden"});
		}
		
		//树鼠标事件
		function onBodyMouseDownTree(event){
			//Tree
			if (event.target.id == "m_add_tree") {//新增目录
				
				$('#addGccountTree').show();
				$('body').append(maskDiv);
				
			}else if(event.target.id == "m_del_tree"){//删除目录
				$('#addZccountTree').show();
				$('body').append(maskDiv);
			}else if(event.target.id == "m_mod_tree"){//修改目录
				$('#addHccountTree').show();
				$('body').append(maskDiv);
			}
			$("#rMenuTree").css({"visibility" : "hidden"});
		}
		
});