<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>询价记录-上工好药</title>
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
                                    <div class="td w2">片型</div>
                                    <div class="td w3">规格等级</div>
                                    <div class="td w4">产地</div>
                                    <div class="td w8">单价<span>（元/公斤）</span></div>
                                    <div class="td w9">报价有效期至</div>
                                    <div class="td w10">操作</div>
                                </div>
                            </div>
                        <#if billsPage??&&billsPage.list?has_content>
                            <#list billsPage.list as bill>
                                <div class="group">
                                    <div class="tr hd">
                                        <a data-billid="${bill.id!}" data-status="${bill.status!}" class="fr c-blue" href="/center/enquiry/index?billId=${bill.id!}">重新询价</a>
                                        <label><input class="cbx" type="checkbox">全选</label>
                                        <span>询价单号：${bill.code!}</span>
                                        <span>询价日期：${bill.createTime?string("yyyy-MM-dd")}</span>
                                    </div>

                                    <#list bill.enquiryCommoditys as commodity>
                                        <div class="tr">
                                            <div class="td w1"><label><#if commodity.myPrice??&&commodity.expireDate??&&(commodity.expireDate?date>.now?date)><input class="cbx" type="checkbox" name="commodity" value="${commodity.id!}"> </#if>${commodity.commodityName!}</label></div>
                                            <div class="td w2">${commodity.specs!}</div>
                                            <div class="td w3">${commodity.level!}</div>
                                            <div class="td w4">${commodity.origin!}</div>
                                            <div class="td w8">${commodity.myPrice!}</div>
                                            <div class="td w9">
                                            <#if commodity.expireDate??>
                                                ${commodity.expireDate?string("yyyy-MM-dd")}
                                            </#if>
                                            </div>
                                            <div class="td w10">
                                                <#if commodity.myPrice??&&commodity.expireDate??&&(commodity.expireDate?date>.now?date)>
                                                    <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                                                    <a href="javascript:void(0);" onclick="page.fn.orderCommodity(${commodity.id!})">订购</a>
                                                    </#if>
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
	
	<form action="/center/order/create" method="post" id="orderForm">
		<input type="hidden" name="commodityIds" id="commodityIds" value="">
	</form>
	
    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
	
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
                },
                filter: function() {
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
                },
                expand: function() {
                    var
                        $table = $('.fa-chart-d');
                        self = this,
                        txt = ['展开 <i class="fa fa-chevron-down"></i>', '收起 <i class="fa fa-chevron-up"></i>'];

                    $table.on('click', '.expand', function() {
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
                    $table.find('.group:gt(0)').each(function() {
                    	var $btnBuy = $(this).find('.hd .c-blue'),
                            $cbs = $(this).find('.w1 .cbx'),
                            status = $btnBuy.data("status");
                        
                        if ($cbs.length === 0) {
                            if(status !='0'){
                                $btnBuy.remove();
                            }
                            return true; // containue
                        } else {
                            <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                            $btnBuy.attr('href', 'javascript:;').html('订购已选商品');
                            </#if>
                        }
                        
                        $btnBuy.on('click',function(){
                        	var commodityStr = [],
                                commodityIds = '';
                                $cbxs = $(this).closest('.group').find('.w1 .cbx');

                        	$cbxs.each(function() {
                        		this.checked && commodityStr.push(this.value);
                        	})
                        	
                        	commodityIds = commodityStr.join(',');
                        	if(commodityIds){
                        		$("#commodityIds").val(commodityIds);
                        		$("#orderForm").submit();
                        	}
                        })
                    });


                    // 全选 &　反选
                    $table.on('click', '.hd .cbx', function() {
                        $(this).closest('.group').find('.cbx').prop('checked', this.checked);
                    })

                    // 单选
                    $table.on('click', '.w1 .cbx', function() {
                        var $cbxAll = $(this).closest('.group').find('.hd .cbx'),
                            $cbxs = $(this).closest('.group').find('.w1 .cbx'),
                            length = $cbxs.length,
                            count = 0;

                        if (this.checked) {
                            $cbxs.each(function(i) {
                                count += this.checked ? 1 : 0;
                            })
                        }
                        $cbxAll.prop('checked', count === length);
                    })
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
                            checkBox = '<label><input class="cbx" type="checkbox" value="'+item.id+'">';
                           <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                            order = '<a href="#">订购</a>';
                           <#else>
                                   order ="";
                           </#if>

                            flag = true;
                        }
                        modal.push('<div class="td w1">'+checkBox, item.commodityName,'</label></div>');
                        modal.push('<div class="td w2">', item.specs,'</div>');
                        modal.push('<div class="td w3">', item.level,'</div>');
                        modal.push('<div class="td w4">', item.origin,'</div>');
                        modal.push('<div class="td w8">', item.myPrice,'</div>');
                        modal.push('<div class="td w9">', self.formatDate(item.expireDate),'</div>');
                        modal.push('<div class="td w10">'+order+'</div>');
                        modal.push('</div>');
                    })
                    modal.push('</div>');
                   <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                    $expend.parent().find('.hd .c-blue').html('订购已选商品');
                   </#if>
                    return modal.join('');
                },
                formatDate: function(date) {
                    return date ? date.split(' ')[0] : '';
                },
                orderCommodity: function(commodityId){
                	if(commodityId != ""){
                		$("#commodityIds").val(commodityId);
                		$("#orderForm").submit();
                	}
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