<!DOCTYPE html>
<html lang="en">
<head>
   <#include "./inc/meta.ftl"/>
    <title>询价-上工好药</title>
</head>

<body>

<#include "./inc/header.ftl"/>


<div class="main-body">
    <div class="wrap">

         <#if !user_session_biz??>
        <div class="fa-msg">
            <i class="fa fa-prompt"></i>
            <span>您还没有登录！登录后订货单的商品将保存到您的账号中。</span>
            <a class="btn-ghost" href="/user/login">立即登录</a>
        </div>
           </#if>


        <div class="enquiry" id="app">
            <div class="hd">询价单</div>

            <div class="list">
                <table>
                    <thead>
                    <tr>
                        <th width="200">商品名称</th>
                        <th width="160">片型</th>
                        <th width="500">规格等级</th>
                        <th width="200">原药产地</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><a href="#" class="c-blue">壁虎（天龙）</a></td>
                        <td>个</td>
                        <td>净</td>
                        <td>湖北</td>
                        <td><a href="javascript:;" class="del">删除</a></td>
                    </tr>
                    </tbody>
                </table>

                <div class="tf">
                    共 <em class="count" id="c_count">6</em> 件商品 <button type="button" class="btn btn-red" id="submit">询价</button>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "./inc/helper.ftl"/>
<#include "./inc/footer.ftl"/>

<!-- start 联系方式 -->
<div class="fa-form fa-form-layer" id="jcontact">
    <form action="">
        <div class="group">
            <div class="txt">
                <span>您的姓名：</span>
            </div>
            <div class="cnt">
                <input type="text" name="username" id="username" class="ipt" autocomplete="off" placeholder="">
                <span class="error"></span>
            </div>
        </div>

        <div class="group">
            <div class="txt">
                <span>手机号码：</span>
            </div>
            <div class="cnt">
                <input type="text" name="mobile" id="mobile" class="ipt ipt-short" autocomplete="off" placeholder="">
                <button type="button" class="btn btn-gray" id="send">获取验证码</button>
                <span class="error"></span>
            </div>
        </div>
        <div class="group">
            <div class="txt">
                <span>验证码：</span>
            </div>
            <div class="cnt">
                <input type="text" name="code" id="code" class="ipt" autocomplete="off" placeholder="">
                <span class="error"></span>
            </div>
        </div>

        <div class="button">
            <button type="submit" class="btn btn-red submit">保存</button>
            <button type="reset" class="btn btn-gray cancel">取消</button>
        </div>
    </form>
</div><!-- end 新增收货地址 -->

<script src="${urls.getForLookupPath('/js/jquery.min.js')}"></script>
<script src="${urls.getForLookupPath('/js/jquery.autocomplete.min.js')}"></script>
<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
<script>
    !(function($, window){

        var _global = {
            fn: {
                init: function() {
                    this.initCart();
                    this.bindEvent();
                },
                initCart: function() {
                    var that = this,
                            cart = shopcart.getCart();

                    $.ajax({
                        url: '/cart/list',
                        type:"POST",
                        data: {ids: cart.split('@').join(',')},
                        success: function(res) {
                            that.toHtml(res.data);
                        }
                    })
                },
                toHtml: function(data) {
                    var that = this,
                            model = [];

                    that.count = data.length;
                    console.log()

                    if (that.count > 0) {
                        $.each(data, function(i, item) {
                            model.push('<tr>');
                            model.push('<td><a href="' , item.url ,'" class="c-blue">', item.name , '</a></td>');
                            model.push('<td>', item.spec, '</td>');
                            model.push('<td>', item.level,'</td>');
                            model.push('<td>', item.originOf, '</td>');
                            model.push('<td><a href="javascript:;" data-id="', item.id, '" class="del">删除</a></td>');
                            model.push('</tr>');
                        })
                        $('.list').find('.count').html(data.length);
                        $('.list').find('tbody').html(model.join(''));
                    } else {
                        that.empty();   // 购物车为空
                    }
                },
                empty: function() {
                    var title = this.isLogin ? '订货单内暂时没有商品，去挑选需要的商品吧' : '订货单内暂时没有商品，登录后将显示您之前加入的商品',
                            model = [];

                    model.push('<div class="fa-pro-empty">');
                    model.push('<div class="fa fa-frown"></div>');
                    model.push('<div class="text">');
                    model.push('<h1 class="title">', title, '</h1>');
                    model.push('<dl><dd>');
                   <#if !user_session_biz??>
                    model.push('<a href="/user/login" class="btn btn-red">登录</a>');
                   </#if>
                    model.push('<a href="/commodity/index">挑选商品&gt;</a>');
                    model.push('</dd></dl></div></div>');
                    $('.main-body').html(model.join(''));
                },
                bindEvent: function() {
                    var that = this,
                            $contact = $('#jcontact');

                    // 删除商品
                    $('.list').on('click', '.del', function() {
                        var $tr = $(this).closest('tr'),
                                id = $(this).data('id');

                        layer.confirm('您确定要将该商品从购物清单中删除吗？', {icon: 3, title:'提示'}, function(index){
                            $tr.remove();
                            shopcart.delCart(id);
                            layer.close(index);
                            that.count --;
                            $("#c_count").html(that.count);
                            if (that.count < 1) {
                                that.empty();
                            }

                        });
                    })

                    // 询价
                    $('#submit').on('click', function() {

                       <#if user_session_biz??>
                            // 已登录
                           $.ajax({
                               url: '/cart/submit',
                               type:"POST",
                               data: {ids: cart.split('@').join(',')},
                               success: function(res) {
                                   window.location.href = '/center/enquiry/index';
                               }
                           })
                        <#else >
                            // 未登录
                            layer.open({
                                area: ['540px'],
                                type: 1,
                                moveType: 1,
                                content: $contact,
                                title: '联系方式'
                            });
                        </#if>


                    })

                    // 关闭弹层
                    $contact.on('click', '.cancel', function() {
                        layer.closeAll();
                    })

                    $contact.validator({
                        fields : {
                            username : '姓名: required; ',
                            mobile : '手机号码: required; mobile',
                            code : '验证码: required'
                        },
                        valid: function(form) {
                            var myfromValid = this;
                            if ( $(form).isValid() ) {
                                $.ajax({
                                    url: '/cart/submit',
                                    type:"POST",
                                    data: $(form).formSerialize()+"&ids="+shopcart.getCart().split('@').join(','),
                                    success: function(res) {
                                        window.location.href = '/center/enquiry/index';
                                    }
                                })

                            }
                        }
                    });
                    this.SMSCode();
                },
                // 短信验证码
                SMSCode: function() {
                    var $send = $('#send'),
                            $msg = $send.next(),
                            self = this;
                    wait = 0,
                            txt = ' 秒后重试';

                    var showMsg = function(msg) {
                        if (msg) {
                            $msg.html(msg).show();
                        } else {
                            $msg.hide();
                        }
                    }

                    var lock = function() {
                        $send.text(wait + txt).prop('disabled', true);
                        wait--;
                        if (wait === 0) {
                            $send.text("获取验证码").prop('disabled', false);
                        } else {
                            setTimeout(function() {
                                lock(wait);
                            }, 1e3);
                        }
                    }

                    var sendMSM = function() {
                        $.ajax({
                            url : '/code/enquiry',
                            dataType: 'json',
                            beforeSend: function() {
                                $send.text('发送中...').prop('disabled', true);
                            },
                            success: function(data) {
                                if (data.status === 'y') {
                                    lock();
                                    showMsg();
                                } else {
                                    wait = 0;
                                    $send.text('获取验证码').prop('disabled', false);
                                    showMsg('发送失败');
                                }
                            },
                            error: function() {
                                wait = 0;
                                $send.text('获取验证码').prop('disabled', false);
                            }
                        })
                    }
                    $send.prop('disabled', false).on('click', function() {
                        $('#mobile').isValid(function(){
                            if(wait === 0) {
                                wait = 60; // 60秒倒计时
                                sendMSM();
                            }
                        });
                    })
                }
            }
        }

        _global.fn.init();

    })(jQuery, window);
</script>
</body>
</html>