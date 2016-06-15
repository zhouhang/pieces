/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    //获取短信验证码后，60s可以重新发送
    var i=60;
    var time=function(){
        i-=1 ;
        $("#getCode").val('(' + i + ')秒后重新获取');
        $("#getCode").attr("disabled","disabled");
        if(i==0){
            $("#getCode").removeAttr("disabled");
            $("#getCode").val("重新获取验证码");
            i=60;
            return;
        }
        setTimeout(time,1000);
    }
    //验证
    $(".registerform").Validform({
        tiptype:2
    });
    $("#getCode").click(function(){
        var mobile = $("#mobile").val();
        var reg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
        if(mobile=="" || mobile==null){
            alert("请输入手机号");
            return;
        }
        if(!reg.test(mobile)){
            alert("请输入正确的手机号!");
            return;
        }
        //验证手机号
        $.post("checkMobileisExist",{
            "mobile":mobile
        },function(data){
            if(data!='y'){
                alert("该手机号已注册,请重新输入！");
                return;
            }else{
                //获取手机验证码
                $.post("/findBackPwd/getMobileCode",{
                    "memberMobile":mobile
                },function(data){
                    if(data=='y'){
                        alert("发送成功");
                        time(60);
                    }else if(data=='n'){
                        alert("发送失败");
                    }
                });
            }
        });

    });
});