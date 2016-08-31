<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增收款账户-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>收款账户信息</dt>
                    <dd>
                        <a class="curr" href="/bank/index">收款账户信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/bank/save" id="myform">
                <div class="title title-btm">
                    <h3><i class="fa fa-chevron-right"></i>新增收款账户</h3>
                    <div class="extra">
                        <a class="btn btn-gray" href="/bank/index">返回</a>
                        <button type="submit" class="btn btn-red" id="jSave">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>收款账户信息</h3>
                    <div class="fa-form">

                        <div class="group">
                            <div class="txt">
                                <i>*</i> 开户行：
                            </div>
                            <div class="cnt">
                                <input type="text" placeholder="" id="bank" name="receiveBank" autocomplete="off" value="" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 开户人：
                            </div>
                            <div class="cnt">
                                <input type="text" placeholder="" id="name" name="receiveAccount" autocomplete="off" value="" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 收款账号：
                            </div>
                            <div class="cnt">
                                <input type="text" placeholder="" id="bankNumber" name="receiveBankCard" autocomplete="off" value="" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 状态：
                            </div>
                            <div class="cnt">
                                <select name="status" id="state" class="wide">
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


    <#include "./inc/footer.ftl"/>

    <script src="js/jquery.min.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="/js/common.js"></script>
    <script>
        var _global = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                },
                formValidate: function() {
                    $('#myform').validator({
                        rules: {
                            receiveBankCard: [/^\d{12,19}$/, '银行卡号是12-19位数字']
                        },
                        fields: {
                            receiveBank: '开户行: required',
                            receiveAccount: '开户人: required, nickName',
                            receiveBankCard: '收款账号: required, bankNumber'
                        },
                        valid: function(form) {
                            if ( $(form).isValid() ) {
                                $.ajax({
                                    url: "/bank/save",
                                    data: $(form).formSerialize(),
                                    type: "POST",
                                    success: function(data){
                                        $(form)[0].reset();
                                        $.notify({
                                            type: 'success',
                                            title: data.info,
                                            delay: 3e3
                                        });
                                    }
                                });
                            }
                        }
                    });

                }
            }
        }
        $(function() {
            _global.fn.init();
        })
    </script>
</body>
</html>