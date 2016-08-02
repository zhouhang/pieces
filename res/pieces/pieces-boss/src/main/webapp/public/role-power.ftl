<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色权限-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />

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

            <div class="side">
                <dl>
                    <dt>角色信息</dt>
                    <dd>
                        <a href="role/info/${role.id}">角色信息</a>
                        <a class="curr" href="role/power/${role.id}">角色权限</a>
                        <a href="role/list/${role.id}">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="myform">
                    <input id="roleId" type="hidden" value="<#if role??>${role.id}</#if>">
                    <div class="title">
                        <h3>修改角色“${role.name}”</h3>
                        <div class="extra">
                            <a class="btn btn-gray" href="role/index">返回</a>
                            <#if role??>
                            <#--//location.href='role/delete?roleId=${role.id}'-->
                                <button type="button" class="btn btn-gray" onclick="javascript:if(confirm('你确定删除吗？')){location.href='role/delete?roleId=${role.id}'}" >删除</button>
                            </#if>
                            <button id="submit" type="button" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>角色权限</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    选择权限：
                                </div>
                                <div class="cnt">
                                    <label><input type="checkbox" name="" id="allCheck" class="cbx">全选</label>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    资源：
                                </div>
                                <div>
                                    <ul id="powerTree" class="ztree"></ul>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>


    <script src="js/jquery.ztree.min.js"></script>

    <script>
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        $(function(){

            var rootTree = null;

            //加载所有资源
            $.ajax({
                url: "/role/resources",
                type: "POST",
                data:{roleId:$("#roleId").val()},
                async:false,
                success: function(result){
                    rootTree =  $.fn.zTree.init($("#powerTree"), setting, result);
                }
            });

            if(rootTree.getCheckedNodes(false).length==0){
                $("#allCheck").attr("checked","checked");
            }


            //全选
            $("#allCheck").click(function() {
                rootTree.checkAllNodes(this.checked)
            });

            //保存
            $("#submit").click(function(){
                save();
            })


            function save(){
                var arrIds = [];
                //获取所有选中的节点
                var checkNodes = rootTree.getCheckedNodes(true);
                $.each(checkNodes,function(index){
                    arrIds.push(this.id)
                })

                var roleId = $("#roleId").val();

                $.ajax({
                    url: "/role/resources/save",
                    type: "POST",
                    data:{roleId:roleId,resourcesIds:arrIds},
                    success: function(result){
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
            }


        });

    </script>
</body>
</html>