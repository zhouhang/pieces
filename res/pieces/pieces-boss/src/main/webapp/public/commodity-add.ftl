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
                                <i>*</i>指导价：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" id="price" value="" name="guidePrice" autocomplete="off" placeholder="">
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
                                <th width="300">属性值</th>
                                <th width="80">排序</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="4"><span class="c-blue" id="addAttribute">+增加新属性</span></td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td><div class="inner"><input name="attrN_1" type="text" class="ipt" value="年限" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_1" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_2" type="text" class="ipt" value="采收时间" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_2" type="text" class="ipt" value="" name="attr" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_3" type="text" class="ipt" value="加工方式" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_3" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_4" type="text" class="ipt" value="含硫情况" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_4" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_5" type="text" class="ipt" value="含量" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_5" type="text" class="ipt" value="" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_6" type="text" class="ipt" value="生产厂家" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_6" type="text" class="ipt" value="亳州市沪谯药业有限公司" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
                                <td><span class="c-red">删除</span></td>
                            </tr>
                            <tr>
                                <td><div class="inner"><input name="attrN_7" type="text" class="ipt" value="袋装规格" data-rule="required;length[1~20]"></div></td>
                                <td><div class="inner"><input name="attrV_7" type="text" class="ipt" value="1公斤/袋" data-rule="required;length[1~100]"></div></td>
                                <td><i class="up"></i><i class="down"></i></td>
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

    var commodityAddPage = {
        v: {
            form: null,
            attr_index:9
        },
        fn: {
            init: function () {
                this.formValidate();
                this.submitEvent();
                this.cropImg();
                this.initAutocomplete();
                this.parameter();
                this.updateTable();
            },
            initAutocomplete: function () {
                var categoryId = $('#categoryId').val();
                var categoryIdV = $("#categoryIdV").val();

                $('#categoryId').autocomplete({
                    serviceUrl: '/breed/search',
                    paramName: 'name',
                    preventBadQueries: false,
                    deferRequestBy: 100,
                    showNoSuggestionNotice: true,
                    noSuggestionNotice: '没有此品种',
                    triggerSelectOnValidInput: false,
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
                $('#price').on('blur', function() {
                    var val = this.value;
                    if (!/^\d+\.?\d*$/.test(val)) {
                        val = Math.abs(parseFloat(val));
                    }
                    val = Math.abs(parseFloat(val));
                    this.value = isNaN(val) ? '' : val.toFixed(2);
                });

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
                        attr:"required;length[2~20]",
                        guidePrice:"required"
                    }
                });
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
        //实例化编辑器
        var um = UM.getEditor('details').setContent("");
    })
</script>
</body>
</html>