<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>新增分类-boss-饮片B2B</title>
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
                    <a class="curr" href="goods.html">基本信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="myform">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>修改商品“艾叶”</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">重置</button>
                        <button type="button" class="btn btn-gray">删除</button>
                        <button type="button" class="btn btn-gray">复制</button>
                        <button type="submit" class="btn btn-red">保存</button>
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
                                <input type="text" class="ipt" value="艾叶" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品名称：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="艾绒" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>切制规格：
                            </div>
                            <div class="cnt">
                                <select name="" id="" class="wide">
                                    <option value=""></option>
                                    <option value="" selected="">个</option>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>原药产地：
                            </div>
                            <div class="cnt">
                                <select name="" id="" class="wide">
                                    <option value=""></option>
                                    <option value="" selected="">安徽省</option>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>执行标准：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="《上海市中药饮片炮制规范》2008版" autocomplete="off"
                                       placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>外观描述：
                            </div>
                            <div class="cnt cnt-mul">
                                <textarea class="ipt ipt-mul" name="">除去叶柄等杂质、成绒、柔软</textarea>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>生产厂家：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="安徽省沪谯饮片厂" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>商品图片：
                            </div>
                            <div class="cnt cnt-mul">
                                <div class="goods-img">
                                    <img src="uploads/p0.jpg"><i class="del"></i>
                                </div>
                                <input type="hidden" value="uploads/p0.jpg" id="imgUrl">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>详细信息：
                            </div>
                            <div class="cnt cnt-mul">
                                <img width="700" height="400" src="uploads/editor.jpg" alt="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="" id="" class="wide">
                                    <option value=""></option>
                                    <option value="">激活</option>
                                    <option value="">禁用</option>
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
<script>

    var roleAddPage = {
        v: {},
        fn: {
            init: function () {
                this.formValidate();
                this.submitEvent();
                this.goodsImg();
            },
            formValidate: function () {
                $("#myform").validator({
                    fields: {
                        username: "required"
                    }
                });
            },
            // 提交事件
            submitEvent: function () {
                var self = this;
                $('button:submit').on('click', function () {
                    $.notify({
                        type: 'success',
                        title: '保存成功',
                        text: '3秒后自动跳转到商品详情页',
                        delay: 3e3,
                        call: function () {
                            setTimeout(function () {
                                location.href = 'goods.html';
                            }, 3e3);
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
                var options = {
                    uploadUrl: 'img_save_to_file.php',
                    cropUrl: 'img_crop_to_file.php',
                    outputUrlId: 'imgUrl',
                    imgEyecandyOpacity: 0.5,
                    loaderHtml: '<span class="loader">正在上传图片，请稍后...</span>',
                    onBeforeImgUpload: function () {
                        alert(9)
                    },
                    onAfterImgUpload: function () {
                        alert(0)
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
                    onError: function (errormessage) {
                    }
                }
                this.cropModal = new Croppic('imgCrop', options);
            }
        }
    }
    $(function () {
        roleAddPage.fn.init();
    })
</script>

</body>
</html>