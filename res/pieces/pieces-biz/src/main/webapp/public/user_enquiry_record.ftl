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
                        <button id="search_btn" class="btn btn-red btn-submit" type="button">查询</button>
                        ${enquiryRecordVo.startDate!}
                            <span>商品名称：</span>
                            <input class="ipt" value="${enquiryRecordVo.commodityName!}" name="commodityName" type="text">
                            <span class="ml">状态：</span>
                            <select name="status" id="status" class="slt">
                                <option value="">全部</option>
                                <option value="1">已报价</option>
                                <option value="0">未报价</option>
                                <option value="2">已过期</option>
                            </select>
                            <span class="ml">询价日期：</span>
                            <input class="ipt date" name="startDate" type="text" id="start"  value="${enquiryRecordVo.startDate!}"><em>-</em><input class="ipt date" name="endDate" type="text" id="end"  value="${enquiryRecordVo.endDate!}">
                    </div>
                    <div class="fa-chart-d">
                        <div class="group">
                            <div class="th">
                                <div class="td w1">选择</div>
                                <div class="td w2">商品名称</div>
                                <div class="td w3">片型</div>
                                <div class="td w4">规格等级</div>
                                <div class="td w5">产地</div>
                                <div class="td w6">单价<span>（元/公斤）</span></div>
                            </div>
                        </div>
                        <#if billsPage??&&billsPage.list?has_content>
                            <#list billsPage.list as bill>
                                <div class="group">
                                    <div class="hd <#if bill.status==0>hd-1<#else ><#if bill.expireDate?exists&&bill.expireDate?is_date&& ((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>hd-2<#else >hd-3</#if></#if>" >
                                        <div class="td w7">
                                            
                                            <#if bill.expireDate?exists&&bill.expireDate?is_date&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                            <input class="cbx" type="checkbox">
                                            <#else >
                                            <input class="cbx" type="checkbox" disabled>
                                            </#if>
                                            <span>询价单号：${bill.code!}</span>
                                            <span>询价日期：${(bill.createTime?date)!}</span>
                                            <#if bill.expireDate?exists&&bill.expireDate?is_date> <span>报价截止日期：${(bill.expireDate?date)!}</span></#if>
                                        </div>
                                        <div class="td w5">
                                            <em>状态：
                                                <#if bill.status==0>
                                                    未报价
                                                <#else >
                                                    <#if bill.expireDate?exists&&bill.expireDate?is_date&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                                        已报价
                                                    <#else >
                                                        已过期
                                                    </#if>
                                                </#if>
                                            </em>
                                        </div>
                                        <div class="td w6">
                                            <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                                                <#if  bill.status == 1 && bill.expireDate?exists&&bill.expireDate?is_date&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                                    <a data-billid="${bill.id!}" data-status="${bill.status!}" class="buy" href="javascript:;">订购</a>
                                                    <a data-billid="${bill.id!}" data-status="${bill.status!}" class="buy" data-down="down" href="javascript:;">导出</a>
                                                </#if>
                                            </#if>
                                        </div>
                                    </div>
                                    <#if bill.status==0>
                                    <!-- 未报价 -->
                                        <#list bill.enquiryCommoditys as commodity>
                                            <div class="bd enable">
                                                <div class="td w1"><input class="cbx" type="checkbox" name="commodity" value="" disabled></div>
                                                <div class="td w2">${commodity.commodityName!}</div>
                                                <div class="td w3">${commodity.specs!}</div>
                                                <div class="td w4">${commodity.level!}</div>
                                                <div class="td w5">${commodity.origin!}</div>
                                                <div class="td w6"></div>
                                            </div>
                                        </#list>
                                    <#else >
                                        <#list bill.enquiryCommoditys as commodity>
                                            <#if commodity.myPrice??&&(commodity.myPrice != 0)&&commodity.expireDate??&&((commodity.expireDate?date gte .now?date) || (commodity.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                                <div class="bd">
                                                    <div class="td w1">
                                                        <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                                                            <input class="cbx" type="checkbox" name="commodity" value="${commodity.id!}">
                                                        <#else>
                                                            <input class="cbx" type="checkbox" name="commodity" value="" disabled>
                                                        </#if>
                                                    </div>
                                                    <div class="td w2">${commodity.commodityName!}</div>
                                                    <div class="td w3">${commodity.specs!}</div>
                                                    <div class="td w4">${commodity.level!}</div>
                                                    <div class="td w5">${commodity.origin!}</div>
                                                    <div class="td w6">${commodity.myPrice!}</div>
                                                </div>
                                            <#else>
                                                <div class="bd enable">
                                                    <div class="td w1"><input class="cbx" type="checkbox" name="commodity" value="" disabled></div>
                                                    <div class="td w2">${commodity.commodityName!}</div>
                                                    <div class="td w3">${commodity.specs!}</div>
                                                    <div class="td w4">${commodity.level!}</div>
                                                    <div class="td w5">${commodity.origin!}</div>
                                                    <div class="td w6"><#if commodity.myPrice??&&commodity.myPrice == 0>
                                                        --<#else>${commodity.myPrice!}</#if></div>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
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
    
    <form action="/center/order/md5" method="post" id="orderForm">
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
                    $("#status").val(${enquiryRecordVo.status!});
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        choose: function(datas){
                             end.min = datas;
                        }
                    };
                    var end = {
                        elem: '#end',
                        choose: function(datas){
                            start.max = datas;
                        }
                    };
                    laydate(start);
                    laydate(end);
                },
                filter: function() {
                    var $ipts = $('.filter .ipt,.slt');
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

                    $table.on('click', '.expand', function(event, call) {
                        var $self = $(this);
                        var billId = $self.data("val")
                        if ($self.data('loader') === 'true') {
                            var status = $self.data('expand') === 0 ? 1 : 0;
                            $self.data('expand', status).html(txt[status]).prev().slideToggle();
                            if(call) call();
                        } else {
                            $.ajax({
                                url: 'center/enquiry/commodity',
                                dataType: 'json',
                                data: {billId: billId},
                                success: function(result) {
                                    $self.data('loader', 'true');

                                    if (result.status=="y") {
                                        result.data.splice(0, 10); // 去掉前10条数据
                                        $self.before(self.toHtml(result.data));
                                        $self.data('expand', '1').html(txt[1]).prev().slideDown();
                                        if(call) call();
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
                        var $btnBuy = $(this).find('.hd .buy'),
                            $cbs = $(this).find('.w1 .cbx'),
                            status = $btnBuy.data("status");
                        
                        $btnBuy.on('click',function(){
                            var commodityStr = [],
                                commodityIds = '';
                                $cbxs = $(this).closest('.group').find('.w1 .cbx:not(:disabled)');

                            $cbxs.each(function() {
                                this.checked && commodityStr.push(this.value);
                            })
                            
                            if (commodityStr.length === 0) {
                                $.notify({
                                    type: 'warn',
                                    title: '提示',
                                    text: $(this).data("down")== 'down'?'请先勾选要导出的商品':'请先勾选要订购的商品',
                                    delay: 3e3
                                });
                                return false;
                            }   
                            commodityIds = commodityStr.join(',');
                            if(commodityIds){
                                if ($(this).data("down")== 'down') {
                                    window.location.href = "/center/enquiry/download?ids=" +commodityIds;
                                } else {
                                    $("#commodityIds").val(commodityIds);
                                    $("#orderForm").submit();
//                                    window.location.href = "/center/order/create?commodityIds=" + commodityIds;
                                }
                            }
                        })
                    });


                    // 全选 &　反选
                    $table.on('click', '.hd .cbx:not(:disabled)', function() {
                        var that = $(this);
                        var expand = $(this).closest('.group').find('.expand');

                        if (expand.length === 1 && expand.data('loader') !== 'true') {
                            expand.trigger('click', function (){
                                that.closest('.group').find('.w1 .cbx:not(:disabled)').prop('checked', that[0].checked);
                            })
                        } else {
                            that.closest('.group').find('.w1 .cbx:not(:disabled)').prop('checked', that[0].checked);
                        }
                    })

                    // 单选
                    $table.on('click', '.w1 .cbx:not(:disabled)', function() {
                        var $cbxAll = $(this).closest('.group').find('.hd .cbx:not(:disabled)'),
                            $cbxs = $(this).closest('.group').find('.w1 .cbx:not(:disabled)'),
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
                toHtml: function(data) {
                    var self = this,
                        modal = [];
                    modal.push('<div class="more" style="display:none;">');
                    $.each(data, function(i, item) {

                        var checkBox = '',
                            myPrice = (item.myPrice != 0 && item.myPrice!= null ) ? item.myPrice : '--';
                        if((item.myPrice != 0 &&item.myPrice!= null )&&item.expireDate!=null&&new Date(item.expireDate.replace(/-/g,'/').split(' ')[0] + ' 23:59:59')>=new Date()){
                            checkBox = '<input class="cbx" type="checkbox" value="'+item.id+'">';
                            modal.push('<div class="bd">');
                        } else {
                            checkBox = '<input class="cbx" type="checkbox" value="" disabled>';
                            modal.push('<div class="bd enable">');
                        }
                        modal.push('<div class="td w1">', checkBox, '</div>');
                        modal.push('<div class="td w2">', item.commodityName, '</div>');
                        modal.push('<div class="td w3">', item.specs, '</div>');
                        modal.push('<div class="td w4">', item.level, '</div>');
                        modal.push('<div class="td w5">', item.origin, '</div>');
                        modal.push('<div class="td w6">', myPrice, '</div>');
                        modal.push('</div>');
                    })
                    modal.push('</div>');
                    return modal.join('');
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