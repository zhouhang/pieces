<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>新增商品-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>商品信息</dt>
                <dd>
                    <a class="curr" href="/commodity/index">基本信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <div class="info">
                <h3><i class="fa"></i>复制商品成功.</h3>
            </div>

            <form action="" id="form">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>${commodity.name}</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="/commodity/index">返回</a>
                        <button type="button" class="btn btn-gray" onclick="window.location.reload();">重置</button>
                        <button type="button" id="copy" class="btn btn-gray">复制</button>
                        <button type="button" id="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>基本信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>原药品种：
                            </div>
                            <div class="cnt">
                                <input type="text" id="categoryId" class="ipt" value="${commodity.categoryName}" autocomplete="off"
                                       placeholder="">
                                <input type="text" id="categoryIdV" name="categoryId" value="${commodity.categoryId}" style=" display: none;">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品名称：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="name" value="${commodity.name}" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品标题：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="title" value="${commodity.title}" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>规格等级：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="level" value="${commodity.level}" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>片型：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="spec" value="${commodity.spec}" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>指导价：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${commodity.guidePrice}" name="guidePrice" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>原药产地：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="originOf" value="${commodity.originOf}" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>执行标准：
                            </div>
                            <div class="cnt">
                                <input name="executiveStandard" id="executiveStandard" value="${commodity.executiveStandard}" class="ipt">
                                </input>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>性状描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea class="ipt ipt-mul" name="exterior">${commodity.exterior}</textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 商品属性 -->
                <div class="user-info">
                    <h3>商品属性</h3>
                    <div class="chart chart-form">
                        <table id="attribute">
                            <thead>
                                <tr>
                                    <th width="200"><div class="inner">属性名</div></th>
                                    <th width="300"><div class="inner">属性值</div></th>
                                    <th width="80">排序</th>
                                    <th class="tc">操作</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td colspan="4"><span class="c-blue" id="addAttribute">+增加新属性</span></td>
                                </tr>
                            </tfoot>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
                <!-- 商品属性 -->
                <input name="attribute" id="attribute"  style="display: none;">
                <!-- 商品图片与详情 -->
                <div class="user-info">
                    <h3>商品图片与详情</h3>
                    <div class="fa-form">

                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品图片：
                            </div>
                            <div class="cnt cnt-mul">
                                <div class="goods-img">
                                    <img src="${commodity.pictureUrl}"><i class="del"></i>
                                </div>
                                <input type="hidden" name="pictureUrl" value="${commodity.pictureUrl}" id="pictureUrl">
                            </div>
                        </div>

                        <div class="group" style="padding-bottom: 20px;">
                            <div class="txt">
                                <i>*</i>详细信息：
                            </div>
                            <div class="cnt cnt-mul" name="details" id="details"
                                 style="width: 700px; height: 350px; clear: both;">
                            </div>
                            <div id="detailsError" style="padding-top: 10px;" class="clear">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="status" id="status" class="wide">
                                    <option value="-1">请选择</option>
                                    <option value="1" selected="selected">激活</option>
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

<div id="umeditorContent" style="display: none;">
${commodity.details}
</div>
<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script src="${urls.getForLookupPath('/js/jquery.autocomplete.min.js')}"></script>
<script src="${urls.getForLookupPath('/js/croppic.min.js')}"></script>
<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
<script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>

<!-- 编辑器相关 -->
<link href="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/themes/default/css/umeditor.css')}" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/umeditor.config.js')}"></script>
<script type="text/javascript" charset="utf-8" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/umeditor.min.js')}"></script>
<script type="text/javascript" src="${urls.getForLookupPath('/js/umeditor1_2_2-utf8/lang/zh-cn/zh-cn.js')}"></script>

<script>
    var categoryId = $('#categoryId').val();
    var categoryIdV =   $("#categoryIdV").val();

    $('#categoryId').autocomplete({
        serviceUrl: '/breed/search',
        paramName:'name',
        preventBadQueries: false,
        deferRequestBy:100,
        showNoSuggestionNotice: true,
        noSuggestionNotice: '没有此品种',
        triggerSelectOnValidInput:false,
        transformResult: function(response) {
            response = JSON.parse(response);
            return {
                suggestions: $.map(response.data, function(dataItem) {
                    return { value: dataItem.name, data: dataItem.id };
                })
            };
        },
        onSelect: function (suggestion) {
            $("#categoryIdV").val(suggestion.data);
            categoryIdV = suggestion.data;
            categoryId =  suggestion.value;
        }
    });

    /**
     * 清空品种输入框的值.
     */
    $("#categoryId").blur(function(){
        if($("#categoryIdV").val() == "") {
            $("#categoryId").val("");
            return;
        }

        if ($("#categoryIdV").val() == categoryIdV && categoryId != $("#categoryId").val()) {
            $("#categoryId").val("");
            $("#categoryIdV").val("");
            return;
        }
    });

    var commodityAddPage = {
        v: {
            attr_index:0
        },
        fn: {
            init: function () {
                this.formValidate();
                this.submitEvent();
                this.cropImg();
                this.parameter();
                this.initParameter();
                this.updateTable();

                // 初始化详细信息
                var um = UM.getEditor('details');
                um.ready(function(){
                    um.setContent($("#umeditorContent").html());
                })


                $("#delete").click(function(){
                    layer.confirm('确认要删除该商品？', {icon: 3, title:'提示'}, function(index){
                        commodityAddPage.fn.deleteCommodity();
                        layer.close(index);
                    });
                })

                $("#copy").click(function(){
                    window.location.href = "/commodity/add/${commodity.id}";
                })
            },
            initParameter: function () {
            <#if commodity.attribute?exists && commodity.attribute != "">
                var parameter = ${commodity.attribute};
                var html = "";
                $.each(parameter, function (k, v) {
                    html += '<tr> \n <td><div class="inner"><input name="attrN_'+commodityAddPage.v.attr_index+'" type="text" class="ipt" value="' + k + '" data-rule="required;length[1~20]"></div></td> \n ' +
                            '<td><div class="inner"><input name="attrV_'+commodityAddPage.v.attr_index+'" type="text" class="ipt" value="' + v + '" data-rule="required;length[1~100]"></div></td> \n ' +
                            '\n <td><i class="up"></i><i class="down"></i></td>' +
                            '<td><span class="c-red">删除</span></td> \n </tr>';
                    commodityAddPage.v.attr_index += 1;
                })
                var $table = $('#attribute').find('tbody');
                $table.html(html);
            </#if>
            },
            formValidate: function () {
                $("#form").validator({
                    fields: {
                        categoryId: "required",
                        name: "required;length[1~20]",
                        title:"required;length[1~50]",
                        spec: "required;length[1~20]",
                        level: "required;length[1~50]",
                        originOf: "required;length[1~20]",
                        executiveStandard: "required;length[1~20]",
                        exterior: "required;length[1~200]",
                        pictureUrl: "required",
                        details: {
                            rule: "required",
                            target: "#detailsError"
                        },
                        status: "required",
                        guidePrice: "required"
                    }
                });
            },
            deleteCommodity: function(){
                $.post("/commodity/delete/${commodity.id}", function(data){
                    if (data.status == "y") {
                        $.notify({
                            type: 'success',
                            title: '删除成功',
                            text: '3秒后自动跳转到商品详情页',
                            delay: 3e3,
                            call: function () {
                                setTimeout(function () {
                                    location.href = '/commodity/index';
                                }, 3e3);
                            }
                        });
                    }
                })
            },
            // 提交事件
            submitEvent: function () {
                $('#submit').on('click', function () {
                    $('#form').isValid(function(v) {
                        if (v) {
                            var attr = {};
                            var trs = $("#attribute>tbody tr");
                            $.each(trs, function (k, v) {
                                attr[$($(v).find("input")[0]).val()] = $($(v).find("input")[1]).val();
                            })
                            var data = $("#form").serializeObject();
                            $.each(data, function(k,v){
                                if (k.match("attr")){
                                    delete data[k];
                                }
                            })
                            data.attribute = JSON.stringify(attr);
                            $.post("/commodity/save", data, function (data) {
                                if (data.status == "y") {
                                    $.notify({
                                        type: 'success',
                                        title: '保存成功',
                                        text: '商品保存成功',
                                        delay: 3e3,
                                        call: function () {
                                            $("#submit").attr("disabled", "disabled");
                                        }
                                    });
                                }
                            })
                        }
                    });
                    return false;
                })
            },
            // 商品图片
            cropImg: function () {
                var self = this;

                // 删除图片
                $('.goods-img').on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除图片？', function(index){
                        $self.parent().empty().next(':hidden').val('');
                        layer.close(index);
                    });
                    return false;
                })

                // 图片裁剪弹层框
                $('.goods-img').on('click', function() {
                    layer.open({
                        skin: 'layui-layer-molv',
                        area: ['600px'],
                        closeBtn: 1,
                        type: 1,
                        moveType: 1,
                        content: '<div class="img-upload-main"><div class="clip" id="imgCrop"></div></div>',
                        title: '上传图片',
                        cancel: function() {
                            self.cropModal.destroy();
                        }
                    });
                    self.croppic($(this));
                })
            },
            croppic: function($el) {
                var self = this;
                self.cropModal = new Croppic('imgCrop', {
                    hideButton: true,
                    uploadUrl: '/gen/upload',
                    cropUrl: '/gen/clipping',
                    onBeforeImgUpload: function() {
                        $('#imgCrop').find('.upimg-msg').remove();
                    },
                    onBeforeImgCrop: function() {
                        $('#imgCrop').append('<span class="upimg-msg">图片剪裁中...</span>');
                    },
                    onAfterImgCrop:function(response){ 
                        $el.html('<img src="' + response.url + '" /><i class="del" title="删除"></i>').next(':hidden').val(response.url);
                        layer.closeAll();
                    },
                    onError: function(msg) {
                        $('#imgCrop').append('<span class="upimg-msg">' + msg + '</span>');
                    }
                });
            },
            // 商品自定义参数
            parameter: function () {
                var $table = $('#attribute').find('tbody');
                var self = this;

                // 新增
                $('#addAttribute').on('click', function () {
                    var tr = '<tr> \n <td><div class="inner"><input name="attrN_'+commodityAddPage.v.attr_index+'" type="text" class="ipt" value="" data-rule="required;length[1~20]"></div></td> ' +
                            '\n <td><div class="inner"><input name="attrV_'+commodityAddPage.v.attr_index+'" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td> ' +
                            '\n <td><i class="up"></i><i class="down"></i></td>' +
                            '\n <td><span class="c-red">删除</span></td> \n </tr>';
                    commodityAddPage.v.attr_index += 1;
                    $table.append(tr);
                    self.updateTable();
                })

                // 删除
                $table.on('click', '.c-red', function () {
                    $(this).closest('tr').remove();
                })
                // 排序
                $table.on('click', '.up', function() {
                    var $tr = $(this).closest('tr');
                    $tr.prev().before($tr);
                    self.updateTable();
                })
                $table.on('click', '.down', function() {
                    var $tr = $(this).closest('tr');
                    $tr.next().after($tr);
                    self.updateTable();

                })
            },
            updateTable: function() {
                var $table = $('#attribute').find('tbody');
                $table.find('i').show();
                $table.find('tr:first').find('.up').hide();
                $table.find('tr:last').find('.down').hide();
            }
        }
    }
    $(function () {
        commodityAddPage.fn.init();
        // .getContent() 获取内容
    })
</script>
</body>
</html>