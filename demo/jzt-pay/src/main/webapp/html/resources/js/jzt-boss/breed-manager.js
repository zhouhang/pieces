/**
*Created by chengchang on 2014/12/24.
/

$(function(){
    //弹层
    $( "#addBreed" ).dialog({
        autoOpen: false,
        width: 794,
        buttons: [
            {
                text: "添加",
                click: function() {
                    $( this ).dialog( "close" );
                    $('.bghui').remove();
                }
            },
            {
                text: "取消",
                click: function() {
                    $( this ).dialog( "close" );
                    $('.bghui').remove();
                }
            }
        ]
    });	
	
	
    // Link to open the dialog(点击添加品种)
    $( ".btn-add" ).click(function( event ) {
        var html = '<div class="bghui"></div>';
        $('body').append(html);
        $( "#addBreed" ).dialog( "open" );
        event.preventDefault();
    });
	//点击修改品种(和添加Breed是同一个页面)
    var ReBreed = $('.operate-2');
    ReBreed.each(function(){
        $(this).click(function( event ){
            var html = '<div class="bghui"></div>';
            $('body').append(html);
            $( "#addBreed" ).dialog( "open" );
            event.preventDefault();
        })
    });	
	//点击删除品种
	var DeleteBreed = $('.operate-4');
    DeleteBreed.each(function(){
        $(this).click(function( event ){
            var html = '<div class="bghui"></div>';
            $('body').append(html);
            $( "#delete-breed" ).dialog( "open" );
            event.preventDefault();
        })
    });	
});