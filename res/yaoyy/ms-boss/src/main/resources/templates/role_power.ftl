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
                <input type="hidden" name="roleId" id="roleId" value="<#if role??>${role.id}</#if>">
                <div class="txt"><i>*</i>角色名称：</div>
                <div class="cnt">
                    <input type="text" value="<#if role??>${role.name}</#if>" name="rolename" id="jrolename" class="ipt" placeholder="角色名称" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">资源：</div>
                <div class="cnt">
                    <ul id="powerTree" class="ztree"></ul>
                </div>
            </div>
            <div class="ft">
                <button type="button" class="ubtn ubtn-blue" id="jsubmit">保存</button>
            </div>
        </div>

    </form>
</div>

<#include "./common/footer.ftl"/>

<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script src="assets/plugins/zTreeStyle/jquery.ztree.min.js"></script>
<script>
    var _global = {
        v: {
            rootTree:null
        },
        fn: {
            init: function() {
                this.power();
                this.myform();
                //全选
                $("#allCheck").click(function() {
                    _global.v.rootTree.checkAllNodes(this.checked)
                });

                //保存
                $("#jsubmit").click(function(){
                    _global.fn.save();
                })
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


                //加载所有资源
                $.ajax({
                    url: "/role/resources",
                    type: "POST",
                    data:{roleId:$("#roleId").val(),name:$("#jrolename").val()},
                    async:false,
                    success: function(result){
                        _global.v.rootTree =  $.fn.zTree.init($("#powerTree"), setting, result);
                    }
                });

                if(_global.v.rootTree.getCheckedNodes(false).length==0){
                    $("#allCheck").attr("checked","checked");
                }



            },
            save:function(){

                $('#myform').isValid(function (v) {
                    // 表单验证通过
                    if (v) {
                        var arrIds = [];
                        //获取所有选中的节点
                        var checkNodes = _global.v.rootTree.getCheckedNodes(true);
                        $.each(checkNodes,function(index){
                            arrIds.push(this.id)
                        })

                        var roleId = $("#roleId").val();

                        var roleName = $("#jrolename").val();

                        $.ajax({
                            url: "/role/resources/save",
                            type: "POST",
                            dataType:"json",
                            data:{roleId:roleId,resourcesIds:arrIds,roleName:roleName},
                            success: function(result){
                                $("#roleId").val(result.data)
                                if (result.status == "200") {
                                    $.notify({
                                        type: 'success',
                                        title: '保存成功',
                                        text: '权限保存成功!',
                                        delay: 3e3,
                                        call: function() {
                                            setTimeout(function() {
                                                location.href = '/role/index';
                                            }, 3e3);
                                        }
                                    });
                                    return false;
                                }
                            }

                        });


                    }})


            }, // 表单
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