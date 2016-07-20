<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>商品详情-饮片B2B</title>
</head>

<body>
	<#include "./inc/header.ftl"/>
	<#include "./inc/nav.ftl"/>
    <div class="main-body">
        <div class="wrap">
            <div class="sitemap">
                <a href="#">商品分类</a>
                <em>&gt;</em>
                <a href="#">分类(根茎)</a>
                <em>&gt;</em>
                <a href="#">${commodity.categoryName}</a>
                <em>&gt;</em>
                <span>${commodity.name}</span>
            </div>

            <div class="hr"></div>

            <div class="side">
                <dl>
                    <dt>—— <em>为您推荐</em> ——</dt>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p2.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">白芍</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p3.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">向日葵花盘</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p2.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">白芍</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p3.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">向日葵花盘</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p2.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">白芍</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p3.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">向日葵花盘</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p2.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">白芍</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p3.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">向日葵花盘</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p2.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">白芍</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                    <dd>
                        <div class="pic">
                            <a href="product.html"><img src="uploads/p3.jpg" width="80" height="80" alt=""></a>
                        </div>
                        <div class="desc">
                            <h3><a href="product.html">向日葵花盘</a></h3>
                            <P>薄片</P>
                        </div>
                    </dd>
                </dl>
            </div>

            <div class="main">
                <div class="product-summary">
                    <div class="preview">
                        <img src="${commodity.pictureUrl}" width="360" height="360" alt="">
                    </div>
                    <div class="ext-info">
                        <h1 class="name">${commodity.name}</h1>
                        <div class="summary">
                            <ul>
                                <li>
                                    <div class="dt">切制规格</div>
                                    <div class="dd">${commodity.specName}</div>
                                </li>
                                <li>
                                    <div class="dt">原药产地</div>
                                    <div class="dd">${commodity.originOfName}</div>
                                </li>
                                <li>
                                    <div class="dt">外观描述</div>
                                    <div class="dd">${commodity.exterior}</div>
                                </li>
                                <li>
                                    <div class="dt">执行标准</div>
                                    <div class="dd">${commodity.executiveStandard}</div>
                                </li>
                                <li>
                                    <div class="dt">生产厂家</div>
                                    <div class="dd">${commodity.factory}</div>
                                </li>
                            </ul>
                        </div>
                        <div class="buttons">
                            <a class="btn btn-red" href="#">询价</a>
                            <a class="btn btn-gray" href="#"><i class="fa fa-heart"></i>收藏</a>
                        </div>
                    </div>
                </div>
                <div class="product-detail">
                    <div class="tab">
                        <span class="on">详细信息</span>
                    </div>
                    <div class="tab-cont">
                        ${commodity.details}
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="helper">
        <div class="wrap">
            <dl>
                <dt>新手指南</dt>
                <dd><a href="#">用户注册</a></dd>
                <dd><a href="#">注册协议</a></dd>
                <dd><a href="#">视频介绍</a></dd>
            </dl>
            <dl>
                <dt>产品询价</dt>
                <dd><a href="#">寻找商品</a></dd>
                <dd><a href="#">在线询价</a></dd>
                <dd><a href="#">一键下单</a></dd>
            </dl>
            <dl>
                <dt>交期管理</dt>
                <dd><a href="#">验货签收</a></dd>
                <dd><a href="#">交货周期</a></dd>
                <dd><a href="#">用户确认</a></dd>
            </dl>
            <dl>
                <dt>增值服务</dt>
                <dd><a href="#">关于发票</a></dd>
                <dd><a href="#">统计分析</a></dd>
                <dd><a href="#">在线对账</a></dd>
            </dl>
            <dl>
                <dt>服务说明</dt>
                <dd><a href="#">隐私声明</a></dd>
                <dd><a href="#">产权保护</a></dd>
                <dd><a href="#">法律声明</a></dd>
            </dl>
        </div>
    </div>
</body>
</html>