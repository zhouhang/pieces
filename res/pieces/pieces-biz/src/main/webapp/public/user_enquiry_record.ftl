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
                        ${enquiryRecordVo.startDate!}
                            <label><span>商品名称：</span><input class="ipt" value="${enquiryRecordVo.commodityName!}" name="commodityName" type="text"></label>
                            <label><span>询价日期：</span><input class="ipt date" name="startDate" type="text" id="start"  value="${enquiryRecordVo.startDate!}"><em>-</em><input class="ipt date" name="endDate" type="text" id="end"  value="${enquiryRecordVo.endDate!}"></label>
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
                        <#if billsPage??&&billsPage.list?has_content>
                            <#list billsPage.list as bill>
                                <div class="group">
                                    <div class="tr hd">
                                        <a data-billid="${bill.id!}" data-status="${bill.status!}" class="fr c-blue" href="/center/enquiry/index?billId=${bill.id!}">重新询价</a>
                                        <span>询价单号：${bill.code!}</span>
                                        <span>询价日期：${bill.createTime?string("yyyy-MM-dd")}</span>
                                    </div>

                                    <#list bill.enquiryCommoditys as commodity>

                                        <div class="tr">
                                            <div class="td w1"><label>  <#if commodity.myPrice??&&commodity.expireDate??&&(commodity.expireDate?date>.now?date)><input class="cbx" type="checkbox" name="commodity" value="${commodity.id!}"> </#if>${commodity.commodityName!}</label></div>
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
                                            <div class="td w10">
                                                <#if commodity.myPrice??&&commodity.expireDate??&&(commodity.expireDate?date>.now?date)>
                                                    <a href="/center/order/create?commodity=${commodity.id!}">订购</a>
                                                </#if>
                                            </div>
                                        </div>
                                    </#list>
                                    <#if (bill.enquiryCommoditys?size>=10) >
                                        <div data-val="${bill.id!}" class="expand">展开 <i class="fa fa-chevron-down"></i></div>
                                    </#if>
                                </div>

                            </#list>
                            <#else>
                                <div class="empty">
                                    <p>您还没有询价记录，现在<a href="/center/enquiry/index">立即询价</a>吧！</p>
                                </div>
                        </#if>
                    </div>
                    <#if billsPage??>
                        <@p.pager inPageNo=billsPage.pageNum-1 pageSize=billsPage.pageSize recordCount=billsPage.total toURL="/center/enquiry/record?${enquiryRecordParam}"/>
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
                pageNum:${billsPage.pageNum},
                pageSize:${billsPage.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    this.dateInit();
                    this.filter();
                    this.expand();

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
                    var url="/center/enquiry/record?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
                    })
                },expand: function() {
                    var
                            self = this,
                            txt = ['展开 <i class="fa fa-chevron-down"></i>', '收起 <i class="fa fa-chevron-up"></i>'];

                    $('.fa-table').on('click', '.expand', function() {
                        var $self = $(this);
                        var billId = $self.data("val")
                        if ($self.data('loader') === 'true') {
                            var status = $self.data('expand') === 0 ? 1 : 0;
                            $self.data('expand', status).html(txt[status]).prev().slideToggle();
                        } else {
                            $.ajax({
                                url: 'center/enquiry/commodity',
                                dataType: 'json',
                                data: {billId: billId},
                                success: function(result) {
                                    $self.data('loader', 'true');

                                    if (result.status=="y") {
                                        result.data.splice(0, 10); // 去掉前10条数据
                                        $self.before(self.toHtml(result.data, $self));
                                        $self.data('expand', '1').html(txt[1]).prev().slideDown();
                                    }else{
                                        $.notify({
                                            type: 'error',
                                            title: '提交错误',
                                            text: result.info
                                        })
                                    }
                                }
                            })
                        }
                    })


                    // 询价操作
                    $('.fa-chart-d').find('.group:gt(0)').each(function() {
                        var status = $(this).find('.hd .c-blue').data("status");
                        var $AgaleTag  = $(this).find('.hd .c-blue');
                        if ($(this).find('.cbx').length === 0) {
                            if(status=='0'){
                                $AgaleTag.attr("href","/center/enquiry/index?billId="+$AgaleTag.data("billid"))
                                $AgaleTag.html('重新询价');
                            }else{
                                $(this).find('.hd .c-blue').remove();
                            }
                        } else {
                            $AgaleTag.html('订购已选商品');
                            $AgaleTag.attr("href","#");
                        }
                    });
                },
                // 插入html
                toHtml: function(data, $expend) {
                    var
                        self = this,
                        flag = false, // 判断是否有复选框
                        modal = [];

                    modal.push('<div class="more" style="display:none;">');
                    $.each(data, function(i, item) {
                        modal.push('<div class="tr">');
                        var checkBox = "";
                        var order = "";
                        if(item.myPrice!=null&&item.expireDate!=null&&new Date(item.expireDate)>new Date()){
                            checkBox = '<label><input class="cbx" type="checkbox">';
                            order = '<a href="#">订购</a>';
                            flag = true;
                        }
                        modal.push('<div class="td w1">'+checkBox, item.commodityName,'</label></div>');
                        modal.push('<div class="td w2">', item.specs,'</div>');
                        modal.push('<div class="td w3">', item.level,'</div>');
                        modal.push('<div class="td w4">', item.origin,'</div>');
                        modal.push('<div class="td w5">', item.amount,'</div>');
                        modal.push('<div class="td w6">', item.expectPrice,'</div>');
                        modal.push('<div class="td w7">', self.formatDate(item.expectDate),'</div>');
                        modal.push('<div class="td w8">', item.myPrice,'</div>');
                        modal.push('<div class="td w9">', self.formatDate(item.expireDate),'</div>');
                        modal.push('<div class="td w10">'+order+'</div>');
                        modal.push('</div>');
                    })
                    modal.push('</div>');
                    $expend.parent().find('.hd .c-blue').html('订购已选商品');
                    return modal.join('');
                },
                formatDate: function(date) {
                    return date ? date.split(' ')[0] : '';
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