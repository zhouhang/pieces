<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改收款账户-boss-上工好药</title>
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
                <form action="" id="myform">
                <div class="title title-btm">
                    <h3><i class="fa fa-chevron-right"></i>修改收款账户</h3>
                    <div class="extra">
                        <a  class="btn btn-gray" href="/bank/index">返回</a>
                        <button id="delete" type="button" class="btn btn-gray">删除</button>
                        <button type="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>收款账户信息</h3>
                    <div class="fa-form">
                        <input type="hidden" value="${payAccount.id }"  name="id" id="id">
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 开户行：
                            </div>
                            <div class="cnt">
                                <input type="text" placeholder="" id="bank" name="receiveBank" autocomplete="off" value="${payAccount.receiveBank}" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 开户人：
                            </div>
                            <div class="cnt">
                                <input type="text" placeholder="" id="name" name="receiveAccount" autocomplete="off" value="${payAccount.receiveAccount}" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 收款账号：
                            </div>
                            <div class="cnt">
                                <input type="text" placeholder="" id="bankNumber" name="receiveBankCard" autocomplete="off" value="${payAccount.receiveBankCard}" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i> 状态：
                            </div>
                            <div class="cnt">
                                <select name="status" id="state" class="wide">
                                    <option <#if payAccount.status == 1>selected="selected"</#if> value="1">激活</option>
                                    <option <#if payAccount.status == 0>selected="selected"</#if> value="0">禁用</option>
                                </select>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

    <script src="/js/jquery.form.js"></script>
    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="/js/common.js"></script>
    <script src="/js/layer/layer.js"></script>
    <script>
        var _global = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                    this.delete();
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
                                        //$(form)[0].reset();
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
                },
                delete: function() {
                    var iid = $("#id").val();
                    $('#delete').on('click', function() {
                        layer.confirm('确认删除该账户？', {icon: 3, title:'提示'}, function(index){
                            layer.close(index);
                            $.ajax({
                                url: "/bank/delete/" + iid,
                                type: "POST",
                                success: function(data){
                                    if(data.status == "y"){
                                        $.notify({
                                            type: 'success',
                                            title: data.info,
                                            text: '3秒后自动跳转到账户列表页',
                                            delay: 3e3,
                                            call: function() {
                                                setTimeout(function() {
                                                    location.href = '/bank/index';
                                                }, 3e3);
                                            }
                                        });
                                    }else{
                                        $.notify({
                                            type: 'error',
                                            title: data.info,
                                            delay: 3e3
                                        });
                                    }
                                }
                            });
                        });
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