<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>单页面信息-boss-饮片B2B</title>
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
                    <a class="curr" href="goods.html">单页面信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="myform">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>新增单页面</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="submit" class="btn btn-red">保存</button>
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
                                <select name="category" id="" class="wide">
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
                                <input type="text" class="ipt" name="title" value="" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>详细信息：
                            </div>
                            <div class="cnt cnt-mul">
                                <div class="cnt cnt-mul" name="details" id="details" style="width: 700px; height: 350px; clear: both;">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="state" id="" class="wide">
                                    <option value=""></option>
                                    <option value="1">激活</option>
                                    <option value="0">禁用</option>
                                </select>
                            </div>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>

<!-- footer start -->
<#include "./inc/footer.ftl"/>
<link type="text/css" rel="stylesheet" href="/js/validator/jquery.validator.css"/>
<script src="/js/validator/jquery.validator.min.js"></script>
<script src="/js/validator/local/zh-CN.js"></script>
<!-- footer end -->
<script>
    var roleAddPage = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
            },
            formValidate: function () {
                $('#myform').validator({
                    fields: {
                        category: 'required',
                        title: 'required',
                        state: 'required'
                    },
                    valid: function (form) {
                        if ($(form).isValid()) {
                            $.ajax({
                                url: '',
                                data: $(form).serialize(),
                                type: 'POST',
                                success: function (data) {
                                    $.notify({
                                        type: 'success',
                                        title: '新增分类成功。',
                                        delay: 3e3
                                    });
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