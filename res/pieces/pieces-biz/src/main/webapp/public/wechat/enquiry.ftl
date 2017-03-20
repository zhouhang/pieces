<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>拍照询价-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="ui-floor">
        <div class="ui-hd">
            <a href="javascript:;" class="help"><i class="ico ico-help"></i> 使用帮助</a>
            <i class="ico ico-list"></i>
            <span>您的物品清单</span>
            <em id="picNumber">0/9</em>
        </div>
        <div class="ui-upload thumb">
            <span class="ui-file" id="upfile">
                <input type="file" name="file" accept="image/gif,image/jpeg,image/png" class="file" />
            </span>
        </div>
    </div>
    <#if !(user?exists)>
    <div class="ui-floor">
        <div class="ui-hd">
            <i class="ico ico-mobile"></i>
            <span>您的联系方式</span>
        </div>
        <div class="ui-form">
            <div class="item">
                <input type="text" class="ui-ipt" id="name" placeholder="姓名" />
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="mobile" class="ui-ipt" id="mobile" placeholder="手机号" />
                <span class="error"></span>
                <button type="button" class="send mid" id="send">发送验证码</button>
            </div>
            <div class="item">
                <input type="text" class="ui-ipt" id="SMSCode" placeholder="验证码" />
                <span class="error"></span>
            </div>
        </div>
    </div>

    <div class="ui-notice">
        <b>*</b>联系方式仅在第一次询价时需要填写。
    </div>
    </#if>
    <div class="ui-button">
        <button type="button" class="ubtn ubtn-red" id="submit">提交</button>
    </div>
</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script src="/h5-static/js/lrz.bundle.js"></script>
<script>
    !(function($) {
        var _global = {
            v:{
                img:{}
            },
            init: function() {
                this.help();
                this.upfile();
                this.bindEvent();
                gallery(true); // 开启图片预览
            },
            help: function() {
                $('.help').on('click', function() {
                    layer.open({
                        className: 'layer-help',
                        content: '<div class="hd">使用帮助</div><div class="bd">用手机拍下您需要的品种清单，上传照片。30分钟内您就能收到报价信息。</div><div class="pic"><img width="250" height="330" src="h5-static/images/help.jpg" /></div>'
                    });
                })
            },
            bindEvent: function() {
                var that = this;
                $('#submit').on('click', function() {
                    if (that.check()) {
                        var enquiry = {};
                        if ($('body').find("#name").length >0) {
                            enquiry['contacts'] = $("#name").val();
                            enquiry['phone'] = $("#mobile").val();
                        }
                        enquiry.files = new Array();
                        $.each(_global.v.img, function(k,v){
                            enquiry.files.push({"attachmentUrl": v})
                        })

                        if( enquiry.files.length == 0) {
                            popover('请选择图片');
                            return false;
                        }
                        $.ajaxSetup({
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8'
                            }
                        });
                        $.ajax({
                            url: '/h5/enquiry?code='+$("#SMSCode").val(),
                            data: JSON.stringify(enquiry),
                            type: 'POST',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status=="y") {
                                    window.location.href = '/h5/enquiry/success';
                                } else {
                                    popover(result.info);
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试！');
                            }
                        })
                    }
                })

                $('.ui-form').on('focus blur', '.ui-ipt', function() {
                    $(this).next().hide();
                })
                that.SMSCodeEvent();
            },
            check: function() {
                if ($('body').find("#name").length >0) {
                    return this.checkName()
                            && this.checkMobile()
                            && this.checkSMSCode()
                } else {
                    return true;
                }
            },
            checkName: function() {
                var $el = $('#name'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请输入姓名！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            checkMobile: function() {
                var $el = $('#mobile'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入手机号码！').show();

                } else if (!_YYY.verify.isMobile(val)) {
                    $el.next().html('请输入有效的手机号！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            checkSMSCode: function() {
                var $el = $('#SMSCode'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请输入短信验证码！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            SMSCodeEvent: function() {
                var $send = $('#send'),
                        that = this;
                second = 0,
                        wait = 0,
                        txt = '秒后重试';

                var lock = function() {
                    wait && clearInterval(wait);
                    wait = setInterval(function() {
                        second--;
                        $send.text(second + txt).prop('disabled', true);
                        if (second === 0) {
                            clearInterval(wait);
                            $send.text("获取验证码").prop('disabled', false);
                        }
                    }, 1e3);
                }
                var sendMSM = function() {
                    popover('验证码发送中，请稍后...!');
                    $.ajax({
                        url: '/gen/code/enquiry',
                        dataType: 'json',
                        data: {phone:$("#mobile").val()},
                        success: function(data) {
                            if (data.status === 'y') {
                                $send.text(second + txt).prop('disabled', true);
                                lock();
                                popover(data.info);
                            } else {
                                popover(data.info);
                            }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            popover('网络连接超时，请您稍后重试!');
                        }
                    })
                }
                $send.prop('disabled', false).on('click', function() {
                    if(second === 0 && that.checkMobile()) {
                        second = 60; // 60秒倒计时
                        sendMSM();
                    }
                })
            },
            upfile: function() {
                var that = this,
                        $upfile = $('#upfile'),
                        $picNumber = $('#picNumber'),
                        idx = 0,
                        number = 0, // 已上传图片数量
                        maxSize = 9; // 最大上传图片数量

                $('body').append('<div id="upload" style="position:fixed;bottom:0;left:0;width:0;height:0;visibility:hidden;"></div>');

                var reset = function() {
                    $upfile.show().html('<input type="file" name="file" accept="image/gif,image/jpeg,image/png" class="file" />');
                }
                var showLader = function() {
                    $upfile.html('<i class="loader">上传中...</i>');
                }

                $upfile.on('change', '.file', function(ev) {
                    showLader();
                    lrz(this.files[0], {
                        width: 800
                    }).then(function (rst) {
                        var base64 = rst.base64;
                        base64 = base64.substr(base64.indexOf(',') + 1);
                        $.ajax({
                            url: '/h5/upload',
                            data: {
                                img: base64,
                                fileName: rst.origin.name
                            },
                            type: 'POST',
                            dataType: 'json',
                            success: function (result) {
                                if (number >= maxSize) {
                                    $upfile.empty('').hide();
                                } else if (result.status == 'success') {
                                    reset();
                                    var model = [];
                                    _global.v.img['img_' + (idx++)] = result.url;
                                    model.push('<span class="ui-file">');
                                    model.push('<img src="' , result.url , '" data-src="' , result.url , '" />');
                                    model.push('<i class="del" id="img_' , idx , '"></i>');
                                    model.push('<span');
                                    $upfile.before(model.join(''));
                                    $picNumber.html((++ number) + '/' + maxSize);
                                    number >= maxSize && $upfile.empty('').hide();
                                } else {
                                    popover('上传图片失败，请刷新页面重试！');
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试！');
                            }
                        })
                    }).catch(function (err) {
                        // 处理失败会执行
                        reset();
                    }).always(function () {
                        // 不管是成功失败，都会执行
                    });
                });

                // 删除图片
                $('.ui-upload').on('click', '.del', function() {
                    delete _global.v.img['img_' + this.id];
                    $picNumber.html((-- number) + '/' + maxSize);
                    $(this).parent().remove();
                    reset();
                    return false;
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>