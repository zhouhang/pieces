<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>新增分类-boss-上工好药</title>
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
            <form action="" id="form">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>新增商品</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="/commodity/index">返回</a>
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
                                <input type="text" id="categoryId" class="ipt" value="" autocomplete="off"
                                       placeholder="">
                                <input type="text" id="categoryIdV" name="categoryId" style=" display: none;">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品名称：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" name="name" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品标题：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" name="title" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>规格等级：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" name="level" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>片型：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" name="spec" autocomplete="off" placeholder="">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>原药产地：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" name="originOf" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>执行标准：
                            </div>
                            <div class="cnt">
                                <input name="executiveStandard" id="executiveStandard" class="ipt">
                                </input>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>性状描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea class="ipt ipt-mul" name="exterior"></textarea>
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
                                <th width="200">属性名</th>
                                <th width="380">属性值</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="3"><span class="c-blue" id="addAttribute">+增加新属性</span></td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td><div class="inner"><input name="attrN_1" type="text" class="ipt" value="年限" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_1" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_2" type="text" class="ipt" value="采收时间" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_2" type="text" class="ipt" value="" name="attr" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_3" type="text" class="ipt" value="加工方式" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_3" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_4" type="text" class="ipt" value="含硫情况" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_4" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_5" type="text" class="ipt" value="含量" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_5" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_6" type="text" class="ipt" value="生产厂家" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_6" type="text" class="ipt" value="亳州市沪谯药业有限公司" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_7" type="text" class="ipt" value="袋装规格" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_7" type="text" class="ipt" value="1公斤/袋" data-rule="required;length[1~100]"></div></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            </tbody>
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
                                    <!-- <img src="images/blank.gif"><i class="del"></i> -->
                                </div>
                                <input type="hidden" name="pictureUrl" value="" id="pictureUrl">
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


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script src="/js/jquery.autocomplete.js"></script>
<link type="text/css" rel="stylesheet" href="/js/autocomplete/style.css"/>

<script src="/js/code.js"></script>
<script src="/js/common.js"></script>
<script src="/js/croppic.min.js"></script>
<script src="/js/layer/layer.js"></script>
<link type="text/css" rel="stylesheet" href="/js/layer/skin/layer.css"/>
<link type="text/css" rel="stylesheet" href="/js/validator/jquery.validator.css"/>
<script src="/js/validator/jquery.validator.min.js"></script>
<script src="/js/validator/local/zh-CN.js"></script>

<!-- 编辑器相关 -->
<link href="/js/umeditor1_2_2-utf8/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/umeditor1_2_2-utf8/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1_2_2-utf8/umeditor.min.js"></script>
<script type="text/javascript" src="/js/umeditor1_2_2-utf8/lang/zh-cn/zh-cn.js"></script>

<script>

    var commodityAddPage = {
        v: {
            form: null,
            attr_index:9
        },
        fn: {
            init: function () {
                this.formValidate();
                this.submitEvent();
                this.goodsImg();
                this.initAutocomplete();
                this.parameter();
            },
            initAutocomplete: function () {
                var categoryId = $('#categoryId').val();
                var categoryIdV = $("#categoryIdV").val();

                /**
                 * 初始化自动提示框.
                 */
                $('#categoryId').autocomplete({
                    serviceUrl: '/breed/search',
                    paramName: 'name',
                    preventBadQueries: false,
                    deferRequestBy: 0,
                    triggerSelectOnValidInput: false,
//                    autoSelectFirst:true,
                    transformResult: function (response) {
                        response = JSON.parse(response);
                        if (response.status == "y") {
                            return {
                                suggestions: $.map(response.data, function (dataItem) {
                                    return {value: dataItem.name, data: dataItem.id};
                                })
                            };
                        } else {
                            return {
                                suggestions: []
                            }
                        }
                    },
                    onSelect: function (suggestion) {
                        $("#categoryIdV").val(suggestion.data);
                    }
                });

                /**
                 * 清空品种输入框的值.
                 */
                $("#categoryId").blur(function () {
                    if ($("#categoryIdV").val() == "") {
                        $("#categoryId").val("");
                        return;
                    }

                    if ($("#categoryIdV").val() == categoryIdV && categoryId != $("#categoryId").val()) {
                        $("#categoryId").val("");
                        $("#categoryIdV").val("");
                        return;
                    }
                });
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
                        attr:"required;length[2~20]"
                    }
                });
            },
            // 提交事件
            submitEvent: function () {
                $('#submit').on('click', function () {
                    $('#form').isValid(function (v) {
                        //console.log(v ? '表单验证通过' : '表单验证不通过');

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
                                        text: '商品添加成功',
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
            goodsImg: function () {
                var self = this;
                // 删除图片
                $('.goods-img').on('click', '.del', function () {
                    var $self = $(this);
                    layer.confirm('确认删除商品图片？', {
                        btn: ['确认', '取消'] //按钮
                    }, function (index) {
                        $self.prev().remove();
                        $self.prev().val('');
                        $self.remove();
                        layer.close(index);
                    });
                    return false;
                })
                // 点击图片无效
                $('.goods-img').on('click', 'img', function () {
                    return false;
                })

                // 图片裁剪弹层框
                $('.goods-img').on('click', function () {
                    layer.open({
                        skin: 'layui-layer-molv',
                        area: ['600px'],
                        closeBtn: 1,
                        type: 1,
                        moveType: 1,
                        content: '<div class="img-upload-main"><div class="clip" id="imgCrop"></div></div>',
                        title: '上传图片',
                        cancel: function () {
                            self.cropModal.destroy();
                        }
                    });

                    self.croppic();
                });
            },
            croppic: function () {
                var self = this;
                var options = {
                    uploadUrl: '/gen/upload',
                    cropUrl: '/gen/clipping',
                    outputUrlId: 'pictureUrl',
                    imgEyecandyOpacity: 0.5, // Transparent image showing current img zoom
                    loaderHtml: '<span class="loader">正在上传图片，请稍后...</span>',
                    onBeforeImgUpload: function () {

                        // 检查图片大小
                        var size = $("#imgCrop_imgUploadField")[0].files[0].size;
                        if (size && size / (1024 * 1024) > 2) {
                            $.notify({
                                type: 'error',
                                title: "提示消息",   // 不允许的文件类型
                                text: "上传的图片大小不能超过2M.",     //'支持 jpg、jepg、png、gif等格式图片文件',
                                delay: 3e3
                            });
                            self.cropModal.reset();
                            throw new Error("图片超过2M无法上传!");
                        }
                    },
                    onAfterImgUpload: function () {
                    },
                    onImgDrag: function () {
                    },
                    onImgZoom: function () {
                    },
                    onBeforeImgCrop: function () {
                    },
                    onAfterImgCrop: function (response) {
                        $('.goods-img').html('<img src="' + response.url + '" /><i class="del" title="删除"></i>');
                        // 关闭弹层
                        layer.closeAll();
                    },
                    onReset: function () {
                        //console.log('onReset')
                    },
                    onError: function (msg) {
                        // console.log(msg)
                        $.notify({
                            type: 'error',
                            title: msg.title,   // 不允许的文件类型
                            text: msg.message,     //'支持 jpg、jepg、png、gif等格式图片文件',
                            delay: 3e3
                        });
                    }
                }
                this.cropModal = new Croppic('imgCrop', options);
            },
            // 商品自定义参数
            parameter: function () {
                var $table = $('#attribute').find('tbody');

                // 新增
                $('#addAttribute').on('click', function () {
                    var tr = '<tr> \n <td><div class="inner"><input name="attrN_'+commodityAddPage.v.attr_index+'" type="text" class="ipt" value="" data-rule="required;length[1~20]"></div></td> ' +
                            '\n <td><div class="inner"><input name="attrV_'+commodityAddPage.v.attr_index+'" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td> ' +
                            '\n <td><span class="c-red">删除</span></td> \n </tr>';
                    commodityAddPage.v.attr_index += 1;
                    $table.append(tr);
                })

                // 删除
                $table.on('click', '.c-red', function () {
                    $(this).closest('tr').remove();
                })
            }
        }
    }
    $(function () {
        commodityAddPage.fn.init();
        //实例化编辑器
        var um = UM.getEditor('details').setContent("");
    })
</script>
</body>
</html>