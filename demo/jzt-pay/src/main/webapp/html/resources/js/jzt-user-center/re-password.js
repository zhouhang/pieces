/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    //弹层
    $( "#reMemberSuc" ).dialog({
        autoOpen: false,
        width: 485,
        buttons: [
            {
                text: "确定",
                click: function() {
                    $( this ).dialog( "close" );
                    $('.bghui').remove();
                }
            }

        ]
    });

    // Link to open the dialog
    $('#reSuc').click(function(){
        bghui();
        Alert({
            str:'密码修改成功，可登录！'
        })
    });
});