<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "wechat/inc/meta.ftl"/>
    <title>支付-上工好药</title>
</head>
<body class="bg-gray">

<section class="ui-content">
    <form action="" id="myform" method="post">
        <input type="hidden" name="orderId" value="${vo.id!}">
        <input type="hidden" name="userId" value="${vo.userId!}">
        <input type="hidden" name="agentId" value="${vo.agentId!}">
    <div class="floors-info">
        <p><span>应付金额：</span><em>&yen;<#if userType==1>${vo.amountsPayable!}<#else>${vo.deposit!}</#if></em></p>
        <p><span>订单号：</span>${vo.code!}</p>
        <p><span>剩余付款时间：</span>${vo.orderValidityPeriod!}</p>
    </div>

    <div class="floors">
        <div class="hd">收款账号</div>
        <#list payAccountList as payAccount>
        <div class="bd">
            <label>
                <input name="bank" type="radio" value="${payAccount.id!}" class="ico ico-rad mid cbx" checked="" />
                <em>${payAccount.receiveAccount!}</em>
                <span>${payAccount.receiveBank!}  ${payAccount.receiveBankCard!}</span>
            </label>
        </div>
        </#list>
    </div>

    <div class="floors">
        <div class="hd"><em class="fr" id="picNumber">0/3</em>支付凭证</div>
        <div class="ui-upload thumb pic">
            <span class="ui-file" id="upfile">
            </span>
        </div>
    </div>

    <div class="ui-button mt30">
        <button type="button" class="ubtn ubtn-red" id="submit">提交</button>
    </div>
    </form>

</section><!-- /ui-content -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<#include "wechat/inc/footer_h5.ftl"/>
<script src="/h5-static/js/lrz.bundle.js"></script>
<script>
!(function($) {

    wx.config({
        debug: false,
        appId: '${signature.appid!}',
        timestamp: ${signature.timestamp!},
        nonceStr: '${signature.noncestr!}',
        signature: '${signature.signature!}',
        jsApiList: [
            'chooseImage',
            'previewImage',
            'uploadImage'
        ]
    });

    var _global = {
        v:{
            img:[]
        },
        init: function() {
//            this.upfile();
            this.bindEvent();
//            gallery(true); // 开启图片预览
            this.camera();
        },
        bindEvent: function() {
            var enable = true;

            // 提交
            $('#submit').on('click', function() {
                var serverIds = [];
                // 先上传微信本地图片到服务器
                function upload(ids){
                    var localId = ids.pop();
                    wx.uploadImage({
                        localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
                        isShowProgressTips: 1, // 默认为1，显示进度提示
                        success: function (res) {

                            serverIds.push(res.serverId);
                            if(ids.length > 0){
                                upload(ids);
                            } else {
                                submitPay(serverIds);
                            }
                        },
                        fail: function (error) {
                            picPath = '';
                            localIds = '';
                            alert(Json.stringify(error));
                        }
                    });
                };

                function submitPay(serverIds) {
                    var bank = $('input:radio[name="bank"]:checked').val();
                    // 图片数组 和payAccountId
                    var data = $("#myform").serializeArray();
                    $.each(serverIds,function(k,v){
                        data.push({name:"img",value:v})
                    })
                    enable && $.ajax({
                        url: '/h5c/payRecord/create?payAccountId='+bank,
                        type: 'POST',
                        data: data,
                        success: function (res) {
                            if (res.status == "y") {
                                location.href = "/h5c/pay/success"
                            }
                            else {
                                popover(res.info);
                            }
                        },
                        complete: function () {
                            enable = true;
                        }
                    })
                    enable = false;
                }

                upload(_global.v.img);
            })
        },
        upfile: function() {
            var that = this,
                $upfile = $('#upfile'),
                $picNumber = $('#picNumber'),
                idx = 0,
                number = 0, // 已上传图片数量
                maxSize = 3, // 最大上传图片数量
                img = {};

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
                                img['img_' + (idx++)] = result.url;
                                model.push('<em class="ui-file">');
                                model.push('<img src="' , result.url , '" data-src="' , result.url , '" />');
                                model.push('<input type="hidden" name="img" value="',result.url,'">');
                                model.push('<i class="del" id="img_' , idx , '"></i>');
                                model.push('<em');
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
                delete img['img_' + this.id];
                $picNumber.html((-- number) + '/' + maxSize);
                $(this).parent().remove();
                reset();
                return false;
            })
        },
        camera: function() {
            var that = this,
                $upfile = $('#upfile'),
                $picNumber = $('#picNumber'),
                idx = 0,
                number = 0, // 已上传图片数量
                maxSize = 3; // 最大上传图片数量

            var showPic = function(localIds) {
                var model = [];
                $.each(localIds, function(i, url) {
                    model.push('<span class="ui-file">');
                    model.push('<img src="' , url , '" data-src="' , url , '" />');
                    model.push('<i class="del" data-url="', url, '" id="img_' , idx , '"></i>');
                    model.push('</span>');
                    _global.v.img.push(url);
                    number ++;
                })

                $upfile.before(model.join(''));
                $picNumber.html((number) + '/' + maxSize);
                number >= maxSize && $upfile.empty('').hide();
            }

            // 图片预览
            $('.thumb').on('click', 'img', function() {
                wx.previewImage({
                    current: this.src,
                    urls: _global.v.img
                });
            })

            $upfile.on('click', function() {
                wx.chooseImage({
                    count: (maxSize - number), // 最多9张
                    sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
                    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                    success: function (res) {
                        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                        showPic(localIds);
                    }
                });
            })

            // 删除图片
            $('.ui-upload').on('click', '.del', function() {
                var _url = $(this).data('url');
                $.each(_global.v.img, function(i, url) {
                    if (url === _url) {
                        _global.v.img.splice(i, 1);
                        return false; // break
                    }
                })
                $picNumber.html((-- number) + '/' + maxSize);
                $(this).parent().remove();
                return false;
            })
        }
    }

    _global.init();
})(window.Zepto || window.jQuery);
</script>
</body>
</html>