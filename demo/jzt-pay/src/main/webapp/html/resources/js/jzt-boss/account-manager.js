/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    $('.operate-1').hover(
        function(){
            $(this).children('.tips').show();
        },
        function(){
            $(this).children('.tips').hide();
        }
    );
    //弹层
    $( "#addAccount" ).dialog({
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

    $( "#reAccount" ).dialog({
        autoOpen: false,
        width: 794,
        buttons: [
            {
                text: "修改",
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
    // Link to open the dialog
    $( ".btn-add" ).click(function( event ) {
        var html = '<div class="bghui"></div>';
        $('body').append(html);
        $( "#addAccount" ).dialog( "open" );
        event.preventDefault();
    });

    var ReMember = $('.operate-2');
    ReMember.each(function(){
        $(this).click(function( event ){
            var html = '<div class="bghui"></div>';
            $('body').append(html);
            $( "#reAccount" ).dialog( "open" );
            event.preventDefault();
        })
    });
});