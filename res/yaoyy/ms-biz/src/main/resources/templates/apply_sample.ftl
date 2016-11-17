<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>申请免费寄样-药优优</title>
</head>
<body class="ui-body-nofoot">
<header class="ui-header">
    <div class="title">申请免费寄样</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="ui-form">
        <form action="" id="sampleInfo">
            <div class="hd">
                您正在申请商品<strong>“${commodity.name} ${commodity.origin} ${commodity.spec}”</strong> 的寄样服务，在填写下面信息后我们会马上与您取得联系， 感谢您的支持！
                <em>（若您需要多件商品的寄样服务，在客服与您取得联系后在电话里沟通即可，不需要重复申请！）</em>
            </div>
            <div class="item">
                <input type="text" class="ipt" name="nickname" id="name" placeholder="姓名" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="tel" value="${(phone)!}" class="ipt" name="phone" id="mobile" placeholder="手机号" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="text" class="ipt" name="area" id="region" placeholder="地区（例如：安徽亳州）" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <button type="button" class="ubtn ubtn-primary" id="submit">提交</button>
            </div>
        </form>
    </div>
</section><!-- /ui-content -->


<#include  "./common/footer.ftl"/>
<script src="assets/js/layer.js"></script>
<script>

    var _global = {
        v: {
            applyUrl: '/apply/sample',
        },
        fn: {
            init: function() {
                this.validator();
                this.loadInfo();
            },
            loadInfo:function(){
                var userinfo = getAppyInfo();
                if(userinfo){
                   $("#name").val(userinfo.nickname || '');
                   $("#mobile").val(userinfo.phone || '');
                   $("#region").val(userinfo.region || '');
                }
            },
            validator: function() {
                var self = this;
                $('#submit').on('click', function() {
                    if (self.checkName()  <#if  !Session.user_session_biz?exists> && self.checkMobile()</#if> && self.checkRegion()) {
                        var userinfo = {
                            nickname: $("#name").val(),
                            phone: $("#mobile").val(),
                            region: $("#region").val()
                        }
                        saveAppyinfo(userinfo);
                        $.ajax({
                            url: _global.v.applyUrl,
                            type: "POST",
                            data:$.param({intention: ${commodity.id}}) + '&'+$("#sampleInfo").serialize(),
                            success: function (result) {
                                if(result.status=="200"){
                                    layer.open({
                                        className: 'layer-custom',
                                        content: '<div class="box"><div class="hd">您的寄样申请已提交成功！</div><div class="bd">我们会在60分钟之内与您取得联系。登录可以跟踪您的所有寄样申请。</div></div>'
                                        ,btn: ['历史寄样单', '返回']
                                        ,yes: function(index){
                                            location.href = '/sample/list' + (_YYY.is_winxin ? '?source=WECHAT' : '');
                                        },no: function(index) {
                                            window.history.back(); // 返回按钮事件
                                        },shadeClose: false
                                    });
                                }
                          }
                        });
                    }
                })
            },
            checkName: function() {
                var $el = $('#name'),
                    val = $el.val();
                if (!val) {
                    $el.next().html('请输入您的姓名！').show();
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
            checkRegion: function() {
                var $el = $('#region'),
                    val = $el.val();
                if (!val) {
                    $el.next().html('请输入您的所在区域（例如：安徽亳州）！').show();
                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            }
        }
    }

    $(function(){
        _global.fn.init();
    });
</script>
</body>
</html>