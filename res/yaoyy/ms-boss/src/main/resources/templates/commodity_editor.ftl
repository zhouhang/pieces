<!DOCTYPE html>
<html lang="en">
<head>
    <title>药优优-商品修改</title>
<#include "./common/meta.ftl"/>
</head>
<body class="wrapper">
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>商品管理</li>
            <li>商品列表</li>
        </ul>
    </div>

    <form id="myform">
        <div class="box fa-form">
            <div class="hd">基本信息</div>
            <div class="item">
                <div class="txt"><i>*</i>品种：</div>
                <div class="cnt">
                    <input type="text" name="categoryName" id="jcatname" value="${commodity.categoryName}" class="ipt" placeholder="品种" autocomplete="off">
                    <input type="hidden" name="categoryId" value="${commodity.categoryId}">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>商品名称：</div>
                <div class="cnt">
                    <input type="text" name="name" class="ipt" placeholder="商品名称" value="${commodity.name}" autocomplete="off">
                    <input type="text" name="id" value="${commodity.id}" style="display: none">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>标题：</div>
                <div class="cnt">
                    <input type="text" name="title" class="ipt" value="${commodity.title}" placeholder="标题" autocomplete="off">
                </div>
            </div>
            <div class="item" id="junitPrice">
                <div class="txt"><i>*</i>价格：</div>
                <div class="cnt">
                    <div class="ipt-wrap">
                        <input type="text" name="price" class="ipt" id="jprice" value="${commodity.price}" placeholder="价格" autocomplete="off">
                        <span class="unit">元</span>
                    </div>
                    <label class="ml"><input type="checkbox"  name="mark" class="cbx" id="jsales">量大价优</label>
                </div>
            </div>
            <div class="item hide" id="jsalesPrice">
                <div class="txt"><i>*</i>公斤/价格</div>
            <#if commodity.gradient?exists && (commodity.gradient?size > 0)>
                <#list commodity.gradient as gradient>
                    <#if gradient_index == 0>
                        <div class="cnt">
                            <div class="ipt-wrap">
                                <input type="text" name="minKg1" class="ipt ipt-short" placeholder="1-99999"
                                       data-rule="required; range(1~99999)" autocomplete="off" value="${gradient.start}">
                            </div>
                            <em>-</em>
                            <div class="ipt-wrap">
                                <input type="text" name="maxKg1" class="ipt ipt-short" placeholder="1-99999"
                                       data-rule="required; range(1~99999)" autocomplete="off" value="${gradient.end}">
                            </div>
                            <div class="ipt-wrap">
                                <input type="text" name="unit1" class="ipt ipt-short" placeholder="计量单位"
                                       data-rule="required" autocomplete="off" value="${gradient.unit}">
                            </div>
                            <div class="ipt-wrap ml">
                                <input type="text" name="price1" class="ipt ipt-short" placeholder="1-9999"
                                       data-rule="required; range(1~9999)" autocomplete="off" value="${gradient.price}">
                                <span class="unit">元</span>
                            </div>
                            <button type="button" class="ubtn ubtn-blue ml" id="jaddNewPrice">添加一行</button>
                        </div>
                        <#else>
                            <div class="cnt">
                                <div class="ipt-wrap">
                                    <input type="text" name="minKg${gradient_index+2}" class="ipt ipt-short"
                                           data-rule="required; range(1~99999)" placeholder="1-99999" autocomplete="off" value="${gradient.start}">
                                </div>
                                <em>-</em>
                                <div class="ipt-wrap">
                                    <input type="text" name="maxKg${gradient_index+2}" class="ipt ipt-short"
                                           data-rule="required; range(1~99999)" placeholder="1-99999" autocomplete="off" value="${gradient.end}">
                                </div>
                                <em>公斤</em>
                                <div class="ipt-wrap ml">
                                    <input type="text" name="price${gradient_index+2}" class="ipt ipt-short" placeholder="1-9999"
                                           data-rule="required; range(1~9999)" autocomplete="off" value="${gradient.price}">
                                    <span class="unit">元</span>
                                </div>
                                <button type="button" class="ubtn ubtn-red ml">删除</button>
                            </div>

                    </#if>
                </#list>
                <#else>
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
                        <div class="ipt-wrap">
                            <input type="text" name="unit1" class="ipt ipt-short" placeholder="计量单位"
                                   data-rule="required" autocomplete="off">
                        </div>
                        <div class="ipt-wrap ml">
                            <input type="text" name="price1" class="ipt ipt-short" placeholder="1-9999"
                                   data-rule="required; range(1~9999)" autocomplete="off">
                            <span class="unit">元</span>
                        </div>
                        <button type="button" class="ubtn ubtn-blue ml" id="jaddNewPrice">添加一行</button>
                    </div>
            </#if>

            </div>
            <div class="item">
                <div class="txt"><i>*</i>规格等级：</div>
                <div class="cnt">
                    <input type="text" name="spec" class="ipt" value="${commodity.spec}" placeholder="规格等级" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>产地：</div>
                <div class="cnt">
                    <input type="text" name="origin" class="ipt" value="${commodity.origin}" placeholder="产地" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>采收年份：</div>
                <div class="cnt">
                    <input type="text" name="harYear" class="ipt" value="${commodity.harYear}" placeholder="采收年份" autocomplete="off">
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
                <div class="txt"><i>*</i>商品图片：</div>
                <div class="cnt cnt-mul">
                    <div class="up-img">
                        <img src="${commodity.pictureUrl}"><i class="del"></i>
                    </div>
                    <input type="hidden" value="${commodity.pictureUrl}" name="pictureUrl" id="pictureUrl">
                </div>
            </div>
            <div class="item">
                <div class="txt">
                    <i>*</i>详细信息：
                </div>
                <div class="cnt cnt-mul" name="detail" id="detail"  style="width: 700px; height: 400px;">
                </div>
                <div id="detailsError" style="padding-top: 10px;" class="clear"></div>
            </div>
            <div class="item">
                <div class="txt">排序：</div>
                <div class="cnt">
                    <input type="text" value="${commodity.sort}" name="sort" class="ipt" placeholder="数字越大越靠前" autocomplete="off">
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

<div id="umeditorContent" style="display: none;">
${commodity.detail}
</div>

<#include "./common/footer.ftl"/>

<script src="assets/js/croppic.min.js"></script>
<script src="assets/js/jquery.autocomplete.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>

<!-- 编辑器相关 -->
<link href="assets/plugins/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets/plugins/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets/plugins/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>

<script>
    var _global = {
        v: {
            attr_index:1
        },
        fn: {
            init: function () {
                this.catname();
                this.myform();
                this.submitEvent();
                this.goodsImg();
                this.parameter();
                this.initAttrAndPrice();
                // 初始化详细信息
                var um = UM.getEditor('detail');
                um.ready(function(){
                    um.setContent($("#umeditorContent").html());
                })
                $("#status").val(${commodity.status});

            },
            initAttrAndPrice: function () {
            <#if commodity.attribute?exists && commodity.attribute != "">
                var parameter = ${commodity.attribute};
                var html = "";
                $.each(parameter, function (k, v) {
                    html += '<tr> \n <td><div class="inner"><input name="attrN_'+_global.v.attr_index+'" type="text" class="ipt" value="' + k + '" data-rule="required;length[1~20]"></div></td> \n ' +
                            '<td><div class="inner"><input name="attrV_'+_global.v.attr_index+'" type="text" class="ipt" value="' + v + '" data-rule="required;length[1~100]"></div></td> \n ' +
                            '<td><span class="c-red">删除</span></td> \n </tr>';
                    _global.v.attr_index += 1;
                })
                var $table = $('#attribute').find('tbody');
                $table.html(html);
            </#if>
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
                        $jcatname.next().val(suggestion.data); // 保存品种id到隐藏文本域
                    }
                });
            },
            myform: function () {

                var $jsalesPrice = $('#jsalesPrice'),
                        $jprice = $('#jprice');

                // 量大价优
                $('#jsales').on('click', function () {
                    $jsalesPrice[this.checked ? 'show' : 'hide']()
                            .find('.ipt').removeClass('n-invalid').trigger("hidemsg");
                    $jprice.attr('class', 'ipt').prop('disabled', this.checked).val('').trigger("hidemsg");
                    $('#myform').data('validator').options.ignore = this.checked ? $jprice : $jsalesPrice.find('.ipt');
                }).prop('checked', false);

                // 添加价格
                var idx = $jsalesPrice.find('.cnt').length;
                $('#jaddNewPrice').on('click', function () {
                    $jsalesPrice.append('<div class="cnt"> \n <div class="ipt-wrap"><input type="text" name="minKg' + (++idx) + '" class="ipt ipt-short" data-rule="required; range(1~99999)" placeholder="1-99999" autocomplete="off"></div> \n <em>-</em> \n <div class="ipt-wrap"><input type="text" name="maxKg' + idx + '" class="ipt ipt-short" data-rule="required; range(1~99999)" placeholder="1-99999" autocomplete="off"></div> \n <em>公斤</em> \n <div class="ipt-wrap ml"> \n <input type="text" name="price' + idx + '" class="ipt ipt-short" placeholder="1-9999" data-rule="required; range(1~9999)" autocomplete="off"> \n <span class="unit">元</span> \n </div> \n <button type="button" class="ubtn ubtn-red ml">删除</button> \n </div>');
                })

                // 删除价格
                $jsalesPrice.on('click', '.ubtn-red', function () {
                    $(this).parent().remove();
                })

                // 表单验证
                $("#myform").validator({
                    ignore: $jsalesPrice.find('.ipt'),
                    fields: {
                        categoryId: '品种: required',
                        name: '商品名称: required; length(1~20)',
                        title: '标题: required; length(1~50)',
                        price: '价格: required; range(1~9999)',
                        spec: '规格等级: required',
                        origin: '产地: required',
                        harYear: '采收年份: required',
                        pictureUrl: '图片: required',
                        detail: {
                            rule: "required",
                            target: "#detailsError"
                        }
                    }
                });
                <#if commodity.mark == 1 >
                    $("#jsales").attr("checked",true).trigger("click");
                </#if>

            },
            // 提交事件
            submitEvent: function () {
                $('#jsubmit').on('click', function () {
                    $('#myform').isValid(function (v) {
                        // 表单验证通过
                        if (v) {
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
                                    if (k == 0) {
                                        gradient.push({
                                            start:$($(v).find("input")[0]).val(),
                                              end:$($(v).find("input")[1]).val(),
                                            price:$($(v).find("input")[3]).val(),
                                             unit:$($(v).find("input")[2]).val()
                                        });
                                    } else {
                                        gradient.push({
                                            start:$($(v).find("input")[0]).val(),
                                              end:$($(v).find("input")[1]).val(),
                                            price:$($(v).find("input")[2]).val()
                                        });
                                    }
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
                                        text: '3秒后自动跳转到商品详情页',
                                        delay: 3e3,
                                        call: function() {
                                            setTimeout(function() {
                                                location.href = '/commodity/list';
                                            }, 3e3);
                                        }
                                    });
                                }
                            })
                        }
                    });
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
                        $upImg.empty().next().val('');
                        layer.close(index);
                    });
                    return false;
                })
                // 点击图片无效
                $upImg.on('click', 'img', function () {
                    return false;
                })

                // 图片裁剪弹层框
                $upImg.on('click', function () {
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
                var options = {
                    uploadUrl: '/gen/upload',
                    cropUrl: '/gen/clipping',
                    outputUrlId: 'pictureUrl',
                    imgEyecandyOpacity: 0.5,
                    loaderHtml: '<span class="loader">正在上传图片，请稍后...</span>',
                    onAfterImgCrop: function (response) {
                        $('.up-img').html('<img src="' + response.url + '" /><i class="del" title="删除"></i>');
                        $('#imgUrl').trigger('validate');
                        // 关闭弹层
                        layer.closeAll();
                    },
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
                    onError: function (msg) {
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