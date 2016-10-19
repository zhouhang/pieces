<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>编辑角色-boss-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl">


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">

            <div  style="display: none" id="error_advices" class="message">
                <i class="fa fa-times-circle"></i>
                <span>新增失败！</span>
            </div>
            <div  style="display: none" id="success_advices" class="message">
                <i class="fa fa-check-circle"></i>
                <span>编辑成功！</span>
            </div>


            <div class="side">
                <dl>
                    <dt>角色信息</dt>
                    <dd>
                        <a id="role_info_a" class="curr" href="<#if role??>role/info/${role.id}<#else>role/add</#if>">角色信息</a>
                        <a id="role_power_a" href="<#if role??>role/power/${role.id}<#else>role/add</#if>">角色权限</a>
                        <a id="role_list_a" href="<#if role??>role/list/${role.id}<#else>role/add</#if>">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="role/save" id="roleForm" method="post">
                    <div class="title">
                        <h3><i class="fa fa-people"></i><#if !role??>新增角色<#else>${role.name}</#if></h3>
                        <div class="extra">
                            <a  class="btn btn-gray" href="role/index">返回</a>
                            <#if role??>
                                <@shiro.hasPermission name="role:delete">
                                    <button type="button" class="btn btn-gray" onclick="javascript:if(confirm('你确定删除吗？')){location.href='/role/delete?roleId=${role.id}'}" >删除</button>
                                </@shiro.hasPermission>
                            </#if>
                            <button id="submit" type="button" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>角色信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>角色名称：
                                </div>
                                <input type="hidden" class="ipt" value="<#if role??>${role.id}</#if>"  name="id" id="id" >

                                <div class="cnt">
                                    <input type="text" class="ipt" value="<#if role??>${role.name}</#if>" autocomplete="off" name="name" id="name" placeholder="请输入角色名称">
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


                    $("#submit").click(function(){
                        roleAddPage.fn.save();
                    })


                },
                formValidate: function() {
                    $("#roleForm").validator({
                        fields: {
                            username: "required"
                        }
                    });
                },
                save:function(){
                    $("#roleForm").ajaxSubmit({
                        success:function(result){
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
                                delay: 3e3,
                                call: function () {
                                    $("#role_info_a").attr("href","role/info/"+result.data.id)
                                    $("#role_power_a").attr("href","role/power/"+result.data.id)
                                    $("#role_list_a").attr("href","role/list/"+result.data.id)
                                }
                            });

                        }
                    })
                }

            }
        }
        $(function() {
            roleAddPage.fn.init();
        })
    </script>
</body>
</html>