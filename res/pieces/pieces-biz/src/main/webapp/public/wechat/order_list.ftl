<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "wechat/inc/meta.ftl"/>
    <title>订单-上工好药</title>
</head>
<body class="bg-gray">

<section class="ui-content">
    <div class="ui-tab">
        <ul class="cf">
            <li <#if status==1>class="curr"</#if> > <a href="/h5c/order/list?status=1">待付款</a></li>
            <li  <#if status==3>class="curr"</#if> ><a href="/h5c/order/list?status=3">待发货</a></li>
            <li  <#if status==4>class="curr"</#if> ><a href="/h5c/order/list?status=4">待收货</a></li>
        </ul>
    </div>
    <div class="ui-cnt" id="order_list">
        <!-- 
        <div class="ui-empty">
            <p>没有代付款的订单！</p>
            <div class="btm">
                <a href="enquiry.html" class="ubtn ubtn-red"><i class="ico ico-camera"></i> 拍照询价</a>
            </div>
        </div> 
        -->

        <ul class="olist">
        </ul>
    </div>


</section><!-- /ui-content -->

<#include "wechat/inc/footer_h5.ftl"/>
<script src="/h5-static/js/dragloader.min.js"></script>
<script>
!(function($) {
    var _global = {
        init: function() {
            this.pagesize = 1;
            this.loadmore();
            this.loading();
            this.changeStatus();
        },
        loading: function() {
            var that = this,
                    $ul = $('.olist');

            $.ajax({
                type: 'POST',
                url: '/h5c/order/list',
                dataType: 'json',
                data: {pageNum: that.pagesize,status:${status}},
                success: function(res){
                    var status=${status};
                    var data=res.data;
                    if (data.list.length==0) {
                        if(that.pagesize==1){
                            var tip="";
                            if(status==1){
                                tip="没有待付款的订单！";
                            }else if(status==3){
                                tip="没有待发货的订单！";
                            }else{
                                tip="没有待收货的订单！";
                            }
                            // $("#order_list").append('<div class="ui-empty"> <p>'+tip+'</p> <div class="btm"> <a href="/h5/enquiry" class="ubtn ubtn-red"><i class="ico ico-camera"></i>拍照询价</a></div></div>')
                        }
                        return;
                    }
                    $ul.append(that.toHtml(data.list));
                    _global.changeStatus();
                    if (data.pages==this.pagesize) {
                        $('body').append('<div class="nomore">没有更多了...</div>');
                        that.dragger.options.disableDragUp = true;
                    }
                    that.pagesize ++;
                },
                error: function(xhr, type){
                    popover('网络连接超时，请您稍后重试!');
                },
                complete: function() {
                    that.dragger.reset();
                }
            });
        },
        loadmore: function() {
            var that = this;

            that.dragger = new DragLoader(document.body, {
                disableDragDown: true,
                dragUpLoadFn: function() {
                    that.loading();
                }
            });
        },
        toHtml: function(data) {
            var model = [];
            $.each(data, function(i, item) {
                model.push('<li>');
                if(item.status==1){
                    model.push('<a href="/h5c/order/pay/', item.id, '" class="ubtn ubtn-red pay">去支付</a>');
                }
                if(item.status==4){
                    model.push('<button  orderId="',item.id,'" class="ubtn ubtn-red pay config">确认收货</button>')
                }

                model.push('<a href="/h5c/order/detail/', item.id, '">');
                model.push('<div class="hd">');
                model.push('<p><span>状态：</span><em>', item.statusText, '</em></p>');
                model.push('<p><span>总价：</span>&yen;', item.amountsPayable, '</p>');
                <#if userType==2>
                model.push('<p><span>保证金：</span>&yen;', item.deposit, '</p>');
                </#if>
                model.push('</div>');
                model.push('<div class="bd">');
                model.push('<span>',item.commodityOverview,'</span>');
                model.push('<em>共',item.commodityNum,'个商品</em>');
                model.push('</div>');
                model.push('</a>');
                model.push('</li>');
            })
            return model.join('');
         },
         changeStatus:function(){
             $('.config').on('click', function() {
                 var orderId=$(this).attr("orderId");
                 $.post("/center/order/status", {orderId: orderId, status: 5}, function (data) {
                     if (data.status == "y") {
                         window.location.reload();
                     }
                 }, "json")
             })



         }


    }

    _global.init();
})(window.Zepto || window.jQuery);
</script>
</body>
</html>