<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>搜索结果-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl"/>

<div class="main-body">
    <div class="wrap">
    <#if (commodityDocPage??&&commodityDocPage.totalElements>0)>
        <div class="sitemap">
            <a href="#">搜索结果</a>
            <em>&gt;</em>
            <span>${keyword!}</span>
        </div>
        <div class="fa-pro-list">
            <table>
                <thead>
                <tr>
                    <th width="150"></th>
                    <th width="260">商品信息</th>
                    <th width="160">规格等级</th>
                    <th width="110">切制规格</th>
                    <th width="120">原药产地</th>
                    <th width="">执行标准</th>
                    <th width="140">操作</th>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                    <#list commodityDocPage.content as commodityDoc>
                        <tr>
                            <td><a href="/commodity/${commodityDoc.id!}"><img class="lazyload" src="images/blank.gif" data-original="${commodityDoc.pictureUrl!}" width="130" height="130" alt=""></a></td>
                            <td class="tl">
                                <div class="desc">
                                    <h3><a href="/commodity/${commodityDoc.id!}">${commodityDoc.name!}</a></h3>
                                    <p>${commodityDoc.title!}</p>
                                </div>
                            </td>
                            <td>${commodityDoc.level!}</td>
                            <td>${commodityDoc.spec!}</td>
                            <td>${commodityDoc.originOf!}</td>
                            <td>${commodityDoc.executiveStandard!}</td>
                            <td><a href="/center/enquiry/index?commodityId=${commodityDoc.id!}" class="btn btn-white btn-quote j_pop_login">立即询价</a></td>
                        </tr>
                    </#list>
                <#else>
                    <div class="fa-pro-empty">
                        <div class="fa fa-frown"></div>
                        <div class="text">
                            <h1 class="title">对不起，没有找到关于“${keyword!}”的商品！</h1>
                            <dl>
                                <dt>建议您：</dt>
                                <dd>1. 检查输入文字是否有误后重新搜索 </dd>
                                <dd>2. 使用 <a href="/center/enquiry/index">快速询价</a> 功能，提交您的采购单</dd>
                            </dl>
                        </div>
                    </div>
                </#if>
                </tbody>
            </table>
        </div>
        <#if commodityDocPage??>
            <@p.pager inPageNo=commodityDocPage.number pageSize=commodityDocPage.size recordCount=commodityDocPage.totalElements toURL="/commodity/search?keyword=${keyword}"/>
        </#if>
    </div>
</div>
    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>
    <script src="/js/layer/layer.js"></script>


</body>
</html>