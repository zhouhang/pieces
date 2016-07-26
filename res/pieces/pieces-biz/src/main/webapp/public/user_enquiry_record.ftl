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
                        <button class="btn btn-red btn-submit" type="button">查询</button>
                            <label><span>商品名称：</span><input class="ipt" type="text"></label>
                            <label><span>询价日期：</span><input class="ipt date" type="text" id="start"  value=""><em>-</em><input class="ipt date" type="text" id="end"  value=""></label>
                        </form>     
                	</div>
				
                	<div class="fa-chart-d">
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

                        <div class="group">
                            <div class="tr hd">
                                <a class="fr c-blue" href="#">重新询价</a>
                                <span>询价单号：E201606201024001</span>
                                <span>询价日期：2016-06-20</span>
                            </div>
                            <div class="tr">
                                <div class="td w1">艾绒</div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8"></div>
                                <div class="td w9"></div>
                                <div class="td w10"></div>
                            </div>
                            <div class="tr">
                                <div class="td w1">艾绒</div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8"></div>
                                <div class="td w9"></div>
                                <div class="td w10"></div>
                            </div>
                        </div> 

                        <div class="group">
                            <div class="tr hd">
                                <a class="fr c-blue" href="#">订购已选商品</a>
                                <span>询价单号：E201606201024001</span>
                                <span>询价日期：2016-06-20</span>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                        </div>

                        <div class="group">
                            <div class="tr hd">
                                <a class="fr c-blue" href="#">订购已选商品</a>
                                <span>询价单号：E201606201024001</span>
                                <span>询价日期：2016-06-20</span>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1">艾绒</div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8"></div>
                                <div class="td w9"></div>
                                <div class="td w10"></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1">艾绒</div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8"></div>
                                <div class="td w9"></div>
                                <div class="td w10"></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="tr">
                                <div class="td w1"><label><input class="cbx" type="checkbox">艾绒</label></div>
                                <div class="td w2">个</div>
                                <div class="td w3">1</div>
                                <div class="td w4">湖北省</div>
                                <div class="td w5">60</div>
                                <div class="td w6">20</div>
                                <div class="td w7">2016-06-20</div>
                                <div class="td w8">24</div>
                                <div class="td w9">2016-06-20</div>
                                <div class="td w10"><a href="#">订购</a></div>
                            </div>
                            <div class="expand">展开 <i class="fa fa-chevron-down"></i></div>
                        </div>
                    </div>

                    <div class="pagin">
                        <span class="disabled">上一页</span>
                        <span class="curr">1</span>
                        <a href="?page=2">2</a>
                        <a href="?page=3">3</a>
                        <a href="?page=4">4</a>
                        <a href="?page=5">5</a>
                        <a href="?page=2">下一页</a>
                        <a href="?page=2">尾页</a>
                        <em>共 284 个商品 / 共29页</em>
                    </div>
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
                id: "page"
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: laydate.now(), //设定最小日期为当前日期
                        max: '2099-06-16 23:59:59', //最大日期
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
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: laydate.now(),
                        max: '2099-06-16 23:59:59',
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                            start.max = datas; //结束日选好后，重置开始日的最大日期
                            $('#end').attr('title', datas);
                        }
                    };
                    laydate(start);
                    laydate(end);
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