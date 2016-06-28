// JavaScript Document
$(function(){
	    /*//Tab功能
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
		$("body").bind("mousedown",onBodyMouseDown);
		
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
		//绑定body的鼠标事件
		function onBodyMouseDown(event){
			if (event.target.id == "m_add") {//新增目录
				$('#addGccount').show();
				$('body').append(html);
			}else if(event.target.id == "m_del"){
				//加弹层事件
			}else if(event.target.id == "m_check"){
				//加弹层事件
			}else if(event.target.id == "add_tab"){
				//加弹层事件
			}
			$("#rMenu").css({"visibility" : "hidden"});
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
        //关联品种弹层

        //var reAccount = $('');
        var Close = $('.close');
        var html = '<div class="bghui"></div>';

        Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
            })
        });
        $('#aaa').on('click',function(){
            $('#addQccount').show();
            $('body').append(html);
        });*/
		
		//绑定 新增目录

		/*$('#m_add').on('click',function(){
			$('#addGccount').show();
			$('body').append(html);
		});*/

		
		
		

    });


