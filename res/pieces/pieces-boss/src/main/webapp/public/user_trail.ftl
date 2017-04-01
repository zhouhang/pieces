<!DOCTYPE html>
<html lang="en">
<head>
    <title>客户跟踪记录-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
    <#include "./inc/header.ftl"/>
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>客户信息</dt>
                    <dd>
                        <a  href="/user/info/${user.id}">客户界面</a>
                        <a  href="/user/edit/${user.id}">账户信息</a>
                    <#if user.type==1>
                        <a  href="/user/certify/${user.id}">企业资质</a>
                    </#if>
                        <a class="curr" href="/user/trail/${user.id}">跟进记录</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title title-btm">
                    <h3><i class="fa fa-chevron-right"></i>${user.contactName} 的跟进信息</h3>
                    <div class="extra">
                        <a href="/user/index" class="btn btn-gray">返回</a>
                        <button type="button" id="submit" class="btn btn-red">保存</button>
                    </div>
                </div>

                <div class="pagin mb20">
                <@p.pager pageInfo=records pageUrl="/user/trail/${user.id}" params=""/>
                </div>

                <div class="chart-info">
                    <h3>跟进记录</h3>

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
                            <#list records.list as record >
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
            $.post("/user/trail",{result:msg,userId:${user.id}}, function (result) {
                if (result.status == "y") {
                    window.location.reload()
                }
            })
        })
    })
</script>
</body>
</html>