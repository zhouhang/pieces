<!DOCTYPE html>
<html lang="en">
<head>
    <title>询价跟踪记录-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
    <#include "./inc/header.ftl"/>
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>新客询价</dt>
                    <dd>
                        <a href="/anon/detail?id=${anonId}">询价信息</a>
                        <a class="curr" href="#">跟进记录</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>王彬 的询价信息</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray">返回</button>
                        <button type="button" id="submit" class="btn btn-red">保存</button>
                    </div>
                </div>
                <div class="chart-info">
                    <h3>询价商品</h3>
                    <div class="chart">
                        <table class="tc">
                            <thead>
                            <tr>
                                <th width="150">跟进时间</th>
                                <th class="tl">跟进结果</th>
                                <th>跟进人</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list list as record >
                            <tr>
                                <td>${record.createTime?datetime}</td>
                                <td class="tl">${record.result!}</td>
                                <td>${record.followerName!}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="chart-info">
                    <h3>最新跟进结果</h3>
                    <form action="" class="note-form">
                        <div class="cnt2">
                            <textarea class="ipt" name="" id="msg" cols="30" rows="10" placeholder="请填写跟进结果。"></textarea>
                        </div>
                    </form>
                </div>

            </div>
        </div><!-- fa-floor end -->
    </div>
    <#include "./inc/footer.ftl"/>
<script>
    $(function () {
        $("#submit").click(function(){
            var msg = $("#msg").val();
            $.post("/anon/trail",{result:msg,anonEnquiryId:${anonId}}, function (result) {
                if (result.status == "y") {
                    window.location.reload()
                }
            })
        })
    })
</script>
</body>
</html>