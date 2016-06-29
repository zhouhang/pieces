/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    //验证
    function Select(){
        if($('#yz>option:selected').text() == '手机验证'){
            $('#iphone').css('display','block');
            $('#email').css('display','none');
        }
        if($('#yz>option:selected').text() == '邮箱验证'){
            $('#iphone').css('display','none');
            $('#email').css('display','block');
        }
    }
    Select();
    $('#yz').click(function(){
        Select();
    })
});