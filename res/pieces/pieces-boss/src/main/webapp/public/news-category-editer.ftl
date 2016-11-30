<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>编辑新闻分类-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>文章类信息</dt>
                <dd>
                    <a class="curr" href="cms/category/index?model=${category.model}">基本信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="form">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>修改文章分类</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="cms/category/index?model=${category.model}">返回</a>
                        <button type="button" id="delete" class="btn btn-gray">删除</button>
                        <button type="submit" id="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>基本信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>分类名称：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${category.name}" autocomplete="off" name="name" id="name"
                                       placeholder="">
                                <input type="text" value="${category.id}" name="id" id="id" style="display: none">
                                <input type="text" value="${category.model}" name="model" id="model" style="display: none">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>
<script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script src="js/common.js"></script>
<script src="/js/layer/layer.js"></script>

<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script>
    var roleAddPage = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
                $("#delete").click(function() {
                    layer.confirm('确认要删除该分类？', {
                        title: '删除分类',
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        $.post("cms/category/delete/${category.id}", function (data) {
                            if (data.status == "y") {
                                $.notify({
                                    type: 'success',
                                    title: '删除成功',
                                    text: '3秒后自动跳转到分类列表',
                                    delay: 3e3,
                                    call: function () {
                                        setTimeout(function () {
                                            location.href = 'cms/category/index?model=${category.model}';
                                        }, 3e3);
                                    }
                                });
                            } else {
                                $.notify({
                                    type: 'warn',
                                    title: '删除失败',
                                    text: data.info,
                                    delay: 3e3
                                });
                            }
                        }, "json")
                        layer.close(index);
                    });
                });
            },
            formValidate: function () {
                $('#form').validator({
                    fields: {
                        name: 'required',
                        sort: 'required'
                    },
                    valid: function (form) {
                        if ($(form).isValid()) {
                            $.ajax({
                                url: 'cms/category/save',
                                data: $(form).serialize(),
                                type: 'POST',
                                success: function (data) {
                                    $("#submit").attr("disabled", "disabled");
                                    if (data.status == "y") {
                                        $.notify({
                                            type: 'success',
                                            title: '保存成功。',
                                            delay: 3e3
                                        });
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
    }
    $(function () {
        roleAddPage.fn.init();
    })


</script>
</body>
</html>