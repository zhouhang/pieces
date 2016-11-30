<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新增账户-boss-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="fa-floor">

    <div class="wrap">

        <div  style="display: none" id="error_advices" class="message">
            <i class="fa fa-times-circle"></i>
            <span>新增失败！</span>
        </div>

        <div class="side">
            <dl>
                <dt>客户信息</dt>
                <dd>
                    <!-- <a href="user/index">客户界面</a> -->
                    <a class="curr" href="/user/add">账户信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="/user/save" id="myform">
                <div class="title">
                    <h3><i class="fa fa-people"></i>新客户</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <!--<button type="button" class="btn btn-gray">保存</button>-->
                        <button type="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="user-info">
                    <h3>账户信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>客户类型：
                            </div>
                            <div class="cnt cbxs" id="categorys">
                                <label><input type="radio" class="cbx" name="type" value="1" checked>终端客户</label>
                                <label><input type="radio" class="cbx" name="type" value="2" >代理商</label>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>会员名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="userName" id="userName" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>联系人姓名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="contactName" id="contactName" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>联系人手机号码：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="contactMobile" id="contactMobile" placeholder="">
                            </div>
                        </div>

                    </div>
                </div>

                <div class="user-info">
                    <h3>密码管理</h3>

                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>新密码：
                            </div>
                            <div class="cnt">
                                <input type="password" class="ipt" value="" autocomplete="off" name="password" id="password" placeholder="请输入新密码">
                            </div>
                        </div>

                        <div class="group">
                            <div class="cnt-extra">
                                <label><input class="cbx" name="random" id="random" type="checkbox">或发送随机密码</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="user-info" id="agencyCnt">
                    <h3>绑定代理商</h3>

                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                代理商姓名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="" id="agencyName" placeholder="">
                            </div>
                            <input type="hidden" class="ipt" value="" autocomplete="off" name="agentId" id="agentId" placeholder="">
                        </div>
                    </div>
                </div>
                <div class="user-info" id="serviceCnt">
                    <h3>跟单员</h3>

                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                跟单员姓名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="" id="serviceName" placeholder="">
                            </div>
                            <input type="hidden" class="ipt" value="" autocomplete="off" name="serviceId" id="serviceId" placeholder="">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
    <script src="js/jquery.form.js"></script>
    <script src="js/jquery.autocomplete.min.js"></script>
    <script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="js/common.js"></script>

<script>
    var _global = {
        v: {},
        fn: {
            init: function() {
                this.formValidate();
                this.agency();
                this.service();
            },
            formValidate: function() {
                $('#myform').validator({
                    fields: {
                        userName: '会员名: required;username;remote(/user/username/check)',
                        contactName: '联系人姓名: required;nickName',
                        contactMobile: '联系人手机号码: required, mobile',
                        password: '新密码: required;password'
                    },
                    valid: function(form) {
                        if ( $(form).isValid() ) {
                            $.ajax({
                                url: '/user/save',
                                data: $(form).formSerialize(),
                                type: "POST",
                                success: function (data) {
                                    if(data.status=="y"){
                                        location.href="/user/index?advices="+data.info
                                    }else{
                                        $("#error_advices span").html(data.info);
                                        $("#error_advices").show();
                                    }
                                }
                            });
                        }
                    }
                });

                var $pwd = $('#password');
                $('#random').on('click', function() {
                    $pwd.attr('class', 'ipt').prop('disabled', this.checked).val('').trigger("hidemsg");
                    $('#myform').data('validator').options.ignore = this.checked ? $pwd : '';
                }).prop('checked', false);

            },
            agency: function() {
                    // 代理商姓名联想
                    var $agencyName = $('#agencyName');
                    $agencyName.autocomplete({
                        serviceUrl: '/user/search',
                        paramName: 'name',
                        deferRequestBy: 100,
                        type: 'POST',
                        showNoSuggestionNotice: true,
                        noSuggestionNotice: '没有该代理商',
                        transformResult: function (response) {
                            response = JSON.parse(response);
                            if (response.status == "y") {
                                return {
                                    suggestions: $.map(response.data.list, function (dataItem) {
                                        return {value: dataItem.contactName, data: dataItem.id};
                                    })
                                };
                            } else {
                                return {
                                    suggestions: []
                                }
                            }
                        },
                        onSelect: function (suggestion) {
                            $("#agentId").val(suggestion.data); // 保存品种id到隐藏文本域
                        }
                    })

                $('#categorys').on('click', '.cbx', function() {
                    if (this.value === '2') {
                        $('#agencyCnt').hide();
                    } else {
                        $('#agencyCnt').show();
                    }
                })
            },
            service:function () {
                // 跟单姓名联想
                var $serviceName = $('#serviceName');
                $serviceName.autocomplete({
                    serviceUrl: '/user/searchMember',
                    paramName: 'name',
                    deferRequestBy: 100,
                    type: 'POST',
                    showNoSuggestionNotice: true,
                    noSuggestionNotice: '没有该客服',
                    transformResult: function (response) {
                        response = JSON.parse(response);
                        if (response.status == "y") {
                            return {
                                suggestions: $.map(response.data.list, function (dataItem) {
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
                        $("#serviceId").val(suggestion.data); // 保存品种id到隐藏文本域
                    }
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