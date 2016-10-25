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

    <form id="myform">
        <div class="box fa-form">
            <div class="hd">基本信息</div>
            <div class="item">
                <div class="txt"><i>*</i>角色名称：</div>
                <div class="cnt">
                    <input type="text" name="rolename" id="jrolename" class="ipt" placeholder="角色名称" autocomplete="off">
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


<script src="assets/js/jquery191.js"></script>
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