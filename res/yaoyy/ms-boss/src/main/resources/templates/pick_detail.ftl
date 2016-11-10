<!DOCTYPE html>
<html lang="en">
<head>
    <title>选货单详情-boss</title>
    <#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>选货单模块</li>
            <li>选货单列表</li>
        </ul>
    </div>

    <div class="box fa-form fa-form-info">
        <div class="hd">用户信息</div>
        <div class="item">
            <div class="txt">送货单编号</div>
            <div class="val">${pickVo.code}</div>
        </div>
        <div class="item">
            <div class="txt">用户姓名：</div>
            <div class="val">${pickVo.nickname}</div>
        </div>
        <div class="item">
            <div class="txt">手机号：</div>
            <div class="val">${pickVo.phone}</div>
        </div>
        <!--
        <div class="item">
            <div class="txt">地区：</div>
            <div class="val">${(pickVo.area)!}</div>
        </div>
        -->
        <div class="item">
            <div class="txt">申请时间：</div>
            <div class="val">${(pickVo.createTime?datetime)!}</div>
        </div>
        <div class="item">
            <div class="txt">状态：</div>
            <div class="val status-${pickVo.status+1}">${pickVo.statusText}</div>
        </div>
    </div>


    <div class="box fa-form">
        <div class="hd">采购单详情</div>
        <table>
            <thead>
            <tr>
                <th>商品名称</th>
                <th>产地</th>
                <th>规格等级</th>
                <th>数量</th>
                <th>价格</th>
                <th>合计</th>
            </tr>
            </thead>
            <tbody>
            <#list pickVo.pickCommodityVoList as pickCommodityVo >
            <tr>
                <td>${pickCommodityVo.name}</td>
                <td>${pickCommodityVo.origin}</td>
                <td>${pickCommodityVo.spec}</td>
                <td>${pickCommodityVo.num}</td>
                <td>${pickCommodityVo.price}元/${pickCommodityVo.unit}</td>
                <td>${pickCommodityVo.total}</td>
            </tr>
            </#list>
            </tbody>
            <tfoot>
            <td colspan="6">
                <div class="total">合计：<em>${pickVo.total}元</em></div>
            </td>
            </tfoot>
        </table>
    </div>


    <div class="box fa-form">
        <div class="hd">客户信息</div>
        <form id="userForm">
            <input type="hidden"  class="ipt" value="${userDetail.id?default('')}" name="id">
            <div class="item">
                <div class="txt">个人称呼：</div>
                <div class="cnt">
                    <input type="text" value="${userDetail.nickname?default('')}" name="nicknam" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">联系电话：</div>
                <div class="cnt">
                    <input type="text"  value="${userDetail.phone?default('')}" name="phone" class="ipt" placeholder="" autocomplete="off" <#if userDetail.userType!=1>disabled</#if>>
                </div>
            </div>
            <div class="item">
                <div class="txt">地区：</div>
                <div class="cnt">
                    <input type="text"  value="${userDetail.area?default('')}"  name="area" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">身份类型：</div>
                <div class="cnt cbxs">
                    <label><input type="radio" name="type" class="cbx" value="1" <#if userDetail.type?exists && userDetail.type==1> checked</#if> >饮片厂</label>
                    <label><input type="radio" name="type" class="cbx" value="2" <#if userDetail.type?exists && userDetail.type==2> checked</#if>>药厂</label>
                    <label><input type="radio" name="type" class="cbx" value="3" <#if userDetail.type?exists && userDetail.type==3> checked</#if>>药材经营公司</label>
                    <label><input type="radio" name="type" class="cbx" value="4" <#if userDetail.type?exists && userDetail.type==4> checked</#if>>个体经营户</label>
                    <label><input type="radio" name="type" class="cbx" value="5" <#if userDetail.type?exists && userDetail.type==5> checked</#if>>合作社</label>
                    <label><input type="radio" name="type" class="cbx" value="6" <#if userDetail.type?exists && userDetail.type==6> checked</#if>>种植基地</label>
                    <label><input type="radio" name="type" class="cbx" value="7" <#if userDetail.type?exists && userDetail.type==7> checked</#if>>其他</label>
                    <label><input type="radio" name="type" class="cbx" value="8" <#if userDetail.type?exists && userDetail.type==8> checked</#if>>个人经营</label>
                    <label><input type="radio" name="type" class="cbx" value="9" <#if userDetail.type?exists && userDetail.type==9> checked</#if>>采购经理</label>
                    <label><input type="radio" name="type" class="cbx" value="10" <#if userDetail.type?exists && userDetail.type==10> checked</#if>>销售经理</label>
                </div>
            </div>
            <div class="item">
                <div class="txt">姓名/单位：</div>
                <div class="cnt">
                    <input type="text"  value="${userDetail.name?default('')}" name="name" class="ipt" placeholder="姓名/单位" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">用户备注：</div>
                <div class="cnt">
                    <textarea id="userRemark" class="ipt ipt-mul">${userDetail.remark?default('')}</textarea>
                </div>
            </div>
            <div class="ft">
                <button type="button" id="saveUser" class="ubtn ubtn-blue">保存客户信息</button>
            </div>
        </form>
    </div>

    <div class="box fa-form">
        <div class="hd">采购单追踪</div>
        <ol class="trace" id="trace">
            <li class="fore">状态：<em class="status-${pickVo.status+1}">${pickVo.statusText}</em></li>
            <#list pickTrackingVos as tracking>
            <li><span>${tracking.name?default('')}</span>&nbsp;&nbsp;<span>${tracking.createTime?string("yyyy年MM月dd日 HH:mm")}</span>&nbsp;&nbsp;<span>${tracking.recordTypeText}</span>&nbsp;&nbsp;<span>${tracking.extra?default('')}</span></li>
            </#list>
        </ol>
        <div class="ft <#if pickVo.status!=0>hide</#if>">
            <button type="button" class="ubtn ubtn-blue submit1">同意受理</button>
            <button type="button" class="ubtn ubtn-gray ml submit2">拒绝受理</button>
        </div>
        <form action=""  <#if pickVo.status==0||pickVo.status==2||pickVo.status=3||pickVo.status=3||pickVo.status=4> class="hide"</#if> id="traceForm">
            <div class="item">
                <div class="txt">跟踪记录：</div>
                <div class="cnt">
                    <textarea name="consigneeNote" class="ipt ipt-mul" placeholder="跟踪记录"></textarea>
                </div>
                <div class="cnt">
                    <button type="button" class="ubtn ubtn-gray submit3">提交记录</button>
                </div>
            </div>
            <div class="ft">
                <button type="button" class="ubtn ubtn-blue submit4">交易完成</button>
                <button type="button" class="ubtn ubtn-gray ml submit5">交易未完成</button>
            </div>
        </form>

    </div>
</div>

<#include "./common/footer.ftl"/>

<script src="assets/js/jquery.autocomplete.js"></script>
<script src="assets/plugins/laydate/laydate.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script>
    var _global = {
        v: {
            userUpdateUrl:'sample/userComplete/',
            trackingCreateUrl:'pick/trackingSave'
        },
        fn: {
            init: function() {
                this.submitEvent();
            },
            // 提交事件
            submitEvent: function() {
                var self = this,
                        $trace = $('#trace'),
                        $pa = $trace.parent();

                // 同意受理
                $pa.on('click', '.submit1', function() {
                    self.submit1();
                    return false;
                })

                // 拒绝受理
                $pa.on('click', '.submit2', function(){
                    self.submit2();
                    return false;
                })

                // 提交记录
                $pa.on('click', '.submit3', function(){
                    var text = $('#traceForm').find('.ipt').val();
                    $.ajax({
                        url: _global.v.trackingCreateUrl,
                        data: {pickId: ${pickVo.id},recordType:3,extra:text},
                        type: "POST",
                        success: function(data) {
                            /*
                            data = {
                                date: '2016年10月20日 15:20',
                                operator: 'frank',
                                msg: text
                            }
                            $('#traceForm').find('.ipt').val('');
                            self.addNewRevord(data);
                            */
                            window.location.reload();
                        }
                    })
                })

                // 交易完成
                $pa.on('click', '.submit4', function() {
                    layer.confirm('选货单完成后不能进行跟踪记录操作，是否确认', {
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        $.ajax({
                            url: _global.v.trackingCreateUrl,
                            data: {pickId: ${pickVo.id},recordType:5},
                            type: "POST",
                            success: function(data) {
                                /*
                                data = {
                                    operator: 'frank',
                                    date: '2016年10月20日 15:20',
                                    msg: text
                                }
                                $('#trace').append('<li><span>' + data.date + '</span><span>操作人：' + data.operator + '</span><span>同意寄样</span></li><li><span>同意理由：' + data.msg + '</span></li>');
                                $('.submit2').parent().remove();
                                $('.submit3').parent().show();
                                */
                                window.location.reload();
                            }
                        })
                        //$('#traceForm').remove();
                        //layer.close(index);
                    });
                })
                // 交易未完成
                $pa.on('click', '.submit5', function() {
                    self.submit5();
                    return false;
                })
            },
            // 同意受理
            submit1: function() {
                $.ajax({
                    url: _global.v.trackingCreateUrl,
                    data: {pickId: ${pickVo.id},recordType:1},
                    type: "POST",
                    success: function(data) {
                        /*
                        data = {
                            operator: 'frank',
                            date: '2016年10月20日 15:20'
                        }
                        $('#trace').append('<li><span>' + data.date + '</span><span>操作人：' + data.operator + '</span><span>同意受理该采购单</span></li>');
                        $('.submit2').parent().remove();
                        $('#traceForm').show();
                        */
                        window.location.reload();

                    }
                })
            },
            // 拒绝受理
            submit2: function() {
                layer.prompt({
                    moveType: 1,
                    formType: 2,
                    title: '拒绝原因',
                    btn: ['拒绝', '关闭']
                }, function(text, index) {
                    $.ajax({
                        url: _global.v.trackingCreateUrl,
                        data: {pickId: ${pickVo.id},recordType:2,extra:"不同意理由："+text},
                        type: "POST",
                        success: function(data) {
                            /*
                            data = {
                                operator: 'frank',
                                date: '2016年10月20日 15:20',
                                msg: text
                            }
                            $('#trace').append('<li><span>' + data.date + '</span><span>操作人：' + data.operator + '</span><span>拒绝受理该采购单</span></li><li><span>拒绝理由：' + data.msg + '</span></li>');
                            $('#trace').nextAll().remove();
                            */
                            window.location.reload();
                        }
                    })
                    layer.close(index);
                });
            },
            // 交易未完成
            submit5: function() {
                layer.prompt({
                    moveType: 1,
                    formType: 2,
                    title: '取消原因',
                    btn: ['确认', '关闭']
                }, function(text, index) {
                    $.ajax({
                        url: _global.v.trackingCreateUrl,
                        data: {pickId: ${pickVo.id},recordType:4,extra:"原因："+text},
                        type: "POST",
                        success: function(data) {
                            /*
                            data = {
                                operator: 'frank',
                                date: '2016年10月20日 15:20',
                                msg: text
                            }
                            $('#trace').append('<li><span>' + data.date + '</span><span>操作人：' + data.operator + '</span><span>交易未完成</span></li><li><span>原因：' + data.msg + '</span></li>');
                            $('#trace').nextAll().remove();
                            */
                            window.location.reload();
                        }
                    })
                    layer.close(index);
                });
            },
            // 表单
            layerForm: function(modal, title) {
                layer.open({
                    area: ['600px'],
                    type: 1,
                    moveType: 1,
                    content: modal,
                    title: title
                });
            },
            // 添加记录
            addNewRevord: function(data) {
                var html = [];
                $.each(data, function(i, item) {
                    html.push('<span>' + item + '</span>');
                })
                html.length > 1 && $('#trace').append('<li>' + html.join('') + '</li>');
            }
        }
    }

    $(function() {
        _global.fn.init();
        $("#saveUser").on('click', function() {
            var url = _global.v.userUpdateUrl;
            $.ajax({
                url: url,
                data: $("#userForm").serialize()+"&remark="+$("#userRemark").val(),
                type: "POST",
                success: function(data){
                    if (data.status == "200") {
                        $.notify({
                            type: 'success',
                            title: '保存成功',
                            text: '3秒后自动刷新页面',
                            delay: 3e3,
                            call: function() {
                                setTimeout(function() {
                                    window.location.reload();
                                }, 3e3);
                            }
                        });
                    }

                }
            });
        });
    })
</script>
</body>
</html>