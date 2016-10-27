<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "./common/meta.ftl"/>
    <title>品种详情-药优优</title>
</head>
<body class="ui-body body-gray">
    <header class="ui-header">
        <div class="title">品种详情</div>
        <div class="abs-l mid">
            <a href="javascript:history.back();" class="fa fa-back"></a>
        </div>
    </header><!-- /ui-header -->

    <footer class="ui-footer">
        <nav class="ui-nav extra">
            <ul>
                <li>
                    <a href="/category/list">
                        <i class="fa fa-list"></i>
                        <span>品种列表</span>
                    </a>
                </li>
                <li>
                    <a href="center.html">
                        <i class="fa fa-cart"></i>
                        <span>采购单</span>
                    </a>
                </li>
                <li class="wide">
                    <a class="sample" href="sample/apply">免费寄样</a>
                </li>
                <li class="wide">
                    <a class="cart" href="center.html">加入采购单</a>
                </li>
            </ul>
        </nav>
    </footer>

    <section class="ui-content">
        <div class="ui-slide ui-slide-b" id="slide1">
            <ul>
                <li><a href="#"><img src="${commodityVo.pictureUrl}" alt=""></a></li>
            </ul>
        </div>

        <div class="pinfo">
            <h1 class="title">${commodityVo.title}</h1>
            <div class="norms">
                <#list commodityVoList as commodity>
                <a href="commodity/detail/${commodity.id?c}" <#if commodity.id==commodityVo.id>class="current"</#if>>${commodity.spec}</a>
                </#list>
            </div>
            <div class="price">
                <i>&yen;</i>
                <em>${commodityVo.price}</em>
                <b>${commodityVo.unit}</b>
                <#if commodityVo.mark!=0>
                    <span>量大价优</span>
                </#if>


                <div class="ui-quantity" id="quantity">
                    <button type="button" class="fa fa-reduce op"></button>
                    <input type="tel" class="ipt" value="1" autocomplete="off" data-price="{1-499:140,500-999:120,1000:100}">
                    <button type="button" class="fa fa-plus op"></button>
                    <b>公斤</b>
                </div>
            </div>
            <div class="sales">
                <#list commodityVo.gradient as gradient>
                <dl>
                    <dt>${gradient.start}-${gradient.end}公斤</dt>
                    <dd>${gradient.price}${gradient.unit}</dd>
                </dl>
                </#list>
            </div>
        </div>

        <div class="pdetail">
            <div class="tab">
                <ul>
                    <li class="current">产品详情</li>
                    <li>药材规格</li>
                    <li>质量保证</li>
                </ul>
            </div>
            <div class="tabcont">
                <div class="item">
                    ${commodityVo.detail}
                </div>
                <div class="item" id="attributeItem">
                </div>
                <div class="item"></div>
            </div>
        </div>


    </section><!-- /ui-content -->


    <#include "./common/footer.ftl"/>
    <script>

    var _global = {
        fn: {
            init: function() {
                this.slide();
                this.tab();
                this.quantity();
                this.initAttr();
            },
            slide: function() {
                var $slide = $('#slide1'),
                    $nav,
                    length = $slide.find('li').length;

                var nav = ['<div class="nav"><b>1</b>/', length, '</div>'];
                $slide.append(nav.join(''));
                $nav = $slide.find('.nav b');

                length > 2 && $slide.swipeSlide({
                    callback : function(i){
                        $nav.html(++i);
                    }
                });
            },
            // 详情内容
            tab: function() {
                var $tab = $('.tab'),
                    $items = $('.tabcont').find('.item');
                $tab.on('click', 'li', function() {
                    $(this).addClass('current').siblings().removeClass('current');
                    $items.eq($(this).index()).show().siblings().hide();
                })
            },
            // 加减数量
            quantity: function() {
                var $quantity = $('#quantity'), 
                    $ipt = $quantity.find('.ipt'),
                    num = $ipt.val() || 1;

                $quantity.on('click', '.fa-plus', function() {
                    $ipt.val(++num);
                })
                $quantity.on('click', '.fa-reduce', function() {
                    num > 1 && $ipt.val(--num);
                })
                // 只能输入数字
                $ipt.on('blur', function() {
                    var val = this.value;
                    if (val) {
                        val = (!isNaN(val = parseInt(val, 10)) && val) > 0 ? val : 1;
                        this.value = val;
                    }
                    num = this.value;
                })
            },
            initAttr: function () {
            <#if commodityVo.attribute?exists && commodityVo.attribute != "">
                var parameter = ${commodityVo.attribute};
                var html = "";
                $.each(parameter, function (k, v) {
                   html=html+"<dl><dt>"+k+"</dt> <dd>"+v+"</dd> </dl>"
                })
                $("#attributeItem").html(html);
            </#if>
            }
        }
    }

    $(function(){
        _global.fn.init();
    });

</script>
</body>
</html>