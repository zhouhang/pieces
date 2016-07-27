<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>询价记录-饮片B2B</title>
</head>

<body>

    <#include "./inc/header-center.ftl"/>

    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>询价记录</h3>
                    <div class="extra"></div>
                </div>

                <div class="fa-table">
                	<div class="filter">
                        <form action="">
                        <button id="search_btn" class="btn btn-red btn-submit" type="button">查询</button>
                            <label><span>商品名称：</span><input class="ipt" name="commodityName" type="text"></label>
                            <label><span>询价日期：</span><input class="ipt date" name="startDate" type="text" id="start"  value=""><em>-</em><input class="ipt date" name="endDate" type="text" id="end"  value=""></label>
                        </form>     
                	</div>

                	<div class="fa-chart-d">
                        <#if billsPage??&&billsPage.list?has_content>
                            <div class="group">
                                <div class="tr th">
                                    <div class="td w1">商品名称</div>
                                    <div class="td w2">切制规格</div>
                                    <div class="td w3">等级</div>
                                    <div class="td w4">产地</div>
                                    <div class="td w5">数量<span>（公斤）</span></div>
                                    <div class="td w6">期望单价<span>（元/公斤）</span></div>
                                    <div class="td w7">期望交货日期</div>
                                    <div class="td w8">裸价<span>（元/公斤）</span></div>
                                    <div class="td w9">报价有效期至</div>
                                    <div class="td w10">操作</div>
                                </div>
                            </div>

                            <#list billsPage.list as bill>
                                <div class="group">
                                    <div class="tr hd">
                                        <a class="fr c-blue" href="#">订购已选商品</a>
                                        <span>询价单号：${bill.code!}</span>
                                        <span>询价日期：${bill.createTime?string("yyyy-MM-dd")}</span>
                                    </div>
                                    <#list bill.enquiryCommoditys as commodity>
                                        <div class="tr">
                                            <div class="td w1"><label><input class="cbx" type="checkbox">${commodity.commodityName!}</label></div>
                                            <div class="td w2">${commodity.specs!}</div>
                                            <div class="td w3">${commodity.level!}</div>
                                            <div class="td w4">${commodity.origin!}</div>
                                            <div class="td w5">${commodity.amount!}</div>
                                            <div class="td w6">${commodity.expectPrice!}</div>
                                            <div class="td w7">
                                            <#if commodity.expectDate??>
                                                ${commodity.expectDate?string("yyyy-MM-dd")}
                                            </#if>
                                            </div>
                                            <div class="td w8">${commodity.myPrice!}</div>
                                            <div class="td w9">
                                            <#if commodity.expireDate??>
                                                ${commodity.expireDate?string("yyyy-MM-dd")}
                                            </#if>
                                            </div>
                                            <div class="td w10"><a href="#">订购</a></div>
                                        </div>
                                    </#list>
                                </div>
                            </#list>
                        </#if>
                    </div>
                    <#if billsPage??>
                        <@p.pager inPageNo=billsPage.pageNum-1 pageSize=billsPage.pageSize recordCount=billsPage.total toURL="/center/enquiry/record"/>
                    </#if>

                </div>
            </div>
        </div>
    </div><!-- member-box end -->

    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
	
	<!-- 输入框联想 start -->
    <div class="suggestions" id="suggestions">
		<div class="hd">
			<div class="group">
				<span class="w1">商品名称</span><span class="w2">切制规格</span><span class="w3">等级</span><span class="w4">产地</span>
			</div>
		</div>
		<div class="bd"></div>
	</div><!-- 输入框联想 end -->

    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script>
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${pageNum},
                pageSize:${pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                    this.filter();
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD',
                        min: '2016-7-1', //设定最小日期为当前日期
                        max: laydate.now(), //最大日期
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                             end.min = datas; //开始日选好后，重置结束日的最小日期
                             end.start = datas; //将结束日的初始值设定为开始日
                             $('#start').attr('title', datas);
                        }
                    };
                    var end = {
                        elem: '#end',
                        format: 'YYYY/MM/DD',
                        min: '2016-7-1',
                        max: laydate.now(),
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                            start.max = datas; //结束日选好后，重置开始日的最大日期
                            $('#end').attr('title', datas);
                        }
                    };
                    laydate(start);
                    laydate(end);
                },filter: function() {
                    var $ipts = $('.filter .ipt');
                    var url="center/enquiry/record?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
                    })
                }

            }
        }
        //加载页面js
        $(function() {
            page.fn.init();
        });
    </script>
</body>
</html>