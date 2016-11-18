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
                        <a href="/user/edit/${user.id}">账户信息</a>
                        <a class="curr" href="/user/certify/${user.id}">企业资质</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-people"></i>${user.userName}</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <!--
                            <button type="reset" class="btn btn-gray">重置</button>
                            <button type="submit" class="btn btn-red">保存</button>
                            -->
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
                                    <input type="text" class="ipt" value="${qualification.typeText}" autocomplete="off" name="" id="" placeholder="">
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    证件号：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${qualification.number}" autocomplete="off" name="" id="" placeholder="">
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    有效期
                                </div>
                                <#if qualification.status=1>
                                    <div class="cnt">
                                        <input type="text" class="ipt" value="长期" autocomplete="off" name="" id="" placeholder="">
                                    </div>
                                <#else>
                                    <div class="cnt">
                                        <input type="text" class="ipt" value="${qualification.term}" autocomplete="off" name="" id="" placeholder="">
                                    </div>
                                </#if>

                            </div>
                            <div class="group">
                                <div class="txt">
                                    证件照片：
                                </div>
                                <div class="cnt cnt-mul">
                                    <div class="goods-img">
                                         <img src="${qualification.pictureUrl}"><i class="del"></i>
                                    </div>
                                    <input type="hidden" value="${qualification.pictureUrl}" id="imgUrl">
                                </div>
                            </div>
                        </div>
                      </#list>
                    </div>
                </form>
            </div>
        </div>
        </div>
        <!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->

    <script src="js/jquery.min.js"></script>
    <script src="js/validform.min.js"></script>


<script>
    $(function() {
        $(function() {
        })

    })
</script>
</body>
</html>