// JavaScript Document
/*$(function() {
		var $tab_title_input = $( "#tab_title"),
			$tab_content_input = $( "#tab_content" );
		var tab_counter = 2;

		// tabs init with a custom tab template and an "add" callback filling in the content
		var $tabs = $( "#tabs").tabs({
			tabTemplate: "<li class='onRC'><a href='#{href}' style='text-align:center; width:112px;'>#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
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
				showRMenu(e.clientX,e.clientY);
			}
		});
		
		//绑定按下鼠标操作
		$("body").bind("mousedown", onBodyMouseDown);
		
		function showRMenu(x, y) {
			$("#rMenu").show();
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			//$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if ($("#rMenu")){
				$("#rMenu").css({"visibility": "hidden"});
				$("body").unbind("mousedown", onBodyMouseDown);
			}
		}
		//隐藏右键
		function onBodyMouseDown(event,callback){
			if (!(event.target.id == "rMenu" || event.target.id == "add_tab")) {
				$("#rMenu").css({"visibility" : "hidden"});
			}
			if(event.target.id == "rMenu"){
				//回调函数
				callback();
			}
		}
		
	});*/










/*$(function() {
		var $tab_title_input = $( "#tab_title"),
			$tab_content_input = $( "#tab_content" );
		var tab_counter = 2;

		// tabs init with a custom tab template and an "add" callback filling in the content
		var $tabs = $( "#tabs").tabs({
			tabTemplate: "<li class='onRC'><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
			add: function( event, ui ) {
				var tab_content = $tab_content_input.val() || "Tab " + tab_counter + " content.";
				$( ui.panel ).append( "<p>" + tab_content + "</p>" );
				
				//alert($( ui.panel ).text());
				$('.onRC').on('contextmenu',function(e){
					e.preventDefault();
					showRMenu(e.clientX,e.clientY);
				});
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
		$("#tabs .ui-state-default").on('contextmenu',function(e){
			e.preventDefault();
			showRMenu(e.clientX,e.clientY);
		});
		function showRMenu(x, y) {
			$("#rMenu").show();
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if ($("#rMenu")) $("#rMenu").css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || event.target.id == "add_tab")) {
				$("#rMenu").css({"visibility" : "hidden"});
			}
		}
		
	});*/