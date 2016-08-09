<#include "./inc/nav.ftl"/>

<!-- header start -->
<div class="header">
    <div class="wrap">
        <div class="logo">
            <a href="/">上工之选首页</a>
        </div>
        <div class="search">
            <div class="form">
                <form id="_search_form" action="commodity/search" method="get">
                    <input id="_search_ipt" class="ipt" name="keyword"  placeholder="请输入原药名称或饮片名称" value="${keyword!}" type="text">
                    <button  class="btn" type="submit">搜索</button>
                </form>
            </div>
            <div class="hotwords">
                <@search_keyword />
            </div>
        </div>
    </div>
</div><!-- header end -->


<!-- nav start -->
<!-- nav start -->
<div class="nav">
    <div class="wrap">
        <div class="cat cat-show">
            <div class="hd">商品分类</div>
            <div class="bd" id="jcat">
                <ul>
                    <li>
                        <div class="cat-name">根茎类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt><b>F</b>~<b>J</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt><b>K</b>~<b>O</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt><b>P</b>~<b>T</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt><b>U</b>~<b>Z</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>

                            <div class="more">
                                <a href="#" class="c-blue">查看更多 &gt;</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">果实籽仁类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">栀子</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">全草类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">鸡蛋花</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">花类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">龙胆</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">叶类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">树皮类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">淡竹叶</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">藤木类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">藤木类</a>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">树脂类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">龙胆</a>
                                    <a href="#">天花粉</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">菌藻类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">矿物类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">南天竹叶</a>
                                    <a href="#">密蒙花</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                    <li>
                        <div class="cat-name">动物类</div>
                        <div class="cat-list">
                            <dl>
                                <dt><b>A</b>~<b>E</b> &gt;</dt>
                                <dd>
                                    <a href="#">龙胆</a>
                                    <a href="#">天花粉</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="menu">
            <ul>
                <li><a href="/">首页</a></li>
                <li><a href="/commodity/index">产品</a></li>
                <li><a href="manufacturers.html">厂家</a></li>
                <li><a href="manufacturers.html">经方</a></li>
                <li><a href="manufacturers.html">功效</a></li>
            </ul>
        </div>
        <div class="plus">
            <a href="#" class="btn btn-gray"><i class="fa fa-question-circle"></i>快速询价</a>
        </div>
    </div>
</div><!-- nav end -->