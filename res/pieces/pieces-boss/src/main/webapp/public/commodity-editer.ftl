<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>修改商品-boss-饮片B2B</title>
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
                    <h3><i class="fa fa-chevron-right"></i>修改商品“${commodity.name}”</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="button" class="btn btn-gray" onclick="window.location.reload();">重置</button>
                        <button type="button" id="delete" class="btn btn-gray">删除</button>
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
                                <input type="text" id="categoryId" class="ipt" value="${commodity.categoryName}" autocomplete="off" placeholder="">
                                <input type="text" id="categoryIdV" value="${commodity.categoryId}" name="categoryId" style="display: none;">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品名称：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="${commodity.name}" name="name" autocomplete="off" placeholder="">
                                <input type="text" class="ipt" value="${commodity.id}" name="id" style="display: none">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>切制规格：
                            </div>
                            <div class="cnt">
                                <select name="spec" id="spec" class="wide">
                                    <option>请选择</option>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>等级：
                            </div>
                            <div class="cnt">
                                <select name="level" id="level" class="wide">
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>原药产地：
                            </div>
                            <div class="cnt">
                                <select name="originOf" id="originOf" class="wide">
                                    <option>请选择</option>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>执行标准：
                            </div>
                            <div class="cnt">
                                <input value="${commodity.executiveStandard}" name="executiveStandard" id="executiveStandard" class="ipt">
                                </input>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>外观描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea class="ipt ipt-mul" name="exterior">${commodity.exterior}</textarea>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>生产厂家：
                            </div>
                            <div class="cnt">
                                <input type="text" name="factory" class="ipt" value="${commodity.factory}" autocomplete="off" placeholder="">
                            </div>
                        </div>

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
                            <div class="cnt cnt-mul" name="details" id="details" style="width: 700px; height: 350px; clear: both;">
                            </div>
                            <div id="detailClean" class="clear"></div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="status" id="status" class="wide">
                                    <option>请选择</option>
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
<!-- footer end -->
<script src="/js/jquery.autocomplete.js"></script>
<link type="text/css" rel="stylesheet" href="/js/autocomplete/style.css" />

<script src="/js/code.js"></script>
<script src="/js/common.js"></script>
<script src="/js/croppic.min.js"></script>
<script src="/js/layer/layer.js"></script>
<link type="text/css" rel="stylesheet" href="/js/layer/skin/layer.css" />
<link type="text/css" rel="stylesheet" href="/js/validator/jquery.validator.css" />
<script src="/js/validator/jquery.validator.min.js"></script>
<script src="/js/validator/local/zh-CN.js"></script>

<!-- 编辑器相关 -->
<link href="/js/umeditor1_2_2-utf8/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/umeditor1_2_2-utf8/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1_2_2-utf8/umeditor.min.js"></script>
<script type="text/javascript" src="/js/umeditor1_2_2-utf8/lang/zh-cn/zh-cn.js"></script>

<script>
    var categoryId = $('#categoryId').val();
    var categoryIdV =   $("#categoryIdV").val();

    $('#categoryId').autocomplete({
        serviceUrl: '/breed/search',
        paramName:'name',
        deferRequestBy:1,
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
            commodityAddPage.fn.initCode(suggestion.data);
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

        if ($("#categoryIdV").val() == categoryIdV && categoryId != $("#categoryIdV").val()) {
            $("#categoryId").val("");
            $("#categoryIdV").val("");
            return;
        }

    });

    var commodityAddPage = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
                this.submitEvent();
                this.goodsImg();

                // 初始化 切制规格,等级,原药产地,详细信息
                var um = UM.getEditor('details');
                um.setContent("${commodity.details}");

                $("#umeditor_textarea_details").appendTo("#detailClean");

                commodityAddPage.fn.initCode(${commodity.categoryId})
                setTimeout(function(){
                    $("#spec").val("${commodity.spec}");
                    $("#originOf").val("${commodity.originOf}");
                    $("#level").val("${commodity.level}");
                    $("#status").val("${commodity.status}");
                }, 1000);


                $("#delete").click(function(){
                    commodityAddPage.fn.deleteCommodity();
                })

                $("#copy").click(function(){
                    window.location.href = "/commodity/add/${commodity.id}";
                })


            },
            initCode: function(beedId) {
                $("#spec").code({beedId:beedId,typeId:'SPEC'});//"切制规格"
                $("#originOf").code({beedId:beedId,typeId:'ORIGIN'});//"原药产地"
                $("#level").code({beedId:beedId,typeId:'LEVEL'});//"等级"
            },
            formValidate: function () {
                $("#myform").validator({
                    fields: {
                        username: "required"
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
                var self = this;


                $('#submit').on('click', function () {
                            $('#form').isValid(function(v) {
                                console.log(v ? '表单验证通过' : '表单验证不通过');
                                if (v) {
                                    var data = $("#form").serializeObject();

                                    $.post("/commodity/save", data, function (data) {
                                        $.notify({
                                            type: 'success',
                                            title: '保存成功',
                                            text: '3秒后自动跳转到商品详情页',
                                            delay: 3e3,
                                            call: function () {
                                                setTimeout(function () {
                                                    location.href = '/commodity/index';
                                                }, 3e3);
                                            }
                                        });
                                    })
                                }
                            })
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
                var options = {
                    uploadUrl: '/commodity/upload',
                    cropUrl: '/commodity/clipping',
                    outputUrlId: 'pictureUrl',
                    imgEyecandyOpacity: 0.5, // Transparent image showing current img zoom
                    loaderHtml: '<span class="loader">正在上传图片，请稍后...</span>',
                    onBeforeImgUpload: function () {
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
                        console.log('onReset')
                    },
                    onError: function (msg) {
                        console.log(msg)
                        $.notify({
                            type: 'error',
                            title: msg.title,   // 不允许的文件类型
                            text: msg.message,     //'支持 jpg、jepg、png、gif等格式图片文件',
                            delay: 3e3
                        });
                    }
                }
                this.cropModal = new Croppic('imgCrop', options);
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