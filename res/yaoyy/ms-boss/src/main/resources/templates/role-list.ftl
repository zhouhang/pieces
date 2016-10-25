<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色详情-boss</title>
<#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>

<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>

<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>账号权限</li>
            <li>角色详情</li>
        </ul>
    </div>

    <form id="myform">
        <div class="box fa-form">
            <div class="hd">基本信息</div>
            <div class="item">
                <input type="hidden" name="roleId" id="roleId" value="${role.id}">
                <div class="txt"><i>*</i>角色名称：</div>
                <div class="cnt">
                    <input type="text" name="rolename" id="jrolename" class="ipt" placeholder="角色名称" autocomplete="off">
                    <input type="hidden" name="catNameId">
                </div>
            </div>
            <div class="item">
                <div class="txt">资源：</div>
                <div class="cnt">
                    <ul id="powerTree" class="ztree"></ul>
                </div>
            </div>
            <div class="ft">
                <button type="submit" class="ubtn ubtn-blue" id="jsubmit">保存</button>
            </div>
        </div>

    </form>
</div>

<#include "./common/footer.ftl"/>


<script src="assets/plugins/layer/layer.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script src="assets/plugins/zTreeStyle/jquery.ztree.min.js"></script>
<script>
    var _global = {
        v: {
        },
        fn: {
            init: function() {
                this.power();
                this.myform();
            },
            power: function() {
                var setting = {
                    check: {
                        enable: true,
                        chkboxType: {
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
                    data:{roleId:$("#roleId").val(),name:$("#jrolename").val()},
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
            },
            // 表单
            myform: function() {
                // 表单验证
                $("#myform").validator({
                    fields: {
                        rolename: '角色名称: required'
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