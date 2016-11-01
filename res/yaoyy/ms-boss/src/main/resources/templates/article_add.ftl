<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>文章新增-药优优</title>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>CMS管理</li>
            <li>文章新增</li>
        </ul>
    </div>

    <form action="" id="myform">
        <div class="box fa-form">
            <div class="item">
                <div class="txt"><i>*</i>标题：</div>
                <div class="cnt">
                    <input type="text" name="title" class="ipt" placeholder="标题" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">
                    <i>*</i>详细信息：
                </div>
                <div class="cnt cnt-mul" name="content" id="content"  style="width: 700px; height: 400px;">
                </div>
                <div id="detailsError" style="padding-top: 10px;" class="clear"></div>
            </div>
            <div class="ft">
                <button type="button" class="ubtn ubtn-blue" id="jsubmit">保存</button>
            </div>
        </div>
    </form>
</div>

<#include "./common/footer.ftl"/>
<script src="/assets/plugins/validator/jquery.validator.min.js"></script>

<!-- 编辑器相关 -->
<link href="/assets/plugins/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/assets/plugins/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/assets/plugins/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="/assets/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>
<script>
    var _global = {
        v: {
            deleteUrl: ''
        },
        fn: {
            init: function() {
                //实例化编辑器
                var um = UM.getEditor('content').setContent("");
                this.validator();
                this.submitEvent();
            },
            validator: function() {
                // 表单验证
                $("#myform").validator({
                    fields: {
                        title: '标题: required',
                        content: {
                            rule: "required",
                            target: "#detailsError"
                        }
                    }
                });
            },
            submitEvent: function () {
                $('#jsubmit').on('click', function () {
                    $('#myform').isValid(function (v) {
                        // 表单验证通过
                        if (v) {
                            var data = $("#myform").serializeObject();
                            // console.log(data);
                            $("#jsubmit").attr("disabled", "disabled");
                            $.post("/cms/save", data, function (data) {
                                $("#jsubmit").attr("disabled", "false");
                                if (data.status == 200) {
                                    $.notify({
                                        type: 'success',
                                        title: '保存成功',
                                        text: '3秒后自动跳转到文章列表页',
                                        delay: 3e3,
                                        call: function() {
                                            setTimeout(function() {
                                                location.href = '/cms/list';
                                            }, 3e3);
                                        }
                                    });
                                }
                            })
                        }
                    })
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