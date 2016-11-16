<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>企业资质认证-上工好药</title>
</head>

<body>

<#include "./inc/header-center.ftl"/>

<!-- member-box start -->
<div class="member-box">
    <div class="wrap">
    <#include "./inc/side-center.ftl"/>

        <div class="main">
            <div class="title">
                <h3>企业资质认证</h3>
                <div class="extra"></div>
            </div>

            <div class="fa-step">
                <ol>
                    <li class="curr"><em>1</em>填写基础信息</li>
                    <li><i class="fa fa-chevron-right"></i></li>
                    <li><em>2</em>上传企业资质，等待平台审核</li>
                    <li><i class="fa fa-chevron-right"></i></li>
                    <li><em>3</em>完成</li>
                </ol>
            </div>

            <div class="fa-form">
                <form action="" id="myform">
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业名称：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="" autocomplete="off" name="company" id="company" placeholder="营业执照上的企业名称全称" type="text">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业负责人：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="" autocomplete="off" name="corporation" id="corporation" placeholder="营业执照上法人姓名" type="text">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业所在地：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="" autocomplete="off" name="address" id="address" placeholder="营业执照上的企业所在地地址" type="text">
                        </div>
                    </div>
                    <div class="group group-cbx">
                        <div class="txt">
                            <i>*</i>企业类型：
                        </div>
                        <div class="cnt">
                            <label><input type="radio" name="type" class="cbx" value="1">单体药店</label>
                            <label><input type="radio" name="type" class="cbx" value="2">连锁药店</label>
                            <label><input type="radio" name="type" class="cbx" value="3">公立医院</label>
                            <label><input type="radio" name="type" class="cbx" value="4">民营医院</label>
                            <label><input type="radio" name="type" class="cbx" value="5">个体诊所</label>
                            <label><input type="radio" name="type" class="cbx" value="6" id="type">社区医疗机构</label>
                        </div>
                    </div>
                    <div class="ft">
                        <div class="cnt">
                            <button type="submit" class="btn btn-red btn-wide" id="submit">下一步</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div><!-- member-box end -->


<#include "./inc/footer.ftl"/>
<script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script src="/js/jquery.form.js"></script>
<script>
    var _global = {
        v: {
            stepOneUrl:'/center/certificate/stepOne',
            stepTwoUrl:'/center/certificate/stepTwo'
        },
        fn: {
            init: function() {
                this.formValidate();
            },
            formValidate: function() {
                $('#myform').validator({
                    fields: {
                        company: 'required',
                        corporation: 'required',
                        address: 'required',
                        type: 'checked',
                    },
                    valid : function(form) {
                        var myfromValid = this;
                        if ($(form).isValid()) {
                            $.ajax({
                                url : _global.v.stepOneUrl,
                                data : $(form).formSerialize(),
                                type : "POST",
                                success : function(data) {
                                    var status = data.status;
                                    var info = data.info;
                                    if (status == 'y') {
                                        window.location.href = _global.v.stepTwoUrl;
                                        return;
                                    }
                                    if (status == '20001') {
                                        myfromValid.showMsg("#company", {
                                            type: "error",
                                            msg: info
                                        })
                                        return;
                                    }
                                    if (status == '20002') {
                                        myfromValid.showMsg("#corporation", {
                                            type: "error",
                                            msg: info
                                        })
                                        return;
                                    }
                                    if (status == '20003') {
                                        myfromValid.showMsg("#address", {
                                            type: "error",
                                            msg: info
                                        })
                                        return;
                                    }
                                    if (status == '20004') {
                                        myfromValid.showMsg("#type", {
                                            type: "error",
                                            msg: info
                                        })
                                        return;
                                    }

                                }
                            });
                        }
                    }
                });
            }
        }
    }
    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>
