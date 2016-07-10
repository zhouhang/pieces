<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>修改角色-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>角色信息</dt>
                    <dd>
                        <a class="curr" href="role_info.html">角色信息</a>
                        <a href="role_power.html">角色权限</a>
                        <a href="role_list.html">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="roleForm" method="post">
                    <div class="title">
                        <h3><i class="fa fa-people"></i><#if !role??>新增角色<#else>${role.name}</#if></h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <button type="reset" class="btn btn-gray">保存</button>
                            <button type="submit" class="btn btn-red">保存并继续</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>角色信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>角色名称：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="<#if role??>${role.name}</#if>" autocomplete="off" name="username" id="username" placeholder="请输入角色名称">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

    <script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script>
        var roleAddPage = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                },
                formValidate: function() {
                    $("#roleForm").validator({
                        fields: {
                            username: "required"
                        }
                    });
                },
                save:function(){

                }
            }
        }
        $(function() {
            roleAddPage.fn.init();
        })
    </script>
</body>
</html>