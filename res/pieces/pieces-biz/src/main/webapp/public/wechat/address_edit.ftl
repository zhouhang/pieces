<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>收货地址-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <form action="" id="myform">
        <div class="ui-form">
            <div class="item">
                <input type="hidden" name="id" value="${address.id!}">
                <input type="text" class="ui-ipt" name="consignee" id="name" placeholder="收货人" value="${address.consignee!}" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="tel" class="ui-ipt" name="tel" id="mobile" placeholder="手机号" value="${address.tel!}" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="hidden" class="ipt" name="areaId" id="areaId" value="${(address.areaId?c)!}">
                <input type="text" class="ui-ipt" name="region" id="region" placeholder="省-市-区/县" value="${address.fullAdd!}" readonly="" autocomplete="off">
                <span class="error"></span>
                <i class="ico ico-arrow mid"></i>
            </div>
            <div class="item">
                <input type="text" class="ui-ipt" name="detail" id="address" placeholder="详细地址" value="${address.detail!}" autocomplete="off">
                <span class="error"></span>
            </div>
        </div>

        <div class="ui-button mt30">
            <button type="button" id="submit" class="ubtn ubtn-red">确认</button>
            <#if address?exists>
            <button type="button" id="delete" class="ubtn ubtn-white">删除地址</button>
            </#if>
        </div>
    </form>
</section><!-- /ui-content -->

<div class="pick-region">
    <div class="ui-header">
        <div class="title">地址选择</div>
        <div class="abs-l mid">
            <a href="javascript:;" class="fa fa-back" id="back"></a>
        </div>
        <div class="tab">
            <span>请选择</span>
            <span></span>
            <span></span>
        </div>
    </div>

    <div class="tabcont">
        <div class="cont">
            <ul></ul>
            <ul></ul>
            <ul></ul>
        </div>
    </div>
</div>

<#include "wechat/inc/footer_h5.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                this.region();
                this.bindEvent();
            },
            bindEvent: function() {
                var that = this,
                        enable = true;
                // 确认
                $('#submit').on('click', function() {
                    var pass = that.validator();
                    if (pass) {
                        $.ajax({
                            url: '/h5c/address/save',
                            type: 'POST',
                            data: $("#myform").serialize(),
                            success: function(result) {
                                if (result.status == "y") {
                                    var address = {};
                                    address.id = result.data;
                                    _YYY.localstorage.set('address_${omd5!}', JSON.stringify(address)); // 保存收货地址ID
                                    location.href ="/h5c/order/address?omd5=${omd5!}";
                                }
                            }
                        })
                    }
                    return false;
                })

                // 删除地址
                $('#delete').on('click', function() {
                    layer.open({
                        content: '确定要删除吗？',
                        btn: ['确定', '取消'],
                        yes: function(index) {
                            enable && $.ajax({
                                url: '/h5c/address/delete?id=${address.id!}',
                                type: 'POST',
                                success: function(result) {
                                    if (result.status == "y") {
                                        var address = _YYY.localstorage.get('address_${omd5!}');
                                        if (address) {
                                            address = JSON.parse(address);
                                            if (address.id == "${address.id!}" ) {
                                                _YYY.localstorage.remove('address_${omd5!}')
                                            }
                                        }
                                        location.href ="/h5c/order/address?omd5=${omd5!}";
                                    }
                                },
                                complete: function() {
                                    enable = true;
                                }
                            })
                            enable = false;
                        }
                    });
                })
            },
            validator: function() {
                return this.checkName() && this.checkMobile() && this.checkRegion() && this.checkAddress();
            },
            checkName: function() {
                var $el = $('#name'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请输入收货人！').show();

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
                    $el.next().html('请选择地区！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            checkAddress: function() {
                var $el = $('#address'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入详细地址！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            region: function() {
                var self = this,
                        $tab = $('.tab'),
                        $tabcont = $('.tabcont'),
                        $item = $tabcont.find('ul'),
                        $cont = $('.cont'),
                        choose = [];

                var tab = function(idx) {
                    var distance = idx * $tabcont.width();
                    $item.css('position','absolute').eq(idx).css('position','relative');
                    $cont.css({
                        '-webkit-transition':'all .3s ease',
                        'transition':'all .3s ease',
                        '-webkit-transform':'translate3d(-' + distance + 'px,0,0)',
                        'transform':'translate3d(-' + distance + 'px,0,0)'
                    });
                }

                // 选择地区
                $('#region').on('click', function() {
                    var name = this.innerHTML;
                    var tip=$tab.find('span').eq(0).html();
                    if($("#region").val()==""||tip=="请选择"){
                        $.ajax({
                            url: '/gen/area',
                            success: function(result) {
                                $tab.find('span').eq(0).html(name).next().html('请选择');
                                self.toHtml(result, $item.eq(0));
                            }
                        })
                    }

                    $('.pick-region').show();
                    $('.ui-form').hide();
                })

                // 返回
                $('#back').on('click', function() {
                    $('.pick-region').hide();
                    $('#myform').show();
                })

                // tab
                $tab.on('click', 'span', function() {
                    var idx = $(this).index();

                    $(this).html('请选择');
                    $tab.find('span').each(function(i) {
                        i > idx && $(this).empty();
                    })
                    tab(idx);
                })

                // 城市级联
                $tabcont.on('click', 'li', function() {
                    var idx = $(this).parent().index(),
                            name = this.innerHTML,
                            cid = $(this).data('id');
                    if(idx==2){
                        $('.pick-region').hide();
                        $('.ui-form').show();
                        var cities=$(this).data('name');
                        var areas=[];
                        $.each(cities.split(","),function(index,value){
                                    if(index!=0){
                                        areas.push(value);
                                    }
                                }
                        );
                        $("#region").val(areas.join(""));
                        $("#areaId").val(cid);
                    }
                    $.ajax({
                        url: '/gen/area',
                        data: {parentId: cid},
                        success: function(result) {
                            $tab.find('span').eq(idx).html(name).next().html('请选择');
                            self.toHtml(result, $item.eq(++idx));
                            tab(idx);
                        }
                    })
                })
            },
            toHtml: function(data, $wrap) {
                var model = [];
                $.each(data, function(i, item) {
                    model.push('<li data-id="', item.id ,'" data-name="',item.position,'">', item.areaname, '</li>');
                })
                $wrap.html(model.join(''));
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>