<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>上工之选</title>
</head>

<#include "./inc/header.ftl"/>

<!-- banner start -->
<div class="banner-slider" id="jslide">
    <div class="bd">
        <ul>
            <#list AD_BANNER as ad>
                <li style="background-color:#0074d4;">
                    <a title="${ad.title!}" style="background-image:url(${ad.pictureUrl!})" href="${ad.link!"#!"}"></a>
                </li>
            </#list>
        </ul>
    </div>
    <div class="hd"></div>
    <div class="side-notice">
        <h3>服务公告</h3>
        <ul>
            <#list articles as article>
                <li><a href="#">${article.title!}</a></li>
            </#list>
        </ul>

        <h3>知名厂家</h3>
        <div class="brands">
            <div class="inner">
                <div class="wrapper">
                    <div class="col">
                        <a href="#"><img src="images/brand-jhjt.png" alt=""></a>
                        <a href="#"><img src="images/brand-yonggang.png" alt=""></a>
                        <a href="#"><img src="images/brand-jrt.png" alt=""></a>
                        <a href="#"><img src="images/brand-wsc.png" alt=""></a>
                    </div>
                    <div class="col">
                        <a href="#"><img src="images/brand-jhjt.png" alt=""></a>
                        <a href="#"><img src="images/brand-yonggang.png" alt=""></a>
                        <a href="#"><img src="images/brand-jrt.png" alt=""></a>
                        <a href="#"><img src="images/brand-wsc.png" alt=""></a>
                    </div>
                </div>
            </div>
            <div class="ctrl">
                <i class="prev">&lt;</i><i class="next">&gt;</i>
            </div>
        </div>
    </div>
</div><!-- banner end -->

<div class="activity">
    <div class="wrap">
        <ul>
            <li>
                <a href="#">
                    <div class="txt">
                        <h4>盐巴戟天</h4>
                        <p>规格：段</p>
                        <p><span class="t-orange">热销</span></p>
                    </div>
                    <div class="img">
                        <img src="uploads/a1.jpg" width="200" height="180">
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="txt">
                        <h4>盐巴戟天</h4>
                        <p>规格：段</p>
                        <p><span class="t-blue">热销</span></p>
                    </div>
                    <div class="img">
                        <img src="uploads/a2.jpg" width="200" height="180">
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="txt">
                        <h4>盐巴戟天</h4>
                        <p>规格：段</p>
                        <p><span class="t-pink">热销</span></p>
                    </div>
                    <div class="img">
                        <img src="uploads/a3.jpg" width="200" height="180">
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="txt">
                        <h4>盐巴戟天</h4>
                        <p>规格：段</p>
                        <p><span class="t-green">热销</span></p>
                    </div>
                    <div class="img">
                        <img src="uploads/a4.jpg" width="200" height="180">
                    </div>
                </a>
            </li>
        </ul>
    </div>
</div>

<div class="wrap idx-main" id="lazyDom">
    <!-- start -->
    <div class="idx-floor" id="floor0">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>根茎类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-01.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <!-- start -->
    <div class="idx-floor" id="floor1">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>果实籽仁类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-02.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <div class="wide-banner">
        <a href="#"><img src="images/blank.gif" width="1200" height="90" data-original="uploads/banner-1200.jpg"></a>
    </div>

    <!-- start -->
    <div class="idx-floor" id="floor2">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>全草类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-03.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <!-- start -->
    <div class="idx-floor" id="floor3">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>花/叶类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-04.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <div class="wide-banner">
        <a href="#"><img src="images/blank.gif" width="1200" height="90" data-original="uploads/banner-1200.jpg"></a>
    </div>

    <!-- start -->
    <div class="idx-floor" id="floor4">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>树皮类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-01.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <!-- start -->
    <div class="idx-floor" id="floor5">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>藤木类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-05.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <div class="wide-banner">
        <a href="#"><img src="images/blank.gif" width="1200" height="90" data-original="uploads/banner-1200.jpg"></a>
    </div>

    <!-- start -->
    <div class="idx-floor" id="floor6">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>矿物菌脂类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-06.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->

    <!-- start -->
    <div class="idx-floor" id="floor7">
            <textarea style="display:none;" autocomplete="off">
                <div class="idx-hd">
                    <h2>动物类</h2>
                </div>
                <div class="idx-bd">
                    <div class="cat">
                        <ul>
                            <li><a href="#">麻黄</a></li>
                            <li><a href="#">肉苁蓉</a></li>
                            <li><a href="#">丁公藤</a></li>
                            <li><a href="#">地骨皮</a></li>
                            <li><a href="#">白鲜皮</a></li>
                            <li><a href="#">穿破石</a></li>
                            <li><a href="#">马齿苋</a></li>
                            <li><a href="#">葱白</a></li>
                            <li><a href="#">鹿药</a></li>
                            <li><a href="#">萱草根</a></li>
                            <li><a href="#">糯稻根</a></li>
                            <li><a href="#">菝葜</a></li>
                        </ul>
                        <div class="img">
                            <img src="uploads/cat-1.jpg" width="200" height="270">
                        </div>
                    </div>

                    <div class="pro">
                        <dl>
                            <dt>
                                <a href="#"><img src="uploads/pro-02.jpg" width="400" height="270"></a>
                            </dt>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-01.jpg" width="180" height="176"></a>
                                <a href="#">白芍</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-02.jpg" width="180" height="176"></a>
                                <a href="#">炙黄芪</a>
                                <span>切制规格：斜薄片</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-03.jpg" width="180" height="176"></a>
                                <a href="#">炒莲子(白)</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-04.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-05.jpg" width="180" height="176"></a>
                                <a href="#">藏红花</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-06.jpg" width="180" height="176"></a>
                                <a href="#">西洋参</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-07.jpg" width="180" height="176"></a>
                                <a href="#">烫白及</a>
                                <span>切制规格：段</span>
                            </dd>
                            <dd>
                                <a href="#"><img src="uploads/pro-01-08.jpg" width="180" height="176"></a>
                                <a href="#">盐巴戟天</a>
                                <span>切制规格：段</span>
                            </dd>
                        </dl>
                    </div>
                </div>
            </textarea>
    </div><!-- end -->
</div>

<#include "./inc/helper.ftl"/>
<#include "./inc/footer.ftl"/>

<div class="elevator" id="jelevator">
    <ul>
        <li class="curr"><a href="#floor0">根茎类</a></li>
        <li><a href="#floor1">果实籽仁类</a></li>
        <li><a href="#floor2">全草类</a></li>
        <li><a href="#floor3">花/叶类</a></li>
        <li><a href="#floor4">树皮类</a></li>
        <li><a href="#floor5">藤木类</a></li>
        <li><a href="#floor6">矿物菌脂类</a></li>
        <li><a href="#floor7">动物类</a></li>
    </ul>
</div>

<div class="gotop" id="jgotop">
    <a href="javascript:;">返回顶部</a>
</div>

<script src="js/jquery.nav.js"></script>
<script src="js/common.js"></script>
<script src="js/index_2016.js"></script>
</body>
</html>