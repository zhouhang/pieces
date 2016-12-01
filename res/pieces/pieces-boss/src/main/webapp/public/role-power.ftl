<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色权限-boss-上工好药</title>
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
                        <a href="/role/info/${role.id}">角色信息</a>
                        <a class="curr" href="/role/power/${role.id}">角色权限</a>
                        <a href="/role/list/${role.id}">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="myform">
                    <input id="roleId" type="hidden" value="<#if role??>${role.id}</#if>">
                    <div class="title">
                        <h3>修改角色“${role.name}”</h3>
                        <div class="extra">
                            <a class="btn btn-gray" href="/role/index">返回</a>
                            <#if role??>
                            <#--//location.href='role/delete?roleId=${role.id}'-->
                                <button type="button" class="btn btn-gray" id="delete">删除</button>
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
                                <div class="cnt cnt-mul">
                                    <label><input type="checkbox" name="" id="allCheck" class="cbx">全选</label>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    资源：
                                </div>
                                <div class="mt">
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
    <script src="js/layer/layer.js"></script>

    <script>
    var _global = {
        fn: {
            init: function() {
                this.bindEvent();
                this.delete();
            },
            bindEvent: function() {
                var setting = {
                    check: {
                        enable: true,
                        chkboxType:{
                            'Y' : 'ps',
                            'N' : 's'
                        }
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };

                var rootTree = null;


                //加载所有资源
                $.ajax({
                    url: "/role/resources",
                    type: "POST",
                    data:{roleId:$("#roleId").val()},
                    async:false,
                    success: function(result){
                        rootTree = $.fn.zTree.init($("#powerTree"), setting, result);
                    }
                });

                if(rootTree.getCheckedNodes(false).length == 0){
                    $('#allCheck').prop('checked', true);
                }

                //全选
                $('#allCheck').click(function() {
                    rootTree.checkAllNodes(this.checked)
                });
                //保存
                $("#submit").click(function(){
                    var roleId = $("#roleId").val(),
                        arrIds = [],
                        checkNodes = rootTree.getCheckedNodes(true),
                        type = "error",
                        title = "操作失败";

                    $.each(checkNodes,function(index){
                        arrIds.push(this.id)
                    })

                    $.ajax({
                        url: "/role/resources/save",
                        type: "POST",
                        data:{roleId:roleId, resourcesIds:arrIds},
                        success: function(result){
                            if(result.status=="y"){
                                type = "success";
                                title = "操作成功";
                            }
                            $.notify({
                                type: type,
                                title: title,
                                text: result.info,
                                delay: 3e3
                            });
                        }
                    });
                })
            },
            delete: function() {
                $('#delete').on('click', function() {
                    layer.confirm('确认要删除该分类？', {icon: 3, title:'提示'}, function(index){
                        location.href = '/role/delete?roleId=${role.id}';
                    })
                })
            }
        }
    }
    
    $(function(){
        _global.fn.init();
    });

    </script>
</body>
</html>