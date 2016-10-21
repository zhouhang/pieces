<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>收货地址-上工好药</title>
    <link rel="stylesheet" href="/css/order.css"/>
</head>

<body>

<#include "./inc/header-center.ftl"/>
<div class="member-box">
    <div class="wrap">
    <#include "./inc/side-center.ftl"/>
        <div class="main">
            <div class="title">
                <h3>收货地址</h3>
                <div class="extra"></div>
            </div>

            <div class="consignee">
            <#if adds??&&adds?size == 0>
                <div class="empty">
                    <i class="fa fa-consignee"></i>
                    <p>你还没有收货地址！</p>
                    <a href="javascript:;" class="jaddConsignee">+新建地址</a>
                </div>
            <#else >
                <div class="hd">
                    <a href="javascript:;" class="fr jaddConsignee">+新建地址</a>
                    <span>已保存收货地址<em>（地址最多 <b>10</b> 条，还能保存 <b>${10 - adds?size}</b> 条）</em></span>
                </div>
                <div class="fa-chart">
                    <table>
                        <thead>
                        <tr>
                            <th width="120">收货人</th>
                            <th width="200">地址</th>
                            <th>联系方式</th>
                            <th>操作</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody id="add-body">
                            <#list adds as add>
                            <tr>
                                <td>${add.consignee}</td>
                                <td>${add.fullAdd}</td>
                                <td>${add.tel}</td>
                                <td>
                                    <a href="${add.id}" class="jedit">编辑</a>
                                    <a href="${add.id}" class="jdel">删除</a>
                                </td>
                                <td>
                                    <#if add.isDefault>
                                        <b>默认地址</b>
                                    <#else>
                                        <a href="${add.id}" class="jdefault">设为默认地址</a>
                                    </#if>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </#if>
            </div>
        </div>
    </div>
</div><!-- member-box end -->
<#include "./inc/footer.ftl"/>


<!-- start 新增收货地址 -->
<div class="fa-form fa-form-layer" id="jconsigneeBox">
    <form action="" id="consigneeForm">
        <div class="group">
            <div class="txt">
                <span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>
            </div>
            <div class="cnt">
                <input type="text" name="consignee" id="consigneeName" class="ipt" autocomplete="off" placeholder="" data-msg-nickName="只能输入中文、英文，长度2-50" maxlength="50">
                <input type="text" style="display: none" id="id" name="id">
            </div>
        </div>

        <div class="group">
            <div class="txt">
                <span>手机号码：</span>
            </div>
            <div class="cnt">
                <input type="text" name="tel" id="consigneeMobile" class="ipt" autocomplete="off" placeholder="">
                <span class="error"></span>
            </div>
        </div>

        <div class="group">
            <div class="txt">
                <span>所在地区：</span>
            </div>
            <div class="cnt" id="pickArea">
                <select name="provinceCode" id="province">
                    <option value="">-省-</option>
                </select>
                <select name="cityCode" id="city">
                    <option value="">-市-</option>
                </select>
                <select name="areaId" id="area" data-msg-required="请选择至最后一级">
                    <option value="">-区/县-</option>
                </select>
                <input type="hidden" id="areaFull" name="areaFull" value="">
                <span class="error"></span>
            </div>
        </div>

        <div class="group">
            <div class="txt">
                <span>详细地址：</span>
            </div>
            <div class="cnt">
                <input type="text" name="detail" id="consigneeAddress" class="ipt ipt-wide" autocomplete="off">
                <span class="error"></span>
            </div>
        </div>

        <div class="group ah">
            <div class="cnt">
                <label><input type="checkbox" value="1" class="cbx" name="isDefault" id="consigneeDefault" checked>设为默认地址</label>
            </div>
        </div>

        <div class="button">
            <button type="submit" class="btn btn-red submit">保存</button>
            <button type="reset" class="btn btn-gray cancel">取消</button>
        </div>
    </form>
</div><!-- end 新增收货地址 -->

<script src="js/layer/layer.js"></script>
<script src="js/validator/jquery.validator.js?local=zh-CN"></script>
<script src="js/area.js"></script>
<script src="js/jquery_util.js"></script>
<script>
    var _global = {
        v: {
            saveUrl: "/user/shippingaddress/save",
            deleteUrl: "/user/shippingaddress/delete/",
            defaultUrl: "/user/shippingaddress/default/",
            getDetailUrl: "/user/shippingaddress/detail/"
        },
        fn: {
            init: function () {
                this.delConsignee();
                this.addConsignee();
                this.defaultConsignee();
            },
            // 删除
            delConsignee: function () {
                $('.fa-chart').on('click', '.jdel', function () {
                    var $this = $(this);
                    layer.confirm('要删除此地址？', {icon: 3, title: '提示'}, function (index) {
                        var url = _global.v.deleteUrl + $this.attr("href");
                        $.get(url, function (data) {
                            if (data.status == "y") {
                                window.location.reload();
                            }
                        }, "json");
                        layer.close(index);
                    });
                    return false; // 组织默认事件
                })
            },
            // 新增 & 修改
            addConsignee: function () {
                var $consigneeBox = $('#jconsigneeBox');
                var $consigneeForm = $('#consigneeForm');
                // 关闭弹层
                $consigneeBox.on('click', '.cancel', function () {
                    layer.closeAll();
                })

                $consigneeForm.validator({
                    fields: {
                        consignee: '收货人: required; nickName',
                        tel: '手机号码: required; mobile',
                        areaId: '所在地区: required',
                        detail: '详细地址: required'
                    },
                    valid: function (form) {
                        var myfromValid = this;
                        if ($(form).isValid()) {
                            $("#areaFull").val($('#province option:selected').text() + $('#city option:selected').text() + $('#area option:selected').text());
                        }
                    }
                });

                // 新增
                $('.jaddConsignee').on('click', function () {
                    var total = $("#add-body").find('tr').length;
                    if (total >= 10) {
                        $.notify({
                            type: 'warn',
                            title: '警告',
                            text: '收货地址不能超过10条!',
                            delay: 3e3
                        });
                        return false;
                    }
                    $consigneeForm[0].reset();
                    $('#pickArea').citys(); // 地区级联
                    layer.open({
                        area: ['600px'],
                        closeBtn: 1,
                        type: 1,
                        moveType: 1,
                        content: $consigneeBox,
                        title: '新建地址'
                    });
                })

                // 修改
                $('.fa-chart').on('click', '.jedit', function () {
                    var url = _global.v.getDetailUrl + $(this).attr("href");

                    $.get(url, function (data) {
                        if (data.status == "y") {
                            var addr = data.data;
                            // 填充数据
                            $("#id").val(addr.id);
                            $('#consigneeName').val(addr.consignee);
                            $('#consigneeMobile').val(addr.tel);
                            $('#pickArea').citys({code: addr.areaId}); // 地区级联，并传入默认值
//                            $('#areaFull').val(addr.areaId);
                            $('#consigneeAddress').val(addr.detail);
                            $('#consigneeDefault').prop('checked', addr.isDefault);
                            layer.open({
                                area: ['600px'],
                                closeBtn: 1,
                                type: 1,
                                moveType: 1,
                                content: $consigneeBox,
                                title: '修改地址'
                            });
                        }
                    }, "json")

                    return false;
                })

                // 保存
                $consigneeForm.submit(function () {
                    var $this = $(this);
                    $consigneeForm.isValid(function (v) {
                        if (v) {
                            var url = _global.v.saveUrl;
                            var data = $this.serializeObject();
                            if (data.isDefault == "1") {
                                data.isDefault = true;
                            } else {
                                data.isDefault = false;
                            }
                            $.post(url, data, function (data) {
                                if (data.status == "y") {
                                    layer.closeAll();
                                    window.location.reload();
                                } else {
                                    $.notify({
                                        type: 'warn',
                                        title: '警告',
                                        text: data.info,
                                        delay: 3e3
                                    });
                                }
                            })
                        }
                    })

                    return false;
                })
            },
            // 默认地址
            defaultConsignee: function () {
                $('.fa-chart').on('click', '.jdefault', function () {
                    var $this = $(this);
                    layer.confirm('确认次将此地址设为默认地址？', {icon: 3, title: '提示'}, function (index) {
                        var url = _global.v.defaultUrl + $this.attr("href");
                        $.get(url, function (data) {
                            if (data.status == "y") {
                                window.location.reload();
                            }
                        }, "json");
                        layer.close(index);
                    });
                    return false; // 组织默认事件
                })
            }
        }
    }
    $(function () {
        _global.fn.init();
    })
</script>

</body>
</html>