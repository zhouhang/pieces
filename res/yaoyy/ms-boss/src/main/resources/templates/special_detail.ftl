<!DOCTYPE html>
<html lang="en">
<head>
    <title>专场详情-boss</title>
    <#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>


<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>专场广告</li>
            <li>专场详情</li>
        </ul>
    </div>

    <form action="special/save" id="myform" method="post">
        <input type="hidden"  class="ipt" value="${special.id?default('')}" name="id" id="cId">
        <div class="box fa-form">
            <div class="item">
                <div class="txt"><i>*</i>标题：</div>
                <div class="cnt">
                    <input type="text" name="title" value="${special.title?default('')}" class="ipt" placeholder="标题" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>专场图片：</div>
                <div class="cnt cnt-mul">
                    <div class="up-img x3">
                        <#if special.pictuerUrl??>
                            <img src="${special.pictuerUrl?default('')}" /><i class="del"></i>
                        </#if>
                        <!-- <img src="images/blank.gif"><i class="del"></i> -->
                    </div>
                    <input type="hidden" value="${special.pictuerUrl?default('')}" name="pictuerUrl" id="pictuerUrl">
                    <span class="tips">图片尺寸：750 X 400</span>
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>添加商品：</div>
                <div class="cnt">
                    <div id="chooseGoods">
                      <#if commodities??>
                        <#list commodities as commodity>
                            <span>${commodity.name}  ${commodity.spec}<i data-id="${commodity.id}"></i></span>
                        </#list>
                      </#if>

                    </div>
                    <input type="text" name="search" id="searchGoods" class="ipt" placeholder="商品名称" autocomplete="off">
                    <input type="hidden" name="commodities" id="goodsName" value="${ids?default('')}">
                    <div class="cnt-table hide" id="goodsSuggestions">
                        <table>
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>规格</th>
                                <th>价格</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="txt">排序：</div>
                <div class="cnt">
                    <input type="text" value="${special.sort?default('')}" name="sort" class="ipt" placeholder="数字越大越靠前" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">上/下架：</div>
                <div class="cnt">
                    <select name="status" id="status" class="slt">
                        <option value="1"<#if special.status??&&special.status==1 >selected</#if>>上架</option>
                        <option value="0" <#if special.status??&&special.status==0 >selected</#if>>下架</option>
                    </select>
                </div>
            </div>
            <div class="ft">
                <button type="button" class="ubtn ubtn-blue" id="jsubmit">保存</button>
            </div>
        </div>
    </form>
</div>

<#include "./common/footer.ftl"/>

<script src="assets/js/jquery.autocomplete.js"></script>
<script src="assets/js/croppic.min.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script>
    var _global = {
        v: {
            searchComodityUrl:'commodity/search',
            saveUrl:'special/save'
        },
        fn: {
            init: function() {
                this.goodsImg();
                this.validator();
                this.searchGoods();
            },
            goodsImg: function() {
                var self = this,
                        $upImg = $('.up-img');

                // 删除图片
                $upImg.on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除商品图片？', {
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        $upImg.empty();
                        $('#pictuerUrl').val('');
                        layer.close(index);
                    });
                    return false;
                })

                // 点击图片看大图
                $upImg.on('click', 'img', function() {
                    _showImg(this.src);
                    return false;
                })

                // 图片裁剪弹层框
                $upImg.on('click', function() {
                    layer.open({
                        skin: 'layui-layer-molv',
                        area: ['810px'],
                        closeBtn: 1,
                        type: 1,
                        moveType: 1,
                        content: '<div class="img-upload-main"><div class="clip clip-x3" id="imgCrop"></div></div>',
                        title: '上传图片',
                        cancel: function() {
                            self.cropModal.destroy();
                        }
                    });

                    self.croppic();
                });
            },
            croppic: function() {
                var options = {
                    uploadUrl: '/gen/upload',
                    cropUrl: '/gen/clipping',
                    imgEyecandyOpacity:0.5,
                    loaderHtml:'<span class="loader">正在上传图片，请稍后...</span>',
                    onAfterImgCrop:function(response){
                        $('.up-img').html('<img src="' + response.url + '" /><i class="del" title="删除"></i>');
                        $('#pictuerUrl').val(response.url).trigger('validate');
                        // 关闭弹层
                        layer.closeAll();
                    },
                    onError:function(msg){
                        $.notify({
                            type: 'error',
                            title: msg.title,   // 不允许的文件类型
                            text: msg.message,     //'支持 jpg、jepg、png、gif等格式图片文件',
                            delay: 3e3
                        });
                    }
                }
                this.cropModal = new Croppic('imgCrop', options);
            },
            validator: function() {
                // 表单验证
                $("#myform").validator({
                    fields: {
                        title: '标题: required',
                        pictuerUrl: '专场图片: required',
                        commodities: '商品: required'
                    }
                });
            },
            // 查询商品
            searchGoods: function() {
                var self = this;
                vals = [${ids?default('')}],
                        timer = 0,
                        $goodsSuggestions = $('#goodsSuggestions');

                var ajaxSearch = function(val) {
                    timer && clearTimeout(timer);
                    timer = setTimeout(function() {
                        $.ajax({
                            url: _global.v.searchComodityUrl,
                            data: {name: val},
                            type:"POST",
                            success: function(response) {
                                var html = [''];
                                if (response && response.status == '200') {
                                    $.each(response.data, function(i, item) {
                                        var className = self.inArray(item.id, vals) ? 'checked' : 'items'
                                        html.push('<tr class="' + className + '" data-pname="' + (item.name + item.spec) + '"data-id="' + item.id + '"><td>' + item.name + '</td><td>' + item.spec + '</td><td>' + item.price + '</td></tr>');
                                    })
                                } else {
                                    html.push('<tr><td colspan="3">未查询到商品，请重新输入</td></tr>');
                                }
                                $goodsSuggestions.show().find('tbody').html(html.join(''));
                            },
                            error: function() {
                                $goodsSuggestions.show().find('tbody').html('<tr><td colspan="3">网络错误</td></tr>');
                            }
                        })
                    }, 300);
                }

                $('#searchGoods').on('input', function() {
                    var val = this.value;
                    if (val) {
                        ajaxSearch(val);
                    } else {
                        $goodsSuggestions.hide();
                    }
                })

                $('body').on('click', function() {
                    $goodsSuggestions.hide();
                })

                $("#jsubmit").on('click',function(){
                    $.ajax({
                        url: _global.v.saveUrl,
                        type: "POST",
                        data: $("#myform").serialize(),
                        success: function(data) {
                            if (data.status == "200") {
                                location.href="special/list"
                            }
                        }
                    })
                })

                // 添加商品
                $goodsSuggestions.on('click', '.items', function() {
                    var pname = $(this).data('pname'),
                            id = $(this).data('id');

                    if (self.inArray(id, vals)) {
                        $.notify({
                            type: 'error',
                            title: '商品添加失败',
                            text: '此商品已在添加列表',
                            delay: 3e3
                        });
                    } else {
                        vals.push(id);
                        $('#chooseGoods').show().append('<span>' + pname + '<i data-id="' + id + '"></i></span>');
                        $('#goodsName').val(vals.join(','));
                        $('#searchGoods').val(''); // 清空输入框
                        $goodsSuggestions.hide();
                    }
                }).on('click', 'table', function() {
                    return false;
                })

                // 删除商品
                $('#chooseGoods').on('click', 'i', function() {
                    var id = $(this).data('id');
                    $(this).parent().remove();
                    self.inArray(id, vals, true); // 删除当前id
                    $('#goodsName').val(vals.join(','));
                })
            },
            /**
             * [inArray 查询数组元素]
             * @param  {[string]} needle [查询值]
             * @param  {[Array]} array  [查询数组]
             * @param  {[bollen]} del    [选填，为true时删除该值]
             * @return {[bollen]}        [true or false]
             */
            inArray: function(needle, array, del) {
                if (typeof needle == 'string' || typeof needle == 'number') {
                    for (var i in array) {
                        if (array[i] == needle) {
                            del && array.splice(i, 1);
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>