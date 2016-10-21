<!DOCTYPE html>
<html lang="en">
<head>
    <title>寄样单详情-boss-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>

<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>寄样服务</li>
            <li>寄样详情</li>
        </ul>
    </div>

    <div class="box fa-form fa-form-info">
        <div class="hd">寄样信息</div>
        <div class="item">
            <div class="txt">用户姓名：</div>
            <div class="val">${sendSampleVo.nickname}</div>
        </div>
        <div class="item">
            <div class="txt">寄样单编号：</div>
            <div class="val">${sendSampleVo.code}</div>
        </div>
        <div class="item">
            <div class="txt">手机号：</div>
            <div class="val">${sendSampleVo.phone}</div>
        </div>
        <div class="item">
            <div class="txt">地区：</div>
            <div class="val">${sendSampleVo.area}</div>
        </div>
        <div class="item">
            <div class="txt">申请时间：</div>
            <div class="val">${sendSampleVo.createTime?string("yyyy-MM-dd HH:mm")}</div>
        </div>
        <div class="item">
            <div class="txt">申请商品：</div>
            <div class="val">${sendSampleVo.intentionText}</div>
        </div>
        <div class="item">
            <div class="txt">状态：</div>
            <div class="val status-${sendSampleVo.status+1}">${sendSampleVo.statusText}</div>
        </div>
        <div class="item">
            <div class="txt">历史寄样信息：</div>
            <div class="cnt cnt-table">
                <table>
                    <thead>
                    <tr>
                        <th>申请商品</th>
                        <th>申请时间</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list historySend.list as sendSample>
                    <tr>
                        <td><a href="#">${sendSample.intentionText}</a></td>
                        <td>${sendSample.createTime?string("yyyy-MM-dd HH:mm")}</td>
                        <td><span class="status-${sendSample.status+1}">${sendSample.statusText}</span></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="box fa-form">
        <div class="hd">信息补全</div>
        <form id="userForm">
            <input type="hidden"  class="ipt" value="${userDetail.id?default('')}" name="id">
            <div class="item">
                <div class="txt">个人称呼：</div>
                <div class="cnt">
                    <input type="text" value="${userDetail.nickname?default('')}" name="nickname" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">联系电话：</div>
                <div class="cnt">
                    <input type="text" value="${userDetail.phone?default('')}" name="phone" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">经营类型：</div>
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
                    <input type="text" value="${userDetail.name?default('')}" name="name" class="ipt" placeholder="姓名/单位" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">用户备注：</div>
                <div class="cnt">
                    <textarea   id="userRemark" class="ipt ipt-mul">${userDetail.remark?default('')}</textarea>
                </div>
            </div>
            <div class="ft">
                <button type="button" id="saveUser" class="ubtn ubtn-blue">保存客户信息</button>
            </div>
        </form>
    </div>

    <div class="box fa-form">
        <div class="hd">地址信息</div>
        <form action="" id="addressForm">
            <input type="hidden"  class="ipt" value="${sendSampleVo.id}" name="sendId">
            <input type="hidden"  class="ipt" value="${sampleAdderss.id?default('')}" name="id">
            <div class="item" id="jgoosList">
                <div class="txt">寄样商品：</div>
               <#list sendSampleVo.commodityList as commodity>
                <div class="cnt">
                    <input type="text" name="intention" value="${commodity.name} ${commodity.origin} ${commodity.spec}" cid="${commodity.id}" id="goods1" class="ipt" placeholder="" autocomplete="off">
                    <#if commodity_index==(sendSampleVo.commodityList?size)>
                        <button type="button" class="ubtn ubtn-blue ml" id="jaddNewGoods">添加一行</button>
                    </#if>

                </div>
               </#list>
            </div>
            <div class="item">
                <div class="txt">收货地址：</div>
                <div class="cnt">
                    <input type="text" value="${sampleAdderss.address?default('')}" name="address" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">收货人：</div>
                <div class="cnt">
                    <input type="text" value="${sampleAdderss.receiver?default('')}" name="receiver" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">收货人电话：</div>
                <div class="cnt">
                    <input type="text" value="${sampleAdderss.receiverPhone?default('')}" name="receiverPhone" class="ipt" placeholder="" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt">备注信息：</div>
                <div class="cnt">
                    <textarea  id="addressRemark" class="ipt ipt-mul">${sampleAdderss.remark?default('')}</textarea>
                </div>
            </div>
            <div class="ft">
                <button type="button" id="saveAddress"  class="ubtn ubtn-blue">保存收货信息</button>
            </div>
        </form>
    </div>

    <div class="box fa-form">
        <div class="hd">寄样追踪</div>
        <ol class="trace">
            <li class="fore">状态：<span class="status-${sendSampleVo.status+1}">${sendSampleVo.statusText}</span></li>
            <li>2016年10月12日    12：30   申请寄样</li>
        </ol>
        <div class="ft">
            <button type="button" class="ubtn ubtn-blue" id="jsubmit1">同意寄样</button>
            <button type="button" class="ubtn ubtn-gray ml" id="jsubmit2">拒绝寄样</button>
        </div>
    </div>
</div>

<#include "./common/footer.ftl"/>
<script src="assets/js/jquery191.js"></script>
<script src="assets/js/jquery.autocomplete.js"></script>
<script src="assets/plugins/layer/layer.js"></script>
<script src="assets/plugins/validator/jquery.validator.min.js"></script>
<script>
    var _global = {
        v: {
            userUpdateUrl:'/sample/userComplete/',
            addressSaveUrl:'/sample/addressSave/'
        },
        fn: {
            init: function() {
                this.myform();
                this.submitEvent();
            },
            // 查询品种
            searchProduct: function($ele) {
                $ele.autocomplete({
                    serviceUrl: 'catName.json',
                    paramName: 'name',
                    deferRequestBy: 100,
                    showNoSuggestionNotice: true,
                    noSuggestionNotice: '没有该品种',
                    transformResult: function (response) {
                        response = JSON.parse(response);
                        if (response.status == "y") {
                            return {
                                suggestions: $.map(response.data, function (dataItem) {
                                    return {value: dataItem.name, data: dataItem.id};
                                })
                            };
                        } else {
                            return {
                                suggestions: []
                            }
                        }
                    },
                    onSelect: function (suggestion) {
                    }
                });
            },
            myform: function() {

                var self = this,
                        $jgoosList = $('#jgoosList'),
                        idx = $jgoosList.find('.cnt').length;

                // 添加商品
                $('#jaddNewGoods').on('click', function() {
                    $jgoosList.append('<div class="cnt"> \n <div class="ipt-wrap"><input type="text" name="intention' + '" id="goods' + (++idx) + '" class="ipt" autocomplete="off"></div> \n <button type="button" class="ubtn ubtn-red ml">删除</button> \n </div>');
                    self.searchProduct($('#goods' + idx));
                })

                // 删除价格
                $jgoosList.on('click', '.ubtn-red', function(){
                    $(this).prev().find('.ipt').autocomplete('dispose');
                    $(this).parent().remove();
                })

                self.searchProduct($('#goods1'));

            },
            // 提交事件
            submitEvent: function() {
                var self = this;

                // 同意寄样
                $('#jsubmit1').on('click', function() {
                    layer.prompt({
                        moveType: 1,
                        formType: 2,
                        title: '同意原因',
                        btn: ['同意', '关闭']
                    }, function(text) {
                        console.log(text); // 输入文本
                    });
                    return false;
                })

                // 拒绝寄样
                $('#jsubmit2').on('click', function() {
                    layer.prompt({
                        moveType: 1,
                        formType: 2,
                        title: '拒绝原因',
                        btn: ['拒绝', '关闭']
                    }, function(text) {
                        console.log(text); // 输入文本
                    });
                    return false;
                })

                $("#saveUser").on('click', function() {
                    var url = _global.v.userUpdateUrl;
                    $.ajax({
                        url: url,
                        data: $("#userForm").serialize()+"&remark="+$("#userRemark").val(),
                        type: "POST",
                        success: function(data){
                            if (data.status == "200") {
                                window.location.reload();
                            }

                        }
                    });
                })
                $("#saveAddress").on('click', function() {
                    var url = _global.v.addressSaveUrl;
                    $.ajax({
                        url: url,
                        data: $("#addressForm").serialize()+"&remark="+$("#addressRemark").val(),
                        type: "POST",
                        success: function(data){
                            if (data.status == "200") {
                                window.location.reload();
                            }

                        }
                    });
                })
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>