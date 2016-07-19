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


                </tbody>
            </table>
        </div>

        <div class="pagin">
            <span class="disabled">上一页</span>
            <span class="curr">1</span>
            <a href="?page=2">2</a>
            <a href="?page=3">3</a>
            <a href="?page=4">4</a>
            <a href="?page=5">5</a>
            <a href="?page=2">下一页</a>
            <a href="?page=2">尾页</a>
            <em>共 284 个商品 / 共29页 / 跳转到第</em>
            <input class="ipt" type="text" onkeydown="javascript:if(event.keyCode==13){page_jump();return false;}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" value="1" maxlength="4" id="jPageSkip" >页
            <button class="btn btn-gray" type="button" onclick="page_jump();">确定</button>
        </div>

    </div>
</div>

</body>
</html>