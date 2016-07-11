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
                <span>修改角色失败！</span>
            </div>

        <div class="side">
                <dl>
                    <dt>用户信息</dt>
                    <dd>
                        <a href="member/index">账号信息</a>
                        <a class="curr" href="member/role/${memberId}">角色信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                
                <div class="title title-btm">
                    <h3><i class="fa fa-people"></i>创建用户</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">重置</button>
                        <button id="submit" type="submit" class="btn btn-red">保存用户</button>
                    </div>
                </div>

                <form id="memberRoleForm" method="post" action="member/role/save">
                    <input type="hidden" id="memberId" name="memberId" value="${memberId}">
                    <div class="chart">
                        <table class="tc">
                            <thead>
                                <tr>
                                    <th>指定</th>
                                    <th>
                                        <div class="tl">角色名称</div>
                                    </th>
                                </tr>

                            </thead>
                            <tfoot></tfoot>
                            <tbody>
                                <#list roleList as role>
                                    <tr>
                                        <td>
                                            <label>
                                                <input type="checkbox" name="roleIds" value="${role.id}">
                                            </label>
                                        </td>
                                        <td>
                                            <div class="tl">${role.name}</div>
                                        </td>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>
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
                    $("#submit").click(function(){
                        roleAddPage.fn.save();
                    })
                    roleAddPage.fn.memberCheckInit()
                },
                memberCheckInit:function () {
                    $.ajax({
                        url: "/role/member",
                        type: "POST",
                        data:{memberId:$("#memberId").val()},
                        success: function(result){

                            $.each(result,function(index,obj){
                                $("input[name='roleIds']").each(function(){
                                    if(obj.id==$(this).val()){
                                        $(this).attr("checked",true)
                                    }
                                })

                            })


                        }
                    });
                },
                save:function(){
                    $("#memberRoleForm").ajaxSubmit({
                        dataType: "json",
                        success: function (result) {
                            if(result.status=="y"){
                                location.href="member/index?advices="+result.info
                            }else{
                                $("#error_advices").show();
                            }
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