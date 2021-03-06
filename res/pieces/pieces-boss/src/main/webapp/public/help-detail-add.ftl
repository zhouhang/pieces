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
                    <h3><i class="fa fa-chevron-right"></i>新增单页面</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="/cms/article/index?model=1">返回</a>
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
                                <input type="text" id="title" class="ipt" name="title" value="" autocomplete="off" placeholder="">
                                <input type="text" id="id" name="id" value="" style="display: none">
                                <input type="text" id="model" name="model" value="${model}" style="display: none">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>排序：
                            </div>
                            <div class="cnt">
                                <input type="text" id="sort" class="ipt ipt-price" name="sort" value="" autocomplete="off" placeholder="请输入数字，数字越大显示越靠前">
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
                                <textarea name="keyWord" id="keyWord" class="ipt ipt-mul"></textarea>
                                <span class="tips">关键字个数不超过5个，每个关键字不超过8个汉字</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea name="intro" id="intro" class="ipt ipt-mul"></textarea>
                                <span class="tips">描述控制在80个汉字之内，160个字符之间</span>
                            </div>
                        </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>

<!-- footer start -->
<#include "./inc/footer.ftl"/>
<script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>

<!-- 编辑器相关 -->
<link href="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/themes/default/css/umeditor.css')}" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/umeditor.config.js')}"></script>
<script type="text/javascript" charset="utf-8" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/umeditor.min.js')}"></script>
<script type="text/javascript" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/lang/zh-cn/zh-cn.js')}"></script>

<!-- footer end -->
<script>
    var roleAddPage = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
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
                                            title: '新增成功。',
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
        var um = UM.getEditor('content').setContent("");
    })
</script>
</body>
</html>