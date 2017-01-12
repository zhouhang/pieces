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
            <#if certificationVo.type!=3>
            <div class="tab">
                <span class="curr">企业三证</span>
                <span>三证合一</span>
                <strong><em>提示：</em>如果您的营业执照是老证，选择企业三证，如果是新证，选择三证合一。</strong>
            </div>
            </#if>
            <div class="tabcont fa-form">
                   <#if certificationVo.type!=3>
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
                                <i>*</i>有效期至：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img upimg thumb"></span>
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
                                <i>*</i>有效期至：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term"  type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img upimg thumb"></span>
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
                                <i>*</i>有效期至：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term"  type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img upimg thumb"></span>
                                <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                    </div>
                   </#if>
                   <#if certificationVo.type==1||certificationVo.type==2>
                    <div class="box" type="2">
                        <div class="group group-val">
                            <div class="txt">
                                <i>*</i>证件名称：
                            </div>
                            <div class="cnt">
                                <span class="val">药品GMP/GSP证书及变更记录</span>
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
                                <i>*</i>有效期至：
                            </div>
                            <div class="cnt">
                                <input class="ipt date" value="" autocomplete="off" name="term"  type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                <span class="error1"></span>
                                <label><input type="checkbox" name="status" class="cbx">长期</label>
                            </div>
                        </div>

                        <div class="group group-up">
                            <div class="txt">
                                <i>*</i>证件照片：
                            </div>
                            <div class="cnt">
                                <span class="up-img upimg thumb"></span>
                                <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                <span class="error1"></span>
                            </div>
                        </div>
                        <div class="group group-up">
                            <div class="txt">
                                资质变更记录表：
                            </div>
                            <div class="cnt thumb">
                                <span class="up-img upimgs"></span>
                                <input type="hidden" name="picture_alter" class="ipt">
                                <p class="notice">如果您变更过企业资质，请上传资质变更记录表。</p>
                            </div>
                        </div>
                    </div>
                       <div class="box" type="3">
                           <div class="group group-val">
                               <div class="txt">
                                   <i>*</i>证件名称：
                               </div>
                               <div class="cnt">
                                   <span class="val">药品生产/经营许可证及变更记录</span>
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
                                   <i>*</i>有效期至：
                               </div>
                               <div class="cnt">
                                   <input class="ipt date" value="" autocomplete="off" name="term"  type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                   <span class="error1"></span>
                                   <label><input type="checkbox" name="status" class="cbx">长期</label>
                               </div>
                           </div>
                           <div class="group group-up">
                               <div class="txt">
                                   <i>*</i>证件照片：
                               </div>
                               <div class="cnt">
                                   <span class="up-img upimg thumb"></span>
                                   <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                   <span class="error1"></span>
                               </div>
                           </div>
                            <div class="group group-up">
                                <div class="txt">
                                    资质变更记录表：
                                </div>
                                <div class="cnt thumb">
                                    <span class="up-img upimgs"></span>
                                    <input type="hidden" name="picture_alter" class="ipt">
                                    <p class="notice">如果您变更过企业资质，请上传资质变更记录表。</p>
                                </div>
                            </div>
                       </div>
                       <div class="box" type="8">
                           <div class="group group-val">
                               <div class="txt">
                                   <i>*</i>证件名称：
                               </div>
                               <div class="cnt">
                                   <span class="val">印鉴章备案</span>
                               </div>
                           </div>
                           <div class="group group-up">
                               <div class="txt">
                                   <i>*</i>证件照片：
                               </div>
                               <div class="cnt">
                                   <span class="up-img upimg thumb"></span>
                                   <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                   <span class="error1"></span>
                               </div>
                           </div>
                       </div>
                   <#else>
                       <div class="box" type="6">
                           <div class="group group-val">
                               <div class="txt">
                                   <i>*</i>证件名称：
                               </div>
                               <div class="cnt">
                                   <span class="val">医疗机构经营许可证及变更记录</span>
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
                                   <i>*</i>有效期至：
                               </div>
                               <div class="cnt">
                                   <input class="ipt date" value="" autocomplete="off" name="term"  type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                   <span class="error1"></span>
                                   <label><input type="checkbox" name="status" class="cbx">长期</label>
                               </div>
                           </div>
                           <div class="group group-up">
                               <div class="txt">
                                   <i>*</i>证件照片：
                               </div>
                               <div class="cnt">
                                   <span class="up-img upimg thumb"></span>
                                   <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                   <span class="error1"></span>
                               </div>
                           </div>
                           <div class="group group-up">
                               <div class="txt">
                                   资质变更记录表：
                               </div>
                               <div class="cnt thumb">
                                   <span class="up-img upimgs"></span>
                                   <input type="hidden" name="picture_alter" class="ipt">
                                   <p class="notice">如果您变更过企业资质，请上传资质变更记录表。</p>
                               </div>
                           </div>
                       </div>
                   </#if>
                   <#if certificationVo.type!=5>
                <div class="box" type="9">
                    <div class="group group-val">
                        <div class="txt">
                            <i>*</i>证件名称：
                        </div>
                        <div class="cnt">
                            <span class="val">开票信息</span>
                        </div>
                    </div>
                    <div class="group group-up">
                        <div class="txt">
                            <i>*</i>证件照片：
                        </div>
                        <div class="cnt">
                            <span class="up-img upimg thumb"></span>
                            <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                            <span class="error1"></span>
                        </div>
                    </div>
                </div>
                   </#if>
                <div class="box" type="7">
                    <div class="group group-val">
                        <div class="txt">
                            <i>*</i>证件名称：
                        </div>
                        <div class="cnt">
                            <span class="val">采购/收货人员委托书</span>
                        </div>
                    </div>
                    <div class="group group-a">
                        <div class="txt">
                            <i>*</i>有效期至：
                        </div>
                        <div class="cnt">
                            <input class="ipt date" value="" autocomplete="off" name="term"  type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                            <span class="error1"></span>
                            <label><input type="checkbox" name="status" class="cbx">长期</label>
                        </div>
                    </div>
                    <div class="group group-up">
                        <div class="txt">
                            <i>*</i>证件照片：
                        </div>
                        <div class="cnt">
                            <span class="up-img upimg thumb"></span>
                            <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                            <span class="error1"></span>
                        </div>
                    </div>
                </div>
                <div class="box" type="10">
                    <div class="group group-val">
                        <div class="txt">
                            <i>*</i>证件名称：
                        </div>
                        <div class="cnt">
                            <span class="val">被委托人身份证正反面</span>
                        </div>
                    </div>
                    <div class="group group-up">
                        <div class="txt">
                            <i>*</i>证件照片：
                        </div>
                        <div class="cnt">
                            <span class="up-img upimg thumb"></span>
                            <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                            <span class="error1"></span>
                        </div>
                    </div>
                </div>



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
<script src="js/layer/layer.js"></script>
<script src="js/croppic.min.js"></script>
<script src="js/lightbox.js"></script>
<script src="js/laydate/laydate.js"></script>
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
                this.upfileImg();
                this.dateInit();
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
                        group=0;
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
                        if($(this).attr("name")!="picture_alter") {
                            msg = '<i class="fa fa-prompt"></i> ' + tips.empty;
                        }
                    }
                    else if (len < 2 || len > 50) {
                        if($(this).attr("name")!="picture_url"&&$(this).attr("name")!="picture_alter"){
                            msg = '<i class="fa fa-prompt"></i> ' + tips.error;
                        }
                    }
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
                        if($(this).attr("name")!="picture_alter") {
                            msg = '<i class="fa fa-prompt"></i> ' + tips.empty;
                        }
                    }

                    else if (len < 2 || len > 50) {
                        if($(this).attr("name")!="picture_url"&&$(this).attr("name")!="picture_alter"){
                            msg = '<i class="fa fa-prompt"></i> ' + tips.error;
                        }

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
                            if(userQualification.type!="7"){
                                userQualification.number=$(this).find("input[name='number']").val();
                            }
                            if(userQualification.status=$(this).find("input[name='status']").is(':checked')){
                                userQualification.status=1;
                                userQualification.term="";
                            }
                            else{
                                userQualification.status=0;
                                userQualification.term=$(this).find("input[name='term']").val();
                            }

                            //userQualification.pictureUrl=$(this).find("input[name='picture_url']").val();
                            var firstUrl=$(this).find("input[name='picture_url']").val();
                            var pics=[];
                            var pic={};
                            pic.pictureUrl=firstUrl;
                            pic.indexNum=0;
                            pics.push(pic);
                            var split = '<>';
                            var otherUrl=$(this).find("input[name='picture_alter']").val();
                            if(otherUrl&&otherUrl!=""){
                                var urls=otherUrl.split(split);
                                if(urls.length>0){
                                    $.each(urls,function(index,item){
                                        var pic1={};
                                        pic1.pictureUrl=item;
                                        pic1.indexNum=index+1;
                                        pics.push(pic1);
                                    });
                                }

                            }
                            userQualification.pictures=pics;
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
            dateInit: function () {
                $('#myform .date').each(function(i) {
                    var id ='date_' + i;
                    this.id = id;
                    laydate({
                        elem: '#' + id,
                        choose: function() {
                            $('#' + id).trigger('blur');
                        }
                    });
                })
            },
            // 商品图片
            upfileImg: function() {
                var self = this,
                    split = '<>',
                    wait = false,
                    type = 1,
                    $el,
                    $upimg = $('.up-img');

                // 多图删除
                $('.thumb').on('click', '.upimgs .del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除图片？', function(index) {
                        var $ipt = $self.closest('.thumb').find('input:hidden'),
                            url = $self.prev().attr('src'),
                            originImg = (split + $ipt.val()).replace(split + url, '').replace(split, '');
                        $ipt.val(originImg).prev().show();;
                        $self.parent().remove();
                        layer.close(index);
                    });
                })

                // 删除图片
                $upimg.on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除图片？', function(index){
                        $self.parent().empty().next(':hidden').val('');
                        layer.close(index);
                    });
                    return false;
                })

                $upimg.each(function() {
                    $(this).next().val('');
                    $(this).on('click', function() {
                        if (wait) {
                            return;
                        }
                        type = $(this).hasClass('upimgs') ? 2 : 1;
                        if (type === 2 && $(this).next().val().split(split).length > 2) {
                            $el = $('<div></div>');
                            $(this).hide();
                            $.notify({
                                type: 'error',
                                title: '最多只能添加3张变更记录表',
                                delay: 3e3
                            });
                            return false;
                        }
                        $el = $(this);
                        $('#upImgForm').find('.cropControlUpload').trigger('click');
                    })
                })

                $('body').append('<div id="upImgForm" style="position:fixed;bottom:0;left:0;visibility:hidden;"></div>');


                var upfile = new Croppic('upImgForm', {
                    uploadUrl:'/gen/img/upload',
                    onBeforeImgUpload: function() {
                        wait = true;
                        $el.html('<span class="loader">图片上传中...</span>');
                    },
                    onAfterImgUpload: function(response){
                        if (type === 1) {
                            $el.show().html('<img src="' + response.url + '" /><i class="del" title="删除"></i>').next().val(response.url).trigger('validate');
                        } else {
                            var $ipt = $el.next();
                            var originImg = $ipt.val();
                            $el.empty().show().before('<span class="up-img upimgs"><img src="' + response.url + '"><i class="del" title="删除"></i></span>');
                            if (originImg) {
                                originImg += split + response.url;
                            } else {
                                originImg = response.url;
                            }
                            if (originImg.split(split).length > 2) {
                                $el.hide();
                            }
                            $ipt.val(originImg);
                        }
                        wait = false;
                    },
                    onError: function(msg) {
                        $el.html('<span class="upimg-msg">' + msg + '</span>');
                        wait = false;
                    }
                });
            }
        }
    }
    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>
