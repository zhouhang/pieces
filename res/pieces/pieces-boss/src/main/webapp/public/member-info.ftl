<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新增用户-boss-饮片B2B</title>
</head>

<body>

<#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div  style="display: none" id="error_advices" class="message">
            <i class="fa fa-times-circle"></i>
            <span>编辑信息失败！</span>
        </div>

        <div class="side">
            <dl>
                <dt>用户信息</dt>
                <dd>
                    <a  class="curr" href="member/index">账号信息</a>
                    <#if member??>
                        <a  href="member/role/${member.id}">角色信息</a>
                    </#if>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="member/save" id="memberForm" method="post">
                <div class="title">
                    <h3><i class="fa fa-people"></i>创建用户</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">重置</button>
                        <button id="memberSubmit" type="button" class="btn btn-red">保存用户</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>账号信息</h3>
                    <input type="hidden" id="id" name="id" value="${(member.id)!}">
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>用户名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${(member.username)!}" autocomplete="off" name="username" id="username" placeholder="用户名">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>姓名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${(member.name)!}" autocomplete="off" name="name" id="name" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>邮箱：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${(member.email)!}" autocomplete="off" name="email" id="email" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <#if (!member??)><i>*</i></#if>密码：
                            </div>
                            <div class="cnt">
                                <input type="password" class="ipt" value="" data-rule="<#if (!member??)>required;</#if>password" autocomplete="off" name="password" id="password" placeholder="不修改密码留空">
                            </div>
                        </div>
                        <input type="hidden" id="idDel"  value="${(member.isDel)!}">

                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="isDel" id="" class="wide">
                                    <option value="false">激活</option>
                                    <option value="true">禁用</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>



<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->

<script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script>
    var roleAddPage = {
        v: {},
        fn: {
            init: function() {
                this.formValidate();
                $("#memberSubmit").click(function(){
                    roleAddPage.fn.save();
                })

                if($("#idDel").val()!=null&&$("#idDel").val()!=""){
                    $("#memberForm select[name=isDel]").val($("#idDel").val())
                }

            },
            formValidate: function() {
                $('#memberForm').validator({
                    fields: {
                        username: '用户名: required',
                        name: '姓名: required, nickName',
                        email: '邮箱: required; email'
                    }
                });
            },
            save:function(){
                if(!$('#memberForm').isValid()) {
                    return false;
                };

                $("#memberForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        if(result.status=="y"){
                            location.href="member/index?advices="+result.info
                        }else{
                            $("#error_advices").show();
                        }
                    }
                });

            }
        }
    }
    $(function() {
        roleAddPage.fn.init();
    })
</script>
</body>
</html>