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
                    <#if certifyRecord.status==0>
                    <button type="button" class="btn btn-gray" id="notpass">不通过</button>
                    <button type="button" class="btn btn-red" id="pass">通过</button>
                    </#if>
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
                            <span class="val">${userCertification.company}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业负责人：
                        </div>
                        <div class="cnt">
                            <span class="val">${userCertification.corporation}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业所在地：
                        </div>
                        <div class="cnt">
                            <span class="val">${userCertification.address}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业类型：
                        </div>
                        <div class="cnt">
                            <span class="val">${userCertification.typeText}</span>
                        </div>
                    </div>

                </div>
            </div>
            <div class="user-info">
                <h3>企业资质</h3>
                <#list userQualification as qualification>
                <div class="fa-form">
                    <div class="group">
                        <div class="txt">
                            证件名称：
                        </div>
                        <div class="cnt">
                            <span class="val">${qualification.typeText}</span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            证件号：
                        </div>
                        <div class="cnt">
                            <span class="val">${qualification.number}</span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            有效期
                        </div>
                        <#if qualification.status=1>
                        <div class="cnt">
                            <span class="val">长期</span>
                        </div>
                        <#else>
                            <div class="cnt">
                                <span class="val">${qualification.term}</span>
                            </div>
                        </#if>


                    </div>
                    <div class="group">
                        <div class="txt">
                            证件照片：
                        </div>
                        <div class="cnt cnt-mul">
                            <div class="goods-img thumb">
                                <img src="${qualification.pictureUrl}" data-src="${qualification.pictureUrl}">
                            </div>
                            <input type="hidden" value="" id="imgUrl">
                        </div>
                    </div>
                </div>
                </#list>
            </div>


            <div class="chart-info">
                <h3>跟进结果</h3>
                <form action="" class="note-form">
                    <p class="tips"><i>*</i>判断为不通过时要填写原因。</p>
                    <div class="cnt2">
                        <textarea class="ipt" name="" value="" id="result" cols="30" rows="10" placeholder="请填写跟进结果。">${certifyRecord.result}</textarea>
                    </div>
                </form>
            </div>
        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<script>
    $(function() {
           var recordId=${certifyRecord.id};
           var userId=${certifyRecord.userId};
           $("#pass").click(function(){
               var status=1;
               $.ajax({
                   url: "/certify/handle",
                   data: {"id":recordId,"userId":userId,"status":status},
                   type: "POST",
                   success: function(data){
                       $.notify({
                           type: 'success',
                           title: data.info,
                           delay: 3e3
                       });
                       if(data.status=="y"){
                           $("#pass").hide();
                           $("#notpass").hide();
                       }

                   }
               });
           });
        $("#notpass").click(function(){
            var status=2;
            var result=$("#result").val();
            if(result==""){
                $.notify({
                    type: 'error',
                    title: "请填写原因",
                    delay: 3e3
                });
                return;
            }
            $.ajax({
                url: "/certify/handle",
                data: {"id":recordId,"userId":userId,"result":result,"status":status},
                type: "POST",
                success: function(data){
                    $.notify({
                        type: 'success',
                        title: data.info,
                        delay: 3e3
                    });
                    if(data.status=="y"){
                        $("#pass").hide();
                        $("#notpass").hide();
                    }
                }
            });
        });


    })
</script>
</body>
</html>
