<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>单页面信息-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>单页面信息</dt>
                <dd>
                    <a class="curr" href="/cms/article/index?model=1">单页面信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="form">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>修改单页面</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="/cms/article/index?model=1">返回</a>
                        <button type="button" id="delete" class="btn btn-gray">删除</button>
                        <button type="submit" id="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>单页面信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>页面分类：
                            </div>
                            <div class="cnt">
                                <select name="categoryId" id="categoryId" class="wide">
                                    <option value=""></option>
                                <#list categorys as category>
                                    <option value="${category.id}">${category.name}</option>
                                </#list>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>单页面标题：
                            </div>
                            <div class="cnt">
                                <input type="text" id="title" class="ipt" name="title" value="${article.title}" autocomplete="off" placeholder="">
                                <input type="text" id="id" name="id" value="${article.id}" style="display: none">
                                <input type="text" id="model" name="model" value="${article.model}" style="display: none">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>排序：
                            </div>
                            <div class="cnt">
                                <input type="text" id="sort" class="ipt ipt-price" name="sort" value="${article.sort}" autocomplete="off" placeholder="请输入数字，数字越大显示越靠前">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>详细信息：
                            </div>
                            <div class="cnt cnt-mul" name="content" id="content"
                                 style="width: 700px; height: 350px; clear: both;">
                            </div>
                            <div id="contentError" style="margin-bottom: 10px; padding-top: 10px;">
                            </div>
                        </div>


                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="status" id="status" class="wide">
                                    <option value=""></option>
                                    <option value="1">激活</option>
                                    <option value="0">禁用</option>
                                </select>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                关键字：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea name="keyWord" id="keyWord" class="ipt ipt-mul">${article.keyWord!}</textarea>
                                <span class="tips">关键字个数不超过5个，每个关键字不超过8个汉字</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea name="intro" id="intro" class="ipt ipt-mul">${article.intro!}</textarea>
                                <span class="tips">描述控制在80个汉字之内，160个字符之间</span>
                            </div>
                        </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>
<div id="umeditorContent" style="display: none;">
${article.content}
</div>
<!-- footer start -->
<#include "./inc/footer.ftl"/>
<script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>

<!-- 编辑器相关 -->
<link href="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/themes/default/css/umeditor.css')}" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/umeditor.config.js')}"></script>
<script type="text/javascript" charset="utf-8" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/umeditor.min.js')}"></script>
<script type="text/javascript" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/lang/zh-cn/zh-cn.js')}"></script>
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
                        categoryId: 'required',
                        title: 'required',
                        status: 'required',
                        content: {
                            rule:  "required",
                            target: "#contentError"
                        },
                        sort:'required;integer[+0]'
                    },
                    valid: function (form) {
                        if ($(form).isValid()) {
                            $.ajax({
                                url: 'cms/article/save',
                                data: $(form).serialize(),
                                type: 'POST',
                                success: function (data) {
                                    $("#submit").attr("disabled", "disabled");
                                    if (data.status == "y") {
                                        $.notify({
                                            type: 'success',
                                            title: '修改成功。',
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
                $("#delete").click(function() {
                    layer.confirm('确认要删除该单页面？', {icon: 3, title:'提示'}, function(index){
                        layer.close(index);
                        $.post("cms/article/delete/${article.id}", function (data) {
                            if (data.status == "y") {
                                $.notify({
                                    type: 'success',
                                    title: '删除成功',
                                    text: '3秒后自动跳转到单页面列表',
                                    delay: 3e3,
                                    call: function () {
                                        setTimeout(function () {
                                            location.href = 'cms/article/index?model=1';
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
        var um = UM.getEditor('content');
        um.ready(function(){
            um.setContent($("#umeditorContent").html());
        })
        $("#status").val('${article.status}');
        $("#categoryId").val('${article.categoryId}');
    })
</script>
</body>
</html>