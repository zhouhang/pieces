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
                            <input class="ipt" value="" autocomplete="off" name="companyName" placeholder="营业执照上的企业名称全称" type="text">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业负责人：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="" autocomplete="off" name="legalPerson" placeholder="营业执照上法人姓名" type="text">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业所在地：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="" autocomplete="off" name="companyRegion" placeholder="营业执照上的企业所在地地址" type="text">
                        </div>
                    </div>
                    <div class="group group-cbx">
                        <div class="txt">
                            <i>*</i>企业类型：
                        </div>
                        <div class="cnt">
                            <label><input type="radio" name="category" class="cbx">单体药店</label>
                            <label><input type="radio" name="category" class="cbx">连锁药店</label>
                            <label><input type="radio" name="category" class="cbx">公立医院</label>
                            <label><input type="radio" name="category" class="cbx">民营医院</label>
                            <label><input type="radio" name="category" class="cbx">个体诊所</label>
                            <label><input type="radio" name="category" class="cbx">社区医疗机构</label>
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


<script src="js/jquery.min.js"></script>
<script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script>
    var _global = {
        v: {
        },
        fn: {
            init: function() {
                this.formValidate();
            },
            formValidate: function() {
                $('#myform').validator({
                    fields: {
                        companyName: 'required',
                        legalPerson: 'required',
                        companyRegion: 'required',
                        category: 'checked',
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
