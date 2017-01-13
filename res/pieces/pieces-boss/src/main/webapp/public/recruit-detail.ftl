<!DOCTYPE html>
<html lang="en">
<head>
    <title>合作伙伴申请详情-boss-上工好药</title>
<#include "./inc/meta.ftl"/>
</head>

<body>
<#include "./inc/header.ftl"/>
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>合作伙伴申请</dt>
                <dd>
                    <a class="curr" href="#!">详情</a>
                    <a href="/recruit/trail?recruitAgentId=${vo.id}">跟进记录</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <div class="title">
                <h3><i class="fa fa-chevron-right"></i>${vo.name} 合作申请</h3>
                <div class="extra">
                    <a href="/recruit/index" class="btn bvotn-gray">返回</a>
                </div>
            </div>

          <div class="user-info">
                <h3>联系方式</h3>
                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><em>联系人：</em>${vo.name}</td>
                        </tr>
                        <tr>
                            <td><em>电&#12288;话：</em>${vo.phone}</td>
                        </tr>
                        <tr>
                            <td><em>所属群：</em>${vo.area}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div><!-- fa-floor end -->
</div>
<#include "./inc/footer.ftl"/>

</body>
</html>