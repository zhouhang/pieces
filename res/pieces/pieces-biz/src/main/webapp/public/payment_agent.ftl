<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>支付-上工好药</title>
    <link rel="stylesheet" href="css/order.css" />
</head>

<body>
    <#include "./inc/header-center.ftl"/>

    <div class="wrap">
        <div class="payment">
            <div class="title">
                <h3>支付</h3>
            </div>

            <div class="main">
                <dl>
                    <dt>订单号：</dt>
                    <dd>${orderForm.code!}</dd>

                    <dt>应付金额：</dt>
                    <dd>
                        <em>&yen;${orderForm.deposit!}</em>
                        <span>剩余付款时间：${orderForm.orderValidityPeriod}</span>
                    </dd>

                    <dt>支付方式：</dt>
                    <dd id="jpayType">
                        <label><input type="radio" name="type" value="0" checked>现款支付</label>
                    </dd>
                </dl>
                <dl>
                    <dt>平台收款帐号：</dt>
                    <dd>
                        <#list payAccountList as payAccount>
                            <p><label><input type="radio"  value="${payAccount.id!}" name="bank">${payAccount.receiveBank!} ${payAccount.receiveAccount!} ${payAccount.receiveBankCard!}</label></p>
                        </#list>
                    </dd>
                </dl>
                
                <div class="fa-form">
                    <form action="/center/pay/create" id="myform" method="post">

                    <div class="hd">填写付款信息</div>
                        <input type="hidden" name="userId" value="${orderForm.userId!}">
                        <input type="hidden" name="orderId" value="${orderForm.id!}">
                        <input type="hidden" name="agentId" value="${orderForm.agentId}">
                        <input type="hidden" name="token" value="${token!}">
                        <div class="group">
                            <div class="txt">支付凭证：</div>
                            <div class="cnt">
                                <span class="up-img" id="imgCrop"></span>
                                <em class="tips">请上传银行开具的打款凭证照片。</em>
                            </div>
                        </div>
                        <div class="group">
                            <button  id="cashSubmit" type="button" class="btn btn-red">确认</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    
    <div id="imgCropWrap"></div>

    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/validator/jquery.validator.js?local=zh-CN"></script>
    <script src="js/croppic.min.js"></script>
    <script src="/js/jquery.form.js"></script>

    <script>
    	var _global = {
    		v: {
    		},
    		fn: {
    			init: function() {
                    this.goodsImg();
                    $("#cashSubmit").click(function(){
                        var bank=$('input:radio[name="bank"]:checked').val();
                        if(!bank){
                            $.notify({
                                type: 'error',
                                title: '',
                                text: '请选择平台收款账号!',
                                delay: 3e3
                            });
                            return false;
                        }

                        $("#myform").ajaxSubmit({
                            data:{'payAccountId':bank},
                            beforeSend: function() {
                                if(!$("#myform").isValid()){
                                    return false;
                                }
                                var imgLength =  $('input[name="img"]').length;
                                if(imgLength<=1){
                                    $.notify({
                                        type: 'error',
                                        title: '',
                                        text: '请至少上传一张支付凭证!',
                                        delay: 3e3
                                    });
                                    return false;
                                }
                            },
                            success: function(result) {
                                if(result.status=="y"){
                                    location.href="/center/pay/success?state=payment"
                                }else{
                                    $.notify({
                                        type: 'error',
                                        title: '错误信息',   // 不允许的文件类型
                                        text: result.info,     //'支持 jpg、jepg、png、gif等格式图片文件',
                                        delay: 3e3,
                                        call:function(){
                                            setTimeout(function () {
                                                location.href = '/center/order/list';
                                            }, 3e3);
                                        }
                                    });
                                }
                            }
                        })
                    })

    			},
                goodsImg: function() {
                    var self = this,
                        $myform = $('#myform');

                    // 删除图片
                    $myform.on('click', '.del', function() {
                        var $self = $(this);
                        layer.confirm('确认删除图片？', {
                            btn: ['确认','取消'] //按钮
                        }, function(index){
                            $self.parent().remove();
                            layer.close(index);
                        });
                        return false;
                    })
                    // 点击图片看大图
                    $myform.on('click', 'img', function() {
                        var url = this.src;
                        window.open(url);
                        return false;
                    })
                    this.upImg();
                },
                upImg: function() {
                    var options = {
                        uploadUrl:'gen/img/upload',
                        customUploadButtonId: 'imgCrop',
                        onAfterImgUpload: function(response){
                            cropModal.destroy();
                            $('#imgCrop').before('<span class="up-img"><img src="' + response.url + '" title="点击图片看大图" /><i class="del" title="删除"></i><input type="hidden" name="img" value="' + response.url + '"></span>');
                            cropModal = new Croppic('imgCropWrap', options);
                        },
                        onError:function(msg){
                            $.notify({
                                type: 'error', 
                                text: msg.info,     //'支持 jpg、jepg、png、gif等格式图片文件',
                                delay: 3e3
                            });
                        }
                    }
                    var cropModal = new Croppic('imgCropWrap', options);
                }
    		}
    	}
    	$(function() {
    		_global.fn.init();
    	})
    </script>
</body>
</html>