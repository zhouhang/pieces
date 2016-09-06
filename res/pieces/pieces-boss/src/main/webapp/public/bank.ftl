<!DOCTYPE html>
<html lang="en">
<head>
    <title>收款账户清单-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>收款账户管理</h3>
                <div class="extra">
                <@shiro.hasPermission name="bank:add">
                    <a class="btn btn-red" href="/bank/add"><i class="fa fa-plus"></i>增加新收款账户</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=pageInfo pageUrl="/bank/index" params=param/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="70">编号</th>
                            <th>开户行</th>
                            <th>开户人</th>
                            <th>账号</th>
                            <th width="200">状态</th>
                            <td width="200">操作</td>
                        </tr>
                        <tr>
                            <td><div class="ipt-wrap"><input name="id" type="text" class="ipt" value="${vo.id}"></div></td>
                            <td><div class="ipt-wrap"><input name="receiveBank" type="text" class="ipt" value="${vo.receiveBank}"></div></td>
                            <td><div class="ipt-wrap"><input name="receiveAccount" type="text" class="ipt" value="${vo.receiveAccount}"></div></td>
                            <td><div class="ipt-wrap"><input name="receiveBankCard" type="text" class="ipt" value="${vo.receiveBankCard}"></div></td>
                            <td>
                                <select name="status" id="state">
                                    <option <#if (!vo.status??)>selected</#if> value=""></option>
                                    <option <#if (vo.status==1)>selected</#if> value="1">激活</option>
                                    <option <#if (vo.status==0)>selected</#if> value="0">禁用</option>
                                </select>
                            </td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                        <#list pageInfo.list as pv>
                        <tr>
                            <td>${pv.id}</td>
                            <td>${pv.receiveBank}</td>
                            <td>${pv.receiveAccount}</td>
                            <td>${pv.receiveBankCard}</td>
                            <td><#if (pv.status==0)>禁用</#if><#if (pv.status==1)>激活</#if></td>
                            <td>
                            <@shiro.hasPermission name="bank:edit">
                                <a href="/bank/edit/${pv.id}">配置</a>
                            </@shiro.hasPermission>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/laydate/laydate.js"></script>

    <script>
    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                pageNum:${pageInfo.pageNum},
                pageSize:${pageInfo.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    $("#submit").click(function(){
                        page.fn.filter();
                    });

                    $("#reset").click(function(){
                        $('.tc :input').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/bank/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;
                    var params = [];
                    $ipts.each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=url+"&"+params.join('&');
                }
            }
        }
        //加载页面js
        $(function() {
            page.fn.init();
        });
    })(jQuery);

        
    </script>
</body>
</html>