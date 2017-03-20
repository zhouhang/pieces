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
            <div class="title">
                <h3><i class="fa fa-people"></i>${user.companyFullName}</h3>
                <div class="extra">
                    <!--
                    <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                    <button type="reset" class="btn btn-gray" id="submit2">保存</button>
                    <button type="button" class="btn btn-red" id="submit1">保存并继续</button>
                    -->
                </div>
            </div>

            <div class="user-info">
                <h3>企业基础信息</h3>
                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><em>&#12288;企业名称：</em>${userCertification.company}</td>
                        </tr>
                        <tr>
                            <td><em>企业负责人：</em>${userCertification.corporation}</td>
                        </tr>
                        <tr>
                            <td><em>企业所在地：</em>${userCertification.address}</td>
                        </tr>
                        <tr>
                            <td><em>&#12288;企业类型：</em>${userCertification.typeText}</td>
                        </tr>
                        </tbody>
                    </table>
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
                            <#if qualification.number?exists>
                            <label>证件号：</label>
                            <span>${qualification.number}</span>
                            </#if>
                            <#if qualification.term?exists>
                            <label>有效期至：</label>
                            <span>${qualification.term!}</span>
                            </#if>
                        </div>
                    </div>
                </div>
                </#list>
            </div>
        </div>
    </div><!-- fa-floor end -->
</div>


<#include "./inc/footer.ftl"/>
<script src="${urls.getForLookupPath('/js/lightbox.js')}"></script>

<script>
    var _global = {
        fn: {
            init: function() {
                this.submitForm();
            },
            submitForm: function() {
                var self = this;
                // 保存并继续
                $('#submit1').on('click', function() {
                    alert('保存并继续')
                    return false;
                })
                // 保存
                $('#submit2').on('click', function() {
                    alert('保存')
                    return false;
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
