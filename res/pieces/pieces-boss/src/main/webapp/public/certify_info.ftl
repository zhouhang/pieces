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
                <h3><i class="fa fa-people"></i>hehuan的企业资质审核</h3>
                <div class="extra">
                    <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                    <button type="reset" class="btn btn-gray">不通过</button>
                    <button type="submit" class="btn btn-red">通过</button>
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
                            <span class="val">武汉同济医院</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业负责人：
                        </div>
                        <div class="cnt">
                            <span class="val">何先生</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业所在地：
                        </div>
                        <div class="cnt">
                            <span class="val">湖北省武汉市XX区XX路XXX号</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业类型：
                        </div>
                        <div class="cnt">
                            <span class="val">公立医院</span>
                        </div>
                    </div>

                </div>
            </div>
            <div class="user-info">
                <h3>企业资质</h3>
                <div class="fa-form">
                    <div class="group">
                        <div class="txt">
                            证件名称：
                        </div>
                        <div class="cnt">
                            <span class="val">医疗机构执业许可证</span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            证件号：
                        </div>
                        <div class="cnt">
                            <span class="val">XXXXXXXXXX</span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            有效期
                        </div>
                        <div class="cnt">
                            <span class="val">长期</span>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">
                            证件照片：
                        </div>
                        <div class="cnt cnt-mul">
                            <div class="goods-img thumb">
                                <img src="uploads/p0.jpg" data-src="uploads/p0.jpg">
                            </div>
                            <input type="hidden" value="" id="imgUrl">
                        </div>
                    </div>
                </div>
            </div>


            <div class="chart-info">
                <h3>跟进结果</h3>
                <form action="" class="note-form">
                    <p class="tips"><i>*</i>判断为不通过时要填写原因。</p>
                    <div class="cnt2">
                        <textarea class="ipt" name="" id="" cols="30" rows="10" placeholder="请填写跟进结果。"></textarea>
                    </div>
                </form>
            </div>
        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>

<script src="js/jquery.min.js"></script>
<script src="js/validform.min.js"></script>
<script src="js/lightbox.js"></script>
<script>
    $(function() {

    })
</script>
</body>
</html>
