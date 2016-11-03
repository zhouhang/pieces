<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>选货单-药优优</title>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">选货单</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="ui-notice">
        您的选货单如下，“选货登记”后我们会在30分钟内与您取得联系如您需要寄养服务可以直接与我们电话沟通，我们为您提供免费的寄养服务。联系电话：0273334474
    </div>
    <div class="pick-form">
        <form action="" id="pick_commodity">
            <div class="item">
                <div class="hd">
                    <em>茯苓</em>
                    <span>云南  2级货  过4号筛  直径0.8cm以内  20元</span>
                </div>
                <div class="price">
                    <i>&yen;</i> <b>200</b> 元
                </div>

                <div class="ui-quantity cale">
                    <button type="button" class="fa fa-reduce op"></button>
                    <input type="tel" class="ipt" value="1" autocomplete="off" data-price="{1-499:140,500-999:120,1000:100}">
                    <button type="button" class="fa fa-plus op"></button>
                </div>
                <div class="cale">公斤</div>

                <div class="del">
                    <button type="button" class="fa fa-remove"></button>
                </div>
            </div>
            <div class="item">
                <div class="hd">
                    <em>三七</em>
                    <span>云南  2级货  过4号筛  直径0.8cm以内  60元</span>
                </div>
                <div class="price">
                    <i>&yen;</i> <b>200</b> 元
                </div>

                <div class="ui-quantity cale">
                    <button type="button" class="fa fa-reduce op"></button>
                    <input type="tel" class="ipt" value="1" autocomplete="off" data-price="{1-499:140,500-999:120,1000:100}">
                    <button type="button" class="fa fa-plus op"></button>
                </div>
                <div class="cale">公斤</div>

                <div class="del">
                    <button type="button" class="fa fa-remove"></button>
                </div>
            </div>
            <div class="item">
                <div class="hd">
                    <em>麦冬</em>
                    <span>云南  2级货  过4号筛  直径0.8cm以内</span>
                </div>
                <div class="price">
                    <i>&yen;</i> <b>200</b> 元
                </div>

                <div class="ui-quantity cale">
                    <button type="button" class="fa fa-reduce op"></button>
                    <input type="tel" class="ipt" value="1" autocomplete="off" data-price="{1-499:140,500-999:120,1000:100}">
                    <button type="button" class="fa fa-plus op"></button>
                </div>
                <div class="cale">公斤</div>

                <div class="del">
                    <button type="button" class="fa fa-remove"></button>
                </div>
            </div>
            <div class="tel">
                <button type="button" class="fa fa-tel"></button>
            </div>
            <div class="button">
                <button type="button" class="ubtn ubtn-primary" id="submit">提交</button>
            </div>
        </form>
    </div>
</section><!-- /ui-content -->



<#include "./common/footer.ftl"/>
<script src="assets/js/layer.js"></script>
<script>

    var _global = {
        v: {
            commoditySearchUrl:"commodity/getDetail"
        },
        fn: {
            init: function() {
                this.iniCart();
                this.quantity();
                this.submit();
            },
            iniCart:function(){
                var self = this;
                var pick_commodity = localStorage.getItem(CARTNAME);
                if(pick_commodity){//如果购物车不为空。
                    var pick_obj = eval(pick_commodity);
                    var commodityIds=[];
                    for(var i=0;i<pick_obj.length;i++){
                       var pick=pick_obj[i];
                        commodityIds.push(pick.commodityId);
                    }
                    if(commodityIds.length!=0){
                        $.ajax({
                            url: _global.v.commoditySearchUrl,
                            data: JSON.stringify(commodityIds),
                            type: "POST",
                            contentType : 'application/json',
                            success: function(data) {
                                self.tohtml(data.data,pick_obj);
                            }
                        })
                    }
                    else{
                        $(".ui-notice").html("选货单还没有商品，去商品详情页面可以添加商品到选货单！");
                        $("#pick_commodity").empty();
                    }



                }
                else{
                    $(".ui-notice").html("选货单还没有商品，去商品详情页面可以添加商品到选货单！");
                    $("#pick_commodity").empty();
                }

            },
            tohtml:function (data,cart) {
                var html="";
                for(var i=0;i<data.length;i++){
                    var commodity=data[i];
                    html=html+"<div class='item'>"
                           +"<div class='hd'>"
                           + "<em>"+commodity.name+"</em>"
                           + "<span>"+commodity.origin+' '+commodity.spec+"</span>"
                           +  "</div> <div class='price'>"
                           + "<i>&yen;</i> <b>"+commodity.price+"</b> 元"
                           + "</div> <div class='ui-quantity cale'> <button type='button' class='fa fa-reduce op'></button>"
                           + "<input type='tel' class='ipt' value='1' cid='"+commodity.id+"'autocomplete='off' data-price='{1-499:140,500-999:120,1000:100}'>"
                            +"<button type='button' class='fa fa-plus op'></button> </div>"
                            +"<div class='cale'>"+commodity.unit+"</div>"
                            +"<div class='del'> <button type='button' class='fa fa-remove' cid='"+commodity.id+"'></button> </div> </div>"

                }
                html=html+'<div class="tel"> <button type="button" class="fa fa-tel"></button> </div> ' +
                        '<div class="button"> <button type="button"' +
                        ' class="ubtn ubtn-primary" id="submit">提交</button> </div>'
                $("#pick_commodity").html(html);
                for(var i=0;i<cart.length;i++){
                    var pick=cart[i];
                    $("#pick_commodity .ipt").each(function(){
                        if(pick.commodityId==$(this).attr("cid")){
                            $(this).val(pick.num);
                        }
                    })
                }


            },


            // 加减数量
            quantity: function() {
                var $quantity = $('.pick-form');
                //删除品种
                $quantity.on('click', '.fa-remove', function() {
                    deleteCommodity($(this).attr("cid"));
                    window.location.reload();
                })

                $quantity.on('click', '.fa-plus', function() {
                    var $ipt = $(this).prev(),
                            num = $ipt.val() || 1;
                    $ipt.val(++num);
                    var commodityId=$ipt.attr("cid");
                    updateCommodity(commodityId,num);
                })
                $quantity.on('click', '.fa-reduce', function() {
                    var $ipt = $(this).next(),
                            num = $ipt.val() || 1;

                    num > 1 && $ipt.val(--num);
                    var commodityId=$ipt.attr("cid");
                    updateCommodity(commodityId,num);
                })
                // 只能输入数字
                $quantity.on('blur', '.ipt', function() {
                    var val = this.value;
                    if (val) {
                        val = (!isNaN(val = parseInt(val, 10)) && val) > 0 ? val : 1;
                        this.value = val;
                    }
                    num = this.value;
                })
            },
            checkName: function() {
                var $el = $('#username'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入姓名').show();

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
            submit: function() {
                var flag = false,
                        self = this,
                        form = function() {
                            flag = true;
                            layer.open({
                                className: 'pick-form-layer ui-form'
                                ,content: '<div class="item"><input type="text" class="ipt" id="username" placeholder="姓名"><span class="error"></span></div>\n <div class="item"><input type="tel" class="ipt" id="mobile" placeholder="手机号"><span class="error"></span></div>'
                                ,shade: false
                            });
                        }

                $('#submit, .fa-tel').on('click', function() {
                    if (!flag) {
                        form();
                        return false;
                    }
                    return self.checkName() && self.checkMobile();
                })
            }
        }
    }

    $(function(){
        _global.fn.init();
    });

</script>
</body>
</html>