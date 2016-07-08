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
        <div class="side">
            <dl>
                <dt>用户信息</dt>
                <dd>
                    <a class="curr" href="user_info.html">账号信息</a>
                    <a href="user_role.html">角色信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="memberForm">
                <div class="title">
                    <h3><i class="fa fa-people"></i>创建用户</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">重置</button>
                        <button type="submit" class="btn btn-red">保存用户</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>账号信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>用户名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="username" id="username" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>姓名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="name" id="name" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>邮箱：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="email" id="email" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>密码：
                            </div>
                            <div class="cnt">
                                <input type="password" class="ipt" value="" autocomplete="off" name="password" id="password" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>新密码：
                            </div>
                            <div class="cnt">
                                <input type="password" class="ipt" value="" autocomplete="off" name="passwordRepeat" id="passwordRepeat" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="is_del" id="" class="wide">
                                    <option value="true">激活</option>
                                    <option value="false">禁用</option>
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
            },
            formValidate: function() {
                $('#memberForm').validator({
                    fields: {
                        username: '用户名: required',
                        name: '姓名: required, nickName',
                        email: '邮箱: required; email',
                        password: '密码: required; password',
                        passwordRepeat: '确认密码: required; match(password)'
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