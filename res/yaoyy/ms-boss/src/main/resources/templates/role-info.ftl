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
            <li>新增角色</li>
        </ul>
    </div>

    <form action="/role/save" id="roleForm" method="post">
        <div class="box fa-form">
            <div class="hd">基本信息</div>
            <div class="item">
                <div class="txt"><i>*</i>角色名称：</div>
                <div class="cnt">
                    <input type="text" name="name" id="jname" class="ipt" placeholder="角色名称" autocomplete="off">
                    <input type="hidden" name="catNameId">
                </div>
            </div>
            <div class="ft">
                <button type="submit" class="ubtn ubtn-blue" id="jsubmit">保存</button>
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
        },
        fn: {
            init: function() {
                this.formValidate();


                $("#submit").click(function(){
                    _global.fn.save();
                })


            },
            formValidate: function() {
                $("#roleForm").validator({
                    fields: {
                        name: "required"
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

                    }
                })
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>