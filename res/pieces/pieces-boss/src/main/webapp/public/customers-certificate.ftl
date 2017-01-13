<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>账户信息-boss-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>客户信息</dt>
                    <dd>
                        <a href="/user/info/${user.id}">客户界面</a>
                        <a  href="/user/edit/${user.id}">账户信息</a>
                        <#if user.type==1>
                        <a class="curr" href="/user/certify/${user.id}">企业资质</a>
                        </#if>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-people"></i>hehuan</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <button type="reset" class="btn btn-gray">重置</button>
                            <button type="button" class="btn btn-red" id="submit1">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>企业基础信息</h3>
                        <div class="fa-form">
                            <input class="ipt" name="id" id="cid" value="${userCertification.id}"  type="hidden">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>企业名称：
                                </div>
                                <div class="cnt">
                                    <input class="ipt" value="${userCertification.company}" autocomplete="off" name="company" id="companyName" placeholder="营业执照上的企业名称全称" type="text">
                                    <span class="error1"></span>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>企业负责人：
                                </div>
                                <div class="cnt">
                                    <input class="ipt" value="${userCertification.corporation}" autocomplete="off" name="corporation" id="legalPerson" placeholder="营业执照上法人姓名" type="text">
                                    <span class="error1"></span>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>企业所在地：
                                </div>
                                <div class="cnt">
                                    <input class="ipt" value="${userCertification.address}" autocomplete="off" name="address" id="companyRegion" placeholder="营业执照上的企业所在地地址" type="text">
                                    <span class="error1"></span>
                                </div>
                            </div>
                            <div class="group group-cbx">
                                <div class="txt">
                                    <i>*</i>企业类型：
                                </div>
                                <div class="cnt">
                                    <label><input type="radio" name="type" class="cbx" value="1" data-type="0,3,4" <#if userCertification.type==1>checked</#if> >单体药店</label>
                                    <label><input type="radio" name="type" class="cbx" value="2" data-type="" <#if userCertification.type==2>checked</#if> >连锁药店</label>
                                    <label><input type="radio" name="type" class="cbx" value="3" data-type=""<#if userCertification.type==3>checked</#if> >公立医院</label>
                                    <label><input type="radio" name="type" class="cbx" value="4" data-type=""<#if userCertification.type==4>checked</#if> >民营医院</label>
                                    <label><input type="radio" name="type" class="cbx" value="5" data-type=""<#if userCertification.type==5>checked</#if> >个体诊所</label>
                                    <label><input type="radio" name="type" class="cbx" value="6" data-type="" <#if userCertification.type==6>checked</#if> >社区医疗机构</label>
                                </div>
                                <span class="error1" id="categoryError"></span>
                            </div>
                        </div>
                    </div>
                    <div class="user-info">
                        <h3>企业资质</h3>
                        <div class="tab" id="combineTab">
                            <span class="curr">企业三证</span>
                            <span>三证合一</span>
                            <strong><em>提示：</em>如果客户的营业执照是老证，选择企业三证，如果是新证，选择三证合一。</strong>
                        </div>
                        <div class="tabcont fa-form">
                            <div class="box" type="1" cid="">
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
                                        <input class="ipt date" value="" autocomplete="off" name="term" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                        <span class="error1"></span>
                                        <label><input type="checkbox" name="status" class="cbx">长期</label>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        <i>*</i>证件照片：
                                    </div>
                                    <div class="cnt">
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="box" type="4" cid="">
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
                                        <input class="ipt date" value="" autocomplete="off" name="term" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                        <span class="error1"></span>
                                        <label><input type="checkbox" name="status" class="cbx">长期</label>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        <i>*</i>证件照片：
                                    </div>
                                    <div class="cnt">
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="box" type="5" cid="">
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
                                        <input class="ipt date" value="" autocomplete="off" name="term" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                        <span class="error1"></span>
                                        <label><input type="checkbox" name="status" class="cbx">长期</label>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        <i>*</i>证件照片：
                                    </div>
                                    <div class="cnt">
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="box" type="2" cid="">
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
                                        <input class="ipt date" value="" autocomplete="off" name="term" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                        <span class="error1"></span>
                                        <label><input type="checkbox" name="status" class="cbx">长期</label>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        <i>*</i>证件照片：
                                    </div>
                                    <div class="cnt">
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        资质变更记录表：
                                    </div>
                                    <div class="cnt thumb">
                                        <span class="goods-img upimgs"></span>
                                        <input type="hidden" name="picture_alter" class="ipt">
                                        <p class="tips">如果您变更过企业资质，请上传资质变更记录表。</p>
                                    </div>
                                </div>
                            </div>

                            <div class="box" type="3" cid="">
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
                                        <input class="ipt date" value="" autocomplete="off" name="term" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                        <span class="error1"></span>
                                        <label><input type="checkbox" name="status" class="cbx">长期</label>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        <i>*</i>证件照片：
                                    </div>
                                    <div class="cnt">
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        资质变更记录表：
                                    </div>
                                    <div class="cnt thumb">
                                        <span class="goods-img upimgs"></span>
                                        <input type="hidden" name="picture_alter" class="ipt">
                                        <p class="tips">如果您变更过企业资质，请上传资质变更记录表。</p>
                                    </div>
                                </div>
                            </div>

                            <div class="box" type="6" cid="">
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
                                        <i>*</i>有效期：
                                    </div>
                                    <div class="cnt">
                                        <input class="ipt date" value="" autocomplete="off" name="term" placeholder="" type="text" data-msg="{empty: '请输入证件有效期', error: '证件号有效期长度2到50个字符！'}">
                                        <span class="error1"></span>
                                        <label><input type="checkbox" name="status" class="cbx">长期</label>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        <i>*</i>证件照片：
                                    </div>
                                    <div class="cnt">
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                                <div class="group group-up">
                                    <div class="txt">
                                        资质变更记录表：
                                    </div>
                                    <div class="cnt thumb">
                                        <span class="goods-img upimgs"></span>
                                        <input type="hidden" name="picture_alter" class="ipt">
                                        <p class="tips">如果您变更过企业资质，请上传资质变更记录表。</p>
                                    </div>
                                </div>
                            </div>
                            <div class="box" type="7" cid="">
                                <div class="group group-val">
                                    <div class="txt">
                                        <i>*</i>证件名称：
                                    </div>
                                    <div class="cnt">
                                        <span class="val">采购人员授权委托书</span>
                                    </div>
                                </div>
                                <div class="group group-a">
                                    <div class="txt">
                                        <i>*</i>有效期：
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
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="box" type="8" cid="">
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
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="box" type="9" cid="">
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
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="box" type="10" cid="">
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
                                        <span class="goods-img upimg thumb"></span>
                                        <input type="hidden" name="picture_url" class="ipt" data-msg="{empty: '请上传证件照片'}">
                                        <span class="error1"></span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
        <!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    <script src="js/lightbox.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/croppic.min.js"></script>
    <script src="js/laydate/laydate.js"></script>

<script>
    var group=0;//是否三证合一
    !(function($) {
        var page = {
            v: {
                id: "page",
                saveUrl:"/user/certify/save"
            },
            fn: {
                init: function () {
                    this.certificate();
                    this.bindEvent();
                    this.submitForm();
                    this.upfileImg();
                    this.dateInit();
                },
                certificate: function() {
                    var $input = $('#myform').find('.cbx[name="type"]:checked'),
                            $box = $('.tabcont').find('.box'),
                            $myform = $('#myform'),
                            $tab = $('.tab');

                    $box.hide();
                    switch($input.parent().index()) {
                        case 0:
                            $myform.find(".box[type!='6']").show();
                            $tab.show();
                            $myform.find("#combineTab :contains(企业三证)").click();
                            break;
                        case 1:
                            $myform.find(".box[type!='6']").show();
                            $tab.show();
                            $myform.find("#combineTab :contains(企业三证)").click();
                            break;
                        case 2:
                            $myform.find(".box[type='6']").show();
                            $myform.find(".box[type='9']").show();
                            $myform.find(".box[type='7']").show();
                            $myform.find(".box[type='10']").show();
                            $tab.hide();
                            break;
                        case 3:
                        case 5:
                            $myform.find(".box[type='1']").show();
                            $myform.find(".box[type='4']").show();
                            $myform.find(".box[type='5']").show();
                            $myform.find(".box[type='6']").show();
                            $myform.find(".box[type='7']").show();
                            $myform.find(".box[type='9']").show();
                            $myform.find(".box[type='10']").show();
                            $tab.show();
                            $myform.find("#combineTab :contains(企业三证)").click();
                            break;
                        case 4:
                            $myform.find(".box[type='1']").show();
                            $myform.find(".box[type='4']").show();
                            $myform.find(".box[type='5']").show();
                            $myform.find(".box[type='6']").show();
                            $myform.find(".box[type='7']").show();
                            $myform.find(".box[type='10']").show();
                            $tab.show();
                            $myform.find("#combineTab :contains(企业三证)").click();
                            break;
                            //  no default
                    };

                    <#if userQualification?size!=0>
                    <#list userQualification as q>
                        var showBox=$("#myform").find(".box[type='${q.type}']");
                        showBox.attr("cid",'${q.id}');
                        showBox.find("input[name='number']").val('${q.number}');
                        showBox.find("input[name='term']").val('${q.term}');
                        <#if q.status==1>
                            showBox.find("input[name='status']").prop("checked",true);
                            showBox.find("input[name='term']").prop('disabled', true);
                        </#if>
                        <#if q.isCombine==1>
                            group=1;
                        <#else>
                            group=0;
                        </#if>
                        var $picture=showBox.find(".upimg");
                        var $pictures=showBox.find(".thumb");
                        var others=[];
                        var otherPics="";
                        <#list q.pictures as pics>
                            var url='${pics.pictureUrl?replace("\\",'/')}';
                        <#if pics.indexNum==0>
                            $picture.show().html('<img src="' + url + '" /><i class="del" title="删除"></i>');
                            $picture.next().val(url);
                        <#else >
                            otherPics=otherPics+'<span class="goods-img upimgs"><img src="' + url + '" /><i class="del" title="删除"></i></span>';
                            others.push(url);
                        </#if>
                        </#list>
                            var addPic=$pictures.find('.upimgs');
                            $pictures.find('.upimgs').before(otherPics);
                            <#if q.pictures?size==4>
                                addPic.remove();
                            </#if>
                            $pictures.find('input:hidden').val(others.join("<>"));
                    </#list>
                    </#if>


                },
                bindEvent: function() {
                    var self = this;
                    $myform = $('#myform'),
                            $box = $('.box');

                    // 三证合一
                    $myform.on('click', '.tab span', function() {
                        $(this).addClass('curr').siblings().removeClass('curr');
                        //$box.hide();
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
                    if(group==1){
                        $myform.find("#combineTab :contains(三证合一)").click();
                    }

                    // 长期
                    $myform.on('click', '.cbx', function() {
                        var $ipt = $(this).closest('.cnt').find('.ipt');
                        $ipt.prop('disabled', this.checked).val('').next().hide();
                    })

                    // 类型
                    $myform.find('.cbx[name="type"]').on('click', function() {
                        self.certificate();
                    })

                    // blur
                    $myform.on('blur', '.box .ipt', function() {
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
                    msg && $input.focus();
                    return msg === '';
                },
                checklegalPerson: function() {
                    var $input = $('#legalPerson'),
                            length = $input.val().length,
                            msg = '';
                    if (length == 0) {
                        msg = '<i class="fa fa-prompt"></i> 请输入企业负责姓名';
                    } else if (length < 2 || length > 50) {
                        msg = '<i class="fa fa-prompt"></i> 企业责任人长度2-50位';
                    }
                    $input.next().html(msg)[msg == '' ? 'hide' : 'show']();
                    msg && $input.focus();
                    return msg === '';
                },
                checkcompanyRegion: function() {
                    var $input = $('#companyRegion'),
                            length = $input.val().length,
                            msg = '';
                    if (length == 0) {
                        msg = '<i class="fa fa-prompt"></i> 请输入企业所在地地址';
                    } else if (length < 2 || length > 50) {
                        msg = '<i class="fa fa-prompt"></i> 企业责任人长度2-150位';
                    }
                    $input.next().html(msg)[msg == '' ? 'hide' : 'show']();
                    msg && $input.focus();
                    return msg === '';
                },
                checkcategory: function() {
                    var $input = $('#myform').find('.cbx[name="type"]:checked'),
                            length = $input.length,
                            msg = '';
                    if (length == 0) {
                        msg = '<i class="fa fa-prompt"></i> 请选择企业类型';
                    }
                    $('#categoryError').html(msg)[msg == '' ? 'hide' : 'show']();
                    return msg === '';
                },
                formValidate: function() {
                    var $myform = $('#myform'),
                            $ipts = $myform.find('.box:visible .ipt'),
                            pass = true;

                    if (!this.checkcompanyRegion()) {
                        pass = false;
                    }
                    if (!this.checklegalPerson()) {
                        pass = false;
                    }
                    if (!this.checkCompName()) {
                        pass = false;
                    }
                    this.checkcategory();

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
                    var boxs = $('#myform').find('.box');
                    $('#submit1').on('click', function() {
                        if (self.formValidate()) {
                            var certifyParamVo={};
                            var userCertificationVo={};
                            var userQualificationVos=[];
                            userCertificationVo.id=$("#cid").val();
                            userCertificationVo.userId='${user.id}';
                            userCertificationVo.company=$("#companyName").val();
                            userCertificationVo.corporation=$("#legalPerson").val();
                            userCertificationVo.address=$("#companyRegion").val();
                            userCertificationVo.type=$('#myform').find('.cbx[name="type"]:checked').val();
                            boxs.each(function() {
                                if($(this).attr("style")=="display: none;"){
                                    return;
                                }
                                var userQualification={};
                                userQualification.id=$(this).attr('cid');
                                userQualification.userId='${user.id}';
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
                            certifyParamVo.userCertificationVo=userCertificationVo;
                            certifyParamVo.userQualificationVos=userQualificationVos;
                            $.ajax({
                                url: page.v.saveUrl,
                                data: JSON.stringify(certifyParamVo),
                                type: "POST",
                                contentType: "application/json; charset=utf-8",
                                success: function (data) {
                                    var status = data.status;
                                    var info = data.info;
                                    if (status == 'y') {
                                        $.notify({
                                            type: 'success',
                                            title: '保存成功',
                                            text: '资质保存成功',
                                            delay: 3e3,
                                            call: function () {
                                                $("#submit1").attr("disabled", "disabled");
                                            }
                                        });
                                    }
                                }
                            });

                        } else {
                        }
                        return false;
                    })
                },
                // 商品图片
                upfileImg: function() {
                    var self = this,
                        split = '<>',
                        wait = false,
                        type = 1,
                        $el,
                        $upimg = $('.goods-img');

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
                        //$(this).next().val('');
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
                        uploadUrl:'gen/upload',
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
                                $el.empty().show().before('<span class="goods-img upimgs"><img src="' + response.url + '"><i class="del" title="删除"></i></span>');
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
        //加载页面js
        $(function() {
            page.fn.init();
        });
    })(jQuery);
</script>
</body>
</html>