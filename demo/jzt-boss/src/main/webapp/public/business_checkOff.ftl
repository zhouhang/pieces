<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>交易取消审核</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/datetimepicker/jquery.datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/jquery-ui/jquery-ui.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#import 'macro.ftl' as tools>
<!-- head start -->
<#include "home/top.ftl" />
<!-- head over -->
<div class="wapper">
<!-- nav start -->
<#include "home/left.ftl" />
<!-- nav over -->

<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">交易取消审核</h1>
            <form action="/ordercancel" method="post" id="queryForm">
                <ul class="form-search ac-search">
                    <li><span>订单编号：</span><input class="text text-4" type="text" name="orderId" value="${orderCancelQuery.orderId!''}"/>&nbsp;
                         <span>审核状态：</span><select class="selecte text-4" name="examineState">
                            <option value="">全部</option>
                            <#if stateMap??>
                            	<#list stateMap?keys as key>
                            		<option value="${key!''}" <#if orderCancelQuery.examineState == key>selected</#if>>${stateMap[key]!'' }</option>
                            	</#list>
                            </#if>
                        </select>&nbsp;
                        <span>申请时间：</span><input class="text text-date" id="date_b" type="text" name="applyStartDate" value="${orderCancelQuery.applyStartDate!''}"/> — <input class="text text-date" id="date_e" type="text" name="applyEndDate" value="${orderCancelQuery.applyEndDate!''}"/>&nbsp;
                        <!--add by fanyuna 2015.07.30 增加 订单买家或卖家关联的业务人员 搜索条件-->
                        <span>业务人员：</span><input class="text text-4" type="text" name="salesmanName" value="${orderCancelQuery.salesmanName!''}"/>&nbsp;
&nbsp;<a href="javascript:;" onclick="resetForm('queryForm')" class="col_999">清除</a>&nbsp;
                      <input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1 mt20">
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="10%" class="bgf5">订单编号</th>
                        <th width="10%" class="bgf5">标题</th>
                        <th width="8%" class="bgf5">申请人</th>
                        <th width="8%" class="bgf5">卖家</th>
                        <th width="7%" class="bgf5">订单总价</th>
                        <th width="7%" class="bgf5">实际付款</th>
                        <th width="6%" class="bgf5">审核状态</th>
                        <th width="10%" class="bgf5">申请时间</th>
                        <th width="8%" class="bgf5">交易取消原因</th>
                        <th width="8%" class="bgf5">买方业务人员</th>
                        <th width="8%" class="bgf5">卖方业务人员</th>
                        <th width="15%" class="bgf5">操作</th>
                    </tr>
                    <#if page.results?? && (page.results?size>0)>
                    	<#list page.results as orderCancel>
		                    <tr>
		                        <td>
		                        	<@shiro.hasPermission name="交易取消审核-查看退款申请详情">
		                        		<a class="col_blue" name="nub" href="javascript:;" data-id="${orderCancel.orderId!''}" data-reclick="n">${orderCancel.orderId!''}</a>
		                        	</@shiro.hasPermission>
		                        	<@shiro.lacksPermission name="交易取消审核-查看退款申请详情">
		                        		${orderCancel.orderId!''}
		                        	</@shiro.lacksPermission>
		                        </td>
		                        <td>${orderCancel.title!''}</td>
		                        <td class="caption">${orderCancel.applicantName!''} <span class="operate-1 wid"><div class="tips tipa" align="left"><span class="sj"></span>姓名/公司名：${orderCancel.applicantRealName!''}<br/>联系方式：${orderCancel.applicantMobile!''}</div></span></td>
		                        <td class="caption">${orderCancel.seller!''}<span class="operate-1 wid"><div class="tips tipa" align="left"><span class="sj"></span>姓名/公司名：${orderCancel.sellerRealName!''}<br/>联系方式：${orderCancel.sellerMobile!''}</div></span></td>
		                        <td><@tools.money num=(orderCancel.totalPrice)!0 format="0.##"/>元</td>
		                        <td><@tools.money num=(orderCancel.actualPayment)!0 format="0.##"/>元</td>
		                        <td>${stateMap[orderCancel.examineState]!'' }</td>
		                        <td>${orderCancel.applicantDate?string("yyyy-MM-dd HH:mm:ss")!''}</td>
		                        <td>${appealTypeMap[orderCancel.cancelType]!'' }</td>
		                        <td>${orderCancel.buyerSalesmanName!''}</td>
		                        <td>${orderCancel.sellerSalesmanName!''}</td>
		                        <#if orderCancel.examineState != '1'>
	                            	<td></td>
                            	<#else>
			                        <td><@shiro.hasPermission name="交易取消审核-通过"><a href="javascript:;" class="blue" name="finished" data-id="${orderCancel.orderId!''}">通过</a></@shiro.hasPermission>&nbsp;<@shiro.hasPermission name="交易取消审核-驳回申请"><a href="javascript:;" class="blue" name="reject" data-id="${orderCancel.orderId!''}">驳回申请</a></@shiro.hasPermission> </td>
                            	</#if>
		                    </tr>
                    	</#list>
                    <#else>
                    	<tr>
                    		<td colspan="12">暂无数据!</td>
                    	</tr>
                    </#if>

                </table>
            </div>

            <@tools.pages page=page form="queryForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 驳回原因 -->
<div class="" id="rejectCause" title="驳回原因">
    <div class="box">
        <form id="rejectForm">
            <ul class="form-1">
                <li>
                    <label><strong style="font-size: 12px;">请填写驳回原因：</strong></label>
                    <p>
                        <input type="hidden" name="orderId">
                        <textarea class="text text-1" style="width: 500px; height: 100px;" name="rejectReason"></textarea>
                    </p>
                    <label></label>
                    <p><span class="col_red2" id="errorMsg"></span></p>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 驳回原因 end -->
<!--申请退款 弹层-->
<div class="cancel-box">
    <div class="close"></div>
    <h2 class="title">申请退款</h2>
    <div class="refund-box">
    </div>
</div>
<!--申请退款 弹层 end-->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
    $(function(){
        //日期控件
        $('#date_b').click(function(){
            WdatePicker({
                maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
                readOnly:true
            });
        });
        $('#date_e').click(function(){
            WdatePicker({
                minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
                readOnly:true
            });
        });
        //查看退款详情
        function bghui(){
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
        }
        $('td a[name=nub]').on('click',function(){
            getOrderCancelInfo(this);
        });
        $('.close').on('click',function(){
            $('.cancel-box').hide();
            $('.cancel-box .refund-box').empty();
            $('.bghui').remove();
        });

        //申请人pop层
        $('td.caption').hover(
            function(){
                $(this).children('.operate-1').show();
            },
            function(){
                $(this).children('.operate-1').hide();
            }
        )

        // Link to open the dialog
        $( "a[name=reject]" ).click(function( event ) {
        	//弹层初始化
        	$("#rejectForm input:hidden").val("");
	        $("#rejectForm textarea").val("");
	        $("#errorMsg").text("");
	        //弹出驳回原因
            bghui();
            $( "#rejectCause" ).dialog( "open" );
            event.preventDefault();
            //设定订单编号
            $("#rejectForm input:hidden").val($(this).data("id"));
        });
        //驳回原因弹层
        $( "#rejectCause" ).dialog({
            autoOpen: false,
            width: 794,
            buttons: [
                {
                    text: "提交",
                    click: function() {
                        rejectAppeal();
                    }
                },
                {
                    text: "取消",
                    click: function() {
                        $( this ).dialog( "close" );
                        $('.bghui').remove();
                    }
                }
            ]
        });
        
        $("a[name=finished]").click(function(){
        	var orderId = $(this).data("id");
        	customConfirm("确定要通过订单编号为【" + orderId + "】的退款申请吗？", function(){
        		finishAppeal(orderId);
        	});
        });
    })
    
    //获取退款申请详情
    function getOrderCancelInfo(obj){
    	var _this = $(obj),
    		orderId = _this.data("id"),
    		reclick = _this.data("reclick");
    	if(reclick == "n") {
    		_this.data("reclick", "y");
    	} else {
    		return false;
    	}
    	var imgServer = "${RESOURCE_IMG_UPLOAD}";
    	$.ajax({
    		type:"POST",
    		url:"/ordercancel/info",
    		data:{orderId:orderId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				bghui();
    				$('.cancel-box').show();
    				var orderCancelInfo = parseJson(data.result);
    				var html = '';
    				html += '<div class="box-1 fl">';
    				html += '<ul>';
    				html += '<li><label>订单编号：</label><span>' + orderCancelInfo.orderId + '</span></li>';
    				html += '<li><label>取消原因：</label><span>' + orderCancelInfo.appealTypeName + '</span></li>';
    				html += '<li><label>具体描述：</label><span><div class="describe">' + orderCancelInfo.reason + '</div></span></li>';
    				html += '<li><label>证据图片：</label>';
    				if(!orderCancelInfo.evidencePic){
    					html += '<span>无</span></li>';
    				} else {
	    				html += '<div class="box-2">';
	    				html += '<ul>';
	    				var pics = orderCancelInfo.evidencePic.split(",");
	    				for(var i = 0; i < pics.length; i++){
		    				html += '<li><span><img src="' + imgServer +'/'+ pics[i] +'" /><p>证据' + (i+1) + '</p></span></li>';
	    				}
	    				html += '</ul>';
	    				html += '</div></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				$('.cancel-box .refund-box').append(html);
    			} else {
    				customAlert(data.result);
    			}
    			_this.data("reclick", "n");
    		},
    		error:function(textStatus){
    			customAlert("操作失败！");
    			_this.data("reclick", "n");
    		}
    	});
    }
    
    //完成赔付
    function finishAppeal(orderId){
    	$.ajax({
    		type:"POST",
    		url:"/ordercancel/finish",
    		data:{orderId:orderId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				customAlert(data.result, function(){
    					//重新刷新页面
    					$("#queryForm").submit();
    				});
    			} else {
    				customAlert(data.result);
    			}
    		},
    		error:function(textStatus){
    			customAlert("操作失败！");
    		}
    	});
    }
    
    //驳回申请
    function rejectAppeal(){
    	$("#errorMsg").text("");
    	var rejectReason = $.trim($("#rejectForm textarea").val());
    	var orderId = $("#rejectForm input:hidden").val();
    	//驳回原因不能为空
    	if("" == rejectReason){
    		$("#errorMsg").text("驳回原因不能为空！");
    		return false;
    	} else if(rejectReason.length > 400){
    		$("#errorMsg").text("驳回原因字符长度超出限制！");
    		return false;
    	}
    	$.ajax({
    		type:"POST",
    		url:"/ordercancel/reject",
    		data:{orderId:orderId,rejectReason:rejectReason},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				//关闭提交弹层
    				$("#rejectCause").dialog( "close" );
                    $('.bghui').remove();
    				customAlert(data.result, function(){
	    				//重新刷新页面
	    				$("#queryForm").submit();
    				});
    			} else {
    				$("#errorMsg").text(data.result);
    			}
    		},
    		error:function(textStatus){
    			$("#errorMsg").text("操作失败！");
    		}
    	});
    }
    
    
    //自定义的alert框
    function customAlert(str, fnOk){
    	bghui();
    	Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-blue',
                ev:{click:function(){
                	 disappear();
                     $(".bghui").remove();
                     if(fnOk){
                     	fnOk();
                     }
                 }
               }
            }]
	    });
    }
    
    //自定义的确认框
    function customConfirm(str, fnOk){
    	bghui();
    	Alert({
            str: str,
            buttons:
            [{
                name:'确定',
                id:'confirmOK',
                classname:'btn-blue',
                ev:{click:function(){
                	 disappear();
                     $(".bghui").remove();
                	 if(fnOk){
                	 	fnOk();
                	 }
                 }
               }
            },
            {
                name:'取消',
                id:'confirmCancel',
                classname:'btn-style',
                ev:{click:function(){
                	 disappear();
                     $(".bghui").remove();
                 }
               }
            }]
	    });
    }
    
    //清除
    function resetForm(id){
    	$("#" + id + " :text").val("");
    	$("#" + id + " select").val("");
    }
    
	function parseJson(text){
		try{
		    return JSON.parse(text);//ie 89 ff ch
		}catch(e){
		    return eval('('+text+')'); //ie7
		}
	}
</script>
</body>
</html>