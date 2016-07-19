<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>搜索结果-饮片B2B</title>

</head>

<body>
    <#include "./inc/header.ftl"/>
    <#include "./inc/nav.ftl"/>

<div class="main-body">
    <div class="wrap">
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
                    <th width="240">
                        <div class="drop-dowm">
                            <div class="hd">
                                <span>商品信息</span></i>
                            </div>
                        </div>
                    </th>
                    <th>
                        <div class="drop-dowm">
                            <div class="hd">
                                <span>切割规格</span></i>
                            </div>
                        </div>
                    </th>
                    <th>
                        <div class="drop-dowm">
                            <div class="hd">
                                <span>原药产地</span></i>
                            </div>
                        </div>
                    </th>
                    <th width="190">
                        <div class="drop-dowm">
                            <div class="hd">
                                <span>执行标准</span></i>
                            </div>
                        </div>
                    </th>
                    <th width="180">
                        <div class="drop-dowm">
                            <div class="hd">
                                <span>生产厂家</span></i>
                            </div>
                        </div>
                    </th>
                    <th width="140">操作</th>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#if commodityDocPage??>
                    <#list commodityDocPage.content as commodityDoc>
                        <tr>
                            <td><a href="product.html"><img src="${commodityDoc.pictureUrl!}" width="130" height="130" alt=""></a></td>
                            <td class="tl">
                                <div class="desc">
                                    <h3><a href="product.html">${commodityDoc.name!}</a></h3>
                                    <p>${commodityDoc.exterior!}</p>
                                </div>
                            </td>
                            <td>${commodityDoc.spec!}</td>
                            <td>${commodityDoc.originOf!}</td>
                            <td class="tl">${commodityDoc.executiveStandard!}</td>
                            <td>${commodityDoc.factory!}</td>
                            <td><a class="btn btn-white btn-quote" href="product.html">立即询价</a></td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
        <#if commodityDocPage??>
            <@p.pager inPageNo=commodityDocPage.number pageSize=commodityDocPage.size recordCount=commodityDocPage.totalElements toURL="/pro/search?keyword=${keyword}"/>
        </#if>
    </div>
</div>

</body>
</html>