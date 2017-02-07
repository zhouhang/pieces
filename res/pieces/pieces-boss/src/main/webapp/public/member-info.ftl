<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新增用户-boss-上工好药</title>
</head>

<body>

<#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div  style="display: none" id="success_advices" class="message">
            <i class="fa fa-check-circle"></i>
            <span>编辑成功！</span>
        </div>
        <div  style="display: none" id="error_advices" class="message">
            <i class="fa fa-times-circle"></i>
            <span>编辑信息失败！</span>
        </div>

        <div class="side">
            <dl>
                <dt>用户信息</dt>
                <dd>
                    <#if member??>
                        <a  class="curr" href="/member/edit/${member.id}">账号信息</a>
                    <#else>
                        <a  class="curr" href="/member/add">账号信息</a>
                    </#if>
                    <#if member??>
                        <a  href="/member/role/${member.id}">角色信息</a>
                    </#if>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="member/save" id="memberForm" method="post">
                <div class="title">
                    <h3><i class="fa fa-people"></i> <#if member??>${member.username}<#else>创建用户</#if></h3>
                    <div class="extra">
                        <a  class="btn btn-gray" href="member/index">返回</a>
                        <button id="memberSubmit" type="button" class="btn btn-red">保存</button>
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
                                <input type="text" class="ipt" value="${(member.name)!}" autocomplete="off" name="name" id="name" placeholder="姓名">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>邮箱：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${(member.email)!}" autocomplete="off" name="email" id="email" placeholder="邮箱">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <#if (!member??)><i>*</i></#if>密码：
                            </div>
                            <div class="cnt">
                                <input type="password" class="ipt" value="" data-rule="<#if (!member??)>required;</#if>password" autocomplete="off" name="password" id="password" placeholder="<#if (member??)>不修改密码留空</#if>">
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

<script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script>
    var roleAddPage = {
        v: {},
        fn: {
            init: function() {
                this.formValidate();
                $("#memberSubmit").click(function(){
                    roleAddPage.fn.save();
                })
                $("#ajaxSubmit").click(function(){
                    roleAddPage.fn.save(true);
                })
                if($("#idDel").val()!=null&&$("#idDel").val()!=""){
                    $("#memberForm select[name=isDel]").val($("#idDel").val())
                }
                //重置表单
                $("#reset").click(function(){
                    roleAddPage.fn.clearForm($("#memberForm"));
                })
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
            save:function(ajax){
                if(!$('#memberForm').isValid()) {
                    return false;
                };

                $("#memberForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        var type = "error";
                        var title = "操作失败";
                        if(result.status=="y"){
                            type="success";
                            title="操作成功";
                        }
                        $.notify({
                            type: type,
                            title: title,
                            text: result.info,
                            delay: 3e3
                        });
                    }
                });
            },
            clearForm: function (form) {
                form.find("textarea").val("");
                form.find('input:not(:hidden, :checkbox)').val("")
                form.find("input[type=checkbox]").attr("checked",false)
            }
        }
    }
    $(function() {
        roleAddPage.fn.init();
    })
</script>
</body>
</html>