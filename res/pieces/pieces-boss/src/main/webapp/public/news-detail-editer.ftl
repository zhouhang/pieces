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
                <dt>文章信息</dt>
                <dd>
                    <a class="curr" href="cms/article/index?model=${article.model}">文章信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="form">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>修改文章</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="cms/article/index?model=${article.model}">返回</a>
                        <button type="submit" id="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>修改文章</h3>
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
                                <i>*</i>文章标题：
                            </div>
                            <div class="cnt">
                                <input type="text" id="title" class="ipt" name="title" value="${article.title}" autocomplete="off" placeholder="">
                                <input type="text" id="id" name="id" value="${article.id}" style="display: none">
                                <input type="text" id="model" name="model" value="${article.model}" style="display: none">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>发布时间:
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="publishedDate" id="start" value="<#if article.publishedDate?exists>${article.publishedDate?date}</#if>" autocomplete="off" placeholder="">
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
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>

<div id="umeditorContent" style="display: none;">
${article.content}
</div>
<!-- footer start -->
<#include "./inc/footer.ftl"/>
<script src="js/laydate/laydate.js"></script>

<link type="text/css" rel="stylesheet" href="/js/validator/jquery.validator.css"/>
<script src="/js/validator/jquery.validator.min.js"></script>
<script src="/js/validator/local/zh-CN.js"></script>

<!-- 编辑器相关 -->
<link href="/js/umeditor1_2_2-utf8/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/umeditor1_2_2-utf8/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1_2_2-utf8/umeditor.min.js"></script>
<script type="text/javascript" src="/js/umeditor1_2_2-utf8/lang/zh-cn/zh-cn.js"></script>

<!-- footer end -->
<script>
    var roleAddPage = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
                this.dateInit();
                $("#delete").click(function() {
                    $.post("cms/article/delete/${article.id}", function (data) {
                        if (data.status == "y") {
                            $.notify({
                                type: 'success',
                                title: '删除成功',
                                text: '3秒后自动跳转到文章列表',
                                delay: 3e3,
                                call: function () {
                                    setTimeout(function () {
                                        location.href = 'cms/article/index?model=${article.model}';
                                    }, 3e3);
                                }
                            });
                        }
                    }, "json")
                });
            },
            dateInit: function () {
                var start = {
                    elem: '#start',
                    format: 'YYYY-MM-DD',
                    min: laydate.now(), //设定最小日期为当前日期
                    max: '2099-06-16', //最大日期
                    istime: true,
                    choose: function(datas){
                        $('#start').removeClass('n-invalid').next().html('');
                    }
                };
                laydate(start);
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
                        publishedDate:'required'
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
            }
        }
    }
    $(function () {
        roleAddPage.fn.init();
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