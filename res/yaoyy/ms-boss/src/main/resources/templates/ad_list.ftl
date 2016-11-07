<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>广告管理-药优优</title>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>专场广告</li>
            <li>广告列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter">
                <form id="filterForm" action="">
                    <label>类型：</label>
                    <select name="typeId" class="slt">
                        <option value="">全部</option>
                        <#list types as type>
                            <option value="${type.id}">${type.name}</option>
                        </#list>
                    </select>
                    <button class="ubtn ubtn-blue" id="search_btn">搜索</button>
                </form>
            </div>

            <div class="action-add">
                <button class="ubtn ubtn-blue" id="jaddNew">新建广告</button>
            </div>
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>类型</th>
                    <th>名称</th>
                    <th>链接</th>
                    <th>排序</th>
                    <th width="100">创建时间</th>
                    <th width="230" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list pageInfo.list as ad>
                <tr <#if ad.status==0>class="gray"</#if>>
                    <td><input type="checkbox"></td>
                    <td>${ad.adTypeName!}</td>
                    <td>${ad.name!}</td>
                    <td>${ad.href!}</td>
                    <td>${ad.sort!}</td>
                    <td>${(ad.createTime?datetime)!}</td>
                    <td class="tc">
                        <a href="javascript:;" class="ubtn ubtn-blue jedit" data-id="${ad.id}">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel" data-id="${ad.id}">删除</a>
                        <#if ad.status == 0>
                            <a href="javascript:;" data-id="${ad.id}"  class="ubtn ubtn-gray jputup">启用</a>
                            <#else>
                            <a href="javascript:;" data-id="${ad.id}"  class="ubtn ubtn-gray jputdown">禁用</a>
                        </#if>

                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=pageInfo url="ad/list" params="" />
    </div>
</div>
<#include "./common/footer.ftl"/>
<!-- 广告新增&编辑弹出框 -->
<form id="myform" class="hide">
    <div class="fa-form fa-form-layer">
        <div class="item">
            <div class="txt">类型：</div>
            <div class="cnt">
                <select name="typeId" id="typeId" class="slt">
                <#list types as type>
                    <option value="${type.id}">${type.name}</option>
                </#list>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt"><i>*</i>名称：</div>
            <div class="cnt">
                <input type="text" name="name" class="ipt" placeholder="名称" autocomplete="off">
                <input type="hidden" name="id">
            </div>
        </div>
        <div class="item">
            <div class="txt">链接：</div>
            <div class="cnt">
                <input type="text" name="href" class="ipt" placeholder="链接" autocomplete="off">
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
                <span class="up-img x1" id="imgCrop"></span>
                <input type="hidden" id="pictureUrl" name="pictureUrl" value="">
                <span class="tips">图片尺寸：750 X 350</span>
            </div>
        </div>

        <div class="button">
            <button type="submit" class="ubtn ubtn-blue">确认</button>
            <button type="button" class="ubtn ubtn-gray">关闭</button>
        </div>
    </div>
</form>

<!-- 上传图片文本域 -->
<div id="imgCropWrap"></div>
<script src="assets/js/croppic.min.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script>
    var _global = {
        v: {
            deleteUrl: 'ad/delete/',
            detailUrl:'ad/detail/',
            enableUrl:'ad/enable/',
            disableUrl:'ad/disable/',
            saveUrl:'ad/save',
            listUrl: '/ad/list',
            flag: false
        },
        fn: {
            init: function() {
                this.bindEvent();
                this.category();
                this.goodsImg();
                this.filter();
                $("#filterForm").initByUrlParams();
            },
            // 筛选
            filter: function() {
                var $ipts = $('.filter .ipt, .filter select');
                var url=_global.v.listUrl+"?";

                $('#search_btn').on('click', function() {
                    var params = [];
                    $ipts.each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=url+params.join('&');
                })
            },
            bindEvent: function() {
                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length;

                // 删除
                $table.on('click', '.jdel', function() {
                    var url = _global.v.deleteUrl + $(this).attr('data-id');
                    layer.confirm('确认删除此广告？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false; // 阻止链接跳转
                })

                // 上架
                $table.on('click', '.jputup', function() {
                    var url = _global.v.enableUrl + $(this).attr('data-id');
                    layer.confirm('确认启用此广告？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false;
                })

                // 下架
                $table.on('click', '.jputdown', function() {
                    var url = _global.v.disableUrl + $(this).attr('data-id');
                    layer.confirm('确认禁用此广告？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false;
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
            },
            // 广告新建 or 编辑
            category: function() {
                var $advForm = $('#myform'),
                    self = this;

                $advForm.validator({
                    fields: {
                        name: '名称: required',
                        sort: '排序: required; integer',
                        pictureUrl: '图片: required'
                    },
                    valid: function (form) {
                        // 保存或者新建
                        var data = $(form).serializeObject();
                        $.post(_global.v.saveUrl,data, function (data) {
                            if(data.status == 200) {
//                                $.notify({
//                                    type: 'success',
//                                    title: '保存成功',
//                                    text: data.msg,
//                                    delay: 3e3
//                                });
                                layer.closeAll();
                                window.location.reload(true);
                            }
                        })
                        return false;
                    }
                });

                // 关闭弹层
                $advForm.on('click', '.ubtn-gray', function () {
                    layer.closeAll();
                })

                // 新建
                $('#jaddNew').on('click', function() {
                    $('#imgCrop').empty();
                    $('#pictureUrl').val('');
                    $advForm[0].reset();
                    $advForm.find('.slt[name="typeId"]').trigger('change');
                    self.upImg();
                    layer.open({
                        area: ['600px'],
                        type: 1,
                        moveType: 1,
                        content: $advForm,
                        title: '新建广告'
                    });
                })

                // 编辑
                $('.table').on('click', '.jedit', function() {
                    if (_global.v.flag) {
                        return false;
                    }
                    _global.v.flag = true;
                    $advForm[0].reset();
                    self.showinfo($(this).data('id'));
                    return false; // 阻止链接跳转
                })
            },
            showinfo: function(id) {
                var self = this,
                    $advForm = $('#myform');

                var showBox = function(data) {
                    $advForm.find('.ipt[name="name"]').val(data.name);
                    $advForm.find('input[name="id"]').val(data.id);
                    $advForm.find('.ipt[name="sort"]').val(data.sort);
                    $advForm.find('.slt[name="typeId"]').val(data.typeId).trigger('change');
                    $advForm.find('.ipt[name="href"]').val(data.href);

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
                        content: $advForm,
                        title: '编辑广告'
                    });
                }

                // 加载数据
                var k = $.ajax({
                    url: _global.v.detailUrl+id,
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
                    title: '编辑广告',
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

                // 切换上传图片的尺寸
                $('#typeId').on('change', function() {
                    var tips = this.value == 1 ? '图片尺寸：750 X 350' : '图片尺寸：750 X 400';
                    $('#imgCrop').attr('class', 'up-img x' + this.value)
                    .nextAll('.tips').html(tips);
                })
            },
            upImg: function() {
                var self = this;
                var options = {
                    uploadUrl: '/gen/upload',
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