/**
 * Created by zhouli on 2015/1/4.
 */
//表单验证
$("#legalizeCompany").Validform({
    tiptype:3
});
//图片上传
$("input[type='file']").change( function() {
    $(this).parents('li').children('span:last').addClass('right');
    //$(this).next('i').text($(this).attr('title'));
});