<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>广告信息-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl"/>


<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>广告信息</dt>
                <dd>
                    <a class="curr" href="ad/index">广告信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <form action="" id="myform">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i><#if ad??>修改"${ad.title!}"<#else>新增广告</#if></h3>
                    <div class="extra">
                        <a  class="btn btn-gray" href="ad/index">返回</a>
                        <#if ad??>
                        <button id="delete" type="button" class="btn btn-gray">删除</button>
                        </#if>
                        <button type="submit" class="btn btn-red">保存</button>
                    </div>
                </div>
                <input type="hidden" class="ipt" id="adId" name="id"  value="<#if ad??>${ad.id!}</#if>" autocomplete="off" placeholder="">
                <div class="user-info">
                    <h3>广告信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>分类：
                            </div>
                            <div class="cnt">
                                <select name="typeId" id="" class="wide">
                                    <#list typeList as type>
                                        <option <#if ad??&&(ad.typeId==type.id)> selected</#if> value="${type.id!}">${type.title!}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>标题：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="title" value="<#if ad??>${ad.title!}</#if>" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>开始时间：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="startTime" id="start" value="<#if ad??>${ad.startTime?string("yyyy-MM-dd HH:mm:ss")}</#if>" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>到期时间：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="endTime" id="end" value="<#if ad??>${ad.endTime?string("yyyy-MM-dd HH:mm:ss")}</#if>" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                图片：
                            </div>
                            <div class="cnt cnt-mul">
                                <div class="goods-img" id="imgCrop">
                                    <#if ad??&&ad.pictureUrl??&&ad.pictureUrl!="">
                                     <img src="${ad.pictureUrl}"><i class="del"></i>
                                    </#if>
                                </div>
                                <input type="hidden" name="pictureUrl" id="imgUrl" value="">
                                <div id="imgCropWrap"></div>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                背景颜色：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="color" value="<#if ad??>${ad.color!}</#if>" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                链接：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="link" value="<#if ad??>${ad.link!}</#if>" autocomplete="off" placeholder="">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                排序：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" name="sort" value="<#if ad??>${ad.sort!}</#if>" autocomplete="off" placeholder="请输入数字，数字越大显示越靠前">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>状态：
                            </div>
                            <div class="cnt">
                                <select name="status" id="" class="wide">
                                    <option <#if ad?? && ad.status>selected</#if> value="true">激活</option>
                                    <option <#if ad?? && !ad.status>selected</#if> value="false">禁用</option>
                                </select>
                            </div>
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

<script src="js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script src="js/laydate/laydate.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/croppic.min.js"></script>
<script src="js/common.js"></script>
<script>
    var _global = {
        v: {},
        fn: {
            init: function() {
                this.dateInit();
                this.formValidate();
                this.goodsImg();
                this.delete();
            },
            //日期选择
            dateInit: function () {
                var start = {
                    elem: '#start',
                    format: 'YYYY-MM-DD hh:mm:ss',
                    min: laydate.now(),
                    istime: true,
                    choose: function(datas){
                        end.min = datas;
                        end.start = datas;
                        $('#start').trigger('validate');
                    }
                };
                var end = {
                    elem: '#end',
                    format: 'YYYY-MM-DD hh:mm:ss',
                    min: laydate.now(),
                    istime: true,
                    choose: function(datas){
                        start.max = datas;
                        $('#end').trigger('validate');
                    }
                };
                laydate(start);
                laydate(end);
            },
            formValidate: function() {
                $('#myform').validator({
                    fields: {
                        title: 'required',
                        status: 'required',
                        startTime: 'required',
                        endTime: 'required',
                    },
                    valid: function(form) {
                        if ( $(form).isValid() ) {
                            $.ajax({
                                url: 'ad/save',
                                data: $(form).serialize(),
                                type: 'POST',
                                success: function(result){
                                    if(result.status=="y"){
                                        $("#adId").val(result.data.id)
                                        $.notify({
                                            type: 'success',
                                            title: '操作成功',
                                            text: result.info,
                                            delay: 3e3
                                        });
                                    }else{
                                        $.notify({
                                            type: 'error',
                                            title: '操作失败',
                                            text: result.info,
                                            delay: 3e3
                                        });
                                    }

                                }
                            });
                        }
                    }
                });
            },
            // 商品图片
            goodsImg: function() {
                var self = this;
                // 删除图片
                $('.goods-img').on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除图片？', {
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        $self.parent().empty().next().val('none');
                        layer.close(index);
                        self.upImg();
                    });
                    return false;
                })
                // 点击图片看大图
                $('.goods-img').on('click', 'img', function() {
                    var url = this.src;
                    window.open(url);
                    return false;
                })
                $('#imgCrop').find('img').length === 0 && self.upImg();
            },
            upImg: function() {
                var options = {
                    uploadUrl:'/gen/upload',
                    customUploadButtonId: 'imgCrop',
                    onAfterImgUpload: function(response){
                        cropModal.destroy();
                        $('#imgCrop').html('<img src="' + response.url + '" title="点击图片看大图" /><i class="del" title="删除"></i>')
                                .next().val(response.url);
                    },
                    onError:function(msg){
                        $.notify({
                            type: 'error',
                            title: "上传错误",   // 不允许的文件类型
                            text: msg.info,     //'支持 jpg、jepg、png、gif等格式图片文件',
                            delay: 3e3
                        });
                    }
                }
                var cropModal = new Croppic('imgCropWrap', options);
            },
            delete: function() {
                var iid = $("#adId").val();
                $('#delete').on('click', function() {
                    layer.confirm('确认删除该广告？', {icon: 3, title:'提示'}, function(index){
                        layer.close(index);
                        $.ajax({
                            url: "/ad/delete?id=" + iid,
                            type: "POST",
                            success: function(data){
                                if(data.status == "y"){
                                    $.notify({
                                        type: 'success',
                                        title: data.info,
                                        text: '3秒后自动跳转到广告列表页',
                                        delay: 3e3,
                                        call: function() {
                                            setTimeout(function() {
                                                location.href = '/ad/index';
                                            }, 3e3);
                                        }
                                    });
                                }else{
                                    $.notify({
                                        type: 'error',
                                        title: data.info,
                                        delay: 3e3
                                    });
                                }
                            }
                        });
                    });
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