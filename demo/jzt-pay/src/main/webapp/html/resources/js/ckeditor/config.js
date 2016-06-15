/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config ) {
	config.language = 'zh-cn'; 
	config.uiColor = '#efefef';
	config.width = '100%'; 
	if (config.height == ''){
		config.height = '500px';
	}
	config.removePlugins = 'elementspath,scayt';
	config.disableNativeSpellChecker = false;
	config.resize_dir = 'vertical';
	config.keystrokes =[[ CKEDITOR.CTRL + 13 /*Enter*/, 'maximize' ]];	
	config.extraPlugins = 'tableresize';
	config.enterMode = CKEDITOR.ENTER_P;
	config.shiftEnterMode = CKEDITOR.ENTER_BR;
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	config.image_previewText='&nbsp;';
	config.toolbar_default = [
		['Source','-','Paste','PasteText','PasteFromWord','-','Undo','RemoveFormat'],
	    ['Bold','Italic','Underline','Strike','-'],['TextColor','BGColor'],
	    ['Subscript','Superscript','-'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Table'],
	];
	config.toolbar = 'default';
	//图片处理
	config.pasteFromWordRemoveStyles = true;
	config.filebrowserImageUploadUrl = '../upload/uploadpic';  
};
CKEDITOR.stylesSet.add( 'default', [
	/* Block Styles */
	{ name : '首行缩进'	, element : 'p', styles : { 'text-indent' : '20pt' } },
	/* Inline Styles */
	{ name : '标注黄色'	, element : 'span', styles : { 'background-color' : 'Yellow' } },
	{ name : '标注绿色'	, element : 'span', styles : { 'background-color' : 'Lime' } },
	/* Object Styles */
	{ name : '图片左对齐', element : 'img', attributes : { 'style' : 'padding: 5px; margin-right: 5px', 'border' : '2', 'align' : 'left' } },
	{ name : '图片有对齐', element : 'img', attributes : { 'style' : 'padding: 5px; margin-left: 5px', 'border' : '2', 'align' : 'right' } },
	{ name : '无边界表格', element : 'table', styles: { 'border-style': 'hidden', 'background-color' : '#E6E6FA' } }
]);
CKEDITOR.on( 'dialogDefinition', function( ev ) {
      // Take the dialog name and its definition from the event data.
      var dialogName = ev.data.name;
      var dialogDefinition = ev.data.definition;

    if(dialogName == 'image') {
      dialogDefinition.onLoad = function () {
         var dialog = CKEDITOR.dialog.getCurrent(); 
         
         // show upload tab 
         this.selectPage('Upload');
         
         // optional:
         dialog.hidePage( 'Link' ); 
         dialog.hidePage( 'advanced' ); 
         
         var uploadTab = dialogDefinition.getContents('Upload');
         var uploadButton = uploadTab.get('uploadButton');
         uploadButton['filebrowser']['onSelect'] = function( fileUrl, errorMessage ) {
            //$("input.cke_dialog_ui_input_text").val(fileUrl);
            dialog.getContentElement('info', 'txtUrl').setValue(fileUrl);
            $(".cke_dialog_ui_button_ok span").click();
         }
      };
   }
});
