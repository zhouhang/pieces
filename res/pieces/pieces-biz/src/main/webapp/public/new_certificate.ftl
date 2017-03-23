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
            <form action="" id="myform">
            <div class="certificate">
                <div class="type">
                    <label><i>*</i>企业类型：</label>
                    <label><input type="radio" name="type" value="1" class="cbx" checked>药店</label>
                    <label><input type="radio" name="type" value="2" class="cbx">医疗机构</label>
                    <label><input type="radio" name="type" value="3" class="cbx">制药企业</label>
                    <label><input type="radio" name="type" value="4" class="cbx">医药公司</label>
                </div>

                <div class="floor" type="6">
                    <h3>医疗机构经营许可证</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传医疗机构经营许可证图片，如果做过资质变更，还需要上传资质变更记录表图片，变更记录表最多能传3张。
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="1">
                    <h3>营业执照副本</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传企业营业执照副本图片，如果是老版的营业执照，还需要上传组织机构代码证以及税务登记证图片。
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="2">
                    <h3>GSP证书</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传GSP证书图片。
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="2">
                    <h3>GMP证书</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传GMP证书图片。
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="3">
                    <h3>药品经营许可证</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传药品经营许可证图片，如果做过资质变更，还需要上传资质变更记录表图片，变更记录表最多能传3张。
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="3">
                    <h3>药品生产许可证</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传药品生产许可证图片，如果做过资质变更，还需要上传资质变更记录表图片，变更记录表最多能传3张。
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="7">
                    <h3>法人授权委托书</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传采购人员的法人授权委托书图片，如果采购人员与收货人员不是同一个人，还需要上传收货人员授权委托书图片。</br>
                                    <a href="javascript:;" class="c-blue thumb">查看示例<img src="${urls.getForLookupPath('/images/1.jpg')}" /></a>
                                    <a href="/doc/1.docx" target="_blank" class="c-blue">下载模板</a>
                                </span>
                        </div>
                    </div>
                </div>
                <div class="floor" type="8">
                    <h3>印鉴章备案</h3>
                    <div class="check">
                        <div class="pic thumb required">
                            <span class="up-img"></span>
                        </div>
                        <div class="form">
                            <i></i>
                            <span>
                                    <em>说明</em>
                                    请上传贵单位的印鉴章备案图片。</br>
                                    <a href="javascript:;" class="c-blue thumb">查看示例<img src="${urls.getForLookupPath('/images/2.jpg')}" /></a>
                                    <a href="/doc/2.docx" target="_blank" class="c-blue">下载模板</a>
                                </span>
                        </div>
                    </div>
                </div>

                <div class="button">
                    <button type="button" class="btn btn-red" id="submit">提交</button>
                </div>
                </form>

            </div>
        </div>

    </div>
</div><!-- member-box end -->


<#include "./inc/footer.ftl"/>
<script src="${urls.getForLookupPath('/js/lightbox.js')}"></script>
<script src="${urls.getForLookupPath('/js/croppic.min.js')}"></script>
<script>
    var _global = {
        v: {
            submitUrl:'/user/submit',
        },
        fn: {
            init: function() {
                this.formValidate();
                this.iniForm();
                this.chooseType();
                this.zoomImg();
                this.goodsImg();
            },
            iniForm: function() {
                <#if userCertification?exists>
                    $("input:radio[value='${userCertification.type}']").click();
                    <#if userQualification>
                    <#list userQualification as qualification>
                        var imgctrl= $(".floor:visible[type='${qualification.type}']");
                        var htmltext="";
                        <#list qualification.pictures as picture>
                            var htmltext='<div class="up-img">' +
                                    '<img src="${picture.pictureUrl}">' +
                                    '<i class="del"></i>' +
                                    '<input type="hidden" value="${picture.pictureUrl}"></div>'+htmltext;


                        </#list>
                        imgctrl.find(".up-img").before(htmltext);
                    </#list>
                    </#if>
                </#if>

            },
            chooseType: function() {
                var $floor = $('.floor');

                // 0.医疗机构许可证
                // 1.营业执照副本
                // 2.GSP证书
                // 3.GMP证书
                // 4.药品经营许可证
                // 5.药品生产许可证
                // 6.法人授权委托书
                // 7.印鉴章备案

                $('.certificate').find('.type').on('click', '.cbx', function() {
                    $floor.hide().find('h3 em').remove();
                    $floor.hide().find('span.error').remove();
                    if (this.value == 1) {
                        // 药店
                        $floor.eq(1).show();
                        $floor.eq(1).show().find(".pic").attr("class","pic thumb required");
                        $floor.eq(4).show();
                        $floor.eq(2).show();
                        $floor.eq(6).show();

                    } else if (this.value == 2) {
                        // 医疗机构
                        $floor.eq(0).show();
                        $floor.eq(1).show().find(".pic").attr("class","pic thumb");
                        $floor.eq(1).show().find('h3').append('<em>（盈利性医疗机构必填）</em>');
                        $floor.eq(6).show();

                    } else if (this.value == 3) {
                        // 制药企业
                        $floor.eq(1).show();
                        $floor.eq(1).show().find(".pic").attr("class","pic thumb required");
                        $floor.eq(3).show();
                        $floor.eq(5).show();
                        $floor.eq(6).show();

                    } else {
                        // 医药公司
                        $floor.eq(1).show();
                        $floor.eq(1).show().find(".pic").attr("class","pic thumb required");
                        $floor.eq(2).show();
                        $floor.eq(2).show().find(".pic").attr("class","pic thumb required");
                        $floor.eq(4).show();
                        $floor.eq(4).show().find(".pic").attr("class","pic thumb required");
                        $floor.eq(6).show();
                    }
                })
                $('.certificate').find('.type .cbx:checked').trigger('click');
            },
            zoomImg: function() {
                $('.form').on('click', '.c-blue', function() {
                    $(this).find('img').trigger('click');
                })
            },
            // 商品图片
            goodsImg: function() {
                var self = this;
                $('body').append('<div id="upload" style="position:fixed;bottom:0;left:0;width:0;height:0;visibility:hidden;"></div>');

                // 删除图片
                $('.pic').on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除图片？', function(index){
                        $self.closest('.pic').find('span.up-img').show();
                        $self.parent().remove();
                        layer.close(index);
                    });
                    return false;
                })

                // 上传图片
                $('.pic').on('click', '.up-img', function() {
                    if (self.wait) {
                        return;
                    }

                    if ($(this).siblings().length > 3) {
                        $.notify({
                            type: 'error',
                            title: '最多只能上传4张图片',
                            delay: 3e3
                        });
                        return;
                    }

                    self.$el = $(this);
                    $('#upload').find('.cropControlUpload').trigger('click');
                })

                this.upImg();
            },
            upImg: function() {
                var self = this;
                var options = {
                    uploadUrl:'/gen/img/upload',
                    onBeforeImgUpload: function() {
                        self.wait = true;
                        self.$el.html('<span class="loader">图片上传中...</span>');
                    },
                    onAfterImgUpload: function(response) {
                        self.$el.empty('').before('<div class="up-img"><img src="' + response.url + '"/><i class="del"></i><input type="hidden" value="' + response.url + '" /></div>');
                        self.$el.siblings().length > 3 && self.$el.hide(); // 上传超过4张后隐藏
                        self.wait = false;
                    },
                    onError:function(msg){
                        $.notify({
                            type: 'error',
                            title: '上传图片失败',   // 不允许的文件类型
                            text: msg,     //'支持 jpg、jepg、png、gif等格式图片文件',
                            delay: 3e3
                        });
                        self.$el.empty('');
                        self.wait = false;
                    }
                }
                var cropModal = new Croppic('upload', options);
            },
            formValidate: function() {
                var self = this,
                    abled = true;

                $('#submit').on('click', function() {
                    var pass = self.checkImg();
                    var boxs = $('#myform').find('.floor:visible');
                    if (pass && abled) {
                        //如果检验通过
                        var certifyDataVo={};
                        certifyDataVo.type=$(" #myform input[name='type']:checked").val();
                        var userQualificationVos=[];
                        boxs.each(function() {
                            if($(this).attr("style")=="display: none;"){
                                return;
                            }
                            var userQualification={};
                            var pics=[];
                            userQualification.type=$(this).attr('type');
                            $(this).find(".up-img").each(function (index) {
                                var url=$(this).find("input[type='hidden']").val();
                                var pic={};
                                if(url!=undefined){
                                    pic.pictureUrl=url;
                                    pic.indexNum=index;
                                    pics.push(pic);
                                }

                            });
                            if(pics.length!=0){
                                userQualification.pictures=pics;
                                userQualificationVos.push(userQualification);
                            }
                        });
                        certifyDataVo.userQualificationVos=userQualificationVos;
                        abled = false;
                        $.ajax({
                            url: _global.v.submitUrl,
                            data: JSON.stringify(certifyDataVo),
                            type: "POST",
                            contentType: "application/json; charset=utf-8",
                            success: function (data) {
                                var status = data.status;
                                var info = data.info;
                                if (status == 'y') {
                                    window.location.href = '/user/submitSuccess';
                                    return;
                                }
                            },
                            complete: function() {
                                abled = true;
                            }
                        });
                    }
                })
            },
            checkImg: function() {
                var pass = true;
                $('.floor:visible').find('.required').each(function() {
                    if ($(this).find('input').length === 0) {
                        pass = false;
                        $(this).append('<span class="error">请上传证件照片</span>');
                    }
                })
                return pass;
            }
          }
        }
    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>
