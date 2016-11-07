<!DOCTYPE html>
<html lang="en">
<head>
    <title>品种列表-boss-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>商品管理</li>
            <li>品种列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter">
                <form action="" id="serarchForm">
                    <label>品种：</label><input type="text" name="variety" class="ipt" placeholder="请输入" id="searchName" value="${categoryVo.variety?default('')}">
                    <label>上/下架：</label>
                    <select class="ipt"  name="status" id="searchStatus" class="slt">
                        <option <#if (categoryVo.status??)> selected</#if>  value="">全部</option>
                        <option <#if categoryVo.status?exists && categoryVo.status==1> selected</#if> value="1">上架</option>
                        <option <#if categoryVo.status?exists && categoryVo.status==0> selected</#if>value="0">下架</option>
                    </select>
                    <button type="button" class="ubtn ubtn-blue" id="search">搜索</button>
                </form>
            </div>

            <div class="action-add">
                <button class="ubtn ubtn-blue" id="jaddNewCat">新建品种</button>
            </div>
            <!--
            <div class="action-length">
                <span>显示</span>
                <select name="" id="pageSize"class="slt">
                    <option <#if (categoryPage.pageSize==10)> selected</#if> value="10">10</option>
                    <option <#if (categoryPage.pageSize==25)> selected</#if> value="25">25</option>
                    <option <#if (categoryPage.pageSize==50)> selected</#if> value="50">50</option>
                    <option <#if (categoryPage.pageSize==100)> selected</#if> value="100">100</option>
                </select>
                <span>条结果</span>
            </div>
            -->
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>品种</th>
                    <th>标题</th>
                    <th>价格描述</th>
                    <th>排序</th>
                    <th>父类</th>
                    <th width="100">创建时间</th>
                    <th width="230" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list categoryPage.list as category>
                <tr <#if category.status==0>class="gray"</#if>>
                    <td><input type="checkbox"></td>
                    <td>${category.variety}</td>
                    <td>${category.title}</td>
                    <td>${category.priceDesc}元/${category.unit}</td>
                    <td>${category.sort}</td>
                    <td>${category.parentName}</td>
                    <td>${(category.createTime?datetime)!}  </td>
                    <td class="tc">
                        <a href="javascript:;" class="ubtn ubtn-blue jedit" categoryId="${category.id?c}">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel"  categoryId="${category.id?c}">删除</a>
                        <#if category.status==0>
                        <a href="javascript:;" class="ubtn ubtn-gray jputaway" categoryId="${category.id?c}" status="${category.status}">上架</a>
                        </#if>
                        <#if category.status==1>
                            <a href="javascript:;" class="ubtn ubtn-gray jputaway" categoryId="${category.id?c}" status="${category.status}">下架</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>

    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=categoryPage url="category/list" params=categoryParams/>
    </div>
</div>

<!-- 品种新增&编辑弹出框 -->
<form id="myform" class="hide" method = 'post'  action = ''>
    <input type="hidden"  class="ipt" value="" name="id" id="cId">
    <div class="fa-form fa-form-layer">
        <div class="item">
            <div class="txt"><i>*</i>父类品种：</div>
            <div class="cnt">
                <select name="pid" id="varieties" class="slt">
                    <#list varieties as variety>
                    <option value="${variety.id?c}">${variety.variety}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>品种：</div>
            <div class="cnt">
                <input type="text" name="variety" class="ipt" placeholder="品种" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>标题：</div>
            <div class="cnt">
                <input type="text" name="title" class="ipt" placeholder="标题" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>价格描述：</div>
            <div class="cnt">
                <input type="text" name="priceDesc" class="ipt" placeholder="价格描述" autocomplete="off">
                <span class="tips">元</span>
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>单位：</div>
            <div class="cnt">
                <select name="unit" class="slt">
                <#list codeVos as code>
                    <option value="${code.name}">${code.name}</option>
                </#list>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>排序：</div>
            <div class="cnt">
                <input type="text" name="sort" class="ipt" placeholder="只能输入数字" autocomplete="off" data-msg-integer="只能输入数字">
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>图片：</div>
            <div class="cnt cnt-mul">
                <span class="up-img x4" id="imgCrop"></span>
                <input type="hidden" value="" name="pictureUrl" id="pictureUrl">
                <span class="tips">图片尺寸：220 X 180</span>
            </div>
        </div>

        <div class="button">
            <button type="submit" class="ubtn ubtn-blue">保存</button>
            <button type="button" class="ubtn ubtn-gray">取消</button>
        </div>
    </div>
</form>

<!-- 上传图片文本域 -->
<div id="imgCropWrap"></div>
<#include "./common/footer.ftl"/>
<script src="assets/js/croppic.min.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script>
    var _global = {
        v: {
            deleteUrl: '/category/delete/',
            updateUrl:'/category/update/',
            getUrl:'/category/get/',
            saveUrl:'/category/save/',
            flag: false
        },
        fn: {
            init: function() {
                this.bindEvent();
                this.category();
                this.goodsImg();
            },
            bindEvent: function() {
                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length;
                var $search =$("#search");
                var $pageSize=$("#pageSize");

                // 删除
                $table.on('click', '.jdel', function() {
                    var url = _global.v.deleteUrl + $(this).attr('categoryId');
                    layer.confirm('确认删除此品种？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == "200") {
                                window.location.reload();
                            }
                            layer.close(index);
                        });

                    });
                    return false; // 阻止链接跳转
                })

                // 上架&下架
                $table.on('click', '.jputaway', function() {
                    var categoryId = $(this).attr('categoryId');
                    var status = $(this).attr("status");
                    var showMsg = '确认上架此品种？';
                    var setStatus = 1;
                    if (status == 1) {
                        showMsg = '确认下架此品种？';
                        setStatus = 0;
                    }
                    layer.confirm(showMsg, {icon: 3, title: '提示'}, function (index) {
                        $.ajax({
                            url: _global.v.updateUrl,
                            data: {"id": categoryId, "status": setStatus},
                            type: "POST",
                            success: function (data) {
                                if (data.status == "200") {
                                    window.location.reload();
                                }
                                layer.close(index);
                            }
                        });
                        return false; // 阻止链接跳转
                    })
                })

                // 全选
                $checkAll.on('click', function() {
                    var isChecked = this.checked;
                    $cbx.each(function() {
                        this.checked = isChecked;
                    })
                })
                // 单选
                $cbx.on('click', function() {
                    var _count = 0;
                    $cbx.each(function() {
                        _count += this.checked ? 1 : 0;
                    })
                    $checkAll.prop('checked', _count === count);
                })
                $search.on("click",function(){
                    var params = [];
                    var url="/category/list?";
                    $("#serarchForm .ipt").each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=url+params.join('&');
                })
            },
            // 品种新建 or 编辑
            category: function() {
                var $carForm = $('#myform'),
                    self = this;

                $carForm.validator({
                    fields: {
                        variety: '品种: required',
                        title: '标题: required',
                        priceDesc: '价格描述: required',
                        sort: '排序: required; integer',
                        pictureUrl: '图片: required'
                    },
                    valid: function (form) {
                        if ( $(form).isValid() ) {
                            $.ajax({
                                url: $carForm.attr("action"),
                                data: $(form).serialize(),
                                type: "POST",
                                success: function(data){
                                    if (data.status == "200") {
                                        window.location.reload();
                                    }
                                    layer.closeAll();
                                }
                            });
                        }
                    }
                });

                // 关闭弹层
                $carForm.on('click', '.ubtn-gray', function () {
                    layer.closeAll();
                })

                // 新建
                $('#jaddNewCat').on('click', function() {
                    $('#imgCrop').empty();
                    $('#pictureUrl').val('');
                    $carForm[0].reset();
                    $carForm.attr("action",_global.v.saveUrl);
                    self.upImg();
                    layer.open({
                        area: ['600px'],
                        type: 1,
                        moveType: 1,
                        content: $carForm,
                        title: '新建品种'
                    });
                })

                // 编辑
                $('.table').on('click', '.jedit', function() {
                    if (_global.v.flag) {
                        return false;
                    }
                    _global.v.flag = true;
                    $carForm[0].reset();
                    $carForm.attr("action",_global.v.updateUrl);
                    self.showinfo($(this).attr('categoryId'));
                    return false;
                })
            },
            showinfo: function(id) {
                var self = this,
                    $carForm = $('#myform');

                var showBox = function(data) {
                    $carForm.find('.ipt[name="variety"]').val(data.variety);
                    $carForm.find('.ipt[name="title"]').val(data.title);
                    $carForm.find('.ipt[name="priceDesc"]').val(data.priceDesc);
                    $carForm.find('.ipt[name="sort"]').val(data.sort);
                    $carForm.find('.ipt[name="id"]').val(data.id);
                    $("#varieties").val(data.pid);

                    // 如果有图片，填充图片
                    if (data.pictureUrl) {
                        $('#imgCrop').html('<img src="' + data.pictureUrl + '" title="点击图片看大图" /><i class="del" title="删除"></i>');
                        self.cropModal && self.cropModal.destroy();
                    } else {
                        $('#imgCrop').empty();
                        self.upImg();
                    }
                    $('#pictureUrl').val(data.pictureUrl);
                    layer.closeAll();
                    layer.open({
                        area: ['600px'],
                        type: 1,
                        moveType: 1,
                        content: $carForm,
                        title: '编辑品种'
                    });
                }

                // 加载数据
                var k = $.ajax({
                    url: _global.v.getUrl+id,
                    type: 'POST',
                    data: {id: id},
                    dataType: 'json',
                    success: function(data) {
                        data.status == 200 && showBox(data.data);
                    },
                    complete: function() {
                        _global.v.flag = false;
                    }
                })

                // loading
                layer.open({
                    area: ['200px'],
                    type: 1,
                    moveType: 1,
                    content: '<div class="layer-loading">加载中...</div>',
                    title: '编辑品种',
                    cancel: function() {
                        k.abort();
                    }
                });
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
                        layer.close(index);
                        $('#imgCrop').empty();
                        $('#pictureUrl').val('');
                        self.upImg();
                    });
                    return false;
                })
                // 点击图片看大图
                $myform.on('click', 'img', function() {
                    _showImg(this.src);
                    return false;
                })
            },
            upImg: function() {
                var self = this;
                var options = {
                    uploadUrl: '/gen/upload',
                    cropUrl: '/gen/clipping',
                    customUploadButtonId: 'imgCrop',
                    loaderHtml:'<span class="loader">正在上传图片，请稍后...</span>',
                    onAfterImgUpload: function(response){
                        self.cropModal && self.cropModal.destroy();
                        $('#imgCrop').show().html('<img src="' + response.url + '" title="点击图片看大图" /><i class="del" title="删除"></i>');
                        $('#pictureUrl').val(response.url).trigger('validate');
                    }
                }

                self.cropModal && self.cropModal.destroy();
                self.cropModal = new Croppic('imgCropWrap', options);
            }
        }
    }


    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>