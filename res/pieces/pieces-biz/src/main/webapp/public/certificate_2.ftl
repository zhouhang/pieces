<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>企业资质认证-上工好药</title>
</head>

<body>

<#include "./inc/header-center.ftl"/>


<!-- member-box start -->
<div class="member-box">
    <div class="wrap">
    <#include "./inc/side-center.ftl"/>
        <div class="main">
            <div class="title">
                <h3>企业资质认证</h3>
                <div class="extra"></div>
            </div>

            <div class="fa-step">
                <ol>
                    <li><em>1</em>填写基础信息</li>
                    <li><i class="fa fa-chevron-right"></i></li>
                    <li class="curr"><em>2</em>上传企业资质，等待平台审核</li>
                    <li><i class="fa fa-chevron-right"></i></li>
                    <li><em>3</em>完成</li>
                </ol>
            </div>
            <form action="" id="myform">
                <#if certificationVo.type==1||certificationVo.type==2>
            <div class="tab">
                <span class="curr">企业三证</span>
                <span>三证合一</span>
                <strong><em>提示：</em>如果客户的营业执照是老证，选择企业三证，如果是新证，选择三证合一。</strong>
            </div>
            <div class="tabcont fa-form">
                    <div class="box" type="1">
                        <div class="group group-val">
                            <div class="txt">
                                <i>*</i>证件名称：
                            </div>
                            <div class="cnt">
                                <span class="val">营业执照</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>证件号：
                            </div>
                            <div class="cnt">
                                <input class="ipt" value="" autocomplete="off" name="number" placeholder="" type="text" data-msg="{empty: '请输入证件号', error: '证件号字符长度2到50个字符！'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                        <div class="group group-a">
                            <div class="txt">
                                <i>*</i>有效期：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term" placeholder="2016/1/1 - 2016/5/8" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img thumb"></span>
                                <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                    </div>
                    <div class="box" type="4">
                        <div class="group group-val">
                            <div class="txt">
                                <i>*</i>证件名称：
                            </div>
                            <div class="cnt">
                                <span class="val">组织机构代码证</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>证件号：
                            </div>
                            <div class="cnt">
                                <input class="ipt" value="" autocomplete="off" name="number" placeholder="" type="text" data-msg="{empty: '请输入证件号', error: '证件号字符长度2到50个字符！'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                        <div class="group group-a">
                            <div class="txt">
                                <i>*</i>有效期：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term" placeholder="2016/1/1 - 2016/5/8" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img thumb"></span>
                                <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                    </div>
                    <div class="box" type="5">
                        <div class="group group-val">
                            <div class="txt">
                                <i>*</i>证件名称：
                            </div>
                            <div class="cnt">
                                <span class="val">税务登记证</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>证件号：
                            </div>
                            <div class="cnt">
                                <input class="ipt" value="" autocomplete="off" name="number" placeholder="" type="text" data-msg="{empty: '请输入证件号', error: '证件号字符长度2到50个字符！'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                        <div class="group group-a">
                            <div class="txt">
                                <i>*</i>有效期：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term" placeholder="2016/1/1 - 2016/5/8" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img thumb"></span>
                                <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                    </div>
                    <div class="box" type="2">
                        <div class="group group-val">
                            <div class="txt">
                                <i>*</i>证件名称：
                            </div>
                            <div class="cnt">
                                <span class="val">GSP/GMP证书</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>证件号：
                            </div>
                            <div class="cnt">
                                <input class="ipt" value="" autocomplete="off" name="number" placeholder="" type="text" data-msg="{empty: '请输入证件号', error: '证件号字符长度2到50个字符！'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                        <div class="group group-a">
                            <div class="txt">
                                <i>*</i>有效期：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term" placeholder="2016/1/1 - 2016/5/8" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img thumb"></span>
                                <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                    </div>
                   <#else>
                   <div class="tabcont fa-form">
                       <div class="box" type="3">
                           <div class="group group-val">
                               <div class="txt">
                                   <i>*</i>证件名称：
                               </div>
                               <div class="cnt">
                                   <span class="val">医疗机构执业许可证</span>
                               </div>
                           </div>
                           <div class="group">
                               <div class="txt">
                                   <i>*</i>证件号：
                               </div>
                               <div class="cnt">
                                   <input class="ipt" value="" autocomplete="off" name="number" placeholder="" type="text" data-msg="{empty: '请输入证件号', error: '证件号字符长度2到50个字符！'}">
                                   <span class="error1"></span>
                               </div>
                           </div>
                           <div class="group group-a">
                               <div class="txt">
                                   <i>*</i>有效期：
                               </div>
                               <div class="cnt">
                                   <input class="ipt date" value="" autocomplete="off" name="term" placeholder="2016/1/1 - 2016/5/8" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                   <span class="error1"></span>
                                   <label><input type="checkbox" name="status" class="cbx">长期</label>
                               </div>
                           </div>
                           <div class="group group-up">
                               <div class="txt">
                                   <i>*</i>证件照片：
                               </div>
                               <div class="cnt">
                                   <span class="up-img thumb"></span>
                                   <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                   <span class="error1"></span>
                               </div>
                           </div>
                       </div>
                       </div>
                    </#if>

                    <div class="tabcont fa-form">
                        <div class="button">
                            <button type="button" class="btn btn-red" id="submit1">提交</button>
                        </div>
                    </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div><!-- member-box end -->


<#include "./inc/footer.ftl"/>

<script src="js/common.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/croppic.min.js"></script>
<script src="js/lightbox.js"></script>
<script>
    var group=0;//是否三证合一
    var _global = {
        v: {
            stepTwoUrl:'/center/certificate/stepTwo',
            stepThreeUrl:'/center/certificate/stepThree'
        },
        fn: {

            init: function() {
                this.bindEvent();
                this.submitForm();
                this.goodsImg();
            },
            bindEvent: function() {
                var $myform = $('#myform');

                // 三证合一
                $('.tab').on('click', 'span', function() {
                    $(this).addClass('curr').siblings().removeClass('curr');
                    if ($(this).index() === 1) {
                        $myform.find(".box[type='4']").hide();
                        $myform.find(".box[type='5']").hide();
                        group=1;

                    } else {
                        $myform.find(".box[type='4']").show();
                        $myform.find(".box[type='5']").show();
                        group=0
                    }
                })

                // 长期
                $myform.on('click', '.cbx', function() {
                    var $ipt = $(this).closest('.cnt').find('.ipt');
                    $ipt.prop('disabled', this.checked).val('').next().hide();
                })

                // blur
                $myform.on('blur', '.ipt', function() {
                    var len = this.value.length,
                            tips = eval('(' + $(this).data('msg') + ')'),
                            msg = '';

                    if (len == 0) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.empty;
                    }
                    /*else if (len < 2 || len > 50) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.error;
                    }*/
                    $(this).next().html(msg)[msg == '' ? 'hide' : 'show']();
                })
            },
            formValidate: function() {
                var $myform = $('#myform'),
                        $ipts = $myform.find('.box:visible .ipt'),
                        pass = true;

                $ipts.each(function() {
                    var len = this.value.length,
                            tips = eval('(' + $(this).data('msg') + ')'),
                            msg = '';

                    if ($(this).prop('disabled')) {
                        // do nothing
                    } else if (len == 0) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.empty;
                    }
                    /*
                    else if (len < 2 || len > 50) {
                        msg = '<i class="fa fa-prompt"></i> ' + tips.error;
                    }*/
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
                var boxs = $('#myform').find('.box:visible');
                $('#submit1').on('click', function() {
                    if (self.formValidate()) {
                        var userQualificationVos=[];
                        boxs.each(function() {
                            if($(this).attr("style")=="display: none;"){
                               return;
                            }
                            var userQualification={};
                            userQualification.type=$(this).attr('type');
                            userQualification.number=$(this).find("input[name='number']").val();
                            if(userQualification.status=$(this).find("input[name='status']").is(':checked')){
                                userQualification.status=1;
                                userQualification.term="";
                            }
                            else{
                                userQualification.status=0;
                                userQualification.term=$(this).find("input[name='term']").val();
                            }

                            userQualification.pictureUrl=$(this).find("input[name='picture_url']").val();
                            userQualificationVos.push(userQualification);
                            userQualification.isCombine=group;
                        });
                        $.ajax({
                            url: _global.v.stepTwoUrl,
                            data: JSON.stringify(userQualificationVos),
                            type: "POST",
                            contentType: "application/json; charset=utf-8",
                            success: function (data) {
                                var status = data.status;
                                var info = data.info;
                                if (status == 'y') {
                                    window.location.href = _global.v.stepThreeUrl;
                                    return;
                                }
                            }
                        });


                    } else {

                    }
                    return false;
                })
            },
            // 商品图片
            goodsImg: function() {
                var self = this,
                        $upImg = $('.up-img');

                // 删除图片
                $upImg.on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除商品图片？', {
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        $self.parent().empty().next(':hidden').val('');
                        layer.close(index);
                    });
                    return false;
                })
                // 点击图片无效
                $upImg.on('click', 'img', function() {
                    return false;
                })

                // 证件照
                $upfiles = $('<div id="upfiles"></div>').hide().appendTo($('body'));
                $upImg.each(function(i) {
                    var $el = $(this),
                            upId = 'upfile' + i,
                            id = 'upfileBtn' + i;

                    this.id = id;
                    $upfiles.append('<div id="' + upId + '"></div>');

                    new Croppic(upId, {
                        uploadUrl:'gen/img/upload',
                        customUploadButtonId: id,
                        onAfterImgUpload: function(response){
                            $el.show().html('<img src="' + response.url + '" /><i class="del" title="删除"></i>');
                            $el.next('input:hidden').val(response.url).trigger('blur');
                        },
                        onError:function(msg){
                            $.notify({
                                type: 'error',
                                title: msg.title,   // 不允许的文件类型
                                text: msg.message,     //'支持 jpg、jepg、png、gif等格式图片文件',
                                delay: 3e3
                            });
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
