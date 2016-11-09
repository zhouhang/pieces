<!DOCTYPE html>
<html lang="en">
<head>
    <title>药优优-商品详情</title>
<#include "./common/meta.ftl"/>
</head>
<body class="wrapper">
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>商品管理</li>
            <li>商品新增</li>
        </ul>
    </div>

    <form id="myform">
        <div class="box fa-form">
            <div class="hd">基本信息</div>
            <div class="item">
                <div class="txt"><i>*</i>品种：</div>
                <div class="cnt">
                    <input type="text" name="categoryName" id="jcatname" class="ipt" placeholder="品种" autocomplete="off">
                    <input type="hidden" name="categoryId">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>商品名称：</div>
                <div class="cnt">
                    <input type="text" name="name" class="ipt" placeholder="商品名称" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>标题：</div>
                <div class="cnt">
                    <input type="text" name="title" class="ipt" placeholder="标题" autocomplete="off">
                </div>
            </div>
            <div class="item" id="junitPrice">
                <div class="txt"><i>*</i>价格：</div>
                <div class="cnt">
                    <div class="ipt-wrap">
                        <input type="text" name="price" class="ipt" id="jprice" placeholder="价格" autocomplete="off">
                        <span class="unit">元</span>
                    </div>
                    <label class="ml"><input type="checkbox" name="mark" class="cbx" id="jsales">量大价优</label>
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>单位：</div>
                <div class="cnt">
                    <select id="unit" name="unit" class="slt">
                        <option value="1">吨</option>
                    </select>
                </div>
            </div>
            <div class="item hide" id="jsalesPrice">
                <div class="txt"><i>*</i>公斤/价格：</div>
                <div class="cnt">
                    <div class="ipt-wrap">
                        <input type="text" name="minKg1" class="ipt ipt-short" placeholder="1-99999"
                               data-rule="required; range(1~99999)" autocomplete="off">
                    </div>
                    <em>-</em>
                    <div class="ipt-wrap">
                        <input type="text" name="maxKg1" class="ipt ipt-short" placeholder="1-99999"
                               data-rule="required; range(1~99999)" autocomplete="off">
                    </div>
                    <em name="unitD"></em>
                    <div class="ipt-wrap ml">
                        <input type="text" name="price1" class="ipt ipt-short" placeholder="1-9999"
                               data-rule="required; range(1~9999)" autocomplete="off">
                        <span class="unit">元</span>
                    </div>
                    <button type="button" class="ubtn ubtn-blue ml" id="jaddNewPrice">添加一行</button>
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>规格等级：</div>
                <div class="cnt">
                    <input type="text" name="spec" class="ipt" placeholder="规格等级" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>产地：</div>
                <div class="cnt">
                    <input type="text" name="origin" class="ipt" placeholder="产地" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>采收年份：</div>
                <div class="cnt">
                    <input type="text" name="harYear" class="ipt" placeholder="采收年份" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">起购数量：</div>
                <div class="cnt">
                    <input type="text" name="minimumQuantity" value="0" class="ipt" placeholder="起购数量" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">商品标语：</div>
                <div class="cnt">
                    <input type="text" name="slogan" class="ipt" placeholder="商品标语" autocomplete="off">
                </div>
            </div>
        </div>

        <div class="box fa-form">
            <div class="hd">商品属性</div>
            <table id="attribute">
                <thead>
                <tr>
                    <th width="180">属性名</th>
                    <th>属性值</th>
                    <th width="80">操作</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td colspan="3"><a href="javascript:;" class="c-blue" id="addAttribute">+增加新属性</a></td>
                </tr>
                </tfoot>
                <tbody>
                <tr>
                    <td>
                        <div class="ipt-wrap"><input type="text" name="attrN_1" class="ipt" value="加工方式"
                                                     autocomplete="off"></div>
                    </td>
                    <td>
                        <div class="ipt-wrap"><input type="text" name="attrV_1" class="ipt" value="趁鲜加工"
                                                     autocomplete="off"></div>
                    </td>
                    <td>
                        <button class="ubtn ubtn-red">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="ipt-wrap"><input type="text" name="attrN_2" class="ipt" value="年限"
                                                     autocomplete="off"></div>
                    </td>
                    <td>
                        <div class="ipt-wrap"><input type="text" name="attrV_2" class="ipt" value="今年新货"
                                                     autocomplete="off"></div>
                    </td>
                    <td>
                        <button class="ubtn ubtn-red">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="box fa-form">
            <div class="hd">商品图片与详情</div>
            <div class="item">
                <div class="txt"><i>*</i>商品缩略图：</div>
                <div class="cnt cnt-mul">
                    <span class="up-img x4" id="jpic1"></span>
                    <input type="hidden" value="" name="thumbnailUrl" id="thumbnailUrl">
                    <span class="tips">图片尺寸：220 X 180</span>
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>商品图片：</div>
                <div class="cnt cnt-mul">
                    <span class="up-img x3" id="jpic2"></span>
                    <input type="hidden" value="" name="pictureUrl" id="pictureUrl">
                    <span class="tips">图片尺寸：750 X 400</span>
                </div>
            </div>
            <div class="item">
                <div class="txt">
                    <i>*</i>详细信息：
                </div>
                <div class="cnt cnt-mul">
                    <script id="detail" name="detail" type="text/plain"></script>
                    <span id="detailsError"></span>
                </div>
            </div>
            <div class="item">
                <div class="txt">排序：</div>
                <div class="cnt">
                    <input type="text" name="sort" class="ipt" value="0" placeholder="数字越大越靠前" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">上/下架：</div>
                <div class="cnt">
                    <select name="status" id="status" class="slt">
                        <option value="1">上架</option>
                        <option value="0">下架</option>
                    </select>
                </div>
            </div>
            <div class="ft">
                <button type="submit" class="ubtn ubtn-blue" id="jsubmit">保存</button>
            </div>
        </div>

    </form>
</div>
<#include "./common/footer.ftl"/>

<script src="assets/js/croppic.min.js"></script>
<script src="assets/js/jquery.autocomplete.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>

<!-- 编辑器相关 -->
<link href="assets/plugins/umeditor/themes/default/css/umeditor.css" rel="stylesheet">
<script src="assets/plugins/umeditor/umeditor.config.js"></script>
<script src="assets/plugins/umeditor/umeditor.min.js"></script>
<script src="assets/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>

<script>
    var _global = {
        v: {},
        fn: {
            init: function () {
                this.catname();
                this.myform();
                this.goodsImg();
                this.parameter();
            },
            // 查询品种
            catname: function () {
                var $jcatname = $('#jcatname');
                $jcatname.autocomplete({
                    serviceUrl: '/category/search',
                    paramName: 'variety',
                    deferRequestBy: 100,
                    showNoSuggestionNotice: true,
                    noSuggestionNotice: '没有该品种',
                    transformResult: function (response) {
                        response = JSON.parse(response);
                        if (response.status == 200) {
                            return {
                                suggestions: $.map(response.data, function (dataItem) {
                                    return {value: dataItem.variety, data: dataItem.id};
                                })
                            };
                        } else {
                            return {
                                suggestions: []
                            }
                        }
                    },
                    onSelect: function (suggestion) {
                        $jcatname.next().val(suggestion.data).trigger('hidemsg'); // 保存品种id到隐藏文本域
                    }
                });
            },
            myform: function () {
                var self = this,
                    $jsalesPrice = $('#jsalesPrice'),
                    $jprice = $('#jprice'),
                    idx = $jsalesPrice.find('.cnt').length,
                    _unit = $('#unit').find('option:selected').text();


                // 添加价格
                $('#jaddNewPrice').on('click', function () {
                    $jsalesPrice.append('<div class="cnt"> \n <div class="ipt-wrap">' +
                            '<input type="text" name="minKg' + (++idx) + '" class="ipt ipt-short" data-rule="required; range(1~99999)" placeholder="1-99999" autocomplete="off">' +
                            '</div> \n <em>-</em> \n <div class="ipt-wrap"><input type="text" name="maxKg' + idx + '" class="ipt ipt-short" data-rule="required; range(1~99999)" ' +
                            'placeholder="1-99999" autocomplete="off"></div> \n <em name="unitD">' + _unit + '</em> \n <div class="ipt-wrap ml"> \n ' +
                            '<input type="text" name="price' + idx + '" class="ipt ipt-short" placeholder="1-9999" data-rule="required; range(1~9999)" autocomplete="off"> \n' +
                            ' <span class="unit">元</span> \n </div> \n <button type="button" class="ubtn ubtn-red ml">删除</button> \n </div>');
                })
                
                // 单位
                $('#unit').code('UNIT');
                $('#unit').on('change', function () {
                    _unit = $(this).find('option:selected').text();
                    $('em[name=unitD]').html(_unit);
                })

                //实例化编辑器
                var um = UM.getEditor('detail', {
                    initialFrameWidth: 700,
                    initialFrameHeight: 400
                }).setContent('');

                // 删除价格
                $jsalesPrice.on('click', '.ubtn-red', function () {
                    $(this).parent().remove();
                })

                // 表单验证
                $("#myform").validator({
                    ignore: $('#jsalesPrice .ipt, .edui-image-searchTxt'),
                    fields: {
                        categoryId: '品种: required',
                        name: '商品名称: required; length(1~20)',
                        title: '标题: required; length(1~50)',
                        price: '价格: required; range(1~9999)',
                        spec: '规格等级: required',
                        origin: '产地: required',
                        harYear: '采收年份: required',
                        thumbnailUrl: '商品缩略图: required',
                        pictureUrl: '商品图片: required',
                        minimumQuantity:'起购数量: range(0~9999)',
                        detail: {
                            rule: '商品详情: required',
                            target: '#detailsError'
                        }
                    },
                    valid: function() {
                        self.submitForm();
                    },
                    invalid: function() {
                        console.log('error')
                    }
                });

                // 量大价优
                $('#jsales').on('click', function () {
                    $jsalesPrice[this.checked ? 'show' : 'hide']()
                            .find('.ipt').removeClass('n-invalid').val('').trigger("hidemsg");
                    $jprice.attr('class', 'ipt').prop('disabled', this.checked).val('').trigger("hidemsg");
                    $('#myform').data('validator').options.ignore = this.checked ? $('#jprice, .edui-image-searchTxt') : $('#jsalesPrice .ipt, .edui-image-searchTxt');
                    $("em[name=unitD]").html(_unit);
                }).prop('checked', false);
            },
            // 提交事件
            submitForm: function () {
                // 序列化属性值
                var attr = {};
                var trs = $("#attribute>tbody tr");
                $.each(trs, function (k, v) {
                    attr[$($(v).find("input")[0]).val()] = $($(v).find("input")[1]).val();
                })
                var data = $("#myform").serializeObject();
                $.each(data, function(k,v){
                    if (k.match("attr")){
                        delete data[k];
                    }
                })
                data.attribute = JSON.stringify(attr);

                if ($("input[name='mark']").is(':checked')){
                    var gradient = new Array();
                    // 量大价优按钮被选中
                    var divs = $("#jsalesPrice > .cnt ");
                    $.each(divs, function (k, v) {
                        gradient.push({
                            start: $($(v).find("input")[0]).val(),
                            end: $($(v).find("input")[1]).val(),
                            price: $($(v).find("input")[2]).val()
                        });
                    })
                    data.gradient = gradient;
                    data.mark = 1;
                } else {
                    data.mark = 0;
                }

                $("#jsubmit").attr("disabled", "disabled");
                $.ajaxSetup({
                    contentType : 'application/json'
                });
                $.post("/commodity/save", JSON.stringify(data), function (data) {
                    if (data.status == 200) {
                        $.notify({
                            type: 'success',
                            title: '保存成功',
                            text: '3秒后自动跳转到商品列表页',
                            delay: 3e3,
                            call: function() {
                                setTimeout(function() {
                                    location.href = '/commodity/list';
                                }, 3e3);
                            }
                        });
                    }
                })
            },
            // 商品图片
            goodsImg: function () {
                var self = this,
                    $upImg = $('.up-img');

                // 删除图片
                $upImg.on('click', '.del', function () {
                    var $self = $(this);
                    layer.confirm('确认删除商品图片？', {
                        btn: ['确认', '取消'] //按钮
                    }, function (index) {
                        $self.parent().empty().next('input:hidden').val('');
                        layer.close(index);
                    });
                    return false;
                })

                // 点击图片看大图
                $upImg.on('click', 'img', function () {
                    _showImg(this.src);
                    return false;
                })

                // 缩略图
                $('#jpic1').on('click', function() {
                    layer.open({
                        skin: 'layui-layer-molv',
                        area: ['500px'],
                        closeBtn: 1,
                        type: 1,
                        moveType: 1,
                        content: '<div class="img-upload-main"><div class="clip clip-x4" id="imgCrop"></div></div>',
                        title: '上传商品缩略图片',
                        cancel: function () {
                            self.cropModal.destroy();
                        }
                    });

                    self.croppic($(this));
                })

                // 商品图
                $('#jpic2').on('click', function() {
                    layer.open({
                        skin: 'layui-layer-molv',
                        area: ['810px'],
                        closeBtn: 1,
                        type: 1,
                        moveType: 1,
                        content: '<div class="img-upload-main"><div class="clip clip-x3" id="imgCrop"></div></div>',
                        title: '上传商品图片',
                        cancel: function () {
                            self.cropModal.destroy();
                        }
                    });

                    self.croppic($(this));
                })
            },
            croppic: function ($el) {
                var self = this;
                var options = {
                    uploadUrl: '/gen/upload',
                    cropUrl: '/gen/clipping',
                    imgEyecandyOpacity: 0.5,
                    loaderHtml: '<span class="loader">正在上传图片，请稍后...</span>',
                    onAfterImgCrop: function (response) {
                        $el.html('<img src="' + response.url + '" /><i class="del" title="删除"></i>');
                        $el.next('input:hidden').val(response.url).trigger('validate');
                        // 关闭弹层
                        layer.closeAll();
                    },
                    onBeforeImgUpload: function () {
                        // 检查图片大小
                        var size = $("#imgCrop_imgUploadField")[0].files[0].size;
                        if (size && size / (1024 * 1024) > 2) {
                            $.notify({
                                type: 'error',
                                title: "提示消息",
                                text: "上传的图片大小不能超过2M",
                                delay: 3e3
                            });
                            self.cropModal.reset();
                            throw new Error("图片超过2M无法上传!");
                        }
                    },
                    onError: function (msg) {
                        $.notify({
                            type: 'error',
                            title: msg.title,   // 不允许的文件类型
                            text: msg.message,     //'支持 jpg、jepg、png、gif等格式图片文件',
                            delay: 3e3
                        });
                    }
                }
                self.cropModal = new Croppic('imgCrop', options);
            },
            // 商品自定义参数
            parameter: function () {
                var $table = $('#attribute').find('tbody'),
                        idx = $table.find('tr').length;

                // 新增
                $('#addAttribute').on('click', function () {
                    var tr = '<tr> \n <td><div class="ipt-wrap"><input type="text" name="attrN_' + (++idx) + '" class="ipt" value="" autocomplete="off"></div></td> \n <td><div class="ipt-wrap"><input type="text" name="attrV_' + idx + '" class="ipt" value="" autocomplete="off"></div></td> \n <td><button class="ubtn ubtn-red">删除</button></td> \n </tr>';
                    $table.append(tr);
                })

                // 删除
                $table.on('click', '.ubtn-red', function () {
                    $(this).closest('tr').remove();
                })
            }
        }
    }

    $(function () {
        _global.fn.init();
    })
</script>

</body>
</html>