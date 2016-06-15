/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    Date.prototype.Format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    };
    //日期控件
    var hour1 = '00:00',
        hour2 = '23:59',
        myDate1 = new Date().Format("yyyy/MM/dd")+ ' '+hour1,
        //myDate1 = new Date().toLocaleDateString()+ ' '+hour1,
        myDate2 = new Date().Format("yyyy/MM/dd")+ ' '+hour2,
        datetimepicker1 = $('#datetimepicker1'),
        datetimepicker2 = $('#datetimepicker2');
    if(datetimepicker1.val() == ''){
        datetimepicker1.val(myDate1);
    }else{
        datetimepicker1.val(datetimepicker1.val());
    }
    if(datetimepicker2.val() == ''){
        datetimepicker2.val(myDate2);
    }else{
        datetimepicker2.val(datetimepicker2.val());
    }
    datetimepicker1.datetimepicker();
    datetimepicker2.datetimepicker();
    $("#datetimepicker1").attr("readonly","readonly");
    $("#datetimepicker2").attr("readonly","readonly");
    $('#datetimepicker1').focus(function() {
        if ($(this).val() == myDate1) {
            $(this).val('');
        }
        else {
            $(this).val($(this).val());
        }
    }).blur(function(){
        if($(this).val() == 0){
            $(this).val(myDate1);
        }else{
            $(this).val($(this).val());
        }
    });
    $('#datetimepicker2').focus(function() {
        if ($(this).val() == myDate2) {
            $(this).val('');
        }
        else {
            $(this).val($(this).val());
        }
    }).blur(function(){
        if($(this).val() == 0){
            $(this).val(myDate2);
        }else{
            $(this).val($(this).val());
        }
    });
    $('#btn-blue').click(function(){
        var startTime=$("#datetimepicker1").val();
        var start=new Date(startTime.replace("-", "/").replace("-", "/"));
        var endTime=$("#datetimepicker2").val();
        var end=new Date(endTime.replace("-", "/").replace("-", "/"));
        if(end<start){
            alert('结束时间不可小于开始时间！');
            return;
        }
        return true;
    });


    //tips
    $('.operate-1').hover(
        function(){
            $(this).children('.tips').show();
        },
        function(){
            $(this).children('.tips').hide();
        }
    );
    //弹层
    $( "#addMember" ).dialog({
        autoOpen: false,
        width: 794,
        buttons: [
            {
                text: "添加",
                id:"addMemberBnt",
                click: function() {
                    $("#addMember form").submit();
                    //$( this ).dialog( "close" );
                    //$('.bghui').remove();
                }
            },
            {
                text: "重置",
                click: function() {
                    addMemberForm.resetForm();
                    /* $( this ).dialog( "close" );
                     $('.bghui').remove(); */
                }
            }
        ]
    });

    $( "#reMember" ).dialog({
        autoOpen: false,
        width: 794,
        buttons: [
            {
                text: "修改",
                id:"reMemberBtn",
                click: function() {
                    $("#reMember form").submit();
                    //$( this ).dialog( "close" );
                    //$('.bghui').remove();
                }
            },
            {
                text: "取消",
                click: function() {
                    //reMemberForm.resetForm();
                    $( this ).dialog( "close" );
                    $('.bghui').remove();
                }
            }
        ]
    });

    $( "#reMark" ).dialog({
        autoOpen: false,
        width: 700,
        buttons: [
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
        $( "#addMember" ).dialog( "open" );
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
        event.preventDefault();
    });

    var ReMember = $('.operate-2');
    ReMember.each(function(){
        $(this).click(function( event ){
            $( "#reMember" ).dialog( "open" );
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
            event.preventDefault();
        })
    });

    //添加会员表单验证
    var addMemberForm = $(".addMemberForm").Validform({
        tiptype:2
        //btnSubmit:"#addMemberBtn"
    });

    var reMemberForm = $(".reMemberForm").Validform({
        tiptype:2
        //btnSubmit:"#reMemberBtn"
    });
    //获取随机密码
    function getRomSecret(){
        $.post("/romSecret",function(data){
            $('#addMember #passWord').val(data);
        });
    }
    //获取会员信息
    var getMember = function(memberId){
        var html = '<div class="bghui"></div>';
        $('body').append(html);
        $( "#reMember" ).dialog( "open" );

        $.post("/getMember",{userId:memberId},function(data){
            alert(data);
            var array = data.split(",");
            for(var i=0;i<array.length;i++){
                var feild = array[i].split(":");
                if(feild[0]=="userId"){
                    $('#reMember #memberId').val(feild[1]);
                }else if(feild[0]=="userName"){
                    $('#reMember #userName').html(feild[1]);
                }else if(feild[0]=="companyName"){
                    $('#reMember #realName').val(feild[1]);
                }else if(feild[0]=="mobile"){
                    $('#reMember #mobileNo').val(feild[1]);
                }else if(feild[0]=="email"){
                    if(feild[1]=="null"){
                        $('#reMember #email').val("");
                    }else{
                        $('#reMember #email').val(feild[1]);
                    }
                }else if(feild[0]=="remark"){
                    if(feild[1]=="null"){
                        $('#reMember #remark').val("");
                    }else{
                        $('#reMember #remark').val(feild[1]);
                    }
                }
            }
        });

    }

    return getMember;
    getMember();

    //会员锁定解锁
    function islock(memberId,status){
        if(status==0){
            if(!window.confirm("确定要锁定该会员吗？")){
                return;
            }
        }else if(status==2){
            if(!window.confirm("该会员为锁定状态，确定要开通吗？")){
                return;
            }
        }else if(status==1){
            alert("已删除会员不能进行操作！");
            return;
        }
        $.post("/lock",{userId:memberId,status:status},function(data){
            if(data==1){
                alert("操作成功");
                location.reload(true);
            }else if(data==0){
                alert("操作失败");
            }

        });

    }

    //删除会员
    function delMember(memberId,status){
        if(status==1){
            alert("该会员已删除,无法操作已删除会员！");
            return;
        }

        if(!window.confirm("删除是不可恢复的，确认要删除吗？")){
            return;
        }

        $.post("/delMember",{userId:memberId},function(data){
            if(data == 1){
                alert("删除成功！");
                location.reload(true);
            }else if(data == 0){
                alert("删除失败！");
            }
        });

    }

    //密码重置
    function secretReset(memberId){
        if(!window.confirm("你确定要重置密码吗？")){
            return;
        }
        $.post("",{userId:memberId},function(data){
            if(data == 1){
                alert("重置短信发送成功！");
            }else if(data == 0){
                alert("重置短信发送失败！");
            }else{
                alert("重置失败！");
            }
        });
    }

    //查看备注
    function lookRemark(memberId){
        $( "#reMark" ).dialog( "open" );
        $.post("/lookRemark",{userId:memberId},function(data){
            $("#reMark p").html(data);
        });
    }
});