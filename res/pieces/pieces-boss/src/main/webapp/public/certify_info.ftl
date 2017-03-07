<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>企业资质审核-boss-上工好药</title>
</head>

<body>

<#include "./inc/header.ftl"/>



<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>企业资质审核</dt>
                <dd>
                    <a class="curr" href="#!">资质信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <div class="title">
                <h3><i class="fa fa-people"></i>${certifyRecord.userName}的企业资质审核</h3>
                <div class="extra">
                    <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                    <button type="button" class="btn btn-gray" id="submit2">不通过</button>
                    <button type="button" class="btn btn-red" id="submit1">通过</button>
                </div>
            </div>

            <div class="user-info">
                <h3>企业基础信息</h3>
                <div class="fa-form">
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业名称：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="${userCertification.company!}" autocomplete="off" name="company" id="companyName" placeholder="营业执照上的企业名称全称" type="text">
                            <span class="error1"></span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业负责人：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="${userCertification.corporation!}" autocomplete="off" name="corporation" id="legalPerson" placeholder="营业执照上法人姓名" type="text">
                            <span class="error1"></span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业所在地：
                        </div>
                        <div class="cnt">
                            <input class="ipt" value="${userCertification.address!}" autocomplete="off" name="address" id="companyRegion" placeholder="营业执照上的企业所在地地址" type="text">
                            <span class="error1"></span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业类型：
                        </div>
                        <div class="cnt">
                            <span class="val">${userCertification.typeText}</span>
                        </div>
                        <span class="error1" id="categoryError"></span>
                    </div>
                </div>
            </div>


            <div class="certificate">
              <#list userQualification as qualification>
                <div class="user-info">
                    <h3>${qualification.typeText}</h3>
                    <div class="check">
                        <div class="pic thumb">
                            <#list qualification.pictures as qualificationPicsVo>
                                    <img src="${qualificationPicsVo.pictureUrl}" alt="">
                            </#list>
                        </div>
                        <div class="form">
                            <label for="">证件号：</label>
                            <input class="ipt" value="${qualification.number}" autocomplete="off" name="cardID_1" placeholder="" type="text" data-msg="{empty: '请输入证件号', error: '证件号字符长度2到50个字符！'}">
                            <em class="error1 top"></em>
                            <label for="">有效期至：</label>
                            <input class="ipt date" value="${qualification.term!}" autocomplete="off" name="indate_1" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}" onclick="laydate()">
                            <em class="error1 bottom"></em>
                        </div>
                    </div>
                </div>
              </#list>
                </div>
            </div>

            <div class="user-info">
                <h3>跟进结果</h3>

                <div class="fa-note">
                    <p class="tips"><i>*</i>判断为不通过时要填写原因。</p>
                    <textarea class="ipt" value="" id="note">${certifyRecord.result!}</textarea>
                    <span class="error1"></span>
                </div>
            </div>
        </div>
    </div><!-- fa-floor end -->
</div>

<#include "./inc/footer.ftl"/>

<script src="${urls.getForLookupPath('/js/jquery.min.js')}"></script>
<script src="${urls.getForLookupPath('/js/lightbox.js')}"></script>
<script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
<script>
    var _global = {
        v: {
            id: "page"
        },
        fn: {
            init: function () {
                this.bindEvent();
                this.submitForm();
            },
            bindEvent: function() {
                var self = this;
                $myform = $('.form');

                // blur
                $myform.on('blur', '.ipt', function() {
                    var len = this.value.length,
                            tips = eval('(' + $(this).data('msg') + ')'),
                            msg = '';

                    if (len == 0) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.empty;
                    } else if (len < 2 || len > 50) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.error;
                    }
                    $(this).next().html(msg)[msg == '' ? 'hide' : 'show']();
                })

                $('#companyName').on('blur', function() {
                    self.checkCompName();
                })
                $('#legalPerson').on('blur', function() {
                    self.checklegalPerson();
                })
                $('#companyRegion').on('blur', function() {
                    self.checkcompanyRegion();
                })
            },
            checkCompName: function() {
                var $input = $('#companyName'),
                        length = $input.val().length,
                        msg = '';
                if (length == 0) {
                    msg = '<i class="fa fa-prompt"></i> 请输入企业名称';
                } else if (!/^([a-zA-Z0-9_\(\)-]|[\u4e00-\u9fa5]|[（）]){4,50}$/.test($input.val())) {
                    msg = '<i class="fa fa-prompt"></i> 企业名称长度4-50，只能由中英文、数字及\"_\"、\"-\"、()、（）组成';
                }
                $input.next().html(msg)[msg == '' ? 'hide' : 'show']();
                return msg === '';
            },
            checklegalPerson: function() {
                var $input = $('#legalPerson'),
                        length = $input.val().length,
                        msg = '';
                if (length == 0) {
                    msg = '<i class="fa fa-prompt"></i> 请输入企业负责姓名';
                } else if (length < 4 || length > 50) {
                    msg = '<i class="fa fa-prompt"></i> 企业责任人长度4-50位';
                }
                $input.next().html(msg)[msg == '' ? 'hide' : 'show']();
                return msg === '';
            },
            checkcompanyRegion: function() {
                var $input = $('#companyRegion'),
                        length = $input.val().length,
                        msg = '';
                if (length == 0) {
                    msg = '<i class="fa fa-prompt"></i> 请输入企业所在地地址';
                } else if (length < 4 || length > 50) {
                    msg = '<i class="fa fa-prompt"></i> 企业责任人长度4-150位';
                }
                $input.next().html(msg)[msg == '' ? 'hide' : 'show']();
                return msg === '';
            },
            formValidate: function() {
                var pass = true;

                if (!this.checkcompanyRegion()) {
                    pass = false;
                }
                if (!this.checklegalPerson()) {
                    pass = false;
                }
                if (!this.checkCompName()) {
                    pass = false;
                }

                $('.form').find('.ipt').each(function() {
                    var len = this.value.length,
                            tips = eval('(' + $(this).data('msg') + ')'),
                            msg = '';

                    if ($(this).prop('disabled')) {
                        // do nothing
                    } else if (len == 0) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.empty;
                    } else if (len < 2 || len > 50) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.error;
                    }
                    $(this).next().html(msg)[msg == '' ? 'hide' : 'show']();
                    if (pass && msg != '') {
                        pass = false;
                        $(this).focus();
                    }
                })

                return pass;
            },
            submitForm: function() {
                var self = this;
                $('#submit1').on('click', function() {
                    if (self.formValidate()) {
                        alert('success')
                    } else {
                    }
                    return false;
                })
                // 不通过
                $('#submit2').on('click', function() {
                    var note = $('#note').val();
                    if (note != '') {
                        alert('success')
                    } else {
                        $('#note').focus();
                    }
                    return false;
                })
            }
        }
    }
    //加载页面js
    $(function() {
        _global.fn.init();
    });
</script>
</body>
</html>
