<!DOCTYPE html>
<html lang="en">
<head>
    <title>商品排序-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>品种信息</dt>
                    <dd>
                        <a href="/breed/edit/${breed.id}">品种信息</a>
                        <a class="curr" href="javascript:return false;">商品排序</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-chevron-right"></i>修改品种“${breed.name}”</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <button id="submit" type="button" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3><span class="fr">数字越大越靠前显示</span>商品排序</h3>
                        <div class="chart">
                            <table>
                                <thead>
                                <tr>
                                    <th width="70">编号</th>
                                    <th width="90">商品名称</th>
                                    <th width="90">片型</th>
                                    <th width="90">原料产地</th>
                                    <th>规格等级</th>
                                    <th width="90">状态</th>
                                    <th width="100">排序</th>
                                </tr>
                                </thead>
                                <tfoot></tfoot>
                                <tbody id="commodity-list">
                                <#list list as commodity>
                                <tr>
                                    <td>${commodity.id}</td>
                                    <td>${commodity.name}</td>
                                    <td>${commodity.spec}</td>
                                    <td>${commodity.originOf}</td>
                                    <td>${commodity.level}</td>
                                    <td><#if commodity.status ==1>激活<#else>禁用</#if></td>
                                    <td><input data-id="${commodity.id}" name="sort" type="text" class="ipt tc" value="${commodity.sort}"></td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>

    <#include "./inc/footer.ftl"/>

    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
    <script>
        var _global = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                },
                formValidate: function() {
                    $("#myform").validator({
                        fields: {
//                            type: 'required',
//                            name: 'required'
                        }
                    });

                    $('#submit').on('click', function () {
                        $('#myform').isValid(function (v) {
                            if (v) {
                                _global.fn.submit();
                            }
                        })
                    });
                },
                submit: function () {
                    var commoditys = new Array();
                    var trs = $("#commodity-list tr");
                    $.each(trs, function (k, v) {
                        commoditys.push({id:$($(v).find("input")[0]).data("id"),sort:$($(v).find("input")[0]).val()})
                    })
                    console.log(JSON.stringify(commoditys));
                    $.ajaxSetup({
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8'
                        }
                    });
                    $.post("/breed/commodity",JSON.stringify(commoditys),function(result){
                        if (result.status=="y") {
                            window.location.reload();
                        }
                    })
                }
            }
        }
        $(function() {
            _global.fn.init();
        })
    </script>
</body>
</html>