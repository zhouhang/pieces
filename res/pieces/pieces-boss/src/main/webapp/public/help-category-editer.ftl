<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>商品管理-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>单页面分类信息</dt>
                <dd>
                    <a class="curr" href="/cms/category/index?model=1">基本信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="form">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>修改单页面分类</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="/cms/category/index?model=1">返回</a>
                        <button type="button" id="delete" class="btn btn-gray">删除</button>
                        <button id="submit" type="submit" class="btn btn-red">保存</button>
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
                        <div class="group">
                            <div class="txt">
                                <i>*</i>排序：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${category.sort}" autocomplete="off" name="sort" id="sort"
                                       placeholder="请输入数字，数字越大显示越靠前">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                关键字：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea name="keyWord" id="keyWord" class="ipt ipt-mul">${category.keyWord!}</textarea>
                                <span class="tips">关键字个数不超过5个，每个关键字不超过8个汉字</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea name="intro" id="intro" class="ipt ipt-mul">${category.intro!}</textarea>
                                <span class="tips">描述控制在80个汉字之内，160个字符之间</span>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>
<script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>

<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script>
    var _global = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
                this.delete();
            },
            formValidate: function () {
                $('#form').validator({
                    fields: {
                        name: 'required',
                        sort: 'required;integer[+0]'
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
            },
            delete: function() {
                $("#delete").click(function(){
                    layer.confirm('确认要删除该分类？', {icon: 3, title:'提示'}, function(index){
                        layer.close(index);
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
                    });
                });
            }
        }
    }
    $(function () {
        _global.fn.init();
    })


</script>
</body>
</html>