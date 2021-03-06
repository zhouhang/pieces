<!DOCTYPE html>
<html lang="en">
<head>
    <title>询价详情-boss-上工好药</title>
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
                        <a class="curr" href="#!">询价信息</a>
                        <a href="/anon/trail?anonId=${vo.id}">跟进记录</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>${vo.contacts} 的询价信息</h3>
                    <div class="extra">
                        <a href="/anon/enquiry" class="btn btn-gray">返回</a>
                    </div>
                </div>
                <div class="chart-info">
                    <h3>询价商品</h3>
                    <div class="chart">
                        <table class="tc">
                            <thead>
                                <tr>
                                    <th>品名</th>
                                    <th>片型</th>
                                    <th>数量（公斤）</th>
                                </tr>
                            </thead>
                            <tbody id="commodity_body"></tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="3" class="tl">
                                        附件：
                                    <#if vo.files?exists && vo.files?size gt 0 >
                                        <#list vo.files as file>
                                            <a href="/anon/download?url=${file.attachmentUrl!}&fileName=${file.content?default("询价文件"+ file_index)}">${file.content?default("询价文件"+ file_index)}</a>
                                        </#list>
                                    <#else>
                                        无
                                    </#if>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>

                <div class="user-info">
                    <h3>联系方式</h3>
                    <div class="info">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>联系人：</em>${vo.contacts}</td>
                            </tr>
                            <tr>
                                <td><em>电&#12288;话：</em>${vo.phone}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div><!-- fa-floor end -->
    </div>
    <#include "./inc/footer.ftl"/>
<script>
    var _global = {
        v:{
            commodity:<#if vo.detail?exists>${vo.detail.content!}</#if>
        },
        fn:{
            init: function() {
                this.initCommodity();
            },
            initCommodity:function(){
                var html = [];
                $.each(_global.v.commodity, function (k,v) {
                   html.push('<tr><td>', v.val1, '</td><td>', v.val2, '</td><td>', v.val3, '</td></tr>');
                })
                $('#commodity_body').html(html.join(''));
            }
        }
    }
    $(function(){
        _global.fn.init();
    })
</script>
</body>
</html>